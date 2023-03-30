package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.GenderCategoryDTO;
import com.wearperfect.dataservice.api.dto.GenderDTO;
import com.wearperfect.dataservice.api.entity.Gender;
import com.wearperfect.dataservice.api.entity.GenderCategory;
import com.wearperfect.dataservice.api.entity.GenderCategory_;
import com.wearperfect.dataservice.api.entity.Gender_;
import com.wearperfect.dataservice.api.mapper.GenderCategoryMapper;
import com.wearperfect.dataservice.api.mapper.GenderMapper;
import com.wearperfect.dataservice.api.repository.GenderCategoryRepository;
import com.wearperfect.dataservice.api.repository.GenderRepository;
import com.wearperfect.dataservice.api.service.GenderService;

@Service
@Transactional
public class GenderServiceImpl implements GenderService {

	@Autowired
	GenderRepository genderRepository;

	@Autowired
	GenderCategoryRepository genderCategoryRepository;

	@Autowired
	GenderMapper genderMapper;

	@Autowired
	GenderCategoryMapper genderCategoryMapper;

	@Override
	public List<GenderDTO> getAllgenders() {
		List<Gender> genders = genderRepository.findAll(Sort.by(Direction.DESC, Gender_.NAME));
		return genders.stream().map(gender -> genderMapper.mapGenderToGenderDto(gender)).collect(Collectors.toList());
	}

	@Override
	public GenderDTO getGenderBygenderId(Integer genderId) {
		Optional<Gender> gender = genderRepository.findById(genderId);
		if (gender.isPresent()) {
			return genderMapper.mapGenderToGenderDto(gender.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public GenderDTO createGender(GenderDTO genderDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenderDTO updateGender(Integer genderId, GenderDTO genderDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenderDTO deleteGender(Integer genderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GenderCategoryDTO> getAllGenderCategories() {
		List<GenderCategory> genderCategories = genderCategoryRepository.findAll(Sort.by(Direction.ASC, GenderCategory_.ID));
		return genderCategories.stream()
				.map(genderCategory -> genderCategoryMapper.mapGenderCategoryToGenderCategoryDto(genderCategory))
				.collect(Collectors.toList());
	}

	@Override
	public GenderCategoryDTO getGenderCategoryByGenderCategoryId(Integer genderCategoryId) {
		Optional<GenderCategory> genderCategory = genderCategoryRepository.findById(genderCategoryId);
		if(genderCategory.isPresent()) {
			return genderCategoryMapper.mapGenderCategoryToGenderCategoryDto(genderCategory.get());
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public GenderCategoryDTO createGenderCategory(GenderCategoryDTO genderCategoryDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenderCategoryDTO updateGenderCategory(Integer genderCategoryId, GenderCategoryDTO genderCategoryDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenderCategoryDTO deleteGenderCategory(Integer genderCategoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
