package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.AddressDTO;
import com.wearperfect.dataservice.api.entities.Address;
import com.wearperfect.dataservice.api.mappers.AddressMapper;
import com.wearperfect.dataservice.api.repositories.AddressRepository;
import com.wearperfect.dataservice.api.service.AddressService;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	AddressMapper addressMapper;

	@Override
	public List<AddressDTO> getAllUserAddresses(Long userId) {
		List<Address> addresses = addressRepository.findByUserId(userId);
		return addresses.stream().map(address->addressMapper.mapAddressToAddressDto(address)).collect(Collectors.toList());
	}
}
