package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.SearchResponseDTO;

public interface SearchService {

	SearchResponseDTO search(String realm, String query, Boolean strictMode);
}
