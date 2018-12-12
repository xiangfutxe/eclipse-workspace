package com.ssm.weixin.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Signature {
	public static HttpClient httpClient = HttpClients.createDefault();

	/**
	 * 
	 * @param appid 你的appid
	 * @param appsecret 你的appsecret
	 * @return access_token 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getAccess_token(String appid, String appsecret)
			throws ClientProtocolException, IOException {

		// 凭证获取(GET)
		String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		String requestUrl = token_url.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		// 发起GET请求获取凭证
		HttpGet get = new HttpGet(requestUrl);
		HttpResponse response = httpClient.execute(get);
		HttpEntity entity = response.getEntity();
		JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity));
		String access_token = null;
		if (jsonObject != null) {
			try {
				access_token = jsonObject.getString("access_token");
			} catch (Exception e) {
				System.out.println("获取token失败");
				e.printStackTrace();
			}
		}
		return access_token;
	}

	/**
	 * 调用微信JS接口的临时票据
	 * 
	 * @param access_token
	 *            接口访问凭证
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String getJsApiTicket(String access_token)
			throws ClientProtocolException, IOException {
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		String requestUrl = url.replace("ACCESS_TOKEN", access_token);
		HttpGet get = new HttpGet(requestUrl);
		HttpResponse response = httpClient.execute(get);
		HttpEntity entity = response.getEntity();
		JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity));
		// 发起GET请求获取凭证
		String ticket = null;
		if (null!=jsonObject) {
			try {
				ticket=jsonObject.getString("ticket");
			} catch (Exception e) {
				System.out.println("获取token失败,errmsg:"+jsonObject.getString("errmsg"));
				e.printStackTrace();
			}
		}
		return ticket;
	}
}
