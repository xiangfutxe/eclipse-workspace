<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@ include file="check.jsp" %>  

<%
User user = (User)request.getSession().getAttribute("sys_user");
User user1 = (User)request.getAttribute("user1");

String token = (String)request.getSession().getAttribute("token");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>提现申请</title>
    
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
<script type="text/javascript" src="js/other.js"></script>
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
				<li><a href="money_detail.jsp">账户明细</a></li> 
				<li  class="now"><a   href="WithDrewServlet?method=apply_list">奖金提现</a></li>
				<li><a href="ChargeApplyServlet?method=apply_list">账户充值</a></li>
				<li ><a  href="AccountServlet?method=money_transfer">转账交易</a></li>
				<li><a href="AccountServlet?method=to_emoney">转账报单券</a></li>
                </ul>
                 <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：财务管理-&gt;奖金提现-&gt;<a>新增申请</a></h2>
            	</div>
            		<div class="content content-full-width">
    <form  action="WithDrewServlet?method=apply_save"  id="myform" name="myform" method="post">
		   <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
		 		   <input type="hidden" name="userId" id="userId" value="<%=StringUtil.notNull(user.getUserId())%>"/> 
		 
                	<div class="notice">
         <h2>新增提现申请</h2><span class="required_notification">* 表示必填项</span>
   </div>
  
  			 <table class ="form-table">
  			 <tr>
  			 <td width="15%"><label><span class="required">*</span>可提现金额：：</label></td>
  			 <td ><%=StringUtil.decimalFormat(user1.getRmoney())%>元</td>
  			 </tr>
  			  <tr>
  			 <td><label><span class="required">*</span>提现金额：</label></td>
  			 <td ><input id="amount" name="amount" type="text">&nbsp;<span id="amountTag"></span></td>
  			 </tr>
  			 <tr>
  			 <td><label><span class="required">*</span>收款账户名：</label></td>
  			 <td ><input  type="text" name="accountName" id="accountName" value="<%=user1.getAccountName() %>"  readonly>&nbsp;<span id="accountNameTag"></span></td>
  			 </tr>
  			  <tr>
  			 <td><label><span class="required">*</span>收款账号：</label></td>
  			 <td ><input  type="text" name="accountId" id="accountId" value="<%=user1.getAccountId() %>"  readonly>&nbsp;<span id="accountIdTag"></span></td>
  			 </tr>
  			  <tr>
  			 <td><label><span class="required">*</span>开户银行：</label></td>
  			 <td ><input  type="text" name="bankName" id="bankName" value="<%=user1.getBankName() %>"  readonly>&nbsp;<span id="bankNameTag"></span></td>
  			 </tr>
  			  <tr>
  			 <td><label><span class="required">*</span>开户网点：</label></td>
  			 <td ><input  type="text" name="bankAdr" id="bankAdr" value="<%=user1.getBankAdr() %>" readonly>&nbsp;<span id="bankAdrTag"></span></td>
  			 </tr>
  			  <tr>
  			 <td><label><span class="required">*</span>支付密码：</label></td>
  			 <td > <input type="password" name="password2" id="password2">&nbsp;<span id="passwordTag"></span></td>
  			 </tr>
  			  <tr>
  			 <td><label><span class="required">*</span>备注说明：</label></td>
  			 <td >请在基本信息内设置好银行账户，否则会造成提现失败;
  			 <br>如果因开户行信息造成转账的手续费由客户自行承担！</td>
  			 </tr>
  			 
  			 
  		</table>
              <div class="p-li" >
              <input name="button" id="sbtn" type="button" class="button blue-royal" value="提交保存" />
                	<input type="button"  id="reback"  class="button blue-sky"  value="申请列表" 
                	onclick="javascript:location.href='WithDrewServlet?method=apply_list'"/>
                	</div>
					</form>
					</div>
         </div> 
               </div>
               </div>
            </div>
