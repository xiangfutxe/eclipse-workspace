<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ssm.pojo.Admin"%>
<%@ page import="com.utils.Constants"%>

<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else {
	if(admin.getType()-1>0){
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加新闻</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>
 <script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script>
KindEditor.ready(function(K) {
	window.editor=K.create('#editor_id', {
		  uploadJson : '../kindeditor/jsp/upload_json.jsp',
                fileManagerJson : '../kindeditor/jsp/file_manager_json.jsp',
                allowFileManager : true
	});
});
        
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">信息管理</a></li>
    <li><a href="#">添加新闻</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="admin_save.do" id="myform" method="post">
    <ul class="forminfo">
    <li><label>登陆昵称<b>*</b></label>
    <input type="hidden" name="token" id="token" value="<c:out value='${session.token}' escapeXml='true' default=''/>"/>
    <input name="title" id="title" type="text" class="dfinput" /><i id="titleTag"></i></li>
    <li><label>真实姓名<b>*</b></label><input name="adminName" id="adminName" type="text" class="dfinput" /><i id="nameTag"></i></li>
    <li><label>登陆密码<b>*</b></label><input name="password" id="password" type="password" class="dfinput" /><i id="passwordTag"></i></li>
    <li><label>确认密码<b>*</b></label><input name="password2" id="password2" type="password" class="dfinput" /></li>
        <li><label>手机号码<b>*</b></label><input name="mobile" id="mobile" type="text" class="dfinput" /><i id="mobileTag"></i></li>
      <li><label>用户类型<b>*</b></label>
      <select name="type" id="type"  class="dfinput">
       <option value="1" >普通管理员</option>
      <%if(admin.getType()-1>0){ %>
     <option value="2">超级管理员</option>
     <%} %>
     </select><i id="typeTag"></i></li>
    <li><label>&nbsp;</label><input id="btn" type="button" class="btn" value="确认保存"/><i id="contentsTag"></i></li>
    </ul>
    </form>
    
    </div>
  <script language="JavaScript">
$(function() {
  var reg_mobile = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证

	$("#adminName").blur(function() {
		$.ajax({
				type : "post",
				url : "admin_isExit.do",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					adminName : $("#adminName").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方
				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data.tag;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					if(d=="0"){
					 $("#nameTag1").text("用户名不能为空！").css({"color":"red","font-size":"12px"});
					 $("#nameTag").text("");
					 }
					else if(d=="1") {
					 $("#nameTag1").text("用户名已存在！").css({"color":"red","font-size":"12px"});
					  $("#nameTag").text("");
					 }
					else if(d=="2"){
					  $("#nameTag1").text("√").css({"font-family":"Arial","color":"green","font-size":"12px"});
					   $("#nameTag").text("");
					 }
					else $("#nameTag").text("");
					if(getBymobileen($("#adminName").val())>50){
					 $("#nameTag1").text("用户名字数不能大于25！").css({"color":"red","font-size":"12px"});
					  $("#nameTag").text("");
					 }
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	});
	
	$("#password").blur(function() {
		if($("#password").val()=="")$("#passwordTag").text("用户密码不能为空！").css({"color":"red","font-size":"12px"});
		else if(getBymobileen($(this).val())>50) $("#passwordTag").text("用户密码字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#passwordTag").text("");
	});
	$("#password2").blur(function() {
		if($("#password2").val()!=$("#password").val())$("#password2Tag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"12px"});
		else if(getBymobileen($(this).val())>50) $("#password2Tag").text("用户密码字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#password2Tag").text("");
	});
	$("#realName").blur(function() {
		if($("#realName").val()=="")$("#realNameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"});
		else $("#realNameTag").text("");
	});
	
	$("#jobId").blur(function() {
		if($("#jobId").val()=="")$("#jobIdTag").text("职务信息不能为空！").css({"color":"red","font-size":"12px"});
		else $("#jobIdTag").text("");
	});
	
	$("#deptId").blur(function() {
		if($("#deptId").val()=="")$("#jobIdTag").text("部门信息不能为空！").css({"color":"red","font-size":"12px"});
		else $("#deptIdTag").text("");
	});
	$("#mobile").blur(function() {
		if($("#mobile").val()=="")$("#yzmTag").text("验证码不能为空！").css({"color":"red","font-size":"12px"});
		if(!(reg_mobile.test($("#mobile").val())))
			$("#mobileTag").text("手机号码格式有误！").css({"color":"red","font-size":"12px"});
		else $("#mobileTag").text("");
	});
	$("#yzm").blur(function() {
		if($("#yzm").val()=="")$("#yzmTag").text("验证码不能为空！").css({"color":"red","font-size":"12px"});
		else $("#yzmTag").text("");
	});
	
	$("#sbtn").click(function(){
	if (!confirm("确认提交注册员工信息？")) {
								return false;
							} else {
		var errorstr = "";
		if($("#adminName").text()!=""){
		$("#nameTag1").text("用户名不能为空！").css({"color":"red","font-size":"12px"});
		$("#nameTag").text("");
			errorstr ="1";
		}else if($("#nameTag1").val()!=""){
			errorstr ="1";
		}
		
		if($("#realName").text()!=""){
		$("#realNameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#realNameTag").val()!=""){
			errorstr ="1";
		}
		if($("#password").val()==""){
			$("#passwordTag").text("用户密码不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#passwordTag").text()!=""){
			errorstr ="1";
		}
	
		if($("#password2").val()!=$("#password").val()){
			$("#password2Tag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#password2Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#mobile").val()==""){
			$("#mobileTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#mobileTag").text()!=""){
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
			return false;
		}else{
       		 $('#myform').submit();
       		 return true;
		
		}
		}
	});
	
});
</script>
</body>

</html>
<%
	}else{
		out.println("<script>");
				out.println("alert('你没有权限进行该操作！')");
				out.println("</script>");
	}
}
%>