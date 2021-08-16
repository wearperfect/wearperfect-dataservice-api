package com.wearperfect.dataservice.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wearperfect.dataservice.api.entities.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{

	 List<User> findByIdIn(List<Long> users);
	 
	 List<User> findByRoleId(Integer roleId);
	 
	 Optional<User> findByUsername(String username);
	 
	 List<User> findByUsernameLike(String query, Pageable page);
	 
	 List<User> findByUsernameLikeOrFullnameLike(String usernameQuery, String fullnameQuery, Pageable page);
}
