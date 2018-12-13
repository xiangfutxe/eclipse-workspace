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
<title>详情修改</title>
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
    <li><a href="#">详情修改</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="member_update.do" id="myform" method="post">
         <input type="hidden" name="id" id="id" value="${member.id}"/>
          <input type="hidden" name="infoId" id="infoId" value="${member.infoId}"/>
         <input type="hidden" name="version" id="version" value="${member.version}"/>
         
    <ul class="forminfo">
    <li><label>会员昵称<b>*</b></label>
    <input type="hidden" name="token" id="token" value="${sessionScope.token}"/>
    <input name="nickName" id="nickName" type="text" class="dfinput" value="${member.nickName }" readonly="readonly"/><i id="nickNameTag"></i></li>
    <li><label>会员等级<b>*</b></label>
      <select name="rank" id="rank"  class="dfinput" disabled="disabled">
       <option value="0" <c:if test="${member.rank==0}">selected</c:if>>普通会员</option>
     <option value="1" <c:if test="${member.rank==0}">selected</c:if>>青铜会员</option>
      <option value="2" <c:if test="${member.rank==0}">selected</c:if>>白银会员</option>
      <option value="3" <c:if test="${member.rank==0}">selected</c:if>>黄金会员</option>
      <option value="4" <c:if test="${member.rank==0}">selected</c:if>>铂金会员</option>
      <option value="5" <c:if test="${member.rank==0}">selected</c:if>>钻石会员</option>
     </select><i id="rankTag"></i></li>
      <li><label>真实姓名<b>*</b></label><input name="realName" id="realName" type="text" class="dfinput"  value="${member.realName }"/><i id="realNameTag"></i></li>
     <li><label>性别<b>*</b></label>
     <input name="sex" type="radio" value="男"  <c:if test='${member.sex=="男"}'>checked="checked"</c:if>/>男
	&nbsp;<input name="sex"  type="radio" value="女"  <c:if test='${member.sex=="女"}'>checked="checked"</c:if>/>女<i id="sexTag"></i></li>
     <li><label>婚姻状况<b>*</b></label>
     <input name="maritalStatus" type="radio" value="未婚" <c:if test='${member.maritalStatus=="未婚"}'>checked="checked"</c:if>/>未婚 &nbsp;
    <input name="maritalStatus" type="radio"  value="已婚" <c:if test='${member.maritalStatus=="已婚"}'>checked="checked"</c:if>/>已婚 &nbsp;
     <input name="maritalStatus" type="radio" value="离异" <c:if test='${member.maritalStatus=="离异"}'>checked="checked"</c:if>/>离异 &nbsp;
     <input name="maritalStatus" type="radio" value="复婚" <c:if test='${member.maritalStatus=="复婚"}'>checked="checked"</c:if>/>复婚 &nbsp;
     <input name="maritalStatus" type="radio" value="再婚"  <c:if test='${member.maritalStatus=="再婚"}'>checked="checked"</c:if>/>再婚 &nbsp;<i id="sexTag"></i></li>
       <li><label>手机号码<b>*</b></label><input name="mobile" id="mobile" type="text" class="dfinput"  value="${member.mobile }"/><i id="mobileTag"></i></li>
     
       <li><label>学历<b>*</b></label>
      <select name="education" id="education"  class="dfinput">
       <option value="小学" <c:if test='${member.education=="小学"}'>selected</c:if>>小学</option>
     <option value="初中" <c:if test='${member.education=="初中"}'>selected</c:if>>初中</option>
      <option value="高中" <c:if test='${member.education=="高中"}'>selected</c:if>>高中</option>
      <option value="大专" <c:if test='${member.education=="大专"}'>selected</c:if>>大专</option>
      <option value="本科" <c:if test='${member.education=="本科"}'>selected</c:if>>本科</option>
      <option value="研究生" <c:if test='${member.education=="研究生"}'>selected</c:if>>研究生</option>
      <option value="博士" <c:if test='${member.education=="博士"}'>selected</c:if>>博士</option>
     </select><i id="educationTag"></i></li>
    <li><label>&nbsp;</label><input id="edit_click" type="button" class="btn" value="确认保存"/><i id="contentsTag"></i></li>
    </ul>
    </form>
     <div class="edit_tip">
    	<div class="edit_tiptop"><span>提示信息</span><a></a></div>
        
      <div class="edit_tipinfo">
        <span><img src="images/ticon_edit.png" /></span>
        <div class="edit_tipright">
        <p>是否确认对信息的进行更新保存 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
       
        <div class="tipbtn">
        <input name="" type="button"  class="edit_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="edit_cancel" value="取消" />
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
	
	$("#edit_click").click(function(){
  $(".edit_tip").fadeIn(200);
  });
  
  $(".edit_tiptop a").click(function(){
  $(".edit_tip").fadeOut(200);
});

  $(".edit_sure").click(function(){
 		 $(".edit_tip").fadeOut(100);
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

  $(".edit_cancel").click(function(){
  $(".edit_tip").fadeOut(100);
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