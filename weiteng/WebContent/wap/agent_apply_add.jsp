<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.Address"%>
<%@ page import="com.ssm.pojo.User"%>

<%@ page import="java.util.*"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
String token = (String)request.getSession().getAttribute("token");
User user1 = (User) request.getAttribute("user1");
User userinfo  = (User) request.getSession().getAttribute(Constants.USERINFO_SESSION);
if(null==user){
	out.println("<script>");
	out.println("window.location.href='login.jsp';");
	out.println("</script>");
}else{
	if(user1!=null){
 %>
<!DOCTYPE html>
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

          <title>成为代理商</title>
       
          </head>
<body>
<header class="header">
	<div class="fix_nav">
		<div class="nav_inner">
			<a class="nav-left back-icon" href="javascript:history.back();"></a>
			<div class="tit">代理申请</div>
			<a class=" nav-right home-icon" href="UserServlet?method=index">首页</a>
		</div>
	</div>
</header>

    <div class="address_main">
    <form action="AgentApplyServlet?method=confirm" method="post" id="myform" name="myform">
    
     <input type="hidden" name="token" id="token" value="<%=token%>"/> 
   <%if(user1.getAgentTag()-4==0) {%>)
    <div class="line">您提交的代理申请正在审批中……</div>
      <div class="line">请确认你的订单已支付，以便公司审核。</div>
      <%}else if(user1.getAgentTag()-2==0){ %>
       <div class="line">您已经是联创合伙人，管辖区域为：</div>
      <div class="line"><i><%=user1.getProvince() %></i></div>
       <%}else if(user1.getAgentTag()-1==0){ %>
         <div class="line">您已经是城市合伙人，管辖区域为：</div>
      <div class="line">
      <i><%=user1.getProvince() %><%=user1.getCity() %><%=user1.getArea() %></i></div>
		 <%}
		if(user1.getAgentTag()-1<=0){ %>
		<%if(user1.getAgentTag()==0){ %>
		 <div class="line">欢迎加入微腾，请填写申请资料</div>
		 <%}else{ %>
		  <div class="line">&nbsp;</div>
		  <%} %>
		 <div class="line"><i>*</i> <label>会员等级：</label><select id="rankJoin" name="rankJoin">
<option value="">--请选择代理类型--</option>
<%if(user1.getAgentTag()==0){ %>
<option value="4">--城市合伙人--</option>
<%} %>
<option value="5">--联创合伙人--</option>
</select>
</div>
 <div class="line p1">
		<!-- sel-provance -->
       <i>*</i> <label>所属省份：</label><select id="s_province" name="province"></select>		
		</div>
         <div class="line p2">
		  <i>*</i>  <label>所&nbsp;属&nbsp;市&nbsp;：</label><select id="s_city" name="city" ></select><!-- sel -->
		 </div>
         <div class="line p3">
		  <i>&nbsp;&nbsp;</i><label>所属区县：</label>
		<select id="s_county" name="area"></select>
		 </div>
            <script type="text/javascript" src="js/area.js"></script>
            <script type="text/javascript">
            _init_area();
           
            </script>
        <div class="line"><i>*</i> <label>真实姓名：</label><input type="text" placeholder="" id="userName" name="userName" value=""></div>
        <div class="line"><i>*</i> <label>联系电话：</label><input type="text" placeholder="" id="mobile" name="mobile" value=""></div>
        <div class="line"><i>&nbsp;</i> <label>微信号码：</label><input type="text" placeholder="" id="weixin" name="weixin" value=""></div>
        

       
    <div class="address_sub1"  onclick="copyText()" >确认</div>
    <%} %>
    <div class="address_sub2"   onclick="javascript:history.back();">返回</div>
    </form>
</div>

</body>

<!-- <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script> -->
<SCRIPT src="js/jquery-1.7.1.min.js" type="text/javascript"></SCRIPT>
<script type="text/javascript" src="js/jquery.gcjs.js"></script>
<script type="text/javascript">
$(".p1").fadeOut(200);
$(".p2").fadeOut(200);
$(".p3").fadeOut(200);

$("input[name='type']").click(function(){
	if($(this).val()=='1'){
		$(".p1").fadeIn(200);
		$(".p2").fadeIn(200);
		$(".p3").fadeIn(200);
	}else if($(this).val()=='2'){
			$(".p1").fadeIn(200);
			$(".p2").fadeOut(200);
			$(".p3").fadeOut(200);
		}
});

$("#rankJoin").change(function(){
	if($(this).val()=='4'){
		$(".p1").fadeIn(200);
		$(".p2").fadeIn(200);
		$(".p3").fadeIn(200);
	}else if($(this).val()=='5'){
			$(".p1").fadeIn(200);
			$(".p2").fadeOut(200);
			$(".p3").fadeOut(200);
		}
	else if($(this).val()==''){
		$(".p1").fadeOut(200);
		$(".p2").fadeOut(200);
		$(".p3").fadeOut(200);
	}
});

   function copyText(){
    if (!confirm("确认提交代理申请？")) {
            return false;
        } else {
            error = "";
            if($("#userName").val()=="") error=error+"真实姓名不能为空！/n";
             if($("#mobile").val()=="") error=error+"手机号码不能为空！/n";
              if(error==""){
             $('#myform').submit();
             return true;
             }else  return false;
        }
   }

</script>
</html>
<%}}%>