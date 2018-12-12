<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

 %>

<!DOCTYPE HTML>
<HTML>
<HEAD>
	<META http-equiv=Content-Type content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
   
	 </HEAD>
  <body> 
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="admin/index.jsp" class="navbar-brand" target=main>会员界面</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="admin/index.jsp" target=main>内部采购系统</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="admin/admin_password_edit.jsp" target=main>修改密码</a></li>
                <li><a href="admin/AdminServlet.do?method=loginout" onclick="if(confirm('确定退出系统?')==false)return false;" target=main>退出</a></li>
            </ul>
        </div>
    </div>
</div>

    </body>
  
    </HTML>
