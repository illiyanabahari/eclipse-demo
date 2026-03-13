<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
</head>
<body>

<h2>Login</h2>

<%-- error message --%>
<div style="color:red;">
  ${error}
</div>

<form action="login" method="post">
  <div>
    <label>Username:</label>
    <input type="text" name="username" value="${username}" required />
  </div>
  <br/>

  <div>
    <label>Password:</label>
    <input type="password" name="password" required />
  </div>
  <br/>

  <button type="submit">Login</button>
</form>

<hr/>
<p>Sample accounts (after you insert users):</p>
<ul>
  <li>admin / admin123</li>
  <li>trainer / trainer123</li>
  <li>user / user123</li>
</ul>

</body>
</html>

