package com.demo.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "users")
public class AppUser {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id")
   private Integer userId;
   @Column(name = "username", nullable = false, unique = true, length = 50)
   private String username;
   @Column(name = "password_hash", nullable = false, length = 100)
   private String passwordHash;
   @Column(name = "full_name", nullable = false, length = 100)
   private String fullName;
   // DB is TINYINT(1) -> map as Byte (0/1)
   @Column(name = "is_active", nullable = false)
   private Byte isActive;
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
   public String getPasswordHash() {
       return passwordHash;
   }
   public void setPasswordHash(String passwordHash) {
       this.passwordHash = passwordHash;
   }
   public String getFullName() {
       return fullName;
   }
   public void setFullName(String fullName) {
       this.fullName = fullName;
   }
   public Byte getIsActive() {
       return isActive;
   }
   public void setIsActive(Byte isActive) {
       this.isActive = isActive;
   }
}
