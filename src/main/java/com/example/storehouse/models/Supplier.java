package com.example.storehouse.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suppliers")
public class Supplier {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "company_name", unique = true)
	private String companyName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "address")
	private String address;
	
}
