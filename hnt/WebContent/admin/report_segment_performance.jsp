<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.WSettle" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
String userId = (String) request.getAttribute("userId");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[8][5].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分段业绩查询</title>
    
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
<script language="JavaScript">
	function delcfm() {
		if (!confirm("确认要退订？")) {
			window.event.returnValue = false;
		}
	}

	$(function() {
		$('#search')
				.click(
						function() {
											
												var startTime = $("#startTime")
													.val();
											var endTime = $("#endTime").val();
											var str = "";
											var a = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;
											
											if (startTime == "") {
												if(endTime!=""){
													str = str + "结束时间选择了，开始时间不能为空！\n";
													$("#startTime").focus();
												}
											} else{
													if (startTime.match(a) == null) {
														str = str + "开始时间格式有误！\n";
														$("#startTime").focus();
													} 
													if(endTime!=""){
														if (endTime.match(a) == null) {
														str = str + "结束时间格式有误！\n";
														$("#endTime").focus();
														}else if(startTime>endTime){
															str = str + "开始时间不能超过结束时间！\n";
															$("#endTime").focus();
														}
													}
											}
											
											if (str == "") {
												$('#searchForm').submit();
											} else {
												alert(str);
												return false;
											}
						});
	});
</script>
  </head>
  
  <body>
 <div class="container clearfix">
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a><span
						class="crumb-step">&gt;</span><span class="crumb-name">报表统计</span><span
						class="crumb-step">&gt;</span><span class="crumb-name">分段业绩查询</span>
					
				</div>
			</div>
				<div class="search-wrap">
				<div class="search-content">
					<form action="admin/ReportServlet?method=admin_report_segment_performance" id="searchForm" method="post">
						<table class="search-tab">
							<tr>
								<td>&nbsp;&nbsp;&nbsp;会员编号：<input type="text" class="common-text" name="userId" value="<%=StringUtil.notNull(userId)%>" />
								&nbsp;起止日期:
								<input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								&nbsp;<input class="btn btn-primary" id="search"
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
							<td colspan="8">分段业绩汇总</td>
						</tr>
						<tr>
							
						</tr>
						  <%
							WSettle wst = (WSettle)request.getAttribute("sys_wst");
							if(wst!=null){
									
							%>
 <tr>
 
 <td>时间段</td><td><%if(startTime.equals("")){%>全部<%}else{%><%=StringUtil.notNull(startTime+"至"+endTime)%><%} %></td></tr><tr>
 <td>用户信息</td><td><%=StringUtil.notNull(wst.getUserName())%>（<%=StringUtil.notNull(wst.getUserId())%>）</td></tr><tr>
<td>区域总金额</td> <td><%=StringUtil.decimalFormat(wst.getNewPrice())%></td></tr><tr>
  <td>区域总业绩</td><td><%=StringUtil.decimalFormat(wst.getNewPerformance())%></td></tr><tr>
   <td>A区金额</td> <td><%=StringUtil.decimalFormat(wst.getAtpv())%></td></tr><tr>
  <td>A区业绩</td> <td><%=StringUtil.decimalFormat(wst.getApv())%></td></tr><tr>
   <td>B区金额</td><td><%=StringUtil.decimalFormat(wst.getBtpv())%></td></tr><tr>
     <td>B区业绩</td><td><%=StringUtil.decimalFormat(wst.getBpv())%></td></tr><tr>
       <td>服务总金额</td> <td><%=StringUtil.decimalFormat(wst.getRtpv())%></td></tr><tr>
      <td>服务总业绩</td> <td><%=StringUtil.decimalFormat(wst.getRpv())%></td></tr><tr>
        <td>复消总金额</td><td><%=StringUtil.decimalFormat(wst.getRtpv1())%></td></tr><tr>
      <td>复消总业绩</td> <td><%=StringUtil.decimalFormat(wst.getRpv1())%></td></tr>
     
 <%}else{
 		String message=(String)request.getAttribute("message");%><%
						 %><tr><td colspan="8"><%=StringUtil.notNull(message) %></td></tr><%} %>
					</table>
				</div>
				<br>
				
			</div>
		</div>
		<!--/main-->
	</div>
</body>
<script language="JavaScript">

	$(function() {
		
						
						 $("#exportExcel").click(function(){
	  	  if (!confirm("确认提交导出Excel？")) {
				return false;
			}
			else{
	       		$("#searchForm").attr("action", "admin/ReportServlet?method=exportExcel");
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
