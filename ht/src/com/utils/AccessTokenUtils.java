package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.pojo.Access_token;

public class AccessTokenUtils {
	
	private static final long MAX_TIME = 7200 * 1000;// 微信允许最长Access_token有效时间（ms）
	private static final String TAG = "weixin";// TAG
	private static final String APPID = "wxce0fd3cb55cf01ae";// APPID
	private static final String SECERT = "2be6731db899456c84d028673332e036";// 秘钥
	
	/*
	 * 该测试用例演示了如何获取access_token。
	 * access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token。
	 */
	public void getAccess_token() throws IOException {
	  // 拼接api要求的httpsurl链接
	  String urlString = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
	      + APPID + "&secret=" + SECERT;
	  // 创建一个url
	  URL reqURL = new URL(urlString);
	  // 拿取链接
	  HttpsURLConnection httpsConn = (HttpsURLConnection) reqURL
	      .openConnection();
	  // 取得该连接的输入流，以读取响应内容
	  InputStreamReader isr = new InputStreamReader(
	      httpsConn.getInputStream());
	  // 读取服务器的响应内容并显示
	  char[] chars = new char[1024];
	  String reslut = "";
	  int len;
	  while ((len = isr.read(chars)) != -1) {
	    reslut += new String(chars, 0, len);
	  }
	  isr.close();
	  /*
	   * 转化json成javabean。引入了第三方jar:GSON
	   */
	  Gson gson = new Gson();// 将获取的json转化为java中的bean
	  // 注意：Access_token access_token是一个自己创建的javabean
	  Access_token access_token = gson.fromJson(reslut,
	      new Access_token().getClass());
	  if (access_token.getAccess_token() != null) {
	    System.out.println("获取的access_token是："
	        + access_token.getAccess_token());
	    System.out.println("该access_token的有效时间是："
	        + access_token.getExpires_in() + "s");
	  } else {
	    System.out.println(TAG + "获取access_token失败，请检查");
	 
	  }
	}
	
	/*
	 * 该方法实现获取Access_token、保存并且只保存2小时Access_token。如果超过两个小时重新获取；如果没有超过两个小时，直接获取。该方法依赖
	 * ：public static String getAccessToken()；
	 * 
	 * 思路:将获取到的Access_token和当前时间存储到file里，
	 * 取出时判断当前时间和存储里面的记录的时间的时间差，如果大于MAX_TIME,重新获取，并且将获取到的存储到file替换原来的内容
	 * ，如果小于MAX_TIME，直接获取。
	 */
	public void getSavedAccess_token() throws IOException {
	  Gson gson = new Gson();
	  String mAccess_token = null;// 需要获取的Access_token；
	  File file = new File("temp_access_token.temp");// Access_token保存的位置
	  // 如果文件不存在，创建
	  if (!file.exists())
	    file.createNewFile();
	  // 如果文件大小等于0，说明第一次使用，存入Access_token
	  if (file.length() == 0) {
	    mAccess_token = getAccessToken();
	    FileOutputStream fos = new FileOutputStream(file, false);// 不允许追加
	    Access_token at = new Access_token();
	    at.setAccess_token(mAccess_token);
	    at.setExpires_in(System.currentTimeMillis() + "");
	    String json = gson.toJson(at);
	    fos.write((json).getBytes());
	    fos.close();
	  } else {
	    // 读取文件内容
	    FileInputStream fis = new FileInputStream(file);
	    byte[] b = new byte[2048];
	    int len = fis.read(b);
	    String mJsonAccess_token = new String(b, 0, len);// 读取到的文件内容
	    Access_token access_token = gson.fromJson(mJsonAccess_token,
	        new Access_token().getClass());
	    if (access_token.getExpires_in() != null) {
	      long saveTime = Long.parseLong(access_token.getExpires_in());
	      long nowTime = System.currentTimeMillis();
	      long remianTime = nowTime - saveTime;
	      // System.out.println(TAG + "时间差：" + remianTime);
	      if (remianTime < MAX_TIME) {
	        Access_token at = gson.fromJson(mJsonAccess_token,
	            new Access_token().getClass());
	        mAccess_token = at.getAccess_token();
	      } else {
	        mAccess_token = getAccessToken();
	        FileOutputStream fos = new FileOutputStream(file, false);// 不允许追加
	        Access_token at = new Access_token();
	        at.setAccess_token(mAccess_token);
	        at.setExpires_in(System.currentTimeMillis() + "");
	        String json = gson.toJson(at);
	        fos.write((json).getBytes());
	        fos.close();
	      }
	 
	    }
	  }
	 
	  System.out.println("获取到的Access_token是：" + mAccess_token);
	}
	 
	/*
	 * 获取微信服务器AccessToken。该部分和getAccess_token() 一致，不再加注释
	 */
	public static String getAccessToken() {
	  String urlString = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
	      + APPID + "&secret=" + SECERT;
	  String reslut = null;
	  try {
	    URL reqURL = new URL(urlString);
	    HttpsURLConnection httpsConn = (HttpsURLConnection) reqURL
	        .openConnection();
	    InputStreamReader isr = new InputStreamReader(
	        httpsConn.getInputStream());
	    char[] chars = new char[1024];
	    reslut = "";
	    int len;
	    while ((len = isr.read(chars)) != -1) {
	      reslut += new String(chars, 0, len);
	    }
	    isr.close();
	  } catch (IOException e) {
	 
	    e.printStackTrace();
	  }
	  Gson gson = new Gson();
	  Access_token access_token = gson.fromJson(reslut,
	      new Access_token().getClass());
	  if (access_token.getAccess_token() != null) {
	    return access_token.getAccess_token();
	  } else {
	    return null;
	  }
	}

}
