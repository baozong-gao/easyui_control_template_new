package com.company.core.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by fireWorks on 2016/2/25.
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
    /** 描述 */
    private static final long serialVersionUID = -3178260335127476542L;

    private String captcha;

    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public CaptchaUsernamePasswordToken() {
        super();
    }

    public CaptchaUsernamePasswordToken(String username, String password,
                                        boolean rememberMe, String host, String captcha, String userType) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
        this.userType = userType;
    }
}
