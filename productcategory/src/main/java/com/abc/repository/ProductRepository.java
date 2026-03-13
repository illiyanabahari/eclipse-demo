package com.abc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // LINQ-like auto query
    //List<Customer> findByCountry(String country);

    // LIKE %keyword%
    List<Product> findByProductnameContainingIgnoreCase(String keyword);
}

