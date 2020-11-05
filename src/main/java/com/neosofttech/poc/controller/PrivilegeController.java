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

import com.neosofttech.poc.entity.Privilege;
import com.neosofttech.poc.exception.RecordAlreadyExistException;
import com.neosofttech.poc.exception.ResourceNotFoundException;
import com.neosofttech.poc.model.ResponseModel;
import com.neosofttech.poc.servise.PrivilegeService;

/**
 * @author pallavi
 * @implNote insert, update, delete, show privileges data.
 *
 */
@RestController
public class PrivilegeController {

	@Autowired
	private PrivilegeService privilegeService;

	
	@PostMapping("/privileges")
	public ResponseEntity<ResponseModel<Privilege>> savePrivilege(@Valid @RequestBody Privilege privilege) {
		ArrayList<Privilege> list = new ArrayList<Privilege>();
		HttpStatus status = null;
		if(!privilegeService.existsByPrivilegeName(privilege))
		{
			if (list.add(privilegeService.savePrivilege(privilege)))
			status = HttpStatus.OK;
		}
		else {
			throw new RecordAlreadyExistException("Privilege already exist");
		}
		Map<String, String> request = new HashMap<String, String>();
		request.put("Save Privilege", privilege.getPrivilegeName());
		ResponseModel<Privilege> responce = new ResponseModel<Privilege>(request, list, HttpStatus.OK);
		return new ResponseEntity<ResponseModel<Privilege>>(responce, status);

	}

	@PutMapping("/privileges")
	public ResponseEntity<ResponseModel<Privilege>> updatePrivilege(@RequestBody Privilege privilege) {
		ArrayList<Privilege> list = new ArrayList<Privilege>();
		HttpStatus status = null;
		if (list.add(privilegeService.savePrivilege(privilege)))
			status = HttpStatus.OK;
		Map<String, String> request = new HashMap<String, String>();
		request.put("Update Privilege", privilege.getPrivilegeName());
		ResponseModel<Privilege> responce = new ResponseModel<Privilege>(request, list, HttpStatus.OK);
		return new ResponseEntity<ResponseModel<Privilege>>(responce, status);

	}

	@GetMapping("/privileges")
	public ResponseEntity<ResponseModel<Privilege>> getAllPrivileges(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortedBy){
		
		Page<Privilege> page = privilegeService.getPrivileges(pageNo, pageSize, sortedBy);
		if(!page.hasContent()) 
			throw new NullPointerException("Table is empty");
		Map<String,String> request = new HashMap<String, String>();
		request.put("Get All Privileges", null);
		ResponseModel<Privilege> responce =  new ResponseModel<Privilege>(request, HttpStatus.OK, page);
		return new ResponseEntity<ResponseModel<Privilege>>(responce,HttpStatus.OK);
		
	}
	
	@GetMapping("/privileges/{id}")
	public ResponseEntity<ResponseModel<Privilege>> getPrivilege(@PathVariable	Integer id) {
		ArrayList<Privilege> list = new ArrayList<Privilege>();
		Optional<Privilege> privilege = privilegeService.getPrivilege(id);
		if(!privilege.isPresent())
			throw new ResourceNotFoundException("Privilege Name with ID= "+id+" is not found");
		list.add(privilege.get());
		Map<String, String> request = new HashMap<String, String>();
		request.put("get Privilege", id.toString());
		ResponseModel<Privilege> responce = new ResponseModel<Privilege>(request, list, HttpStatus.OK);
		return new ResponseEntity<ResponseModel<Privilege>>(responce, HttpStatus.OK);

	}
	
	@GetMapping("/privileges-all")
	public ResponseEntity<ResponseModel<Privilege>> getAllPrivilegesBySpecificName(){
		
		List<Privilege> privileges = privilegeService.getAllPrivileges();
		Map<String,String> request = new HashMap<String, String>();
		request.put("Get All Privileges", null);
		ResponseModel<Privilege> responce =  new ResponseModel<Privilege>(request, privileges, HttpStatus.OK);
		return new ResponseEntity<ResponseModel<Privilege>>(responce,HttpStatus.OK);
		
	}
}
