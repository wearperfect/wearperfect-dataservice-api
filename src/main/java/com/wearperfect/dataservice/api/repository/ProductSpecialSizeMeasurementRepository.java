package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.ProductSpecialSizeMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpecialSizeMeasurementRepository extends JpaRepository<ProductSpecialSizeMeasurement, Long> {
}
