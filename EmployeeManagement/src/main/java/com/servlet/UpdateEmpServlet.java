package com.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dao.EmpDAO;
import com.model.Emp;
import com.model.Status;

/**
 * Servlet implementation class UpdateEmpServlet
 */
@WebServlet("/updateEmp")
@MultipartConfig
public class UpdateEmpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String job = request.getParameter("job");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String dept = request.getParameter("dept");
        LocalDate hireDate = LocalDate.parse(request.getParameter("hireDate"));
        Status status = Status.valueOf(request.getParameter("status"));

        // Handle photo
        byte[] photo = null;
        Part filePart = request.getPart("photo");
        if (filePart != null && filePart.getSize() > 0) {
            photo = filePart.getInputStream().readAllBytes();
        }

        Emp emp = new Emp();
        emp.setId(id);
        emp.setName(name);
        emp.setJob(job);
        emp.setSalary(salary);
        emp.setDept(dept);
        emp.setHireDate(hireDate);
        emp.setStatus(status);
        if (photo != null) emp.setPhoto(photo); // only update if new photo uploaded

        EmpDAO dao = new EmpDAO();
        boolean result = dao.updateEmp(emp);

        if (result) {
            response.sendRedirect("viewEmps");
        } else {
            response.getWriter().println("Error updating employee");
        }
    }
}
