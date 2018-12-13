<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String userId = (String)request.getAttribute("userId");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		 String[][] rankstr1 = StringUtil.getRankStr(admin.getRank());
		if(rankstr1[2][5].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>团队会员奖衔</title>
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
		if (!confirm("确认要进行退单操作？")) {
			window.event.returnValue = false;
		}
	}
	
	function passowrdcfm() {
		if (!confirm("确认重置登录密码？")) {
		alert( $subs.filter(":checked").length);
			window.event.returnValue = false;
		}
	}
	
	function passowrd2cfm() {
		if (!confirm("确认重置二级密码？")) {
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
		
		$("#passwordinit").click(function() {

			if (!confirm("确认要重置一级密码？")) {
				window.event.returnValue = false;
			} else {
			var i = 0;
				$("input[name='ids']").each(function(){
				if(this.checked==true) i++;
				}); 
				if(i==0){
				alert("未选择需要重置密码的用户！");
				
					window.event.returnValue = false;
				}else{
				$('#myform').attr("action", "admin/password1_init.action");
				$('#myform').submit();
					window.event.returnValue = true;
				}
				
			}

		});
		
		$("#password2init").click(function() {

			if (!confirm("确认要重置二级密码？")) {
				window.event.returnValue = false;
			} else {
			var i = 0;
				$("input[name='ids']").each(function(){
				if(this.checked==true) i++;
				}); 
				if(i==0){
				alert("未选择需要重置密码的用户！");
				
					window.event.returnValue = false;
				}else{
				$('#myform').attr("action", "admin/password2_init.action");
				$('#myform').submit();
					window.event.returnValue = true;
				}
				
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
		  
		  
	});
</script>
</head>

<body>
	<div class="container clearfix">
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a><span
						class="crumb-step">&gt;</span><span class="crumb-name">网络管理</span>
						<span
						class="crumb-step">&gt;</span><span class="crumb-name">团队会员奖衔</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/ReportServlet?method=user_team_rankmanage" id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
									<th width="100px">&nbsp;会员编号：</th><td>
									<input class="common-text"  name="userId" id="userId" type="text" size="15" value='<%=StringUtil.notNull(userId)%>'>
									&nbsp;<input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="submit"><span
									id="timeTag" style="color:red"></span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
				
			<div class="result-wrap">
			<form  id="myform" method="post">
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
						<th class="tc" width="5%"><input class="allChoose" id="ckAll" name="ckAll" type="checkbox"></th>
							<th>用户编号</th><th>用户名</th><th>会员等级</th><th>会员奖衔</th><th>销售商</th><th>销售区域</th><th>服务商</th><th>报单人</th><th>注册时间</th><th>奖金状态</th><th>状态</th>
						</tr>
						  <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									while(it.hasNext()){
									User user = (User)it.next();
							%>
 <tr>
  <td class="tc"><input name="ids" type="checkbox" value="<%=user.getId()%>">
 <td><%=StringUtil.notNull(user.getUserId())%></td>
 <td><%=StringUtil.notNull(user.getUserName())%></td>
 <td><%if(user.getRankJoin()==0){%>消费会员<%}else if(user.getRankJoin()==1){%>银卡会员<%}else if(user.getRankJoin()==2) {%>金卡会员<%}else if(user.getRankJoin()==3){%>白金会员
 <%}else if(user.getRankJoin()==4){%>VIP会员<%}else if(user.getRankJoin()==5){%>至尊会员<% }else{%>-<%}%></td>
 <td><%if(user.getRankManage()==0){ %>-<%} %>
                            <%if(user.getRankManage()==1){ %>一级经理<%} %>
											<%if(user.getRankManage()==2){ %>二级经理<%} %>
									<%if(user.getRankManage()==3){ %>三级经理<%} %>
									<%if(user.getRankManage()==4){ %>四级经理<%} %>
									<%if(user.getRankManage()==5){ %>五级经理<%} %>
<%if(user.getRankManage()==6){ %>一星钻石<%} %>
<%if(user.getRankManage()==7){ %>二星钻石<%} %>
<%if(user.getRankManage()==8){ %>三星钻石<%} %>
<%if(user.getRankManage()==9){ %>四星钻石<%} %>
<%if(user.getRankManage()==10){ %>五星钻石<%} %>
<%if(user.getRankManage()==11){ %>董事<%} %></td>
 <td><%=StringUtil.notNull(user.getUserByBelongId())%></td>
<td><%if(user.getNodeTag()==1){%>A区<%}else if(user.getNodeTag()==2) {%>B区<%}else if(user.getNodeTag()==3){%>C区<% }else{%>未指定区域<%}%></td>

<td><%=StringUtil.notNull(user.getUserByRefereeId())%></td>
<td><%=StringUtil.notNull(user.getUserByDeclarationId())%></td>
<td><%=StringUtil.parseToString(user.getEntryTime(), DateUtil.yyyyMMddHHmmss)%></td>
<td><%if(user.getPayTag()==1){%>正常<%}else {%>锁定<%}%></td>
 <td><%if(user.getState()==1){%>待激活<%}else if(user.getState()==2){%>在线<%}else {%>下线<%}%></td>
  
 </tr>
	 	<%}}
						}else{ %><tr><td colspan="16">暂无记录！</td></tr><%} %>
					</table>


				</div>
				</form>
				 <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/ReportServlet?method=user_team_rankmanage" method="post">
							<input type="hidden" name="pageNoStr" value="1"/>
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
			<form name="pageNoForm"  action="admin/ReportServlet?method=user_team_rankmanage" method="post">
					
							<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
							
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/ReportServlet?method=user_team_rankmanage&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1
						&userId=<%=StringUtil.notNull(userId)%>
						
						">首页</a> | <a
						href="admin/ReportServlet?method=user_team_rankmanage&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>
						&userId=<%=StringUtil.notNull(userId)%>
						
						">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/ReportServlet?method=user_team_rankmanage&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&userId=<%=StringUtil.notNull(userId)%>
						
						">
                    下一页</a> | <a
						href="admin/ReportServlet?method=user_team_rankmanage&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>
						&userId=<%=StringUtil.notNull(userId)%>
						
						">尾页</a>
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