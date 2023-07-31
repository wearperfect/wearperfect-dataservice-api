package com.wearperfect.dataservice.api.specification;

import com.wearperfect.dataservice.api.entity.*;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItemSpecification {
    public static Specification<ShoppingCartItem> shoppingCartItemByIdAndProductIdAndSizeId(Long shoppingCartItemId) {
        return new Specification<ShoppingCartItem>() {
            @Serial
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<ShoppingCartItem> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                Join<ShoppingCartItem, Product> shoppingCartItemProductJoin = root.join(ShoppingCartItem_.PRODUCT_ID);
                Join<ShoppingCartItem, Size> shoppingCartItemSizeJoin = root.join(ShoppingCartItem_.SIZE_ID);
                Predicate roleCodePredicate = shoppingCartItemProductJoin.in("BRAND");
                predicates.add(roleCodePredicate);
                return cq.where(cb.and(predicates.toArray(new Predicate[] {}))).getRestriction();
            }
        };

    }
}
