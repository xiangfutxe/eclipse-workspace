<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.OrderDelivery" %>
<%@page import="com.ssm.pojo.Order" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.ArithUtil" %>

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
Order order_old = (Order)request.getAttribute("order_old");
	
	String token = (String)request.getSession().getAttribute("token");
	
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
<div id="div_all">
	<div class="container clearfix">
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a><span
						class="crumb-step">&gt;</span>订单管理
						<span class="crumb-step">&gt;</span><span>订单配货</span>
						<span class="crumb-step">&gt;</span><span>订单详情</span>
				</div>
			</div>
			
			<div class="result-wrap">
				<form method="post" id="myform" name="myform">
					<input type="hidden" name="id" id="id" value='<%=orders.getOrderId()%>'/>
                <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
					<div id="div1">
					<div class="result-title">
						<div class="result-list">
						<p style="text-align:center;"><span style="font-size:16px;font-weight:bold;"> 大健康发货单</span></p>
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
									<td><%=StringUtil.notNull(order_old.getOrderId())%></td>
									<th>订单总额：</th>
									<td><%=StringUtil.decimalFormat(order_old.getPrice())%></td>
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
									<td colspan="3"><%=StringUtil.notNull(orders.getAddress())%></td>
								
								
								</tr>
								<tr>
								
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
									<td>规格</td>
									<td>单价</td>
									<td>购买数量</td>
									<td>小计</td></tr>
									</thead>
						  	<tbody>
						 <%
						 double total = 0;
								 Collection coll =  (Collection)request.getAttribute("coll");
						if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									OrderDeliveryDetail od = (OrderDeliveryDetail)it.next();
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
									</tr>
							<%}
						}else{ %><tr><td colspan="7">暂无记录！</td></tr><%} %>
							<tr>
						
							<td colspan="4">&nbsp;</td><td>合计</td>
									<td><%=StringUtil.decimalFormat(total) %>
    </td>
    
										
								</tr>
								<tr>
						
							<td colspan="4">&nbsp;</td><td>优惠价</td>
									<td><%=StringUtil.decimalFormat(orders.getPrice()) %>
    </td>
    
										
								</tr>
							</tbody>	</table>
					</div>
					</div>
					<div class="result-title">
						<div class="result-list">
						<br>
							<p><input type="button" class="btn" value="返回上一页" onclick="history.go(-1)" /> 
							&nbsp;<input type="button" class="btn" value="打印订单" target="_self" onclick="javascript:printme();"/> 
							&nbsp;<%if(orders.getState()==1){ %><input type="button" class="btn" id="deliveryBtn" value="确认出库"/>&nbsp;<input type="button" class="btn" id="deliveryBackBtn" value="订单退回"/><%} %></p>
						</div>
					</div>
				</form>
			</div>

		</div>
		</div>
		<!--/main-->
		<script type="text/javascript">
function printme() 
{ 

var div_all = document.body.innerHTML=document.getElementById('div_all').innerHTML+'<br/>'; 
document.body.innerHTML=document.getElementById('div1').innerHTML+'<br/>'; 
window.print(); 
document.body.innerHTML = div_all;

} 
	/* 页面加载完成，绑定事件 */
	$(document).ready(function() {
		$("#deliveryBtn").bind("click", function() {
			if (!confirm("确认要出库，请核对好出库清单？")) {
			window.event.returnValue = false;
		}else{
		 	$('#myform').attr("action", "admin/OrderServlet?method=admin_order_delivery_express");
       		$('#myform').submit();
			return true;
		}
		});
		
		$("#deliveryBackBtn").bind("click", function() {
			if (!confirm("确认要将订单退回？？退回后请通知配货重新核对！")) {
			window.event.returnValue = false;
		}else{
		 	$('#myform').attr("action", "admin/OrderServlet?method=admin_order_delivery_back");
       		$('#myform').submit();
			return true;
		}
			
		});
	});
</script>
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