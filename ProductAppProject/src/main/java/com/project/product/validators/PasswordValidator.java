package com.project.product.validators;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String>{
	//regex that matches at least 1 capital letter, 1 small letter, 1 digit, 1 symbol
	private static final String REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?=.*\\d).+$";
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		if (Objects.isNull(password)) {
			return false;
		}
		if (!password.matches(REGEX)) {
			return false;
		}
		return true;
	}
}
