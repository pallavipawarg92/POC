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

import com.neosofttech.poc.entity.Privilege;
import com.neosofttech.poc.repository.PrivilegeRepository;

@Service
public class PrivilegeService {

	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	
	public Privilege savePrivilege(Privilege privilege){
		return privilegeRepository.save(privilege);
	}


	public Page<Privilege> getPrivileges(Integer pageNo, Integer pageSize, String sortedBy) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy));
		Page<Privilege> pageResult = privilegeRepository.findAll(pageable);
		return pageResult;
		
	}


	public Optional<Privilege> getPrivilege(Integer id) {
		return privilegeRepository.findById(id);
	}


	public List<Privilege> getAllPrivileges(){
		return privilegeRepository.findAll();
	}


	public boolean existsByPrivilegeName(@Valid Privilege privilege) {
		return privilegeRepository.existsByPrivilegeName(privilege.getPrivilegeName());
	}
}
