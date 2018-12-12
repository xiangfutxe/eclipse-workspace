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
<title>会员详情</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>
 <script src="../js/calendar.js"></script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">会员管理</a></li>
    <li><a href="#">会员详情</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="member_save.do" id="myform" method="post">
    <ul class="forminfo">
    <li><label>会员昵称</label>
    <label>${member.nickName}</label></li>
    <li><label>会员等级</label><label>
    <c:if test="${member.rank==0}">普通会员</c:if>
     <c:if test="${member.rank==1}">青铜会员</c:if>
      <c:if test="${member.rank==2}">白银会员</c:if>
      <c:if test="${member.rank==3}">黄金会员</c:if>
      <c:if test="${member.rank==4}">铂金会员</c:if>
      <c:if test="${member.rank==5}">钻石会员</c:if></label>
     </li>
      <li><label>真实姓名</label><label> ${member.realName}</label></li>
     <li><label>性别</label>
 <label>${member.sex}</label></li>
      <li><label>婚姻状况<b>*</b></label> <label>${member.maritalStatus}</label></li>
       <li><label>手机号码<b>*</b></label><label>${member.mobile}</label></li>
       <li><label>学历<b>*</b></label><label>${member.education}</label>
      </li>
    <li><label>&nbsp;</label><input type="button" class="btn" value="返回上一页" onclick="javascript:history.go(-1);"/><i id="contentsTag"></i></li>
    </ul>
    </form>
    
       
       
    </div>
  
 <script type="text/javascript">
$(function() {
	  var reg_mobile = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证
	
	$("#nickName").blur(function() {
		if($(this).val()=="")$("#nickNameTag").text("登陆昵称不能为空！").css({"color":"red","font-size":"12px"});
		else $("#nickNameTag").text("");
	});
	
	$("#rank").blur(function() {
		if($(this).val()=="")$("#rankTag").text("会员等级不能为空！").css({"color":"red","font-size":"12px"});
	
		else $("#rankTag").text("");
	});
	
	$("#realName").blur(function() {
		if($(this).val()=="")$("#realNameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"});
	
		else $("#realNameTag").text("");
	});
	
	$("#mobile").blur(function() {
		if($(this).val()=="")$("#mobileTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"});
		else $("#mobileTag").text("");
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
				if($("#nickName").val()==""){
					$("#nickNameTag").text("登陆昵称不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#nickNameTag").text()!=""){
					errorstr ="1";
				}
				
				if($("#rank").val()==""){
					$("#rankTag").text("会员等级不能为空！").css({"color":"red","font-size":"12px"});
					errorstr ="1";
				}else if($("#rankTag").text()!=""){
					errorstr ="1";
				}
				if($("#mobile").val()==""){
					$("#mobileTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#mobileTag").text()!=""){
					errorstr ="1";
				}
				if(errorstr!=""){
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
				out.println("alert('你没有权限进行该操作！')");
				out.println("</script>");
	}
}
%>