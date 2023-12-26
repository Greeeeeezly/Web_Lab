package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.Model;
import com.example.springdatabasicdemo.models.agregation.BrandModelCountResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BrandRepo extends JpaRepository<Brand, UUID> {
    Optional<Brand> findByName(String name);

    @Query("SELECT new com.example.springdatabasicdemo.models.agregation.BrandModelCountResult(b.id, b.name, COUNT(m)) FROM Brand b LEFT JOIN b.model m GROUP BY b.id, b.name ORDER BY COUNT(m) DESC LIMIT 1")
    List<BrandModelCountResult> findTop3BrandsWithModelCount();
    @Query("SELECT b FROM Brand b LEFT JOIN FETCH b.model WHERE b.id = :brandId")
    Optional<Brand> findByIdWithModels(@Param("brandId") UUID brandId);
}
