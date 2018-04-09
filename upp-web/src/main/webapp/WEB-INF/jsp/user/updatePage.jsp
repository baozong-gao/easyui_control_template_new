<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp"%>
<head>
	<title>修改用户</title>
</head>
<body>
<h1 class="title clearfix" class="title clearfix"><span class="fl">用户管理</span> <span class="point">·</span> <span class="fl">用户修改</span></h1>
<div class="formAdd">
	<form:form method="post" action="user/update" modelAttribute="rout" id="userForm">
		<form:hidden path="id"/>
		<h3 class="tit">填写用户信息<span>（*为必填项）</span></h3>
		<div class="row3 clearfix">
			<div class="list">
				<label for="instId">机构号:</label>
				<form:input class="easyui-validatebox" type="text" placeholder="请输入机构号" path="instId"  data-options="prompt:'请输入机构号.',required:true,validType:'length[1,32]'" />
				<span>*</span>
			</div>
		</div>
		<div class="row3 clearfix">
			<div class="list">
				<label for="name">名称:</label>
				<form:input class="easyui-validatebox" type="text" placeholder="请输入角色名称" path="name" data-options="required:true" />
				<span>*</span>
			</div>
		</div>
		<%--<div class="row3 clearfix">--%>
			<%--<div class="list">--%>
				<%--<label for="pwd">密码:</label>--%>
				<%--<form:input class="easyui-validatebox" type="password" placeholder="请输入密码" path="pwd" value="dfwes" editable="false" />--%>
				<%--<span>*</span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="row3 clearfix">
			<div class="list">
				<label for="email">邮箱:</label>
				<form:input class="easyui-validatebox" type="text" placeholder="请输入邮箱" path="email" data-options="required:true" />
				<span>*</span>
			</div>
		</div>
		<div class="row3 clearfix">
			<div class="list btList">
				<label for="name">类型:</label>
				<form:radiobutton id="yunyin" path="userType" value="00" checked="true" />
				<label class="bt" for="yunyin" style="width: 130px; text-align: left;">代理商操作员</label>
				<form:radiobutton id="huoban" path="userType" value="01" />
				<label class="bt" for="huoban" style="width: 130px; text-align: left;">商户操作员</label>
				<form:radiobutton id="huoban1" path="userType" value="99" />
				<label class="bt" for="huoban1" style="width: 150px; text-align: left;">机构运营操作员</label>
			</div>
		</div>
		<div class="row3 clearfix">
			<div class="list">
				<label for="remark">备注:</label>
				<form:input class="easyui-validatebox" type="text" placeholder="请输入备注" path="remark" data-options="required:true" />
				<span>*</span>
			</div>
		</div>

		<input type="hidden" id="_roles" name="roles">
		<div class="row3 clearfix">
			<div class="list">
				<label for="remark" style=" float:left; margin-top: 15px;">选择角色:</label>

				<div class="itemWalp itemRow clearfix content" style="float: left;">
					<div class="item">
						<ul class="selected" id="role_user">
							<c:forEach items="${roles}" var="role">
								<li><input  id="${role.id}" value="${role.id}" type="checkbox" ${role.auth?"checked":""}/><label style="width: auto" for="${role.id}">${role.name}</label></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="row3 clearfix">
			<div class="list btList">
				<label for="name">状态:</label>
				<form:radiobutton id="open" path="userStatus" value="NORMAL" checked="true" />
				<label class="bt" for="open" style="width: 67px">启用</label>
				<form:radiobutton id="close" path="userStatus" value="CLOSE"/>
				<label class="bt" for="close" style="width: 67px">关闭</label>
			</div>
		</div>

	</form:form>

	<a style="margin:30px 0 0 100px" class="commitForm" onclick="_save()">确认修改</a>

</div>

<script>

    function _save() {
        var str=[];
        $("#role_user").find('input:checked').each(function(){
            str.push($(this).val());
        });
        $("#_roles").val(str);
        $("#userForm").submit();
    }
    $("#userForm").submit(function (e) {
        e.preventDefault();
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