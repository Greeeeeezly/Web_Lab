package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.*;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.Model;
import com.example.springdatabasicdemo.models.agregation.ModelByYear;
import com.example.springdatabasicdemo.repositories.BrandRepo;
import com.example.springdatabasicdemo.repositories.ModelRepo;
import com.example.springdatabasicdemo.services.ModelService;
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
@EnableCaching
public class ModelServiceImpl implements ModelService<UUID> {

    final ModelMapper modelMapper;
    private ModelRepo modelRepo;
    private BrandRepo brandRepo;

    @Autowired
    public void setModelRepo(ModelRepo modelRepo) {
        this.modelRepo = modelRepo;
    }
    @Autowired
    public void setBrandRepo(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    public ModelServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    @CacheEvict(cacheNames = "models", allEntries = true)
    public AddModelDto register(AddModelDto entity) {
        if(entity.getName()!=null) {
            Optional<Model> model = modelRepo.findByName(entity.getName());
            if (model.isPresent()) {
                Model m = modelMapper.map(entity, Model.class);
                m.setCreated(model.get().getCreated());
                m.setModified(new Date());
                m.setBrand(brandRepo.findByName(entity.getBrand()).orElse(null));
                return modelMapper.map(modelRepo.save(m), AddModelDto.class);
            }
        }
        Model m = modelMapper.map(entity,Model.class);
        m.setCreated(new Date());
        m.setBrand(brandRepo.findByName(entity.getBrand()).orElse(null));
        return modelMapper.map(modelRepo.save(m), AddModelDto.class);
    }
    @Override
    public ModelDto registerM(ModelDto entity) {
        if (entity.getName()!=null) {
            Optional<Model> model = modelRepo.findByName(entity.getName());
            if (model.isPresent()) {
                Model m = modelMapper.map(entity, Model.class);
                m.setCreated(model.get().getCreated());
                m.setModified(new Date());
                m.setBrand(brandRepo.findByName(entity.getBrand().getName()).orElse(null));
                return modelMapper.map(modelRepo.save(m), ModelDto.class);

            }
        }
        Model m = modelMapper.map(entity,Model.class);
        m.setCreated(new Date());
        m.setBrand(brandRepo.findByName(entity.getBrand().getName()).orElse(null));
        return modelMapper.map(modelRepo.save(m), ModelDto.class);
    }

    @Override
    @CacheEvict(cacheNames = "models", allEntries = true)
    public void deleteById(UUID id) {
        modelRepo.deleteById(id);
    }

    @CacheEvict(cacheNames = "models", allEntries = true)
    @Override
    public void delete(ModelDto entity) {
        modelRepo.deleteById(entity.getId());
    }

    @CacheEvict(cacheNames = "models", allEntries = true)
    public void addModel(AddModelDto addModelDto) {
        Model model = modelMapper.map(addModelDto, Model.class);
        model.setCreated(new Date());
        model.setBrand(brandRepo.findByName(addModelDto.getName()).orElse(null));
        modelRepo.saveAndFlush(model);
    }
    @Override
    public List<ModelByYear> findModelsByYear() {
        return modelRepo.findTop5BrandsByYear();
    }
    @Override
    @Cacheable("models")
    public List<ModelDto> getAll() {
        return modelRepo.findAll().stream().map((c)->modelMapper.map(c,ModelDto.class)).collect(Collectors.toList());
    }
    @Override
    @Cacheable("models")
    public List<ShowModelDto> getAllShow() {
        return modelRepo.findAll().stream().map((m)->modelMapper.map(m,ShowModelDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<DetailedModelDto> getById(UUID id) {
        return Optional.ofNullable(modelMapper.map(modelRepo.findById(id),DetailedModelDto.class));
    }
    @Override
    @CacheEvict(cacheNames = "models", allEntries = true)
    public DetailedModelDto update(DetailedModelDto modelDto){
        Model m = modelRepo.findById(modelDto.getId()).get();
        m.setName(modelDto.getName());
        m.setCategory(modelDto.getCategory());
        m.setStartYear(modelDto.getStartYear());
        m.setEndYear(modelDto.getEndYear());
        m.setBrand(brandRepo.findByName(modelDto.getBrand().getName()).get());
        return modelMapper.map(modelRepo.save(m),DetailedModelDto.class);
    }

    @Override
    @Cacheable("models")
    public Optional<DetailedModelDto> getByIdDetailed(UUID id) {
        Optional<Model> modelOptional = modelRepo.findByIdWithBrand(id);
        return modelOptional.map(model -> modelMapper.map(model, DetailedModelDto.class));
    }
    @Override
    public Optional<ShowModelDto> getByName(String name){
        return Optional.ofNullable(modelMapper.map(modelRepo.findByName(name),ShowModelDto.class));
    }
    public List<String> getModelNames(){
        return modelRepo.findModelsNames();
    }
}
