package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.Model;
import com.example.springdatabasicdemo.models.agregation.BrandModelCountResult;
import com.example.springdatabasicdemo.models.agregation.ModelByYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModelRepo extends JpaRepository<Model, UUID> {

    Optional<Model> findByName(String name);

    @Query("SELECT m.name FROM Model m")
    List<String> findModelsNames();
    @Query("SELECT m FROM Model m LEFT JOIN FETCH m.brand WHERE m.id = :modelId")
    Optional<Model> findByIdWithBrand(@Param("modelId") UUID modelId);

    @Query("SELECT new com.example.springdatabasicdemo.models.agregation.ModelByYear(m.id, m.name, m.startYear) FROM Model m ORDER BY m.startYear DESC LIMIT 5")
    List<ModelByYear> findTop5BrandsByYear();
}
