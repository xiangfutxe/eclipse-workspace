<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>温馨提示</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script language="javascript">
	$(function(){
    $('.success').css({'position':'absolute','left':($(window).width()-490)/2});
	$(window).resize(function(){  
    $('.success').css({'position':'absolute','left':($(window).width()-490)/2});
    }) ;
});  
</script> 


</head>


<body style="background:#edf6fa;">

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
     <li><a href="#">会员管理</a></li>
    <li><a href="#">温馨提示</a></li>
    </ul>
    </div>
    
    <div class="success">
    
    <h2><c:out value='${message}' escapeXml='true' default=''/></h2>
    <p>看到这个提示，可以在会员列表查看详情或继续新增会员!</p>
    <div class="reindex"><a href="member_list.do">会员列表</a>&nbsp;&nbsp;<a href="member_add.do">新增会员</a></div>
    
    </div>
   
</body>

</html>
