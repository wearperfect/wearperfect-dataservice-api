//package com.wearperfect.dataservice.api.mapper;
//
//
//import org.mapstruct.Mapper;
//import org.mapstruct.NullValuePropertyMappingStrategy;
//import org.mapstruct.ReportingPolicy;
//
//@Mapper(uses = {UtilityMapper.class, WishlistCollectionProductMapper.class, WishlistProductMapper.class, ProductMapper.class},
//        unmappedTargetPolicy = ReportingPolicy.IGNORE,
//        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//public interface GenericMapper<E, D> {
//    public D mapEntityToDTO(E entity);
//
//    public E mapDTOToEntity(D dto);
//}
