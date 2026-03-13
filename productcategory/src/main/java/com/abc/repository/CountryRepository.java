package com.abc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entities.Country;


public interface CountryRepository extends JpaRepository<Country, Integer> {

    // LINQ-like auto query
    //List<Customer> findByCountry(String country);

    // LIKE %keyword%
    List<Country> findByCountrynameContainingIgnoreCase(String keyword);
}