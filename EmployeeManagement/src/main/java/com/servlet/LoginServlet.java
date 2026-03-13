package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String ADMIN_USER = "admin";
    private final String ADMIN_PASS = "admin123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(ADMIN_USER.equals(username) && ADMIN_PASS.equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("admin", username);
            response.sendRedirect("viewEmps");
        } else {
            response.sendRedirect("login.jsp?error=Invalid+username+or+password");
        }
    }
}