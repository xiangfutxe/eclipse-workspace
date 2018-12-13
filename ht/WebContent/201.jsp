<%@ page language="java"  pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="css/table.css">
<script type="text/javascript" src="js/other.js"></script>
<script src="js/jquery.slider.js"></script>
<link rel="stylesheet" href="fonts/fontCss.css">
	<link rel="stylesheet" href="css/jquery.slider.css">
  </head>
  <script>
	window.onload = function(){
		$('.testSlider').slider({
			originType:'tuoyuan'
		});
		$('.testSlider1').slider({
			width:1280,
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
	};
</script>
 <body>
 <!--头部-->
 <jsp:include page="header.jsp" />

<div class="main_bg">
    <!--幻灯片-->
    <div class="banner1">
         <span class="testSlider1" style="display: inline-block;"></span>
    </div>
	<!--幻灯片-->  
	<div class="second">   
        <div class="scd_m clearfix">
        	<div class="scd_ml">
            	<ul class="sidenav">
            	 <a href="#"><img alt="" src="images/left_1.png"></a>
            	<a href="#"><img alt="" src="images/left_1_1.jpg" width="300px"></a>
                <li>个人资料</li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                </ul>
                </div>
                
            <div class="scd_mr">
            	<div class="about clearfix">
            	<div class="ht_table">
            	<h2>当前位置：个人信息-><a href="AboutServlet.do?method=A_201">个人资料</a></h2>
            	</div>
            	<div class="ht_table">
            	<ul>
            	 <li class="h2_1"><img alt="" src="images/h2.png"> 个人资料</li>
                	<p>&nbsp;</p>
                	<p><img src="images/about/a001.jpg" width="200" height="120" style=" float:right;">
					世界华埠太太俱乐部总部位于中国香港，中国大陆总部位于广州，其组建的渊源来自于世界华埠太太竞选活动。世界华埠太太俱乐部在加拿大、美国、瑞典、瑞士、澳大利亚、印度尼西亚、新加坡、泰国、缅甸、马来西亚、菲律宾等国家都设有联盟机构，全球有近28万会员。
　 　世界华埠太太俱乐部以“弘扬中华女德，共建幸福家园”为办会宗旨，将中华女性如“体贴善良、忠贞忍耐、爱心奉献、坚强本分、内敛包容”的传统美德予以挖掘和弘扬，同时也展现当代女性“积极，
时尚、关爱、参与、奉献”的内在世界。</p>
<p>&nbsp;</p>
　<p>世界华埠太太俱乐部的成立，得到了中国妇联等组织的高度重视，世界华埠太太俱乐部将彰显出独特的中国文化魅力，打造一张价值非凡的中国名片，向世界展示中国女性之美！让全球关心、关爱、关注女性，同时也为华埠太太们提供一个展示自我风采、分享太太心得、共创理想参与社会公益事业的平台。
</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

<p><img src="images/about/a002.jpg" width="120" height="90" style=" float:left;">世界华埠太太俱乐部是一个在亚太地区乃至全世界领先的高端女性交流与分享平台，在大力推广中国慈善公益事业和宣传中华女性传统美德与时代风貌的同时，俱乐部致力于为华人高端女性提供极具创新的产品和服务；为会员创造利益、实现会员的社会价值，最终为会员打造一个公益慈善和商业价值无缝对接、人性化和个性化完美结合的服务平台。</p>
          <p>&nbsp;</p>          
    <p>&nbsp;</p>

<p>&nbsp;</p>
<p><img src="images/about/a003.jpg" width="120" height="90" style=" float:right;">
世界华埠太太俱乐部中国大陆总部在专业团队的运营下，已逐渐发展成为亚太地区最具规模、最有影响力的高端女性俱乐部之一，目前已拥有来自广州、上海、北京、成都、深圳、香港、澳门、台北等各地会员，仅广州会员就超过5000多名。会员入会均经过严格挑选，均为中国一线城市的高端女性，会员范围包括行业领袖、商界精英、传媒公关知名人士、社会和公益慈善活动家等等。</p>
                    <p>&nbsp;</p>
     <p>&nbsp;</p>

<p><img src="images/about/a004.jpg" width="120" height="90" style=" float:left;">世界华埠太太俱乐部继续将积极向上的生活理念传达给社会，推动女性的社会责任和公益慈善的发展；不断提升自我价值，借助俱乐部的交流与分享平台实现会员之间的优势资源对接，使更多优秀太太们加盟，实现会员价值、社会价值的双向互通共赢。同时，俱乐部更与全球各地的高端女性社会团体组织紧密交流，努力促进现代女性文化发展及公益慈善事业的共同繁荣，为共建“幸福家园”做出贡献。</p>
       <p>&nbsp;</p>             
         </div> 
               </div>
            </div>
        </div>
</div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
