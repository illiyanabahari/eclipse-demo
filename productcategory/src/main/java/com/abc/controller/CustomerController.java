package com.abc.controller;

import com.abc.entities.Category;
import com.abc.entities.Country;
import com.abc.entities.Customer;
import com.abc.entities.Product;
import com.abc.repository.CountryRepository;
import com.abc.repository.CustomerRepository;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository repo;
    private final CountryRepository countryRepo;

    public CustomerController(CustomerRepository repo, CountryRepository countryRepo) {
        this.repo = repo;
        this.countryRepo = countryRepo;
    }

    @GetMapping
    public String index(@RequestParam(name = "q", required = false) String q,
                        Model model) {

        if (q != null && !q.trim().isEmpty()) {
            model.addAttribute("customers", repo.findByFirstnameContainingIgnoreCase(q.trim()));
            model.addAttribute("q", q.trim());
        } else {
            model.addAttribute("customers", repo.findAll());
            model.addAttribute("q", "");
        }

        model.addAttribute("content", "/WEB-INF/views/customers/index.jsp");
        return "layouts/main";

    }
    
    @GetMapping("/new")
    public String createForm(Model model)
    {
        model.addAttribute("customer", new Customer());
        model.addAttribute("countries", countryRepo.findAll());
        model.addAttribute("content", "/WEB-INF/views/customers/form.jsp");
        return "layouts/main";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Customer customer,
                       @RequestParam("countryId") Integer countryId) {

        Country country = countryRepo.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        customer.setCountry(country);

        repo.save(customer);

        return "redirect:/customers";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("customer not found"));
        model.addAttribute("customer", customer);
        model.addAttribute("countries", countryRepo.findAll());
        model.addAttribute("content", "/WEB-INF/views/customers/form.jsp");
        return "layouts/main";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        repo.deleteById(id);
        return "redirect:/customers";
    }

}
