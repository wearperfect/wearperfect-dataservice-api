package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.AddressDTO;
import com.wearperfect.dataservice.api.entities.Address;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.AddressMapper;
import com.wearperfect.dataservice.api.repository.AddressRepository;
import com.wearperfect.dataservice.api.repository.UserRepository;
import com.wearperfect.dataservice.api.service.AddressService;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressMapper addressMapper;

	@Override
	public List<AddressDTO> getAllUserAddresses(Long userId) {
		List<Address> addresses = addressRepository.findByUserId(userId);
		return addresses.stream().map(address->addressMapper.mapAddressToAddressDto(address)).collect(Collectors.toList());
	}

	@Override
	public List<AddressDTO> getUserBasicInfoContactAddresses(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			
			return null;
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}
}
