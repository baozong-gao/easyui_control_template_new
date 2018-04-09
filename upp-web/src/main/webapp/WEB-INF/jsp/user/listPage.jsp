<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp"%>
<head>
    <title>用户列表</title>
</head>
<body>
<div class="ul-list-page">

    <!-- 搜索条件 -->
    <form id="searchForm" method="post">
        <div id="searchPanel" class="easyui-panel">
            <h1 class="title clearfix" class="title clearfix"><span class="fl">用户管理</span> <span class="point">·</span> <span class="fl">列表</span></h1>
            <div class="lists">
                <label for="">搜索条件</label>
                <input class="w90" name="name" placeholder="用户名称">
                <input class="w120" name="email" placeholder="邮箱">
                <label style="width: 40px;" for="userStatus">状态：</label>
                <select class="w90  easyui-combobox" name="userStatus" id="userStatus">
                    <option value="">请选择</option>
                    <option value="NORMAL">启用</option>
                    <option value="CLOSE">禁用</option>
                </select>
                <label style="width: 40px;" for="_type">类型：</label>
                <select class="w90 easyui-combobox" name="userType" id="_type">
                    <option value="">请选择</option>
                    <option value="00">代理商操作员</option>
                    <option value="01">商户操作员</option>
                    <option value="99">机构运营操作员</option>
                </select>
                <a href="javascript:void(0)" onclick="searchData()" class="search">查询</a>
            </div>
        </div>
    </form>

    <!-- 表操作 -->
    <div id="toolbar">
        <shiro:hasPermission name="user:add">
            <a href="user/addpage" class="add">新建</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="user:up">
            <a href="javascript:void(0)" onclick="edit()" class="edit">修改</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="user:del">
            <a href="javascript:void(0)" onclick="del()" class="delete">删除</a>
        </shiro:hasPermission>
    </div>

    <!-- 显示表数据 单元格默认居左，align:'center' 居中，align:'right' 居右 -->
    <table id="listGrid" url="user/search" class="easyui-datagrid" toolbar="#toolbar" singleSelect="false"
           pagination="true" pageList="[10,20,50,100]" pagesize="10" style="text-align: center">
        <thead>
        <tr>
            <th data-options="field:'ck',width:'5%',align:'center', checkbox:'true'">全选</th>
            <th data-options="field:'id',width:'20%',align:'center'">ID</th>
            <th data-options="field:'name',width:'20%',align:'center'">用户名</th>
            <th data-options="field:'email',width:'20%',align:'center'">用户邮箱</th>
            <th data-options="field:'remark',width:'20%',align:'center'">备注</th>
            <th data-options="field:'userStatus',width:'10%',align:'center'" formatter="fmStatus">状态</th>
            <th data-options="field:'userType',width:'10%',align:'center'" formatter="fmType">类型</th>
        </tr>
        </thead>
    </table>


</div>

<script>

    $(function(){
        //双击编辑 formatter="fmFormatDateTime"
        $('#listGrid').datagrid({
            onDblClickRow: function(index,row){
                window.location.href="user/updatepage/"+row.id;
            }
        });
    })

    //查询
    function searchData() {
        if ($("#searchForm").form("validate")) {
            var queryList = getQueryParams("searchPanel");
            $("#listGrid").datagrid("load", queryList);
        }
    }

    //新增 - 添加数据
    function btnAdd() {
        $('#fromAdd').form('submit', {
            onSubmit: function(){
            },
            success:function(){
                layer.closeAll();
                $('#listGrid').datagrid('reload');
            }
        });
    }

    //编辑  双击编辑数据-保存修改
    function btnSave() {
        $('#fromData').form('submit', {
            onSubmit: function(params){
                //可以对提交参数做操作
            },
            success: function(data){
                var dJson = JSON.parse(data)
                layer.closeAll();
                if(dJson.statusCode == '200'){
                    $('#listGrid').datagrid('reload');
                }else{
                    layer.msg(dJson.message, {icon: 5});
                }
            },
            onLoadError:function (data) {
                layer.msg(data.message, {icon: 5});
            }
        });

        $('#ff').form('submit', {
            success: function(data){
                var data = eval('(' + data + ')'); // change the JSON string to javascript object
                if (data.success){
                    alert(data.message)
                }
            }
        });

    }

    //编辑
    function edit(){
        var rows = $('#listGrid').datagrid('getSelections');
        console.log(rows.length)
        if(rows.length>1){
            layer.msg('请单击行选中数据')
            return false;
        }
        var row = $('#listGrid').datagrid('getSelected');
        if(row === null){
            layer.msg('请单击行选中数据')
            return false;
        }
        window.location.replace('user/updatepage/'+row.id)
    }

    //删除  点击单行数据-点删除按钮删除
    function del(){
        var row = $('#listGrid').datagrid('getSelected');
        console.log(row)
        if(row === null){
            layer.msg('请单击行选中数据')
            return false;
        }
        layer.msg('你确定要删除吗?', {
            time: 0
            ,btn: ['确定', '取消']
            ,yes: function(index){
                layer.close(index);
                $.ajax({
                    url : 'user/delete/'+row.id,
                    type : 'post',
                    dataType : 'json',
                    success : function(){
                        $('#listGrid').datagrid('reload');
                    },
                    traditional : true
                });
            }
        });
    }

    //状态格式化
    function fmStatus(val) {
        return sysEnums.roleStatusType[val];
    }

    function fmType(val) {
        return sysEnums.userType[val];
    }
</script>

</body>
</html>