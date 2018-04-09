package com.company.core.api.netty;

import com.company.core.constant.SystemConstant;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import javax.annotation.PreDestroy;
import javax.net.ssl.SSLContext;
import javax.ws.rs.ext.Provider;
import java.util.*;

@Component
public class NettyServer {
    @Autowired
    ApplicationContext ac;

    private final String rootResourcePath = SystemConstant.API_ROOT;
    private final int port = SystemConstant.API_PORT;
    private final int ioWorkerCount = SystemConstant.API_IO_COUNT;
    private final int executorThreadCount = SystemConstant.API_WORK_COUNT;
    private SSLContext sslContext = null;
    private final int maxRequestSize = SystemConstant.API_REQUEST_COUNT;
    private final IdleStateHandler idleStateHandler = new IdleStateHandler(new HashedWheelTimer(), 0, 0, 600);

    NettyJaxrsServer netty;

    public void start() {
        ResteasyDeployment dp = new ResteasyDeployment();
        Collection<Object> providers = ac.getBeansWithAnnotation(Provider.class).values();
        Collection<Object> controllers = ac.getBeansWithAnnotation(Controller.class).values();
        Assert.notEmpty(controllers);
        // extract providers
        if (providers != null) {
            dp.getProviders().addAll(providers);
        }
        // extract only controller annotated beans
        dp.getResources().addAll(controllers);
        Map<String, Object> channelOptions = new HashMap<String, Object>();
        channelOptions.put("reuseAddress", true);
        List<ChannelHandler> channelHandlerList = new ArrayList<ChannelHandler>();
        channelHandlerList.add(idleStateHandler);
        netty = new NettyJaxrsServer();
        netty.setChannelOptions(channelOptions);
        netty.setDeployment(dp);
        netty.setPort(port);
        netty.setRootResourcePath(rootResourcePath);
        netty.setIoWorkerCount(ioWorkerCount);
        netty.setExecutorThreadCount(executorThreadCount);
        netty.setMaxRequestSize(maxRequestSize);
        netty.setSSLContext(sslContext);
        netty.setKeepAlive(true);
        netty.setChannelHandlers(channelHandlerList);
        netty.setSecurityDomain(null);
        netty.start();
    }

    @PreDestroy
    public void cleanUp() {
        netty.stop();
    }
}
