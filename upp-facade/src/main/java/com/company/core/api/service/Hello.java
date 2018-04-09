package com.company.core.api.service;

import org.springframework.stereotype.Controller;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * @Author: gaobaozong
 * @Description: TODO 描述类的作用
 * @Date: Created in 2018/1/4 - 16:39
 * @Version: V1.0-SNAPSHOT
 */
@Controller
@Path("hello")
public class Hello {

    @PermitAll
    @GET
    @Path("world/{id}")
    @Produces("application/json")
    @Encoded
    public Response helloworld(@PathParam("id") String id, @Context HttpHeaders headers) throws Exception {
         return Response.status(200).entity("hello word "+id).build();
    }

}
