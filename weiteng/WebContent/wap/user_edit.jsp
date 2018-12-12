<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.Address"%>
<%@ page import="com.ssm.pojo.User"%>

<%@ page import="java.util.*"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="com.ssm.utils.StringUtil"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
User user1  = (User) request.getAttribute("user1");
if(null==user||user1==null){
	out.println("<script>");
	out.println("window.location.href='login.jsp';");
	out.println("</script>");
}else{
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

          <title>个人信息</title>
       
          </head>
<body>
<header class="header">
	<div class="fix_nav">
		<div class="nav_inner">
			<a class="nav-left back-icon" href="javascript:history.back();"></a>
			<div class="tit">个人信息</div>
			<a class=" nav-right home-icon" href="UserServlet?method=index">首页</a>
		</div>
	</div>
</header>

    <div class="address_main">
    <form action="UserServlet?method=user_update" method="post" id="myform" name="myform">
    
     <input type="hidden" name="token" id="token" value="${sessionScope.token }"/> 
          <input type="hidden" name="id" id="id" value="<%=user1.getId()%>"/> 
     
       <div class="line"><i>*</i> <label>会员编号：</label><%=user1.getUserId()%></div>
       <div class="line"><i>*</i> <label>真实姓名：</label><input type="text" placeholder="" id="userName" name="userName" value="<%=StringUtil.notNull(user1.getUserName())%>"></div>
    <div class="line"><i>*</i> <label>会员昵称：</label><input type="text" placeholder="" id="nickName" name="nickName" value="<%=StringUtil.notNull(user1.getNickName())%>"></div>
     <div class="line"><i>*</i> <label>联系电话：</label><input type="text" placeholder="" id="mobile" name="mobile" value="<%=StringUtil.notNull(user1.getMobile())%>"></div>
 	 <div class="line"><i>&nbsp;</i>  <label>证件号码：</label><input type="text" placeholder="" id="numId" name="numId" value="<%=StringUtil.notNull(user1.getNumId())%>"></div>
     <div class="line"><i>&nbsp;</i> <label>银行账号：</label><input type="text" placeholder="" id="accountId" name="accountId" value="<%=StringUtil.notNull(user1.getAccountId())%>"></div>
	<div class="line"><i>&nbsp;</i> <label>开户银行：</label><select id="bankName" name="bankName">
	<option value="">--请选择开户银行--</option>
	<option value="中国工商银行股份有限公司" <%if(user1.getBankName().equals("中国工商银行股份有限公司")){ %>selected="selected"<%} %>>中国工商银行股份有限公司</option>
	<option value="中国建设银行股份有限公司" <%if(user1.getBankName().equals("中国建设银行股份有限公司")){ %>selected="selected"<%} %>>中国建设银行股份有限公司</option>
	<option value="中国农业银行股份有限公司" <%if(user1.getBankName().equals("中国农业银行股份有限公司")){ %>selected="selected"<%} %>>中国农业银行股份有限公司</option>
	<option value="中国邮政储蓄银行股份有限公司" <%if(user1.getBankName().equals("中国邮政储蓄银行股份有限公司")){ %>selected="selected"<%} %>>中国邮政储蓄银行股份有限公司</option>
	<option value="交通银行股份有限公司" <%if(user1.getBankName().equals("交通银行股份有限公司")){ %>selected="selected"<%} %>>交通银行股份有限公司</option>
	<option value="招商银行股份有限公司" <%if(user1.getBankName().equals("招商银行股份有限公司")){ %>selected="selected"<%} %>>招商银行股份有限公司</option>
	<option value="广州银行股份有限公司" <%if(user1.getBankName().equals("广州银行股份有限公司")){ %>selected="selected"<%} %>>广州银行股份有限公司</option>
</select>
</div>
<div class="line"><i>&nbsp;</i> <label>开户支行：</label><input type="text" placeholder="" id="bankAdr" name="bankAdr" value="<%=StringUtil.notNull(user1.getBankAdr())%>"></div>
 <div class="line"><i>&nbsp;</i> <label>备注说明：</label><i>银行账号用户名与真实姓名必须一致。</i></div>
 
       
    <div class="address_sub1"  onclick="copyText()" >提交修改</div>
    <div class="address_sub2"   onclick="javascript:history.back();">返回</div>
    </form>
</div>

</body>

<!-- <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script> -->
<SCRIPT src="js/jquery-1.7.1.min.js" type="text/javascript"></SCRIPT>
<script type="text/javascript" src="js/jquery.gcjs.js"></script>
<script type="text/javascript">

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
    if (!confirm("确认提交个人信息修改？")) {
            return false;
        } else {
            error = "";
            if($("#nickName").val()=="") error=error+"用户昵称不能为空！/n";
            if($("#userName").val()=="") error=error+"真实姓名不能为空！/n";
             if($("#mobile").val()=="") error=error+"手机号码不能为空！/n";
              if(error==""){
             $('#myform').submit();
             return true;
             }else{
            	 alert(error);
             return false;
             }
        }
   }

</script>
</html>
<%}%>