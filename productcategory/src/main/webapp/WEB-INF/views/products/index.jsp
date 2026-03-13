<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <div>
        <h3 class="mb-0">Tickets</h3>
        <small class="text-muted">Manage Product</small>
    </div>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/products/new">
        + Add Products
    </a>
</div>

<div class="card shadow-sm mb-3">
    <div class="card-body">
        <form class="row g-2 align-items-center" method="get" action="${pageContext.request.contextPath}/products">
            <div class="col-sm-8 col-md-6 col-lg-5">
                <input type="text" class="form-control" name="q" value="${q}" placeholder="Search product..." />
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-outline-primary">Search</button>
            </div>
            <div class="col-auto">
                <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/products">Reset</a>
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
                        <th style="width:120px;">Product No</th>
  
                        <th style="width:220px;">ProductName</th>
                        <th style="width:220px;">CategoryName</th>
                        <th style="width:170px;">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${products}" var="p">
                        <tr>
                            <td class="fw-semibold">${p.productId}</td>
                            <td>${p.productname}</td>
                            <td>${p.category.categoryname}</td>
       
                            <td>
                                <a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/products/edit/${p.productId}">
                                    Edit
                                </a>
                                <a class="btn btn-sm btn-outline-danger ms-1" href="${pageContext.request.contextPath}/products/delete/${p.productId}"
                                   onclick="return confirm('Delete this ticket?')">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty products}">
                        <tr>
                            <td colspan="7" class="text-center p-4 text-muted">No product found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>