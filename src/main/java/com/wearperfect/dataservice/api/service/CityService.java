package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.CityBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.CityDTO;
import com.wearperfect.dataservice.api.dto.StateDetailsDTO;

public interface CityService {

	List<CityBasicDetailsDTO> getAllCities();

	CityBasicDetailsDTO getCityDetailsByCityId(Integer cityId);

	CityBasicDetailsDTO createCity(CityDTO cityDto);

	CityBasicDetailsDTO updateCity(Integer cityId, CityDTO cityDto);

	CityBasicDetailsDTO deleteCity(Integer cityId);

	StateDetailsDTO getAllCitiesByStateId(Integer stateId);

}
