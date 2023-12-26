package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.*;
import com.example.springdatabasicdemo.models.agregation.ModelByYear;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;
import java.util.Optional;

public interface ModelService<UUID>{

    AddModelDto register(AddModelDto model);
    ModelDto registerM(ModelDto model);
    void delete (ModelDto model);
    void deleteById(UUID id);
    Optional<DetailedModelDto> getById(UUID id);
    Optional<ShowModelDto> getByName(String name);
    void addModel(AddModelDto model);

    List<ModelByYear> findModelsByYear();

    DetailedModelDto update(DetailedModelDto modelDto);

    Optional<DetailedModelDto> getByIdDetailed(UUID id);
    List<ModelDto> getAll();
    List<ShowModelDto> getAllShow();
    public List<String> getModelNames();

}
