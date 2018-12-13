<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.WReward" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
Integer weekTag1 = (Integer) request.getAttribute("weekTag1");
Integer weekTag2 = (Integer) request.getAttribute("weekTag2");
String userId = (String) request.getAttribute("userId");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[7][12].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>奖金明细汇总</title>
    
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
						class="crumb-step">&gt;</span><span class="crumb-name">奖金管理</span><span
						class="crumb-step">&gt;</span><span class="crumb-name">明细汇总</span>
					
				</div>
			</div>
				<div class="search-wrap">
				<div class="search-content">
					<form action="admin/RewardServlet?method=admin_reward_week_summary_all" id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
								<th width="80">开始周:</th>
								
								<td><select class="common-text" id="weekTag1"
									name="weekTag1">
									 <%
								 List<Integer> slist = (List<Integer>)request.getAttribute("slist");
						
								for(int i=0;i<slist.size();i++){
								int week = slist.get(i);
							%>
										<option value="<%=week%>" <%if(weekTag1-week==0){ %>selected<%} %>>第<%=week%>期</option>
										<%}%>
								</select>&nbsp;</td>
								<td >结束周:</td>
								<td><select class="common-text" id="weekTag2"
									name="weekTag2">
									 <%
								
							
								for(int i=0;i<slist.size();i++){
								int week = slist.get(i);
							%>
										<option value="<%=week%>" <%if(weekTag2-week==0){ %>selected<%} %>>第<%=week%>期</option>
										<%}%>
								</select>&nbsp;
								会员编号：<input class="common-text" type="text" name="userId" id="userId" value="<%=StringUtil.notNull(userId)%>"/></td>
								<td><input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="submit">&nbsp;<input class="btn btn-green" id="exportExcel"
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
					<table class="result-tab" width="98%">
						<tr>
							<th>起止时间</th><th>用户信息</th><th>创业奖</th><th>推荐奖</th><th>见点奖</th><th>拓展奖</th><th>培育奖</th><th>领导奖</th><th>物流补助</th><th>消费奖</th><th>奖金小计</th><th>复消</th><th>综合费</th><th>实发金额</th>
						</tr>
						
						  <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									while(it.hasNext()){
									WReward w = (WReward)it.next();
							%>
 <tr>
 
 <td>第<%=weekTag1%>周<br>至<br>第<%=weekTag2%>周</td>
 <td><%=StringUtil.notNull(w.getUserName())%>（<%=StringUtil.notNull(w.getUserId())%>）</td>
 <td><%=StringUtil.decimalFormat(w.getAmount_1())%></td>
        <td><%=StringUtil.decimalFormat(w.getAmount_7())%></td>
 
        <td><%=StringUtil.decimalFormat(w.getAmount_8())%></td>
 
   <td><%=StringUtil.decimalFormat(w.getAmount_2())%></td>
    <td><%=StringUtil.decimalFormat(w.getAmount_3())%></td>
     <td><%=StringUtil.decimalFormat(w.getAmount_4())%></td>
      <td><%=StringUtil.decimalFormat(w.getAmount_5())%></td>
       <td><%=StringUtil.decimalFormat(w.getAmount_6())%></td>
      <td><%=StringUtil.decimalFormat(w.getAmount_9())%></td>
		<td><%=StringUtil.decimalFormat(w.getAmount_10())%></td>
 <td><%=StringUtil.decimalFormat(w.getAmount_11())%></td>
 <td><%=StringUtil.decimalFormat(w.getAmount_12())%></td>
 </tr>	<%}}
						}else{ %><tr><td colspan="13">暂无记录！</td></tr><%} %>
					</table>


				</div>
				 <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/RewardServlet?method=admin_reward_week_summary_all" method="post">
							<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="weekTag1" value="<%=weekTag1%>"/>
	<input type="hidden" name="weekTag2" value="<%=weekTag2%>"/>
							
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
			<form name="pageNoForm"  action="admin/RewardServlet?method=admin_reward_week_summary_all" method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
						<input type="hidden" name="weekTag1" value="<%=weekTag1%>"/>
							<input type="hidden" name="weekTag2" value="<%=weekTag2%>"/>
						
<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/RewardServlet?method=admin_reward_week_summary_all&userId=<%=StringUtil.notNull(userId)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&weekTag1=<%=weekTag1%>&weekTag2=<%=weekTag2%>">首页</a> | <a
						href="admin/RewardServlet?method=admin_reward_week_summary_all&userId=<%=StringUtil.notNull(userId)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&weekTag1=<%=weekTag1%>&weekTag2=<%=weekTag2%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/RewardServlet?method=admin_reward_week_summary_all&userId=<%=StringUtil.notNull(userId)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>&weekTag1=<%=weekTag1%>&weekTag2=<%=weekTag2%>">
                    下一页</a> | <a
						href="admin/RewardServlet?method=admin_reward_week_summary_all&userId=<%=StringUtil.notNull(userId)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&weekTag1=<%=weekTag1%>&weekTag2=<%=weekTag2%>">尾页</a>
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
<script type="text/javascript">
	$(function(){
		$("#payoff_all").click(function(){
		 if(!confirm("工资发放条件仅按周期，如果需要发放某一期奖金,请先点击查询后再发放，确认提交发放工资？")){
		 	return false;
		 }else{
		 	$("myform").submit();
		 	return true;
		 }
		});
		
		$("#exportExcel").click(function(){
	  	  if (!confirm("确认提交导出Excel？")) {
				return false;
			}
			else{
	       		$("#searchForm").attr("action", "admin/RewardServlet?method=exportExcel");
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
