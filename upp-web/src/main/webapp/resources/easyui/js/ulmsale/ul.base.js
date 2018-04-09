/**
 * Created by liming on 2016/1/28.
 */

/**
 * easyui datagrid 公共列转换函数
 */

//数字转换为 带千分位、小数点后保留两位小数
function fmMoney(value,row){
    if(isNaN(value))
        return value;
    else
        return Highcharts.numberFormat(value,2,'.',',');
}

//数字转换为 带千分位、小数点后保留两位小数
function fmNumP3(value,row){
    if(isNaN(value))
        return value;
    else
        return Highcharts.numberFormat(value,3,'.',',');
}


//数字转换为 带千分位、小数点后保留两位小数
function fmNum(value,row){
    return Highcharts.numberFormat(value,0,'.',',');
}

//数字转换为百分比,保留两位小数
function fmPercent(value) {
    return Highcharts.numberFormat(value*100,2,'.',',')+"%";
}

//判断对象是否为空对象
function isEmptyObject(obj){
    for(var key in obj)
        return false;
    return true;
}

//内部字段格式化
function fmInner(value,row,index){
    if(!this.field)
        return;
    var filed = this.field.split('.');
    var result = row[filed[0]];
    try{
        for(var i=1;i<filed.length;i++){
            result = result[filed[i]];
        }
    }catch(e){
        result = "";
    }

    return result;
}

//日期格式化函数
function fmDate(val,row){
    return $.format.date(val,'yyyy-MM-dd');
}

//查询拼接字符串
function getQueryParams(barName){
    var queryParams={};
    if(barName)
        $("#"+barName+" input,#"+barName+" select").each(function(i,n){
            if($(n).attr("name") && "" != $.trim($(n).val())){
                queryParams[$(n).attr("name")] = $.trim($(n).val());
            }
        });
    console.log(queryParams)
    return queryParams;
}

function documentSize(){
    var doc = new Object();
    var body = (document.compatMode && document.compatMode.toLowerCase() == "css1compat") ? document.documentElement
        : document.body;
    if (isNaN($(window).innerHeight()) || isNaN($(window).innerWidth())){
        doc.height = body.clientHeight;
        doc.width = body.clientWidth;
        doc.mode = "document";
    } else {
        doc.height = $(window).innerHeight();
        doc.width = $(window).innerWidth();
        doc.mode = "window";
    }
    return doc;
}


//初始化layoutPanel大小
function initLayoutPanel(layoutId){
    $(layoutId).layout('resize', {
        width:'100%',
        height:$(window).height()
    })
}

//初始化明细页面layoutPanel大小
function initDetailLayoutPanel(layoutId){
    $(layoutId).layout('resize', {
        width:'100%',
        height:parent.innerHeight-36
    })
}

//弹出全屏窗体
function windowOpenFullScreen(path, name){
    var doc = documentSize();
    var width = Math.max(Math.max(doc.width, $(window).innerWidth()), window.screen.width)-15;
    var height = Math.max(Math.max(doc.height, $(window).innerHeight()), window.screen.height)-110;
    return windowOpen(path, name, width, height, 0, 0);
}

//弹出窗体在中间
function windowOpenCenter(path, name, width, height){
    var doc = documentSize();
    var windowWidth = Math.max(Math.max(doc.width, $(window).innerWidth()), window.screen.width);
    var windowHeight = Math.max(Math.max(doc.height, $(window).innerHeight()), window.screen.height);
    var top = (windowHeight - height-50) / 2 - 20;
    var left = (windowWidth - width) / 2;
    return windowOpen(path, name, width, height, top, left);
}

//弹出窗体隐藏Title
function windowOpenFullScreenHideTitle(path, name){
    return window.open(path,name,'fullscreen=yes,directories=no,status=no,toolbar=no,scroll=no,help=no,width=800px,height=600px');
}

//弹出窗体
function windowOpen(path, name, width, height, top, left){
    return window.open(path, name,
        'height='+ height + 'px,width='+ width + 'px,top=' + top + ',left=' + left +
        ',toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no');
}

//url拼接辅助
function _getRandUrl(url) {
    return url + (url.indexOf("?") == -1 ? "?" : "&");
}

/**
 * 组合url
 */
function concatURL(url,data){
    var u = url, firstParam = false;
    if(u.indexOf('?') == -1) {
        u += '?';
        firstParam = true;
    }

    if(typeof data == 'string')
        u += decodeURIComponent(firstParam ? "" : '&') + decodeURIComponent(data);
    else
        u += decodeURIComponent(firstParam ? "" : '&') + decodeURIComponent($.param(data,true));
    return u;
}

//定义当前总共打开过罩层的数量，当为1时关闭
var maskQty=0;
/**
 * ajax异步执行
 * @param {String} url
 * @param {Object} data
 * dep:jquery,handleJSONResult()
 */
