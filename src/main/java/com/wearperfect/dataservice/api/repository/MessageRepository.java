package com.wearperfect.dataservice.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message>{

	List<Message> findBySentBy(Long sentBy);
	
	List<Message> findBySentByAndSentTo(Long sentBy, Long sentTo);
	
	List<Message> findBySentByInAndSentToIn(Long[] sentByList, Long[] sentToList, Pageable page);
}
