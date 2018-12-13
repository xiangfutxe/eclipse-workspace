<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.StringUtil"%>
<%@ page import="com.ssm.utils.DateUtil"%>
<%@page import="com.ssm.pojo.Product"%>
<%@page import="com.ssm.pojo.News"%>

<%
User user = (User)request.getSession().getAttribute("sys_user");
if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else if(user.getUserId().equals("")){
		out.println("<script>");
		out.println("alert('用户登陆信息获取失败.');parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	System.out.println(user.getUserId());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>首页／HOME</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="会员购物商城">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="Assets/css/reset.css"/>
<script type="text/javascript" src="Assets/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="Assets/js/js_z.js"></script>
<script type="text/javascript" src="Assets/js/main.js"></script>
<link rel="stylesheet" type="text/css" href="css/thems.css">
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
			width:1094,
			height:400,
			originPosition:'center'
		});
	};
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
            	<div class="box_h">温馨提示/<span>INF.</span><a href="" class="more">&nbsp;</a>
            	</div>
                <div class="box_gd">
                <ul>
              
                	<li>1、为了保障您的个人账户安全，请不要在网吧及公共场所登录系统。
                	</li>
                	<li>2、请将您的登录密码以及支付密码设置为8位以上，并用字符、字母和数字的</li>
                	<li>组合。
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
                             <%
							Collection coll_new =(Collection)request.getAttribute("coll_new");
							if(coll_new!=null){
									Iterator it = coll_new.iterator();
									int t =0;
									while(it.hasNext()){
									News ca = (News)it.next();
									t++;
									if(t<5){
							%>
                	 <li>
                    	<a href="NewsServlet?method=news_detail&id=<%=ca.getId()%>">[<%=ca.getSort() %>]&nbsp;
                    	<%if(ca.getTitle().length()<=20){ %><%=ca.getTitle()%><%}else{ %>
                    	<%=StringUtil.notNull(ca.getTitle().substring(0, 20))%>...<%} %>
                    	<em><%=StringUtil.parseToString(ca.getEntryTime(), DateUtil.yyyyMMdd)%></em></a>
                    </li>
                    <%}}} %>
                </ul>
                        <ul class="swap"></ul>
                    </div>
            </div>
           
        </div>
       <div class="box_a clearfix">
        	<!--产品展示-->
        	<div class="box_at">
            	<div class="box_h">产品介绍/<span>PRODUCT</span><a href="" class="more">&nbsp;</a>
            	</div>
                <div class="box_gd">
                <ul>
                <%
							Collection coll_pro =(Collection)request.getAttribute("coll_pro");
							if(coll_pro!=null){
									Iterator it = coll_pro.iterator();
									int t =0;
									while(it.hasNext()){
									Product p = (Product)it.next();
									if(p.getIsHide()==1){
									t++;
									if(t<13){
							%>
                	<li><img alt="" src="../upload/<%=p.getImageUrl()%>">
                	<br>
                	<h4><%if(p.getProductName().length()<=10){ %><%=p.getProductName()%><%}else{ %>
                    	<%=StringUtil.notNull(p.getProductName().substring(0, 10))%>...<%} %></h4>
                	<h4>专柜价：¥&nbsp;<%=StringUtil.decimalFormat(p.getPrice()) %><span><a href="ProductServlet?method=product_detail&id=<%=p.getProductId()%>">详情</a></span></h4>
                	</li>
                	<%}}}} %>
                	
                </ul>
                <p>&nbsp;</p>
                </div>
            </div>
    </div>
</div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
<%}%>