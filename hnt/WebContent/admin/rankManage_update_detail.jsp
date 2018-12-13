<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.Constants" %>

<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
User up_user = (User) request.getAttribute("up_user");
String token = (String)request.getSession().getAttribute("token");
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
                <form action="admin/RankManageUpdateDetailServlet?method=admin_save" method="post" id="myform" name="myform" >
                <input type="hidden" name="id" value='<%=up_user.getUserId()%>'>
                <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="15%"><i class="require-red">*</i>用户ID：</th>
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
                                <th><i class="require-red">*</i>当前管理级别：</th>
                                <td>
                              <%if(up_user.getRank()==1){ %><%=Constants.RANK_1 %>
                                <%}else if(up_user.getRank()==2){ %><%=Constants.RANK_2 %>
                                <%}else if(up_user.getRank()==3){ %><%=Constants.RANK_3 %>
                                <%}else if(up_user.getRank()==4){ %><%=Constants.RANK_4 %>
                                <%}else if(up_user.getRank()==5){ %><%=Constants.RANK_5 %>
                                <%} %>
                                </td>
                            </tr>
                           
                              <tr>
                                <th><i class="require-red">*</i>升级管理级别：</th>
                                <td>
                                    <select id="rankManage" name="rankManage" class="common-text required">
										 <%if(up_user.getRank()==0){ %>
										  <option value="1"><%=Constants.RANK_1 %></option>
										    <option value="2"><%=Constants.RANK_2 %></option>
										  <option value="3"><%=Constants.RANK_3 %></option>
										  <option value="4"><%=Constants.RANK_4 %></option>
										   <option value="5"><%=Constants.RANK_5 %></option>
										    <%}else if(up_user.getRank()==1){ %>
										  <option value="2"><%=Constants.RANK_2 %></option>
										  <option value="3"><%=Constants.RANK_3 %></option>
										  <option value="4"><%=Constants.RANK_4 %></option>
										   <option value="5"><%=Constants.RANK_5 %></option>
										  <%}else if(up_user.getRank()==2){ %>
										    <option value="3"><%=Constants.RANK_3 %></option>
										  <option value="4"><%=Constants.RANK_4 %></option>
										   <option value="5"><%=Constants.RANK_5 %></option>
										  <%}else if(up_user.getRank()==3){ %>
										 <option value="4"><%=Constants.RANK_4 %></option>
										   <option value="5"><%=Constants.RANK_5 %></option>
									  <%}else if(up_user.getRank()==4){ %>
										  <option value="5"><%=Constants.RANK_5 %></option>
										  <%}else if(up_user.getRank()==5){ %>
										  <option value="">-会员当前为最高管理级别-</option>
										  <%} %>
										  </select>&nbsp;<span id="rankManagemsg"></span><input type="hidden" name="rankManageOld" id="rankManageOld" value="<%=up_user.getRank()%>">
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
	$("#sbtn").click(function(){
	 if (!confirm("确认提交变更管理级别？")) {
				window.event.returnValue = false;
			}else{
		var errorstr = "";
		if($("#rankManage").val()==""){
			$("#rankManagemsg").text("未选择需要变更的管理级别！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#rankManage").val()==$("#rankManageTag").val()){
			$("#rankManagemsg").text("最新管理级别与原管理级别相同，请核对后再试！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		
		if(errorstr!=""){
			return false;
		}else{
       		 $('#myform').submit();
       		 return ture;
		
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