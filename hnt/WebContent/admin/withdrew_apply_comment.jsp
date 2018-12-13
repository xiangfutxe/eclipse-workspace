<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.WithDrew" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
WithDrew wd = (WithDrew)request.getAttribute("sys_apply");

if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[6][2].equals("1")||admin.getState().equals("1")){
		String message = (String)request.getAttribute("message");
		if(!StringUtil.notNull(message).equals(""))
		{
		out.println("<script>");
out.println("alert('"+message+"')");
out.println("</script>");}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>提现申请</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>财务管理<span class="crumb-step">&gt;提现申请</span>
            <span class="crumb-step">&gt;备注</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form method="post" id="myform" name="myform">
				<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                <input type="hidden" name="applyId" id="applyId" value="<%=StringUtil.notNull(wd.getApplyId())%>"/> 
                  <input type="hidden" name="id" id="id" value="<%=wd.getId()%>"/> 
                   
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="10%"><i class="require-red">*</i>用户编号：</th>
                                <td>
								<%=wd.getUserName() %>(<%=wd.getUserId() %>)
								</td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>提现金额：</th>
                                <td>
                                   <%=StringUtil.decimalFormat(wd.getAmount()) %>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red"></i>备注：</th>
                                <td>
                                <textarea style="width:60%;height:200px;overflow:scroll;resize:none;" id="comments" name="comments" ><%=StringUtil.notNull(wd.getComments()) %></textarea>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary" value="提交" type="button">
                                    <input class="btn" id ="reback" value="提现列表" type="button">
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

	var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/; //验证金额
	
	
	
	$("#sbtn").click(function(){
		if (!confirm("确认要提修改备注？")) {
            return false;
        }else{
		
			$('#myform').attr("action", "admin/WithDrewServlet?method=apply_comment_save");
	       	$('#myform').submit();
       		 return true;
		
		}
	});
	
	$("#reback").click(function(){
		
		
			$('#myform').attr("action", "admin/WithDrewServlet?method=admin_list");
	       	$('#myform').submit();
       		 return true;
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
