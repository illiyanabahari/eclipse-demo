package com.abc.cmspring.repositories;

import com.abc.cmspring.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // LINQ-like auto query
    List<Customer> findByCountry(String country);

    // LIKE %keyword%
    List<Customer> findByCustomerNameContainingIgnoreCase(String keyword);
}
