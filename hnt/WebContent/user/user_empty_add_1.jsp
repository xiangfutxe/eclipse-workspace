<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");
String token = (String)request.getSession().getAttribute("token");
		String psw2 = (String)request.getSession().getAttribute("psw2");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else if(StringUtil.notNull(psw2).equals("")){
		out.println("<script>");
		out.println("window.location.href='psw_login.jsp';");
		out.println("</script>");
	}else if(!StringUtil.notNull(psw2).equals(user.getPassword2())){
		out.println("<script>");
		out.println("window.location.href='psw_login.jsp';");
		out.println("</script>");
	}else{
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<title> 个人资料 </title>

<!-- **Favicon** -->
<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />

<!-- **CSS - stylesheets** -->
<link href="style.css" rel="stylesheet" type="text/css" media="all" />

<!-- **jQuery** -->
<script type="text/javascript" src="js/jquery.jcarousel.min.js"></script>
<script type="text/javascript" src="js/spa.custom.js"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<script src="js/contact.js" type="text/javascript"></script>

      <script type="text/javascript" src="../js/jquery.js"></script>
      <script type="text/javascript" src="js/jquery.cityselect.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>

<body>
<div id="header">
	<div class="container">
    	  <%@ include file="header.jsp" %>  
		 
    </div>
</div>


<!-- ** Main** -->
<div id="main">

	<div class="container">
    	<!-- **Breadcrumb** -->
        <div class="breadcrumb">
            <a href="index.jsp" title=""> 首页 </a>
            <span class="arrow"> </span><a>业务管理 </a>
			  <span class="arrow"> </span>
             <span class="current-crumb">空单注册 </span>
        </div>  <!-- **Breadcrumb - End** -->      	
    </div>
    
	<!-- **Main Container** -->
	<div class="main-container">
    	<!-- **Content Full Width** -->
    	<div class="content content-full-width"> 
         <form  id="myform" name="myform" method="post">
  <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
   <h4><font color="red">备注：带“*”为必填项。</font></h4>
		 <h1 class="title"> <span> 会员关系 </span> </h1>
<div class="notice">
									<span class="required">*</span><span class="left">会&nbsp;员&nbsp;级&nbsp;别：&nbsp;</span><span class="right"><select id="rankJoin" name="rankJoin"
										class="common-text required">
											<option value="1">普卡会员</option>
									</select>&nbsp;<span id="rankJoinTag"></span></span>
						</div>
			<div class="notice">

							<span class="required">*</span><span class="left">服务商编号：</span><span class="right"><input id="refereeId" name="refereeId" size="50"  value="" type="text">&nbsp;<span id="refereeuser"></span>&nbsp;<span id="refereeuser1"></span>
							</span>
						</div>
						
						<div class="notice">
							<span class="required">*</span><span class="left">销售商编号：</span><input id="belongId" name="belongId" size="50" value="" type="text">&nbsp; <select id="nodeTag" name="nodeTag" class="common-text required">
										  <option value="0">--请选择--</option>
										  </select>&nbsp;<span id="belonguser"></span><span id="nodeTagmsg"></span>
							
						</div>
			 
						<h1 class="title">
							<span> 会员资料 </span>
						</h1>
						<div class="notice">
							<span class="required"> * </span><span class="left">会员编号：</span><span class="right">    <input id="userId" name="userId" size="50"  value="" type="text">
                                <span id="idTag"></span>
							</span>
							<br>
							<span class="right">*（请输入两位英文字母，不区分大小写，后面数字将随机产生。）</span>
						</div>
						<div class="notice">
							<span class="required"> * </span><span class="left">真实姓名：</span><span class="right"> <input
								id="userName" name="userName" size="50"
								value=''
								type="text"> <span id="nameTag"></span>
							</span>
						</div>
						<div class="notice">
							<span class="required"> * </span><span class="left">登录密码：</span><span class="right">  <input id="password" name="password"  value="" size="50" type="password">
                                <span id="passwordTag"></span>
							</span>
						</div>
						<div class="notice">
							<span class="required"> * </span><span class="left">确认密码：</span><span class="right">  <input id="password2" name="password2"  value="" size="50" type="password">
                                <span id="password2Tag"></span>
							</span>
						</div>
						<!-- 
						<div class="notice">
						<span class="required">*</span><span class="left">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：&nbsp;&nbsp;</span><span
								class="right"> <input id="age" type="text" name="age"
								size="50" value="">
								&nbsp; <span id="ageTag"></span> </span>
						</div> -->
						<div class="notice">
						<span class="required">*</span>	<span class="left">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span><span
								class="right"><input type="radio" name="sex" value="男" checked>男 &nbsp;<input type="radio" name="sex" value="女">女 <span id="sexTag"></span> </span>
						</div>
						<div class="notice">
							<span class="required">*</span><span class="left">证件类型：&nbsp;&nbsp;</span><span class="right"> <select
								name="documentType" id="documentType">
									<option value="居民身份证">居民身份证</option>
							</select> <span id="documentTypeTag"></span> </span>
						</div>
						<div class="notice">
							<span class="left">*证件号码：</span><span class="right"> <input
								id="numId" name="numId" size="50"
								value='' type="text">&nbsp;<span
								id="numIdTag"></span>
							</span>
						</div>
						<div class="notice">
							<span class="required">*</span><span class="left">联系电话：</span><span class="right"> <input
								id="tel" name="tel" size="50"
								value='' type="text">&nbsp;<span
								id="telTag"></span>
							</span>
						</div>
	<div class="notice">
							<span class="required"></span><span class="left">固定电话：&nbsp;&nbsp;</span><span class="right"> <input
								id="phone" name="phone" size="50"
								value='' type="text">&nbsp;<span
								id="phoneTag"></span>
							</span>
						</div>
						<div class="notice">
							<span class="left">电子邮箱：&nbsp;&nbsp;</span><span class="right"> <input
								id="email" name="email" size="50"
								value='' type="text">&nbsp;<span
								id="emailTag"></span>
							</span>
						</div>

						<div class="notice">
							<span class="left">Q&nbsp;Q&nbsp;号&nbsp;码：&nbsp;</span><span class="right">
								<input id="qq" name="qq"
								value='' size="50" type="text">&nbsp;<span
								id="qqTag"></span>
							</span>
						</div>
						<div class="notice">
							<span class="left">微&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;信：&nbsp;&nbsp;&nbsp;</span><span class="right">
								<input id="weixin" name="weixin"
								value='' size="50" type="text">&nbsp;<span
								id="weixinTag"></span>
							</span>
						</div>
						<div class="notice">
							<span class="required">*</span><span class="left">经营区域：</span><span class="right" id="city_2">
							<select class="prov" id="province" name="province">
  		</select> 
    	<select class="city" id="city" name="city" disabled="disabled">

		</select>
		 <select class="dist" id="area" name="area" disabled="disabled">
		</select> &nbsp;<span id="provinceTag"></span></span>
						</div>
						<div class="notice">
							<span class="left">联系地址：</span><span class="right"> <input
								size="50" id="address" name="address"
								value='' type="text">&nbsp;<span
								id="addressTag"></span>
							</span>
						</div>
						<h1 class="title">
							<span> 银行账号</span>
						</h1>
							<div class="notice">
								<span class="left">账户姓名：</span><span class="right"><input
									id="accountName" name="accountName" size="50"
									value=''
									type="text" readonly>&nbsp;<span id="accountNameTag"></span>
								</span>
							</div>
							<div class="notice">
								<span class="left">银行卡号：</span><span class="right"><input
									id="accountId" name="accountId" size="50"
									value=''
									type="text">&nbsp;<span id="accountIdTag"></span>
								</span>
							</div>
							<div class="notice">
								<span class="left">开户银行：</span><span class="right">
								<select name="bankName" id="bankName">
								<option value="中国农业银行股份有限公司">中国农业银行股份有限公司</option>
										<option value="中国工商银行股份有限公司">中国工商银行股份有限公司</option>
										<option value="中国建设银行股份有限公司">中国建设银行股份有限公司</option>
										<option value="中国银行股份有限公司">中国银行股份有限公司</option>
								</select>
								</span>
								</div>
						<div class="notice">
								<span class="left">开户网点：</span><span class="right">
								<input
									id="bankAdr" name="bankAdr" size="50"
									value=''
									type="text">&nbsp;<span id="bankAdrTag"></span>
								</span>
							</div>
							
							<div class="notice">
								<span class="left"> <input name="button" id="btn1"
									type="submit" value="提交" /> </span>
							</div>
						
							</form>
						</div>
					</div>
    </div>
    
     <!-- **Footer Bottom** -->
    <%@ include file="footer.jsp" %>  
    <!-- **Footer Bottom - End** -->


</body>
 <script type="text/javascript" src="../js/custom.js"></script>
 <script type="text/javascript">
	$(function() {
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  //验证身份证号码
   var reg1 = /^[1-9]+[0-9]*]*$/;  //验证整数
  var reg2 = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证
    var reg3 = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; //验证电子邮箱
   // var reg4 =  /^(?!(\d)\1{1,}$)[1-9]\d{4,}$/;//验证QQ号码
    var reg5=/^([A-Za-z])+$/;  //验证数字和字母的组合
    var reg6=/^[0-9]*$/;  //验证数字组合
     $("#city_2").citySelect({
		nodata:"none",
		required:false
	}); 
   $("#userName").blur(function(){
	   $("#accountName").attr("value",$("#userName").val());
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
   
    $("#province").blur(function(){
   		 if($("#province").val()=="")
   		  $("#provinceTag").text("省市县不能为空！").css({"color":"red","font-size":"12px"});
   		  else $("#provinceTag").text("");
   	});
   	 $("#city").blur(function(){
   		 if($("#city").val()=="")
   		  $("#provinceTag").text("省市县不能为空！").css({"color":"red","font-size":"12px"});
   		  else $("#provinceTag").text("");
   	});

$("#documentType").blur(function() {
					if ($("#documentType").val() == "")
							$("#documentTypeTag").text("证件类型不能为空！").css({
								"color" : "red",
								"font-size" : "12px"
							});
					 else
						$("#documentTypeTag").text("");
				});

   $("#numId").blur(function(){
   	if($("#documentType").val()=="居民身份证"||$("#documentType").val()=="临时身份证"){
				if(!reg.test($("#numId").val()))
					$("#numIdTag").text("身份证号码格式有误！").css({"color":"red","font-size":"12px"}); 
					else $("#numIdTag").text("");
	}else $("#numIdTag").text("");
   });
   /*
    $("#age").blur(function(){
   	 if($("#age").val()=="")  $("#ageTag").text("年龄不能为空！").css({"color":"red","font-size":"12px"}); 
		else if(!reg1.test($("#age").val())) $("#ageTag").text("年龄格式有误！").css({"color":"red","font-size":"12px"}); 
		else if($("#age").val()>200) $("#ageTag").text("年龄超出了正常范围,请核对！").css({"color":"red","font-size":"12px"}); 
		else if($("#age").val()<18) $("#ageTag").text("年龄未满18岁，未满足加盟资格！").css({"color":"red","font-size":"12px"}); 
		else $("#ageTag").text("");
   });
  */
    
   $("#tel").blur(function(){
   	 if($("#tel").val()!=""){
				if(!(reg2.test($("#tel").val())))
					$("#telTag").text("联系电话格式有误！").css({"color":"red","font-size":"12px"}); 
					else $("#telTag").text("");
	}else{
		$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"});
	}
   });
   
   $("#email").blur(function(){
   	 if($("#email").val()!=""){
				if(!reg3.test($("#email").val()))
					$("#emailTag").text("电子邮箱格式有误！").css({"color":"red","font-size":"12px"}); 
					else $("#emailTag").text("");
	}else $("#emailTag").text("");
   });
   
   $("#qq").blur(function(){
   	 if($("#qq").val()!=""){
				if(!reg1.test($("#qq").val()))
					$("#qqTag").text("QQ号码格式有误！").css({"color":"red","font-size":"12px"}); 
					else $("#qqTag").text("");
					
	}else $("#qqTag").text("");
   });
   
 	$("#address").blur(function(){
   		 if($("#address").val()!=""){
   		  if(getByteLen($("#address").val())>100) $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"9px"});
   		else $("#addressTag").text("");
   		}else $("#addressTag").text("");
   			});
   			
  	
  	  //给按钮绑定点击事件
  	 $("#refereeId").blur(function(){
  	 	$.ajax({
  	 		type:"post",
  	 		url:"UserServlet?method=refereeAjax_user",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
  	 		data:{//设置数据源
				refereeId:$("input[name=refereeId]").val() //这里不要加","  不然会报错，而且根本不会提示错误地方
					
				},
 
 			dataType:"json",//设置需要返回的数据类型

			success:function(data){
						var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
													//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
						if(d.userName==""){
						 $("#refereeuser1").text("查无此人，请重新输入！").css({"color":"red","font-size":"12px"});
						  $("#refereeuser").text("");
						 }
						else{
						 $("#refereeuser").text(""+d.userName+"").css({"color":"green","font-size":"12px"});
						  $("#refereeuser1").text("");
						 }
 						$("#belongId").val("");
						  $("#belonguser").text("");
						 $("#belonguser1").text("请根据销售商填写。").css({"color":"red","font-size":"12px"});
                    },

                    error:function(){
                    
                        alert("系统异常，请稍后重试！");

                    }//这里不要加","
  	 	});
  	 });
  	 
	$("#userId").blur(function() {
			if ($("#userId").val() != "") {
				if (!reg5.test($(this).val())){
					$("#idTag").text("请输入两位英文字母！").css({
						"color" : "red",
						"font-size" : "12px"
					});
				}else if($("#userId").val().length!=2){
					$("#idTag").text("长度有误，请输入两位英文字母！").css({
						"color" : "red",
						"font-size" : "12px"
					});
					}
				else
					$("#idTag").text("");
			}else
					$("#idTag").text("请输入两位英文字母！").css({
						"color" : "red",
						"font-size" : "12px"
					});
		});
  	 
  	 $("#belongId").blur(function(){
  	 	$.ajax({
  	 		type:"post",
  	 		url:"UserServlet?method=belongAjax_user",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
  	 		data:{//设置数据源
						belongId:$("input[name=belongId]").val(),
						refereeId:$("input[name=refereeId]").val() //这里不要加","  不然会报错，而且根本不会提示错误地方
				},
 			dataType:"json",//设置需要返回的数据类型
			success:function(data){
						var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
						 if(d.userName==""){
							 $("#nodeTag").empty();
							 $("#nodeTagmsg").text("查无此人，请重新输入！").css({"color":"red","font-size":"12px"});
							 $("#belonguser").text("");
						 }else{
							$("#nodeTag").empty();
								if (d.nodeTag == 1) {
									$("#nodeTag").append(
											'<option value="1">A区</option>');
									$("#nodeTag").append(
											'<option value="2">B区</option>');
									$("#nodeTagmsg").text("");
								} else if (d.nodeTag == 2) {
									$("#nodeTag").append(
											'<option value="1">A区</option>');
									$("#nodeTagmsg").text("");
								} else if (d.nodeTag == 3) {
									$("#nodeTag").append(
											'<option value="2">B区</option>');
									$("#nodeTagmsg").text("");
								}else {
									$("#nodeTagmsg").text(
											"该用户的AB区域已满，请重新选择安置用户！").css({
										"color" : "red",
										"font-size" : "12px"
									});
								}
						  $("#belonguser").text(""+d.userName+"").css({"color":"green","font-size":"12px"});
						 }

                    },
                    error:function(){
                        alert("系统异常，请稍后重试！");
                    }//这里不要加","
  	 	});
  	 });

		$("#rankJoin").blur(function() {
			if ($("#rankJoin").val() == "")
				$("#rankJoinTag").text("加盟等级不能为空！").css({"color" : "red",
					"font-size" : "12px"
				});
			else
				$("#rankJoinTag").text("");

		});

  	 
  	  $("#btn1").click(function(){
  	  if (!confirm("确认提交保存会员基本信息？")) {
				return false;
			}else{
		  	 	var errorstr ="";
			
 			if($("#rankJoin").val()==""){
					$("#rankJoinTag").text("加盟等级不能为空！").css({"color":"red","font-size":"12px"});
					errorstr="1";
					$("#rankJoin").focus();
				}else if($("#rankJoinTag").val()!="") {
					errorstr="1";
					$("#rankJoin").focus();
				}

	   		 if($("#userName").val()=="") {
	   		 	 $("#nameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"}); 
	   		  	errorstr="1";
	   		  	$("#userName").focus();
	   		  }else if($("#nameTag").text()!=""){
	   		   errorstr="1";
	   		   $("#userName").focus();
	   		  		 }
	   		  
	   		  if($("#password").val()=="") {
	   		 	 $("#passwordTag").text("登录密码不能为空！").css({"color":"red","font-size":"12px"}); 
	   		  	errorstr="1";
	   		  	$("#password").focus();
	   		  }else if($("#passwordTag").text()!="") {
	   		  errorstr="1";
	   		  $("#password").focus();
	   		  }
			
	   if($("#password2").val()!=$("#password").val()) {
	   		 	 $("#password2Tag").text("两次输入密码不一致！").css({"color":"red","font-size":"12px"}); 
	   		  	errorstr="1";
	   		  	$("#password2").focus();
	   		  }else if($("#password2Tag").text()!="") {
	   		  errorstr="1";
	   		  $("#password2").focus();
	   		  }
		  	 
if($("#documentType").val()==""){
				$("#documentTypeTag").text("证件类型不能为空！").css({"color":"red","font-size":"12px"}); 
										errorstr="1";
										$("#documentType").focus();
				}else if($("#documentTypeTag").val()!=""){
				errorstr="1";
				$("#documentType").focus();
				}	

				if($("#documentType").val()=="居民身份证"||$("#documentType").val()=="临时身份证"){
					if(!reg.test($("#numId").val())){
						$("#numIdTag").text("身份证号码格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr="1";
						$("#numId").focus();
						}
				}else if($("#numIdTag").val()!=""){
					errorstr="1";
					$("#numId").focus();
				}
				if($("#tel").val()!=""){
					if(!(reg2.test($("#tel").val()))){
						$("#telTag").text("手机号码格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr="1";
						$("#tel").focus();
					}
				
			}else if($("#tel").val()==""){
				$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr="1";
					$("#tel").focus();
			}else if($("#telTag").val()!=""){
				errorstr="1";
				$("#tel").focus();
			}
				
				 if ($(":radio:checked").length == 0)
				{
				$("#sexTag").text("未选择性别！").css({"color":"red","font-size":"12px"});
				errorstr="1";
				
				}
				
				if($("#email").val()!=""){
					if(!reg3.test($("#email").val())){
						$("#emailTag").text("电子邮箱格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr="1";
						$("#email").focus();
					}
				}else if($("#emailTag").val()!=""){
					errorstr="1";
					$("#email").focus();
				}
				
				if($("#qq").val()!=""){
					if(!reg1.test($("#qq").val())){
						$("#qqTag").text("QQ号码格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr="1";
						$("#qq").focus();
					}
				}else if($("#qqTag").val()!=""){
					errorstr="1";
					$("#qq").focus();
				}
				if($("#phone").val()!=""){
					if(getByteLen($("#phone").val()) >25){
						$("#phoneTag").text("固定电话格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr="1";
						$("#phone").focus();
					}
				}else if($("#phoneTag").val()!=""){
					errorstr="1";
					$("#phone").focus();
				}
				if($("#weixin").val()!=""){
					if(getByteLen($("#weixin").val())>50){
						$("#weixinTag").text("微信格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr="1";
						$("#weixin").focus();
					}
				}else if($("#weixinTag").val()!=""){
					errorstr="1";
					$("#weixin").focus();
				}		
/*
				if($("#age").val()==""){
				 $("#ageTag").text("年龄不能为空！").css({"color":"red","font-size":"12px"}); 
				 errorstr="1";
				 $("#age").focus();
				}else if($("#ageTag").text()!=""){
				errorstr ="1";
				$("#age").focus();
				}
				*/
				if($("#belongId").val()==""){
					$("#nodeTagmsg").text("归属人不能为空！").css({"color":"red","font-size":"12px"});
					$("#belonguser").text("");
					errorstr="1";
					$("#belongId").focus();
				}
				else if($("#nodeTagmsg").text()!=""){
						errorstr="1";
						$("#belongId").focus();
				}
				
				if($("#refereeId").val()==""){
					$("#refereeuser1").text("推荐人不能为空！").css({"color":"red","font-size":"12px"});
					$("#refereeuser").text("");
					errorstr="1";
					 $("#refereeId").focus();
				}else if($("#refereeuser1").text()!=""){
						errorstr="1";
					 $("#refereeId").focus();
				}
				
			if($("#userId").val()==""){
					$("#idTag").text("用户编号不能为空！").css({"color":"red","font-size":"12px"});
					errorstr="1";
					 $("#userId").focus();
				}else if($("#idTag").text()!=""){
						errorstr="1";
						 $("#userId").focus();
				}else{
					if (!reg5.test($("#userId").val())){
						$("#idTag").text("请输入两位英文字母！").css({
						"color" : "red",
						"font-size" : "12px"
					});
					errorstr="1";
					 $("#userId").focus();
						}else if($("#userId").val().length!=2){
					$("#idTag").text("长度有误，请输入两位英文字母！").css({
						"color" : "red",
						"font-size" : "12px"
					});
				errorstr="1";
				 $("#userId").focus();
					}				
				}
			
			if($("#province").val()=="") {
   		   $("#provinceTag").text("省市县不能为空！").css({"color":"red","font-size":"9px"});
   			errorstr="1";
   			$("#province").focus();
   				
   		 }else if($("#city").val()=="") {
   		   $("#provinceTag").text("省市县不能为空！").css({"color":"red","font-size":"9px"});
   			errorstr="1";
   			$("#city").focus();
   				
   		 }else if($("#provinceTag").val()!=""){
   		 errorstr="1";
   		 $("#province").focus();
   		 }
		
		if($("#address").val()!="") {
   		  if(getByteLen($("#address").val())>100){
   		   $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"9px"});
   			errorstr="1";
   			$("#address").focus();
   				}
   		 }else if($("#addressTag").val()!=""){
   		 errorstr="1";
   		 $("#address").focus();
   		 }
   		

   		 if($("#accountName").val()==""){
   			 $("#accountNameTag").text("持卡人姓名不能为空！").css({"color":"red","font-size":"12px"}); 
   			 errorstr="1";
   			 $("#accountName").focus();
			}else if($("#accountName").val()!=$("#userName").val()){
   			 $("#accountNameTag").text("持卡人姓名与用户真实姓名必须一致！").css({"color":"red","font-size":"12px"}); 
   			 errorstr="1";
   			 $("#accountName").focus();
			}
			else if($("#accountNameTag").val()!=""){
			 errorstr="1";
			 $("#accountName").focus();
			 }

				if(errorstr=="1"){
					return false;
				}else{
				$('#myform').attr("action","UserServlet?method=user_empty_save");
		       		 $('#myform').submit();
		       		 return true;
				
		}
		 }   
	   });
   
   
        });
    
        
   </script>
</html>
<%
}
%>
