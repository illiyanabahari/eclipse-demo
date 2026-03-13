package com.demo.web;

import java.io.IOException;
import java.io.PrintWriter;

import com.demo.dao.UserDao;
import com.demo.models.AppUser;
import com.demo.security.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/init-user")
public class InitUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Creates: admin / Password@123  (permission ADMIN)
        // and: user / Password@123 (permission USER)

        AppUser admin = new AppUser();
        admin.setUsername("admin");
        admin.setPasswordHash(PasswordUtil.hashPassword("Password@123".toCharArray()));
        admin.setFirstName("System");
        admin.setLastName("Admin");
        admin.setPermission("ADMIN");

        AppUser user = new AppUser();
        user.setUsername("user");
        user.setPasswordHash(PasswordUtil.hashPassword("Password@123".toCharArray()));
        user.setFirstName("Normal");
        user.setLastName("User");
        user.setPermission("USER");

        // insert only if not exists
        if (userDao.findByUsername("admin") == null) userDao.createUser(admin);
        if (userDao.findByUsername("user") == null) userDao.createUser(user);

        response.setContentType("text/plain");
        try (PrintWriter out = response.getWriter()) {
            out.println("✅ Users created (if they didn't exist already).");
            out.println("admin / Password@123 (ADMIN)");
            out.println("user  / Password@123 (USER)");
            out.println("Now go to /login");
            out.println("IMPORTANT: Delete/disable this servlet after setup in real apps.");
        }
    }
}

