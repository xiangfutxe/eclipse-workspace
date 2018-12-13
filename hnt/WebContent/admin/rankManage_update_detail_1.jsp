<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][22].equals("1")||admin.getState().equals("1")){

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>级别调整</title>
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
       <script type="text/javascript" src="js/custom.js"></script>
     
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>会员管理<span class="crumb-step">&gt;</span><span>管理级别调整</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                 <form action="admin/RankManageUpdateDetailServlet?method=admin_rankManage_update" method="post" id="myform" name="myform">
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="10%"><i class="require-red">*</i>会员ID：</th>
                                <td><input type="text" name="userId" id="userId"><span id="usermsg"></span><span id="userIdTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary btn4 mr10" value="提交" type="button">
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

$("#userId").blur(
				function() {
					$.ajax({
						type : "post",
						url : "admin/UserServlet?method=userAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
						data : {//设置数据源
							userId : $("input[name=userId]").val()
						//这里不要加","  不然会报错，而且根本不会提示错误地方
						},

						dataType : "json",//设置需要返回的数据类型

						success : function(data) {
							var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
							if (d.userName == "") {
								$("#usermsg").text("");
							} else{
								$("#usermsg").text("" + d.userName + "")
										.css({
											"color" : "green",
											"font-size" : "12px"
										});
							}

						},

						error : function() {

							alert("系统异常，请稍后重试！");

						}//这里不要加","
					});
				});
	$("#sbtn").click(function(){
	 if (!confirm("确认提交操作？")) {
				return false;
			}else{
		var errorstr = "";
		if($("#userId").val()==""){
			$("#userIdTag").text("会员编号不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#userIdTag").val()!=""){
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
