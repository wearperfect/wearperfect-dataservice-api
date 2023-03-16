package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.ProductMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMediaRepository extends JpaRepository<ProductMedia, Long> {
}
