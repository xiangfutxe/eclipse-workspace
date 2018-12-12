<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Order" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.OrderDetail" %>
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
		if(rankstr[5][1].equals("1")||admin.getState().equals("1")){
		double totalNum = 0;
		double totalPrice =0;
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
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.jqprint-0.3.js"></script>
<script language="Javascript">
function preview()
{
    bdhtml=window.document.body.innerHTML;
    sprnstr="<!--startprint-->";
    eprnstr="<!--endprint-->";
    prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML=prnhtml;
    window.print();
     window.document.body.innerHTML=bdhtml;
}
</script>
<style media=print>
   .Noprint{display:none;}
   .PageNext{page-break-after: always;}
</style>
</head>

<body>
	<div class="container clearfix">
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a><span
						class="crumb-step">&gt;</span>订单管理
						<span class="crumb-step">&gt;</span><span>配送单</span>
				</div>
			</div>
			<div class="result-wrap">
				<form method="post" id="myform" name="myform">
					<input type="hidden" name="id" id="id" value='<%=orders.getOrderId()%>'/>
						
					 <!--startprint-->
					<div id="printContainer">
					<div class="result-content">
						<table  width="97%" style="font_family:'微软雅黑',sans-serif;color:#000 !important;font-weight:normal;border:0px; border-collapse:separate; border-spacing:0px 10px;">
							<tbody>
							<tr style="font-size:20px;">
							<td colspan="3" align="center">
							<strong>广州秦筷餐饮服务有限公司配送单</strong>
							<br>
							</td>
							</tr>
							<tr style="font-size:16px;">
									<td colspan="2">分店：<%=StringUtil.notNull(orders.getUserName())%></td>
									<td>单号：<%=StringUtil.notNull(orders.getOrderId())%></td>
  							</tr>
								<tr  style="font-size:16px;">
									<td>联系人：<%=StringUtil.notNull(orders.getReceiver())%></td>
									<td>电话：<%=StringUtil.notNull(orders.getPhone())%></td>
									<td>地址：<%=StringUtil.notNull(orders.getAddress())%></td>
								</tr>
							</tbody>
						</table>
					</div>
					
				
					<div class="result-content">
						<table class="result-tab-1"  width="100%">
						<thead>
								<tr>
								<td>分类</td>
									<td>编码</td>
									<td>产品名称</td>
									<td>规格</td>
									<td>单位</td>
									<td>单价</td>
									<td>数量</td>
									<td>金额</td>
									<td>备注</td>
									</tr>
									</thead>
						  	<tbody>
						 <%
								 Collection coll =  (Collection)request.getAttribute("coll");
						if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									OrderDetail od = (OrderDetail)it.next();
									t++;
									totalNum = totalNum+od.getNum();
									totalPrice = totalPrice+od.getPrice();
							%>
 <tr <%if(t%2==1){%>class="even"<%}%>>
 <td><%=StringUtil.notNull(od.getProductType())%>
										</td>
										<td><%=StringUtil.notNull(od.getProductId())%>
										</td>
										<td><%=StringUtil.notNull(od.getProductName())%>
										</td>
										<td><%=StringUtil.notNull(od.getSpecification())%>
										</td>
										<td><%=StringUtil.notNull(od.getUnit())%>
										</td>
										<td><%=StringUtil.decimalFormat(od.getProductPrice()) %>
										</td>
										<td><%=StringUtil.decimalFormat(od.getNum()) %>
										</td>
										<td><%=StringUtil.decimalFormat(od.getPrice()) %>
										</td>
										<td>
										</td>
									</tr>
							<%}
						}else{ %><tr><td colspan="9">暂无记录！</td></tr><%} %>
								<tr>
							<td colspan="5" align="center">-合计-</td>
							<td align="center">-
    </td>
    <td><%=StringUtil.decimalFormat(totalNum) %>
    </td>
									<td><%=StringUtil.decimalFormat(totalPrice) %>
    </td>
    <td>-
    </td>
								</tr>
								<tr>
							<td rowspan="4" colspan="3" align="center">返回包装统计</td>
							<td rowspan="2" align="center">周转盒
    </td>
    <td  rowspan="2">33*22*12
    </td>
	<td  rowspan="2">套
    </td>
   
     <td>
     当日送达
    </td>
    <td>
    </td>
     <td>
    </td>
	</tr>
	<tr>
						
     <td>
     实际返还
    </td>
    <td>
    </td>
     <td>
    </td>
	</tr>
	<tr>
		<td rowspan="2" align="center">斜柄西王桶
    </td>
    <td  rowspan="2">5L
    </td>
	<td  rowspan="2">件
    </td>
   
     <td>
     当日送达
    </td>
    <td>
    </td>
     <td>
    </td>
	</tr>
	<tr>
						
     <td>
     实际返还
    </td>
    <td>
    </td>
     <td>
    </td>
	</tr>
							</tbody>	</table>
					</div>
					<div class="result-content">

						<table  style="font_family:'微软雅黑';color:#000 !important;line-height:15px;border:0px; font-size:16px; border-collapse:separate; border-spacing:0px 10px;" width="97%" >
							<tbody>
							<tr>
									<td>制表人：</td>
									<td>配货人：</td>
									<td>送货人：</td>
									<td>接货人：</td>
  							</tr>
							</tbody>
						</table>
					</div>
					</div>
					 <!--endprint-->
					<div class="result-title">
						<div class="result-list">
						<br>
							<p><input type="button" class="btn" value="返回上一页" onclick="history.go(-1)" /> 
							&nbsp;<input type="button" onclick="preview()" class="btn" value="打印配货单" /> </p>
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
	$("#print_btn").bind("click", function() {
		$("#printContainer").jqprint({
	     debug: false, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
	     importCSS: true, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
	     printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）。
	     operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true
		});
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