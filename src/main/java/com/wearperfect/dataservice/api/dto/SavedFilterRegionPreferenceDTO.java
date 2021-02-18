package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class SavedFilterRegionPreferenceDTO {

	Long id;

	Long savedFilterId;

	Long regionId;

	RegionBasicDetailsDTO region;

	Boolean active;
}
