package com.abc.cmspring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.cmspring.entities.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> 
{
	 List<Employee> findByFirstNameContainingIgnoreCase(String keyword);
}
