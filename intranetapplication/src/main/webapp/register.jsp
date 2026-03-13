<%@ page import="java.io.*" %>
<%
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // Store data in session (for demo purpose)
    session.setAttribute("username", username);
    session.setAttribute("password", password);
    session.setAttribute("name", name);
    session.setAttribute("email", email);

    response.sendRedirect("login.html");
%>

