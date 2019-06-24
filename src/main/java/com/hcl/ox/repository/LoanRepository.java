package com.hcl.ox.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.ox.entity.Loan;
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
	
	public List<Loan> findByLoanStatus(String loanStatus);
	
	@Transactional
	@Modifying
    @Query(value = "delete from oxbank.Loan_details where manager_id=?1 and loan_status='rejected'", nativeQuery = true)
	public void findBymanagerId(long managerId);

}
