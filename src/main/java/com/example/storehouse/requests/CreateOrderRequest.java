package com.example.storehouse.requests;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class CreateOrderRequest {
	
	@NotNull
	private Long supplierId;

	@NotNull
	private Long rawMaterialId;

	@NotNull
	private Long userId;

}
