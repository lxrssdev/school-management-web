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

import java.util.Optional;

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


    @GetMapping("/create-teacher")
    public String showCreateForm(Model model) {
        model.addAttribute("title", "Agregar Maestro");
        model.addAttribute("teacher", new Teacher()); //empty teacher for thymeleaf form
        return "formTeacher";
    }

    @GetMapping("/form-create")
    public String formCreate(Model model, RedirectAttributes redirect) {
        model.addAttribute("title", "Formulario | Creación de maestros!");
        model.addAttribute("teacher", new Teacher());
        redirect.addFlashAttribute("success", "Maestro agregado correctamente al sistema");
        return "formTeacher";
    }

    @PostMapping("/teachers")
    public String processFormCreate(@Valid Teacher teacher, BindingResult result, Model model, RedirectAttributes redirect, SessionStatus status) {
        if(result.hasErrors()) {
            model.addAttribute("title", "Formulario en validación | Completa los campos");
            return "formTeacher";
        }
        String message = (teacher.getId() != null && teacher.getId() > 0)
                ? "Maestro "+ teacher.getFullName() + " actualizado correctamente"
                : "Maestro " + teacher.getFullName() + " creado correctamente";
        teacherService.save(teacher);
        status.setComplete();
        redirect.addFlashAttribute("success", message);
        return "redirect:/dashboard/teachers";
    }

    @GetMapping("/edit-teacher/{id}")
    public String showEditForm(@PathVariable Long id,Model model, RedirectAttributes redirect) {
        Optional<Teacher> teacher = teacherService.findById(id);
        if(teacher.isPresent()) {
            model.addAttribute("title", "Formulario | Editar Maestro");
            model.addAttribute("teacher", teacher.get());
            redirect.addFlashAttribute("success", "Maestro " + teacher.get().getFullName() + " editado correctamente");
            return "formTeacher";
        }else{
            return "redirect:/dashboard/teachers";
        }
    }
    @GetMapping("/delete-teacher/{id}")
    public String deleteTeacher(@PathVariable Long id,RedirectAttributes redirect) {
        Optional<Teacher> teacher = teacherService.findById(id);
        if(teacher.isPresent()) {
            teacherService.deleteById(id);
            redirect.addFlashAttribute("success", "Maestro eliminado correctamente del sistema");
        }else{
            redirect.addFlashAttribute("error", "Maestro no encontrado en el sistema");
        }
        return "redirect:/dashboard/teachers";
    }

}
