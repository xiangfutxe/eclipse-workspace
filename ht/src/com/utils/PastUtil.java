package com.utils;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;

public class PastUtil {
	public static String access_token = null;
	public static String time = null;
	public static String jsapi_ticket = null;

	static {
		/**
		 * 第一步：
		 * 只要在这里传入你的appid 和 appsecret 然后计算出 access_token
		 */
		try {
			access_token = Signature.getAccess_token("appid",
					"appsecret");
			jsapi_ticket = Signature.getJsApiTicket(access_token);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static  Map<String, String> getSin(String url) {
		String uuid = UUID.randomUUID().toString();
		long time = System.currentTimeMillis() / 1000;
		String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + uuid
				+ "&timestamp=" + time + "&url=" + url;
		String signature = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(str.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
			map.put("jsapi_ticket", jsapi_ticket);
			map.put("noncestr", uuid);
			map.put("timestamp", time + "");
			map.put("url", url);
			map.put("signature", signature);
			System.out.println("signature--------:--------" + signature);
			System.out.println("jsapi_ticket--------:--------" + jsapi_ticket
					+ "\nnoncestr--------:--------" + uuid
					+ "\ntimestamp--------:--------" + time
					+ "\nurl--------:--------" + url);
			System.out.println(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return map;

	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	public static void main(String[] args) {
		
		//调用getSin方法，传入你要分享的网址
		Map<String, String> sin = PastUtil.getSin("http://cardgame.jikehd.com/game/index.html");
		String string = sin.get("jsapi_ticket");
		System.out.println(string);
	}

}
