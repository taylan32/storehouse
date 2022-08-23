package com.example.storehouse.responses;

import com.example.storehouse.models.RawMaterial;

import lombok.Data;

@Data
public class RawMaterialResponse {

	private Long id;
	
	private String name;
	
	private String amount;
	
	public RawMaterialResponse(RawMaterial rawMaterial) {
		this.id = rawMaterial.getId();
		this.name = rawMaterial.getName();
		this.amount = rawMaterial.getAmount();
	}
	
}
