<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.utils.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}
	String message = (String) request.getAttribute("message");
	String tag = (String) request.getAttribute("tag");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>报单中心</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
      <script type="text/javascript" src="js/jquery.js"></script>
     
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="user/index.jsp">首页</a><span class="crumb-step">&gt;</span>会员管理<span class="crumb-step">&gt;</span>服务中心(隐藏)
            <span class="crumb-step">&gt;</span>信息提示
            </div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
			<ul>
            <li>
               <%=StringUtil.notNull(message)%>
             </li>
			<li>&nbsp;</li>
			    <li>
             <a href="admin/CenterServlet?method=list_hide">隐藏服务店列表</a>
             <%if(StringUtil.notNull(tag).equals("1")){ %>&nbsp;|&nbsp;<a href="#" onclick="history.go(-1);">返回上一页</a><%} %>
             </li>
             </ul>
            </div>
        </div>
    </div>
    </div>
  </body>
   <script type="text/javascript" src="js/custom.js"></script>
 
</html>
