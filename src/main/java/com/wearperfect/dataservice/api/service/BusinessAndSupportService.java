package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.BusinessAndSupportDTO;
import com.wearperfect.dataservice.api.dto.BusinessAndSupportDetailsDTO;

public interface BusinessAndSupportService {

	List<BusinessAndSupportDetailsDTO> getAllBusinessAndSupport(Integer page);

	BusinessAndSupportDetailsDTO getBusinessAndSupportById(Long userId, Long businessAndSupportId);

	BusinessAndSupportDetailsDTO createBusinessAndSupport(Long userId, BusinessAndSupportDTO businessAndSupportDto);

	BusinessAndSupportDetailsDTO updateBusinessAndSupport(Long userId, Long businessAndSupportId,
			BusinessAndSupportDTO businessAndSupportDto);

	BusinessAndSupportDTO deleteBusinessAndSupport(Long userId, Long businessAndSupportId);

}
