package com.wearperfect.dataservice.api.service;

import java.util.List;
import java.util.Set;

import com.wearperfect.dataservice.api.dto.HashTagDTO;
import com.wearperfect.dataservice.api.dto.PostHashTagDTO;

public interface HashTagService {
	
	List<HashTagDTO> saveHashTags(Set<String> hashTags);
	
	List<PostHashTagDTO> savePostHashTags(List<Long> hashTagIdList, Long postId);
}
