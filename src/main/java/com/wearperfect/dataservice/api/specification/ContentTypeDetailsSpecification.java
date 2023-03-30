package com.wearperfect.dataservice.api.specification;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.wearperfect.dataservice.api.entity.ContentType;
import com.wearperfect.dataservice.api.entity.ContentType_;

public class ContentTypeDetailsSpecification {
	
	/**
	 * @param userId: used to fetch posts by active user id
	 * @return list of user posts.
	 */	
	public static Specification<ContentType> contentTypeExtensionPredicate(final String extension) {

		   return new Specification<ContentType>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ContentType> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
								
				Predicate contentTypeExtensionPredicate=cq.where(cb.equal(root.get(ContentType_.extension), extension.toUpperCase())).getRestriction();
							    
				return contentTypeExtensionPredicate;
			}
			   
		};
	   
	}

	/**
	 * @param userId: used to fetch posts by active user id
	 * @return list of user posts.
	 */	
	public static Specification<ContentType> contentTypeListExtensionPredicate(final List<String> extensions) {

		   return new Specification<ContentType>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ContentType> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
								
				Predicate contentTypeExtensionPredicate=root.get(ContentType_.extension).in(extensions);
							    
				return contentTypeExtensionPredicate;
			}
			   
		};
	   
	}
}
