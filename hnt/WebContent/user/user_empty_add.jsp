<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.StringUtil"%>
<%@ page import="com.ssm.utils.DateUtil"%>
<%@page import="com.ssm.pojo.Product"%>
<%@page import="com.ssm.pojo.News"%>

<%
User user = (User)request.getSession().getAttribute("sys_user");
String token = (String)request.getSession().getAttribute("token");
String psw2 = (String)request.getSession().getAttribute("psw2");
		User new_user = (User)request.getSession().getAttribute("new_user");
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
    <title>空单注册</title>
    
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
<script src="js/jquery.slider.js"></script>
<link rel="stylesheet" href="fonts/fontCss.css">
	<link rel="stylesheet" href="css/jquery.slider.css">
 <script type="text/javascript" src="js/jquery.cityselect.js"></script>
 <link rel="stylesheet" type="text/css" href="css/form.css">
 
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
          		 <%if(user.getCenterId()==2||auth1.substring(14, 15).equals("1")){ %>
          		  <li ><a href="user_add_protocol.jsp">会员注册</a></li>
                    <li ><a href="rankJoin_up_all.jsp">会员升级</a></li>
                     <li  class="now"><a href="UserServlet?method=user_empty_add">空单注册</a></li>
                    <%} %>
                    <%if(auth1.substring(10, 11).equals("1")){ %>
                    <li><a href="UserServlet?method=getBelong">销售关系</a></li>
                    <%}if(auth1.substring(11, 12).equals("1")){ %>
                    <li><a href="UserServlet?method=getReferee">服务关系</a></li>
                    <%} %>
                    <li><a href="AddressServlet?method=list">地址管理</a></li>
                    <%if(user.getCenterId()==2){ %>
                     <li><a href="CenterServlet?method=detail">服务中心</a></li>
                     <%} %>
                </ul>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：业务管理-&gt;空单注册-&gt;<a>会员资料</a></h2>
            	</div>
            		<div class="content content-full-width">
  <form    id="myform" name="myform" method="post">
   <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
      <input type="hidden" name="rankJoin" id="rankJoin" value="1"/> <span id="rankJoinTag"></span>
   
                	<div class="notice">
         <h2>空单注册</h2><span class="required_notification">* 表示必填项</span>
   </div>
  
  			 <table class ="form-table">
  			 <tr>
  			 <td width="12%"><label><span class="required">*</span>服务商编号：</label></td>
  			 <td  width="38%"><input id="refereeId" name="refereeId" value="" type="text">&nbsp;<span id="refereeuser"></span>&nbsp;<span id="refereeuser1"></span></td>
  			 <td width="12%"><label><span class="required">*</span>销售商编号：</label></td>
  			 <td  width="38%"><input id="belongId" name="belongId" value="" type="text" style="width:120px;"> <select id="nodeTag" name="nodeTag">
				<option value="0">-区域-</option>	
				 </select><span id="belonguser"></span><span id="nodeTagmsg"></span></td>
  			 </tr>
  			 
  			 
  			 
  			  <tr>
  			 <td><label><span class="required">*</span>会员编号:</label></td>
  			 <td><input id="userId" name="userId" maxlength="2"  value="" type="text">
                                <span id="idTag"></span></td>
  			 <td><label><span class="required">*</span>真实姓名:</label></td>
  			 <td><input
								id="userName" name="userName" maxlength="20"
								value=''
								type="text"> <span id="nameTag"></span></td>
  			 </tr>
  			 
  			   <tr>
  			 <td><label><span class="required">*</span>登陆密码:</label></td>
  			 <td><input id="password" name="password"  value="" type="password" style="width:170px; height:25px;"> <span id="passwordTag"></span></td>
  			 <td><label><span class="required">*</span>确认密码:</label></td>
  			 <td><input id="password2" name="password2"  value=""  type="password" style="width:170px; height:25px;">
                                <span id="password2Tag"></span></td>
  			 </tr>
  			 
  			   <tr>
  			 <td><label><span class="required">*</span>性别:</label></td>
  			 <td><input type="radio" name="sex" value="男" checked>男 &nbsp;<input type="radio" name="sex" value="女">女 <span id="sexTag"></span></td>
  			 <td><label><span class="required">*</span>证件类型:</label></td>
  			 <td><select
								name="documentType" id="documentType">
									<option value="居民身份证">居民身份证</option>
							</select> <span id="documentTypeTag"></span></td>
  			 </tr>
  			 
  			   <tr>
  			 <td><label><span class="required">*</span>证件号码:</label></td>
  			 <td><input id="numId" name="numId" value='' type="text">&nbsp;<span id="numIdTag"></span></td>
  			 <td><label><span class="required">*</span>手机号码:</label></td>
  			 <td><input id="tel" name="tel"  value='' type="text">&nbsp;<span id="telTag"></span></td>
  			 </tr>
  			 
  			  <tr>
  			 <td><label>固定电话:</label></td>
  			 <td><input id="phone" name="phone" value='' type="text">&nbsp;<span id="phoneTag"></span></td>
  			 <td><label>电子邮箱:</label></td>
  			 <td><input id="email" name="email"  value='' type="text">&nbsp;<span id="emailTag"></span></td>
  			 </tr>
  			 
  			  <tr>
  			 <td><label>QQ号码:</label></td>
  			 <td><input id="qq" name="qq" value='' type="text">&nbsp;<span id="qqTag"></span></td>
  			 <td><label>微信:</label></td>
  			 <td><input id="weixin" name="weixin"  value='' type="text">&nbsp;<span id="weixinTag"></span></td>
  			 </tr>
  			 
  			  <tr>
  			 <td><label><span class="required">*</span>银行账户名:</label></td>
  			 <td><input id="accountName" name="accountName" value='' type="text">&nbsp;<span id="accountNameTag"></span></td>
  			 <td><label><span class="required">*</span>银行卡号:</label></td>
  			 <td><input id="accountId" name="accountId"  value='' type="text">&nbsp;<span id="accountIdTag"></span></td>
  			 </tr>
  			 
  			  <tr>
  			 <td><label><span class="required">*</span>开户银行:</label></td>
  			 <td><select name="bankName" id="bankName">
								<option value="中国农业银行股份有限公司">中国农业银行股份有限公司</option>
										<option value="中国工商银行股份有限公司">中国工商银行股份有限公司</option>
										<option value="中国建设银行股份有限公司">中国建设银行股份有限公司</option>
										<option value="中国银行股份有限公司">中国银行股份有限公司</option>
								</select>&nbsp;<span id="bankNameTag"></span></td>
  			 <td><label><span class="required">*</span>开户网点:</label></td>
  			 <td><input id="bankAdr" name="bankName"  value='' type="text">&nbsp;<span id="bankAdrTag"></span></td>
  			 </tr>
  			 <tr>
  			 <td><label><span class="required">*</span>收货地址：</label></td>
  			 <td colspan="3"><span class="right" id="city_2">
							<select class="prov" id="province" name="province">
  		</select> 
    	<select class="city" id="city" name="city" disabled="disabled">
		</select>
		 <select class="dist" id="area" name="area" disabled="disabled">
		</select> &nbsp;<span id="provinceTag"></span></span></td>
  			
  			 </tr>
  			  <tr>
  			 <td><label><span class="required">*</span>详细地址：</label></td>
  			 <td colspan="3"><input style="width:300px;" maxlength="100" id="address" name="address"
								value='' type="text">&nbsp;<span
								id="addressTag"></span></td>
  			
  			 </tr>
  			 </table>
  			 <div class="clear"></div>
          
              <div class="p-li  text-center">
                	<input type="button"  id="btn1"  class="button blue-sky" value="提交订单">

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
   		 if($("#userName").val()=="")  $("#nameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"9px"}); 
		else if(getByteLen($("#userName").val())>50) $("#nameTag").text("真实姓名字数不能大于25！").css({"color":"red","font-size":"9px"});
		else $("#nameTag").text("");
		
   });
   
    $("#password").blur(function(){
   		 if($("#password").val()=="")  $("#passwordTag").text("登录密码不能为空！").css({"color":"red","font-size":"9px"}); 
   		 else if(getByteLen($("#password").val())<6) $("#passwordTag").text("密码字数不能小于6位数！").css({"color":"red","font-size":"9px"});
		else if(getByteLen($("#password").val())>50) $("#passwordTag").text("密码字数不能大于25位数！").css({"color":"red","font-size":"9px"});
		else $("#passwordTag").text("");
		
   });
    $("#password2").blur(function(){
   		 if($(this).val()!= $("#password").val())  $("#password2Tag").text("两次输入密码不一致不能为空！").css({"color":"red","font-size":"9px"}); 
		else $("#password2Tag").text("");
		
   });
   
    $("#province").blur(function(){
   		 if($("#province").val()=="")
   		  $("#provinceTag").text("省市县不能为空！").css({"color":"red","font-size":"9px"});
   		  else $("#provinceTag").text("");
   	});
   	 $("#city").blur(function(){
   		 if($("#city").val()=="")
   		  $("#provinceTag").text("省市县不能为空！").css({"color":"red","font-size":"9px"});
   		  else $("#provinceTag").text("");
   	});

