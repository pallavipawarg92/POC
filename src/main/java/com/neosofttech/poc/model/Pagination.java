package com.neosofttech.poc.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> {

	private int page;
	private int count;
	private int totalItems;
	private int totalPages;
	private List<T> items;

}
