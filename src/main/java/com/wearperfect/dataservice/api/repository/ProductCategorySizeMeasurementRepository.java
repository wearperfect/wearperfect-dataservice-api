package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.ProductCategorySizeMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategorySizeMeasurementRepository extends JpaRepository<ProductCategorySizeMeasurement, Integer> {
}
