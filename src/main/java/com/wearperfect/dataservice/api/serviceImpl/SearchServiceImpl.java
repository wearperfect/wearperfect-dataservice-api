package com.wearperfect.dataservice.api.serviceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.SearchResponseDTO;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;

	@Override
	public SearchResponseDTO search(String query) {

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
			if (firstLetter.equalsIgnoreCase("@")) {
				List<User> users = userRepository.findByUsernameLike(searchContentQuery);
				List<UserDTO> userDtoList = users.stream().map(user -> userMapper.mapUserToUserDto(user))
						.collect(Collectors.toList());
				searchResponse.setUsers(userDtoList);
			} else if (firstLetter.equalsIgnoreCase("#")) {

			} else {
				List<User> users = userRepository.findByUsernameLikeAndFullnameLike(searchContentQuery,
						searchContentQuery);
				List<UserDTO> userDtoList = users.stream().map(user -> userMapper.mapUserToUserDto(user))
						.collect(Collectors.toList());
				searchResponse.setUsers(userDtoList);
			}
		} else {
			searchResponse.setUsers(new LinkedList<>());
			return searchResponse;
		}

		return searchResponse;
	}

}
