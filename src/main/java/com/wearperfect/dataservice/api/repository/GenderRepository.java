package com.wearperfect.dataservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entity.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer>, JpaSpecificationExecutor<Gender>{

}
