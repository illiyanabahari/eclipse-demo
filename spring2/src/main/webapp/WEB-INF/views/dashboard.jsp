<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Dashboard</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
        rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">

  <div class="card shadow">
    <div class="card-body">
      <h2 class="card-title mb-4">Dashboard</h2>

      <p class="fs-5">
       Welcome <strong>${firstname} ${lastname}</strong>
        <span class="text-muted">(${username})</span>
      </p>

      <a href="logout" class="btn btn-danger mt-3">Logout</a>
    </div>
  </div>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>