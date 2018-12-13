<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.utils.ArithUtil" %>

<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.pojo.Order" %>
<%@page import="com.ssm.pojo.OrderDetail" %>

<%@page import="com.ssm.utils.Pager" %>

<%@ include file="check.jsp" %>  

<%
User user = (User)request.getSession().getAttribute("sys_user");
String psw2 = (String)request.getSession().getAttribute("psw2");
	Order orders = (Order)request.getAttribute("orders");

	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
out.println("userId:"+user.getUserId());
out.println("userName:"+user.getUserName());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="Assets/css/reset.css"/>
<script type="text/javascript" src="Assets/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="Assets/js/js_z.js"></script>
<script type="text/javascript" src="Assets/js/main.js"></script>
<link rel="stylesheet" type="text/css" href="css/thems.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<link rel="stylesheet" type="text/css" href="css/form.css">
<script type="text/javascript" src="js/other.js"></script>
<script src="js/jquery.slider.js"></script>
<script type="text/javascript" src="js/psw2.js"></script>
<link rel="stylesheet" href="fonts/fontCss.css">
<link rel="stylesheet" href="css/jquery.slider.css">
 <script type="text/javascript" src="js/jquery.cityselect.js"></script>
 <script src="../js/calendar.js"></script>
 
	<script>
	window.onload = function(){
		$('.testSlider').slider({
			originType:'tuoyuan'
		});
		$('.testSlider1').slider({
			width:300,
			height:300,
			showSlider: false,
			showOrigin: false,
			originPosition:'center'
		});

		$('.testSlider3').slider({
			width:1094,
			height:300,
			originPosition:'center',
			source: [
							{
								src: 'img/slider1.jpg',									// 幻灯片图片地址		
								hasHref: true,												// 是否有链接
								href: '#',			// 链接地址					
								hrefTarget: '_self'											// 链接打开方式  _self, _blank, _top等
 							},
						]
		});
	};
</script>
  </head>
  
 <body>
 <!--头部-->
 <jsp:include page="header.jsp" />
<div class="main_bg">
    <!--幻灯片-->
    <div class="banner1">
            <span class="testSlider3" style="display: inline-block;"></span>
    </div>
  
	<!--幻灯片END-->
	<div class="main_bg">
	<div class="second">   
        <div class="scd_m clearfix">
        	<div class="scd_ml">
            	<ul class="sidenav">
            	<li  class="now"><a href="OrderServlet?method=orders">订单列表</a></li> 
				<li><a href="OrderServlet?method=order_divery">服务订单</a></li> 
				<li><a  href="OrderServlet?method=order_emoney_add">零售购物</a></li>
				<li><a href="OrderServlet?method=order_dmoney_add">复消购物</a></li>
                </ul>
                <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：订单中心-&gt;订单列表-&gt;<a>订单详情</a></h2>
            	</div>
            			<div class="content content-full-width" id="content-psw">
   		<input type="hidden" id="psw1" name="psw1" value="<%=user.getPassword2()%>"/>
		<div class="notice">
         <h2>支付密码</h2><span class="required_notification"></span>
         </div>
         	 <table class ="form-table">
  			 <tr>
  			 <td width="15%"><label><span class="required">*</span>请输入支付密码：</label></td>
  			 <td ><input id="psw2" name="psw2"  type="password" value="<%=psw2%>"/>&nbsp;<span id="psw2Tag"></span></td>
  			 </tr>
  			 </table>
   		
		</div>
            		<div class="content content-full-width" id="content-all">
  				
                	<div class="notice">
         <h2>订单详情</h2><span class="required_notification"></span>
   </div>
 
    <div class="p-li ">
     <table class="result-tab" width="100%">
							<tbody>
							<tr>
									<th>订单编号：</th>
									<td><%=StringUtil.notNull(orders.getOrderId())%></td>
									<th>当前状态：</th>
									<td><%if(orders.getState()==1){%>待确认<%}else if(orders.getState()==2){%>待发货<%}else if(orders.getState()==3){%>出库中<%}else if(orders.getState()==4){%>已发货<%}else if(orders.getState()==5){%>已收货<%}else if(orders.getState()==0){%>已退订<%} %></td>
  							</tr>
								<tr>
									<th width="150px">收件人：</th>
									<td><%=StringUtil.notNull(orders.getReceiver())%></td>
									<th width="150px">联系电话：</th>
									<td><%=StringUtil.notNull(orders.getPhone())%></td>
								</tr>
								<tr>
									<th >收货地址：</th>
									<td colspan="3"><%=StringUtil.notNull(orders.getAddress())%></td>
									</tr>
								<tr>
									<th>快递公司：</th>
									<td><%=StringUtil.notNull(orders.getExpress())%></td>
									<th>快递单号：</th>
									<td><%=StringUtil.notNull(orders.getExpressNum())%>
									</td>
								</tr>
							
							</tbody>
						</table>
    </div>
			 <div class="p-li">
			<table class="result-tab" width="100%">
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
						 int n=0;
						 double total = 0;
								 Collection coll =  (Collection)request.getAttribute("coll");
						if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									
									while(it.hasNext()){
									OrderDetail od = (OrderDetail)it.next();
									t++;
									if(od.getType()>1) n++;
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
						}else{ %><tr><td colspan="6">暂无记录！</td></tr><%} %>
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
							<div class="p-li text-center">
                <input id="agree_btn" name="agreeb" class="button blue-sky"
									type="button" value="返回上一页" onclick="javacript:location.href='OrderServlet?method=orders';"/>
                	</div>
                </div>
             </div>
         </div> 
               </div>
               </div>
            </div>
</div>
<jsp:include page="footer.jsp" />
</body>
 <script language="JavaScript">
	function delcfm() {
		if (!confirm("确认要进行退单操作，该操作执行后将不可逆转？")) {
			window.event.returnValue = false;
		}
	}

	$(function() {

		$("#search")
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
												$('#sform').submit();
											} else {
												alert(str);
												return false;
											}
						});
	});
</script>
</html>
<%
}
%>