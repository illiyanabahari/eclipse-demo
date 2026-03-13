
<link rel="stylesheet" type="text/css" href="style.css">

<%@ page import="java.util.*,com.dao.*,com.library.*" import="com.model.*"%>
<%
    BookDAO dao = new BookDAO();
    List<Book> books = dao.getAllBooks();
%>
<h2>Library Books</h2>
<table border="1">
    <tr>
        <th>ID</th><th>Title</th><th>Author</th><th>Publisher</th><th>Quantity</th><th>Action</th>
    </tr>
    <%
        for(Book b : books){
    %>
    <tr>
        <td><%= b.getId() %></td>
        <td><%= b.getTitle() %></td>
        <td><%= b.getAuthor() %></td>
        <td><%= b.getPublisher() %></td>
        <td><%= b.getQuantity() %></td>
        <td>
            <a href="EditBookServlet?id=<%=b.getId()%>">Edit</a>

            <a href="DeleteBookServlet?id=<%=b.getId()%>" onclick="return confirm('Are you sure?')">Delete</a>
        </td>
    </tr>
    <% } %>
    <a href="addBook.jsp">
    <button type="button">Add Books</button>
</a>
</table>
