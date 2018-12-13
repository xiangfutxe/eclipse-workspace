<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String token = (String)request.getSession().getAttribute("token");
	User user = (User) request.getAttribute("admin_user");
	String message = (String)request.getAttribute("message");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][3].equals("1")||admin.getState().equals("1")){

		if(user==null){
		out.println("<script>");
				out.println("parent.window.location.href='javascript:history.go(-1)';");
				out.println("</script>");
		}else{
		 if(!StringUtil.notNull(message).equals("")){
			out.println("<script>");
			out.println("alert('"+message+"')");
			out.println("</script>");
		}
			String auth = StringUtil.notNull(user.getAuthority());
			if(auth.length()>=30){

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
         <form  action="admin/UserServlet?method=admin_user_authority_save"  id="myform" name="myform" method="post">
		   <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
          <input  id="id" name="id"  value='<%=user.getId()%>' type="hidden">
           <input  id="userId" name="userId"  value='<%=StringUtil.notNull(user.getUserId())%>' type="hidden">
           <input  id="auth_old" name="auth_old"  value='<%=auth%>' type="hidden">
          
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
								<input name="str11" type="radio" value="1" <%if(auth.substring(10, 11).equals("1")){ %>checked<%} %>>开放
								&nbsp;<input name="str11" type="radio" value="0" <%if(auth.substring(10, 11).equals("0")){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                  <tr>
                                <th width="150px"><i class="require-red">*</i>服务商关系：</th>
                                <td>
								<input  name="str12" type="radio" value="1" <%if(auth.substring(11, 12).equals("1")){ %>checked<%} %>>开放
								&nbsp;<input  name="str12" type="radio" value="0" <%if(auth.substring(11, 12).equals("0")){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                  <tr>
                                <th width="150px"><i class="require-red">*</i>销售商关系查询：</th>
                                <td>
								<input  name="str13" type="radio" value="1" <%if(auth.substring(12, 13).equals("1")){ %>checked<%} %>>开放
								&nbsp;<input  name="str13" type="radio" value="0" <%if(auth.substring(12, 13).equals("0")){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                  <tr>
                                <th width="150px"><i class="require-red">*</i>服务商关系查询：</th>
                                <td>
								<input  name="str14" type="radio" value="1" <%if(auth.substring(13, 14).equals("1")){ %>checked<%} %>>开放
								&nbsp;<input  name="str14" type="radio" value="0" <%if(auth.substring(13, 14).equals("0")){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                  <tr>
                                <th width="150px"><i class="require-red">*</i>报单权限：</th>
                                <td>
								<input  name="str15" type="radio" value="1" <%if(auth.substring(14, 15).equals("1")){ %>checked<%} %>>开放
								&nbsp;<input  name="str15"  type="radio" value="0" <%if(auth.substring(14, 15).equals("0")){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                  <tr>
                                <th width="150px"><i class="require-red">*</i>会员列表：</th>
                                <td>
								<input name="str16" type="radio"  value="1" <%if(auth.substring(15, 16).equals("1")){ %>checked<%} %>>开放
								&nbsp;<input name="str16" type="radio"  value="0" <%if(auth.substring(15, 16).equals("0")){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                  <tr>
                                <th width="150px"><i class="require-red">*</i>待结业绩：</th>
                                <td>
								<input name="str17" type="radio"  value="1" <%if(auth.substring(16, 17).equals("1")){ %>checked<%} %>>开放
								&nbsp;<input name="str17" type="radio"  value="0" <%if(auth.substring(16, 17).equals("0")){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                 <tr>
                                <th width="150px"><i class="require-red">*</i>业绩统计：</th>
                                <td>
								<input name="str10" type="radio"  value="1" <%if(auth.substring(9, 10).equals("1")){ %>checked<%} %>>开放
								&nbsp;<input name="str10" type="radio"  value="0" <%if(auth.substring(9, 10).equals("0")){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                 <tr>
                                <th width="150px"><i class="require-red">*</i>补贴明细：</th>
                                <td>
								<input name="str9" type="radio"  value="1" <%if(auth.substring(8, 9).equals("1")){ %>checked<%} %>>开放
								&nbsp;<input name="str9" type="radio"  value="0" <%if(auth.substring(8, 9).equals("0")){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                   <tr>
                                <th width="150px"><i class="require-red">*</i>拓展封顶：</th>
                                <td>
								<input name="isMax" type="radio"  value="1" <%if(user.getIsMax()==1){ %>checked<%} %>>是
								&nbsp;<input name="isMax" type="radio"  value="0" <%if(user.getIsMax()==0){ %>checked<%} %>>否
                                </td>
                                </tr>
                                 <tr>
                                <th width="150px"><i class="require-red">*</i>拓展封顶倍数：</th>
                                <td>
                                <select name="capFactor" id="capFactor">
                                <option value='0' <%if(user.getCapFactor()==0){ %>selected<%} %>>0</option>
                                  <option value='1' <%if(user.getCapFactor()==1){ %>selected<%} %>>1</option>
                                    <option value='2' <%if(user.getCapFactor()==2){ %>selected<%} %>>2</option>
                                     
                                </select>
                                </td>
                                </tr>
                                 
                                 <tr>
                                <th width="150px"><i class="require-red">*</i>会员业绩：</th>
                                <td>
								<input name="str22" type="radio"  value="1" <%if(auth.substring(21, 22).equals("1")){ %>checked<%} %>>开放
								&nbsp;<input name="str22" type="radio"  value="0" <%if(auth.substring(21, 22).equals("0")){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                 <tr>
                                <th width="150px"><i class="require-red">*</i>增报购物：</th>
                                <td>
								<input name="str23" type="radio"  value="1" <%if(auth.substring(22, 23).equals("1")){ %>checked<%} %>>开放
								&nbsp;<input name="str23" type="radio"  value="0" <%if(auth.substring(22, 23).equals("0")){ %>checked<%} %>>关闭
                                </td>
                                
                                </tr>
                                 <tr>
                                <th width="150px"><i class="require-red">*</i>功能冻结：</th>
                                <td>
								<input name="str24" type="radio"  value="1" <%if(auth.substring(23, 24).equals("1")){ %>checked<%} %>>冻结
								&nbsp;<input name="str24" type="radio"  value="0" <%if(auth.substring(23, 24).equals("0")){ %>checked<%} %>>正常
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
}else{
		out.println("<script>");
		out.println("alert('会员权限获取失败。')");
		out.println("parent.window.location.href='javascript:history.go(-1)';");
		out.println("</script>");	
}
}
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>