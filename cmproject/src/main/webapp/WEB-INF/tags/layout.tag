<%@ tag description="Main Layout" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="false" rtexprvalue="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8" />
   <meta name="viewport" content="width=device-width, initial-scale=1" />
   <title>${empty title ? 'App' : title}</title>
   <!-- Bootstrap -->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<!-- Responsive Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
   <div class="container">
       <a class="navbar-brand fw-bold" href="#">
           ${empty title ? 'App' : title}
       </a>
       <button class="navbar-toggler" type="button"
           data-bs-toggle="collapse"
           data-bs-target="#mainNavbar">
           <span class="navbar-toggler-icon"></span>
       </button>
       <div class="collapse navbar-collapse" id="mainNavbar">
           <ul class="navbar-nav ms-auto">
               <li class="nav-item">
                   <a class="nav-link"
                      href="${pageContext.request.contextPath}/logout">
                      logout
                   </a>
               </li>
               <li class="nav-item">
                   <a class="nav-link"
                      href="${pageContext.request.contextPath}/dbtest2">
                      DB Test
                   </a>
               </li>
           </ul>
       </div>
   </div>
</nav>
<!-- Page Content -->
<div class="container my-4">
   <div class="card shadow-sm">
       <div class="card-body">
           <jsp:doBody />
       </div>
   </div>
</div>
<footer class="border-top py-3 bg-white">
   <div class="container text-muted small">
       Core MVC (Servlet + JSP) Layout
   </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
