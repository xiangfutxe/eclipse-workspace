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
			if(ranks[9][2].equals("1")||admin.getState()-1==0){
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
    <li><a href="#">员工管理</a></li>
    <li><a href="#">员工详情</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="AdminServlet?method=updateAdmin" id="myform" method="post">
      <input type="hidden" name="token" id="token" value="<c:out value='${sessionScope.token}' escapeXml='true' default=''/>"/>
       <input type="hidden" name="id" id="id" value="${admin1.id }"/>
    <ul class="forminfo">
    <li><label>登陆昵称<b>*</b></label>
   
    <input name="adminName" id="adminName" type="text" class="dfinput" value="${admin1.adminName }" readonly="readonly"/><i id="nameTag1"></i><i id="nameTag"></i></li>
    <li><label>真实姓名<b>*</b></label><input name="realName" id="realName" type="text" class="dfinput" value="${admin1.realName }"/><i id="realNameTag"></i></li>
    <li><label>手机号码<b>*</b></label><input name="tel" id="tel" type="text" class="dfinput" value="${admin1.tel }"/><i id="telTag"></i></li>
      <li><label>所在部门<b>*</b></label>
      <select name="deptId" id="deptId"  class="dfinput">
       <option value="" >--请选择所在部门--</option>
      <c:if test="${depts!=null}">
        <c:forEach items="${depts}" var="dt">
     <option value="${dt.id}" <c:if test="${dt.id==admin1.deptId }">selected</c:if>>${dt.name}</option>
     </c:forEach>
     </c:if>
     </select><i id="deptTag"></i></li>
     <li><label>所在部门<b>*</b></label>
      <select name="jobId" id="jobId"  class="dfinput">
       <option value="" >--请选择职务--</option>
     <c:if test="${jobs!=null}">
        <c:forEach items="${jobs}" var="jb">
     <option value="${jb.id}" <c:if test="${jb.id==admin1.jobId }">selected</c:if>>${jb.name}</option>
    </c:forEach>
    </c:if>
     </select><i id="deptTag"></i></li>
      <li><label>用户类型<b>*</b></label>
      <select name="state" id="state"  class="dfinput">
       <option value="2" >普通管理员</option>
      <%if(admin.getState()-1>0){ %>
     <option value="1">超级管理员</option>
     <%} %>
     </select><i id="typeTag"></i></li>
    <li><label>&nbsp;</label><input id="save_click"  type="button" class="btn" value="确认保存"/><i id="contentsTag"></i></li>
    </ul>
    </form>
     <div class="save_tip">
    	<div class="save_tiptop"><span>提示信息</span><a></a></div>
        
      <div class="save_tipinfo">
        <span><img src="images/ticon_save.png" /></span>
        <div class="save_tipright">
        <p>是否确认对信息的进行保存？</p>
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

	
	$("#realName").blur(function() {
		if($("#realName").val()=="")$("#realNameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"});
		else $("#realNameTag").text("");
	});

	$("#tel").blur(function() {
		if($("#tel").val()=="")$("#yzmTag").text("验证码不能为空！").css({"color":"red","font-size":"12px"});
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
		
		
		if($("#tel").val()==""){
			$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#telTag").text()!=""){
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
	}else{
		out.println("<script>");
		out.println("window.location.href='error_rank.jsp';");
		out.println("</script>");
	}
}
%>