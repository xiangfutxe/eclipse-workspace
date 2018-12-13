<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.StringUtil"%>

<%
User user = (User)request.getSession().getAttribute("sys_user");
String psw2 = (String)request.getSession().getAttribute("psw2");

if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
}else{
	String auth1 = StringUtil.notNull(user.getAuthority());
	
	 if(auth1.length()<30){
		out.println("<script>");
		out.println("alert('会员权限信息获取失败，请重新登陆');parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登陆密码修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="Assets/css/reset.css"/>
<script type="text/javascript" src="Assets/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="Assets/js/js_z.js"></script>
<script type="text/javascript" src="Assets/js/main.js"></script>
<link rel="stylesheet" type="text/css" href="css/thems.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<link rel="stylesheet" type="text/css" href="css/form.css">
<script type="text/javascript" src="js/psw2.js"></script>
<script src="js/jquery.slider.js"></script>
<link rel="stylesheet" href="fonts/fontCss.css">
	<link rel="stylesheet" href="css/jquery.slider.css">
	      <script type="text/javascript" src="js/jquery.cityselect.js"></script>
	<script>
	window.onload = function(){
		$('.testSlider').slider({
			originType:'tuoyuan'
		});
		$('.testSlider1').slider({
			width:300,
			height:300,
			showSlider: false,
			showOrigin: false,
			originPosition:'center'
		});

		$('.testSlider3').slider({
			width:1094,
			height:300,
			originPosition:'center',
			source: [
							{
								src: 'img/slider1.jpg',									// 幻灯片图片地址		
								hasHref: true,												// 是否有链接
								href: '#',			// 链接地址					
								hrefTarget: '_self'											// 链接打开方式  _self, _blank, _top等
 							},
						]
		});
	};
</script>
  </head>
  
 <body>
 <!--头部-->
 <jsp:include page="header.jsp" />
<div class="main_bg">
    <!--幻灯片-->
    <div class="banner1">
            <span class="testSlider3" style="display: inline-block;"></span>
    </div>
  
	<!--幻灯片END-->
	<div class="main_bg">
	<div class="second">   
        <div class="scd_m clearfix">
        	<div class="scd_ml">
            	<ul class="sidenav">
          		  <li><a href="UserServlet?method=user_detail">个人资料</a></li>
          		 
                     <%if(auth1.substring(8, 9).equals("1")){ %>
                    <li><a href="DRewardServlet?method=reward_day_list">补贴明细</a></li>
                    <%}if(auth1.substring(9, 10).equals("1")){  %>
                    <li><a href="ReportServlet?method=report_settle_day">已结业绩</a></li>
                   
                    <%} %>
                    <li  class="now"><a href="password1_edit.jsp">登陆密码</a></li>
                     <li><a href="password2_edit.jsp">支付密码</a></li>
                </ul>
                 <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：个人信息-&gt;<a>登陆密码修改</a></h2>
            	</div>
            	<div class="content content-full-width" id="content-psw">
   		<input type="hidden" id="psw1" name="psw1" value="<%=user.getPassword2()%>"/>
		<div class="notice">
         <h2>支付密码</h2><span class="required_notification"></span>
         </div>
         	 <table class ="form-table">
  			 <tr>
  			 <td width="15%"><label><span class="required">*</span>请输入支付密码：</label></td>
  			 <td ><input id="psw2" name="psw2"  type="password" value="<%=psw2%>"/>&nbsp;<span id="psw2Tag"></span></td>
  			 </tr>
  			 </table>
   		
		</div>
		<div class="content content-full-width" id="content-all">
         <form  action="UserServlet?method=user_password1_update"  id="myform" name="myform" method="post">
		
            		
                	<div class="notice">
         <h2>登陆密码修改</h2><span class="required_notification"></span>
   </div>
  
  			 <table class ="form-table">
  			 <tr>
  			 <td width="12%"><label>用户信息： </label></td>
  			 <td><%=StringUtil.notNull(user.getUserName())%>(<%=StringUtil.notNull(user.getUserId())%>)</td>
  		
  			 </tr>
  			  <tr>
  			 <td><label>原密码： </label></td>
  			 <td><input type="password" name="pwd" id="pwd">&nbsp;<span id="pwdTag"></span></td>
  		
  			 </tr>
  			 
  			  <tr>
  			 <td><label>新密码： </label></td>
  			 <td><input type="password" name="password" id="password">&nbsp;<span id="passwordTag"></span></td>
  		
  			 </tr>
  			  <tr>
  			 <td><label>确认密码： </label></td>
  			 <td><input type="password" name="repassword" id="repassword" >&nbsp;<span id="repasswordTag"></span></td>
  		
  			 </tr>
  			 <tr><td colspan="2">
                	<input type="button"  id="sbtn"  class="button blue-sky" value="保存修改">
                	</td>
                	</tr>
                	 </table>
                	
			</form>
			 </div>
					</div>
         </div> 
               </div>
               </div>
</div>
<jsp:include page="footer.jsp" />
</body>
  <script type="text/javascript" src="../js/custom.js"></script>
 <script type="text/javascript">
$(function() {
	$("#pwd").blur(function() {
		if($("#pwd").val()=="")$("#pwdTag").text("原密码不能为空！").css({"color":"red","font-size":"12px"});
		else $("#pwdTag").text("");
	});
	$("#password").blur(function() {
		if($("#password").val()=="")$("#passwordTag").text("新密码不能为空！").css({"color":"red","font-size":"12px"});
		else $("#passwordTag").text("");
	});
	$("#repassword").blur(function() {
		if($("#repassword").val()!=$("#password").val())$("#repasswordTag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"12px"});
		else $("#repasswordTag").text("");
	});
	$("#sbtn").click(function() {
        if (!confirm("确认要修改密码，修改成功后需要重新登录？")) {
            return false;
        }else{
			var error="";
		if($("#pwd").val()==""){
			$("#pwdTag").text("原密码不能为空！").css({"color":"red","font-size":"12px"});
			error = error+"1";
		}
		else if($("#pwdTag").val()!="") error = error+"1";
		
		if($("#password").val()==""){
			$("#passwordTag").text("新密码不能为空！").css({"color":"red","font-size":"12px"});
		}
		else if($("#passwordTag").val()!="")error = error+"1";
		
		if($("#repassword").val()!=$("#password").val()){
			$("#repasswordTag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"12px"});
			error = error+"1";
		}
		else if($("#repasswordTag").val()!="")error = error+"1";
		
		if(error!="")  return false;
		else {
			$("#myform").submit();
			return true;
		}
		}
	});
	});
   </script>
</html>
<%
}}
%>