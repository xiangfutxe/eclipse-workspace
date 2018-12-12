package com.ssm.weixin.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.ConnectException;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import com.ssm.pojo.Token;

import sun.net.www.protocol.https.Handler;

public class CommonUtil {
	
	//凭证获取（GET）
	public final static String token_url =  "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	
	/*
	 * 发送https请求
	 * @param requestUrl 请求地址
	 * @param outputStr 提交的数据
	 * @param requestMethod 请求方式（GET、POST）
	 * JSONObject(通过JSONOject.get(key)的方式获取JSON对象的属性值
	 * 
	 */
	public static JSONObject httpsRequest(String requestUrl,String requestMethod,String outputStr){
		JSONObject jsonObject = null;
		try{
			  URL url = new URL(null,requestUrl,new Handler());
              HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
             
              TrustManager[] tm = { new MyX509TrustManager()};
             
					SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
					sslContext.init(null, tm, new SecureRandom());
					SSLSocketFactory ssf = sslContext.getSocketFactory();
					
					conn.setSSLSocketFactory(ssf);
					
					conn.setDoOutput(true);
					conn.setDoInput(true);
					conn.setRequestMethod(requestMethod);
					
					//当outputStr不为null时，向输出流写数据
					if(null!=outputStr){
						java.io.OutputStream outputStream = conn.getOutputStream();
						//注意编码格式
						outputStream.write(outputStr.getBytes());
						outputStream.close();
					}
					//取得输入流
					InputStream inputStream = conn.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					
					//读取相应内容
					StringBuffer buffer = new StringBuffer();
					String str = null;
					while((str=bufferedReader.readLine())!=null){
						buffer.append(str);
					}
					
					//关闭 ／释放资源
					bufferedReader.close();
					inputStreamReader.close();
					inputStream.close();
					conn.disconnect();
					jsonObject = JSONObject.fromObject(buffer.toString());
		}catch(ConnectException ce){
			ce.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return  jsonObject;
	}
	
	
	
	/*
	 * 获取接口访问凭证
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	
	public static Token getToken(String appid,String appsecret){
		Token token = null;
		String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpsRequest(requestUrl,"GET",null);
		if(null!=jsonObject){
			try{
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			}catch(JSONException e){
				token = null;
				e.printStackTrace();
			}
		}
		return token;
	}
	
	public static String urlEncodeUTF8(String source){
		String result = source;
		try{
			result= java.net.URLEncoder.encode(source,"utf-8");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return result;
	}
	

}
