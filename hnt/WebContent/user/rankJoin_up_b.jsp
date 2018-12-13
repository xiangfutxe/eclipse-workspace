<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.StringUtil"%>
<%@ page import="com.ssm.utils.DateUtil"%>
<%@page import="com.ssm.pojo.Product"%>
<%@page import="com.ssm.pojo.Address"%>
<%@page import="com.utils.MD5"%>
<%
User user = (User)request.getSession().getAttribute("sys_user");
String psw2 = (String)request.getSession().getAttribute("psw2");
String token = (String)request.getSession().getAttribute("token");

User user1 = (User)request.getSession().getAttribute("user1");

	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String auth1 = StringUtil.notNull(user.getAuthority());
	
	 if(auth1.length()<30){
		out.println("<script>");
		out.println("alert('会员权限信息获取失败，请重新登陆');parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>会员升级</title>
    
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
          		  <li><a href="user_add_protocol.jsp">会员注册</a></li>
                    <li   class="now"><a href="rankJoin_up_all.jsp">会员升级</a></li>
                    <%if(auth1.substring(10, 11).equals("1")){ %>
                    <li><a href="UserServlet?method=getBelong">销售关系</a></li>
                    <%}if(auth1.substring(11, 12).equals("1")){ %>
                    <li><a href="UserServlet?method=getReferee">服务关系</a></li>
                    <%} %>
                    <li><a href="AddressServlet?method=list">地址管理</a></li>
                    <%if(user.getCenterId()==2){ %>
                     <li><a href="CenterServlet?method=detail">服务中心</a></li>
                     <%} %>
                </ul>
                <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：业务管理-&gt;会员升级-&gt;<a>产品选择</a></h2>
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
         <form  action="UserServlet?method=rankJoin_up_b"  id="myform" name="myform" method="post">
       <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
            <input type="hidden" name="emoney" id="emoney" value="<%=user1.getEmoney() %>"/>
		      <input type="hidden" name="dmoney" id="dmoney" value="<%=user1.getDmoney() %>"/>
		      <input type="hidden" name="rankJoin" id="rankJoin" value="<%=user1.getRankJoin() %>"/>
		       <input type="hidden" name="rankJoinOld" id="rankJoinOld" value="<%=user1.getRankJoinOld() %>"/>
		       <input type="hidden" name="userId" value='<%=user1.getUserId()%>'/>
		        <input type="hidden" name="declarationId" value='<%=user.getUserId()%>'/>
                	<div class="notice">
         <h2>会员升级</h2><span class="required_notification">* 表示必填项</span>
   </div>
  <div class="p-li">
  		备注：你所选会员等级升级为<%if(user1.getRankJoin()==1){ %>银卡会员<%}else if(user1.getRankJoin()==2){ %>金卡会员
			<%}else if(user1.getRankJoin()==3){ %>白金卡会员<%}else if(user1.getRankJoin()==4){ %>VIP会员<%}else if(user1.getRankJoin()==5){ %>至尊会员<%} %>，需要选购<%=user1.getEmoney()%>元(含)以上的产品。
  </div>
  <div class="p-li">
  收件人信息：
				<%	
							Collection coll_adr =  (Collection)request.getAttribute("coll_adr");
							if(coll_adr!=null){
							%>
			<select  name="addressId" id="addressId">
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
									未设置收货地址，请在<a href="AddressServlet?method=list">地址管理</a>中设置好地址信息！<%} %><span id="addressTag"></span></span>
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
  			 <table class="result-tab" width="98%">
  			 <tr>
									<th>产品编码</th>
									<th>产品名称</th>
									<th>规格</th>
										<th>类型</th>
									<th>单价</th>
									<th>产品PV</th>
									<th>购买数量</th>
								</tr><tbody>
					<%
							Collection coll =  (Collection)request.getAttribute("coll");
							if(coll!=null){
									Iterator it = coll.iterator();
									int i=0;
									while(it.hasNext()){
									Product pt = (Product)it.next();
									i++;
							%>
									<tr <%if(i%2==1){ %>class="even"<%} %>>
										<td><%if(pt.getType()==2){ %><a href="ProductServlet?method=product_detail&id=<%=pt.getProductId() %>" target="_blank"><%=pt.getProductId() %></a>
										<%}else{ %><%=pt.getProductId() %><%} %></td>
										<td><%=pt.getProductName() %>
										</td>
										<td><%=pt.getSpecification() %>
										</td>
										
											<td><%if(pt.getType()==2){ %><a href="ProductServlet?method=product_detail&id=<%=StringUtil.notNull(pt.getProductId())%>" title="点击查看产品清单" title="点击查看产品清单" target="_blank"><font style="font-weight:bold">套餐</font></a><%}else if(pt.getType()==1) {%>单品<%}else{ %>-<%} %>
										<td><%=pt.getPrice() %><input type="hidden"
											name="pricestr" value='<%=pt.getPrice()%>'><input
											type="hidden" name="pvstr" value='<%=pt.getPv()%>'>
										</td>
										<td><%=pt.getPv() %>
										</td>
										<td><input type="text" name='numstr' size="5"; value="0"><input
											type="hidden" name="pid" value='<%=pt.getId()%>'>
										</td>
									</tr>
								<%}
						}else{ %><tr><td colspan="8">暂无产品，请联系商家添加产品！</td></tr> <%} %>
								<tr class="even">
									
							<td colspan="5"><span id="msg"></span></td>
									<td>合计</td>
									<td><span id="totalPrice"></span><input type="hidden" id="tprice"/></td>
								</tr>
				  </tbody>	</table>
  			   <div class="p-li text-center">
                <input id="btn1" class="button orange"
									value="计算金额" type="button"/> <input id="btn2" class="button blue-sky"
									value="保存结算" type="button"/> <input type="button"
									class="button blue-light" onclick="history.go(-1)" value="返回">
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
	$(function() {
	var reg = /^[1-9]+[0-9]*]*$/;
	
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
					userId : $("input[name=declarationId]").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					var $num = $("input[name=numstr]");
					var $price = $("input[name=pricestr]");
					var $pv = $("input[name=pvstr]");
					var emoney = $('#emoney').val();
					var dmoney = $('#dmoney').val();
					var rankJoin = $('#rankJoin').val();
					var totalprice = 0;
					var totalpv = 0;
					
					var error=0;
					for ( var i = 0; i < $num.size(); i++) {
						var n = 0;
						var p = 0;
						var v = 0;
						if ($num[i].value == ""||$num[i].value == 0){
							n = 0;
						}
						else if(!reg.test($num[i].value)){
						$("#msg").text("数值格式有误，请重试！").css({"color":"red","font-size":"12px"});
							error++;
							break;
						}
						else{
							n = $num[i].value;
							$("#msg").text("");
							}
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
					if(error >0)	$("#msg").text("产品数量的格式有误，请重新核对！").css({"color":"red","font-size":"12px"});
					else if (d.emoney-totalprice<0) $("#msg").text("当前账户报单券余额为"+d.emoney+"元，不足以选购当前等级会员！").css({"color":"red","font-size":"12px"});
					else if (emoney > totalprice) $("#msg").text("您选购的产品与你所选会员等级标准不符合！").css({"color":"red","font-size":"12px"});
					else {$("#msg").text("");
					$("#totalPrice").text(totalprice + "元").css({"color":"green","font-size":"12px"});
					$("#tprice").val(totalprice);
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
			return  false;
		}else{
			var error = 0;
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
				error++;
			}else if($("#addressTag").text()!=""){
			error++;
			
			}
			else if($("#tprice").val()==""){
				$("#msg").text("请先计算金额后再保存并结算！").css({"color":"red","font-size":"12px"});
				
				error++;
			}else if(totalprice<=0){
				$("#msg").text("产品金额总额不能为零！").css({"color":"red","font-size":"12px"});
				error++;
			}else if($("#tprice").val()!=totalprice){
				$("#msg").text("产品数量有变，请重新计算金额后再保存并结算！").css({"color":"red","font-size":"12px"});
				
				error++;
			}else if($("#addressId").val()==0||$("#addressId").val()==""){
				$("#msg").text("未选择收货地址信息！").css({"color":"red","font-size":"12px"});
				error++;
			}
			
				if($("#centerId").val()==""){
					$("#centerIdTag").text("服务中心不能为空！").css({"color":"red","font-size":"9px"});
					$("#centerIdMsg").text("");
					error++;
					 $("#centerId").focus();
				}else if($("#centerIdMsg").text()==""||$("#centerIdTag").text()!=""){
					error++;
					 $("#centerId").focus();
				}
			
			$("input[name='numstr']").each(function(){
				if($(this).val()=="")$(this).test("0");
			});
			if(error>0){
				return false;
			}else{
	       		 $("#myform").submit();
	       		 return true;
		
		}
		}
		});
		
	});
	</script>
</html>
<%
}}
%>