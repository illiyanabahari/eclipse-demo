<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dao.GameDAO" %>
<%@ page import="com.model.Game" %>
<%@ include file="navbar.jsp" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    Game g = new GameDAO().getGameById(id);
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= g.getName() %> - Details</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container card" style="max-width:700px;">
    <h2 class="game-title"><%= g.getName() %></h2>
    <p><strong>Publisher:</strong> <%= g.getPublisher() %></p>
    <p><strong>Category:</strong> <%= g.getCategory() %></p>
    <p><strong>Platform:</strong> <%= g.getPlatform() %></p>
    <p><strong>Description:</strong> <%= g.getDescription() %></p>
</div>
</body>
</html>
