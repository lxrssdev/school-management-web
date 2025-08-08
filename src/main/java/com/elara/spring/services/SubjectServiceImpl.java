package com.elara.spring.services;

import com.elara.spring.entities.Subject;
import com.elara.spring.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return (List<Subject>) subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> findById(int id) {
        return subjectRepository.findById((long) id);
    }

    @Override
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void delete(Subject subject) {
        subjectRepository.delete(subject);
    }
}
