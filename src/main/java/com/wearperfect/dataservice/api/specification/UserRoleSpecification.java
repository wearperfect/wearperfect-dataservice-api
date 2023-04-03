package com.wearperfect.dataservice.api.specification;

import com.wearperfect.dataservice.api.entity.Role;
import com.wearperfect.dataservice.api.entity.Role_;
import com.wearperfect.dataservice.api.entity.User;
import com.wearperfect.dataservice.api.entity.User_;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserRoleSpecification {
    public static Specification<User> userRoleFilter(List<String> userRoles) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                Join<User, Role> userRoleJoin = root.join(User_.ROLE_DETAILS);
                Expression<String> roleCode = userRoleJoin.get(Role_.CODE);
                Predicate roleCodePredicate = roleCode.in(userRoles);
                predicates.add(roleCodePredicate);
                return cq.where(cb.and(predicates.toArray(new Predicate[]{}))).getRestriction();
            }
        };

    }
}
