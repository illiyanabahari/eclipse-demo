package com.abc.controller;

import com.abc.entities.Customer;
import com.abc.entities.UserAccount;
import com.abc.entities.UserRole;
import com.abc.repositories.CustomerRepository;
import com.abc.repositories.UserAccountRepository;
import com.abc.security.PasswordUtil; // your password hashing utility
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final UserAccountRepository userAccountRepository;

    // Constructor injection for both repositories
    public CustomerController(CustomerRepository customerRepository,
                              UserAccountRepository userAccountRepository) {
        this.customerRepository = customerRepository;
        this.userAccountRepository = userAccountRepository;
    }

    // =========================
    // LIST CUSTOMERS
    // =========================
    @GetMapping
    public String index(@RequestParam(name = "q", required = false) String q,
                        Model model) {

        if (q != null && !q.trim().isEmpty()) {
            String keyword = q.trim();
            model.addAttribute("customers", customerRepository.findByNameContainingIgnoreCase(keyword));
            model.addAttribute("q", keyword);
        } else {
            model.addAttribute("customers", customerRepository.findAll());
            model.addAttribute("q", "");
        }

        model.addAttribute("content", "/WEB-INF/views/customers/index.jsp");
        return "layouts/main";
    }

    // =========================
    // CREATE CUSTOMER FORM
    // =========================
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("content", "/WEB-INF/views/customers/form.jsp");
        return "layouts/main";
    }

    // =========================
    // SAVE CUSTOMER (CREATE + UPDATE)
    // =========================
    @PostMapping("/save")
    public String save(@ModelAttribute Customer customer) {

        boolean isNew = (customer.getCustomerId() == null);

        // Save customer first to get generated ID
        Customer saved = customerRepository.save(customer);

        if (isNew) {
            String username = saved.getEmail().trim().toLowerCase();

            // Only create a UserAccount if it doesn't exist
            if (!userAccountRepository.existsByUsername(username)) {
                UserAccount ua = new UserAccount();
                ua.setUsername(username);
                ua.setPasswordHash(PasswordUtil.hash("ChangeMe@123")); // temporary password
                ua.setRole(UserRole.CUSTOMER);
                ua.setCustomer(saved);
                ua.setEmployee(null);

                userAccountRepository.save(ua);
            }
        }

        return "redirect:/customers";
    }

    // =========================
    // EDIT CUSTOMER
    // =========================
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        model.addAttribute("customer", customer);
        model.addAttribute("content", "/WEB-INF/views/customers/form.jsp");
        return "layouts/main";
    }

    // =========================
    // DELETE CUSTOMER
    // =========================
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }
}