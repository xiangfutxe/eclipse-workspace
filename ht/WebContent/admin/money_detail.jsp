<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.AccountDetail" %>
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
	String userId = (String)request.getAttribute("userId");
String startTime = (String)request.getAttribute("startTime");
String type = (String)request.getAttribute("type");
String tradeType = (String)request.getAttribute("tradeType");
String endTime = (String)request.getAttribute("endTime");
double[] sum = (double[])request.getAttribute("sum");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[6][7].equals("1")||admin.getState().equals("1")){
	String message = (String) request.getAttribute("message");
	if (message != null) {
		out.println("<script>");
		out.println("alert('" + message + "')");
		out.println("</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>资金明细</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<%=path%>/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
<script src="<%=path%>/js/jquery.js"></script>
<script src="<%=path%>/js/calendar.js"></script>
<script>
	$(document)
			.ready(
					function() {
						//提交按钮,所有验证通过方可提交
						$('#search')
								.click(
										function() {

											var str = "";
											var a = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;

											if ($("#type").val() == "") {
												str = str + "账户类型不能为空！\n";
												$("#type").focus();
											} else if ($("#startTime").val() == "") {
												if ($("#endTime").val() != "") {
													str = str
															+ "结束时间不为空,开始时间不能为空！\n";
													$("#startTime").focus();
												}
											} else if ($("#startTime").val()
													.match(a) == null) {
												str = str + "开始时间格式有误！\n";
												$("#startTime").focus();
											} else if ($("#endTime").val() == "") {
												str = str
														+ "开始时间不为空，结束时间不能为空！\n";
												$("#endTime").focus();
											} else if ($("#endTime").val()
													.match(a) == null) {
												str = str + "结束时间格式有误！\n";
												$("#endTime").focus();
											} else if ($("#startTime").val() > $(
													"#endTime").val()) {
												str = str + "开始时间不能大于结束时！\n";
												$("#endTime").focus();
											}

											if (str == "") {
												$('#myform').submit();
												return true;
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
	       		$("#myform").attr("action", "admin/AccountServlet.do?method=exportExcel");
				$("#myform").submit();
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
						class="crumb-step">&gt;</span><span class="crumb-name">财务管理</span>
					<span class="crumb-step">&gt;</span><span class="crumb-name">资金明细</span>
					
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/AccountServlet.do?method=admin_money_detail" method="post" id="myform">
						<table class="search-tab">
							<tr>
								<th width="60">账户类型:</th>
								<td><select class="common-text" id="type" name="type">
										<option value="">--请选择账户类型--</option>
									<option value="1"
											<%if(StringUtil.notNull(type).equals("1")){%>selected<%} %>>--电子币账户--</option>
										<option value="2"
											<%if(StringUtil.notNull(type).equals("2")){%>selected<%} %>>--积分账户--</option>
											<option value="3"
											<%if(StringUtil.notNull(type).equals("3")){%>selected<%} %>>--奖金币账户--</option>
										
								</select>&nbsp;&nbsp;客户信息: <input class="common-text" name="userId"
									id="userId" type="text"  value='<%=StringUtil.notNull(userId) %>'
									/> 
									&nbsp;&nbsp;交易类型: <select class="common-text" id="tradeType" name="tradeType">
										<option value="">--请选择交易类型--</option>
									<option value="汇款充值"
											<%if(StringUtil.notNull(tradeType).equals("汇款充值")){%>selected<%} %>>--汇款充值--</option>
										<option value="现金充值"
											<%if(StringUtil.notNull(tradeType).equals("现金充值")){%>selected<%} %>>--现金充值--</option>
											<option value="积分奖励"
											<%if(StringUtil.notNull(tradeType).equals("积分奖励")){%>selected<%} %>>--积分奖励--</option>
										<option value="注册会员"
											<%if(StringUtil.notNull(tradeType).equals("注册会员")){%>selected<%} %>>--注册会员--</option>
											<option value="会员升级"
											<%if(StringUtil.notNull(tradeType).equals("会员升级")){%>selected<%} %>>--会员升级--</option>
											<option value="会员退单"
											<%if(StringUtil.notNull(tradeType).equals("会员退单")){%>selected<%} %>>--会员退单--</option>
											<option value="零售购物"
											<%if(StringUtil.notNull(tradeType).equals("零售购物")){%>selected<%} %>>--零售购物--</option>
											<option value="电子币互转"
											<%if(StringUtil.notNull(tradeType).equals("电子币互转")){%>selected<%} %>>--电子币互转--</option>
											<option value="电子币提现"
											<%if(StringUtil.notNull(tradeType).equals("电子币提现")){%>selected<%} %>>--电子币提现--</option>
											<option value="提现退回"
											<%if(StringUtil.notNull(tradeType).equals("提现退回")){%>selected<%} %>>--提现退回--</option>
											<option value="购物退回"
											<%if(StringUtil.notNull(tradeType).equals("购物退回")){%>selected<%} %>>--购物退回--</option>
												<option value="转为报单券"
											<%if(StringUtil.notNull(tradeType).equals("转为报单券")){%>selected<%} %>>--转为报单券--</option>
											<option value="结算重置"
											<%if(StringUtil.notNull(tradeType).equals("结算重置")){%>selected<%} %>>--结算重置--</option>
								</select>
								</td>
							</tr>
							<tr>
								<th width="60">起止日期:</th>
								<td><input class="common-text" name="startTime" id="startTime" size="20"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-
								<input class="common-text" name="endTime" id="endTime" size="20"
									type="text"  value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">&nbsp;<input
									class="btn btn-primary" name="search" id="search" value="查询"
									type="button">&nbsp;<input
									class="btn btn-info" name="exportExcel" id="exportExcel" value="导出EXCEL"
									type="button"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">

				<div class="result-content">
				
					<table class="result-tab" width="100%">
						<tr>
							<td rowspan="2">序号</td>
							<td rowspan="2">交易日期</td>
							<td rowspan="2">交易类型</td>
							<td colspan="2">记账金额</td>
							<td rowspan="2">余额</td>
							<td rowspan="2">用户信息</td>
							<td rowspan="2">摘要</td>
						</tr>
						<tr>
							<td>收入</td>
							<td>支出</td>
						</tr>
						 <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									AccountDetail ad = (AccountDetail)it.next();
									t++;
							%>
 <tr <%if(t%2==1){%>class="even"<%}%>>
									<td><%=t %></td>
								<td><%=StringUtil.parseToString(ad.getTime(), DateUtil.yyyyMMddHHmmss) %>
									</td>
										<td><%=StringUtil.notNull(ad.getTradeType()) %></td>
								<td><%if(StringUtil.notNull(ad.getPayType()).equals("1")){ %>
											<%=StringUtil.decimalFormat(ad.getAmount())%>
											<%} else{%>-<%} %>
									</td>
									<td><%if(StringUtil.notNull(ad.getPayType()).equals("2")){ %>
											<%=StringUtil.decimalFormat(ad.getAmount())%>
											<%} else{%>-<%} %>
									</td>
									<td><%=StringUtil.decimalFormat(ad.getBalance())%>
									</td>
									<td><%=StringUtil.notNull(ad.getUserName())%>(<%=StringUtil.notNull(ad.getUserId())%>)
									</td>
									<td><%=StringUtil.notNull(ad.getSummary()) %>
									</td>

								</tr>
								<%}}%>
						
							<tr>
								<td colspan='8' style="text-align:left">累计总收入：<%=StringUtil.decimalFormat(sum[0])%><br>
								累计总支出：<%=StringUtil.decimalFormat(sum[1])%>
								</td>
							</tr>
						<%}else{ %>
						<tr>	<td colspan="8">暂无交易记录！</td></tr>
						<%} %>
				  </tbody>	</table>
					
							</div>
				 <% if(pager!=null){ %>
				<div class="list-page">
					<div class="list-page-left">
						<form name="pageSizeForm" action="admin/AccountServlet.do?method=admin_money_detail"
							method="post">
								<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
							<input type="hidden" name="type" value="<%=StringUtil.notNull(type)%>"/>
							<input type="hidden" name="tradeType" value="<%=StringUtil.notNull(tradeType)%>"/>
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
						<form name="pageNoForm" action="admin/AccountServlet.do?method=admin_money_detail"
							method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
								<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
							<input type="hidden" name="type" value="<%=StringUtil.notNull(type)%>"/>
							<input type="hidden" name="tradeType" value="<%=StringUtil.notNull(tradeType)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页 | 上一页  |<%}else{%>
					<a
						href="admin/AccountServlet.do?method=admin_money_detail&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&type=<%=StringUtil.notNull(type)%>
						&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&userId=<%=StringUtil.notNull(userId)%>&tradeType=<%=StringUtil.notNull(tradeType)%>&tag=1">首页</a> | <a
						href="admin/AccountServlet.do?method=admin_money_detail&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&type=<%=StringUtil.notNull(type)%>
						&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&userId=<%=StringUtil.notNull(userId)%>&tradeType=<%=StringUtil.notNull(tradeType)%>&tag=1">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/AccountServlet.do?method=admin_money_detail&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&userId=<%=StringUtil.notNull(userId)%>&type=<%=StringUtil.notNull(type)%>
						&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&tradeType=<%=StringUtil.notNull(tradeType)%>&tag=1">
                    下一页</a> | <a
						href="admin/AccountServlet.do?method=admin_money_detail&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&type=<%=StringUtil.notNull(type)%>
						&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&userId=<%=StringUtil.notNull(userId)%>&tradeType=<%=StringUtil.notNull(tradeType)%>&tag=1">尾页</a>
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