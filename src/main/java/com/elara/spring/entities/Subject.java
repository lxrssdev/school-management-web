package com.elara.spring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la materia no puede estar vacío!")
    @Size(min=3, max=30, message = "El nombre tiene que estar entre 3 y 30 caracteres")
    private String name;

    @NotBlank(message = "El área no puede estar vacía")
    @Size(max = 50, message = "El área debe tener máximo 50 caracteres")
    private String area;

    @Min(value = 1, message = "El semestre debe ser al menos 1")
    @Max(value = 6, message = "El semestre debe ser como máximo 6")
    private int semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    public Subject() {}

    public Subject(String name, String area, int semester, Teacher teacher) {
        this.name = name;
        this.area = area;
        this.semester = semester;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
