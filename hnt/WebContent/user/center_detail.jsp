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
	String auth1 = StringUtil.notNull(user.getAuthority());
	
	 if(auth1.length()<30){
		out.println("<script>");
		out.println("alert('会员权限信息获取失败，请重新登陆');parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>服务中心</title>
    
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
          		  <li><a href="user_add_protocol.jsp">会员注册</a></li>
                    <li ><a href="rankJoin_up_all.jsp">会员升级</a></li>
                    <%if(auth1.substring(10, 11).equals("1")){ %>
                    
                    <li><a href="UserServlet?method=getBelong">销售关系</a></li>
                    <%}if(auth1.substring(11, 12).equals("1")){ %>
                    
                    <li><a href="UserServlet?method=getReferee">服务关系</a></li>
                    <%} %>
                    <li ><a href="AddressServlet?method=list">地址管理</a></li>
                    <%if(user.getCenterId()==2){ %>
                     <li class="now"><a href="CenterServlet?method=detail">服务中心</a></li>
                     <%} %>
                </ul>
                 <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：业务管理-&gt;<a>服务中心</a></h2>
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
		            	         <form  id="myform" name="myform" method="post">
		
		  <input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
            		
            	 
                	<div class="notice">
         <h2>服务中心</h2><span class="required_notification">* 表示必填项</span>
   </div>
  
  			 <table class ="form-table">
  			 <tr>
  			 <td width="12%"><label>暂无信息</label></td>
  			 </tr>
  			 
  			 </table>
  			 
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
 <script type="text/javascript">
	$(function() {
   
   });
   
   </script>
</html>
<%
}}
%>