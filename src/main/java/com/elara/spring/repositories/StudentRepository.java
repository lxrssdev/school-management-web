package com.elara.spring.repositories;

import com.elara.spring.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {}
