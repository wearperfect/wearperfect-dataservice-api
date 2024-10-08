package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.WishlistCollectionProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistCollectionProductRepository extends JpaRepository<WishlistCollectionProduct, Long> {

    Page<WishlistCollectionProduct> findByWishlistCollectionId(Long wishlistCollectionId, Pageable pageable);

    void deleteByWishlistProductId(Long wishlistProductId);

    void deleteByWishlistProductIdIn(List<Long> wishlistProductIdList);
}
