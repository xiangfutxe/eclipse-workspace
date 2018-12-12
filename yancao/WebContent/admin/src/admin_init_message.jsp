<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>超级用户初始化</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">系统管理</a></li>
    <li><a href="#">超级用户</a></li>
    <li><a href="#">初始化</a></li>
     <li><a href="#">信息提示</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form  id="myform">
    <ul class="forminfo">
    <li><c:out value='${message}' escapeXml='true' default=''/></li>
   
    <li><input id="btn" type="button" class="btn" value="返回登陆页面" onclick="javascript:location.href='login.jsp'"/></li>
    </ul>
    </form>
    
    </div>
   
</body>

</html>
