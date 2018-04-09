package com.company.core.api.provider;

import org.jboss.netty.buffer.ChannelBufferInputStream;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

/**
 * @Author: gaobaozong
 * @Description: TODO 描述类的作用
 * @Date: Created in 2018/1/5 - 15:58
 * @Version: V1.0-SNAPSHOT
 */
public class RequestFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        ChannelBufferInputStream cbis = (ChannelBufferInputStream) requestContext.getEntityStream();

    }
}
