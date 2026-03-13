<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="d-flex justify-content-between align-items-center mb-3">
   <div>
       <h3 class="mb-0">Employees</h3>
       <small class="text-muted">Manage employee records</small>
   </div>
   <a class="btn btn-primary"
      href="${pageContext.request.contextPath}/employees/new">
       + Add Employee
   </a>
</div>

<div class="card shadow-sm mb-3">
   <div class="card-body">
       <form class="row g-2 align-items-center"
             method="get"
             action="${pageContext.request.contextPath}/employees">

           <div class="col-sm-8 col-md-6 col-lg-5">
               <input type="text"
                      class="form-control"
                      name="q"
                      value="${q}"
                      placeholder="Search employee name..." />
           </div>

           <div class="col-auto">
               <button type="submit" class="btn btn-outline-primary">
                   Search
               </button>
           </div>

           <div class="col-auto">
               <a class="btn btn-outline-secondary"
                  href="${pageContext.request.contextPath}/employees">
                   Reset
               </a>
           </div>

       </form>
   </div>
</div>

<div class="card shadow-sm">
   <div class="card-body p-0">
       <div class="table-responsive">
           <table class="table table-striped table-hover mb-0 align-middle">
               <thead class="table-light">
               <tr>
                   <th style="width:80px;">ID</th>
                   <th>Name</th>
                   <th>Email</th>
                   <th style="width:160px;">Role</th>
                   <th style="width:170px;">Actions</th>
               </tr>
               </thead>

               <tbody>

               <c:forEach items="${employees}" var="e">
                   <tr>
                       <td class="fw-semibold">${e.employeeId}</td>
                       <td>${e.name}</td>
                       <td>${e.email}</td>
                       <td>
                           <span class="badge bg-secondary">
                               ${e.role}
                           </span>
                       </td>
                       <td>
                           <a class="btn btn-sm btn-outline-primary"
                              href="${pageContext.request.contextPath}/employees/edit/${e.employeeId}">
                               Edit
                           </a>
                           <a class="btn btn-sm btn-outline-danger ms-1"
                              href="${pageContext.request.contextPath}/employees/delete/${e.employeeId}"
                              onclick="return confirm('Delete this employee?')">
                               Delete
                           </a>
                       </td>
                   </tr>
               </c:forEach>

               <c:if test="${empty employees}">
                   <tr>
                       <td colspan="5" class="text-center p-4 text-muted">
                           No employees found.
                       </td>
                   </tr>
               </c:if>

               </tbody>
           </table>
       </div>
   </div>
</div>