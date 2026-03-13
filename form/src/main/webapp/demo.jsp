<%@ page import="java.io.*" %>
<%
    String method = request.getMethod();       // GET or POST
    String name = request.getParameter("name"); // Get parameter 'name'

    out.println("<h2>Form submitted using: " + method + "</h2>");
    out.println("<h3>Name = " + name + "</h3>");

    // Show full query string for GET
    if ("GET".equalsIgnoreCase(method)) {
        out.println("<p>Query string (visible in URL): " + request.getQueryString() + "</p>");
    } else {
        out.println("<p>Data sent in request body (not visible in URL).</p>");
    }
%>
