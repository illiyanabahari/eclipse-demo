<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <div>
        <h3 class="mb-0">Country</h3>
        <small class="text-muted">Manage country records</small>
    </div>
    <a class="btn btn-primary"
       href="${pageContext.request.contextPath}/countries/new">
        + Add Category
    </a>
</div>

<div class="card shadow-sm mb-3">
    <div class="card-body">
        <form class="row g-2 align-items-center"
              method="get"
              action="${pageContext.request.contextPath}/countries">

            <div class="col-sm-8 col-md-6 col-lg-5">
                <input type="text"
                       class="form-control"
                       name="q"
                       value="${q}"
                       placeholder="Search country name..." />
            </div>

            <div class="col-auto">
                <button type="submit" class="btn btn-outline-primary">
                    Search
                </button>
            </div>

            <div class="col-auto">
            
                <a class="btn btn-outline-secondary"
                   href="${pageContext.request.contextPath}/countries">
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
                    <th>Countryname</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${countries}" var="c" varStatus="status">
                    <tr>
                        <td class="fw-semibold">${status.index + 1}</td>
                        <td>${c.countryname}</td>
                        <td>
                            <a class="btn btn-sm btn-outline-primary"
                               href="${pageContext.request.contextPath}/categories/edit/${c.countryId}">
                                Edit
                            </a>
                            <a class="btn btn-sm btn-outline-danger ms-1"
                               href="${pageContext.request.contextPath}/categories/delete/${c.countryId}"
                               onclick="return confirm('Delete this country?')">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty countries}">
                    <tr>
                        <td colspan="5" class="text-center p-4 text-muted">
                            No country found.
                        </td>
                    </tr>
                </c:if>

                </tbody>
            </table>
        </div>
    </div>
</div>