package com.wearperfect.dataservice.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wearperfect.dataservice.api.entities.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{

}
