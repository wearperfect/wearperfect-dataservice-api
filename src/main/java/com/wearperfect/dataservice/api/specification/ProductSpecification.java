package com.wearperfect.dataservice.api.specification;

import com.wearperfect.dataservice.api.dto.ProductFilterDTO;
import com.wearperfect.dataservice.api.entity.*;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;
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
                List<Predicate> filterAndPredicates = new ArrayList<>();
                List<Predicate> filterOrPredicates = new ArrayList<>();

                if(null != productFilters.getSearchText() && !productFilters.getSearchText().trim().isEmpty()){
                    Expression<String> searchText = root.get(Product_.name);
                    Predicate searchTextPredicate = cb.like(cb.upper(searchText), "%"+productFilters.getSearchText().toUpperCase()+"%");
                    filterAndPredicates.add(searchTextPredicate);
                }
                if (null != productFilters.getManufacturerCategories() && productFilters.getManufacturerCategories().size() > 0) {
                    Join<Product, User> productManufacturerJoin = root.join(Product_.MANUFACTURER);
                    Expression<Long> role = productManufacturerJoin.get(User_.ROLE_ID);
                    Predicate manufacturerRolePredicate = role.in(productFilters.getManufacturerCategories());
                    filterAndPredicates.add(manufacturerRolePredicate);
                }
                if(null != productFilters.getBrands() && productFilters.getBrands().size() > 0){
                    Expression<Long> brandId = root.get(Product_.MANUFACTURER_ID);
                    Predicate brandIdPredicate = brandId.in(productFilters.getBrands());
                    filterOrPredicates.add(brandIdPredicate);
                }
                if(null != productFilters.getDesigners() && productFilters.getDesigners().size() > 0){
                    Expression<Long> designerId = root.get(Product_.MANUFACTURER_ID);
                    Predicate designerIdPredicate = designerId.in(productFilters.getDesigners());
                    filterOrPredicates.add(designerIdPredicate);
                }
                if(null != productFilters.getCategories() && productFilters.getCategories().size()>0){
                    Join<Product, ProductCategory> productProductCategoryJoin = root.join(Product_.PRODUCT_CATEGORY);
                    Expression<Integer> category = productProductCategoryJoin.get(ProductCategory_.CATEGORY_ID);
                    Predicate categoryPredicate = category.in(productFilters.getCategories());
                    filterAndPredicates.add(categoryPredicate);
                }
                if(null != productFilters.getColors() && productFilters.getColors().size()>0){
                    Expression<Integer> color = root.get(Product_.colorId);
                    Predicate colorPredicate = color.in(productFilters.getColors());
                    filterAndPredicates.add(colorPredicate);
                }
                if(null != productFilters.getProductCategories() && productFilters.getProductCategories().size()>0){
                    Expression<Integer> productCategory = root.get(Product_.productCategoryId);
                    Predicate productCategoryPredicate = productCategory.in(productFilters.getProductCategories());
                    filterAndPredicates.add(productCategoryPredicate);
                }
                if(null != productFilters.getGenderCategories() && productFilters.getGenderCategories().size()>0){
                    Expression<Integer> genderCategory = root.get(Product_.genderCategoryId);
                    Predicate genderCategoryIdPredicate = genderCategory.in(productFilters.getGenderCategories());
                    filterAndPredicates.add(genderCategoryIdPredicate);
                }
                if(null != productFilters.getStyles() && productFilters.getStyles().size()>0){
                    Join<Product, ProductStyle> productProductStyleJoin = root.join(Product_.PRODUCT_STYLES);
                    Expression<Integer> productStyle = productProductStyleJoin.get(ProductStyle_.STYLE_ID);
                    Predicate productStylePredicate = productStyle.in(productFilters.getStyles());
                    filterAndPredicates.add(productStylePredicate);
                }
//                if(null != productFilters.getRegions() && productFilters.getRegions().size()>0){
//                    Join<Product, ProductStyle> productProductStyleJoin = root.join(Product_.PRODUCT_STYLES);
//                    Join<ProductStyle, RegionStyle> productStyleRegionStyleJoin = productProductStyleJoin.join(ProductStyle_.STYLE_ID);
//                    Expression<Integer> region = productStyleRegionStyleJoin.get(RegionStyle_.REGION_ID);
//                    Predicate regionPredicate = region.in(productFilters.getRegions());
//                    filterAndPredicates.add(regionPredicate);
//                }
                if(null != productFilters.getSizes() && productFilters.getSizes().size()>0){
                    Join<Product, ProductInventoryItem> productProductInventoryItemJoin = root.join(Product_.PRODUCT_INVENTORY_ITEMS);
                    Expression<Short> size = productProductInventoryItemJoin.get(ProductInventoryItem_.SIZE_ID);
                    Predicate sizePredicate = size.in(productFilters.getSizes());
                    filterAndPredicates.add(sizePredicate);
                }
                if(filterOrPredicates.size()>0){
                    return cq.where(cb.and(
                            cb.and(filterAndPredicates.toArray(new Predicate[] {})),
                            cb.or(filterOrPredicates.toArray(new Predicate[] {}))
                    )).getRestriction();
                }else{
                    return cq.where(cb.and(filterAndPredicates.toArray(new Predicate[] {}))).getRestriction();
                }
            }
        };

    }
}
