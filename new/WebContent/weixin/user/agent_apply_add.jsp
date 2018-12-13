<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

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

<title>加盟我们</title>

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
        	加盟我们</p><p class="go-title-en">JOIN US
        </p>
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
                <form  action="AgentApplyServlet.do?method=save"  id="myform" name="myform" method="post">
                    <fieldset>
                       
                         <div>
                            <label>会员编号： &nbsp;&nbsp;<span id="nameTag"></span></label>
                            <input type="text" name="userId" value="" class="contactField" id="userId"/>
                        </div>
                         <div>
                            <label>证件类型： &nbsp;&nbsp;<span id="documentTypeTag"></span></label>
                           
                           <select class="contactField" id="documentType" name="documentType">
<option value="居民身份证">居民身份证</option>
<option value="军官证">军官证</option>
<option value="武警警官证">武警警官证</option>
<option value="士兵证">士兵证</option>
<option value="中华人民共和国来往港澳通行证">中华人民共和国来往港澳通行证</option>
<option value="港澳同胞回乡证">港澳同胞回乡证</option>
<option value="港澳居民来往内地通行证">港澳居民来往内地通行证</option>
<option value="台湾居民来往大陆通行证">台湾居民来往大陆通行证</option>
<option value="大陆居民往来台湾通行证">大陆居民往来台湾通行证</option>
<option value="护照">护照</option>
<option value="其它有效证件">其它有效证件</option>
  		</select>  
                        </div>
                         <div>
                            <label>证件号码： &nbsp;&nbsp;<span id="numIdTag"></span></label>
                        <input type="text" name="numId" value="" class="contactField" id="numId"/>
                            
                       </div>
                        <div>
                            <label>经营区域：&nbsp;<span id="provinceTag"></span></label>
						<span id="city_2">	<select class="prov contactField" id="province" name="province">
  		</select> 
    	<select class="city contactField" id="city" name="city" disabled="disabled">
		</select>
		 <select class="dist contactField" id="area" name="area" disabled="disabled">
		</select> </span></div>
                             <div>
                            <label>详细地址：  &nbsp;&nbsp;<span id="addressTag"></span></label>
                            <input type="text" name="address" value="" class="contactField" id="address"/>
                        </div>
                          <div>
                            <label>预留手机号码： &nbsp;&nbsp;<span id="phoneTag"></span> </label>
                             <input type="text" name="phone" value="" class="contactField" id="phone"/>
                                </div>
                     	
                        <div>
                            <input type="submit" class="button button-green" id="sbtn" value="确认提交"/>
                            <input type="reset" class="button button-blue" id="btn_reset" value="清空重置"/>
                          
                            
                        
                        </div>
                    </fieldset>
                </form>       
            </div>
        </div>
    </div>
    
  
    <div class="decoration"></div>
</div>

<div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>
<script type="text/javascript" src="../../js/custom.js"></script>
 <script type="text/javascript">

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
   
     $("#btn_close").click(function(){
     if (confirm("您确定要关闭本页吗？")){
window.opener=null;
window.open('','_self');
window.close();
}
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
   
    $("#numId").blur(function(){
   	 if($(this).val()==""){
				
					$("#numIdTag").text("证件号码不能为空！").css({"color":"red","font-size":"12px"}); 
				}
	else $("#numIdTag").text("");
   });
   
   $("#province").blur(function(){
   	 if($(this).val()==""){
				
					$("#provinceTag").text("经营区域不能为空！").css({"color":"red","font-size":"12px"}); 
				}
	else $("#provinceTag").text("");
   });
    $("#city").blur(function(){
   	 if($(this).val()==""){
					$("#provinceTag").text("经营区域不能为空！").css({"color":"red","font-size":"12px"}); 
				}
	else $("#provinceTag").text("");
   });
   
	
	$("#sbtn").click(function() {
	 if (!confirm("您确定要提交申请信息吗？")){
	 return false;
	 }else{
	var errorstr ="";
		
			if($("#receiver").val()==""){
					$("#nameTag").text("收件人不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#nameTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#numId").val()==""){
					$("#numIdTag").text("证件号码不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#numIdTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#address").val()==""){
					$("#addressTag").text("收货地址不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#addressTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			
			if($("#phone").val()==""){
					$("#phoneTag").text("预留电话格式有误！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#phoneTag").text()!=""){
				errorstr =errorstr+"1";
			}
			if($("#province").val()==""){
					$("#provinceTag").text("经营区域不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#provinceTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#city").val()==""){
					$("#provinceTag").text("经营区域不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#provinceTag").text()!=""){
				errorstr =errorstr+"1";
			}
		
		if(errorstr!=""){
			return false;
		}else{
       		 $('#myform').submit();
       		 return true;
		
		}
}
	});	
});
</script>
</html>