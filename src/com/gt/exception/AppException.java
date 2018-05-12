package com.gt.exception;


public class AppException extends Exception {
	private static final long serialVersionUID = 1L;

	public AppException(Exception e) {
		super(e);
	}

}
