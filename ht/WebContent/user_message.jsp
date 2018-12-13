<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Branch"%>
<%@page import="com.utils.StringUtil"%>
<%@page import="com.utils.DateUtil"%>
<%@page import="com.utils.Pager"%>
<%@ page import="com.pojo.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String message=(String) request.getAttribute("message");
User user = (User)request.getSession().getAttribute("ht_user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>华太俱乐部</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="华太俱乐部">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="Assets/css/reset.css"/>
<script type="text/javascript" src="Assets/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="Assets/js/js_z.js"></script>
<script type="text/javascript" src="Assets/js/main.js"></script>
<link rel="stylesheet" type="text/css" href="css/about.css">
<script type="text/javascript" src="js/other.js"></script>
  </head>
  
 <body>
 <!--头部-->
 <div class="header">
<div class="head clearfix">
	<div class="logo"><a href=""><img src="images/logo.png" alt="华太"/></a></div>
    <div class="tel">  <span><%if(user==null){ %><a href="UserServlet.do?method=login">会员登录</a>
    &nbsp;|&nbsp;<a href="UserServlet.do?method=user_add_branch">免费注册</a>
    <%}else{ %><%=StringUtil.notNull(user.getUserId())%>(<a href="UserServlet.do?method=login_out">退出</a>)<%} %>
    &nbsp;|&nbsp;<a href="ContactServelt.do?method=C_801">联系我们</a></span></div></div>
</div>
<!--导航 -->
<div class="nav_bg">
	<div class="nav_m">
    	<ul id="nav">
        	<li>
        	<a href="NewsServlet.do?method=index">首页</a>
        	</li>
          
            <li><a href="AboutServlet.do?method=A_201">关于我们</a>
                            <ul>
                                 <li><a href="AboutServlet.do?method=A_201">公司简介</a></li>
                                <li><a href="AboutServlet.do?method=A_202">企业文化</a></li>
                                <li><a href="AboutServlet.do?method=A_203">企业愿景</a></li>
                                <li><a href="AboutServlet.do?method=A_204">核心价值观</a></li>
                                <li><a href="AboutServlet.do?method=A_205">共同发展</a></li>
                            </ul>
            </li>
          
            <li><a href="BranchServlet.do?method=B_305">全球分会</a>
               <ul>
                                <li><a href="BranchServlet.do?method=B_301">全景规划</a></li>
                                <li><a href="BranchServlet.do?method=B_302">管理章程</a></li>
                                <li><a href="BranchServlet.do?method=B_303">管理架构</a></li>
                                <li><a href="BranchServlet.do?method=B_304">分会申请</a></li>
                                <li><a href="BranchServlet.do?method=B_305">分会动态</a></li>
                            </ul>
            </li>
           
          
            <li><a href="ActivityServlet.do?method=A_401">全球活动</a>
               <ul>
                              <li><a href="ActivityServlet.do?method=A_402">总部竞赛</a></li>
                                <li><a href="ActivityServlet.do?method=A_403">分部竞赛</a></li>
                                <li><a href="ActivityServlet.do?method=A_404">环球之旅</a></li>
                                <li><a href="ActivityServlet.do?method=A_401">魅惑倩影</a></li>
                            </ul></li>
          
           <li><a href="PhilanthropyServlet.do?method=P_501">慈善公益</a>
             <ul>
                                <li><a href="PhilanthropyServlet.do?method=P_501">公益足迹</a></li>
                                <li><a href="PhilanthropyServlet.do?method=P_502">善行有我</a></li>
                                <li><a href="PhilanthropyServlet.do?method=P_501">公益剪影</a></li>
                            </ul></li>
          
           <li><a href="SchoolServlet.do?method=S_601">名媛学堂</a>
            
             <ul>
                                <li><a href="SchoolServlet.do?method=S_601">学院宗旨</a></li>
                                <li><a href="SchoolServlet.do?method=S_602">视频在线</a></li>
                                <li><a href="SchoolServlet.do?method=S_603">旗袍文化</a></li>
                                <li><a href="SchoolServlet.do?method=S_604">国学传播</a></li>
                            </ul></li>
          
           
              <li><a href="UserServlet.do?method=U_701"><span  class="now">会员注册</span></a>
                <ul>
                                <li><a href="UserServlet.do?method=U_701">分会一览</a></li>
                                <li><a href="UserServlet.do?method=U_702">会员须知</a></li>
                                <%if(user==null){%>
                                <li><a href="UserServlet.do?method=user_add_branch">免费注册</a></li>
                                <%} %>
                            </ul>
              </li>
          
             <li><a href=ContactServelt.do?method=C_801>联系我们</a>
               <ul>
                                <li><a href="ContactServelt.do?method=C_801">联系方式</a></li>
                                <%if(user==null){%>
                                <li><a href="ContactServelt.do?method=C_802">在线留言</a></li>
                                <%}else{%>
                                <li><a href="ContactServelt.do?method=C_803">留言簿</a></li>
                                <%} %>
                            </ul></li>
          
            <li><a href="ShopServlet.do?method=index">华太商城</a></li>
        </ul>
    </div>
</div>
<div class="main_bg">
    <!--幻灯片-->
    <div class="banner">
        <div id="inner">
            <div class="hot-event">
                <div class="event-item" style="display: block;">
                    <a class="banner">
                        <img src="images/about/banner01.jpg" class="photo" alt="华太" />
                    </a>
                </div>
            </div>
        </div>
    </div>
	<!--幻灯片-->
	<div class="second">   
        <div class="scd_m clearfix">
        	<div class="scd_ml">
            	<ul class="sidenav">
            	 <a href="#"><img alt="" src="images/left_1.png"></a>
            	<a href="#"><img alt="" src="images/left_1_1.jpg" width="300px"></a>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;旗袍是民国的旗人之袍，盛行于三四十年代。</li>
                <li>二十世纪二十年代看作旗袍流行的起点，三十年代</li>
                <li>它到了顶峰状态，很快从发源地上海风靡至中国各</li>
                <li>地。旗袍追随着时代，承载着文明，以其流动的旋</li>
                <li>律、潇洒的画意与浓郁的诗情，表现出中华女性贤</li>
                <li>淑、典雅、温柔、清丽的性情与气质。旗袍连接起</li>
                <li>过去和未来，连接起生活与艺术，将美的风韵洒满</li>
                <li>人间。</li>
                </ul>
                  <ul>
            	 <li class="letf">&nbsp;</li>
            	 </ul>
                <ul  class="sidenav">
                <a href="#"><img alt="" src="images/left_2.png"></a>
                <li>&nbsp;</li></ul>
                  <ul>
            	 <li class="letf">&nbsp;</li>
            	 </ul>
                 <ul  class="sidenav">
               <a href="#"><img alt="" src="images/left_3.png"></a>
                <li>&nbsp;</li></ul>
              
            </div>
            <div class="scd_mr">
            	<div class="about clearfix">
            	<div class="ht_table">
            	<h2>当前位置：<a href="NewsServlet.do?method=news_branch_list">全球分会</a>-><a  href="NewsServlet.do?method=news_branch_list">分会资讯</a></h2>
            	</div>
            	<div class="ht_table">
                	<ul>
                	<li>
                	<%=StringUtil.notNull(message) %>
                	</li>
                	
                	</ul>
         </div> 
               </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
