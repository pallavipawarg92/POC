package com.neosofttech.poc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosofttech.poc.entity.Role;
import com.neosofttech.poc.exception.RecordAlreadyExistException;
import com.neosofttech.poc.exception.ResourceNotFoundException;
import com.neosofttech.poc.model.ResponseModel;
import com.neosofttech.poc.servise.RoleService;

/**
 * @author pallavi
 * @implNote insert, update, delete, show roles data.
 *
 */
@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;

	
	@PostMapping("/roles")
	public ResponseEntity<ResponseModel<Role>> saveRole(@Valid @RequestBody Role role) {
		ArrayList<Role> list = new ArrayList<Role>();
		HttpStatus status = null;
		if(!roleService.existsByRoleName(role))
		{
			if (list.add(roleService.saveRole(role)))
			status = HttpStatus.OK;
		}
		else {
			throw new RecordAlreadyExistException("Role already exist");
		}
		Map<String, String> request = new HashMap<String, String>();
		request.put("Save Role", role.getRoleName());
		ResponseModel<Role> responce = new ResponseModel<Role>(request, list, HttpStatus.OK);
		return new ResponseEntity<ResponseModel<Role>>(responce, status);

	}

	@PutMapping("/roles")
	public ResponseEntity<ResponseModel<Role>> updateRole(@RequestBody Role role) {
		ArrayList<Role> list = new ArrayList<Role>();
		HttpStatus status = null;
		if (list.add(roleService.saveRole(role)))
			status = HttpStatus.OK;
		Map<String, String> request = new HashMap<String, String>();
		request.put("Update Role", role.getRoleName());
		ResponseModel<Role> responce = new ResponseModel<Role>(request, list, HttpStatus.OK);
		return new ResponseEntity<ResponseModel<Role>>(responce, status);

	}

	@GetMapping("/roles")
	public ResponseEntity<ResponseModel<Role>> getAllRoles(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortedBy){
		
		Page<Role> page = roleService.getRoles(pageNo, pageSize, sortedBy);
		if(!page.hasContent()) 
			throw new NullPointerException("Table is empty");
		Map<String,String> request = new HashMap<String, String>();
		request.put("Get All Roles", null);
		ResponseModel<Role> responce =  new ResponseModel<Role>(request, HttpStatus.OK, page);
		return new ResponseEntity<ResponseModel<Role>>(responce,HttpStatus.OK);
		
	}
	
	@GetMapping("/roles/{id}")
	public ResponseEntity<ResponseModel<Role>> getRole(@PathVariable	Integer id) {
		ArrayList<Role> list = new ArrayList<Role>();
		Optional<Role> role = roleService.getRole(id);
		if(!role.isPresent())
			throw new ResourceNotFoundException("Role Name with ID= "+id+" is not found");
		list.add(role.get());
		Map<String, String> request = new HashMap<String, String>();
		request.put("get Role", id.toString());
		ResponseModel<Role> responce = new ResponseModel<Role>(request, list, HttpStatus.OK);
		return new ResponseEntity<ResponseModel<Role>>(responce, HttpStatus.OK);

	}
	
	@GetMapping("/roles-all")
	public ResponseEntity<ResponseModel<Role>> getAllRolesBySpecificName(){
		
		List<Role> roles = roleService.getAllRoles();
		Map<String,String> request = new HashMap<String, String>();
		request.put("Get All Roles", null);
		ResponseModel<Role> responce =  new ResponseModel<Role>(request, roles, HttpStatus.OK);
		return new ResponseEntity<ResponseModel<Role>>(responce,HttpStatus.OK);
		
	}
}
