<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Branch"%>
<%@ page import="com.pojo.User"%>
<%@page import="com.utils.StringUtil"%>
<%@page import="com.utils.DateUtil"%>
<%@page import="com.utils.Pager"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("ht_user");
Branch branch = (Branch)request.getAttribute("branch");
String token = (String)request.getSession().getAttribute("token");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>华太俱乐部</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="华太俱乐部">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="Assets/css/reset.css"/>
<script type="text/javascript" src="Assets/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="Assets/js/js_z.js"></script>
<script type="text/javascript" src="Assets/js/main.js"></script>
<link rel="stylesheet" type="text/css" href="css/about.css">
<link rel="stylesheet" type="text/css" href="css/form.css">
<script type="text/javascript" src="js/other.js"></script>
<script src="js/calendar.js"></script>
  </head>
  
 <body>
 <!--头部-->
 <div class="header">
<div class="head clearfix">
	<div class="logo"><a href=""><img src="images/logo.png" alt="华太"/></a></div>
    <div class="tel"> <span><%if(user==null){ %><a href="UserServlet.do?method=login">会员登录</a>
    &nbsp;|&nbsp;<a href="UserServlet.do?method=user_add_branch">免费注册</a>
    <%}else{ %><%=StringUtil.notNull(user.getUserId())%>(<a href="UserServlet.do?method=login_out">退出</a>)<%} %>
    &nbsp;|&nbsp;<a href="ContactServelt.do?method=C_801">联系我们</a></span></div></div>
</div>
<!--导航 -->
<div class="nav_bg">
	<div class="nav_m">
    	<ul id="nav">
        	<li>
        	<a href="NewsServlet.do?method=index">首页</a>
        	</li>
          
            <li><a href="AboutServlet.do?method=A_201">关于我们</a>
                            <ul>
                                 <li><a href="AboutServlet.do?method=A_201">公司简介</a></li>
                                <li><a href="AboutServlet.do?method=A_202">企业文化</a></li>
                                <li><a href="AboutServlet.do?method=A_203">企业愿景</a></li>
                                <li><a href="AboutServlet.do?method=A_204">核心价值观</a></li>
                                <li><a href="AboutServlet.do?method=A_205">共同发展</a></li>
                            </ul>
            </li>
          
            <li><a href="BranchServlet.do?method=B_305">全球分会</a>
               <ul>
                                <li><a href="BranchServlet.do?method=B_301">全景规划</a></li>
                                <li><a href="BranchServlet.do?method=B_302">管理章程</a></li>
                                <li><a href="BranchServlet.do?method=B_303">管理架构</a></li>
                                <li><a href="BranchServlet.do?method=B_304">分会申请</a></li>
                                <li><a href="BranchServlet.do?method=B_305">分会动态</a></li>
                            </ul>
            </li>
           
          
            <li><a href="ActivityServlet.do?method=A_401">全球活动</a>
               <ul>
                              <li><a href="ActivityServlet.do?method=A_402">总部竞赛</a></li>
                                <li><a href="ActivityServlet.do?method=A_403">分部竞赛</a></li>
                                <li><a href="ActivityServlet.do?method=A_404">环球之旅</a></li>
                                <li><a href="ActivityServlet.do?method=A_401">魅惑倩影</a></li>
                            </ul></li>
          
           <li><a href="PhilanthropyServlet.do?method=P_501">慈善公益</a>
             <ul>
                                <li><a href="PhilanthropyServlet.do?method=P_501">公益足迹</a></li>
                                <li><a href="PhilanthropyServlet.do?method=P_502">善行有我</a></li>
                                <li><a href="PhilanthropyServlet.do?method=P_501">公益剪影</a></li>
                            </ul></li>
          
           <li><a href="SchoolServlet.do?method=S_601">名媛学堂</a>
            
             <ul>
                                <li><a href="SchoolServlet.do?method=S_601">学院宗旨</a></li>
                                <li><a href="SchoolServlet.do?method=S_602">视频在线</a></li>
                                <li><a href="SchoolServlet.do?method=S_603">旗袍文化</a></li>
                                <li><a href="SchoolServlet.do?method=S_604">国学传播</a></li>
                            </ul></li>
          
           
              <li><a href="UserServlet.do?method=U_701"><span  class="now">会员注册</span></a>
                <ul>
                                <li><a href="UserServlet.do?method=U_701">分会一览</a></li>
                                <li><a href="UserServlet.do?method=U_702">会员须知</a></li>
                                <%if(user==null){%>
                                <li><a href="UserServlet.do?method=user_add_branch">免费注册</a></li>
                                <%} %>
                            </ul>
              </li>
          
             <li><a href=ContactServelt.do?method=C_801>联系我们</a>
               <ul>
                                <li><a href="ContactServelt.do?method=C_801">联系方式</a></li>
                                <%if(user==null){%>
                                <li><a href="ContactServelt.do?method=C_802">在线留言</a></li>
                                <%}else{%>
                                <li><a href="ContactServelt.do?method=C_803">留言簿</a></li>
                                <%} %>
                            </ul></li>
          
            <li><a href="ShopServlet.do?method=index">华太商城</a></li>
        </ul>
    </div>
