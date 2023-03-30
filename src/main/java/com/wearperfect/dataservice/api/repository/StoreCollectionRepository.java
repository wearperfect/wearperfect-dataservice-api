package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.StoreCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreCollectionRepository extends JpaRepository<StoreCollection, Integer>, JpaSpecificationExecutor<StoreCollection> {
}
