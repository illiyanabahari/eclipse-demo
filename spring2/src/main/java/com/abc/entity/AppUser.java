package com.abc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "users")
public class AppUser {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "userid")
   private Integer userId;
   
   @Column(name = "username", nullable = false, length = 100)
   private String username;
   
   @Column(name = "password", nullable = false, length = 100)
   private String password;
   
   @Column(name = "firstname", nullable = false, length = 100)
   private String firstname;
   
   @Column(name = "lastname", nullable = false, length = 100)
   private String lastname;

   public Integer getUserId() {
	return userId;
   }

   public void setUserId(Integer userId) {
	this.userId = userId;
   }
   

   public String getUsername() {
	return username;
}

   public void setUsername(String username) {
	this.username = username;
   }

   public String getPassword() {
	return password;
   }

   public void setPasswor(String password) {
	this.password = password;
   }

   public String getFirstname() {
	return firstname;
   }

   public void setFirstname(String firstname) {
	this.firstname = firstname;
   }

   public String getLastname() {
	return lastname;
   }

   public void setLastname(String lastname) {
	this.lastname = lastname;
   }
   
}
  