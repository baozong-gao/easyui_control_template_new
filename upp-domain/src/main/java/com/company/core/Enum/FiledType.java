package com.company.core.Enum;

import java.lang.annotation.*;

/**
 * @Author: gaobaozong
 * @Description: 字段
 * @Date: Created in 2018/3/27 - 14:11
 * @Version: V1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FiledType {

    UserTypeEnum value() default UserTypeEnum.INST;

}
