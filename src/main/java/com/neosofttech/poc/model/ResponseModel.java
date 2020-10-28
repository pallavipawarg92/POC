package com.neosofttech.poc.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResponseModel<T> {
	
	private Map<String, String> request;
	private List<T> responce;
	private Error error;
	private HttpStatus status;
	private Page<T> page;
	
	public ResponseModel(Map<String, String> request, Error exceptionResponce, HttpStatus status) {
		this.request = request;
		this.error = exceptionResponce;
		this.status = status;
	}

	public ResponseModel(Map<String, String> request, HttpStatus status, Page<T> page) {
		this.request = request;
		this.status = status;
		this.page = page;
	}

	public ResponseModel(Map<String, String> request, List<T> responce, HttpStatus status) {
		this.request = request;
		this.responce = responce;
		this.status = status;
	}
	
	

}
