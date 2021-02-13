package com.wearperfect.dataservice.api.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.UserWorksResponseDTO;
import com.wearperfect.dataservice.api.dto.WorkDTO;
import com.wearperfect.dataservice.api.entities.Work;
import com.wearperfect.dataservice.api.entities.Work_;
import com.wearperfect.dataservice.api.mappers.WorkMapper;
import com.wearperfect.dataservice.api.repositories.WorkRepository;
import com.wearperfect.dataservice.api.service.WorkService;

@Service
@Transactional
public class WorkServiceImpl implements WorkService {

	@Autowired
	WorkRepository workRepository;

	@Autowired
	WorkMapper workMapper;

	@Override
	public UserWorksResponseDTO getUserWorkList(Long userId) {
		List<Work> works = workRepository.findByWorkedBy(userId,
				PageRequest.of(0, 50, Sort.by(Direction.DESC, Work_.WORKED_FROM)));
		List<WorkDTO> workDtoList = works.stream().map(work -> workMapper.mapWorkToWorkDto(work)).collect(Collectors.toList());
		return new UserWorksResponseDTO(userId, workDtoList);
	}

	@Override
	public UserWorksResponseDTO addUserWork(Long userId, WorkDTO workDto) {

		Work work = workMapper.mapWorkDtoToWork(workDto);
		if ((null != workDto.getId())
				|| (null == work.getWorkedAs()
						&& (null == work.getWorkedAsAlt() || work.getWorkedAsAlt().trim().length() <= 0))
				|| (null == work.getWorkedAt()
						&& (null == work.getWorkedAtAlt() || work.getWorkedAtAlt().length() <= 0))
				|| (null == work.getWorkedFrom())) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		if(null == work.getWorkedTo()) {
			work.setWorkingActively(true);
		}else {
			work.setWorkingActively(false);
		}
		work.setWorkedBy(userId);
		work.setActive(true);
		work.setCreatedBy(userId);
		work.setCreatedOn(new Date());
		work.setLastUpdatedBy(null);
		work.setLastUpdatedOn(null);
		workRepository.save(work);
		return getUserWorksResponseDTO(userId, workMapper.mapWorkToWorkDto(work));
	}

	@Override
	public UserWorksResponseDTO updateUserWork(Long userId, Long workId, WorkDTO workDto) {

		Work work = workMapper.mapWorkDtoToWork(workDto);
		if ((null == workDto.getId()) || workId != work.getId()
				|| (null == work.getWorkedAs()
						&& (null == work.getWorkedAsAlt() || work.getWorkedAsAlt().trim().length() <= 0))
				|| (null == work.getWorkedAt()
						&& (null == work.getWorkedAtAlt() || work.getWorkedAtAlt().length() <= 0))
				|| (null == work.getWorkedFrom())) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		Work existingWork = workRepository.findByIdAndWorkedBy(work.getId(), userId);

		if(null == work.getWorkedTo()) {
			work.setWorkingActively(true);
		}else {
			work.setWorkingActively(false);
		}
		work.setActive(true);
		work.setCreatedBy(existingWork.getCreatedBy());
		work.setCreatedOn(existingWork.getCreatedOn());
		work.setLastUpdatedBy(userId);
		work.setLastUpdatedOn(new Date());
		workRepository.save(work);
		return getUserWorksResponseDTO(userId, workMapper.mapWorkToWorkDto(existingWork));
	}

	@Override
	public UserWorksResponseDTO deleteUserWork(Long userId, Long workId, WorkDTO workDto) {
		
		if(workId != workDto.getId() || userId != workDto.getWorkedBy()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		workRepository.deleteByIdAndWorkedBy(workId, userId);
		return getUserWorksResponseDTO(userId, workDto);
	}
	
	UserWorksResponseDTO getUserWorksResponseDTO(Long userId, WorkDTO workDto) {
		List<WorkDTO> workDtoList = new ArrayList<>();
		workDtoList.add(workDto);
		return new UserWorksResponseDTO(userId, workDtoList);
	}

}
