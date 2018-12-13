<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil"%>
<%@page import="com.ssm.pojo.Product" %>
<%@ include file="check.jsp" %>  

<%
User user = (User)request.getSession().getAttribute("sys_user");
String type = StringUtil.notNull((String)request.getAttribute("type"));
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>健康产品</title>
    
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
<script src="js/jquery.slider.js"></script>
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
            	<li <%if(type.equals("1")){%>class="now"<%} %>><a href="ProductServlet?method=product_index&type=1">全部产品</a></li> 
				<li <%if(type.equals("2")){%>class="now"<%} %>><a href="ProductServlet?method=product_index&type=2">报单专区</a></li> 
				<li <%if(type.equals("3")){%>class="now"<%} %>><a   href="ProductServlet?method=product_index&type=3">复消专区</a></li>
          		  
                </ul>
                <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：健康产品-&gt;<%if(type.equals("1")){%><a>全部产品</a>
            	<%}else if(type.equals("2")){ %><a>报单专区</a>
            	<%}if(type.equals("3")){%><a>复消专区</a><%} %></h2>
            	</div>
            			
            		<div class="content content-full-width">
  				
                	<div class="notice">
         <h2><%if(type.equals("1")){%>全部产品
            	<%}else if(type.equals("2")){ %>报单产品
            	<%}if(type.equals("3")){%>复消产品<%} %></h2><span class="required_notification"></span>
   </div>
 
   <div class="box_ap">
    <div class="box_gd">
                <ul>
                <%
							Collection coll_pro =(Collection)request.getAttribute("coll");
							if(coll_pro!=null){
									Iterator it = coll_pro.iterator();
									int t =0;
									while(it.hasNext()){
									Product p = (Product)it.next();
									if(p.getIsHide()==1){
									t++;
							%>
                	<li><img alt="" src="../upload/<%=p.getImageUrl()%>">
                	<br>
                	<h4><%if(p.getProductName().length()<=10){ %><%=p.getProductName()%><%}else{ %>
                    	<%=StringUtil.notNull(p.getProductName().substring(0, 10))%>...<%} %></h4>
                	<h4>专柜价：¥&nbsp;<%=StringUtil.decimalFormat(p.getPrice()) %><span><a href="ProductServlet?method=product_detail&id=<%=p.getProductId()%>">详情</a></span></h4>
                	</li>
                	<%}}} %>
                	
                </ul>
                <p>&nbsp;</p>
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