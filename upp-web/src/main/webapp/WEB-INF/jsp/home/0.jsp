<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp"%>

<html>
<head>
    <style type="text/css">
        *{margin: 0;}
        .index-list{width:100%;height:256px;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;margin-top:20px}.index-list li{position:relative;width:400px;height:256px;border:1px solid #DDDDDD;margin-left:40px;line-height:30px;-webkit-transition:all .3s ease;transition:all .3s ease;overflow:hidden}.index-list li:hover{box-shadow:0 15px 30px rgba(0,0,0,0.2);-webkit-transform:translate3d(0, -3px, 0);transform:translate3d(0, -3px, 0)}.index-list li .index-list-title{font-size:30px;color:#2476E9;text-align:center;padding-top:70px;font-weight:500}.index-list li .index-list-subtitle{font-size:20px;padding-top:10px;color:#333333;font-weight:500;text-align:center}.index-list li .index-list-bttom{position:absolute;width:100%;height:59px;bottom:0;line-height:59px;border-top:1px solid #DDDDDD;font-size:14px;color:#333333;padding-left:15px;font-weight:500}.index-list li .index-list-bttom span{font-size:18px;color:#2476E9}
    </style>
<title>首页</title>
</head>
<body>
<div class="ul-list-page">
    <form id="searchForm" method="post" action="trans/search">
    <div id="searchPanel" class="easyui-panel">
        <h1 class="title clearfix" class="title clearfix">
            <span class="fl">商户控台</span> <span class="point">·</span> <span class="fl">首页</span>
        </h1>
    </div>
    </form>
    <ul class="index-list">
        <c:if test="${userType != 01}">
        <li>
            <p class="index-list-title">${merSize}（个）</p>
            <p class="index-list-subtitle">今日商户数</p>
            <div class="index-list-bttom">
                <p>总商户数：<span>${merAllSize}（个）</span></p>
            </div>
        </li>
        </c:if>
        <li>
            <p class="index-list-title">￥${transSum}</p>
            <p class="index-list-subtitle">今日扫码成功交易额</p>
            <div class="index-list-bttom">
                <p>今日成功交易数：<span>${transSize}（条）</span></p>
            </div>
        </li>
    </ul>
</div>
</body>
</html>