<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@ include file="check.jsp" %>  

<%
User user = (User)request.getSession().getAttribute("sys_user");
User user1 = (User)request.getAttribute("user1");
	String psw2 = (String)request.getSession().getAttribute("psw2");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>电子钱包</title>
    
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
<script type="text/javascript" src="js/psw2.js"></script>

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
            	<li class="now"><a href="AccountServlet?method=money_balance">电子钱包</a></li> 
				<li><A href="money_detail.jsp">账户明细</a></li> 
				<li><a   href="WithDrewServlet?method=apply_list">奖金提现</a></li>
				<li><a href="ChargeApplyServlet?method=apply_list">账户充值</a></li>
				<li><a  href="AccountServlet?method=money_transfer">转账交易</a></li>
				<li><a href="AccountServlet?method=to_emoney">转账报单券</a></li>
                </ul>
                <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：财务管理-&gt;<a>电子钱包</a></h2>
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
         <h2>钱包余额</h2><span class="required_notification"><a href="money_detail.jsp">账户明细<span> </span></a></span>
   </div>
 
    
			 <div class="p-li">
			<table class="result-tab" width="100%">
						<thead><tr>
							<th>账户名称</th><th>账户余额</th><th>有效期</th><th>操作</th>
						</tr>
						</thead>
						  <tbody>
 <tr>
 <td>报单券账户</td>
 <td><%=StringUtil.decimalFormat(user1.getEmoney()) %></td>
<td>长期</td>
 <td><a href='AccountServlet?method=money_detail&type=1'>交易明细</a></td>
</tr>
 
 <tr>
 <td>复消券账户</td>
 <td><%=StringUtil.decimalFormat(user1.getDmoney()) %></td>
<td>长期</td>
 <td><a href='AccountServlet?method=money_detail&type=3'>交易明细</a></td>
</tr> <tr>
 <td>奖金券账户</td>
 <td><%=StringUtil.decimalFormat(user1.getRmoney()) %></td>
<td>长期</td>
 <td><a href='AccountServlet?method=money_detail&type=4'>交易明细</a></td>
</tr>

</tbody>
					</table>
				  </div>
             </div>
         </div> 
               </div>
               </div>
            </div>
</div>
<jsp:include page="footer.jsp" />
</body>
 
</html>
<%
}
%>