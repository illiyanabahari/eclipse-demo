package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.EmpDAO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.Emp;

/**
 * Servlet implementation class DownloadPdfServlet
 */
@WebServlet("/downloadPdf")
public class DownloadPdfServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Session check
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("admin") == null){
            response.sendRedirect("login.jsp");
            return;
        }

        // Get employee ID from request
        String idStr = request.getParameter("id");
        if(idStr == null || idStr.isEmpty()){
            response.getWriter().println("Employee ID is missing!");
            return;
        }

        int empId = Integer.parseInt(idStr);

        // Fetch employee from database
        EmpDAO dao = new EmpDAO();
        Emp emp = dao.getEmpById(empId); // you need to create this method in EmpDAO
        if(emp == null){
            response.getWriter().println("Employee not found!");
            return;
        }

        try {
            // Set response content type
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=employee_" + empId + ".pdf");

            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.DARK_GRAY);
            Paragraph title = new Paragraph("Employee Details", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Add employee info
            Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            document.add(new Paragraph("ID: " + emp.getId(), valueFont));
            document.add(new Paragraph("Name: " + emp.getName(), valueFont));
            document.add(new Paragraph("Job: " + emp.getJob(), valueFont));
            document.add(new Paragraph("Salary: " + emp.getSalary(), valueFont));
            document.add(new Paragraph("Department: " + emp.getDept(), valueFont));
            document.add(new Paragraph("Hire Date: " + emp.getHireDate(), valueFont));
            document.add(new Paragraph("Status: " + emp.getStatus(), valueFont));

            document.add(Chunk.NEWLINE);

            // Add photo if exists
            if(emp.getPhoto() != null){
                Image img = Image.getInstance(emp.getPhoto());
                img.scaleToFit(150,150);
                img.setAlignment(Element.ALIGN_CENTER);
                document.add(img);
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}