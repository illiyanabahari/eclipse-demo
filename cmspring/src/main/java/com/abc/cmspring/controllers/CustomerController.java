package com.abc.cmspring.controllers;

import com.abc.cmspring.repositories.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository repo;

    public CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    // /customers  OR  /customers?q=mini
    @GetMapping
    public String index(@RequestParam(name = "q", required = false) String q,
                        Model model) {

        if (q != null && !q.trim().isEmpty()) {
            model.addAttribute("customers", repo.findByCustomerNameContainingIgnoreCase(q.trim()));
            model.addAttribute("q", q.trim());
        } else {
            model.addAttribute("customers", repo.findAll());
            model.addAttribute("q", "");
        }

        model.addAttribute("content", "/WEB-INF/views/customers/index.jsp");
        return "layouts/main";

    }
}
