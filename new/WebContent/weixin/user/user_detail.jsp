<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");
String token = (String)request.getSession().getAttribute("token");
User new_user = (User)request.getAttribute("users");
String message = (String)request.getAttribute("message");
if(!StringUtil.notNull(message).equals("")){
out.println("<script>");
		out.println("alert('"+message+"')");
		out.println("</script>");
}		
String psw2 = (String)request.getSession().getAttribute("psw2");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{

%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../images/splash/splash-icon.png">
<link rel="apple-touch-startup-image" href="../images/splash/splash-screen.png" 			media="screen and (max-device-width: 320px)" />  
<link rel="apple-touch-startup-image" href="../images/splash/splash-screen_402x.png" 		media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" /> 
<link rel="apple-touch-startup-image" sizes="640x1096" href="../images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="../images/splash/splash-screen-ipad-landscape" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004" href="../images/splash/splash-screen-ipad-portrait.png" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="../images/splash/splash-screen-ipad-portrait-retina.png"   media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
<link rel="apple-touch-startup-image" sizes="1496x2048" href="../images/splash/splash-screen-ipad-landscape-retina.png"   media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>

<title>个人资料</title>

<link href="../styles/style.css"     		rel="stylesheet" type="text/css">
<link href="../styles/framework.css" 		rel="stylesheet" type="text/css">
<link href="../styles/owl.carousel.css" 	 rel="stylesheet" type="text/css">
<link href="../styles/owl.theme.css" 		rel="stylesheet" type="text/css">
<link href="../styles/swipebox.css"		 rel="stylesheet" type="text/css">
<link href="../styles/colorbox.css"		 rel="stylesheet" type="text/css">


<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jqueryui.js"></script>
<script type="text/javascript" src="../scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="../scripts/colorbox.js"></script>
<script type="text/javascript" src="../scripts/snap.js"></script>
<script type="text/javascript" src="../scripts/contact.js"></script>
<script type="text/javascript" src="../scripts/custom.js"></script>
<script type="text/javascript" src="../scripts/framework.js"></script> 
<script type="text/javascript" src="../scripts/jquery.cityselect.js"></script>
</head>
<body>

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	个人资料</p><p class="go-title-en">INF.
        </p>
         <a href="javascript:history.go(-1);" class="go-logo">返回</a>
        <a href="main.jsp" class="go-home">首页</a>
        <a href="#" class="go-menu">菜单</a>
        <a href="#" class="go-back">关闭</a>
    </div>
    <div class="decoration"></div>
    <div class="navigation">
    	<div class="corner-deco"></div>
    	<div class="navigation-wrapper">
          <%@ include file="menu.jsp" %>  
        </div>
    </div>
</div>
<div class="content">
    <div class="container">
    <div class="one-half-responsive last-column">
        <div class="container no-bottom">
         <form  action="UserServlet.do?method=user_update"  id="myform" name="myform" method="post">
         <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
          <table  class="table-detail">
           <tr>
                <td colspan="2"><h4>会员关系</h4></td>
            </tr>
            <tr>
                <td style="width:30%">会员等级:</td>
                <td><%if(new_user.getRankJoin()==1){ %>银级代理商<%}else if(new_user.getRankJoin()==2){ %>
			金级代理商<%}else if(new_user.getRankJoin()==2){ %>钻级代理商<%}else{ %>-<%} %></td>
            </tr>
             
              <tr>
                <td>客服编号 :</td>
                <td><%=StringUtil.notNull(new_user.getRefereeUserId())%> </td>
            </tr>
          
              <tr>
                <td colspan="2"><h4>基本资料</h4></td>
            </tr>
            <tr>
                <td>会员编号：
                <td><%=StringUtil.notNull(new_user.getUserId())%></td></tr>
              <tr>
                <td>真实姓名：
                <td><%=StringUtil.notNull(new_user.getUserName())%></tr>
             
              <tr>
                <td>性别：</td>
                <td><select name="sex" id="sex" class="contactField">
                            <option value="男" <%if(StringUtil.notNull(new_user.getSex()).equals("男")){%>selected<%} %>>男</option>
                            <option value="女" <%if(StringUtil.notNull(new_user.getSex()).equals("女")){%>selected<%} %>>女</option></select></td>
            </tr>
            <tr>
                <td>证件类型：</td>
                <td><%=StringUtil.notNull(new_user.getDocumentType())%></td>
            </tr>
            <tr>
                <td>证件号码：</td>
                <td><%=StringUtil.notNull(new_user.getNumId())%></td>
            </tr>
           
            <tr>
                <td>手机号码：</td>
                <td><input
								id="phone" name="phone" 
								value='<%=StringUtil.notNull(new_user.getTel())%>' type="text"  class="contactField"></td>
            </tr>
            <tr>
                <td>电子邮箱：</td>
                <td><input
								id="email" name="email" 
								value='<%=StringUtil.notNull(new_user.getEmail()) %>' type="text" class="contactField"></td>
            </tr>
            <tr>
                <td>QQ号码：</td>
                <td><input id="qq" name="qq"
								value='<%=StringUtil.notNull(new_user.getQq()) %>' type="text" class="contactField"></td>
            </tr>
            <tr>
                <td>微信：</td>
                <td><input id="weixin" name="weixin"
								value='<%=StringUtil.notNull(new_user.getWeixin()) %>' type="text" class="contactField"></td>
            </tr>
             <tr>
                <td>经营地区：</td>
                <td>	<span id="city_2"><select class="prov contactField" id="province" name="province">
  		</select> 
    	<select class="city contactField" id="city" name="city" disabled="disabled">
		</select>
		 <select class="dist contactField" id="area" name="area" disabled="disabled">
		</select> </span></td>
            </tr>
             <tr>
                <td>联系地址：</td>
                <td> <input id="address" name="address" 
								value='<%=StringUtil.notNull(new_user.getAddress())%>' type="text" class="contactField"></td>
            </tr>
             <tr>
                <td colspan="2"><h4>银行账号</h4></td>
            </tr>
            <tr>
                <td style="width:30%">账户姓名：</td>
                <td><input id="accountName" name="accountName"  class="contactField"
									value='<%=StringUtil.notNull(new_user.getAccountName())%>'
									type="text" readonly></td>
            </tr>
              <tr>
                <td style="width:30%">银行卡号：</td>
                <td><input
									id="accountId" name="accountId" class="contactField"
									value='<%=StringUtil.notNull(new_user.getAccountId())%>'
									type="text"></td>
            </tr>
              <tr>
                <td>开户银行：</td>
                <td><select name="bankName" id="bankName" class="contactField">
<option value="中国工商银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国工商银行股份有限公司")) {%>
									selected <%}%>>中国工商银行股份有限公司</option>
									<option value="中国建设银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国建设银行股份有限公司")) {%>
									selected <%}%>>中国建设银行股份有限公司</option>
									<option value="中国农业银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国农业银行股份有限公司")) {%>
									selected <%}%>>中国农业银行股份有限公司</option>
									<option value="中国银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国银行股份有限公司")) {%>
									selected <%}%>>中国银行股份有限公司</option>
									<option value="交通银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"交通银行股份有限公司")) {%>
									selected <%}%>>交通银行股份有限公司</option>
									<option value="中国邮政储蓄银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国邮政储蓄银行股份有限公司")) {%>
									selected <%}%>>中国邮政储蓄银行股份有限公司</option>
									<option value="中信银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中信银行股份有限公司")) {%>
									selected <%}%>>中信银行股份有限公司</option>
									<option value="中国光大银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国光大银行股份有限公司")) {%>
									selected <%}%>>中国光大银行股份有限公司</option>
									<option value="华夏银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"华夏银行股份有限公司")) {%>
									selected <%}%>>华夏银行股份有限公司</option>
									<option value="中国民生银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"中国民生银行股份有限公司")) {%>
									selected <%}%>>中国民生银行股份有限公司</option>
									<option value="招商银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"招商银行股份有限公司")) {%>
									selected <%}%>>招商银行股份有限公司</option>
									<option value="广发银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"广发银行股份有限公司")) {%>
									selected <%}%>>广发银行股份有限公司</option>
									<option value="兴业银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"兴业银行股份有限公司")) {%>
									selected <%}%>>兴业银行股份有限公司</option>
									<option value="广州商业银行股份有限公司"
									<%if (StringUtil.notNull(user.getBankName()).equals(
						"广州商业银行股份有限公司")) {%>
									selected <%}%>>广州商业银行股份有限公司</option>								</select></td>
            </tr>
            
             <tr>
                <td>开户网点：</td>
                <td><input
									id="bankAdr" name="bankAdr" class="contactField"
									value='<%=StringUtil.notNull(new_user.getBankAdr()) %>'
									type="text"></td>
            </tr>
		</table>
		</form>
		<div><span id="ageTag"></span></div>
		<div><span id="sexTag"></span></div>
		<div><span id="phoneTag"></span></div>
		<div><span id="emailTag"></span></div>
		<div><span id="qqTag"></span></div>
		<div><span id="weixinTag"></span></div>
		<div><span id="provinceTag"></span></div>
		<div><span id="addressTag"></span>
				</div>
		  <div>
                            <input type="button" class="buttonWrap button button-green contactSubmitButton" id="btn1" value="保存"/>
                        </div>
        </div>
   </div>   
