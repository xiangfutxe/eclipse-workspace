<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.News" %>
<%@ include file="check.jsp" %>  

<%
User user = (User)request.getSession().getAttribute("sys_user");
News  news = (News) request.getAttribute("news");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新闻列表</title>
    
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
          		  <li class="now"><a href="NewsServlet?method=list">最新资讯</a></li>
                    <li ><a href="MessageServlet?method=list">留言信箱</a></li>
                   
                </ul>
                <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：新闻资讯-&gt;<a>最新动态</a></h2>
            	</div>
            		<div class="content content-full-width">
  				
                	<div class="notice">
         <h2><%=news.getSort()%></h2><span class="required_notification"><a href="javascript:history.go(-1)">返回上一页</a></span>
   </div>
 
    
			 <div class="p-li">
			 <h4><%=news.getTitle() %></h4>
			  <h5>发布时间：<%=StringUtil.parseToString(news.getEntryTime(), DateUtil.yyyyMMddHHmmss) %></h5>
			 <div class="news-contents">
			<%=news.getContents() %>
			</div>
				  </div>
             
         </div> 
         </div>
               </div>
               </div>
            </div>
</div>
<jsp:include page="footer.jsp" />
</body>
 <script type="text/javascript" src="../js/custom.js"></script>
 <script type="text/javascript">
	$(function() {
		var reg_num = /^[1-9]+[0-9]*]*$/;
	
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
		else if(getByteLen($("#userName").val())>20) $("#nameTag").text("真实姓名字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#nameTag").text("");
		
   });
   
    $("#password").blur(function(){
   		 if($("#password").val()=="")  $("#passwordTag").text("登录密码不能为空！").css({"color":"red","font-size":"12px"}); 
   		 else if(getByteLen($("#password").val())<6) $("#passwordTag").text("密码字数不能小于6位数！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($("#password").val())>20) $("#passwordTag").text("密码字数不能大于25位数！").css({"color":"red","font-size":"12px"});
		else $("#passwordTag").text("");
		
   });
    $("#password2").blur(function(){
   		 if($(this).val()!= $("#password").val())  $("#password2Tag").text("两次输入密码不一致不能为空！").css({"color":"red","font-size":"12px"}); 
		else $("#password2Tag").text("");
		
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
   
 	$("#address").blur(function(){
   		 if($("#address").val()!=""){
   		  if(getByteLen($("#address").val())>100) $("#addressTag").text("联系地址字数不能大于20位数！").css({"color":"red","font-size":"12px"});
   		else $("#addressTag").text("");
   		}else $("#addressTag").text("");
   			});
   			
  	
  	  //给按钮绑定点击事件
  	 $("#refereeId").blur(function(){
  	 	$.ajax({
  	 		type:"post",
  	 		url:"UserServlet?method=refereeAjax_user",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
  	 		data:{//设置数据源
						refereeId:$("input[name=refereeId]").val()//这里不要加","  不然会报错，而且根本不会提示错误地方
					
				},
 
 			dataType:"json",//设置需要返回的数据类型

			success:function(data){
						var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
													//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
						if(d.userName==""){
						 $("#refereeuser1").text("查无此人。").css({"color":"red","font-size":"12px"});
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
					$("#idTag").text("请输入两位英文字母。").css({
						"color" : "red",
						"font-size" : "12px"
					});
				}else if($("#userId").val().length!=2){
					$("#idTag").text("请输入两位英文字母。").css({
						"color" : "red",
						"font-size" : "12px"
					});
					}
				else
					$("#idTag").text("");
			}else
					$("#idTag").text("请输入两位英文字母。").css({
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
						refereeId:$("input[name=refereeId]").val()  //这里不要加","  不然会报错，而且根本不会提示错误地方
				},
 			dataType:"json",//设置需要返回的数据类型
			success:function(data){
						var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
						 if(d.userName==""){
							 $("#nodeTag").empty();
							 $("#nodeTagmsg").text("查无此人。").css({"color":"red","font-size":"12px"});
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
											"AB区域已满。").css({
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

  	   $("#reback").click(function(){
     	window.location.href="user_add_protocol.jsp"; 
        });
  	  $("#btn2").click(function(){
  	  
  	  if (!confirm("确认提交保存会员基本信息？")) {
				return false;
			}else{
		  	 	var errorstr ="";
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
			}else if($("#tprice").val()==""){
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
	   		  	errorstr="2";
	   		  	$("#password").focus();
	   		  }else if($("#passwordTag").text()!="") {
	   		  errorstr="2";
	   		  $("#password").focus();
	   		  }
			alert(errorstr);
	   if($("#password2").val()!=$("#password").val()) {
	   		 	 $("#password2Tag").text("两次输入密码不一致！").css({"color":"red","font-size":"12px"}); 
	   		  	errorstr="3";
	   		  	$("#password2").focus();
	   		  }else if($("#password2Tag").text()!="") {
	   		  errorstr="3";
	   		  $("#password2").focus();
	   		  }
		  	 alert(errorstr);
if($("#documentType").val()==""){
				$("#documentTypeTag").text("证件类型不能为空！").css({"color":"red","font-size":"12px"}); 
										errorstr="4";
										$("#documentType").focus();
				}else if($("#documentTypeTag").val()!=""){
				errorstr="4";
				$("#documentType").focus();
				}	
alert(errorstr);
				if($("#numId").val()==""){
						$("#numIdTag").text("身份证号码不能为空！").css({"color":"red","font-size":"12px"}); 
						errorstr="5";
						$("#numId").focus();
				}else if($("#numIdTag").val()!=""){
					errorstr="5";
					$("#numId").focus();
				}
				if($("#tel").val()!=""){
					if(!(reg2.test($("#tel").val()))){
						$("#telTag").text("手机号码格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr="6";
						$("#tel").focus();
					}
				alert(errorstr);
			}else if($("#tel").val()==""){
				$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr="6";
					$("#tel").focus();
			}else if($("#telTag").val()!=""){
				errorstr="6";
				$("#tel").focus();
			}
				
				 if ($(":radio:checked").length == 0)
				{
				$("#sexTag").text("未选择性别！").css({"color":"red","font-size":"12px"});
				errorstr="7";
				
				}else{
				$("#sexTag").text("");
				}
				alert(errorstr);
				if($("#email").val()!=""){
					if(!reg3.test($("#email").val())){
						$("#emailTag").text("电子邮箱格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr="8";
						$("#email").focus();
					}
				}else if($("#emailTag").val()!=""){
					errorstr="8";
					$("#email").focus();
				}
				alert(errorstr);
				if($("#qq").val()!=""){
					if(!reg1.test($("#qq").val())){
						$("#qqTag").text("QQ号码格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr="9";
						$("#qq").focus();
					}
				}else if($("#qqTag").val()!=""){
					errorstr="9";
					$("#qq").focus();
				}
				if($("#phone").val()!=""){
					if(getByteLen($("#phone").val()) >25){
						$("#phoneTag").text("固定电话格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr="10";
						$("#phone").focus();
					}
				}else if($("#phoneTag").val()!=""){
					errorstr="10";
					$("#phone").focus();
				}
				if($("#weixin").val()!=""){
					if(getByteLen($("#weixin").val())>20){
						$("#weixinTag").text("微信格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr="11";
						$("#weixin").focus();
					}
				}else if($("#weixinTag").val()!=""){
					errorstr="11";
					$("#weixin").focus();
				}		
				alert(errorstr);
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
					errorstr="12";
					$("#belongId").focus();
				}
				else if($("#nodeTagmsg").text()!=""){
						errorstr="12";
						$("#belongId").focus();
				}
				alert(errorstr);
				if($("#refereeId").val()==""){
					$("#refereeuser1").text("推荐人不能为空！").css({"color":"red","font-size":"12px"});
					$("#refereeuser").text("");
					errorstr="13";
					 $("#refereeId").focus();
				}else if($("#refereeuser1").text()!=""){
						errorstr="13";
					 $("#refereeId").focus();
				}
				
			if($("#userId").val()==""){
					$("#idTag").text("用户编号不能为空！").css({"color":"red","font-size":"12px"});
					errorstr="14";
					 $("#userId").focus();
				}else if($("#idTag").text()!=""){
						errorstr="14";
						 $("#userId").focus();
				}else{
					if (!reg5.test($("#userId").val())){
						$("#idTag").text("请输入两位英文字母！").css({
						"color" : "red",
						"font-size" : "12px"
					});
					errorstr="14";
					 $("#userId").focus();
						}else if($("#userId").val().length!=2){
					$("#idTag").text("长度有误，请输入两位英文字母！").css({
						"color" : "red",
						"font-size" : "12px"
					});
				errorstr="14";
				 $("#userId").focus();
					}				
				}
		alert(errorstr);	
		if($("#province").val()=="") {
   		   $("#provinceTag").text("省市县不能为空！").css({"color":"red","font-size":"12px"});
   			errorstr="15";
   			$("#province").focus();
   				
   		 }else if($("#city").val()=="") {
   		   $("#provinceTag").text("省市县不能为空！").css({"color":"red","font-size":"12px"});
   			errorstr="15";
   			$("#city").focus();
   		 }else if($("#provinceTag").val()!=""){
   		 errorstr="15";
   		 $("#province").focus();
   		 }
		alert(errorstr);
		if($("#address").val()!="") {
   		  if(getByteLen($("#address").val())>100){
   		   $("#addressTag").text("联系地址字数不能大于100位数！").css({"color":"red","font-size":"12px"});
   			errorstr="16";
   			$("#address").focus();
   				}
   		 }else if($("#addressTag").val()!=""){
   		 errorstr="16";
   		 $("#address").focus();
   		 }
   		
alert(errorstr);
   		 if($("#accountName").val()==""){
   			 $("#accountNameTag").text("持卡人姓名不能为空！").css({"color":"red","font-size":"12px"}); 
   			 errorstr="17";
   			 $("#accountName").focus();
			}else if($("#accountName").val()!=$("#userName").val()){
   			 $("#accountNameTag").text("持卡人姓名与用户真实姓名必须一致！").css({"color":"red","font-size":"12px"}); 
   			 errorstr="17";
   			 $("#accountName").focus();
			}
			else if($("#accountNameTag").val()!=""){
			 errorstr="17";
			 $("#accountName").focus();
			 }
				if(errorstr!=""||error>0){
				alert(errorstr);
					return false;
				}else{
				$('#myform').attr("action","UserServlet?method=user_save");
		       		 $('#myform').submit();
		       		 return true;
		}
		 }   
	   });
	   
	   $("#btn1").bind("click", function() {
			$.ajax({
				type : "post",
				url : "UserServlet?method=moneyAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					userId : $("input[name=declareId]").val()
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
						else if(!reg_num.test($num[i].value)){
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
					if(error>0)	$("#msg").text("产品数量的格式有误，请重新核对！").css({"color":"red","font-size":"12px"});
					else if (d.emoney-totalprice<0) $("#msg").text("当前账户报单券余额为"+d.emoney+"元不足以选购当前等级会员！").css({"color":"red","font-size":"12px"});
					else if (emoney>totalprice) $("#msg").text("您选购的产品与你所选会员等级标准不符合！").css({"color":"red","font-size":"12px"});
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
			
			
    });
   
  </script>      
</html>
<%
}
%>