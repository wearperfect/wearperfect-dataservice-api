package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.AwsS3BucketDTO;
import com.wearperfect.dataservice.api.entities.AwsS3Bucket;

@Mapper(uses = { UtilityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AwsS3BucketMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	AwsS3BucketDTO mapAwsS3BucketToAwsS3BucketDto(AwsS3Bucket awsS3Bucket);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	AwsS3Bucket mapAwsS3BucketDtoToAwsS3Bucket(AwsS3BucketDTO awsS3BucketDto);
}
