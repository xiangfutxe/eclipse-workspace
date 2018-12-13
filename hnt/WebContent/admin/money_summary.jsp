<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.AccountDetail" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String startTime = (String)request.getAttribute("startTime");
	String endTime = (String)request.getAttribute("endTime");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		  String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[6][9].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>余额汇总查询</title>
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
						class="crumb-step">&gt;</span>财务管理<span class="crumb-step">&gt;</span><span>余额查询</span>
				</div>
			</div>
			<div class="result-wrap">
			
				<div class="search-wrap">
										<div class="search-content">
						<form id="searchForm_2"  method="post">
						<table class="search-tab">
							<tr>
								<td>&nbsp;&nbsp;起止日期:&nbsp;<input class="common-text" name="startTime" id="startTime" size="15" type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								</td>
						
								<td><input class="btn btn-primary" id="export_excel_2"
									name="export_excel_2" value="查询" type="submit">&nbsp;<span
									id="timeTag" style="color:red"></span>
								</td>
						
							</tr>
						</table>
					</form>
				</div>
			</div>
					<div class="result-content">
						<table class="result-tab" width="100%">
							<tbody>
							
							<tr>
							<td  rowspan="2">起止时间</td>
							<td colspan="4">报单券</td>
							<td colspan="4">复消券</td>
							<td colspan="4">奖金券</td>
							</tr>
							<tr>
							<td>期初余额</td>
							<td>本期收入</td>
							<td>本期支出</td>
							<td>期末余额</td>
							<td>期初余额</td>
							<td>本期收入</td>
							<td>本期支出</td>
							<td>期末余额</td>
							<td>期初余额</td>
							<td>本期收入</td>
							<td>本期支出</td>
							<td>期末余额</td>
							</tr>
							<% AccountDetail ad = (AccountDetail)request.getAttribute("sys_ad");
							if(ad!=null){ %>
							<tr>
							<td><%=StringUtil.parseToString(ad.getStartTime(), DateUtil.yyyyMMdd) %>至<%=StringUtil.parseToString(ad.getEndTime(), DateUtil.yyyyMMdd) %></td>
							<td><%=StringUtil.decimalFormat(ad.getEmoneyBalance()) %></td>
							<td><%=StringUtil.decimalFormat(ad.getEmoneyAdd()) %></td>
							<td><%=StringUtil.decimalFormat(ad.getEmoneySub()) %></td>
							<td><%=StringUtil.decimalFormat(ad.getEmoneyBalance()+ad.getEmoneyAdd()-ad.getEmoneySub()) %></td>
							<td><%=StringUtil.decimalFormat(ad.getDmoneyBalance()) %></td>
							<td><%=StringUtil.decimalFormat(ad.getDmoneyAdd()) %></td>
							<td><%=StringUtil.decimalFormat(ad.getDmoneySub()) %></td>
							<td><%=StringUtil.decimalFormat(ad.getDmoneyBalance()+ad.getDmoneyAdd()-ad.getDmoneySub()) %></td>
							<td><%=StringUtil.decimalFormat(ad.getRmoneyBalance()) %></td>
							<td><%=StringUtil.decimalFormat(ad.getRmoneyAdd()) %></td>
							<td><%=StringUtil.decimalFormat(ad.getRmoneySub()) %></td>
							<td><%=StringUtil.decimalFormat(ad.getRmoneyBalance()+ad.getRmoneyAdd()-ad.getRmoneySub()) %></td>
							<%} %>
							</tbody>
						</table>
					</div>
			</div>

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
	  	  						var startTime = $("#startTime")
													.val();
											var endTime = $("#endTime").val();
											var str = "";
											var a = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;
											
											if (startTime == "") {
												if(endTime!=""){
													str = str + "开始时间不能为空！\n";
													$("#startTime").focus();
												}
											}else if (endTime == "") {
												if(startTime!=""){
													str = str + "结束时间不能为空！\n";
													$("#endTime").focus();
												}
											} else if (startTime.match(a) == null) {
												str = str + "开始时间格式有误！\n";
												$("#startTime").focus();
											} else if (endTime.match(a) == null) {
												str = str + "结束时间格式有误！\n";
												$("#endTime").focus();
											}else if(startTime>endTime)
												{
												str = str + "开始时间不能超过结束时间！\n";
												$("#endTime").focus();
											}
											
											if (str == "") {
$("#searchForm_2").attr("action", "admin/AccountServlet?method=admin_money_summary");
				$("#searchForm_2").submit();
					       		 return true;
					} else {
												alert(str);
												return false;
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