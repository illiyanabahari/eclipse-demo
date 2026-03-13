<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Book</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Add New Book</h2>

    <% String msg = request.getParameter("msg"); 
       if (msg != null) { %>
        <p style="color:green;"><%= msg %></p>
    <% } %>

    <form action="addBook" method="post">
        Title: <input type="text" name="title" required><br><br>
        Author: <input type="text" name="author" required><br><br>
        Publisher: <input type="text" name="publisher"><br><br>
        Quantity: <input type="number" name="quantity" required><br><br>
        <input type="submit" value="Add Book">
    </form>
    <a href="viewBooks.jsp">
    <button type="button">View All Books</button>
</a>
</body>
</html>
