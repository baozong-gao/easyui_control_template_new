package com.company.core.Enum.validator;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.UnsupportedEncodingException;

public class StringByteSizeValidator implements ConstraintValidator<StringByteSize, String> {

	String codeing;
	int max;
	int min;

	@Override
	public void initialize(StringByteSize arg0) {
		this.codeing = arg0.coding();
		this.max = arg0.max();
		this.min = arg0.min();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(StringUtils.isBlank(value)){
			return true;
		}
		try {
			int size = value.getBytes(this.codeing).length;
			return size <= this.max && size >= min;
		} catch (UnsupportedEncodingException e) {
			return false;
		}
	}
}
