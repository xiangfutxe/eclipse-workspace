<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.WithDrew" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
WithDrew apply = (WithDrew)request.getAttribute("apply");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[6][4].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加备注</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>财务管理
            <span class="crumb-step">&gt;</span><span>提现申请</span>
            <span class="crumb-step">&gt;</span><span>备注提示修改</span>
            </div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form method="post" id="myform" name="myform">
				<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
				<input type="hidden" name="id" id="id" value="<%=apply.getId()%>"/>  
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="120px"><i class="require-red"></i>备注提示：</th>
                                <td>
                                <textarea  rows="10" cols="30" id="comments" name="comments">
                                <%=StringUtil.notNull(apply.getComments()) %>
                                </textarea>
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
		if (!confirm("确认要提交修改提现备注提示？")) {
            return false;
        }else{
			$('#myform').attr("action", "admin/WithDrewServlet.do?method=apply_comments_update");
	       	$('#myform').submit();
       		 return true;
		
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
