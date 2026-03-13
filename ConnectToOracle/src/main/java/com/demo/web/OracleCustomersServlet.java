package com.demo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.demo.dao.CustomerDao;
import com.demo.dao.CustomerDao.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/oracle-customers")
public class OracleCustomersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final CustomerDao dao = new CustomerDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");

        List<Customer> customers = dao.findAll();

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='UTF-8'><title>Oracle Customers</title></head><body>");
            out.println("<h2>Customers from Oracle XE (Docker)</h2>");

            out.println("<table border='1' cellpadding='6' cellspacing='0'>");
            out.println("<tr><th>ID</th><th>First</th><th>Last</th><th>Email</th></tr>");

            for (Customer c : customers) {
                out.println("<tr>");
                out.println("<td>" + c.getCustomerId() + "</td>");
                out.println("<td>" + c.getFirstName() + "</td>");
                out.println("<td>" + c.getLastName() + "</td>");
                out.println("<td>" + c.getEmail() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");
        }
    }
}
