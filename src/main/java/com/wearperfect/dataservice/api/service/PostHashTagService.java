package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostHashTagDTO;

public interface PostHashTagService {

	List<PostHashTagDTO> savePostHashTags(List<Long> hashTagIdList, Long postId);
}
