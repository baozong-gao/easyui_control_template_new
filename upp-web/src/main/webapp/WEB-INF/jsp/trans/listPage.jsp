<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp"%>

<style type="text/css">
    .select {
        width: 190px!important;
        border: 1px solid #CCCCCC;
        border-radius: 2px;
    }
    .upload-but {
        display: inline-block;
        width: 54px;
        height: 32px;
        background: #F68B4D;
        border-radius: 2px;
        color: #fff;
        line-height: 32px;
        font-size: 14px;
        padding-left: 46px;
        background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAqCAYAAAD1T9h6AAAABGdBTUEAALGPC/xhBQAAAphJREFUWAntmL9rFFEQx7Mhoo2gNhqjlZWF+YEpxPqwEttUgv+EVWr/ixSClW0UBNPbiEEUtLLyBwY1CDZKDs/PN+7i8Xbm8fbu3e2eZODL2zczb+Y7+2bf3u3c3IRkMBisgecl1iaUZjJhS/LfGCvR9WwUAdFVMEx+uIjVydyyTFEj5LtfRAL5qoiv8s10z/KEgdAKELFUke9KnuxjRhER0IR8VWT7RYxBvv0iMpBvrwgyL4NR2qYiHY6KtTxmN6ctJ9Ei+BIyyDBXzMU0Fv+8FnTJwssMG6X6YVEUb8tra/iB8i44VhqPM94BV8t56vACx/vgV7nggFGxXTF5ouyBn6ASXffcKIYB/6VqcYNxyQjlqsQJ1Hmi3DWS7rqRHIMRI6pywrhqj2eBQVt32EpDq/u0UdUiQ2r/Umx9a91C/KKu9TUez3mWhOQVxdL50adjsTgtqICZlqMC2t6+nDvwukExTXyjYXUKmafHCKfEWTLdBHqxxUQvrsfE34s5hTaXp2toeMyFCXPPPZ45Wyg356R41tmatDB04g7pb+JtkNJCD9jgl2GMUeY5n4H3ELiQSOIDBVxM9D10m0YLfW9AqIlvNGzOHThHplsgpYW22YHPUWaB0duBbAUE+bJPvQJm/hQ6KiB7rzQM+F/sQN8qmofmhKVvQxfh0tcOeD+qrrVB1snpcdnTJ5UdHVGGvEKns71VEQcgLpbs6LfQI9AzWF5B94ZVTxk/GfZpqM6T5AY47STb1ovsFMZ34Izj1FX1PsQuzfNK1++Sza6yjPDaLLn/dWEntqwm66huq1YYRNVO98DvjpIWLXETR/+jGMZ18AQcgK6IuIjTenjn3Upw1kN9Hegj7Mlw4ZTm+lr9ETyj3/XQ1uQP/jfKv77J1FwAAAAASUVORK5CYII=");
        background-size: 16px;
        background-position: 24px center;
        background-repeat: no-repeat
    }
    .textbox {
        margin-top: 5px;
    }
</style>
<head>
    <title>订单列表</title>
</head>

<body>
<div class="ul-list-page">
    <!-- 搜索条件 -->
    <form id="searchForm" method="post" action="trans/search">
        <div id="searchPanel" class="easyui-panel">
            <!-- 页头 -->
            <h1 class="title clearfix" class="title clearfix">
                <span class="fl">扫码支付</span> <span class="point">·</span> <span class="fl">交易查询</span>
            </h1>
            <!-- 页头 结束-->
            <div class="lists2 clearfix" style="margin-top: 10px; margin-bottom: 10px">
                <label for="">搜索交易</label>
                <input name="merchantId" placeholder="请输入商户号">
                <label for="" style="margin-right: 21px;">订单号</label>
                <input name="orderId" placeholder="请输入订单号">
                <label for="" style="margin-right: 10px;">交易状态</label>
                <select name="orderStatus">
                    <option value="">选择交易状态</option>
                    <option value="SUCCESS">成功</option>
                    <option value="FAIL">失败</option>
                    <option value="PROCESSING">处理中</option>
                    <option value="ORDER_EXPIRE">超时未支付</option>
                    <option value="INITIAL">初始待确认</option>
                </select>
                <br>
                <label for="start" style="margin-right: 10px; margin-top: 5px;">开始时间</label>
                <input name="startDate" id="start" style="margin-top: 5px;" class="easyui-datetimebox"/>
                <label for="end" style="margin-right: 10px; margin-top: 5px;">结束时间</label>
                <input name="endDate" id="end" style="margin-top: 5px;" class="easyui-datetimebox"/>
                <a href="javascript:void(0)" style="margin-top: 5px;" onclick="searchData()" class="search">查询</a>
                <a href="javascript:void(0)" style="margin-top: 5px;" onclick="exportData('trans/export')" class="upload-but" >导出</a>
            </div>
        </div>
    </form>
    <table id="listGrid" url="trans/search" class="easyui-datagrid" toolbar="#toolbar" singleSelect="true" pagination="true" pageList="[10,20,50,100]" pagesize="10" style="text-align: center">
        <thead>
        <tr>
            <th data-options="field:'ck',width:'5%',align:'center', checkbox:'true'">全选</th>
            <th data-options="field:'merchantId',width:'14%',align:'center'">商户号</th>
            <th data-options="field:'orderId',width:'14%',align:'center'">商户订单号</th>
            <th data-options="field:'orderAmount',width:'14%',align:'center'">金额</th>
            <th data-options="field:'orderFeeAmount',width:'14%',align:'center'">手续费</th>
            <th data-options="field:'orderDate',width:'14%',align:'center'" formatter="formatDate">交易日期</th>
            <th data-options="field:'orderTime',width:'14%',align:'center'"   formatter="formatTime">交易时间</th>
            <th data-options="field:'orderFlag',width:'14%',align:'center'" formatter="fmType">交易类型</th>
            <th data-options="field:'orderStatus',width:'14%',align:'center'" formatter="fmStatus">交易状态</th>
        </tr>
        </thead>
    </table>
</div>
<script>
    function formatDate(val) {
        return val.replace(/^(\d{4})(\d{2})(\d{2})$/, '$1-$2-$3')
    }
    function formatTime(val) {
        return val.replace(/^(\d{2})(\d{2})(\d{2})$/, '$1:$2:$3')
    }
    function fmStatus(val){
        return sysEnums.transStatus[val];
    }
    function fmType(val){
        return sysEnums.transType[val];
    }
    
    function exportData(url) {
        var href = url;
        var action = $("#searchForm").attr("action");
        $("#searchForm").attr("action", href);
        $("#searchForm").submit();
        $("#searchForm").attr("action",action);
    }
</script>

</body>
</html>
