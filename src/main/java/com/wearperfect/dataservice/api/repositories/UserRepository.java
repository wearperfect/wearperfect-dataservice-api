package com.wearperfect.dataservice.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wearperfect.dataservice.api.entities.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{

	 List<User> findByIdIn(List<Long> users);
	 
	 List<User> findByRoleId(Integer roleId);
	 
	 User findByUsername(String username);
	 
	 List<User> findByUsernameLike(String query);
	 
	 List<User> findByUsernameLikeOrFullnameLike(String usernameQuery, String fullnameQuery);
}
