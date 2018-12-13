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
String rankJoin = StringUtil.notNull((String) request.getAttribute("rankJoin"));
String tag = (String) request.getAttribute("tag");

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
						class="crumb-step">&gt;</span><span class="crumb-name">等级业绩查询</span>
					
				</div>
			</div>
				<div class="search-wrap">
				<div class="search-content">
					<form action="admin/ReportServlet.do?method=report_rankJoin_performance" id="searchForm" method="post">
						<table class="search-tab">
							<tr>
								<td>&nbsp;&nbsp;&nbsp;会员等级：<select id="rankJoin" name="rankJoin"
										class="common-text required">
											<option value="">--全部--</option>
											<option value="1" <%if(rankJoin.equals("1")){ %>selected<%} %>>银卡会员</option>
											<option value="2" <%if(rankJoin.equals("2")){ %>selected<%} %>>金卡会员</option>
											<option value="3" <%if(rankJoin.equals("3")){ %>selected<%} %>>白金卡会员</option>
											<option value="4" <%if(rankJoin.equals("4")){ %>selected<%} %>>VIP会员</option>
											<option value="5" <%if(rankJoin.equals("5")){ %>selected<%} %>>至尊会员</option>
									</select>&nbsp;业绩要求：<input type="text" id="tag" name="tag" value="<%=StringUtil.notNull(tag) %>" placeholder="该业绩为大于等于，如果业绩为零的，请不要输入业绩。">
									&nbsp;起止日期:
								<input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								&nbsp;<input class="btn btn-primary" id="search"
									name="search" value="查询" type="submit">
									&nbsp;<input class="btn btn-info" id="exportExcel"
									name="exportExcel" value="导出EXCEL" type="button">&nbsp;<span
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
							<th>序号</th><th>日期</th><th>用户信息</th><th>当前等级</th><th>区域新增金额</th><th>区域新增业绩</th><th>A区金额</th><th>A区业绩</th><th>B区金额</th><th>B区业绩</th><th>服务金额</th><th>服务业绩</th>
						</tr>
						
						  <%
							
							 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									WSettle w = (WSettle)it.next();
									t++;
							%>
 <tr>
  <td><%=t%></td>
 <td><%=StringUtil.parseToString(w.getStartTime(), DateUtil.yyyyMMdd)%>至<%=StringUtil.parseToString(w.getEndTime(), DateUtil.yyyyMMdd)%></td>
 <td><%=StringUtil.notNull(w.getUserName())%>（<%=StringUtil.notNull(w.getUserId())%>）</td>
 <td><%if(w.getRankJoin()==1){ %>银卡会员
 <%}else if(w.getRankJoin()==2){ %>金卡会员
 <%}else if(w.getRankJoin()==3){ %>白金会员
 <%}else if(w.getRankJoin()==4){ %>VIP会员
 <%}else if(w.getRankJoin()==5){ %>至尊会员
 <%} %></td>
 <td><%=StringUtil.decimalFormat(w.getNewPrice())%></td>
  <td><%=StringUtil.decimalFormat(w.getNewPerformance())%></td>
   <td><%=StringUtil.decimalFormat(w.getAtpv())%></td>
   <td><%=StringUtil.decimalFormat(w.getApv())%></td>
        <td><%=StringUtil.decimalFormat(w.getBtpv())%></td>
     <td><%=StringUtil.decimalFormat(w.getBpv())%></td>
            <td><%=StringUtil.decimalFormat(w.getRtpv())%></td>
       <td><%=StringUtil.decimalFormat(w.getRpv())%></td>
 </tr>	<%}}} %>
					</table>
					
					 <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/ReportServlet.do?method=report_rankJoin_performance" method="post">
							<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="tag" value="<%=StringUtil.notNull(tag)%>"/>
												<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
												<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
							<input type="hidden" name="rankJoin" value="<%=StringUtil.notNull(rankJoin)%>"/>
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
			<form name="pageNoForm"  action="admin/ReportServlet.do?method=report_rankJoin_performance" method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
												<input type="hidden" name="tag" value="<%=StringUtil.notNull(tag)%>"/>
												<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
												<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					
							<input type="hidden" name="rankJoin" value="<%=StringUtil.notNull(rankJoin)%>"/>
							
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/ReportServlet.do?method=report_rankJoin_performance&tag=<%=tag%>&rankJoin=<%=rankJoin%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1
						&rankJoin=<%=StringUtil.notNull(rankJoin)%>">首页</a> | <a
						href="admin/ReportServlet.do?method=report_rankJoin_performance&tag=<%=tag%>&rankJoin=<%=rankJoin%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>
						&rankJoin=<%=StringUtil.notNull(rankJoin)%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/ReportServlet.do?method=report_rankJoin_performance&tag=<%=tag%>&rankJoin=<%=rankJoin%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&rankJoin=<%=StringUtil.notNull(rankJoin)%>">
                    下一页</a> | <a
						href="admin/ReportServlet.do?method=report_rankJoin_performance&tag=<%=tag%>&rankJoin=<%=rankJoin%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>
						&rankJoin=<%=StringUtil.notNull(rankJoin)%>">尾页</a>
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
	       		$("#searchForm").attr("action", "admin/ReportServlet.do?method=exportExcel_rankJoin_performance");
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