package com.wearperfect.dataservice.api.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearperfect.dataservice.api.security.models.WearperfectUserPrincipal;
import com.wearperfect.dataservice.api.security.service.JwtUtilService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtilService jwtUtilService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorization = request.getHeader("Authorization");
        Collection<? extends GrantedAuthority> authorities = null;
        Jws<Claims> jwsClaims = null;
        Claims claims = null;
        String subject = null;
        Long userId = null;

        ObjectMapper mapper = new ObjectMapper();

        if (null != authorization && !authorization.trim().isEmpty() && authorization.startsWith("Bearer ")) {
            String jwt = authorization.substring(7);
            System.out.println("JWT Token: " + jwt);
            // validateTokenIfBlacklisted function verifies if the token is blacklisted or not.
            if (jwtUtilService.validateTokenIfBlacklisted(jwt)) {
                // extractAllClaims function verifies the token internally.
                jwsClaims = jwtUtilService.extractAllClaims(jwt);
            }
        }

        if (jwsClaims != null) {
            System.out.println("jwsClaims: " + mapper.writeValueAsString(jwsClaims));
            claims = jwsClaims.getBody();
            subject = claims.getSubject();
            System.out.println("LoggedIn Username:" + subject);
            if (claims.get("scopes") != null) {
                String scopesString = claims.get("scopes").toString();
                String[] authStrings = scopesString.split(",");
                authorities = Arrays.stream(authStrings)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            }
            if (claims.get("userId") != null) {
                userId = Long.valueOf(claims.get("userId").toString());
                System.out.println("LoggedIn UserID:" + userId);
            }
        }

        if (null != subject && null != userId && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            new WearperfectUserPrincipal(userId, subject),
                            null,
                            authorities
                    );
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            System.out.println("Authentication Object: " + mapper.writeValueAsString(SecurityContextHolder.getContext().getAuthentication()));
        }

        filterChain.doFilter(request, response);
    }

}
