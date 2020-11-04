package com.neosofttech.poc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

import com.neosofttech.poc.entity.User;
import com.neosofttech.poc.exception.RecordAlreadyExistException;
import com.neosofttech.poc.exception.ResourceNotFoundException;
import com.neosofttech.poc.model.ResponseModel;
import com.neosofttech.poc.servise.UserService;

/**
 * @author pallavi
 * @implNote insert, update, delete, show users data.
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	
	@PostMapping("/users")
	public ResponseEntity<ResponseModel<User>> saveUser(@RequestBody User user) {
		ArrayList<User> list = new ArrayList<User>();
		HttpStatus status = null;
		if(!userService.existsByMobile(user))
		{
			if (list.add(userService.saveUser(user)))
			status = HttpStatus.OK;
		}
		else {
			throw new RecordAlreadyExistException("User already exist");
		}
		Map<String, String> request = new HashMap<String, String>();
		request.put("Save User", user.getFname() + " " + user.getLname());
		ResponseModel<User> responce = new ResponseModel<User>(request, list, HttpStatus.OK);
		return new ResponseEntity<ResponseModel<User>>(responce, status);

	}

	@PutMapping("/users")
	public ResponseEntity<ResponseModel<User>> updateUser(@RequestBody User user) {
		ArrayList<User> list = new ArrayList<User>();
		HttpStatus status = null;
		if (list.add(userService.saveUser(user)))
			status = HttpStatus.OK;
		Map<String, String> request = new HashMap<String, String>();
		request.put("Update User", user.getFname() + " " + user.getLname());
		ResponseModel<User> responce = new ResponseModel<User>(request, list, HttpStatus.OK);
		return new ResponseEntity<ResponseModel<User>>(responce, status);

	}

	@GetMapping("/users")
	public ResponseEntity<ResponseModel<User>> getAllUsers(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortedBy){
		
		Page<User> page = userService.getUsers(pageNo, pageSize, sortedBy);
		if(!page.hasContent()) 
			throw new NullPointerException("Table is empty");
		Map<String,String> request = new HashMap<String, String>();
		request.put("Get All Users", null);
		ResponseModel<User> responce =  new ResponseModel<User>(request, HttpStatus.OK, page);
		return new ResponseEntity<ResponseModel<User>>(responce,HttpStatus.OK);
		
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<ResponseModel<User>> getUser(@PathVariable	Integer id) {
		ArrayList<User> list = new ArrayList<User>();
		Optional<User> user = userService.getUser(id);
		if(!user.isPresent())
			throw new ResourceNotFoundException("User Name with ID= "+id+" is not found");
		list.add(user.get());
		Map<String, String> request = new HashMap<String, String>();
		request.put("get User", id.toString());
		ResponseModel<User> responce = new ResponseModel<User>(request, list, HttpStatus.OK);
		return new ResponseEntity<ResponseModel<User>>(responce, HttpStatus.OK);

	}
	
	
}
