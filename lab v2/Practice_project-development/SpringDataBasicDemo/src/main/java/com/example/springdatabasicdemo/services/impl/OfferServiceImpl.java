package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.*;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.Model;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.repositories.ModelRepo;
import com.example.springdatabasicdemo.repositories.OfferRepo;
import com.example.springdatabasicdemo.repositories.UserRepo;
import com.example.springdatabasicdemo.services.AuthService;
import com.example.springdatabasicdemo.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class OfferServiceImpl implements OfferService<UUID> {
    final ModelMapper modelMapper;
    private OfferRepo offerRepo;

    private ModelRepo modelRepo;
    private UserRepo userRepo;

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setOfferRepo(OfferRepo offerRepo) {
        this.offerRepo = offerRepo;
    }

    @Autowired
    public void setModelRepo(ModelRepo modelRepo) {
        this.modelRepo = modelRepo;
    }

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public OfferServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    @CacheEvict(cacheNames = "offers", allEntries = true)
    public DetailedOfferDto register(DetailedOfferDto entity, Principal principal) {
       /* if (entity.getId() != null) {
            Optional<Offer> offer = offerRepo.findById(entity.getId());
            if (offer.isPresent()) {
                String username = principal.getName();
                User u = authService.getUser(username);
                Offer o = modelMapper.map(entity, Offer.class);
                o.setUser(u);
                o.setModel(modelRepo.findByName(entity.getModel().getName()).orElse(null));
                o.setCreated(offer.get().getCreated());
                o.setModified(new Date());
                return modelMapper.map(offerRepo.save(o), DetailedOfferDto.class);
            }
        }*/
        String username = principal.getName();
        User u = authService.getUser(username);
        Offer o = modelMapper.map(entity, Offer.class);
        o.setUser(u);
        o.setModel(modelRepo.findByName(entity.getModel().getName()).orElse(null));
        o.setCreated(new Date());
        return modelMapper.map(offerRepo.save(o), DetailedOfferDto.class);
    }

   /* @Override
    public void registerOf(AddOfferDto offer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            User currentUser = userRepo.findByUsername(userDetails.getUsername()).orElse(null);

            Offer of = modelMapper.map(offer, Offer.class);
            of.setModel(modelRepo.findByName(offer.getModel()).orElse(null));
            of.setUser(currentUser);
            of.setCreated(new Date());
            offerRepo.saveAndFlush(of);
        } else {
            throw new RuntimeException("Пользователь не аутентифицирован");
        }
    }*/

    @Override
    public List<ShowOfferDto> getAllByPrice(String sort) {
        List<Offer> offers;
        if ("asc".equals(sort)) {
            offers = offerRepo.findAllByOrderByPriceAsc();
        } else {
            offers = offerRepo.findAllByOrderByPriceDesc();
        }
        return offers.stream().map(offer -> modelMapper.map(offer, ShowOfferDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ShowOfferDto> getAllByMileage(String sort) {
        List<Offer> offers;
        if ("asc".equals(sort)) {
            offers = offerRepo.findAllByOrderByMileageAsc();
        } else {
            offers = offerRepo.findAllByOrderByMileageDesc();
        }
        return offers.stream().map(offer -> modelMapper.map(offer, ShowOfferDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ShowOfferDto> getAllByYear(String sort) {
        List<Offer> offers;
        if ("asc".equals(sort)) {
            offers = offerRepo.findAllByOrderByYearAsc();
        } else {
            offers = offerRepo.findAllByOrderByYearDesc();
        }
        return offers.stream().map(offer -> modelMapper.map(offer, ShowOfferDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ShowOfferDto> findAllByModel(String name) {
        return offerRepo.findAllByModel(name).stream().map((c) -> modelMapper.map(c, ShowOfferDto.class)).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "offers", allEntries = true)
    public void deleteById(UUID id) {
        offerRepo.deleteById(id);
    }

    @Override
    @Cacheable("offers")
    public List<ShowOfferDto> getAll() {
        return offerRepo.findAll().stream().map((c) -> modelMapper.map(c, ShowOfferDto.class)).collect(Collectors.toList());
    }

    @Override
    @Cacheable("offers")
    public List<ShowOfferDto> getAllShow() {
        return offerRepo.findAll().stream().map((o) -> modelMapper.map(o, ShowOfferDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<DetailedOfferDto> getById(UUID id) {
        return Optional.ofNullable(modelMapper.map(offerRepo.findById(id), DetailedOfferDto.class));
    }

    @Override
    public Optional<DetailedOfferDto> getByIdDetailed(UUID id) {
        Optional<Offer> offerOptional = offerRepo.findById(id);
        return offerOptional.map(offer -> modelMapper.map(offer, DetailedOfferDto.class));
    }

    @Override
    @CacheEvict(cacheNames = "offers", allEntries = true)
    public DetailedOfferDto update(DetailedOfferDto detailedOfferDto) {
        Offer offer = offerRepo.findById(detailedOfferDto.getId()).get();
        offer.setDescription(detailedOfferDto.getDescription());
        offer.setEngine(detailedOfferDto.getEngine());
        offer.setTransmission(detailedOfferDto.getTransmission());
        offer.setMileage(detailedOfferDto.getMileage());
        offer.setPrice(detailedOfferDto.getPrice());
        return modelMapper.map(offerRepo.save(offer), DetailedOfferDto.class);
    }
}