<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.pojo.Admin" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][0].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>空单注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.cityselect.js"></script>
</head>
<body>
	<div class="container clearfix">
		<div class="main-wrap">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="admin/index.jsp">首页</a><span
						class="crumb-step">&gt;</span>会员管理 <span class="crumb-step">&gt;</span>
					<span><a href="admin/UserServlet.do?method=add">空单注册</a>
					</span>
				</div>
			</div>
			<div class="result-wrap">
				<form action="admin/UserServlet.do?method=admin_user_save" id="myform" name="myform"
					method="post" >
  <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
					<div class="result-content">
						<table class="insert-tab" width="97%">
							<tbody>
								<tr>
									<td colspan="2"><h4>
											<i class="require-red">*</i>加盟信息：
										</h4>
									</td>
								</tr>
<tr>
									<th><i class="require-red">*</i>会员级别：</th>
									<td><select id="rankJoin" name="rankJoin"
										class="common-text required">
											<option value="0">--请选择会员级别--</option>
											<option value="2">普通VIP会员</option>
											<option value="3">银级VIP会员</option>
											<option value="4">金级VIP会员</option>
									</select>&nbsp;<span id="rankJoinTag"></span></td>

								</tr>
								<tr>
									<th><i class="require-red">*</i>客服编号：</th>
									<td><input class="common-text required" id="refereeId"
										name="refereeId" value="" type="text">&nbsp;推荐人姓名：<span
										id="refereeuser"></span></td>
								</tr>
								
								
								<tr>
									<td colspan="2"><h4>
											<i class="require-red">*</i>会员资料：
										</h4>
									</td>
								</tr>
								
								<tr>
									<th width="150px"><i class="require-red">*</i>真实姓名：</th>
									<td><input class="common-text required" id="userName"
										name="userName" value="" type="text"> <span
										id="nameTag"></span></td>
								</tr>
								<tr>
									<th><i class="require-red">*</i>登录密码：</th>
									<td><input class="common-text required" id="password"
										name="password" value="" type="password"> <span
										id="passwordTag"></span></td>
								</tr>
								<tr>
								<tr>
									<th><i class="require-red">*</i>确认密码：</th>
									<td><input class="common-text required" id="password2"
										name="password2" value="" type="password"> <span
										id="password2Tag"></span></td>
								</tr>
								<tr>
									<th><i class="require-red">*</i>性别：</th>
									<td><input type="radio" name="sex" value="男" checked>男
										&nbsp;<input type="radio" name="sex" value="女">女 <span
										id="sexTag"></span>
								</tr>
								<tr>
									<th><i class="require-red">*</i>证件类型：</th>
									<td><select name="documentType" id="documentType"
										class="common-text required">
											<option value="居民身份证">居民身份证</option>
									</select> <span id="documentTypeTag"></span></td>
								</tr>
								<tr>
									<th><i class="require-red">*</i>证件号码：</th>
									<td><input class="common-text required" id="numId"
										name="numId" value="" type="text">&nbsp;<span
										id="numIdTag"></span></td>

								</tr>
								<tr>
									<th><i class="require-red">*</i>手机号码：</th>
									<td><input class="common-text required" id="tel"
										name="tel" value="" type="text" maxlength="13">&nbsp;<span
										id="telTag"></span></td>

								</tr>
								<tr>
									<th>固定电话：</th>
									<td><input class="common-text required" id="phone"
										name="phone" value="" type="text" maxlength="13">&nbsp;<span
										id="phoneTag"></span></td>

								</tr>
								<tr>
									<th>电子邮箱：</th>
									<td><input class="common-text required" id="email"
										name="email" value="" type="text">&nbsp;<span
										id="emailTag"></span></td>

								</tr>
								<tr>
									<th>QQ号码：</th>
									<td><input class="common-text required" id="qq" name="qq"
										value="" type="text">&nbsp;<span id="qqTag"></span></td>

								</tr>
									<tr>
									<th>微信：</th>
									<td><input class="common-text required" id="weixin" name="weixin"
										value="" type="text">&nbsp;<span id="weixinTag"></span></td>

								</tr>
								<tr>
									<th><i class="require-red">*</i>经营区域：</th>
									<td>
										<div id="city_2">
											<select class="prov common-text " id="province"
												name="province"></select> <select class="city common-text "
												id="city" name="city" disabled="disabled">
											</select> <select class="dist common-text " id="area" name="area"
												disabled="disabled"></select> &nbsp;<span id="provinceTag"></span>
										</div>
									</td>

								</tr>
								<tr>
									<th>联系地址：</th>
									<td><input class="common-text required" id="address"
										name="address" value="" type="text">&nbsp;<span
										id="addressTag"></span></td>

								</tr>
								<tr>
									<td colspan="2"><h4>
											<i class="require-red">*</i>银行账户：
										</h4>
									</td>
								</tr>
								<tr>
									<th><i class="require-red">*</i>持卡人姓名：</th>
									<td><input class="common-text required" id="accountName"
										name="accountName" value="" type="text">&nbsp;<span
										id="accountNameTag"></span></td>

								</tr>
								<tr>
									<th>银行卡号：</th>
									<td><input class="common-text required" id="accountId"
										name="accountId" value="" type="text">&nbsp;<span
										id="accountIdTag"></span></td>
								</tr>
								<tr>
									<th>开户银行：</th>
									<td><select class="common-text required" id="bankName"
										name="bankName">
										<option value="中国工商银行股份有限公司">中国工商银行股份有限公司</option>
										<option value="中国农业银行股份有限公司">中国农业银行股份有限公司</option>
										<option value="中国建设银行股份有限公司">中国建设银行股份有限公司</option>
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
								&nbsp;<span
										id="bankNameTag"></span></td>
								</tr>
								<tr>
									<th>开户网点：</th>
									<td><input class="common-text required" id="bankAdr"
										name="bankAdr" value="" type="text">&nbsp;<span
										id="bankAdrTag"></span></td>
								</tr>
								<tr>

								</tr>
								<tr>
									<th><input id="btn1" class="btn btn-primary" value="提交"
										type="button">
									</th>
									<td><input type="reset" class="btn" value="重置"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>

		</div>
		<!--/main-->
	</div>
