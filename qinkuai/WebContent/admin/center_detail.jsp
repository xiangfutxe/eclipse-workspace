<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.Center" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
Center center = (Center) request.getAttribute("center");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
		if(center!=null){

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>服务店详情</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>会员管理<span class="crumb-step">&gt;</span><span>服务中心</span><span class="crumb-step">&gt;</span><span>详情</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                    <table class="insert-tab" width="100%">
                        <tbody>
                           
                             <tr>
                                <th width="150px"><i class="require-red">*</i>服务店类型：</th>
                                <td>
                                <%if(center.getType()==1) {%> 社区店
								<%}else if(center.getType()==2) {%>中心店
								<%} %></td>
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
                                <th width="150px"><i class="require-red">*</i>负责人信息：</th>
                                <td><%=center.getUserId()%>(<%=center.getUserName()%>)
                                </td>
                            </tr>
                            <tr><th><i class="require-red">*</i>经营区域：</th>
									<td>
										<%=StringUtil.notNull(center.getProvince()) %>
										<%=StringUtil.notNull(center.getCity()) %>
										<%=StringUtil.notNull(center.getArea()) %>
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
                                <th width="150px"><i class="require-red">*</i>营业执照：</th>
                                <td><%=StringUtil.notNull(center.getLicense()) %></td>
                            </tr>
                              <tr>
                                <th width="150px"><i class="require-red">*</i>推荐人编号：</th>
                                <td><%=StringUtil.notNull(center.getRefereeId()) %></td>
                            </tr>
                              <tr>
                                <th width="150px"><i class="require-red">*</i>推荐人姓名：</th>
                                <td><%=StringUtil.notNull(center.getRefereeName()) %></td>
                            </tr>
                         
                                <tr>
                                <th></th>
                                <td>
                                    <input id="bbtn" class="btn btn-info" value="返回" type="button" onclick="javascript:history.go(-1);"/>
                                </td>
                            </tr>
  </tbody></table>
            </div>
        </div>

    </div>
    <!--/main-->
    </div>
  </body>
   <script type="text/javascript" src="js/custom.js"></script>
 <script type="text/javascript">
$(function() {
	
	$("#sbtn").click(function() {
	  if (!confirm("确认提交变更服务店类型？")) {
				return false;
			}
		else{
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