</div>
<jsp:include page="footer.jsp" />
</body>
 <script language="JavaScript">
 $(function() {
	//var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;验证浮点数
	$("#amount").blur(function() {
		$.ajax({
					type : "post",
					url : "WithDrewServlet?method=amountAjax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
					data : {//设置数据源
						amount : $("input[name=amount]").val(),
						userId : $("input[name=userId]").val()
					//这里不要加","  不然会报错，而且根本不会提示错误地方
	
					},
	
					dataType : "json",//设置需要返回的数据类型
					success : function(data) {
						var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
						//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
						
						if(d.tag==1) $("#amountTag").text("当前账户余额不足，请重新确认金额！").css({"color":"red","font-size":"12px"});
						else if(d.tag==2) $("#amountTag").text("数值格式有误，金额应为实数！").css({"color":"red","font-size":"12px"});
						else if($("#amount").val()<50) $("#amountTag").text("提现金额不能小于50元！").css({"color":"red","font-size":"12px"});
						else $("#amountTag").text("");
					},
					error : function() {
						alert("系统异常，请稍后重试！");
						$("#amountTag").text("系统异常，请稍后重试！").css({"color":"red","font-size":"12px"}); 
					}//这里不要加","
					
				});
		
	});	
	
	$("#password2").blur(function() {
		if($(this).val()==""){
			$("#passwordTag").text("支付密码不能为空！").css({"color":"red","font-size":"12px"}); 
		}else $("#passwordTag").text("");
	});	
	
	$("#sbtn").click(function() {
		 if (!confirm("确认要提交申请？")) {
	            window.event.returnValue = false;
	        }else{
							var errorstr ="";
							if($("#amount").val()==""){
								$("#amountTag").text("充值金额不能为空！").css({"color":"red","font-size":"12px"}); 
								errorstr = errorstr +"1";
							}else if($("#amount").val()<50){
							$("#amountTag").text("提现金额不能小于50元！").css({"color":"red","font-size":"12px"});
							errorstr = errorstr + "1";
}
							else if($("#amountTag").text()!="") errorstr = errorstr + "1";
							
							if($("#password2").val()==""){
								$("#passwordTag").text("支付密码不能为空！").css({"color":"red","font-size":"12px"}); 
								errorstr = errorstr +"1";
							}else if($("#passwordTag").text()!="") errorstr = errorstr + "1";
							
							if($("#accountName").val()==""){
								$("#accountNameTag").text("收款账户名不能为空。").css({"color":"red","font-size":"12px"}); 
								errorstr = errorstr +"1";
							}else if($("#accountNameTag").text()!=""){
								$("#accountNameTag").text("");
								 errorstr = errorstr + "1";
							 }
							 
							 if($("#accountId").val()==""){
								$("#accountIdTag").text("收款账号不能为空。").css({"color":"red","font-size":"12px"}); 
								errorstr = errorstr +"1";
							}else if($("#accountIdTag").text()!=""){
								$("#accountIdTag").text("");
								 errorstr = errorstr + "1";
							 }
							 
							 if($("#bankName").val()==""){
								$("#bankNameTag").text("开户银行不能为空。").css({"color":"red","font-size":"12px"}); 
								errorstr = errorstr +"1";
							}else if($("#bankNameTag").text()!=""){
								$("#bankNameTag").text("");
								 errorstr = errorstr + "1";
							 }
							 
							  if($("#bankAdr").val()==""){
								$("#bankAdrTag").text("开户网点不能为空。").css({"color":"red","font-size":"12px"}); 
								errorstr = errorstr +"1";
							}else if($("#bankAdrTag").text()!=""){
								$("#bankAdrTag").text("");
								 errorstr = errorstr + "1";
							 }
							
							if($("#remarkTag").text()!="") errorstr = errorstr + "1";
					
							if(errorstr!=""){
								 window.event.returnValue = false;
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
</html>