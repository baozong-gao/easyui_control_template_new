$(function(){
    var existsListPanel = $('#searchPanel').length>0;
    var searchPanelHeight = existsListPanel?parseInt($('#searchPanel').panel('panel').css("height")):0;
    var diffHeight = 10;
    var diffWidth = 0;

    //浏览器窗口变化调grid高度
    baseResizeGridList();
    $(window).resize(function(){
        if(existsListPanel){
            $('#searchPanel').panel('resize',{
                width: $(window).width()-diffWidth,
                height: searchPanelHeight + 20
            });
        }
        baseResizeGridList();

        $('.datagrid-btable').panel('resize',{
            width: $(window).width()-20
        });
    });

    function baseResizeGridList(){
        $('#listGrid').datagrid('resize',{
            width: $(window).width(),
            height: $(window).height() - searchPanelHeight
        });
    }

});

//弹出加载层
function load() {
    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("<img style='width: 30px; height:30px;' src='../../resources/easyui/image/loading.png'>").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
}
//取消加载层
function disLoad() {
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}

//查询
function searchData() {
    if ($("#searchForm").form("validate")) {
        var queryList = getQueryParams("searchPanel");
        $("#listGrid").datagrid("load", queryList);
    }
}
