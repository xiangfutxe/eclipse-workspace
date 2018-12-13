<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.pojo.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
User user = (User)request.getAttribute("user_info");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][0].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>会员详情</title>
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
             <div class="crumb-list"><i class="icon-font"></i><a href="user/index.jsp">首页</a>
             <span class="crumb-step">&gt;</span><span>会员管理</span>
             <span class="crumb-step">&gt;</span><span>会员详情</span>
             </div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
               <table class="insert-tab" width="100%">
               	<tbody>
               		<tr>
               		<th width="120px"><i class="require-red">*</i>会员头像：</th>
               		<td>
<img src="upload/<%=StringUtil.notNull(user.getImageUrl())%>" width='100' height='100' alt="暂无图片">
               		</td>
               	</tr>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>会员昵称：</th>
               		<td><%=StringUtil.notNull(user.getUserId()) %>
               	 	
               		</td>
               	</tr>
               		<tr>
               		<th width="120px"><i class="require-red">*</i>真实姓名：</th>
               		<td><%=StringUtil.notNull(user.getUserName()) %>
               	 	
               		</td>
               	</tr>
               		<tr>
               		<th width="120px"><i class="require-red">*</i>性别：</th>
               		<td><%=StringUtil.notNull(user.getSex()) %>
               	 	
               		</td>
               	</tr>
               		<tr>
               		<th width="120px"><i class="require-red">*</i>出生日期：</th>
               		<td><%=StringUtil.parseToString(user.getBirth(), DateUtil.yyyyMMdd) %>
               	 	
               		</td>
               	</tr>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>证件类型：</th>
               		<td><%=StringUtil.notNull(user.getDocumentType()) %>
               	 	
               		</td>
               	</tr>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>证件号码：</th>
               		<td><%=StringUtil.notNull(user.getNumId()) %>
               		</td>
               	</tr>
               <tr>
               		<th width="120px"><i class="require-red">*</i>手机号码：</th>
               		<td><%=StringUtil.notNull(user.getTel()) %>
               		</td>
               	</tr>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>联系电话：</th>
               		<td><%=StringUtil.notNull(user.getPhone()) %>
               		</td>
               	</tr>
               	 	<tr>
               		<th width="120px"><i class="require-red">*</i>Email：</th>
               		<td><%=StringUtil.notNull(user.getEmail()) %>
               		</td>
               	</tr>
               	 	<tr>
               		<th width="120px"><i class="require-red">*</i>微信：</th>
               		<td><%=StringUtil.notNull(user.getWeixin()) %>
               		</td>
               	</tr>
               	 	<tr>
               		<th width="120px"><i class="require-red">*</i>QQ：</th>
               		<td><%=StringUtil.notNull(user.getQq()) %>
               		</td>
               	</tr>
               		<tr>
               		<th width="120px"><i class="require-red">*</i>所在区域：</th>
               		<td><%=StringUtil.notNull(user.getProvince()) %><%=StringUtil.notNull(user.getCity()) %><%=StringUtil.notNull(user.getArea()) %>
               		</td>
               	</tr>
               	
               		<tr>
               		<th width="120px"><i class="require-red">*</i>常住地址：</th>
               		<td><%=StringUtil.notNull(user.getAddress())%>
               		</td>
               	</tr>
               	
                             <tr>
                                <th>&nbsp;</th>
                                <td>
                                    &nbsp; <input id="btn1" class="btn"  value="返回" type="button" onclick="javascript:history.go(-1);">
                                </td>
                            </tr>
               	</tbody>
               </table>
               </div>
               </div>
               
    </div>
    </div>
  </body>
</html>
<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>