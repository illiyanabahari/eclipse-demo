package com.abc.security;

import com.abc.entities.UserRole;
public class SessionUser
{
   private Integer userId;
   private String username;
   private UserRole role;
   private Integer employeeId;
   private Integer customerId;
   private boolean mustChangePassword;
   public Integer getUserId() { return userId; }
   public void setUserId(Integer userId) { this.userId = userId; }
   public String getUsername() { return username; }
   public void setUsername(String username) { this.username = username; }
   public UserRole getRole() { return role; }
   public void setRole(UserRole role) { this.role = role; }
   public Integer getEmployeeId() { return employeeId; }
   public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }
   public Integer getCustomerId() { return customerId; }
   public void setCustomerId(Integer customerId) { this.customerId = customerId; }
   public boolean isMustChangePassword() { return mustChangePassword; }
   public void setMustChangePassword(boolean mustChangePassword) { this.mustChangePassword = mustChangePassword; }
}

