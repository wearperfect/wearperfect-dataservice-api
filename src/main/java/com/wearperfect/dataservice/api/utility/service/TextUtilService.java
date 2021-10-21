package com.wearperfect.dataservice.api.utility.service;

import java.util.Set;

public interface TextUtilService {

	Set<String> extractHashTagsFromText(String text);
	
	Set<String> extractUserMentionsFromText(String text);
}
