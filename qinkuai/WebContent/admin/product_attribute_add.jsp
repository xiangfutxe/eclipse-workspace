<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.utils.StringUtil" %>
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
		if(rankstr[2][13].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增属性</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a>
            <span class="crumb-step">&gt;</span> <span>产品管理</span>
            <span class="crumb-step">&gt;</span> <span>属性管理</span>
            <span class="crumb-step">&gt;</span>
            <span>新增属性</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/ProductAttributeServlet.do?method=save" method="post" id="myform" name="myform">
                  <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="97%">
                        <tbody>
                            <tr>
                                <th width="120px"><i class="require-red">*</i>属性名称：</th>
                                <td>
                                    <input class="common-text required" id="name" name="name"  value="" type="text">&nbsp;<span id="nameTag"><span id="nameTag1"></span>
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
	$("#name").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/ProductAttributeServlet.do?method=isExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					name : $("#name").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="1"){
					  $("#nameTag1").text("属性名称已存在！").css({"color":"red","font-size":"9px"});
					 $("#nameTag").text("");
					
					}else if(d.tag=="2") {
					$("#nameTag").text("属性名称可用！").css({"color":"green","font-size":"9px"});
					 $("#nameTag1").text("");
					}else{
					 $("#nameTag").text("");
					  $("#nameTag1").text("");
					}
					if(getByteLen($("#name").val())>50){
					 $("#nameTag1").text("产品属性字数不能大于25！").css({"color":"red","font-size":"9px"});
					  $("#nameTag").text("");
					 }
				},
				error : function() {
					$("#nameTag1").text("数据操作失败！");
				}//这里不要加","
				
			});
	});
	
	$("#sbtn").click(function(){
			if (!confirm("确认保存产品属性？")) {
								return false;
							} else {
		var errorstr = "";
		if($("#name").val()==""){
			$("#nameTag").text("属性名称不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if($("#nameTag1").text()!=""){
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
