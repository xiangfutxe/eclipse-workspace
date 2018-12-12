package com.ssm.weixin.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.weixin.pojo.SNSUserInfo;
import com.ssm.weixin.pojo.WeixinGroup;
import com.ssm.weixin.pojo.WeixinOauth2Token;
import com.ssm.weixin.pojo.WeixinQRCode;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.weixin.pojo.WeixinUserList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class AdvancedUtil {
	
	//private static Logger log = LoggerFactory.getLogger(MenuUtil.class);
	
	public static WeixinOauth2Token getOauth2AccessToken(String appId,String appSecret,String code){
		
		WeixinOauth2Token wat = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if(null!=jsonObject){
			try{
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
				wat.setUrl(requestUrl);
			}catch(Exception e){
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
			}
		}
		return wat;
	}
	
public static WeixinOauth2Token refreshOauth2AccessToken(String appId,String refreshToken){
		
		WeixinOauth2Token wat = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if(null!=jsonObject){
			try{
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			}catch(Exception e){
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
			}
		}
		return wat;
	}

@SuppressWarnings("unchecked")
public static SNSUserInfo getSNSUserInfo(String accessToken,String openId){
	String msg="";
	SNSUserInfo snsUserInfo = null;
	String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="
	+accessToken+"&openid="+openId+"lang=zh_CN";
	JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
	//msg+=requestUrl+" ;";
	if(null!=jsonObject){
		try{
			snsUserInfo = new SNSUserInfo();
			snsUserInfo.setOpenId(jsonObject.getString("openid"));
			snsUserInfo.setNickName(jsonObject.getString("nickname"));
			snsUserInfo.setSex(jsonObject.getInt("sex"));
			snsUserInfo.setCountry(jsonObject.getString("country"));
			snsUserInfo.setProvince(jsonObject.getString("province"));
			snsUserInfo.setCity(jsonObject.getString("city"));
			snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
			snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"),List.class));
		}catch(Exception e){
			snsUserInfo = null;
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			//msg +=e.getMessage();
		}
	}
	return snsUserInfo;
}



public static WeixinQRCode createTemporaryQRCode(String accessToken,int expireSeconds,int sceneId){
	WeixinQRCode weixinQRCode = null;
	String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
	String jsonMsg = "{\"expire_seconds\":%d, \"action_name\": \"QR_ SCENE\",\"action_info\": {\"scene\": {\"scene_id\": %d}}}";
	JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, expireSeconds,sceneId));
	if(null!=jsonObject){
		try{
			weixinQRCode = new WeixinQRCode();
			weixinQRCode.setTicket(jsonObject.getString("ticket"));
			weixinQRCode.setExpireSeconds(jsonObject.getInt("expire_seconds"));
			//log.info("创建临时带参二维码成功ticket:{} expire_seconds:{}");
		}catch(Exception e){
			weixinQRCode = null;
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			//log.info("创建临时带参二维码失败errorCode:{} errorMsg:{}",errorCode,errorMsg);
		}
	}
	return weixinQRCode;
	
}

/*
 * 创建永久二维码ID
 * 
 * @param accessToken 接口访问凭证
 * @param sceneId 场景ID
 * @return ticket
 * 
 */
public static String createPermanentQRCode(String accessToken,int sceneId){
	String ticket = null;
	String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
	String jsonMsg = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
	JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, sceneId));
	if(null!=jsonObject){
		try{
			ticket = jsonObject.getString("ticket");
			//log.info("创建永久带参二维码成功ticket:{} expire_seconds:{}");
		}catch(Exception e){
			ticket = e.getMessage();
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			//log.info("创建永久带参二维码失败errorCode:{} errorMsg:{}",errorCode,errorMsg);
		}
	}else {
		ticket = "二维码信息获取失败";
	}
	return ticket;
}

/*
 * 根据ticket换取二维码
 */
