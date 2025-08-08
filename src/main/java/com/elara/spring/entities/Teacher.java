package com.elara.spring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo \"Nombre\" no puede estar vacío!")
    @Size(min=2, max=30, message = "El nombre debe estar entre 2 y 30 caracteres")
    private String name;

    @NotBlank(message = "El campo \"Apellido\" no puede estar vacío!")
    @Size(min=2, max=30, message = "El apellido debe estar entre 2 y 30 caracteres")
    private String lastname;

    @NotBlank(message = "El campo \"Correo\" no puede estar vacío!")
    @Email(message = "Correo invalido")
    private String email;

    /*@NotBlank(message = "El campo \"Materia\" no puede estar vacío!")
    @Size(min=4, max=100, message = "La materia debe estar entre 4 y 100 caracteres")
    private String subject;*/

    public Teacher() {}

    public Teacher(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }*/

    public String getFullName(){
        return name + " " + lastname;
    }
}
