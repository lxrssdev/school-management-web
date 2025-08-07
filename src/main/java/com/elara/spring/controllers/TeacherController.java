package com.elara.spring.controllers;

import com.elara.spring.services.TeacherService;
import jakarta.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/dashboard")
@SessionAttributes({"teacher"})
public class TeacherController {

    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public String showDashboard(Model model) {
        //show dashboard
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }

    @GetMapping("/teachers")
    public String showTeachers(Model model, ServletRequest servletRequest){
        //list all teachers
        model.addAttribute("title", "Lista de maestros");
        model.addAttribute("teachers", teacherService.listTeachers());
        return "teachers";
    }


}
