package com.wearperfect.dataservice.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entity.PreferenceFilterRegion;

@Repository
public interface PreferenceFilterRegionRepository extends JpaRepository<PreferenceFilterRegion, Long>, JpaSpecificationExecutor<PreferenceFilterRegion>{

	Optional<PreferenceFilterRegion> findByPreferenceFilterIdAndRegionId(Long filterId, Integer regionId);

}
