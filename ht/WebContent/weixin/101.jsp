<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Modular" %>
<%@page import="com.utils.StringUtil" %>
<% 
String message = (String) request.getAttribute("message");
if(!StringUtil.notNull(message).equals("")){
out.println(StringUtil.notNull(message));
}else{
Modular mod = (Modular) request.getAttribute("modular");
 %>
<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="js/jquery.min.js?v=01291"></script>
<script charset="utf-8" src="js/global.js?v=01291"></script>
<script charset="utf-8" src="js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="js/template.js?v=01291"></script>

<link rel="stylesheet" href="css/bootstrap.css?v=01291">
<link rel="stylesheet" href="css/style.css?v=1?v=01291">
<link rel="stylesheet" href="css/member.css?v=01291">
<link rel="stylesheet" href="css/order3.css?v=01291"><meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title><%=mod.getTitle() %></title>
</head>
<body>
<div class="backgroud">
<div class="container">

<h4>
&nbsp;
</h4>
<div>
<%=mod.getContents() %>
</div>

</div>
</div>
</body></html>
<%} %>
