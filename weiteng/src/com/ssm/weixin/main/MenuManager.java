package com.ssm.weixin.main;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.weixin.pojo.menu.ClickButton;
import com.ssm.weixin.pojo.menu.ComplexButton;
import com.ssm.weixin.pojo.menu.CreateMenu;
import com.ssm.weixin.pojo.menu.Menu;
import com.ssm.weixin.pojo.menu.Button;
import com.ssm.weixin.pojo.menu.ViewButton;
import com.ssm.pojo.Token;
import com.ssm.weixin.utils.CommonUtil;
import com.ssm.weixin.utils.MenuUtil;
import com.ssm.weixin.utils.SignUtil;

public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	
	private static Menu getMenu() throws UnsupportedEncodingException{
		ViewButton btn11 = new ViewButton();
		btn11.setName("交易中心");
		btn11.setType("view");
		btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize"
				+ "?appid=wx0ece4261440ccb85&redirect_uri="
				+ "http%3A%2F%2Fwww.szyds.org%2Fht%2Fweixin%2FModularServlet.do?method=weixin_101&response_type=code"
				+ "&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect");
		ClickButton btn12 = new ClickButton();
		btn12.setName("我的名片");
		btn12.setType("click");
		btn12.setKey("11");
		
	//String url13=	"http://www.zhihuakc.com/";
	String backUrl = "http://zhihuakc.com/weiteng/wap/WeixinUserInfoServlet?method=save_weixin";
		String url13 ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="
		 +SignUtil.APPID + "&redirect_uri="+URLEncoder.encode(backUrl,"UTF-8")+ "&response_type=code" + "&scope=snsapi_userinfo" + "&state=STATE#wechat_redirect";
		ViewButton btn13 = new ViewButton();
		btn13.setName("成为会员");
		btn13.setType("view");
		btn13.setUrl(url13);

		
		
		ViewButton btn21 = new ViewButton();
		btn21.setName("代理模式");
		btn21.setType("view");
		btn21.setUrl("http://www.zhihuakc.com/");
		
		ViewButton btn22 = new ViewButton();
		btn22.setName("卡号绑定");
		btn22.setType("view");
		btn22.setUrl("http://www.zhihuakc.com/");
		
		ViewButton btn23 = new ViewButton();
		btn23.setName("最新资讯");
		btn23.setType("view");
		btn23.setUrl("http://m.vipshop.com/");
		
		ViewButton btn31 = new ViewButton();
		btn31.setName("走进威腾");
		btn31.setType("view");
		btn31.setUrl("http://www.zhihuakc.com/");
		
		ViewButton btn32 = new ViewButton();
		btn32.setName("产品手册");
		btn32.setType("view");
		btn32.setUrl("http://www.zhihuakc.com/");
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("平台商务");
		mainBtn1.setSub_button(new Button[] {btn11,btn12,btn13});
		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("会员中心");
		mainBtn2.setSub_button(new Button[] {btn21,btn22,btn23});
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("品牌视窗");
		mainBtn3.setSub_button(new Button[] {btn31,btn32});
		Menu menu  =new Menu();
		menu.setButton(new Button[]{mainBtn1,mainBtn2,mainBtn3});
		return menu;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException{
	//	Token token= CommonUtil.getToken(SignUtil.APPID, SignUtil.APPSECRET);
		CreateMenu cm = new CreateMenu();
		String tokenstr="15_0WOM5YFVY-VdZXtVjp_CKnb-4dlOFfULfHwE62E6Gw6rrffRZoQ20Juw6LMHRt0afCqG-h_m1PlWQ9QdI6lkKPkW1n_WXnp3pIq8ggIEeeBsH_mST8UxUdqFmmUty8PHvBePlJWs9u34MoLZNUWeAIAUBD";
			boolean result = MenuUtil.createMenu(cm.create(), tokenstr);
			if(result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败！");
		
	}

}
