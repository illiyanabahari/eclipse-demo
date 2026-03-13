package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

@WebServlet("/addEmp")
@MultipartConfig
public class AddEmpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read form data
        String name = request.getParameter("name");
        String job = request.getParameter("job");
        String dept = request.getParameter("dept");
        double salary = Double.parseDouble(request.getParameter("salary"));
        LocalDate hireDate = LocalDate.parse(request.getParameter("hireDate"),
                DateTimeFormatter.ISO_LOCAL_DATE);

        // Status
        String statusStr = request.getParameter("status");
        Status status = Status.valueOf(statusStr.toUpperCase());

        // Photo
        Part photoPart = request.getPart("photo");
        byte[] photo = null;

        if (photoPart != null && photoPart.getSize() > 0) {
            try (InputStream is = photoPart.getInputStream()) {
                photo = is.readAllBytes();
            }
        }

        // Create Emp object
        Emp emp = new Emp();
        emp.setName(name);
        emp.setJob(job);
        emp.setDept(dept);
        emp.setSalary(salary);
        emp.setHireDate(hireDate);
        emp.setStatus(status);
        emp.setPhoto(photo);

        // DAO call
        EmpDAO dao = new EmpDAO();
        boolean result = dao.addEmp(emp);

        // Redirect
        if (result) {
            response.sendRedirect("addEmp.jsp?msg=Employee added successfully");
        } else {
            response.sendRedirect("addEmp.jsp?msg=Error adding employee");
        }
    }
}