function getAsyncJSON(url,data,success,msg,error){
    // $(function(){
    //     $("body").mask(msg||"运行中...");
    //     maskQty++;
    // });
    $.ajax({
        url : _getRandUrl(url),
        data : data,
        type : 'post',
        dataType : 'json',
        complete : function(XHR, textStatus){
            $(function(){
                if(maskQty==1){
                  $("body").unmask();
                }
                maskQty--;
            });

            handleJSONResult(XHR.responseText,success,error,textStatus);
        },
        traditional : true
    });
}


/**
 * 处理后台返回的json消息
 * @param {Object} jsonText
 * @param {Object} success
 * @param {Object} failed
 * @param {Object} error
 * dep:jquery,alertMessage(),handleFieldError()
 */
function handleJSONResult(jsonText,success,error,textStatus){

    var data = {};
    try{
        data = $.parseJSON(jsonText);
    }catch(e){
        //转型失败
        $(function(){
            $('#sysMsg').dialog({
                title:'系统提示',
                width:600,
                height:400,
                cache: false,
                collapsible:false,
                minimizable:false,
                maximizable:true,
                content:jsonText,
                modal:true
            });
        });
        if(!!error)
            error(jsonText);
        return jsonText;
    }

    if(!textStatus || textStatus == "success"){
        if(success)
            success(data);
    }else{
        var errorMsg = handleFieldError(data);
        //$.messager.alert('请求失败 : ',errorMsg);
        $('#sysMsg').dialog({
            title:'系统提示',
            width:600,
            height:400,
            cache: false,
            collapsible:false,
            minimizable:false,
            maximizable:true,
            content:errorMsg,
            modal:true
        });
    }
}

/**
 * 处理错误消息
 * @param {Object} msgs
 * @param {Object} erros
 * dep:jquery
 */
function handleFieldError(erros){
    var msg = [];
    if(!!erros)
        for(var error in erros)
            msg.push('<strong>' + error+ '</strong>  :  ' + erros[error]);
    return msg.join('<br>');
}


/**
 * 将数据读入form中
 * @param {String} selector 选择器字符串
 * @param {Object} data 数据对象
 * @param {String} prefix 表格input name 前缀
 * dep:easyui
 */
function loadFormData(selector,data,prefix){
    if(!prefix)
        prefix = '';
    else
        prefix = prefix +'.'
    var obj = {};
    for(v in data){
        obj[prefix + v] = data[v];
    }
    $(selector).form('load',obj);
}

/**
 * 将数据递归读入form中
 * @param {String} selector 选择器字符串
 * @param {Object} data 数据对象
 * @param {String} prefix 表格input name 前缀
 * dep:easyui
 */
function loadFormDataRecursion(selector,data,prefix){
    if(!prefix)
        prefix = '';
    else
        prefix = prefix +'.'
    var obj = {};
    for(v in data){
        if(typeof (data[v]) == 'object') {
            var datas = data[v];
            for(i in datas){
                obj[prefix + v + '.'+ i] = datas[i];
            }
        }else {
            obj[prefix + v] = data[v];
        }
    }
    console.log(obj);
    $(selector).form('load',obj);
}
/**
 * 提交表单
 * @param {String} selector 选择器字符串
 * @param {Function} onSubmit 提交前执行
 * @param {Function} success 成功后执行
 * @param {Function} failed 失败时执行
 * @param {Function} error 未知错误时执行
 * dep:easyui
 */
function submitFormData(selector,onSubmit,success,error){
    $(".temp").remove();
    $(selector).form('submit', {
        onSubmit : function() {
            if(!!onSubmit)
                onSubmit();
            // $('body').mask("正在处理，请稍候...");
            // maskQty++;
            return true;
        },
        success : function(dataText) {

            // if(maskQty = 1)
            //     $('body').unmask();
            // maskQty--;
            handleJSONResult(dataText,success,error);
        }
    });
}

/**
 *
 * @param {String} selector 选择器字符串
 * @param {String} prefix 表格input name 前缀
 * @param {Object} rows 数据对象集合
 * dep:jquery
 */
function addRowsToForm(selector,prefix,rows){
    var form = $(selector);
    var index = 0;
    $(rows).each(function(){
        for(v in this){
            initFormValue(form,v,this[v],index,prefix,"");
        }
        index ++;
    });
    var input2 = $('<input type="hidden" />');
    input2.attr('name',prefix + 'Length');
    input2.val(rows.length);
    input2.addClass('temp');
    form.append(input2);
}

function initFormValue(form,key,value,index,prefix,xPrefix){
    if(typeof(value) == 'object'){
        xPrefix += key + '.';
        for(cKey in value){
            initFormValue(form,cKey,value[cKey],index,prefix,xPrefix)
        }
    }else{
        var input = $('<input type="hidden" />');
        var name = prefix + '['+ index +'].' + xPrefix + key;
        input.attr('name',name);
        input.val(value);
        input.addClass('temp');
        form.append(input);
    }
}