public static String getQRCode(String ticket,String savePath){
	String msg="";
	String filePath = null;
	//拼接请求地址
	String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
	requestUrl=requestUrl.replace("TICKET", CommonUtil.urlEncodeUTF8(ticket));
	try{
		URL url = new URL(requestUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setRequestMethod("GET");
		if(!savePath.endsWith("/")){
			savePath +="/";
		}
		//将ticket作为文件名
		filePath = savePath+ticket+".jpg";
		
		//将微信服务器返回的输入流写入文件
		BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		FileOutputStream fos = new FileOutputStream(new File(filePath));
		byte[] buf = new byte[8096];
		int size= 0;
		while((size=bis.read(buf))!=-1)
			fos.write(buf,0,size);
		fos.close();
		bis.close();
		conn.disconnect();
		//log.info("根据ticket换取二维码成功，filePath="+filePath);
	}catch(Exception e){
		filePath=null;
		msg+="根据ticket换取二维码失败：{}"+e.getMessage();
		//log.error("根据ticket换取二维码失败：{}",e);
	}
	return msg;
	
}

public static  WeixinUserInfo getUserInfo(String accessToken,String openId){
	WeixinUserInfo weixinUserInfo = null;
	String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN"
			+ "&openid=OPENID";
	requestUrl=requestUrl.replace("ACCESS_TOKEN",accessToken).replace("OPENID", openId);
	JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
	if(null!=jsonObject){
		try{
			weixinUserInfo = new WeixinUserInfo();
			weixinUserInfo.setOpenId(jsonObject.getString("openid"));
			weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
			weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
			weixinUserInfo.setNickName(jsonObject.getString("nickname"));
			weixinUserInfo.setSex(jsonObject.getInt("sex"));
			weixinUserInfo.setCountry(jsonObject.getString("country"));
			weixinUserInfo.setProvince(jsonObject.getString("province"));
			weixinUserInfo.setCity(jsonObject.getString("city"));
			weixinUserInfo.setLanguage(jsonObject.getString("language"));
			weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
		}catch(Exception e){
			if(0==weixinUserInfo.getSubscribe()){
			//	log.info("用户{}已取消关注",weixinUserInfo.getOpenId());
			}else{
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
			//log.info("获取用户信息失败 errorCode:{} errorMsg:{}",errorCode,errorMsg);
			}
		}
		}
	return weixinUserInfo;
}

@SuppressWarnings("unchecked")
public static WeixinUserList getUserList(String accessToken,String nextOpenId){
	WeixinUserList weixinUserList = null;
	if(null==nextOpenId)
		nextOpenId="";
	String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN"
			+ "&next_openid=NEXT_OPENID";
	requestUrl=requestUrl.replace("ACCESS_TOKEN",accessToken).replace("NEXT_OPENID", nextOpenId);
	JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
	if(null!=jsonObject){
		try{
			weixinUserList = new WeixinUserList();
			weixinUserList.setTotal(jsonObject.getInt("total"));
			weixinUserList.setCount(jsonObject.getInt("count"));
			weixinUserList.setNextOpenId(jsonObject.getString("next_openid"));
			JSONObject dataObject = (JSONObject) jsonObject.get("data");
			weixinUserList.setOpenIdList(JSONArray.toList(dataObject.getJSONArray("openid"),List.class));
		}catch(Exception e){
			weixinUserList = null;
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			//log.info("获取关注者列表失败 errorCode:{} errorMsg:{}",errorCode,errorMsg);
		}
		}
	return weixinUserList;
}

@SuppressWarnings("unchecked")
public static List<WeixinGroup> getGroup(String accessToken){
	List<WeixinGroup> weixinGroupList = null;
	
	String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
	requestUrl=requestUrl.replace("ACCESS_TOKEN",accessToken);
	JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
	if(null!=jsonObject){
		try{
			weixinGroupList = JSONArray.toList(jsonObject.getJSONArray("groups"),WeixinGroup.class);
		}catch(Exception e){
			weixinGroupList = null;
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			//log.info("查询分组失败 errorCode:{} errorMsg:{}",errorCode,errorMsg);
		}
		}
	return weixinGroupList;
}

}
