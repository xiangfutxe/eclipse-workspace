<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<title> 销售关系 </title>

<!-- **Favicon** -->
<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />

<!-- **CSS - stylesheets** -->
<link href="style.css" rel="stylesheet" type="text/css" media="all" />

<!-- **jQuery** -->
<script type="text/javascript" src="js/jquery.jcarousel.min.js"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<script src="js/contact.js" type="text/javascript"></script>

      <script type="text/javascript" src="../js/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="../js/calendar.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jstree.min.js"></script>
<script type="text/javascript" src="../js/json2.js"></script>
<script type="text/javascript" src="../js/json_parse.js"></script>
<link rel="stylesheet" href="../css/style.min.css" type="text/css">
</head>

<body>
<div id="header">
	<div class="container">
    	  <%@ include file="header.jsp" %>  
    </div>
</div>


<!-- ** Main** -->
<div id="main">

	<div class="container">
    	<!-- **Breadcrumb** -->
        <div class="breadcrumb">
            <a href="index.action" title=""> 首页 </a>
            <span class="arrow"> </span><a>业务管理 </a>
			  <span class="arrow"> </span>
             <span class="current-crumb">销售关系</span>
        </div>  <!-- **Breadcrumb - End** -->      	
    </div>
    
	<!-- **Main Container** -->
	<div class="main-container">
    	<!-- **Content Full Width** -->
    	<div class="content content-full-width"> 
		 <div class="shortcodes">
		<div class="notice">
									<span class="required">*</span><span class="left"> 备注：开放3层关系图</span></div>
		 </div>
				 <div class="shortcodes">
				  <div class="column one-half ">
			<div id="jstree" class="demo">
			</div>
 		</div>
					</div>
						</div>
					</div>
					
					<div class="hr_invisible"></div>
					<div class="hr_invisible"></div>
					 <div class="hr_invisible"></div>
    <div class="hr_invisible"></div>
    <div class="hr_invisible"></div>
    <div class="hr_invisible"></div>
    <div class="hr_invisible"></div>
    <div class="hr_invisible"></div>
    <div class="hr_invisible"></div>
    <div class="hr_invisible"></div>
    <div class="hr_invisible"></div>
     <!-- **Footer Bottom** -->
    <div class="footer-bottom"> 
    	<div class="main-container">        
        	<p> &copy; <a href="#" title="版权所有">版权所有</a> </p>        
        </div>
    </div><!-- **Footer Bottom - End** -->

</div><!-- **Main - End**-->

</body>
 <SCRIPT type="text/javascript">

	 $(function () {
    // 6 create an instance when the DOM is ready
    var strJSON =<%=request.getAttribute("userJson")%>;
    $('#jstree').jstree({
     'core' : {
	  'data' :strJSON
	  }
	 
	});
  });
</SCRIPT>
</html>
