package com.abc.controller;

import com.abc.entities.Customer;
import com.abc.entities.Employee;
import com.abc.entities.Ticket;
import com.abc.entities.TicketStatus;
import com.abc.repositories.CustomerRepository;
import com.abc.repositories.EmployeeRepository;
import com.abc.repositories.TicketRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    public TicketController(TicketRepository ticketRepository,
                            CustomerRepository customerRepository,
                            EmployeeRepository employeeRepository) {
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        model.addAttribute("content", "/WEB-INF/views/tickets/index.jsp");
        return "layouts/main";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("statuses", TicketStatus.values());
        model.addAttribute("content", "/WEB-INF/views/tickets/form.jsp");
        return "layouts/main";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Ticket ticket,
                       @RequestParam(name = "customerId", required = false) Integer customerId,
                       @RequestParam(name = "employeeId", required = false) Integer employeeId) {

        if (customerId != null) {
            ticket.setCustomer(customerRepository.findById(customerId).orElseThrow());
        }
        if (employeeId != null) {
            ticket.setEmployee(employeeRepository.findById(employeeId).orElseThrow());
        }

        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer ticketId, Model model) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        model.addAttribute("ticket", ticket);
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("statuses", TicketStatus.values());
        model.addAttribute("content", "/WEB-INF/views/tickets/form.jsp");
        return "layouts/main";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer ticketId) {
        ticketRepository.deleteById(ticketId);
        return "redirect:/tickets";
    }
}