package com.company.core.util;

/**
 * @Author: gaobaozong
 * @Description: 不参加字段
 * @Date: Created in 2017/11/21 - 13:48
 * @Version: V1.0-SNAPSHOT
 */

import java.lang.annotation.*;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface NotSearchField {
}
