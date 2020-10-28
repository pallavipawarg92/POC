package com.neosofttech.poc.exception;

public class RecordAlreadyExistException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordAlreadyExistException(String err) {
		super(err);
	}
}
