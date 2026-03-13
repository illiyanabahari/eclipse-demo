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
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
    <h2 style="color:#ff2edf;">Admin Dashboard</h2>
    <a href="<%= request.getContextPath() %>/addGame.jsp" class="btn">Add New Game</a>
    <div class="game-grid" style="margin-top:20px;">
        <% for(Game g : games) { %>
        <div class="card">
            <!-- Display game photo -->
           <img src="<%= request.getContextPath() %>/gameImage?id=<%= g.getGameId() %>" 
     alt="Game Photo" 
     style="max-width:150px; max-height:150px; object-fit:contain; border-radius:8px;" />
            
            <h3 class="game-title"><%= g.getName() %></h3>
            <p><strong>Publisher:</strong> <%= g.getPublisher() %></p>
            <p><strong>Category:</strong> <%= g.getCategory() %></p>
            <p><strong>Platform:</strong> <%= g.getPlatform() %></p>
            <a href="<%= request.getContextPath() %>/admin?action=edit&id=<%= g.getGameId() %>" class="btn">Edit</a>
            <a href="<%= request.getContextPath() %>/admin?action=delete&id=<%= g.getGameId() %>" class="btn" style="background:#ff2edf;">Delete</a>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>
