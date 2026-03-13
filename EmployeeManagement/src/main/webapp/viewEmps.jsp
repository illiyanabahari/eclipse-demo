<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.model.Emp"%>
<%@ page import="com.model.Status"%>
<%@ page import="java.util.Base64"%>

<%
    if(session == null || session.getAttribute("admin") == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Employees</title>
<style>
    body { font-family: 'Segoe UI', sans-serif; background-color: #fdf6f0; margin:0; padding:0; color:#333;}
    h1 { text-align: center; color: #5a3e36; margin-top:20px; }
    p { text-align:right; margin-right:50px; }
    a { text-decoration: none; color: #5a3e36; font-weight: bold; padding:5px 10px; border-radius: 6px; background-color: #ffd6d6; transition:0.3s; }
    a:hover { background-color:#ffc4c4; }
    table { width:90%; margin:20px auto; border-collapse: collapse; border-radius: 10px; overflow:hidden; background-color:#fff6f6; box-shadow: 0 4px 12px rgba(0,0,0,0.1);}
    th, td { padding:12px 15px; text-align:center; }
    th { background-color:#ffbaba; color:#5a3e36; font-weight:bold; }
    tr:nth-child(even) { background-color:#ffe5e5; }
    tr:hover { background-color:#ffd6d6; transition:0.2s; }
    img { border-radius:50%; border:2px solid #ffd6d6; }
    button { background-color:#ffd6d6; color:#5a3e36; font-weight:bold; border:none; padding:8px 15px; border-radius:8px; cursor:pointer; transition:0.3s; }
    button:hover { background-color:#ffc4c4; }

    /* Actions links styling */
    .action-link {
        background-color: #ffe6e6;
        padding: 5px 10px;
        border-radius: 6px;
        margin: 2px;
        display: inline-block;
        color: #842029;
        font-weight: 600;
        transition: 0.3s;
    }
    .action-link:hover {
        background-color: #ffc4c4;
        color: #5a1b1b;
    }
</style>
</head>
<body>

<h1>All Employees</h1>

<p>
    Welcome, <%= session.getAttribute("admin") %>! 
    <a href="logout">Logout</a>
</p>

<table border="1" cellpadding="10">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Job</th>
        <th>Salary</th>
        <th>Department</th>
        <th>Hire Date</th>
        <th>Status</th>
        <th>Photo</th>
        <th>Actions</th>
    </tr>

<%
    List<Emp> empList = (List<Emp>) request.getAttribute("empList");
    if (empList != null) {
        for (Emp emp : empList) {
%>
    <tr>
        <td><%= emp.getId() %></td>
        <td><%= emp.getName() %></td>
        <td><%= emp.getJob() %></td>
        <td><%= emp.getSalary() %></td>
        <td><%= emp.getDept() %></td>
        <td><%= emp.getHireDate() %></td>
        <td><%= emp.getStatus().name().toLowerCase() %></td>
        <td>
            <%
                if (emp.getPhoto() != null) {
                    String base64 = Base64.getEncoder().encodeToString(emp.getPhoto());
            %>
                <img src="data:image/jpeg;base64,<%= base64 %>" width="100" height="100"/>
            <%
                } else {
            %>
                No Photo
            <%
                }
            %>
        </td>
        <td>
            <a href="viewEmpDetail.jsp?id=<%= emp.getId() %>" class="action-link">View</a>
            <a href="editEmp?id=<%= emp.getId() %>" class="action-link">Edit</a>
            <a href="deleteEmp?id=<%= emp.getId() %>" class="action-link" onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
            <a href="downloadPdf?id=<%= emp.getId() %>" class="action-link">Download PDF</a>
        </td>
    </tr>
<%
        }
    }
%>
</table>

<br>
<a href="addEmp.jsp"><button type="button">Add New Employee</button></a>

</body>
</html>
