<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ssm.pojo.Admin"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@page import="com.ssm.utils.StringUtil" %>


<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else {
	  String[][] ranks = StringUtil.getRankStr(admin.getRank());
			if(ranks[3][2].equals("1")||admin.getState()-1==0){
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上传图片</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>
 <script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">产品管理</a></li>
    <li><a href="#">上传图片</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
  
     <form action="ProductServlet?method=img_save" id="myform" method="post" enctype="multipart/form-data">
      <input type="hidden" name="id" id="id" value="${product.id }"/> 
      <input type="hidden" name="token" id="token" value="${sessionScope.token }"/> 
    <ul class="forminfo">
    <li><label>产品编号<b></b></label>
  ${product.productId}</li>
   <li><label>产品名称<b></b></label>
  ${product.productName}</li>
   <li><label>产品原图<b></b></label><img src="../upload/product/${product.imageUrl}" alt="暂无图片" width="300px" height="300px"/>
   <img src="../upload/product/${product.imageUrl1}" alt="暂无图片" width="300px" height="300px"/>
   <img src="../upload/product/${product.imageUrl2}" alt="暂无图片" width="300px" height="300px"/>
   <img src="../upload/product/${product.imageUrl3}" alt="暂无图片" width="300px" height="300px"/>
   <img src="../upload/product/${product.imageUrl4}" alt="暂无图片" width="300px" height="300px"/></li>
     <li><label>上传新图1<b></b></label> <input name="imageUrl" id="imageUrl" type="file" /></li>
     <li><label>上传新图2<b></b></label> <input name="imageUrl1" id="imageUrl1" type="file" /></li>
     <li><label>上传新图3<b></b></label> <input name="imageUrl2" id="imageUrl2" type="file" /></li>
     <li><label>上传新图4<b></b></label> <input name="imageUrl3" id="imageUrl3" type="file" /></li>
     <li><label>上传新图5<b></b></label> <input name="imageUrl4" id="imageUrl4" type="file" /></li>
  
    <li><label>&nbsp;</label>
    <%if(ranks[3][2].equals("1")||admin.getState()-1==0){ %>
    <input id="save_click"  type="button" class="btn" value="确认上传"/>
    <%} %> 
    <input  type="button" class="btn" value="返回上一层" onclick="javascript:history.go(-1);"/></li>
    </ul>
    </form>
     <div class="save_tip">
    	<div class="save_tiptop"><span>提示信息</span><a></a></div>
      <div class="save_tipinfo">
        <span><img src="images/ticon_save.png" /></span>
        <div class="save_tipright">
        <p>是否确认对图片的进行重新上传？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
       
        <div class="tipbtn">
        <input name="" type="button"  class="save_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="save_cancel" value="取消" />
        </div>
     </div>
    </div>
  <script language="JavaScript">
$(function() {
 
	

	$("#save_click").click(function(){
		  $(".save_tip").fadeIn(200);
		  });
		  
		  $(".save_tiptop a").click(function(){
		  $(".save_tip").fadeOut(200);
		});

	$(".save_sure").click(function(){
		 		 $(".save_tip").fadeOut(100);
		var errorstr = "";
		
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