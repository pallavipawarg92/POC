package com.neosofttech.poc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String address1;
	private String address2;
	
	@NotBlank(message = "City is required")
	private String City;
	@Pattern(regexp = "^[1-9][0-9]{5}$", message = "Pincode is invalid.")
	private String pincode;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	

}
