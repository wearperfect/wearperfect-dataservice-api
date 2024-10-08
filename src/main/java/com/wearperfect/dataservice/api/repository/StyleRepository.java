package com.wearperfect.dataservice.api.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entity.Style;

@Repository
public interface StyleRepository extends JpaRepository<Style, Integer>, JpaSpecificationExecutor<Style>{
	
	List<Style> findByIdIn(List<Integer> styles, Sort sort);
}
