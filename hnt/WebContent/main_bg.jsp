<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
if(admin==null) {
out.println("<script>");
out.println("window.location.href='admin/login.jsp';");
out.println("</script>");
}
%>
<!DOCTYPE HTML>
<html>
<HEAD>
<TITLE>后台管理员界面</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META http-equiv=Pragma content=no-cache>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Expires content=-1000>
<LINK href="css/admin.css" type="text/css" rel="stylesheet">
</HEAD>
<FRAMESET border=0 frameSpacing=0 rows="50, *" frameBorder=0>
<FRAME name=header src="top_bg.jsp" frameBorder=0 noResize scrolling=no>
<FRAMESET cols="190, *">
<FRAME name=menu src="left_bg.jsp" frameBorder=0 noResize>
<FRAME name=main src="admin/index.jsp" frameBorder=0 noResize scrolling=yes>
</FRAMESET>
</FRAMESET>
<noframes>
</noframes>
</HTML>
