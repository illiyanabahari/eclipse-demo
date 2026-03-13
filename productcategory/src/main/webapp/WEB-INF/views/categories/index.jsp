<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <div>
        <h3 class="mb-0">Category</h3>
        <small class="text-muted">Manage category records</small>
    </div>
    <a class="btn btn-primary"
       href="${pageContext.request.contextPath}/categories/new">
        + Add Category
    </a>
</div>

<div class="card shadow-sm mb-3">
    <div class="card-body">
        <form class="row g-2 align-items-center"
              method="get"
              action="${pageContext.request.contextPath}/categories">

            <div class="col-sm-8 col-md-6 col-lg-5">
                <input type="text"
                       class="form-control"
                       name="q"
                       value="${q}"
                       placeholder="Search category name..." />
            </div>

            <div class="col-auto">
                <button type="submit" class="btn btn-outline-primary">
                    Search
                </button>
            </div>

            <div class="col-auto">
            
                <a class="btn btn-outline-secondary"
                   href="${pageContext.request.contextPath}/categories">
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
                    <th style="width:80px;">No</th>
                    <th>Name</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${categories}" var="c" varStatus="status">
                    <tr>
                        <td class="fw-semibold">${status.index + 1}</td>
                        <td>${c.categoryname}</td>
                        <td>
                            <a class="btn btn-sm btn-outline-primary"
                               href="${pageContext.request.contextPath}/categories/edit/${c.categoryId}">
                                Edit
                            </a>
                            <a class="btn btn-sm btn-outline-danger ms-1"
                               href="${pageContext.request.contextPath}/categories/delete/${c.categoryId}"
                               onclick="return confirm('Delete this category?')">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty categories}">
                    <tr>
                        <td colspan="5" class="text-center p-4 text-muted">
                            No category found.
                        </td>
                    </tr>
                </c:if>

                </tbody>
            </table>
        </div>
    </div>
</div>