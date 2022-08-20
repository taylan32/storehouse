package com.example.storehouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.storehouse.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role getByName(String name);
	
	@Query("FROM Role where id=:id")
	Role getById(Long id);
	
	boolean existsByName(String name);
}
