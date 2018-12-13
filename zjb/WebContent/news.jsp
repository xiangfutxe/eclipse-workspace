<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%int class_val = 4;%>
<!DOCTYPE HTML>
<html>
<head>
<title>公司简介</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<script src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>	
<!--web-fonts-->
 <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Cabin:400,700,500,600' rel='stylesheet' type='text/css'>
<!--//web-fonts-->
	<!--search jQuery-->
	<script src="js/main.js"></script>
	<link href="css/animate.min.css" rel="stylesheet"> 
<style type="text/css"> 
<!-- 
#items { 
width:100%; 
height:auto; 
margin:0 auto; 
padding:5px 0; 
clear:both; 
} 
#items ul, #items li { 
} 
#items li { 
width:100%; 
text-align: right; 
background-repeat: no-repeat; 
background-position: 50px center; 
padding-left: 0px; 
line-height:28px; 
margin-top:20px; 
color:#CCC; 
border-bottom:dashed 1px #CCC; 
} 
#items li a { 
float:left; 
text-align:left; 
line-height:28px; 
color:#666; 
text-decoration:none; 
} 
#items li a:hover { 
color:#F00; 
} 
--> 
</style> 
	<!--//search jQuery-->
</head>
<body>
		<%@ include file="header.jsp" %>


<!-- //header -->
<!-- banner -->
	<div class="banner-1 agileinfo-3">
		<div class="container">
			<%@ include file="banner.jsp" %>
		
		</div>
	</div>
<!-- //banner -->  
	 <!--/fashion-->
	  <div class="about w3-3">
		   <div class="container">

		   <h2 class="tit">新闻中心</h2>
			  
			 <!--button-->
  
<div id="items"> 
<ul> 
<c:if test="${pager != null}">
	<c:forEach var="list" items="${pager.getResultList()}">
		<li><a href="news_detail.do?id=${list.id}">${list.title }</a><fmt:formatDate value="${list.entryTime }" pattern="yyyy-MM-dd" /></li> 
	</c:forEach>
</c:if>
</ul> 
</div> 
  </div>
  <!--buttons-->
		   </div>

	<!--//fashion-->
	
	<!-- /w3ls -->
	<%@ include file="footer.jsp" %>

	<!-- //w3ls -->
</body>
</html>