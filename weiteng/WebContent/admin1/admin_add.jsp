<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.utils.StringUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
	   String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[9][0].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增管理员</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin/AdminServlet.do?method=list">管理员管理</a><span class="crumb-step">&gt;</span><span>新增管理员</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/AdminServlet.do?method=save" method="post" id="myform" name="myform">
                <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="97%">
                        <tbody>
                            <tr>
                                <th width="120px"><i class="require-red">*</i>用户名称：</th>
                                <td>
                                    <input class="common-text required" id="adminName" name="adminName"  value="" type="text">&nbsp;<span id="nameTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>用户密码：</th>
                                <td>
                                    <input class="common-text required" id="password" name="password" value="" type="password">&nbsp;<span id="passwordTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>确认密码：</th>
                                <td>
                                    <input class="common-text required" id="repassword" name="repassword" value="" type="password">&nbsp;<span id="repasswordTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary mr10" value="保存" type="button">
                                    <input class="btn" onClick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
        </div>

    </div>
    <!--/main-->
    </div>
  </body>
   <script type="text/javascript" src="js/custom.js"></script>
 <script language="JavaScript">
$(function() {
//var img = $("#progressImgage");
//var mask = $("#maskOfProgressImage");
	$("#adminName").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/AdminServlet.do?method=isExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					adminName : $("#adminName").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方
				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data.tag;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					if(d=="0") $("#nameTag").text("用户名不能为空！").css({"color":"red","font-size":"9px"});
					else if(d=="1")  $("#nameTag").text("用户名已存在！").css({"color":"red","font-size":"9px"});
					else if(d=="2")  $("#nameTag").text("用户名可用！").css({"color":"green","font-size":"9px"});
					else $("#nameTag").text("");
					if(getByteLen($("#adminName").val())>50) $("#nameTag").text("用户名字数不能大于25！").css({"color":"red","font-size":"9px"});
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	});
	
	$("#password").blur(function() {
		if($("#password").val()=="")$("#passwordTag").text("用户密码不能为空！").css({"color":"red","font-size":"9px"});
		else if(getByteLen($(this).val())>50) $("#passwordTag").text("用户密码字数不能大于25！").css({"color":"red","font-size":"9px"});
		else $("#passwordTag").text("");
	});
	$("#repassword").blur(function() {
		if($("#repassword").val()!=$("#password").val())$("#repasswordTag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"9px"});
		else if(getByteLen($(this).val())>50) $("#repasswordTag").text("用户密码字数不能大于25！").css({"color":"red","font-size":"9px"});
		else $("#repasswordTag").text("");
	});
	$("#yzm").blur(function() {
		if($("#yzm").val()=="")$("#yzmTag").text("验证码不能为空！").css({"color":"red","font-size":"9px"});
		else $("#yzmTag").text("");
	});
	
	$("#sbtn").click(function(){
	if (!confirm("确认提交注册管理员信息？")) {
								return false;
							} else {
		var errorstr = "";
		if($("#adminName").text()!=""){
		$("#nameTag").text("用户名不能为空！").css({"color":"red","font-size":"9px"});
			errorstr ="1";
		}else if($("#nameTag").val()!=""){
			if($("#nameTag").val()!="用户名可用！")
			errorstr ="1";
		}
		if($("#password").val()==""){
			$("#passwordTag").text("用户密码不能为空！").css({"color":"red","font-size":"9px"});
			errorstr ="1";
		}else if($("#passwordTag").text()!=""){
			errorstr ="1";
		}
	
		if($("#repassword").val()!=$("#password").val()){
			$("#repasswordTag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"9px"});
			errorstr ="1";
		}else if($("#repasswordTag").text()!=""){
			errorstr ="1";
		}
	
		if(errorstr!=""){
			return false;
		}else{
       		 $('#myform').submit();
       		 return true;
		
		}
		}
	});
	
});
</script>
</html>
<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>
