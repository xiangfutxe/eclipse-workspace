<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<HEAD>
<TITLE>会员用户界面</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META http-equiv=Pragma content=no-cache>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Expires content=-1000>
<LINK href="css/admin.css" type="text/css" rel="stylesheet">
</HEAD>
<FRAMESET border=0 frameSpacing=0 rows="50, *" frameBorder=0>
<FRAME name=header src="top.jsp" frameBorder=0 noResize scrolling=no>
<FRAMESET cols="190, *">
<FRAME name=menu src="left.jsp" frameBorder=0 noResize>
<FRAME name=main src="user/index.action" frameBorder=0 noResize scrolling=yes>
</FRAMESET>
</FRAMESET>
<noframes>
</noframes>
</HTML>
