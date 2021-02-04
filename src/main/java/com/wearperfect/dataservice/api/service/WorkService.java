package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.WorkDTO;

public interface WorkService {

	List<WorkDTO> getUserWorkList(Long userId);

	List<WorkDTO> addUserWorks(Long userId, List<WorkDTO> workDtos);

}
