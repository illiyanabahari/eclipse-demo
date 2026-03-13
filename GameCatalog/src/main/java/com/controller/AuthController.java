package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.model.User;

/**
 * Servlet implementation class AuthController
 */
@WebServlet("/login")
public class AuthController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email.equals("admin@gmail.com") && password.equals("admin123")) {
            User admin = new User();
            admin.setName("Admin");
            admin.setRole("admin");
            req.getSession().setAttribute("user", admin);
            res.sendRedirect("adminDashboard.jsp");
            return;
        }

        User user = new UserDAO().login(email, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            res.sendRedirect("games");
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}