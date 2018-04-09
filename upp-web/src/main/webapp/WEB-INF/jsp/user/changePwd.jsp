<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp" %>
<head>
  <title>修改密码</title>
</head>
<body>
<style>
  .row2 {
    width: 700px !important
  }

  .w100 {
    width: 100px !important
  }

  #_easyui_textbox_input2 {
    width: 160px !important;
  }

  #statusSelect .textbox.combo {
    width: 160px !important;
  }

  #statusSelect #_easyui_textbox_input1 {
    padding-left: 10px;
  }

  .fold-box {
    position: relative;
    min-height: 30px;
    overflow: hidden;
    border-top: 2px dashed #DDDDDD;
    margin-top: 20px;
  }

  .fold-box-close {
    height: 30px !important;
  }

  .fold-but {
    position: absolute;
    width: 46px;
    height: 20px;
    line-height: 20px;
    top: 8.5px;
    right: 48px;
    font-size: 14px;
    color: #2476E9;
    border: 0;
  }

  .open {
    background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAOCAYAAAA1+Nx+AAAABGdBTUEAALGPC/xhBQAAAbBJREFUOBGtkktOAkEQhqsaIxdwcOEWxrP4DD5i1EgkGj2CwAlGXLoyMRqMCW4QfHsWHlsXMCdAocuqYVoGAyhKJ5OaVP31/V3djdFTCuObm1cKr8qO9QBjWLMZd1lr2qUZaxujR/USM+OI8E6g1mtZ6/E/HtGUu4SgC0QwyZw7NYFwhgBNSUghlnEX/2oQhAvTZwPYqcYcAd15JjwJKLVWdaynUYy8jWl9axgIGK9kI69s1FnfBQBqtZq1nk19WIyl3AUAXTTw4AaVaZQdKwhtsPOHCKWh02gU/WMPnHuFEZz+y0Day9mpewDaRMSWMbEzjfn+aD5ar2Z2ji3p7TC6HV9H1E0BRI8a6/yqboiI7wmaGMLVihN5CWoETm0qEkDY39BW7SRSCGrkv2cCUxQhG+wAYFsAApKHYOreo/DhohFtP7jo+05gQHy+2/y6roFIeZMgrkiNJyuJMSBqvrMEP4a86fkehxqI2M7UE1pjzphIzsCVomTFmb6W3KD1o4E0xtKNJGm4ZLSvR0IFe9XjSG4Q2OR/ZSBivvh9Dud+4wGf+YX/P75gp91D+UYhfgKBZc4iSgTFSQAAAABJRU5ErkJggg==');
    background-size: 12px;
    background-position: right center;
    background-repeat: no-repeat;
  }

  .close {
    background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAOCAYAAAA1+Nx+AAAABGdBTUEAALGPC/xhBQAAAcRJREFUOBGlks9O20AQxmfWFy5wiRK/gBPyCJSeKekzRIqUFrUSqFWR2tgpUm9t4oAEooUDahUpLwH03j+PkCZ5Acfi0ubqnc4sWoSjNI1hJWt2duf7fbPrBcgwPD9+J18GCeCixSV//FYTvZd6hbg3CAsfFtEuZOAFYx80tVNAhcGoXQhTazOS/xoU/eg1EeyLFhF/SySilesc3gxD90Dm/xpzDbxG/ApAH4rYwJV6ZEBaf7UmfGG7o07+KLNB0R+/YMixhRM5m6NO7qfkXuNqDTG5tCZs/nIYFj7OMpl5Ai+ItkHDySy4hUybgIKdUds9tfs2KjuxsejHz0HjJ8kR8I+jnIrt3NZIlDXZkxqzzhqjvV3E89QJSkG8pTWd8W9EA3eczX4r92NKk0rLzasHScLXBbTMOFIKnw3a+c+26Mag2BjXCeDLDRyp0g/d77ZwXiz70XpCeGFNGPp02Cl0RWMMSn5U04Bdfn/KdJ4Bbo1TJohaAdUHodtDL4irDO5ZuHLo8a+W+80Ks8TVZvRQJ3huTsIm/LZr3DFtGDji5D5waUQaEwY/28l1w7Shqkv5J+x0ohRV7tr57VMaE2YJU9h/ARAuyINfc3GXAAAAAElFTkSuQmCC');
    background-size: 12px;
    background-position: right center;
    background-repeat: no-repeat;
  }
</style>


  <h1 class="title clearfix" class="title clearfix"><span class="fl">我的信息</span> <span class="point">·</span> <span class="fl">修改密码</span></h1>
  <div class="formAdd">
    <form id="companyFrom" method="post" action="user/changepwd">
      <h3 class="tit">修改密码<span>（*为必填项）</span></h3>
      <input type="hidden" name="userid" value="${userId}" />
      <div class="row2 clearfix">
        <div class="list">
          <label class="w100">原密码：</label>
          <input class="easyui-validatebox w215" type="password" placeholder="请输入原密码" name="oldpassword" data-options="prompt:'请输入公司法人姓名.',required:true,validType:'length[2,18]'" />
          <span>*</span>
        </div>
      </div>
      <div class="row2 clearfix">
        <div class="list">
          <label class="w100">新密码：</label>
          <input class="easyui-validatebox w215" type="password" placeholder="请输入新密码" name="password" data-options="prompt:'请输入公司法人姓名.',required:true,validType:'length[2,18]'" />
          <span>*</span>
        </div>
      </div>
      <div class="row2 clearfix">
        <div class="list">
          <label class="w100">确认新密码：</label>
          <input class="easyui-validatebox w215" type="password" placeholder="请输入确认新密码" name="surePassword" data-options="prompt:'请输入确认新密码.',required:true,validType:'length[2,18]'" />
          <span>*</span>
        </div>
      </div>
    </form>
    <a style="margin:30px 0 10px 180px" class="commitForm" onclick="pushCompany()">确定修改</a>
  </div>
</body>
  <script>
  $(document).ready(function () {

    $("#orgType").combobox({
      onChange: function (n, o) {
        console.log(n)
        if (n === '01') {
          $('#fold-2').show()
          $('#fold-3').show()
        } else {
          $('#fold-2').hide()
          $('#fold-3').hide()
        }
      }
    })

  })

  function showOrhide (id, e) {
    if ($('#' + id).hasClass('fold-box-close')) {
      $('#' + id).removeClass('fold-box-close')
      $(e).html('收起')
      $(e).removeClass('close')
      $(e).addClass('open')
    } else {
      $('#' + id).addClass('fold-box-close')
      $(e).html('展开')
      $(e).removeClass('open')
      $(e).addClass('close')
    }
  }

  function pushCompany () {
    $("#companyFrom").submit();
  }

  $("#companyFrom").submit(function (e) {
      e.preventDefault();
      var validate = $("#companyFrom").form('validate');
      if (!validate) {
          $("#companyFrom").find(".validatebox-invalid:first").focus();
          return false
      }
      if ($('[name="password"]').val() !== $('[name="surePassword"]').val()) {
          layer.msg("两次密码输入不一致，请重新输入");
          return;
      }
      var options = {
          success:  showResponse,
          resetForm: true
      };
      $(this).ajaxSubmit(options);
  });

  function showResponse(responseText, statusText) {
      if(responseText.statusCode == 200){
          layer.msg('成功');
          window.location.href = "/logout"
      }else{
          layer.msg(responseText.message);
      }
  }
  </script>