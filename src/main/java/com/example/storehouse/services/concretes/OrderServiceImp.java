package com.example.storehouse.services.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.storehouse.contants.Messages;
import com.example.storehouse.exceptions.NotFoundException;
import com.example.storehouse.models.Order;
import com.example.storehouse.repositories.OrderRepository;
import com.example.storehouse.repositories.RawMaterialRepository;
import com.example.storehouse.repositories.SupplierRepository;
import com.example.storehouse.repositories.UserRepository;
import com.example.storehouse.requests.CreateOrderRequest;
import com.example.storehouse.requests.UpdateOrderRequest;
import com.example.storehouse.responses.OrderResponse;
import com.example.storehouse.services.abstracts.OrderService;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;
import com.example.storehouse.utils.results.SuccessDataResult;
import com.example.storehouse.utils.results.SuccessResult;

@Service
public class OrderServiceImp implements OrderService {

	private OrderRepository orderRepository;
	private UserRepository userRepository;
	private SupplierRepository supplierRepository;
	private RawMaterialRepository materialRepository;

	@Autowired
	public OrderServiceImp(OrderRepository orderRepository, UserRepository userRepository,
			SupplierRepository supplierRepository, RawMaterialRepository materialRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.supplierRepository = supplierRepository;
		this.materialRepository = materialRepository;
	}

	@Override
	public Result add(CreateOrderRequest createOrderRequest) {
		
		Order order = new Order();
		order.setUser(this.userRepository.getById(createOrderRequest.getUserId()));
		order.setSupplier(this.supplierRepository.getById(createOrderRequest.getSupplierId()));
		order.setRawMaterial(this.materialRepository.getById(createOrderRequest.getRawMaterialId()));
		this.orderRepository.save(order);
		return new SuccessResult(Messages.added);

	}

	@Override
	public Result update(Long id, UpdateOrderRequest updateOrderRequest) {
		if (!this.orderRepository.existsById(id)) {
			throw new NotFoundException(Messages.orderNotFound);
		}
		Order order = this.orderRepository.getById(id);
		order.setSupplier(this.supplierRepository.getById(updateOrderRequest.getSupplierId()));
		this.orderRepository.save(order);
		return new SuccessResult(Messages.updated);
	}

	@Override
	public Result delete(Long id) {
		if (!this.orderRepository.existsById(id)) {
			throw new NotFoundException(Messages.orderNotFound);
		}
		this.orderRepository.delete(this.orderRepository.getById(id));
		return new SuccessResult(Messages.deleted);
	}

	@Override
	public DataResult<List<OrderResponse>> getAll() {
		List<Order> orders = this.orderRepository.findAll();
		return new SuccessDataResult<List<OrderResponse>>(
				orders.stream().map(order -> new OrderResponse(order)).collect(Collectors.toList()), Messages.listed);
	}

	@Override
	public DataResult<OrderResponse> getById(Long id) {
		Order order = this.orderRepository.getById(id);
		if (order == null) {
			throw new NotFoundException(Messages.orderNotFound);
		}
		return new SuccessDataResult<OrderResponse>(new OrderResponse(order), Messages.listed);
	}

}
