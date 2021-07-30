package com.wearperfect.dataservice.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message>{

	List<Message> findBySentBy(Long sentBy);
	
	List<Message> findBySentByAndSentTo(Long sentBy, Long sentTo);
}
