package com.example.storehouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.storehouse.models.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

	@Query("FROM Department where id=:id")
	Department getById(Long id);
	
	boolean existsByName(String name);
	
	boolean existsById(Long id);
	
}
