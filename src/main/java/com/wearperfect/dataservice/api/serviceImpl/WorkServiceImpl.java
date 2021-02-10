package com.wearperfect.dataservice.api.serviceImpl;

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
import org.springframework.web.client.HttpStatusCodeException;

import com.wearperfect.dataservice.api.dto.WorkDTO;
import com.wearperfect.dataservice.api.entities.Work;
import com.wearperfect.dataservice.api.entities.Work_;
import com.wearperfect.dataservice.api.mappers.WorkMapper;
import com.wearperfect.dataservice.api.repositories.WorkRepository;
import com.wearperfect.dataservice.api.service.WorkService;

@Service
@Transactional
public class WorkServiceImpl implements WorkService{
	
	@Autowired
	WorkRepository workRepository;
	
	@Autowired
	WorkMapper workMapper;

	@Override
	public List<WorkDTO> getUserWorkList(Long userId) {
		List<Work> works = workRepository.findByWorkedBy(userId, PageRequest.of(0, 50, Sort.by(Direction.DESC, Work_.WORKED_FROM)));
		return works.stream().map(work->workMapper.mapWorkToWorkDto(work)).collect(Collectors.toList());
	}

	@Override
	public WorkDTO addUserWork(Long userId, WorkDTO workDto) {
		
		Work work = workMapper.mapWorkDtoToWork(workDto);
		if ((null == work.getWorkedAs() && null == work.getWorkedAsAlt())
				|| (null == work.getWorkedAt() && null == work.getWorkedAtAlt()) || (null == work.getWorkedFrom())) {
			throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {
				private static final long serialVersionUID = 1L;
			};
		}

		work.setActive(true);
		work.setCreatedBy(userId);
		work.setCreatedOn(new Date());
		work.setLastUpdatedBy(null);
		work.setLastUpdatedOn(null);
		workRepository.save(work);
		return workMapper.mapWorkToWorkDto(work);
	}

	@Override
	public WorkDTO updateUserWork(Long userId, WorkDTO workDto) {
		
		Work work = workMapper.mapWorkDtoToWork(workDto);
		if ( (null == workDto.getId()) || (null == work.getWorkedAs() && null == work.getWorkedAsAlt())
				|| (null == work.getWorkedAt() && null == work.getWorkedAtAlt()) || (null == work.getWorkedFrom())) {
			throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {
				private static final long serialVersionUID = 1L;
			};
		}
		
		Work existingWork = workRepository.findByIdAndWorkedBy(work.getId(), userId);
		
		work.setActive(true);
		work.setCreatedBy(existingWork.getCreatedBy());
		work.setCreatedOn(existingWork.getCreatedOn());
		work.setLastUpdatedBy(userId);
		work.setLastUpdatedOn(new Date());
		workRepository.save(work);
		return workMapper.mapWorkToWorkDto(work);
	}

}
