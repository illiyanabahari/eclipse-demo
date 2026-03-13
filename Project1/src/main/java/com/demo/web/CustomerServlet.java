package com.demo.web;

import com.demo.dao.CustomerDao;
import com.demo.models.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final CustomerDao customerDao = new CustomerDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Customer> customers = customerDao.findAll();
        request.setAttribute("customers", customers);

        request.getRequestDispatcher("/WEB-INF/views/customers.jsp").forward(request, response);
    }
}


