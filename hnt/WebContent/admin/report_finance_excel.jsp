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
String startTime_3 = (String)request.getAttribute("startTime_3");
String endTime_3 = (String)request.getAttribute("endTime_3");
String userId = (String) request.getAttribute("userId");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[8][14].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>财务汇总导出</title>
    
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
						class="crumb-step">&gt;</span><span class="crumb-name">财务汇总导出</span>
					
				</div>
			</div>
			<%
					if(rankstr[8][20].equals("1")||admin.getState().equals("1")){
			
			 %>
				<div class="search-wrap">
				<div class="search-content">
					<form id="searchForm_1" action="admin/ReportServlet?method=admin_reward_week_summary_excel" method="post">
						<table class="search-tab">
							<tr>
								<td>&nbsp;&nbsp;开始周:&nbsp;
								<select class="common-text" id="weekTag1"
									name="weekTag1">
									 <%
								 List<Integer> slist = (List<Integer>)request.getAttribute("slist");
								for(int i=slist.size()-1;i>=0;i--){
								int week = slist.get(i);
							%>
										<option value="<%=week%>">第<%=week%>期</option>
										<%}%>
								</select>&nbsp;&nbsp;结束周:&nbsp;<select class="common-text" id="weekTag2"
									name="weekTag2">
									 <%
								for(int i=0;i<slist.size();i++){
								int week = slist.get(i);
							%>
										<option value="<%=week%>">第<%=week%>期</option>
										<%}%>
								</select>&nbsp;
								</td>
						
								<td><input class="btn btn-primary" id="export_excel_1"
									name="export_excel_1" value="奖金明细汇总导出" type="submit">&nbsp;<span
									id="timeTag" style="color:red"></span>
								</td>
						
							</tr>
						</table>
					</form>
					</div>
					</div>
						<%
						}
					if(rankstr[8][21].equals("1")||admin.getState().equals("1")){
			
			 %>
					<div class="search-wrap">
										<div class="search-content">
						<form id="searchForm_2" action="admin/ReportServlet?method=admin_report_money_excel" method="post">
						<table class="search-tab">
							<tr>
								<td>&nbsp;&nbsp;起止日期:&nbsp;<input class="common-text" name="startTime" id="startTime" size="15" type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								</td>
						
								<td><input class="btn btn-primary" id="export_excel_2"
									name="export_excel_2" value="电子币余额汇总导出" type="submit">&nbsp;<span
									id="timeTag" style="color:red"></span>
								</td>
						
							</tr>
						</table>
					</form>
				</div>
			</div>
			<%
						}
					if(rankstr[8][23].equals("1")||admin.getState().equals("1")){
			
			 %>
					<div class="search-wrap">
										<div class="search-content">
						<form id="searchForm_3"  method="post">
						<table class="search-tab">
							<tr>
								<td>&nbsp;&nbsp;起止日期:&nbsp;<input class="common-text" name="startTime_3" id="startTime_3" size="15" type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-<input class="common-text" name="endTime_3" id="endTime_3" size="15"
									type="text" value='<%=StringUtil.notNull(endTime_3) %>' onfocus="new Calendar().show(this);">
								</td>
						
								<td><input class="btn btn-primary" id="export_excel_3"
									name="export_excel_3" value="奖金明细汇总导出(日结)" type="button">&nbsp;<span
									id="timeTag" style="color:red"></span>
								</td>
						
							</tr>
						</table>
					</form>
				</div>
			</div>
			<%} %>
			</div>
			
		<!--/main-->
	</div>
</body>
<script language="JavaScript">

	$(function() {
	$("#export_excel_1").click(function(){
	  	  if (!confirm("确认导出奖金明细汇总表？")) {
				return false;
			}
			else{
				$("#searchForm_1").submit();
	       		 return true;
	       		
			}
		 });
		 
		 $("#export_excel_2").click(function(){
	  	  if (!confirm("确认导出电子币余额汇总表？")) {
				return false;
			}
			else{
	       		$("#searchForm_2").attr("action", "admin/ReportServlet?method=admin_report_money_excel");
				$("#searchForm_2").submit();
	       		 return true;
	       		
			}
		 });
		
		 $("#export_excel_3").click(function(){
	  	  if (!confirm("确认导出奖金明细汇总表(新制度)？")) {
				return false;
			}else{
			var time ="2018-03-26";
			 var arrStart =  $("#startTime_3").val().split("-");
  			var startTime = new Date(arrStart[0], arrStart[1], arrStart[2]);
 			 var startTimes = startTime.getTime();
 			 var arrEnd = time.split("-");
  			var endTime = new Date(arrEnd[0], arrEnd[1], arrEnd[2]);
  			var endTimes = endTime.getTime();
	  			if (endTimes<=startTimes) {
		       		$("#searchForm_3").attr("action", "admin/ReportServlet?method=admin_report_reward_day_excel");
					$("#searchForm_3").submit();
		       		 return true;
		       	}else{
		       		 alert("新制度最早开始时间为2018-3-26！");
		       		  return false;
		       	};
			};
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
