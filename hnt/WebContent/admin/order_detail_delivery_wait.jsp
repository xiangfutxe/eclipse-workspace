<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Order" %>
<%@page import="com.ssm.pojo.OrderDelivery" %>
<%@page import="com.ssm.utils.ArithUtil" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.OrderDetailProduct" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	Order orders = (Order)request.getAttribute("orders");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[5][2].equals("1")||admin.getState().equals("1")){
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
						class="crumb-step">&gt;</span>订单管理<span class="crumb-step">&gt;</span><span>订单详情</span>
				</div>
			</div>
			<div class="result-wrap">
				<form method="post" id="myform" name="myform">
					<input type="hidden" name="id" id="id" value='<%=orders.getId()%>'/>
			<input type="hidden" name="orderId" id="orderId" value='<%=orders.getOrderId()%>'/>
					
					<div class="result-title">
						<div class="result-list">
							<i class="require-red">*</i>收货信息
						</div>
					</div>
					<div class="result-content">

						<table class="insert-tab" width="97%">
							<tbody>
							<tr>
									<th>订单编号：</th>
									<td><%=StringUtil.notNull(orders.getOrderId())%></td>
									<th>当前状态：</th>
									<td><%if(orders.getState()==1){%>待确认<%}else if(orders.getState()==2){%>待发货<%}else if(orders.getState()==3){%>出库中<%}else if(orders.getState()==4){%>已发货<%}else if(orders.getState()==5){%>已收货<%}else if(orders.getState()==0){%>已退订<%} %></td>
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
								
								
								
							</tbody>
						</table>
					</div>
					<% 
					 Collection coll_1 =  (Collection)request.getAttribute("coll_1");
						if(coll_1!=null){
						%>
					<div class="result-title">
						<div class="result-list">
							<i class="require-red">*</i>发货单
						</div>
					</div>
					<div class="result-content">

						<table class="insert-tab" width="100%">
						<thead>
								<tr>
									<td>序号</td>
									<td>订单编号</td>
									<td>金额</td>
									<td>PV</td>
									<td>操作员</td>
									<td>订单日期</td>
									<td>状态</td>
								</tr>
									</thead>
						  	<tbody>
						 <%
														 double total =0;
								
									Iterator it = coll_1.iterator();
									int t =0;
									while(it.hasNext()){
									OrderDelivery order = (OrderDelivery)it.next();
									t++;
							%>
 <tr <%if(t%2==1){%>class="even"<%}%>>
 <td><%=t%>
										<td><%=StringUtil.notNull(order.getOrderId())%>
										</td>
										<td><%=StringUtil.decimalFormat(order.getPrice())%>
										</td>
										<td><%=StringUtil.decimalFormat(order.getPv())%>
										</td>
										<td><%=StringUtil.notNull(order.getAdminByDeliveryId()) %>
										</td>
										<td><%=StringUtil.parseToString(order.getConfirmTime(), DateUtil.yyyyMMddHHmmss) %>
										</td>
									<td><%if(order.getState()==1){ %>已确认待配货
									<%}else if(order.getState()==2){ %>已配货待审核
									<%}else if(order.getState()==3){ %>已发货
									<%} %>
										</td>
									</tr>
							<%}
						}else{ %><tr><td colspan="7">暂无记录！</td></tr><%} %>
								
							</tbody>	</table>
					</div>
					
					<div class="result-title">
						<div class="result-list">
							<i class="require-red">*</i>产品清单
						</div>
					</div>
					<div class="result-content">

						<table class="insert-tab" width="100%">
						<thead>
								<tr>
									<td>产品编码</td>
									<td>产品名称</td>
									<td>规格</td>
									<td>单价</td>
									<td>购买数量</td>
									<td>小计</td>
									<td>已发数量</td>
									
									</tr>
									</thead>
						  	<tbody>
						 <%
						 double total = 0;
								 Collection coll =  (Collection)request.getAttribute("coll");
						if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									OrderDetailProduct od = (OrderDetailProduct)it.next();
									t++;
									total = ArithUtil.add(total,od.getPrice());
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
										<td><%=StringUtil.decimalFormat(od.getDeliveryNum()) %>
										</td>
									</tr>
							<%}
						}else{ %><tr><td colspan="7"></td></tr><%} %>
						<tr>
						
							<td colspan="4">&nbsp;</td><td>合计</td>
									<td><%=StringUtil.decimalFormat(total) %>
    </td>
    <td></td>
										
								</tr>
								<tr>
						
							<td colspan="4">&nbsp;</td><td>优惠价
							</td>
									<td><%=StringUtil.decimalFormat(orders.getPrice()) %>
    </td>
    <td></td>
										
								</tr>
							</tbody>	</table>
					</div>
					<div class="result-title">
						<div class="result-list">
						<br>
							<p><input type="button" class="btn" value="返回上一页" onclick="history.go(-1)" /> 
							&nbsp;<input type="button" class="btn" value="打印订单" onclick="javascript:window.print();"/> 
							&nbsp;<%if(orders.getState()==2){ %><input type="button" class="btn" id="deliveryBtn" value="生成配货单"/>&nbsp;<%if(orders.getDeliveryNum()==0){ %><input type="button" class="btn" id="deliveryBackBtn" value="订单退回"/><%}} %></p>
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
		$("#deliveryBtn").bind("click", function() {
			if (!confirm("确认要生成发货单？")) {
			return false;
		}else{
		 	$('#myform').attr("action", "admin/OrderServlet?method=admin_order_detail_delivery_add");
       		$('#myform').submit();
			return true;
		}
		});
		
		$("#deliveryBackBtn").bind("click", function() {
			if (!confirm("确认要将订单退回客服？？退回后请通知客服人员重新核对！")) {
			return false;
		}else{
		 	$('#myform').attr("action", "admin/OrderServlet?method=admin_order_delivery_wait_back");
       		$('#myform').submit();
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