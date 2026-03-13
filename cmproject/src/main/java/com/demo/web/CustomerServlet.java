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
       String q = request.getParameter("q"); // global search query
       int page = parseIntOrDefault(request.getParameter("page"), 1);
       int size = parseIntOrDefault(request.getParameter("size"), 10);
       // sanitize size
       if (size < 1) size = 10;
       if (size > 100) size = 100;
       // count based on q
       int totalRows = customerDao.countAll(q);
       int totalPages = (int) Math.ceil(totalRows / (double) size);
       if (totalPages < 1) totalPages = 1;
       // sanitize page
       if (page < 1) page = 1;
       if (page > totalPages) page = totalPages;
       List<Customer> customers = customerDao.findPage(q, page, size);
       request.setAttribute("customers", customers);
       request.setAttribute("page", page);
       request.setAttribute("size", size);
       request.setAttribute("totalRows", totalRows);
       request.setAttribute("totalPages", totalPages);
       // IMPORTANT: keep q so JSP can show it + keep it in links
       request.setAttribute("q", (q == null) ? "" : q.trim());
       request.getRequestDispatcher("/WEB-INF/views/customers.jsp").forward(request, response);
   }
   private int parseIntOrDefault(String value, int defaultValue) {
       try {
           if (value == null || value.trim().isEmpty()) return defaultValue;
           return Integer.parseInt(value.trim());
       } catch (Exception ex) {
           return defaultValue;
       }
   }
}
