<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.StringUtil"%>

<%
User user = (User)request.getSession().getAttribute("sys_user");
String token = (String)request.getSession().getAttribute("token");
String tag = (String)request.getAttribute("tag");
String userId = (String)request.getAttribute("userId");
String[] mid = (String[])request.getAttribute("mid");
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
String message = (String) request.getAttribute("message");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>会员升级</title>
    
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
          		  <li><a href="user_add_protocol.jsp">会员注册</a></li>
                    <li   class="now"><a href="rankJoin_up_all.jsp">会员升级</a></li>
                    <%if(auth1.substring(10, 11).equals("1")){ %>
                    <li><a href="UserServlet?method=getBelong">销售关系</a></li>
                    <%}if(auth1.substring(11, 12).equals("1")){ %>
                    <li><a href="UserServlet?method=getReferee">服务关系</a></li>
                    <%} %>
                    <li><a href="AddressServlet?method=list">地址管理</a></li>
                    <%if(user.getCenterId()==2){ %>
                     <li><a href="CenterServlet?method=detail">服务中心</a></li>
                     <%} %>
                </ul>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：业务管理-&gt;会员升级-&gt;<a>信息提示</a></h2>
            	</div>
            		<div class="content content-full-width">
                	<div class="notice">
         <h2>信息提示</h2><span class="required_notification"></span>
   </div>
    <%if(!StringUtil.notNull(tag).equals("1")){ %>
							<div class="p-li">
								 <%=StringUtil.notNull(message)%>
							</div>
							<div class="p-li">
							<span class="left"><a href="rankJoin_up_all.jsp">会员升级 </a> </span>
							</div>
							<%}else{ %>
							<div class="p-li">
								 <%=StringUtil.notNull(message)%>
							</div>
							<form id="myform" method="post">
							<input type="hidden" name="token" id="token" value="<%=StringUtil.notNull(token)%>"/> 
							<input type="hidden" name="id" id="id" value="<%=StringUtil.notNull(userId)%>"/> 
							<div class="p-li">
							<span class="left">备选会员编号：</span>
								<span
								class="right"><input type="radio" name="userId" value="<%=mid[1] %>" checked><%=mid[1] %> &nbsp;
								<input type="radio" name="userId" value="<%=mid[2] %>"><%=mid[2] %> &nbsp;
								</span>
							</div>
							<div class="p-li">
							<input type="submit" id="btn1" class="button orange" value="确认更改" /> &nbsp;<input type="button" class="button blue-sky"  id="btn2" value="注册会员" /> 
								
							</div>
							<div class="p-li">
							<span class="left">备注说明：</span>
								<span
								class="right">如果无需修改默认会员编号，请直接退出该页面，如果需要修改，请选中心仪的编号，一旦修改将不能再修改！ &nbsp;
								</span>	
							</div>
							</form>
							<%} %>
  </div>
					</div>
         </div> 
               </div>
            </div>
</div>
<jsp:include page="footer.jsp" />
</body>
<script type="text/javascript">
	$(function() {
  	  $("#btn1").click(function(){
  	  if (!confirm("确认提交修改会员编号，修改后不能再进行还原！")) {
				return false;
			}else{
			$('#myform').attr("action","UserServlet?method=user_update_id");
		       		 $('#myform').submit();
		       		 return true;
		 }   
	   });
	   
	   $("#btn2").click(function(){
  	  if (!confirm("确认退出修改会员编号，继续进行注册会员！")) {
				return false;
			}else{
			$('#myform').attr("action","user_add_protocol.jsp");
		       		 $('#myform').submit();
		       		 return true;
		 }   
	   });
   
   
        });
    
        
   </script>
</html>
<%}}%>