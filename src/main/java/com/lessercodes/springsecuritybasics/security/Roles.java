package com.lessercodes.springsecuritybasics.security;

import java.util.Set;

import static com.lessercodes.springsecuritybasics.security.Permissions.*;

public enum Roles {

    STUDENT(Set.of(STUDENT_READ)),
    ADMIN(Set.of(STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE)),
    ADMIN_TRAINEE(Set.of(STUDENT_READ, COURSE_READ));

    private final Set<Permissions> permissions;

    Roles(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
