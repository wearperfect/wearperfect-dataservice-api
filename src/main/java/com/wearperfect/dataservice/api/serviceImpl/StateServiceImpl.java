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

import com.wearperfect.dataservice.api.dto.CountryDetailsDTO;
import com.wearperfect.dataservice.api.dto.StateBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.StateDTO;
import com.wearperfect.dataservice.api.entities.Country;
import com.wearperfect.dataservice.api.entities.State;
import com.wearperfect.dataservice.api.entities.State_;
import com.wearperfect.dataservice.api.mappers.CountryMapper;
import com.wearperfect.dataservice.api.mappers.StateMapper;
import com.wearperfect.dataservice.api.repository.CountryRepository;
import com.wearperfect.dataservice.api.repository.StateRepository;
import com.wearperfect.dataservice.api.service.StateService;

@Service
@Transactional
public class StateServiceImpl implements StateService{
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	StateMapper stateMapper;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	CountryMapper countryMapper;

	@Override
	public List<StateBasicDetailsDTO> getAllStates() {
		List<State> states = stateRepository.findAll(Sort.by(Direction.ASC, State_.NAME));
		return states.stream().map(state->stateMapper.mapStateToStateBasicDetailsDto(state)).collect(Collectors.toList());
	}

	@Override
	public StateBasicDetailsDTO getStateDetailsByStateId(Integer stateId) {
		Optional<State> state = stateRepository.findById(stateId);
		if(state.isPresent()) {
			return stateMapper.mapStateToStateBasicDetailsDto(state.get());
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public StateBasicDetailsDTO cteateState(StateDTO stateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateBasicDetailsDTO updateState(Integer stateId, StateDTO stateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateBasicDetailsDTO delelteState(Integer stateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountryDetailsDTO getAllStatesByCountryId(Integer countryId) {
		Optional<Country> country = countryRepository.findById(countryId);
		if(country.isPresent()) {
			return countryMapper.mapCountryToCountryDetailsDto(country.get());
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}


}
