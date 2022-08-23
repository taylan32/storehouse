package com.example.storehouse.responses;

import com.example.storehouse.models.Order;
import com.example.storehouse.models.RawMaterial;
import com.example.storehouse.models.Supplier;
import com.example.storehouse.models.User;

import lombok.Data;

@Data
public class OrderResponse {

	private User user;
	private Supplier supplier;
	private RawMaterial rawMaterial;
	
	public OrderResponse(Order order) {
		this.user = order.getUser();
		this.supplier = order.getSupplier();
		this.rawMaterial = order.getRawMaterial();
	}
	
}
