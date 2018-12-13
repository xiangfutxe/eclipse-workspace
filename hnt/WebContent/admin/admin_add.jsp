<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.Job" %>

<%@page import="com.ssm.pojo.Dept" %>

<%@page import="com.ssm.utils.StringUtil" %>
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
		if(rankstr[9][1].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增员工</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin/AdminServlet.do?method=list">员工管理</a><span class="crumb-step">&gt;</span><span>新增员工</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/AdminServlet?method=saveAdmin" method="post" id="myform" name="myform">
                <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="97%">
                        <tbody>
                            <tr>
                                <th width="120px"><i class="require-red">*</i>用户昵称：</th>
                                <td>
                                    <input class="common-text required" id="adminName" name="adminName"  value="" type="text">&nbsp;<span id="nameTag"></span><span id="nameTag1"></span>
                                </td>
                            </tr>
                             <tr>
                                <th width="120px"><i class="require-red">*</i>真实姓名：</th>
                                <td>
                                    <input class="common-text required" id="realName" name="realName"  value="" type="text">&nbsp;<span id="realNameTag"></span>
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
                                <th><i class="require-red">*</i>手机号码：</th>
                                <td>
                                    <input class="common-text required" id="tel" name="tel" value="" type="text">&nbsp;<span id="telTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>所属部门：</th>
                                <td>
                                     <select id="deptId" name="deptId">
                                <%List<Dept> depts = (List<Dept>) request.getAttribute("depts"); 
                                for(int i=0;i<depts.size();i++){
                                %>
                                <option value="<%=depts.get(i).getId()%>"><%=depts.get(i).getName()%></option>
                                <%} %>
                                </select>&nbsp;<span id=deptIdTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>所属职务：</th>
                                <td>
                                <select id="jobId" name="jobId">
                                <%List<Job> jobs = (List<Job>) request.getAttribute("jobs"); 
                                for(int i=0;i<jobs.size();i++){
                                %>
                                <option value="<%=jobs.get(i).getId()%>"><%=jobs.get(i).getName()%></option>
                                <%} %>
                                </select>
                                   &nbsp;<span id="jobIdTag"></span>
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
  var reg_tel = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证

	$("#adminName").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/AdminServlet?method=isExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					adminName : $("#adminName").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方
				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data.tag;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					if(d=="0"){
					 $("#nameTag1").text("用户名不能为空！").css({"color":"red","font-size":"12px"});
					 $("#nameTag").text("");
					 }
					else if(d=="1") {
					 $("#nameTag1").text("用户名已存在！").css({"color":"red","font-size":"12px"});
					  $("#nameTag").text("");
					 }
					else if(d=="2"){
					  $("#nameTag1").text("√").css({"font-family":"Arial","color":"green","font-size":"12px"});
					   $("#nameTag").text("");
					 }
					else $("#nameTag").text("");
					if(getByteLen($("#adminName").val())>50){
					 $("#nameTag1").text("用户名字数不能大于25！").css({"color":"red","font-size":"12px"});
					  $("#nameTag").text("");
					 }
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	});
	
	$("#password").blur(function() {
		if($("#password").val()=="")$("#passwordTag").text("用户密码不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($(this).val())>50) $("#passwordTag").text("用户密码字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#passwordTag").text("");
	});
	$("#repassword").blur(function() {
		if($("#repassword").val()!=$("#password").val())$("#repasswordTag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"12px"});
		else if(getByteLen($(this).val())>50) $("#repasswordTag").text("用户密码字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#repasswordTag").text("");
	});
	$("#realName").blur(function() {
		if($("#realName").val()=="")$("#realNameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"});
		else $("#realNameTag").text("");
	});
	
	$("#jobId").blur(function() {
		if($("#jobId").val()=="")$("#jobIdTag").text("职务信息不能为空！").css({"color":"red","font-size":"12px"});
		else $("#jobIdTag").text("");
	});
	
	$("#deptId").blur(function() {
		if($("#deptId").val()=="")$("#jobIdTag").text("部门信息不能为空！").css({"color":"red","font-size":"12px"});
		else $("#deptIdTag").text("");
	});
	$("#tel").blur(function() {
		if($("#tel").val()=="")$("#yzmTag").text("验证码不能为空！").css({"color":"red","font-size":"12px"});
		if(!(reg_tel.test($("#tel").val())))
			$("#telTag").text("手机号码格式有误！").css({"color":"red","font-size":"12px"});
		else $("#telTag").text("");
	});
	$("#yzm").blur(function() {
		if($("#yzm").val()=="")$("#yzmTag").text("验证码不能为空！").css({"color":"red","font-size":"12px"});
		else $("#yzmTag").text("");
	});
	
	$("#sbtn").click(function(){
	if (!confirm("确认提交注册员工信息？")) {
								return false;
							} else {
		var errorstr = "";
		if($("#adminName").text()!=""){
		$("#nameTag1").text("用户名不能为空！").css({"color":"red","font-size":"12px"});
		$("#nameTag").text("");
			errorstr ="1";
		}else if($("#nameTag1").val()!=""){
			errorstr ="1";
		}
		if($("#password").val()==""){
			$("#passwordTag").text("用户密码不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#passwordTag").text()!=""){
			errorstr ="1";
		}
	
		if($("#repassword").val()!=$("#password").val()){
			$("#repasswordTag").text("两次输入的密码不一致，请重试。").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#repasswordTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#realName").val()==""){
			$("#realNameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#realNameTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#tel").val()==""){
			$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#telTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#deptId").val()==""){
			$("#deptIdTag").text("部门信息不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#deptIdTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#jobId").val()==""){
			$("#jobIdTag").text("部门信息不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#jobIdTag").text()!=""){
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
