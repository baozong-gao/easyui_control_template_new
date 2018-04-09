<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp"%>
<head>
    <title>增加权限</title>
</head>
<body>
<h1 class="title clearfix" class="title clearfix"><span class="fl">权限管理</span> <span class="point">·</span> <span class="fl">权限新增</span></h1>
<div class="formAdd">
    <form:form method="post" action="fun/add" modelAttribute="rout" id="funForm">
        <h3 class="tit">填写权限信息<span>（*为必填项）</span></h3>
        <div class="row3 clearfix">
            <div class="list">
                <label for="name">名称:</label>
                <form:input class="easyui-validatebox" type="text" placeholder="请输入权限名称" path="name"  data-options="prompt:'请输入权限姓名.',required:true,validType:'length[2,9]'" />
                <span>*</span>
            </div>
        </div>
        <div class="row3 clearfix">
            <div class="list">
                <label for="grade">等级:</label>
                <form:select path="grade" id="grade" class="easyui-validatebox easyui-combobox" >
                    <form:option value="A">一级</form:option>
                    <form:option value="B">二级</form:option>
                    <form:option value="C">三级</form:option>
                </form:select>
                <span>*</span>
            </div>
        </div>
        <div class="row3 clearfix">
            <div class="list">
                <label for="parentId">父级:</label>
                <select id="parentId" name="parentId" class="easyui-validatebox easyui-combobox" >
                    <option value="0">项级</option>
                </select>
                <span>*</span>
            </div>
        </div>
        <div class="row3 clearfix">
            <div class="list">
                <label for="uri">URL:</label>
                <form:input class="easyui-validatebox" type="text" placeholder="请输入URL" path="uri"  data-options="prompt:'请输入URL.',validType:'length[0,80]'" />
                <%--<span>*</span>--%>
            </div>
        </div>
        <div class="row3 clearfix">
            <div class="list">
                <label for="code">权限码:</label>
                <form:input class="easyui-validatebox" type="text" placeholder="请输入权限码" path="code" data-options="prompt:'请输入URL.',required:true,validType:'length[0,80]'" />
                <span>*</span>
            </div>
        </div>
        <div class="row3 clearfix">
            <div class="list">
                <label for="orderBy">排序:</label>
                <form:input class="easyui-validatebox" type="text" placeholder="请输入排序" path="orderBy"/>
                <%--<span>*</span>--%>
            </div>
        </div>
        <div class="row3 clearfix">
            <div class="list btList">
                <label for="name">状态:</label>
                <form:radiobutton id="open" path="status" value="NORMAL" checked="true" />
                <label class="bt" for="open" style="width: 67px">启用</label>
                <form:radiobutton id="close" path="status" value="CLOSE" />
                <label class="bt" for="close" style="width: 67px">关闭</label>
            </div>
        </div>
        <div class="row3 clearfix">
            <div class="list btList">
                <label for="name">类型:</label>
                <form:radiobutton id="yunyin" path="type" value="00" checked="true" />
                <label class="bt" for="yunyin" style="width: 130px; text-align: left;">代理商操作员</label>
                <form:radiobutton id="huoban" path="type" value="01" />
                <label class="bt" for="huoban" style="width: 120px; text-align: left;">商户操作员</label>
                <form:radiobutton id="huoban1" path="type" value="99" />
                <label class="bt" for="huoban1" style="width: 140px; text-align: left;">机构运营操作员</label>
            </div>
        </div>
    </form:form>
    <a style="margin:30px 0 0 100px" class="commitForm" onclick="_save()">确认添加</a>
</div>
<script>
        $("#grade").combobox({
            onChange:function(){
                var curValue = $('#grade').combobox('getValue');
                var uri = "fun/searchparent/"+curValue;
                $("#parentId").combobox("reload", uri);
            }
        });

        var option = {
            valueField: 'id',
            textField: 'name',
            onLoadSuccess: function(){
                var types = $("#parentId").combobox('getData');
                if (types.length > 0){
                    $("#parentId").combobox('select', types[0].id);  //默认选择一个
                }
            }
        }
        $("#parentId").combobox(option);

    function _save() {
        $("#funForm").submit();
    }
    $("#funForm").submit(function (e) {
        e.preventDefault();
        var validate = $("#funForm").form('validate')
        if (!validate) {
            $("#funForm").find(".validatebox-invalid:first").focus();
            return false
        }
        var options = {
            success:  showResponse,
            resetForm: true
        };
        $(this).ajaxSubmit(options);
    });
    function showResponse(responseText, statusText) {
        if(responseText.statusCode == 200){
            window.location.replace(document.referrer);
            layer.msg('成功');
        }else{
            layer.msg(responseText.message);
        }
    }
</script>
</body>
</html>
