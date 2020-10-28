package com.neosofttech.poc.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Error {
	
	
	private String message;
	private LocalDateTime timeStamp;
	private String details;
	
	
}
