<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.WReward" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
Integer weekTag = (Integer) request.getAttribute("weekTag");
String userId = (String) request.getAttribute("userId");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[7][7].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>团队奖金查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script src="js/calendar.js"></script>
  </head>
  
  <body>
 <div class="container clearfix">
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a><span
						class="crumb-step">&gt;</span><span class="crumb-name">报表统计</span><span
						class="crumb-step">&gt;</span><span class="crumb-name">团队奖金查询</span>
					
				</div>
			</div>
				<div class="search-wrap">
				<div class="search-content">
					<form action="admin/ReportServlet.do?method=admin_report_reward_team_summary" id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
								<th width="80">按周查询:</th>
								
								<td><select class="common-text" id="weekTag"
									name="weekTag">
									 <%
								 List<Integer> slist = (List<Integer>)request.getAttribute("slist");
						if(slist.size()>0){
							
								for(int i=0;i<slist.size();i++){
								int week = slist.get(i);
							%>
										<option value="<%=week%>" <%if(weekTag==week){ %>selected<%} %>>第<%=week%>周</option>
										<%}}%>
								</select>&nbsp;会员编号：<input class="common-text" type="text" name="userId" id="userId" value="<%=StringUtil.notNull(userId)%>"/></td>
								<td><input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="submit">&nbsp;<span
									id="timeTag" style="color:red"></span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			
			<div class="result-wrap">
		
				<div class="result-content">
					<table class="result-tab" width="98%">
						<tr>
							<th>时间</th><th>团队领导人信息</th><th>幸运奖</th><th>创业奖</th><th>拓展奖</th><th>培育奖</th><th>领导奖</th><th>物流补助</th><th>分红</th><th>消费奖</th><th>奖金小计</th><th>复消</th><th>扣税</th><th>实发金额</th>
						</tr>
						
						  <%
								 WReward w = (WReward)request.getAttribute("sys_wrd");
						if(w!=null){
						
							%>
 <tr>
 
 <td>第<%=weekTag%>周</td>
 <td><%=StringUtil.notNull(w.getUserName())%>（<%=StringUtil.notNull(w.getUserId())%>）</td>
 <td><%=StringUtil.decimalFormat(w.getAmount_1())%></td>
  <td><%=StringUtil.decimalFormat(w.getAmount_8())%></td>
  <td><%=StringUtil.decimalFormat(w.getAmount_2())%></td>
   <td><%=StringUtil.decimalFormat(w.getAmount_3())%></td>
    <td><%=StringUtil.decimalFormat(w.getAmount_4())%></td>
     <td><%=StringUtil.decimalFormat(w.getAmount_5())%></td>
      <td><%=StringUtil.decimalFormat(w.getAmount_6())%></td>
       <td><%=StringUtil.decimalFormat(w.getAmount_7())%></td>
      <td><%=StringUtil.decimalFormat(w.getAmount_9())%></td>
		<td><%=StringUtil.decimalFormat(w.getAmount_10())%></td>
 <td><%=StringUtil.decimalFormat(w.getAmount_11())%></td>
 <td><%=StringUtil.decimalFormat(w.getAmount_12())%></td>
 </tr>	<%}else{ %><tr><td colspan="13">
 <%String message = (String)request.getAttribute("message");
 if(StringUtil.notNull(message).equals("")){ %>
 暂无记录！
 <%}else{ %><%=StringUtil.notNull(message) %><%} %></td></tr><%} %>
					</table>


				</div>
				
			</div>
		</div>
		<!--/main-->
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#payoff_all").click(function(){
		 if(!confirm("确认提交发放工资？")){
		 	return false;
		 }else{
		 	$("myform").submit();
		 	return true;
		 }
		});
		
		$("#exportExcel").click(function(){
	  	  if (!confirm("确认提交导出Excel？")) {
				return false;
			}
			else{
	       		$("#searchForm").attr("action", "admin/RewardServlet.do?method=exportExcel");
				$("#searchForm").submit();
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
