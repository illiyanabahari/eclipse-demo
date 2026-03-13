package com.abc.cmspring.controllers;

import com.abc.cmspring.repositories.EmployeeRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employees")
public class EmployeeController 
{

    private final EmployeeRepository repo;
    public EmployeeController(EmployeeRepository repo) 
    {
        this.repo = repo;
    }
    @GetMapping
    public String index(@RequestParam(name = "q", required = false) String q, Model model) {

        if (q != null && !q.trim().isEmpty()) {
            String keyword = q.trim();
            model.addAttribute("employees", repo.findByFirstNameContainingIgnoreCase(keyword));
            model.addAttribute("q", keyword);
        } else {
            model.addAttribute("employees", repo.findAll());
            model.addAttribute("q", "");
        }
        model.addAttribute("content", "/WEB-INF/views/employees/index.jsp");
        return "layouts/main";

    }
}
