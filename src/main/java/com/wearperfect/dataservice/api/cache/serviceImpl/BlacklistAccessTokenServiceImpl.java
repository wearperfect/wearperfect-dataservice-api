package com.wearperfect.dataservice.api.cache.serviceImpl;

import com.wearperfect.dataservice.api.cache.entities.BlacklistAccessToken;
import com.wearperfect.dataservice.api.cache.repository.BlacklistAccessTokenRepository;
import com.wearperfect.dataservice.api.cache.service.BlacklistAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlacklistAccessTokenServiceImpl implements BlacklistAccessTokenService {

    @Autowired
    BlacklistAccessTokenRepository blacklistAccessTokenRepository;

    @Override
    public Boolean blacklistAccessToken(String accessToken) {
        BlacklistAccessToken savedBlacklistAccessToken = blacklistAccessTokenRepository.save(new BlacklistAccessToken(accessToken));
        List<BlacklistAccessToken> list = (List<BlacklistAccessToken>) blacklistAccessTokenRepository.findAll();
        System.out.println("Total BlackListAccessTokens:::"+list.size());
        if(savedBlacklistAccessToken.getToken().equals(accessToken)){
            System.out.println("Saved BlackListAccessToken Successfully.");
            return true;
        }else{
            System.out.println("Saving BlackListAccessToken Failed.");
            return false;
        }
    }

    @Override
    public boolean verifyIfAccessTokenIsBlacklisted(String accessToken) {
        return blacklistAccessTokenRepository.existsById(accessToken);
    }
}
