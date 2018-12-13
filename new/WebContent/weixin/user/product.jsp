<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User"%>
<%@page import="com.pojo.Product"%>
<%@page import="com.utils.StringUtil"%>
<%@page import="com.utils.Pager"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) request.getSession().getAttribute("sys_user");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0 minimal-ui"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/splash/splash-icon.png">
<link rel="apple-touch-icon-precomposed" sizes="180x180" href="images/splash/splash-icon-big.png">
<link rel="apple-touch-startup-image" href="images/splash/splash-screen.png" 	media="screen and (max-device-width: 320px)" />  
<link rel="apple-touch-startup-image" href="images/splash/splash-screen@2x.png" media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" /> 
<link rel="apple-touch-startup-image" href="images/splash/splash-screen-six.png" media="(device-width: 375px)">
<link rel="apple-touch-startup-image" href="images/splash/splash-screen-six-plus.png" media="(device-width: 414px)">
<link rel="apple-touch-startup-image" sizes="640x1096" href="images/splash/splash-screen@3x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="images/splash/splash-screen-ipad-landscape" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004" href="images/splash/splash-screen-ipad-portrait.png" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="images/splash/splash-screen-ipad-portrait-retina.png"   media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
<link rel="apple-touch-startup-image" sizes="1496x2048" href="images/splash/splash-screen-ipad-landscape-retina.png"   media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>

<title>产品展示</title>

<link href="../styles/style.css"     		rel="stylesheet" type="text/css">
<link href="../styles/framework.css" 		rel="stylesheet" type="text/css">
<link href="../styles/owl.carousel.css" 	 rel="stylesheet" type="text/css">
<link href="../styles/owl.theme.css" 		rel="stylesheet" type="text/css">
<link href="../styles/swipebox.css"		 rel="stylesheet" type="text/css">
<link href="../styles/colorbox.css"		 rel="stylesheet" type="text/css">

<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jqueryui.js"></script>
<script type="text/javascript" src="../scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="../scripts/colorbox.js"></script>
<script type="text/javascript" src="../scripts/snap.js"></script>
<script type="text/javascript" src="../scripts/contact.js"></script>
<script type="text/javascript" src="../scripts/custom.js"></script>
<script type="text/javascript" src="../scripts/framework.js"></script>
<script type="text/javascript" src="../scripts/framework.launcher.js"></script>

</head>
<body>

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	产品展示</p><p class="go-title-en">PRODUCT
        </p>
         <a href="javascript:history.go(-1);" class="go-logo">返回</a>
        <a href="main.jsp" class="go-home">首页</a>
        <a href="#" class="go-menu">菜单</a>
        <a href="#" class="go-back">关闭</a>
    </div>
    <div class="decoration"></div>
    <div class="navigation">
    	<div class="corner-deco"></div>
    	<div class="navigation-wrapper">
       <%@ include file="menu.jsp" %>  
        </div>
    </div>
</div>

  <div class="content">
    <div class="container no-bottom">
    <%
 Pager pager = (Pager)request.getAttribute("pager");
						if(pager!=null){
							Collection coll = pager.getResultList();
							System.out.println("coll.size():"+coll.size());
							if(coll!=null){
									Iterator it1 = coll.iterator();
									int t=0;
									while(it1.hasNext()){
									Product p = (Product)it1.next();
							%>
      <div class="one-half-responsive">
        <p class="thumb-left no-bottom">
            <a href="ProductServlet.do?method=product_detail&id=<%=p.getProductId()%>">
                <img width="200px" src="../../upload/<%=StringUtil.notNull(p.getImageUrl())%>" alt="暂无图片">
            </a>
            <strong>
              <a href="ProductServlet.do?method=product_detail&id=<%=p.getProductId()%>">
              <%=StringUtil.notNull(p.getProductName())%>
              </a></strong>
            <br>  <strong>
                ¥<%=StringUtil.decimalFormat(p.getPrice())%> 元
            </strong>
            <em><br>
                <%=StringUtil.notNull(p.getSpecification())%> 
            </em>
            </p>
             <div class="thumb-clear"></div>
        </div>
        <%}}}%>
    </div>
</div> 
   <div class="decoration"></div>
   <div class="content"> 
   <p>
   <% if(pager!=null){ %>
   <%if(pager.getPageNo()==1) {%> 首页 | 上一页  |<%}else{%>
					<a
						href="ProductServlet.do?method=product_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=1">首页</a> | <a
						href="ProductServlet.do?method=product_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()-1%>">上一页</a>  | <%} %>
                 <%if(pager.getPageNo()!=pager.getPageCount()) {%>
					<a
						href="ProductServlet.do?method=product_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageNo()+1%>">
                    下一页</a> | <a
						href="ProductServlet.do?method=product_list&pageSizeStr=<%=pager.getPageSize()%>&pageNoStr=<%=pager.getPageCount()%>">尾页</a>
				<%}else{ %>下一页 | 尾页<%} %>&nbsp;
   <%} %>
   </p>
</div>
    <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  
<div style="height:28px;"></div>
<div class="bottom-deco"></div>

</body>
</html>
