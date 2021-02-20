package com.wearperfect.dataservice.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.PreferenceFilterStyle;

@Repository
public interface PreferenceFilterStyleRepository extends JpaRepository<PreferenceFilterStyle, Long>, JpaSpecificationExecutor<PreferenceFilterStyle>{

	Optional<PreferenceFilterStyle> findByPreferenceFilterIdAndStyleId(Long filterId, Integer styleId);

}
