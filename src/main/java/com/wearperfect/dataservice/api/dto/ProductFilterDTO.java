package com.wearperfect.dataservice.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductFilterDTO {
    String searchText; // Working
    List<Short> manufacturerCategories; // Working
    List<Integer> categories; // Working
    List<Integer> colors; // Working
    List<Integer> brands; // Working
    List<Integer> designers; // Working
    List<Integer> genderCategories; // Working
    List<Integer> productCategories; // Working
    List<Integer> regions;
    List<Integer> styles; // Working
    List<Short> sizes; // Working
}
