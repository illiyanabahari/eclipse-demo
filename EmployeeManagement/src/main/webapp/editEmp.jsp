<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.model.Emp"%>
<%@ page import="com.model.Status"%>
<%
    Emp emp = (Emp) request.getAttribute("emp");
    if(session == null || session.getAttribute("admin") == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Employee</title>
<style>
    body { font-family:'Segoe UI', sans-serif; background-color:#fdf6f0; }
    .container { width:400px; margin:50px auto; background-color:#fff6f6; padding:30px; border-radius:12px; box-shadow:0 4px 15px rgba(0,0,0,0.1);}
    h1 { text-align:center; color:#5a3e36;}
    input, select { width:100%; padding:10px; margin:8px 0; border-radius:6px; border:1px solid #f0dede; background-color:#fff0f0;}
    input[type=submit] { background-color:#ffd6d6; color:#5a3e36; font-weight:bold; border:none; cursor:pointer; transition:0.3s; }
    input[type=submit]:hover { background-color:#ffc4c4; }
    a { text-decoration:none; color:#5a3e36; padding:5px 10px; border-radius:6px; background-color:#ffd6d6; transition:0.3s; }
    a:hover { background-color:#ffc4c4; }
    img { border-radius:50%; border:2px solid #ffd6d6; margin-top:5px; }
    .btn-back {
    display: inline-block;
    padding: 10px 20px;
    background-color: #ffd6d6; /* pastel pink */
    color: #5a3e36; /* dark brown text */
    font-weight: bold;
    border-radius: 12px;
    text-decoration: none;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    transition: all 0.3s ease;
    margin-top: 15px;
}

.btn-back:hover {
    background-color: #ffc4c4; /* slightly darker on hover */
    transform: translateY(-2px);
    box-shadow: 0 6px 12px rgba(0,0,0,0.15);
}
    
</style>
</head>
<body>

<div class="container">
<h1>Edit Employee</h1>
 <a href="logout">Logout</a>

<form action="updateEmp" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="<%= emp.getId() %>">

    Name: <input type="text" name="name" value="<%= emp.getName() %>" required>
    Job: <input type="text" name="job" value="<%= emp.getJob() %>" required>
    Salary: <input type="number" step="0.01" name="salary" value="<%= emp.getSalary() %>" required>
    Department: <input type="text" name="dept" value="<%= emp.getDept() %>" required>
    Hire Date: <input type="date" name="hireDate" value="<%= emp.getHireDate() %>" required>

    Status:
    <select name="status">
        <option value="ACTIVE" <%= emp.getStatus() == Status.ACTIVE ? "selected" : "" %>>Active</option>
        <option value="RESIGNED" <%= emp.getStatus() == Status.RESIGNED ? "selected" : "" %>>Resigned</option>
    </select>

    Photo: <input type="file" name="photo">
    <% if(emp.getPhoto() != null) { %>
        Current Photo: 
        <img src="data:image/jpeg;base64,<%= java.util.Base64.getEncoder().encodeToString(emp.getPhoto()) %>" width="100" height="100"/>
    <% } %>

    <input type="submit" value="Update Employee">
</form>

<a href="viewEmps" class="btn-back">Back</a>

</div>

</body>
</html>
