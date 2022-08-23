package com.example.storehouse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.storehouse.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("FROM Order where id=:id")
	Order getById(Long id);

	List<Order> getBySupplier_Id(Long supplierId);

	List<Order> getByRawMaterial_Id(Long rawMaterialId);
	

}
