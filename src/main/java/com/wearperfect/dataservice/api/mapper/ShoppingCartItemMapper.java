package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ShoppingCartItemDTO;
import com.wearperfect.dataservice.api.dto.ShoppingCartItemDetailsDTO;
import com.wearperfect.dataservice.api.entity.ShoppingCartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {UtilityMapper.class, ProductMapper.class, SizeMapper.class, DiscountCouponMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShoppingCartItemMapper {
    //@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
    //@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
    ShoppingCartItem mapShoppingCartItemDtoToShoppingCartItem(ShoppingCartItemDTO shoppingCartItemDTO);

    //@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
    //@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
    ShoppingCartItemDTO mapShoppingCartItemToShoppingCartItemDto(ShoppingCartItem shoppingCartItem);

    ShoppingCartItemDetailsDTO mapShoppingCartItemToShoppingCartItemDetailsDto(ShoppingCartItem shoppingCartItem);
}
