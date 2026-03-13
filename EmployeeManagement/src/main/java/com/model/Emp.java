package com.model;

import java.sql.Date;
import java.time.LocalDate;


public class Emp {
private int id;
private String name;
private String job;
private double salary;
private String dept;
private LocalDate hireDate;
private byte[] photo;
private Status status;

public Emp() {
	
}


public Emp(int id, String name, String job, double salary, String dept, LocalDate hireDate, byte[] photo, Status status) {
    this.id = id;
    this.name = name;
    this.job = job;
    this.salary = salary;
    this.dept = dept;
    this.hireDate = hireDate;
    this.photo = photo;
    this.status = status;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getJob() {
	return job;
}
public void setJob(String job) {
	this.job = job;
}
public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
	this.salary = salary;
}
public String getDept() {
	return dept;
}
public void setDept(String dept) {
	this.dept = dept;
}
public LocalDate getHireDate() {
	return hireDate;
}
public void setHireDate(LocalDate hireDate) {
	this.hireDate = hireDate;
}


public byte[] getPhoto() {
	return photo;
}


public void setPhoto(byte[] photo) {
	this.photo = photo;
}


public Status getStatus() {
	return status;
}


public void setStatus(Status status) {
	this.status = status;
}


}
