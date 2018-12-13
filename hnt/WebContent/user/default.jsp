
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.User"%>
<%@page import="com.ssm.pojo.News"%>
<%@page import="com.ssm.utils.StringUtil"%>
<%@page import="com.ssm.utils.DateUtil" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
User user = (User)session.getAttribute("sys_user");
	if (user == null) {
		out.println("<script>");
		out.println("window.location.href='login.jsp';");
		out.println("</script>");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title> 个人中心</title>

<!-- **Favicon** -->
<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />

<!-- **CSS - stylesheets** -->
<link href="style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/nivo-slider.css" rel="stylesheet" type="text/css" media="all" />


<link href="http://img.lrjz100.com/lrkf/skin/lrkf_green1.css" rel="stylesheet" type="text/css" media="all" />

<!-- **jQuery** -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.arctext.js"></script>
<script type="text/javascript" src="js/jquery.jcarousel.min.js"></script>
<script src="js/jquery.tipTip.minified.js" type="text/javascript"></script>

<!-- **Nivo Slider** -->
<script type="text/javascript" src="js/jquery.nivo.slider.js"></script>
<script type='text/javascript' src="js/spa.custom.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="http://img.lrjz100.com/lrkf/js/lrkf.js" charset='utf-8'></script>
<script>
    $(function(){
        /*
         * 皮肤编号 lrkf_blue1 无图版，lrkf_blue2 图片版，更多皮肤敬请期待 懒人qq客服 - http://www.51xuediannao.com/qqkefu/
         * 参数配置参考正文中的参数配置说明
         
        $("#lrkfwarp").lrkf({
			root:'http://img.lrjz100.com/lrkf/0.01/',	//客服代码文件夹的路径，例如: '/qqkefu/'
            skin:'lrkf_green1',
            kfTop:'340',
            defShow:false,
            qqs:[
                {'name':'客服QQ','qq':'3170362352'}			//注意逗号是英文的逗号
           		//注意最后一个{}不要英文逗号
            ],
            tel:[
               		
                {'name':'客服电话','tel':'13181130503'},//注意逗号是英文的逗号
				{'name':'客服微信','tel':'13181130503'}
            ],
            foot:"7:30-16:30"                                  //底部在线时间，有些风格里面没有给这个预留位置

        });
*/
    });
</script>

</head>

<body class="home">

<!-- **Header** -->
<div id="header">
	<div class="container">
    
    	<!-- **Top-Menu** -->
    <%@ include file="header.jsp" %>  
	<!-- **Top-Menu - End** -->
        
    	
    </div>
</div><!-- **Header - End** -->

<!-- ** Home Slider** -->
<div id="home-slider">
	<div class="slider-container">
    
    	<div class="slider-wrapper theme-default">    
            <div id="slider" class="nivoSlider">
                <img src="images/post-images/image4.jpg" alt="" title="#htmlcaption1" />
            </div>
            <div id="htmlcaption1" class="nivo-html-caption">
                 <h2> </h2>
                <p>  </p>
            </div>
			<div id="htmlcaption2" class="nivo-html-caption">
                 <h2> </h2>
                <p>  </p>
            </div>
            <div id="htmlcaption3" class="nivo-html-caption">
                  <h2> </h2>
                <p>  </p>
            </div>    
                    
        </div>
    
    </div>
</div><!-- **Home Slider - End** -->

<!-- ** Main** -->
<div id="main">
	<!-- **Main Container** -->
	<div class="main-container">
    
    	<!-- **Content Full Width** -->
    	<div class="content content-full-width">
            <div class="post-title">
                	<h2> <a href="NewsServlet.do?method=list" title=""> 新闻中心 </a> </h2>
                    <span class="arrow"> </span>
                </div>
            
			 <div class="box-content">
			 <p>&nbsp;</p>
			 	
                </div>	
           
          <div class="hr_invisible"> </div>
            
            <!-- **Popular Procedures** -->
          <div class="post-title">
                	<h2><a> 温馨提示 </a> </h2>
					 <span class="arrow"> </span>
                </div>
            
			 <div class="box-content">
			 <p>&nbsp;</p>
			   <div class="notice">&nbsp;<span class="left">  1、为了保障您的个人账户安全，请不要在网吧及公共场所登录系统。</span></div>
				 <div class="notice">&nbsp;<span class="left"> 2、请将您的登录密码和二级密码设置为8位以上，并用字符、字母和数字的组合。</span></div>
				  <div class="notice">&nbsp;<span class="left">  3、请您在离开电脑旁时退出系统，已防止他人进入系统进行恶意操作。</span></div>
				   <div class="notice">&nbsp;<span class="left">  4、如系统有任何的操作异常，请截图保存并及时联系管理员。</span></div>
				    <div class="notice">&nbsp;<span class="left">  &nbsp;</span></div>
				     <div class="notice">&nbsp;<span class="left">  &nbsp;</span></div>
                </div>	
           
            
            <!-- **Popular Procedures - End** -->
            
            <div class="hr_invisible"> </div>
            
        </div> <!-- **Content Full Width - End** -->   	
        
       
        
    </div><!-- **Main Container - End** -->
    
  
    <!-- **Footer Bottom** -->
    <%@ include file="footer.jsp" %>  
    <!-- **Footer Bottom - End** -->

</div><!-- **Main - End**-->


<script type="text/javascript">
	$(document).ready(function(){
		$("span.arctext").each(function(){
			$(this).arctext({radius: $(this).attr('data-radius')});
		});
	});
</script>

</body>
</html>
