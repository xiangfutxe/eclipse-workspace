<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.VideoInfo"%>
<%@page import="com.utils.StringUtil"%>
<%@page import="com.utils.DateUtil"%>
<%@page import="com.utils.Pager"%>
<%@page import="com.pojo.Admin"%>
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
		if (rankstr[2][1].equals("1") || admin.getState().equals("1")) {
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

<title>视频管理</title>
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
						class="crumb-step">&gt;</span><span class="crumb-name">视频管理</span><span
						class="crumb-step">&gt;</span><span class="crumb-name">视频列表</span>

				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/VideoServlet.do?method=admin_list"
						id="searchForm" method="post">
						<table class="search-tab">
							<tr>
								<th width="80">发布日期:</th>
								<td><input class="common-text" name="startTime"
									id="startTime" type="text"
									value='<%=StringUtil.notNull(startTime)%>'
									onfocus="new Calendar().show(this);"> -
								<td><input class="common-text" name="endTime" id="endTime"
									type="text" value='<%=StringUtil.notNull(endTime)%>'
									onfocus="new Calendar().show(this);"></td>
								<td><input class="btn btn-primary" id="search"
									name="search" value="查询" type="submit"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">
				<form name="delform" id="delform" method="post"
					enctype="multipart/form-data">
					<div class="result-title">
						<div class="result-list">
							<a href="admin/VideoServlet.do?method=admin_video_add"><i
								class="icon-font">&#xe026;</i>添加新闻</a> <a id="batchDel"><i
								class="icon-font">&#xe019;</i>批量删除</a>
						</div>
					</div>
					<div class="result-content">

						<table class="result-tab" width="100%">
							<tr>
								<th class="tc" width="5%"><input class="allChoose"
									id="ckAll" name="ckAll" type="checkbox"></th>
								<th>视频截图</th>
								<th>视频标题</th>
								<th>视频链接</th>
								<th>发布来源</th>
								<th>浏览人数</th>
								<th>发布者</th>
								<th>发布时间</th>
								<th>最后修改时间</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
							<%
								Pager pager = (Pager) request.getAttribute("pager");
										if (pager != null) {
											Collection coll = pager.getResultList();
											if (coll != null) {
												Iterator it = coll.iterator();
												int t = 0;
												while (it.hasNext()) {
													VideoInfo as = (VideoInfo) it.next();
							%>
							<tr>
								<td class="tc"><input name="ids" type="checkbox"
									value="<%=as.getId()%>"></td>
									<td><img src="upload/video/<%=StringUtil.notNull(as.getImageUrl())%>" alt="暂无"  width='100' height="100"/>
								</td>
								<td><a class="link-update"
									href="admin/VideoServlet.do?method=admin_video_detail&id=<%=as.getId()%>"><%=StringUtil.notNull(as.getTitle())%></a>
								</td>
								<td>
									<%=StringUtil.notNull(as.getVideoUrl())%>
								</td>
								<td><%=StringUtil.notNull(as.getSource())%></td>
								<td><%=as.getBrowseNum()%>
								<td><%=StringUtil.notNull(as.getPublisher())%></td>
								<td><%=StringUtil.parseToString(
										as.getEntryTime(),
										DateUtil.yyyyMMddHHmmss)%>
								</td>
								<td><%=StringUtil.parseToString(
										as.getEndTime(),
										DateUtil.yyyyMMddHHmmss)%>
								</td>
								<td>
									<%
										if (as.getState() == 1) {
									%>在线<%
										} else {
									%>下线<%
										}
									%>
								</td>
								<td><a class="link-update"
									href="admin/VideoServlet.do?method=admin_detail&id=<%=as.getId()%>">详情</a>|
									<a class="link-update"
									href="admin/VideoServlet.do?method=admin_edit&id=<%=as.getId()%>">修改</a>|
									<a class="link-update"
									href="admin/VideoServlet.do?method=img_edit&id=<%=as.getId()%>">上传图片</a>|
									<%
										if (as.getState() == 0) {
									%> <a class="link-del"
									href='admin/VideoServlet.do?method=admin_up&id=<%=as.getId()%>'
									onClick="javascript:if (!confirm('确认要将此视频上线，上线后会员可以查看此视频？'))return false;">上线</a>
									<%
										} else {
									%> <a class="link-del"
									href='admin/VideoServlet.do?method=admin_down&id=<%=as.getId()%>'
									onClick="javascript:if (!confirm('确认要将此视频下线，下线后会员将无法查看？'))return false;">下线</a>|<a class="link-del"
									href='admin/VideoServlet.do?method=admin_del&id=<%=as.getId()%>'
									onClick="javascript:if (!confirm('确认要彻底删除此视频，删除后将无法恢复？'))return false;">删除</a> <%}%></td>
							</tr>
							<%
								}
											}
										} else {
							%><tr>
								<td colspan="10">暂无新闻，请点击<a
									href="admin/VideoServlet.do?method=admin_video_add">添加新闻。</a>
								</td>
							</tr>
							<%
								}
							%>
						</table>


					</div>
				</form>
				<%
					if (pager != null) {
				%>
				<div class="list-page">
					<div class="list-page-left">
						<form name="pageSizeForm"
							action="admin/VideoServlet.do?method=admin_list"
							method="post">
							<input type="hidden" name="pageNoStr" value="1" /> <input
								type="hidden" name="sort" value="<%=StringUtil.notNull(sort)%>" />
							<input type="hidden" name="startTime"
								value="<%=StringUtil.notNull(startTime)%>" /> <input
								type="hidden" name="endTime"
								value="<%=StringUtil.notNull(endTime)%>" /> 当前每页显示<select
								name="pageSizeStr" id="pageSizeStr"
								onchange="javascript:pageSizeForm.submit();">

								<option value="10" <%if (pager.getPageSize() == 10) {%> selected
									<%}%>>10</option>
								<option value="20" <%if (pager.getPageSize() == 20) {%> selected
									<%}%>>20</option>
								<option value="40" <%if (pager.getPageSize() == 40) {%> selected
									<%}%>>40</option>
								<option value="60" <%if (pager.getPageSize() == 60) {%> selected
									<%}%>>60</option>
								<option value="80" <%if (pager.getPageSize() == 80) {%> selected
									<%}%>>80</option>
								<option value="100" <%if (pager.getPageSize() == 100) {%> selected
									<%}%>>100</option>
								<option value="200" <%if (pager.getPageSize() == 200) {%> selected
									<%}%>>200</option>
							</select>条记录
						</form>

					</div>
					<div class="list-page-right">
						<form name="pageNoForm"
							action="admin/VideoServlet.do?method=admin_list"
							method="post">
							<input type="hidden" name="pageSizeStr"
								value="<%=pager.getPageSize()%>" /> <input type="hidden"
								name="sort" value="<%=StringUtil.notNull(sort)%>" /> <input
								type="hidden" name="startTime"
								value="<%=StringUtil.notNull(startTime)%>" /> <input
								type="hidden" name="endTime"
								value="<%=StringUtil.notNull(endTime)%>" /> 第<font color="red"><%=pager.getPageNo()%>
							</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页共<font
								color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
							<%
								if (pager.getPageNo() == 1) {
							%>
							首页 | 上一页 |<%
								} else {
							%>
							<a
								href="admin/VideoServlet.do?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">首页</a>
							| <a
								href="admin/VideoServlet.do?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo() - 1%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">上一页</a>
							|
							<%
								}
							%>
							<%
								if (pager.getPageNo() != pager.getPageCount()) {
							%>
							<a
								href="admin/VideoServlet.do?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo() + 1%>
						&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">
								下一页</a> | <a
								href="admin/VideoServlet.do?method=admin_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">尾页</a>
							<%
								} else {
							%>下一页 | 尾页<%
								}
							%>&nbsp; 跳转到第<select name="pageNoStr"
								onchange="javascript:pageNoForm.submit();">
								<%
									for (int i = 0; i < pager.getPageCount(); i++) {
								%>
								<option value="<%=i + 1%>" <%if (pager.getPageNo() == (i + 1)) {%>
									selected <%}%>>
									<%=i + 1%>
								</option>
								<%
									}
								%>
							</select>页
						</form>
					</div>
					<%
						}
					%>
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