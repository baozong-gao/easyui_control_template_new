package com.company.core.api.util;

/**
 * @Author: gaobaozong
 * @Description: api 异常处理
 * @Date: Created in 2018/1/5 - 11:27
 * @Version: V1.0-SNAPSHOT
 */
public class ApiException extends Exception{
    public ApiException(String msg){
        super(msg);
    }
}
