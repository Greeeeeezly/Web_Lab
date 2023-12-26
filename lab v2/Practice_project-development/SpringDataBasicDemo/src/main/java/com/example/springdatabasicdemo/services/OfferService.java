package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.AddOfferDto;
import com.example.springdatabasicdemo.dtos.DetailedOfferDto;
import com.example.springdatabasicdemo.dtos.OfferDto;
import com.example.springdatabasicdemo.dtos.ShowOfferDto;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface OfferService<UUID>{
    Optional<DetailedOfferDto> getByIdDetailed(UUID id);
    DetailedOfferDto update(DetailedOfferDto detailedOfferDto);
    DetailedOfferDto register(DetailedOfferDto offer, Principal principal);
    void delete (OfferDto offer);
    void deleteById(UUID id);
    Optional<DetailedOfferDto> getById(UUID id);
    List<OfferDto> getAll();

    List<ShowOfferDto> getAllShow();
    void registerOf(AddOfferDto offer);
    List<ShowOfferDto> getAllByPrice(String sort);
    List<ShowOfferDto> getAllByMileage(String sort);
    List<ShowOfferDto> getAllByYear(String sort);
    List<ShowOfferDto> findAllByModel(String name);

}
