package com.elara.spring.services;

import com.elara.spring.entities.Student;
import com.elara.spring.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;

    public StudentServiceImpl(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> listStudents() {
        return (List<Student>) studentRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepo.findById(id);
    }

    @Transactional
    @Override
    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }

    @Transactional
    @Override
    public void deleteStudentById(Long id) {
        studentRepo.deleteById(id);
    }
}
