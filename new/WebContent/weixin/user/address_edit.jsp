<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.pojo.Address" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");
String token = (String)request.getSession().getAttribute("token");
Address adr = (Address)request.getAttribute("address");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}
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

<title>修改地址</title>

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
<script type="text/javascript" src="../scripts/address.js"></script>
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
        	修改地址</p><p class="go-title-en">EDIT ADDRESS
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
            <div class="contact-form no-bottom"> 
                   <form  action="AddressServlet.do?method=update"  id="myform" name="myform" method="post">
		  <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
		       <input type="hidden" name="id" id="id" value="<%=adr.getId()%>"/>
                    <fieldset>
                       
                         <div >
                            <label >收件人： &nbsp;<span id="nameTag"></span></label>
                            <input type="text" name="receiver"  class="contactField" id="receiver" value="<%=StringUtil.notNull(adr.getReceiver())%>"/>
                        </div>
                           <div>
                            <label>省市县：&nbsp;<span id="provinceTag"></span></label>
                        <span id="city_2"><select class="prov contactField" id="province" name="province">
  		</select> 
    	<select class="city contactField" id="city" name="city" disabled="disabled">
		</select>
		 <select class="dist contactField" id="area" name="area" disabled="disabled">
		</select> </span></div>
                             <div>
                            <label>详细地址： &nbsp;<span id="addressTag"></span></label>
                            <input type="text" name="address"  class="contactField" id="address" value="<%=StringUtil.notNull(adr.getAddress())%>"/>
                        </div>
                          <div>
                            <label>联系电话： &nbsp;<span id="phoneTag"></span></label>
                             <input type="text" name="phone"  class="contactField" id="phone" value="<%=StringUtil.notNull(adr.getPhone())%>"/>
                                </div>
                     	
                        <div class="formSubmitButtonErrorsWrap">
                            <input type="submit" class="buttonWrap button button-green contactSubmitButton" id="sbtn" value="提交修改" data-formId="myForm"/>
                        </div>
                    </fieldset>
                </form>       
            </div>
        </div>
    </div>
    
</div>
 <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  
<div style="height:28px;"></div>
<div class="bottom-deco"></div>

</body>
<script type="text/javascript" src="../../js/custom.js"></script>
 <script type="text/javascript">
$(function() {
	var reg = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证

	 $("#receiver").blur(function(){
   		 if($(this).val()=="")  $("#nameTag").text("收件人不能为空！").css({"color":"red","font-size":"12px"}); 
		else if(getByteLen($("#receiver").val())>50) $("#nameTag").text("真实姓名字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#nameTag").text("");
		
   });
    $("#city_2").citySelect({
		prov:"<%=StringUtil.notNull(adr.getProvince())%>", 
    	city:"<%=StringUtil.notNull(adr.getCity())%>",
		dist:"<%=StringUtil.notNull(adr.getArea())%>",
		nodata:"none"
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
		
		if(errorstr!=""){
			return false;
		}else{
       		 $('#myform').submit();
       		 return ture;
		
		}

	});	
});
</script>
</html>