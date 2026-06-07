package com.bookstack.backend.repository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPhone(String phone);
    List<Customer> findByLastName(String name);
}
