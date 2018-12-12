<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Order" %>
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
	String userId = (String)request.getAttribute("userId");
String startTime = (String)request.getAttribute("startTime");
String orderType = (String)request.getAttribute("orderType");
String endTime = (String)request.getAttribute("endTime");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[5][1].equals("1")||admin.getState().equals("1")){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>订单管理</title>
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
		if (!confirm("确认要退订？")) {
			window.event.returnValue = false;
		}
	}
	
	

	$(function() {
	
	
	 $("#all_yes").click(function(){
	  	  if (!confirm("确认批量确认通过所选订单？")) {
				return false;
			}
			else{
	       		$("#myform").attr("action", "admin/OrderServlet.do?method=admin_order_confirm_all_yes");
				$("#myform").submit();
	       		 return true;
			}
		 });
		 
		 $("#merge").click(function(){
	  	  if (!confirm("确认要合并所选订单，合并后将不能进行分拆?")) {
				return false;
			}
			else{
	       		$("#myform").attr("action", "admin/OrderServlet.do?method=admin_order_confirm_merge");
				$("#myform").submit();
	       		 return true;
			}
		 });
		 
		  $("#confirm_list").click(function(){
	  	  if (!confirm("确认查看已确认通过的列表？")) {
				return false;
			}
			else{
	       		$("#myform").attr("action", "admin/OrderServlet.do?method=admin_order_confirm_ylist");
				$("#myform").submit();
	       		 return true;
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
						class="crumb-step">&gt;</span><span class="crumb-name">订单管理</span><span
						class="crumb-step">&gt;</span><span class="crumb-name">订单确认</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="admin/OrderServlet.do?method=admin_order_confirm" id="searchForm" method="post">
							<table class="search-tab">
							<tr>
								<th width="100">订单类型：</th><td><select name="orderType" id="orderType" class="common-text">
					<option value="">--请选择订单类型--</option>
						<option value="1" <%if(StringUtil.notNull(orderType).equals("1")){ %>selected<%} %>>消费购物</option>
						<option value="2" <%if(StringUtil.notNull(orderType).equals("2")){ %>selected<%} %>>新店配货</option>
						</select>	&nbsp;&nbsp;
							&nbsp;&nbsp;分店编号：<input class="common-text" name="userId" id="userId" size="15"
									type="text" value='<%=StringUtil.notNull(userId) %>'>
							</td>
							</tr>
							<tr>
							<th width="100">
									&nbsp;起止日期：</th><td><input class="common-text" name="startTime" id="startTime" size="15"
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-<input class="common-text" name="endTime" id="endTime" size="15"
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								&nbsp;<input class="btn btn-primary btn2" id="search"
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
                         <button id="confirm_list" class="btn"><i class="icon-font">&#xe05b;</i>查看确认列表</button>
                      &nbsp;
                     
                        <button id="merge" class="btn"><i class="icon-font">&#xe022;</i>合并订单</button>
                         &nbsp;
                     
                        <button id="all_yes" class="btn"><i class="icon-font">&#xe025;</i>批量确认通过</button>
                   
                    </div>
                </div>
			
				<div class="result-content">
					<table class="result-tab" width="99%">
						<tr>
						<th class="tc" width="20px"><input class="allChoose" id="ckAll" name="ckAll" type="checkbox"></th>

							<th>订单编号</th><th>订单类型</th><th>总金额</th><th>分店信息</th><th>收件人</th><th>联系电话</th><th>订单日期</th><th>当前状态</th><th>操作</th>
						
						</tr>
						 <%
								 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									Order order = (Order)it.next();
									t++;
							%>
 <tr <%if(t%2==1){%>class="even"<%}%>>
   <td class="tc"><input name="ids" type="checkbox" value="<%=order.getId()%>"></td>
 <td><%=StringUtil.notNull(order.getOrderId()) %></td>
 <td><%if(order.getOrderType()==1){ %>消费购物<%}else if(order.getOrderType()==2){ %>新店配货<%}else if(order.getOrderType()==3){%>换货购物<%}else{%>-<%} %></td>
 <td><%=StringUtil.decimalFormat(order.getPrice()) %>
    </td>
    <td><%=StringUtil.notNull(order.getUserName()) %>(<%=StringUtil.notNull(order.getUserId()) %>)</td>
 <td><%=StringUtil.notNull(order.getReceiver()) %></td>
  <td><%=StringUtil.notNull(order.getPhone()) %></td>
   
 <td><%=StringUtil.parseToString(order.getOrderTime(), DateUtil.yyyyMMddHHmmss)%></td>
  <td><%if(order.getState()==1){%>待确认<%}else if(order.getState()==2){%>待发货<%}else if(order.getState()==3){%>出库中<%}else if(order.getState()==4){%>已发货<%}else if(order.getState()==5){%>已收货<%}else if(order.getState()==0){%>已退订<%} %></td>
 <td><a href="admin/OrderServlet.do?method=admin_delivery_list&id=<%=order.getOrderId()%>">配货单</a>
 |<a href="admin/OrderServlet.do?method=admin_order_adr_edit&id=<%=order.getOrderId()%>">修改收货地址</a>
|<a href="admin/OrderServlet.do?method=admin_order_detail_confirm&id=<%=order.getOrderId()%>">详情</a>
 |<a href="admin/OrderServlet.do?method=admin_order_back&id=<%=order.getOrderId()%>" onclick="javacript:if(!confirm('确认要删除该笔订单?')) return true;">退单</a>
  </td>
 </tr>
	 	<%}}
						}else{ %><tr><td colspan="10">暂无记录！</td></tr><%} %>
					</table>
				</div>
				</form>
			 <% if(pager!=null){ %>
				<div class="list-page">
					<div class="list-page-left">
						<form name="pageSizeForm" action="admin/OrderServlet.do?method=admin_order_confirm"
							method="post">
								<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
							<input type="hidden" name="orderType" value="<%=StringUtil.notNull(orderType)%>"/>
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
						<form name="pageNoForm" action="admin/OrderServlet.do?method=admin_order_confirm"
							method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
								<input type="hidden" name="userId" value="<%=StringUtil.notNull(userId)%>"/>
							<input type="hidden" name="orderType" value="<%=StringUtil.notNull(orderType)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页 | 上一页  |<%}else{%>
					<a
						href="admin/OrderServlet.do?method=admin_order_confirm&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&orderType=<%=StringUtil.notNull(orderType)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&userId=<%=StringUtil.notNull(userId)%>">首页</a> | <a
						href="admin/OrderServlet.do?method=admin_order_confirm&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&orderType=<%=StringUtil.notNull(orderType)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&userId=<%=StringUtil.notNull(userId)%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="admin/OrderServlet.do?method=admin_order_confirm&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&userId=<%=StringUtil.notNull(userId)%>&orderType=<%=StringUtil.notNull(orderType)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">
                    下一页</a> | <a
						href="admin/OrderServlet.do?method=admin_order_confirm&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&orderType=<%=StringUtil.notNull(orderType)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>&userId=<%=StringUtil.notNull(userId)%>">尾页</a>
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