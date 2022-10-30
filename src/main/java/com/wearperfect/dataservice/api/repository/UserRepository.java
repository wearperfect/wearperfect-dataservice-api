package com.wearperfect.dataservice.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wearperfect.dataservice.api.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{

	 List<User> findByIdIn(List<Long> users);
	 
	 List<User> findByUsernameIn(Set<String> usernameSet);
	 
	 List<User> findByRoleId(Integer roleId);
	 
	 Optional<User> findByUsername(String username);
	 
	 List<User> findByUsernameLike(String query, Pageable page);
	 
	 List<User> findByUsernameLikeOrFullnameLike(String usernameQuery, String fullnameQuery, Pageable page);
}
