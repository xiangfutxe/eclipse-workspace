<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil"%>
<%@ include file="check.jsp" %>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User)request.getSession().getAttribute("sys_user");
String token = (String)request.getSession().getAttribute("token");
String psw2 = (String)request.getSession().getAttribute("psw2");
	if (user == null) {
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

<title>提现申请</title>

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
<script type="text/javascript" src="../scripts/apply.js"></script>
<script type="text/javascript" src="../scripts/custom.js"></script>
<script type="text/javascript" src="../scripts/framework.js"></script>

</head>
<body>

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	提现申请</p><p class="go-title-en">WITHDREW APPLY
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
               <form  action="WithDrewServlet.do?method=apply_save"  id="myform" name="myform" method="post">
                 <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                         <fieldset>
                          <div>
                            <label>可提现金额：&nbsp;&nbsp;</label>
                           <%=StringUtil.decimalFormat(user.getRmoney()) %>元
                        </div>
                         <div>
                            <label>提现金额：&nbsp;&nbsp;<span id="amountTag"></span></label>
                            <input type="text" name="amount" value="" class="contactField" id="amount"/>
                        </div>
                             <div>
                            <label>支付密码：&nbsp; &nbsp;<span id="passwordTag"></span></label>
                            <input type="password" name="password2" value="" class="contactField" id="password2"/>
                        </div>
                          <div>
                            <label><a>*温馨提示：</a>
                           请在基本信息内设置好银行账户，否则会造成提现失败！ </label>
                                </div>
                     	
                        <div>
                            <input type="button" class="buttonWrap button button-green contactSubmitButton" id="sbtn" value="提交" />
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

	//var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;验证浮点数
	var reg =  /^[1-9]\d*00$/; //验证100的整数倍
	$("#amount").blur(function() {
		$.ajax({
					type : "post",
					url : "WithDrewServlet.do?method=amountAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
					data : {//设置数据源
						amount : $("input[name=amount]").val()
					//这里不要加","  不然会报错，而且根本不会提示错误地方
	
					},
	
					dataType : "json",//设置需要返回的数据类型
					success : function(data) {
						var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
						//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
						
						if(d.tag==1) $("#amountTag").text("当前账户余额不足，请重新确认金额！").css({"color":"red","font-size":"12px"});
						else if(d.tag==2) $("#amountTag").text("数值格式有误，金额应为100的整数倍！").css({"color":"red","font-size":"12px"});
						else if($("#amount").val()<100) $("#amountTag").text("提现金额不能小于100元！").css({"color":"red","font-size":"12px"});
						else if(!reg.test($("#amount").val())) $("#amountTag").text("数值格式有误，金额应为100的整数倍！").css({"color":"red","font-size":"12px"});
						
						else $("#amountTag").text("");
					},
					error : function() {
						alert("系统异常，请稍后重试！");
						$("#amountTag").text("系统异常，请稍后重试！").css({"color":"red","font-size":"12px"}); 
					}//这里不要加","
					
				});
		
	});	
	
	$("#password2").blur(function() {
		if($(this).val()==""){
			$("#passwordTag").text("支付密码不能为空！").css({"color":"red","font-size":"12px"}); 
		}else $("#passwordTag").text("");
	});	
	
	$("#sbtn").click(function() {
		 if (!confirm("确认要提交申请？")) {
	            window.event.returnValue = false;
	        }else{
							var errorstr ="";
							if($("#amount").val()==""){
								$("#amountTag").text("充值金额不能为空！").css({"color":"red","font-size":"12px"}); 
								errorstr = errorstr +"1";
							}else if(!reg.test($("#amount").val())) {
								$("#amountTag").text("数值格式有误，金额应为100的整数倍！").css({"color":"red","font-size":"12px"});
								errorstr = errorstr +"1";
							}
						else if($("#amount").val()<100){
							$("#amountTag").text("提现金额不能小于100元，且必须是100的整数倍！").css({"color":"red","font-size":"12px"});
							errorstr = errorstr + "1";
}
							else if($("#amountTag").text()!="") errorstr = errorstr + "1";
							
							if($("#password2").val()==""){
								$("#passwordTag").text("支付密码不能为空！").css({"color":"red","font-size":"12px"}); 
								errorstr = errorstr +"1";
							}else if($("#passwordTag").text()!="") errorstr = errorstr + "1";
							
							if($("#remarkTag").text()!="") errorstr = errorstr + "1";
					
							if(errorstr!=""){
								 window.event.returnValue = false;
							}else{
								
					       		 $('#myform').submit();
					       		return true;
							
							}
						
			}
			
	});	
});

</script>
</html>
<%}%>