package com.wearperfect.dataservice.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entity.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long>, JpaSpecificationExecutor<Follow> {

	Long countByUserId(Long userId);

	Long countByFollowingBy(Long userId);

	List<Follow> findByUserId(Long userId);

	List<Follow> findByFollowingBy(Long userId);

	Optional<Follow> findByUserIdAndFollowingBy(Long userId, Long followingBy);

	List<Follow> findByUserIdOrFollowingBy(Long userId, Long followedBy);

	List<Follow> findByUserIdOrFollowingByAndUserIdNotInAndFollowingByNotIn(Long userId, Long followedBy,
			List<Long> userIdList, List<Long> followingByUserIdList, Pageable page);

	void deleteByUserIdAndFollowingBy(Long userId, Long followedBy);
}
