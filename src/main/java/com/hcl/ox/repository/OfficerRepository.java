package com.hcl.ox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.ox.entity.Officer;

public interface OfficerRepository extends JpaRepository<Officer, Long> {

}
