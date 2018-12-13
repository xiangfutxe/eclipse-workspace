<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.Center" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
Center center = (Center) request.getAttribute("center");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][16].equals("1")||admin.getState().equals("1")){
		if(center!=null){

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>服务店升级</title>
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
      <script type="text/javascript" src="js/jquery.cityselect.js"></script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>会员管理<span class="crumb-step">&gt;</span><span>服务中心</span><span class="crumb-step">&gt;</span><span>服务店升级</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                   <form action="admin/CenterServlet?method=admin_up_save" method="post" id="myform" name="myform">
                     <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
                      <input type="hidden" name="id" id="id" value="<%=center.getId()%>"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>会员ID：</th>
                                <td><%=center.getUserId()%>(<%=center.getUserName()%>)
                                </td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red">*</i>原类型：</th>
                                <td>
                               <%if(center.getType()==1){%>
								资格店<%}else if(center.getType()==2){%>实体店
								<%}else if(center.getType()==3){%>工作室<%}else{%>
								－<%}%></td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red">*</i>修改类型：</th>
                                <td>
                              <select name="typeNew" id="typeNew">
                               <%if(center.getType()!=1){ %>
                              <option value="1">工作室</option>
                              <%} %>
                              <%if(center.getType()!=2){ %>
                              <option value="2">发货中心</option>
                              <%} %>
                               <%if(center.getType()!=3){ %>
                              <option value="3">运营中心</option>
                              <%} %>
                              </select></td>
                            </tr>
                               <tr>
                                <th width="150px"><i class="require-red">*</i>服务店编号：</th>
                                <td><%=StringUtil.notNull(center.getCenterId()) %></td>
                            </tr>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>服务店名称：</th>
                                <td><%=StringUtil.notNull(center.getCenterName()) %></td>
                            </tr>
                            <tr>
									<th><i class="require-red">*</i>经营区域：</th>
									<td><%=StringUtil.notNull(center.getProvince()) %><%=StringUtil.notNull(center.getCity()) %><%=StringUtil.notNull(center.getArea()) %>
										
									</td>

								</tr>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>经营地址：</th>
                                <td><%=StringUtil.notNull(center.getAddress()) %></td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red">*</i>联系电话：</th>
                                <td><%=StringUtil.notNull(center.getPhone()) %></td>
                            </tr>
                             <tr>
                                <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary" value="提交" type="button"/>
                                    &nbsp;
                                    <input id="bbtn" class="btn btn-info" value="返回" type="button" onclick="javascript:history.go(-1);"/>
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
var reg = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证
	
 
	
	$("#sbtn").click(function() {
	  if (!confirm("确认提交变更店铺资格？")) {
				window.event.returnValue = false;
			}else{
	
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
			out.println("alert('需要修改的服务店信息获取失败！')");
			out.println("</script>");
		}
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>
