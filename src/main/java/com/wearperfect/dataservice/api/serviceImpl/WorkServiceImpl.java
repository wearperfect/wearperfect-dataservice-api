package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

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
	public List<WorkDTO> addUserWorks(Long userId, List<WorkDTO> workDtos) {
		
		List<Work> works = workDtos.stream().map(workDto->workMapper.mapWorkDtoToWork(workDto)).collect(Collectors.toList());
		works.forEach(work->{
			work.setCreatedOn(new Date());
			work.setCreatedBy(userId);
			work.setActive(true);
			work.setWorkedFrom(new Date());
			work.setWorkingActively(true);
		});
		workRepository.saveAll(works);
		return works.stream().map(work->workMapper.mapWorkToWorkDto(work)).collect(Collectors.toList());
	}

}
