package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.*;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.repositories.UserRepo;
import com.example.springdatabasicdemo.repositories.UserRoleRepo;
import com.example.springdatabasicdemo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<UUID> {
    final ModelMapper modelMapper;
    private UserRepo userRepo;

    private UserRoleRepo urRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Autowired
    public void setUserRoleRepo(UserRoleRepo urRepo) {
        this.urRepo = urRepo;
    }

    public UserServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto register(UserDto entity) {
        if (entity.getId()!=null) {
            Optional<User> user = userRepo.findById(entity.getId());
            if (user.isPresent()) {
                entity.setActive(Boolean.TRUE);
                User u = modelMapper.map(entity, User.class);
                u.setCreated(user.get().getCreated());
                u.setModified(new Date());
                return modelMapper.map(userRepo.save(u), UserDto.class);
            }
        }
        entity.setActive(Boolean.TRUE);
        User u = modelMapper.map(entity,User.class);
        u.setCreated(new Date());
        return modelMapper.map(userRepo.save(u), UserDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            UserDto dto = modelMapper.map(user,UserDto.class);
            dto.setActive(Boolean.FALSE);
            User u = modelMapper.map(dto,User.class);
            u.setModified(new Date());
            userRepo.save(u);
        }
    }

    @Override
    public void delete(UserDto entity) {
        Optional<User> user = userRepo.findById(entity.getId());
        if (user.isPresent()) {
            UserDto dto = modelMapper.map(user,UserDto.class);
            dto.setActive(Boolean.FALSE);
            User u = modelMapper.map(dto,User.class);
            u.setModified(new Date());
            userRepo.save(u);
        }
    }

    @Override
    public List<UserDto> getAll() {
        return userRepo.findAll().stream().map((c)->modelMapper.map(c,UserDto.class)).collect(Collectors.toList());
    }


    @Override
    public Optional<UserDto> getById(UUID id) {
        return Optional.ofNullable(modelMapper.map(userRepo.findById(id),UserDto.class));
    }
    public Optional<UserDto> getByUsername(String username){
        return Optional.ofNullable(modelMapper.map(userRepo.findByUsername(username),UserDto.class));
    }
    @Override
    public AddUserDto register(AddUserDto user) {

        User u = modelMapper.map(user, User.class);
        u.setUserRole(urRepo.findByRole (user.getRoleName()).orElse(null));
        u.setActive(false);
        return modelMapper.map(userRepo.save(u),AddUserDto.class);
    }
    @Override
    public List<ShowUserDto> getAllShow() {
        return userRepo.findAll().stream().map((u)->modelMapper.map(u,ShowUserDto.class)).collect(Collectors.toList());
    }
    @Override
    public Optional<DetailedUserDto> getByIdDetailed(UUID id) {
        Optional<User> userOptional = userRepo.findByIdWithUserRole(id);

        return userOptional.map(user -> modelMapper.map(user, DetailedUserDto.class));
    }
}
