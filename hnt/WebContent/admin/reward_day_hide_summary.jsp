<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.DReward" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String startTime = (String) request.getAttribute("startTime");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[7][26].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>公司奖金</title>
    
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
						class="crumb-step">&gt;</span><span class="crumb-name">奖金管理</span><span
						class="crumb-step">&gt;</span><span class="crumb-name">公司奖金汇总</span>
					
				</div>
			</div>
				<div class="search-wrap">
				<div class="search-content">
					<form action="admin/DRewardServlet?method=admin_reward_day_hide_summary" id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
								<th width="80">
										结算日期：</th>
										<td>
										
									<input class="common-text" type="text" name="startTime" id="startTime" value="<%=StringUtil.notNull(startTime)%>" onfocus="new Calendar().show(this);"/><input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="submit"><span
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
							<th>起止时间</th><th>类型</th><th>新增金额</th><th>新增业绩</th><th>推荐奖</th><th>见点奖</th><th>层碰奖</th><th>拓展奖</th><th>培育奖</th><th>领导奖</th><th>物流补助</th><th>消费奖</th><th>奖金小计</th><th>复消</th><th>综合费</th><th>实发金额</th>
						</tr>
						
						  <%
								 DReward wrd = (DReward)request.getAttribute("sys_wrd");
						if(wrd!=null){
							%>
 <tr>
 <td><%=StringUtil.parseToString(wrd.getStartTime(), DateUtil.yyyyMMddHHmmss)%><br>至<br><%=StringUtil.parseToString(wrd.getEndTime(), DateUtil.yyyyMMddHHmmss)%></td>
  <td>奖金汇总</td>
   <td><%=StringUtil.decimalFormat3(wrd.getAmount())%></td>
   <td><%=StringUtil.decimalFormat3(wrd.getNewPerformance())%></td>
    <td><%=StringUtil.decimalFormat3(wrd.getAmount_1())%></td>
    <td><%=StringUtil.decimalFormat3(wrd.getAmount_2())%></td>
    <td><%=StringUtil.decimalFormat3(wrd.getAmount_3())%></td>
    <td><%=StringUtil.decimalFormat3(wrd.getAmount_4())%></td>
    <td><%=StringUtil.decimalFormat3(wrd.getAmount_5())%></td>
    <td><%=StringUtil.decimalFormat3(wrd.getAmount_6())%></td>
    <td><%=StringUtil.decimalFormat3(wrd.getAmount_7())%></td>
    <td><%=StringUtil.decimalFormat3(wrd.getAmount_8())%></td>
    <td><%=StringUtil.decimalFormat3(wrd.getAmount_9())%></td>
    <td><%=StringUtil.decimalFormat3(wrd.getAmount_10())%></td>
    <td><%=StringUtil.decimalFormat3(wrd.getAmount_11())%></td>
    <td><%=StringUtil.decimalFormat3(wrd.getAmount_12())%></td>
 </tr>
 <tr>
 <td><%=StringUtil.parseToString(wrd.getStartTime(), DateUtil.yyyyMMddHHmmss)%><br>至<br><%=StringUtil.parseToString(wrd.getEndTime(), DateUtil.yyyyMMddHHmmss)%></td>
  <td>金额拨比</td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getNewPerformance()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_1()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_2()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_3()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_4()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_5()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_6()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_7()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_8()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_9()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_10()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_11()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getAmount()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_12()/wrd.getAmount()*100)%>%<%}else{ %>0%<%} %></td>
 </tr>
 <tr>
 <td><%=StringUtil.parseToString(wrd.getStartTime(), DateUtil.yyyyMMddHHmmss)%><br>至<br><%=StringUtil.parseToString(wrd.getEndTime(), DateUtil.yyyyMMddHHmmss)%></td>
  <td>业绩拨比</td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getNewPerformance()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_1()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_2()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_3()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_4()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_5()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_6()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_7()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_8()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_9()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_10()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_11()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
		<td><%if(wrd.getNewPerformance()>0){%><%=StringUtil.decimalFormat3(wrd.getAmount_12()/wrd.getNewPerformance()*100)%>%<%}else{ %>0%<%} %></td>
 </tr>
 	<%}else{ 
 		String message  =StringUtil.notNull((String)request.getAttribute("message"));
 	%><tr><td colspan="15"><%=message %></td></tr><%} %>
					</table>


				</div>
				
			</div>
		</div>
		<!--/main-->
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
