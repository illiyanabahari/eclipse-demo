<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Result</title>
</head>
<body>

<%
    // Get the form parameters
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // Simple hardcoded authentication (replace with DB logic in real apps)
    String validUser = "admin";
    String validPass = "password123";

    if(username != null && password != null) {
        if(username.equals(validUser) && password.equals(validPass)) {
%>
            <h1>Welcome, <%= username %>!</h1>
            <p>Login successful.</p>
<%
        } else {
%>
            <h1>Login Failed</h1>
            <p>Invalid username or password.</p>
<%
        }
    } else {
%>
    <h1>Error</h1>
    <p>Please enter username and password.</p>
<%
    }
%>

</body>
</html>
