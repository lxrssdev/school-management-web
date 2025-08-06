package com.elara.spring.services;

import com.elara.spring.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> listStudents();
    Optional<Student> getStudentById(Long id);
    Student saveStudent(Student student);
    void deleteStudentById(Long id);
}
