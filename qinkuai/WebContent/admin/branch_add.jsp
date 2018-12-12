<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.pojo.User" %>
<%@page import="com.pojo.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
User user1 = (User) request.getAttribute("user1");
	if (admin == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	 String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[1][1].equals("1")||admin.getState().equals("1")){

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增分店</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
      <script type="text/javascript" src="js/jquery.js"></script>
       <script type="text/javascript" src="js/custom.js"></script>
       <script type="text/javascript" src="js/jquery.cityselect.js"></script>
     
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>分店管理<span class="crumb-step">&gt;</span><span>分店新增</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                 <form action="admin/BranchServlet.do?method=admin_save" method="post" id="myform" name="myform">
                     <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>店铺编号：</th>
                                <td><input type="text" name="code" id="code" value=""><span id="codeTag"></span><span id="codeTag1"></span>
                                </td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red">*</i>店铺名称：</th>
                               <td><input
							id="name" name="name" value='' type="text">&nbsp;<span
							id="nameTag"></span></td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red">*</i>店商类型：</th>
                               <td><select id="type" name="type">
							<option value="">--请选择店商类型--</option>
							<option value="1">--加盟店--</option>
							<option value="2">--直营店--</option>
							<option value="3">--外购商--</option>
							</select>&nbsp;<span
							id="typeTag"></span></td>
                            </tr>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>登陆密码：</th>
                                <td><input type="password" value='' name="psw" id="psw">&nbsp;<span
							id="pswTag"></span></td>
                            </tr>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>确认密码：</th>
                                <td><input type="password" value=''
							name="psw2" id="psw2">&nbsp;<span
							id="psw2Tag"></span></td>
                            </tr>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>负责人：</th>
                                <td><input
							id="linkman" name="linkman" value='' type="text">&nbsp;<span
							id="linkmanTag"></span></td>
                            </tr>
                              <tr>
                                <th width="150px"><i class="require-red">*</i>手机号码：</th>
                                <td><input type="text" value=''
							name="tel" id="tel">&nbsp;<span id="telTag"></span></td>
                            </tr>
                            <tr>
                                <th width="150px"><i class="require-red"></i>固定电话：</th>
                                <td><input type="text" value=''
							name="phone" id="phone">&nbsp;<span id="phoneTag"></span></td>
                            </tr>
                            
                            <tr>
									<th><i class="require-red">*</i>省市县：</th>
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
                                <th width="150px"><i class="require-red">*</i>详细地址：</th>
                                <td><input type="text" value=''
							name="address" id="address">&nbsp;<span id="addressTag"></span></td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red">*</i>授信额度：</th>
                                <td><input type="text" name="credit"
							id="credit" value="0">&nbsp;<span id="creditTag"></span></td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red">*</i>预付款额度：</th>
                                <td><input type="text" name="advancePayment"
							id="advancePayment" value="0">&nbsp;<span id="advancePaymentTag"></span></td>
                            </tr>
                              <tr>
                                <th width="150px"><i class="require-red">*</i>最低起购额：</th>
                                <td><input type="text" name="standardMin"
							id="standardMin" value="0">&nbsp;<span id="standardMinTag"></span></td>
                            </tr>
                             <tr>
                                <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary btn4 mr10" value="提交" type="button">
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
        </div>

    </div>
    <!--/main-->
    </div>
  </body>
   <script type="text/javascript" src="js/custom.js"></script>
 <script language="JavaScript">

$(function() {
	var reg = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证
	
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; //验证手机号码
	var reg1 = /^[0-9]*$/; //验证非负整数
		$("#city_2").citySelect({
			nodata : "none",
			required : false
		});
		
		$("#code").blur(
				function() {
					$.ajax({
						type : "post",
						url : "admin/BranchServlet.do?method=codeAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
						data : {//设置数据源
							code : $("#code").val()
						//这里不要加","  不然会报错，而且根本不会提示错误地方
						},

						dataType : "json",//设置需要返回的数据类型

						success : function(data) {
							var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
							if (d.tag == "4") {
								$("#codeTag").text("管理员未登陆，请重新登陆！").css({
									"color" : "red",
									"font-size" : "12px"
								});
								$("#codeTag1").text("");
							} else if (d.tag == "3") {
								$("#codeTag").text("数据库连接失败，请稍后再试！").css({
									"color" : "red",
									"font-size" : "12px"
								});
								$("#codeTag1").text("");
							} else if (d.tag == "2") {
								$("#codeTag").text("店铺编号不能为空，请重新输入！").css({
									"color" : "red",
									"font-size" : "12px"
								});
							} else if (d.tag == "1") {
								$("#codeTag").text("店铺编号已经存在，请重新输入！").css({
									"color" : "red",
									"font-size" : "12px"
								});
								$("#codeTag1").text("");
							} else if(d.tag==0){
								$("#codeTag1").text("店铺编号可用！").css({
									"color" : "green",
									"font-size" : "12px"
								});
								$("#codeTag").text("");
							}else{
									$("#codeTag").text("");
									$("#codeTag1").text("");
							}

						},

						error : function() {
							$("#codeTag").text("系统异常，请稍后重试！").css({
									"color" : "red",
									"font-size" : "12px"
								});

						}//这里不要加","
					});
				});
	
	$("#name").blur(function(){
			 if($(this).val()=="")  $("#nameTag").text("服务店名称不能为空！").css({"color":"red","font-size":"12px"}); 
   		 else if(getByteLen($("#name").val())>50) $("#nameTag").text("服务店名称字数不能大于25位数！").css({"color":"red","font-size":"12px"});
	else $("#nameTag").text("");
		});
		
		
		
		$("#credit").blur(function(){
			 if($(this).val()=="")  $("#creditTag").text("授信额度不能为空！").css({"color":"red","font-size":"12px"}); 
	   		else if(!(reg1.test($(this).val())))
	   		$("#creditTag").text("授信额度格式有误！").css({"color":"red","font-size":"12px"}); 
	   		else $("#creditTag").text("");
		});
		
		$("#advancePayment").blur(function(){
			 if($(this).val()=="")  $("#advancePaymentTag").text("预付款不能为空！").css({"color":"red","font-size":"12px"}); 
	   		else if(!(reg1.test($(this).val())))
	   		$("#advancePaymentTag").text("预付款格式有误！").css({"color":"red","font-size":"12px"}); 
	   		else $("#advancePaymentTag").text("");
		});
		$("#standardMin").blur(function(){
			 if($(this).val()=="")  $("#standardMinTag").text("最低起购额不能为空！").css({"color":"red","font-size":"12px"}); 
	   		else if(!(reg1.test($(this).val())))
	   		$("#standardMinTag").text("最低起购额格式有误！").css({"color":"red","font-size":"12px"}); 
	   		else $("#standardMinTag").text("");
		});
		
		$("#linkman").blur(function(){
			 if($(this).val()=="")  $("#linkmanTag").text("负责人名称不能为空！").css({"color":"red","font-size":"12px"}); 
   		
			else $("#linkmanTag").text("");

		});
		
		$("#type").blur(function(){
			 if($(this).val()=="")  $("#typeTag").text("店商类型不能为空！").css({"color":"red","font-size":"12px"}); 
   		
			else $("#typeTag").text("");

		});
	
	$("#psw").blur(function(){
   		 if($(this).val()=="")  $("#pswTag").text("服务店密码不能为空！").css({"color":"red","font-size":"12px"}); 
   		 else if(getByteLen($("#psw").val())<6) $("#pswTag").text("密码字数不能小于6位数！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($("#psw").val())>50) $("#pswTag").text("密码字数不能大于25位数！").css({"color":"red","font-size":"12px"});
		else $("#pswTag").text("");
		
   });
   
   $("#psw2").blur(function(){
   		 if($(this).val()!=$("#psw").val())  $("#psw2Tag").text("两次输入密码不一致！").css({"color":"red","font-size":"12px"}); 
   		
		else $("#psw2Tag").text("");
		
   });
	
	
 	
   
    	$("#phone").blur(function(){
   			if($(this).val()!=""){
				if(!(reg.test($(this).val())))
					$("#phoneTag").text("固定电话格式有误！").css({"color":"red","font-size":"12px"}); 
					else $("#phoneTag").text("");
			}
	
   		});
   		
   		$("#tel").blur(function(){
   			if($("#tel").val()=="")
   				$("#telTag").text("手机号码格式不能为空！").css({"color":"red","font-size":"12px"}); 

				else if(!(myreg.test($(this).val())))
					$("#telTag").text("手机号码格式有误！").css({"color":"red","font-size":"12px"}); 
					else $("#telTag").text("");
	
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
   
	
	$("#sbtn").click(function() {
	  if (!confirm("确认提交申请？")) {
				window.event.returnValue = false;
			}else{
	var errorstr ="";
		if($("#code").val()==""){
					$("#codeTag").text("店铺编号不能为空！").css({"color":"red","font-size":"12px"}); 
					$("#codeTag1").text("");
					errorstr =errorstr+"1";
			}else if($("#codeTag").text()!=""){
				errorstr =errorstr+"1";
				$("#codeTag1").text("");
			}else if($("#codeTag1").text()==""){
			$("#codeTag").text("店铺编号匹配失败！").css({"color":"red","font-size":"12px"}); 
				errorstr =errorstr+"1";
				$("#codeTag1").text("");
			}
			if($("#name").val()==""){
					$("#nameTag").text("店铺名称不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#nameTag").text()!=""){
				errorstr =errorstr+"1";
			}
			if($("#credit").val()==""){
					$("#creditTag").text("授信额度不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
			}else if($("#creditTag").text()!=""){
				errorstr =errorstr+"1";
			}
			if($("#advancePayment").val()==""){
			  $("#advancePaymentTag").text("预付款不能为空！").css({"color":"red","font-size":"12px"}); 
			  errorstr =errorstr+"1";
			 }
	   		else if(!(reg1.test($("#advancePayment").val()))){
		   		$("#advancePaymentTag").text("预付款格式有误！").css({"color":"red","font-size":"12px"}); 
		   		errorstr =errorstr+"1";
	   		}
	   		else if($("#advancePaymentTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			
			if($("#standardMin").val()==""){
			  $("#standardMinTag").text("最低起购额不能为空！").css({"color":"red","font-size":"12px"}); 
			  errorstr =errorstr+"1";
			 }
	   		else if(!(reg1.test($("#standardMin").val()))){
	   		$("#standardMinTag").text("最低起购额格式有误！").css({"color":"red","font-size":"12px"}); 
	   		errorstr =errorstr+"1";
	   		}
	   		else if($("#standardMintTag").text()!=""){
				errorstr =errorstr+"1";
			}
			if($("#type").val()==""){
					$("#typeTag").text("店商类型不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
			}else if($("#typeTag").text()!=""){
				errorstr =errorstr+"1";
			}
			if($("#address").val()==""){
					$("#addressTag").text("联系地址不能为空！").css({"color":"red","font-size":"12px"}); 
					
					errorstr =errorstr+"1";
				
			}else if($("#addressTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#address").val()==""){
					$("#addressTag").text("联系地址不能为空！").css({"color":"red","font-size":"12px"}); 
					
					errorstr =errorstr+"1";
				
			}else if($("#addressTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			
   		 if($("#psw").val()=="") {
			 $("#pswTag").text("服务店密码不能为空！").css({"color":"red","font-size":"12px"}); 
			 errorstr =errorstr+"1";
		 }
   		 else if(getByteLen($("#psw").val())<6) {
			 $("#pswTag").text("密码字数不能小于6位数！").css({"color":"red","font-size":"12px"});
			 errorstr =errorstr+"1";
		 }
		else if(getByteLen($("#psw").val())>50){
			 $("#pswTag").text("密码字数不能大于25位数！").css({"color":"red","font-size":"12px"});
			errorstr =errorstr+"1";
		}
		else if($("#pswTag").val()!=""){
			errorstr =errorstr+"1";
		}
   		 if($("#psw2").val()!=$("#psw").val()) {
			 $("#psw2Tag").text("两次输入密码不一致！").css({"color":"red","font-size":"12px"}); 
			 errorstr =errorstr+"1";
		 } else if($("#psw2Tag").val()!=""){
			  errorstr =errorstr+"1";
		 }
			
			if(!(myreg.test($("#tel").val()))){
					$("#telTag").text("手机号码格式有误！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#telTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
			 if($("#phoneTag").text()!=""){
				errorstr =errorstr+"1";
			}
			
		if($("#province").val()=="") {
   		   $("#provinceTag").text("经营区域选择不完整！").css({"color":"red","font-size":"12px"});
   			errorstr =errorstr+"1";
   		 }
   		else if($("#provinceTag").val()!="") errorstr =errorstr+"1";
   		
   		if($("#city").val()=="") {
   		   $("#provinceTag").text("经营区域选择不完整！").css({"color":"red","font-size":"12px"});
   			errorstr =errorstr+"1";
   		 }
   		else if($("#provinceTag").val()!="") errorstr =errorstr+"1";
		
   		if($("#address").val()!="") {
   		  if(getByteLen($("#address").val())>100){
   		   $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"12px"});
   			errorstr =errorstr+"1";
   				}
   		 }
   		else if($("#addressTag").val()!="") errorstr =errorstr+"1";
		
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
<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>
