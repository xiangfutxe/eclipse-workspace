<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.pojo.Product" %>
<%@page import="com.pojo.ProductType" %>
<%@page import="java.util.*" %>
<%@page import="com.utils.StringUtil" %>
<%
//WeixinUserInfo sns_user = (WeixinUserInfo)request.getSession().getAttribute("snsUserInfo");
//if(null!=sns_user){
String idstr = (String)request.getAttribute("idstr");
List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_cart_list");
int num=0;
if(plist!=null){
for(int i=0;i<plist.size();i++){
num = num+plist.get(i).getNum();
}
}
 %>
<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="js/jquery.min.js?v=01291"></script>
<script charset="utf-8" src="js/global.js?v=01291"></script>
<script charset="utf-8" src="js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="js/template.js?v=01291"></script>
		<script src="js/amazeui.min.js" type="text/javascript"></script>
		<link href="css/amazeui.min.css" type="text/css" rel="stylesheet" />
		<link href="css/style_p.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="css/bootstrap.css?v=01291">
<link rel="stylesheet" href="css/style.css?v=1?v=01291">
<link rel="stylesheet" href="css/member.css?v=01291">
<link rel="stylesheet" href="css/order3.css?v=01291">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>商品列表</title>
<script type="text/javascript">
$(document).ready(function(){
	var attr = parseInt($(".member_m_pic_1").height());
	var attr3 = parseInt($(".member_m_z_1").height());
	var h = attr - attr3;
	var clientWidth=document.body.clientWidth;
	$(".member_mp_t_img img").css("width",clientWidth*0.3);
	$(".member_mp_t_img img").css("height",clientWidth*0.3);
	
	handleUserPic();
});

function handleUserPic(){
	var th = $(".member_m_pic").outerHeight(true);
	if(th<60){
		setTimeout("handleUserPic",500);
	}else{
		$(".member_m_pic .img-circle").css("height",th*0.9);
		$(".member_m_pic .img-circle").css("width",th*0.9);
	}
	
}
</script>
<style>
body{
background:#FFFFFF;
}
</style>
</head>
<body>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div>
<header class="header">
	<div class="fix_nav">
		<div class="nav_inner">
			<a class="nav-left back-icon" href="javascript:history.back();">返回</a>
			<div class="tit">产品列表</div>
			<a class="nav-right home-icon" href="index.jsp">首页</a>
		</div>
	</div>
</header>

 <div class="content-list" id="outer">
	    	<div class="list-left" id="tab">
	    	<li <%if(StringUtil.notNull(idstr).equals("0")){%> class="current"<%} %>>
	    	<a href="ProductServlet?method=list">全部</a></li>
	    	<%
	    	Collection coll_pt = (Collection) request.getAttribute("coll_pt");
	    		if(coll_pt!=null){
									Iterator it1 = coll_pt.iterator();
									while(it1.hasNext()){
									ProductType p = (ProductType)it1.next();
									
	    	 %>
	    		<li <%if(idstr.equals(String.valueOf(p.getId()))){%> class="current"<%} %>>
	    		<a href="ProductServlet?method=list&id=<%=p.getId()%>"><%=p.getTypeName() %></a>
	    		</li>
	    	<%}} %>
	    	</div>
	    	<div class="list-right" id="content">
	    		<ul class="list-pro">
	    		<%
	    	Collection coll = (Collection) request.getAttribute("coll");
	    		if(coll!=null){
									Iterator it1 = coll.iterator();
									while(it1.hasNext()){
									Product p = (Product)it1.next();
									
	    	 %>
			    	<li>
			    	<!--  	<img src="../upload/<%=p.getImageUrl() %>" style="width:80px" class="list-pic" />-->
			    		<div class="shop-list-mid">
		                	<div><a href="ProductServlet?method=product_detail&id=<%=p.getProductId()%>"><%=p.getProductName() %></a></div>
		                	<div>规格:<%=p.getSpecification()%>;单位:<%=StringUtil.notNull(p.getUnit())%></div>
		                	<div class="am-gallery-desc">￥<%=p.getPrice()%></div>
		                </div>
		                <div class="list-cart">
					                <div class="btn-group btn-group-sm control-num">
		                    <a  href="ProductServlet?method=disDe_p&id=<%=p.getId()%>&idstr=<%=idstr%>" class="btn btn-default"><span class="glyphicon glyphicon-minus gary"></span></a>
		                    <input type="tel" class="btn gary2 Amount" readonly="readonly" value="<%=p.getNum() %>" maxlength="4">
		                    <a  href="ProductServlet?method=increate_p&id=<%=p.getId()%>&idstr=<%=idstr%>" class="btn btn-default"><span class="glyphicon glyphicon-plus gary"></span></a>
		                
			                </div>
		                </div> 
			    	</li>
			    	<%}} %>
			    	
	            </ul>
	    	</div>
	    </div>
<div class="clear"></div>

<footer class="footer">
  <div class="foot-con">
	<div class="foot-con_2">
		<a href="index.jsp">
			<i class="navIcon home"></i>
			<span class="text">店铺主页</span>
		</a>
		
		<a href="ProductServlet?method=list"   class="active">
			<i class="navIcon sort"></i>
			<span class="text">所有商品</span>		
		</a>
		<a href="shopcart.jsp" style="position:relative;">
			<i class="navIcon shop"></i>
			<span class="text">购物车<i class="num"><%=num %></i></span>
		</a>
		<a href="userhome.jsp">
			<i class="navIcon member"></i>
			<span class="text">会员主页</span>
		</a>
	</div>
	
  </div>
</footer>
</body>
<script>
//购物数量加减
$(function(){
		$('.increase').click(function(){
			var self = $(this);
			var current_num = parseInt(self.siblings('input').val());
			current_num += 1;
			if(current_num >= 0){
				self.siblings(".decrease").fadeIn();
				self.siblings(".text_box").fadeIn();
			}
			self.siblings('input').val(current_num);
			update_item(self.siblings('input').data('item-id'));
		});	
		$('.decrease').click(function(){
			var self = $(this);
			var current_num = parseInt(self.siblings('input').val());
			if(current_num > 0){
				current_num -= 1;
                if(current_num < 1){
	                self.fadeOut();
					self.siblings(".text_box").fadeOut();
                }
				self.siblings('input').val(current_num);
				update_item(self.siblings('input').data('item-id'));
			}
		})
	})
    
//删除提示信息   
    $(function() {
  $('#doc-modal-list').find('.am-icon-close').add('#doc-confirm-toggle').
    on('click', function() {
      $('#my-confirm').modal({
        relatedTarget: this,
        onConfirm: function(options) {
          var $link = $(this.relatedTarget).prev('a');
          var msg = $link.length ? '你要删除的饮品 为 ' + $link.data('id') :
            '确定了';
//        alert(msg);
        },
        onCancel: function() {
          alert('不删除');
        }
      });
    });
});

//tab切换
        $(function(){
                window.onload = function()
                {
                        var $li = $('#tab li');
                        var $ul = $('#content ul');
                        $li.click(function(){
                                var $this = $(this);
                                var $t = $this.index();
                                $li.removeClass();
                                $this.addClass('current');
                                $ul.css('display','none');
                                $ul.eq($t).css('display','block');
                        })
                }
        });
</script>
</html>
<%//}%>