<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.AgentApply" %>
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
		if(rankstr[1][13].equals("1")||admin.getState().equals("1")){

AgentApply apply = (AgentApply)request.getAttribute("agent_apply");
if(apply==null){
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
    
    <title>代理商申请详情</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>会员管理<span class="crumb-step">&gt;</span><span>代理商管理</span>
            <span class="crumb-step">&gt;</span><span>详情</span></div>
        </div>
         <div class="result-wrap">
         <form action=""  id="myform" name="myform" method="post">
		   <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
          <input  id="id" name="id"  value='<%=apply.getId()%>' type="hidden">
            <div class="result-content">
               
                    <table class="insert-tab" width="100%">
                        <tbody>
                         <tr>
                                <th><i class="require-red">*</i>会员编号：</th>
                                <td >
                                  <%=StringUtil.notNull(apply.getUserId()) %> 
                                </td>
                               </tr>
                             <tr>
                                <th><i class="require-red">*</i>真实姓名：</th>
                                <td >
                                    <input class="common-text required" id="userName" name="userName"  value="<%=StringUtil.notNull(apply.getUserName()) %>" type="text">
                               
                                <span id="nameTag"></span>
                                </td>
                               </tr>
                           
                             <tr>
                                 <th>证件类型：</th>
                                <td>
                                <select name="documentType" id="documentType" class="common-text required">
                                <option value="" >--请选择证件类型--</option>
                                <option value="居民身份证" <%if(apply.getDocumentType().equals("居民身份证")){%>selected<%} %>>居民身份证</option>
<option value="军官证"  <%if(apply.getDocumentType().equals("军官证")){%>selected<%} %>>军官证</option>
<option value="武警警官证"  <%if(apply.getDocumentType().equals("武警警官证")){%>selected<%} %>>武警警官证</option>
<option value="士兵证" <%if(apply.getDocumentType().equals("士兵证")){%>selected<%} %>>士兵证</option>
<option value="中华人民共和国来往港澳通行证" <%if(apply.getDocumentType().equals("中华人民共和国来往港澳通行证")){%>selected<%} %>>中华人民共和国来往港澳通行证</option>
<option value="港澳同胞回乡证" <%if(apply.getDocumentType().equals("港澳同胞回乡证")){%>selected<%} %>>港澳同胞回乡证</option>
<option value="港澳居民来往内地通行证" <%if(apply.getDocumentType().equals("港澳居民来往内地通行证")){%>selected<%} %>>港澳居民来往内地通行证</option>
<option value="台湾居民来往大陆通行证" <%if(apply.getDocumentType().equals("台湾居民来往大陆通行证")){%>selected<%} %>>台湾居民来往大陆通行证</option>
<option value="大陆居民往来台湾通行证" <%if(apply.getDocumentType().equals("大陆居民往来台湾通行证")){%>selected<%} %>>大陆居民往来台湾通行证</option>
<option value="护照" <%if(apply.getDocumentType().equals("护照")){%>selected<%} %>>护照</option>
<option value="其它有效证件" <%if(apply.getDocumentType().equals("其它有效证件")){%>selected<%} %>>其它有效证件</option>
                                </select>
                                
                                <span id="documentTypeTag"></span>
                                </td>
                                </tr><tr>
                                 <th>证件号码：</th>
                                <td>
                                    <input class="common-text required" id="numId" name="numId" value="<%=StringUtil.notNull(apply.getNumId()) %>" type="text">&nbsp;<span id="numIdTag"></span>
                                </td>
                               
                            </tr>
                            <tr>
                                 <th><i class="require-red">*</i>手机号码：</th>
                                <td>
                                    <input class="common-text required" id="tel" name="tel" value="<%=StringUtil.notNull(apply.getTel()) %>" type="text">&nbsp;<span id="telTag"></span>
                                </td>
                               
                            </tr>
                             <tr>
                                 <th>经营区域：</th>
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
                                 <th>详细地址：</th>
                                <td>
                                    <input class="common-text required" id="address" name="address" value="<%=StringUtil.notNull(apply.getAddress()) %>" type="text">&nbsp;<span id="addressTag"></span>
                                </td>
                               
                            </tr>
                             <tr>
                                 <th>申请时间：</th>
                             <td><%=StringUtil.parseToString(apply.getEntryTime(),DateUtil.yyyyMMddHHmmss) %></td>
  </tr>
   <tr>
                                 <th>审批人：</th>
  <td><%=StringUtil.notNull(apply.getReviewer()) %></td>
  </tr> <tr>
                                 <th>审批时间：</th>
  <td><%=StringUtil.parseToString(apply.getReviewTime(),DateUtil.yyyyMMddHHmmss) %></td>
 </tr>
 
             <tr>
                                 <th>当前状态：</th>
                                <td><%if(apply.getState()==1){ %>审批中<%}else if(apply.getState()==2){ %>审批通过<%}else{ %>已关闭<%} %>
                                </td>
                               
                            </tr>
                        <tr>
                                <th></th>
                                <td>
                                <%if(rankstr[1][15].equals("1")||admin.getState().equals("1")){ %>
                                   <input id="btn1" class="btn btn-primary" value="保存修改" type="button">
                                   <%} %>
                                    <%if(rankstr[1][16].equals("1")||admin.getState().equals("1")){ %>
                                     <%if(apply.getState()==1){ %>
                                   <input id="btn_yes" class="btn btn-success" value="审批通过" type="button">
                                   <input id="btn_no" class="btn btn-info" value="审批不通过" type="button">
                                  
                                   <%} }%>
                                    <input id="btn_list" class="btn btn" value="代理商列表" type="button">
                                   
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
		
		prov:"<%=StringUtil.notNull(apply.getProvince())%>", 
    	city:"<%=StringUtil.notNull(apply.getCity())%>",
		dist:"<%=StringUtil.notNull(apply.getArea())%>",
		nodata:"none"
	}); 
   
    $("#btn_yes").click(function(){
  	   if (!confirm("确认审批通过？")) {
				return false;
			}else{
			alert("yes");
			 $("#myform").attr("action","admin/AgentApplyServlet.do?method=admin_review_yes");
	       		 $("#myform").submit();
	       		 return true;
			}
		});
		
	$("#btn_no").click(function(){
  	   if (!confirm("确认审批不通过？")) {
				return false;
			}else{
			 $('#myform').attr("action","admin/AgentApplyServlet.do?method=admin_review_no");
	       		 $('#myform').submit();
	       		 return true;
			}
		});
		
		  $("#btn_list").click(function(){
			 $('#myform').attr("action","admin/AgentApplyServlet.do?method=admin_list");
	       		 $('#myform').submit();
	       		 return true;
		});
   
   $("#userName").blur(function(){
   		 if($(this).val()=="")  $("#nameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"}); 
		else if(getByteLen($("#userName").val())>50) $("#nameTag").text("真实姓名字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#nameTag").text("");
		
   });
   $("#numId").blur(function(){
   	if($("#numId").val()==""){
				
					$("#numIdTag").text("证件号码不能为空！").css({"color":"red","font-size":"12px"}); 
					
	}else $("#numIdTag").text("");
   });
   
    $("#documentType").blur(function(){
   	if($("#documentType").val()==""){
				
					$("#documentTypeTag").text("证件类型不能为空！").css({"color":"red","font-size":"12px"}); 
					
	}else $("#documentTypeTag").text("");
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
   	 if($(this).val()!=""){
				if(!(reg2.test($(this).val())))
					$("#telTag").text("联系电话格式有误！").css({"color":"red","font-size":"12px"}); 
					else $("#telTag").text("");
	}else{
		$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"});
	}
   });
   
   
   			
   			$("#address").blur(function(){
   		 if($("#address").val()!=""){
   		  if(getByteLen($("#address").val())>100) $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"12px"});
   		else $("#addressTag").text("");
   		}else $("#addressTag").text("");
   			});
   			
  	
  	  $("#btn1").click(function(){
  	   if (!confirm("确认保存修改！")) {
				return false;
			}else{
	  	 var errorstr = "";
	  	 
   		 if($("#userName").val()=="") {
   		 	 $("#nameTag").text("真实姓名不能为空！").css({"color":"red","font-size":"12px"}); 
   		  	errorstr =errorstr+"1";
   		  }else if($("#nameTag").text()!="") errorstr =errorstr+"1";
   		
			if($("#documentType").val()==""){
					$("#documentTypeTag").text("证件类型不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
			}else if($("#documentTypeTag").val()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#numId").val()==""){
					$("#numIdTag").text("证件号码不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
			}else if($("#numIdTag").val()!=""){
				errorstr =errorstr+"1";
			}
			
			if($("#tel").val()==""){
				$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr =errorstr+"1";
			}else if($("#telTag").val()!=""){
				errorstr =errorstr+"1";
			}
			
			
   		if($("#address").val()!="") {
   		  if(getByteLen($("#address").val())>100){
   		   $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"12px"});
   			errorstr =errorstr+"1";
   			$("#address").focus();
   				}
   		 }else if($("#addressTag").val()!=""){
   		errorstr =errorstr+"1";
   		 $("#address").focus();
   		 }
			if(errorstr!=""){
				return false;
			}else{
			 $("#myform").attr("action","admin/AgentApplyServlet.do?method=update");
	       		 $("#myform").submit();
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