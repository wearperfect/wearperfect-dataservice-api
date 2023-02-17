package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.CategoryDTO;
import com.wearperfect.dataservice.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/v1/categories")
    List<CategoryDTO> getCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(value = "/v1/categories/{regionId}")
    CategoryDTO getCategoryByCategoryId(@PathVariable(name = "categoryId") Integer categoryId) {
        return categoryService.getCategoryByCategoryId(categoryId);
    }

    @PostMapping(value = "/v1/categories")
    CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }

    @PutMapping(value = "/v1/categories/{categoryId}")
    CategoryDTO updateCategory(@PathVariable(name = "categoryId") Integer categoryId, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(categoryId, categoryDTO);
    }

    @DeleteMapping(value = "/v1/categories/{categoryId}")
    CategoryDTO deleteCategory(@PathVariable(name = "categoryId") Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
