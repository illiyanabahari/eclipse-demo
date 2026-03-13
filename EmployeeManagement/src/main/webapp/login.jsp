<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fdf6f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            background-color: #fff6f6;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            width: 350px;
        }
        h1 { text-align: center; color: #5a3e36; }
        input[type=text], input[type=password] {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border-radius: 8px;
            border: 1px solid #f0dede;
            background-color: #fff0f0;
        }
        input[type=submit] {
            width: 100%;
            padding: 10px;
            background-color: #ffd6d6;
            border: none;
            border-radius: 8px;
            color: #5a3e36;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
        }
        input[type=submit]:hover { background-color: #ffc4c4; }
        .error { color: red; text-align: center; margin-bottom:10px; }
        label { font-weight: bold; color: #5a3e36; }
    </style>
</head>
<body>

<div class="login-container">
    <h1>Admin Login</h1>

    <% 
        String error = request.getParameter("error");
        if(error != null) { 
    %>
        <p class="error"><%= error %></p>
    <% } %>

    <form action="login" method="post">
        <label>Username:</label>
        <input type="text" name="username" placeholder="Enter username" required>

        <label>Password:</label>
        <input type="password" name="password" placeholder="Enter password" required>

        <input type="submit" value="Login">
    </form>
</div>

</body>
</html>
