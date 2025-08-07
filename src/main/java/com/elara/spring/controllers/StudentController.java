package com.elara.spring.controllers;

import com.elara.spring.entities.Student;
import com.elara.spring.services.StudentService;
import com.elara.spring.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller()
@RequestMapping("/dashboard")
@SessionAttributes({"student"})
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService service, TeacherService teacherService) {
        this.studentService = service;
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
        model.addAttribute("title", "Lista de estudiantes");
        model.addAttribute("students", studentService.listStudents());
        return "students";
    }

    @GetMapping("/create-student")
    public String showCreateForm(Model model) {
        model.addAttribute("title", "Agregar Estudiante");
        model.addAttribute("student", new Student()); //empty object for thymeleaf form
        return "form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if(student.isPresent()) {
            model.addAttribute("student", student.get());
            model.addAttribute("title", "Editar estudiante");
            return "form";
        }else{
            return "redirect:/dashboard/students";

        }
    }

    @PostMapping
    public String processForm(@Valid Student student, BindingResult result, Model model, RedirectAttributes redirect, SessionStatus status) {
        if(result.hasErrors()) {
            model.addAttribute("title", "Crear estudiante");
            return "form";
        }
        String message = (student.getId() > 0)
                ? "Estudiante " + student.getFullName() + "actualizado con éxito!"
                : "Estudiante " + student.getFullName() + "creado con éxito!";
        studentService.saveStudent(student);
        status.setComplete();
        redirect.addFlashAttribute("success", message);
        return "redirect:/dashboard/students";

    }

    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Student> student = studentService.getStudentById(id);
        if(student.isPresent()) {
            studentService.deleteStudentById(id);
            String message = "Estudiante " + student.get().getFullName() + " eliminado con éxito";
            redirect.addFlashAttribute("success", message);
        }else{
            redirect.addFlashAttribute("error", "Estudiante con id " + id + " no encontrado!");
        }
        return "redirect:/students";
    }


}
