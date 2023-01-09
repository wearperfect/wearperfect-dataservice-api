package com.wearperfect.dataservice.api.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.wearperfect.dataservice.api.security.filters.JWTRequestFilter;
import com.wearperfect.dataservice.api.security.service.WearperfectUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	WearperfectUserDetailsService wearperfectUserDetailsService;
	
	@Autowired
	JWTRequestFilter jwtRequestFilter;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf()
		.disable()
		.cors()
		.and()
		.authorizeRequests()
		.antMatchers(
				"/v3/api-docs/**",
				"/swagger-ui",
				"/swagger-ui/**",
				"/login/**",
				"/signin/**",
				"/signup/**",
				"/feed/**",
				"/explore/**",
				"/filters/**",
				"/users/**/posts/**/comments")
		.permitAll()
		.anyRequest()
		.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://127.0.0.1:3000", "http://192.168.55.107:3000", "http://192.168.55.101:19000", "http://192.168.55.102:19000", "http://192.168.55.103:19000", "http://192.168.55.104:19000", "http://192.168.55.105:19000", "http://192.168.55.106:19000", "http://192.168.55.107:19000", "http://192.168.55.108:19000", "http://192.168.55.109:19000", "http://192.168.60.26:19000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("x-requested-with", "Content-Type", "origin", "authorization", "accept", "client-security-token"));
		configuration.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptVersion.$2Y, 12);
    }

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(wearperfectUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	return authenticationConfiguration.getAuthenticationManager();
	}
	
}
