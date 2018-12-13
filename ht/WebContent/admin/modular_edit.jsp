<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Admin" %>
<%@page import="com.pojo.Modular" %>
<%@page import="com.utils.StringUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
String token = (String)request.getSession().getAttribute("token");
Modular mod = (Modular) request.getAttribute("modular");
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else{
	String[][] rankstr = StringUtil.getRankStr(admin.getRank());
		if(rankstr[0][3].equals("1")||admin.getState().equals("1")){
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加模块</title>
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
             <div class="crumb-list"><i class="icon-font"></i><a href="user/index.jsp">首页</a><span class="crumb-step">&gt;</span>模块管理<span class="crumb-step">&gt;</span><span>添加模块</span>
			<span class="crumb-step">&gt;</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
               <form action="admin/ModularServlet.do?method=update" method="post" id="myform">
               <input type="hidden" name="id" id="id" value="<%=mod.getId()%>"/>
               <table class="insert-tab" width="100%">
               	<tbody>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>模块名称：</th>
               		<td><input class="common-text" name="type" id="type" type="text" size="50" value="<%=StringUtil.notNull(mod.getType())%>">&nbsp;<span id="typeTag"></span>
               	 	<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
               		</td>
               	</tr>
               	 	<tr>
               		<th width="120px"><i class="require-red">*</i>模块标题：</th>
               		<td><input class="common-text" name="title" id="title" type="text" size="50" value="<%=StringUtil.notNull(mod.getTitle())%>">&nbsp;<span id="titleTag"></span>
      
               		</td>
               	</tr>
               		<tr>
               		<th width="120px"><i class="require-red">*</i>来源：</th>
               		<td><input class="common-text" name="source" id="source" type="text" size="50" value="<%=StringUtil.notNull(mod.getSource())%>">&nbsp;<span id="sourceTag"></span>
               		</td>
               	</tr>
               	 <tr>
                                <th><i class="require-red">*</i>模块内容：</th>
                                 <td><textarea id="contents" name="contents" style="width:700px;height:300px;">
                                <%=StringUtil.notNull(mod.getContents())%>
</textarea><span id="contentsTag"></span>
								 </td>
                            </tr>
                             <tr>
                                <th>辅助工具：</th>
                                 <td><textarea id="editor_id" name="editor_id" style="width:700px;height:300px;">
</textarea>
								 </td>
                            </tr>
                             <tr>
                                <th>&nbsp;</th>
                                <td>
                                    <input id="btn" class="btn btn-primarymr10" value="修改保存" type="button">
                                    &nbsp; <input id="btn1" class="btn"  value="重置" type="button">
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
	$("#title").blur(function() {
		if($(this).val()=="")$("#titleTag").text("模块标题不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($(this).val())>200) $("#titleTag").text("模块标题字数不能大于100！").css({"color":"red","font-size":"12px"});
		else $("#titleTag").text("");
	});
	
	$("#contents").blur(function() {
		if($(this).val()=="")$("#contentsTag").text("模块正文不能为空！").css({"color":"red","font-size":"12px"});
	
		else $("#contentsTag").text("");
	});
	
	$("#source").blur(function() {
		if($(this).val()=="")$("#sourceTag").text("发布来源不能为空！").css({"color":"red","font-size":"12px"});
	
		else $("#sourceTag").text("");
	});
	
	$("#type").blur(function() {
		if($(this).val()=="")$("#typeTag").text("模块名称不能为空！").css({"color":"red","font-size":"12px"});
		else $("#typeTag").text("");
	});
	
	
	$("#btn").click(function(){
		
		 if (!confirm("确认提交保存模块！")) {
				return false;
			}
			else{
			editor.sync();
				var errorstr = "";
				if($("#title").val()==""){
					$("#titleTag").text("模块标题不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#titleTag").text()!=""){
					errorstr ="1";
				}
				alert(errorstr);
				if($("#type").val()==""){
					$("#typeTag").text("模块名称不能为空！").css({"color":"red","font-size":"12px"});
					errorstr ="1";
				}else if($("#typeTag").text()!=""){
					errorstr ="1";
				}
				if($("#contents").val()==""){
					$("#contentsTag").text("正文内容不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#contentsTag").text()!=""){
					errorstr ="1";
				}
				
				if($("#source").val()==""){
					$("#sourceTag").text("发布来源不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#sourceTag").text()!=""){
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