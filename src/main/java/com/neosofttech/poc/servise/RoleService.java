package com.neosofttech.poc.servise;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.neosofttech.poc.entity.Role;
import com.neosofttech.poc.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	
	public Role saveRole(Role role){
		return roleRepository.save(role);
	}


	public Page<Role> getRoles(Integer pageNo, Integer pageSize, String sortedBy) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy));
		Page<Role> pageResult = roleRepository.findAll(pageable);
		return pageResult;
		
	}


	public Optional<Role> getRole(Integer id) {
		return roleRepository.findById(id);
	}


	public List<Role> getAllRoles(){
		return roleRepository.findAll();
	}


	public boolean existsByRoleName(@Valid Role role) {
		return roleRepository.existsByRoleName(role.getRoleName());
	}
	
}
