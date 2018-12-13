package com.ssm.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import net.sf.json.JSONObject;

public class Kuaidi {
	
	static String urlStr ="http://www.kuaidi100.com/query";
	
	  public static String GetResponseData() {
		  String returnData="";
		  try {
			  URL url = new URL(urlStr);
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
          JSONObject obj = new JSONObject();
         // String message = java.net.URLEncoder.encode("哈哈哈","utf-8");
          Random rand = new Random();
          obj.element("postid", "456011889651");
          obj.element("id", 1);
          obj.element("type ", "shunfeng");
          obj.element("temp ", rand.nextInt(100000000)/100000000);
          obj.element("valicode ", "");
          out.writeBytes(obj.toString());
          System.out.println(obj.toString());
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
			 System.out.println(GetResponseData());
			 //System.out.println(Utils.ImportAsnToWms(urlStr, postJson));

		    }

}
