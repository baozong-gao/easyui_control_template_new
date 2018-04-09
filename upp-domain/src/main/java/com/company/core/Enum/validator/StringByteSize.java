package com.company.core.Enum.validator;

import javax.validation.Constraint;  
import javax.validation.Payload;  
import java.lang.annotation.Documented;  
import java.lang.annotation.Retention;  
import java.lang.annotation.Target;  
import static java.lang.annotation.ElementType.*;  
import static java.lang.annotation.RetentionPolicy.*;

/**
 * 字符串转字节数组长度验证
 * @author gbz
 *
 */

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })  
@Retention(RUNTIME)  
@Constraint(validatedBy = StringByteSizeValidator.class)  
@Documented  
public @interface StringByteSize {
	
	String message() default "{stringbytesize.word}";  
	
	String coding() default "UTF-8";
	
	int max() default Integer.MAX_VALUE;

	int min() default 1;
	

	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};  


	
}
