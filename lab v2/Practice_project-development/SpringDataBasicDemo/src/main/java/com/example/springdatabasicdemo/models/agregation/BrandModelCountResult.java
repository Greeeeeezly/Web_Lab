package com.example.springdatabasicdemo.models.agregation;

import com.example.springdatabasicdemo.models.Brand;

import java.util.UUID;

public class BrandModelCountResult {
    private UUID brandId;
    private String brandName;
    private Long modelCount;

    public BrandModelCountResult(UUID brandId, String brandName, Long modelCount) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.modelCount = modelCount;
    }

    public UUID getBrandId() {
        return brandId;
    }

    public void setBrandId(UUID brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getModelCount() {
        return modelCount;
    }

    public void setModelCount(Long modelCount) {
        this.modelCount = modelCount;
    }

    @Override
    public String toString() {
        return "BrandModelCountResult{" +
                "brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", modelCount=" + modelCount +
                '}';
    }
}
