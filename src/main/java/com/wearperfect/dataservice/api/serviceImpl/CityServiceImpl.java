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

import com.wearperfect.dataservice.api.dto.CityBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.CityDTO;
import com.wearperfect.dataservice.api.dto.StateDetailsDTO;
import com.wearperfect.dataservice.api.entity.City;
import com.wearperfect.dataservice.api.entity.City_;
import com.wearperfect.dataservice.api.entity.State;
import com.wearperfect.dataservice.api.mapper.CityMapper;
import com.wearperfect.dataservice.api.mapper.StateMapper;
import com.wearperfect.dataservice.api.repository.CityRepository;
import com.wearperfect.dataservice.api.repository.StateRepository;
import com.wearperfect.dataservice.api.service.CityService;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepository cityRepository;

	@Autowired
	CityMapper cityMapper;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	StateMapper stateMapper;

	@Override
	public List<CityBasicDetailsDTO> getAllCities() {
		List<City> cities = cityRepository.findAll(Sort.by(Direction.ASC, City_.NAME));
		return cities.stream().map(city -> cityMapper.mapCityToCityBasicDetailsDto(city)).collect(Collectors.toList());
	}

	@Override
	public CityBasicDetailsDTO getCityDetailsByCityId(Integer cityId) {
		Optional<City> city = cityRepository.findById(cityId);
		if (city.isPresent()) {
			return cityMapper.mapCityToCityBasicDetailsDto(city.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public CityBasicDetailsDTO createCity(CityDTO cityDto) {
		//
		return null;
	}

	@Override
	public CityBasicDetailsDTO updateCity(Integer cityId, CityDTO cityDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityBasicDetailsDTO deleteCity(Integer cityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateDetailsDTO getAllCitiesByStateId(Integer stateId) {
		Optional<State> state = stateRepository.findById(stateId);
		if (state.isPresent()) {
			return stateMapper.mapStateToStateDetailsDto(state.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

}
