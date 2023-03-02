package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductInventoryItemBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductInventoryItemDTO;
import com.wearperfect.dataservice.api.entity.ProductInventoryItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class, SizeMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductInventoryItemMapper {

    ProductInventoryItem mapProductInventoryItemDtoToProductInventoryItem(ProductInventoryItemDTO productInventoryItemDTO);

    ProductInventoryItemDTO mapProductInventoryItemToProductInventoryItemDto(ProductInventoryItem productInventoryItem);

    ProductInventoryItemBasicDetailsDTO mapProductInventoryItemBasicDetailsDtoToProductInventoryItem(ProductInventoryItem productInventoryItem);
}
