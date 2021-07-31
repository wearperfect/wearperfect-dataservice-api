package com.wearperfect.dataservice.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.UserContact;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Integer>, JpaSpecificationExecutor<UserContact>{
	
	List<UserContact> findByUserId(Long userId, Pageable page);
	
	List<UserContact> findByUserIdOrContactUserId(Long userId, Long contactUserId, Pageable page);
	
	Optional<UserContact> findByUserIdAndContactUserId(Long userId, Long contactUserId);
	
	Optional<UserContact> findByUserIdInAndContactUserIdIn(Long[] userIdList, Long[] contactUserIdList);
}
