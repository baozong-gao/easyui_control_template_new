package com.company.core.Enum;

/**
 * @Author: gaobaozong
 * @Description: bean copy 别名
 * @Date: Created in 2017/12/20 - 14:43
 * @Version: V1.0-SNAPSHOT
 */
import java.lang.annotation.*;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FiledAlias {
    public String  value();
}
