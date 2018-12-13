<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.StringUtil"%>

<%
User user = (User)request.getSession().getAttribute("sys_user");
String token = (String)request.getSession().getAttribute("token");
String psw2 = (String)request.getSession().getAttribute("psw2");
User user1 = (User)request.getAttribute("user1");
String message = (String)request.getAttribute("message");
if(!StringUtil.notNull(message).equals("")){
	out.println("<script>");
		out.println("alert('"+message+"')");
		out.println("</script>");
}		
if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
}else{

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>转为报单券</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="Assets/css/reset.css"/>
<script type="text/javascript" src="Assets/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="Assets/js/js_z.js"></script>
<script type="text/javascript" src="Assets/js/main.js"></script>
<link rel="stylesheet" type="text/css" href="css/thems.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<link rel="stylesheet" type="text/css" href="css/form.css">
<script type="text/javascript" src="js/psw2.js"></script>
<script src="js/jquery.slider.js"></script>
<link rel="stylesheet" href="fonts/fontCss.css">
	<link rel="stylesheet" href="css/jquery.slider.css">
	      <script type="text/javascript" src="js/jquery.cityselect.js"></script>
	<script>
	window.onload = function(){
		$('.testSlider').slider({
			originType:'tuoyuan'
		});
		$('.testSlider1').slider({
			width:300,
			height:300,
			showSlider: false,
			showOrigin: false,
			originPosition:'center'
		});

		$('.testSlider3').slider({
			width:1094,
			height:300,
			originPosition:'center',
			source: [
							{
								src: 'img/slider1.jpg',									// 幻灯片图片地址		
								hasHref: true,												// 是否有链接
								href: '#',			// 链接地址					
								hrefTarget: '_self'											// 链接打开方式  _self, _blank, _top等
 							},
						]
		});
	};
</script>
  </head>
  
 <body>
 <!--头部-->
 <jsp:include page="header.jsp" />
<div class="main_bg">
    <!--幻灯片-->
    <div class="banner1">
            <span class="testSlider3" style="display: inline-block;"></span>
    </div>
  
	<!--幻灯片END-->
	<div class="main_bg">
	<div class="second">   
        <div class="scd_m clearfix">
        	<div class="scd_ml">
            	<ul class="sidenav">
          		<li><a href="AccountServlet?method=money_balance">电子钱包</a></li> 
				<li><A href="money_detail.jsp">账户明细</a></li> 
				<li><a   href="WithDrewServlet?method=apply_list">奖金提现</a></li>
				<li><a href="ChargeApplyServlet?method=apply_list">账户充值</a></li>
				<li><a  href="AccountServlet?method=money_transfer">转账交易</a></li>
				<li  class="now"><a href="AccountServlet?method=to_emoney">转账报单券</a></li>
                </ul>
                 <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：财务管理-&gt;<a>转为报单券</a></h2>
            	</div>
            	<div class="content content-full-width" id="content-psw">
   		<input type="hidden" id="psw1" name="psw1" value="<%=user.getPassword2()%>"/>
		<div class="notice">
         <h2>支付密码</h2><span class="required_notification"></span>
         </div>
         	 <table class ="form-table">
  			 <tr>
  			 <td width="15%"><label><span class="required">*</span>请输入支付密码：</label></td>
  			 <td ><input id="psw2" name="psw2"  type="password" value="<%=psw2%>"/>&nbsp;<span id="psw2Tag"></span></td>
  			 </tr>
  			 </table>
   		
		</div>
		<div class="content content-full-width" id="content-all">
		            	     
            	 
                	<div class="notice">
         <h2>奖金提现</h2><span class="required_notification"></span>
   </div>
  
  			 <div class="p-li">
			 <form  action="AccountServlet?method=to_emoney_save"  id="myform" name="myform" method="post">
		   <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
		   <table  class="form-table">
						<tr><td width="15%">可转账金额：</td><td><span><%=StringUtil.decimalFormat(user.getRmoney())%>元</span>
							</td>
							</tr>
						<tr><td>
							转账金额：</td><td><input id="amount" name="amount"  type="text">&nbsp;<span id="amountTag"></span></td>
						</tr>
	
						<tr><td>支付密码：</td><td> <input type="password" name="password2" id="password2" >&nbsp;<span id="passwordTag"></span>
							</td>
						</tr>
						<tr><td colspan="2">备注说明： 此项为奖金币转为报单币，可转账金额仅供参考，以余额实际为准！
							</td>
						</tr>
						<tr><td colspan="2"><input name="button" class="button blue-sky" id="sbtn"
									type="button" value="提交转账" /> </td>
									</tr>
							</table>
							</form>
				  </div>
			
				  </div>
					</div>
         </div> 
               </div>
               </div>
</div>
<jsp:include page="footer.jsp" />
</body>
 <script type="text/javascript" src="../js/custom.js"></script>
 <script language="JavaScript">
$(function() {
	var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
	$("#amount").blur(function() {
		if($(this).val()==""){
			$("#amountTag").text("转账金额不能为空！").css({"color":"red","font-size":"9px"}); 
			errorstr ="1";
		}else if(!reg.test($(this).val())) $("#amountTag").text("数值格式有误，金额应为实数！").css({"color":"red","font-size":"9px"});
		else $("#amountTag").text("");
	});	
	
	
	$("#password2").blur(function() {
		if($(this).val()==""){
			$("#passwordTag").text("支付密码不能为空！").css({"color":"red","font-size":"9px"}); 
		}else $("#passwordTag").text("");
	});	
	
	$("#sbtn").click(function() {
	 if (!confirm("确认提交转账？")) {
				return false;
			} else {
			var errorstr ="";
			if($("#amount").val()==""){
				$("#amountTag").text("转账金额不能为空！").css({"color":"red","font-size":"9px"}); 
				errorstr = "1";
			}else if($("#amountTag").text()!="") errorstr = errorstr + "1";
			
			if($("#password2").val()==""){
				$("#passwordTag").text("支付密码不能为空！").css({"color":"red","font-size":"9px"}); 
				errorstr = "2";
			}else if($("#passwordTag").text()!="") errorstr = errorstr + "2";
		
			if(errorstr!=""){
				return false;
			}else{
				
	       		 $("#myform").submit();
	       		return true;
			
			}
		}

	});	
});
</script>
</html>
<%
}
%>