package com.wearperfect.dataservice.api.specification;

import com.wearperfect.dataservice.api.entity.*;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNullApi;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class WishlistProductSpecification {
    public static Specification<WishlistProduct> wishlistProductsByUserIdAndWishlistCollectionIdAndActive(Long userId, Long wishlistCollectionId, Boolean active) {
        return new Specification<WishlistProduct>() {
            @Serial
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<WishlistProduct> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(null != userId){
                    Expression<Long> userIdExpression = root.get(WishlistProduct_.USER_ID);
                    Predicate userIdPredicate = cb.equal(userIdExpression, userId);
                    predicates.add(userIdPredicate);
                }
                if(null != wishlistCollectionId){
                    Join<WishlistProduct, WishlistCollectionProduct> wishlistCollectionProductJoin = root.join(WishlistProduct_.WISHLIST_COLLECTION_PRODUCTS);
                    Expression<Long> wishlistCollectionIdExpression = wishlistCollectionProductJoin.get(WishlistCollectionProduct_.WISHLIST_COLLECTION_ID);
                    Predicate wishlistCollectionIdPredicate = cb.equal(wishlistCollectionIdExpression, wishlistCollectionId);
                    predicates.add(wishlistCollectionIdPredicate);
                }
                Expression<Boolean> activeExpression = root.get(WishlistProduct_.ACTIVE);
                Predicate activePredicate = cb.equal(activeExpression, active);
                predicates.add(activePredicate);
                return cq.where(cb.and(predicates.toArray(new Predicate[] {}))).getRestriction();
            }
        };

    }
}
