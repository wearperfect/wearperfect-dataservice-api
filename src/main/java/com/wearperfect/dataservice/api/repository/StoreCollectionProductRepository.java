package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.StoreCollectionProduct;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreCollectionProductRepository extends JpaRepository<StoreCollectionProduct, Long>, JpaSpecificationExecutor<StoreCollectionProduct> {

    List<StoreCollectionProduct> findByStoreCollectionId(Integer storeCollectionId, PageRequest page);
}
