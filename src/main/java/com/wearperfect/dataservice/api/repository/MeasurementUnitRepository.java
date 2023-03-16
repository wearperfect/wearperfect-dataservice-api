package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit, Byte> {
}
