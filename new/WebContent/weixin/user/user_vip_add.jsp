<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String token = (String)request.getSession().getAttribute("token");
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
        	成为经销商</p><p class="go-title-en">MEMBER REGISTRATION
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
    <div class="container no-bottom">
      <div class="contact-form no-bottom"> 
              
                <form action="UserServlet.do?method=user_rank_join_save" method="post" class="contactForm" id="myform">
                 <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <fieldset>
                        
                        <div>
                            <label ><span class="font_red font_size_16">*</span>经销商等级: <span id="rankJoinTag"></span></label>
                            <select name="rankJoin"  class="contactField">
                            <option value="">--请选择会员级别--</option>
											<option value="1">银级代理</option>
											<option value="2">金级代理</option>
											<option value="3">钻级代理</option>
										</select>
                        </div>
                        <div>
                            <label><span class="font_red font_size_16">*</span>客服编号： &nbsp;&nbsp;<span id="refereeuser"></span><span id="refereeIdTag"></span></label>
                            <input type="text" name="refereeId" value="" class="contactField" id="refereeId"/>
                        </div>
                         <div>
                            <label><span class="font_red font_size_16">*</span>真实姓名： &nbsp;&nbsp;<span id="nameTag"></span></label>
                            <input type="text" name="userName" value="" class="contactField" id="userId"/>
                        </div>
                         <div>
                            <label><span class="font_red font_size_16">*</span>性别： &nbsp;&nbsp;<span id="sexTag"></span></label>
                             <select class="contactField" id="sex" name="sex">
                             <option value="男">男</option>
                              <option value="女">女</option>
                             </select>
                            
                        </div>
                         <div>
                            <label><span class="font_red font_size_16">*</span>登录密码: <span id="passwordTag"></span></label>
                            <input type="password" name="password" value="" class="contactField requiredField" id="password"/>
                        </div>
                          <div>
                            <label><span class="font_red font_size_16">*</span>密码确认: <span id="password2Tag"></span></label>
                            <input type="password" name="password2" value="" class="contactField" id="password2"/>
                        </div>
                        	   <div>
                            <label><span class="font_red font_size_16">*</span>证件类型： &nbsp;&nbsp;<span id="documentTypeTag"></span></label>
                           
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
                            <label><span class="font_red font_size_16">*</span>证件号码： &nbsp;&nbsp;<span id="numIdTag"></span></label>
                        <input type="text" name="numId" value="" class="contactField" id="numId"/>
                            
                       </div>
                        <div>
                            <label><span class="font_red font_size_16">*</span>收货信息：&nbsp;<span id="provinceTag"></span></label>
						<span id="city_2">	<select class="prov contactField" id="province" name="province">
  		</select> 
    	<select class="city contactField" id="city" name="city" disabled="disabled">
		</select>
		 <select class="dist contactField" id="area" name="area" disabled="disabled">
		</select> </span></div>
                             <div>
                            <label><span class="font_red font_size_16">*</span>详细地址：  &nbsp;&nbsp;<span id="addressTag"></span></label>
                            <input type="text" name="address" value="" class="contactField" id="address"/>
                        </div>
                          <div>
                            <label><span class="font_red font_size_16">*</span>手机号码： &nbsp;&nbsp;<span id="phoneTag"></span> </label>
                             <input type="text" name="phone" value="" class="contactField" id="phone"/>
                                </div>
  
                        <div>
                            <label ><span class="font_red font_size_16">*</span>银行账户姓名：
