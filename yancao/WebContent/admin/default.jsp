<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.ssm.pojo.Admin"%>
    <%@page import="com.ssm.utils.Constants"%>
    
    
<%
Admin admin1 = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
if (admin1 == null) {
	out.println("<script>");
	out.println("parent.window.location.href='main.jsp';");
	out.println("</script>");
}
%>