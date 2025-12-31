package tn.riadh.myfin.infrastructure.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextHolderFilter;

import tn.riadh.myfin.infrastructure.web.filter.MonetaryContextFilter;

@Configuration
public class SecurityConfig {

    private final StoreResolver storeResolver;

    public SecurityConfig(StoreResolver storeResolver) {
        this.storeResolver = storeResolver;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterAfter(new MonetaryContextFilter(storeResolver), SecurityContextHolderFilter.class);
        return http.build();
    }

    @Bean
    public FilterRegistrationBean<MonetaryContextFilter> monetaryFilterRegistration(MonetaryContextFilter filter) {
        FilterRegistrationBean<MonetaryContextFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }
}
