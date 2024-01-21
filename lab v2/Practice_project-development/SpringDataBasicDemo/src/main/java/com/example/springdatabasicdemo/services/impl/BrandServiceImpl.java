package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.*;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.agregation.BrandModelCountResult;
import com.example.springdatabasicdemo.repositories.BrandRepo;

import com.example.springdatabasicdemo.services.BrandService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//@EnableCaching
public class BrandServiceImpl implements BrandService<UUID> {

    final ModelMapper modelMapper;
    private BrandRepo brandRepo;

    public BrandServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setBrandRepo(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    @CacheEvict(cacheNames = "brands", allEntries = true)
    public AddBrandDto register(AddBrandDto entity) {
        Brand b = modelMapper.map(entity,Brand.class);
        b.setCreated(new Date());
        return modelMapper.map(brandRepo.save(b), AddBrandDto.class);
    }
    @Override
    @CacheEvict(cacheNames = "brands", allEntries = true)
    public DetailedBrandDto update(DetailedBrandDto brandDto){
        Brand b = brandRepo.findById(brandDto.getId()).get();
        b.setName(brandDto.getName());
        return modelMapper.map(brandRepo.save(b),DetailedBrandDto.class);
    }
    @Override
    @CacheEvict(cacheNames = "brands", allEntries = true)
    public void delete(DetailedBrandDto entity) {
        brandRepo.deleteById(entity.getId());
    }

    @Override
    @CacheEvict(cacheNames = "brands", allEntries = true)
    public void deleteById(UUID id) {
        brandRepo.deleteById(id);
    }

    @Override
    public List<BrandModelCountResult> findBrandsByModels() {
        return brandRepo.findTop3BrandsWithModelCount();
    }


    @Override
    //@Cacheable("brands")
    public List<ShowBrandDto> getAll() {
        return brandRepo.findAll().stream().map((c)->modelMapper.map(c,ShowBrandDto.class)).collect(Collectors.toList());
    }

    @Override
    @Cacheable("brands")
    public List<ShowBrandDto> getAllShow() {
        return brandRepo.findAll().stream().map((c)->modelMapper.map(c,ShowBrandDto.class)).collect(Collectors.toList());
    }


    @Override
    public Optional<DetailedBrandDto> getById(UUID id) {
        return Optional.ofNullable(modelMapper.map(brandRepo.findById(id),DetailedBrandDto.class));
    }

    @Override
    public Optional<DetailedBrandDto> getByIdDetailed(UUID id) {
        Optional<Brand> brandOptional = brandRepo.findByIdWithModels(id);
        return brandOptional.map(brand -> modelMapper.map(brand, DetailedBrandDto.class));
    }
}
