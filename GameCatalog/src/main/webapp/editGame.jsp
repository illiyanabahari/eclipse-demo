<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.model.Game" %>
<%@ include file="navbar.jsp" %>
<%
    Game g = (Game) request.getAttribute("game");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Game</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container card" style="max-width:700px;">
    <h2 style="text-align:center; color:#ff2edf;">Edit Game</h2>
    <form action="<%= request.getContextPath() %>/admin" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="<%= g.getGameId() %>"/>
        <input type="text" name="name" placeholder="Game Name" value="<%= g.getName() %>" required />
        <input type="text" name="publisher" placeholder="Publisher" value="<%= g.getPublisher() %>" required />
        <input type="date" name="date" value="<%= g.getDate() %>" required />
        <input type="text" name="category" placeholder="Category" value="<%= g.getCategory() %>" required />
        <input type="text" name="platform" placeholder="Platform" value="<%= g.getPlatform() %>" required />
        <textarea name="description" rows="5" placeholder="Description" required><%= g.getDescription() %></textarea>
        <label for="photo" style="color:#00f5ff; font-weight:600;">Upload New Photo (optional)</label>
        <input type="file" name="photo" id="photo" accept="image/*" />
        <% if(g.getPhotos() != null && g.getPhotos().length > 0) { %>
    <img src="<%= request.getContextPath() %>/gameImage?id=<%= g.getGameId() %>" 
         style="max-width:150px; max-height:150px; object-fit:contain; margin-top:10px;" />
<% } %>

        <button type="submit" class="btn" style="margin-top:10px;">Update Game</button>
    </form>
</div>
</body>
</html>
