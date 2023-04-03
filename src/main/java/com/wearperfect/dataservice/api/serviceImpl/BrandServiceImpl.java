package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.constant.UserRoleCode;
import com.wearperfect.dataservice.api.dto.UserBasicDetailsDTO;
import com.wearperfect.dataservice.api.entity.User;
import com.wearperfect.dataservice.api.entity.User_;
import com.wearperfect.dataservice.api.mapper.UserMapper;
import com.wearperfect.dataservice.api.repository.UserRepository;
import com.wearperfect.dataservice.api.service.BrandService;
import com.wearperfect.dataservice.api.specification.UserRoleSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserBasicDetailsDTO> getTopTenBrands() {
        List<String> brandRoleCodes = new ArrayList<>();
        brandRoleCodes.add(UserRoleCode.BRAND.getValue());
        List<User> topTenBrands = userRepository.findAll(UserRoleSpecification.userRoleFilter(brandRoleCodes), PageRequest.of(0,10)).getContent();
        return topTenBrands.stream().map(user -> userMapper.mapUserToUserBasicDetailsDto(user)).collect(Collectors.toList());
    }

    @Override
    public List<UserBasicDetailsDTO> getNewlyLaunchedBrands() {
        List<String> brandRoleCodes = new ArrayList<>();
        brandRoleCodes.add(UserRoleCode.BRAND.getValue());
        List<User> topTenBrands = userRepository.findAll(UserRoleSpecification.userRoleFilter(brandRoleCodes), PageRequest.of(0,10,  Sort.by(Sort.Direction.DESC, User_.CREATED_ON))).getContent();
        return topTenBrands.stream().map(user -> userMapper.mapUserToUserBasicDetailsDto(user)).collect(Collectors.toList());
    }
}
