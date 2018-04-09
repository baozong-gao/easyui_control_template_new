<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<body class="easyui-layout">

	<!--header-->
	<div class="north">
		<a class="logo" href=""><img src="resources/easyui/image/logo@2x.png" alt=""></a>
		<div style="width: 30px; margin:0 10px; color: #333; float: right;"><img style="width: 20px; height: 20px; margin-top: 20px;" src="resources/easyui/image/new@2x.png" alt=""></div>
		<div id="dd" href="javascript:void(0)" class="userTool">${loginName}
			<div class="toolWalp">
				<div class="arrow"></div>
				<div class="tool">
					<ul>
						<li><a href="logout" >退出登录</a></li>
						<li onclick="modifyPassword()">修改密码</li>
					</ul>
				</div>
			</div>
		</div>

	</div>

	<div id="modifyPassword">
		<form class="modifyWin" id="changePwd" action="user/changepwd" method="post">
			<input name="userid" type="hidden"  value="${loginId}"/>
			<div class="row clearfix">
				<label for="oldpassword">原密码：</label>
				<input id="oldpassword" type="password" placeholder="请输入原密码" name="oldpassword">
			</div>
			<div class="row clearfix">
				<label for="password">新密码：</label>
				<input id="password" type="password" placeholder="请输入新密码" name="password">
			</div>
			<div class="row clearfix">
				<label for="surePassword">确认密码：</label>
				<input id="surePassword" type="password" placeholder="请输入确认密码" name="surePassword">
			</div>
			<div class="bot clearfix">
				<a href="javascript:;" onclick="_save()">确定</a>
				<a href="javascript:;" onclick="javascript:layer.closeAll();">取消</a>
			</div>
		</form>
	</div>

	<div class="west">
		<ul class="ul1">
			<c:forEach items="${menuBOList}" var="m">
				<li class="li1 arrow" id="${m.funcPriority}" >
					<a class="active1 a1" onclick="menuClick(false,false,this,'menu1')" href="javascript:;">
						<img class="img1" src="${m.logo}" alt="">${m.funcName}
					</a>
					<ul class="ul2">
						<c:forEach items="${m.childMenuBOList}" var="sm">
							<li class="li2"><a onclick="menuClick('${m.funcName}','${sm.funcUrl}',this,'menu2')" class="active2 a2" href="javascript:;">
								${sm.funcName}</a></li>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>
		</ul>
	</div>

	<!--center-->
	<div class="center">
		<div id="tabsMain" class="easyui-tabs" >
			<%--<a href="./user/listPage.jsp"></a>--%>
		</div>
	</div>
<script>

	$(function(){
		tabResize();
		$(function(){
            addTab('首页','home',true);
		})

		$(window).resize(function(){
			tabResize();
			$('iframe[name="xTabIframe"]').each(function(){
				$(this).height(caculateFrameHeight());
			})
		});

        $(".north .userTool").hover(function () {
            $(".toolWalp").show();
            $(".toolWalp").show();
        },function () {
            $(".toolWalp").hide();
            $(".toolWalp").hide();
        })

	})
	function tabResize(){
		$('#tabsMain').tabs('resize',{
			width: $(window).width()-230,
			height: $(window).height() - 50
		});
	}

	//点击菜单
    function menuClick(menuName,url,self,menu){
        console.log(menu)
        if(menu == 'meun1'){
            $('.ul1 .li1 a ').removeClass('active1');
            $(self).addClass('active1');
        }else {
            $('.ul1 .li1 .ul2 .li2 a ').removeClass('active2');
            $(self).addClass('active2');

        }
//		$('.ul1 .li1 a ').removeClass('active1');
//		$(self).addClass('active1');
        if(menuName === false){
            $(self).siblings(".ul2").stop(true).slideToggle();
        }else {
            addTab(menuName,url);
        }
    }

    //点击菜单
        function menuClick(menuName,url,self,menu){
            if(menu == 'meun1'){
                $('.ul1 .li1 a ').removeClass('active1');
                $(self).addClass('active1');
            }else {
                $('.ul1 .li1 .ul2 .li2 a ').removeClass('active2');
                $(self).addClass('active2');
            }
            if(menuName === false){
                $(self).siblings(".ul2").slideToggle();
            }else {
                addTab(menuName,url);
            }
        }

	function addTab(title, url,closable){
		if ($('#tabsMain').tabs('exists', title)){
			$('#tabsMain').tabs('close', title);
		}
		var contentHeight = caculateFrameHeight();
		var content = '<iframe name="xTabIframe" scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;height:'+ contentHeight +'px;"></iframe>';
		$('#tabsMain').tabs('add',{
			title:title,
			content:content,
			closable:closable==true?false:true
		});
	}

	function caculateFrameHeight(){
		return $('#tabsMain').tabs('options').height - $('#tabsMain').tabs('options').tabHeight - 9;
	}

    //修改密码
    function modifyPassword() {
        layer.open({
            type: 1,
            title: '修改密码',
            area: ['400px', '310px'],
            fixed: false, //不固定
            maxmin: true,
            content: $('#modifyPassword')
        });
    }

    var isOpen = ${openChangeWindow};
    if (isOpen) {
        modifyPassword()
    }

    function _save() {
	    if($('#surePassword').val() != $('#password').val()){
            layer.msg('请保持两次输入密码一致！');
            return false
		}
        $("#changePwd").submit();
    }
    $("#changePwd").submit(function (e) {
        e.preventDefault();
        var options = {
            success:  showResponse,
            resetForm: true
        };
        $(this).ajaxSubmit(options);
    });
    function showResponse(responseText, statusText) {
        if(responseText.statusCode == 200){
            layer.closeAll();
            layer.msg('成功');
        }else{
            layer.msg(responseText.message);
        }
    }
</script>
</body>
</html>
