package com.cqi.controls.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cqi.controls.util.CNP;

/**
 * @author cqfb
 *
 */
public class DocumentoFederalValidator implements ConstraintValidator<DocumentoFederal, String> {

	@Override
	public void initialize(DocumentoFederal constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean retorno = false;
		if (value.length() == 11) {
			retorno = CNP.isValidCPF(value);
		} else {
			retorno = CNP.isValidCNPJ(value);
		}
		return retorno;
	}

}
