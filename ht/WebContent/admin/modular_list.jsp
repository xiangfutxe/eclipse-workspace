<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Modular"%>
<%@page import="com.utils.StringUtil"%>
<%@page import="com.utils.DateUtil"%>
<%@page import="com.utils.Pager"%>
<%@page import="com.pojo.Admin"%>
<%@page import="java.util.Collection"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession()
			.getAttribute("sys_admin");
	String sort = (String) request.getAttribute("sort");
	String startTime = (String) request.getAttribute("startTime");
	String endTime = (String) request.getAttribute("endTime");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	} else {
		String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if (rankstr[0][1].equals("1") || admin.getState().equals("1")) {
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

<title>模块化管理</title>
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

	function upcfm() {
		if (!confirm("确认要重新上线？")) {
			window.event.returnValue = false;
		}
	}
	$(function() {

		$("#batchDel").click(
				function() {

					if (!confirm("确认要批量删除？")) {
						window.event.returnValue = false;
					} else {
						$('#delform').attr("action",
								"admin/news_sort_deleteAll.action");
						$('#delform').submit();
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

		$('#search')
				.click(
						function() {

							var startTime = $("#startTime").val();
							var endTime = $("#endTime").val();
							var str = "";
							var a = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;

							if (startTime == "") {
								if (endTime != "") {
									str = str + "开始时间不能为空！\n";
									$("#startTime").focus();
								}
							} else if (endTime == "") {
								if (startTime != "") {
									str = str + "结束时间不能为空！\n";
									$("#endTime").focus();
								}
							} else if (startTime.match(a) == null) {
								str = str + "开始时间格式有误！\n";
								$("#startTime").focus();
							} else if (endTime.match(a) == null) {
								str = str + "结束时间格式有误！\n";
								$("#endTime").focus();
							} else if (startTime > endTime) {
								str = str + "开始时间不能超过结束时间！\n";
								$("#endTime").focus();
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
						class="crumb-step">&gt;</span><span class="crumb-name">信息管理</span><span
						class="crumb-step">&gt;</span><span class="crumb-name">模块管理</span>

				</div>
			</div>
		
			<div class="result-wrap">
			<div class="result-title">
						<div class="result-list">
							<a href="admin/ModularServlet.do?method=add" class="icon-font"><i
								>&#xe026;</i>新增模块</a>
						</div>
					</div>
				<div class="result-content">
			
				</div>
					<div class="result-content">
						<table class="result-tab" width="100%">
							<tr>
								<th>序号</th>
								<th>模块名称</th>
								<th>模块标题</th>
								<th>发布来源</th>
								<th>发布时间</th>
								<th>最后修改时间</th>
								<th>操作</th>
							</tr>
							<%
								 Collection coll= (Collection) request.getAttribute("mlist");
								 if (coll!=null) {
											if (coll.size()>0) {
												Iterator it = coll.iterator();
												int t = 0;
												while (it.hasNext()) {
													Modular as = (Modular) it.next();
													t++;
							%>
							<tr>
								<td class="tc"><%=t %></td>
								<td><a class="link-update"
									href="admin/ModularServlet.do?method=edit&id=<%=as.getId()%>"><%=StringUtil.notNull(as.getType())%></a>
								</td>
								<td>
									<%=StringUtil.notNull(as.getTitle())%>
								</td>
								<td><%=StringUtil.notNull(as.getSource())%></td>
								<td><%=StringUtil.parseToString(
										as.getEntryTime(),
										DateUtil.yyyyMMddHHmmss)%>
								</td>
								<td><%=StringUtil.parseToString(
										as.getEndTime(),
										DateUtil.yyyyMMddHHmmss)%>
								</td>
							
								<td>
									<a class="link-update"
									href="admin/ModularServlet.do?method=edit&id=<%=as.getId()%>">修改</a>|
								<a class="link-del"
									href='admin/ModularServlet.do?method=adel&id=<%=as.getId()%>'
									onClick="javascript:if (!confirm('确认要彻底删除此模块，删除后将无法恢复？'))return false;">删除</a> </td>
							</tr>
							<%}
							}
							} else {
							%><tr>
								<td colspan="8">暂无模块，请点击<a
									href="admin/ModularServlet.do?method=add">添加模块。</a>
								</td>
							</tr>
							<%
								}
							%>
						</table>


					</div>
				</div>
			</div>
		<!--/main-->
	</div>
</body>
</html>
<%
	} else {
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
	}
%>