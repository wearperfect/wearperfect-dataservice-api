package com.wearperfect.dataservice.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wearperfect.dataservice.api.entities.PostUserMention;

public interface PostUserMentionRepository extends JpaRepository<PostUserMention, Long> {

}
