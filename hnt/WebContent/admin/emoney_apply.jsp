<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.ChargeApply" %>
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
		String typeKey = (String)request.getAttribute("typeKey");
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
		if(rankstr[6][1].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>充值管理</title>
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
		if (!confirm("确认要删除？")) {
			window.event.returnValue = false;
		}
	}

	function cfm(str) {
		if (!confirm("确认要" + str + "？")) {
			window.event.returnValue = false;
		}
	}

	$(function() {

		$("#batchDel").click(function() {

			if (!confirm("确认要删除？")) {
				window.event.returnValue = false;
			} else {
				$('#delform').attr("action", "admin/apply_deleteAll.action");
				$('#delform').submit();
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
												$('#searchForm').submit();
											} else {
												alert(str);
												return false;
											}
						});
						$("#exportExcel").click(function(){
	  	  if (!confirm("确认提交导出Excel？")) {
				return false;
			}
			else{
	       		$("#searchForm").attr("action", "admin/ChargeApplyServlet?method=exportExcel");
				$("#searchForm").submit();
	       		 return true;
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
						class="crumb-step">&gt;</span><span class="crumb-name">充值审核</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/ChargeApplyServlet?method=admin_list" id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
							<th width="70">会员编号:</th>
								<td><input class="common-text" id="userId" name="userId" value="<%=StringUtil.notNull(userId) %>" type="text"/>
										选择状态:<select class="common-text" id="state" name="state">
										<option value="">--请选择状态--</option>
										<option value="1" <%if(StringUtil.notNull(state).equals("1")){ %>selected<%} %>
											>--待审核--</option>
										<option value="2" <%if(StringUtil.notNull(state).equals("2")){ %>selected<%} %>
											>--审核通过--</option>
										<option value="3" <%if(StringUtil.notNull(state).equals("3")){ %>selected<%} %>>--审核不通过--</option>
								<option value="0" <%if(StringUtil.notNull(state).equals("0")){ %>selected<%} %>
											>--已删除--</option>
								</select>
								充值类型:<select class="common-text" id="typeKey" name="typeKey">
										<option value="">--请选择充值类型--</option>
										<option value="1" <%if(StringUtil.notNull(typeKey).equals("1")){ %>selected<%} %>>现金充值</option>
											<option value="2"<%if(StringUtil.notNull(typeKey).equals("2")){ %>selected<%} %>>汇款充值</option>
								</select>
								</tr>
								<tr>
								<th width="70">申请时间:</th><td><input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-
								<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								<input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="button">&nbsp;<input
									class="btn btn-info" name="exportExcel" id="exportExcel" value="导出EXCEL"
									type="button"><span
									id="timeTag" style="color:red"></span></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">

				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th>申请编号</th>
							<th>充值金额</th>
							<th>收款账户信息</th>
							<th>充值类型</th>
							<th>备注信息</th>
							<th>申请人</th>
							<th>申请时间</th>
							<th>审批人</th>
							<th>审批时间</th>
							<th>状态</th>
							<th>操作</th>

						</tr>
						 <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							System.out.println(coll.size());
							if(coll!=null){
									Iterator it = coll.iterator();
									while(it.hasNext()){
									ChargeApply ca = (ChargeApply)it.next();
							%>
								<tr>
									<td><%=StringUtil.notNull(ca.getApplyId())%>
									</td>
									<td><%=StringUtil.decimalFormat(ca.getAmount()) %> 
									</td>
									<td><%if(ca.getType()==2) {%><%=StringUtil.notNull(ca.getAccountName()) %> |<%=StringUtil.notNull(ca.getAccountId()) %> |<%=StringUtil.notNull(ca.getBankName()) %><%} %>
									</td>
									<td><%if(ca.getType()==1) {%>现金充值<%}else if(ca.getType()==2){ %>
										汇款充值<%} %>
									</td>
									<td><%=StringUtil.notNull(ca.getRemark()) %>
									</td>
									<td><%=StringUtil.notNull(ca.getUserName()) %>(<%=StringUtil.notNull(ca.getUserId()) %>)
									</td>
									<td><%=StringUtil.parseToString(ca.getApplyTime(), DateUtil.yyyyMMdd)%>
									<br><%=StringUtil.parseToString(ca.getApplyTime(), DateUtil.HHmmss)%>
									</td>
									<td><%=StringUtil.notNull(ca.getAdmin()) %>
									</td>
									<td><%=StringUtil.parseToString(ca.getReviewTime(), DateUtil.yyyyMMdd)%>
									<br><%=StringUtil.parseToString(ca.getReviewTime(), DateUtil.HHmmss)%>
									</td>
									<td><%if(ca.getState()==1) {%>待审批<%}else if(ca.getState()==2){ %>
										审核通过<%}else if(ca.getState()==3){ %>
										审核不通过<%}else { %>已删除<%} %>
										
									</td>
									<td><%if(ca.getState()==1) {%>
											<a href="admin/ChargeApplyServlet?method=apply_yes&id=<%=ca.getId() %>&appllyId=<%=ca.getApplyId() %>"
												onclick='cfm("确认审核通过？审核通过后会直接将金额转入会员账户！")'>审核通过</a>&nbsp;|&nbsp;<a
												href="admin/ChargeApplyServlet?method=apply_no&id=<%=ca.getId() %>&appllyId=<%=ca.getApplyId() %>" onclick='cfm("确认审核不通过？")'>审核不通过</a>
										<%} %>
									</td>
								</tr>
							<%}}
								double[] sum= pager.getSum();%>
		<tr><td colspan="11">累计金额：<%=StringUtil.decimalFormat(sum[0]) %>元</td></tr>
		<%
						}else{ %><tr>
							<td colspan="11">暂无记录！</td>
						 </tr>
						 <%} %>
					</table>


				</div>
				  <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/ChargeApplyServlet?method=admin_list" method="post">
							<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
							<input type="hidden" name="typeKey" value="<%=StringUtil.notNull(typeKey)%>"/>
							<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					当前每页显示<select name="pageSizeStr" id="pageSizeStr" onchange="javascript:pageSizeForm.submit();">
					
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
                    
			</div> <div class="list-page-right">
			<form name="pageNoForm"  action="admin/ChargeApplyServlet?method=admin_list" method="post">
			<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
							<input type="hidden" name="typeKey" value="<%=StringUtil.notNull(typeKey)%>"/>
							<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/ChargeApplyServlet?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1
						&userId=<%=StringUtil.notNull(userId)%>&state=<%=StringUtil.notNull(state)%>&typeKey=<%=StringUtil.notNull(typeKey)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">首页</a> | <a
						href="admin/ChargeApplyServlet?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/ChargeApplyServlet?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&userId=<%=StringUtil.notNull(userId)%>&state=<%=StringUtil.notNull(state)%>&typeKey=<%=StringUtil.notNull(typeKey)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">
                    下一页</a> | <a
						href="admin/ChargeApplyServlet?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>
						&userId=<%=StringUtil.notNull(userId)%>&state=<%=StringUtil.notNull(state)%>&typeKey=<%=StringUtil.notNull(typeKey)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">尾页</a>
				<%}else{ %>下一页 | 尾页<%} %>&nbsp;&nbsp; &nbsp; 跳转到第<select
						name="pageNoStr" onchange="javascript:pageNoForm.submit();">
						<%for(int i=0;i<pager.getPageCount();i++){ %>
							<option value="<%=i+1 %>" <%if(pager.getPageNo()==(i+1)){%>selected<%} %>>
								<%=i+1 %>
							</option>
							<%} %>
					</select>页&nbsp;
			</form>
				</div>
                </div>
                <%} %>
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
