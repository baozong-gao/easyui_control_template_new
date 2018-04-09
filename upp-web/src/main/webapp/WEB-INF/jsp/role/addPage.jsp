<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp"%>
<head>
    <title>增加角色</title>
</head>
<body>
<div class="toolbar">
    <h3 class="tit">新建角色</h3>
    <form:form action="role/add" modelAttribute="rout" method="post" id="roleform">
    <div class="list clearfix">
        <div class="item clearfix">
            <lable class="name">角色姓名：</lable>
            <form:input path="name" placeholder="请输入角色姓名" class="easyui-validatebox"  data-options="prompt:'请输入角色姓名.',required:true,validType:'length[2,18]'" />
        </div>
        <div class="item clearfix headRadio">
            <lable>角色状态：</lable>
            <form:radiobutton id="open" path="status" value="NORMAL" checked="true" />
            <label for="open">启用</label>
            <form:radiobutton id="close" path="status" value="CLOSE"/>
            <label for="close">关闭</label>
        </div>
        <div class="item clearfix">
            <lable>备注：</lable>
            <form:input path="remark" placeholder="请输入备注" class="easyui-validatebox"  data-options="prompt:'请输入备注.',required:true,validType:'length[0,85]'"/>
        </div>
        <input type="hidden" id="funcs" name="funcs"/>
    </div>
    </form:form>

    <div class="content" id="role_func">
        <h3 class="tit">选择角色权限</h3>
        <c:forEach var="role" items="${roles}">
            <div class="list clearfix">
                <div class="checkAll">
                    <input id="checkAll${role.id}" type="checkbox" onclick="selectOneMemu(this)" value="${role.id}"
                           ${role.auth?"checked":""}/>
                    <label for="checkAll${role.id}">${role.name}</label>
                </div>
                <div class="itemWalp clearfix">
                    <c:forEach var="subrole" items="${role.subFunc}">
                        <div class="item">
                            <h5>
                                <input id="checkAllItem${subrole.id}" onclick="selectTwoMemu('${role.id}',this)"
                                       value="${subrole.id}" type="checkbox" ${subrole.auth?"checked":""}/>
                                <label for="checkAllItem${subrole.id}">${subrole.name}</label>
                            </h5>
                            <ul class="selected">
                                <c:forEach var="grdrole" items="${subrole.subFunc}">
                                    <li><input onclick="selectThreeMenu('${role.id}',this)" id="checkItem${grdrole.id}"
                                               value="${grdrole.id}"
                                               type="checkbox" ${grdrole.auth?"checked":""}/>
                                        <label for="checkItem${grdrole.id}">${grdrole.name}</label></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>
    <div>
        <a class="commitForm" onclick="pushRole()">确认添加</a>
    </div>


    <script>

        function pushRole() {
            $("#roleform").submit();
        }

        $("#roleform").submit(function (e) {
            //验证
            e.preventDefault();
            var validate = $("#roleform").form('validate')
            if (!validate) {
                $("#roleform").find(".validatebox-invalid:first").focus();
                return false
            }
            var str=[];
            $("#role_func").find('input:checked').each(function(){
                str.push($(this).val());
            });
            $("#funcs").val(str);
            if(str == '') {
                layer.msg('请选择权限');
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

        //单击一级权限
        function selectOneMemu(self) {
            var selectValue = [];
            var parentDom = $(self).parents('div.list.clearfix')
            var parentDomWalp = $(self).parents('div.content')
            if (self.checked) {
                parentDom.find('input:checkbox').prop("checked", true);
                var arrDom = parentDomWalp.find('input:checked')
                for (i = 0; i < arrDom.length; i++) {
                    selectValue.push($(arrDom[i]).val())
                }
                console.log(selectValue)
            } else {
                selectValue = [];
                parentDom.find('input:checkbox').prop("checked", false);
                var arrDom = parentDomWalp.find('input:checked')
                for (i = 0; i < arrDom.length; i++) {
                    selectValue.push($(arrDom[i]).val())
                }
                console.log(selectValue)
            }
        }

        //单击二级权限
        function selectTwoMemu(oneId, self) {
            var selectValue = [];
            //计算二级菜单的长度
            var twoMenuLenght = $(self).parents('.itemWalp').find('.item h5').length
            //计算二级菜单被选中的长度
            var twoMenuCheckedLenght = $(self).parents('.itemWalp').find('.item h5 input:checked').length
            var parentDom = $(self).parents('div.list.clearfix')
            var parentDomWalp = $(self).parents('div.content')
            if (self.checked) {
                $(self).parents('.item').find('input:checkbox').prop("checked", true);
                $(self).parents('.list').find('#checkAll' + oneId).prop("checked", true)

                var arrDom = parentDomWalp.find('input:checked')
                for (i = 0; i < arrDom.length; i++) {
                    selectValue.push($(arrDom[i]).val())
                }
                console.log(selectValue)

            } else {
                $(self).parents('.item').find('input:checkbox').prop("checked", false);
                if (twoMenuCheckedLenght == 0) {
                    $(self).parents('.list').find('#checkAll' + oneId).prop("checked", false)
                } else {
                    $(self).parents('.list').find('#checkAll' + oneId).prop("checked", true)
                }
                var arrDom = parentDomWalp.find('input:checked')
                for (i = 0; i < arrDom.length; i++) {
                    selectValue.push($(arrDom[i]).val())
                }
                console.log(selectValue)
            }
        }

        //单击三级权限
        function selectThreeMenu(oneId, self) {

            var parentDom = $(self).parents('div.list.clearfix')
            var parentDomWalp = $(self).parents('div.content')
            var selectValue = [];
            if (self.checked) {
                //$('#checkAllItem'+twoId).prop("checked", true);
                $(self).parents('.item').find('h5 input').prop("checked", true)
                $(self).parents('.list').find('#checkAll' + oneId).prop("checked", true)
                var arrDom = parentDomWalp.find('input:checked')
                for (i = 0; i < arrDom.length; i++) {
                    selectValue.push($(arrDom[i]).val())
                }
                console.log(selectValue)

            } else {
                var check3Lentht = $(self).parents('ul').find('li input:checked').length;
                if (check3Lentht == 0) {
                    $(self).parents('.item').find('h5 input').prop("checked", false)
                } else {
                    $(self).parents('.item').find('h5 input').prop("checked", true)
                }
                var check2Lentht = $(self).parents('.itemWalp').find('.item h5 input:checked').length;
                if (check2Lentht == 0) {
                    $('#checkAll' + oneId).prop("checked", false);
                } else {
                    $('#checkAll' + oneId).prop("checked", true);
                }
                var arrDom = parentDomWalp.find('input:checked')
                for (i = 0; i < arrDom.length; i++) {
                    selectValue.push($(arrDom[i]).val())
                }
                console.log(selectValue)
            }

        }


    </script>
</body>
</html>
