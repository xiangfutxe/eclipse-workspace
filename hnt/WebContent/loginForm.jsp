<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试接收JSON格式的数据</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-3.2.1.min.js" ></script>
	<script type="text/javascript" src="js/json2.js" ></script>
	<script type="text/javascript">
	$(document).ready(function(){
		testRequestBody();
	});
	function testRequestBody(){
		$.ajax({
			url:"json/testRequestBody",
			dataType:"json",
			type:"post",
			contentType:"application/json",
			data:JSON.stringify({id:1,name:"Spring MVC企业应用实战"}),
			async: true,
			success:function(data){
				console.log(data);
				$("#id").html(data.id);
				$("#name").html(data.name);
				$("#author").html(data.author);
			},
			error:function(){
				alert("数据发送失败");
			}
		});
	}
	</script>
  </head>
  
  <body>
  <form action="login" method="post">
  用户名：<input type="text" id="loginname" name="loginname" />
  <input type="submit" value="提交">
  </form>
  </body>
</html>
