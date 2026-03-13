<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<h2>Customers</h2>
<form method="get" action="${pageContext.request.contextPath}/customers">
   <input type="text" name="q" value="${q}" placeholder="Search customer name..." />
   <button type="submit">Search</button>
   <a href="${pageContext.request.contextPath}/customers">Reset</a>
</form>
<br/>
<table border="1" cellpadding="6" cellspacing="0">
   <thead>
   <tr>
       <th>No</th>
       <th>Name</th>
       <th>Contact</th>
       <th>Phone</th>
       <th>City</th>
       <th>Country</th>
       <th>Credit Limit</th>
   </tr>
   </thead>
   <tbody>
   <c:forEach items="${customers}" var="c">
       <tr>
           <td>${c.customerNumber}</td>
           <td>${c.customerName}</td>
           <td>${c.contactFirstName} ${c.contactLastName}</td>
           <td>${c.phone}</td>
           <td>${c.city}</td>
           <td>${c.country}</td>
           <td>${c.creditLimit}</td>
       </tr>
   </c:forEach>
   <c:if test="${empty customers}">
       <tr>
           <td colspan="7">No customers found.</td>
       </tr>
   </c:if>
   </tbody>
</table>
