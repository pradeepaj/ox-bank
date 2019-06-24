package com.hcl.ox.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "officerDetails")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Officer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "managerId")
	private long Id;
	private String name;
	private String level;

}
