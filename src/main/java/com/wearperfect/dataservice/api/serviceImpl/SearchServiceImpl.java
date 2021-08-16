package com.wearperfect.dataservice.api.serviceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.HashTagSearchDTO;
import com.wearperfect.dataservice.api.dto.SearchResponseDTO;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.entities.HashTag;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.entities.User_;
import com.wearperfect.dataservice.api.mappers.HashTagMapper;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.repositories.HashTagRepository;
import com.wearperfect.dataservice.api.repositories.PostHashTagRepository;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	HashTagRepository hashTagRepository;
	
	@Autowired
	HashTagMapper hashTagMapper;
	
	@Autowired
	PostHashTagRepository postHashTagRepository;

	@Override
	public SearchResponseDTO search(String realm, String query) {

		SearchResponseDTO searchResponse = new SearchResponseDTO();

		if (query.length() > 0) {
			String firstLetter = query.substring(0, 1);
			String searchContentQuery;

			if (firstLetter.equalsIgnoreCase("@") || firstLetter.equalsIgnoreCase("#")) {
				searchContentQuery = "%" + query.substring(1, query.length()) + "%";
				if (query.length() == 1) {
					searchResponse.setUsers(new LinkedList<>());
					return searchResponse;
				}
			} else {
				searchContentQuery = "%" + query + "%";
			}

			System.out.println(firstLetter + "-----" + searchContentQuery);
			if (firstLetter.equalsIgnoreCase("@") || realm.equalsIgnoreCase("accounts")) {
				List<User> users = userRepository.findByUsernameLike(searchContentQuery, PageRequest.of(0, 25, Sort.by(Direction.ASC, User_.USERNAME)));
				List<UserDTO> userDtoList = users.stream().map(user -> userMapper.mapUserToUserDto(user))
						.collect(Collectors.toList());
				searchResponse.setUsers(userDtoList);
			} else if (firstLetter.equalsIgnoreCase("#") || realm.equalsIgnoreCase("tags")) {
				List<HashTag> hashTags = hashTagRepository.findByTagLike(searchContentQuery);
				List<HashTagSearchDTO> searchedHashTagDtoList = hashTags.stream().map(hashTag->hashTagMapper.mapHashTagToHashTagSearchDto(hashTag)).collect(Collectors.toList());
				searchedHashTagDtoList.forEach(searchedHashTagDto->{
					searchedHashTagDto.setTotalPostsTagged(postHashTagRepository.countByHashTagId(searchedHashTagDto.getId()));
				});
				searchResponse.setHashTags(searchedHashTagDtoList);
			}
		} else {
			searchResponse.setUsers(new LinkedList<>());
			return searchResponse;
		}

		return searchResponse;
	}

}
