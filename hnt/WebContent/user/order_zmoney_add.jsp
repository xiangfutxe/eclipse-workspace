<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil"%>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Product" %>
<%@page import="com.ssm.pojo.Address" %>
<%@ include file="check.jsp" %>  

<%
User user = (User)request.getSession().getAttribute("sys_user");
User user1 = (User)request.getAttribute("user1");
String message = (String) request.getAttribute("message");
Double dis = (Double) request.getAttribute("discount");
double discount =1;
if(dis!=null) discount =dis;
String psw2 = (String)request.getSession().getAttribute("psw2");
String token = (String)request.getSession().getAttribute("token");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
		if(user1!=null){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>责任购物</title>
    
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
<script type="text/javascript" src="js/psw2.js"></script>
<script src="js/jquery.slider.js"></script>
<link rel="stylesheet" href="fonts/fontCss.css">
	<link rel="stylesheet" href="css/jquery.slider.css">
	      <script type="text/javascript" src="js/jquery.cityselect.js"></script>
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
                  <li><a href="OrderServlet?method=orders">订单列表</a></li> 
				<li><a href="OrderServlet?method=order_divery">服务订单</a></li> 
				<li class="now"><a  href="OrderServlet?method=order_emoney_add">零售购物</a></li>
				<li><a href="OrderServlet?method=order_dmoney_add">复消购物</a></li>
				<li><a href="OrderServlet?method=order_zmoney_add">责任购物</a></li>
                </ul>
                                 <div style="height:300px;"></div>
                
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：订单中心-&gt;<a>责任购物</a></h2>
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
            		  <form  action="OrderServlet?method=order_zmoney_save"  id="myform" name="myform" method="post">
		    <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
		    <input type="hidden" name="userId" id="userId" value="<%=StringUtil.notNull(user1.getUserId())%>"/> 
             		    <input type="hidden" name="discount" id="discount" value="<%=discount%>"/> 
             
                	<div class="notice">
         <h2>责任购物</h2><span class="required_notification"></span>
   </div>
      <div class="p-li"><p><span style="color:red">*运费说明：自4月16日起，小于200元订单，运费到付！</span></div>
   
							<div class="p-li">
							<div class="form-table">
							会员信息：<%=user1.getUserName() %>(<%=user1.getUserId() %>)&nbsp;&nbsp;
							
								收件人信息：
				<%	
							Collection coll_adr =  (Collection)request.getAttribute("coll_adr");
							if(coll_adr!=null){
							%>
			<select name="addressId" id="addressId">
									<option value="">--请选择收货地址--</option>
								<%
									Iterator it1 = coll_adr.iterator();
									int i=0;
									while(it1.hasNext()){
									Address adr = (Address)it1.next();
									i++;
							%>
									<option value='<%=adr.getId()%>'><%=adr.getReceiver() %>&nbsp;|&nbsp;<%=adr.getPhone()%>&nbsp;|&nbsp;<%=adr.getAddress()%></option>
									<%} %></select><%}else{ %>
									未设置收货地址，请在<a href="AddressServlet.do?method=address">地址管理</a>中设置好地址信息！<%} %><span id="addressTag"></span>
								</div>
								</div>
								<div class="p-li">
								<div class="form-table">服务中心：<input style="width:200px;" maxlength="50" id="centerId" name="centerId"
								value='' type="text">&nbsp;<span
								id="centerIdTag"></span>&nbsp;<span
								id="centerIdMsg"></span>&nbsp;	
									</div>
									</div>
							<div class="p-li">
			<table class="result-tab" width="100%">
						<thead><tr>
									<th>产品编码</th>
									<th>产品名称</th>
									<th>规格</th>
									<th>分类</th>
										<th>类型</th>
									<th>单价</th>
									<th>产品PV</th>
									<th>购买数量</th>
								</tr></thead><tbody>
					<%
							List<Product> coll =  (List<Product>)request.getAttribute("coll");
							if(coll!=null){
									for(int i=0;i<coll.size();i++){
									Product pt = (Product)coll.get(i);
							%>
									<tr <%if((i+1)%2==1){ %>class="even"<%} %>>
										<td><%if(pt.getType()==2){ %><a href="ProductServlet?method=product_detail&id=<%=pt.getProductId() %>" target="_blank"><%=pt.getProductId() %></a>
										<%}else{ %><%=pt.getProductId() %><%} %></td>
										<td><%=pt.getProductName() %>
										</td>
										<td><%=pt.getSpecification() %>
										</td>
										<td><%=pt.getProductType() %>
										</td>
											<td><%if(pt.getType()==2){ %>套餐<%}else if(pt.getType()==1) {%>单品<%}else{ %>-<%} %>
										<td><%=pt.getPrice() %><input type="hidden"
											name="pricestr" value='<%=pt.getPrice()%>'><input
											type="hidden" name="pvstr" value='<%=pt.getPv()%>'>
										</td>
										<td><%=pt.getPv() %>
										</td>
										<td><input type="text" name='numstr' size="8" value="0"><input
											type="hidden" name="pid" value='<%=pt.getId()%>'>
										</td>
									</tr>
								<%}
						}else{ %><tr><td colspan="8">暂无产品，请联系商家添加产品！</td></tr> <%} %>
								<tr class="even">
									
							<td colspan="6"><span id="msg"></span></td>
									<td>合计</td>
									<td><span id="totalPrice"></span><input type="hidden" id="tprice"/></td>
								</tr>
								<tr class="even">
									
							<td colspan="6"></td>
									<td>折扣价</td>
									<td><span id="totalPriceDiscount"></span><input type="hidden" id="tpriceDiscount"/></td>
								</tr>
				  </tbody>	</table>
				  </div>
              <div class="p-li  text-center">
                	 <input type="button"  id="btn1"  class="button orange" value="计算金额">
                	<input type="button"  id="btn2"  class="button blue-sky" value="提交订单">
                	</div>
					</form>
					</div>
							</div>
  </div>
					</div>
         </div> 
               </div>
<jsp:include page="footer.jsp" />
</body>
<script type="text/javascript">
  
	/* 页面加载完成，绑定事件 */
	$(document).ready(function() {
	var reg = /^[0-9]*]*$/;
	
	$("#addressId").blur(function(){
		if($(this).val()=="") $("#addressTag").text("未选择收货信息！").css({"color":"red","font-size":"9px"});
		else  $("#addressTag").text("");
	});
	
	 $("#centerId").blur(function(){
  	 	$.ajax({
  	 		type:"post",
  	 		url:"CenterServlet?method=centerIdAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
  	 		data:{//设置数据源
						centerId:$("input[name=centerId]").val()//这里不要加","  不然会报错，而且根本不会提示错误地方
					
				},
 			dataType:"json",//设置需要返回的数据类型

			success:function(data){
						var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
						//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
						if(d.userName==""){
						 $("#centerIdTag").text("查无此服务中心。").css({"color":"red","font-size":"9px"});
						  $("#centerIdMsg").text("");
						 }
						else{
							 $("#centerIdMsg").text(""+d.userName+"").css({"color":"green","font-size":"9px"});
						  	$("#centerIdTag").text("");
						 }
                    },

                    error:function(){
                    
                        alert("系统异常，请稍后重试！");

                    }//这里不要加","
  	 	});
  	 });
  	 
	
		$("#btn1").bind("click", function() {
			$.ajax({
				type : "post",
				url : "UserServlet?method=moneyAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					userId : $("input[name=userId]").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},
				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					var $num = $("input[name=numstr]");
					var $price = $("input[name=pricestr]");
					var $pv = $("input[name=pvstr]");
					var discount = $("#discount").val();
					var totalprice = 0;
					var totalpv = 0;
					var err=0;
					for ( var i = 0; i < $num.size(); i++) {
						var n = 0;
						var p = 0;
						var v = 0;
						if ($num[i].value == ""||$num[i].value == 0){
							n = 0;
						}
						else if(!reg.test($num[i].value)){
							err =1;
							n=0;
						}
						else
							n = $num[i].value;
						if ($price[i].value == "")
							p = 0;
						else
							p = $price[i].value;
						if ($pv[i].value == "")
							v = 0;
						else
							v = $pv[i].value;
						totalprice = totalprice + n * p;
						totalpv = totalpv+n*v;
					}
					if(err ==1)	$("#msg").text("产品数量的格式有误，请重新核对！").css({"color":"red","font-size":"9px"});
					else if (d.emoney-totalprice*discount<0) $("#msg").text("当前账户余额为"+d.emoney+"元，账户余额不足，请重新选购！").css({"color":"red","font-size":"9px"});
					else {$("#msg").text("");

					$("#totalPrice").text(totalprice + "元").css({"color":"green","font-size":"9px"});
					$("#tprice").val(totalprice);
					$("#totalPriceDiscount").text(Math.round(totalprice*discount*100)/100 + "元").css({"color":"green","font-size":"9px"});
					$("#tpriceDiscount").val(Math.round(totalprice*discount*100)/100);
					
					}
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
			});

		});
		
		$("input[name='numstr']").each(function(){
			if($(this).val()=="")$(this).test("0");
			});
		
		$("#btn2").bind("click", function() {
		if (!confirm("确认保存并结算？提交后将会在你对应账户扣除金额！")) {
			window.event.returnValue = false;
		}else{
			var error = "";
			var $num = $("input[name=numstr]");
					var $price = $("input[name=pricestr]");
					var $pv = $("input[name=pvstr]");
					var totalprice = 0;
					var totalpv = 0;
					for ( var i = 0; i < $num.size(); i++) {
						var n = 0;
						var p = 0;
						var v = 0;
						if ($num[i].value == "")
							n = 0;
						else
							n = $num[i].value;
						if ($price[i].value == "")
							p = 0;
						else
							p = $price[i].value;
						if ($pv[i].value == "")
							v = 0;
						else
							v = $pv[i].value;
						totalprice = totalprice + n * p;
						totalpv = totalpv+n*v;
					}
			if($("#msg").text()!="") {
				error = error +$("#msg").text()+"\n";
			}else if($("#tprice").val()==""){
				$("#msg").text("请先计算金额后再保存并结算！").css({"color":"red","font-size":"9px"});
				
				error = error +$("#msg").text()+"\n";
			}else if(totalprice<=0){
				$("#msg").text("产品金额总额不能为零！").css({"color":"red","font-size":"9px"});
				error = error +$("#msg").text()+"\n";
			}else if($("#tprice").val()!=totalprice){
				$("#msg").text("产品数量有变，请重新计算金额后再保存并结算！").css({"color":"red","font-size":"9px"});
				error = error +$("#msg").text()+"\n";
			}else if($("#addressId").val()==0||$("#addressId").val()==""){
				$("#msg").text("未选择收货地址信息！").css({"color":"red","font-size":"9px"});
				
				error = error +$("#msg").text()+"\n";
			}
			
			if($("#centerId").val()==""){
					$("#centerIdTag").text("服务中心不能为空！").css({"color":"red","font-size":"9px"});
					$("#centerIdMsg").text("");
					error = error +$("#centerIdTag").text()+"\n";
					 $("#centerId").focus();
				}else if($("#centerIdMsg").text()==""||$("#centerIdTag").text()!=""){
					error = error +$("#centerIdTag").text()+"\n";
					 $("#centerId").focus();
				}
				
			$("input[name='numstr']").each(function(){
			if($(this).val()=="")$(this).test("0");
			});
			
			if(error!=""){
			alert(error);
				return false;
			}else{
			
			if($("#tpriceDiscount").val()<200){
			if (confirm("自4月16日起，订单金额小于200，运费到付！")) {
	       		 $('#myform').submit();
	       		 return true;
	       		 }else return false;
	       	 }else{
	       	  	$('#myform').submit();
	       		 return true;
	       	 }
		
		}
		}
		});
		
	});
	</script>
</html>
<%}else{
		out.println("<script>");
		out.println("parent.window.location.href='order_zmoney_message.jsp';");
		out.println("</script>");
}
}%>