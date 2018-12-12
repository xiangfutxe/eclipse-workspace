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
			if(ranks[9][17].equals("1")||admin.getState()-1==0){
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>参数新增</title>
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
    <li><a href="#">其他设置</a></li>
    <li><a href="#">参数新增</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="ParamServlet?method=save" id="myform" method="post">
    <ul class="forminfo">
    <li><label>参数名称<b>*</b></label>
    <input type="hidden" name="token" id="token" value="<c:out value='${sessionScope.token}' escapeXml='true' default=''/>"/>
    <input name="paramName" id="paramName" type="text" class="dfinput" /><i id="nameTag1"></i><i id="nameTag"></i></li>
   <li><label>参数1<b>*</b></label>
    <input name="amount_1" id="amount_1" type="text" class="dfinput" value="0"/><i id="amount_1Tag"></i></li>
       <li><label>参数2<b>*</b></label>
    <input name="amount_2" id="amount_2" type="text" class="dfinput"  value="0"/><i id="amount_2Tag"></i></li>
       <li><label>参数3<b>*</b></label>
    <input name="amount_3" id="amount_3" type="text" class="dfinput"  value="0"/><i id="amount_3Tag"></i></li>
       <li><label>参数4<b>*</b></label>
    <input name="amount_4" id="amount_4" type="text" class="dfinput"  value="0"/><i id="amount_4Tag"></i></li>
       <li><label>参数5<b>*</b></label>
    <input name="amount_5" id="amount_5" type="text" class="dfinput"  value="0"/><i id="amount_5Tag"></i></li>
       <li><label>参数6<b>*</b></label>
    <input name="amount_6" id="amount_6" type="text" class="dfinput"  value="0"/><i id="amount_6Tag"></i></li>
       <li><label>参数7<b>*</b></label>
    <input name="amount_7" id="amount_7" type="text" class="dfinput"  value="0"/><i id="amount_7Tag"></i></li>
       <li><label>参数8<b>*</b></label>
    <input name="amount_8" id="amount_8" type="text" class="dfinput"  value="0"/><i id="amount_8Tag"></i></li>
       <li><label>参数9<b>*</b></label>
    <input name="amount_9" id="amount_9" type="text" class="dfinput"  value="0"/><i id="amount_9Tag"></i></li>
       <li><label>参数10<b>*</b></label>
    <input name="amount_10" id="amount_10" type="text" class="dfinput"  value="0"/><i id="amount_10Tag"></i></li>
       <li><label>单位<b></b></label>
    <input name="unit" id="unit" type="text" class="dfinput" /><i id="unitTag"></i></li>
    
    <li><label>&nbsp;</label><input id="sbtn"  type="button" class="btn" value="确认保存"/><i id="contentsTag"></i></li>
    </ul>
    </form>
   
    </div>
  <script language="JavaScript">
$(function() {

	var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
	$("#paramName").blur(function() {
		$.ajax({
				type : "post",
				url : "ParamServlet?method=isExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					paramName : $("#paramName").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},
				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = eval("(" + data + ")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="0"){
						$("#nameTag1").text("参数不能为空！").css({"color":"red","font-size":"12px"});
						$("#nameTag").text("");
					}
					else if(d.tag=="1"){
						$("#nameTag1").text("参数已存在！").css({"color":"red","font-size":"12px"});
						$("#nameTag").text("");
					}
					else if(d.tag=="2"){
						$("#nameTag").text("参数可用！").css({"color":"green","font-size":"12px"});
						$("#nameTag1").text("");
					}
					else{
						$("#nameTag1").text("数据获取异常！").css({"color":"red","font-size":"12px"});
						$("#nameTag").text("");
					}
					
					if($(this).val().length>50){
					$("#nameTag1").text("参数名字数不能大于25！").css({"color":"red","font-size":"12px"});$("#nameTag").text("");
					$("#nameTag").text("");
					}
				},
				error : function() {
					$("#nameTag1").text("系统异常，请稍后重试！").css({"color":"red","font-size":"12px"});
					$("#nameTag").text("");
				}//这里不要加","
				
			});
	});
	

	
	$("#amount_1").blur(function() {
		if($(this).val()==""){
		$("#amount_1Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#amount_1Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#amount_1Tag").text("");
	});	
	$("#amount_2").blur(function() {
		if($(this).val()==""){
		$("#amount_2Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#amount_2Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#amount_2Tag").text("");
	});	
	$("#amount_3").blur(function() {
		if($(this).val()==""){
		$("#amount_3Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#amount_3Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#amount_3Tag").text("");
	});	
	$("#amount_4").blur(function() {
		if($(this).val()==""){
		$("#amount_4Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#amount_4Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#amount_4Tag").text("");
	});	
	$("#amount_5").blur(function() {
		if($(this).val()==""){
		$("#amount_5Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#amount_5Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#amount_5Tag").text("");
	});	
	$("#amount_6").blur(function() {
		if($(this).val()==""){
		$("#amount_6Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#amount_6Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#amount_6Tag").text("");
	});	
	$("#amount_7").blur(function() {
		if($(this).val()==""){
		$("#amount_7Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#amount_7Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#amount_7Tag").text("");
	});	
	$("#amount_8").blur(function() {
		if($(this).val()==""){
		$("#amount_8Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#amount_8Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#amount_8Tag").text("");
	});	
	$("#amount_9").blur(function() {
		if($(this).val()==""){
		$("#amount_9Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#amount_9Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#amount_9Tag").text("");
	});	
	
	$("#amount_10").blur(function() {
		if($(this).val()==""){
		$("#amount_10Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#amount_10Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#amount_10Tag").text("");
	});	
	
	$("#sbtn").click(function(){
		if (!confirm("是否保存参数设置？")) {
			return false;
		} else {
		var errorstr = "";
			
		if($("#paramName").val()==""){
			$("#nameTag").text("参数名不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr=1;
		}else if($("#nameTag").text()!=""){
			if($("#nameTag").val()=="参数可用！") errorstr =1;
		}
		
		if($("#amount_1").val()==""){
			$("#amount_1").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#amount_1Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#amount_2").val()==""){
			$("#amount_2").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#amount_2Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#amount_3").val()==""){
			$("#amount_3").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#amount_3Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#amount_4").val()==""){
			$("#amount_4").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#amount_4Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#amount_5").val()==""){
			$("#amount_5Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#amount_5Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#amount_6").val()==""){
			$("#amount_6Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#amount_6Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#amount_7").val()==""){
			$("#amount_7Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#amount_7Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#amount_8").val()==""){
			$("#amount_8Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#amount_8Tag").text()!=""){
			errorstr ="1";
		}
		
			if($("#amount_9").val()==""){
			$("#amount_9Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#amount_9Tag").text()!=""){
			errorstr ="1";
		}
		
			if($("#amount_10").val()==""){
			$("#amount_10Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#amount_10Tag").text()!=""){
			errorstr ="1";
		}
		
		
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