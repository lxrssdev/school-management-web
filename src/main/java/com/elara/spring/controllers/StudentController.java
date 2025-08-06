package com.elara.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/dashboard")
public class StudentController {
    //show dashboard
    @GetMapping
    public String showDashboard() {
        return "dashboard";
    }
}
