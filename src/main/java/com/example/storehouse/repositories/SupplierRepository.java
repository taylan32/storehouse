package com.example.storehouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.storehouse.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	@Query("FROM Supplier where id=:id")
	Supplier getById(Long id);
	
	Supplier getByEmail(String email);
	
	Supplier getByCompanyName(String companyName);
	
	boolean existsByEmail(String email);
	
	boolean existsById(Long id);
	
	boolean existsByCompanyName(String companyName);
	
	
}
