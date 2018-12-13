<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ssm.pojo.Admin"%>
<%@ page import="com.utils.Constants"%>

<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
if(admin==null) {
out.println("<script>");
out.println("parent.window.location.href='login.jsp';");
out.println("</script>");
}else {
	if(admin.getType()-1>0){
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>详情修改</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tip.css" rel="stylesheet" type="text/css" />
 <script charset="utf-8" src="js/jquery.js"></script>
 <script src="../js/calendar.js"></script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">会员管理</a></li>
    <li><a href="#">详情修改</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    <ul class="forminfo">
    <li><label>会员昵称<b>*</b></label>
    <input name="nickName" id="nickName" type="text" class="dfinput" value="${member.nickName }" readonly="readonly"/><i id="nickNameTag"></i></li>
     <li><label>真实姓名<b>*</b></label><input name="realName" id="realName" type="text" class="dfinput"  value="${member.realName }"  readonly="readonly"/><i id="realNameTag"></i></li>
    </ul>
    </div>
  <div class="formbody">
  <form id="myform" method="post">
         <input type="hidden" name="id" id="id" value="${member.id}"/>
         <input type="hidden" name="version" id="version" value="${member.version}"/>
    <div class="formtitle"><span>其他操作</span></div>
     <div class="toolsli">
    <ul class="toollist">
    <li><a  class="psw1_click" href="javascript:void(0);"><img src="images/m01.png" /></a><h2>登陆密码重置</h2></li>
    <li><a  class="psw2_click" href="javascript:void(0);"><img src="images/m02.png" /></a><h2>支付密码重置</h2></li>
    <li><a href="#"><img src="images/m03.png" /></a><h2>会员锁定</h2></li>
    <li><a href="#"><img src="images/m04.png" /></a><h2>会员解锁</h2></li>      
    </ul>
    <span class="tooladd"><img src="images/add.png" title="添加" /></span>  
    </div>
    </form>
   
     <div class="psw1_tip">
    	<div class="psw1_tiptop"><span>提示信息</span><a></a></div>
        
      <div class="psw1_tipinfo">
        <span><img src="images/ticon_psw1.png" /></span>
        <div class="psw1_tipright">
        <p>是否重置当前会员登陆密码?</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        <div class="tipbtn">
        <input name="" type="button"  class="psw1_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="psw1_cancel" value="取消" />
        </div>
     </div>
     
      <div class="psw2_tip">
    	<div class="psw2_tiptop"><span>提示信息</span><a></a></div>
        
      <div class="psw2_tipinfo">
        <span><img src="images/ticon_psw2.png" /></span>
        <div class="psw2_tipright">
        <p>是否重置当前会员支付密码?</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        <div class="tipbtn">
        <input name="" type="button"  class="psw2_sure" value="确定" />&nbsp;
        <input name="" type="button"  class="psw2_cancel" value="取消" />
        </div>
     </div>
      </div>
    
 <script type="text/javascript">
$(function() {

//psw1_click;
	$(".psw1_click").click(function(){
  		$(".psw1_tip").fadeIn(200);
  	});
  
  $(".psw1_tiptop a").click(function(){
  $(".psw1_tip").fadeOut(200);
});

  $(".psw1_sure").click(function(){
 		 $(".psw1_tip").fadeOut(100);
 		 $("#myform").attr("action","member_psw_update.do");
 		 $("#myform").submit();
		 return true;
});

  $(".psw1_cancel").click(function(){
  $(".psw1_tip").fadeOut(100);
  return false;
});
	
	//psw2_click;
	$(".psw2_click").click(function(){
  		$(".psw2_tip").fadeIn(200);
  	});
  
  $(".psw2_tiptop a").click(function(){
  $(".psw2_tip").fadeOut(200);
});

  $(".psw2_sure").click(function(){
 		 $(".psw2_tip").fadeOut(100);
 		 $("#myform").attr("action","member_psw2_update.do");
 		 $("#myform").submit();
		 return true;
});

  $(".psw2_cancel").click(function(){
  $(".psw2_tip").fadeOut(100);
  return false;
});
});
</script>
</body>

</html>
<%
	}else{
		out.println("<script>");
				out.println("alert('你没有权限进行该操作！')");
				out.println("</script>");
	}
}
%>