<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.ProductSort" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Admin" %>
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
		if(rankstr[3][10].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增类型</title>
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
       <script type="text/javascript" src="js/custom.js"></script>
     
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>产品管理<span class="crumb-step">&gt;</span>
           <span>类型列表</span><span class="crumb-step">&gt;</span>
            <span>新增类型</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/ProductSortServlet?method=save" method="post" id="myform" name="myform">
                  <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="97%">
                        <tbody>
                            <tr>
                                <th width="120px"><i class="require-red">*</i>类型名称：</th>
                                <td>
                                    <input class="common-text required" id="typeName" name="typeName"  value="" type="text">&nbsp;<span id="nameTag"></span>
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
  
 <script language="JavaScript">
 
$(function() {
	$("#typeName").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/ProductSortServlet?method=isProductSortExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					typeName : $("#typeName").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="1")  $("#nameTag").text("类型名称已存在！").css({"color":"red","font-size":"9px"});
					else if(d.tag=="2")  $("#nameTag").text("类型名称可用！").css({"color":"green","font-size":"9px"});
					else $("#nameTag").text("");
					
					if(getByteLen($("#typeName").val())>50) $("#nameTag").text("产品类型字数不能大于25！").css({"color":"red","font-size":"9px"});
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	});
	
	$("#sbtn").click(function(){
			if (!confirm("确认保存产品类型？")) {
								return false;
							} else {
		var errorstr = "";
		if($("#typeName").val()==""){
			$("#nameTag").text("类型名称不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#nameTag").text()!="类型名称可用！"){
		alert("yes");
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
</html>
<%
}else{
			out.println("<script>");
			out.println("alert('你没有权限进行该操作！')");
			out.println("</script>");
		}
}
%>
