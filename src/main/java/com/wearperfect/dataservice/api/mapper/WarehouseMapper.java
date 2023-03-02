package com.wearperfect.dataservice.api.mapper;


import com.wearperfect.dataservice.api.dto.WarehouseBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.WarehouseDTO;
import com.wearperfect.dataservice.api.entity.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class, AddressMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WarehouseMapper {
    Warehouse mapWarehouseDtoToWarehouse(WarehouseDTO warehouseDTO);

    WarehouseDTO mapWarehouseToWarehouseDto(Warehouse Warehouse);

    WarehouseBasicDetailsDTO mapWarehouseToWarehouseBasicDetailsDTO(Warehouse Warehouse);
}
