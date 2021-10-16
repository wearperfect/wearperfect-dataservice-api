package com.wearperfect.dataservice.api.serviceImpl;

import org.springframework.beans.factory.annotation.Value;

import com.wearperfect.dataservice.api.service.AwsRegionService;

import lombok.Data;

@Data
public class AwsRegionServiceImpl implements AwsRegionService{

	@Value("${application.aws.s3.buckets.posts}")
	String postsS3Bucket;

	@Value("${cloud.aws.region.static}")
	private String postss3Region;
	
	
}
