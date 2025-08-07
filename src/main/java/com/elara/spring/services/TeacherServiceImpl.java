package com.elara.spring.services;

import com.elara.spring.entities.Teacher;
import com.elara.spring.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepo;

    public TeacherServiceImpl(TeacherRepository repository) {
        this.teacherRepo = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Teacher> listTeachers() {
        return (List<Teacher>) teacherRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepo.findById(id);
    }
    @Transactional
    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        teacherRepo.deleteById(id);
    }
}
