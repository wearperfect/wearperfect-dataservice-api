package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.CountryDetailsDTO;
import com.wearperfect.dataservice.api.dto.StateBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.StateDTO;

public interface StateService {

	List<StateBasicDetailsDTO> getAllStates();

	StateBasicDetailsDTO getStateDetailsByStateId(Integer stateId);

	StateBasicDetailsDTO cteateState(StateDTO stateDto);

	StateBasicDetailsDTO updateState(Integer stateId, StateDTO stateDto);

	StateBasicDetailsDTO delelteState(Integer stateId);

	CountryDetailsDTO getAllStatesByCountryId(Integer countryId);

}
