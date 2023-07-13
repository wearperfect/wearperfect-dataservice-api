package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.ProductSpecialSizeChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpecialSizeChartRepository extends JpaRepository<ProductSpecialSizeChart, Long>, JpaSpecificationExecutor<ProductSpecialSizeChart> {
}
