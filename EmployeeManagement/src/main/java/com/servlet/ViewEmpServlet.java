package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.EmpDAO;
import com.model.Emp;

/**
 * Servlet implementation class ViewEmpServlet
 */
@WebServlet("/viewEmps")
public class ViewEmpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	  HttpSession session = request.getSession(false);
          if(session == null || session.getAttribute("admin")==null){
              response.sendRedirect("login.jsp");
              return;
          }
        EmpDAO dao = new EmpDAO();
        List<Emp> empList = dao.getAllEmps();

        request.setAttribute("empList", empList);
        request.getRequestDispatcher("viewEmps.jsp").forward(request, response);
    }
}
