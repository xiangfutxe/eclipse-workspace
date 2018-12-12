<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.Address"%>
<%@ page import="com.ssm.pojo.User"%>

<%@ page import="java.util.*"%>
<%@ page import="com.ssm.utils.Constants"%><%
WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
String token = (String)request.getSession().getAttribute("token");
User user1  = (User) request.getAttribute("user1");

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
          <title>提现申请</title>
          </head>
<body>


    <!-- 从官方下载的文件放在项目的 jquery-mobile 目录中 -->  

<header class="header header_1">
    <div class="fix_nav">
        <div class="nav_inner">
            <a class="nav-left back-icon" href="javascript:history.back();">返回</a>
            <div class="tit">新增提现</div>
			<a class=" nav-right user-icon" href="UserServlet?method=user_home">个人中心</a>
        </div>
    </div>
</header>
<form action="WithDrewServlet?method=save" method="post" id="myform" name="myform">

<div id="container">
    <div class="address_main">
     <input type="hidden" name="token" id="token" value="<%=token%>"/> 
        <input type="hidden" id="addressid" value="">
		<input type="hidden" id="spuid" value="3074">
        <div class="line"> <label>提现金额：</label><input type="text" placeholder="请输入提现金额(可提现金额为${user1.rmoney })" id="amount" name="amount" value=""></div>
        <div class="line"> <label>支付密码：</label><input type="password" placeholder="请输入支付密码" id="password" name="password" value=""></div>
		 <div class="line"> <label>收款户名：</label><input type="text" placeholder="请输入收款人姓名" id="accountName" name="accountName" value="<%=user1.getAccountName()%>"></div>
       <div class="line"> <label>银行账号：</label><input type="text" placeholder="请输入银行卡账号" id="accountId" name="accountId" value="<%=user1.getAccountId()%>"></div>
		<!-- sel-provance -->
  <div class="line"><label>开户银行：</label><select id="bankName" name="bankName">
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
        <div class="line"><label>所在支行：</label><input type="text" placeholder="请输入开户银行所在支行" id="bankAdr" name="bankAdr" value="<%=user1.getBankAdr()%>"></div>
        <br>		
		</div>
       
        
    <div class="address_sub1"  onclick="copyText()" >确认</div>
    <div class="address_sub2"   onclick="javascript:history.back();">返回</div>
</div>
</div>
</form>

</body>

<!-- <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script> -->
<SCRIPT src="js/jquery-1.7.1.min.js" type="text/javascript"></SCRIPT>
<script type="text/javascript" src="js/jquery.gcjs.js"></script>
<script type="text/javascript">
   function copyText(){
    if (!confirm("确认提交提现申请？")) {
            return false;
        } else {
            error = "";
            if($("#amount").val()=="") error=error+"提现金额不能为空！/n";
             if($("#password").val()=="") error=error+"支付密码不能为空！/n";
              if($("#accountName").val()=="") error=error+"收款人不能为空！/n";
              if($("#accountId").val()=="") error=error+"收款账号不能为空！/n";
              if($("#bankName").val()=="") error=error+"开户银行不能为空！/n";
              if($("#bankAdr").val()=="") error=error+"开户行所在支行不能为空！/n";
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