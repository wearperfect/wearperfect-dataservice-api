package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.BusinessAndSupportDTO;

public interface BusinessAndSupportService {

	List<BusinessAndSupportDTO> getAllBusinessAndSupport(Integer page);

	BusinessAndSupportDTO getBusinessAndSupportById(Long userId, Long businessAndSupportId);

	BusinessAndSupportDTO createBusinessAndSupport(Long userId, BusinessAndSupportDTO businessAndSupportDto);

	BusinessAndSupportDTO updateBusinessAndSupport(Long userId, Long businessAndSupportId,
			BusinessAndSupportDTO businessAndSupportDto);

	BusinessAndSupportDTO deleteBusinessAndSupport(Long userId, Long businessAndSupportId);

}
