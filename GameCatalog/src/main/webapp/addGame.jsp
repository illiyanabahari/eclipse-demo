<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Game</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container card" style="max-width:700px;">
   <div class="container card" style="max-width:700px;">
    <h2 style="text-align:center; color:#ff2edf;">Add New Game</h2>
    <form action="<%= request.getContextPath() %>/admin" method="post" enctype="multipart/form-data">
        <input type="text" name="name" placeholder="Game Name" required />
        <input type="text" name="publisher" placeholder="Publisher" required />
        <input type="date" name="date" required />
        <input type="text" name="category" placeholder="Category" required />
        <input type="text" name="platform" placeholder="Platform" required />
        <textarea name="description" rows="5" placeholder="Description" required></textarea>
        <label for="photo" style="color:#00f5ff; font-weight:600;">Upload Game Photo</label>
        <input type="file" name="photo" id="photo" accept="image/*" required />
        <button type="submit" class="btn">Add Game</button>
    </form>
</div>
</body>
</html>
