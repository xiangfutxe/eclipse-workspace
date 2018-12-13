<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.utils.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String token = (String)request.getSession().getAttribute("token");
	
%>
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

<title>经销商注册</title>

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
<script type="text/javascript" src="../scripts/custom.js"></script>
<script type="text/javascript" src="../scripts/framework.js"></script>

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
              
                <form action="UserServlet.do?method=register" method="post" class="contactForm" id="myform">
                       <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <fieldset>
                        
                          <div>
                            <label class="field-title contactNameField">*客服编号:<span id="refereeuser"></span><span id="refereeIdTag"></span></label>
                            <input type="text" name="refereeId" id="refereeId" value="" class="contactField"/>
                        </div>
                        <div>
                            <label class="field-title contactNameField" >*真实姓名:<span id="nameTag"></span></label>
                            <input type="text" name="userName" value="" class="contactField" id="userName"/>
                        </div>
                        
                        <div>
                            <label class="field-title contactNameField" >*登录密码: <span id="passwordTag"></span></label>
                            <input type="password" name="password" value="" class="contactField requiredField" id="password"/>
                        </div>
                          <div>
                            <label class="field-title contactNameField" >*密码确认: <span id="password2Tag"></span></label>
                            <input type="password" name="password2" value="" class="contactField" id="password2"/>
                        </div>
                       <div>
                            <label class="field-title contactNameField" >*手机号码:<span id="telTag"></span></label>
                            <input type="text" name="tel" value="" class="contactField" id="tel"/>
                        </div>
                        <div>
                            <input type="submit" class="buttonWrap button button-green contactSubmitButton" id="btn1" value="提交注册"/>
                        </div>
                        
                       
                          <div>
                         <a href="login.jsp">经销商登陆</a>
                         
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
<script type="text/javascript" src="../../js/custom.js"></script>
 <script type="text/javascript">
	$(function(){
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  //验证身份证号码
   var reg1 = /^[1-9]+[0-9]*]*$/;  //验证整数
  var reg2 = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证
    var reg3 = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; //验证电子邮箱
   // var reg4 =  /^(?!(\d)\1{1,}$)[1-9]\d{4,}$/;//验证QQ号码
    var reg5=/^([A-Za-z])+$/;  //验证数字和字母的组合
    var reg6=/^[0-9]*$/;  //验证数字组合
    
  
   $("#userName").blur(function(){
   		 if($("#userName").val()=="")  $("#nameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"}); 
		else if(getByteLen($("#userName").val())>50) $("#nameTag").text("真实姓名字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#nameTag").text("");
		
   });
   
    $("#password").blur(function(){
   		 if($("#password").val()=="")  $("#passwordTag").text("登录密码不能为空！").css({"color":"red","font-size":"12px"}); 
   		 else if(getByteLen($("#password").val())<6) $("#passwordTag").text("密码字数不能小于6位数！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($("#password").val())>50) $("#passwordTag").text("密码字数不能大于25位数！").css({"color":"red","font-size":"12px"});
		else $("#passwordTag").text("");
		
   });
    $("#password2").blur(function(){
   		 if($(this).val()!= $("#password").val())  $("#password2Tag").text("两次输入密码不一致不能为空！").css({"color":"red","font-size":"12px"}); 
		else $("#password2Tag").text("");
		
   });


    
   $("#tel").blur(function(){
   	 if($("#tel").val()!=""){
				if(!(reg2.test($("#tel").val())))
					$("#telTag").text("联系电话格式有误！").css({"color":"red","font-size":"12px"}); 
					else $("#telTag").text("");
	}else{
		$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"});
	}
   });
  	  //给按钮绑定点击事件
  	 $("#refereeId").blur(function(){
  	 	$.ajax({
  	 		type:"post",
  	 		url:"UserServlet.do?method=userAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
  	 		data:{//设置数据源
						userId:$("input[name=refereeId]").val() //这里不要加","  不然会报错，而且根本不会提示错误地方
					
				},
 
 			dataType:"json",//设置需要返回的数据类型

			success:function(data){
						var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
													//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
						if(d.userName==""){
						 $("#refereeuser").text("");
						 $("#refereeIdTag").text("查无此人，请重新输入！").css({"color":"red","font-size":"12px"});
						 }
						else{
						 $("#refereeuser").text(""+d.userName+"").css({"color":"green","font-size":"12px"});
						 $("#refereeIdTag").text("");
						 }

                    },

                    error:function(){
                    
                        alert("系统异常，请稍后重试！");

                    }//这里不要加","
  	 	});
  	 });
  	 
  	  $("#btn1").click(function(){
  	  if (!confirm("确认提交保存会员基本信息？")) {
				return false;
			}else{
		  	 	var errorstr =0;
			
	   		 if($("#userName").val()=="") {
	   		 	 $("#nameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"}); 
	   		  	errorstr++;
	   		  	$("#userName").focus();
	   		  }else if($("#nameTag").text()!=""){
	   		   errorstr++;
	   		   $("#userName").focus();
	   		  		 }
	   		  
	   		  if($("#password").val()=="") {
	   		 	 $("#passwordTag").text("登录密码不能为空！").css({"color":"red","font-size":"12px"}); 
	   		  	errorstr++;
	   		  	$("#password").focus();
	   		  }else if($("#passwordTag").text()!="") {
	   		  errorstr++;
	   		  $("#password").focus();
	   		  }
			
	   if($("#password2").val()!=$("#password").val()) {
	   		 	 $("#password2Tag").text("两次输入密码不一致！").css({"color":"red","font-size":"12px"}); 
	   		  	errorstr++;
	   		  	$("#password2").focus();
	   		  }else if($("#password2Tag").text()!="") {
	   		  errorstr++;
	   		  $("#password2").focus();
	   		  }
		  	 

				if($("#tel").val()!=""){
					if(!(reg2.test($("#tel").val()))){
						$("#telTag").text("手机号码格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr++;
						$("#tel").focus();
					}
				
			}else if($("#tel").val()==""){
				$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr++;
					$("#tel").focus();
			}else if($("#telTag").val()!=""){
				errorstr++;
				$("#tel").focus();
			}
				
			
				if($("#refereeId").val()==""){
				$("#refereeuser").text("");
					$("#refereeIdTag").text("客服编号不能为空！").css({"color":"red","font-size":"12px"});
					errorstr++;
					 $("#refereeId").focus();
				}
				else if($("#refereeIdTag").text()!=""){
				$("#refereeuser").text("");
						errorstr++;
						 $("#refereeId").focus();
				}
				
				if(errorstr>0){
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