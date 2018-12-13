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
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：业务管理-&gt;会员升级-&gt;<a>选择类型</a></h2>
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
         <form  action="UserServlet?method=rankJoin_up"  id="myform" name="myform" method="post">
                	<div class="notice">
         <h2>会员升级</h2><span class="required_notification">* 表示必填项</span>
   </div>
  
  			 <table class ="form-table">
  			 <tr>
  			 <td width="12%"><label>升级类型：</label></td>
  			 <td><input type="radio" name="type" value="1" checked>为自己升级 &nbsp;<input type="radio" name="type" value="2"> 为他人升级<span id="typemsg"></span></td>
  			 </tr>
  			 
  			  <tr>
  			 <td><label>会员编号:</label></td>
  			 <td>	  <input id="userId" name="userId" size="50"  value="" type="text">
                               <span id="usermsg"></span>  <span id="usermsg1"></span></td>
  			 </tr>
  			  <tr>
  			 <td><label>备注说明:</label></td>
  			 <td>	为他人升级时请输入需要升级的会员编号，如果为自己升级，无需输入！</td>
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
  $("#userId").blur(function(){
  	 	$.ajax({
  	 		type:"post",
  	 		url:"UserServlet?method=refereeAjax_user",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
  	 		data:{//设置数据源
						refereeId:$("input[name=userId]").val()//这里不要加","  不然会报错，而且根本不会提示错误地方
					
				},
 
 			dataType:"json",//设置需要返回的数据类型

			success:function(data){
						var type =$("input:radio[name='type']:checked").val();
						var d = data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
						if(type=="2"){				//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
							if(d.userName==""){
							 $("#usermsg1").text("查无此人。").css({"color":"red","font-size":"12px"});
							  $("#usermsg").text("");
							 }
							else{
							 $("#usermsg").text(""+d.userName+"").css({"color":"green","font-size":"12px"});
							  $("#usermsg1").text("");
							 }
						 }else{
						  $("#userId").val("");
						  $("#usermsg").text("");
						  $("#usermsg1").text("");
						 }
                    },

                    error:function(){
                     $("#usermsg1").text("系统异常，请稍后重试！").css({"color":"red","font-size":"12px"});

                    }//这里不要加","
  	 	});
  	 });
  	 
  	  $("input:radio[name='type']:checked").blur(function(){
  		 var type =$("input:radio[name='type']:checked").val();
  	 	if(type=="1"){
						  $("#userId").val("");
						  $("#usermsg").text("");
						  $("#usermsg1").text("");
						 }
  	 });
	
  	 
	$("#sbtn").click(function(){
	 if (!confirm("确认提交会员升级？")) {
				return false;
			}else{
		var error = "";
	 var type =$("input:radio[name='type']:checked").val();
				 if(type==""){
				  $("#typemsg").text("升级类型不能为空").css({"color":"red","font-size":"12px"});
				 	error="升级类型不能为空。/n";
				 }else{
				  	$("#typemsg").text("");
				 }
			 if(type=="2"){
			 	if($("#userId").val==""){
			 		 $("#usermsg1").text("会员编号不能为空。").css({"color":"red","font-size":"12px"});
			 		  $("#usermsg").text("");
			 		error=error+"会员编号不能为空。/n";
			 	}else if( $("#usermsg1").text()!=null){
			 		 $("#usermsg").text("");
			 		error=error+ $("#usermsg1").text();
			 	}
			 }
		
		if(error!=""){
			return false;
		}else{
       		 $('#myform').submit();
       		 return ture;
		
		}
		}
	});
});
   </script>
</html>
<%
}}
%>