<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%int class_val = 1;%>
<!DOCTYPE HTML>
<html>
<head>
<title>首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<script src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>	
<!--web-fonts-->
 <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Cabin:400,700,500,600' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Pacifico|Prata' rel='stylesheet' type='text/css'>
<!--//web-fonts-->
	<!--search jQuery-->
	<script src="js/main.js"></script>
	<!--//search jQuery-->
	<!-- animation-effect -->
<link href="css/animate.min.css" rel="stylesheet"> 
<script src="js/wow.min.js"></script>
<script>
 new WOW().init();
</script>
<!-- //animation-effect -->
</head>
<body>
	<!--start-header-->
	<%@ include file="header.jsp" %>

<!-- //header -->
<!-- banner -->
	<div class="banner agileinfo-1">
		<div class="container">
			<%@ include file="banner.jsp" %>
		
		<div class="banner-info">
			<h2>专注直销行业信息化解决方案</h2>
			<p>在创新共赢中成为生态链的引导者、构建者和协调者</p>
		</div>
		</div>
	</div>
<!-- //banner -->  



<div class="feature">
	<div class="container">
		<h3 class="tit">我们的服务</h3>
		<div class="feature-grids">
			<div class="col-md-3 feature-grid text-center wow fadeInLeft animated animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: fadeInLeft;">
				<div class="feature-grid-two">
					<a href="#" class="hi-icon hi-icon-archive glyphicon glyphicon-pencil"> </a>
					<h4>直销制度设计</h4>
					<p> 我们提供专业的直销制度设计方案以及提供制度设计的指导、建议及测评。</p>
				</div>
			</div>
			<div class="col-md-3 feature-grid text-center wow fadeInLeft animated animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: fadeInLeft;">
				<div class="feature-grid-two">
					<a href="#" class="hi-icon hi-icon-archive glyphicon glyphicon-user"> </a>
					<h4>直销软件服务</h4>
					<p> 我们有专业化的直销软件管理系统，根据客户的需求进行个性化定制。</p>
				</div>
			</div>
			<div class="col-md-3 feature-grid text-center wow fadeInRight animated animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: fadeInRight;">
				<div class="feature-grid-two">
					<a href="#" class="hi-icon hi-icon-archive glyphicon glyphicon-star"> </a>
					<h4>定制化ERP管理</h4>
					<p> 根据企业的个性化需求，我们提供定制化的ERP以及OA管理系统。</p>
				</div>
			</div>
			<div class="col-md-3 feature-grid text-center wow fadeInRight animated animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: fadeInRight;">
				<div class="feature-grid-two">
					<a href="#" class="hi-icon hi-icon-archive glyphicon glyphicon-map-marker"> </a>
					<h4>加盟连锁管理</h4>
					<p> 我们提供专业化的连锁店信息管理，通过统一采购、配送、结算等实现对连锁店的统一管理。</p>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<!-- /w3ls -->
<%@ include file="footer.jsp" %>
	<!-- //w3ls -->
</body>
</html>