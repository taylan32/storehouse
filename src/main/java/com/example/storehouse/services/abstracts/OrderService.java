package com.example.storehouse.services.abstracts;

import java.util.List;

import com.example.storehouse.requests.CreateOrderRequest;
import com.example.storehouse.requests.UpdateOrderRequest;
import com.example.storehouse.responses.OrderResponse;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;

public interface OrderService {

	Result add(CreateOrderRequest createOrderRequest);

	Result update(Long id, UpdateOrderRequest updateOrderRequest);

	Result delete(Long id);

	DataResult<List<OrderResponse>> getAll();

	DataResult<OrderResponse> getById(Long id);

}