$("#documentType").blur(function() {
					if ($("#documentType").val() == "")
							$("#documentTypeTag").text("证件类型不能为空！").css({
								"color" : "red",
								"font-size" : "9px"
							});
					 else
						$("#documentTypeTag").text("");
				});

   $("#numId").blur(function(){
   	if($("#documentType").val()=="居民身份证"||$("#documentType").val()=="临时身份证"){
				if(!reg.test($("#numId").val()))
					$("#numIdTag").text("身份证号码格式有误！").css({"color":"red","font-size":"9px"}); 
					else $("#numIdTag").text("");
	}else $("#numIdTag").text("");
   });
   /*
    $("#age").blur(function(){
   	 if($("#age").val()=="")  $("#ageTag").text("年龄不能为空！").css({"color":"red","font-size":"9px"}); 
		else if(!reg1.test($("#age").val())) $("#ageTag").text("年龄格式有误！").css({"color":"red","font-size":"9px"}); 
		else if($("#age").val()>200) $("#ageTag").text("年龄超出了正常范围,请核对！").css({"color":"red","font-size":"9px"}); 
		else if($("#age").val()<18) $("#ageTag").text("年龄未满18岁，未满足加盟资格！").css({"color":"red","font-size":"9px"}); 
		else $("#ageTag").text("");
   });
  */
    
   $("#tel").blur(function(){
   	 if($("#tel").val()!=""){
				if(!(reg2.test($("#tel").val())))
					$("#telTag").text("联系电话格式有误！").css({"color":"red","font-size":"9px"}); 
					else $("#telTag").text("");
	}else{
		$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"9px"});
	}
   });
   
   $("#email").blur(function(){
   	 if($("#email").val()!=""){
				if(!reg3.test($("#email").val()))
					$("#emailTag").text("电子邮箱格式有误！").css({"color":"red","font-size":"9px"}); 
					else $("#emailTag").text("");
	}else $("#emailTag").text("");
   });
   
   $("#qq").blur(function(){
   	 if($("#qq").val()!=""){
				if(!reg1.test($("#qq").val()))
					$("#qqTag").text("QQ号码格式有误！").css({"color":"red","font-size":"9px"}); 
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
						 $("#refereeuser1").text("查无此人，请重新输入！").css({"color":"red","font-size":"9px"});
						  $("#refereeuser").text("");
						 }
						else{
						 $("#refereeuser").text(""+d.userName+"").css({"color":"green","font-size":"9px"});
						  $("#refereeuser1").text("");
						 }
 						$("#belongId").val("");
						  $("#belonguser").text("");
						 $("#belonguser1").text("请根据销售商填写。").css({"color":"red","font-size":"9px"});
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
						"font-size" : "9px"
					});
				}else if($("#userId").val().length!=2){
					$("#idTag").text("长度有误，请输入两位英文字母！").css({
						"color" : "red",
						"font-size" : "9px"
					});
					}
				else
					$("#idTag").text("");
			}else
					$("#idTag").text("请输入两位英文字母！").css({
						"color" : "red",
						"font-size" : "9px"
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
							 $("#nodeTagmsg").text("查无此人，请重新输入！").css({"color":"red","font-size":"9px"});
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
										"font-size" : "9px"
									});
								}
						  $("#belonguser").text(""+d.userName+"").css({"color":"green","font-size":"9px"});
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
					"font-size" : "9px"
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
					$("#rankJoinTag").text("加盟等级不能为空！").css({"color":"red","font-size":"9px"});
					errorstr="1";
					$("#rankJoin").focus();
				}else if($("#rankJoinTag").val()!="") {
					errorstr="1";
					$("#rankJoin").focus();
				}

	   		 if($("#userName").val()=="") {
	   		 	 $("#nameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"9px"}); 
	   		  	errorstr="1";
	   		  	$("#userName").focus();
	   		  }else if($("#nameTag").text()!=""){
	   		   errorstr="1";
	   		   $("#userName").focus();
	   		  		 }
	   		  
	   		  if($("#password").val()=="") {
	   		 	 $("#passwordTag").text("登录密码不能为空！").css({"color":"red","font-size":"9px"}); 
	   		  	errorstr="1";
	   		  	$("#password").focus();
	   		  }else if($("#passwordTag").text()!="") {
	   		  errorstr="1";
	   		  $("#password").focus();
	   		  }
			
	   if($("#password2").val()!=$("#password").val()) {
	   		 	 $("#password2Tag").text("两次输入密码不一致！").css({"color":"red","font-size":"9px"}); 
	   		  	errorstr="1";
	   		  	$("#password2").focus();
	   		  }else if($("#password2Tag").text()!="") {
	   		  errorstr="1";
	   		  $("#password2").focus();
	   		  }
		  	 
		if($("#documentType").val()==""){
				$("#documentTypeTag").text("证件类型不能为空！").css({"color":"red","font-size":"9px"}); 
										errorstr="1";
										$("#documentType").focus();
				}else if($("#documentTypeTag").val()!=""){
				errorstr="1";
				$("#documentType").focus();
				}	

				if($("#documentType").val()=="居民身份证"||$("#documentType").val()=="临时身份证"){
					if(!reg.test($("#numId").val())){
						$("#numIdTag").text("身份证号码格式有误！").css({"color":"red","font-size":"9px"}); 
						errorstr="1";
						$("#numId").focus();
						}
				}else if($("#numIdTag").val()!=""){
					errorstr="1";
					$("#numId").focus();
				}
				if($("#tel").val()!=""){
					if(!(reg2.test($("#tel").val()))){
						$("#telTag").text("手机号码格式有误！").css({"color":"red","font-size":"9px"}); 
						errorstr="1";
						$("#tel").focus();
					}
				
			}else if($("#tel").val()==""){
				$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"9px"}); 
					errorstr="1";
					$("#tel").focus();
			}else if($("#telTag").val()!=""){
				errorstr="1";
				$("#tel").focus();
			}
				
				 if ($(":radio:checked").length == 0)
				{
				$("#sexTag").text("未选择性别！").css({"color":"red","font-size":"9px"});
				errorstr="1";
				
				}
				
				if($("#email").val()!=""){
					if(!reg3.test($("#email").val())){
						$("#emailTag").text("电子邮箱格式有误！").css({"color":"red","font-size":"9px"}); 
						errorstr="1";
						$("#email").focus();
					}
				}else if($("#emailTag").val()!=""){
					errorstr="1";
					$("#email").focus();
				}
				
				if($("#qq").val()!=""){
					if(!reg1.test($("#qq").val())){
						$("#qqTag").text("QQ号码格式有误！").css({"color":"red","font-size":"9px"}); 
						errorstr="1";
						$("#qq").focus();
					}
				}else if($("#qqTag").val()!=""){
					errorstr="1";
					$("#qq").focus();
				}
				if($("#phone").val()!=""){
					if(getByteLen($("#phone").val()) >25){
						$("#phoneTag").text("固定电话格式有误！").css({"color":"red","font-size":"9px"}); 
						errorstr="1";
						$("#phone").focus();
					}
				}else if($("#phoneTag").val()!=""){
					errorstr="1";
					$("#phone").focus();
				}
				if($("#weixin").val()!=""){
					if(getByteLen($("#weixin").val())>50){
						$("#weixinTag").text("微信格式有误！").css({"color":"red","font-size":"9px"}); 
						errorstr="1";
						$("#weixin").focus();
					}
				}else if($("#weixinTag").val()!=""){
					errorstr="1";
					$("#weixin").focus();
				}		
