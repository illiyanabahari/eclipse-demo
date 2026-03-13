<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.model.Emp"%>
<%@ page import="com.model.Status"%>
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
<title>Add Employee</title>
<style>
    body { font-family:'Segoe UI', sans-serif; background-color:#fdf6f0; }
    .container { width:400px; margin:50px auto; background-color:#fff6f6; padding:30px; border-radius:12px; box-shadow:0 4px 15px rgba(0,0,0,0.1);}
    h1 { text-align:center; color:#5a3e36;}
    input, select { width:100%; padding:10px; margin:8px 0; border-radius:6px; border:1px solid #f0dede; background-color:#fff0f0;}
    input[type=submit] { background-color:#ffd6d6; color:#5a3e36; font-weight:bold; border:none; cursor:pointer; transition:0.3s; }
    input[type=submit]:hover { background-color:#ffc4c4; }
    a { text-decoration:none; color:#5a3e36; padding:5px 10px; border-radius:6px; background-color:#ffd6d6; transition:0.3s; }
    a:hover { background-color:#ffc4c4; }
    p { color:green; font-weight:bold; text-align:center; }
    .btn-pastel {
    display: inline-block;
    padding: 10px 25px;
    background-color:#ffd6d6; /* pastel pink */
    color: #5a3e36; /* dark brown text */
    font-weight: bold;
    border-radius: 12px;
    text-decoration: none;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    transition: all 0.3s ease;
    margin-top: 15px;
}

.btn-pastel:hover {
    background-color: #b1e6b3; /* slightly darker pastel on hover */
    transform: translateY(-2px);
    box-shadow: 0 6px 12px rgba(0,0,0,0.15);
}
    
</style>
</head>
<body>

<div class="container">
<h1>Add New Employee</h1>

 <a href="logout">Logout</a>

<%
    String msg = request.getParameter("msg");
    if (msg != null) {
%>
    <p><%= msg %></p>
<%
    }
%>

<form action="addEmp" method="post" enctype="multipart/form-data">
    Name:
    <input type="text" name="name" required>

    Job:
    <input type="text" name="job" required>

    Salary:
    <input type="number" step="0.01" name="salary" required>

    Department:
    <input type="text" name="dept" required>

    Hire Date:
    <input type="date" name="hireDate" required>

    Photo:
    <input type="file" name="photo" accept="image/*" required>

    Status:
    <select name="status" required>
        <option value="active">Active</option>
        <option value="resigned">Resigned</option>
    </select>

    <input type="submit" value="Add Emp">
</form>

<br>
<a href="viewEmps" class="btn-pastel">View All Employees</a>

</div>

</body>
</html>
