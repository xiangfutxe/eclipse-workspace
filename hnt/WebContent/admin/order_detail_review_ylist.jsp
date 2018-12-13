<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.OrderDelivery" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.OrderDeliveryDetail" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	OrderDelivery orders = (OrderDelivery)request.getAttribute("orders");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[5][3].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>订单详情</title>
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

</head>

<body>
	<div class="container clearfix">
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a><span
						class="crumb-step">&gt;</span>订单管理<span class="crumb-step">&gt;</span><a href="">已发货订单</a><span class="crumb-step">&gt;</span><span>订单详情</span>
				</div>
			</div>
			<div class="result-wrap">
				<form method="post" id="myform" name="myform">
					<input type="hidden" name="id" id="id" value='<%=orders.getOrderId()%>'/>
					<div class="result-title">
						<div class="result-list">
							<i class="require-red">*</i>收货信息
						</div>
					</div>
					<div class="result-content">

						<table class="insert-tab" width="98%">
								<tbody>
							<tr>
									<th>配货单号：</th>
									<td><%=StringUtil.notNull(orders.getOrderId())%></td>
									<th>当前状态：</th>
								<td><%if(orders.getState()==1){%>待确认<%}else if(orders.getState()==2){%>待发货<%}else if(orders.getState()==3){%>出库中<%}else if(orders.getState()==4){%>已发货<%}else if(orders.getState()==5){%>已收货<%}else if(orders.getState()==0){%>已退订<%} %></td>
  							</tr>
  							<tr>
									<th>订单编号：</th>
									<td><%=StringUtil.notNull(orders.getToOrderId())%></td>
									<th>订单总额：</th>
									<td><%=StringUtil.decimalFormat(orders.getPrice())%></td>
  							</tr>
  							<tr>
									<th>会员编号：</th>
									<td><%=StringUtil.notNull(orders.getUserId())%></td>
									<th>会员名称：</th>
									<td><%=StringUtil.notNull(orders.getUserName())%></td>
  							</tr>
								<tr>
									<th width="150px">收件人：</th>
									<td><%=StringUtil.notNull(orders.getReceiver())%></td>
									<th width="150px">联系电话：</th>
									<td><%=StringUtil.notNull(orders.getPhone())%></td>
								</tr>
								<tr>
	<th>收货地址：</th>
									<td><%=StringUtil.notNull(orders.getAddress())%></td>
								
									<th>发货地点：</th>
									<td><%=StringUtil.notNull(orders.getInventory())%>
								</tr>
								<tr>

<tr>
									<th>确认专员：</th>
									<td><%=StringUtil.notNull(orders.getAdminByConfirmId())%></td>
									<th>出库时间：</th>
									<td><%=StringUtil.parseToString(orders.getConfirmTime(), DateUtil.yyyyMMddHHmmss)%>
									</td>
								</tr>
								<tr>
									<th>发货人：</th>
									<td><%=StringUtil.notNull(orders.getAdminByDeliveryId())%></td>
									<th>出库时间：</th>
									<td><%=StringUtil.parseToString(orders.getDeliveryTime(), DateUtil.yyyyMMddHHmmss)%>
									</td>
								</tr>
								<tr>
									<th>快递公司：</th>
									<td><%=StringUtil.notNull(orders.getExpress())%></td>
									<th>快递单号：</th>
									<td><%=StringUtil.notNull(orders.getExpressNum())%>
									</td>
								</tr>
								<tr>
									<th>复核人：</th>
									<td><%=StringUtil.notNull(orders.getAdminByReviewerId())%></td>
									<th>出库时间：</th>
									<td><%=StringUtil.parseToString(orders.getReviewerTime(), DateUtil.yyyyMMddHHmmss)%>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					
					<div class="result-title">
						<div class="result-list">
							<i class="require-red">*</i>产品清单
						</div>
					</div>
					<div class="result-content">

						<table class="insert-tab" width="98%">
						<thead>
								<tr>
									<td>产品编码</td>
									<td>产品名称</td>
									<td>单价</td>
									<td>规格</td>
									<td>购买数量</td>
									<td>小计</td></tr>
									</thead>
						  	<tbody>
						 <%
								 Collection coll =  (Collection)request.getAttribute("coll");
						if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									OrderDeliveryDetail od = (OrderDeliveryDetail)it.next();
									t++;
							%>
 <tr <%if(t%2==1){%>class="even"<%}%>>
										<td><%=StringUtil.notNull(od.getProductId())%>
										</td>
										<td><%=StringUtil.notNull(od.getProductName())%>
										</td>
										<td><%=StringUtil.notNull(od.getSpecification())%>
										</td>
										<td><%=StringUtil.decimalFormat(od.getProductPrice()) %>
										</td>
										
										<td><%=StringUtil.decimalFormat(od.getNum()) %>
										</td>
										<td><%=StringUtil.decimalFormat(od.getPrice()) %>
										</td>
									</tr>
							<%}
						}else{ %><tr><td colspan="6">暂无记录！</td></tr><%} %>
								<tr>
						
							<td colspan="4">&nbsp;</td><td>合计</td>
									<td><%=StringUtil.decimalFormat(orders.getPrice()) %>
    </td>
    
										
								</tr>
							</tbody>	</table>
					</div>
					<div class="result-title">
						<div class="result-list">
						<br>
							<p><input type="button" class="btn" value="返回上一页" onclick="history.go(-1)" /> &nbsp;<input type="button" class="btn" value="打印订单" onclick="javascript:window.print();"/> 
							&nbsp;<%if(orders.getState()==3){ %><input type="button" class="btn" id="backBtn" value="退货撤回"/><%} %>
						</div>
					</div>
					
				</form>
			</div>
		</div>
		<!--/main-->
	</div>
</body>

<script type="text/javascript">
  
	/* 页面加载完成，绑定事件 */
	$(document).ready(function() {
		$("#backBtn").bind("click", function() {
			if (!confirm("确认要进行退货撤回操作？")) {
			window.event.returnValue = false;
		}else{
		 	$('#myform').attr("action", "admin/OrderServlet?method=admin_order_review_back");
       						$('#myform').submit();
		}
			
		});
		$("#noBtn").bind("click", function() {
			if (!confirm("确认要审核不通过？")) {
			window.event.returnValue = false;
		}else{
		 	$('#myform').attr("action", "admin/OrderServlet?method=admin_order_review_no");
       						$('#myform').submit();
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