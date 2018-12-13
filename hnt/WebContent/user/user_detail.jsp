<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.User"%>

<%@ page import="com.ssm.utils.StringUtil"%>
<%@ page import="com.ssm.utils.Constants"%>


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
    <title>个人资料</title>
    
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
          		  <li class="now"><a href="UserServlet?method=user_detail">个人资料</a></li>
                  
                     <%if(auth1.substring(8, 9).equals("1")){ %>
                    <li><a href="DRewardServlet?method=reward_day_list">补贴明细</a></li>
                    <%}if(auth1.substring(9, 10).equals("1")){  %>
                    <li><a href="ReportServlet?method=report_settle_day">已结业绩</a></li>
                   
                    <%} %>
                    <li><a href="password1_edit.jsp">登陆密码</a></li>
                     <li><a href="password2_edit.jsp">支付密码</a></li>
                </ul>
                 <div style="height:300px;"></div>
                </div>
		 <div class="scd_mr">
            	<div class="ht_table">
            	<h2>当前位置：个人信息-&gt;<a>个人资料</a></h2>
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
         <h2>个人资料</h2><span class="required_notification">* 表示必填项</span>
   </div>
  
  			 <table class ="form-table">
  			 <tr>
  			 <td width="12%"><label>会员等级 : </label></td>
  			 <td  width="38%"><%if(user.getRankJoin()==0) {%>消费会员<%}else if(user.getRankJoin()==1) {%>银卡会员<%}else if(user.getRankJoin()==2){ %>金卡会员
				<%}else if(user.getRankJoin()==3){ %>白金卡会员<%}else if(user.getRankJoin()==4){ %>VIP会员
				<%}else if(user.getRankJoin()==5){ %>至尊会员<%}else{ %>-<%} %></td>
  			 <td width="12%"><label>管理级别 : </label></td>
  			 <td  width="38%">
  			 <%	if(user.getRank()==1) { out.println(Constants.RANK_1);
  				 }else if(user.getRank()==2){ out.println(Constants.RANK_2);
				}else if(user.getRank()==3){ out.println(Constants.RANK_3);
				}else if(user.getRank()==4){ out.println(Constants.RANK_4);
				}else if(user.getRank()==5){ out.println(Constants.RANK_5);
				}else{ %>-<%} %></td>
  			 </tr>
  			 
  			 
  			 
  			  <tr>
  			 <td><label>会员编号:</label></td>
  			 <td> <%=StringUtil.notNull(user1.getUserId()) %></td>
  			 <td><label>真实姓名:</label></td>
  			 <td> <%=StringUtil.notNull(user1.getUserName())%></td>
  			 </tr>
  			   <tr>
  			 <td><label>性别:</label></td>
  			 <td><input type="radio" name="sex" value="男" <%if(StringUtil.notNull(user1.getSex()).equals("男")){ %>checked<%} %>>男 &nbsp;<input type="radio" name="sex" value="女" <%if(StringUtil.notNull(user1.getSex()).equals("女")){ %>checked<%} %>>女 <span id="sexTag"></span></td>
  			 <td><label>证件类型:</label></td>
  			 <td><%=StringUtil.notNull(user1.getDocumentType())%> </td>
  			 </tr>
  			 
  			   <tr>
  			 <td><label>证件号码:</label></td>
  			 <td> <%=StringUtil.notNull(user1.getNumId()) %></td>
  			 <td><label>手机号码:</label></td>
  			 <td><%=StringUtil.notNull(user1.getTel()) %></td>
  			 </tr>
  			 
  			  <tr>
  			 <td><label>固定电话:</label></td>
  			 <td><input
								id="phone" name="phone" 
								value='<%=StringUtil.notNull(user1.getPhone()) %>' type="text">&nbsp;<span
								id="phoneTag"></span></td>
  			 <td><label>电子邮箱:</label></td>
  			 <td><input
								id="email" name="email"
								value='<%=StringUtil.notNull(user1.getEmail()) %>' type="text">&nbsp;<span
								id="emailTag"></span></td>
  			 </tr>
  			 
  			  <tr>
  			 <td><label>QQ号码:</label></td>
  			 <td><input id="qq" name="qq" value='<%=StringUtil.notNull(user1.getQq()) %>'  type="text">&nbsp;<span id="qqTag"></span></td>
  			 <td><label>微信:</label></td>
  			 <td><input id="weixin" name="weixin"  value='<%=StringUtil.notNull(user1.getWeixin()) %>'  type="text">&nbsp;<span id="weixinTag"></span></td>
  			 </tr>
  			 
  			  <tr>
  			 <td><label><span class="required">*</span>银行账户名:</label></td>
  			 <td><input id="accountName" name="accountName" value='<%=StringUtil.notNull(user1.getAccountName())%>' type="text" readonly>&nbsp;<span id="accountNameTag"></span></td>
  			 <td><label><span class="required">*</span>银行卡号:</label></td>
  			 <td><input id="accountId" name="accountId"  value='<%=StringUtil.notNull(user1.getAccountId())%>' type="text">&nbsp;<span id="accountIdTag"></span></td>
  			 </tr>
  			 
  			  <tr>
  			 <td><label><span class="required">*</span>开户银行:</label></td>
  			 <td><select name="bankName" id="bankName">
								<option value="中国农业银行股份有限公司" <%if(StringUtil.notNull(user1.getBankName()).equals("中国农业银行股份有限公司")){%>selected<%}%>>中国农业银行股份有限公司</option>
								<option value="中国工商银行股份有限公司"
									<%if (StringUtil.notNull(user1.getBankName()).equals(
						"中国工商银行股份有限公司")) {%>
									selected <%}%>>中国工商银行股份有限公司</option>
									<option value="中国建设银行股份有限公司"
									<%if (StringUtil.notNull(user1.getBankName()).equals(
						"中国建设银行股份有限公司")) {%>
									selected <%}%>>中国建设银行股份有限公司</option>
									<option value="中国银行股份有限公司"
									<%if (StringUtil.notNull(user1.getBankName()).equals(
						"中国银行股份有限公司")) {%>
									selected <%}%>>中国银行股份有限公司</option></select>&nbsp;<span id="bankNameTag"></span></td>
  			 <td><label><span class="required">*</span>开户网点:</label></td>
  			 <td><input id="bankAdr" name="bankAdr" value='<%=StringUtil.notNull(user1.getBankAdr()) %>' type="text">&nbsp;<span id="bankAdrTag"></span></td>
  			 </tr>
  			 <tr>
  			 <td><label><span class="required">*</span>收货地址：</label></td>
  			 <td colspan="3"><span class="right" id="city_2">
							<select class="prov" id="province" name="province">
  		</select> 
    	<select class="city" id="city" name="city" disabled="disabled">
		</select>
		 <select class="dist" id="area" name="area" disabled="disabled">
		</select> &nbsp;<span id="provinceTag"></span></span></td>
  			
  			 </tr>
  			  <tr>
  			 <td><label><span class="required">*</span>详细地址：</label></td>
  			 <td colspan="3"><input style="width:300px;" maxlength="100" id="address" name="address"
								value='<%=StringUtil.notNull(user1.getAddress()) %>' type="text">&nbsp;<span
								id="addressTag"></span></td>
  			
  			 </tr>
  			 </table>
  			 
              <div class="p-li  text-center">
                	<input type="button"  id="btn1"  class="button blue-sky" value="保存修改">
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
 <script type="text/javascript">
	$(function() {
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  //验证身份证号码
   var reg1 = /^[1-9]+[0-9]*]*$/;  //验证整数
 var reg2 = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;//电话号码与手机号码同时验证
    var reg3 = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; //验证电子邮箱
   // var reg4 =  /^(?!(\d)\1{1,}$)[1-9]\d{4,}$/;//验证QQ号码
    $("#city_2").citySelect({
		prov:"<%=StringUtil.notNull(user1.getProvince())%>", 
    	city:"<%=StringUtil.notNull(user1.getCity())%>",
		dist:"<%=StringUtil.notNull(user1.getArea())%>",
		nodata:"none"
	}); 
   

    $("#age").blur(function(){
   	 if($("#age").val()=="")  $("#ageTag").text("年龄不能为空！").css({"color":"red","font-size":"12px"}); 
	else if(!reg1.test($("#age").val())) $("#ageTag").text("年龄格式有误！").css({"color":"red","font-size":"12px"}); 
	else if($("#age").val()>200) $("#ageTag").text("年龄超出了正常范围,请核对！").css({"color":"red","font-size":"12px"}); 
		else if($("#age").val()<18) $("#ageTag").text("年龄未满18岁，未满足加盟资格！").css({"color":"red","font-size":"12px"}); 
	else $("#ageTag").text("");
		
   });
   
    $("#province").blur(function(){
   		 if($("#province").val()=="")
   		  $("#provinceTag").text("省市县不能为空！").css({"color":"red","font-size":"12px"});
   		  else $("#provinceTag").text("");
   	});
   	 $("#city").blur(function(){
   		 if($("#city").val()=="")
   		  $("#provinceTag").text("省市县不能为空！").css({"color":"red","font-size":"12px"});
   		  else $("#provinceTag").text("");
   	});
   
   $("#email").blur(function(){
   	 if($("#email").val()!=""){
				if(!reg3.test($("#email").val()))
					$("#emailTag").text("电子邮箱格式有误！").css({"color":"red","font-size":"12px"}); 
					else $("#emailTag").text("");
	}else $("#emailTag").text("");
   });
   /*
   $("#qq").blur(function(){
   	 if($("#qq").val()!=""){
				if(!reg1.test($("#qq").val()))
					$("#qqTag").text("QQ号码格式有误！").css({"color":"red","font-size":"12px"}); 
					else $("#qqTag").text("");
					
	}else $("#qqTag").text("");
   });
   */
   
   			$("#address").blur(function(){
   		 if($("#address").val()!="") {
   		 if(getByteLen($("#address").val())>100){ $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"12px"});
   		}else $("#addressTag").text("");
   		}else $("#addressTag").text("");
   			});
   			
  	 
  	  $("#btn1").click(function(){
  	   if (!confirm("确认保存修改！")) {
				return false;
			}
			else{
	  	 var errorstr = "";
			 if ($(":radio:checked").length == 0)
			{
			$("#sexTag").text("未选择性别！").css({"color":"red","font-size":"12px"});
			errorstr="1";
			}
			if($("#email").val()!=""){
				if(!reg3.test($("#email").val())){
					$("#emailTag").text("电子邮箱格式有误！").css({"color":"red","font-size":"12px"}); 
					errorstr="1";
				}
			}else if($("#emailTag").val()!=""){
				errorstr="1";
			}
			if($("#qq").val()!=""){
				if(!reg1.test($("#qq").val())){
					$("#qqTag").text("QQ号码格式有误！").css({"color":"red","font-size":"12px"}); 
					errorstr="1";
				}
			}else if($("#qqTag").val()!=""){
				errorstr="1";
			}
				if($("#phone").val()!=""){
					if(getByteLen($("#phone").val()) >25){
						$("#phoneTag").text("固定电话格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr="1";
					}
				}else if($("#phoneTag").val()!=""){
					errorstr="1";
				}
				if($("#weixin").val()!=""){
					if(getByteLen($("#weixin").val())>50){
						$("#weixinTag").text("微信格式有误！").css({"color":"red","font-size":"12px"}); 
						errorstr="1";
					}
				}else if($("#weixinTag").val()!=""){
					errorstr="1";
				}		
			 /*
			if($("#age").val()==""){
			 $("#ageTag").text("年龄不能为空！").css({"color":"red","font-size":"12px"}); 
			 errorstr="1";
			}else if($("#ageTag").text()!="") errorstr ="1";
			
   		*/
   		if($("#province").val()=="") {
   		   $("#provinceTag").text("省市县不能为空！").css({"color":"red","font-size":"12px"});
   			errorstr="1";
   			$("#province").focus();
   		 }else if($("#city").val()=="") {
   		   $("#provinceTag").text("省市县不能为空！").css({"color":"red","font-size":"12px"});
   			errorstr="1";
   			$("#city").focus();
   		 }else if($("#provinceTag").val()!=""){
   		 errorstr="1";
   		 $("#province").focus();
   		 }
		
   			if($("#address").val()!="") {
   		  if(getByteLen($("#address").val())>100){
   		   $("#addressTag").text("联系地址字数不能大于50位数！").css({"color":"red","font-size":"12px"});
   			errorstr="1";
   			$("#address").focus();
   				}
   		 }else if($("#addressTag").val()!=""){
   		 errorstr="1";
   		 $("#address").focus();
   		 }
			if(errorstr=="1"){
				return false;
			}else{
			$('#myform').attr("action","UserServlet?method=user_update");
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