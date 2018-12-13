<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.WithDrew" %>
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
String userId = (String)request.getAttribute("userId");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[6][2].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>提现审核</title>
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
<script  type="text/javascript">
	function delcfm() {
		if (!confirm("确认要删除？")) {
			return false;
		}
	}

	$(function() {

		$("#yes").click(function() {

			if (!confirm("确认要审核通过？")) {
				return false;
			} else {
				return true;
			}

		});
		
		 $("#exportExcel").click(function(){
	  	  if (!confirm("确认提交导出Excel？")) {
				return false;
			}
			else{
	       		$("#searchForm").attr("action", "admin/WithDrewServlet?method=exportExcel");
				$("#searchForm").submit();
	       		 return true;
			}
		 });
		
		$("#no").click(function() {

			if (!confirm("确认要审核不通过？")) {
				return false;
			} else {
				return true;
			}

		});
		$("#reback").click(function() {

			if (!confirm("确认要撤回已通过的审批流程？")) {
				return false;
			} else {
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
												$("#searchForm").attr("action", "admin/WithDrewServlet?method=admin_list");
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
						class="crumb-step">&gt;</span><span class="crumb-name">财务管理</span><span
						class="crumb-step">&gt;</span><span class="crumb-name">提现审核</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form  id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
								<th width="70">选择状态:</th>
								<td><select class="common-text" id="state"
									name="state">
										<option value="">--请选择状态--</option>
										<option value="1" <%if(StringUtil.notNull(state).equals("1")){ %>selected<%} %>
											>--待审核--</option>
										<option value="2" <%if(StringUtil.notNull(state).equals("2")){ %>selected<%} %>
											>--审核通过--</option>
										<option value="3" <%if(StringUtil.notNull(state).equals("3")){ %>selected<%} %>>--审核不通过--</option>
								<option value="0" <%if(StringUtil.notNull(state).equals("0")){ %>selected<%} %>
											>--已删除--</option>
								</select>&nbsp;会员编号:<input class="common-text" id="userId" name="userId" value="<%=StringUtil.notNull(userId) %>" type="text"/>
								</td></tr><tr><th width="70">申请时间:</th><td><input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-
								<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								&nbsp;<input class="btn btn-primary" id="search"
									name="search" value="查询" type="button">&nbsp;<input class="btn btn-green" id="exportExcel"
									name="exportExcel" value="导出Excel" type="button"><span
									id="timeTag" style="color:red"></span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">
				
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th>申请编号</th><th>提现金额</th><th>手续费</th><th>到账金额</th><th>账户信息</th><th>申请人</th><th>申请时间</th><th>审批人</th><th>审批时间</th><th>状态</th><th>操作</th>
						</tr>
					 <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									WithDrew ca = (WithDrew)it.next();
									t++;
							%>
  	<tr>
<td><%=StringUtil.notNull(ca.getApplyId())%>
 	<td><%=StringUtil.decimalFormat(ca.getAmount()) %> </td>
 		<td><%=StringUtil.decimalFormat(ca.getFee()) %> 
		<td><%=StringUtil.decimalFormat(ca.getActualAmount()) %> 
	 
<td><%=StringUtil.notNull(ca.getAccountName()) %> |<%=StringUtil.notNull(ca.getAccountId()) %> <br>
<%=StringUtil.notNull(ca.getBankName()) %><%=StringUtil.notNull(ca.getBankAdr()) %>
<td><%=StringUtil.notNull(ca.getUserName())%>(<%=StringUtil.notNull(ca.getUserId())%>)</td>
<td><%=StringUtil.parseToString(ca.getApplyTime(), DateUtil.yyyyMMddHHmmss)%>
<td><%=StringUtil.notNull(ca.getAdmin())%></td>
<td><%=StringUtil.parseToString(ca.getReviewTime(), DateUtil.yyyyMMddHHmmss)%>
 <td><%if(ca.getState()==1) {%>待审批<%}else if(ca.getState()==2){ %>
										审核通过<%}else if(ca.getState()==3){ %>
										审核不通过<%}else { %>已删除<%} %>
	</td>
										<td><a href="admin/WithDrewServlet?method=apply_comment_edit&id=<%=StringUtil.notNull(ca.getApplyId()) %>">备注 </a><%if(ca.getState()==1) {%>&nbsp;|&nbsp;<a id="yes" href="admin/WithDrewServlet?method=apply_yes&id=<%=StringUtil.notNull(ca.getApplyId()) %>">审核通过 </a>
										&nbsp;|&nbsp;<a id='no' href="admin/WithDrewServlet?method=apply_no&id=<%=StringUtil.notNull(ca.getApplyId()) %>">审核不通过 </a><%} %>
										<%if(ca.getState()==2) {%>&nbsp;|&nbsp;
										<a id='reback' href="admin/WithDrewServlet?method=apply_yes_reback&id=<%=StringUtil.notNull(ca.getApplyId()) %>">撤销审批 </a>
										<%} %>
										</td>
 </tr>
		<%}%>
							<%}
							double[] sum = pager.getSum();
							if(sum!=null){
							%>
								<tr>
							<td>合计</td>
							<td><%=StringUtil.decimalFormat(sum[0]) %></td>
							<td><%=StringUtil.decimalFormat(sum[1]) %></td>
							<td><%=StringUtil.decimalFormat(sum[2]) %></td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							
						 </tr>
							<%
							}
						}else{ %><tr>
							<td colspan="11">暂无记录！</td>
						 </tr>
						 <%} %>
					</table>

				</div>
			 <% if(pager!=null){ %>
				<div class="list-page">
					<div class="list-page-left">
						<form name="pageSizeForm" action="admin/WithDrewServlet?method=admin_list"
							method="post">
								<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
							<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
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
						<form name="pageNoForm" action="admin/WithDrewServlet?method=admin_list"
							method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
							<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
								<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页 | 上一页  |<%}else{%>
					<a
						href="admin/WithDrewServlet?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&state=<%=StringUtil.notNull(state)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&userId=<%=StringUtil.notNull(userId)%>">首页</a> | <a
						href="admin/WithDrewServlet?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&state=<%=StringUtil.notNull(state)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&userId=<%=StringUtil.notNull(userId)%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/WithDrewServlet?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&userId=<%=StringUtil.notNull(userId)%>&state=<%=StringUtil.notNull(state)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">
                    下一页</a> | <a
						href="admin/WithDrewServlet?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&state=<%=StringUtil.notNull(state)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&userId=<%=StringUtil.notNull(userId)%>">尾页</a>
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