<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
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
	String message1 = (String) request.getAttribute("message1");
	
	
	if (!StringUtil.notNull(message1).equals("")) {
		out.println("<script>");
		out.println("alert('"+message1+"')");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[7][20].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>奖金结算</title>
    
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>奖金管理<span class="crumb-step">&gt;</span><span><a href="admin/reward_week.action">奖金结算</a></span></div>
        </div>
        <div class="result-wrap">
         <form id="myform" name="myform"  method="post">
		  <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
		   <input type="hidden" name="startTime" id="startTime" value="<%=startTime%>"/> 
            <div class="content-list">
              <table class="center-tab" width="100%">
                        <tbody>
             <tr> <td>请按以下步骤进行结算，如发现结算有误，在结算确认前，可通过点击“初始化至本次结算前状态”按钮进行恢复，然后按照步骤重新结算：</td> </tr>
             <tr><td><h3><% if(!StringUtil.notNull(message).equals("")){%><%=StringUtil.notNull(message) %><%}else{ %>
             结算时间为：<%=StringUtil.notNull(startTime)%><%}%></h3></td></tr>
            <% if(tag!=1){%> <tr><td> <input id="btn1" class="btn" value="确认周期" type="button" <%if(tag!=0){ %>disabled="disabled"<%} %>/></td></tr>
            <%} %>
            <% if(tag==1){
  if(StringUtil.notNull(state).equals("1")){ %>
  <tr><td> 如果确认结算周期时间段无误，请点击开始结算。</td></tr>
  <%}else if(StringUtil.notNull(state).equals("2")){ %>
  <tr><td>请在奖金明细中认真核对奖金，奖金确认后无法撤回。</td></tr>
  <%}} %>
  <tr>
  

  <td>
    <% if(tag==1){
if(StringUtil.notNull(state).equals("1")){ %>
 <input id="btn2" class="btn" value="开始结算" type="button"/>&nbsp;
 
<%}else if(StringUtil.notNull(state).equals("2")){ %>
 <input id="btn3" class="btn" value="结算确认" type="button" <%if(!StringUtil.notNull(state).equals("2")){%>disabled="disabled"<%} %>/>&nbsp;
 <%} %>
 <input id="btn4" class="btn" value="初始化至本次结算前状态" type="button" <%if(StringUtil.notNull(state).equals("3")){%>disabled="disabled"<%} %>/>
  <%} %>
 </td>
  <tr>
  <td>
  </td></tr>
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
	  	  if (!confirm("确认提交结算周期进行结算！")) {
				return false;
			}
			else{
				$("#myform").attr("action", "admin/DRewardServlet?method=admin_reward_day_0");
	       		 $("#myform").submit();
	       		 return  true;
			}
		 });
		 
		  $("#btn2").click(function(){
	  	  if (!confirm("确认提交开始进行结算，结算可能需要花费一点时间，确认后请耐心等待！")) {
				return false;
			}
			else{
				$("#myform").attr("action", "admin/DRewardServlet?method=admin_reward_day_1");
	       		 $("#myform").submit();
	       		 return true;
			}
		 });
		 
		  $("#btn4").click(function(){
	  	  if (!confirm("确认初始化至本次结算前状态！")) {
				return false;
			}
			else{
			$('#myform').attr("action", "admin/DRewardServlet?method=admin_reward_day_reset");
	       		 $('#myform').submit();
	       		return true;
			}
		 });
		 
		  $("#btn3").click(function(){
	  	  if (!confirm("确认提交结算确认，确认后将不能重新计算本次结算的！")) {
				return false;
			}
			else{
			$('#myform').attr("action", "admin/DRewardServlet?method=admin_reward_day_confirm");
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
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>
