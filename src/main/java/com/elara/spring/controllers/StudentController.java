package com.elara.spring.controllers;

import com.elara.spring.entities.Student;
import com.elara.spring.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/dashboard")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public String showDashboard(Model model) {
        //show dashboard
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }

    @GetMapping("/students")
    public String showStudents(Model model) {
        //list all students
        model.addAttribute("title", "Students");
        model.addAttribute("studens", service.listStudents());
        return "students";
    }

    @GetMapping("/create-form")
    public String showCreateForm(Model model) {
        model.addAttribute("title", "Create Student");
        model.addAttribute("student", new Student()); //empty object for thymeleaf
        return "form";
    }


}
