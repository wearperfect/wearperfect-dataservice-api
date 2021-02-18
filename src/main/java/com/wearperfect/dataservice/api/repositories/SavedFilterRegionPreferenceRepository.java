package com.wearperfect.dataservice.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.SavedFilterRegionPreference;

@Repository
public interface SavedFilterRegionPreferenceRepository extends JpaRepository<SavedFilterRegionPreference, Long>, JpaSpecificationExecutor<SavedFilterRegionPreference>{

}
