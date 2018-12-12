﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>


</head>

<body style="background:url(images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="main.jsp" target="_parent"><img src="images/logo.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">
    <li><a href="index.jsp" target="rightFrame" class="selected"><img src="images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
     <li><a href="ContractServlet?method=list" target="rightFrame"><img src="images/icon05.png" title="合同查询" /><h2>文件管理</h2></a></li>
   
    <li><a href="AdminServlet?method=admin_psw_edit"  target="rightFrame"><img src="images/icon06.png" title="系统设置" /><h2>密码修改</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><a href="#">广东烟草烟草湛江有限公司</a></li>
    <li><a href="AdminServlet?method=logout">退出</a></li>
    </ul>
     
    <div class="user">
    <span>${sessionScope.weiteng_admin.adminName}</span>
    <i>消息</i>
    </div>    
    
    </div>
</body>
</html>
