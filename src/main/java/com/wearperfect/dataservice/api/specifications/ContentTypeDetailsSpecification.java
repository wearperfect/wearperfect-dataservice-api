package com.wearperfect.dataservice.api.specifications;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.wearperfect.dataservice.api.entities.ContentType;
import com.wearperfect.dataservice.api.entities.ContentType_;

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
