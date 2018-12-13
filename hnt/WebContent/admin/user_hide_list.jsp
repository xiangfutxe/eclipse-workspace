<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%@ page import="com.ssm.utils.Constants"%>

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
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][5].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>隐藏会员管理</title>
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
			return false;
		}
	}
	
	function passowrdcfm() {
		if (!confirm("确认重置登录密码？")) {
		alert( $subs.filter(":checked").length);
			return false;
		}
	}
	
	function passowrd2cfm() {
		if (!confirm("确认重置二级密码？")) {
			return false;
		}
	}
	

	$(function() {

		$("#batchDel").click(function() {

			if (!confirm("确认要删除？")) {
				return false;
			} else {
				$('#delform').attr("action", "admin/apply_deleteAll.action");
				$('#delform').submit();
			}

		});
		
		 $("#exportExcel").click(function(){
	  	  if (!confirm("确认提交导出Excel？")) {
				return false;
			}
			else{
	       		$("#searchForm").attr("action", "admin/UserServlet?method=exportExcel");
				$("#searchForm").submit();
	       		 return true;
			}
		 });
		
		$("#passwordinit").click(function() {

			if (!confirm("确认要重置一级密码？")) {
				return false;
			} else {
			var i = 0;
				$("input[name='ids']").each(function(){
				if(this.checked==true) i++;
				}); 
				if(i==0){
				alert("未选择需要重置密码的用户！");
				
					return false;
				}else{
				$('#myform').attr("action", "admin/UserServlet?method=admin_psw1_init");
				$('#myform').submit();
					return true;
				}
				
			}

		});
		
		$("#password2init").click(function() {

			if (!confirm("确认要重置二级密码？")) {
				return false;
			} else {
			var i = 0;
				$("input[name='ids']").each(function(){
				if(this.checked==true) i++;
				}); 
				if(i==0){
				alert("未选择需要重置密码的用户！");
				
					return false;
				}else{
				$('#myform').attr("action", "admin/UserServlet?method=admin_psw2_init");
				$('#myform').submit();
					return true;
				}
			}
		});
		
		$("#lock").click(function() {

			if (!confirm("确认要锁定该用户？")) {
				return false;
			} else {
			var i = 0;
				$("input[name='ids']").each(function(){
				if(this.checked==true) i++;
				}); 
				if(i==0){
				alert("未选择需要锁定的用户！");
				
					return false;
				}else{
				$('#myform').attr("action", "admin/UserServlet?method=admin_user_lock");
				$('#myform').submit();
					return true;
				}
			}
		});
		
		$("#unhide").click(function() {
			if (!confirm("确认要显示会员？")) {
				return false;
			} else {
			var i = 0;
				$("input[name='ids']").each(function(){
				if(this.checked==true) i++;
				}); 
				if(i==0){
				alert("未选择需要显示会员！");
				
					return false;
				}else{
				$('#myform').attr("action", "admin/UserServlet?method=admin_user_hide_reset");
				$('#myform').submit();
					return true;
				}
			}
		});
		
		 $("#exportExcel").click(function(){
	  	  if (!confirm("确认提交导出Excel？")) {
				return false;
			}
			else{
	       		$("#searchForm").attr("action", "admin/UserServlet?method=exportExcel_user_hide_list");
				$("#searchForm").submit();
	       		 return true;
			}
		 });
		
		$("#search")
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
												return true;
											} else {
												alert(str);
												return false;
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
						class="crumb-step">&gt;</span><span class="crumb-name">会员管理</span>
						<span
						class="crumb-step">&gt;</span><span class="crumb-name">隐藏会员列表</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/UserServlet?method=admin_user_hide_list" id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
								<td width="800px">会员信息：<input class="common-text" name="userId" id="userId" size="15"
									type="text" value='<%=StringUtil.notNull(userId) %>'>&nbsp;
								<input class="btn btn-primary btn2" id="search"
									name="search" value="查询" type="button">&nbsp;
									<%if(rankstr[1][4].equals("1")||admin.getState().equals("1")){ %>
									<input class="btn btn-info" id="exportExcel"
									name="exportExcel" value="导出Excel" type="button">
									<%} %>
								</td>
								</tr>
						</table>
					</form>
				</div>
			</div>
				
			<div class="result-wrap">
			<form  id="myform" method="post">
				 <div class="result-title">
                    <div class="result-list">
                         <button id="unhide" class="btn"><i class="icon-font">&#xe018;</i>显示会员</button>
                   
                    </div>
                </div>
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
						<th class="tc" width="5%"><input class="allChoose" id="ckAll" name="ckAll" type="checkbox"></th>
							<th>用户编号</th><th>用户名</th><th>会员等级</th><th>会员奖衔</th><th>管理头衔</th><th>销售商</th><th>销售区域</th><th>服务商</th><th>报单人</th><th>空单名额</th><th>注册时间</th><th>奖金状态</th><th>状态</th><th>操作</th>
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
  <td class="tc"><input name="ids" type="checkbox" value="<%=user.getId()%>"></td>
 <td><a href='admin/UserServlet?method=adminDetail&id=<%=user.getId()%>'><%=StringUtil.notNull(user.getUserId())%></a></td>
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
 <td> <%	if(user.getRank()==1) { out.println(Constants.RANK_1);
  				 }else if(user.getRank()==2){ out.println(Constants.RANK_2);
				}else if(user.getRank()==3){ out.println(Constants.RANK_3);
				}else if(user.getRank()==4){ out.println(Constants.RANK_4);
				}else if(user.getRank()==5){ out.println(Constants.RANK_5);
				}else{ %>-<%} %>
				</td>
 <td><%=StringUtil.notNull(user.getUserByBelongId())%></td>
<td><%if(user.getNodeTag()==1){%>A区<%}else if(user.getNodeTag()==2) {%>B区<%}else if(user.getNodeTag()==3){%>C区<% }else{%>未指定区域<%}%></td>

<td><%=StringUtil.notNull(user.getUserByRefereeId())%></td>
<td><%=StringUtil.notNull(user.getUserByDeclarationId())%></td>
<td><%=user.getEmptyNum()%></td>
<td><%=StringUtil.parseToString(user.getEntryTime(), DateUtil.yyyyMMddHHmmss)%></td>
<td><%if(user.getPayTag()==1){%>正常<%}else {%>锁定<%}%></td>
 <td><%if(user.getState()==1){%>待激活<%}else if(user.getState()==2){%>在线<%}else {%>下线<%}%></td>
 
 <td><a href='admin/UserServlet?method=admin_user_hide_detail&id=<%=user.getUserId()%>'>详情</a>
 </td>
  
 </tr>
	 	<%}}
						}else{ %><tr><td colspan="14">暂无记录！</td></tr><%} %>
					</table>


				</div>
				</form>
				 <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/UserServlet?method=admin_user_hide_list" method="post">
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
			<form name="pageNoForm"  action="admin/UserServlet?method=admin_user_hide_list" method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					
							<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
							
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/UserServlet?method=admin_user_hide_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1
						&userId=<%=StringUtil.notNull(userId)%>">首页</a> | <a
						href="admin/UserServlet?method=admin_user_hide_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>
						&userId=<%=StringUtil.notNull(userId)%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/UserServlet?method=admin_user_hide_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&userId=<%=StringUtil.notNull(userId)%>">
                    下一页</a> | <a
						href="admin/UserServlet?method=admin_user_hide_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>
						&userId=<%=StringUtil.notNull(userId)%>">尾页</a>
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
