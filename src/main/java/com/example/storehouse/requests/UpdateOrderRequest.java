package com.example.storehouse.requests;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class UpdateOrderRequest {
	@NotNull
	private Long supplierId;
}
