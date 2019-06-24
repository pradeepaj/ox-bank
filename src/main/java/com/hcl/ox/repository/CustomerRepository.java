package com.hcl.ox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ox.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
