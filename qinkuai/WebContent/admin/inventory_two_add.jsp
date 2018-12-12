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
		if(rankstr[3][0].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增仓库</title>
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
            <span class="crumb-step">&gt;</span>仓库管理
            <span class="crumb-step">&gt;</span><span>新增逻辑仓库</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/InventoryServlet.do?method=two_save" method="post" id="myform" name="myform">
                 <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>仓库名称：</th>
                                <td>
                                    <input class="common-text required" id="inventoryName" name="inventoryName" value="" type="text">&nbsp;<span id="nameTag"></span>
                                </td>
                            </tr>
                            
                            <tr>
                                <th></th>
                                <td>
                                    <input id="sbtn" class="btn btn-primary mr10" value="提交" type="submit">
                                    <input class="btn " onClick="history.go(-1)" value="返回" type="button">
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
 var reg =/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/; //电话号码与手机号码同时验证
		
	$("#inventoryName").blur(function() {
		$.ajax({
				type : "post",
				url : "admin/InventoryServlet.do?method=two_isExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					inventoryName : $("input[name=inventoryName]").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="0") $("#nameTag").text("逻辑仓库名称不能为空！").css({"color":"red","font-size":"9px"});
					else if(d.tag=="1")  $("#nameTag").text("逻辑仓库名称已存在！").css({"color":"red","font-size":"9px"});
					else if(d.tag=="2")  $("#nameTag").text("逻辑仓库名称可用！").css({"color":"green","font-size":"9px"});
					else $("#nameTag").text("");
					
					if(getByteLen($("#name").val())>50) $("#nameTag").text("逻辑仓库名称字数不能大于25！").css({"color":"red","font-size":"9px"});
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	});
	
	
	
	$("#sbtn").click(function(){
		 if (!confirm("确认提交保存逻辑仓库？")) {
				return false;
			}else{
		var errorstr = "";
		if($("#inventoryName").val()==""){
			$("#nameTag").text("逻辑仓库名称不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if(getByteLen($("#name").val)>50){
			$("#nameTag").text("逻辑仓库名称字数不能大于25！").css({"color":"red","font-size":"9px"});
			errorstr ="1";
		}else if($("#nameTag").text()!="逻辑仓库名称可用！"){
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