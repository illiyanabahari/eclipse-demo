package com.abc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entities.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> 
{
	 List<Employee> findByNameContainingIgnoreCase(String keyword);
}

