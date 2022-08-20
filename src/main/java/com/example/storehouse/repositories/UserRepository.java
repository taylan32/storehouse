package com.example.storehouse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.storehouse.models.User;
import com.example.storehouse.models.dto.UserWithDepartmentDto;

public interface UserRepository extends JpaRepository<User, Long> {

	User getByEmail(String email);

	@Query("FROM User where id=:id")
	User getById(Long id);

	boolean existsByEmail(String email);

	List<User> getByDepartmentId(Long departmentId);

	//@Query(value = "select u.id, u.email, u.first_name, u.last_name, d.id, d.name from users as u inner join departments as d on u.department_id = d.id", nativeQuery = true)
	@Query("Select new com.example.storehouse.models.dto.UserWithDepartmentDto (u.id, u.email, u.firstName, u.lastName, d.id, d.name) From Department d Inner Join d.users u")
	List<UserWithDepartmentDto> getAllUsersWithDepartment();
}
