package com.wearperfect.dataservice.api.cache.repository;

import com.wearperfect.dataservice.api.cache.entities.BlacklistAccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistAccessTokenRepository extends CrudRepository<BlacklistAccessToken, String> {
}
