package com.project.product.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = { PasswordValidator.class})
public @interface PasswordConstraint {
	String message() default "Password must not be null and contain at least 1 capital letter, 1 small letter, 1 digit, 1 symbol";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
