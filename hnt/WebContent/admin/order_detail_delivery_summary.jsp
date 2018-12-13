<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Order" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.Product" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String orderId = (String)request.getAttribute("orderId");
	
String token = (String)request.getSession().getAttribute("token");
	
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[5][5].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>订单汇总</title>
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
						class="crumb-step">&gt;</span>订单管理<span class="crumb-step">&gt;</span><span>订单配货</span><span class="crumb-step">&gt;</span><span>订单汇总</span>
				</div>
			</div>
			<div class="result-wrap">
				<form method="post" id="myform" name="myform">
					
					<div class="result-title">
						<div class="result-list">
							<i class="require-red">*</i>订单产品明细汇总
						</div>
					</div>
					<div class="result-content">

						<table class="result-tab" width="100%">
						<thead>
								<tr>
								<th>序号</th>
									<th>产品编码</th>
									<th>产品名称</th>
									<th>规格</th>
									<th>平均单价</th>
									<th>购买数量</th>
									<th>金额小计</th>
									</tr>
									</thead>
						  	<tbody>
						 <%
								 List<Product> coll =  (List<Product>)request.getAttribute("coll");
						if(coll!=null){
							int t=0;
								for(int i=1;i<coll.size();i++){
									Product od = coll.get(i);
									t++;
							%>
 						<tr <%if(t%2==1){%>class="even"<%}%>>
 										<td><%=t%></td>
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
							<%}%>
							<tr><td colspan="4">合计</td>
							<td>-</td>
							<td><%=coll.get(0).getNum() %></td>
							<td><%=coll.get(0).getPrice() %></td>
							</tr>
							<tr><td colspan="7">汇总订单编号：<%=orderId %></td></tr>
							<%
						}else{ %><tr><td colspan="7">暂无记录！</td></tr>
						<%} %>
						
							</tbody>	</table>
					</div>
					<div class="result-title">
						<div class="result-list">
						<br>
							<p><input type="button" class="btn" value="返回上一页" onclick="history.go(-1)" /> 
							&nbsp;<input type="button" class="btn" value="打印订单" onclick="javascript:window.print();"/> 
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
		$("#yesBtn").bind("click", function() {
			if (!confirm("通过订单确认？")) {
			window.event.returnValue = false;
		}else{
		 	$('#myform').attr("action", "admin/OrderServlet?method=admin_order_confirm_yes");
       		$('#myform').submit();
			window.event.returnValue = true;
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