</div>
<div class="main_bg">
    <!--幻灯片-->
    <div class="banner">
        <div id="inner">
            <div class="hot-event">
                <div class="event-item" style="display: block;">
                    <a class="banner">
                        <img src="images/about/banner01.jpg" class="photo" alt="华太" />
                    </a>
                </div>
            </div>
        </div>
    </div>
	<!--幻灯片-->
	<div class="second">   
        <div class="scd_m clearfix">
        	<jsp:include page="second.jsp"/>
            <div class="scd_mr">
            	
            	<div class="about clearfix">
            	<form action="UserServlet.do?method=user_save" method="post" class="contact_form">
            	<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
            	<input type="hidden" name="branchId" id="branchId" value="<%=StringUtil.notNull(branch.getBranchId())%>"/>
            	<input type="hidden" name="branchName" id="branchName" value="<%=StringUtil.notNull(branch.getBranchName())%>"/>  
                	<ul>
                	 <li>
         <h2>快速注册</h2>
         <span class="required_notification">* 表示必填项</span>
    </li>
                	<li>
                	<label>所属协会</label>
                	<%=branch.getBranchName() %>(<%=branch.getBranchId() %>)
                	</li>
						<li>
                	<label>会员昵称<span class="required">*</span></label><input type="text" id="userId" name="userId" value="" placeholder="请输入6-10位的数字与字母！" />
                	<span id="idTag"></span>
                	</li>
                	<li>
                	<label>
                	真实姓名<span class="required">*</span></label><input type="text" id="userName" name="userName" value=""/>
                	<span id="nameTag"></span>
                	</li>
                	<li>
                	<label>登录密码<span class="required">*</span></label><input type="password" id="password" name="password" value=""/>
                	
                	<span id="passwordTag"></span></li>
                	<li>
                	<label>确认密码<span class="required">*</span></label>
                	<input type="password" id="repassword" name="repassword" value=""/>
                	
                	<span id="repasswordTag"></span>
                	</li>
                	<li>
                	<label>性别<span class="required">*</span></label>
                	<input type="radio" name="sex" value="男" checked/>男
                	<input type="radio" name="sex" value="男"/>女
                	<span id="nameTag"></span>
                	</li>
                	<li>
                	<label>出生日期<span class="required">*</span></label>
                	
                	<input name="birth"
									id="birth" type="text" value=''
									onfocus="new Calendar().show(this);">
									 <span id="birthTag"></span>
                	</li>
                	<li>
                	<label>证件类型</label>
                	<select name="documentType" id="documentType">
									<option value="居民身份证">居民身份证</option>
							</select> 
							<span id="documentTypeTag"></span>
                	</li>
                	<li>
                	<label>证件号码</label>
                	 <input id="numId" name="numId" size="50"
								value='' type="text">&nbsp;<span
								id="numIdTag"></span>
								</li>
                	<li>
                	<label>手机号码<span class="required">*</span></label>
                	<input id="tel" name="tel" size="50"
								value='' type="text">
								<span
								id="telTag"></span>
                	</li>
                	<li><label>&nbsp;</label>
                	<button  id="btn"  class="button">提交</button>
                	<button  id="btn1"  class="button1" onclick="javascript:history.go(-1);">返回</button>
                	</li>
                	</ul>
					</form>
					</div>
					</div>
					</div>
					
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
<script type="text/javascript" src="js/custom.js"></script>
<script language="JavaScript">
	$(function() {
	  var reg2 = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证
	
		var a = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;
	var reg5=/^[A-Za-z0-9]+$/;  //验证数字和字母的组合
	$("#userId").blur(function() {
				$.ajax({
  	 		type:"post",
  	 		url:"UserServlet.do?method=userAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
  	 		data:{//设置数据源
						userId:$("input[name=userId]").val() //这里不要加","  不然会报错，而且根本不会提示错误地方
					
				},
 			dataType:"json",//设置需要返回的数据类型
			success:function(data){
				var d = data;
				if($("#userId").val()=="") $("#idTag").text("会员昵称不能为空！").css({"color":"red","font-size":"12px"});	 
				else if(getByteLen($("#userId").val())<6||getByteLen($("#userId").val())>10) $("#idTag").text("长度有误，请输入6-10位的数字与字母的组合昵称！").css({"color":"red","font-size":"12px"});
				else if($("#userId").val().match(reg5)==null) $("#idTag").text("格式有误，请输入6-10位的数字与字母的组合昵称！").css({"color":"red","font-size":"12px"});
				else if(d.tag==1) $("#idTag").text("会员昵称已被使用！").css({"color":"red","font-size":"12px"});
				else $("#idTag").text("");
             },
			error:function(){
			
				 $("#idTag").text("系统异常，请稍后重试！");

                    }//这里不要加","
  	 	});
	});
	 $("#userName").blur(function(){
	
   		 if($("#userName").val()=="")  $("#nameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"}); 
		else if(getByteLen($("#userName").val())>50) $("#nameTag").text("真实姓名字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#nameTag").text("");
   });
   
    $("#password").blur(function(){
   		 if($("#password").val()=="")  $("#passwordTag").text("登录密码不能为空！").css({"color":"red","font-size":"12px"}); 
   		 else if(getByteLen($("#password").val())<6) $("#passwordTag").text("密码字数不能小于6位数！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($("#password").val())>50) $("#passwordTag").text("密码字数不能大于25位数！").css({"color":"red","font-size":"12px"});
		else $("#passwordTag").text("");
		
   });
    $("#password2").blur(function(){
   		 if($(this).val()!= $("#password").val())  $("#password2Tag").text("两次输入密码不一致不能为空！").css({"color":"red","font-size":"12px"}); 
		else $("#password2Tag").text("");
		
   });
	
	$("#brith").blur(function() {
			if ($(this).val()== "") {
								$("#brithTag").text("出生日期不能为空！").css({"color":"red","font-size":"12px"});
			}else if ($(this).val().match(a) == null) {
								$("#brithTag").text("出生日期格式有误！").css({"color":"red","font-size":"12px"});
			}else{
				$("#brithTag").text("");
			}
	});
	 $("#tel").blur(function(){
   	 if($("#tel").val()!=""){
				if(!(reg2.test($("#tel").val())))
					$("#telTag").text("联系电话格式有误！").css({"color":"red","font-size":"12px"}); 
					else $("#telTag").text("");
	}else{
		$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"});
	}
   });
		$("#btn").click(
				function() {
					if (!confirm("确认要批提交注册新会员？")) {
						return false;
					} else {
				
						$('#myform').attr("action",
								"UserServlet.do?method=user_save");
						$('#myform').submit();
					}

				});
	
	});
</script>
</html>
