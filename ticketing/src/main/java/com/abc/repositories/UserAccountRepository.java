package com.abc.repositories;

import com.abc.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> 
{
    Optional<UserAccount> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmployee_EmployeeId(Integer employeeId);
    boolean existsByCustomer_CustomerId(Integer customerId);   
}
