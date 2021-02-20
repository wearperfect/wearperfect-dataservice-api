package com.wearperfect.dataservice.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.PreferenceFilterCategory;

@Repository
public interface PreferenceFilterCategoryRepository extends JpaRepository<PreferenceFilterCategory, Long>, JpaSpecificationExecutor<PreferenceFilterCategory>{

}
