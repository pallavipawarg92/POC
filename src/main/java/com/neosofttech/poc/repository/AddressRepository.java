package com.neosofttech.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosofttech.poc.entity.Address;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Integer>{

}
