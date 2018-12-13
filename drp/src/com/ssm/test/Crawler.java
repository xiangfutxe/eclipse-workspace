package com.ssm.test;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Crawler {
	public static String GetResponseData(String urlStr) {
		  String returnData="";
		  try {
			  HashMap<String, Object> obj = new HashMap<String, Object>();
			   // String message = java.net.URLEncoder.encode("哈哈哈","utf-8");
				Random rand = new Random();
				obj.put("postid", "456011889651");
				obj.put("id", 1);
				obj.put("type", "shunfeng");
				obj.put("temp", rand.nextInt(100000000)/100000000);
				obj.put("valicode", "");
				StringBuilder queryString = new StringBuilder();
				
				for(Object key: obj.keySet()) {
					if(queryString.length() > 0) {
						queryString.append('&');
					}
					queryString.append(URLEncoder.encode((String)key, "UTF-8"));
					queryString.append('=');
					queryString.append(URLEncoder.encode((String)(obj.get(key) + ""), "UTF-8"));
				}
				
			  URL url = new URL(urlStr + '?' + queryString);
				HttpURLConnection connection = (HttpURLConnection) url
				        .openConnection();
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setRequestMethod("GET");
				connection.setUseCaches(false);
				connection.setInstanceFollowRedirects(true);
				//connection.setRequestProperty("connection", "Keep-Alive");
				connection.setRequestProperty("Content-Type", "application/json");
			    connection.connect();
			  //POST请求
			    DataOutputStream out = new DataOutputStream(
			            connection.getOutputStream());
//			    JSONObject obj = new JSONObject();
				
				out.writeBytes(queryString.toString());
				System.out.println(queryString);
				out.flush();
				out.close();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
				        connection.getInputStream()));
				String lines;
				StringBuffer sb = new StringBuffer("");
				while ((lines = reader.readLine()) != null) {
						lines = new String(lines.getBytes(), "utf-8");
				        sb.append(lines);
				    }
				    System.out.println(sb);
				    returnData = sb.toString();
				    reader.close();
				    connection.disconnect();
				} catch (MalformedURLException e) {
				    e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
				    e.printStackTrace();
				} catch (IOException e) {
				    e.printStackTrace();
				}
		  return returnData;
	  }
	
	public static void main(String[] args) {
		String url = "http://www.kuaidi100.com/query";
		String sb = Crawler.GetResponseData(url);
		  JSONObject jsonObj = JSONObject.fromObject(sb);
			System.out.println("sb:"+sb);

			 String message  = (String) jsonObj.get("message");
			 System.out.println("message:"+message);
			 if(message.equals("ok")){
				 String json  = (String) jsonObj.get("data").toString();
				 JSONArray str = JSONArray.fromObject(json);
				 if(str.size()>0){  
					  for(int i=0;i<str.size();i++){ 
						  System.out.println("["+str.getJSONObject(i).get("time")+"]:"+str.getJSONObject(i).get("context")) ; 
					  }
				 }
			 }else System.out.println(message);
	}
}