/**
 * 表单中加入隐藏字段
 * @param {Object} selector
 * @param {Object} name
 * @param {Object} value
 * dep:jquery
 */
function pushValueToForm(selector,name,value,id){
    if(!value)return;
    var form = $(selector);
    var v2 = [];
    if(typeof value != 'object'){
        v2.push(value);
    }else
        v2 = value;
    $(v2).each(function(){
        var input2 = $('<input type="hidden" />');
        input2.attr('name',name);
        input2.val(this);
        input2.addClass('temp');
        if(id)
            input2.attr('id',id);
        form.append(input2);
    });
}

/**
 * 表单中加入隐藏字段
 * @param {Object} selector
 * @param {Object} array
 * dep:jquery
 */
function pushArrayToForm(selector,prefix,array){
    if(!array)return;
    var form = $(selector);
    for(v in array){
        var input = $('<input type="hidden" />');
        var name = prefix + '['+ v +']';
        input.attr('name',name);//<input name="product.name"/>
        input.val(array[v]);//<input value="123" />
        input.addClass('temp');//<input class=""/>
        form.append(input);
    }
}

//关闭表格编辑
function dataGridEndEdit(selector){
    if($(selector).datagrid('cell'))
        $(selector).datagrid('endEdit',$(selector).datagrid('cell').index);
}

//检测返回结果是否正确
function catchDataError(data){
    if(data.resultStatus ==1)
        return true;
    layer.msg(data.resultMessage)
    return false;
}

//弹出提示信息
function popInfo(msg,width,height){
    $('#sysMsg').dialog({
        title:'系统提示',
        width:width?width:600,
        height:height?height:400,
        cache: false,
        collapsible:false,
        minimizable:false,
        maximizable:true,
        content:msg,
        modal:true
    });
}

//调整表格高度
function resizeGrid(grid,topheight,minHeight) {
    var height = $(window).height() <= minHeight ? minHeight : $(window).height();

    $(grid).datagrid('resize',{
        width: '100%',
        height: height - topheight
    });

}

//公共弹出
function popGrid(popId,popTitle,singleSelect,url,width,height){
    var href = '/basic/PopCtrl/popIndex?';
    href += 'popId=' + popId;
    href += '&singleSelect=' + singleSelect;
    href += '&url=' + url;
    $('#popWindow').window({
        width: width?width:800,
        height: height?height:400,
        collapsible:false,
        minimizable:false,
        maximizable:false,
        modal: true,
        href: href,
        title: popTitle
    });
}

//验证是否存在某个函数
function isExitsFunction(funcName) {
    try {
        if (typeof(eval(funcName)) == "function") {
            return true;
        }
    } catch(e) {}
    return false;
}


//枚举对象转换
function enumsToList(sysenum){
    var sysenumArray = [];
    var sysenum = sysEnums[sysenum];
    for(var v in sysenum){
        sysenumArray.push({value:v,name:sysenum[v]});
    }
    return sysenumArray;
}

//枚举对象转换
function enumsToListWithNull(sysenum){
    var sysenumArray = [];
    var sysenum = sysEnums[sysenum];
    sysenumArray.push({value:'',name:'----请选择----'});
    for(var v in sysenum){
        sysenumArray.push({value:v,name:sysenum[v]});
    }
    return sysenumArray;
}

//枚举对象转换
function enumsToListWithBlank(sysenum){
    var sysenumArray = [];
    var sysenum = sysEnums[sysenum];
    sysenumArray.push({value:'',name:''});
    for(var v in sysenum){
        sysenumArray.push({value:v,name:sysenum[v]});
    }
    return sysenumArray;
}

//产品类型 基础产品 套餐 成品 
function fmProductClassify(value) {
    return sysEnums.productClassify[value];
}

//商户类型 自营 社区服务 进货平台
function fmMerchantType(value) {
    return sysEnums.merchantType[value];
}

//格式化订单标记颜色
function fmMarkColor(value){
    return '<div style="background: '+ value +';width: 12px;height: 15px;"></div>';
}

//AvailableStatus状态 AVAILABLE: '启用', FREEZE: '禁用'
function fmAvailableStatus(value) {
    return sysEnums.availableStatus[value];
}
//
function  fmAddressType(value) {
    return sysEnums.addressType[value];

}

//报修分类
function fmIssueSubType(val) {
   return sysEnums.issueSubType[val];
}
//处理状态
function fmIssueStatus(val) {
   return sysEnums.issueStatus[val]; 
}//证件类型
function fmOwnerIdentityType(val) {
   return sysEnums.ownerIdentityType[val]; 
}
