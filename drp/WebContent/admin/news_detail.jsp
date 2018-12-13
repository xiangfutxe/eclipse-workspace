<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻详情</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>
 <script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script>
KindEditor.ready(function(K) {
	window.editor=K.create('#editor_id', {
		  uploadJson : '../kindeditor/jsp/upload_json.jsp',
                fileManagerJson : '../kindeditor/jsp/file_manager_json.jsp',
                allowFileManager : true
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
    <li><a href="#">新闻列表</a></li>
    <li><a href="#">新闻详情</a></li>
    </ul>
    </div>
    <div class="newsbody">
    <div class="news">
    <span class="news_title">${news.title}</span>
     <span class="news_subtitle">类型：${news.sort}；发布者：${news.publisher}</span>
    </div>    
    <div class="news_detail">
    ${news.contents}
    </div>
      <div class="news_bottom"><input id="btn" type="button" class="btn" value="返回上一页" onclick="javascript:history.go(-1);"/></div>
   </div>
</body>

</html>
