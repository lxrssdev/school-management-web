package com.elara.spring.services;

import com.elara.spring.entities.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    List<Subject> getAllSubjects();
    Optional<Subject> findById(int id);
    void save(Subject subject);
    void delete(Subject subject);
}
