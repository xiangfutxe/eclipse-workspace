<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.WReward" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.ArithUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String weekTag = (String) request.getAttribute("weekTag");
String userId = (String)request.getAttribute("userId");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[7][2].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工资明细</title>
    
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
<script  type="text/javascript" >
$(function() {
 $("#exportExcel").click(function(){
	  	  if (!confirm("确认提交导出Excel？")) {
				return false;
			}
			else{
	       		$("#searchForm").attr("action", "admin/RewardServlet.do?method=exportExcel");
				$("#searchForm").submit();
	       		 return true;
			}
		 });
		 
	$("#ckAll").click(function() {
				if(this.checked){ 
					$("input[name='ids']").each(function(){this.checked=true;}); 
				}else{ 
					$("input[name='ids']").each(function(){this.checked=false;}); 
				} 
			});
	
			$("input[name='ids']").click(function() {
		    var $ids = $("input[name='ids']");
		    $("#ckAll").prop("checked" , $ids.length == $subs.filter(":checked").length ? true :false);
		  });
		  
		  	$("#pay_off").click(function() {

			if (!confirm("确认已经发放所选会员第工资？")) {
				return false;
			} else {
			var i = 0;
				$("input[name='ids']").each(function(){
				if(this.checked==true) i++;
				}); 
				if(i==0){
				alert("未选择已发放工资的用户！");
					return false;
				}else{
				$('#myform').attr("action", "admin/RewardServlet.do?method=admin_reward_week_payoff");
				$('#myform').submit();
					return true;
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
						class="crumb-step">&gt;</span><span class="crumb-name">奖金管理</span><span
						class="crumb-step">&gt;</span><span class="crumb-name">工资明细</span>
					
				</div>
			</div>
				<div class="search-wrap">
				<div class="search-content">
					<form action="admin/RewardServlet.do?method=admin_reward_week_summary" id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
								<th width="80">按期查询:</th>
								<td><select class="common-text" id="weekTag"
									name="weekTag">
									<option value="">全部</option>
									 <%
								 List<Integer> slist = (List<Integer>)request.getAttribute("slist");
						if(slist.size()>0){
							
								for(int i=0;i<slist.size();i++){
								int week = slist.get(i);
							%>
										<option value="<%=week%>" <%if(weekTag.equals(String.valueOf(week))){ %>selected<%} %>>第<%=week%>周</option>
										<%}}%>
								</select>&nbsp;会员编号：<input type="text" id="userId" name="userId" />&nbsp;<input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="submit">&nbsp;<input class="btn btn-info" id="exportExcel"
									name="exportExcel" value="导出Excel" type="button"><span
									id="timeTag" style="color:red"></span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">
				<form  id="myform" method="post">
				<input type="hidden" name="weekTag" value="<%=StringUtil.notNull(weekTag) %>"	/>
						 <div class="result-title">
                    <div class="result-list">
                        <button id="pay_off" class="btn"><i class="icon-font">&#xe035;</i>批量发放工资</button>
                       
                    </div>
                </div>
                
				<div class="result-content">
					<table class="result-tab" width="98%">
						<tr>
				<th class="tc" width="5%"><input class="allChoose" id="ckAll" name="ckAll" type="checkbox"></th>
						
							<th>起止时间</th><th>会员信息</th><th>市场推广费</th><th>销售补贴</th><th>小计</th><th>综合管理费</th><th>实发金额</th><th>操作</th>
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
 
   <td class="tc"> <%if(w.getState()==2){ %><input name="ids" type="checkbox" value="<%=w.getId()%>"><%} %></td>
 <td><%=StringUtil.parseToString(w.getStartTime(), DateUtil.yyyyMMddHHmmss)%><br>至<br><%=StringUtil.parseToString(w.getEndTime(), DateUtil.yyyyMMddHHmmss)%></td>
  <td><%=StringUtil.notNull(w.getUserName()) %>(<%=StringUtil.notNull(w.getUserId()) %>)</td>
  <td><%=StringUtil.decimalFormat(w.getAmount1()) %></td>
    <td><%=StringUtil.decimalFormat(w.getAmount2()) %></td>
        <td><%=StringUtil.decimalFormat(w.getTotalAmount()) %></td>
            <td><%=StringUtil.decimalFormat(w.getTax()) %></td>
    <td><%=StringUtil.decimalFormat(w.getAmount()) %></td>
    <td><%if(w.getState()==1){ %>结算中<%}else if(w.getState()==2){ %>待发工资<%}else if(w.getState()==3){ %>已发工资
  <%}else{ %>-<%} %></td>
 
 <%}}
						}else{ %><tr><td colspan="8">暂无记录！</td></tr><%} %>
					</table>
</form>

				</div>
				 <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/RewardServlet.do?method=admin_reward_week_summary" method="post">
							<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="weekTag" value="<%=StringUtil.notNull(weekTag)%>"/>
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
			<form name="pageNoForm"  action="admin/RewardServlet.do?method=admin_reward_week_summary" method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
<input type="hidden" name="weekTag" value="<%=StringUtil.notNull(weekTag)%>"/>
							<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
												第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/RewardServlet.do?method=admin_reward_week_summary&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&weekTag=<%=StringUtil.notNull(weekTag)%>&userId=<%=StringUtil.notNull(userId)%>">首页</a> | <a
						href="admin/RewardServlet.do?method=admin_reward_week_summary&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&weekTag=<%=StringUtil.notNull(weekTag)%>&userId=<%=StringUtil.notNull(userId)%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/RewardServlet.do?method=admin_reward_week_summary&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>&weekTag=<%=StringUtil.notNull(weekTag)%>&userId=<%=StringUtil.notNull(userId)%>">
                    下一页</a> | <a
						href="admin/RewardServlet.do?method=admin_reward_week_summary&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&weekTag=<%=StringUtil.notNull(weekTag)%>&userId=<%=StringUtil.notNull(userId)%>">尾页</a>
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
