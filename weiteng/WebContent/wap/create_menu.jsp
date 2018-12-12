<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Token" %>
<%@page import="com.ssm.dao.TokenDao" %>
<%@page import="java.util.Date" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="com.ssm.weixin.utils.MenuUtil" %>
<%@page import="java.io.UnsupportedEncodingException" %>
<%@page import="java.net.URLEncoder" %>
<%@page import="com.ssm.dao.TokenDao" %>
<%@page import="com.ssm.weixin.pojo.menu.Menu" %>
<%@page import="com.ssm.weixin.pojo.menu.CreateMenu" %>

<%

Timestamp date = new Timestamp(new Date().getTime());
TokenDao tokenDao =  new TokenDao();
String token = tokenDao.getToken(date);
CreateMenu getMenu = new CreateMenu();
if(null!=token){
	boolean result = MenuUtil.createMenu(getMenu.create(), token);
	if(result)
		out.println("菜单创建成功！");
	else
		out.println("菜单创建失败！");
}
%>
