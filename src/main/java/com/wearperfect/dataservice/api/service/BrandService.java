package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.UserBasicDetailsDTO;

import java.util.List;

public interface BrandService {
    List<UserBasicDetailsDTO> getTopTenBrands();

    List<UserBasicDetailsDTO> getNewlyLaunchedBrands();
}
