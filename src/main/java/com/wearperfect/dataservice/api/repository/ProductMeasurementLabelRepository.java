package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.ProductMeasurementLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMeasurementLabelRepository extends JpaRepository<ProductMeasurementLabel, Integer> {
}
