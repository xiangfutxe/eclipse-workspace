package com.ssm.weixin.pojo.menu;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.ssm.weixin.utils.SignUtil;

public class CreateMenu {
	
	public  Menu create() throws UnsupportedEncodingException{
		
		String backUrl4 = "http://www.gzweiteng.com.cn/wap/WeixinUserInfoServlet?method=save_weixin";
		String url14 ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="
		 +SignUtil.APPID + "&redirect_uri="+URLEncoder.encode(backUrl4,"UTF-8")+ "&response_type=code" + "&scope=snsapi_userinfo" + "&state=STATE#wechat_redirect";
		ViewButton btn14 = new ViewButton();
		btn14.setName("成为会员");
		btn14.setType("view");
		btn14.setUrl(url14);

		String backUrl21 = "http://www.gzweiteng.com.cn/wap/WeixinUserInfoServlet?method=index";
		String url21 ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="
		 +SignUtil.APPID + "&redirect_uri="+URLEncoder.encode(backUrl21,"UTF-8")+ "&response_type=code" + "&scope=snsapi_userinfo" + "&state=STATE#wechat_redirect";
		
		ViewButton btn21 = new ViewButton();
		btn21.setName("微腾说");
		btn21.setType("view");
		btn21.setUrl(url21);
		
		ViewButton btn22 = new ViewButton();
		btn22.setName("微腾学院");
		btn22.setType("view");
		btn22.setUrl(url21);
		
		ViewButton btn23 = new ViewButton();
		btn23.setName("项目资讯");
		btn23.setType("view");
		btn23.setUrl(url21);
		
		
		String backUrl31 = "http://www.gzweiteng.com.cn/wap/WeixinUserInfoServlet?method=user_home";
		String url31 ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="
		 +SignUtil.APPID + "&redirect_uri="+URLEncoder.encode(backUrl31,"UTF-8")+ "&response_type=code" + "&scope=snsapi_userinfo" + "&state=STATE#wechat_redirect";
		
		ViewButton btn31 = new ViewButton();
		btn31.setName("个人中心");
		btn31.setType("view");
		btn31.setUrl(url31);
		
		String backUrl32 = "http://www.gzweiteng.com.cn/wap/WeixinUserInfoServlet?method=user_team";
		String url32 ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="
		 +SignUtil.APPID + "&redirect_uri="+URLEncoder.encode(backUrl32,"UTF-8")+ "&response_type=code" + "&scope=snsapi_userinfo" + "&state=STATE#wechat_redirect";
		ViewButton btn32 = new ViewButton();
		btn32.setName("创客中心");
		btn32.setType("view");
		btn32.setUrl(url32);
		
		String backUrl33 = "http://www.gzweiteng.com.cn/wap/WeixinUserInfoServlet?method=user_code";
		String url33 ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="
		 +SignUtil.APPID + "&redirect_uri="+URLEncoder.encode(backUrl33,"UTF-8")+ "&response_type=code" + "&scope=snsapi_userinfo" + "&state=STATE#wechat_redirect";
		
		ViewButton btn33 = new ViewButton();
		btn33.setName("邀请海报");
		btn33.setType("view");
		btn33.setUrl(url33);
		
		ViewButton btn34 = new ViewButton();
		btn34.setName("联系客服");
		btn34.setType("view");
		btn34.setUrl(url31);
		
		String backUrl1 = "http://www.gzweiteng.com.cn/wap/WeixinUserInfoServlet?method=index";
		String url11 ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="
		 +SignUtil.APPID + "&redirect_uri="+URLEncoder.encode(backUrl1,"UTF-8")+ "&response_type=code" + "&scope=snsapi_userinfo" + "&state=STATE#wechat_redirect";
	
		ViewButton mainBtn1 = new ViewButton();
		mainBtn1.setName("会员商城");
		mainBtn1.setType("view");
		mainBtn1.setUrl(url11);
		
		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("关于我们");
		mainBtn2.setSub_button(new Button[] {btn21,btn22,btn23});
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("个人中心");
		mainBtn3.setSub_button(new Button[] {btn31,btn32,btn33,btn34,btn14});
		Menu menu  =new Menu();
		menu.setButton(new Button[]{mainBtn1,mainBtn2,mainBtn3});
		return menu;
	}

	public static Menu getMenu() {
		// TODO Auto-generated method stub
		return null;
	}
}
