package com.company.core.sysexception;

/**
 * @Author: gaobaozong
 * @Description: 数据持久层异常
 * @Date: Created in 2017/12/15 - 11:31
 * @Version: V1.0-SNAPSHOT
 */
public class PersistentException extends  Exception{

    public PersistentException(String message){
        super(message);
    }
}
