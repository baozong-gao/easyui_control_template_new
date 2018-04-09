package com.company.core.api.provider;

import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @Author: gaobaozong
 * @Description: 统一异常处理
 * @Date: Created in 2018/1/5 - 11:26
 * @Version: V1.0-SNAPSHOT
 */
@Component
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception>{
    @Override
    public Response toResponse(Exception e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
