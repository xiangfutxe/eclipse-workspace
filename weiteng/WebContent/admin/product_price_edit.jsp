<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ssm.pojo.Admin"%>
<%@ page import="com.ssm.pojo.Product"%>
<%@ page import="com.ssm.pojo.ProductType"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@page import="com.ssm.utils.StringUtil" %>
<%@ page import="java.util.List"%>


<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else {
	  String[][] ranks = StringUtil.getRankStr(admin.getRank());
			if(ranks[3][2].equals("1")||admin.getState()-1==0){
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>价格设置</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>
  <script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script>
KindEditor.ready(function(K) {
	window.editor=K.create('#features', {
		  uploadJson : '../kindeditor/jsp/upload_json.jsp',
                fileManagerJson : '../kindeditor/jsp/file_manager_json.jsp',
                allowFileManager : true
	});
});
        
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">产品管理</a></li>
    <li><a href="#">价格设置</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="ProductServlet?method=updatePrice" id="myform" method="post">
         <input type="hidden" name="token" id="token" value="<c:out value='${sessionScope.token}' escapeXml='true' default=''/>"/>
       <input type="hidden" name="id" id="id" value="${product.id}"/>
    <ul class="forminfo">
   
    <li><label>产品编号<b>*</b></label>
    <input name="productId" id="productId" type="text" value="${product.productId}" class="dfinput" maxlength="10" readonly="readonly"/><i id="productIdTag"></i><i id="productIdTag1"></i></li>
     <li><label>特卖专项价<b>*</b></label>
    <input name="price1" id="price1"  type="text" value="${product.price1}" class="dfinput" /><i id="price1Tag"></i></li>
     <li><label>积分专项价<b>*</b></label>
    <input name="price2" id="price2"  type="text" value="${product.price2}" class="dfinput" /><i id="price2Tag"></i></li>
    <li><label>特买权数1<b>*</b></label>
    <input name="cashNum1" id="cashNum1"  type="text" value="${product.cashNum1}" class="dfinput" /><i id="cashNum1"></i></li>
     <li><label>代金券抵扣1<b>*</b></label>
    <input name="cash1" id="cash1"  type="text" value="${product.cash1}" class="dfinput" /><i id="cash1"></i></li>
    <li><label>积分抵扣1<b>*</b></label>
    <input name="integral1" id="integral1"  type="text" value="${product.integral1}" class="dfinput" /><i id="integral1Tag"></i></li>
      <li><label>赠送积分<b>*</b></label>
      <input name="integral2" id="integral2"  type="text" value="${product.integral2}" class="dfinput" /><i id="integral2Tag"></i></li>
			<% if(ranks[3][2].equals("1")||admin.getState()-1==0){ %>
    <li><label>&nbsp;</label><input id="save_click"  type="button" class="btn" value="确认保存"/><i id="contentsTag"></i></li>
    <%}%>
    </ul>
    </form>
   
    </div>
  <script language="JavaScript">
$(function() {
  var reg_tel = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证
  var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
	$("#price1").blur(function() {
		if($("#price1").val()=="")$("#price1Tag").text("折扣价不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#price1Tag").text("数值格式有误，折扣价应为实数！").css({"color":"red","font-size":"12px"});
		else $("#price1Tag").text("");
	});
	$("#price2").blur(function() {
		if($("#price2").val()=="")$("#price2Tag").text("折扣价不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#price2Tag").text("数值格式有误，折扣价应为实数！").css({"color":"red","font-size":"12px"});
		else $("#price2Tag").text("");
	});
	$("#price3").blur(function() {
		if($("#price3").val()=="")$("#price3Tag").text("折扣价不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#price3Tag").text("数值格式有误，折扣价应为实数！").css({"color":"red","font-size":"12px"});
		else $("#price3Tag").text("");
	});
	$("#price4").blur(function() {
		if($("#price4").val()=="")$("#price4Tag").text("折扣价不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#price4Tag").text("数值格式有误，折扣价应为实数！").css({"color":"red","font-size":"12px"});
		else $("#price4Tag").text("");
	});
	$("#price5").blur(function() {
		if($("#price5").val()=="")$("#price5Tag").text("折扣价不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#price5Tag").text("数值格式有误，折扣价应为实数！").css({"color":"red","font-size":"12px"});
		else $("#price5Tag").text("");
	});
	$("#price6").blur(function() {
		if($("#price6").val()=="")$("#price6Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#price6Tag").text("数值格式有误，特买权数应为实数！").css({"color":"red","font-size":"12px"});
		else $("#price6Tag").text("");
	});
	$("#price7").blur(function() {
		if($("#price7").val()=="")$("#price7Tag").text("折扣价不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#price7Tag").text("数值格式有误，折扣价应为实数！").css({"color":"red","font-size":"12px"});
		else $("#price7Tag").text("");
	});
	$("#cash1").blur(function() {
		if($(this).val()=="")$("#cash1Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cash1Tag").text("数值格式有误，代金券应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cash1Tag").text("");
	});
	$("#cash2").blur(function() {
		if($(this).val()=="")$("#cash2Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cash2Tag").text("数值格式有误，代金券应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cash2Tag").text("");
	});
	$("#cash3").blur(function() {
		if($(this).val()=="")$("#cash3Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cash3Tag").text("数值格式有误，代金券应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cash3Tag").text("");
	});
	$("#cash4").blur(function() {
		if($(this).val()=="")$("#cash4Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cash4Tag").text("数值格式有误，代金券应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cash4Tag").text("");
	});
	$("#cash5").blur(function() {
		if($(this).val()=="")$("#cash5Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cash5Tag").text("数值格式有误，代金券应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cash5Tag").text("");
	});
	$("#cash6").blur(function() {
		if($(this).val()=="")$("#cash6Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cash6Tag").text("数值格式有误，代金券应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cash6Tag").text("");
	});
	$("#cash7").blur(function() {
		if($(this).val()=="")$("#cash7Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cash7Tag").text("数值格式有误，代金券应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cash7Tag").text("");
	});
	$("#cashNum1").blur(function() {
		if($(this).val()=="")$("#cashNum1Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cashNum1Tag").text("数值格式有误，特买权数应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cashNum1Tag").text("");
	});
	$("#cashNum2").blur(function() {
		if($(this).val()=="")$("#cashNum2Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cashNum2Tag").text("数值格式有误，特买权数应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cashNum2Tag").text("");
	});
	$("#cashNum3").blur(function() {
		if($(this).val()=="")$("#cashNum3Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cashNum3Tag").text("数值格式有误，特买权数应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cashNum3Tag").text("");
	});
	$("#cashNum4").blur(function() {
		if($(this).val()=="")$("#cashNum4Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cashNum4Tag").text("数值格式有误，特买权数应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cashNum4Tag").text("");
	});
	$("#cashNum5").blur(function() {
		if($(this).val()=="")$("#cashNum5Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cashNum5Tag").text("数值格式有误，特买权数应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cashNum5Tag").text("");
	});
	$("#cashNum6").blur(function() {
		if($(this).val()=="")$("#cashNum6Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cashNum6Tag").text("数值格式有误，特买权数应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cashNum6Tag").text("");
	});
	$("#cashNum7").blur(function() {
		if($(this).val()=="")$("#cashNum7Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#cashNum7Tag").text("数值格式有误，特买权数应为实数！").css({"color":"red","font-size":"12px"});
		else $("#cashNum7Tag").text("");
	});
	$("#integral1").blur(function() {
		if($(this).val()=="")$("#integral1Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#integral1Tag").text("数值格式有误，积分抵扣应为实数！").css({"color":"red","font-size":"12px"});
		else $("#integral1Tag").text("");
	});
	$("#integral2").blur(function() {
		if($(this).val()=="")$("#integral2Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#integral2Tag").text("数值格式有误，积分抵扣应为实数！").css({"color":"red","font-size":"12px"});
		else $("#integral2Tag").text("");
	});
	$("#integral3").blur(function() {
		if($(this).val()=="")$("#integral3Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#integral3Tag").text("数值格式有误，积分抵扣应为实数！").css({"color":"red","font-size":"12px"});
		else $("#integral3Tag").text("");
	});
	$("#integral4").blur(function() {
		if($(this).val()=="")$("#integral4Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#integral4Tag").text("数值格式有误，积分抵扣应为实数！").css({"color":"red","font-size":"12px"});
		else $("#integral4Tag").text("");
	});
	$("#integral5").blur(function() {
		if($(this).val()=="")$("#integral5Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#integral5Tag").text("数值格式有误，积分抵扣应为实数！").css({"color":"red","font-size":"12px"});
		else $("#integral5Tag").text("");
	});
	$("#integral6").blur(function() {
		if($(this).val()=="")$("#integral6Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#integral6Tag").text("数值格式有误，积分抵扣应为实数！").css({"color":"red","font-size":"12px"});
		else $("#integral6Tag").text("");
	});
	$("#integral7").blur(function() {
		if($(this).val()=="")$("#integral7Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"});
		else if(!reg.test($(this).val())) $("#integral7Tag").text("数值格式有误，积分抵扣应为实数！").css({"color":"red","font-size":"12px"});
		else $("#integral7Tag").text("");
	});
	
	$("#save_click").click(function(){
		if (!confirm("是否保存价格设置？")) {
			return false;
		} else {
		var errorstr = "";
		if($("#price1").val()==""){
			$("#price1Tag").text("折扣价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#price1Tag").text()!=""){
			errorstr ="1";
		}
		if($("#price2").val()==""){
			$("#price2Tag").text("折扣价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#price2Tag").text()!=""){
			errorstr ="1";
		}
		if($("#price3").val()==""){
			$("#price3Tag").text("折扣价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#price3Tag").text()!=""){
			errorstr ="1";
		}
		if($("#price4").val()==""){
			$("#price4Tag").text("折扣价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#price4Tag").text()!=""){
			errorstr ="1";
		}
		if($("#price5").val()==""){
			$("#price5Tag").text("折扣价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#price5Tag").text()!=""){
			errorstr ="1";
		}
		if($("#price6").val()==""){
			$("#price6Tag").text("折扣价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#price6Tag").text()!=""){
			errorstr ="1";
		}
		if($("#price7").val()==""){
			$("#price7Tag").text("折扣价不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#price7Tag").text()!=""){
			errorstr ="1";
		}
		if($("#cash1").val()==""){
			$("#cash1Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cash1Tag").text()!=""){
			errorstr ="1";
		}
		if($("#cash2").val()==""){
			$("#cash2Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cash2Tag").text()!=""){
			errorstr ="1";
		}
		if($("#cash3").val()==""){
			$("#cash3Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cash3Tag").text()!=""){
			errorstr ="1";
		}
		if($("#cash4").val()==""){
			$("#cash4Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cash4Tag").text()!=""){
			errorstr ="1";
		}
		if($("#cash5").val()==""){
			$("#cash5Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cash5Tag").text()!=""){
			errorstr ="1";
		}
		if($("#cash6").val()==""){
			$("#cash6Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cash6Tag").text()!=""){
			errorstr ="1";
		}
		if($("#cash7").val()==""){
			$("#cash7Tag").text("代金券不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cash7Tag").text()!=""){
			errorstr ="1";
		}
		if($("#cashNum1").val()==""){
			$("#cashNum1Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cashNum1Tag").text()!=""){
			errorstr ="1";
		}
		if($("#cashNum2").val()==""){
			$("#cashNum2Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cashNum2Tag").text()!=""){
			errorstr ="1";
		}
		if($("#cashNum3").val()==""){
			$("#cashNum3Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cashNum3Tag").text()!=""){
			errorstr ="1";
		}
		if($("#cashNum4").val()==""){
			$("#cashNum4Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cashNum4Tag").text()!=""){
			errorstr ="1";
		}
		if($("#cashNum5").val()==""){
			$("#cashNum5Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cashNum5Tag").text()!=""){
			errorstr ="1";
		}if($("#cashNum6").val()==""){
			$("#cashNum6Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cashNum6Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#cashNum7").val()==""){
			$("#cashNum7Tag").text("特买权数不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#cashNum7Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#integral1").val()==""){
			$("#integral1Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#integral1Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#integral2").val()==""){
			$("#integral2Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#integral2Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#integral3").val()==""){
			$("#integral3Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#integral3Tag").text()!=""){
			errorstr ="1";
		}
		if($("#integral4").val()==""){
			$("#integral4Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#integral4Tag").text()!=""){
			errorstr ="1";
		}
		if($("#integral5").val()==""){
			$("#integral5Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#integral5Tag").text()!=""){
			errorstr ="1";
		}
		if($("#integral6").val()==""){
			$("#integral6Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#integral6Tag").text()!=""){
			errorstr ="1";
		}
		if($("#integral7").val()==""){
			$("#integral7Tag").text("积分抵扣不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}
		else if($("#integral7Tag").text()!=""){
			errorstr ="1";
		}
		
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
</body>

</html>
<%
	}else{
		out.println("<script>");
		out.println("window.location.href='error_rank.jsp';");
		out.println("</script>");
	}
}
%>