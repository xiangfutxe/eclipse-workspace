<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.StringUtil"%>
<%@ page import="com.ssm.pojo.WSettle"%>

<%
User user = (User)request.getSession().getAttribute("sys_user");
String psw2 = (String)request.getSession().getAttribute("psw2");
User user1 = (User)request.getAttribute("user1");
String weekTag = (String) request.getAttribute("weekTag");
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
    <title>已结业绩</title>
    
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
          		 <li><a href="UserServlet?method=user_detail">个人资料</a></li>
           			 
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
            	<h2>当前位置：个人信息-&gt;<a>历史业绩</a></h2>
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
         <h2>历史业绩</h2><span class="required_notification"></span>
   </div>
  
  			  <div class="p-li ">
     <div class="form-table">
         <form  action="ReportServlet?method=report_wsettle_summary"  id="sform" name="sform" method="post">
		    <%
								 List<Integer> slist = (List<Integer>)request.getAttribute("slist");
								 if(slist!=null){
						if(slist.size()>0){%>
		   按期查询：<select id="weekTag" name="weekTag">
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
					
						<%WSettle w = (WSettle) request.getAttribute("wst");
						if(w!=null){ %>
						<tr>
						<th>当前期</th><td>第<%=w.getWeekTag()%>期</td>
						</tr>
						<tr><th>当期奖衔</th>
  <td><%if(w.getRank()==1){ %>一星经理<%}else if(w.getRank()==2){ %>二星经理<%}else if(w.getRank()==3){ %>三星经理<%}else if(w.getRank()==4){ %>
四星经理 <%}else if(w.getRank()==5){ %>五星经理<%}else if(w.getRank()==6){ %>一星钻石<%}else if(w.getRank()==7){ %>二星经理<%}else if(w.getRank()==8){ %>三星钻石
 <%}else if(w.getRank()==9){ %>四星钻石<%}else if(w.getRank()==10){ %>五星钻石<%}else if(w.getRank()==11){ %>董事<%}else{ %>-<%} %></td>
</tr>
						<tr><th>区域总业绩</th><td><%=StringUtil.decimalFormat((w.getTotalPerformance()+w.getNewPerformance()))%></td>
  </tr>
						<tr><th>期初业绩</th><td><%=StringUtil.decimalFormat(w.getTotalPerformance())%></td>
  </tr>
						<tr><th>区域新增业绩</th><td><%=StringUtil.decimalFormat(w.getNewPerformance())%></td>
  </tr>
						<tr><th>A区新增业绩</th> <td><%=StringUtil.decimalFormat(w.getApv())%></td>
  </tr>
						<tr><th>A区盈余业绩</th>  <td><%=StringUtil.decimalFormat(w.getAcpv())%></td>
   </tr>
						<tr> <th>B区新增业绩</th> <td><%=StringUtil.decimalFormat(w.getBpv())%></td>
   </tr>
						<tr> <th>B区盈余业绩</th>  <td><%=StringUtil.decimalFormat(w.getBcpv())%></td>
   </tr>
						<tr>  <th>服务新增业绩</th>  <td><%=StringUtil.decimalFormat(w.getRpv()+w.getRpv1())%></td>
   </tr>
						<tr>  <th>期初服务业绩</th>  <td><%=StringUtil.decimalFormat(w.getRtpv()+w.getRtpv1())%></td>
    </tr>
						<tr> <th>服务总累计</th>   <td><%=StringUtil.decimalFormat(w.getRtpv()+w.getRpv()+w.getRpv1()+w.getRtpv1())%></td>
						</tr>
						<%}else{ %>
  			 <tr>
  			 <td colspan="2"><label>暂无记录</label></td>
  			 </tr>
  			 <%} %>
  			 </table>
			
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
	
   </script>
</html>
<%
}}
%>