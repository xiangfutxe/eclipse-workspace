<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>

<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.pojo.Promotion" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
Promotion pro = (Promotion) request.getAttribute("promotion");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[9][20].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>参数修改</title>
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
        <script type="text/javascript" src="js/lhgcalendar.min.js"></script>
       <script type="text/javascript">
	 $(function(){
	   $('#startTime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
    $('#endTime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
});
</script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>基础设置<span class="crumb-step">&gt;</span><span>修改参数</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form method="post" id="myform" name="myform">
                <input type="hidden" id="id" name="id" value='<%=pro.getId() %>' />
                	<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                          <tr>
                                <th width="10%"><i class="require-red">*</i>促销名称：</th>
                                <td>
                                    <input class="common-text required" id="name" name="name"  value="<%=StringUtil.notNull(pro.getName()) %>" type="text">&nbsp;<span id="nameTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>参数1：</th>
                                <td>
                                    <input class="common-text required" id="num1" name="num1" value="<%=StringUtil.decimalFormat(pro.getNum1()) %>" type="text">&nbsp;<span id="num1Tag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>参数2：</th>
                                <td>
                                    <input class="common-text required" id="num2" name="num2"  value="<%=StringUtil.decimalFormat(pro.getNum2()) %>" type="text">&nbsp;<span id="num2Tag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>参数3：</th>
                                <td>
                                    <input class="common-text required" id="num3" name="num3" value="<%=StringUtil.decimalFormat(pro.getNum3()) %>" type="text">&nbsp;<span id="num3Tag"></span>
                                </td>
                            </tr>
							 <tr>
                                <th><i class="require-red">*</i>参数4：</th>
                                <td>
                                    <input class="common-text required" id="num4" name="num4" value="<%=StringUtil.decimalFormat(pro.getNum4()) %>" type="text">&nbsp;<span id="num4Tag"></span>
                                </td>
                            </tr>
							  <tr>
                                <th><i class="require-red">*</i>参数5：</th>
                                <td>
                                    <input class="common-text required" id="num5" name="num5" value="<%=StringUtil.decimalFormat(pro.getNum5()) %>" type="text">&nbsp;<span id="num5Tag"></span>
                                </td>
                            </tr>
							  <tr>
                                <th><i class="require-red">*</i>开始时间</th>
                                <td>
                                    <input class="common-text required" id="startTime" name="startTime" value="<%=StringUtil.parseToString(pro.getStartTime(), DateUtil.yyyyMMddHHmmss) %>"  type="text">&nbsp;<span id="startTimeTag"></span>
                                </td>
                            </tr>
							  <tr>
                                <th><i class="require-red">*</i>结束时间</th>
                                <td>
                                    <input class="common-text required" id="endTime" name="endTime"  value="<%=StringUtil.parseToString(pro.getEndTime(), DateUtil.yyyyMMddHHmmss) %>" type="text">&nbsp;<span id="endTimeTag"></span>
                                </td>
                            </tr>
							  <tr>
                                <th><i class="require-red">*</i>摘要</th>
                                <td>
                                    <input class="common-text required" id="summary" name="summary" value="<%=StringUtil.notNull(pro.getSummary()) %>" type="text">&nbsp;<span id="summaryTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary" value="提交修改" type="button">
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
	$("#paramName").change(function() {
		$.ajax({
				type : "post",
				url : "admin/PromotionServlet?method=isExitUpdate",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				param : {//设置数据源
					id:$("#id").val(),
					name : $("input[name=name]").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				paramType : "json",//设置需要返回的数据类型
				success : function(param) {
					var d = eval("(" + param + ")");//将数据转换成json类型，可以把param用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="0") $("#nameTag").text("参数名不能为空！").css({"color":"red","font-size":"12px"});
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
	

	
	$("#num1").blur(function() {
		if($(this).val()==""){
		$("#num1Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#num1Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#num1Tag").text("");
	});	
	$("#num2").blur(function() {
		if($(this).val()==""){
		$("#num2Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#num2Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#num2Tag").text("");
	});	
	$("#num3").blur(function() {
		if($(this).val()==""){
		$("#num3Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#num3Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#num3Tag").text("");
	});	
	$("#num4").blur(function() {
		if($(this).val()==""){
		$("#num4Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#num4Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#num4Tag").text("");
	});	
	$("#num5").blur(function() {
		if($(this).val()==""){
		$("#num5Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
	}else if(!reg.test($(this).val())) $("#num5Tag").text("参数值格式有误！").css({"color":"red","font-size":"12px"}); 
	else $("#num5Tag").text("");
	});	
	$("#startTime").blur(function() {
		if($(this).val()==""){
		$("#startTimeTag").text("开始时间不能为空！").css({"color":"red","font-size":"12px"}); 
		}else $("#startTimeTag").text("");
	});	
	$("#endTime").blur(function() {
		if($(this).val()==""){
		$("#endTimeTag").text("结束时间不能为空！").css({"color":"red","font-size":"12px"}); 
		}else $("#endTimeTag").text("");
	});	
	$("#summary").blur(function() {
		if($(this).val()==""){
		$("#summaryTag").text("摘要不能为空！").css({"color":"red","font-size":"12px"}); 
	}else $("#summaryTag").text("");
	});	
	
	$("#sbtn").click(function(){
		var errorstr = "";
		if($("#type").val()==""){
			$("#nameTag").text("参数名不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr=1;
		}else if($("#nameTag").text()!=""){
			if($("#nameTag").val()=="参数可用！") errorstr =1;
		}
		
		if($("#num1").val()==""){
			$("#num1").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#num1Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#num2").val()==""){
			$("#num2").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#num2Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#num3").val()==""){
			$("#num3").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#num3Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#num4").val()==""){
			$("#num4").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#num4Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#num5").val()==""){
			$("#num5Tag").text("参数值不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#num5Tag").text()!=""){
			errorstr ="1";
		}
		
		if($("#summary").val()==""){
			$("#summaryTag").text("摘要不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#summaryTag").text()!=""){
			errorstr ="1";
		}
		
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var str = "";
		var a = /^(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1])) (?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]:[0-5][0-9]$/;
											
		if (startTime == "") {
				str = str + "开始时间不能为空！\n";
				$("#startTimeTag").text("开始时间不能为空。").css({"color":"red","font-size":"12px"}); 
					$("#startTime").focus();
		}else if (startTime.match(a) == null) {
			str = str + "开始时间格式有误！\n";
			$("#startTimeTag").text("开始时间格式有误。").css({"color":"red","font-size":"12px"}); 
				$("#startTime").focus();
		}else if($("#startTimeTag").text()!=""){
			str +=$("#startTimeTag").text();
		}
		if (endTime == "") {
			str = str + "结束时间不能为空！\n";
			$("#endTimeTag").text("结束时间不能为空。").css({"color":"red","font-size":"12px"}); 
			$("#endTime").focus();
		} else if (endTime.match(a) == null) {
				str = str + "结束时间格式有误！\n";
				$("#startTimeTag").text("结束时间格式有误。").css({"color":"red","font-size":"12px"}); 
				$("#endTime").focus();
		}else if(startTime>endTime){
			str = str + "开始时间不能超过结束时间！\n";
			$("#endTimeTag").text("开始时间不能超过结束时间。").css({"color":"red","font-size":"12px"}); 
			$("#endTime").focus();
		}else if($("#endTimeTag").text()!=""){
			str +=$("#endTimeTag").text();
		}
		if(errorstr!=""||str!=""){
			return false;
		}else{
			$('#myform').attr("action", "admin/PromotionServlet?method=updatePro");
	       	$('#myform').submit();
       		 return ture;
		
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