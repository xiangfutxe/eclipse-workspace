<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.pojo.User" %>
<%@page import="com.utils.StringUtil" %>
<%@page import="com.utils.DateUtil" %>
<%@page import="com.utils.Pager" %>
<%@ include file="check.jsp" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sys_user");
		String psw2 = (String)request.getSession().getAttribute("psw2");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{

%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../images/splash/splash-icon.png">
<link rel="apple-touch-startup-image" href="../images/splash/splash-screen.png" 			media="screen and (max-device-width: 320px)" />  
<link rel="apple-touch-startup-image" href="../images/splash/splash-screen_402x.png" 		media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" /> 
<link rel="apple-touch-startup-image" sizes="640x1096" href="../images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="../images/splash/splash-screen-ipad-landscape" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004" href="../images/splash/splash-screen-ipad-portrait.png" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="../images/splash/splash-screen-ipad-portrait-retina.png"   media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
<link rel="apple-touch-startup-image" sizes="1496x2048" href="../images/splash/splash-screen-ipad-landscape-retina.png"   media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>

<title>服务条款</title>

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
 <script type="text/javascript" src="../scripts/jquery.cityselect.js"></script>
</head>
<body>


<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="../images/misc/index-logo.png" alt="img">
        </a>
        <p class="go-title-ch">
        	服务条款</p><p class="go-title-en">PROTOCOL
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
    <div class="container">
    <div class="container no-bottom">
      <div class="contact-form no-bottom"> 
              
               <form  action="UserServlet.do?method=user_vip_add"  name="agree" method="post">
                    <fieldset>
                             <h4 class="title"> <span> 服务条款与声明 </span> </h4>
                        
                        <div>
                          <textarea class="protocol">
鉴于乙方拟通过甲方提供的电子商务平台选购甲方产品，为规范甲、乙双方的交易行为，特制定本合同。乙方在上述电子商务平台选购甲方产品之前，须审慎、详细阅读本合同， 乙方同意并与甲方签订本合同后，方可选购甲方产品。本合同以电子合同的形式存在，乙方一经点击“我同意”按键，即视为乙方完全同意本合同内容并已与甲方签订本合同，合同自乙方点击“我同意”按键的动作完成之时起立即成立并生效，甲、乙双方的交易行为完全受本合同约束，合同具体条款如下：
一、名词定义 
1、 线下：指非电子形式的书面形式。 
2、 电子商务平台：特指网址为购物网站。 
3、 电子合同：是指以非纸质形式存在的、一经点击“我同意”按键即视为合同立即成 立并生效 。 
4、 经销商：特指甲方授予乙方的资格，乙方只有取得该项资格后，方可与甲方发生交易行为或其它法律行为。 
5、 乙方：是指为了在电子商务平台上选购甲方产品而自愿与甲方签订本合同的自然人、法人或其它法律主体。 
二、双方的权利义务
1、甲方的权利和义务
1)、甲方给予乙方经销商待遇；
2)、甲方有权统一市场零售价格，并保证产品供应。
3)、甲方负责产品的售前、售后咨询；
4)、甲方保留对产品经营方式、业绩指标、让利及分配方案的修订权和解释权；
5)、甲方有权决定乙方所订产品的发货方式（如铁路、汽运、邮局等）。
2、乙方的权利和义务
1)、乙方有权享受甲方提供的价格优惠和返利；
2)、自愿向甲方支付加盟费，积极拓展市场，努力提高销售业绩，加强自身销售技能的提升。
3)、乙方有义务保证不盗用甲方产品商标、专利、著作权进行假冒产品的生产和销售；
4)、乙方应保护甲方的产品品牌，自觉维护其声誉。严格遵循甲方规定让利销售的原则，不
得倾销甲方商品，扰乱市场价格，不得夸大产品作用误导消费者，否则引起的相关责任
由乙方承担。
三、乙方在电子商务平台从事交易的主体资格与条件 
1、乙方的经销商资格: 
乙方须向甲方递交书面申请的途径来获取经销商资格，当甲方收到乙方申请成为经销商的申请资料后，经过审核，对于符合条件的申请人，将授予其经销商资格。 
2、乙方在获得经销商资格后，将由甲方提供电子商务平台帐号，须凭个人密码方可登录上述电子商务平台。双方同意，甲方只须凭“密码相符”即可认定在该电子商务平台上实施一切行为的主体是乙方，故乙方应妥善保管个人密码，因个人密码保管不当等原因引起的法律责任完全由乙方承担。 
四、乙方订购甲方产品时，产品名称、数量、价格、规格等资讯均以电子商务平台所记录的资料为准。 
五、支付条款 
乙方须先支付货款，并承担汇款发生的相关银行费用，甲方根据乙方的订购行为发货，乙方累计订购的产品价款总额不得超过先支付的货款总额，预付款余额不足的，乙方须先行补足余额，方可订购产品。 
六、交货条款 
1.交货方式： 
1）代办托运。 
2）乙方在电子商务平台完成订购后，甲方根据乙方的订购行为，负责将乙方所订购的产品以代办托运的方式交货给乙方，自货交乙方之时起，甲方完成交货义务。 
3）甲方应在发货后向乙方或委托电子商务平台向乙方发出发货通知。 
2.收货方式: 
1）七个工作天内，产品由货运公司送达， 每笔订单仅限同一收货地址。 
2）收货人收受快递公司送达之货件时，请务必核对「签收单」记载是否吻合，如有差异请于签收单上注明清楚，切勿贸然签收，并速与客服部联络。 
3）清点货件时，请检视货箱外观，若有破损或被拆封过的情形，可要求当场清点，若产品与所附发票上之记载不符，则应拒收该产品，并立即电话通知客服部。 
4）货品如有破损、短缺溢送情况，请于收货当日内告知客服部，经查核无误后于7日内补寄产品。 
5）货品如无人收取经快递公司退回本公司，视为甲方完成交货义务。货物暂时寄存本公司，公司有权根据需要适当收取仓储费用。本公司有权先行将货品售出，再通知订货人至本公司领取，如需再运送服务需自行负担运费。如遇产品缺货时，需待货源到齐方能通知您至公司领取。（不能换货亦不退款）。 
6）如遇货源不足，将依订单先后于产品到货时寄发给订货人。 
3.货品因收货地址信息不全无法发出的，乙方应在订单生成后15日内修改地址。如未及时修改地址造成货物无法发出的，自订单生成15日后，视为甲方完成交货义务。货物暂时寄存本公司，公司有权根据需要适当收取仓储费用。 
4. 自选商品： 
1）已产生自选商品还未选货的，请及时登陆系统，填地址、电话、选择的商品等信息，提交后，公司将会在五个工作日内发货; 
2）逾期15天未选货的，视为甲方完成交货义务。公司将收取一定额度的仓储代管费，自动从电子币余额中扣除。 
3）收取规则：从自选商品的金额生成之日，超过15天未选货的，扣取未选货金额仓储代管费3%。 
七、退换货条款
1)、除非由于产品质量问题，甲方不接受乙方退换货。
2)、如果产品质量原因申请退换货，乙方应当自收到产品之日起60内填写《退换货申请表》，并将其与订货凭证原件及申请退换货的产品一并交给甲方审核。但申请退换货产品的使用量不应超过其包装物体积的三分之一。
3)、甲方检查申请退换货产品与订购单相符，并确认存在质量问题的，给于办理退换货。
八、 执行事项
乙方同意甲方拥有依其自身业务判断随时修订经销商相关的规章制度；制定及修改产品价格，增加、减少、改进或停止生产某些产品；制定修改市场激励及折扣优惠计划的权利；乙方同意甲方有权未经其批准而制定和/或修订前述合同、规章制度、产品规划或者市场计划并予以遵守。甲方应通知乙方适用于双方之间关系的相关条款的变化。乙方同意遵守所有修订的条款。乙方继续履行本合同项下的服务并继续接受甲方所支付的报酬表明乙方同意接受所有修订的条款。甲方根据业务需求自主向乙方提供产品信息、推广和促销资料及相应的业务培训的咨询。 
九、争议的解决
乙方发现本人权益受到侵害时，须书面形式向公司提出投诉之申请，但自侵权行为发生日起算六个月内未向公司提出投诉之申请，公司保留不予受理的权利。 
十、合同期限与续展：本合同有效期自乙方点击“我同意”按键的动作完成之时起至其所签订的《经销商申请书》生效第十二个月最后一日止。经销商申请书期满前三十天，经本人重消后，由公司对其评估，确定是否于重消后续约，续约标准公司将定期以公告的形式予以公布。 
十一、终止合同：除非另有规定，本合同在下述任一情况下终止： 
1.通过双方共同书面协定 。
2.如果另一方完全因其责任在合同规定的时间期限内未履行其义务，程度严重，并且在收到违约方的书面通知后七日内未能消除违约影响或采取补救措施，在此种情况下，非违约方应给另一方书面通知来终止合同。
									</textarea>
                        </div>
                        	
                        <div>
                        <input id="agree_btn" name="agreeb" class=" button button-green contactSubmitButton"
									type="submit" value="请认真查看《服务条款和声明》（3）" />
                        </div>
                    </fieldset>
                </form>       
            </div>
        </div>
    </div>
     
</div>
 <div class="decoration"></div>
 <%@ include file="footer.jsp" %>  
<div style="height:28px;"></div>
<div class="bottom-deco"></div>


</body>
 <script>
var secs = 3;
document.agree.agreeb.disabled=true;
for(var i=1;i<=secs;i++) {
	window.setTimeout("update(" + i + ")", i * 1000);
}
function update(num) {
	if(num == secs) {
		document.agree.agreeb.value =" 我 同 意 ";
		document.agree.agreeb.disabled=false;
	}
	else {
		var printnr = secs-num;
		document.agree.agreeb.value = "请认真查看<服务条款和声明> (" + printnr +")";
	}
}

function showtime(t){
	document.myform.phone.disabled=true;
	for(var i=1;i<=t;i++) {
		window.setTimeout("update_p(" + i + ","+t+")", i * 1000);
	}

}

function update_p(num,t) {
	if(num == t) {
		document.myform.phone.value =" 重新发送 ";
		document.myform.phone.disabled=false;
	}
	else {
		printnr = t-num;
		document.myform.phone.value = " (" + printnr +")秒后重新发送";
	}
}

</script>
</html>
<%
}
%>