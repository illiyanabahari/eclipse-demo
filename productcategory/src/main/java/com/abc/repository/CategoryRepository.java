package com.abc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // LINQ-like auto query
    //List<Customer> findByCountry(String country);

    // LIKE %keyword%
    List<Category> findByCategorynameContainingIgnoreCase(String keyword);
}


