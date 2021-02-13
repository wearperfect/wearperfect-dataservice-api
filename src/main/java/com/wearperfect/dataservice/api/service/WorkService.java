package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.UserWorksResponseDTO;
import com.wearperfect.dataservice.api.dto.WorkDTO;

public interface WorkService {

	UserWorksResponseDTO getUserWorkList(Long userId);

	UserWorksResponseDTO addUserWork(Long userId, WorkDTO workDto);

	UserWorksResponseDTO updateUserWork(Long userId, Long workId, WorkDTO workDto);

	UserWorksResponseDTO deleteUserWork(Long userId, Long workId, WorkDTO workDto);

}
