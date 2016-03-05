package com.cqi.controls.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * @author cqfb
 *
 */

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DocumentoFederalValidator.class})
public @interface DocumentoFederal {
	
	String message() default "{com.cqi.constraint.validation.DocumentoFederal.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
