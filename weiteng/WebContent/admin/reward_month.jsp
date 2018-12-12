<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Constants" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
String token = (String)request.getSession().getAttribute("token");
String startTime = (String)request.getAttribute("startTime");
	if (admin == null) {
		out.println("<script>");
		out.println("window.location.href='login.jsp';");
		out.println("</script>");
	}
	String message = (String) request.getAttribute("message");
	Integer tag = (Integer) request.getAttribute("tag");
	String state = (String) request.getAttribute("state");
	Integer monthTag = (Integer) request.getAttribute("monthTag");
	String message1 = (String) request.getAttribute("message1");
	
	
	if (!StringUtil.notNull(message1).equals("")) {
		out.println("<script>");
		out.println("alert('"+message1+"')");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[7][1].equals("1")||admin.getState()-1==0){
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理-佣金结算</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/lhgcalendar.min.js"></script>
	 <script type="text/javascript">
	 $(function(){
   		 $('#endTime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
		});
</script>
</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
     <li><a href="#">佣金管理</a></li>
      <li><a href="#">佣金结算</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
    
    
    <div class="info"><b>请按以下步骤进行结算，如发现结算有误，在结算确认前，可通过点击“初始化至本次结算前状态”按钮进行恢复，然后按照步骤重新结算：</b></div>

      <form id="myform" name="myform"  method="post">
		  <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
		   <input type="hidden" name="monthTag" id="monthTag" value="<%=monthTag%>"/> 
		   <input type="hidden" name="startTime" id="startTime" value="<%=startTime%>"/> 
     <ul class="infolist">
    <li><span><% if(tag==1){%><%=StringUtil.notNull(message) %><%}else{ %>
             结算时间为：<%=StringUtil.notNull(startTime)%>&nbsp;至&nbsp;<input type="text" name="endTime" id="endTime"/><%} %>
             </span></li>
      <% if(tag!=1){%> <li><span><a id="btn1" class="ibtn">确认周期</a></span></li>
            <%} %>
          <% if(StringUtil.notNull(state).equals("1")){ %>
 <li><span> 如果确认结算周期时间段无误，请点击开始结算。</span></li>
  <%}else if(StringUtil.notNull(state).equals("2")){ %>
 <li><span>请在结算明细中认真核对奖金，奖金确认后无法撤回。</span></li>
  <%} %>
            <li> <% if(tag==1){
if(StringUtil.notNull(state).equals("1")){ %>
 <span><a id="btn2" class="ibtn">开始结算</a>&nbsp;</span>
 
<%}else if(StringUtil.notNull(state).equals("2")){ %>
 <span><a id="btn3" class="ibtn">结算确认</a>&nbsp;</span>
 <%} %>
 <span><a id="btn4" class="ibtn">初始化至本次结算前状态</a> </span>
  <%} %>
 </li>
    </ul>
    </form>
    <div class="xline"></div>
    
    
    </div>
    <script type="text/javascript">
	$(function() {
  
  	 	 $("#btn1").click(function(){
	  	  if (!confirm("确认提交结算周期进行结算！")) {
				return false;
			}
			else{
				var error ="";
				if($("#endTime").val()==""){
					error="结算周期不能为空！";
					alert(error);
					return  false;
				}else{
				$("#myform").attr("action", "RewardServlet?method=admin_reward_month_0");
	       		 $("#myform").submit();
	       		 return  true;
				}
			}
		 });
		 
		  $("#btn2").click(function(){
	  	  if (!confirm("确认提交开始进行结算，结算可能需要花费一点时间，确认后请耐心等待！")) {
				return false;
			}
			else{
				$("#myform").attr("action", "RewardServlet?method=admin_reward_month_1");
	       		 $("#myform").submit();
	       		 return true;
			}
		 });
		 
		  $("#btn4").click(function(){
	  	  if (!confirm("确认初始化至本次结算前状态！")) {
				return false;
			}
			else{
			$('#myform').attr("action", "RewardServlet?method=admin_reward_month_reset");
	       		 $('#myform').submit();
	       		return true;
			}
		 });
		 
		  $("#btn3").click(function(){
	  	  if (!confirm("确认提交结算确认，确认后将不能重新计算本次结算的！")) {
				return false;
			}
			else{
			$('#myform').attr("action", "admin/RewardServlet?method=admin_reward_month_confirm");
	       		 $('#myform').submit();
	       		 return true;
			}
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