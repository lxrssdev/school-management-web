package com.elara.spring.controllers;

import com.elara.spring.entities.Subject;
import com.elara.spring.services.SubjectService;
import com.elara.spring.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
@SessionAttributes({"subject"})
public class SubjectController {

    private final SubjectService subjectService;
    private final TeacherService teacherService;

    public SubjectController(SubjectService subjectService, TeacherService teacherService) {
        this.subjectService = subjectService;
        this.teacherService = teacherService;
    }

    @GetMapping("/subjects")
    public String listSubjects(Model model) {
        model.addAttribute("title", "Listado de materias");
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjects";
    }

    @GetMapping("/create-subject")
    public String createSubject(Model model, RedirectAttributes redirect) {
        model.addAttribute("title", "Agregar materia");
        model.addAttribute("subject", new Subject());
        model.addAttribute("teachers", teacherService.listTeachers());
        return "formSubject";
    }



    @GetMapping("/edit-subject/{id}")
    public String editSubject(@PathVariable int id, Model model, RedirectAttributes redirect) {
        Optional<Subject> subject = subjectService.findById(id);
        if(subject.isPresent()) {
            model.addAttribute("teachers", teacherService.listTeachers());
            model.addAttribute("subject", subject.get());
            model.addAttribute("title", "Formulario | Editar materia");
            redirect.addFlashAttribute("success", "Materia editada con éxito!");
            return "formSubject";
        }else{
            return "redirect:/dashboard/subjects/";
        }
    }

    @GetMapping("/delete-subject/{id}")
    public String deleteSubject(@PathVariable int id, RedirectAttributes redirect) {
        Optional<Subject> subject = subjectService.findById(id);
        if(subject.isPresent()) {
            subjectService.delete(subject.get());
            redirect.addFlashAttribute("success", "Materia eliminada con éxito!");
        }else{
            redirect.addFlashAttribute("error", "No existe materia con ese id!");
        }
        return "redirect:/dashboard/subjects";
    }

    @PostMapping("/subjects")
    public String processForm(@Valid Subject subject, BindingResult bindingResult, Model model, RedirectAttributes redirect, SessionStatus status) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("title", "Formulario validado | Agregar materia");
            model.addAttribute("teachers", teacherService.listTeachers());
            return "formSubject";
        }
        String message = (subject.getId() != null && subject.getId() > 0)
                ? "La materia ha sido actualizada con éxito!"
                : "La materia ha sido creada con éxito!";
        subjectService.save(subject);
        status.setComplete();
        redirect.addFlashAttribute("success", message);
        return "redirect:/dashboard/subjects";
    }
}
