<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Product" %>
<%@page import="com.pojo.Address" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User new_user = (User)request.getSession().getAttribute("new_user");
	if (new_user == null) {
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

<title>产品选择</title>

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
<script type="text/javascript" src="../scripts/jquery.cityselect.js"></script>
</head>
<body>

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	产品选择</p><p class="go-title-en">SELECT PRODUCT
        </p>
         <a href="login.jsp" class="go-home">登陆</a>
         <a href="javascript:history.go(-1);" class="go-logo-1">返回</a>
       
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
    
     <form  action="UserServlet.do?method=user_save_product"  id="myform" name="myform" method="post">
		   <input type="hidden" name="emoney" id="emoney" value="<%=new_user.getEmoney() %>"/>
		      <input type="hidden" name="rankJoin" id="rankJoin" value="<%=new_user.getRankJoin() %>"/>
		    <div class="container no-bottom">
			 <p>备注：你所选会员等级为<%if(new_user.getRankJoin()==1){ %>银级代理商<%}else if(new_user.getRankJoin()==2){ %>金级代理商
			<%}else if(new_user.getRankJoin()==3){ %>钻级代理商<%} %>，需要选购<a><%=new_user.getEmoney()%></a>元(含)以上的产品。
			</p></div>
			
			<div class="container no-bottom">
      	 <table  class="table-detail">
            <tr>
                <th>产品编码/产品名称</th>
                <th>规格/类型</th>
                <th>单价</th>
                 <th>购买数量</th>
            </tr>
           
					<%
							Collection coll =  (Collection)request.getAttribute("coll");
							if(coll!=null){
									Iterator it = coll.iterator();
									int i=0;
									while(it.hasNext()){
									Product pt = (Product)it.next();
									i++;
							%>
									<tr>
										<td><%=pt.getProductId() %><br><%=pt.getProductName() %>
										</td>
										<td><%=pt.getSpecification() %>
										<br><%if(pt.getType()==2){ %>套餐<%}else if(pt.getType()==1) {%>单品<%}else{ %>-<%} %>
										<td><%=pt.getPrice() %><input type="hidden"
											name="pricestr" value='<%=pt.getPrice()%>'>
										
										</td>
										<td><input type="text" name='numstr' value="0" class="input_num"><input
											type="hidden" name="pid" value='<%=pt.getId()%>'>
										</td>
									</tr>
								<%}
						} %>
						<tr>
						<td colspan="2"><span id="msg"></span></td>
									<td>合计</td>
									<td><span id="totalPrice"></span><input type="hidden" id="tprice"/></td>
								</tr>
        </table>
         </div>
          <div class="container">
          <p><span id="addressTag"></span>
			</p>
				
            <a id="btn1" class="button button-blue">计算金额</a>
             <a id="btn2"  class="button button-blue">保存结算</a>
              <a href="" onclick="javascript:histroy.go(-1);" class="button button-blue">返回上一页</a>
        </div>
        </form>
   </div>      
  </div>
      <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  
<div style="height:28px;"></div>
<div class="bottom-deco"></div>
     


<script type="text/javascript">
  
	/* 页面加载完成，绑定事件 */
	$(function() {
	var reg = /^[1-9]+[0-9]*]*$/;
	
	
		$("#btn1").bind("click", function() {
			$.ajax({
				type : "post",
				url : "UserServlet.do?method=emoneyAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
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
					var emoney = $('#emoney').val();
					var totalprice = 0;
					var error=0;
					for ( var i = 0; i < $num.size(); i++) {
						var n = 0;
						var p = 0;
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
					
						totalprice = totalprice + n * p;
					}
				
					 if(error >0)	$("#msg").text("产品数量的格式有误，请重新核对！").css({"color":"red","font-size":"12px"});
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
		if (!confirm("确认保存选择的产品信息。")) {
			return  false;
		}else{
			var error = 0;
			var $num = $("input[name=numstr]");
					var $price = $("input[name=pricestr]");
				
					
					var totalprice = 0;
					
					for ( var i = 0; i < $num.size(); i++) {
						var n = 0;
						var p = 0;
						if ($num[i].value == "")
							n = 0;
						else
							n = $num[i].value;
						if ($price[i].value == "")
							p = 0;
						else
							p = $price[i].value;
					
						totalprice = totalprice + n * p;
						
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
</body>
</html>
<%}%>