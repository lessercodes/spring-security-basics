package com.lessercodes.springsecuritybasics.student;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
@RequiredArgsConstructor
public class StudentManagementController {

    private final StudentService studentService;

    @GetMapping
    @PreAuthorize("hasAnyRole(T(com.lessercodes.springsecuritybasics.security.Roles).ADMIN.name(), " +
            "T(com.lessercodes.springsecuritybasics.security.Roles).ADMIN_TRAINEE.name())")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    @PreAuthorize("hasAuthority(T(com.lessercodes.springsecuritybasics.security.Permissions).COURSE_WRITE.getPermission())")
    public void registerStudent(@RequestBody Student student) {
        studentService.registerStudent(student);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(T(com.lessercodes.springsecuritybasics.security.Permissions).COURSE_WRITE.getPermission())")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(T(com.lessercodes.springsecuritybasics.security.Permissions).COURSE_WRITE.getPermission())")
    public void updateStudent(@PathVariable Long id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
    }

}
