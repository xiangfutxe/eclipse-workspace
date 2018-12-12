<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.News" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
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
		if(rankstr[0][0].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加新闻</title>
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
       <script charset="utf-8" src="kindeditor/kindeditor-all.js"></script>
<script>
KindEditor.ready(function(K) {
	window.editor=K.create('#editor_id', {
		  uploadJson : 'kindeditor/jsp/upload_json.jsp',
                fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
                allowFileManager : true
	});
});
        
</script>
  </head>
  
  <body>
  <div class="container clearfix">
   <div class="main-wrap">
        <div class="crumb-wrap">
             <div class="crumb-list"><i class="icon-font"></i><a href="user/index.jsp">首页</a><span class="crumb-step">&gt;</span>信息管理<span class="crumb-step">&gt;</span>
             <span>短信模板</span>
			<span class="crumb-step">&gt;</span> <span>添加模板</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
               <form action="admin/ShortMessageServlet.do?method=admin_save" method="post" id="myform">
              <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
              
               <table class="insert-tab" width="97%">
               	<tbody>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>短信类型：</th>
               		<td><input class="common-text" name="type" id="type" type="text" size="50">&nbsp;<span id="typeTag"></span>
               		</td>
               	</tr>
               	 	<tr>
               		<th width="120px"><i class="require-red">*</i>手机号码：</th>
               		<td><input class="common-text" name="tel" id="tel" type="text" size="50">&nbsp;<span id="telTag"></span>
               		<br>如果需要输入多个手机号码，以“,"隔开。</td>
               	</tr>
               	 <tr>
                                <th><i class="require-red">*</i>短信内容：</th>
                                 <td><textarea id="shortMsg" name="shortMsg" style="width:400px;height:200px;">
</textarea><span id="msgTag"></span>
								 </td>
                            </tr>
                             <tr>
                                <th>&nbsp;</th>
                                <td>
                                    <input id="btn" class="btn btn-primarymr10" value="提交" type="button">
                                    &nbsp; <input id="btn1" class="btn"  value="重置" type="reset">
                                </td>
                            </tr>
               	</tbody>
               
               </table>
               </form>
               </div>
               </div>
               
    </div>
    </div>
  </body>
  
 <script language="JavaScript">
$(function() {
	$("#type").blur(function() {
		if($(this).val()=="")$("#typeTag").text("短信类型不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($(this).val())>50) $("#typeTag").text("短信类型字数不能大于25！").css({"color":"red","font-size":"12px"});
		else $("#typeTag").text("");
	});
	
	$("#tel").blur(function() {
		if($(this).val()=="")$("#telTag").text("手机号码不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($(this).val())>200) $("#telTag").text("手机号码长度不能大于100个字数！").css({"color":"red","font-size":"12px"});
		else $("#telTag").text("");
	});
	$("#shortMsg").blur(function() {
		if($(this).val()=="")$("#msgTag").text("短信内容不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($(this).val())>80) $("#msgTag").text("短信内容不能大于40个字！").css({"color":"red","font-size":"12px"});
		else $("#msgTag").text("");
	});
	
	
	$("#btn").click(function(){
		 if (!confirm("确认提交短信模板！")) {
				return false;
			}
			else{
				var errorstr = "";
				if($("#type").val()==""){
					$("#typeTag").text("短信类型不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#typeTag").text()!=""){
					errorstr ="1";
				}
				
				if($("#tel").val()==""){
					$("#telTag").text("手机号码不能为空").css({"color":"red","font-size":"12px"});
					errorstr ="1";
				}else if($("#telTag").text()!=""){
					errorstr ="1";
				}
				
				if($("#shortMsg").val()==""){
					$("#msgTag").text("短信内容不能为空").css({"color":"red","font-size":"12px"});
					errorstr ="1";
				}else if($("#msgTag").text()!=""){
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