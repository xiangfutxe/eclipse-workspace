<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.StringUtil"%>
<%
User user_2 = (User)request.getSession().getAttribute("sys_user");
String path_1 = request.getContextPath();
	
 if(user_2==null){
		out.println("<script>");
		out.println("alert('用户登陆信息获取失败.');parent.window.location.href='login.jsp';");
		out.println("</script>");
}else if(user_2.getUserId().equals("")){
		out.println("<script>");
		out.println("alert('用户登陆信息获取失败.');parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String auth = StringUtil.notNull(user_2.getAuthority());
	
	 if(auth.length()<30){
		out.println("<script>");
		out.println("alert('会员权限信息获取失败，请重新登陆');parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
			
%>


 <div class="header">
<div class="head clearfix">
	<div class="logo"><img src="images/logo-1.png" alt="会员购物商城"/></div>
    <div class="tel">
    <span><a>中国官网</a>&nbsp;|&nbsp;<a>简体中文</a>&nbsp;|&nbsp;<a href="UserServlet?method=login_out">退出系统</a></span></div>
</div>
</div>

<!--导航 -->
<div class="nav_bg">
	<div class="nav_m">
    	<UL id=nav> 
<LI class=top><A class=top_link 
href="UserServlet?method=index"><SPAN>首页／HOME</SPAN></A></LI> 

<LI class=top><A id=products class=top_link 
href="#"><SPAN class=down>个人信息</SPAN></A> 
<UL class=sub> 
<LI><A class=fly href="UserServlet?method=user_detail">个人资料</A></LI> 

 <%if(auth.substring(8, 9).equals("1")){ %>
                    <li><a class=fly href="DRewardServlet?method=reward_day_list">补贴明细</a></li>
                    <%}if(auth.substring(9, 10).equals("1")){  %>
                    <li><a class=fly href="ReportServlet?method=report_settle_day">已结业绩</a></li>
                    <%} %>
<LI><A class=fly href="password1_edit.jsp">登陆密码</A></LI>
<LI><A class=fly href="password2_edit.jsp">支付密码</A></LI>
</UL>
</LI> 
<LI class=top><A id=services class=top_link href="#"><SPAN 
class=down>业务管理</SPAN></A> 
<UL class=sub> 
<LI><A class=fly href="user_add_protocol.jsp">会员注册</A> </LI>
<LI><A class=fly href="rankJoin_up_all.jsp">会员升级</A> </LI>
<%if(auth.substring(10, 11).equals("1")){ %>
<LI><A class=fly href="UserServlet?method=getBelong">销售关系</A> </LI>
<%}if(auth.substring(11, 12).equals("1")){ %>
<LI><A class=fly href="UserServlet?method=getReferee">服务关系</A> </LI>
<%} %>
<LI><A class=fly href="AddressServlet?method=list">地址管理</A> </LI>
<%if(user_2.getCenterId()==2){ %>
<LI><A class=fly href="CenterServlet?method=detail">服务中心</A> </LI>
<%} %>
</UL> 
</LI>

<LI class=top><A id=contacts class=top_link 
href="#"><SPAN class=down>订单中心</SPAN></A> 
<UL class=sub> 
<LI><A  class=fly href="OrderServlet?method=orders">订单列表</A></LI> 
<LI><A  class=fly href="OrderServlet?method=order_divery">服务订单</A></LI>
<LI><A  class=fly href="OrderServlet?method=order_emoney_add">零售购物</A></LI> 
<LI><A  class=fly href="OrderServlet?method=order_dmoney_add">复消购物</A></LI> 
</UL></LI> 

<LI class=top><A id=hr class=top_link href="#"><SPAN 
class=down>财务管理</SPAN></A> 
<UL class=sub> 
<LI><A  class=fly href="AccountServlet?method=money_balance">电子钱包</A></LI> 
<LI><A  class=fly href="money_detail.jsp">账户明细</A></LI> 
<LI><A  class=fly  class=fly href="WithDrewServlet?method=apply_list">奖金提现</A></LI>
<LI><A  class=fly href="ChargeApplyServlet?method=apply_list">账户充值</A></LI>
<LI><A  class=fly href="AccountServlet?method=money_transfer">转账交易</A></LI>
<LI><A  class=fly href="AccountServlet?method=to_emoney">转账报单券</A></LI>
</UL></LI> 
<LI class=top><A id=investors class=top_link 
href="#"><SPAN class=down>新闻资讯</SPAN></A> 
<UL class=sub> 
<LI><A  class=fly href="NewsServlet?method=list">最新动态</A></LI> 
<LI><A  class=fly href="MessageServlet?method=list">留言信箱</A></LI></UL></LI> 
<LI class=top><A id=privacy class=top_link href="ProductServlet?method=product_index&type=1"><SPAN>健康产品</SPAN></A></LI></UL> 
 </div>
</div>
<%}}%>