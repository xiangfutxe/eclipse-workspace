<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.utils.StringUtil"%>
<%@ page import="com.pojo.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String message = (String)request.getAttribute("message");
User user = (User)request.getSession().getAttribute("ht_user");
if(!StringUtil.notNull(message).equals("")){
out.println("<script>");
out.println("alert('"+message+"');");
out.println("</script>");
System.out.println(message);
}
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
 <script type="text/javascript">
        var code;
        function createCode() {
            code = "";
            var codeLength = 4; //验证码的长度
            var checkCode = document.getElementById("checkCode");
            var hiddenCode = document.getElementById("code"); 
            var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9); //所有候选组成验证码的字符，当然也可以用中文的
            for (var i = 0; i < codeLength; i++) 
            {
                var charNum = Math.floor(Math.random() * 10);
                code += codeChars[charNum];
            }
            if (checkCode) 
            {
                checkCode.className = "code";
                checkCode.innerHTML = code;
                hiddenCode.value= code;
            }
        }
        </script>
  
<script>
$(function(){

$(".i-text").focus(function(){
$(this).addClass('h-light');
});

$(".i-text").focusout(function(){
$(this).removeClass('h-light');
});

$("#userId").blur(function(){
 var userId = $(this).val();
 if(userId==''){
 $("#idTag").text('请输入用户名称！').css({"color":"red","font-size":"12px"});
 }else{$("#idTag").text('');}
});

$("#password").blur(function(){
 var password = $(this).val();
 if(password==''){
 $("#passwordTag").text('请输入登录密码！').css({"color":"red","font-size":"12px"});
 }else{$("#passwordTag").text('');}
});

$("#yzm").blur(function(){
 var yzm = $(this).val();
 var code = $("#code").val();
 if(yzm==''){
 $("#yzmTag").text('请输入验证码！').css({"color":"red","font-size":"12px"});
 }else if(yzm!=code){
 $("#yzmTag").text('验证码有误！').css({"color":"red","font-size":"12px"});
 }else{$("#yzmTag").text('');}
});

$("#btn").click(function(){
		
		var errorstr = "";
		if($("#userId").val()==""){
			$("#idTag").text("请输入用户名称！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}
		
		if($("#password").val()==""){
			$("#passwordTag").text("请输入登录密码！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		
		
		if($("#yzm").val()==""){
			$("#yzmTag").text("请输入验证码！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#yzm").val()!=$("#code").val()){
			$("#yzmTag").text("验证码有误！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		if($("#yzmTag").text()!=""){
			errorstr ="1";
		}
		
		if(errorstr!=""){
			return false;
		}else{
       		 $('#loginForm').submit();
       		 return true;
		
		}
	});
	

});
</script>
  </head>
  
 <body  onload="createCode()">
 <!--头部-->
 <div class="header">
<div class="head clearfix">
	<div class="logo"><a href=""><img src="images/logo.png" alt="华太"/></a></div>
    <div class="tel"><span><%if(user==null){ %><a href="UserServlet.do?method=login">会员登录</a>
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
          
           
              <li  class="now"><a href="UserServlet.do?method=U_701">会员注册</a>
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
        	<div class="scd_ml">
            	<ul class="sidenav">
            	 <a href="#"><img alt="" src="images/left_1.png"></a>
            	<a href="#"><img alt="" src="images/left_1_1.jpg" width="300px"></a>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;旗袍是民国的旗人之袍，盛行于三四十年代。</li>
                <li>二十世纪二十年代看作旗袍流行的起点，三十年代</li>
                <li>它到了顶峰状态，很快从发源地上海风靡至中国各</li>
                <li>地。旗袍追随着时代，承载着文明，以其流动的旋</li>
                <li>律、潇洒的画意与浓郁的诗情，表现出中华女性贤</li>
                <li>淑、典雅、温柔、清丽的性情与气质。旗袍连接起</li>
                <li>过去和未来，连接起生活与艺术，将美的风韵洒满</li>
                <li>人间。</li>
                </ul>
                  <ul>
            	 <li class="letf">&nbsp;</li>
            	 </ul>
                <ul  class="sidenav">
                <a href="#"><img alt="" src="images/left_2.png"></a>
                <li>&nbsp;</li></ul>
                  <ul>
            	 <li class="letf">&nbsp;</li>
            	 </ul>
                 <ul  class="sidenav">
               <a href="#"><img alt="" src="images/left_3.png"></a>
                <li>&nbsp;</li></ul>
              
            </div>
            <div class="scd_mr">
            	
            	<div class="about clearfix">
            	<form id="loginForm" action="UserServlet.do?method=U_704" method="post" class="contact_form">
                	<ul>
                	 <li>
         <h2>会员登录</h2>
         <span class="required_notification">* 表示必填项</span>
    </li>
                	<li>
                	<label>会员昵称：<span class="required">*</span></label>
                <input type="text" id="userId" name="userId" value=""/>	<span id="idTag"></span>
                	</li>
                	<li>
                	<label>登录密码：<span class="required">*</span></label><input type="password" id="password" name="password" value=""/>
                	
                	<span id="passwordTag"></span></li>
                	<li>
                	<label>验&nbsp;证&nbsp;码：<span class="required">*</span></label>
                	  <input type="text" maxlength="100" id="yzm" class="i-text yzm" placeholder="输入验证码"/>    
       <span class="yzm-img" > <span id="checkCode" onclick="createCode()"></span></span>
        <input type="hidden"  id="code" name="code"> <span id="yzmTag"></span>
                	</li>
                	
                	<li><label>&nbsp;</label>
                	<button  id="btn"  class="button">登录</button>
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
</html>
