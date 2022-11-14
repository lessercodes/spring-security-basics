package com.lessercodes.springsecuritybasics.config;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeRequests(conf -> {
                    conf.antMatchers("/", "index", "/css/**", "/js/**");
                    conf.anyRequest().authenticated();
                })
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        val john = User.builder()
                .username("john")
                .password("password")
                .roles("STUDENT")
                .build();

        return new InMemoryUserDetailsManager(john);
    }
}
