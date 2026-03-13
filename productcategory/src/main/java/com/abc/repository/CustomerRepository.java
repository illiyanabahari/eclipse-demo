package com.abc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entities.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // LINQ-like auto query
    //List<Customer> findByCountry(String country);

    // LIKE %keyword%
    List<Customer> findByFirstnameContainingIgnoreCase(String keyword);
}