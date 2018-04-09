package com.company.core.controller;

import com.company.core.constant.SystemConstant;
import com.company.core.entity.SysUsrDAO;
import com.company.core.service.IUserService;
import com.company.core.util.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;

/**
 * Created by ligq01 on 2016/7/22.
 */
@Slf4j
@Controller
@RequestMapping("email")
public class EmailController {

    @Autowired
    IUserService userService;

    @RequestMapping("send")
    public String verify(HttpServletRequest request, HttpServletResponse response) {
        String loginName = request.getParameter("loginName");
        if (loginName == null || loginName.isEmpty()) {
            request.setAttribute("message", "用户名不能为空，请输入用户名！");
            return "login";
        }

        try {
            com.company.core.bo.UserBO form = new com.company.core.bo.UserBO();
            form.setName(loginName);
            SysUsrDAO userDO = ((List<SysUsrDAO>) userService.search(form)).get(0);
            if (userDO.getEmail() == null || userDO.getEmail().isEmpty()) {
                request.setAttribute("message", "用户无邮箱！");
                return "login";
            }
            int newPasswd = new Random().nextInt(999999);
            boolean sendsuccess = emailSend(newPasswd, userDO);
            if (sendsuccess) {
                SysUsrDAO userBO = new SysUsrDAO();
                userBO.setId(userDO.getId());
                userBO.setName(userDO.getName());
                userBO.setPwd(EncryptUtils.encryptPwd(newPasswd + ""));
                userService.update(userBO);
                request.setAttribute("message", "密码重置成功，请登录邮箱查看！");
            } else {
                request.setAttribute("message", "密码重置失败");
            }
        } catch (Exception ex) {
            request.setAttribute("message", "用户不存在，请输入正确的用户名！");
            log.error("重置密码错误", ex);
        }
        return "login";
    }

    public boolean emailSend(int newPasswd, SysUsrDAO userDO) {
        HtmlEmail email = new HtmlEmail();
        try {
            String message = "用户  " + userDO.getName() + "  你好！你的登录密码已重置为：" + newPasswd
                    + ",请及时登录系统更改密码！";
            email.setHostName(SystemConstant.EMAIL_HOST_NAME);
            email.setCharset(SystemConstant.EMAIL_CHARSET);
            email.addTo(userDO.getEmail());
            email.setFrom(SystemConstant.EMAIL_SANDER);
            email.setAuthentication(SystemConstant.EMAIL_USER_NAME,
                    SystemConstant.EMAIL_USER_PASSWORD);
            email.setSubject("控台密码重置");
            email.setMsg(message);
            email.send();
            return true;
        } catch (Exception ex) {
            log.error("发送邮件失败", ex);
            ex.printStackTrace();
            return false;
        }
    }
}
