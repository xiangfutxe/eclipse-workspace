<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.Param" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
	    			
		if(rankstr[9][2].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增参数</title>
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
  </head>
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>
            <a href="admin/index.action">首页</a><span class="crumb-step">&gt;</span>
            <a class="crumb-name" href="admin/bank_account.action">基础设置</a>
            <span class="crumb-step">&gt;</span><span>新增参数</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form method="post" id="myform" name="myform" action="admin/ParamServlet.do?method=save">
                	<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
                    <table class="insert-tab" width="97%">
                        <tbody>
                            <tr>
                                <th width="10%"><i class="require-red">*</i>参数名称：</th>
                                <td>
                                    <input class="common-text required" id="paramName" name="paramName"  value="" type="text">&nbsp;<span id="nameTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>参数1：</th>
                                <td>
                                    <input class="common-text required" id="amount_1" name="amount_1" value="0" type="text">&nbsp;<span id="amount_1Tag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>参数2：</th>
                                <td>
                                    <input class="common-text required" id="amount_2" name="amount_2"  value="0" type="text">&nbsp;<span id="amount_2Tag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>参数3：</th>
                                <td>
                                    <input class="common-text required" id="amount_3" name="amount_3" value="0" type="text">&nbsp;<span id="amount_3Tag"></span>
                                </td>
                            </tr>
							 <tr>
                                <th><i class="require-red">*</i>参数4：</th>
                                <td>
                                    <input class="common-text required" id="amount_4" name="amount_4" value="0" type="text">&nbsp;<span id="amount_4Tag"></span>
                                </td>
                            </tr>
							  <tr>
                                <th><i class="require-red">*</i>参数5：</th>
                                <td>
                                    <input class="common-text required" id="amount_5" name="amount_5" value="0" type="text">&nbsp;<span id="amount_5Tag"></span>
                                </td>
                            </tr>
							  <tr>
                                <th><i class="require-red">*</i>参数6：</th>
                                <td>
                                    <input class="common-text required" id="amount_6" name="amount_6" value="0" type="text">&nbsp;<span id="amount_6Tag"></span>
                                </td>
                            </tr>
							  <tr>
                                <th><i class="require-red">*</i>参数7：</th>
                                <td>
                                    <input class="common-text required" id="amount_7" name="amount_7" value="0" type="text">&nbsp;<span id="amount_7Tag"></span>
                                </td>
                            </tr>
							  <tr>
                                <th><i class="require-red">*</i>参数8：</th>
                                <td>
                                    <input class="common-text required" id="amount_8" name="amount_8" value="0" type="text">&nbsp;<span id="amount_8Tag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>参数9：</th>
                                <td>
                                    <input class="common-text required" id="amount_9" name="amount_9" value="0" type="text">&nbsp;<span id="amount_9Tag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>参数10：</th>
                                <td>
                                    <input class="common-text required" id="amount_10" name="amount_10" value="0" type="text">&nbsp;<span id="amount_10Tag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th>单位：</th>
                                <td>
                                    <input class="common-text required" id="unit" name="unit" value="" type="text">&nbsp;<span id="unitTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary mr10" value="提交" type="button">
                                    <input class="btn" onClick="history.go(-1)" value="返回" type="button">
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

	var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
	$("#type").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/ParamServlet.do?method=checkDataType",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					type : $("input[name=type]").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = eval("(" + data + ")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="0") $("#nameTag").text("参数不能为空！").css({"color":"red","font-size":"12px"});
					else if(d.tag=="1")  $("#nameTag").text("参数已存在！").css({"color":"red","font-size":"12px"});
					else if(d.tag=="2")  $("#nameTag").text("参数可用！").css({"color":"green","font-size":"12px"});
					else $("#nameTag").text("");
					
					if(getByteLen($(this).val())>50) $("#nameTag").text("参数字数不能大于25！").css({"color":"red","font-size":"12px"});
				},
				error : function() {
					alert("系统异常，请稍后重试！");
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
		var errorstr = "";
			
		if($("#type").val()==""){
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