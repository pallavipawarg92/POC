package com.neosofttech.poc.servise;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.neosofttech.poc.entity.User;
import com.neosofttech.poc.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public User saveUser(User user){
		return userRepository.save(user);
	}


	public Page<User> getUsers(Integer pageNo, Integer pageSize, String sortedBy) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy));
		Page<User> pageResult = userRepository.findAll(pageable);
		return pageResult;
		
	}


	public Optional<User> getUser(Integer id) {
		return userRepository.findById(id);
	}


	public boolean existsByFnameAndMnameAndLnameAndMobile(User user) {
		return userRepository.existsByFnameAndMnameAndLnameAndMobile(user.getFname(), user.getMname(), user.getLname(), user.getMobile());
	}


	public boolean existsByMobile(User user) {
		return userRepository.existsByMobile(user.getMobile());
	}

	
	
}
