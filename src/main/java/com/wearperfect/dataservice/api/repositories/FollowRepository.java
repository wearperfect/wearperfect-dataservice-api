package com.wearperfect.dataservice.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long>, JpaSpecificationExecutor<Follow>{
	
	Long countByUserId(Long userId);
	
	Long countByFollowingBy(Long userId);
	
	List<Follow> findByUserId(Long userId);
	
	List<Follow> findByFollowingBy(Long userId);
	
	Follow findByUserIdAndFollowingBy(Long userId, Long followedBy);
	
	List<Follow> findByUserIdOrFollowingBy(Long userId, Long followedBy);
	
	List<Follow> findByUserIdOrFollowingByAndUserIdNotInAndFollowingByNotIn(Long userId, Long followedBy, List<Long> userIdList, List<Long> followingByUserIdList);
	
	void deleteByUserIdAndFollowingBy(Long userId, Long followedBy);
}
