package com.demo.web;

import java.io.IOException;

import com.demo.dao.UserDao;
import com.demo.models.AppUser;
import com.demo.security.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {

        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        username = (username == null) ? "" : username.trim();
        password = (password == null) ? "" : password;

        AppUser user = userDao.findByUsername(username);

        boolean ok = (user != null) && PasswordUtil.verifyPassword(password.toCharArray(), user.getPasswordHash());

        if (!ok) {
            request.setAttribute("error", "Invalid username or password.");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        }

        // login success
        HttpSession session = request.getSession(true);

        // store a safe user object in session (we can blank passwordHash to be safe)
        user.setPasswordHash(null);
        session.setAttribute("authUser", user);

        response.sendRedirect(request.getContextPath() + "/customers");
    }
}
