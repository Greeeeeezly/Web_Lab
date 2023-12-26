package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepo extends JpaRepository<Offer, UUID> {
    List<Offer> findAllByOrderByPriceAsc();
    List<Offer> findAllByOrderByPriceDesc();
    List<Offer> findAllByOrderByMileageAsc();
    List<Offer> findAllByOrderByMileageDesc();
    List<Offer> findAllByOrderByYearAsc();
    List<Offer> findAllByOrderByYearDesc();

    @Query("SELECT o FROM Offer o WHERE o.model.name = :modelName")
    List<Offer> findAllByModel(@Param("modelName") String modelName);

}