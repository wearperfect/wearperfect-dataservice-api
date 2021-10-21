package com.wearperfect.dataservice.api.utility.serviceImpl;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.utility.service.TextUtilService;

@Service
public class TextUtilServiceImpl implements TextUtilService{

	@Override
	public Set<String> extractHashTagsFromText(String text) {
		String hashTagRegexPattern = "(#\\w+)";
		Pattern hashTagPattern = Pattern.compile(hashTagRegexPattern);
		Matcher hashTagMatcher = hashTagPattern.matcher(text);
		LinkedHashSet<String> hashTagSet = new LinkedHashSet<String>();
		while (hashTagMatcher.find()) {
			hashTagSet.add(hashTagMatcher.group(1).substring(1));
		}
		return hashTagSet;
	}

	@Override
	public Set<String> extractUserMentionsFromText(String text) {
		String userMentionRegexPattern = "(@\\w+)";
		Pattern userMentionPattern = Pattern.compile(userMentionRegexPattern);
		Matcher userMentionMatcher = userMentionPattern.matcher(text);
		LinkedHashSet<String> userMentionSet = new LinkedHashSet<String>();
		while (userMentionMatcher.find()) {
			userMentionSet.add(userMentionMatcher.group(1).substring(1));
		}
		return userMentionSet;
	}

}
