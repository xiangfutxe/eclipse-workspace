<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="com.pojo.User"%>
<%@ page import="com.utils.StringUtil"%>
<%
User user_2 = (User)request.getSession().getAttribute("ht_user");
String path_1 = request.getContextPath();
%>


 <div class="header">
<div class="head clearfix">
	<div class="logo"><img src="images/logo.png" alt="华太"/></div>
    <div class="tel">
    <span><%if(user_2==null){ %><a href="UserServlet.do?method=login">会员登录</a>
    &nbsp;|&nbsp;<a href="UserServlet.do?method=user_add_branch">免费注册</a>
    <%}else{ %><%=StringUtil.notNull(user_2.getUserId())%>(<a href="UserServlet.do?method=login_out">退出</a>)<%} %>
    &nbsp;|&nbsp;<a href="ContactServelt.do?method=C_801">联系我们</a></span></div>
</div>
</div>