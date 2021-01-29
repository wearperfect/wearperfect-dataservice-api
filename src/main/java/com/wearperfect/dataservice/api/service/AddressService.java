package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.AddressDTO;

public interface AddressService {

	List<AddressDTO> getAllUserAddresses(Long userId);

}
