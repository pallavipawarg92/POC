package com.neosofttech.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosofttech.poc.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public boolean existsByFnameAndMnameAndLnameAndMobile(String fName, String mName, String lName, String mobile);

	public boolean existsByMobile(String mobile);
}
