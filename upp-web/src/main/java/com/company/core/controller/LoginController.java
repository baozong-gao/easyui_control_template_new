package com.company.core.controller;

import com.company.core.bo.MenuBO;
import com.company.core.constant.SystemConstant;
import com.company.core.entity.SysUsrDAO;
import com.company.core.service.IUserService;
import com.company.core.service.MenuService;
import com.company.core.shiro.CaptchaUsernamePasswordToken;
import com.company.core.shiro.MonitorRealm;
import com.company.core.vo.LoginForm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MenuService menuService;
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String goToLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, HttpSession session,
                        HttpServletRequest request) {

        Map<String, String> resultMap = new HashMap<>();
        String username = loginForm.getUsername();
        String host = request.getRemoteHost();
        loginForm.setHost(host);

        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            resultMap = login(currentUser, loginForm);
        } else {//重复登录
            currentUser.logout();
            resultMap = login(currentUser, loginForm);
        }

        if ("200".equals(resultMap.get("statusCode"))) {
            com.company.core.bo.UserBO bo = new com.company.core.bo.UserBO();
            bo.setName(username);
            List<SysUsrDAO> userBO = userService.search(bo);

//            if("INITIAL".equals(userBO.get(0).getUserStatus())){
//                request.setAttribute("userId",userBO.get(0).getId());
//                return "user/changePwd";
//            }
            session.setAttribute(SystemConstant.USER_SESSION_KEY, userBO.get(0));
            return "redirect:/main";
        } else {
            request.setAttribute("error", resultMap);
            return "login";
        }
    }

    private Map<String, String> login(Subject currentUser, LoginForm loginForm) {
        Map<String, String> resultMap = new HashMap<>();
        CaptchaUsernamePasswordToken captchaUsernamePasswordToken = new CaptchaUsernamePasswordToken(
                loginForm.getUsername(), loginForm.getPassword(), true, loginForm.getHost(),
                loginForm.getCaptcha(), loginForm.getUserType());
        try {
            currentUser.login(captchaUsernamePasswordToken);
            resultMap.put("statusCode", "200");
            resultMap.put("message", "登录成功!");
        } catch (UnknownAccountException uae) {
            resultMap.put("statusCode", "300");
            resultMap.put("message", "用户账户不存在!");
        } catch (IncorrectCredentialsException ice) {
            resultMap.put("statusCode", "300");
            resultMap.put("message", "用户密码错误!");
        } catch (LockedAccountException lae) {
            resultMap.put("statusCode", "300");
            resultMap.put("message", "账户已被锁定!");
        } catch (AuthenticationException ae) {
            resultMap.put("statusCode", "300");
            resultMap.put("message", "认证异常!");
        }
        return resultMap;
    }

    @RequiresAuthentication
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String goMain(HttpSession session, HttpServletRequest request,
                         HttpServletResponse response, Map<String, Object> model) {
        MonitorRealm.ShiroUser shiroUser = (MonitorRealm.ShiroUser) SecurityUtils.getSubject()
                .getPrincipal();
        if (shiroUser == null) {
            return "redirect:/login";
        }
        BigDecimal userid = shiroUser.getId();
        List<MenuBO> menuBOList = menuService.getAllEnabledMenuByUserId(userid);
        model.put("menuBOList", menuBOList);
        model.put("loginName", shiroUser);
        model.put("loginId", userid);
        model.put("openChangeWindow", Optional.ofNullable(shiroUser.getUser()).map(_user -> _user.getUserStatus()).filter(_status -> "INITIAL".equals(_status)).map(_s -> true).orElse(false));

        session.setAttribute("user", userService.searchById(userid));
        return "main";
    }
}