/*
				if($("#age").val()==""){
				 $("#ageTag").text("年龄不能为空！").css({"color":"red","font-size":"9px"}); 
				 errorstr="1";
				 $("#age").focus();
				}else if($("#ageTag").text()!=""){
				errorstr ="1";
				$("#age").focus();
				}
				*/
				if($("#belongId").val()==""){
					$("#nodeTagmsg").text("归属人不能为空！").css({"color":"red","font-size":"9px"});
					$("#belonguser").text("");
					errorstr="1";
					$("#belongId").focus();
				}
				else if($("#nodeTagmsg").text()!=""){
						errorstr="1";
						$("#belongId").focus();
				}
				
				if($("#refereeId").val()==""){
					$("#refereeuser1").text("推荐人不能为空！").css({"color":"red","font-size":"9px"});
					$("#refereeuser").text("");
					errorstr="1";
					 $("#refereeId").focus();
				}else if($("#refereeuser1").text()!=""){
						errorstr="1";
					 $("#refereeId").focus();
				}
				
			if($("#userId").val()==""){
					$("#idTag").text("用户编号不能为空！").css({"color":"red","font-size":"9px"});
					errorstr="1";
					 $("#userId").focus();
				}else if($("#idTag").text()!=""){
						errorstr="1";
						 $("#userId").focus();
				}else{
					if (!reg5.test($("#userId").val())){
						$("#idTag").text("请输入两位英文字母！").css({
						"color" : "red",
						"font-size" : "9px"
					});
					errorstr="1";
					 $("#userId").focus();
						}else if($("#userId").val().length!=2){
					$("#idTag").text("长度有误，请输入两位英文字母！").css({
						"color" : "red",
						"font-size" : "9px"
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
   			 $("#accountNameTag").text("持卡人姓名不能为空！").css({"color":"red","font-size":"9px"}); 
   			 errorstr="1";
   			 $("#accountName").focus();
			}else if($("#accountName").val()!=$("#userName").val()){
   			 $("#accountNameTag").text("持卡人姓名与用户真实姓名必须一致！").css({"color":"red","font-size":"9px"}); 
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
}}
%>