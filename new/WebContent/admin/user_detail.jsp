<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
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
		if(rankstr[1][2].equals("1")||admin.getState().equals("1")){

User user = (User)request.getAttribute("user");
if(user==null){
out.println("<script>");
		out.println("parent.window.location.href='javascript:history.go(-1)';");
		out.println("</script>");
}else{
String message = (String)request.getAttribute("message");
if(!StringUtil.notNull(message).equals("")){
out.println("<script>");
		out.println("alert('"+message+"')");
		out.println("</script>");
}		

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户详情</title>
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
      <script type="text/javascript" src="js/jquery.cityselect.js"></script>
     
  </head>
  
  <body onload="initialize()">
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>会员管理<span class="crumb-step">&gt;</span><span>会员列表</span>
            <span class="crumb-step">&gt;</span><span>详情</span></div>
        </div>
         <div class="result-wrap">
         <form  action="admin/UserServlet.do?method=admin_user_update"  id="myform" name="myform" method="post">
		   <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
          <input  id="id" name="id"  value='<%=StringUtil.notNull(user.getUserId())%>' type="hidden">
            <div class="result-content">
               
                    <table class="insert-tab" width="100%">
                        <tbody>
                          <tr>
                                <td colspan="2" ><h4><i class="require-red">*</i>加盟信息：</h4></td>
                                </tr>
                                <tr>
                                <th width="150px"><i class="require-red">*</i>会员等级：</th>
                                <td>
								<%
										if (user.getRankJoin() == 1) {
									%>银级代理商<%
										} else if (user.getRankJoin() == 2) {
									%>
									金级代理商<%
										} else if (user.getRankJoin() == 3) {
									%>钻级代理商
									<%
										} else {
									%> -<%
										}
									%>
                                </td>
                                
                                </tr><tr>
                                 <th><i class="require-red">*</i>客服信息：</th>
                                <td>
                                <%=user.getRefereeUserId() %>
                                </td>
                                  </tr>
                         
                         <tr>
                                <td colspan="2"><h4><i class="require-red">*</i>会员资料：</h4></td>
                         </tr>
						  <tr>
                                <th><i class="require-red">*</i>会员编号：</th>
                                <td >
                                  <%=StringUtil.notNull(user.getUserId()) %> 
                                </td>
                               </tr>
                             <tr>
                                <th><i class="require-red">*</i>真实姓名：</th>
                                <td >
                                    <input class="common-text required" id="userName" name="userName"  value="<%=StringUtil.notNull(user.getUserName()) %>" type="text">
                               
                                <span id="nameTag"></span>
                                </td>
                               </tr><tr>
                                 <th><i class="require-red">*</i>性别：</th>
                                <td>
                                   <input type="radio" name="sex" value="男"  <%if(user.getSex().equals("男")){%>checked<%} %>>男 &nbsp;<input type="radio" name="sex" value="女"  <%if(user.getSex().equals("女")){%>checked<%} %>>女 <span id="sexTag"></span>
                               
                            </tr>
                           
                             <tr>
                                 <th>证件类型：</th>
                                <td>
                                <select name="documentType" id="documentType" class="common-text required">
                                <option value="" >--请选择证件类型--</option>
                                <option value="居民身份证" <%if(user.getDocumentType().equals("居民身份证")){%>selected<%} %>>居民身份证</option>
                                </select>
                                
                                <span id="documentTypeTag"></span>
                                </td>
                                </tr><tr>
                                 <th>证件号码：</th>
                                <td>
                                    <input class="common-text required" id="numId" name="numId" value="<%=StringUtil.notNull(user.getNumId()) %>" type="text">&nbsp;<span id="numIdTag"></span>
                                </td>
                               
                            </tr>
                            <tr>
                                 <th><i class="require-red">*</i>手机号码：</th>
                                <td>
                                    <input class="common-text required" id="tel" name="tel" value="<%=StringUtil.notNull(user.getTel()) %>" type="text">&nbsp;<span id="telTag"></span>
                                </td>
                               
                            </tr>
                              <tr>
                                 <th>固定电话：</th>
                                <td>
                                    <input class="common-text required" id="phone" name="phone" value="<%=StringUtil.notNull(user.getPhone()) %>" type="text">&nbsp;<span id="phoneTag"></span>
                                </td>
                               
                            </tr>
                         <tr>
                                 <th>电子邮箱：</th>
                                <td>
                                    <input class="common-text required" id="email" name="email" value="<%=StringUtil.notNull(user.getEmail()) %>" type="text">&nbsp;<span id="emailTag"></span>
                                </td>
                               
                            </tr>
                             <tr>
                                 <th>QQ号码：</th>
                                <td>
                                    <input class="common-text required" id="qq" name="qq" value="<%=StringUtil.notNull(user.getQq()) %>" type="text">&nbsp;<span id="qqTag"></span>
                                </td>
                               
                            </tr>
                             <tr>
                                 <th>微信：</th>
                                <td>
                                    <input class="common-text required" id="weixin" name="weixin" value="<%=StringUtil.notNull(user.getWeixin()) %>" type="text">&nbsp;<span id="weixinTag"></span>
                                </td>
                               
                            </tr>
                             <tr>
                                 <th>所在省市：</th>
                                <td>
                                  
<div id="city_2">
  		<select class="prov common-text" id="province" name="province">
  		</select> 
    	<select class="city common-text" id="city" name="city" disabled="disabled">

		</select>
		 <select class="dist common-text " id="area" name="area" disabled="disabled">
		</select>
   &nbsp;<span id="provinceTag"></span> </div></td>
                               
                            </tr>
                             <tr>
                                 <th>联系地址：</th>
                                <td>
                                    <input class="common-text required" id="address" name="address" value="<%=StringUtil.notNull(user.getAddress()) %>" type="text">&nbsp;<span id="addressTag"></span>
                                </td>
                               
                            </tr>
             <tr>
                                <td colspan="2"><h4><i class="require-red">*</i>银行账户：</h4></td>
                                </tr>
                           
                                   <tr>
                                <th><i class="require-red">*</i>持卡人姓名：</th>
                                <td>
                                   <input class="common-text required" id="accountName" name="accountName" value="<%=StringUtil.notNull(user.getAccountName()) %>" type="text">&nbsp;<span id="accountNameTag"></span>
                                   </td>
                                
                                </tr>
                                   <tr>
                                <th>银行卡号：</th>
                                <td>
                                   <input class="common-text required" id="accountId" name="accountId" value="<%=StringUtil.notNull(user.getAccountId()) %>" type="text">&nbsp;<span id="accountIdTag"></span>
                                   </td>
                                
                                </tr>
                                <tr>
                                <th>开户银行：</th>
                                <td>
                                    <select
							name="bankName" id="bankName" >
								<option value="中国工商银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国工商银行股份有限公司")) {%>
									selected <%}%>>中国工商银行股份有限公司</option>
									<option value="中国建设银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国建设银行股份有限公司")) {%>
									selected <%}%>>中国建设银行股份有限公司</option>
									<option value="中国农业银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国农业银行股份有限公司")) {%>
									selected <%}%>>中国农业银行股份有限公司</option>
									<option value="中国银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国银行股份有限公司")) {%>
									selected <%}%>>中国银行股份有限公司</option>
									<option value="交通银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"交通银行股份有限公司")) {%>
									selected <%}%>>交通银行股份有限公司</option>
									<option value="中国邮政储蓄银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国邮政储蓄银行股份有限公司")) {%>
									selected <%}%>>中国邮政储蓄银行股份有限公司</option>
									<option value="中信银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中信银行股份有限公司")) {%>
									selected <%}%>>中信银行股份有限公司</option>
									<option value="中国光大银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国光大银行股份有限公司")) {%>
									selected <%}%>>中国光大银行股份有限公司</option>
									<option value="华夏银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"华夏银行股份有限公司")) {%>
									selected <%}%>>华夏银行股份有限公司</option>
									<option value="中国民生银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国民生银行股份有限公司")) {%>
									selected <%}%>>中国民生银行股份有限公司</option>
									<option value="招商银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"招商银行股份有限公司")) {%>
									selected <%}%>>招商银行股份有限公司</option>
									<option value="广发银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"广发银行股份有限公司")) {%>
									selected <%}%>>广发银行股份有限公司</option>
									<option value="兴业银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"兴业银行股份有限公司")) {%>
									selected <%}%>>兴业银行股份有限公司</option>
									<option value="广州商业银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"广州商业银行股份有限公司")) {%>
									selected <%}%>>广州商业银行股份有限公司</option>
									
									
						</select>&nbsp;<span id="bankNameTag"></span>  </td>
                                
                                </tr>
                                 <tr>
                                <th>开户网点：</th>
                                <td>
                                   <input class="common-text required" id="bankAdr" name="bankAdr"  value="<%=StringUtil.notNull(user.getBankAdr()) %>" type="text">&nbsp;<span id="bankAdrTag"></span>
                                   </td>
                                
                                </tr>
                        <tr>
                                <th></th>
                                <td>
                                <%if(rankstr[1][3].equals("1")||admin.getState().equals("1")){ %>
                                   <input id="btn1" class="btn btn-primary" value="保存修改" type="submit">
                                   <%} %>
                                </td>
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
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  //验证身份证号码
   var reg1 = /^[1-9]+[0-9]*]*$/;  //验证整数
 var reg2 = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证
    var reg3 = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; //验证电子邮箱
   // var reg4 =  /^(?!(\d)\1{1,}$)[1-9]\d{4,}$/;//验证QQ号码
    $("#city_2").citySelect({
		
		prov:"<%=StringUtil.notNull(user.getProvince())%>", 
    	city:"<%=StringUtil.notNull(user.getCity())%>",
		dist:"<%=StringUtil.notNull(user.getArea())%>",
		nodata:"none"
	}); 
   
   
   $("#userName").blur(function(){
     $("#accountName").attr("value",$("#userName").val());
   		 if($(this).val()=="")  $("#nameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"9px"}); 
		else if(getByteLen($("#userName").val())>50) $("#nameTag").text("真实姓名字数不能大于25！").css({"color":"red","font-size":"9px"});
		else $("#nameTag").text("");
		
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
	else if($("#age").val()>200) $("#ageTag").text("年龄超出了正常范围,请核对！").css({"color":"red","font-size":"12px"}); 
		else if($("#age").val()<18) $("#ageTag").text("年龄未满18岁，未满足加盟资格！").css({"color":"red","font-size":"12px"}); 
	else $("#ageTag").text("");
		
   });
    */
  $("#tel").blur(function(){
   	 if($(this).val()!=""){
				if(!(reg2.test($(this).val())))
					$("#telTag").text("联系电话格式有误！").css({"color":"red","font-size":"9px"}); 
					else $("#telTag").text("");
	}else{
		$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"9px"});
	}
   });
   $("#email").blur(function(){
   	 if($(this).val()!=""){
				if(!reg3.test($(this).val()))
					$("#emailTag").text("电子邮箱格式有误！").css({"color":"red","font-size":"9px"}); 
					else $("#emailTag").text("");
	}
   });
   
   $("#qq").blur(function(){
   	 if($(this).val()!=""){
				if(!reg1.test($(this).val()))
					$("#qqTag").text("QQ号码格式有误！").css({"color":"red","font-size":"9px"}); 
					else $("#qqTag").text("");
					
	}
   });
   
   			
   			$("#address").blur(function(){
   		 if($("#address").val()!=""){
   		  if(getByteLen($("#address").val())>100) $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"9px"});
   		else $("#addressTag").text("");
   		}else $("#addressTag").text("");
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
  	 
  	  $("#btn1").click(function(){
  	   if (!confirm("确认保存修改！")) {
				return false;
			}else{
	  	 var errorstr = "";
	  	 
   		 if($("#userName").val()=="") {
   		 	 $("#nameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"9px"}); 
   		  	errorstr =errorstr+"1";
   		  }else if($("#nameTag").text()!="") errorstr =errorstr+"1";
   		
			if($("#documentType").val()=="居民身份证"||$("#documentType").val()=="临时身份证"){
				if(!reg.test($("#numId").val())){
					$("#numIdTag").text("身份证号码格式有误！").css({"color":"red","font-size":"9px"}); 
					errorstr =errorstr+"1";
					}
			}else if($("#numIdTag").val()!=""){
				errorstr =errorstr+"1";
			}
			if($("#tel").val()!=""){
					if(!(reg2.test(("#tel").val()))){
						$("#telTag").text("手机号码格式有误！").css({"color":"red","font-size":"9px"}); 
						errorstr =errorstr+"1";
					}
				
			}else if($("#tel").val()==""){
				$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"9px"}); 
					errorstr =errorstr+"1";
			}else if($("#telTag").val()!=""){
				errorstr =errorstr+"1";
			}
			
			 if ($(":radio:checked").length == 0)
			{
			$("#sexTag").text("未选择性别！").css({"color":"red","font-size":"9px"});
			errorstr =errorstr+"1";
			}
			
			if($("#email").val()!=""){
				if(!reg3.test($("#email").val())){
					$("#emailTag").text("电子邮箱格式有误！").css({"color":"red","font-size":"9px"}); 
					errorstr =errorstr+"1";
				}
			}else if($("#emailTag").val()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#qq").val()!=""){
				if(!reg1.test($("#qq").val())){
					$("#qqTag").text("QQ号码格式有误！").css({"color":"red","font-size":"9px"}); 
					errorstr =errorstr+"1";
				}
			}else if($("#qqTag").val()!=""){
				errorstr =errorstr+"1";
			}
		/*	
			if($("#age").val()==""){
			 $("#ageTag").text("年龄不能为空！").css({"color":"red","font-size":"9px"}); 
			 errorstr =errorstr+"1";
			}else if($("#ageTag").text()!="") errorstr ="1";
   			*/
   		if($("#address").val()!="") {
   		  if(getByteLen($("#address").val())>100){
   		   $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"9px"});
   			errorstr =errorstr+"1";
   			$("#address").focus();
   				}
   		 }else if($("#addressTag").val()!=""){
   		errorstr =errorstr+"1";
   		 $("#address").focus();
   		 }
		
		 if($("#accountName").val()==""){
   			 $("#accountNameTag").text("持卡人姓名不能为空！").css({"color":"red","font-size":"12px"}); 
   			errorstr =errorstr+"1";
			}else if($("#accountName").val()!=$("#userName").val()){
   			 $("#accountNameTag").text("持卡人姓名与用户真实姓名必须一致！").css({"color":"red","font-size":"12px"}); 
   			 errorstr =errorstr+"1";
   			 $("#accountName").focus();
			}else if($("#accountNameTag").val()!="") errorstr =errorstr+"1";
   		
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
}
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>