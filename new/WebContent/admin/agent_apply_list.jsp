<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.AgentApply" %>
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
				String state = StringUtil.notNull((String)request.getAttribute("state"));
String userId = StringUtil.notNull((String)request.getAttribute("userId"));
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][13].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>代理商申请</title>
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
		
		$("#no").click(function() {

			if (!confirm("确认拒绝该服务中心的申请？")) {
				window.event.returnValue = false;
			} else {
				window.event.returnValue = true;
			}

		});
		
		$("#passwordinit").click(function() {

			if (!confirm("确认要重置密码？")) {
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
				$('#myform').attr("action", "admin/center_password_init.action");
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

		$("#search")
				.click(
						function() {
							var str = "";
							
							if (str == "") {
								$('#searchForm').submit();
							} else {
								
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
						class="crumb-step">&gt;</span><span class="crumb-name">代理商申请</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/AgentApplyServlet.do?method=admin_list" id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
								<th width="80">当前状态:</th>
								<td><select class="common-text" id="state"
									name="state">
										<option value="">--全部--</option>
										<option value="1" <%if(state.equals("1")){ %>selected<%} %>>审核中</option>
										<option value="2" <%if(state.equals("2")){ %>selected<%} %>>审核通过</option>
											<option value="0" <%if(state.equals("0")){ %>selected<%} %>>已关闭</option>
								</select></td>
								<th width="80">用户编号:</th>
								<td><input class="common-text"  name="userId" id="userId" type="text"  value='<%=StringUtil.notNull(userId)%>'/></td>
									
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
			<form  id="myform" method="post">
			 <div class="result-title">
                    <div class="result-list">
                    <!--  <button id="passwordinit" class="btn"><i class="icon-font">&#xe035;</i>初始化密码</button>（备注：初始化密码均为：12345678。） -->   
                     
                    </div>
                </div>
                 <div class="result-title">
                    <div class="result-list">
                        <a href="admin/CenterServlet.do?method=admin_add"><i class="icon-font">&#xe026;</i>新增服务店</a>
                    </div>
                </div>
				<div class="result-content">
					<table class="result-tab" width="97%">
						<tr>
						<th class="tc" width="5%"><input class="allChoose" id="ckAll" name="ckAll" type="checkbox"></th>
							<th>序号</th><th>会员信息</th><th>证件类型</th><th>证件号码</th><th>经营区域</th><th>经营地址</th><th>联系电话</th><th>申请时间</th><th>审批人</th><th>审批时间</th><th>当前状态</th><th>操作</th>
						</tr>
						 <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							System.out.println(coll.size());
							if(coll!=null){
									Iterator it = coll.iterator();
									int t=0;
									while(it.hasNext()){
									AgentApply apply = (AgentApply)it.next();
									t++;
							%>
 <tr>
  <td class="tc"><input name="ids" type="checkbox" value="<%=apply.getId()%>"></td>
 <td><%=t %>
  <td><%=StringUtil.notNull(apply.getUserName()) %>(<%=StringUtil.notNull(apply.getUserId()) %>)</td>
  <td><%=StringUtil.notNull(apply.getDocumentType()) %></td>
 <td><%=StringUtil.notNull(apply.getNumId()) %></td>
 <td><%=StringUtil.notNull(apply.getProvince()) %><%=StringUtil.notNull(apply.getCity()) %><%=StringUtil.notNull(apply.getArea()) %></td>
<td><%=StringUtil.notNull(apply.getAddress()) %></td>
 <td><%=StringUtil.notNull(apply.getTel()) %></td>
 <td><%=StringUtil.parseToString(apply.getEntryTime(),DateUtil.yyyyMMddHHmmss) %></td>
  <td><%=StringUtil.notNull(apply.getReviewer()) %></td>
  <td><%=StringUtil.parseToString(apply.getReviewTime(),DateUtil.yyyyMMddHHmmss) %></td>
 
  <td><%if(apply.getState()==1){ %>审批中<%}else if(apply.getState()==2){ %>审批通过<%}else{ %>已关闭<%} %>
                                </td>
 <td><a  href="admin/AgentApplyServlet.do?method=admin_detail&id=<%=apply.getId() %>">详情</a>
<%if(rankstr[1][14].equals("1")&&apply.getState()==1){%>|<a  href="admin/AgentApplyServlet.do?method=admin_review_yes&id=<%=apply.getId() %>" onclick="javascript:if(!confirm('确认审批通过此代理商？')) return false;">审批通过</a>
|<a  href="admin/AgentApplyServlet.do?method=admin_review_no&id=<%=apply.getId() %>" onclick="javascript:if(!confirm('确认审核不通过？')) return false;">审批不通过</a><%} %></td>
  
 </tr>
	<%}}
						}else{ %><tr>
							<td colspan="10">暂无记录！</td>
						 </tr>
						 <%} %>
					</table>


				</div>
				</form>
			  <%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/AgentApplyServlet.do?method=admin_list" method="post">
							<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
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
			<form name="pageNoForm"  action="admin/AgentApplyServlet.do?method=admin_list" method="post">
			<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
							<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/AgentApplyServlet.do?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1
						&userId=<%=StringUtil.notNull(userId)%>&state=<%=StringUtil.notNull(state)%>">首页</a> | <a
						href="admin/AgentApplyServlet.do?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/AgentApplyServlet.do?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&userId=<%=StringUtil.notNull(userId)%>&state=<%=StringUtil.notNull(state)%>">
                    下一页</a> | <a
						href="admin/AgentApplyServlet.do?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>
						&userId=<%=StringUtil.notNull(userId)%>&state=<%=StringUtil.notNull(state)%>">尾页</a>
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
