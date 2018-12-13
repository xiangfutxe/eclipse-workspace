<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	 String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[9][0].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>管理员列表</title>
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
<script language="JavaScript">
	function delcfm() {
		if (!confirm("确认要删除？")) {
			return false;
		}
	}

	$(function() {
		$("#batchDel").click(function() {
			if (!confirm("确认要批量删除？")) {
				return false;
			} else {
				$('#delform').submit();
				return true;
			}

		});

		$("#ckAll").click(function() {
			if (this.checked) {
				$("input[name='ids']").each(function() {
					this.checked = true;
				});
			} else {
				$("input[name='ids']").each(function() {
					this.checked = false;
				});
			}
		});

		$("input[name='ids']")
				.click(
						function() {
							var $ids = $("input[name='ids']");
							$("#ckAll")
									.prop(
											"checked",
											$ids.length == $subs
													.filter(":checked").length ? true
													: false);
						});

		$("#pageSize").bind("propertychange", function() {
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
						class="crumb-step">&gt;</span><span class="crumb-name">管理员管理</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/AdminServlet.do?method=list" method="post">
						<table class="search-tab">
							<tr>
								<th width="100">名称:</th>
								<% 
									String adminName = (String)request.getAttribute("adminName");
								 %>
								<td><input class="common-text" name="adminName"
									type="text" value='<%=StringUtil.notNull(adminName)%>'>
								</td>
								<td><input class="btn btn-primary btn2" name="sub"
									value="查询" type="submit">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">
				<form name="delform" id="delform" action="admin/AdminServlet.do?method=delAll"  method="post">
					<div class="result-title">
						<div class="result-list">
							<a href="admin/AdminServlet.do?method=add"><i class="icon-font">&#xe026;</i>新增管理员</a>
							<a id="batchDel"><i class="icon-font">&#xe019;</i>批量删除</a>
						</div>
					</div>
					<div class="result-content">
					
						<table class="result-tab" width="100%">
							<tr>
								<th class="tc" width="5%"><input class="allChoose"
									id="ckAll" name="ckAll" type="checkbox">
								</th>
								<th>名称</th>
								<th>注册时间</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
							<%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									while(it.hasNext()){
									Admin ad = (Admin)it.next();
							%>
								<tr>
									<td class="tc"><input name="ids" type="checkbox"
										value="<%=ad.getId()%>">
									</td>
									<td><%=StringUtil.notNull(ad.getAdminName())%>
									</td>
									<td><%=StringUtil.parseToString(ad.getEntryTime(), DateUtil.yyyyMMddHHmmss)%></td>
									<td><%if(ad.getState().equals("0")){%>普通管理员<%}else if(ad.getState().equals("1")){ %>超级管理员<%}else{%>-<%} %></td>
									<td>
									<a class="link-update"
										href="admin/AdminServlet.do?method=editRank&id=<%=ad.getId()%>">修改权限</a>&nbsp;|&nbsp;<a
										class="link-update" href="admin/AdminServlet.do?method=initPsw&id=<%=ad.getId()%>" onclick="if(confirm('确定要初始化该管理员的密码？初始化密码为12345678，请谨记！')==false)return false;">初始化密码</a>&nbsp;|&nbsp;
										<a
										class="link-del"
										href="admin/AdminServlet.do?method=del&id=<%=ad.getId()%>" onclick="if(confirm('确定删除该管理员信息?')==false)return false;">删除</a></td>
								</tr>
						<%}}
						}else{ %>
						 <tr>
						 <td colspan="7">暂无管理员，请点击<a href="admin/AdminServlet.do?method=add">添加管理员。</a></td>
						  </tr>
						 <%} %>
						</table>


					</div>
				</form>
			<%if(pager!=null){ %>
				 <div class="list-page"> 
                    <div class="list-page-left">
                   <form name="pageSizeForm"  action="admin/AdminServlet.do?method=list" method="post">
						<input type="hidden" name="adminName" value="<%=StringUtil.notNull(adminName)%>"/>
							<input type="hidden" name="pageNoStr" value="1"/>
					当前每页显示<select name="pageSizeStr" id="pageSizeStr" onchange="javascript:pageSizeForm.submit();">
						<option value="1"
							<%if(pager.getPageSize()==1) {%>selected<%}%>>1</option>
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
			<form name="pageNoForm"  action="admin/AdminServlet.do?method=list" method="post">
					<input type="hidden" name="adminName" value="<%=StringUtil.notNull(adminName)%>"/>
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页&nbsp; 共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp; &nbsp; &nbsp; 
					<%if(pager.getPageNo()==1) {%> 首页  | 上一页  |<%}else{%>
					<a
						href="admin/AdminServlet.do?method=list&adminName=<%=StringUtil.notNull(adminName)%>&pageSize=<%=pager.getPageSize()%>&pageNoStr=1">首页</a> | <a
						href="admin/AdminServlet.do?method=list&adminName=<%=StringUtil.notNull(adminName)%>&pageSize=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/AdminServlet.do?method=list&adminName=<%=StringUtil.notNull(adminName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a> | <a
						href="admin/AdminServlet.do?method=list&adminName=<%=StringUtil.notNull(adminName)%>&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
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
