
<link rel="stylesheet" type="text/css" href="style.css">
<%@ page import="com.model.*" %>
<%
    Book book = (Book) request.getAttribute("book");
    if(book == null){
%>
<p>Book not found!</p>
<a href="viewBooks.jsp">Go back</a>
<%
    } else {
%>
<form action="EditBookServlet" method="post">
    <input type="hidden" name="id" value="<%=book.getId()%>"/>
    Title: <input type="text" name="title" value="<%=book.getTitle()%>"/><br/>
    Author: <input type="text" name="author" value="<%=book.getAuthor()%>"/><br/>
    Publisher: <input type="text" name="publisher" value="<%=book.getPublisher()%>"/><br/>
    Quantity: <input type="number" name="quantity" value="<%=book.getQuantity()%>"/><br/>
    <input type="submit" value="Update Book"/>
</form>
<% } %>


