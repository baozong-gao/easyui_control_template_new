<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0-SNAPSHOT,maximum-scale=1.0-SNAPSHOT,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta name="Keywords" content="聚合·支付"/>
    <meta name="Description" content="聚合·支付管理平台"/>
    <title>聚合·支付</title>
    <base href="<%=basePath%>">
    <link rel="icon" href="resources/image/upp.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="resources/image/upp.ico" type="image/x-icon" />
    <link rel="bookmark" href="resources/image/upp.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="resources/easyui/css/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/easyui/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="resources/easyui/css/icon.css">
    <link rel="stylesheet" type="text/css" href="resources/easyui/css/color.css">
    <link rel="stylesheet" type="text/css" href="resources/easyui/css/ul.base.css">
    <link rel="stylesheet" type="text/css" href="resources/css/easyui-edit.css">
    <script type="text/javascript" src="resources/easyui/js/jquery.min.js"></script>
    <script type="text/javascript" src="resources/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="resources/easyui/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="resources/easyui/js/reset_win.js"></script>
    <script type="text/javascript" src="resources/easyui/js/ulmsale/ul.enums.js"></script>
    <script type="text/javascript" src="resources/easyui/js/ulmsale/ul.base.js"></script>
    <script type="text/javascript" src="resources/easyui/js/layer/layer.js"></script>
    <script type="text/javascript" src="resources/other/jquery.form.js"></script>


</head>
