package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.WishlistCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistCollectionRepository extends JpaRepository<WishlistCollection, Long> {
    Page<WishlistCollection> findByUserId(Long userId, Pageable pageable);

    List<WishlistCollection> findByCoverWishlistCollectionProductId(Long coverWishlistProductId);

    @Query(value = "SELECT wc FROM WishlistCollection wc JOIN WishlistCollectionProduct wcp " +
            "ON wc.coverWishlistCollectionProductId = wcp.id WHERE wcp.wishlistProductId =:wishlistProductId")
    List<WishlistCollection> getByCoverWishlistCollectionProductLinkedWishListProductId(@Param("wishlistProductId") Long wishlistProductId);
}
