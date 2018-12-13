<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Inventory" %>
<%@page import="com.ssm.pojo.InventoryApply" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String state = (String)request.getAttribute("state");
	String inventoryId = (String)request.getAttribute("inventoryId");
String startTime = (String)request.getAttribute("startTime");
String endTime = (String)request.getAttribute("endTime");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[4][3].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>产品列表</title>
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
<script type="text/javascript" src="js/layer.js"></script>
<script src="js/calendar.js"></script>
<script language="JavaScript">
	function delcfm() {
		if (!confirm("确认要删除？")) {
			return false;
		}
	}

	$(function() {

		$("#batchDel").click(function() {

			if (!confirm("确认要删除？")) {
				return false;
			} else {
				$('#delform').attr("action", "admin/product_deleteAll.action");
				$('#delform').submit();
			}

		});
$("#dbtn").click(function() {
		$.ajax({
				type : "post",
				url : "admin/apply_detail.action",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					productId : $("input[name=productId]").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = eval("(" + data + ")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="0") $("#proudctIdTag").text("产品编号不能为空！").css({"color":"red","font-size":"9px"});
					else if(d.tag=="1")  $("#proudctIdTag").text("产品编号已存在！").css({"color":"red","font-size":"9px"});
					else if(d.tag=="2")  $("#proudctIdTag").text("此产品编号可用！").css({"color":"green","font-size":"9px"});
					else $("#proudctIdTag").text("");
					layer.open({
				        type: 2,
				        title: '修改清单',
				        maxmin: true,
				        shadeClose: true, //点击遮罩关闭层
				        area : ['800px' , '460px'],
				        content: 'test/iframe.html'
    				});
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	
});
$("#rbtn").click(function() {
		if (!confirm("确认要删除？")) {
			return false;
		}else{
		
		}
});
		$('#search')
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
						class="crumb-step">&gt;</span><span class="crumb-name">仓库管理</span><span
						class="crumb-step">&gt;</span><span class="crumb-name">出库审核</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/InventoryServlet?method=apply_out_review" id="searchForm"
						method="post">
						<table class="search-tab">
							<tr>
								<th width="80">选择仓库:</th>
								<td><select class="common-text" id="inventoryId"
									name="inventoryId">
										<option value="">--所有仓库--</option>
									 <%
							Collection coll_1 = (Collection)request.getAttribute("coll_1");
							if(coll_1!=null){
									Iterator it1 = coll_1.iterator();
									while(it1.hasNext()){
									Inventory ap = (Inventory)it1.next();
							%>
											<option value="<%=StringUtil.notNull(ap.getInventoryName())%>" <%if(StringUtil.notNull(inventoryId).equals(ap.getInventoryName())){%>selected<%}%>>
												<%=StringUtil.notNull(ap.getInventoryName())%>
											</option>
										<%} }%>
								</select></td>
									<th width="70">申请时间:</th>
								<td><input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-
								<td><input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								</td>
								<td><input class="btn btn-primary" id="search"
									name="search" value="查询" type="button"><span
									id="timeTag" style="color:red"></span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">
				
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>

							<th>申请单号</th>
							<th>仓库名称</th>
							<th>申请人</th>
							<th>申请时间</th>
							<th>审核人</th>
							<th>审核时间</th>
							<th>当前状态</th>
							<th>操作</th>
						</tr>
						 <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									InventoryApply apply = (InventoryApply)it.next();
							%>
							<tr>
								<td><%=StringUtil.notNull(apply.getApplyId()) %>
								</td>
								<td><%=StringUtil.notNull(apply.getInventory()) %>
								</td>
								<td><%=StringUtil.notNull(apply.getAdminByOperatorId()) %></td>
								<td><%=StringUtil.parseToString(apply.getTime(), DateUtil.yyyyMMddHHmmss)%></td>
								<td><%=StringUtil.notNull(apply.getAdminByReviewerId()) %></td>
								<td><%=StringUtil.parseToString(apply.getReviewTime(), DateUtil.yyyyMMddHHmmss)%></td>
								<td><%if(apply.getState()==1){%>待审核<%}else if(apply.getState()==2) {%>审核通过<%}else if(apply.getState()==3) {%>审核不通过<%}else{ %>已删除<%} %></td>
								<td><a class="link-update"
									href="admin/InventoryServlet?method=apply_out_review_detail&id=<%=StringUtil.notNull(apply.getApplyId())%>">详情</a>&nbsp;
									</td>
							</tr>
						<%}}}else{%>
						<tr> <td colspan="8">暂无需要审核的申请。</td></tr>
						<%} %>
					</table>


				</div>
			  <% if(pager!=null){ %>
				<div class="list-page">
					<div class="list-page-left">
						<form name="pageSizeForm" action="admin/InventoryServlet?method=apply_out_review"
							method="post">
								<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
							<input type="hidden" name="inventoryId" value="<%=StringUtil.notNull(inventoryId)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
							当前每页显示<select name="pageSizeStr" id="pageSizeStr"
								onchange="javascript:pageSizeForm.submit();">

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

					</div>
					<div class="list-page-right">
						<form name="pageNoForm" action="admin/InventoryServlet?method=apply_out_review"
							method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
							<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
								<input type="hidden" name="inventoryId" value="<%=StringUtil.notNull(inventoryId)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页 | 上一页  |<%}else{%>
					<a
						href="admin/InventoryServlet?method=apply_out_review&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&state=<%=StringUtil.notNull(state)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&inventoryId=<%=StringUtil.notNull(inventoryId)%>">首页</a> | <a
						href="admin/InventoryServlet?method=apply_out_review&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&state=<%=StringUtil.notNull(state)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&inventoryId=<%=StringUtil.notNull(inventoryId)%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/InventoryServlet?method=apply_out_review&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&inventoryId=<%=StringUtil.notNull(inventoryId)%>&state=<%=StringUtil.notNull(state)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">
                    下一页</a> | <a
						href="admin/InventoryServlet?method=apply_out_review&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&state=<%=StringUtil.notNull(state)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&inventoryId=<%=StringUtil.notNull(inventoryId)%>">尾页</a>
				<%}else{ %>下一页 | 尾页<%} %>&nbsp; 跳转到第<select
						name="pageNoStr" onchange="javascript:pageNoForm.submit();">
						<%for(int i=0;i<pager.getPageCount();i++){ %>
							<option value="<%=i+1 %>" <%if(pager.getPageNo()==(i+1)){%>selected<%} %>>
								<%=i+1 %>
							</option>
							<%} %>
					</select>页
						</form>
					</div>
                <%} %>
				</div>
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