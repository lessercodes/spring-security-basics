package com.lessercodes.springsecuritybasics.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final List<Student> students = Arrays.asList(
            new Student(1L, "James Bond"),
            new Student(2L, "Maria Jones"),
            new Student(3L, "Anna Smith")
    );

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable Long studentId) {
        return students.stream()
                .filter(s -> studentId.equals(s.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student with id [] does not exist"));
    }

}
