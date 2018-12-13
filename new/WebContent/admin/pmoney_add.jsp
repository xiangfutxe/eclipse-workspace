<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.TimeParam" %>
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
		if(rankstr[6][8].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>电子券支持</title>
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
    <script src="js/calendar.js"></script>

  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="addmin/index.jsp">首页</a><span class="crumb-step">&gt;</span>财务管理<span class="crumb-step">&gt;</span><span>电子券支持</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form  method="post" id="myform" name="myform">
<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>会员编号：</th>
                                <td>
                                    <input class="common-text required" id="userId" name="userId"  type="text">&nbsp;<span id="nameTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>支持金额：</th>
								
                                <td>
                                    <input class="common-text required" id="amount" name="amount"  type="text" />&nbsp;<span id="amountTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>有效期：</th>
                                <td>
                                    <input class="common-text required" id="validtyTime" name="validtyTime"  type="text" onfocus="new Calendar().show(this);"/>&nbsp;<span id="timeTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>摘要：</th>
                                <td>
                                    <input class="common-text required" id="summary" name="summary"  type="text" />&nbsp;<span id="summaryTag"></span>
                                </td>
                            </tr>
                            
                             <tr>
                                <th><i class="require-red">备注：</i></th>
                                <td>
                                   				 此选中的有效期为支持用户所有电子券的有效期，请确保支持用户原有电子券已使用完毕！
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary" value="提交" type="button">
                                    <input id="addbtn" class="btn" value="重置"  type="reset">
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

var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/; //验证金额

var a = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;
		
	
	$("#amount").blur(function() {
		if($("#amount").val()==""){
			$("#amountTag").text("支持金额不能为空！").css({"color":"red","font-size":"12px"}); 
		}else if(!reg.test($("#amount").val())) $("#amountTag").text("数值格式有误，金额应为实数！").css({"color":"red","font-size":"12px"});
		else $("#amountTag").text("");
	});	
	
	$("#validtyTime").blur(function() {
		if ($("#validtyTime").val().match(a) == null) {
		$("#timeTag").text("有效期格式有误！").css({"color":"red","font-size":"12px"}); 
												errorstr=errorstr+"1";
												$("#validtyTime").focus();
		}else{
		$("#timeTag").text("");
		}
	});	
	
	$("#summary").blur(function() {
		if(getByteLen($("#summary").val())>100) $("#summaryTag").text("摘要字数不能大于50！").css({"color":"red","font-size":"12px"});
		else $("#summaryTag").text("");
	});	

$("#userId").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/UserServlet.do?method=userAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					userId : $("#userId").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.userName==""){
						 $("#nameTag").text("查无此人，请重新输入！").css({"color":"red","font-size":"12px"});
						 }
						else{
						 $("#nameTag").text(""+d.userName+"").css({"color":"green","font-size":"12px"});
						 }
					},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	});
	
	$("#sbtn").click(function(){
	
	if($("#amount").val()==""){
			$("#amountTag").text("支持金额不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if(!reg.test($("#amount").val())) $("#amountTag").text("数值格式有误，金额应为实数！").css({"color":"red","font-size":"12px"});
		else $("#amountTag").text("");
	
		  if (!confirm("确认提交保存？")) {
				return false;
			}else{
		var errorstr = "";
											
		if ($("#validtyTime").val().match(a) == null) {
		$("#validtyTime").text("有效期格式有误！").css({"color":"red","font-size":"12px"}); 
												errorstr=errorstr+"1";
												$("#validtyTime").focus();
		}else if($("#timeTag").val()!=""){
		errorstr=errorstr+"1";
		}
		
			
		if($("#userId").val()==""){
				$("#nameTag").text("用户编号不能为空！").css({"color":"red","font-size":"12px"});
				errorstr = errorstr+"1";
			}
			else if($("#nameTag").text()=="用户编号不能为空！"
			|| $("#nameTag").text()=="查无此人，请重新输入！"){
					errorstr = errorstr+"1";
			}
			
			if($("#amount").val()==""){
			$("#amountTag").text("支持金额不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr = errorstr +"1";
		}else if($("#amountTag").text()!="") errorstr = errorstr + "1";
	
		if($("#summaryTag").text()!="") errorstr = errorstr + "1";
	
		if(errorstr!=""){
			return false;
		}else{
		 $('#myform').attr("action","admin/AccountServlet.do?method=pmoney_save");
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
