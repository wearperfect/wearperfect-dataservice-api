package com.wearperfect.dataservice.api.service;

import java.util.List;
import java.util.Set;

import com.wearperfect.dataservice.api.dto.PostUserMentionDTO;

public interface PostUserMentionService {

	List<PostUserMentionDTO> savePostUserMentions(Long postId, Long mentionedBy, Set<String> usernameSet);
}
