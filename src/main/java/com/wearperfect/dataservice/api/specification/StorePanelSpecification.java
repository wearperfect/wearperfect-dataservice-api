package com.wearperfect.dataservice.api.specification;

import com.wearperfect.dataservice.api.entity.*;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class StorePanelSpecification {
    /**
     * @param storePanels	: used to get user details of a patient of that mobile/email/username.
     * @return Product Filter Specification.
     */
    public static Specification<StorePanel> filterStorePanels(final List<String> storePanels) {

        return new Specification<StorePanel>() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<StorePanel> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (null != storePanels && !storePanels.isEmpty()) {
                    Expression<Long> role = root.get(StorePanel_.CODE);
                    Predicate codePredicate = role.in(storePanels);
                    predicates.add(codePredicate);
                }
                return cq.where(cb.and(predicates.toArray(new Predicate[] {}))).getRestriction();
            }
        };

    }
}
