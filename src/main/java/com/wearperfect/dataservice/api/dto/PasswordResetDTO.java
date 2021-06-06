package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PasswordResetDTO {
	
	String oldPassword;
	
	String newPassword;
	
	String confirmNewPassword;
}
