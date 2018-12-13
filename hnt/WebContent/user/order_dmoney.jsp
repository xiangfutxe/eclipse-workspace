<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil"%>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.utils.Pager" %>
<%@page import="com.ssm.pojo.Product" %>
<%@page import="com.ssm.pojo.Address" %>
<%@ include file="check.jsp" %>  

<%
User user = (User)request.getSession().getAttribute("sys_user");
String message = (String) request.getAttribute("message");
String psw2 = (String)request.getSession().getAttribute("psw2");
String token = (String)request.getSession().getAttribute("token");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else if(user.getCenterId()==2){
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>复消购物</title>
    
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
                  <li><a href="OrderServlet?method=orders">订单列表</a></li> 
				<li><a href="OrderServlet?method=order_divery">服务订单</a></li> 
				<li><a   href="OrderServlet?method=order_emoney_add">零售购物</a></li>
				<li class="now"><a href="OrderServlet?method=order_dmoney_add">复消购物</a></li>
                </ul>
                                 <div style="height:300px;"></div>
                
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：订单中心-&gt;<a>复消购物</a></h2>
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
            		  <form  action="OrderServlet?method=order_dmoney_add"  id="myform" name="myform" method="post">
                	<div class="notice">
         <h2>复消购物</h2><span class="required_notification"></span>
   </div>
   
							<div class="p-li">
							
							<table class="form-table">
							<tr>
							<td>
							购物类型<span class="required">*</span>:
							</td>
							<td>
							<input type="radio" name="type" value="1" checked>自主购物&nbsp;<input type="radio" name="type" value="2">会员代购&nbsp;<span id="typemsg"></span>
							</td>
							</tr>
							<tr>
								<td>
								会员编号：
								</td>
								<td>
								<input type="text" name="userId" id="userId"/><span id="usermsg"></span><span id="usermsg1"></span>
								</td>
							</tr>
							
							<tr>
								<td>
								备注说明：
								</td>
								<td>
								如果选择自主购物，无需输入会员编号；如果是会员代购，请输入会员编号
								</td>
							</tr>
							</table>
							
				  </div>
              <div class="p-li">
                	<input type="button"  id="btn1"  class="button blue-sky" value="提交订单">
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
<script type="text/javascript">
  
	/* 页面加载完成，绑定事件 */
	$(document).ready(function() {
	var reg = /^[0-9]*]*$/;
	
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
	
		
		
		
		$("#btn1").bind("click", function() {
		if (!confirm("确认保存并进入下一步流程！")) {
			return false;
		}else{
			var error = "";
			 var type =$("input:radio[name='type']:checked").val();
				 if(type==""){
				  $("#typemsg").text("购物类型不能为空").css({"color":"red","font-size":"12px"});
				 	error="购物类型不能为空。/n";
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
			alert(error);
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
<%}%>