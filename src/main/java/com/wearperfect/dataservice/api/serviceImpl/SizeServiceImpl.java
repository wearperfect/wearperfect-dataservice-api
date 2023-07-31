package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.SizeBasicDetailsDTO;
import com.wearperfect.dataservice.api.entity.Size;
import com.wearperfect.dataservice.api.mapper.SizeMapper;
import com.wearperfect.dataservice.api.repository.SizeRepository;
import com.wearperfect.dataservice.api.service.SizeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    SizeMapper sizeMapper;

    @Override
    public SizeBasicDetailsDTO getSizeBasicDetailsById(Short id) {
        Optional<Size> size = sizeRepository.findById(id);
        if (size.isPresent()) {
            return sizeMapper.mapSizeToSizeBasicDetailsDTO(size.get());
        } else {
            throw new EntityNotFoundException("Size with ID " + id + " is not found.");
        }
    }
}
