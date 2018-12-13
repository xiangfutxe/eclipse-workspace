<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%int class_val = 6;%>
<!DOCTYPE HTML>
<html>
<head>
<title>公司简介</title>
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
	<link href="css/animate.min.css" rel="stylesheet"> 

	<!--//search jQuery-->
</head>
<body>
		<%@ include file="header.jsp" %>


<!-- //header -->
<!-- banner -->
	<div class="banner-1 agileinfo-3">
		<div class="container">
			<%@ include file="banner.jsp" %>
		
		</div>
	</div>
<!-- //banner -->  
	<!-- agile --> 	
<div class="agile">
		<div class="container">
			<div class="col-md-6 agile-left wow fadeInLeft animated animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: fadeInLeft;">
			<h3>人才战略</h3>
			<div class="grid_3 grid_5">
				<div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
					<ul id="myTab" class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#expeditions" id="expeditions-tab" role="tab" data-toggle="tab" aria-controls="expeditions" aria-expanded="true">人才发展</a></li>
						<li role="presentation"><a href="#tours" role="tab" id="tours-tab" data-toggle="tab" aria-controls="tours">员工福利</a></li>
						
					</ul>
					<div id="myTabContent" class="tab-content">
						<div role="tabpanel" class="tab-pane fade in active" id="expeditions" aria-labelledby="expeditions-tab">
						<div class="tab-info">
								<p>用人唯才，人尽其才。<br>
人才是企业的灵魂，公司视人才为最大财富，始终把人才作为企业发展的创业之本、竞争之本、发展之本。 <br>
公司为员工提供一个能发挥才能的宽广舞台，展示才华的广阔空间，做到发现人才、培养人才、尊重人才、发展人才。<br>
注重人才的引进和再培养，配套全方位的技术和业务培训，增强各层次员工在工作上的实际操作能力，做到以人为本、任人唯贤、人尽其才。<br>
我们有一支年轻、高学历的员工队伍、一个和谐、勤勉的工作氛围、一种共同发展的团队精神。<br>
我们相信：智华科创是德才兼备的各类人才施展抱负的理想平台。</p>
								
								
							</div>
						</div>
						<div role="tabpanel" class="tab-pane fade" id="tours" aria-labelledby="tours-tab">
							<div class="tab-info">
								<p>法定福利（五险一金）、商业保险、年度体检、带薪休假、交通补贴 。法定福利（五险一金）：根据国家规定的五险一金种类，为员工按期缴纳。 带薪休假：国家规定的公休假、带薪年假、带薪病假、产假、哺乳假、陪产假。交通类：公司为员工发放交通补贴，其中在多个城市提供免费班车，解决交通难题。<br>
								每年充裕的旅行基金、丰富多彩的文化活动、家庭日聚会，让你享受智华Family式的温暖； 大气磅礴的运动会、亦动亦静的篮球、乒羽、足球等多种活动让你乐享生活嗨翻天！ </p>
								
								
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
			<div class="col-md-6 agile-right wow fadeInRight animated animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: fadeInRight;">
				<ul>		
					<li>
						<div class="list-img">
							<img src="images/1.jpg" class="img-responsive" alt=" ">
							<div class="textbox"></div>
						</div>
					</li>
					<li>
						<div class="list-img">
							<img src="images/2.jpg" class="img-responsive" alt=" ">
							<div class="textbox"></div>
						</div>
					</li>
					<li>
						<div class="list-img">
							<img src="images/3.jpg" class="img-responsive" alt=" ">
							<div class="textbox"></div>
						</div>
					</li>
					<li>
						<div class="list-img">
							<img src="images/4.jpg" class="img-responsive" alt=" ">
							<div class="textbox"></div>
						</div>
					</li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
<!-- //agile -->
	
	<!-- /w3ls -->
	<%@ include file="footer.jsp" %>

	<!-- //w3ls -->
</body>
</html>