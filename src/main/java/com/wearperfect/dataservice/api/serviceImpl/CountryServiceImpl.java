package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.CountryBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.CountryDTO;
import com.wearperfect.dataservice.api.entities.Country;
import com.wearperfect.dataservice.api.entities.Country_;
import com.wearperfect.dataservice.api.mappers.CountryMapper;
import com.wearperfect.dataservice.api.repositories.CountryRepository;
import com.wearperfect.dataservice.api.service.CountryService;

@Service
@Transactional
public class CountryServiceImpl implements CountryService{

	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	CountryMapper countryMapper;

	@Override
	public List<CountryBasicDetailsDTO> getAllCounries() {
		List<Country> countries = countryRepository.findAll(Sort.by(Direction.ASC, Country_.NAME));
		return countries.stream().map(country->countryMapper.mapCountryToCountryBasicDetailsDto(country)).collect(Collectors.toList());
	}

	@Override
	public CountryBasicDetailsDTO getCountryDetailsByCountryId(Integer countryId) {
		Optional<Country> country = countryRepository.findById(countryId);
		if(country.isPresent()) {
			return countryMapper.mapCountryToCountryBasicDetailsDto(country.get());
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public CountryBasicDetailsDTO createCountry(CountryDTO countryDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountryBasicDetailsDTO updateCountry(Integer countryId, CountryDTO countryDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountryBasicDetailsDTO deleteCountry(Integer countryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
