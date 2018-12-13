<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ssm.pojo.User"%>
<%@ page import="com.ssm.utils.StringUtil"%>
<%@ page import="com.ssm.utils.DateUtil"%>
<%@page import="com.ssm.pojo.Product"%>
<%@page import="com.ssm.pojo.News"%>
<%@page import="com.utils.MD5"%>
<%
User user = (User)request.getSession().getAttribute("sys_user");
String psw2 = (String)request.getSession().getAttribute("psw2");
	if (user == null) {
		out.println("<script>");
		out.println("parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
	String auth1 = StringUtil.notNull(user.getAuthority());
	
	 if(auth1.length()<30){
		out.println("<script>");
		out.println("alert('会员权限信息获取失败，请重新登陆');parent.window.location.href='login.jsp';");
		out.println("</script>");
	}else{
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>条款及等级</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="Assets/css/reset.css"/>
<script type="text/javascript" src="Assets/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="Assets/js/js_z.js"></script>
<script type="text/javascript" src="Assets/js/main.js"></script>
<link rel="stylesheet" type="text/css" href="css/thems.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<link rel="stylesheet" type="text/css" href="css/form.css">
<script type="text/javascript" src="js/other.js"></script>
<script type="text/javascript" src="js/psw2.js"></script>
<script src="js/jquery.slider.js"></script>
<link rel="stylesheet" href="fonts/fontCss.css">
<link rel="stylesheet" href="css/jquery.slider.css">
	      <script type="text/javascript" src="js/jquery.cityselect.js"></script>
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
			height:300,
			originPosition:'center',
			source: [
							{
								src: 'img/slider1.jpg',									// 幻灯片图片地址		
								hasHref: true,												// 是否有链接
								href: '#',			// 链接地址					
								hrefTarget: '_self'											// 链接打开方式  _self, _blank, _top等
 							},
						]
		});
	};
</script>
  </head>
  
 <body>
  
	<!--幻灯片END-->
	<div class="main_bg">
	         <form  action="UserServlet?method=index"  name="agree" method="post">
	
  			 <table class ="form-table">
  			 <tr>
  			 <td width="10%"><label></label></td>
  			 <td>	<textarea class="protocol" style = "height:600px;" readonly>
“好当家” 经销商授权合同书

授 权 方：荣成市好当家海洋大健康产业有限公司 
          
    
被授权方：                                      （以下简称乙方）

甲、乙双方经过友好平等协商，在严守信誉和商业道德的基础上，依据《中华人民共和国合同法》及有关法律，本着相互信任，互惠互利的原则，共同发展“好当家”品牌系列产品，就乙方经销“好当家”品牌系列产品达成以下协议.  
1.授权条件：
1.1乙方应具有独立的法人资格；
1.2乙方应具备良好的商业信誉和较强的公共关系能力，富有开拓创新精神；能够协调处理好经营中的问题，并且有一定的经济实力，不得借贷经营。
1.3乙方应具有一定的市场经营网络、自主的销售渠道、健康类产品代理及经销经验，具备一定的营销实力。
1.4乙方应遵守甲方的《经销商行为规范管理条例》负责好当家的产品销售和品牌保护，并保证其营业执照内容、经营场地符合甲方要求。
2. 授权事项
2.1授权期限：壹年。
2.2授权时间：自乙方点击确认同意之日起算； 
2.3合同期限届满，双方明确不再续约的，本合同将自然终止，否则，本合同将自动续延；
2.4乙方欲提前终止本合同的，需提前30日向甲方提出书面申请，甲方应予允许，但乙方仍应与甲方结清因履行本合同而产生的债权债务；
2.5乙方不能正确履行本合同规定义务，或者违反营运细则、有关规章制度或其他适用于乙方的纪律的，甲方有权提前终止本合同，并立即取消乙方经销商资格。被取消资格的经销商不得再从事任何本合同项下的活动；如乙方的上述行为给甲方造成不良影响或损失的，甲方有权根据本合同或相关法律法规的规定追究乙方的法律责任。
3.2 乙方报酬
3.2.1 乙方按甲方规定，产品销售的价格不得低于甲方规定的产品最低零售价，进销差
部分为乙方所得利润。
4. 合作原则及规定 
4.1乙方作为甲方的代理商，应严格遵守甲方关于市场营销秩序的相关规定；执行甲方的营销策略及价格体系，按甲方规定市场统一的价格进行市场调配及宣传推广产品，不得以任何不正当的竞争方式侵害甲方其他经销商的合法权益。
4.2乙方在进行销售业务时，不得与诋毁甲方或甲方其他代理商，不得从事任何损害甲方权益的活动； 

