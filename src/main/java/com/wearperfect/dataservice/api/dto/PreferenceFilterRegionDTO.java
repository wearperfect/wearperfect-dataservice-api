package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PreferenceFilterRegionDTO {
	Long id;
	Long preferenceFilterId;
	Integer regionId;
	RegionBasicDetailsDTO region;
	Boolean active;
}
