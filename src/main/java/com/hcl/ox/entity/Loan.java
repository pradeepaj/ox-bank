package com.hcl.ox.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loanDetails")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loanAccountNumber;
	private String securityAddress;
	private double securityAmount;
	private String loanStatus;
	private double loanAmount;
	
	@OneToOne
	@JoinColumn(name = "CustomerId")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name = "managerId")
	private Officer officer;
}

//findByCustomer() and pass customer objevct