</div>  
</div>
 <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  
<div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>
 <script type="text/javascript" src="../../js/custom.js"></script>
 <script type="text/javascript">
	$(function() {
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  //验证身份证号码
   var reg1 = /^[1-9]+[0-9]*]*$/;  //验证整数
 var reg2 = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;//电话号码与手机号码同时验证
    var reg3 = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; //验证电子邮箱
   // var reg4 =  /^(?!(\d)\1{1,}$)[1-9]\d{4,}$/;//验证QQ号码
    $("#city_2").citySelect({
		prov:"<%=StringUtil.notNull(new_user.getProvince())%>", 
    	city:"<%=StringUtil.notNull(new_user.getCity())%>",
		dist:"<%=StringUtil.notNull(new_user.getArea())%>",
		nodata:"none"
	}); 
   /*

    $("#age").blur(function(){
   	 if($("#age").val()=="")  $("#ageTag").text("年龄不能为空！").css({"color":"red","font-size":"9px"}); 
	else if(!reg1.test($("#age").val())) $("#ageTag").text("年龄格式有误！").css({"color":"red","font-size":"9px"}); 
	else if($("#age").val()>200) $("#ageTag").text("年龄超出了正常范围,请核对！").css({"color":"red","font-size":"12px"}); 
		else if($("#age").val()<18) $("#ageTag").text("年龄未满18岁，未满足加盟资格！").css({"color":"red","font-size":"12px"}); 
	else $("#ageTag").text("");
		
   });
   */
   $("#email").blur(function(){
   	 if($("#email").val()!=""){
				if(!reg3.test($("#email").val()))
					$("#emailTag").text("电子邮箱格式有误！").css({"color":"red","font-size":"9px"}); 
					else $("#emailTag").text("");
	}else $("#emailTag").text("");
   });
   
   $("#qq").blur(function(){
   	 if($("#qq").val()!=""){
				if(!reg1.test($("#qq").val()))
					$("#qqTag").text("QQ号码格式有误！").css({"color":"red","font-size":"9px"}); 
					else $("#qqTag").text("");
					
	}else $("#qqTag").text("");
   });
   
		  	$("#address").blur(function(){
   		 if($("#address").val()!=""){
   		  if(getByteLen($("#address").val())>100) $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"9px"});
   		else $("#addressTag").text("");
   		}else $("#addressTag").text("");
   			});
  	 
  	  $("#btn1").click(function(){
  	   if (!confirm("确认保存修改！")) {
				return false;
			}
			else{
	  	 var errorstr = "";
			
			if($("#email").val()!=""){
				if(!reg3.test($("#email").val())){
					$("#emailTag").text("电子邮箱格式有误！").css({"color":"red","font-size":"9px"}); 
					errorstr =errorstr+"1";
				}
			}else if($("#emailTag").val()!=""){
				errorstr =errorstr+"1";
			}
			if($("#qq").val()!=""){
				if(!reg1.test($("#qq").val())){
					$("#qqTag").text("QQ号码格式有误！").css({"color":"red","font-size":"9px"}); 
					errorstr =errorstr+"1";
				}
			}else if($("#qqTag").val()!=""){
				errorstr =errorstr+"1";
			}
				if($("#phone").val()!=""){
					if(getByteLen($("#phone").val()) >25){
						$("#phoneTag").text("固定电话格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr++;
					}
				}else if($("#phoneTag").val()!=""){
					errorstr++;
				}
				if($("#weixin").val()!=""){
					if(getByteLen($("#weixin").val())>50){
						$("#weixinTag").text("微信格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr++;
					}
				}else if($("#weixinTag").val()!=""){
					errorstr++;
				}		
			
			if($("#age").val()==""){
			 $("#ageTag").text("年龄不能为空！").css({"color":"red","font-size":"9px"}); 
			 errorstr =errorstr+"1";
			}else if($("#ageTag").text()!="") errorstr ="1";
			
   			if($("#address").val()!="") {
   		  if(getByteLen($("#address").val())>100){
   		   $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"9px"});
   			errorstr ="1";
   			$("#address").focus();
   				}
   		 }else if($("#addressTag").val()!=""){
   		 errorstr ="1";
   		 $("#address").focus();
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
<%}%>