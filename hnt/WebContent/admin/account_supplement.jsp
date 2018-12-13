<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.AccountSupplement"%>
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
	String type = (String)request.getAttribute("type");
String userId = (String)request.getAttribute("userId");
String payType = (String)request.getAttribute("payType");

String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		 String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[6][6].equals("1")||admin.getState().equals("1")){
			String message = (String)request.getAttribute("message");
			if (message != null) {
			out.println("<script>");
			out.println("alert('"+message+"')");
			out.println("</script>");
			}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>补扣明细</title>
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
											
											var startTime = $("#startTime").val();
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
	       		$("#searchForm").attr("action", "admin/AccountSupplementServlet?method=exportExcel");
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
						class="crumb-step">&gt;</span><span class="crumb-name">扣账明细</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/AccountSupplementServlet?method=list" id="searchForm" method="post" >
						<table class="search-tab">
							<tr>
								<th width="80">账户类型</th><td><select name="type" id="type" class="common-text">
							
					<option value="">--请选择订单类型--</option>
					<option value="1" <%if(StringUtil.notNull(type).equals("1")){ %>selected<%} %>>报单券账户</option>
						<option value="2" <%if(StringUtil.notNull(type).equals("2")){ %>selected<%} %>>购物券账户</option>
						<option value="3" <%if(StringUtil.notNull(type).equals("3")){ %>selected<%} %>>复消券账户</option>
						<option value="4" <%if(StringUtil.notNull(type).equals("4")){ %>selected<%} %>>奖金券账户</option>
						<option value="5" <%if(StringUtil.notNull(type).equals("5")){ %>selected<%} %>>积分账户</option>
						</select>&nbsp;
						增扣类型：<select name="payType" id="payType" class="common-text">
							
					<option value="">--全部--</option>
					<option value="1" <%if(StringUtil.notNull(payType).equals("1")){ %>selected<%} %>>--补增--</option>
						<option value="2" <%if(StringUtil.notNull(payType).equals("2")){ %>selected<%} %>>--补扣--</option>
						</select>
							&nbsp;会员编号<input class="common-text" name="userId" id="userId" size="15"
									type="text" value='<%=StringUtil.notNull(userId) %>'>
							</td>
							</tr>
							<tr>
							<th width="70">起止日期:</th>
								<td><input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								&nbsp;<input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="button">&nbsp;<input
									class="btn btn-info" name="exportExcel" id="exportExcel" value="导出EXCEL"
									type="button"><span
									id="timeTag" style="color:red"></span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">
				
				<div class="result-content">
					<table class="result-tab" width="99%">
						<tr>

							<th>交易时间</th><th>账户类型</th><th>补扣类型</th><th>金额</th><th>用户编号</th><th>用户名称</th><th>摘要</th>
						
						</tr>
					 <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									AccountSupplement as = (AccountSupplement)it.next();
							%>
  	<tr>
 <td><%=StringUtil.parseToString(as.getEntryTime(), DateUtil.yyyyMMddHHmmss)%></td>
 <td><%if(as.getType()==1){ %>报单券账户<%} else if(as.getType()==2){ %>购物券账户<%} else if(as.getType()==3){ %>复消券账户<%} else if(as.getType()==4){ %>奖金券账户<%} else if(as.getType()==5){ %>积分账户<%}else{ %>-<%} %></td>
  <td><%if(as.getPayType()==1){ %>补增<%} else if(as.getPayType()==2){ %>补扣<%}else{ %>-<%} %></td>
 <td><%=StringUtil.decimalFormat(as.getAmount()) %>
 </td>
 <td><%=StringUtil.notNull(as.getUserId()) %></td>
  <td><%=StringUtil.notNull(as.getUserName()) %></td>
   <td><%=StringUtil.notNull(as.getSummary()) %></td>
 
 </tr>
		<%}
		double[] sum= pager.getSum();%>
		<tr><td colspan="7">累计金额：<%=StringUtil.decimalFormat(sum[0]) %>元</td></tr>
		<%}
						}else{ %><tr><td colspan="7">暂无记录！</td></tr><%} %>
					</table>



				</div>
			 <% if(pager!=null){ %>
				<div class="list-page">
					<div class="list-page-left">
						<form name="pageSizeForm" action="admin/AccountSupplementServlet?method=list"
							method="post">
								<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
							<input type="hidden" name="type" value="<%=StringUtil.notNull(type)%>"/>
							<input type="hidden" name="payType" value="<%=StringUtil.notNull(payType)%>"/>
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
						<form name="pageNoForm" action="admin/AccountSupplementServlet?method=list"
							method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
								<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
							<input type="hidden" name="type" value="<%=StringUtil.notNull(type)%>"/>
							<input type="hidden" name="payType" value="<%=StringUtil.notNull(payType)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页 | 上一页  |<%}else{%>
					<a
						href="admin/AccountSupplementServlet?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&payType=<%=StringUtil.notNull(payType)%>&type=<%=StringUtil.notNull(type)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&userId=<%=StringUtil.notNull(userId)%>">首页</a> | <a
						href="admin/AccountSupplementServlet?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&payType=<%=StringUtil.notNull(payType)%>&type=<%=StringUtil.notNull(type)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&userId=<%=StringUtil.notNull(userId)%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/AccountSupplementServlet?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&userId=<%=StringUtil.notNull(userId)%>&payType=<%=StringUtil.notNull(payType)%>&type=<%=StringUtil.notNull(type)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">
                    下一页</a> | <a
						href="admin/AccountSupplementServlet?method=list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&payType=<%=StringUtil.notNull(payType)%>&type=<%=StringUtil.notNull(type)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&userId=<%=StringUtil.notNull(userId)%>">尾页</a>
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