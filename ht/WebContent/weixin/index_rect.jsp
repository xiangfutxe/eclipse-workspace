<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.utils.StringUtil"%>
<%
String message = (String) request.getAttribute("message");
if(!StringUtil.notNull(message).equals("")){
out.println(message);
}else{
out.println("<script>");
out.println("parent.window.location.href='weixin/index.jsp';");
out.println("</script>");
}
 %>
