package com.neosofttech.poc.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.neosofttech.poc.model.Error;
import com.neosofttech.poc.model.ResponseModel;

@ControllerAdvice
public class ExceptionHandling {

	private static final Logger LOGGER = LogManager.getLogger(ExceptionHandling.class);
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleEcxeption(Exception ex, WebRequest req){
		
		Error exception = new Error(ex.getMessage(), LocalDateTime.now(), req.getDescription(false));
		Map<String,String> m = new HashMap<String, String>();
		m.put(req.getDescription(false),null);
		ResponseModel<Object> res = new ResponseModel<Object>(m, exception, HttpStatus.INTERNAL_SERVER_ERROR);
		LOGGER.error(ex.getMessage());
		return new ResponseEntity<Object>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	@ExceptionHandler(value = {ResourceNotFoundException.class})
	public ResponseEntity<Object> handleInvalidInputeException(ResourceNotFoundException ex, WebRequest req){
		
		Error exception = new Error(ex.getMessage(), LocalDateTime.now(), req.getDescription(false));
		Map<String,String> m = new HashMap<String, String>();
		m.put(req.getDescription(false),null);
		ResponseModel<Object> res = new ResponseModel<Object>(m, exception, HttpStatus.NOT_FOUND);
		LOGGER.error(ex.getMessage());
		return new ResponseEntity<Object>(res,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value = {RecordAlreadyExistException.class})
	public ResponseEntity<Object> handleRecordAlredyExistException(RecordAlreadyExistException ex, WebRequest req){
		
		Error exception = new Error(ex.getMessage(), LocalDateTime.now(), req.getDescription(false));
		Map<String,String> m = new HashMap<String, String>();
		m.put(req.getDescription(false),null);
		ResponseModel<Object> res = new ResponseModel<Object>(m, exception, HttpStatus.CONFLICT);
		LOGGER.error(ex.getMessage());
		return new ResponseEntity<Object>(res,HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(value = {NullPointerException.class})
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest req){
		
		Error exception = new Error(ex.getMessage(), LocalDateTime.now(), req.getDescription(false));
		Map<String,String> m = new HashMap<String, String>();
		m.put(req.getDescription(false),null);
		ResponseModel<Object> res = new ResponseModel<Object>(m, exception, HttpStatus.NOT_FOUND);
		LOGGER.error(ex.getMessage());
		return new ResponseEntity<Object>(res,HttpStatus.NOT_FOUND);
		
	}
}
