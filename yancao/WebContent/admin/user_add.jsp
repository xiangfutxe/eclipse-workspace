<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ssm.pojo.Admin"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@page import="com.ssm.utils.StringUtil" %>


<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else {
	  String[][] ranks = StringUtil.getRankStr(admin.getRank());
			if(ranks[1][0].equals("1")||admin.getState()-1==0){
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加员工</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>
 <script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">会员管理</a></li>
    <li><a href="#">会员新增</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="UserServlet?method=admin_user_save" id="myform" method="post">
     <input type="hidden" name="token" id="token" value="<c:out value='${sessionScope.token}' escapeXml='true' default=''/>"/> 
    <ul class="forminfo">
     <li><label>会员等级<b>*</b></label>
     <select name="rankJoin" id="rankJoin" class="dfinput">
     <option value="0">--关注会员--</option>
      <option value="1">--普通会员--</option>
       <option value="2">--天使会员--</option>
        <option value="3">--资深会员--</option>
         <option value="4">--高级合伙人--</option>
          <option value="5">--城市合伙人--</option>
           <option value="6">--联创合伙人--</option>
            <option value="7">--官方合伙人--</option>
     </select>
     </li>
     <li><label>邀请码<b>*</b></label>
    <input name="refereeId" id="refereeId" type="text" class="dfinput" /><i id="refereeIdTag"></i><i id="refereeIdTag1"></i></li>
    
         <li><label>会员昵称<b>*</b></label>
    <input name="nickName" id="nickName" type="text" class="dfinput" /><i id="nameTag1"></i><i id="nameTag"></i></li>
    <li><label>真实姓名<b>*</b></label><input name="userName" id="userName" type="text" class="dfinput" /><i id="userNameTag"></i></li>
    <li><label>登陆密码<b>*</b></label><input name="password" id="password" type="password" class="dfinput" /><i id="passwordTag"></i></li>
    <li><label>微信号<b>*</b></label><input name="openId" id="openId" type="text" class="dfinput" /><i id="openIdTag"></i></li>
    <li><label>性别<b>*</b></label><input name="sex" type="radio" value="1" checked="checked"/>男&nbsp;
    <input name="sex" type="radio" value="2" />女<i id="sexTag"></i></li>
    <li><label>手机号码<b>*</b></label><input name="tel" id="tel" type="text" class="dfinput" /><i id="telTag"></i></li>
    <li><label>&nbsp;</label><input id="save_click"  type="button" class="btn" value="确认保存"/><i id="contentsTag"></i></li>
    </ul>
    </form>
     <div class="save_tip">
    	<div class="save_tiptop"><span>提示信息</span><a></a></div>
        
      <div class="save_tipinfo">
        <span><img src="images/ticon_save.png" /></span>
        <div class="save_tipright">
        <p>是否确认对信息的进行保存 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
       
        <div class="tipbtn">
        <input name="" type="button"  class="save_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="save_cancel" value="取消" />
        </div>
     </div>
    </div>
  <script language="JavaScript">
$(function() {
  var reg_tel = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证

	
	$("#password").blur(function() {
		if($("#password").val()=="")$("#passwordTag").text("用户密码不能为空！").css({"color":"red","font-size":"12px"});
		else if($(this).val().length>50) $("#passwordTag").text("用户密码字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#passwordTag").text("");
	});
	$("#password2").blur(function() {
		if($("#password2").val()!=$("#password").val())$("#password2Tag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"12px"});
		else if($(this).val().length>50) $("#password2Tag").text("用户密码字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#password2Tag").text("");
	});
	$("#userName").blur(function() {
		if($("#userName").val()=="")$("#userNameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"});
		else $("#userNameTag").text("");
	});
	$("#nickName").blur(function() {
		if($("#nickName").val()=="")$("#nameTag").text("会员昵称不能为空！").css({"color":"red","font-size":"12px"});
		else $("#nameTag").text("");
	});
	
	
	$("#openId").blur(function() {
		if($("#openId").val()=="")$("#jobIdTag").text("微信号不能为空！").css({"color":"red","font-size":"12px"});
		else $("#openIdTag").text("");
	});
	$("#tel").blur(function() {
		if($("#tel").val()=="")$("#yzmTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"});
		if(!(reg_tel.test($("#tel").val())))
			$("#telTag").text("手机号码格式有误！").css({"color":"red","font-size":"12px"});
		else $("#telTag").text("");
	});
	
	$("#save_click").click(function(){
		  $(".save_tip").fadeIn(200);
		  });
		  
		  $(".save_tiptop a").click(function(){
		  $(".save_tip").fadeOut(200);
		});

		  $(".save_sure").click(function(){
		 		 $(".save_tip").fadeOut(100);
		var errorstr = "";
		if($("#nickName").text()!=""){
		$("#nameTag").text("会员昵称不能为空！").css({"color":"red","font-size":"12px"});
		$("#nameTag").text("");
			errorstr ="1";
		}else if($("#nameTag").val()!=""){
			errorstr ="1";
		}
		
		if($("#userName").text()!=""){
		$("#userNameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#userNameTag").val()!=""){
			errorstr ="1";
		}
		if($("#password").val()==""){
			$("#passwordTag").text("用户密码不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#passwordTag").text()!=""){
			errorstr ="1";
		}
	
		/*
		if($("#tel").val()==""){
			$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#telTag").text()!=""){
			errorstr ="1";
		}
		*/
		
		if($("#openId").val()==""){
			$("#openIdTag").text("微信号不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#openIdTag").text()!=""){
			errorstr ="1";
		}
		/*
		if($("#jobId").val()==""){
			$("#jobIdTag").text("部门信息不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#jobIdTag").text()!=""){
			errorstr ="1";
		}
		*/
		if(errorstr!=""){
			alert(errorstr);
			return false;
		}else{
       		 $('#myform').submit();
       		 return true;
		
		}
	});
		  
		  $(".save_cancel").click(function(){
			  $(".save_tip").fadeOut(100);
			});
	
});
</script>
</body>

</html>
<%
	}else{
		out.println("<script>");
		out.println("window.location.href='error_rank.jsp';");
		out.println("</script>");
	}
}
%>