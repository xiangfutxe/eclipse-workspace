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
    <div class="tel"><span><%if(user==null){ %><a href="UserServlet.do?method=login">会员登录</a>
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
           
          
            <li><a href="ActivityServlet.do?method=A_401"><span  class="now">全球活动</span></a>
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
            	<h2>当前位置：<a href="ActivityServlet.do?method=A_401">全球活动</a></h2>
            	</div>
            	<div class="ht_table">
            	<ul>
            	 <li class="h2_1"><img alt="" src="images/h2.png"> 大赛简介</li>
              <p>美丽的伊始篇.起缘：</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;“太太”一词源于两千多年商周时期，是对已婚女性的尊重和赞美。“太太”可以是妻子，母亲、儿媳，女儿等。太太的传统女德，是我们中华民族的“根”和“魂”，是我们民族的精神财富和传家之宝！
　　</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;世界华埠太太竞选沿用了“太太”这一尊称，以突显出我们致力于展现当代中国女性“积极、时尚、关爱、参与、奉献”的内在世界，彰显世界华埠太太“参与奉献，发展公益，关爱社会，共建和谐”的知性世界。华埠太太不仅仅代表美丽，她们更是慈善、关爱、健康与和平的传播者和践行者。世界因她们的善良而善良，因她们的宁静而宁静，因她们的时尚而时尚，因她们美丽而美丽！
　　</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;“世界华埠太太竞选”中国选区系列活动以中国宋庆龄基金会为指导单位，由广州市华太文化传播有限公司进行承办，以“弘扬中华女德，共建幸福家园”为竞选活动的宣传口号，将中国女性“体贴善良、忠贞忍耐、爱心奉献、坚强本分、内敛包容”的传统美德予以挖掘和弘扬，秉承“持续不断、投身公益、和睦家庭、奉献社会、关爱全球”的主旨，实现“促进交流、增进友谊、创造机会”的目标。“世界华埠太太竞选”中国选区活动得到了中国文化部、中国妇联的高度重视。
　　</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;“世界华埠太太竞选（中国选区）全国性活动现正开展，各地组委会成员汇聚社会贤达和企业精英，见证竞选这一盛事。竞选活动这旨在提供一个高端平台，为中华太太打开通道，提升自我；为企业和个人发展创造最佳机会。参加佳丽如愿向评委、向公众展现内外形象，表达对婚姻、家庭、社会和国际事务的见解，让社会加深了中华女性的传统美德和时尚魅力。只要你有自信，都可以站在台上展现美丽形象和独特风格。“
　　</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;世界华埠太太竞选”是一个全球性、持续性、公益性和友善性的活动。是为善良、宁静、时尚和美丽的全球华埠太太们搭建一个交流和展示的平台，让她们在交流和竞选过程中，经一步实现外在美与内在美的完美结合，借以播亮她们善良的心灯，启迪她们智慧的光芒，表彰她们付出的心血！她们是最优秀的女性，她们用以真诚、善良、智慧和才干为社会的创建做出贡献。她们所到之处谐兴旺，各行各业变得和兴兴向荣！
　　</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;“世界华埠太太竞选”中国选区活动彰显出独特的中国文化魅力，打造一张价值非凡的中国名片，向世界展示中国之美！
　　</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;通过世界华埠太太竞选的活动，呼唤大爱太太们，除了对家庭以及社会承担责任外，更能履行参选承诺，持续不断参与社会公益事业，做到关爱家庭和社会同时，投身公益事业发展，弘扬中华女德，共建幸福家园的伟大使命中来。
美丽的伊始.孕育：
　　</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;女性之伟大，女德之神圣，女孝之恒真，在于她拥有大地搬的慈爱，容忍的内涵和孕育万物至胸怀，所以女人之圣德可以感化万物。倘若天下女子，都能拥有真诚之心，去感受家人之需，做到孝敬父母，辅助丈夫，教养子女，修身养性，不仅能成就美满婚姻和幸福人生，更能继往开来，教育和影响高素质的下一代，为社会贡献高素质人才。
中华女德，是善和爱的融合；是孝和顺的汇聚；是最美的女人味！
　　</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;世界华埠太太竞选价值“大视觉，大文化“概念引入，以传承中华文化为己任，挖掘中华文化瑰宝，汇聚与时俱进的现代文明与时尚元素，创造性的将继承、传播、发扬有机整合起来，担当起对中国社会，对中华民族，对中华儿女的责任和使命。在竞选过程中，汇聚国际服装时尚，引领中国潮流服饰发展。时尚与经典完美演绎。在传承国际美学时尚的同时，打开“美丽时尚的经济”之门，是身份与价值的完美体现。
　　</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;世界华埠太太竞选一改美丽赛事注重外表，重形式，轻内涵的传统模式。打造具有使命感、责任感、影响力的新型赛事典范。让参与的企业更加注重赛事的公益色彩，重视品牌的美誉度。在事件效应，赛事宣传，活动影响力三管齐下，竞选的价值无可限量。世界华埠太太竞选盛事有巨大吸引力和影响力，当优秀太太代表登上展示爱心的舞台掀起时尚风潮，可替企业品牌扩大形象宣传，提升核心价值。省市超越名模以及小姐选美局限，已婚女性具有涵盖所有家庭和儿童衣食住行等消费产业的持续价值。大会集合各地精英人士，精心打造品牌效应，与华埠太太竞选盛事强强联合，实现双赢。
　　</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;世界华埠太太竞选并非简单意义上的赛事活动，而是一个跨行业的，跨领域的，广泛的产业交流品台，不论对产业推广，还是品牌宣传，均为极佳的实效营销平台。竞选为全国广大女性为主题搭建平台，为相关行业充分挖掘具有规模的潜在客户群体。世界华埠太太竞选，选的不仅仅是美丽，还有更多是继承优秀的中华女德文化，以及传递爱到世界各地，让我们共同携手，参与到这一美丽的盛事来！
</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;佳丽报名热线：4006—311—883/020—22928189/020—37381879
</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;佳丽网上报名：www.mrscht.com
</p>
　　<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现场报名地址：广东省广州市白云区同泰路1498号2F</p>
<p>&nbsp;</p>
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
