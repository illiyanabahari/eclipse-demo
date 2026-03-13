<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
        rel="stylesheet">
</head>
<body class="bg-light">

<div class="container vh-100 d-flex justify-content-center align-items-center">

  <div class="col-md-5">
    <div class="card shadow">
      <div class="card-body p-4">

        <h2 class="text-center mb-4">Login</h2>

        <!-- Error Message -->
        <c:if test="${not empty error}">
          <div class="alert alert-danger">
            ${error}
          </div>
        </c:if>

        <form action="login" method="post">

          <div class="mb-3">
            <label class="form-label">Username</label>
            <input type="text" 
                   name="username" 
                   value="${username}" 
                   class="form-control" 
                   required>
          </div>

          <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" 
                   name="password" 
                   class="form-control" 
                   required>
          </div>

          <div class="d-grid">
            <button type="submit" class="btn btn-primary">
              Login
            </button>
          </div>

        </form>

        <hr>

        <div class="small text-muted">
          <p class="mb-1"><strong>Sample accounts:</strong></p>
          <ul class="mb-0">
            <li>admin / admin123</li>
            <li>trainer / trainer123</li>
            <li>user / user123</li>
          </ul>
        </div>

      </div>
    </div>
  </div>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>