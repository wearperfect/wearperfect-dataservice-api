package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.constants.Pagination;
import com.wearperfect.dataservice.api.dto.BusinessAndSupportDTO;
import com.wearperfect.dataservice.api.entities.BusinessAndSupport;
import com.wearperfect.dataservice.api.entities.BusinessAndSupport_;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.BusinessAndSupportMapper;
import com.wearperfect.dataservice.api.repositories.BusinessAndSupportRepository;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.service.BusinessAndSupportService;
import com.wearperfect.dataservice.api.service.CityService;
import com.wearperfect.dataservice.api.service.CountryService;
import com.wearperfect.dataservice.api.service.StateService;

@Service
@Transactional
public class BusinessAndSupportServiceImpl implements BusinessAndSupportService{
	
	@Autowired
	BusinessAndSupportRepository businessAndSupportRepository;
	
	@Autowired
	BusinessAndSupportMapper businessAndSupportMapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	StateService stateService;
	
	@Autowired
	CountryService countryService;

	@Override
	public List<BusinessAndSupportDTO> getAllBusinessAndSupport(Integer page) {
		if(null == page || page < 0 ) {
			page = Pagination.PageNumber.DEFAULT.getValue();
		}
		Page<BusinessAndSupport> businessAndSupport = businessAndSupportRepository.findAll(PageRequest.of(page, Pagination.PageSize.BUSINESS_AND_SUPPORT.getValue(), Sort.by(Direction.DESC, BusinessAndSupport_.CREATED_ON)));
		return businessAndSupport.getContent().stream().map(business -> businessAndSupportMapper.mapBusinessAndSupporToBusinessAndSupportDto(business))
				.collect(Collectors.toList());
	}

