<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.pojo.User"%>
<%@ page import="com.utils.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<link rel="stylesheet" type="text/css" href="css/thems.css">
<script type="text/javascript" src="js/other.js"></script>
<script src="js/jquery.slider.js"></script>
<link rel="stylesheet" href="fonts/fontCss.css">
	<link rel="stylesheet" href="css/jquery.slider.css">
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
			width:1280,
			height:400,
			originPosition:'center'
		});
	}
</script>
  </head>
  
 <body>
 <!--头部-->
 <jsp:include page="header.jsp" />
<div class="main_bg">
    <!--幻灯片-->
    <div class="banner">
            <span class="testSlider3" style="display: inline-block;"></span>
    </div>
  
	<!--幻灯片END-->
	<div class="main clearfix">
	 
    	<div class="box_a clearfix">
        	<!--咨询服务-->
        	<div class="box_am">
            	<div class="box_h">温馨提示/<span>INF.</span>
            	</div>
                <div class="box_gd">
                <ul>
              
                	<li>1、为了保障您的个人账户安全，请不要在网吧及公共场所登录系统。
                	</li>
                	<li>2、请将您的登录密码和支付密码设置为8位以上，并用字符、字母和</li>
                	<li>数字的组合。
                	</li>
                	<li>3、请您在离开电脑旁时退出系统，已防止他人进入系统进行恶意操作。
                	</li>
                	<li>4、如系统有任何的操作异常，请截图并与客服人员联系。
                	</li>
                </ul>
                </div>
            </div>
            <!--咨询服务-->
              <!--产品系统-->
            <div class="box_am">
            	<div class="box_h">
                	新闻中心/<span>News</span><a href="" class="more">&nbsp;</a>
                </div>
                <div class="box_gd">
                            <ul>
                	 <li>
                    	<a href=""><p>[最新资讯]相约周六丨《周六下午三点半》旗袍会第七期...</p><em>2015-01-01</em></a>
                    </li>
                     <li>
                    	<a href="">[最新资讯]2016年中国旗袍模特大赛暨第三届魅力环...</p><em>2015-01-01</em></a>
                    </li>
                    <li>
                    	<a href=""><p>[最新资讯]倒计时6天|“信用建设与品牌发展”新闻发...</p><em>2015-01-01</em></a>
                    </li>
                     <li>
                    	<a href=""><p> [最新资讯]2016年中国旗袍模特大赛暨我最喜爱的旗...</p><em>2015-01-01</em></a>
                    </li>
                    <li>
                    	<a href=""><p>[最新资讯]《周六下午三点半》旗袍会省钱Party第...</p><em>2015-01-01</em></a>
                    </li>
                </ul>
                        <ul class="swap"></ul>
                    </div>
            </div>
           
        </div>
       <div class="box_a clearfix">
        	<!--产品展示-->
        	<div class="box_at">
            	<div class="box_h">产品介绍/<span>PRODUCT</span>
            	</div>
                <div class="box_gd">
                <ul>
              
                	<li><img alt="" src="upload/BJSP001.jpg">
                	<br>
                	<h4>产品名称...</h4>
                	<h4>专柜价：¥ 1000元<span><a>详情</a></span></h4>
                	</li>
                	<li><img alt="" src="upload/BJSP002.jpg">
                	<br>
                	<h4>产品名称...</h4>
                	<h4>专柜价：¥ 1000元<span><a>详情</a></span></h4>
                	</li>
                	<li><img alt="" src="upload/BJSP003.jpg">
                	<br>
                	<h4>产品名称...</h4>
                	<h4>专柜价：¥ 1000元<span><a>详情</a></span></h4>
                	</li>
                	<li><img alt="" src="upload/BJSP003.jpg">
                	<br>
                	<h4>产品名称...</h4>
                	<h4>专柜价：¥ 1000元<span><a>详情</a></span></h4>
                	</li>
                		<li><img alt="" src="upload/BJSP001.jpg">
                	<br>
                	<h4>产品名称...</h4>
                	<h4>专柜价：¥ 1000元 <span><a>详情</a></span></h4>
                	</li>
                	<li><img alt="" src="upload/BJSP002.jpg">
                	<br>
                	<h4>产品名称...</h4>
                	<h4>专柜价：¥ 1000元<span><a>详情</a></span></h4>
                	</li>
                	<li><img alt="" src="upload/BJSP003.jpg">
                	<br>
                	<h4>产品名称...</h4>
                	<h4>专柜价：¥ 1000元</h4>
                	</li>
                	<li><img alt="" src="upload/BJSP003.jpg">
                	<br>
                	<h4>产品名称...</h4>
                	<h4>专柜价：¥ 1000元<span><a>详情</a></span></h4>
                	</li>
                </ul>
                </div>
            </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
