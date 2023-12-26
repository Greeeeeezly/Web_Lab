package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.AddBrandDto;
import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.dtos.DetailedBrandDto;
import com.example.springdatabasicdemo.dtos.ShowBrandDto;
import com.example.springdatabasicdemo.models.agregation.BrandModelCountResult;

import java.util.List;
import java.util.Optional;

public interface BrandService<UUID> {
    List<ShowBrandDto> getAllShow();
    AddBrandDto register(AddBrandDto brand);
    DetailedBrandDto update(DetailedBrandDto brandDto);
    void delete (BrandDto brand);
    void deleteById(UUID id);
    Optional<BrandDto> getById(UUID id);
    List<BrandModelCountResult> findBrandsByModels();
    Optional<DetailedBrandDto> getByIdDetailed(UUID id);
    List<BrandDto> getAll();
}
