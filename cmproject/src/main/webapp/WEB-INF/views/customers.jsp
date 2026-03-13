<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<t:layout title="Customers">
   <h3 class="mb-3">Customer List</h3>
   <!-- ================= SEARCH BAR ================= -->
   <form method="get" action="${pageContext.request.contextPath}/customers" class="row g-2 mb-3">
       <!-- when searching, always reset to page 1 -->
       <input type="hidden" name="page" value="1" />
       <input type="hidden" name="size" value="${size}" />
       <div class="col-md-8">
           <input type="text"
                  name="q"
                  value="${q}"
                  class="form-control"
                  placeholder="Search name, contact, phone, city, country, customer number..." />
       </div>
       <div class="col-md-4 d-flex gap-2">
           <button type="submit" class="btn btn-primary">Search</button>
           <!-- Clear search: go back to normal list -->
           <a class="btn btn-outline-secondary"
              href="${pageContext.request.contextPath}/customers?page=1&size=${size}">
               Clear
           </a>
       </div>
   </form>
   <!-- ============================================== -->
   <c:choose>
       <c:when test="${empty customers}">
           <div class="alert alert-warning">
               No customers found.
           </div>
       </c:when>
       <c:otherwise>
           <!-- ✅ Responsive wrapper -->
           <div class="table-responsive">
               <table class="table table-striped table-hover align-middle">
                   <thead class="table-dark">
                       <tr>
                           <th style="white-space:nowrap;">Customer No</th>
                           <th style="min-width:220px;">Customer Name</th>
                           <th style="white-space:nowrap;">Contact First</th>
                           <th style="white-space:nowrap;">Contact Last</th>
                           <th style="white-space:nowrap;">Phone</th>
                           <th style="white-space:nowrap;">City</th>
                           <th style="white-space:nowrap;">State</th>
                           <th style="white-space:nowrap;">Postal</th>
                           <th style="white-space:nowrap;">Country</th>
                           <th style="white-space:nowrap;">Sales Rep</th>
                           <th style="white-space:nowrap;">Credit Limit</th>
                       </tr>
                   </thead>
                   <tbody>
                       <c:forEach var="c" items="${customers}">
                           <tr>
                               <td style="white-space:nowrap;">${c.customerNumber}</td>
                               <td style="min-width:220px; word-break:break-word;">
                                   ${c.customerName}
                               </td>
                               <td style="white-space:nowrap;">${c.contactFirstName}</td>
                               <td style="white-space:nowrap;">${c.contactLastName}</td>
                               <td style="white-space:nowrap;">${c.phone}</td>
                               <td style="white-space:nowrap;">${c.city}</td>
                               <td style="white-space:nowrap;">${c.state}</td>
                               <td style="white-space:nowrap;">${c.postalCode}</td>
                               <td style="white-space:nowrap;">${c.country}</td>
                               <td style="white-space:nowrap;">${c.salesRepEmployeeNumber}</td>
                               <td style="white-space:nowrap;">
                                   ${c.creditLimit}
                               </td>
                           </tr>
                       </c:forEach>
                   </tbody>
               </table>
           </div>
           <!-- ================= PAGINATION ================= -->
           <div class="d-flex justify-content-between align-items-center mt-3 flex-wrap gap-2">
               <div>
                   Page <strong>${page}</strong> of <strong>${totalPages}</strong>
                   (Total: <strong>${totalRows}</strong>)
                   <c:if test="${not empty q}">
                       | Search: <strong>${q}</strong>
                   </c:if>
               </div>
               <nav aria-label="Customer pages">
                   <ul class="pagination mb-0">
                       <!-- Prev -->
                       <li class="page-item ${page == 1 ? 'disabled' : ''}">
                           <a class="page-link"
                              href="${pageContext.request.contextPath}/customers?q=${q}&page=${page-1}&size=${size}">
                               Prev
                           </a>
                       </li>
                       <!-- Page numbers -->
                       <c:forEach begin="1" end="${totalPages}" var="i">
                           <li class="page-item ${i == page ? 'active' : ''}">
                               <a class="page-link"
                                  href="${pageContext.request.contextPath}/customers?q=${q}&page=${i}&size=${size}">
                                   ${i}
                               </a>
                           </li>
                       </c:forEach>
                       <!-- Next -->
                       <li class="page-item ${page == totalPages ? 'disabled' : ''}">
                           <a class="page-link"
                              href="${pageContext.request.contextPath}/customers?q=${q}&page=${page+1}&size=${size}">
                               Next
                           </a>
                       </li>
                   </ul>
               </nav>
           </div>
           <!-- ============================================== -->
       </c:otherwise>
   </c:choose>
</t:layout>



