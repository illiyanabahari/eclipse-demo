<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dao.GameDAO" %>
<%@ page import="com.model.Game" %>
<%@ include file="navbar.jsp" %>
<%
    GameDAO dao = new GameDAO();
    List<Game> games = dao.getAllGames();
%>
<!DOCTYPE html>
<html>
<head>
    <title>All Games</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
    <h2 style="color:#ff2edf;">All Games</h2>
    <div class="game-grid">
        <% for(Game g : games){ %>
        <div class="card">
            <h3 class="game-title"><%= g.getName() %></h3>
            <p><strong>Category:</strong> <%= g.getCategory() %></p>
            <p><strong>Platform:</strong> <%= g.getPlatform() %></p>
            <a href="<%= request.getContextPath() %>/gameDetails.jsp?id=<%= g.getGameId() %>" class="btn">View Details</a>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>
