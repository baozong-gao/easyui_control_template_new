package com.company.core.api.provider;

import com.company.core.entity.SysFunctionDAO;
import com.company.core.entity.SysUsrDAO;
import com.company.core.service.IUserService;
import com.company.core.service.UserAuthService;
import com.company.core.util.AuthCacheUtil;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: gaobaozong
 * @Description: 权限 验证
 * @Date: Created in 2018/1/5 - 17:08
 * @Version: V1.0-SNAPSHOT
 */
@Component
@Provider
public class LoginFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_PROPERTY = "UserToken";
    private static final ServerResponse ACCESS_DENIED = new ServerResponse("没有权限", 401, new Headers<Object>());
    ;
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("禁止访问", 403, new Headers<Object>());
    ;

    @Autowired
    private AuthCacheUtil cacheUtil;
    @Autowired
    private IUserService userService;
    @Autowired
    UserAuthService userAuthService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        //获取方法
        ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
        Method method = methodInvoker.getMethod();
        //通过所有
        if (!method.isAnnotationPresent(PermitAll.class)) {
            //禁止所有
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }

            //登入验证
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
            if (authorization == null || authorization.isEmpty()) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }
            final String userToken = authorization.get(0);
            String userId = cacheUtil.get("api_" + userToken);
            SysUsrDAO user = (SysUsrDAO) userService.searchById(new BigDecimal(userId));
            if (user == null) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }


            //权限验证
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

                List<SysFunctionDAO> functions = userAuthService.getAuthByUserId(user.getId());
                List<String> userAuth = Optional.ofNullable(functions).map(funcs -> funcs.stream().filter(_f -> "O".equals(_f.getStatus())).map(func -> func.getCode()).collect(Collectors.toList())).orElse(null);
                if (!userAuth.contains(rolesSet)) {
                    requestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            }
        }
    }
}
