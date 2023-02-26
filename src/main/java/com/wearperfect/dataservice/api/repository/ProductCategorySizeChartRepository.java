package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.ProductCategory;
import com.wearperfect.dataservice.api.entity.ProductCategorySizeChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategorySizeChartRepository extends JpaRepository<ProductCategorySizeChart, Integer>, JpaSpecificationExecutor<ProductCategorySizeChart> {
}
