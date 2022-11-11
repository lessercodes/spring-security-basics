package com.lessercodes.springsecuritybasics.student;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Student {

    private final Long id;
    private final String name;

}
