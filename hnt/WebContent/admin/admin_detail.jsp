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
Admin admin1 = (Admin)request.getAttribute("admin1");

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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin/AdminServlet.do?method=list">员工管理</a><span class="crumb-step">&gt;</span><span>员工详情</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/AdminServlet?method=updateAdmin" method="post" id="myform" name="myform">
                <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                <input type="hidden" name="id" id="id" value="<%=admin1.getId()%>"/> 
                   
                    <table class="insert-tab" width="97%">
                        <tbody>
                            <tr>
                                <th width="120px"><i class="require-red">*</i>用户昵称：</th>
                                <td>
                                    <input class="common-text required" id="adminName" name="adminName"  value="<%=admin1.getAdminName() %>" type="text" readonly>&nbsp;<span id="nameTag"></span><span id="nameTag1"></span>
                                </td>
                            </tr>
                             <tr>
                                <th width="120px"><i class="require-red">*</i>真实姓名：</th>
                                <td>
                                    <input class="common-text required" id="realName" name="realName"  value="<%=admin1.getRealName() %>" type="text">&nbsp;<span id="realNameTag"></span>
                                </td>
                            </tr>
                            
                             <tr>
                                <th><i class="require-red">*</i>手机号码：</th>
                                <td>
                                    <input class="common-text required" id="tel" name="tel" value="<%=admin1.getTel() %>" type="text">&nbsp;<span id="telTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>所属部门：</th>
                                <td>
                                     <select id="deptId" name="deptId">
                                <%List<Dept> depts = (List<Dept>) request.getAttribute("depts"); 
                                for(int i=0;i<depts.size();i++){
                                %>
                                <option value="<%=depts.get(i).getId()%>" <%if(depts.get(i).getId()-admin1.getDeptId()==0){ %>selected<%} %>><%=depts.get(i).getName()%></option>
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
                                <option value="<%=jobs.get(i).getId()%>" <%if(jobs.get(i).getId()-admin1.getJobId()==0){ %>selected<%} %>><%=jobs.get(i).getName()%></option>
                                <%} %>
                                </select>
                                   &nbsp;<span id="jobIdTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary mr10" value="保存修改" type="button">
                                    <input class="btn" onClick="javascript:history.go(-1)" value="返回" type="button">
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
