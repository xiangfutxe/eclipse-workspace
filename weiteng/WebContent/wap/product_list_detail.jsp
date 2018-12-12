<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.WeixinUserInfo"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.Constants"%>
<%@ page import="com.ssm.utils.StringUtil"%>


<%
int atag=2;	

WeixinUserInfo user  = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
User userinfo  = (User) request.getSession().getAttribute(Constants.USERINFO_SESSION);
if(null==user){
	out.println("<script>");
	out.println("window.location.href='login.jsp';");
	out.println("</script>");
}else if(StringUtil.notNull(user.getNickName()).equals("")){
	out.println("<script>");
	out.println("window.location.href='UserServlet?method=user_edit';");
	out.println("</script>");
}else{
 %>

<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="js/jquery.min.js?v=01291"></script>
<script charset="utf-8" src="js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="js/template.js?v=01291"></script>

<link rel="stylesheet" href="css/bootstrap.css?v=01291">
<link rel="stylesheet" href="css/style.css?v=1?v=01291">
<link rel="stylesheet" href="css/member.css?v=01291">
<link rel="stylesheet" href="css/order3.css?v=01291"><meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>产品分类</title>
<link rel="stylesheet" href="css/productDetail.css?v=01291">
<script charset="utf-8" src="js/TouchSlide.js?v=01291"></script>
<script charset="utf-8" src="js/prodDetail.js?v=01291"></script>

<style type="text/css">
.details_con li .tb-out-of-stock{
border: 1px dashed #bbb;
color:#bbb;
cursor: not-allowed;
}
.no-selected{
background: #ffe8e8 none repeat scroll 0 0;
border: 2px solid #be0106;
margin: -1px;
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
		<div style="max-width:768px;margin:0 auto;background:#c5a069;position: relative;">
			<a class="nav-left back-icon" href="javascript:history.back();">返回</a>
			<div class="tit">商品详细</div>
			<a class=" nav-right home-icon" href="UserServlet?method=index">首页</a>

		</div>
	</div>
</header>
<input type="hidden" id="id" value="${product.id}"/>
<input id="currSkuId" value="" type="hidden"/>
<div class="container">
    <div class="row white-bg">
        <div id="slide"> 
            <div class="hd">
                <ul>
                	<li>1</li></ul>
            </div>
            <div class="bd">
                <div class="tempWrap" style="overflow:hidden; position:relative;">
                	<ul style="width: 3072px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translateX(-768px);">
                        <li style="display: table-cell; vertical-align: middle; max-width: 768px;">
                        		<a href="#"><img style="max-width:100vw;max-height:80vw;margin:auto;" src="../upload/product/${product.imageUrl }"></a>
                        	</li>
                        </ul>
               </div>
            </div>
        </div>
    </div>
    <div class="row gary-bg">
      <form action="OrderServlet?method=shop_confirm" id="myform" method="post">
        <input type="hidden" name="id" id="id" value="${product.productId }">
         <div class="white-bg p10 details_con">
         <h1 class="item-name" id="prodName">${product.productName }</h1>
         <ul>
			<li>
				<label>商城价格：</label>
				<span class="price">¥<span class="price" id="prodCash">${product.price }</span></span>
			</li>
			<li id="choose_0" index="0" >
					<label id="propName">产品规格：</label>
					<span>${product.specification }</span>
				</li>
			
			</ul>
		</div>
		</form>
        <div id="goodsContent" class="goods-content white-bg">
			
            <div class="hd hd_fav">
                <ul>
                    <li class="on">图文详情</li>
                    <li class="">规格参数</li>
                    <li class="">评价(0)</li>
                </ul>
            </div>
			
            <div class="tempWrap" style="overflow:hidden; position:relative;">
            <div style="width: 2304px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translateX(0px);" class="bd">
                <ul style="display: table-cell; vertical-align: top; max-width: 768px;width: 100%;" class="property">
                    <div class="prop-area" style="min-height:300px;overflow: hidden;">
                    	${product.features}
                    	</div>
                </ul>
                <ul class="txt-imgs" style="display: table-cell; vertical-align: top; max-width: 768px;width: 100%;">
                	<div class="desc-area" style="padding: 0px 10px 0 10px;">
	                <li style="height:30px;">
	                	<div id="ajax_loading" style="margin: 10px  auto 15px;text-align:center;"> <img src="images/loading.gif" style="width: auto; display: block;  margin: auto;"></div>
	                </li>
	                </div>
                </ul>
                <ul style="display: table-cell; vertical-align: top; max-width: 768px;width: 100%;" class="appraise" rel="1" status="1">
                	<div style="height:30px;">
	                	<div id="ajax_loading" style="margin: 10px  auto 15px;text-align:center;"> <img src="images/loading.gif" style="width: auto; display: block;  margin: auto;"></div>
	                </div>
                     <div class="wap_page" style="display: none;" onclick="next_comments(this)"><span>下一页</span></div>
                </ul>
            </div>
            </div>
        </div>
    </div>
</div>

<%@include file ="footer.jsp" %>

<div class="clear"></div>

<script type="text/javascript">

function increate() {
	var num = Number($("#prodCount").val())+Number(1);
	$("#prodCount").attr("value",num);
}

function disDe() {
	var num = Number($("#prodCount").val())-Number(1);
	if(num>=0)
	$("#prodCount").attr("value",num);
}

function addShopCart(){
	$.ajax({
		type : "post",
		url : "OrderServlet?method=add_cart",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
		data : {//设置数据源
			num : $("#prodCount").val(),
			id:$("#id").val()
		//这里不要加","  不然会报错，而且根本不会提示错误地方
		},
		dataType : "json",//设置需要返回的数据类型
		success : function(data) {
			var d =  data;//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
			//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
			if(d.num>0){
				  $("#totalNum").text(d.num);
				   num_val.prev().val(d.num);
				}
				else{
				 }
			},
		error : function() {
			alert("系统异常，请稍后重试！");
		}//这里不要加","
		
	});
}

$(function(){
	$('.spinner').spinner({value:0, min:0, len:3, max:1000});	
	
	
});
var contextPath = '';
var currProdId = '663';
var prodName= '华族经典女装婚礼妈妈装长袖开衫两件套中长款越南奥黛连衣裙秋季';
var skuDtoList = eval('[{"amountDetail":{"cash":1380,"num":1,"price":1380,"promotionPrice":1380,"totalAmount":1380},"name":"华族经典女装婚礼妈妈装长袖开衫两件套中长款越南奥黛连衣裙秋季","price":1380,"properties":"208:636","skuId":1358,"status":1},{"amountDetail":{"cash":1380,"num":1,"price":1380,"promotionPrice":1380,"totalAmount":1380},"name":"艾吉贝2015新款多层收纳真皮单肩斜挎包女包头层牛皮斜跨小包包女","price":1380,"properties":"208:661","skuId":1359,"status":1},{"amountDetail":{"cash":1380,"num":1,"price":1380,"promotionPrice":1380,"totalAmount":1380},"name":"华族经典女装婚礼妈妈装长袖开衫两件套中长款越南奥黛连衣裙秋季","price":1380,"properties":"208:635","skuId":1360,"status":1}]');
var propValueImgList = eval('[{"imgList":["img/d15bbf94-615d-4b11-9ed8-cb9e2365f12b.jpg","img/f77cb899-e3a5-4ecd-a5dc-af5e2037935f.jpg","img/30259b99-d6ce-44a8-acb8-d6a64278bc88.jpg","img/d2cd16e6-71f0-4c42-b57e-30e417e8a28f.jpg","img/2c06d677-b2ac-459e-8dd0-1c16437333fe.jpg"],"valueId":635},{"imgList":["img/ed971163-f1bf-45e8-b63a-50cf514df3e1.jpg","img/c8117d09-63bc-4e96-adce-89dc7d2017a3.jpg","img/30259b99-d6ce-44a8-acb8-d6a64278bc88.jpg","img/d2cd16e6-71f0-4c42-b57e-30e417e8a28f.jpg","img/01de5ef6-3763-4cfe-bda8-c412266e14c7.jpg"],"valueId":661},{"imgList":["img/53698282-4ff7-4daa-bb4c-4bcaa14b00fc.jpg","img/d2cd16e6-71f0-4c42-b57e-30e417e8a28f.jpg","img/78743cc9-5d29-4289-b0a1-0d5df79e63c7.jpg","img/1c9cc5cf-4ef5-4474-b4ae-7b2f1efa88f0.jpg","img/01de5ef6-3763-4cfe-bda8-c412266e14c7.jpg"],"valueId":636}]');
var allSelected = true;
var prodLessMsg = '商品缺货';
var failedOwnerMsg = '您是商品主人, 不能购买此商品。';
var failedBasketMaxMsg = '已达到购物车最大数量, 不能购买此商品。';
var failedBasketErrorMsg = '购物车错误, 不能购买此商品。';
var photoPath = "";
var distUserName = '';

//插件：图片轮播
TouchSlide({
	slideCell:"#slide",
	titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
	mainCell:".bd ul",
	effect:"left",
	autoPlay:false,//自动播放
	autoPage:true, //自动分页
	switchLoad:"_src" //切换加载，真实图片路径为"_src"
});

var scrollTop = 0;
TouchSlide({
	slideCell:"#goodsContent",
	startFun:function(i,c){
		var prodId = $("#prodId").val();
		if(i==1){//规格参数
			var th = jQuery("#goodsContent .bd ul").eq(i);
			if(!th.hasClass('hadGoodsContent')){				
				queryParameter(th,prodId);
			}

			if($(window).scrollTop() > scrollTop){
				$(window).scrollTop(scrollTop);
			}
				
		}else if(i==2){//评价
			var th = jQuery("#goodsContent .bd ul").eq(i);

			if(!th.hasClass('hadConments')){
				queryProdComment(th,prodId);
			}

			if($(window).scrollTop() > scrollTop){
				$(window).scrollTop(scrollTop);
			}
		}else{
			if(scrollTop == 0){
				$(window).scrollTop(scrollTop);
				var hd_fav = $('.hd_fav');
				var position = hd_fav.position();
				
				scrollTop = position.top;
			}
		}
	},
});

</script>
</body>
</html>
<%}%>