<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <div>
        <h3 class="mb-0">
            <c:choose>
                <c:when test="${customer.customerId == null}">Create customer</c:when>
                <c:otherwise>Edit customer</c:otherwise>
            </c:choose>
        </h3>
        <small class="text-muted">Log and manage support product</small>
    </div>
    <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/customers">
        Back to Products
    </a>
</div>

<div class="card shadow-sm">
    <div class="card-body">
        <form method="post" action="${pageContext.request.contextPath}/customers/save" class="row g-3">
            
            <!-- Hidden field for edit -->
            <input type="hidden" name="customerId" value="${customer.customerId}" />

            <!-- productname -->
            <div class="col-md-4">
                <label class="form-label">Firstname</label>
                <input type="text" class="form-control" name="firstname" value="${customer.firstname}" placeholder="illiyana" required />
            </div>
            
             <!-- productname -->
            <div class="col-md-4">
                <label class="form-label">Lastname</label>
                <input type="text" class="form-control" name="lastname" value="${customer.lastname}" placeholder="bahari" required />
            </div>
            
             <!-- Customer -->
            <div class="col-md-8">
                <label class="form-label">countries</label>
                <select class="form-select" name="countryId" required>
                    <option value="">-- Select country --</option>
                    <c:forEach items="${countries}" var="c">
                        <option value="${c.countryId}" 
                            <c:if test="${customer.country != null && customer.country.countryId == c.countryId}">selected</c:if>>
                            ${c.countryname} 
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- Buttons -->
            <div class="col-12 d-flex gap-2">
                <button type="submit" class="btn btn-primary">Save customer</button>
                <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/customers">Cancel</a>
            </div>
        </form>
    </div>
</div>