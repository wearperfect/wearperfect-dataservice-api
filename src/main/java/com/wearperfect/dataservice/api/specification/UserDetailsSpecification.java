package com.wearperfect.dataservice.api.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import com.wearperfect.dataservice.api.entity.User_;
import org.springframework.data.jpa.domain.Specification;

import com.wearperfect.dataservice.api.entity.User;

public class UserDetailsSpecification {

	/**
	 * @param username	: used to get user details of a patient of that mobile/email/username.
	 * @return list of patient details.
	 */	
	public static Specification<User> userMobileOrEmailOrUsernamePredicate(final String username) {
		   return new Specification<User>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate userMobileOrEmailOrUsernamePredicate=cq.where(
						cb.or(
						cb.equal(root.get(User_.phone), username),
			    		cb.equal(root.get(User_.email), username.toLowerCase()),
			    		cb.equal(root.get(User_.username), username.toLowerCase()))
						).getRestriction();
				return userMobileOrEmailOrUsernamePredicate;
			}
		};
	   
	}
	
	/**
	 * @param username	: used to get user details of a patient of that mobile/email/username.
	 * @return list of patient details.
	 */	
	public static Specification<User> userMobileOrEmailOrUsernameAndPasswordPredicate(final String username, final String password) {
		   return new Specification<User>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate userMobileOrEmailOrUsernameAndPasswordPredicate=cq.where(
						cb.or(
						cb.equal(root.get(User_.phone), username),
			    		cb.equal(root.get(User_.email), username.toLowerCase()),
			    		cb.equal(root.get(User_.username), username)
			    		),
						cb.and(cb.equal(root.get(User_.password), password.toLowerCase()))
						).getRestriction();
				return userMobileOrEmailOrUsernameAndPasswordPredicate;
			}
			   
		};
	   
	}
}
