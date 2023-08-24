package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.constant.Pagination;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductFilterDTO;
import com.wearperfect.dataservice.api.dto.WishlistProductDTO;
import com.wearperfect.dataservice.api.entity.Product;
import com.wearperfect.dataservice.api.entity.ProductCategorySizeChart;
import com.wearperfect.dataservice.api.mapper.ProductCategorySizeChartMapper;
import com.wearperfect.dataservice.api.mapper.ProductMapper;
import com.wearperfect.dataservice.api.repository.ProductCategorySizeChartRepository;
import com.wearperfect.dataservice.api.repository.ProductRepository;
import com.wearperfect.dataservice.api.security.models.WearperfectUserPrincipal;
import com.wearperfect.dataservice.api.security.service.WearperfectUserDetailsService;
import com.wearperfect.dataservice.api.service.ProductService;
import com.wearperfect.dataservice.api.service.WishlistProductService;
import com.wearperfect.dataservice.api.specification.ProductSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    ProductMapper productMapper;

    ProductCategorySizeChartMapper productCategorySizeChartMapper;

    ProductCategorySizeChartRepository productCategorySizeChartRepository;

    WearperfectUserDetailsService wearperfectUserDetailsService;

    WishlistProductService wishlistProductService;

    EntityManager em;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper,
                              ProductCategorySizeChartMapper productCategorySizeChartMapper,
                              ProductCategorySizeChartRepository productCategorySizeChartRepository,
                              WearperfectUserDetailsService wearperfectUserDetailsService,
                              WishlistProductService wishlistProductService,
                              EntityManager em) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productCategorySizeChartMapper = productCategorySizeChartMapper;
        this.productCategorySizeChartRepository = productCategorySizeChartRepository;
        this.wearperfectUserDetailsService = wearperfectUserDetailsService;
        this.wishlistProductService = wishlistProductService;
        this.em = em;
    }

    @Override
    public List<ProductDetailsDTO> getProducts(ProductFilterDTO productFilter, Integer page, Integer size) {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> productMapper.mapProductToProductDetailsDTO(product)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDetailsDTO> searchProducts(String searchText, Integer page, Integer size) {
        // This searches substring content in sequential order only.
        List<Product> products = productRepository.findByNameContainingIgnoreCase(
                searchText,
                PageRequest.of(
                        page != null ? page : Pagination.PageNumber.DEFAULT.getValue(),
                        size != null ? size : Pagination.PageSize.PRODUCTS.getValue())
        );
        // While returning group products by category and product category
        return products.stream().map(product -> productMapper.mapProductToProductDetailsDTO(product)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDetailsDTO> filterProducts(ProductFilterDTO productFilters, Integer page, Integer size) {
        List<Product> products = productRepository.findAll(
                ProductSpecification.filterProducts(productFilters),
                PageRequest.of(
                        page != null ? page : Pagination.PageNumber.DEFAULT.getValue(),
                        size != null ? size : Pagination.PageSize.PRODUCTS.getValue())
        ).getContent();
        return products.stream().map(product -> productMapper.mapProductToProductDetailsDTO(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDetailsDTO getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Optional<WearperfectUserPrincipal> optionalWearperfectUserPrincipal = wearperfectUserDetailsService.getOptionalLoggedInUserDetails();
            ProductDetailsDTO productDetailsDTO = productMapper.mapProductToProductDetailsDTO(product.get());
            optionalWearperfectUserPrincipal.ifPresent(wearperfectUserPrincipal -> {
                Optional<WishlistProductDTO> optionalWishlistProductDTO = wishlistProductService.findByUserIdAndProductId(wearperfectUserPrincipal.getUserId(), productId);
                optionalWishlistProductDTO.ifPresent(wishlistProductDTO -> {
                    productDetailsDTO.setWishlistProductId(wishlistProductDTO.getId());
                });
            });
            return productDetailsDTO;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<ProductCategorySizeChartBasicDetailsDTO> getProductSizeCharts() {
        List<ProductCategorySizeChart> charts = productCategorySizeChartRepository.findAll();
        return charts.stream().map(chart -> productCategorySizeChartMapper.mapProductCategorySizeChartToProductCategorySizeChartBasicDetailsDto(chart)).collect(Collectors.toList());
    }
}
