package com.project.product.exception;

public class UserAlreadyExistException extends Exception{
	private static final long serialVersionUID = -8126420022161100373L;

	public UserAlreadyExistException(String email) {
		super("User with email: " + email  + " already exists!");
	}
}
