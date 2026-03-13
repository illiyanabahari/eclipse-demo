package com.abc.controller;

import com.abc.entities.Employee;
import com.abc.entities.UserAccount;
import com.abc.entities.UserRole;
import com.abc.repositories.EmployeeRepository;
import com.abc.repositories.UserAccountRepository;
import com.abc.security.PasswordUtil; // your password hashing utility

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final UserAccountRepository userAccountRepository;

    // Constructor injection for both repositories
    public EmployeeController(EmployeeRepository employeeRepository,
                              UserAccountRepository userAccountRepository) {
        this.employeeRepository = employeeRepository;
        this.userAccountRepository = userAccountRepository;
    }

    // =========================
    // LIST EMPLOYEES
    // =========================
    @GetMapping
    public String index(@RequestParam(name = "q", required = false) String q, Model model) {

        if (q != null && !q.trim().isEmpty()) {
            String keyword = q.trim();
            model.addAttribute("employees", employeeRepository.findByNameContainingIgnoreCase(keyword));
            model.addAttribute("q", keyword);
        } else {
            model.addAttribute("employees", employeeRepository.findAll());
            model.addAttribute("q", "");
        }

        model.addAttribute("content", "/WEB-INF/views/employees/index.jsp");
        return "layouts/main";
    }

    // =========================
    // CREATE FORM
    // =========================
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("roles", java.util.List.of("Support Agent", "Technician", "Manager"));
        model.addAttribute("content", "/WEB-INF/views/employees/form.jsp");
        return "layouts/main";
    }

    // =========================
    // SAVE EMPLOYEE (CREATE + UPDATE)
    // =========================
    @PostMapping("/save")
    public String save(@ModelAttribute Employee employee) {

        boolean isNew = (employee.getEmployeeId() == null);

        // Save employee first to get generated ID
        Employee saved = employeeRepository.save(employee);

        if (isNew) {
            String username = saved.getEmail().trim().toLowerCase();

            // Only create a UserAccount if it doesn't exist
            if (!userAccountRepository.existsByUsername(username)) {
                UserAccount ua = new UserAccount();
                ua.setUsername(username);
                ua.setPasswordHash(PasswordUtil.hash("ChangeMe@123")); // temporary password
                ua.setRole(UserRole.EMPLOYEE);
                ua.setEmployee(saved);
                ua.setCustomer(null);

                userAccountRepository.save(ua);
            }
        }

        return "redirect:/employees";
    }

    // =========================
    // EDIT EMPLOYEE
    // =========================
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        model.addAttribute("employee", employee);
        model.addAttribute("roles", java.util.List.of("Support Agent", "Technician", "Manager"));
        model.addAttribute("content", "/WEB-INF/views/employees/form.jsp");
        return "layouts/main";
    }

    // =========================
    // DELETE EMPLOYEE
    // =========================
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }

}