5. 甲方的权利和义务 
5.1甲方的权利
5.1.1有审核、规范乙方产品广告宣传的权利。
5.1.2有处置乙方违反市场规范问题的权利。
5.1.3乙方未执行甲方价格规定的，甲方有权按照甲方的营运细则或规章制度对乙方进行处分或终止合作。
5.2甲方的义务 
5.2.1签订本合同之后，甲方向乙方提供“代理授权书”，维护乙方作为代理商的正当合法权益。
5.2.2甲方提供产品销售时所需的营销、技术和宣传资料及相关产品认证证书的复印件。 
5.2.3甲方须保证按时、按质、按量向乙方提供包装完整的合格产品；若因产品质量造成的一切损失由甲方承担（质量标准以检验报告为准）；协助乙方做好货物托运工作。
5.2.4甲方为乙方提供必要的市场指导、培训支持等相关配套服务。 
5.2.5甲方实行统一的价格管理和广告支持，配送宣传资料。
6. 乙方的权利和义务 
6.1乙方的权利
  6.1.1乙方经甲方授权，有权在一定范围内代表甲方进行产品的销售和推广。
  6.1.2 乙方有权在产品销售后按时取得报酬的权利。
  6.1.3 乙方有权接受甲方提供的技术支持及产品培训。
6.2乙方的义务 
6.2.1乙方进行经销活动时，需严格按照甲方制定的价格标准执行，乙方出售的产品价格不得违反甲方的产品价格规定。
6.2.2乙方非经甲方书面特别授权，乙方、乙方法定代表人或其配偶无权代表甲方或甲方管理人员发表任何言论，或以任何身份代表甲方发表、签署任何声明、文件、或承诺承担任何法律责任。
6.2.3自觉维护甲方及其产品形象和声誉，并协助配合甲方进行市场监督，对侵犯甲方权益的行为进行制止并及时通知甲方。 
6.2.3印制各种针对甲方的产品宣传资料前必须呈交甲方审核，并经甲方书面许可后方能发布。未经甲方书面许可，乙方不得擅自以甲方的名义发布任何形式的宣传材料，由此引发的一切法律责任应由乙方自行承担。
6.2.4乙方不得擅自将甲方授予的经销权以任何形式转让给第三方，如确系经营不善要转让时，必须书面申请，征得甲方同意。
6.2.5 乙方因从事本合同项下的授权行为所产生的税赋及根据本合同取得收益的应缴税款全部由乙方自行承担并办理缴交、完税手续。
    6.2.6乙方应负责对其人员进行必要的指导和培训，以在销售区域内进行有效地产品推销、发售、服务和支持。

7. 销售行为规定
7.1 产品价格 
7.1.1为保障乙方利益，甲方在进行产品价格体系调整时，甲方在正式调整前七个工作日内将价格调整信息书面通知乙方。对于甲方进行产品价格体系调整前（以甲方正式执行调整后的新价格体系日期为准），已确认的销售定单（或采购的行为），甲方不负责进行差价补偿。
7.2 购货流程 
7.2.1 在每次购货时，乙方以购货单的形式发给甲方。每个购货单必须列明品名、数量、和交货时间。购货单可能还会附有关于采购、运输的其他条款以及其他事先书面约定的条件。
7.3 付款条件
7.3.1甲方遵循款到发货的原则，乙方每次进货必须以现金、汇款形式支付甲方方为有效。甲方在收到全额货款后安排发货。如乙方不按本付款条件支付，甲方有权把约定的发货期顺延。
7.4 交货期、运输及运费
7.4.1甲方应在收到全额货款后的五天内安排发货，如对交货安排有特殊要求，甲乙双方可以协商解决。运费由乙方支付。
7.5 反低价管理 
7.5.1乙方应按照甲方的价格体系进行销售，防范和制止任何低价倾销行为。 
7.5.6乙方在一个季度内连续发生两次违反甲方的价格管理规定的行为，进行不正当竞争的，经查实甲方有权立即取消乙方的代理商资格，并追究其法律责任。
8.售后、质保服务  
   8.1 产品质量问题确系由甲方造成(以权威部门出具的相关检测报告为准)，由甲方负责调换货，前后因调换货物产生的费用由甲方负责；产品非质量问题不予退换货处理。
   8.2 货物在从甲方发往乙方指定地点的运输途中如发生货物丢失和损坏的情况，乙方须在到货后三天内提出异议，并提供运输部门出具的货物丢失或损坏证明，责任方按相关规定赔偿损失，甲方协助其解决赔偿问题。
