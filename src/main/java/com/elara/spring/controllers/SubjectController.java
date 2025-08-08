package com.elara.spring.controllers;

import com.elara.spring.services.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subjects")
    public String listSubjects(Model model) {
        model.addAttribute("title", "Listado de materias");
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjects";
    }
}
