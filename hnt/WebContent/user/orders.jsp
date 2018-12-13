<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.pojo.Order" %>
<%@page import="com.ssm.utils.Pager" %>

<%@ include file="check.jsp" %>  

<%
User user = (User)request.getSession().getAttribute("sys_user");
String psw2 = (String)request.getSession().getAttribute("psw2");
String state = (String)request.getAttribute("state");
String startTime = (String)request.getAttribute("startTime");
String orderType = (String)request.getAttribute("orderType");
String endTime = (String)request.getAttribute("endTime");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
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
            	<h2>当前位置：订单中心-&gt;<a>订单列表</a></h2>
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
         <h2>订单列表</h2><span class="required_notification"></span>
   </div>
 
    <div class="p-li ">
     <div class="form-table-one">
         <form  action="OrderServlet?method=orders"  id="sform" name="sform" method="post">
		   
		     <input id="id" name="id"  value='<%=user.getId() %>' type="hidden">
		
    订单类型：<select name="orderType" id="orderType" >
							<option value="">--请选择订单类型--</option>
						<option value="1" <%if(StringUtil.notNull(orderType).equals("1")){ %>selected<%} %>>报单购物</option>
						<option value="5" <%if(StringUtil.notNull(orderType).equals("5")){ %>selected<%} %>>升级购物</option>
						<option value="2" <%if(StringUtil.notNull(orderType).equals("2")){ %>selected<%} %>>零售购物</option>
						<option value="3" <%if(StringUtil.notNull(orderType).equals("3")){ %>selected<%} %>>复消购物</option>
						<option value="4" <%if(StringUtil.notNull(orderType).equals("4")){ %>selected<%} %>>责任购物</option>
						
						</select>&nbsp;订单状态<select name="state" id="state" class="common-text">
						<option value="">全部</option>
						<option value="0" <%if(StringUtil.notNull(state).equals("0")){ %>selected<%} %>>已退订</option>
						<option value="1"<%if(StringUtil.notNull(state).equals("1")){ %>selected<%} %>>待确认</option>
						<option value="2"<%if(StringUtil.notNull(state).equals("2")){ %>selected<%} %>>待配货</option>
						<option value="3"<%if(StringUtil.notNull(state).equals("3")){ %>selected<%} %>>配货中</option>
						<option value="4"<%if(StringUtil.notNull(state).equals("4")){ %>selected<%} %>>出库中</option>
						<option value="5"<%if(StringUtil.notNull(state).equals("5")){ %>selected<%} %>>已发货</option>
						<option value="6"<%if(StringUtil.notNull(state).equals("5")){ %>selected<%} %>>已收货</option>
						
						</select>&nbsp;申请时间:：
								<input name="startTime" id="startTime" 
									type="text" value='<%=StringUtil.notNull(startTime) %>' onfocus="new Calendar().show(this);">
								-
								<input name="endTime" id="endTime" 
									type="text" value='<%=StringUtil.notNull(endTime) %>' onfocus="new Calendar().show(this);">
								&nbsp;<input class="button-search blue-sky" id="search"
									name="search" value="查询" type="button"><span
									id="timeTag" style="color:red"></span>
		
			</form>	
			</div>
    </div>
			 <div class="p-li">
			<table class="result-tab" width="100%">
						<thead><tr>
							<th>订单编号</th><th>订单类型</th><th>总金额</th><th>收件人</th><th>联系电话</th><th>收货地址</th><th>订单日期</th><th>当前状态</th><th>操作</th>
						
						</tr></thead><tbody>
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
 <td><%=StringUtil.notNull(order.getOrderId()) %></td>
 <td><%if(order.getOrderType()==1){ %>报单购物<%}else if(order.getOrderType()==2){ %>零售购物
 <%}else if(order.getOrderType()==3){ %>复消购物<%}else if(order.getOrderType()==4){%>责任购物
 <%}else if(order.getOrderType()==5){%>升级购物 <%}else if(order.getOrderType()==6){%>创业购物<%}else{%>-<%} %></td>
 <td><%=StringUtil.decimalFormat(order.getPrice()) %>
    </td>
 <td><%=StringUtil.notNull(order.getReceiver()) %></td>
  <td><%=StringUtil.notNull(order.getPhone()) %></td>
   <td><%=StringUtil.notNull(order.getAddress()) %></td>
 <td><%=StringUtil.parseToString(order.getOrderTime(), DateUtil.yyyyMMddHHmmss)%></td>
 	<td><%if(order.getState()==1){%>待确认<%}else if(order.getState()==2){%>待配货<%}else if(order.getState()==3){%>配货中<%}else if(order.getState()==4){%>出库中<%}else if(order.getState()==5){%>已发货<%}else if(order.getState()==6){%>已收货<%} %></td>
  							<td><a href="OrderServlet?method=order_detail&id=<%=order.getOrderId()%>">详情</a>
  							<%if(order.getState()==1){%><a href='OrderServlet?method=order_adr_edit&id=<%=order.getOrderId()%>'>&nbsp;|&nbsp;修改地址</a><%} %>
  </td>
 </tr>
	 	<%}}
						}else{ %><tr><td colspan="9">暂无记录！</td></tr><%} %>
					</table>
				  <div class="pager">
				   <% if(pager!=null){ %>
				  <span class="left">
                   <form name="pageSizeForm"  action="OrderServlet?method=orders" method="post" class="add_form">
							<input type="hidden" name="pageNoStr" value="1"/>
							<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
							<input type="hidden" name="orderType" value="<%=StringUtil.notNull(orderType)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					当前每页显示<select name="pageSizeStr" id="pageSizeStr" onchange="javascript:pageSizeForm.submit();">
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
					</span><span class="right">
			<form name="pageNoForm"  action="OrderServlet?method=orders" method="post">
						<input type="hidden" name="pageSizeStr" value="<%=pager.getPageSize()%>"/>
							<input type="hidden" name="state" value="<%=StringUtil.notNull(state)%>"/>
							<input type="hidden" name="orderType" value="<%=StringUtil.notNull(orderType)%>"/>
							<input type="hidden" name="startTime" value="<%=StringUtil.notNull(startTime)%>"/>
							<input type="hidden" name="endTime" value="<%=StringUtil.notNull(endTime)%>"/>
					第<font color="red"><%=pager.getPageNo()%>
					</font>页&nbsp; 共<font color="red"><%=pager.getPageCount()%></font>页共<font color="red"><%=pager.getRowCount()%></font>条记录&nbsp;
					<%if(pager.getPageNo()==1) {%> 首页 | 上一页  |<%}else{%>
					<a
						href="OrderServlet?method=orders&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&state=<%=StringUtil.notNull(state)%>&orderType=<%=StringUtil.notNull(orderType)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">首页</a> | <a
						href="OrderServlet?method=orders&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&state=<%=StringUtil.notNull(state)%>&orderType=<%=StringUtil.notNull(orderType)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="OrderServlet?method=orders&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&state=<%=StringUtil.notNull(state)%>&orderType=<%=StringUtil.notNull(orderType)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">
                    下一页</a> | <a
						href="OrderServlet?method=orders&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&state=<%=StringUtil.notNull(state)%>&orderType=<%=StringUtil.notNull(orderType)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>">尾页</a>
				<%}else{ %>下一页 | 尾页<%} %>&nbsp; 跳转到第<select
						name="pageNoStr" onchange="javascript:pageNoForm.submit();">
						<%for(int i=0;i<pager.getPageCount();i++){ %>
							<option value="<%=i+1 %>" <%if(pager.getPageNo()==(i+1)){%>selected<%} %>>
								<%=i+1 %>
							</option>
							<%} %>
					</select>页
						</form>
					</span>
                <%} %>
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