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
        	<jsp:include page="second.jsp"/>
            <div class="scd_mr">
            	
            	<div class="about clearfix">
            	<div class="ht_table">
            	<h2>当前位置：会员注册-><a  href="UserServlet.do?method=U_702">会员须知</a></h2>
            	</div>
            	<div class="ht_table">
            	<ul>
            	 <li class="h2_1"><img alt="" src="images/h2.png">会员须知</li>
            	 <li>
            	<p> 您申请注册成为本网站会员时，必须向本网站提供真实且准确的个人或商家资料。</p>
<p>一、个人用户注册时应当遵守以下规则</p>
<p>
1、遵守中国有关的法律、法规和规范性文件。</p>
<p>
2、遵守华太俱乐部官网所有版块的网络协议、规定。</p>
<p>
3、不得以党和国家领导人或其他社会名人的真实姓名、字号、艺名、笔名注册。</p>
<p>
4、不得以国家机构或其他机构的名称或其简称注册。</p>
<p>
5、不得注册不文明、不健康名字，或包含歧视、侮辱、猥亵类词语的名字。</p>
<p>
6、不得注册易产生歧义、引起他人误解的名字。</p>
<p>
7、不得为任何非法目的而使用华太俱乐部官网网络服务系统。</p>
<p>
8、不得利用华太俱乐部官网网络服务系统进行任何可能对互联网或移动网正常运转造成不利影响的任何行为。</p>
<p>
9、不得利用华太俱乐部官网提供的网络服务上传、展示或传播任何非法的、虚假的、违反社会公德的信息资料。</p>
<p>
10、不得从事任何侵犯其他任何第三方的专利权、著作权、商标权、名誉权或其他任何合法权益的行为。</p>
<p>
如果您注册的用户名违反了任何上述规则，华太俱乐部官网有权在不事先通知您的情况下拒绝您的注册或者删除您已经注册的用户名。</p>
<p>
您注册成功后即成为本网站的会员。您注册时登记的用户名和密码是您会员身份的唯一有效识别。您有义务对您的用户名和密码进行妥善保管。您不得将您的用户名、密码给予他人使用（包括但不限于出借、转让）。华太俱乐部官网并无必须核查每一用户名使用合法性的义务，因此，如果您发现您的用户名受到他人非法使用时，应当立即通知华太俱乐部官网。由于您疏于保管或黑客行为等原因造成的用户名、密码丢失及相应的损失均由您自行承担。</p>
<p>
如您注册的用户名在任何连续365个自然日内未实际使用，则华太俱乐部官网有权删除该账号并终止为您提供相关的服务。</p>
<p>
二、商家用户注册时应当遵守以下规则</p>
<p>
商家用户在使用华太俱乐部官网展示、推广产品与服务过程中，必须遵循以下规则：</p>
<p>
1、遵守中国有关的法律、法规和规范性文件。</p>
<p>
2、遵守华太俱乐部官网所有模块的网络协议、规定。</p>
<p>
3、不得为任何非法目的而使用华太俱乐部官网网络服务系统。</p>
<p>
4、不得利用华太俱乐部官网网络服务系统进行任何可能对互联网或移动网正常运转造成不利影响的任何行为。</p>
<p>
5、不得利用华太俱乐部官网提供的网络服务上传、展示或传播任何非法的、虚假的、违反社会公德的信息资料。</p>
<p>
6、不得从事任何侵犯其他任何第三方的专利权、著作权、商标权、名誉权或其他任何合法权益的行为。</p>
<p>
7、不得利用华太俱乐部官网提供虚假产品和服务。</p>
<p>
8、不得利用华太俱乐部官网网络服务系统进行虚假商业广告行为。</p>
<p>
9、如发现任何非法使用用户名或用户名出现安全漏洞的情况，应立即通知华太俱乐部官网。</p>
<p>
10、商家用户应对以该用户名进行的所有活动和事件负全部责任。</p>
<p>
11、商家用户不得以任何形式擅自转让或授权他人使用自己在本网站的用户帐号。</p>
<p>
12、商家用户承诺自己在使用本网站实施的所有行为遵守国家法律、法规和本网站的相关规定以及各种社会公共利益或公共道德。对于任何法律后果的发生，用户将以自己的名义独立承担所有相应的法律责任。</p>
<p>
 三、服务声明</p>
