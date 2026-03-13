<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <div>
        <h3 class="mb-0">
            <c:choose>
                <c:when test="${employee.employeeId != null}">
                    Edit Employee
                </c:when>
                <c:otherwise>
                    Create Employee
                </c:otherwise>
            </c:choose>
        </h3>
        <small class="text-muted">
            Manage employee information
        </small>
    </div>

    <a class="btn btn-outline-secondary"
       href="${pageContext.request.contextPath}/employees">
        Back to Employees
    </a>
</div>

<div class="card shadow-sm">
    <div class="card-body">

        <form method="post"
              action="${pageContext.request.contextPath}/employees/save"
              class="row g-3">

            <!-- Hidden ID (for Edit mode) -->
            <c:if test="${employee.employeeId != null}">
                <input type="hidden"
                       name="employeeId"
                       value="${employee.employeeId}" />
            </c:if>

            <!-- Name -->
            <div class="col-md-6">
                <label class="form-label">Full Name</label>
                <input type="text"
                       class="form-control"
                       name="name"
                       value="${employee.name}"
                       placeholder="John Doe"
                       required />
            </div>

            <!-- Email -->
            <div class="col-md-6">
                <label class="form-label">Email</label>
                <input type="email"
                       class="form-control"
                       name="email"
                       value="${employee.email}"
                       placeholder="john@company.com"
                       required />
            </div>

            <!-- Role Dropdown -->
            <div class="col-md-6">
                <label class="form-label">Role</label>
                <select class="form-select" name="role" required>
                    <option value="">-- Select Role --</option>

                    <c:forEach items="${roles}" var="r">
                        <option value="${r}"
                            <c:if test="${employee.role == r}">
                                selected
                            </c:if>>
                            ${r}
                        </option>
                    </c:forEach>

                </select>
            </div>

            <!-- Buttons -->
            <div class="col-12 d-flex gap-2">
                <button type="submit" class="btn btn-primary">
                    Save Employee
                </button>

                <a class="btn btn-outline-secondary"
                   href="${pageContext.request.contextPath}/employees/new">
                    Reset
                </a>
            </div>

        </form>

    </div>
</div>