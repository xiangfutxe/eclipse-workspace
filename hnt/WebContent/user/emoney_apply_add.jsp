<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.pojo.BankAccount" %>

<%@ include file="check.jsp" %>  

<%
User user = (User)request.getSession().getAttribute("sys_user");
String token = (String)request.getSession().getAttribute("token");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新增充值</title>
    
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
					<li><A href="money_detail.jsp">账户明细</a></li> 
					<li><a   href="WithDrewServlet?method=apply_list">奖金提现</a></li>
					<li class="now"><a href="ChargeApplyServlet?method=apply_list">账户充值</a></li>
					<li><a  href="AccountServlet?method=money_transfer">转账交易</a></li>
					<li><a href="AccountServlet?method=to_emoney">转账报单券</a></li>
                </ul>
                 <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：财务管理-&gt;账户充值-&gt;<a>充值记录</a></h2>
            	</div>
            		<div class="content content-full-width">
    <form  action="ChargeApplyServlet?method=apply_save"  id="myform" name="myform" method="post">
		   <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
                	<div class="notice">
         <h2>新增充值</h2><span class="required_notification">* 表示必填项</span>
   </div>
  
  			 <table class ="form-table">
  			 <tr>
  			 <td width="12%"><label><span class="required">*</span>充值金额：</label></td>
  			 <td ><input id="amount" name="amount" type="text">&nbsp;<span id="amountTag"></span></td>
  			 </tr>
  			  <tr>
  			 <td><label><span class="required">*</span>转入账号：</label></td>
  			 <td ><select name="accountId" id="accountId">
                                <option value="">--请选择转入账号--</option>
                                  <%
								 Collection coll = (Collection)request.getAttribute("coll");
						
							if(coll!=null){
									Iterator it = coll.iterator();
									int t =0;
									while(it.hasNext()){
									BankAccount ba = (BankAccount)it.next();
							%>
									   <option value="<%=ba.getId()%>"><%=StringUtil.notNull(ba.getAccountId()) %>&nbsp;|&nbsp;<%=StringUtil.notNull(ba.getAccountName()) %>&nbsp; |&nbsp;<%=StringUtil.notNull(ba.getBankName()) %>
									  </option>
									 <%}} %>
                                </select>&nbsp;<span id="accountTag"></span></td>
  			 </tr>
  			  <tr>
  			 <td><label><span class="required">*</span>支付密码：</label></td>
  			 <td ><input type="password" name="password2" id="password2">&nbsp;<span id="passwordTag"></span></td>
  			 </tr>
  			 <tr>
  			 <td><label><span class="required">*</span>备注说明：</label></td>
  			 <td ><input type="text" name="remark" id="remark"> &nbsp;<span id="remarkTag"></span>
  			 <br>*请注明汇款账户、汇款姓名、汇款时间以便财务审核通过！
  			 </td>
  			 </tr>
  			 
  		</table>
              <div class="p-li" >
              <input name="button" id="sbtn" type="button" class="button blue-royal" value="提交保存" />
                	<input type="button"  id="reback"  class="button blue-sky"  value="充值列表">
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
 <script type="text/javascript" src="../js/custom.js"></script>
 <script language="JavaScript">
$(function() {
	var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
	$("#amount").blur(function() {
		if($(this).val()==""){
			$("#amountTag").text("充值金额不能为空！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if(!reg.test($(this).val())) $("#amountTag").text("数值格式有误，金额应为实数！").css({"color":"red","font-size":"12px"});
		else $("#amountTag").text("");
	});	
	
	$("#remark").blur(function() {
	if(getByteLen($(this).val())>200) $("#remarkTag").text("备注内容字数不能大于100！").css({"color":"red","font-size":"12px"});
	else $("#remarkTag").text("");
	});	
	
	$("#accountId").blur(function() {
		if($(this).val()==""){
			$("#accountTag").text("未选择转入账号！").css({"color":"red","font-size":"12px"}); 
		}else $("#accountTag").text("");
	});	
	
	$("#password2").blur(function() {
		if($(this).val()==""){
			$("#passwordTag").text("支付密码不能为空！").css({"color":"red","font-size":"12px"}); 
		}else $("#passwordTag").text("");
	});	
	
	$("#sbtn").click(function() {
	 if (!confirm("确认提交申请？")) {
				return false;
			} else {
			var errorstr ="";
			if($("#amount").val()==""){
				$("#amountTag").text("充值金额不能为空！").css({"color":"red","font-size":"12px"}); 
				errorstr = "1";
			}else if($("#amountTag").text()!="") errorstr = errorstr + "1";
			
			if($("#password2").val()==""){
				$("#passwordTag").text("支付密码不能为空！").css({"color":"red","font-size":"12px"}); 
				errorstr = "2";
			}else if($("#passwordTag").text()!="") errorstr = errorstr + "2";
			
			if($("#accountId").val()==""){
				$("#accountTag").text("未选择转入账号！").css({"color":"red","font-size":"12px"}); 
				errorstr = "3";
			}else if($("#accountTag").val()!="") errorstr="3";
			
			if(getByteLen($("#remark").val())>200){
			 $("#remarkTag").text("备注内容字数不能大于100！").css({"color":"red","font-size":"12px"});
			 errorstr = errorstr + "4";
			 }else if($("#remarkTag").text()!="")  
			 errorstr = errorstr + "4";
		
			if(errorstr!=""){
				return false;
			}else{
				
	       		 $("#myform").submit();
	       		return true;
			
			}
		}

	});	
	$("#reback").click(function(){
     	window.location.href="ChargeApplyServlet?method=apply_list"; 
        });
			
    });
   
  </script>      
</html>