<p>
1、针对华太俱乐部官网提供的网络服务，华太俱乐部官网有权随时通过各种方式（包括但不限于网页公告、电子邮件、短信提醒等）做出任何相关的声明、通知、警示。该等声明、通知、警示的内容视为本协议的一部分，如您在华太俱乐部官网做出声明、通知、警示后使用网络服务，视为您完全同意该等声明、通知、警示的内容。</p>
<p>
2、在下列情形下，华太俱乐部官网有权随时中断或终止向商家提供本协议项下的网络服务（包括收费的网络服务）而无需对您或任何第三方承担任何责任：</p>
<p>
（1） 您违反本协议中的注册规则。</p>
<p>
（2） 您违反本协议中规定的使用规则。在此等情形下，华太俱乐部官网亦有权要求您改正或自行更改、删除您发布的全部或部分商品、信息、服务资料。</p>
<p>
3、鉴于网络的不可预见性，您理解并明确同意您使用华太俱乐部官网提供的网络服务所存在的风险及由此产生的一切后果均完全由您自行承担，无论华太俱乐部官网是否就该等风险事先告知您，华太俱乐部官网无须就此对您承担任何责任。</p>
<p>
4、用户使用本网站之风险由用户个人负担，华太俱乐部官网不就下述事项向您做任何形式的担保或保证：</p>
<p>
（1）本网站提供的服务一定能满足您的要求。</p>
<p>
（2）本网站不受干扰、能够及时提供、安全可靠或免于出错。</p>
<p>
5、当用户本网站服务时，用户应明确了解并同意∶</p>
<p>
（1）因下载任何资料而导致用户电脑系统的任何损坏或资料流失，用户应负完全责任。</p>
<p>
（2）用户经由本网站取得的建议和资讯，无论其形式或表现，绝不构成本协议未明示规定的任何保证。</p>
<p>
（3）基于以下原因而造成的利润、商誉、使用、资料损失或其它无形损失，本网站不承担任何直接、间接、附带、特别、衍生性或惩罚性赔偿（即使本网站已被告知前款赔偿的可能性）：</p>
<p>
①本网站的使用或无法使用；</p>
<p>
②用户的传输或资料遭到未获授权的存取或变更；</p>
<p>
③本网站中任何第三方之声明或行为；</p>
<p>
④本网站其它相关事宜；</p>
<p>
6、根据国家法律法规、本协议的内容和本网站所掌握的事实依据，可以认定用户存在违法或违反本协议行为以及其他不当行为时，本网站有权限制用户的活动、向用户核实有关资料、发出警告通知、暂时中止、无限期中止及拒绝向该用户提供服务，有权公布用户的违法行为，并有权随时作出删除相关信息等处理而无须征得用户的同意。</p>
<p>
7、本网站提供与其它互联网上的网站或资源的链接，用户可能会因此链接至其它运营商经营的网站，但不表示本网站与这些运营商有任何关系。其它运营商经营的网站均由各经营者自行负责，不属于本网站控制及负责范围之内。对于存在或来源于此类网站或资源的任何内容、广告、产品或其它资料，本网站亦不予保证或负责。因使用或依赖任何此类网站或资源发布的或经由此类网站或资源获得的任何内容、物品或服务所产生的任何损害或损失，本网站不负任何直接或间接的责任。
</p>
<p>四、知识产权
</p>
<p>1、华太俱乐部官网提供的网络服务中可能包括文本、图片、图形、图表、软件、音频或视频资料等均受著作权、商标或其它财产所有权法律的保护。
</p>
<p>2、您只有获得相关权利人同意之后，才能依照相关权利人授权的范围使用上述资料。否则，您不得复制、发布、传播、修改、再创造上述资料，亦不得将上述资料用于其他任何商业目的。
</p>
<p>3、您保证您对通过本网站发布的内容享有知识产权或已就相关行为获得相关权利人的授权，并对上述保证事项承担全部法律责任。若因您通过本网站发布的内容侵犯第三方的权利而引起第三方对华太俱乐部官网的索赔、交涉、诉讼，您应为华太俱乐部官网抗辩，或在华太俱乐部官网的要求下合作抗辩，保证华太俱乐部官网利益不受损害，您应承担因此而产生的所有赔偿、罚款、律师费和损害赔偿等费用。
</p>
<p>五、不可抗力
</p>
<p>1、 “不可抗力”是指本协议双方不能预见、不能避免并且不能克服的客观情况，其情形包括：火灾、地震、洪水、战争、罢工、网络堵塞或瘫痪、病毒、黑客攻击等。
</p>
<p>2、遭受不可抗力事件的一方可暂行中止履行本协议项下的义务直至不可抗力事件的影响消除为止，并且无需为此而承担违约责任，但应尽最大努力克服该等事件，减轻其负面的影响。
</p>
<p>3、因不可抗力造成的损失，各方互不承担责任。
</p>
<p>六、争议的解决
</p>
<p>若就本协议发生争议或者因本网站产生任何诉诸于诉讼程序的法律争议，双方应本着友好协商的态度解决，协商不成，任何一方均可向华太俱乐部官网运营商所在地的人民法院起诉。
</p>
<p>七、其他
</p>
<p>1、本协议可由本网站随时修订，并将修订后的协议公告于本网站之上，修订后的条款内容自公告时起生效，并成为本协议的一部分。用户若在本协议修改之后，仍继续使用本网站，则视为用户接受和自愿遵守修订后的协议。本网站行使修改或中断服务时，不需对任何第三方负责。
</p>
<p>2、如您不同意对本协议相关条款所做的修改，应当立即停止使用本网站提供的网络服务。如果您在本网站对本协议相关条款做出修改后继续使用本网站提供的网络服务，则视为您完全接受本网站对本协议相关条款所做的全部修改。
</p>
<p>本规定最终解释权归华太俱乐部官网所有。</p>
            	 </li>
                 <li>&nbsp;</li>
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
