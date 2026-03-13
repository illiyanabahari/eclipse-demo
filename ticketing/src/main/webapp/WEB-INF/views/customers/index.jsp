<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <div>
        <h3 class="mb-0">Customers</h3>
        <small class="text-muted">Manage customer records</small>
    </div>
    <a class="btn btn-primary"
       href="${pageContext.request.contextPath}/customers/new">
        + Add Customer
    </a>
</div>

<div class="card shadow-sm mb-3">
    <div class="card-body">
        <form class="row g-2 align-items-center"
              method="get"
              action="${pageContext.request.contextPath}/customers">

            <div class="col-sm-8 col-md-6 col-lg-5">
                <input type="text"
                       class="form-control"
                       name="q"
                       value="${q}"
                       placeholder="Search customer name..." />
            </div>

            <div class="col-auto">
                <button type="submit" class="btn btn-outline-primary">
                    Search
                </button>
            </div>

            <div class="col-auto">
                <a class="btn btn-outline-secondary"
                   href="${pageContext.request.contextPath}/customers">
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
                    <th>Email</th>
                    <th style="width:150px;">Phone</th>
                    <th style="width:170px;">Actions</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${customers}" var="c" varStatus="status">
                    <tr>
                        <td class="fw-semibold">${status.index + 1}</td>
                        <td>${c.name}</td>
                        <td>${c.email}</td>
                        <td>${c.phone}</td>
                        <td>
                            <a class="btn btn-sm btn-outline-primary"
                               href="${pageContext.request.contextPath}/customers/edit/${c.customerId}">
                                Edit
                            </a>
                            <a class="btn btn-sm btn-outline-danger ms-1"
                               href="${pageContext.request.contextPath}/customers/delete/${c.customerId}"
                               onclick="return confirm('Delete this customer?')">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty customers}">
                    <tr>
                        <td colspan="5" class="text-center p-4 text-muted">
                            No customers found.
                        </td>
                    </tr>
                </c:if>

                </tbody>
            </table>
        </div>
    </div>
</div>