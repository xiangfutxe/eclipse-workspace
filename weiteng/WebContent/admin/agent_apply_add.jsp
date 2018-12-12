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
if(null!=user){
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
          <title>成为区域代理商</title>
       
          </head>
<body>


<div id="container">
		    <div class="row">
        <div id="slide">
            <div class="hd">
                <ul><li class="on">1</li><li class="on">2</li><li class="on">3</li></ul>
            </div>
            <div class="bd">
                <div class="tempWrap" style="overflow:hidden; position:relative;">
                	<ul style="width:3840px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translateX(-768px);">
	   	                <li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="contact_us.jsp" target="_blank">
	   	                    		<img src="img/banner4.jpg" alt="暂无图片">
	   	                    	</a>
	   	                    </li>
	                	<li style="display: table-cell; vertical-align: top; width: 768px;">
	   	                    	<a href="contact_us.jsp" target="_blank">
	   	                    		<img src="img/banner4.jpg" alt="暂无图片">
	   	                    	</a>
	   	                    </li>
	                	</ul>
                </div>
            </div>
        </div>
    </div>
		</div>
<script charset="utf-8" src="js/TouchSlide.js"></script>

<script type="text/javascript">
	
	TouchSlide({
		slideCell:"#slide",
		titCell:".hd ul", 
		mainCell:".bd ul",
		effect:"left",
		autoPlay:true,
		autoPage:true,
		switchLoad:"_src" 
	});
	</script>
    <div class="address_main pt0">
    <form action="AgentApplyServlet?method=save" method="post" id="myform" name="myform">
    
     <input type="hidden" name="token" id="token" value="<%=token%>"/> 
    <%if(user1.getRankJoin()-5==0&&user1.getAgentTag()-1==0){ %>
     <div class="line">恭喜您，你已经是我们的市级代理商，你所属区域：<%=user1.getProvince() %><%=user1.getCity() %><%=user1.getArea() %></div>
     <%}else if(user1.getRankJoin()-6==0&&user1.getAgentTag()-2==0){ %>
     <div class="line">恭喜您，你已经是我们的省级代理商,<%=user1.getProvince() %></div>
       <%}else if(user1.getRankJoin()-7==0&&user1.getAgentTag()-3==0){ %>
     <div class="line">恭喜您，你已经是我们的全国代理商！</div>
      <%}else if(user1.getAgentTag()-4==0){ %>
     <div class="line">您好，你已提交申请，请耐心等待审核！</div>
     <%}else{ %>
		 <div class="line">欢迎加入微腾，请填写申请资料</div>
        <div class="line"><input type="text" placeholder="请填写真实姓名，用于结算" id="userName" name="userName" value=""></div>
        <div class="line"><input type="text" placeholder="请填写手机号码方便先联系" id="mobile" name="mobile" value=""></div>
        <div class="line"><input type="text" placeholder="请填写微信号" id="weixin" name="weixin" value=""></div>
<div class="line">代理类型：
<c:if test="${user1.rankJoin==6}">
<input type="radio" name="type" value="2" />省级
</c:if>
<c:if test="${user1.rankJoin==5}">
&nbsp;<input type="radio" name="type" value="1">市级
</c:if>
</div>
        <div class="line p1">
		<!-- sel-provance -->
        <select id="s_province" name="province"></select><br>		
		</div>
         <div class="line p2">
		  <select id="s_city" name="city" ></select><br><!-- sel -->
		 </div>
         <div class="line p3">
		 <!-- sel-area -->
		 <select id="s_county" name="area"></select><br>
		 </div>
            <script type="text/javascript" src="js/area.js"></script>
            <script type="text/javascript">
            _init_area();
           
            </script>
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
<%}%>