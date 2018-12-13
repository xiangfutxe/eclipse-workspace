<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>
<%@page import="com.ssm.pojo.WReward" %>
<%@page import="com.ssm.utils.Pager" %>

<%@ include file="check.jsp" %>  

<%
User user = (User)request.getSession().getAttribute("sys_user");
String psw2 = (String)request.getSession().getAttribute("psw2");
String weekTag = (String) request.getAttribute("weekTag");
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
	}else if(auth1.substring(8, 9).equals("1")){
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>补贴明细</title>
    
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
 <script src="../js/calendar.js"></script>
 
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
             <li><a href="UserServlet?method=user_detail">个人资料</a></li>
             <%if(auth1.substring(8, 9).equals("1")){ %>
                    <li  class="now"><a href="RewardServlet?method=reward_week_list">历史补贴</a></li>
                    <%}if(auth1.substring(9, 10).equals("1")){  %>
                    <li><a href="ReportServlet?method=report_wsettle_summary">历史业绩</a></li>
                   
                    <%} %>
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
            	<h2>当前位置：个人信息-&gt;<a>历史补贴</a></h2>
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
         <h2>历史补贴</h2><span class="required_notification"></span>
   </div>
 
    <div class="p-li ">
     <div class="form-table-one">
         <form  action="RewardServlet?method=reward_week_list"  id="sform" name="sform" method="post">
		    <%
								 List<Integer> slist = (List<Integer>)request.getAttribute("slist");
								 if(slist!=null){
						if(slist.size()>0){%>
		   按期查询:<select  id="weekTag"
									name="weekTag">
									<option value="" >全部</option>
									<% 
								int s =slist.size();
							if(s>=4)s =4;
								for(int i=0;i<s;i++){
								
								int week = slist.get(i);
							%>
										<option value="<%=week%>" <%if(weekTag.equals(String.valueOf(week))){ %>selected<%} %>>第<%=week%>期</option>
										<%}%>
								</select>
								<input class="button-search blue-sky" id="search"
									name="search" value="查询" type="submit">
									<%}}%>
		
			</form>	
			</div>
    </div>
			 <div class="p-li">
			<table class="result-tab" width="100%">
						<thead>
						 <%
								
									WReward w = (WReward)request.getAttribute("wrd");
									if(w!=null){
									if(w.getState()>1){
							%>
							<tr>
 <th>起止时间</th><td><%=StringUtil.parseToString(w.getStartTime(), DateUtil.yyyyMMddHHmmss)%>至<%=StringUtil.parseToString(w.getEndTime(), DateUtil.yyyyMMddHHmmss)%></td>
 </tr><tr><th>推荐补贴</th><td><%=StringUtil.decimalFormat(w.getAmount_7())%></td>
 </tr><tr><th>见点补贴</th><td><%=StringUtil.decimalFormat(w.getAmount_8())%></td>
   </tr><tr><th>拓展补贴</th> <td><%=StringUtil.decimalFormat(w.getAmount_2())%></td>
   </tr><tr>  <th>培育补贴</th><td><%=StringUtil.decimalFormat(w.getAmount_3())%></td>
   </tr><tr>  <th>领导补贴</th> <td><%=StringUtil.decimalFormat(w.getAmount_4())%></td>
   </tr><tr>   <th>物流补助</th> <td><%=StringUtil.decimalFormat(w.getAmount_5())%></td>
   </tr><tr>    <th>消费补贴</th> <td><%=StringUtil.decimalFormat(w.getAmount_6())%></td>
  </tr><tr>     <th>小计</th><td><%=StringUtil.decimalFormat(w.getAmount_9())%></td>
 </tr><tr>		 <th>重消</th><td><%=StringUtil.decimalFormat(w.getAmount_10())%></td>
 </tr><tr><th>综合费</th><td><%=StringUtil.decimalFormat(w.getAmount_11())%></td>
 </tr><tr> <th>实发</th><td><%=StringUtil.decimalFormat(w.getAmount_12())%></td>
 </tr><tr><th>状态</th><td><%if(w.getState()==1){%>待发补贴<%}else if(w.getState()==2){ %>已发补贴<%}else if(w.getState()==3){%>拒发补贴<%}else{%>结算中<%} %></td>
 </tr>	<%}else{ %><tr><td colspan="2">暂无记录！</td></tr><%}}else{ %><tr><td colspan="2">暂无记录！</td></tr><%} %>
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
 <script language="JavaScript">
	function delcfm() {
		if (!confirm("确认要进行退单操作，该操作执行后将不可逆转？")) {
			window.event.returnValue = false;
		}
	}

	$(function() {

		$("#search")
				.click(
						function() {
											var startTime = $("#startTime")
													.val();
											var endTime = $("#endTime").val();
											var str = "";
											var a = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;
											
											if (startTime == "") {
												if(endTime!=""){
													str = str + "开始时间不能为空！\n";
													$("#startTime").focus();
												}
											}else if (endTime == "") {
												if(startTime!=""){
													str = str + "结束时间不能为空！\n";
													$("#endTime").focus();
												}
											} else if (startTime.match(a) == null) {
												str = str + "开始时间格式有误！\n";
												$("#startTime").focus();
											} else if (endTime.match(a) == null) {
												str = str + "结束时间格式有误！\n";
												$("#endTime").focus();
											}else if(startTime>endTime)
												{
												str = str + "开始时间不能超过结束时间！\n";
												$("#endTime").focus();
											}
											
											if (str == "") {
												$('#sform').submit();
											} else {
												alert(str);
												return false;
											}
						});
	});
</script>
</html>
<%
}else{
out.println("<script>");
		out.println("alert('你没有该操作权限。');");
		out.println("</script>");
}
}
%>