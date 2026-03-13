<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <div>
        <h3 class="mb-0">
            <c:choose>
                <c:when test="${ticket.ticketId == null}">Create Ticket</c:when>
                <c:otherwise>Edit Ticket</c:otherwise>
            </c:choose>
        </h3>
        <small class="text-muted">Log and manage support tickets</small>
    </div>
    <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/tickets">
        Back to Tickets
    </a>
</div>

<div class="card shadow-sm">
    <div class="card-body">
        <form method="post" action="${pageContext.request.contextPath}/tickets/save" class="row g-3">
            
            <!-- Hidden field for edit -->
            <input type="hidden" name="ticketId" value="${ticket.ticketId}" />

            <!-- Ticket No -->
            <div class="col-md-4">
                <label class="form-label">Ticket No</label>
                <input type="text" class="form-control" name="ticketNo" value="${ticket.ticketNo}" placeholder="TCK-003" required />
            </div>

            <!-- Customer -->
            <div class="col-md-8">
                <label class="form-label">Customer</label>
                <select class="form-select" name="customerId" required>
                    <option value="">-- Select customer --</option>
                    <c:forEach items="${customers}" var="c">
                        <option value="${c.customerId}" 
                            <c:if test="${ticket.customer != null && ticket.customer.customerId == c.customerId}">selected</c:if>>
                            ${c.name} (${c.email})
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- Subject -->
            <div class="col-md-8">
                <label class="form-label">Subject</label>
                <input type="text" class="form-control" name="subject" value="${ticket.subject}" required />
            </div>

            <!-- Status -->
            <div class="col-md-4">
                <label class="form-label">Status</label>
                <select class="form-select" name="status" required>
                    <c:forEach items="${statuses}" var="s">
                        <option value="${s}" <c:if test="${ticket.status == s}">selected</c:if>>${s}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Employee (optional) -->
            <div class="col-md-8">
                <label class="form-label">Assigned Employee (optional)</label>
                <select class="form-select" name="employeeId">
                    <option value="">-- Unassigned --</option>
                    <c:forEach items="${employees}" var="e">
                        <option value="${e.employeeId}" 
                            <c:if test="${ticket.employee != null && ticket.employee.employeeId == e.employeeId}">selected</c:if>>
                            ${e.name} (${e.email})
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- Description -->
            <div class="col-12">
                <label class="form-label">Description</label>
                <textarea class="form-control" name="description" rows="5" required>${ticket.description}</textarea>
            </div>

            <!-- Buttons -->
            <div class="col-12 d-flex gap-2">
                <button type="submit" class="btn btn-primary">Save Ticket</button>
                <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/tickets">Cancel</a>
            </div>
        </form>
    </div>
</div>