<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ssm.pojo.Admin"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@page import="com.ssm.utils.StringUtil" %>


<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else {
	  String[][] ranks = StringUtil.getRankStr(admin.getRank());
			if(ranks[5][4].equals("1")||admin.getState()-1==0){
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加员工</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>
 <script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">订单管理</a></li>
    <li><a href="#">配货审核</a></li>
    <li><a href="#">填写物流信息</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="OrderDeliveryServlet?method=admin_order_review_yes" id="myform" method="post">
         <input type="hidden" name="token" id="token" value="<c:out value='${sessionScope.token}' escapeXml='true' default=''/>"/>
     
     <input type="hidden" name="id" id="id" value="${orders.id }"/>
          <input type="hidden" name="orderId" id="orderId" value="${orders.orderId }"/>
     
    <ul class="forminfo">
    <li><label>物流公司<b>*</b></label>
  	 <select name="express" id="express"  class="dfinput">
       <option value="顺丰快递" >--顺丰快递--</option>
        <option value="EMS" >--EMS--</option>
       <option value="中通快递" >--中通快递--</option>
       <option value="圆通快递" >--圆通快递--</option>
          <option value="申通快递" >--申通快递--</option>
           <option value="百世快递" >--百世快递--</option>
       <option value="韵达快递" >--韵达快递--</option>
        <option value="买家自提" >--买家自提--</option>
       
      </select><i id="expressTag"></i></li>
    <li><label>物流单号<b>*</b></label><input name="expressNum" id="expressNum" type="text" class="dfinput" /><i id="expressNumTag"></i></li>
   
    <li><label>&nbsp;</label><input id="save_click"  type="button" class="btn" value="确认出库"/><i id="contentsTag"></i></li>
    </ul>
    </form>
     <div class="save_tip">
    	<div class="save_tiptop"><span>提示信息</span><a></a></div>
        
      <div class="save_tipinfo">
        <span><img src="images/ticon_yes.png" /></span>
        <div class="save_tipright">
        <p>是否确认对提交出库信息 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
       
        <div class="tipbtn">
        <input name="" type="button"  class="save_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="save_cancel" value="取消" />
        </div>
     </div>
    </div>
  <script language="JavaScript">
$(function() {
  var reg_tel = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证

	$("#adminName").blur(function() {
		$.ajax({
				type : "post",
				url : "AdminServlet?method=isExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					adminName : $("#adminName").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方
				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data.tag;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					if(d=="0"){
					 $("#nameTag1").text("用户名不能为空！").css({"color":"red","font-size":"12px"});
					 $("#nameTag").text("");
					 }
					else if(d=="1") {
					 $("#nameTag1").text("用户名已存在！").css({"color":"red","font-size":"12px"});
					  $("#nameTag").text("");
					 }
					else if(d=="2"){
					  $("#nameTag1").text("√").css({"font-family":"Arial","color":"green","font-size":"12px"});
					   $("#nameTag").text("");
					 }
					else $("#nameTag").text("");
					if($("#adminName").val().length>50){
					 $("#nameTag1").text("用户名字数不能大于25！").css({"color":"red","font-size":"12px"});
					  $("#nameTag").text("");
					 }
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	});
	
	$("#express").blur(function() {
		if($("#express").val()=="")$("#expressTag").text("快递公司不能为空！").css({"color":"red","font-size":"12px"});
		else if($(this).val().length>50) $("#expressTag").text("快递公司字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#expressTag").text("");
	});
	$("#expressNum").blur(function() {
		if($("#expressNum").val()=="")$("#expressNumTag").text("快递公司不能为空！").css({"color":"red","font-size":"12px"});
		else if($(this).val().length>50) $("#passwordNumTag").text("快递公司字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#expressNumTag").text("");
	});
	
	
	$("#save_click").click(function(){
		  $(".save_tip").fadeIn(200);
		  });
		  
		  $(".save_tiptop a").click(function(){
		  $(".save_tip").fadeOut(200);
		});

		  $(".save_sure").click(function(){
		 		 $(".save_tip").fadeOut(100);
		var errorstr = "";
		
		if($("#express").val()==""){
		$("#expressTag").text("快递公司不能为空！").css({"color":"red","font-size":"12px"});
			errorstr ="1";
		}else if($("#expressTag").text()!=""){
			errorstr ="1";
		}
		if($("#expressNum").val()==""){
			$("#expressNumTag").text("快递单号不能为空！").css({"color":"red","font-size":"12px"});
				errorstr ="1";
			}else if($("#expressNumTag").text()!=""){
				errorstr ="1";
			}
		
		if(errorstr!=""){
			alert(errorstr);
			return false;
		}else{
       		 $('#myform').submit();
       		 return true;
		
		}
	});
		  
		  $(".save_cancel").click(function(){
			  $(".save_tip").fadeOut(100);
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