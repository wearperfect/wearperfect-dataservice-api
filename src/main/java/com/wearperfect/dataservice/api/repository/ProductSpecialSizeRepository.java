package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.ProductSpecialSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpecialSizeRepository extends JpaRepository<ProductSpecialSize, Long> {
}
