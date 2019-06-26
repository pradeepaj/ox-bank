package com.hcl.ox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.ox.entity.Loan;
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
	
	public List<Loan> findByLoanStatus(String loanStatus);
	
//	@Transactional
//	@Modifying
//    @Query(value = "delete from oxbank.Loan_details where manager_id=?1 and loan_status='rejected'", nativeQuery = true)
//	public List<Loan> findBymanagerId(long managerId);
	
	@Query("select ca from Loan ca where ca.officer.Id=:managerId and ca.loanStatus=:status")
	public List<Loan> findByManagerIdAndStatus( Long managerId, String status);
}
