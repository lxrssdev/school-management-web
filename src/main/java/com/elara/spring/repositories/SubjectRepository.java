package com.elara.spring.repositories;

import com.elara.spring.entities.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, Long> { }
