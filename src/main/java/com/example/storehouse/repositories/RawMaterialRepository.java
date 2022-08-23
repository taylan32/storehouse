package com.example.storehouse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.storehouse.models.RawMaterial;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {

	@Query("FROM RawMaterial where id=:id")
	RawMaterial getById(Long id);
	
	//List<RawMaterial> getBySupplier_Id(long supplierId);
	
	boolean existsByName(String name);
	
	boolean existsById(Long id);
}
