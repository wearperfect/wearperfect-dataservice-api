package com.wearperfect.dataservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.Master;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long>, JpaSpecificationExecutor<Master>{

}
