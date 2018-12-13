<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="com.pojo.User"%>
<%@ page import="com.utils.StringUtil"%>
<%
User user_2 = (User)request.getSession().getAttribute("ht_user");
String path_1 = request.getContextPath();
%>


 <div class="header">
<div class="head clearfix">
	<div class="logo"><img src="images/logo-1.png" alt="img"/></div>
    <div class="tel">
    <span><a href="ContactServelt.do?method=C_801">中国官网</a>&nbsp;|&nbsp;<a href="ContactServelt.do?method=C_801">简体中文</a>&nbsp;|&nbsp;<a href="ContactServelt.do?method=C_801">退出系统</a></span></div>
</div>
</div>

<!--导航 -->
<div class="nav_bg">
	<div class="nav_m">
    	<UL id=nav> 
<LI class=top><A class=top_link 
href="#"><SPAN>首页／HOME</SPAN></A></LI> 
<LI class=top><A id=products class=top_link 
href="#"><SPAN class=down>个人信息</SPAN></A> 
<UL class=sub> 
<LI><A class=fly href="201.jsp">个人资料</A></LI> 
<LI><A class=fly href="#">补贴明细</A></LI> 
<LI><A class=fly href="#">已结业绩</A></LI> 
<LI><A class=fly href="#">待结业绩</A></LI>
<LI><A class=fly href="#">登陆密码</A></LI>
<LI><A class=fly href="#">支付业绩</A></LI>
</UL>
</LI> 
<LI class=top><A id=services class=top_link href="#"><SPAN 
class=down>业务管理</SPAN></A> 
<UL class=sub> 
<LI><A class=fly href="#">会员注册</A> </LI>
<LI><A class=fly href="#">会员升级</A> </LI>
<LI><A class=fly href="#">销售关系</A> </LI>
<LI><A class=fly href="#">服务关系</A> </LI>
<LI><A class=fly href="#">地址管理</A> </LI>
</UL> 
</LI>

<LI class=top><A id=contacts class=top_link 
href="#"><SPAN class=down>订单中心</SPAN></A> 
<UL class=sub> 
<LI><A  class=fly href="#">订单列表</A></LI> 
<LI><A  class=fly href="#">服务订单</A></LI>
<LI><A  class=fly href="#">零售购物</A></LI> 
<LI><A  class=fly href="#">复消购物</A></LI> 
</UL></LI> 
<LI class=top><A id=hr class=top_link href="#"><SPAN 
class=down>财务管理</SPAN></A> 
<UL class=sub> 
<LI><A  class=fly href="#">电子钱包</A></LI> 
<LI><A  class=fly href="#">账户明细</A></LI> 
<LI><A  class=fly  class=fly href="#">奖金提现</A></LI>
<LI><A  class=fly href="#">账户充值</A></LI>
<LI><A  class=fly href="#">转账交易</A></LI>
</UL></LI> 
<LI class=top><A id=investors class=top_link 
href="#"><SPAN class=down>新闻资讯</SPAN></A> 
<UL class=sub> 
<LI><A  class=fly href="#">最新动态</A></LI> 
<LI><A  class=fly href="#">通知公告</A></LI> 
<LI><A  class=fly href="#">留言信箱</A></LI></UL></LI> 
<LI class=top><A id=privacy class=top_link href="#"><SPAN>健康产品</SPAN></A></LI></UL> 
    </div>
</div>