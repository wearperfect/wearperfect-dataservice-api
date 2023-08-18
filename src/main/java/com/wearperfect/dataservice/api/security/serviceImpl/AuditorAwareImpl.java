package com.wearperfect.dataservice.api.security.serviceImpl;

import com.wearperfect.dataservice.api.security.models.WearperfectUserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        WearperfectUserPrincipal wearperfectUserPrincipal = (WearperfectUserPrincipal) auth.getPrincipal();
        return Optional.of(wearperfectUserPrincipal.getUserId());
    }
}
