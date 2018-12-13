<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.WSettle" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
Integer weekTag = (Integer) request.getAttribute("weekTag");
String userId = (String) request.getAttribute("userId");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[8][2].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>区域业绩查询</title>
    
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
  </head>
  
  <body>
 <div class="container clearfix">
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a><span
						class="crumb-step">&gt;</span><span class="crumb-name">报表统计</span><span
						class="crumb-step">&gt;</span><span class="crumb-name">区域业绩查询</span>
					
				</div>
			</div>
				<div class="search-wrap">
				<div class="search-content">
					<form action="admin/ReportServlet.do?method=admin_report_wsettle" id="searchForm" method="post">
						<table class="search-tab">
							<tr>
								
								
								<th width="80">按周查询:</th>
								
								<td><select class="common-text" id="weekTag"
									name="weekTag">
									 <%
								 List<Integer> slist = (List<Integer>)request.getAttribute("slist");
						if(slist.size()>0){
							
								for(int i=0;i<slist.size();i++){
								int week = slist.get(i);
							%>
										<option value="<%=week%>" <%if(weekTag==week){ %>selected<%} %>>第<%=week%>周</option>
										<%}}%>
								</select>&nbsp;会员编号：<input type="text" class="common-text" name="userId" value="<%=StringUtil.notNull(userId)%>"></td>
								<td><input class="btn btn-primary" id="search"
									name="search" value="查询" type="submit"><span
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
							<th>起止时间</th><th>用户信息</th><th>区域<br/>总业绩</th><th>期初业绩</th><th>新增业绩</th><th>A区<br/>新增业绩</th><th>A区<br/>盈余业绩</th><th>B区<br/>新增业绩</th><th>B区<br/>盈余业绩</th><th>销售<br/>新增业绩</th><th>销售<br/>期初业绩</th><th>销售<br/>总业绩</th><th>本期新增<br/>新增复消</th><th>期初<br/>总复消</th><th>累计<br/>总复消</th>
						</tr>
						
						  <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									while(it.hasNext()){
									WSettle w = (WSettle)it.next();
							%>
 <tr>
 
 <td><%=StringUtil.parseToString(w.getStartTime(), DateUtil.yyyyMMddHHmmss)%><br>至<br><%=StringUtil.parseToString(w.getEndTime(), DateUtil.yyyyMMddHHmmss)%></td>
 <td><%=StringUtil.notNull(w.getUserName())%><br/>（<%=StringUtil.notNull(w.getUserId())%>）</td>
 <td><%=StringUtil.decimalFormat1(w.getTotalPerformance()+w.getNewPerformance())%></td>
  <td><%=StringUtil.decimalFormat1(w.getTotalPerformance())%></td>
  <td><%=StringUtil.decimalFormat1(w.getNewPerformance())%></td>
   <td><%=StringUtil.decimalFormat1(w.getApv())%></td>
    <td><%=StringUtil.decimalFormat1(w.getAcpv())%></td>
     <td><%=StringUtil.decimalFormat1(w.getBpv())%></td>
      <td><%=StringUtil.decimalFormat1(w.getBcpv())%></td>
       <td><%=StringUtil.decimalFormat1(w.getRpv())%></td>
       <td><%=StringUtil.decimalFormat1(w.getRtpv())%></td>
        <td><%=StringUtil.decimalFormat1(w.getRtpv()+w.getRpv())%></td>
        <td><%=StringUtil.decimalFormat1(w.getTNPv())%></td>
       <td><%=StringUtil.decimalFormat1(w.getTPv())%></td>
        <td><%=StringUtil.decimalFormat1(w.getTPv()+w.getTNPv())%></td>
 </tr>	<%}}
						}else{ %><tr><td colspan="12">暂无记录！</td></tr><%} %>
					</table>


				</div>
				 <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/ReportServlet.do?method=admin_report_wsettle" method="post">
							<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="weekTag" value="<%=weekTag%>"/>
							<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
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
			<form name="pageNoForm"  action="admin/ReportServlet.do?method=admin_report_wsettle" method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
						<input type="hidden" name="weekTag" value="<%=weekTag%>"/>
					<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/ReportServlet.do?method=admin_report_wsettle&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&weekTag=<%=weekTag%>&userId=<%=StringUtil.notNull(userId)%>">首页</a> | <a
						href="admin/ReportServlet.do?method=admin_report_wsettle&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&weekTag=<%=weekTag%>&userId=<%=StringUtil.notNull(userId)%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/ReportServlet.do?method=admin_report_wsettle&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>&weekTag=<%=weekTag%>&userId=<%=StringUtil.notNull(userId)%>">
                    下一页</a> | <a
						href="admin/ReportServlet.do?method=admin_report_wsettle&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&weekTag=<%=weekTag%>&userId=<%=StringUtil.notNull(userId)%>">尾页</a>
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
