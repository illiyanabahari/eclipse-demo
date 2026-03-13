package com.demo.models;

public class AppUser {
	   private int userId;
	   private String username;
	   private String passwordHash;
	   private String firstName;
	   private String lastName;
	   private String permission;
	   public int getUserId() { return userId; }
	   public void setUserId(int userId) { this.userId = userId; }
	   public String getUsername() { return username; }
	   public void setUsername(String username) { this.username = username; }
	   public String getPasswordHash() { return passwordHash; }
	   public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
	   public String getFirstName() { return firstName; }
	   public void setFirstName(String firstName) { this.firstName = firstName; }
	   public String getLastName() { return lastName; }
	   public void setLastName(String lastName) { this.lastName = lastName; }
	   public String getPermission() { return permission; }
	   public void setPermission(String permission) { this.permission = permission; }
	   public String getFullName() {
	       return (firstName == null ? "" : firstName) + " " + (lastName == null ? "" : lastName);
	   }
	}
