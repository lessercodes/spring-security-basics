package com.lessercodes.springsecuritybasics.security;

import lombok.val;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.lessercodes.springsecuritybasics.security.Permissions.COURSE_READ;
import static com.lessercodes.springsecuritybasics.security.Permissions.COURSE_WRITE;
import static com.lessercodes.springsecuritybasics.security.Permissions.STUDENT_READ;
import static com.lessercodes.springsecuritybasics.security.Permissions.STUDENT_WRITE;

public enum Roles {

    STUDENT(Set.of()),
    ADMIN(Set.of(STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE)),
    ADMIN_TRAINEE(Set.of(STUDENT_READ, COURSE_READ));

    private final Set<Permissions> permissions;

    Roles(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<? extends GrantedAuthority> getGrantedAuthorities() {
        val authorities = permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
