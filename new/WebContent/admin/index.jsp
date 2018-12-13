<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font">&#xe06b;</i><span>欢迎使用直销管理系统，直销管理的首选工具。</span></div>
        </div>
        <div class="result-wrap">
           <div class="result-title">
                <h1><i>温馨提示</i></h1>
				 </div>
            <div class="result-content">
                <ul class="sys-info-list">
			     <li><span class="res-info">1、为了保障您的个人账户安全，请不要在网吧及公共场所登录系统。</span></li>
			
                    <li><span class="res-info">2、请将您的登录密码和二级密码设置为8位以上，并用字符、字母和数字的组合。</span></li>
				
                    <li><span class="res-info"> 3、请您在离开电脑旁时退出系统，已防止他人进入系统进行恶意操作。</span></li>
			
                    <li><span class="res-info"> 4、如系统有任何的操作异常，请截图保存并及时联系管理员。</span></li>
				
                   </ul></div>
              </div>	
              </div>	
    <!--/main-->
    </div>
  </body>
</html>
<%
}
%>