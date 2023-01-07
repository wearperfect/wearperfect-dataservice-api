package com.wearperfect.dataservice.api.cache.service;

import com.wearperfect.dataservice.api.cache.entities.BlacklistAccessToken;

public interface BlacklistAccessTokenService {

    Boolean blacklistAccessToken(String accessToken);

    boolean verifyIfAccessTokenIsBlacklisted(String accessToken);
}
