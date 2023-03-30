package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.CategoryDTO;
import com.wearperfect.dataservice.api.entity.Category;
import com.wearperfect.dataservice.api.entity.Category_;
import com.wearperfect.dataservice.api.mapper.CategoryMapper;
import com.wearperfect.dataservice.api.repository.CategoryRepository;
import com.wearperfect.dataservice.api.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CategoryMapper categoryMapper;

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<Category> categories = categoryRepository.findAll(Sort.by(Direction.ASC, Category_.NAME));
		return categories.stream().map(category->categoryMapper.mapCategorytoCategoryDto(category)).collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByCategoryId(Integer categoryId) {
		Optional<Category> category = categoryRepository.findById(categoryId);
		if(category.isPresent()) {
			return categoryMapper.mapCategorytoCategoryDto(category.get());
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDTO updateCategory(Integer categoryId, CategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDTO deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
