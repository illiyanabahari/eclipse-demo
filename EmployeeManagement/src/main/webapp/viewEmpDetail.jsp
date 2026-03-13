<%@ page import="com.dao.EmpDAO, com.model.Emp" %>
<%@ page import="java.util.Base64" %>
<%
    String idStr = request.getParameter("id");
    Emp emp = null;
    if (idStr != null) {
        try {
            int id = Integer.parseInt(idStr);
            EmpDAO dao = new EmpDAO();
            emp = dao.getEmpById(id); // Make sure this method exists in EmpDAO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    if (emp == null) {
%>
        <p style="color:red; font-weight:bold;">Employee not found.</p>
        <a href="viewEmps" class="btn-pastel">Back to list</a>
<%
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Employee Details</title>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: #fcefed;  /* pastel peach */
        color: #5a3e36;
        padding: 40px;
        max-width: 600px;
        margin: auto;
        border-radius: 16px;
        box-shadow: 0 8px 24px rgba(122, 68, 53, 0.15);
    }
    h2 {
        text-align: center;
        color: #b56576; /* dusty rose */
        margin-bottom: 30px;
    }
    p {
        font-size: 18px;
        margin: 12px 0;
        border-bottom: 1px solid #f7d5d5;
        padding-bottom: 8px;
    }
    strong {
        color: #b56576;
    }
    img {
        display: block;
        margin: 20px auto;
        border-radius: 12px;
        box-shadow: 0 4px 12px rgba(181, 101, 118, 0.3);
        max-width: 150px;
        max-height: 150px;
        object-fit: cover;
    }
    .btn-pastel {
        display: inline-block;
        background-color: #f8d7da; /* pastel pink */
        color: #842029;
        padding: 10px 30px;
        font-weight: bold;
        border-radius: 12px;
        text-decoration: none;
        box-shadow: 0 5px 12px rgba(132, 32, 41, 0.3);
        transition: background-color 0.3s ease, transform 0.3s ease;
        margin-top: 25px;
        text-align: center;
    }
    .btn-pastel:hover {
        background-color: #e59aa0;
        transform: translateY(-3px);
        box-shadow: 0 8px 20px rgba(132, 32, 41, 0.5);
    }
</style>
</head>
<body>

<h2>Employee Details</h2>

<p><strong>ID:</strong> <%= emp.getId() %></p>
<p><strong>Name:</strong> <%= emp.getName() %></p>
<p><strong>Job:</strong> <%= emp.getJob() %></p>
<p><strong>Salary:</strong> $<%= String.format("%.2f", emp.getSalary()) %></p>
<p><strong>Department:</strong> <%= emp.getDept() %></p>
<p><strong>Hire Date:</strong> <%= emp.getHireDate() %></p>
<p><strong>Status:</strong> <%= emp.getStatus().name().toLowerCase() %></p>

<%
    if (emp.getPhoto() != null) {
        String base64 = Base64.getEncoder().encodeToString(emp.getPhoto());
%>
    <img src="data:image/jpeg;base64,<%= base64 %>" alt="Employee Photo" />
<% } else { %>
    <p><em>No photo available</em></p>
<% } %>

<a href="viewEmps" class="btn-pastel">Back to Employee List</a>

</body>
</html>
