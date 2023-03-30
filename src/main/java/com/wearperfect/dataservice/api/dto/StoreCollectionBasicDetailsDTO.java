package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreCollectionBasicDetailsDTO {
    private Integer id;
    private String title;
    private String desc;
    private String cover;
    private String thumbnail;
    private String actionText;
    private Boolean featured = false;
    private Boolean active = false;
    private List<StoreCollectionProductBasicDetailsDTO> storeCollectionProducts;
}
