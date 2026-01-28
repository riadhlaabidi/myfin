package tn.riadh.myfin.infrastructure.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
public class SecurityConfig {

    // private final StoreResolver storeResolver;

    public SecurityConfig() {
        // this.storeResolver = storeResolver;
    }

    // Add the monetary context filter after the last authentication filter, since
    // we need to know the current authenticated user in order to resolve the store
    // and the correct currency before performing monetary operations.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterAfter(null, AnonymousAuthenticationFilter.class);
        return http.build();
    }

    // Prevent registering the monetary context filter bean with the embedded
    // container, which would otherwise cause the filter to be invoked twice: one by
    // the container as a Spring bean and one by Spring Security in a different
    // order. I only want the filter to be invoked in Spring Security while
    // taking advantage of dependency injection by decalring it as a component.
    @Bean
    public FilterRegistrationBean<MonetaryContextFilter> monetaryFilterRegistration(MonetaryContextFilter filter) {
        FilterRegistrationBean<MonetaryContextFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }
}
