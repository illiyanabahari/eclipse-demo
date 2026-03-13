<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Image Upload</title>
</head>
<body>

<h2>Upload Image</h2>

<form action="upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" required>
    <br><br>
    <input type="submit" value="Upload">
</form>

<%
    // Display the uploaded image if 'img' parameter exists
    String fileName = request.getParameter("img");
    if (fileName != null) {
%>
    <h3>Uploaded Image:</h3>
    <img src="upload?show=<%= fileName %>" width="300">
<%
    }
%>

</body>
</html>