&nbsp;<span id="accountNameTag"></span></label><input
									id="accountName" name="accountName"  class="contactField"
									value=''
									type="text">
								</div>
								
							<div>
                            <label ><span class="font_red font_size_16">*</span>银行卡号：&nbsp;<span id="accountIdTag"></span>
                            </label><input
									id="accountId" name="accountId" class="contactField"
									value=''
									type="text">
								</div>
								
							<div>
                            <label ><span class="font_red font_size_16">*</span>开户银行：</label>
							<select name="bankName" id="bankName" class="contactField">
								<option value="中国工商银行股份有限公司">中国工商银行股份有限公司</option>
									<option value="中国建设银行股份有限公司">中国建设银行股份有限公司</option>
									<option value="中国农业银行股份有限公司">中国农业银行股份有限公司</option>
									<option value="中国银行股份有限公司">中国银行股份有限公司</option>
									<option value="交通银行股份有限公司">交通银行股份有限公司</option>
									<option value="中国邮政储蓄银行股份有限公司">中国邮政储蓄银行股份有限公司</option>
									<option value="中信银行股份有限公司">中信银行股份有限公司</option>
									<option value="中国光大银行股份有限公司">中国光大银行股份有限公司</option>
									<option value="华夏银行股份有限公司">华夏银行股份有限公司</option>
									<option value="中国民生银行股份有限公司">中国民生银行股份有限公司</option>
									<option value="招商银行股份有限公司">招商银行股份有限公司</option>
									<option value="广发银行股份有限公司">广发银行股份有限公司</option>
									<option value="兴业银行股份有限公司">兴业银行股份有限公司</option>
									<option value="广州商业银行股份有限公司">广州商业银行股份有限公司</option>
									</select>
								</div>
								
							<div>
                            <label ><span class="font_red font_size_16">*</span>开户网点：&nbsp;<span id="bankAdrTag"></span></label>
								<input
									id="bankAdr" name="bankAdr" class="contactField"
									value=''
									type="text">
							</div>
                        <div>
                            <input type="submit" class="buttonWrap button button-green contactSubmitButton" id="btn1" value="保存"/>
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

 $("#city_2").citySelect({
		nodata:"none",
		required:false
	}); 

	
   
   $("#rankJoin").blur(function(){
   		 if($(this).val()=="")  $("#rankJoinTag").text("代理商等级不能为空！").css({"color":"red","font-size":"12px"}); 
		else $("#rankJoinTag").text("");
		
   });
   
   $("#userName").blur(function(){
   		 if($(this).val()=="")  $("#nameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"}); 
		else $("#nameTag").text("");
		
   });
   
    $("#password").blur(function(){
   		 if($("#password").val()=="")  $("#passwordTag").text("登录密码不能为空！").css({"color":"red","font-size":"12px"}); 
   		 else if(getByteLen($("#password").val())<6) $("#passwordTag").text("密码字数不能小于6位数！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($("#password").val())>25) $("#passwordTag").text("密码字数不能大于25位数！").css({"color":"red","font-size":"12px"});
		else $("#passwordTag").text("");
		
   });
    $("#password2").blur(function(){
   		 if($(this).val()!= $("#password").val())  $("#password2Tag").text("两次输入密码不一致不能为空！").css({"color":"red","font-size":"12px"}); 
		else $("#password2Tag").text("");
		
   });
   
   $("#address").blur(function(){
   		 if($(this).val()=="")  $("#addressTag").text("详细地址不能为空！").css({"color":"red","font-size":"12px"}); 
		else if(getByteLen($("#address").val())>200) $("#addressTag").text("详细地址字数不能大于100！").css({"color":"red","font-size":"12px"});
		else $("#addressTag").text("");
		
   });
   
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
   
   $("#phone").blur(function(){
   	 if($(this).val()!=""){
		if(!reg.test($(this).val()))
			$("#phoneTag").text("联系电话格式有误！").css({"color":"red","font-size":"12px"}); 
		else $("#phoneTag").text("");
	}else if(getByteLen($("#phoneTag").val())>15) $("#nameTag").text("联系电话字数不能大于15！").css({"color":"red","font-size":"12px"});
	else $("#phoneTag").text("");
   });
   
    $("#numId").blur(function(){
   	 if($(this).val()==""){
		$("#numIdTag").text("证件号码不能为空！").css({"color":"red","font-size":"12px"}); 
	}else $("#numIdTag").text("");
   });
   
   $("#province").blur(function(){
   	 if($(this).val()==""){
		$("#provinceTag").text("收货信息不能为空！").css({"color":"red","font-size":"12px"}); 
	}else $("#provinceTag").text("");
   });
    $("#city").blur(function(){
   	 if($(this).val()==""){
		$("#provinceTag").text("收货信息不能为空！").css({"color":"red","font-size":"12px"}); 
				}
	else $("#provinceTag").text("");
   });
   
    $("#accountName").blur(function(){
   	 if($(this).val()==""){
		$("#accountNameTag").text("银行账户姓名不能为空！").css({"color":"red","font-size":"12px"}); 
				}
	else $("#accountNameTag").text("");
   });
   $("#accountId").blur(function(){
   	 if($(this).val()==""){
		$("#accountIdTag").text("银行账号不能为空！").css({"color":"red","font-size":"12px"}); 
				}
	else $("#accountIdTag").text("");
   });
   
   $("#bankName").blur(function(){
   	 if($(this).val()==""){
		$("#bankNameTag").text("开户银行不能为空！").css({"color":"red","font-size":"12px"}); 
				}
	else $("#bankNameTag").text("");
   });
   
   $("#bankAdr").blur(function(){
   	 if($(this).val()==""){
		$("#bankAdrTag").text("开户网点不能为空！").css({"color":"red","font-size":"12px"}); 
				}
	else $("#bankAdrTag").text("");
   });
   
	
	$("#sbtn").click(function() {
	 if (!confirm("您确定要提交申请信息吗？")){
	 return false;
	 }else{
	var errorstr ="";
		if($("#rankJoin").val()==""){
					$("#rankJoinTag").text("代理商等级不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#rankJoinTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			  if($("#password2").val()!=$("#password").val()) {
	   		 	 $("#password2Tag").text("两次输入密码不一致！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
	   		  	$("#password2").focus();
	   		  }else if($("#password2Tag").text()!="") {
					errorstr =errorstr+"1";
	   		  $("#password2").focus();
	   		  }
			
			if($("#numId").val()==""){
					$("#numIdTag").text("证件号码不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#numIdTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#address").val()==""){
					$("#addressTag").text("详细地址不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#addressTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#refereeId").val()==""){
			$("#refereeIdTag").text("客服信息不能为空。").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
					$("#refereeuser").text("");
			}else if($("#refereeIdTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			
			if($("#phone").val()==""){
					$("#phoneTag").text("手机号码格式有误！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#phoneTag").text()!=""){
				errorstr =errorstr+"1";
			}
			if($("#province").val()==""){
					$("#provinceTag").text("收货信息不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#provinceTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#city").val()==""){
					$("#provinceTag").text("收货信息不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#provinceTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			 if($("#accountName").val()==""){
   			 $("#accountNameTag").text("银行账号姓名不能为空！").css({"color":"red","font-size":"12px"}); 
   				errorstr =errorstr+"1";
			}else if($("#accountNameTag").val()!="") errorstr =errorstr+"1";
			
			 if($("#accountId").val()==""){
   			 $("#accountIdTag").text("银行卡号不能为空！").css({"color":"red","font-size":"12px"}); 
   				errorstr =errorstr+"1";
			}else if($("#accountIdTag").val()!="") errorstr =errorstr+"1";
			
			if($("#backName").val()==""){
   			 $("#backNameTag").text("开户银行不能为空！").css({"color":"red","font-size":"12px"}); 
   				errorstr =errorstr+"1";
			}else if($("#backNameTag").val()!="") errorstr =errorstr+"1";
			
			if($("#backAdr").val()==""){
   			 $("#backAdrTag").text("开户网点不能为空！").css({"color":"red","font-size":"12px"}); 
   				errorstr =errorstr+"1";
			}else if($("#backAdrTag").val()!="") errorstr =errorstr+"1";
		
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