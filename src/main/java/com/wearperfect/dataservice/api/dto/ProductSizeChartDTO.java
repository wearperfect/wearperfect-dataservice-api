package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductSizeChartDTO {
    private List<SizeBasicDetailsDTO> sizes;
}
