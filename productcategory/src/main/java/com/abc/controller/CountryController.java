package com.abc.controller;


import com.abc.entities.Country;

import com.abc.repository.CountryRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryRepository repo;

    public CountryController(CountryRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String index(@RequestParam(name = "q", required = false) String q,
                        Model model) {

        if (q != null && !q.trim().isEmpty()) {
            model.addAttribute("countries", repo.findByCountrynameContainingIgnoreCase(q.trim()));
            model.addAttribute("q", q.trim());
        } else {
            model.addAttribute("countries", repo.findAll());
            model.addAttribute("q", "");
        }

        model.addAttribute("content", "/WEB-INF/views/countries/index.jsp");
        return "layouts/main";

    }
    
    @GetMapping("/new")
    public String createForm(Model model)
    {
        model.addAttribute("country", new Country());
        model.addAttribute("content", "/WEB-INF/views/countries/form.jsp");
        return "layouts/main";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Country country)
    {
        repo.save(country);
        return "redirect:/countries";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
       Country country = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        model.addAttribute("country", country);
        model.addAttribute("content", "/WEB-INF/views/countries/form.jsp");
        return "layouts/main";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        repo.deleteById(id);
        return "redirect:/countries";
    }

}
