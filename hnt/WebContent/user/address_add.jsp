<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@ include file="check.jsp" %>  

<%
User user = (User)request.getSession().getAttribute("sys_user");
String token = (String)request.getSession().getAttribute("token");
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
    <title>新增地址</title>
    
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
                    <li ><a href="rankJoin_up_all.jsp">会员升级</a></li>
                    <%if(auth1.substring(10, 11).equals("1")){ %>
                    
                    <li><a href="UserServlet?method=getBelong">销售关系</a></li>
                    <%}if(auth1.substring(11, 12).equals("1")){ %>
                    
                    <li><a href="UserServlet?method=getReferee">服务关系</a></li>
                    <%} %>
                    <li  class="now"><a href="AddressServlet?method=list">地址管理</a></li>
                    <%if(user.getCenterId()==2){ %>
                     <li><a href="CenterServlet?method=detail">服务中心</a></li>
                     <%} %>
                </ul>
                 <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：业务管理-&gt;地址管理-&gt;<a>新增地址</a></h2>
            	</div>
            		<div class="content content-full-width">
    <form  action="AddressServlet?method=save"  id="myform" name="myform" method="post">
		   <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                	<div class="notice">
         <h2>新增收货地址</h2><span class="required_notification">* 表示必填项</span>
   </div>
  
  			 <table class ="form-table">
  			 <tr>
  			 <td width="12%"><label><span class="required">*</span>收件人：</label></td>
  			 <td ><input id="receiver" name="receiver"  type="text">&nbsp;<span id="nameTag"></span></td>
  			 </tr>
  			  <tr>
  			 <td><label><span class="required">*</span>省市县：</label></td>
  			 <td ><span class="right" id="city_2">
											<select class="prov" id="province"
												name="province"></select> <select class="city"
												id="city" name="city" disabled="disabled">
											</select> <select class="dist" id="area" name="area"
												disabled="disabled"></select> &nbsp;<span id="provinceTag"></span>
									</span></td>
  			 </tr>
  			  <tr>
  			 <td><label><span class="required">*</span>详细地址：</label></td>
  			 <td ><input  type="text"  name="address"  id="address" >&nbsp;<span id="addressTag"></span></td>
  			 </tr>
  			 <tr>
  			 <td><label><span class="required">*</span>联系电话：</label></td>
  			 <td ><input  type="text" name="phone" id="phone">&nbsp;<span id="phoneTag"></span></td>
  			 </tr>
  			 
  		</table>
              <div class="p-li" >
              <input name="button" id="sbtn" type="button" class="button blue-royal" value="提交保存" />
                	<input type="button"  id="reback"  class="button blue-sky"  value="地址列表">
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
 <script type="text/javascript" src="../js/custom.js"></script>
 <script language="JavaScript">
$(function() {
	var reg = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证
 $("#city_2").citySelect({
		nodata:"none",
		required:false
	}); 
	 $("#receiver").blur(function(){
   		 if($(this).val()=="")  $("#nameTag").text("收件人不能为空！").css({"color":"red","font-size":"12px"}); 
		else if(getByteLen($("#receiver").val())>50) $("#nameTag").text("真实姓名字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#nameTag").text("");
		
   });
   
   $("#address").blur(function(){
   		 if($(this).val()=="")  $("#addressTag").text("收件人不能为空！").css({"color":"red","font-size":"12px"}); 
		else if(getByteLen($("#address").val())>200) $("#addressTag").text("真实姓名字数不能大于100！").css({"color":"red","font-size":"12px"});
		else $("#addressTag").text("");
		
   });
   
     
   $("#phone").blur(function(){
   	 if($(this).val()!=""){
				if(!reg.test($(this).val()))
					$("#phoneTag").text("联系电话格式有误！").css({"color":"red","font-size":"12px"}); 
				else $("#phoneTag").text("");
	}
	else if(getByteLen($("#phoneTag").val())>15) $("#nameTag").text("联系电话字数不能大于15！").css({"color":"red","font-size":"12px"});
	else $("#phoneTag").text("联系电话格式有误！").css({"color":"red","font-size":"12px"});
   });
   
	
	$("#sbtn").click(function() {
	var errorstr ="";
		
			if($("#receiver").val()==""){
					$("#nameTag").text("收件人不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#nameTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#address").val()==""){
					$("#addressTag").text("收货地址不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#addressTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#phone").val()==""){
					$("#phoneTag").text("联系电话格式有误！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#phoneTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#province").val()=="") {
	   		   $("#provinceTag").text("省市县选择不完整！").css({"color":"red","font-size":"12px"});
	   			errorstr =errorstr+"1";
   		 	}else if($("#city").val()=="") {
	   		   $("#provinceTag").text("省市县选择不完整！").css({"color":"red","font-size":"12px"});
	   			errorstr =errorstr+"1";
   		 	}
   			else if($("#provinceTag").val()!="") errorstr =errorstr+"1";
		
		if(errorstr!=""){
			return false;
		}else{
       		 $('#myform').submit();
       		 return true;
		
		}

	});	
	
	$("#reback").click(function(){
     	window.location.href="AddressServlet?method=list"; 
        });
			
    });
   
  </script>      
</html>
<%}}%>