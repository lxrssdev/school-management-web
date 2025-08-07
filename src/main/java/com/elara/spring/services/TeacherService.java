package com.elara.spring.services;

import com.elara.spring.entities.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> listTeachers();
    Optional<Teacher> findById(Long id);
    Teacher save(Teacher teacher);
    void deleteById(Long id);
}
