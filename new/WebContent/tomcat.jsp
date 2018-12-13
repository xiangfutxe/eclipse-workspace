<%--
Document : memory
Created on : 2016-01-28, 10:24:17
Author : Administrator
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tomcat.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <%
double total = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);
double max = (Runtime.getRuntime().maxMemory()) / (1024.0 * 1024);
double free = (Runtime.getRuntime().freeMemory()) / (1024.0 * 1024);
out.println("Java 虚拟机试图使用的最大内存量(当前JVM的最大可用内存)maxMemory(): " + max + "MB<br/>");
out.println("Java 虚拟机中的内存总量(当前JVM占用的内存总数)totalMemory(): " + total + "MB<br/>");
out.println("Java 虚拟机中的空闲内存量(当前JVM空闲内存)freeMemory(): " + free + "MB<br/>");
out.println("因为JVM只有在需要内存时才占用物理内存使用，所以freeMemory()的值一般情况下都很小，<br/>" +
"而JVM实际可用内存并不等于freeMemory()，而应该等于 maxMemory()-totalMemory()+freeMemory()。<br/>");
out.println("JVM实际可用内存: " + (max - total + free) + "MB<br/>");
out.println("jspcn");
%>
  </body>
</html>
