<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Result</title>
<link rel="stylesheet" type="text/css" href="appstyle.css">
</head>
<body>

<%
    // Get form parameters
    String name = request.getParameter("name");
    String age = request.getParameter("age");
    String email = request.getParameter("email");

    if(name != null && age != null && email != null 
       && !name.isEmpty() && !age.isEmpty() && !email.isEmpty()) {

%>
        <h1>Registration Successful!</h1>
        <p><strong>Name:</strong> <%= name %></p>
        <p><strong>Age:</strong> <%= age %></p>
        <p><strong>Email:</strong> <%= email %></p>
<%
    } else {
%>
        <h1>Registration Error</h1>
        <p>Please fill out all fields.</p>
<%
    }
%>

</body>
</html>
