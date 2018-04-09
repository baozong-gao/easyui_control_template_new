<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp"%>
<head>
    <title>角色列表</title>
</head>
<body>
<div class="ul-list-page">

    <!-- 搜索条件 -->
    <form id="searchForm" method="post" action="role/search">
        <div id="searchPanel" class="easyui-panel">
            <h1 class="title clearfix" class="title clearfix"><span class="fl">角色管理</span> <span class="point">·</span> <span class="fl">列表</span></h1>
            <div class="lists clearfix">
                <label for="">搜索角色</label>
                <input class="w120" name="id" placeholder="ID">
                <input class="w120" name="name" placeholder="名称">
                <label style="width: 40px;">状态：</label>
                <select class="w120 easyui-combobox" name="status">
                    <option value="">请选择</option>
                    <option value="NORMAL">启用</option>
                    <option value="CLOSE">禁用</option>
                </select>
                <a href="javascript:void(0)" onclick="searchData()" class="search">查询</a>
            </div>
        </div>
    </form>

    <!-- 表操作 -->
    <div id="toolbar">
        <shiro:hasPermission name="role:add">
            <a href="role/addpage" class="add">新建</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="role:up">
            <a href="javascript:void(0)" onclick="edit()" class="edit">修改</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="role:del">
            <a href="javascript:void(0)" onclick="del()" class="delete">删除</a>
        </shiro:hasPermission>
    </div>

    <!-- 显示表数据 单元格默认居左，align:'center' 居中，align:'right' 居右 -->
    <table id="listGrid" url="role/search" class="easyui-datagrid" toolbar="#toolbar" singleSelect="true"
           pagination="true" pageList="[10,20,50,100]" pagesize="10" style="text-align: center">
        <thead>
        <tr>
            <th data-options="field:'ck',width:'5%',align:'center', checkbox:'true'">全选</th>
            <th data-options="field:'id',width:'15%',align:'center'">ID</th>
            <th data-options="field:'name',width:'35%',align:'center'">名称</th>
            <th data-options="field:'remark',width:'30%',align:'center'">备注</th>
            <th data-options="field:'status',width:'15%',align:'center'" formatter="fmStatus">状态</th>
        </tr>
        </thead>
    </table>


</div>

<script>

    $(function(){
        //双击编辑 formatter="fmFormatDateTime"
        $('#listGrid').datagrid({
            onDblClickRow: function(index,row){
                window.location.href="role/updatepage/"+row.id;
            }
        });
    })

    //加载
    function btnEnable() {
        //加载
        load()
        //删除
        //disLoad()
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
                var data = eval('(' + data + ')');
                if (data.success){
                    alert(data.message)
                }
            }
        });

    }

    //查询
    function searchData() {
        if ($("#searchForm").form("validate")) {
            var queryList = getQueryParams("searchPanel");
            $("#listGrid").datagrid("load", queryList);
        }
    }

    //编辑
    function edit(){
        var row = $('#listGrid').datagrid('getSelected');
        if(row === null){
            layer.msg('请单击行选中数据')
            return false;
        }
        window.location.replace('role/updatepage/'+row.id)
    }

    //删除  点击单行数据-点删除按钮删除
    function del(){
        var row = $('#listGrid').datagrid('getSelected');
        console.log(row)
        if(row === null){
            layer.msg('请单击行选中数据')
            return false;
        }
        layer.msg('你确定要删除吗？', {
            time: 0
            ,btn: ['确定', '取消']
            ,yes: function(index){
                layer.close(index);
                $.ajax({
                    url : 'role/delete/'+row.id,
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

</script>


</body>
</html>
