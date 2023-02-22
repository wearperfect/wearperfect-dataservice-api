package com.wearperfect.dataservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wearperfect.dataservice.api.entity.PostUserMention;

public interface PostUserMentionRepository extends JpaRepository<PostUserMention, Long> {

}
