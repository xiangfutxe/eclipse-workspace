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
		if(rankstr[3][1].equals("1")||admin.getState().equals("1")){

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增服务店</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>会员管理<span class="crumb-step">&gt;</span><span>服务中心</span><span class="crumb-step">&gt;</span><span>新增服务店</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                 <form action="admin/CenterServlet.do?method=admin_save" method="post" id="myform" name="myform">
                     <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>服务店编号：</th>
                                <td><input type="text" name="centerId" id="centerId" value=""><span id="centerIdTag"></span><span id="centerIdTag1"></span>
                                </td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red">*</i>服务店类型：</th>
                                <td><select name="type" id="type">
								<option value="1">社区店</option>
								<option value="2" >中心店</option>
								</select>&nbsp;<span
							id="typeTag"></span></td>
                            </tr>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>服务店名称：</th>
                                <td><input
							id="centerName" name="centerName" value='' type="text">&nbsp;<span
							id="nameTag"></span></td>
                            </tr>
                              <tr>
                                <th width="150px"><i class="require-red">*</i>负责人：</th>
                                <td><input type="text" value=''
							name="userName" id="userName">&nbsp;<span id="userNameTag"></span></td>
                            </tr>
                            <tr>
                                <th width="150px"><i class="require-red"></i>负责人编号：</th>
                                <td><input type="text" value=''
							name="userId" id="userId">（选填）&nbsp;<span id="userIdTag"></span></td>
                            </tr>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>服务店密码：</th>
                                <td><input type="password" value='' name="password" id="password">&nbsp;<span
							id="passwordTag"></span></td>
                            </tr>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>确认密码：</th>
                                <td><input type="password" value=''
							name="password2" id="password2">&nbsp;<span
							id="password2Tag"></span></td>
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
                                <th width="150px"><i class="require-red">*</i>经营地址：</th>
                                <td><input type="text" value=''
							name="address" id="address">&nbsp;<span id="addressTag"></span></td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red">*</i>联系电话：</th>
                                <td><input type="text" name="phone"
							id="phone" value="">&nbsp;<span id="phoneTag"></span></td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red"></i>营业执照：</th>
                                <td><input type="text" name="license"
							id="license" value="">（选填）&nbsp;<span id="licenseTag"></span></td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red"></i>推荐人编号：</th>
                                <td><input type="text" name="refereeId"
							id="refereeId" value="">&nbsp;（推荐人可以是会员也可以店铺，请正确输入以便奖金统计）<span id="refereeIdTag"></span></td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red"></i>推荐人名称：</th>
                                <td><input type="text" name="refereeName"
							id="refereeName" value="">&nbsp;<span id="refereeNameTag"></span></td>
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
		$("#city_2").citySelect({
			nodata : "none",
			required : false
		});
		
		$("#centerId").blur(
				function() {
					$.ajax({
						type : "post",
						url : "admin/CenterServlet.do?method=centerIdAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
						data : {//设置数据源
							centerId : $("input[name=centerId]").val()
						//这里不要加","  不然会报错，而且根本不会提示错误地方
						},

						dataType : "json",//设置需要返回的数据类型

						success : function(data) {
							var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
							if (d.tag == "4") {
								$("#centerIdTag").text("管理员未登陆，请重新登陆！").css({
									"color" : "red",
									"font-size" : "12px"
								});
								$("#centerIdTag1").text("");
							} else if (d.tag == "3") {
								$("#centerIdTag").text("数据库连接失败，请稍后再试！").css({
									"color" : "red",
									"font-size" : "12px"
								});
								$("#centerIdTag1").text("");
							} else if (d.tag == "2") {
								$("#centerIdTag").text("服务店ID不能为空，请重新输入！").css({
									"color" : "red",
									"font-size" : "12px"
								});
							} else if (d.tag == "1") {
								$("#centerIdTag").text("服务店ID已经存在，请重新输入！").css({
									"color" : "red",
									"font-size" : "12px"
								});
								$("#centerIdTag1").text("");
							} else if(d.tag==0){
								$("#centerIdTag1").text("服务店ID可用！").css({
									"color" : "green",
									"font-size" : "12px"
								});
							}else{
									$("#centerIdTag").text("");
							}

						},

						error : function() {
							$("#centerIdTag").text("系统异常，请稍后重试！").css({
									"color" : "red",
									"font-size" : "12px"
								});

						}//这里不要加","
					});
				});
	
	$("#centerName").blur(function(){
			 if($(this).val()=="")  $("#nameTag").text("服务店名称不能为空！").css({"color":"red","font-size":"12px"}); 
   		 else if(getByteLen($("#centerName").val())>50) $("#nameTag").text("服务店名称字数不能大于25位数！").css({"color":"red","font-size":"12px"});
	else $("#nameTag").text("");
		});
		
		
		
		$("#type").blur(function(){
			 if($(this).val()=="")  $("#typeTag").text("服务店类型不能为空！").css({"color":"red","font-size":"12px"}); 
   		
	else $("#typeTag").text("");

		});
		
		$("#userName").blur(function(){
			 if($(this).val()=="")  $("#userNameTag").text("负责人名称不能为空！").css({"color":"red","font-size":"12px"}); 
   		
	else $("#userNameTag").text("");

		});
	
	$("#password").blur(function(){
   		 if($(this).val()=="")  $("#passwordTag").text("服务店密码不能为空！").css({"color":"red","font-size":"12px"}); 
   		 else if(getByteLen($("#password").val())<6) $("#passwordTag").text("密码字数不能小于6位数！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($("#password").val())>50) $("#passwordTag").text("密码字数不能大于25位数！").css({"color":"red","font-size":"12px"});
		else $("#passwordTag").text("");
		
   });
   
   $("#password2").blur(function(){
   		 if($(this).val()!=$("#password").val())  $("#password2Tag").text("两次输入密码不一致！").css({"color":"red","font-size":"12px"}); 
   		
		else $("#password2Tag").text("");
		
   });
	
	
 	
   
    	$("#phone").blur(function(){
   	
				if(!(reg.test($(this).val())))
					$("#phoneTag").text("联系电话格式有误！").css({"color":"red","font-size":"12px"}); 
					else $("#phoneTag").text("");
	
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
		if($("#centerId").val()==""){
					$("#centerIdTag").text("服务店ID不能为空！").css({"color":"red","font-size":"12px"}); 
					$("#centerIdTag1").text("");
					errorstr =errorstr+"1";
				
			}else if($("#centerIdTag").text()!=""){
				errorstr =errorstr+"1";
				$("#centerIdTag1").text("");
			}else if($("#centerIdTag1").text()==""){
			$("#centerIdTag").text("服务店ID匹配失败，请重新输入！").css({"color":"red","font-size":"12px"}); 
				errorstr =errorstr+"1";
				$("#centerIdTag1").text("");
			}
			if($("#centerName").val()==""){
					$("#nameTag").text("服务店名称不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#nameTag").text()!=""){
				errorstr =errorstr+"1";
			}
			if($("#type").val()==""){
					$("#typeTag").text("服务店类型不能为空！").css({"color":"red","font-size":"12px"}); 
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
			
			
   		 if($("#password").val()=="") {
			 $("#passwordTag").text("服务店密码不能为空！").css({"color":"red","font-size":"12px"}); 
			 errorstr =errorstr+"1";
		 }
   		 else if(getByteLen($("#password").val())<6) {
			 $("#passwordTag").text("密码字数不能小于6位数！").css({"color":"red","font-size":"12px"});
			 errorstr =errorstr+"1";
		 }
		else if(getByteLen($("#password").val())>50){
			 $("#passwordTag").text("密码字数不能大于25位数！").css({"color":"red","font-size":"12px"});
			errorstr =errorstr+"1";
		}
		else if($("#passwordTag").val()!=""){
			errorstr =errorstr+"1";
		}
   		 if($("#password2").val()!=$("#password").val()) {
			 $("#password2Tag").text("两次输入密码不一致！").css({"color":"red","font-size":"12px"}); 
			 errorstr =errorstr+"1";
		 } else if($("#password2Tag").val()!=""){
			  errorstr =errorstr+"1";
		 }
			
			if(!(reg.test($("#phone").val()))){
					$("#phoneTag").text("联系电话格式有误！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
				
			}else if($("#phoneTag").text()!=""){
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
