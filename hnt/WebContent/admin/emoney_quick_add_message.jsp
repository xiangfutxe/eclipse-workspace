<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.utils.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}
	String message = (String) request.getAttribute("message");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>充值申请</title>
    
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
<script src="js/calendar.js"></script>
  </head>
  
  <body>
   <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>财务管理
            <span class="crumb-step">&gt;</span>现金充值</div>
        </div>
        <div class="result-wrap">
       
            <div class="content-list">
              <ul>
           
             <li>
               <%=StringUtil.notNull(message)%>
             </li>
               <li>
             继续<a href="admin/ChargeApplyServlet?method=emoney_quick_add">现金充值</a>
             或 <a href="javascript:history.go(-1);">返回上一页</a>
             </li>
              </ul>
 </div>
 
 </div>
 </div>
 </div>
 
  </body>
     <script type="text/javascript" src="js/custom.js"></script>
 <script type="text/javascript">
	$(function() {
  
  	  $("#btn1").click(function(){
	  	  if (!confirm("确认提交结算！")) {
				window.event.returnValue = false;
			}
			else{
	       		 $('#myform').submit();
	       		 window.event.returnValue = true;
			}
		 });
   });
   
        
        
   </script>
</html>
