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
		if(rankstr[1][6].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增分会</title>
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
      <script type="text/javascript" src="js/jquery.cityselect.js"></script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a>
            <span class="crumb-step">&gt;</span>会员管理
            <span class="crumb-step">&gt;</span><span><a class="crumb-name" href="admin/branch_add.jsp">新增协会</a></span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/BranchServlet.do?method=admin_branch_add" method="post" id="myform" name="myform">
                <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="97%">
                        <tbody>
                            <tr>
                                <th width="120px"><i class="require-red">*</i>会员编号：</th>
                                <td>
                                    <input class="common-text required" id="userId" name="userId"  value="" type="text">&nbsp;<span id="userTag"></span><span id="idTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary mr10" value="提交" type="button">
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

	$("#userId").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/BranchServlet.do?method=isExit_User",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					userId : $("#userId").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方
				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data.tag;
					if(d==0){
					 $("#idTag").text("编号不能为空！").css({"color":"red","font-size":"12px"});
					 $("#userTag").text("");
					 }
					else if(d==1) {
					 $("#idTag").text("该会员已存在协会信息！").css({"color":"red","font-size":"12px"});
					  $("#userTag").text("");
					 }
					else if(d==2) {
					$("#idTag").text("");
					$("#userTag").text(data.nickName).css({"color":"green","font-size":"12px"});
					}
					else if(d==3){
					  $("#idTag").text("未查询到相应的会员信息！").css({"color":"red","font-size":"12px"});
					$("#userTag").text("");
					 }
					 else if(d==4){
					  $("#idTag").text("数据库连接获取失败！").css({"color":"red","font-size":"12px"});
					$("#userTag").text("");
					 }
					 else if(d==5){
					  $("#idTag").text("管理员未登陆！").css({"color":"red","font-size":"12px"});
					$("#userTag").text("");
					 }else{
					  $("#idTag").text("");
					  $("#userTag").text("");
					  }
					
				},
				error : function() {
					$("#idTag").text("系统异常，请稍后重试！").css({"color":"red","font-size":"12px"});
				  $("#userTag").text("");
				}//这里不要加","
				
			});
	});
	
	
   			
	$("#sbtn").click(function(){
	if (!confirm("确认提交会员编号？")) {
								return false;
							} else {
		var errorstr = "";
		if($("#userId").text()!=""){
		$("#idTag").text("会员编号不能为空！").css({"color":"red","font-size":"12px"});
		$("#userTag").text("");
			errorstr ="1";
		}else if($("#idTag").val()!=""){
			$("#userTag").text("");
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
