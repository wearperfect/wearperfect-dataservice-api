package com.wearperfect.dataservice.api.specification;

import com.wearperfect.dataservice.api.dto.ProductFilterDTO;
import com.wearperfect.dataservice.api.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.JoinColumn;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    /**
     * @param productFilters	: used to get user details of a patient of that mobile/email/username.
     * @return Product Filter Specification.
     */
    public static Specification<Product> filterProducts(final ProductFilterDTO productFilters) {

        return new Specification<Product>() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> filterPredicates = new ArrayList<>();
                if(null != productFilters.getSearchText() && !productFilters.getSearchText().trim().isEmpty()){
                    Expression<String> searchText = root.get(Product_.name);
                    Predicate searchTextPredicate = cb.like(cb.upper(searchText), "%"+productFilters.getSearchText().toUpperCase()+"%");
                    filterPredicates.add(searchTextPredicate);
                }
                // Add Multi-Color Update
                if(null != productFilters.getColors() && productFilters.getColors().size()>0){
                    Expression<Integer> color = root.get(Product_.colorId);
                    Predicate colorPredicate = color.in(productFilters.getColors());
                    filterPredicates.add(colorPredicate);
                }
                if(null != productFilters.getProductCategories() && productFilters.getProductCategories().size()>0){
                    Expression<Integer> productCategory = root.get(Product_.productCategoryId);
                    Predicate productCategoryPredicate = productCategory.in(productFilters.getProductCategories());
                    filterPredicates.add(productCategoryPredicate);
                }
                if(null != productFilters.getGenderCategories() && productFilters.getGenderCategories().size()>0){
                    Expression<Integer> genderCategory = root.get(Product_.genderCategoryId);
                    Predicate genderCategoryIdPredicate = genderCategory.in(productFilters.getGenderCategories());
                    filterPredicates.add(genderCategoryIdPredicate);
                }
                if(null != productFilters.getStyles() && productFilters.getStyles().size()>0){
                    Join<Product, ProductStyle> productProductStyleJoin = root.join(Product_.PRODUCT_STYLE_LIST);
                    Expression<Integer> productStyle = productProductStyleJoin.get(ProductStyle_.STYLE_ID);
                    Predicate productStylePredicate = productStyle.in(productFilters.getStyles());
                    filterPredicates.add(productStylePredicate);
                }
                return cq.where(cb.and(filterPredicates.toArray(new Predicate[] {}))).getRestriction();
            }

        };

    }
}
