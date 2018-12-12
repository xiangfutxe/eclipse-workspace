<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.InventoryApply" %>

<%@page import="com.utils.StringUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
InventoryApply ivy = (InventoryApply)request.getAttribute("sys_ivy");

if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
	  String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[3][5].equals("1")||admin.getState().equals("1")){
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>仓库管理<span class="crumb-step">&gt;</span><span>采购审批</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="admin/InventoryServlet.do?method=apply_in_approve_no" method="post" id="myform" name="myform">
                 <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                                     <input type="hidden" name="id" id="id" value="<%=StringUtil.notNull(ivy.getApplyId())%>"/> 
                    
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th width="150px"><i class="require-red">*</i>入库单号：</th>
                                <td>
<%=StringUtil.notNull(ivy.getApplyId()) %>                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>审批不通过备注：</th>
                                <td>
                                <textarea rows="10" cols="50" id="remark" name="remark"></textarea>
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
				url : "admin/InventoryServlet.do?method=isExit",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
				data : {//设置数据源
					inventoryName : $("input[name=inventoryName]").val()
				//这里不要加","  不然会报错，而且根本不会提示错误地方

				},

				dataType : "json",//设置需要返回的数据类型
				success : function(data) {
					var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
					//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
					
					if(d.tag=="0") $("#nameTag").text("仓库名称不能为空！").css({"color":"red","font-size":"9px"});
					else if(d.tag=="1")  $("#nameTag").text("仓库名称已存在！").css({"color":"red","font-size":"9px"});
					else if(d.tag=="2")  $("#nameTag").text("仓库名称可用！").css({"color":"green","font-size":"9px"});
					else $("#nameTag").text("");
					
					if(getByteLen($("#name").val())>50) $("#nameTag").text("仓库名称字数不能大于25！").css({"color":"red","font-size":"9px"});
				},
				error : function() {
					alert("系统异常，请稍后重试！");
				}//这里不要加","
				
			});
	});
	
	$("#linkman").blur(function() {
	if($(this).val=="") $("#linkmanTag").text("仓库负责人不能为空！").css({"color":"red","font-size":"9px"}); 
	else if(getByteLen($(this).val)>20) $("#linkmanTag").text("仓库负责人字数不能大于10！").css({"color":"red","font-size":"9px"});
	else $("#linkmanTag").text("");
	});
	
	$("#address").blur(function() {
	if($(this).val=="") $("#addressTag").text("仓库所在地不能为空！").css({"color":"red","font-size":"9px"}); 
	else if(getByteLen($(this).val)>200) $("#addressTag").text("仓库所在地字数不能大于100！").css({"color":"red","font-size":"9px"});
	else $("#addressTag").text("");
	});
	
	 $("#phone").blur(function(){
   	 if($(this).val()=="")$("#phoneTag").text("联系电话不能为空！").css({"color":"red","font-size":"9px"}); 
	else if(!(reg.test($(this).val()))) $("#phoneTag").text("联系电话格式有误！").css({"color":"red","font-size":"9px"}); 
	else if($(this).val().length>13) $("#phoneTag").text("联系电话格式有误！").css({"color":"red","font-size":"9px"});
	else $("#phoneTag").text("");
   });
	
	$("#sbtn").click(function(){
		 if (!confirm("确认提交保存仓库？")) {
				return false;
			}else{
		var errorstr = "";
		if($("#inventoryName").val()==""){
			$("#nameTag").text("仓库名称不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if(getByteLen($("#name").val)>50){
			$("#nameTag").text("仓库名称字数不能大于25！").css({"color":"red","font-size":"9px"});
			errorstr ="1";
		}else if($("#nameTag").text()!="仓库名称可用！"){
			errorstr ="1";
		}
		
		if($("#linkman").val()==""){
			$("#linkmanTag").text("仓库负责人不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if(getByteLen($("#linkman").val)>20){
			$("#linkmanTag").text("仓库负责人字数不能大于10！").css({"color":"red","font-size":"9px"});
			errorstr ="1";
		}else if($("#linkmanTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#address").val()==""){
			$("#addressTag").text("仓库所在地不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if(getByteLen($("#address").val)>200){
			$("#addressTag").text("仓库所在地字数不能大于100！").css({"color":"red","font-size":"9px"});
			errorstr ="1";
		}
		else if($("#addressTag").text()!=""){
			errorstr ="1";
		}
		
		if($("#phone").val()==""){
			$("#phoneTag").text("联系电话不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if(!(reg.test($("#phone").val()))){
		$("#phoneTag").text("联系电话格式有误！").css({"color":"red","font-size":"9px"});
			errorstr ="1";
		}else if($("#phoneTag").val().length>11){
			$("#phoneTag").text("联系电话格式有误！").css({"color":"red","font-size":"9px"});
			errorstr ="1";
		}else if($("#phoneTag").text()!=""){
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