package com.wearperfect.dataservice.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entity.PreferenceFilterGenderCategory;

@Repository
public interface PreferenceFilterGenderCategoryRepository extends JpaRepository<PreferenceFilterGenderCategory, Long>, JpaSpecificationExecutor<PreferenceFilterGenderCategory> {

	Optional<PreferenceFilterGenderCategory> findByPreferenceFilterIdAndGenderCategoryId(Long filterId,
			Integer genderCategoryId);

}
