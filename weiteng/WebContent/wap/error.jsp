<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="java.util.*"%>
<%
String message = (String) request.getAttribute("message");
out.println("message:"+message);
 %>
