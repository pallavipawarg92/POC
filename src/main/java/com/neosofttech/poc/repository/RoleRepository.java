package com.neosofttech.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosofttech.poc.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	boolean existsByRoleName(String roleName);

}
