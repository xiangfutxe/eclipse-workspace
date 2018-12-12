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
		
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>密码修改</title>
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
    <li><a href="#">其他设置</a></li>
    <li><a href="#">密码修改</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="AdminServlet?method=admin_psw_update" id="myform" method="post">
       <input type="hidden" name="id" id="id" value="${admin.id }"/>
    <ul class="forminfo">
    <li><label>用户名：<b>*</b></label>
   
    <input name="adminName" id="adminName" type="text" class="dfinput" value="<%=admin.getAdminName() %>" readonly="readonly"/><i id="nameTag"></i></li>
    <li><label>原密码：<b>*</b></label><input name="psw" id="psw" type="password" class="dfinput" /><i id="pswTag"></i></li>
    <li><label>新密码：<b>*</b></label><input name="password" id="password" type="password" class="dfinput" value=""/><i id="passwordTag"></i></li>
      <li><label>确认密码<b>*</b></label>
     <input name="password2" id="password2" type="password" class="dfinput" value=""/><i id="passwordTag2"></i></li>
    <li><label>&nbsp;</label><input id="save_click"  type="button" class="btn" value="提交修改"/><i id="contentsTag"></i></li>
    </ul>
    </form>
     <div class="save_tip">
    	<div class="save_tiptop"><span>提示信息</span><a></a></div>
        
      <div class="save_tipinfo">
        <span><img src="images/ticon_save.png" /></span>
        <div class="save_tipright">
        <p>是否确认对信息的进行提交修改？</p>
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

	
	$("#psw").blur(function() {
		if($("#psw").val()=="")$("#realNameTag").text("原密码不能为空！").css({"color":"red","font-size":"12px"});
		else $("#pswTag").text("");
	});
  
	$("#password").blur(function() {
		if($("#password").val()=="")$("#passwordTag").text("新密码不能为空！").css({"color":"red","font-size":"12px"});
		else $("#passwordTag").text("");
	});
	
	$("#password2").blur(function() {
		if($("#password2").val()==""||$("#password2").val()!=$("#password").val())$("#password2Tag").text("两次密码不能为空！").css({"color":"red","font-size":"12px"});
		else $("#password2Tag").text("");
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
		if($("#psw").val()==""){
			$("#pswTag").text("用户名不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#pswTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#password").val()==""){
		$("#passwordTag").text("新密码不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#passwordTag").text()!=""){
			errorstr ="1";
		}
		
		
		if($("#password2").val()==""){
			$("#passwordTag2").text("确认密码不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#passwordTag2").text()!=""){
			errorstr ="1";
		}
		
		/*
		if($("#deptId").val()==""){
			$("#deptIdTag").text("部门信息不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#deptIdTag").text()!=""){
			errorstr ="1";
		}
		
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
	
}
%>