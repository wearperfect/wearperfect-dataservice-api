package com.wearperfect.dataservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entity.AwsS3Bucket;

@Repository
public interface AwsS3BucketRepository extends JpaRepository<AwsS3Bucket, Integer>{

}
