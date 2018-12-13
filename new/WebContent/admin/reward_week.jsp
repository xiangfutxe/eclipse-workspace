<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
String startTime = (String)request.getAttribute("startTime");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}
	String message = (String) request.getAttribute("message");
	Integer tag = (Integer) request.getAttribute("tag");
	String state = (String) request.getAttribute("state");
	Integer weekTag = (Integer) request.getAttribute("weekTag");
	String message1 = (String) request.getAttribute("message1");
	
	
	if (!StringUtil.notNull(message1).equals("")) {
		out.println("<script>");
		out.println("alert('"+message1+"')");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[7][1].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工资结算</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/jquery.js"></script>
 <script type="text/javascript" src="js/lhgcalendar.min.js"></script>
	 <script type="text/javascript">
	 $(function(){
    $('#endTime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
});
</script>
  </head>
  
  <body>
   <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>工资管理<span class="crumb-step">&gt;</span><span>工资结算</span></div>
        </div>
        <div class="result-wrap">
         <form id="myform" name="myform"  method="post">
		  <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
		   <input type="hidden" name="weekTag" id="weekTag" value="<%=weekTag%>"/> 
		   <input type="hidden" name="startTime" id="startTime" value="<%=startTime%>"/> 
            <div class="content-list">
              <table class="center-tab" width="100%">
                        <tbody>
             <tr> <td>请按以下步骤进行结算，如发现结算有误，在结算确认前，可通过点击“初始化至本次结算前状态”按钮进行恢复，然后按照步骤重新结算：</td> </tr>
             <tr><td><h3><% if(tag==1){%><%=StringUtil.notNull(message) %><%}else{ %>
             结算时间为：<%=StringUtil.notNull(startTime)%>至<input type="text" name="endTime" id="endTime"/><%} %></h3></td></tr>
             <%if(!StringUtil.notNull(state).equals("1")){ %> <tr><td> <input id="btn1" class="btn" value="开始结算" type="button" <%if(tag!=2){ %>disabled="disabled"<%} %>/></td></tr>
             <%} %>
  <%if(StringUtil.notNull(state).equals("1")){ %>
  <tr>
  <td>
  <input id="btn3" class="btn" value="结算确认" type="button" <%if(!StringUtil.notNull(state).equals("1")){%>disabled="disabled"<%} %>/>&nbsp;
  <input id="btn2" class="btn" value="初始化至本次结算前状态" type="button" <%if(StringUtil.notNull(state).equals("2")){%>disabled="disabled"<%} %>/>
  &nbsp;</td></tr>
  <%} %>
  </tbody>
</table>
 </div>
 </form>
 </div>
 </div>
 </div>
      <script type="text/javascript" src="js/custom.js"></script>
 <script type="text/javascript">
	$(function() {
  
  	 	 $("#btn1").click(function(){
	  	  if (!confirm("确认提交开始进行结算！")) {
				return false;
			}
			else{
				$("#myform").attr("action", "admin/RewardServlet.do?method=admin_reward_week_0");
	       		 $("#myform").submit();
	       		 return  true;
			}
		 });
		 
		  $("#rbtn1").click(function(){
	  	  if (!confirm("确认提交晋升级别！")) {
				return false;
			}
			else{
				$("#myform").attr("action", "admin/RewardServlet.do?method=admin_reward_week_1");
	       		 $("#myform").submit();
	       		 return true;
			}
		 });
		 
		   $("#rbtn2").click(function(){
	  	  if (!confirm("确认提交幸运奖结算！")) {
				return false;
			}
			else{
				$('#myform').attr("action", "admin/RewardServlet.do?method=admin_reward_week_2");
	       		 $('#myform').submit();
	       		 return true;
			}
		 });
		   $("#rbtn3").click(function(){
	  	  if (!confirm("确认提交拓展奖结算！")) {
				return false;
			}
			else{
				$('#myform').attr("action", "admin/RewardServlet.do?method=admin_reward_week_3");
	       		 $('#myform').submit();
	       		 return true;
			}
		 });
		   $("#rbtn4").click(function(){
	  	  if (!confirm("确认提交培育奖结算！")) {
				return false;
			}
			else{
				$('#myform').attr("action", "admin/RewardServlet.do?method=admin_reward_week_4");
	       		 $('#myform').submit();
	       		 return true;
			}
		 });
		  $("#rbtn5").click(function(){
	  	  if (!confirm("确认提交管理奖结算！")) {
				return false;
			}
			else{
				$('#myform').attr("action", "admin/RewardServlet.do?method=admin_reward_week_5");
	       		 $('#myform').submit();
	       		 return true;
			}
		 });
		   $("#rbtn6").click(function(){
	  	  if (!confirm("确认提交物流补贴结算！")) {
				return false;
			}
			else{
				$('#myform').attr("action", "admin/RewardServlet.do?method=admin_reward_week_6");
	       		 $('#myform').submit();
	       		 return true;
			}
		 });
 $("#rbtn7").click(function(){
	  	  if (!confirm("确认提交分红结算！")) {
				return false;
			}
			else{
				$('#myform').attr("action", "admin/RewardServlet.do?method=admin_reward_week_7");
	       		 $('#myform').submit();
	       		 return true;
			}
		 });
$("#rbtn8").click(function(){
	  	  if (!confirm("确认提交消费奖结算！")) {
				return false;
			}
			else{
				$('#myform').attr("action", "admin/RewardServlet.do?method=admin_reward_week_8");
	       		 $('#myform').submit();
	       		 return true;
			}
		 });

$("#rbtn10").click(function(){
	  	  if (!confirm("确认提交其它费用结算！")) {
				return false;
			}
			else{
				$('#myform').attr("action", "admin/RewardServlet.do?method=admin_reward_week_10");
	       		 $('#myform').submit();
	       		 return true;
			}
		 });



		 
		  $("#btn2").click(function(){
	  	  if (!confirm("确认初始化至本次结算前状态！")) {
				return false;
			}
			else{
			$('#myform').attr("action", "admin/RewardServlet.do?method=admin_reward_week_reset");
	       		 $('#myform').submit();
	       		return true;
			}
		 });
		 
		  $("#btn3").click(function(){
	  	  if (!confirm("确认提交结算确认，确认后将不能重新计算本次结算的！")) {
				return false;
			}
			else{
			$('#myform').attr("action", "admin/RewardServlet.do?method=admin_reward_week_confirm");
	       		 $('#myform').submit();
	       		 return true;
			}
		 });
		  $("#btn4").click(function(){
	  	  if (!confirm("确认预览工资？")) {
				 return false;
			}
			else{
			$('#myform').attr("action", "admin/RewardServlet.do?method=admin_reward_week_list");
	       		 $('#myform').submit();
	       		 return true;
			}
		 });
   });
        
   </script>
  </body>

</html>
<%
System.out.println("weekTag:"+weekTag);
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>
