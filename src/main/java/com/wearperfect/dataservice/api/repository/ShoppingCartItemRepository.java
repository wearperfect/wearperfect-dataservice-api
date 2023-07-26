package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.ShoppingCartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    Page<ShoppingCartItem> findByUserId(Long userId, Pageable pageable);

    Optional<ShoppingCartItem> findByUserIdAndProductIdAndSizeId(Long userId, Long productId, Integer sizeId);

    ShoppingCartItem removeById(Long id);
}
