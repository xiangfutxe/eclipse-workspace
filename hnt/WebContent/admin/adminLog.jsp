<%@page import="com.ssm.utils.ConstantsLog"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.AdminLog" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil"%>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.utils.ConstantsLog"%>
<%@page import="com.ssm.pojo.Admin" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
String adminName = (String)request.getAttribute("adminName");
String type = (String)request.getAttribute("type");


	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[9][19].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>日志管理</title>
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
	       		$("#searchForm").attr("action", "admin/AdminLogServlet?method=exportExcel");
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
											$('#searchForm').attr("action","admin/AdminLogServlet?method=admin_log_list");
												$('#searchForm').submit();
											} else {
												alert(str);
												return false;
											}
						});
						
		$('#delAll').click(
						function() {
										if (!confirm("确认要批量删除管理员工作日志？")) {	
										
												return false;
												} else {
												var startTime = $("#startTime")
													.val();
											var endTime = $("#endTime").val();
											var str = "";
											var a = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;
											
											if (startTime == "") {
													str = str + "开始时间不能为空！\n";
													$("#startTime").focus();
											}else if (endTime == "") {
													str = str + "结束时间不能为空！\n";
													$("#endTime").focus();
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
											$('#searchForm').attr("action","admin/AdminLogServlet?method=admin_del");
												$('#searchForm').submit();
												return true;
											} else {
												alert(str);
												return false;
											}
											
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
						class="crumb-step">&gt;</span><span class="crumb-name">日志管理</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form  id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
								<th width="70">管理员姓名</th><td><input class="common-text" name="adminName" id="adminName" size="15"
									type="text" value='<%=StringUtil.notNull(adminName) %>'>
							
								&nbsp;类型：<select id="type" name="type" class="common-text" >
								<option value="">--全部--</option>
								<option value="<%=ConstantsLog.LOGTYPE_1 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_1)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_1 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_2 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_2)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_2 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_3 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_3)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_3 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_4 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_4)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_4 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_5 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_5)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_5 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_6 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_6)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_6 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_7 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_7)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_7 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_8 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_8)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_8 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_9 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_9)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_9 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_10 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_10)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_10 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_11 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_11)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_11 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_12 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_12)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_12 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_13 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_13)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_13 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_14 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_14)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_14 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_15 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_15)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_15 %></option>
								<option value="<%=ConstantsLog.LOGTYPE_16 %>" <%if(StringUtil.notNull(type).equals(ConstantsLog.LOGTYPE_16)){ %>selected<%} %>><%=ConstantsLog.LOGTYPE_16 %></option>
								</select>
							</td>
							<th width="70">起止日期:</th>
								<td><input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-
								<td><input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								</td>
								<td><input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="button">&nbsp;<input class="btn" id="delAll"
									name="delAll" value="按日期删除" type="button">
									&nbsp;<input class="btn" id="exportExcel"
									name="exportExcel" value="日志导出" type="button"><span
									id="timeTag" style="color:red"></span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">
				
				<div class="result-content">
					<table class="result-tab" width="97%">
						<tr>

							<th>编号</th><th>日志内容</th><th>操作员</th><th>操作时间</th><th>操作</th>
						
						</tr>
						 <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									AdminLog log = (AdminLog)it.next();
									t++;
							%>
 <tr <%if(t%2==1){%>class="even"<%}%>>
 <td><%=t %></td>
 <td><%=StringUtil.notNull(log.getContents()) %>
    </td>
    <td><%=StringUtil.notNull(log.getAdminName()) %>
    </td>
    <td><%=StringUtil.parseToString(log.getEntryTime(), DateUtil.yyyyMMddHHmmss) %>
    </td>
  <td><a href="admin/AdminLogServlet?method=admin_log_del&id=<%=log.getId()%>">删除</a></td>
 </tr>
	 	<%}}
						}else{ %><tr><td colspan="5">暂无记录！</td></tr><%} %>
					</table>



				</div>
			 <% if(pager!=null){ %>
				<div class="list-page">
					<div class="list-page-left">
						<form name="pageSizeForm" action="admin/AdminLogServlet?method=admin_log_list"
							method="post">
								<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="adminName" value="<%=StringUtil.notNull(adminName)%>"/>
								<input type="hidden" name="type" value="<%=StringUtil.notNull(type)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
							当前每页显示<select name="pageSizeStr" id="pageSizeStr"
								onchange="javascript:pageSizeForm.submit();">

								<option value="10"
							<%if(pager.getPageSize()==10) {%>selected<%}%>>10</option>
						<option value="20"
							<%if(pager.getPageSize()==20) {%> selected<%}%>>20</option>
						<option value="40"
							<%if(pager.getPageSize()==40) {%> selected<%}%>>40</option>
						<option value="60"
							<%if(pager.getPageSize()==60) {%> selected<%}%>>60</option>
						<option value="80"
							<%if(pager.getPageSize()==80) {%>selected<%}%>>80</option>
						<option value="100"
							<%if(pager.getPageSize()==100) {%> selected<%}%>>100</option>
							<option value="200"
							<%if(pager.getPageSize()==200) {%> selected<%}%>>200</option>
							</select>条记录
						</form>

					</div>
					<div class="list-page-right">
						<form name="pageNoForm" action="admin/AdminLogServlet?method=admin_log_list"
							method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
							<input type="hidden" name="adminName" value="<%=StringUtil.notNull(adminName)%>"/>
							<input type="hidden" name="type" value="<%=StringUtil.notNull(type)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页 | 上一页  |<%}else{%>
					<a
						href="admin/AdminLogServlet?method=admin_log_list&type=<%=StringUtil.notNull(type)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&adminName=<%=StringUtil.notNull(adminName)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">首页</a> | <a
						href="admin/AdminLogServlet?method=admin_log_list&type=<%=StringUtil.notNull(type)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&adminName=<%=StringUtil.notNull(adminName)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/AdminLogServlet?method=admin_log_list&type=<%=StringUtil.notNull(type)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&adminName=<%=StringUtil.notNull(adminName)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">
                    下一页</a> | <a
						href="admin/AdminLogServlet?method=admin_log_list&type=<%=StringUtil.notNull(type)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&adminName=<%=StringUtil.notNull(adminName)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">尾页</a>
				<%}else{ %>下一页 | 尾页<%} %>&nbsp; 跳转到第<select
						name="pageNoStr" onchange="javascript:pageNoForm.submit();">
						<%for(int i=0;i<pager.getPageCount();i++){ %>
							<option value="<%=i+1 %>" <%if(pager.getPageNo()==(i+1)){%>selected<%} %>>
								<%=i+1 %>
							</option>
							<%} %>
					</select>页
						</form>
					</div>
                <%} %>
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