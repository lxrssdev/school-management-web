package com.elara.spring.controllers;

import com.elara.spring.entities.Teacher;
import com.elara.spring.services.TeacherService;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dashboard")
@SessionAttributes({"teacher"})
public class TeacherController {

    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public String showTeachers(Model model, ServletRequest servletRequest){
        //list all teachers
        model.addAttribute("title", "Lista de maestros");
        model.addAttribute("teachers", teacherService.listTeachers());
        return "teachers";
    }

    @GetMapping("/form-create")
    public String formCreate(Model model){
        model.addAttribute("title", "Formulario | Creación de maestros!");
        model.addAttribute("teacher", new Teacher());
        return "form";
    }

    @PostMapping("/dashboard/teachers")
    public String processFormCreate(@Valid Teacher teacher, BindingResult result, Model model, RedirectAttributes redirect, SessionStatus status) {
        if(result.hasErrors()) {
            model.addAttribute("title", "Formulario en validación | Completa los campos");
            return "form";
        }
        String message = (teacher.getId() != null && teacher.getId() > 0)
                ? "Maestro "+ teacher.getFullName() + " actualizado correctamente"
                : "Maestro " + teacher.getFullName() + " creado correctamente";
        teacherService.save(teacher);
        status.setComplete();
        redirect.addFlashAttribute("success", message);
        return "redirect:/dashboard/teachers";
    }


}
