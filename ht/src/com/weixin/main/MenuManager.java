package com.weixin.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weixin.menu.ClickButton;
import com.weixin.menu.ComplexButton;
import com.weixin.menu.Menu;
import com.utils.Constants;
import com.weixin.menu.Button;
import com.weixin.menu.ViewButton;
import com.weixin.pojo.Token;
import com.weixin.util.CommonUtil;
import com.weixin.util.MenuUtil;

public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	
	private static Menu getMenu(){
		ViewButton btn11 = new ViewButton();
		btn11.setName("旗袍总会简介");
		btn11.setType("view");
		btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize"
				+ "?appid=wx0ece4261440ccb85&redirect_uri="
				+ "http%3A%2F%2Fwww.szyds.org%2Fht%2Fweixin%2FModularServlet.do?method=weixin_101&response_type=code"
				+ "&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect");
		
		ViewButton btn12 = new ViewButton();
		btn12.setName("服务项目");
		btn12.setType("view");
		btn12.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize"
				+ "?appid=wx0ece4261440ccb85&redirect_uri="
				+ "http%3A%2F%2Fwww.szyds.org%2Fht%2Fweixin%2FModularServlet.do?method=weixin_102&response_type=code"
				+ "&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect");
		
		ViewButton btn13 = new ViewButton();
		btn13.setName("品牌赛事");
		btn13.setType("view");
		btn13.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize"
				+ "?appid=wx0ece4261440ccb85&redirect_uri="
				+ "http%3A%2F%2Fwww.szyds.org%2Fht%2Fweixin%2FModularServlet.do?method=weixin_103&response_type=code"
				+ "&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect");

		ViewButton btn14 = new ViewButton();
		btn14.setName("旗袍文化");
		btn14.setType("view");
		btn14.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize"
				+ "?appid=wx0ece4261440ccb85&redirect_uri="
				+ "http%3A%2F%2Fwww.szyds.org%2Fht%2Fweixin%2FModularServlet.do?method=weixin_104&response_type=code"
				+ "&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect");
		
		ViewButton btn21 = new ViewButton();
		btn21.setName("旗袍商城");
		btn21.setType("view");
		btn21.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0ece4261440ccb85&redirect_uri="
				+ "http%3A%2F%2Fwww.szyds.org%2Fht%2FWeixinShopServlet&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
		ViewButton btn22 = new ViewButton();
		btn22.setName("历程回顾");
		btn22.setType("view");
		btn22.setUrl("http://m.jd.com/");
		
		ViewButton btn23 = new ViewButton();
		btn23.setName("大赛报名");
		btn23.setType("view");
		btn23.setUrl("http://m.vipshop.com/");
		
		ViewButton btn31 = new ViewButton();
		btn31.setName("旗袍会员");
		btn31.setType("view");
		btn31.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0ece4261440ccb85&redirect_uri="
				+ "http%3A%2F%2Fwww.szyds.org%2Fht%2FoauthServlet&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect");
		
		ViewButton btn32 = new ViewButton();
		btn32.setName("微信群规");
		btn32.setType("view");
		btn32.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize"
				+ "?appid=wx0ece4261440ccb85&redirect_uri="
				+ "http%3A%2F%2Fwww.szyds.org%2Fht%2FoauthServlet&response_type=code"
				+ "&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("关于我们");
		mainBtn1.setSub_button(new Button[] {btn11,btn12,btn13,btn14});
		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("资讯动态");
		mainBtn2.setSub_button(new Button[] {btn21,btn22,btn23});
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("会员专区");
		mainBtn3.setSub_button(new Button[] {btn31,btn32});
		Menu menu  =new Menu();
		menu.setButton(new Button[]{mainBtn1,btn21,mainBtn3});
		return menu;
	}
	
	public static void main(String[] args){
		Token token= CommonUtil.getToken(Constants.WEIXIN_APPID, Constants.WEIXIN_APPSECRET);
		if(null!=token){
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());
			if(result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败！");
		}
	}

}
