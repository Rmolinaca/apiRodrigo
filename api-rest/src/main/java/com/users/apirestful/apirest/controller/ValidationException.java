package com.users.apirestful.apirest.controller;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -7597250173182681463L;

	public ValidationException(String message) {
		super(message);
	}
}