<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.News" %>
<%@page import="java.util.*" %>
<%@page import="com.ssm.utils.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="js/jquery.min.js?v=01291"></script>
<script charset="utf-8" src="js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="js/template.js?v=01291"></script>
<script charset="utf-8" src="js/scroll.js?v=01291"></script>
<script charset="utf-8" src="js/layer.js?v=01291"></script>

<link rel="stylesheet" href="css/bootstrap.css?v=01291">
<link rel="stylesheet" href="css/style.css?v=1?v=01291">
<link rel="stylesheet" href="css/member.css?v=01291">
<link rel="stylesheet" href="css/order3.css?v=01291">
<link rel="stylesheet" href="css/scroll.css?v=01291">

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>首页HOME</title>

</head>
<body>
<h2 style="padding:10px 0 5px;">功能说明</h2>
  
  <p><strong>一个在底部弹出的完整对话框：</strong></p>
  <pre>
<span id="demo2a"></span>
<a href="javascript:;"  class="button" id="slide">运行</a>
  </pre>
<script type="text/javascript">
$(document).ready(function(){
		$("#slide").click(function(){
			layer.open({
			    content: '您确定要刷新一下本页面吗？',
			    btn: ['确定', '取消'],
			    yes: function(index){
			      location.reload();
			      layer.close(index);
			    }
			  });
		});
});
</script>
</body></html>

