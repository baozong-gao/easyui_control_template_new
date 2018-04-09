package com.company.core.api;

import com.company.core.api.netty.NettyServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        Assert.notNull(ac);
        NettyServer netty = ac.getBean(NettyServer.class);
        netty.start();
    }
}