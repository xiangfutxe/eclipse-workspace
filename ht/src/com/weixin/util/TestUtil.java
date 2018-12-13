package com.weixin.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.db.DBConn;
import com.service.IWeixinService;
import com.service.WeixinService;
import com.utils.Constants;
import com.utils.DateUtil;
import com.utils.StringUtil;
import com.weixin.pojo.WeixinGroup;
import com.weixin.pojo.WeixinQRCode;
import com.weixin.pojo.WeixinUserInfo;
import com.weixin.pojo.WeixinUserList;

public class TestUtil {

	public static void main(String[] args) {
		String accessToken = CommonUtil.getToken(Constants.WEIXIN_APPID, Constants.WEIXIN_APPSECRET).getAccessToken();
		//System.out.println("accessToken:"+accessToken);
		//WeixinQRCode weixinQRCode = AdvancedUtil.createTemporaryQRCode(accessToken,900,111111);
		//System.out.println(weixinQRCode.getTicket());
		//System.out.println(weixinQRCode.getExpireSeconds());
		/*
		String ticket = AdvancedUtil.createPermanentQRCode(accessToken, 100000);
		System.out.println(ticket);
		String savePath="/usr/local/tomcat/webapps/ROOT/upload/qr";
		AdvancedUtil.getQRCode(ticket, savePath);
		WeixinUserList weixinUserList = AdvancedUtil.getUserList(accessToken,"");
		System.out.println("总关注用户数："+weixinUserList.getTotal());
		System.out.println("本次获取用户数："+weixinUserList.getCount());
		System.out.println("OpenId 列表："+weixinUserList.getOpenIdList().toString());
		System.out.println("next_openid:"+weixinUserList.getNextOpenId());
		
		WeixinUserInfo weixinUserInfo = AdvancedUtil.getUserInfo(accessToken, 
				"oc0ABxDAux_wFe0jsYmQXSkUWTLA");
		System.out.println("头像："+weixinUserInfo.getHeadImgUrl());
		System.out.println("昵称："+weixinUserInfo.getNickName());
		System.out.println("性别："+weixinUserInfo.getSex());
		System.out.println("关注时间："+StringUtil.parseToString(new Timestamp(Integer.valueOf(
				weixinUserInfo.getSubscribeTime())),DateUtil.yyyyMMddHHmmss));
		System.out.println("关注状态："+weixinUserInfo.getSubscribe());
		System.out.println("语言："+weixinUserInfo.getLanguage());
		
		List<WeixinGroup> groupList = AdvancedUtil.getGroup(accessToken);
		for(WeixinGroup group:groupList){
			System.out.println(String.format("ID: %d 名称：　%s 用户数：%d",
					group.getId(),group.getName(),group.getCount()));
		}
*/
		Date date1 = new Date();
		long  time =1480825835*1000L;
		Timestamp date = new Timestamp(time);
		System.out.println(date1.getTime());
		System.out.println(StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss));
	}

}
