//package com.wearperfect.dataservice.api.mapper;
//
//import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
//import org.springframework.data.domain.Page;
//
//import java.util.List;
//
//public interface PageableResponseDTOMapper <E, D> {
//
//    default PageableResponseDTO<D> mapPageToPageableResponseDTO(Page<E> page, GenericMapper<E, D> mapper){
//        List<D> list = page
//                .getContent()
//                .stream()
//                .map(mapper::mapEntityToDTO)
//                .toList();
//        PageableResponseDTO<D> pageableResponseDTO = new PageableResponseDTO<>();
//        pageableResponseDTO.setList(list);
//        pageableResponseDTO.setPage(new PageableResponseDTO.PageMetadata(
//                page.getSize(),
//                page.getNumber(),
//                page.getTotalElements(),
//                page.getTotalPages()
//        ));
//        return pageableResponseDTO;
//    }
//
//}
