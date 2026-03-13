<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>Add User</title>
</head>
<body>
<h2>Add User (JSP → Servlet → Firebase Firestore)</h2>
<form method="post" action="<%=request.getContextPath()%>/users/add">
   <div>
       <label>Email:</label>
       <input type="email" name="email" required />
   </div>
   <div style="margin-top:10px;">
       <label>Full Name:</label>
       <input type="text" name="fullname" required />
   </div>
   <div style="margin-top:10px;">
       <button type="submit">Save to Firebase</button>
   </div>
</form>
<%
   String msg = (String) request.getAttribute("msg");
   if (msg != null) {
%>
   <p style="margin-top:15px;"><b><%= msg %></b></p>
<%
   }
%>
</body>
</html>