9. 合同的生效、修订、解除
9.1本合同在乙方首批配货款交齐由双方签字盖章后生效。
9.2本合同的修改必须经双方协商一致，并形成书面材料，经双方签字盖章后生效。
9.4乙方如自动放弃代理权或违约被甲方终止代理权，乙方所有库存产品不得低于甲方全国统一市场建议零售价销售。否则，乙方将无条件保证赔偿由此给甲方造成的一切直接和间接损失。
10.争议解决
10.1因本合同或履行本合同产生的纠纷，双方先进行协商协商，协商不成，双方有权向甲方所在地人民法院起诉。
10.2合同的生效及解释权归属
本合同自签订之日起生效，甲方已提请乙方对合同各条款做全面、准确的理解，并应乙方要求对各条款做出说明。签约后如需对合同内容进行修改的，应由双方协商一致另行签署书面协议。合同一式两份，甲乙双方各执一份。
11、其他
11.1．为了切实保障本合同的顺利履行，乙方签署本合同的代表必须是法定代表人。
11.2．乙方签约前，已知晓包括甲方产品介绍、营运细则及规章制度等资料；签约时乙方已阅读并完全了解本合同及各项相关企业资料，乙方同意并遵守。
11.3．乙方允许甲方在各类好当家资料（包括文字和音像资料）中长期无偿使用乙方拍摄或者乙方提供的乙方及乙方相关人员的照片或者相关录音录像等资料，乙方保证甲方取得所需要的授权或许可。
11.4.因国家政策变动，甲方不能履行合同时，甲方有权无条件终止合同，乙方有权要求甲方退换货或赔偿。乙方不能履行合同时，甲方有权根据甲方损失要求乙方进行补偿。
11.5.如遇不可抗力，本合同所指不可抗力为不能预见、不能克服、不能避免的客观事件。包括地震、自然火灾、海啸、水灾、战争、罢工及其其他自然灾害，致使本合同无法继续履行的，双方互相免除因不可抗力直接导致的责任。
11.6 乙方明白并同意乙方、乙方经营者及其配偶、乙方相关人员等并非甲方的员工，不纳入甲方劳动关系管理，不享有甲方的劳动福利、保险等权益，也不构成合伙企业、合资企业或是任何形式的代理关系。
11.通讯地址及信息发布方式
11.1本合同所注明的地址，传真、手机或其它联系方式，任何一方变更应自变更之日起十日内，将变更后的地址、联系方式通知另一方。否则由此引起的相关通知无法送达所导致的后果由责任方承担。
13.合同副本和附件
13.1本合同复印无效。
13.2本合同一式贰份，甲乙双方各执壹份为凭。下述附件乃本合同不可分割的组成部分，与本合同具有同等效力。 





 
甲    方：好当家海洋大健康产业有限公司

甲方代表：                       

地    址：荣成市虎山镇沙咀子

电    话：                      

传    真：                      

签字时间：    年     月     日

乙    方：               （签章）

地    址：                    

邮    编：                   

电话/手机：                   

传    真：                  

签字时间：    年     月     日
</textarea></td>
  			 </tr>
  			 </table>
              <div class="p-li text-center">
                <input id="agree_btn" name="agreeb" class="button blue-sky"
									type="submit" value="请认真查看《授权合同》(5)" />
									
                	</div>
					</form>
					</div>
         </div> 
</div>
<jsp:include page="footer.jsp" />
</body>
  <script  type="text/javascript">
var secs = 5;
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
		document.agree.agreeb.value = "请认真查看<授权合同> (" + printnr +")";
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
<script type="text/javascript">
	$(function() {
	
	
		
		 $("#agree_btn").click(function(){
  	  if (!confirm("请确认同意授权合同的相关条款？")) {
				return false;
			}else{
		  	 	var errorstr ="";
				if(errorstr=="1"){
					return false;
				}else{
				$('#myform').attr("action","UserServlet?method=index");
		       		 $('#myform').submit();
		       		 return true;
				
		}
		 }   
				});
	  });
    
        
   </script>
</html>
<%
}}
%>