</body>
<script type="text/javascript" src="js/custom.js"></script>
<script type="text/javascript">
	$(function() {
		var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; //验证身份证号码
		var reg1 = /^[1-9]+[0-9]*]*$/; //验证整数
		var reg2 = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证
		var reg3 = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; //验证电子邮箱
		// var reg4 =  /^(?!(\d)\1{1,}$)[1-9]\d{4,}$/;//验证QQ号码
		var reg5 = /^([A-Za-z])+$/; //验证数字和字母的组合
		var reg6 = /^[0-9a-zA-Z]*$/; //验证数字组合
		var reg6 = /^[0-9a-zA-Z]*$/; //验证数字组合
		var reg7=/^\d+(\.\d+)?$/;//验证正数或者浮点数

		$("#city_2").citySelect({
			nodata : "none",
			required : false
		});

		
		$("#userName").blur(function() {
			if ($("#userName").val() == "")
				$("#nameTag").text("真实姓名不能为空！").css({
					"color" : "red",
					"font-size" : "12px"
				});
			else if (getByteLen($("#userName").val()) > 50)
				$("#nameTag").text("真实姓名字数不能大于25！").css({
					"color" : "red",
					"font-size" : "12px"
				});
			else
				$("#nameTag").text("");

		});
		
		
		$("#accountName").blur(function() {
			if ($("#accountName").val() == "")
				$("#accountNameTag").text("持卡人姓名不能为空！").css({
					"color" : "red",
					"font-size" : "12px"
				});
			else if (getByteLen($("#accountName").val()) > 50)
				$("#accountNameTag").text("持卡人姓名字数不能大于25！").css({
					"color" : "red",
					"font-size" : "12px"
				});
			else if($("#accountName").val()!=$("#userName").val()){
   			 $("#accountNameTag").text("持卡人姓名与用户真实姓名必须一致！").css({"color":"red","font-size":"12px"}); 
   			 $("#accountName").focus();
			}
			else
				$("#accountNameTag").text("");

		});

		$("#password").blur(function() {
			if ($(this).val() == "")
				$("#passwordTag").text("登录密码不能为空！").css({
					"color" : "red",
					"font-size" : "12px"
				});
			else if (getByteLen($("#password").val()) < 6)
				$("#passwordTag").text("密码字数不能小于6位数！").css({
					"color" : "red",
					"font-size" : "12px"
				});
			else if (getByteLen($("#password").val()) > 50)
				$("#passwordTag").text("密码字数不能大于25位数！").css({
					"color" : "red",
					"font-size" : "12px"
				});
			else
				$("#passwordTag").text("");

		});
		$("#password2").blur(function() {
			if ($(this).val() != $("#password").val())
				$("#password2Tag").text("两次输入密码不一致不能为空！").css({
					"color" : "red",
					"font-size" : "12px"
				});
			else
				$("#password2Tag").text("");

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
				
		$("#numId").blur(
				function() {
					if ($("#documentType").val() == "居民身份证"
							|| $("#documentType").val() == "临时身份证") {
						if (!reg.test($("#numId").val()))
							$("#numIdTag").text("身份证号码格式有误！").css({
								"color" : "red",
								"font-size" : "12px"
							});
						else
							$("#numIdTag").text("");
					} else
						$("#numIdTag").text("");
				});
				/*
		$("#age").blur(function() {
			if ($(this).val() == "")
				$("#ageTag").text("年龄不能为空！").css({
					"color" : "red",
					"font-size" : "12px"
				});
			else if (!reg1.test($(this).val()))
				$("#ageTag").text("年龄格式有误！").css({
					"color" : "red",
					"font-size" : "12px"
				});
			else if ($(this).val().trim().length > 3)
				$("#ageTag").text("年龄超出了正常范围,请核对！").css({
					"color" : "red",
					"font-size" : "12px"
				});
			else
				$("#ageTag").text("");

		});
		*/
	
		$("#tel").blur(function() {
			if ($(this).val() != "") {
				if (!reg2.test($(this).val()))
					$("#telTag").text("联系电话格式有误！").css({
						"color" : "red",
						"font-size" : "12px"
					});
				else
					$("#telTag").text("");
			}
		});
		
		$("#backfill").blur(function() {
			if ($(this).val() != "") {
				if (!reg7.test($(this).val()))
					$("#backfillTag").text("回填金额格式有误，如无需回填，请输入‘0’！").css({
						"color" : "red",
						"font-size" : "12px"
					});
				else
					$("#backfillTag").text("");
			}else
					$("#backfillTag").text("回填金额格式有误，如无需回填，请输入‘0’！").css({
						"color" : "red",
						"font-size" : "12px"
					});
		});

		$("#email").blur(function() {
			if ($(this).val() != "") {
				if (!reg3.test($(this).val()))
					$("#emailTag").text("电子邮箱格式有误！").css({
						"color" : "red",
						"font-size" : "12px"
					});
				else
					$("#emailTag").text("");
			}
		});

		$("#qq").blur(function() {
			if ($(this).val() != "") {
				if (!reg1.test($(this).val()))
					$("#qqTag").text("QQ号码格式有误！").css({
						"color" : "red",
						"font-size" : "12px"
					});
				else
					$("#qqTag").text("");

			}
		});

			$("#rankJoin").blur(function() {
			if ($(this).val() == "")
				$("#rankJoinTag").text("加盟等级不能为空！").css({
					"color" : "red",
					"font-size" : "12px"
				});
			else
				$("#rankJoinTag").text("");

		});

$("#province").blur(function(){
   		 if($("#province").val()=="")$("#provinceTag").text("经营区域选择不完整！").css({"color":"red","font-size":"12px"});
   			else $("#provinceTag").text("");
   			});
   			$("#city").blur(function(){
   		 if($("#city").val()=="")$("#provinceTag").text("经营区域选择不完整！").css({"color":"red","font-size":"12px"});
   			else $("#provinceTag").text("");
   			});
		
		$("#address").blur(function(){
   		 if($("#address").val()!=""){
   		  if(getByteLen($("#address").val())>100) $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"12px"});
   		else $("#addressTag").text("");
   		}else $("#addressTag").text("");
   			});
   			

		//给按钮绑定点击事件
		$("#refereeId").blur(function() {
			$.ajax({
				type : "post",
				url : "admin/UserServlet.do?method=userAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					userId : $("input[name=refereeId]").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型

				success : function(data) {
					var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					if (d.userName == "") {
						$("#refereeuser").text("查无此人，请重新输入！").css({
							"color" : "red",
							"font-size" : "12px"
						});
					} else if(d.state==0){
								$("#refereeuser").text("查无此人，请重新输入！").css({
									"color" : "red",
									"font-size" : "12px"
								});
					} else {
						$("#refereeuser").text("" + d.userName + "").css({
							"color" : "green",
							"font-size" : "12px"
						});
					}

				},

				error : function() {

					alert("系统异常，请稍后重试！");

				}//这里不要加","
			});
		});

		$("#btn1").click(function() {
							if (!confirm("确认提交注册空单会员？")) {
								return false;
							} else {
								var errorstr =0;
			 if($("#rankJoin").val()==""||$("#rankJoin").val()=="0"){
					$("#rankJoinTag").text("加盟等级不能为空！").css({"color":"red","font-size":"12px"});
					errorstr++;
					$("#rankJoin").focus();
				}else if($("#rankJoinTag").val()!="") {
					errorstr++;
					$("#rankJoin").focus();
				}
				
	   		 if($("#userName").val()=="") {
	   		 	 $("#nameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"}); 
	   		 	 $("#userName").focus();
	   		  	errorstr++;
	   		  }else if($("#nameTag").text()!=""){
	   		   errorstr++;
	   		    $("#userName").focus();
	   		   }
	   		  
	   		  if($("#password").val()=="") {
	   		 	 $("#passwordTag").text("登录密码不能为空！").css({"color":"red","font-size":"12px"}); 
	   		 	  $("#password").focus();
	   		  	errorstr++;
	   		  }else if($("#passwordTag").text()!=""){
	   		   errorstr++;
	   		    $("#password").focus();
	   		   }
			
	  	 if($("#password2").val()!=$("#password").val()) {
	   		 	 $("#password2Tag").text("两次输入密码不一致！").css({"color":"red","font-size":"12px"}); 
	   		  	errorstr++;
	   		  	 $("#password2").focus();
	   		}else if($("#password2Tag").text()!=""){
	   		 errorstr++;
	   		  $("#password2").focus();
	   		 }

				if($("#documentType").val()==""){
				$("#documentTypeTag").text("证件类型不能为空！").css({"color":"red","font-size":"12px"}); 
										errorstr++;
										 $("#documentType").focus();
				}else if($("#documentTypeTag").val()!=""){
				errorstr++;
				 $("#documentType").focus();
				}		  	 
				if($("#numId").val()==""){
				$("#numIdTag").text("证件号码不能为空！").css({"color":"red","font-size":"12px"}); 
										errorstr++;
										 $("#numId").focus();
				}else if($("#documentType").val()=="居民身份证"||$("#documentType").val()=="临时身份证"){
					if(!reg.test($("#numId").val())){
						$("#numIdTag").text("身份证号码格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr++;
						 $("#numId").focus();
					}
				}else if($("#numIdTag").val()!=""){
					errorstr++;
					 $("#numId").focus();
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
				
				 if ($(":radio:checked").length == 0)
				{
				$("#sexTag").text("未选择性别！").css({"color":"red","font-size":"12px"});
				errorstr++;
			
				}
				
				if($("#email").val()!=""){
					if(!reg3.test($("#email").val())){
						$("#emailTag").text("电子邮箱格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr++;
							$("#email").focus();
					}
				}else if($("#emailTag").val()!=""){
					errorstr++;
					$("#email").focus();
				}
				
				if($("#backfill").val()!=""){
					if(!reg7.test($("#backfill").val())){
						$("#backfillTag").text("回填金额格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr++;
							$("#backfill").focus();
					}
				}else if($("#backfillTag").val()!=""){
					errorstr++;
					$("#backfill").focus();
				}
				
				if($("#qq").val()!=""){
					if(!reg1.test($("#qq").val())){
						$("#qqTag").text("QQ号码格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr++;
					}
				}else if($("#qqTag").val()!=""){
					errorstr++;
				}
				if($("#phone").val()!=""){
					if(getByteLen($("#phone").val()) >25){
						$("#phoneTag").text("固定电话格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr++;
					}
				}else if($("#phoneTag").val()!=""){
					errorstr++;
				}
				if($("#weixin").val()!=""){
					if(getByteLen($("#weixin").val())>50){
						$("#weixinTag").text("微信格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr++;
					}
				}else if($("#weixinTag").val()!=""){
					errorstr++;
				}		
				/*
				if($("#age").val()==""){
				 $("#ageTag").text("年龄不能为空！").css({"color":"red","font-size":"12px"}); 
				 errorstr++;
				}else if($("#ageTag").text()!="") errorstr ="1";
*/
				if($("#belongId").val()==""){
					$("#belonguser").text("归属人不能为空！").css({"color":"red","font-size":"12px"});
					errorstr++;
				}
				else if($("#belonguser").text()=="该用户的AB区域已满，请重新选择安置用户！"
				||$("#belonguser").text()=="归属人不能为空！"
				|| $("#belonguser").text()=="查无此人，请重新输入！"){
						errorstr++;
				}
				
				if($("#refereeId").val()==""){
					$("#refereeuser").text("推荐人不能为空！").css({"color":"red","font-size":"12px"});
					errorstr++;
				}
				else if($("#refereeuser").text()=="推荐人不能为空！"
				|| $("#refereeuser").text()=="查无此人，请重新输入！"){
						errorstr++;
				}
			
				
		if($("#province").val()=="") {
   		   $("#provinceTag").text("经营区域选择不完整！").css({"color":"red","font-size":"12px"});
   			errorstr = errorstr++;
   		 }
   		else if($("#provinceTag").val()!="") errorstr++;
   		
   		if($("#city").val()=="") {
   		   $("#provinceTag").text("经营区域选择不完整！").css({"color":"red","font-size":"12px"});
   			errorstr = errorstr++;
   		 }
   		else if($("#provinceTag").val()!="") errorstr++;
		
   		if($("#address").val()!="") {
   		  if(getByteLen($("#address").val())>100){
   		   $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"12px"});
   			errorstr = errorstr++;
   				}
   		 }
   		else if($("#addressTag").val()!="") errorstr++;
   		
   		 if($("#accountName").val()==""){
   			 $("#accountNameTag").text("持卡人姓名不能为空！").css({"color":"red","font-size":"12px"}); 
   			 errorstr++;
			}else if($("#accountName").val()!=$("#userName").val()){
   			 $("#accountNameTag").text("持卡人姓名与用户真实姓名必须一致！").css({"color":"red","font-size":"12px"}); 
   			 errorstr++;
   			 $("#accountName").focus();
			}else if($("#accountNameTag").val()!="") errorstr++;
   		
				if(errorstr!=""){
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

<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>
