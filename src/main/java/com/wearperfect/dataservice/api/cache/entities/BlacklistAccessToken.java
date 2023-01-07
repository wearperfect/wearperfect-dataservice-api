package com.wearperfect.dataservice.api.cache.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@AllArgsConstructor
@RedisHash("blacklist_access_token")
public class BlacklistAccessToken {
    @Id
    String token;
}
