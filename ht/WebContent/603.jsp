<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page  import="com.pojo.News"%>
<%@ page import="com.pojo.User"%>
<%@ page  import="com.utils.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
News news =(News) request.getAttribute("news");
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
<link rel="stylesheet" type="text/css" href="css/table.css">
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
          
           <li><a href="SchoolServlet.do?method=S_601"><span  class="now">名媛学堂</span></a>
            
             <ul>
                                <li><a href="SchoolServlet.do?method=S_601">学院宗旨</a></li>
                                <li><a href="SchoolServlet.do?method=S_602">视频在线</a></li>
                                <li><a href="SchoolServlet.do?method=S_603">旗袍文化</a></li>
                                <li><a href="SchoolServlet.do?method=S_604">国学传播</a></li>
                            </ul></li>
          
           
              <li><a href="UserServlet.do?method=U_701">会员注册</a>
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
        	<jsp:include page="second.jsp"/>
            <div class="scd_mr">
            	
            	<div class="about clearfix">
            	<div class="ht_table">
            	<h2>当前位置：名媛学堂-><a  href="BranchServlet.do?method=S_603">旗袍文化</a></h2>
            	</div>
            	<div class="ht_table">
            	<ul>
            	 <li class="h2_1"><img alt="" src="images/h2.png">旗袍文化</li>
            	 <li><img alt="" src="images/603_1.jpg" width="830" ></li>
                 <li>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;旗袍是民国的旗人之袍，盛行于三四十年代。行家把二十世纪二十年代看作旗袍流行的起点，三十年代它到了顶峰状态，很快从发源地上海风靡至中国各地。旗袍追随着时代，承载着文明，以其流动的旋律、潇洒的画意与浓郁的诗情，表现出中华女性贤淑、典雅、温柔、清丽的性情与气质。旗袍连接起过去和未来，连接起生活与艺术，将美的风韵洒满人间。
</p><p>&nbsp;</p><h2>文化风格
       </h2><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;旗袍不能脱离人体而孤立存在。女性的头、颈、肩、臂、胸、腰、臀、 腿以及手足，构成众多曲线巧妙结合的完美整体，形成旗袍文化。京派与海派旗袍，代表着艺术、文化上的两种风格。海派风格以吸收西艺为特点，标新且灵活多样，商业气息浓厚；京派风格则带有官派作风，显得矜持凝练。
</p><p>&nbsp;</p><h2>历史沿革</h2>
       <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;旗人之袍与旗袍不是一个固定的形式，而有一个变化发展的过程。把旗袍视为旗人之袍或旗女之袍虽看似无大错，却难免有望文生义之嫌。
</p><p>&nbsp;</p><h2>旗袍起源</h2>
       <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;辛亥革命废除帝制，创立民国。民国之初，剪辫发，易服色，把属于封建朝代的冠服等级制度送进了历史博物馆，这一切为倒大袖与新样式旗袍的延生创造了条件。二十年代早期，城市女性中时兴过一阵“文明新装”。
</p><p>&nbsp;</p><h2>黄金时代</h2>
       <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二十世纪二十年代到四十年代，是中国旗袍最灿烂的时期。尤其是三十年代，旗袍奠定了它在女装舞台上不可替代的重要地位，成为中国女装的典型代表，基本完成旗袍文化走向经典的过程，四十年代是其黄金时代在时间上的延续。 经过二十世纪上半叶的演变，旗袍的各种基本特征和组成元素慢慢稳定下来。旗袍成为一种经典女装。经典相对稳定，而时装千变万化。但时装设计师常从经典的宝库中寻找灵感，旗袍也是设计师灵感的来源之一。
</p><p>&nbsp;</p><h2>风姿再现</h2>
       <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二十世纪五十年代，旗袍曾有过灿烂的一瞬 。在人民当家作主的时代，服装流行的主导已转向平民。 从一九六六至一九七六年，中华传统文化的遭遇浩劫，旗袍文化也被冷落。二十世纪八十年代开放之初，被冷落了三十年之久的旗袍文化显得有些落伍。近十几年来，时装中重新出现的旗袍，在国际时装舞频频亮相，风姿绰约有胜当年，并被做为一种有民族代表意义的正式礼服出现在各种国际社交礼仪场合。
<p>&nbsp;</p>
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
