<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.StringUtil"%>
<%@ page import="com.ssm.utils.DateUtil"%>
<%@page import="com.ssm.pojo.Product"%>
<%@page import="com.ssm.pojo.News"%>
<%@page import="com.utils.MD5"%>
<%
User user = (User)request.getSession().getAttribute("sys_user");
String psw2 = (String)request.getSession().getAttribute("psw2");
User user1 = (User)request.getAttribute("user1");

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
                <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：业务管理-&gt;会员升级-&gt;<a>选择等级</a></h2>
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
         <form  action="UserServlet?method=rankJoin_up_a"  id="myform" name="myform" method="post">
        <input id="userId" name="userId"  type="hidden" value="<%=user1.getUserId()%>"/>
      
                	<div class="notice">
         <h2>会员升级</h2><span class="required_notification">* 表示必填项</span>
   </div>
  
  			 <table class ="form-table">
  			 <tr>
  			 <td width="12%"><label>用户ID：</label></td>
  			 <td><%=StringUtil.notNull(user1.getUserId()) %></td>
  			 </tr>
  			 
  			  <tr>
  			 <td><label>用户名：</label></td>
  			 <td>	 <%=StringUtil.notNull(user1.getUserName()) %></td>
  			 </tr>
  			  <tr>
  			 <td><label>当前等级：</label></td>
  			 <td>	 <%if(user1.getRankJoin()==1){ %>银卡会员 
                                  <%}else if(user1.getRankJoin()==2){ %>金卡会员
                                  <%}else if(user1.getRankJoin()==3){ %>白金卡会员
								   <%}else if(user1.getRankJoin()==4){ %>VIP会员
								      <%}else if(user1.getRankJoin()==5){ %>至尊会员<%} %>
								      <input type="hidden" name="rankJoinOld" value="<%=user1.getRankJoin() %>"/>
                  </td>
  			 </tr> <tr>
  			 <td><label>升级等级：</label></td>
  			 <td>	 <select id="rankJoin" name="rankJoin">
										 <%if(user1.getRankJoin()==0){ %>
										  <option value="1">银卡会员</option>
										    <option value="2">金卡会员</option>
										  <option value="3">白金卡会员</option>
										    <%}else if(user1.getRankJoin()==1){ %>
										     <option value="2">金卡会员</option>
										  <option value="3">白金卡会员</option>
										  <%}else if(user1.getRankJoin()==2){ %>
										   <option value="3">白金卡会员</option>
										  <%}else if(user1.getRankJoin()>2){ %>
										  <option value="">-你当前为最高等级-</option>
										  <%} %>
										  </select>&nbsp;<span id="rankJoinemsg"></span><input type="hidden" name="rankJoinTag" id="rankJoinTag" value="<%=user1.getRankJoin()%>"></td>
  			 </tr>
  			 
  			 <tr><td colspan="2"><input id="sbtn"  value="提交" type="button"  class="button blue-sky"></td></tr>
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
  <script  type="text/javascript">
$(function() {
	$("#sbtn").click(function(){
	 if (!confirm("确认提交升级加盟级别？")) {
				return false;
			}else{
		var errorstr = "";
		if($("#rankJoin").val()==""){
			$("#rankJoinmsg").text("未选择需要加盟的等级！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
		}else if($("#rankJoin").val()==$("#rankJoinTag").val()){
			$("#rankJoinmsg").text("最新等级与原等级相同，请核对后再试！").css({"color":"red","font-size":"12px"}); 
			errorstr ="1";
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
<%
}}
%>