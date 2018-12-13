<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String) session.getAttribute("token");
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
    
    <title>新增职务</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>员工管理<span class="crumb-step">&gt;</span><span>新增职务</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/JobServlet?method=saveJob" method="post" id="myform" name="myform">
                <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="97%">
                        <tbody>
                            <tr>
                                <th width="120px"><i class="require-red">*</i>职务名称：</th>
                                <td>
                                    <input class="common-text required" id="name" name="name"  value="" type="text">&nbsp;<span id="nameTag"></span>
                                    <span id="nameTag1"></span>
                                </td>
                            </tr>
                            
                             <tr>
                                <th><i class="require-red">*</i>职务描述：</th>
                                <td>
                                    <input class="common-text required" id="remark" name="remark" value="" type="text">&nbsp;<span id="remarkTag"></span>
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
 <script type="text/javascript">
$(function() {
	$("#name").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/JobServlet?method=isJobExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					name : $("#name").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方
				},
				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data.tag;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					if(d=="0"){
					 $("#nameTag").text("");
					 $("#nameTag1").text("职务名称不能为空。").css({"color":"red","font-size":"12px"});
					 }
					else if(d=="1") {
					 $("#nameTag1").text("职务名称已存在。").css({"color":"red","font-size":"12px"});
					  $("#nameTag").text("");
					 }else if(d=="2"){
					   $("#nameTag").text("√").css({"font-family":"Arial","color":"green","font-size":"12px"});
					    $("#nameTag1").text("");
					   }else if(getByteLen($("#name").val())>50){
							 $("#nameTag1").text("职务名称字数不能大于25。").css({"color":"red","font-size":"12px"});
							 $("#nameTag").text("");
						 }else{
						  $("#nameTag1").text("数据格式输入有误。").css({"color":"red","font-size":"12px"});
							 $("#nameTag").text("");
						 }
				},
				error : function() {
					$("#nameTag1").text("系统异常，请稍后重试！").css({"color":"red","font-size":"12px"});
							 $("#nameTag").text("");
				}//这里不要加","
				
			});
	});
	
	$("#remark").blur(function() {
		if($("#remark").val()=="")$("#remarkTag").text("职务描述不能为空！").css({"color":"red","font-size":"9px"});
		else $("#remarkTag").text("");
	});
	
	$("#sbtn").click(function(){
	if (!confirm("确认提交保存职务信息？")) {
								return false;
							} else {
		var errorstr = "";
		if($("#name").text()!=""){
		$("#nameTag1").text("职务名称不能为空！").css({"color":"red","font-size":"12px"});
		$("#nameTag").text("");
		
			errorstr ="1";
		}else if($("#nameTag1").val()!=""){
		alert("yes");
			errorstr ="1";
		}
		if($("#remark").val()==""){
			$("#remarkTag").text("职务描述不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#remarkTag").text()!=""){
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
