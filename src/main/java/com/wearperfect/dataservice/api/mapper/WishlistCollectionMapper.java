package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.WishlistCollectionDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionDetailsDTO;
import com.wearperfect.dataservice.api.entity.WishlistCollection;
import com.wearperfect.dataservice.api.entity.WishlistProduct;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Comparator;

@Mapper(
        uses = {UtilityMapper.class, WishlistCollectionProductMapper.class, WishlistProductMapper.class, ProductMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface WishlistCollectionMapper {
    @Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "epochSecondToInstantConverter")
    @Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "epochSecondToInstantConverter")
    WishlistCollection mapWishlistCollectionDtoToWishlistCollection(WishlistCollectionDTO wishlistCollectionDTO);

    @Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "epochSecondToInstantConverter")
    @Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "epochSecondToInstantConverter")
    void updateWishlistCollectionFromWishlistCollectionDTO(WishlistCollectionDTO wishlistCollectionDTO, @MappingTarget WishlistCollection wishlistCollection);

    @Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "instantToEpochSecondConverter")
    @Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "instantToEpochSecondConverter")
    void updateWishlistCollectionDTOFromWishlistCollection(@MappingTarget WishlistCollectionDTO wishlistCollectionDTO, WishlistCollection wishlistCollection);

    @Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "instantToEpochSecondConverter")
    @Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "instantToEpochSecondConverter")
    WishlistCollectionDTO mapWishlistCollectionToWishlistCollectionDto(WishlistCollection wishlistCollection);

    @Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "instantToEpochSecondConverter")
    @Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "instantToEpochSecondConverter")
    WishlistCollectionDetailsDTO mapWishlistCollectionToWishlistCollectionDetailsDto(WishlistCollection wishlistCollection);
}
