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
<title>新增会员</title>
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
    <li><a href="#">添加会员</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="member_save.do" id="myform" method="post">
    <ul class="forminfo">
    <li><label>登陆昵称<b>*</b></label>
    <input type="hidden" name="token" id="token" value="${sessionScope.token}"/>
    <input name="nickName" id="nickName" type="text" class="dfinput" /><i id="nickNameTag"></i></li>
    <li><label>会员等级<b>*</b></label>
      <select name="rank" id="rank"  class="dfinput">
       <option value="0" >普通会员</option>
     <option value="1">青铜会员</option>
      <option value="2">白银会员</option>
      <option value="3">黄金会员</option>
      <option value="4">铂金会员</option>
      <option value="5">钻石会员</option>
     </select><i id="rankTag"></i></li>
    <li><label>登陆密码<b>*</b></label><input name="password" id="password" type="password" class="dfinput" /><i id="passwordTag"></i></li>
    <li><label>确认密码<b>*</b></label><input name="password2" id="password2" type="password" class="dfinput" /></li>
      <li><label>真实姓名<b>*</b></label><input name="realName" id="realName" type="text" class="dfinput" /><i id="realNameTag"></i></li>
     <li><label>性别<b>*</b></label>
     <input name="sex" type="radio" value="男" checked="checked"/>男 &nbsp;<input name="sex"  type="radio"  value="女"/>女<i id="sexTag"></i></li>
     <li><label>婚姻状况<b>*</b></label>
     <input name="maritalStatus" type="radio" value="未婚" checked="checked"/>未婚 &nbsp;
    <input name="maritalStatus" type="radio"  value="已婚"/>已婚 &nbsp;
     <input name="maritalStatus" type="radio"  value="离异"/>离异 &nbsp;
     <input name="maritalStatus" type="radio"  value="复婚"/>复婚 &nbsp;
     <input name="maritalStatus" type="radio"  value="再婚"/>再婚 &nbsp;<i id="sexTag"></i></li>
       <li><label>手机号码<b>*</b></label><input name="mobile" id="mobile" type="text" class="dfinput" /><i id="mobileTag"></i></li>
       <li><label>学历<b>*</b></label>
      <select name="education" id="education"  class="dfinput">
       <option value="小学" >小学</option>
     <option value="初中">初中</option>
      <option value="高中">高中</option>
      <option value="大专">大专</option>
      <option value="本科">本科</option>
      <option value="研究生">研究生</option>
      <option value="博士">博士</option>
     </select><i id="educationTag"></i></li>
    <li><label>&nbsp;</label><input id="save_click" type="button" class="btn" value="确认保存"/><i id="contentsTag"></i></li>
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