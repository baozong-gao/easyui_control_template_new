package com.company.core.shiro;



import com.company.core.controller.CaptchaImageCreateController;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * Created by fireWorks on 2016/2/25.
 */
public class CaptchaFormAuthenticationFilter extends BaseFormAuthenticationFilter {

    private String captchaParam = CaptchaImageCreateController.CAPTCHA_KEY;

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request,
                                              ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new CaptchaUsernamePasswordToken(username, password, rememberMe,
                host, captcha, null);
    }

}
