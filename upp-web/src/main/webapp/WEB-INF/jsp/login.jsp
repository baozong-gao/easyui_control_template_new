<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta name="Keywords" content="幸会物业" />
    <meta name="Description" content="幸会物业管理平台" />
    <title>幸会物业</title>
    <link rel="stylesheet" type="text/css" href="resources/easyui/css/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/easyui/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="resources/easyui/css/icon.css">
    <link rel="stylesheet" type="text/css" href="resources/easyui/css/color.css">
    <link rel="stylesheet" type="text/css" href="resources/easyui/css/ul.base.css">
    <link rel="stylesheet" type="text/css" href="resources/css/easyui-edit.css">
    <link rel="stylesheet" type="text/css" href="resources/css/easyui-edit.css">
    <link rel="stylesheet" type="text/css" href="resources/css/index.css">
</head>
<body>
<div id="cp-login">
    <i class="logo"></i>
    <div class="login-type">
        <p class="login-type-title">请选择登录方式</p>
        <div class="login-type-but">
            <div><a onclick="showLogin('商户','01')"><p>商户</p></a></div>
            <div><a onclick="showLogin('代理商','00')"><p>代理商</p></a></div>
            <div><a onclick="showLogin('机构','99')"><p>机构</p></a></div>
        </div>
    </div>
    <a class="login-set-but dn" onclick="setLoginType()">切换登录角色</a>
    <form class="form-horizontal mt10" action="login" id="login_form" method="post">
    <div class="login-form-box bounceIn dn">
        <p class="logint-form-title">代理商登录</p>
        <input type="hidden" id="userType" name="userType" value="99" />
        <div style="color:red;margin: auto auto; margin-bottom: 5px; text-align: center;">${error.message}</div>
        <input type="text" name="username" placeholder="手机号／登录账号">
        <input type="password" name="password" placeholder="请输入密码">
        <div>
            <input type="text" id="j_captcha" value="" name="captcha" type="password"
                   class="form-controls" placeholder="请输入验证码" style="width: 200px;">
            <img id="captcha_img" alt="点击更换" title="点击更换"
                 src="${pageContext.request.contextPath}/image/captcha-image" class="m"  style="position: relative; top: -4px; width: 100px;">
        </div>
        <%--<div class="remberPassword" style="position:  relative;">--%>
            <%--<input name="remember"  style="margin-right: 0px; margin-left: 15px;" type="checkbox" id="j_remember" value="true">--%>
            <%--<label for="j_remember" style="top: -2px">记住密码</label>--%>
        <%--</div>--%>
        <button class="login-but" type="submit" style="margin-top: 20px;">登录</button>
        <%--<p class="login-form-tel">联系客服：021-68746534</p>--%>
    </div>
    </form>
    <p class="copyright">Copyright © 2017 聚合支付SAAS平台</p>
</div>
<script type="text/javascript" src="resources/easyui/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/easyui/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="resources/easyui/js/reset_win.js"></script>
<script type="text/javascript" src="resources/easyui/js/ulmsale/ul.enums.js"></script>
<script type="text/javascript" src="resources/easyui/js/ulmsale/ul.base.js"></script>
<script type="text/javascript" src="resources/easyui/js/layer/layer.js"></script>
<script type="text/javascript" src="resources/other/jquery.form.js"></script>
</body>
<script>
    function setLoginType () {
        $('.login-type').show()

        $('.login-set-but').hide()
        $('.login-form-box').hide()
    }
    function showLogin (title, code) {
        $('.login-type').hide()
        $("#userType").val(code);
        $('.logint-form-title').html(title + '登录')
        $('.login-set-but').show()
        $('.login-form-box').show()
    }
</script>
</html>
