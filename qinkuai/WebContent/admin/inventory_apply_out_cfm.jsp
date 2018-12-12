<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.InventoryApply" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String state = (String)request.getAttribute("state");
	String type = (String)request.getAttribute("type");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][12].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>产品列表</title>
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
			return false;
		}
	}

	$(function() {

		$("#batchDel").click(function() {

			if (!confirm("确认要删除？")) {
				return false;
			} else {
				$('#delform').attr("action", "admin/inventory_apply_out_deleteAll.action");
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
	});
</script>
</head>

<body>
	<div class="container clearfix">
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a>
					<span class="crumb-step">&gt;</span><span class="crumb-name">仓库管理</span>
					<span class="crumb-step">&gt;</span><span class="crumb-name">出库仓管确认</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/InventoryServlet.do?method=apply_out_cfm" id="searchForm" method='post'>
						<table class="search-tab">
							<tr>
								<th width="120">选择分类:</th>
								<td><select class="common-text" id="type"
									name="type">
										<option value="">--全部--</option>
										<option value="1" <%if(StringUtil.notNull(type).equals("1")){%>selected<%}%>>
												加工出库
											</option>
											
										<option value="3" <%if(StringUtil.notNull(type).equals("3")){%>selected<%}%>>
												损耗出库
											</option>
											<option value="4" <%if(StringUtil.notNull(type).equals("4")){%>selected<%}%>>
												外销出库
												</option>
												<option value="5" <%if(StringUtil.notNull(type).equals("5")){%>selected<%}%>>
												体验出库
											</option>
											<option value="6" <%if(StringUtil.notNull(type).equals("6")){%>selected<%}%>>
												赠送出库
											</option>
											<option value="7" <%if(StringUtil.notNull(type).equals("7")){%>selected<%}%>>
												研发出库
											</option>
											
											<option value="8" <%if(StringUtil.notNull(type).equals("8")){%>selected<%}%>>
												退货出库
											</option>
											<option value="9" <%if(StringUtil.notNull(type).equals("9")){%>selected<%}%>>
												展销出库
											</option>
								</select></td>
								
								<th width="70">申请时间:</th>
								<td><input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-
								<td><input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								</td>
								<td>
								<td><input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="button"><span
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

							<th width="120px">申请单号</th>
							<th width="60px">出库类型</th>
							<th width="60px">单据编号</th>
							<th>申请人</th>
							<th>申请时间</th>
							<th>验收人</th>
							<th>验收时间</th>
							<th>确认人</th>
							<th>确认时间</th>
							<th>审核人</th>
							<th>审核时间</th>
							<th>当前状态</th>
							<th>操作</th>
						</tr>
							 <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									InventoryApply apply = (InventoryApply)it.next();
							%>
							<tr>
								<td><%=StringUtil.notNull(apply.getApplyId()) %>
								</td>
								<td><%if(apply.getType()==1){ %>加工出库<%}else if(apply.getType()==2){ %>盘亏出库
								<%}else if(apply.getType()==3){ %>损耗出库<%}else if(apply.getType()==4){ %>外销出库
								<%}else if(apply.getType()==5){ %>体验出库
								<%}else if(apply.getType()==6){ %>赠送出库
								<%}else if(apply.getType()==7){ %>研发出库
								<%}else if(apply.getType()==8){ %>退货出库
								<%}else if(apply.getType()==9){ %>展销出库
								<%} %>
								</td>
								<td><%=StringUtil.notNull(apply.getDocNum()) %>
								</td>
								<td><%=StringUtil.notNull(apply.getOperatorId()) %></td>
								<td><%=StringUtil.parseToString(apply.getEntryTime(), DateUtil.yyyyMMddHHmmss)%></td>
								<td><%=StringUtil.notNull(apply.getCheckId()) %></td>
								<td><%=StringUtil.parseToString(apply.getCheckTime(), DateUtil.yyyyMMddHHmmss)%></td>
								<td><%=StringUtil.notNull(apply.getConfirmId()) %></td>
								<td><%=StringUtil.parseToString(apply.getConfirmTime(), DateUtil.yyyyMMddHHmmss)%></td>
								<td><%=StringUtil.notNull(apply.getReviewId()) %></td>
								<td><%=StringUtil.parseToString(apply.getReviewTime(), DateUtil.yyyyMMddHHmmss)%></td>
								<td><%if(apply.getState()==1){%>数据录入中<%}else if(apply.getState()==2) {%>待质检验收<%}else if(apply.getState()==3) {%>待仓管确认<%}else if(apply.getState()==4) { %>待财务审核<%}else if(apply.getState()==5) { %>财务已审核<%}else if(apply.getState()==7) { %>财务审核不通过<%} %></td>
								<td><a class="link-update"
									href="admin/InventoryServlet.do?method=apply_out_detail_cfm&id=<%=StringUtil.notNull(apply.getApplyId())%>">详情</a>&nbsp;
									</td>
							</tr>
						<%}}}else{ %>
						<tr> <td colspan="13">暂无申请记录。</td></tr>
						<%} %>
					</table>

				</div>
				  <% if(pager!=null){ %>
				<div class="list-page">
					<div class="list-page-left">
						<form name="pageSizeForm" action="admin/InventoryServlet.do?method=apply_out_cfm"
							method="post">
								<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
							<input type="hidden" name="type" value="<%=type%>"/>
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
						<form name="pageNoForm" action="admin/InventoryServlet.do?method=apply_out_cfm"
							method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
							<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
								<input type="hidden" name="type" value="<%=type%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页 | 上一页  |<%}else{%>
					<a
						href="admin/InventoryServlet.do?method=apply_out_cfm&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&state=<%=StringUtil.notNull(state)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&type=<%=type%>">首页</a> | <a
						href="admin/InventoryServlet.do?method=apply_out_cfm&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&state=<%=StringUtil.notNull(state)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&type=<%=type%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/InventoryServlet.do?method=apply_out_cfm&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&type=<%=type%>&state=<%=StringUtil.notNull(state)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">
                    下一页</a> | <a
						href="admin/InventoryServlet.do?method=apply_out_cfm&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&state=<%=StringUtil.notNull(state)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&type=<%=type%>">尾页</a>
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