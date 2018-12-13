<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.utils.StringUtil" %>
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
		if(rankstr[6][11].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>账户补扣</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>财务管理<span class="crumb-step">&gt;</span><span>账户补扣</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form method="post" id="myform" name="myform">
                <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                                <th width="150px"><i class="require-red">*</i>账户类型：</th>
                                <td><select name="type" id="type" class="common-text required">
                                <option value="">--请选择补扣账户类型--</option>
                                <option value="1">报单券账户</option>
								  <option value="4">奖金券账户</option>
								    <option value="5">促销币账户</option>
                                </select>
                                   &nbsp;<span id="typeTag"></span>
                                </td>
                            </tr>
							  <tr>
                                <th width="150px"><i class="require-red">*</i>补扣类型：</th>
                                <td><select name="payType" id="payType" class="common-text required">
                                <option value="2">--补扣--</option>
                                <option value="1">--补增--</option>
                                </select>
                                   &nbsp;<span id="payTypeTag"></span>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>用户编号：</th>
                                <td>
                                    <input class="common-text required" id="userId" name="userId"  value="" type="text">&nbsp;<span id="nameTag"></span>
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>金额：</th>
                                <td>
                                    <input class="common-text required" id="amount" name="amount"  value="" type="text">&nbsp;<span id="amountTag"></span>
									
                                </td>
                            </tr>
                              <tr>
                                <th><i class="require-red">*</i>摘要：</th>
                                <td>
                                    <input type="text" class="common-text required" id="summary" name="summary" />&nbsp;<span id="summaryTag"></span>
                                </td>
                            </tr>
							  <tr>
                            
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
	var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/; //验证金额
	$("#amount").blur(function() {
		if($(this).val()==""){
			$("#amountTag").text("充值金额不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if(!reg.test($(this).val())) $("#amountTag").text("数值格式有误，金额应为实数！").css({"color":"red","font-size":"9px"});
		else $("#amountTag").text("");
	});	
	
	$("#type").blur(function() {
		if($(this).val()==""){
				$("#typeTag").text("账户类型不能为空！").css({"color":"red","font-size":"9px"});
			}else $("#typeTag").text("");
	});	
	$("#summary").blur(function() {
		if(getByteLen($("#summary").val())>50) $("#summaryTag").text("摘要字数不能大于25！").css({"color":"red","font-size":"9px"});
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
					var d =  data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.userName==""){
						 $("#nameTag").text("查无此人，请重新输入！").css({"color":"red","font-size":"9px"});
						 }
						else{
						 $("#nameTag").text(""+d.userName+"").css({"color":"green","font-size":"9px"});
						 }
					},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	});
	
	
	$("#sbtn").click(function(){
	if (!confirm("确认提交补扣，请认真核对补扣信息？")) {
				window.event.returnValue = false;
			}else{
		var errorstr = "";
		
		
		if($("#type").val()==""){
				$("#typeTag").text("账户类型不能为空！").css({"color":"red","font-size":"9px"});
				errorstr = errorstr+"1";
			}
			else if($("#typeTag").text()!=""){
					errorstr = errorstr+"1";
			}else $("#typeTag").text("");
		
		if($("#payType").val()==""){
				$("#payTypeTag").text("补扣类型不能为空！").css({"color":"red","font-size":"9px"});
				errorstr = errorstr+"1";
			}
			else if($("#payTypeTag").text()!=""){
					errorstr = errorstr+"1";
			}else $("#payTypeTag").text("");
		
		if($("#userId").val()==""){
				$("#nameTag").text("用户编号不能为空！").css({"color":"red","font-size":"9px"});
				errorstr = errorstr+"1";
			}
			else if($("#nameTag").text()=="用户编号不能为空！"
			|| $("#nameTag").text()=="查无此人，请重新输入！"){
					errorstr = errorstr+"1";
			}
			
			if($("#amount").val()==""){
			$("#amountTag").text("金额不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr = errorstr +"1";
		}else if($("#amountTag").text()!="") errorstr = errorstr + "1";
		
		if($("#summaryTag").text()!="") errorstr = errorstr + "1";
		
		if(errorstr!=""){
			return false;
		}else{
			$('#myform').attr("action", "admin/AccountSupplementServlet.do?method=save");
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