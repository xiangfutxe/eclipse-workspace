<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String token = (String)request.getSession().getAttribute("token");
	User user = (User) request.getAttribute("admin_user");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][15].equals("1")||admin.getState().equals("1")){

if(user==null){
out.println("<script>");
		out.println("parent.window.location.href='javascript:history.go(-1)';");
		out.println("</script>");
}else{
String message = (String)request.getAttribute("message");
if(!StringUtil.notNull(message).equals("")){
out.println("<script>");
		out.println("alert('"+message+"')");
		out.println("</script>");
}		

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>会员权限</title>
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
  
  <body onload="initialize()">
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>会员管理<span class="crumb-step">&gt;</span><span><a href="admin/user_list.action">会员权限</a></span></div>
        </div>
         <div class="result-wrap">
         <form  action="admin/UserServlet.do?method=admin_user_authority_save"  id="myform" name="myform" method="post">
		   <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
          <input  id="id" name="id"  value='<%=StringUtil.notNull(user.getUserId())%>' type="hidden">
            <div class="result-content">
               
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                                 <th><i class="require-red">*</i>会员编号：</th>
                                <td>
                                <%=user.getUserId() %>
                                </td>
                                  </tr>
                                  <tr>
                                 <th><i class="require-red">*</i>会员名称：</th>
                                <td>
                                <%=user.getUserName() %>
                                </td>
                                  </tr>
                          <tr>
                                <td colspan="2" ><h4><i class="require-red">*</i>会员权限：</h4></td>
                                </tr>
                                <tr>
                                <th width="150px"><i class="require-red">*</i>销售商关系：</th>
                                <td>
								<input name="isRefereeList" type="radio" value="1" <%if(user.getIsRefereeList()==1){ %>checked<%} %>>开放
								&nbsp;<input name="isRefereeList" type="radio" value="0" <%if(user.getIsRefereeList()==0){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                  <tr>
                                <th width="150px"><i class="require-red">*</i>服务商关系：</th>
                                <td>
								<input  name="isBelongList" type="radio" value="1" <%if(user.getIsBelongList()==1){ %>checked<%} %>>开放
								&nbsp;<input  name="isBelongList" type="radio" value="0" <%if(user.getIsBelongList()==0){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                  <tr>
                                <th width="150px"><i class="require-red">*</i>报单权限：</th>
                                <td>
								<input  name="isDeclaration" type="radio" value="1" <%if(user.getIsDeclaration()==1){ %>checked<%} %>>开放
								&nbsp;<input  name="isDeclaration"  type="radio" value="0" <%if(user.getIsDeclaration()==0){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                  <tr>
                                <th width="150px"><i class="require-red">*</i>会员列表：</th>
                                <td>
								<input name="isUserList" type="radio"  value="1" <%if(user.getIsUserList()==1){ %>checked<%} %>>开放
								&nbsp;<input name="isUserList" type="radio"  value="0" <%if(user.getIsUserList()==0){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                 <tr>
                                <th width="150px"><i class="require-red">*</i>奖金发放：</th>
                                <td>
								<input name="payTag" type="radio"  value="1" <%if(user.getPayTag()==1){ %>checked<%} %>>发放
								&nbsp;<input name="payTag" type="radio"  value="0" <%if(user.getPayTag()==0){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                        <tr>
                                <th></th>
                                <td>
                                   <input id="btn1" class="btn btn-primary" value="保存修改" type="submit">
                                  
                                </td>
                            </tr>
                            
                             </tbody>
                            </table>
            </div>
               </form>
        </div>

    </div>
    <!--/main-->
    </div>
  </body>
   <script type="text/javascript" src="js/custom.js"></script>
 <script type="text/javascript">
	$(function() {
   
  	 
  	  $("#btn1").click(function(){
  	   if (!confirm("确认保存权限修改！")) {
				return false;
			}else{
	  	
	       		 $('#myform').submit();
	       		 return true;
		}
			 });
   });
   
        
        
   </script>
</html>

<%
}
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>