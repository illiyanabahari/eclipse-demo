<%
    String user = request.getParameter("user");
    String name = (String) session.getAttribute("name");
    String email =(String) session.getAttribute("email");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
     <link rel="stylesheet" type="text/css" href="appstyle.css">
</head>
<body>
    <h2>Welcome, <%= name %>!</h2>
    <p>Your username is: <%= user %></p><br><br>
    <p>Your email:<%= email %></p>



</body>
</html>
