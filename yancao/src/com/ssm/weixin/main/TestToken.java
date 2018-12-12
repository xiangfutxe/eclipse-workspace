package com.ssm.weixin.main;


import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.dao.TokenDao;
import com.ssm.dao.WeixinUserInfoDao;
import com.ssm.pojo.Token;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.weixin.utils.AdvancedUtil;
import com.ssm.weixin.utils.CommonUtil;
import com.ssm.weixin.utils.SignUtil;

public class TestToken {
	private static Logger log = LoggerFactory.getLogger(TestToken.class);

	
	public static void main(String[] args) {
	//Token token= CommonUtil.getToken(SignUtil.APPID, SignUtil.APPSECRET);
		TokenDao tokenDao = new TokenDao();
		Timestamp date = new Timestamp(new Date().getTime());
		String str = "http://thirdwx.qlogo.cn/mmopen/Q9LPheCQZa3wuxw4ndU1xA797F1FY7iaiclribibmLfNzEFmMdFzaTDiadQPUfaPjaZ3TMm6NPibzbLTicW0Krd2zHjsO7R6aE5VHico/132";
		String str1 = "gQEX8jwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyamo1Z29BNzNkZWoxMDAwMDAwM0cAAgSymuNbAwQAAAAA.jpg";
		System.out.println(tokenDao.getToken(date));
		//String token = tokenDao.getToken(date);
		//String ticket = AdvancedUtil.createPermanentQRCode(token, 23456);
		//System.out.println(ticket);
		
		WeixinUserInfoDao weixinDao = new WeixinUserInfoDao();
		System.out.println(weixinDao.get_card_id(date));
		/*
		WeixinUserInfo user = new WeixinUserInfo();
		user.setId(1);
		user.setSubscribe(0);
		user.setEndTime(date);
		System.out.println(weixinDao.update(user));
		*/
		/*
		user.setOpenId("kjddadjalkj");
		user.setNickName("kjjdajl");
		user.setSex(1);
		user.setQrId(0);
		user.setUserId("0");
		user.setSubscribe(1);
		user.setSubscribeTime("");
		user.setCountry("CN");
		user.setProvince("广东");
		user.setCity("ShenZhen");
		user.setLanguage("ch");
		user.setHeadImgUrl("jkj.jps");
		user.setEntryTime(date);
		user.setEndTime(date);
		user.setRefereeId(0);
		weixinDao.save(user);
		*/
	}

}
