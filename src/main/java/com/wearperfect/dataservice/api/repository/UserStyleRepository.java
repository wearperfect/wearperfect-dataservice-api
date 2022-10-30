package com.wearperfect.dataservice.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.UserStyle;

@Repository
public interface UserStyleRepository extends JpaRepository<UserStyle, Integer>, JpaSpecificationExecutor<UserStyle> {

	List<UserStyle> findByUserId(Long userId);
	
	void deleteByUserIdAndStyleId(Long userId, Integer styleId);
}
