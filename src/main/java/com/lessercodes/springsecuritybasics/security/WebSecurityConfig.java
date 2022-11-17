package com.lessercodes.springsecuritybasics.security;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
        val john = User.builder()
                .username("john")
                .password(passwordEncoder.encode("password"))
                .roles(Roles.STUDENT.name())
                .build();

        val admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .roles(Roles.ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(john, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
