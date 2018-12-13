<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.WReward" %>
<%@page import="com.ssm.pojo.WRewardDetail" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
Integer weekTag = (Integer) request.getAttribute("weekTag");
Integer type = (Integer) request.getAttribute("type");
String userId = (String) request.getAttribute("userId");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[7][1].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>明细清单</title>
    
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
						class="crumb-step">&gt;</span><span class="crumb-name">明细清单</span>
					
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-content">
					<table class="result-tab" width="98%">
						<tr>
							<th>起止时间</th><th>用户信息</th><th>奖金类型</th><th>奖金来源</th><th>计奖金额</th><th>备注说明</th>
						</tr>
						
						  <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									while(it.hasNext()){
									WRewardDetail w = (WRewardDetail)it.next();
							%>
 <tr>
 
 <td><%=StringUtil.parseToString(w.getStartTime(), DateUtil.yyyyMMddHHmmss)%><br>至<br><%=StringUtil.parseToString(w.getEndTime(), DateUtil.yyyyMMddHHmmss)%></td>
 <td><%=StringUtil.notNull(w.getUserName())%>（<%=StringUtil.notNull(w.getUserId())%>）</td>
 <td><%if(w.getType()==1){ %>促销补贴<%}else if(w.getType()==2){ %>销售提成<%}else if(w.getType()==3){ %>培育补贴<%}else if(w.getType()==4){ %>管理提成<%}else if(w.getType()==5){ %>购物折扣<%}else if(w.getType()==6){ %>差旅补贴<%}else if(w.getType()==7){ %>车房旅游基金<%}else if(w.getType()==7){ %>董事分红<%}else if(w.getType()==8){ %>物流补助<%}else if(w.getType()==10){ %>二次进货管理费<%}else if(w.getType()==11){ %>经销商物流管理费<%}else if(w.getType()==12){ %>区域开店管理费<%}else if(w.getType()==13){ %>店铺零售提成<%} %></td>
 <td><%=StringUtil.notNull(w.getUserByUserName())%>(<%=StringUtil.notNull(w.getUserByUserId())%>)</td>
 <td><%=StringUtil.decimalFormat(w.getAmount())%></td>
  <td><%=StringUtil.notNull(w.getRemark())%></td>
 </tr>	<%}}
						}else{ %><tr><td colspan="6">暂无记录！</td></tr><%} %>
					</table>


				</div>
				 <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/RewardServlet.do?method=admin_reward_week_detail" method="post">
							<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="weekTag" value="<%=weekTag%>"/>
							<input type="hidden" name="type" value="<%="type"%>"/>
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
			<form name="pageNoForm"  action="admin/RewardServlet.do?method=admin_reward_week_detail" method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
						<input type="hidden" name="weekTag" value="<%=weekTag%>"/>
<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
<input type="hidden" name="type" value="<%="type"%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/RewardServlet.do?method=admin_reward_week_detail&userId=<%=StringUtil.notNull(userId)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&weekTag=<%=weekTag%>&type=<%=type%>">首页</a> | <a
						href="admin/RewardServlet.do?method=admin_reward_week_detail&userId=<%=StringUtil.notNull(userId)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&weekTag=<%=weekTag%>&type=<%=type%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/RewardServlet.do?method=admin_reward_week_detail&userId=<%=StringUtil.notNull(userId)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>&weekTag=<%=weekTag%>&type=<%=type%>">
                    下一页</a> | <a
						href="admin/RewardServlet.do?method=admin_reward_week_detail&userId=<%=StringUtil.notNull(userId)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&weekTag=<%=weekTag%>&type=<%=type%>">尾页</a>
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
		 if(!confirm("确认提交发放工资？")){
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
	       		$("#searchForm").attr("action", "admin/RewardServlet.do?method=exportExcel");
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
