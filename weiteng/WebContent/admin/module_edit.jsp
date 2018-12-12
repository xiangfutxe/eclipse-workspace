<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ssm.pojo.Admin" %>
<%@ page import="com.ssm.utils.Constants" %>
<%@ page import="com.ssm.utils.StringUtil" %>
<% 
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
if (admin == null) {
	out.println("<script>");
	out.println("parent.window.location.href='login.jsp';");
	out.println("</script>");
}else{
 String[][] ranks = StringUtil.getRankStr(admin.getRank());
	if(ranks[0][7].equals("1")||admin.getState()-1==0){
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模块编辑</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>
 <script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
 
 <script type="text/javascript" src="js/select-ui.min.js"></script>
 <link href="css/select.css" rel="stylesheet" type="text/css" />
 
<script>
KindEditor.ready(function(K) {
	window.editor=K.create('#editor_id', {
		  uploadJson : '../kindeditor/jsp/upload_json.jsp',
                fileManagerJson : '../kindeditor/jsp/file_manager_json.jsp',
                allowFileManager : true
	});
});
        
</script>
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
</script>

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">信息管理</a></li>
    <li><a href="#">模块详情</a></li>
    </ul>
    </div>
    
    <div class="formbody">
     <div id="usual1" class="usual"> 
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="ModuleServlet?method=update" id="myform" method="post">
     <input type="hidden" name="id" id="id" value="${module.id}"/> 
     <input type="hidden" name="token" id="token" value="${sessionScope.token}"/>
    <ul class="forminfo">
     <li><label>模块编号<b>*</b></label>
    <input name="code" id="code" type="text" class="dfinput" value="${module.code}"/><i id="codeTag"></i></li>
    
    <li><label>模块标题<b>*</b></label>
    <input name="title" id="title" type="text" class="dfinput" value="${module.title}"/><i id="titleTag"></i></li>
    <li><label>新闻正文<b>*</b></label><textarea id="editor_id" name="contents" style="width:700px;height:250px;visibility:hidden;">
    ${module.contents}</textarea><i id="contentsTag"></i></li>
    <li><label>&nbsp;</label><input id="save_click" type="button" class="btn" value="修改保存"/></li>
    </ul>
    </form>
     <div class="save_tip">
    	<div class="save_tiptop"><span>提示信息</span><a></a></div>
        
      <div class="save_tipinfo">
        <span><img src="images/ticon_save.png" /></span>
        <div class="save_tipright">
        <p>是否确认对信息的修改进行保存 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
       
        <div class="tipbtn">
        <input name="" type="button"  class="save_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="save_cancel" value="取消" />
        </div>
     </div>
    </div>
    </div>
    <script type="text/javascript">
$(function() {
	
	$("#code").blur(function() {
		if($(this).val()=="")$("#codeTag").text("模块编号不能为空！").css({"color":"red","font-size":"12px"});
		else $("#codeTag").text("");
	});
	
	$("#title").blur(function() {
		if($(this).val()=="")$("#titleTag").text("新闻标题不能为空！").css({"color":"red","font-size":"12px"});
		else $("#titleTag").text("");
	});
	
	$("#contents").blur(function() {
		if($(this).val()=="")$("#contentsTag").text("新闻正文不能为空！").css({"color":"red","font-size":"12px"});
	
		else $("#contentsTag").text("");
	});
	
	$("#source").blur(function() {
		if($(this).val()=="")$("#sourceTag").text("新闻来源不能为空！").css({"color":"red","font-size":"12px"});
	
		else $("#sourceTag").text("");
	});
	
	$("#sort").blur(function() {
		if($(this).val()=="")$("#sortTag").text("新闻分类不能为空！").css({"color":"red","font-size":"12px"});
		else $("#sortTag").text("");
	});
	
	$("#save_click").click(function(){
  $(".save_tip").fadeIn(200);
  });
  
  $(".save_tiptop a").click(function(){
  $(".save_tip").fadeOut(200);
});

  $(".save_sure").click(function(){
 		 $(".save_tip").fadeOut(100);
 		 editor.sync();
				var errorstr = "";
				if($("#code").val()==""){
					$("#codeTag").text("模块编号不能为空！").css({"color":"red","font-size":"12px"}); 
					errorstr ="1";
				}else if($("#codeTag").text()!=""){
					errorstr ="1";
				}
				
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
				if($("#source").val()==""){
					$("#sourceTag").text("新闻来源不能为空！").css({"color":"red","font-size":"12px"}); 
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
});

  $(".save_cancel").click(function(){
  $(".save_tip").fadeOut(100);
});
	
	
});
</script>
<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
</body>

</html>
<%
	}else{
		out.println("<script>");
		out.println("window.location.href='error_rank.jsp';");
		out.println("</script>");
	}
}
%>