package com.example.storehouse.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Order> orders;
	
}