	@Override
	public BusinessAndSupportDTO getBusinessAndSupportById(Long userId, Long businessAndSupportId) {
		Optional<BusinessAndSupport> businessAndSupport = businessAndSupportRepository.findByIdAndUserId(businessAndSupportId, userId);
		if(businessAndSupport.isPresent()) {
			return businessAndSupportMapper.mapBusinessAndSupporToBusinessAndSupportDto(businessAndSupport.get());	
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public BusinessAndSupportDTO createBusinessAndSupport(Long userId,
			BusinessAndSupportDTO businessAndSupportDto) {
		BusinessAndSupport businessAndSupport =  businessAndSupportMapper.mapBusinessAndSupportDtoToBusinessAndSupport(businessAndSupportDto);
		businessAndSupport.setActive(true);
		businessAndSupport.setUserId(userId);
		businessAndSupport.setCreatedBy(userId);
		businessAndSupport.setCreatedOn(new Date());
		businessAndSupportRepository.save(businessAndSupport);
		Optional<User> user = userRepository.findById(businessAndSupport.getUserId());
		if(user.isPresent()) {
			user.get().setBusinessAndSupportId(businessAndSupport.getId());
			userRepository.save(user.get());
		}
		BusinessAndSupportDTO businessAndSupportDetailsDto = getBusinessAndSupportById(userId, businessAndSupport.getId());
		businessAndSupportDetailsDto.setCity(cityService.getCityDetailsByCityId(businessAndSupport.getCityId()));
		businessAndSupportDetailsDto.setState(stateService.getStateDetailsByStateId(businessAndSupport.getStateId()));
		businessAndSupportDetailsDto.setCountry(countryService.getCountryDetailsByCountryId(businessAndSupport.getCountryId()));
		return businessAndSupportDetailsDto;
	}

	@Override
	public BusinessAndSupportDTO updateBusinessAndSupport(Long userId, Long businessAndSupportId,
			BusinessAndSupportDTO businessAndSupportDto) {
		
		if(userId != businessAndSupportDto.getUserId() || userId != businessAndSupportDto.getCreatedBy()) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}
		
		Optional<BusinessAndSupport> existingBusinessAndSupport = businessAndSupportRepository.findById(businessAndSupportId);
		if(existingBusinessAndSupport.isPresent()) {
			BusinessAndSupport businessAndSupport =  businessAndSupportMapper.mapBusinessAndSupportDtoToBusinessAndSupport(businessAndSupportDto);
			existingBusinessAndSupport.get().setTitle(businessAndSupport.getTitle());
			existingBusinessAndSupport.get().setCityId(businessAndSupport.getCityId());
			existingBusinessAndSupport.get().setStateId(businessAndSupport.getStateId());
			existingBusinessAndSupport.get().setCountryId(businessAndSupport.getCountryId());
			existingBusinessAndSupport.get().setAddressLine1(businessAndSupport.getAddressLine1());
			existingBusinessAndSupport.get().setGeoLocation(businessAndSupport.getGeoLocation());
			existingBusinessAndSupport.get().setLandmark(businessAndSupport.getLandmark());
			existingBusinessAndSupport.get().setZipCode(businessAndSupport.getZipCode());
			existingBusinessAndSupport.get().setSupportEmail(businessAndSupport.getSupportEmail());
			existingBusinessAndSupport.get().setSupportPhone(businessAndSupport.getSupportPhone());
			existingBusinessAndSupport.get().setSupportLink(businessAndSupport.getSupportLink());
			existingBusinessAndSupport.get().setLastUpdatedBy(userId);
			existingBusinessAndSupport.get().setLastUpdatedOn(new Date());
			businessAndSupportRepository.save(existingBusinessAndSupport.get());
			return businessAndSupportMapper.mapBusinessAndSupporToBusinessAndSupportDto(existingBusinessAndSupport.get());
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}
	


	@Override
	public BusinessAndSupportDTO updateBusinessAddress(Long userId, Long businessAndSupportId,
			BusinessAndSupportDTO businessAndSupportDto) {
		if(userId != businessAndSupportDto.getUserId() || userId != businessAndSupportDto.getCreatedBy()) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}
		
		Optional<BusinessAndSupport> existingBusinessAndSupport = businessAndSupportRepository.findById(businessAndSupportId);
		if(existingBusinessAndSupport.isPresent()) {
			BusinessAndSupport businessAndSupport =  businessAndSupportMapper.mapBusinessAndSupportDtoToBusinessAndSupport(businessAndSupportDto);
			existingBusinessAndSupport.get().setTitle(businessAndSupport.getTitle());
			existingBusinessAndSupport.get().setCityId(businessAndSupport.getCityId());
			existingBusinessAndSupport.get().setStateId(businessAndSupport.getStateId());
			existingBusinessAndSupport.get().setCountryId(businessAndSupport.getCountryId());
			existingBusinessAndSupport.get().setAddressLine1(businessAndSupport.getAddressLine1());
			existingBusinessAndSupport.get().setGeoLocation(businessAndSupport.getGeoLocation());
			existingBusinessAndSupport.get().setLandmark(businessAndSupport.getLandmark());
			existingBusinessAndSupport.get().setZipCode(businessAndSupport.getZipCode());
			existingBusinessAndSupport.get().setLastUpdatedBy(userId);
			existingBusinessAndSupport.get().setLastUpdatedOn(new Date());
			businessAndSupportRepository.save(existingBusinessAndSupport.get());
			return businessAndSupportMapper.mapBusinessAndSupporToBusinessAndSupportDto(existingBusinessAndSupport.get());
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public BusinessAndSupportDTO updateBusinessSupport(Long userId, Long businessAndSupportId,
			BusinessAndSupportDTO businessAndSupportDto) {
		if(userId != businessAndSupportDto.getUserId() || userId != businessAndSupportDto.getCreatedBy()) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}
		
		Optional<BusinessAndSupport> existingBusinessAndSupport = businessAndSupportRepository.findById(businessAndSupportId);
		if(existingBusinessAndSupport.isPresent()) {
			BusinessAndSupport businessAndSupport =  businessAndSupportMapper.mapBusinessAndSupportDtoToBusinessAndSupport(businessAndSupportDto);
			existingBusinessAndSupport.get().setSupportEmail(businessAndSupport.getSupportEmail());
			existingBusinessAndSupport.get().setSupportPhone(businessAndSupport.getSupportPhone());
			existingBusinessAndSupport.get().setSupportLink(businessAndSupport.getSupportLink());
			existingBusinessAndSupport.get().setLastUpdatedBy(userId);
			existingBusinessAndSupport.get().setLastUpdatedOn(new Date());
			businessAndSupportRepository.save(existingBusinessAndSupport.get());
			return businessAndSupportMapper.mapBusinessAndSupporToBusinessAndSupportDto(existingBusinessAndSupport.get());
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public BusinessAndSupportDTO deleteBusinessAndSupport(Long userId, Long businessAndSupportId) {
		Optional<BusinessAndSupport> existingBusinessAndSupport = businessAndSupportRepository.findById(businessAndSupportId);
		if(existingBusinessAndSupport.isPresent()) {
			BusinessAndSupportDTO businessAndSupportDto = businessAndSupportMapper.mapBusinessAndSupporToBusinessAndSupportDto(existingBusinessAndSupport.get());
			businessAndSupportRepository.deleteByIdAndUserId(businessAndSupportId, userId);
			return businessAndSupportDto;
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

}
