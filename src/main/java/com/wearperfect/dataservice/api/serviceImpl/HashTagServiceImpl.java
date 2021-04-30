package com.wearperfect.dataservice.api.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.wearperfect.dataservice.api.repositories.HashTagRepository;
import com.wearperfect.dataservice.api.service.HashTagService;

public class HashTagServiceImpl implements HashTagService{

	@Autowired
	HashTagRepository hashTagRepository;
}
