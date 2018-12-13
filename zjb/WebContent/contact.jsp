<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%int class_val = 7;%>

<!DOCTYPE HTML>
<html>
<head>
<title>联系我们</title>
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
<!--//web-fonts-->
	<!--search jQuery-->
	<script src="js/main.js"></script>
	<!--//search jQuery-->
</head>
<body>
	<!--start-header-->
			<%@ include file="header.jsp" %>

<!-- //header -->
<!-- banner -->
	<div class="banner-1 agileinfo-5">
		<div class="container">
						<%@ include file="banner.jsp" %>

		</div>
	</div>
<!-- //banner -->  
	 <!--contact-->
  <div class="contact w3-5">
		<div class="container">
		<h2 class="tit">联系我们</h2>
			 <div class="contact-inner">
				  <div class="col-md-4 contact-text">
				      <h3 class="tittle con">公司信息</h3>
					   <div class="contact-text-inner">
					          <address>
								  深圳市宝安区新安街道前进一路<br>
								  265号泰华大厦2座25F<br>
								  <abbr title="Phone">固定电话:</abbr> (0755) ********
								</address>
								  <p>联系电话 : +086-18002516301</p>
								  <p>电子邮箱 : <a href="zhihuakc@163.com">zhihuakc@163.com</a></p>
								</div>
                      </div>
				  </div>
				  <div class="col-md-8 con-form agileits-5">
				     <form>
						<p class="your-para">您的名称:</p>
						 <input type="text" placeholder="" required="">
						<p class="your-para">您的邮箱:</p>
						 <input type="text" placeholder="" required="">
						<p class="your-para">您的留言:</p>
						<textarea placeholder="" required=""></textarea>

						<div class="send">
							<input type="submit" value="提交发送" >
						</div>
					</form>

				  </div>
				  <div class="clearfix"></div>
			 </div>
			<div class="map wow fadeInUp"  data-wow-duration="1s" data-wow-delay=".3s">
            <iframe src="https://j.map.baidu.com/2hCNP"></iframe>
			 </div>
		</div>
	</div>

	<!--//contact-->
<!-- /w3ls -->
		<%@ include file="footer.jsp" %>

	<!-- //w3ls -->
</body>
</html>