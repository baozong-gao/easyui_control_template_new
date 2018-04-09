package com.company.core.shiro.filter;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author: gaobaozong
 * @Description: shiro 退出管理器
 * @Date: Created in 2017/12/14 - 14:18
 * @Version: V1.0-SNAPSHOT
 */
@Service
public class SystemLogoutFilter extends LogoutFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        String redirectUrl = getRedirectUrl(request, response, subject);
        try {
            subject.logout();
        } catch (SessionException ise) {
            ise.printStackTrace();
        }
        issueRedirect(request, response, redirectUrl);
        //返回false表示不执行后续的过滤器，直接返回跳转到登录页面
        return false;
    }
}
