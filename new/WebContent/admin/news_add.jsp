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
             <div class="crumb-list"><i class="icon-font"></i><a href="user/index.jsp">首页</a><span class="crumb-step">&gt;</span>新闻管理<span class="crumb-step">&gt;</span><span>添加新闻</span>
			<span class="crumb-step">&gt;</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
               <form action="admin/NewsServlet.do?method=save" method="post" id="myform">
               <table class="insert-tab" width="100%">
               	<tbody>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>新闻标题：</th>
               		<td><input class="common-text" name="title" id="title" type="text" size="50">&nbsp;<span id="titleTag"></span>
               	 	<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/>
               		</td>
               	</tr>
               	<tr>
               		<th width="120px"><i class="require-red">*</i>新闻分类：</th>
               		<td>
                                 <select class="common-text" id="sort" name="sort">
                                    <option value='通知公告'>通知公告</option>
                                   <option value='公司动态'>公司动态</option>
                                </select>
                               &nbsp;<span id="sortTag"></span>
                            </td>
               	</tr>
               	 <tr>
                                <th><i class="require-red">*</i>新闻正文：</th>
                                 <td><textarea id="editor_id" name="contents" style="width:700px;height:300px;">
</textarea><span id="contentsTag"></span>
								 </td>
                            </tr>
                             <tr>
                                <th>&nbsp;</th>
                                <td>
                                    <input id="btn" class="btn btn-primarymr10" value="提交" type="button">
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
		if($(this).val()=="")$("#titleTag").text("新闻标题不能为空！").css({"color":"red","font-size":"12px"});
		else if(getByteLen($(this).val())>200) $("#titleTag").text("新闻标题字数不能大于100！").css({"color":"red","font-size":"12px"});
		else $("#titleTag").text("");
	});
	
	$("#contents").blur(function() {
		if($(this).val()=="")$("#contentsTag").text("新闻正文不能为空！").css({"color":"red","font-size":"12px"});
	
		else $("#contentsTag").text("");
	});
	
	$("#sort").blur(function() {
		if($(this).val()=="")$("#sortTag").text("新闻分类不能为空！").css({"color":"red","font-size":"12px"});
		else $("#sortTag").text("");
	});
	
	
	$("#btn").click(function(){
		 if (!confirm("确认提交保存新闻！")) {
				return false;
			}
			else{
				editor.sync();
				var errorstr = "";
				if($("#title").val()==""){
					$("#titleTag").text("新闻标题不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#titleTag").text()!=""){
					errorstr ="1";
				}
				
				if($("#sort").val()==""){
					$("#sortTag").text("未定义新闻分类，请在添加分类中添加分类！").css({"color":"red","font-size":"12px"});
					errorstr ="1";
				}else if($("#sortTag").text()!=""){
					errorstr ="1";
				}
				if($("#contents").val()==""){
					$("#contentsTag").text("正文内容不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#contentsTag").text()!=""){
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