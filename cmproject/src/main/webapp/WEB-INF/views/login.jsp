<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<t:layout title="Login">
   <h3 class="mb-3">Login</h3>
   <c:if test="${not empty error}">
       <div class="alert alert-danger">
           ${error}
       </div>
   </c:if>
   <form method="post" action="${pageContext.request.contextPath}/login" class="card p-3" style="max-width:520px;">
       <div class="mb-3">
           <label class="form-label">Username</label>
           <input type="text" name="username" class="form-control" value="${username}" required />
       </div>
       <div class="mb-3">
           <label class="form-label">Password</label>
           <input type="password" name="password" class="form-control" required />
       </div>
       <button type="submit" class="btn btn-primary">Sign in</button>
       <div class="text-muted mt-3" style="font-size:0.9rem;">
           Demo accounts (after visiting <code>/init-user</code>):<br/>
           <strong>admin</strong> / Password@123<br/>
           <strong>user</strong> / Password@123
       </div>
   </form>
</t:layout>
