<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <div>
        <h3 class="mb-0">
            <c:choose>
                <c:when test="${product.productId == null}">Create product</c:when>
                <c:otherwise>Edit Product</c:otherwise>
            </c:choose>
        </h3>
        <small class="text-muted">Log and manage support product</small>
    </div>
    <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/products">
        Back to Products
    </a>
</div>

<div class="card shadow-sm">
    <div class="card-body">
        <form method="post" action="${pageContext.request.contextPath}/products/save" class="row g-3">
            
            <!-- Hidden field for edit -->
            <input type="hidden" name="productId" value="${product.productId}" />

            <!-- productname -->
            <div class="col-md-4">
                <label class="form-label">ProductName</label>
                <input type="text" class="form-control" name="productname" value="${product.productname}" placeholder="noodle" required />
            </div>
            
             <!-- Customer -->
            <div class="col-md-8">
                <label class="form-label">category</label>
                <select class="form-select" name="categoryId" required>
                    <option value="">-- Select category --</option>
                    <c:forEach items="${categories}" var="c">
                        <option value="${c.categoryId}" 
                            <c:if test="${product.category != null && product.category.categoryId == c.categoryId}">selected</c:if>>
                            ${c.categoryname} 
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- Buttons -->
            <div class="col-12 d-flex gap-2">
                <button type="submit" class="btn btn-primary">Save Product</button>
                <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/products">Cancel</a>
            </div>
        </form>
    </div>
</div>