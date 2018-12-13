<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Order" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../images/splash/splash-icon.png">
<link rel="apple-touch-startup-image" href="../images/splash/splash-screen.png" 			media="screen and (max-device-width: 320px)" />  
<link rel="apple-touch-startup-image" href="../images/splash/splash-screen_402x.png" 		media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" /> 
<link rel="apple-touch-startup-image" sizes="640x1096" href="../images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="../images/splash/splash-screen-ipad-landscape" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004" href="../images/splash/splash-screen-ipad-portrait.png" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="../images/splash/splash-screen-ipad-portrait-retina.png"   media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
<link rel="apple-touch-startup-image" sizes="1496x2048" href="../images/splash/splash-screen-ipad-landscape-retina.png"   media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>

<title>订单明细</title>

<link href="../styles/style.css"     		rel="stylesheet" type="text/css">
<link href="../styles/framework.css" 		rel="stylesheet" type="text/css">
<link href="../styles/owl.carousel.css" 	 rel="stylesheet" type="text/css">
<link href="../styles/owl.theme.css" 		rel="stylesheet" type="text/css">
<link href="../styles/swipebox.css"		 rel="stylesheet" type="text/css">
<link href="../styles/colorbox.css"		 rel="stylesheet" type="text/css">


<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jqueryui.js"></script>
<script type="text/javascript" src="../scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="../scripts/colorbox.js"></script>
<script type="text/javascript" src="../scripts/snap.js"></script>
<script type="text/javascript" src="../scripts/contact.js"></script>
<script type="text/javascript" src="../scripts/custom.js"></script>
<script type="text/javascript" src="../scripts/framework.js"></script>
<script type="text/javascript" src="../scripts/framework.launcher.js"></script>
<script type="text/javascript">
function delcfm() {
		if (!confirm("确认要退订？")) {
			return false;
		}
	}
	
	function yescfm() {
		if (!confirm("确认收货？")) {
			return false;
		}
	}
</script>
</head>
<body>

 <%@ include file="header.jsp" %>  

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	订单明细</p><p class="go-title-en">ORDER DETAIL
        </p>
         <a href="javascript:history.go(-1);" class="go-logo">返回</a>
        <a href="main.jsp" class="go-home">首页</a>
        <a href="#" class="go-menu">菜单</a>
        <a href="#" class="go-back">关闭</a>
    </div>
    <div class="decoration"></div>
    <div class="navigation">
    	<div class="corner-deco"></div>
    	<div class="navigation-wrapper">
          <%@ include file="menu.jsp" %>  
        </div>
    </div>
</div>
<div class="content">
    <div class="container">
    <div class="container no-bottom">
       <table  class="table-detail">
            <tr>
                <th>订单编号/(类型／订单状态)</th>
                <th>订单金额/申请时间</th>
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
									Order order = (Order)it.next();
									t++;
							%>
            <tr>
                <td><%=StringUtil.notNull(order.getOrderId()) %>
                <br><%if(order.getOrderType()==1){ %>加盟购物<%}else if(order.getOrderType()==2){ %>升级购物<%}else if(order.getOrderType()==3){ %>再次购物<%}else{%>-<%} %>/<%if(order.getState()==1){%>待确认<%}else if(order.getState()==2){%>待发货<%}else if(order.getState()==3){%>出库中<%}else if(order.getState()==4){%>已发货<%}else if(order.getState()==5){%>已收货<%}else if(order.getState()==0){%>已退订<%} %></td>
                <td><%=StringUtil.decimalFormat(order.getPrice()) %><br><%=StringUtil.parseToString(order.getOrderTime(), DateUtil.yyyyMMddHHmmss)%></td>
                <td><a href="OrderServlet.do?method=order_detail&id=<%=order.getOrderId()%>">详情</a>
               <%if(order.getOrderType()==3&&order.getState()==1){ %>| <a href="OrderServlet.do?method=order_back&id=<%=order.getOrderId()%>" onclick="javascript:if(!confirm('是否确定要删除该订单？')) return false;">退单</a><%} %></td>
            </tr>
           	<%}}
						}else{ %><tr><td colspan="3">暂无交易记录！</td></tr><%} %>
        </table>
        </div>
   </div>      
  <% if(pager!=null){ %>
     <div class="one-half-responsive">
        <div class="container">
       <%if(pager.getPageNo()!=1){ %>
      <a href="OrderServlet.do?method=orders&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1&state=<%=StringUtil.notNull(state)%>&orderType=<%=StringUtil.notNull(orderType)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>" class="button-icon icon-home button-orange">首页</a>
        
       <a href="OrderServlet.do?method=orders&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>&state=<%=StringUtil.notNull(state)%>&orderType=<%=StringUtil.notNull(orderType)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>" class="button-icon icon-left button-blue">上一页</a>
        <%} if(pager.getPageNo()!=pager.getPageCount()){ %>
   		 <a
						href="OrderServlet.do?method=orders&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>
						&state=<%=StringUtil.notNull(state)%>&orderType=<%=StringUtil.notNull(orderType)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>" class="button-icon icon-right button-green">下一页</a>
       <a href="OrderServlet.do?method=orders&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>&state=<%=StringUtil.notNull(state)%>&orderType=<%=StringUtil.notNull(orderType)%>&startTime=<%=StringUtil.notNull(startTime)%>&endTime=<%=StringUtil.notNull(endTime)%>" class="button-icon icon-end button-magenta">尾页</a><%} %>
      </div>
      </div>
      <%} %>
       <div class="decoration"></div>
        <div class="container no-bottom">
    <p>温馨提示：如上述明细没有包含当日最新发生明细，请您稍后重查。</p>
      </div>
     
</div>
 <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  
<div style="height:28px;"></div>
<div class="bottom-deco"></div>

<script>
function delcfm() {
		if (!confirm("确认要进行退单操作，该操作执行后将不可逆转？")) {
			return false;
		}
	}

	$(function() {

		$("#batchDel").click(function() {

			if (!confirm("确认要删除？")) {
				return false;
			} else {
				$('#delform').attr("action", "apply_deleteAll.action");
				$('#delform').submit();
				return true;
			}

		});
		
		$("#userBack").click(function() {

			if (!confirm("是否要进行退单操作？")) {
				window.event.returnValue = false;
			} else {
				$('#delform').attr("action", "user_back.action");
				$('#delform').submit();
				window.event.returnValue = true;
			}

		});

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
</body>
</html>
<%}%>