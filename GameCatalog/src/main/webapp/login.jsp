

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container card" style="max-width:400px;">
    <h2 style="text-align:center; color:#ff2edf;">Login</h2>
    <form action="<%= request.getContextPath() %>/login" method="post">
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>
        <button type="submit" class="btn">Login</button>
    </form>
</div>
</body>
</html>
