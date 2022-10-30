package com.wearperfect.dataservice.api.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.entities.User_;

public class UserDetailsSpecification {

	/**
	 * @param username	: used to get user details of a patient of that mobile/email/username.
	 * @return list of patient details.
	 */	
	public static Specification<User> userMobileOrEmailOrUsernamePredicate(final String username) {

		   return new Specification<User>() {

			/**
			 * 
			 */
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

			/**
			 * 
			 */
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
