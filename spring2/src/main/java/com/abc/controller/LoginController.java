package com.abc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.abc.entity.AppUser;
import com.abc.service.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping({"/", "/login"})
    public String showLogin(HttpSession session) {
        // if already logged in, go dashboard
        if (session.getAttribute("LOGGED_USER") != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session,
                               Model model) {

        AppUser user = authService.authenticate(username, password);

        if (user == null) {
            model.addAttribute("error", "Invalid username or password");
            model.addAttribute("username", username);
            return "login";
        }

        // store minimal info in session
        session.setAttribute("LOGGED_USER", user);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        AppUser user = (AppUser) session.getAttribute("LOGGED_USER");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("firstname", user.getFirstname());
        model.addAttribute("lastname", user.getLastname());
        model.addAttribute("username", user.getUsername());
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
