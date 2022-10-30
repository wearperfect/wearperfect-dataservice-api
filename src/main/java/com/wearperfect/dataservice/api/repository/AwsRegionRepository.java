package com.wearperfect.dataservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.AwsRegion;

@Repository
public interface AwsRegionRepository extends JpaRepository<AwsRegion, Integer>{

}
