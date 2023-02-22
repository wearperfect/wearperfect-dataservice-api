package com.wearperfect.dataservice.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductFilterDTO {
    String searchText;
    List<Integer> categories;
    List<Integer> colors;
    List<Integer> brands;
    List<Integer> designers;
    List<Integer> genderCategories;
    List<Integer> productCategories;
    List<Integer> regions;
    List<Integer> styles;
}
