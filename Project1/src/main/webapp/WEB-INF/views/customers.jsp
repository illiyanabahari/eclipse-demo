<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<t:layout title="Customers">
   <h3 class="mb-3">Customer List</h3>
   <c:choose>
       <c:when test="${empty customers}">
           <div class="alert alert-warning">
               No customers found.
           </div>
       </c:when>
       <c:otherwise>
           <!-- ✅ Responsive wrapper (this is the key fix) -->
           <div class="table-responsive">
               <table class="table table-striped table-hover align-middle">
                   <thead class="table-dark">
                       <tr>
                           <th style="white-space:nowrap;">ID</th>
                           <th style="white-space:nowrap;">First</th>
                           <th style="white-space:nowrap;">Last</th>
                           <!-- Email can be long -->
                           <th style="min-width:220px;">Email</th>
                           <th style="white-space:nowrap;">Phone</th>
                           <th style="white-space:nowrap;">Created</th>
                       </tr>
                   </thead>
                   <tbody>
                       <c:forEach var="c" items="${customers}">
                           <tr>
                               <td style="white-space:nowrap;">${c.customerId}</td>
                               <td style="white-space:nowrap;">${c.firstName}</td>
                               <td style="white-space:nowrap;">${c.lastName}</td>
                               <td style="min-width:220px; word-break:break-word;">
                                   ${c.email}
                               </td>
                               <td style="white-space:nowrap;">${c.phone}</td>
                               <td style="white-space:nowrap;">
                                   ${c.createdAt}
                               </td>
                           </tr>
                       </c:forEach>
                   </tbody>
               </table>
           </div>
       </c:otherwise>
   </c:choose>
</t:layout>
