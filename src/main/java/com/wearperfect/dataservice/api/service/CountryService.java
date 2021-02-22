package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.CountryBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.CountryDTO;

public interface CountryService {

	List<CountryBasicDetailsDTO> getAllCounries();

	CountryBasicDetailsDTO getCountryDetailsByCountryId(Integer countryId);

	CountryBasicDetailsDTO createCountry(CountryDTO countryDto);

	CountryBasicDetailsDTO updateCountry(Integer countryId, CountryDTO countryDto);

	CountryBasicDetailsDTO deleteCountry(Integer countryId);

}
