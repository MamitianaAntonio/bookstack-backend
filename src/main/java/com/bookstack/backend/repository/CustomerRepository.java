package com.bookstack.backend.repository;

import com.bookstack.backend.repository.model.JCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<JCustomer, String> {
}
