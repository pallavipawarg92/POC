package com.neosofttech.poc.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Privilege {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "privilege Name is required")
	private String privilegeName;

	@ManyToMany(mappedBy = "privileges")
	@JsonIgnore
	private Set<Role> roles;

	/*
	 * @ManyToMany(mappedBy = "privileges")
	 * 
	 * @JsonIgnore private Set<User> uses;
	 */

}
