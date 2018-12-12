<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.ChargeApply" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@page import="com.pojo.Admin" %>
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
		if(rankstr[6][0].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>现金充值</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>财务管理<span class="crumb-step">&gt;</span><span>现金充值</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form method="post" id="myform" name="myform">
				<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="10%"><i class="require-red">*</i>用户编号：</th>
                                <td>
                                    <input class="common-text required" id="userId" name="userId"  value="" type="text">&nbsp;<span id="nameTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>充值金额：</th>
                                <td>
                                    <input class="common-text required" id="amount" name="amount"  value="" type="text">&nbsp;<span id="amountTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red"></i>备注说明：</th>
                                <td>
                                    <input class="common-text required" id="remark" name="remark"  value="" type="text">&nbsp;<span id="remarkTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary btn4 mr10" value="提交" type="button">
                                    <input class="btn btn4" onClick="history.go(-1)" value="返回" type="button">
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
	
	$("#amount").blur(function() {
		if($(this).val()==""){
			$("#amountTag").text("充值金额不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if(!reg.test($(this).val())) $("#amountTag").text("数值格式有误，金额应为实数！").css({"color":"red","font-size":"12px"});
		else $("#amountTag").text("");
	});	
	
	$("#userId").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/BranchServlet.do?method=codeAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					code : $("#userId").val()
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
	
	$("#remark").blur(function() {
	if(getByteLen($(this).val())>200) $("#remarkTag").text("备注内容字数不能大于100！").css({"color":"red","font-size":"12px"});
	else $("#remarkTag").text("");
	});	
	
	
	$("#sbtn").click(function(){
		if (!confirm("确认要提交现金充值？")) {
            return false;
        }else{
		var errorstr = "";
		
		if($("#userId").val()==""){
				$("#nameTag").text("用户编号不能为空！").css({"color":"red","font-size":"12px"});
				errorstr = errorstr+"1";
			}
			else if($("#nameTag").text()=="用户编号不能为空！"
			|| $("#nameTag").text()=="查无此人，请重新输入！"){
					errorstr = errorstr+"1";
			}
			
			if($("#amount").val()==""){
			$("#amountTag").text("充值金额不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr = errorstr +"1";
		}else if($("#amountTag").text()!="") errorstr = errorstr + "1";
		
			if(getByteLen($("#remark").val())>200){
			 $("#remarkTag").text("备注内容字数不能大于100！").css({"color":"red","font-size":"12px"});
			 errorstr = errorstr + "4";
			 }else if($("#remarkTag").text()!="")  
			 errorstr = errorstr + "4";
		
		if(errorstr!=""){
			return false;
		}else{
			$('#myform').attr("action", "admin/ChargeApplyServlet.do?method=emoney_quick_save");
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
