package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionDetailsDTO;
import com.wearperfect.dataservice.api.entity.WishlistCollection;
import com.wearperfect.dataservice.api.entity.WishlistCollectionProduct;
import com.wearperfect.dataservice.api.entity.WishlistCollection_;
import com.wearperfect.dataservice.api.mapper.WishlistCollectionMapper;
import com.wearperfect.dataservice.api.repository.WishlistCollectionRepository;
import com.wearperfect.dataservice.api.service.WishlistCollectionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WishlistCollectionServiceImpl implements WishlistCollectionService {

    WishlistCollectionMapper wishlistCollectionMapper;

    WishlistCollectionRepository wishlistCollectionRepository;

    public WishlistCollectionServiceImpl(
            WishlistCollectionMapper wishlistCollectionMapper,
            WishlistCollectionRepository wishlistCollectionRepository
    ) {
        this.wishlistCollectionMapper = wishlistCollectionMapper;
        this.wishlistCollectionRepository = wishlistCollectionRepository;
    }

    @Override
    public PageableResponseDTO<WishlistCollectionDetailsDTO> getWishlistCollections(Long userId, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, WishlistCollection_.CREATED_ON).
                        and(Sort.by(Sort.Direction.DESC, WishlistCollection_.LAST_UPDATED_ON))
        );
        Page<WishlistCollection> wishlistCollectionPage;
        if (userId != null) {
            wishlistCollectionPage = wishlistCollectionRepository
                    .findByUserId(userId, pageRequest);
        } else {
            wishlistCollectionPage = wishlistCollectionRepository.findAll(pageRequest);
        }
        List<WishlistCollectionDetailsDTO> wishlistCollectionDetailsDTOList = wishlistCollectionPage.getContent().stream()
                .map(wishlistCollection -> {
                    // Set default Cover Wishlist Collection Product if coverWishlistCollectionProduct is NULL
                    if (wishlistCollection.getCoverWishlistCollectionProduct() == null &&
                            wishlistCollection.getWishlistCollectionProducts() != null &&
                            !wishlistCollection.getWishlistCollectionProducts().isEmpty()) {
                        List<WishlistCollectionProduct> activeWishlistCollectionProductList = wishlistCollection
                                .getWishlistCollectionProducts()
                                .stream()
                                //.filter(wishlistCollectionProduct -> wishlistCollectionProduct.getWishlistProduct().getActive())
                                .toList();
                        if (!activeWishlistCollectionProductList.isEmpty()) {
                            wishlistCollection.setCoverWishlistCollectionProduct(
                                    activeWishlistCollectionProductList.get(0)
                            );
                        }
                    }
                    return wishlistCollectionMapper.mapWishlistCollectionToWishlistCollectionDetailsDto(wishlistCollection);
                }).toList();
        PageableResponseDTO<WishlistCollectionDetailsDTO> pageableResponseDTO = new PageableResponseDTO<>();
        pageableResponseDTO.setList(wishlistCollectionDetailsDTOList);
        pageableResponseDTO.setPage(new PageableResponseDTO.PageMetadata(
                wishlistCollectionPage.getSize(),
                wishlistCollectionPage.getNumber(),
                wishlistCollectionPage.getTotalElements(),
                wishlistCollectionPage.getTotalPages()
        ));
        return pageableResponseDTO;
    }

    @Override
    public WishlistCollectionDetailsDTO getWishlistCollectionById(Long wishlistCollectionId) {
        Optional<WishlistCollection> wishlistCollection = wishlistCollectionRepository.findById(wishlistCollectionId);
        if (wishlistCollection.isPresent()) {
            return wishlistCollectionMapper.mapWishlistCollectionToWishlistCollectionDetailsDto(wishlistCollection.get());
        } else {
            throw new EntityNotFoundException("Wishlist Collection by ID " + wishlistCollectionId + " not found.");
        }
    }

    @Override
    public WishlistCollectionDTO createWishlistCollection(WishlistCollectionDTO wishlistCollectionDTO) {
        try {
            WishlistCollection wishlistCollection = wishlistCollectionMapper.mapWishlistCollectionDtoToWishlistCollection(wishlistCollectionDTO);
            wishlistCollection = wishlistCollectionRepository.save(wishlistCollection);
            return wishlistCollectionMapper.mapWishlistCollectionToWishlistCollectionDto(wishlistCollection);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in adding wishlist collection." + e.getMessage());
        }
    }

    @Override
    public WishlistCollectionDTO updateWishlistCollection(WishlistCollectionDTO wishlistCollectionDTO) {
        try {
            Optional<WishlistCollection> optionalWishlistCollection = wishlistCollectionRepository.findById(wishlistCollectionDTO.getId());
            if (optionalWishlistCollection.isPresent()) {
                WishlistCollection wishlistCollection = optionalWishlistCollection.get();
                wishlistCollectionMapper.updateWishlistCollectionFromWishlistCollectionDTO(wishlistCollectionDTO, wishlistCollection);
                wishlistCollection = wishlistCollectionRepository.saveAndFlush(wishlistCollection);
                return wishlistCollectionMapper.mapWishlistCollectionToWishlistCollectionDto(wishlistCollection);
            } else {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Collection doesn't exist with ID " + wishlistCollectionDTO.getId());
            }
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in Updating wishlist collection." + e.getMessage());
        }
    }

    @Override
    public Long deleteWishlistCollectionById(Long wishlistCollectionId) {
        try {
            wishlistCollectionRepository.deleteById(wishlistCollectionId);
            return wishlistCollectionId;
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in removing item in wishlist collection by ID " + wishlistCollectionId + ".");
        }
    }

    @Override
    public void removeCoverWishlistCollectionProductByWishlistProductIdFromAllWishlistCollections(Long wishlistProductId) {
        try {
            List<WishlistCollection> wishlistCollectionList = wishlistCollectionRepository.getByCoverWishlistCollectionProductLinkedWishListProductId(wishlistProductId);
            wishlistCollectionList.forEach(wishlistCollection -> {
                wishlistCollection.setCoverWishlistCollectionProductId(null);
            });
            wishlistCollectionRepository.saveAll(wishlistCollectionList);
        } catch (Exception e) {
            throw new HttpClientErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Error in removing CoverWishlistCollectionProduct by %s WishlistProductId from all WishlistCollections.", wishlistProductId)
            );
        }
    }
}
