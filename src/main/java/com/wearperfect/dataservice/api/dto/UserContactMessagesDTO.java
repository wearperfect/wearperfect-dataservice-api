package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserContactMessagesDTO {
	Integer id;
	Long userId;
	Long contactUserId;
	Long firstContactedOn;
	Long lastContactedOn;
	Boolean active;
	UserBasicDetailsDTO userDetails;
	UserBasicDetailsDTO contactUserDetails;
	List<MessageDetailsDTO> messages;
}
