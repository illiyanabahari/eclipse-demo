<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add User</title>
</head>
<body>
 <form action="addUser" method="post">
 Name:
 <input type="text" name="name" required>
 
 Email:
 <input type="text" name="email" required>
 
 Password:
 <input type="text" name="password" required>
 
 Confirm Password:
 <input type="text" name="confirmpass" required>
 
 <input type="submit" value="Add User">
 </form>
 
 <a href="login">Login</a>
</body>
</html>