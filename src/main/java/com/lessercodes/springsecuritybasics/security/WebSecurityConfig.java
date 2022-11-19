package com.lessercodes.springsecuritybasics.security;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.lessercodes.springsecuritybasics.security.Roles.ADMIN;
import static com.lessercodes.springsecuritybasics.security.Roles.ADMIN_TRAINEE;
import static com.lessercodes.springsecuritybasics.security.Roles.STUDENT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeRequests(conf -> {
                    conf.antMatchers("/", "index", "/css/**", "/js/**");
                    conf.antMatchers("/api/**").hasRole(STUDENT.name());
                    conf.anyRequest().authenticated();
                })
                .httpBasic()
                .and()
                .csrf()
                    .disable()
                .build();
    }

    @Bean
    public UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
        val john = User.builder()
                .username("john")
                .password(passwordEncoder.encode("password"))
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        val admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        val trainee = User.builder()
                .username("trainee")
                .password(passwordEncoder.encode("password"))
                .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(john, admin, trainee);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
