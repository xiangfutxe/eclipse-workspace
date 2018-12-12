<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
User up_user = (User) request.getSession().getAttribute("up_user");
String token = (String)request.getSession().getAttribute("token");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		 String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[2][4].equals("1")||admin.getState().equals("1")){

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>服务商变更</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>会员管理<span class="crumb-step">&gt;</span><span>服务商变更</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/UserServlet.do?method=admin_belong_update_save" method="post" id="myform" name="myform" >
                <input type="hidden" name="id" value='<%=up_user.getUserId()%>'>
                <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="10%"><i class="require-red">*</i>用户ID：</th>
                                <td><%=up_user.getUserId() %>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>用户名：</th>
                                <td>
                                    <%=up_user.getUserName() %>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>当前服务商：</th>
                                <td>
                              <%=up_user.getUserByBelongId()%>
                                </td>
                            </tr>
                           
                              <tr>
                                <th><i class="require-red">*</i>最新服务商：</th>
                                <td>
                                   <input type="text" name="belongId" id="belongId">
										  &nbsp;<span id="belongUser"></span>
                                </td>
                                </tr>
                                <tr>
									<th><i class="require-red">*</i>服务区域：</th>
									<td><select id="nodeTag" name="nodeTag"
										class="common-text required">
											<option value="0">--请选择--</option>
									</select>&nbsp;<span id="nodeTagmsg"></span>
									</td>
								</tr>
                                <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary btn4 mr10" value="提交" type="button">
                                    <input class="btn btn4" onClick="history.go(-1)" value="返回" type="button">
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
$("#belongId").blur(
				function() {
					$.ajax({
						type : "post",
						url : "admin/UserServlet.do?method=belongAdminAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
						data : {//设置数据源
							userId : $("input[name=belongId]").val()
						//这里不要加","  不然会报错，而且根本不会提示错误地方
						},

						dataType : "json",//设置需要返回的数据类型

						success : function(data) {
							var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
							if (d.userName == "") {
								$("#belonguser").text("");
								$("#nodeTag").empty();
								$("#nodeTagmsg").text("查无此人，请重新输入！").css({
									"color" : "red",
									"font-size" : "12px"
								});
							} else if(d.state==0){
								$("#belonguser").text("");
								$("#nodeTag").empty();
								$("#nodeTagmsg").text("查无此人，请重新输入！").css({
									"color" : "red",
									"font-size" : "12px"
								});
							}else {
								$("#nodeTag").empty();
								if (d.nodeTag == 1) {
									$("#nodeTag").append(
											'<option value="1">A区</option>');
									$("#nodeTag").append(
											'<option value="2">B区</option>');
									$("#nodeTagmsg").text("");
								} else if (d.nodeTag == 2) {
									$("#nodeTag").append(
											'<option value="1">A区</option>');
									$("#nodeTagmsg").text("");
								} else if (d.nodeTag == 3) {
									$("#nodeTag").append(
											'<option value="2">B区</option>');
									$("#nodeTagmsg").text("");
								} else {
									$("#nodeTagmsg").text(
											"该用户的AB区域已满，请重新选择安置用户！").css({
										"color" : "red",
										"font-size" : "12px"
									});
								}
								$("#belonguser").text("" + d.userName + "")
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
	 if (!confirm("确认提交变更服务商？")) {
				return false;
			}else{
		var errorstr = "";
		
		
		if($("#belongId").val()==""){
					$("#belonguser").text("接点人不能为空！").css({"color":"red","font-size":"12px"});
					eerrorstr ="1";
				}
				else if($("#belonguser").text()=="该用户的AB区域已满，请重新选择安置用户！"
				||$("#belonguser").text()=="接点人不能为空！"
				|| $("#belonguser").text()=="查无此人，请重新输入！"){
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