<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.BankAccount" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
BankAccount ba = (BankAccount) request.getAttribute("bankAccount");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[6][3].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改账号</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="admin/bank_account.action">财务管理</a><span class="crumb-step">&gt;</span><span>账号管理</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form  method="post" id="myform" name="myform">
                 <input type="hidden" name="id" value="<%=ba.getId()%>">
                  <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th><i class="require-red">*</i>用户名：</th>
                                <td>
                                    <input class="common-text required" id="accountName" name="accountName" size="50" value="<%=StringUtil.notNull(ba.getAccountName()) %>" type="text">&nbsp;<span id="nameTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>用户账号：</th>
                                <td>
                                    <input class="common-text required" id="accountId" name="accountId" size="50" value="<%=StringUtil.notNull(ba.getAccountId()) %>" type="text">&nbsp;<span id="accountTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>开户行名称：</th>
                                <td>
                                    <input class="common-text required" id="bankName" name="bankName" size="50" value="<%=StringUtil.notNull(ba.getBankName()) %>" type="text">&nbsp;<span id="bankTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary mr10" value="提交" type="button">
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
   var reg = /^[1-9]+[0-9]*]*$/;
$(function() {
	$("#accountId").blur(function() {
		
				if($("#accountId").val()=="")$("#accountTag").text("账号不能为空！").css({"color":"red","font-size":"9px"});
					else if(!reg.test($("#accountId").val()))$("#accountTag").text("账号格式有误！").css({"color":"red","font-size":"9px"});
					else if(getByteLen($("#accountId").val())>50) $("#accountTag").text("账号字数不能大于25！").css({"color":"red","font-size":"9px"});
					else $("#accountTag").text("");
	});
	
	$("#accountName").blur(function() {
		if($(this).val()==""){
			$("#nameTag").text("用户名不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if(getByteLen($(this).val())>50) $("#nameTag").text("用户名称字数不能大于25！").css({"color":"red","font-size":"9px"});
		else $("#nameTag").text("");
	});	
	
	$("#bankName").blur(function() {
		if($(this).val()==""){
		$("#bankTag").text("开户行不能为空！").css({"color":"red","font-size":"9px"}); 
	}else if(getByteLen($(this).val())>50) $("#bankTag").text("开户行名称字数不能大于25！").css({"color":"red","font-size":"9px"});
	else $("#bankTag").text("");
	});	
	
	$("#sbtn").click(function(){
		var errorstr = "";
		if($("#accountName").val()==""){
			$("#nameTag").text("用户名不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#accountName").text()!=""){
			errorstr ="1";
		}
		
		if($("#accountId").val()==""){
			$("#accountTag").text("账号不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#nameTag").text()!=""){
			if($("#nameTag").text()!="")
				errorstr ="1";
		}
		
		if($("#bankName").val()==""){
			$("#bankTag").text("开户行不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#bankTag").text()!=""){
			errorstr ="1";
		}
		
		if(errorstr!=""){
			return false;
		}else{
       		$('#myform').attr("action", "admin/BankAccountServlet?method=update");
	       	$('#myform').submit();
       		 return ture;
		
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