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
User up_user = (User) request.getAttribute("up_user");
String token = (String)request.getSession().getAttribute("token");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][9].equals("1")||admin.getState().equals("1")){

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>奖衔变更</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>会员管理<span class="crumb-step">&gt;</span><span>奖衔变更</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/RankManageUpdateServlet.do?method=rankManage_update" method="post" id="myform" name="myform" >
                <input type="hidden" name="userId" value='<%=up_user.getUserId()%>'>
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
                                <th><i class="require-red">*</i>当前等级：</th>
                                <td>
                              <% if (up_user.getRankManage() == 1) {
									%>见习员<%
										} else if (up_user.getRankManage() == 2) {
									%>
									主管<%
										} else if (up_user.getRankManage() == 3) {
									%>经理<%
										} else if (up_user.getRankManage() == 4) {
									%>
									高经<%
										} else if (up_user.getRankManage() == 5) {
									%>总监<%
										} else if (up_user.getRankManage() == 6) {
									%>董事
									<%
										} else if (up_user.getRankManage() == 7) {
									%>一星董事<%
										} else if (up_user.getRankManage() == 8) {
									%>二星董事<%
										} else if (up_user.getRankManage() == 9) {
									%>三星董事
									<%
										} else if (up_user.getRankManage() == 10) {
									%>四星董事<%
										} else if (up_user.getRankManage() == 11) {
									%>五星董事
									<%
										} else if (up_user.getRankManage() == 12) {
									%>六星董事<%
										} else if (up_user.getRankManage() == 13) {
									%>七星董事
									<%
										} else if (up_user.getRankManage() == 14) {
									%>八星董事<%
										} else if (up_user.getRankManage() == 15) {
									%>九星董事
									<%
										} else {
									%> -<%
										}
									%>
                                </td>
                            </tr>
                           
                              <tr>
                                <th><i class="require-red">*</i>变更等级：</th>
                                <td>
                                    <select id="rankJoin" name="rankManage" class="common-text required">
										  <option value="0">-</option>
										   <option value="1">见习员</option>
										    <option value="2">主管</option>
										  <option value="3">经理</option>
										    <option value="4">高经</option>
										      <option value="5">总监</option>
										      <option value="6">董事</option>
										       <option value="7">一星董事</option>
										     <option value="8">二星董事</option>
										       <option value="9">三星董事</option>
										         <option value="10">四星董事</option>
										           <option value="11">五星董事</option>
										             <option value="12">六星董事</option>
										               <option value="13">七星董事</option>
										                 <option value="14">八星董事</option>
										                   <option value="15">九星董事</option>
										  </select>&nbsp;<span id="rankManagemsg"></span><input type="hidden" name="rankManageTag" id="rankManageTag" value="<%=up_user.getRankManage()%>">
                                </td>
                                
                                </tr>
                                <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary" value="提交" type="button">
                                    <input class="btn" onClick="history.go(-1)" value="返回" type="button">
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
	 if (!confirm("确认提交变更等级？")) {
				window.event.returnValue = false;
			}else{
		var errorstr = "";
		if($("#rankManage").val()==""){
			$("#rankManagemsg").text("未选择需要变更的等级！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#rankManage").val()==$("#rankManageTag").val()){
			$("#rankManagemsg").text("最新等级与原等级相同，请核对后再试！").css({"color":"red","font-size":"12px"}); 
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