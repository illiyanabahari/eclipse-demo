<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<div class="d-flex justify-content-between align-items-center mb-3">
   <div>
       <h3 class="mb-0">
           <c:choose>
               <c:when test="${country.countryId == null}">Add country</c:when>
               <c:otherwise>Edit country</c:otherwise>
           </c:choose>
       </h3>
       <small class="text-muted">country details</small>
   </div>
   <a class="btn btn-outline-secondary"
      href="${pageContext.request.contextPath}/countries">
       Back
   </a>
</div>
<div class="card shadow-sm">
   <div class="card-body">
       <form method="post"
             action="${pageContext.request.contextPath}/countries/save"
             class="row g-3">
           <!-- IMPORTANT: needed for EDIT to work -->
           <input type="hidden" name="countryId" value="${country.countryId}" />
           <div class="col-md-6">
               <label class="form-label">CountryName</label>
               <input type="text"
                      class="form-control"
                      name="countryname"
                      value="${country.countryname}"
                      required />
           </div>
           <div class="col-12 d-flex gap-2">
               <button type="submit" class="btn btn-primary">
                   Save
               </button>
               <a class="btn btn-outline-secondary"
                  href="${pageContext.request.contextPath}/countries">
                   Cancel
               </a>
           </div>
       </form>
   </div>
</div>
