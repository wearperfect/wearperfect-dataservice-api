package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.ProductCategorySize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategorySizeRepository extends JpaRepository<ProductCategorySize, Long> {
}
