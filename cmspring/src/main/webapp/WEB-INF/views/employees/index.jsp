<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<h2>Customers</h2>
<form method="get" action="${pageContext.request.contextPath}/employees">
   <input type="text" name="q" value="${q}" placeholder="Search employee name..." />
   <button type="submit">Search</button>
   <a href="${pageContext.request.contextPath}/employees">Reset</a>
</form>
<br/>
<table border="1" cellpadding="6" cellspacing="0">
   <thead>
   <tr>
       <th>EmployeeNumber</th>
       <th>Full Name</th>
       <th>Extension</th>
       <th>Email</th>
       <th>Job Title</th>
   </tr>
   </thead>
   <tbody>
   <c:forEach items="${employees}" var="e">
       <tr>
           <td>${e.employeeNumber}</td>
           <td>${e.firstName} ${c.lastName}</td>
           <td>${e.extension}</td>
           <td>${e.email}</td>
           <td>${e.jobTitle}</td>
       </tr>
   </c:forEach>
   <c:if test="${empty employees}">
       <tr>
           <td colspan="7">No employees found.</td>
       </tr>
   </c:if>
   </tbody>
</table>
