package com.wearperfect.dataservice.api.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.service.AwsS3BucketService;

@Service
public class AwsS3BucketServiceimpl implements AwsS3BucketService{

	@Value("${application.aws.s3.buckets.posts}")
	String postsS3Bucket;

	@Value("${cloud.aws.region.static}")
	private String postss3Region;
	
	
}
