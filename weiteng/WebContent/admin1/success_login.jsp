<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String message = (String)request.getAttribute("message");
if(message!=null) {
out.println("<script>");
out.println("alert('"+message+"');window.location.href='login.jsp';");
out.println("</script>");
}else{
out.println("<script>");
out.println("parent.window.location.href='../main_bg.jsp';");
out.println("</script>");
}
%>
