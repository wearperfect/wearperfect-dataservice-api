package com.wearperfect.dataservice.api.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.FollowDTO;
import com.wearperfect.dataservice.api.dto.UserBasicDetailsDTO;
import com.wearperfect.dataservice.api.entities.Follow;
import com.wearperfect.dataservice.api.entities.Follow_;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.FollowMapper;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.repositories.FollowRepository;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.service.FollowService;

@Service
@Transactional
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowRepository followRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	FollowMapper followMapper;

	@Autowired
	UserMapper userMapper;

	@Autowired
	EntityManager em;

	@Override
	public List<UserBasicDetailsDTO> getUserFollowers(Long userId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Follow> root = cq.from(Follow.class);

		cq.select(root.get(Follow_.followedBy)).where(builder.equal(root.get(Follow_.userId), userId));

		List<Object> resultList = em.createQuery(cq).getResultList();

		List<Long> followersIdList = resultList.stream().map(obj -> (Long) obj).collect(Collectors.toList());

		System.out.println("followersIdList>>>>" + followersIdList.size());

		List<User> followers = userRepository.findByIdIn(followersIdList);
		return followers.stream().map(follower -> userMapper.mapUserToUserBasicDetailsDto(follower))
				.collect(Collectors.toList());
	}

	@Override
	public List<UserBasicDetailsDTO> getUserFollowings(Long userId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Follow> root = cq.from(Follow.class);

		cq.select(root.get(Follow_.userId)).where(builder.equal(root.get(Follow_.followedBy), userId));

		List<Object> resultList = em.createQuery(cq).getResultList();

		List<Long> followersIdList = resultList.stream().map(obj -> (Long) obj).collect(Collectors.toList());

		System.out.println("followersIdList>>>>" + followersIdList.size());

		List<User> followers = userRepository.findByIdIn(followersIdList);
		return followers.stream().map(follower -> userMapper.mapUserToUserBasicDetailsDto(follower))
				.collect(Collectors.toList());
	}

	@Override
	public FollowDTO followUser(Long followedBy, Long userId) {
		Follow follow = new Follow();
		follow.setUserId(userId);
		follow.setFollowedBy(followedBy);
		follow.setActive(true);
		follow.setCreatedOn(new Date());
		followRepository.save(follow);
		return followMapper.mapFollowToFollowDto(follow);
	}

}
