package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EmpDAO;
import com.model.Emp;

/**
 * Servlet implementation class EditEmpServlet
 */
@WebServlet("/editEmp")
public class EditEmpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        EmpDAO dao = new EmpDAO();
        Emp emp = dao.getEmpById(id); // we’ll add this method in DAO

        request.setAttribute("emp", emp);
        request.getRequestDispatcher("editEmp.jsp").forward(request, response);
    }
}
