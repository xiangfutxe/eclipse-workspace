<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.Address"%>
<%@ page import="com.ssm.pojo.User"%>

<%@ page import="java.util.*"%>
<%@ page import="com.ssm.utils.Constants"%><%
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
String token = (String)request.getSession().getAttribute("token");
User userinfo  = (User) request.getSession().getAttribute(Constants.USERINFO_SESSION);
if(null==user){
	out.println("<script>");
	out.println("window.location.href='login.jsp';");
	out.println("</script>");
}else{
 %>
<!DOCTYPE html>
    <!-- 作者：mark_wang  QQ:275881702 -->  
<html><head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
          <link href="css/font-awesome.min.css" rel="stylesheet">
           <link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/adr.css">
          <title>添加地址</title>
          </head>
<body>


<header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">新增收货地址</div>
        </div>
    </div>
</header>
<form action="AddressServlet?method=save" method="post" id="myform" name="myform">

<div id="container">
    <div class="address_main">
     <input type="hidden" name="token" id="token" value="<%=token%>"/> 
          <input type="hidden" name="tag" id="tag" value="${sessionScope.tag }"/> 
     
        <input type="hidden" id="addressid" value="">
		<input type="hidden" id="spuid" value="3074">
        <div class="line">收&nbsp;件&nbsp;人&nbsp;：<input type="text" placeholder="" id="reciver" name="reciver" value=""></div>
        <div class="line">联系电话：<input type="text" placeholder="" id="phone" name="phone" value=""></div>

        <div class="line">
		<label>所属省份：</label>
        <select id="s_province" name="province"></select><br>		
		</div>
         <div class="line">
         <label>所&nbsp;属&nbsp;市：</label>
		  <select id="s_city" name="city" ></select><br><!-- sel -->
		 </div>
         <div class="line">
		  <label>所属县区：</label>
		 <select id="s_county" name="county"></select><br>
		 </div>
            <script type="text/javascript" src="js/area.js"></script>
            <script type="text/javascript">_init_area();</script>
	 	 
        <div class="line">
        <label>详细地址：</label><input type="text" placeholder="" id="address" name="address" value=""></div>
    </div>

    <div class="address_sub1"  onclick="copyText()" >确认</div>
    <div class="address_sub2"   onclick="showLoader()">取消</div>
</div>
</form>
</body>

<!-- <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script> -->
<SCRIPT src="js/jquery-1.7.1.min.js" type="text/javascript"></SCRIPT>
<script type="text/javascript" src="js/jquery.gcjs.js"></script>
<script type="text/javascript">
   function copyText(){
    if (!confirm("确认保存收货地址？")) {
            return false;
        } else {
            error = "";
            if($("#reciver").val()=="") error=error+"收件人不能为空！/n";
             if($("#phone").val()=="") error=error+"手机号码不能为空！/n";
              if($("#address").val()=="") error=error+"详细地址不能为空！/n";
              if(error==""){
             $('#myform').submit();
             return true;
             }else  return false;
        }
   }

</script>
</html>
<%}%>