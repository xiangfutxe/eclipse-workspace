<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.dao.UserDao" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@ include file="check.jsp" %>  

<%
UserDao userDao = new UserDao();
User user = userDao.findByUserId("AA000000");
if(user!=null) out.println(user.getUserName());
else out.println("bug");

%>
