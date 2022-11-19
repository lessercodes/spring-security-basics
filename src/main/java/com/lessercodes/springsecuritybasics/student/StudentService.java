package com.lessercodes.springsecuritybasics.student;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();

    @PostConstruct
    private void load() {
        students.put(1L, new Student(1L, "James Bond"));
        students.put(2L, new Student(2L, "Maria Jones"));
        students.put(3L, new Student(3L, "Anna Smith"));
    }

    public List<Student> getAllStudents() {
        return students.values().stream()
                .toList();
    }

    public Student getStudent(Long id) {
        if (!students.containsKey(id)) {
            throw new IllegalStateException("Student with id [] does not exist");
        }
        return students.get(id);
    }

    public void registerStudent(Student student) {
        val id = System.currentTimeMillis();
        students.putIfAbsent(id, new Student(id, student.getName()));
        log.info(String.format("Create student with id [%s]", id));
    }

    public void deleteStudent(Long id) {
        log.info(String.format("Delete student with id [%s]", id));
        students.remove(id);
    }

    public void updateStudent(Long id, Student student) {
        log.info(String.format("Update student with id [%s], data: [%s]", id, student.toString()));
    }
}
