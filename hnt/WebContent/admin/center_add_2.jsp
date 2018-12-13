<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.pojo.Admin" %>
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
		if(rankstr[1][16].equals("1")||admin.getState().equals("1")){

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
                 <form action="admin/CenterServlet?method=saveCenter" method="post" id="myform" name="myform">
                     <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>会员ID：</th>
                                <td><input type="text" name="userId" id="userId" value="<%=user1.getUserId()%>" readonly><span id="usermsg"></span><span id="userIdTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red">*</i>服务店类型：</th>
                                <td><select name="type" id="type">
                                <option value="1" >工作室</option>
								<option value="2">发货中心</option>
								<option value="3" >运营中心</option>
								
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
                                <th width="150px"><i class="require-red"></i>工作室形式：</th>
                                <td><select name="typeForm" id="typeForm">
                                <option value="1" >无</option>
								<option value="2">门面</option>
								<option value="3" >办公室</option>
								
								</select>&nbsp;&nbsp;<span id="typeFormTag"></span></td>
                            </tr>
                            <tr>
                                <th width="150px"><i class="require-red"></i>面积：</th>
                                <td><input type="text" name="measure"
							id="measure" value="">&nbsp;<span id="measureTag"></span></td>
                            </tr>
                             <tr>
                                <th width="150px"><i class="require-red"></i>营业执照：</th>
                                <td><input type="text" name="license"
							id="license" value="">&nbsp;<span id="licenseTag"></span></td>
                            </tr>
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
	
	$("#centerName").blur(function(){
			 if($(this).val()=="")  $("#nameTag").text("服务店名称不能为空！").css({"color":"red","font-size":"12px"}); 
   		 else if(getByteLen($("#centerName").val())>50) $("#nameTag").text("服务店名称字数不能大于25位数！").css({"color":"red","font-size":"12px"});
	else $("#nameTag").text("");
		});
		
		$("#type").blur(function(){
			 if($(this).val()=="")  $("#typeTag").text("服务店类型不能为空！").css({"color":"red","font-size":"12px"}); 
   		
	else $("#typeTag").text("");

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
