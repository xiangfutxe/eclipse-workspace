<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Product" %>
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
	String state = (String)request.getAttribute("state");
	Integer[] sum = (Integer[])request.getAttribute("sum");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[4][4].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>仓库管理</title>
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
	
	 $("#exportExcel").click(function(){
	  	  if (!confirm("确认提交导出Excel？")) {
				return false;
			}
			else{
	       		$("#searchForm").attr("action", "admin/ReportServlet.do?method=exportExcel_product_give_summary");
				$("#searchForm").submit();
	       		 return true;
			}
		 });
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
											$('#searchForm').attr("action", "admin/ReportServlet.do?method=report_product_give_summary");
												$('#searchForm').submit();
												return true;
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
						class="crumb-step">&gt;</span><span class="crumb-name">报表统计</span>
						<span
						class="crumb-step">&gt;</span><span class="crumb-name">赠品汇总</span>
				</div>
			</div>
			
			<div class="result-wrap">
			<div class="result-content">
				<form  id="searchForm" 
						method="post">
						起止日期:<input class="common-text" name="startTime" id="startTime" size="15" type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
				
				<input class="btn btn-info" id="search"
									name="search" value="查询" type="button">
				<input class="btn btn-info" id="exportExcel"
									name="exportExcel" value="导出Excel" type="button">
									</form>
				</div>
				</div>
					<div class="result-wrap">
				<div class="result-content">
					<table class="result-tab" width="97%">
						<tr>

							<th>序号</th><th>时间段</th><th>产品编号</th><th>产品名称</th><th>产品规格</th><th>数量</th>
						
						</tr>
						 <%
							
							Collection coll =(Collection)request.getAttribute("coll");
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									Product p = (Product)it.next();
									t++;
							%>
 <tr <%if(t%2==1){%>class="even"<%}%>>
 <td><%=t %></td>
 <td><%=StringUtil.notNull(startTime) %>至<%=StringUtil.notNull(endTime) %></td>
 <td><%=StringUtil.notNull(p.getProductId()) %></td>
 <td><%=StringUtil.notNull(p.getProductName()) %>
    </td>
     <td><%=StringUtil.notNull(p.getSpecification()) %></td>
    <td><%=StringUtil.decimalFormat(p.getNum())%></td>
 </tr>
	 	<%}%>
	 	<tr><td colspan="5">合计</td><td><%=StringUtil.decimalFormat(sum[0])%></td></tr>
						<%}else{ %><tr><td colspan="6">暂无记录！</td></tr><%} %>
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