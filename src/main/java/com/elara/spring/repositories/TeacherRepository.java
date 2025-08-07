package com.elara.spring.repositories;

import com.elara.spring.entities.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {}
