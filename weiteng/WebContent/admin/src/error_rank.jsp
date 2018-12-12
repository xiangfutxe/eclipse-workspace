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
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">错误提示</a></li>
    </ul>
    </div>
    
    <div class="error">
    
    <h2>非常遗憾，您没有权限访问该页面！</h2>
    <p>看到这个提示，就自认倒霉吧!</p>
    <div class="reindex"><a href="index.jsp">返回首页</a></div>
    
    </div>
   
</body>

</html>
