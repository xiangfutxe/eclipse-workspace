<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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

<title>用户登录</title>

<link href="../styles/style.css"     		rel="stylesheet" type="text/css">
<link href="../styles/framework.css" 		rel="stylesheet" type="text/css">
<link href="../styles/owl.carousel.css" 	 rel="stylesheet" type="text/css">
<link href="../styles/owl.theme.css" 		rel="stylesheet" type="text/css">
<link href="../styles/swipebox.css"		 rel="stylesheet" type="text/css">
<link href="../styles/colorbox.css"		 rel="stylesheet" type="text/css">


<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/jqueryui.js"></script>
<script type="text/javascript" src="scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="scripts/colorbox.js"></script>
<script type="text/javascript" src="scripts/snap.js"></script>
<script type="text/javascript" src="scripts/contact.js"></script>
<script type="text/javascript" src="../scripts/custom.js"></script>
<script type="text/javascript" src="scripts/framework.js"></script>
<script type="text/javascript" src="scripts/framework.launcher.js"></script>

</head>
<body>


<div class="top-deco"></div>

<div class="landing-page">
	<a class="landing-logo" href="#">
    	<img src="../images/misc/login-logo.png" alt="img">
    </a>
    
    <div class="decoration"></div>
      
  <div class="content">
    <div class="container">   
        <div class="container no-bottom">
            <div class="contact-form no-bottom"> 
              
                <form action="UserServlet.do?method=login" method="post" class="contactForm" id="contactForm">
                    <fieldset>
                        
                            <div id="tag"  class="static-notification-red tap-dismiss-notification">
                                <p class="center-text uppercase"></p>
                            </div>
                        
                       <div class="formValidationError" id="passworddError2">
                            <div class="static-notification-red tap-dismiss-notification">
                                <p class="center-text uppercase">登录密码有误！</p>
                            </div>
                        </div> 
                        <div class="formFieldWrap">
                            <label class="field-title contactNameField" for="contactNameField">用户名:<span id="nameTag"></span></label>
                            <input type="text" id="userId" name="userId" value="" class="contactField requiredField" id="userId"/>
                            <span id="nameTag"></span>
                        </div>
                        <div class="formFieldWrap">
                            <label class="field-title contactPasswordField" for="contactPasswordField">登录密码: </label>
                            <input type="password" name="password" value="" class="contactField requiredField requiredPasswordField" id="password"/>
                        </div>
                     
                        <div class="formSubmitButtonErrorsWrap">
                            <input type="submit" class="buttonWrap button button-green contactSubmitButton" id="submitButton" value="登录" data-formId="contactForm"/>
                        </div>
                    </fieldset>
                </form>       
            </div>
        </div>
    </div>
    <div>
    </div>
    </div>
    <div class="decoration"></div>
    
    <%@ include file="footer.jsp" %>  
</div>

<div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>
<script type="text/javascript" src="js/custom.js"></script>
 <script type="text/javascript">
$(function(){
 $("#userId").blur(function(){
 if($("#userId").val()=="") $("#nameTag").text("OK!");
 else $("#nameTag").text("");
 });
 });
</script>
</html>