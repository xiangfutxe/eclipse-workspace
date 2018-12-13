<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
		
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>密码修改</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>系统管理<span class="crumb-step">&gt;</span><span>修改密码</span></div>
        </div>
        <div class="result-wrap">
            <form action="admin/AdminServlet?method=admin_password_update" method="post" id="myform" name="myform">
                <div class="config-items">
                    <div class="config-title">
                        <h1><i class="icon-font">&#xe00a;</i>管理员密码修改</h1>
                    </div>
                    <div class="result-content">
                        <table width="100%" class="insert-tab">
                            <tbody><tr>
                                <th width="15%"><i class="require-red">*</i>用户名：</th>
                                <td><label><%=admin.getAdminName() %></label></td>
                            </tr>
                                <tr>
                                    <th><i class="require-red">*</i>旧密码：</th>
                                    <td><input type="password" id="pwd" name="pwd" class="common-text"> <span id="pwdTag"></span></td>
                                </tr>
                                <tr>
                                    <th><i class="require-red">*</i>新密码：</th>
                                    <td><input type="password" id="password"  name="password" class="common-text"> <span id="passwordTag"></span></td>
                                </tr>
                                <tr>
                                    <th><i class="require-red">*</i>确认新密码：</th>
                                    <td><input type="password" id="repassword"  name="repassword" class="common-text"> <span id="repasswordTag"></span></td>
                                </tr>
                                <tr>
                                    <th></th>
                                    <td>
                                        <input type="submit" value="提交" class="btn btn-primary btn4 mr10" onclick="checkcfm();" />
        
                                        <input type="button" value="返回" onclick="history.go(-1)" class="btn btn4">
                                    </td>
                                </tr>
                            </tbody></table>
                    </div>
                </div>
                </form>
                </div>

    </div>
    <!--/main-->
    </div>
  </body>
  
 <script language="JavaScript">
  function checkcfm() {
        if (!confirm("确认要修改密码，修改成功后需要重新登录？")) {
            window.event.returnValue = false;
        }
    }
 
$(function() {
	
	$("#pwd").blur(function() {
		if($("#pwd").val()=="")$("#pwdTag").text("旧密码不能为空！").css({"color":"red","font-size":"9px"});
		else $("#pwdTag").text("");
	});
	$("#password").blur(function() {
		if($("#password").val()=="")$("#passwordTag").text("新密码不能为空！").css({"color":"red","font-size":"9px"});
		else $("#passwordTag").text("");
	});
	$("#repassword").blur(function() {
		if($("#repassword").val()!=$("#password").val())$("#repasswordTag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"9px"});
		else $("#repasswordTag").text("");
	});
});
</script>
</html>
<%
}
%>