package com.wearperfect.dataservice.api.specification;

import com.wearperfect.dataservice.api.entity.StoreCollection;
import com.wearperfect.dataservice.api.entity.StoreCollection_;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class StoreCollectionSpecification {
    /**
     * @param storeCollectionCodes	: used to get user details of a patient of that mobile/email/username.
     * @return Product Filter Specification.
     */
    public static Specification<StoreCollection> filterStoreCollectionTypes(final List<String> storeCollectionCodes) {

        return new Specification<StoreCollection>() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<StoreCollection> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

//                if (null != storeCollectionCodes && !storeCollectionCodes.isEmpty()) {
//                    Expression<Long> role = root.get(StoreCollection_.CODE);
//                    Predicate codePredicate = role.in(storeCollectionCodes);
//                    predicates.add(codePredicate);
//                }
                return cq.where(cb.and(predicates.toArray(new Predicate[] {}))).getRestriction();
            }
        };

    }
}