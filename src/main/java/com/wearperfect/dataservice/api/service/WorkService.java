package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.WorkDTO;

public interface WorkService {

	List<WorkDTO> getUserWorkList(Long userId);

	WorkDTO addUserWork(Long userId, WorkDTO workDto);

	WorkDTO updateUserWork(Long userId, Long workId, WorkDTO workDto);

	WorkDTO deleteUserWork(Long userId, Long workId, WorkDTO workDto);

}
