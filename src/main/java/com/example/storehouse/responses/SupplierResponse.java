package com.example.storehouse.responses;

import com.example.storehouse.models.Supplier;

import lombok.Data;

@Data
public class SupplierResponse {

	private Long id;
	private String email;
	private String companyName;
	private String phoneNumber;
	private String address;
	
	public SupplierResponse(Supplier supplier) {
		this.id = supplier.getId();
		this.email = supplier.getEmail();
		this.companyName = supplier.getCompanyName();
		this.phoneNumber = supplier.getPhoneNumber();
		this.address = supplier.getAddress();
	}
	
}
