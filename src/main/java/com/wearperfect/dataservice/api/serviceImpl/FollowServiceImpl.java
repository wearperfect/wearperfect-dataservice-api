package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.FollowDTO;
import com.wearperfect.dataservice.api.dto.UserFollowUpDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserFollowsResponseDTO;
import com.wearperfect.dataservice.api.entity.Follow;
import com.wearperfect.dataservice.api.mapper.FollowMapper;
import com.wearperfect.dataservice.api.mapper.UserMapper;
import com.wearperfect.dataservice.api.repository.FollowRepository;
import com.wearperfect.dataservice.api.repository.UserRepository;
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
	public UserFollowsResponseDTO getUserFollowers(Long userId) {

		List<Follow> follows = followRepository.findByUserIdOrFollowingBy(userId, userId);

		List<Follow> followersList = new LinkedList<>();
		List<Follow> followingsList = new LinkedList<>();
		List<Long> followersIdList = new LinkedList<>();
		List<Long> followingsIdList = new LinkedList<>();

		follows.stream().forEach(follow -> {
			if (follow.getUserId().equals(userId)) {
				followersList.add(follow);
				followersIdList.add(follow.getFollowingBy());
			} else if (follow.getFollowingBy().equals(userId)) {
				followingsList.add(follow);
				followingsIdList.add(follow.getUserId());
			}
		});

		List<UserFollowUpDetailsDTO> followers = userRepository.findByIdIn(followersIdList).stream()
				.map(follow -> userMapper.mapUserToUserFollowUpDetailsDto(follow)).collect(Collectors.toList());

		followers.stream().forEach(follower -> {
			follower.setRequestedUserId(userId);
			follower.setFollowed(true);
			follower.setFollowing(false);
			followingsIdList.forEach(followingId -> {
				if (follower.getUserId().equals(followingId)) {
					follower.setFollowing(true);
				}
			});
		});

		return new UserFollowsResponseDTO(userId, followers, null);
	}

	@Override
	public UserFollowsResponseDTO getUserFollowings(Long userId) {
		List<Follow> follows = followRepository.findByUserIdOrFollowingBy(userId, userId);

		List<Follow> followersList = new LinkedList<>();
		List<Follow> followingsList = new LinkedList<>();
		List<Long> followersIdList = new LinkedList<>();
		List<Long> followingsIdList = new LinkedList<>();

		follows.stream().forEach(follow -> {
			if (follow.getUserId().equals(userId)) {
				followersList.add(follow);
				followersIdList.add(follow.getFollowingBy());
			} else if (follow.getFollowingBy().equals(userId)) {
				followingsList.add(follow);
				followingsIdList.add(follow.getUserId());
			}
		});

		List<UserFollowUpDetailsDTO> followings = userRepository.findByIdIn(followingsIdList).stream()
				.map(follow -> userMapper.mapUserToUserFollowUpDetailsDto(follow)).collect(Collectors.toList());

		followings.stream().forEach(following -> {
			following.setRequestedUserId(userId);
			following.setFollowing(true);
			following.setFollowed(false);
			followersIdList.forEach(followerId -> {
				if (following.getUserId().equals(followerId)) {
					following.setFollowed(true);
				}
			});
		});

		return new UserFollowsResponseDTO(userId, null, followings);
	}

	@Override
	public FollowDTO followUser(Long followingBy, Long userId) {
		Follow follow = new Follow();
		follow.setUserId(userId);
		follow.setFollowingBy(followingBy);
		follow.setActive(true);
		follow.setCreatedOn(new Date());
		followRepository.save(follow);

//		UserFollowUpDetailsDTO userFollowUpDetails = userMapper.mapUserToUserFollowUpDetailsDto(userRepository.findById(userId).get());
//		userFollowUpDetails.setRequestedUserId(followingBy);
//		userFollowUpDetails.setFollowing(true);
//		
//		Optional<Follow> following = Optional.ofNullable(followRepository.findByUserIdAndFollowingBy(followingBy, userId));
//		if(following.isPresent()) {
//			userFollowUpDetails.setFollowed(true);
//		}else {
//			userFollowUpDetails.setFollowed(false);
//		}
		return followMapper.mapFollowToFollowDto(follow);
	}

	@Override
	public FollowDTO unFollowUser(Long followingBy, Long userId) {
		Optional<Follow> follow = followRepository.findByUserIdAndFollowingBy(userId, followingBy);
		if (follow.isPresent()) {
			followRepository.deleteByUserIdAndFollowingBy(userId, followingBy);
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		return followMapper.mapFollowToFollowDto(follow.get());
	}

	@Override
	public Boolean isUserFollowedByUserId(Long userId, Long followingBy) {
		Optional<Follow> follow = followRepository.findByUserIdAndFollowingBy(userId, followingBy);
		if (follow.isPresent()) {
			return true;
		}
		return false;
	}

//	@Override
//	public List<UserFollowUpDetailsDTO> getUserFollowings(Long userId) {
//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		CriteriaQuery<Object> cq = builder.createQuery();
//		Root<Follow> root = cq.from(Follow.class);
//
//		cq.select(root.get(Follow_.userId)).where(builder.equal(root.get(Follow_.followingBy), userId));
//
//		List<Object> resultList = em.createQuery(cq).getResultList();
//
//		List<Long> followersIdList = resultList.stream().map(obj -> (Long) obj).collect(Collectors.toList());
//
//		System.out.println("followersIdList>>>>" + followersIdList.size());
//
//		List<User> followers = userRepository.findByIdIn(followersIdList);
//		return followers.stream().map(follower -> userMapper.mapUserToUserBasicDetailsDto(follower))
//				.collect(Collectors.toList());
//	}

}
