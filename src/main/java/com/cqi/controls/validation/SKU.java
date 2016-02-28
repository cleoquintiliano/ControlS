package com.cqi.controls.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "([a-zA-Z]{2}\\d{4,18})?")
public @interface SKU {
	
	//Todos os atributos de um tipo de anotação devem ser declarados
	
	//Define mensagem
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "{com.cqi.constraints.SKU.message}";
	//Validação será aplicada sobre o grupo padrão
	Class<?>[] groups() default {};
	//Atributo Payload define que a validação pode ser utilizada por clientes Bean Validation
	Class<? extends Payload>[] payload() default {};
	
}
