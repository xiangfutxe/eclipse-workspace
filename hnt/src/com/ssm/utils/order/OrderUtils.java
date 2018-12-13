package com.ssm.utils.order;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JSONObject;

import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.StringUtil;

public class OrderUtils {
	
	public static final String  ADD_URL="http://183.6.177.234:9561/api/GetToken";
	
	public static String createTimestamp(){
		  Timestamp date = new Timestamp(new Date().getTime());
          String timestr = StringUtil.parseToString(date, DateUtil.yyyymdh);
          return MD5.GetMD5CodeToUpper(timestr);
	}
	
	public static String getToken(){
		 String token="";
		  try {
	            URL url = new URL(ADD_URL);
	            HttpURLConnection connection = (HttpURLConnection) url
	                    .openConnection();
	            connection.setDoOutput(true);
	            connection.setDoInput(true);
	            connection.setRequestMethod("POST");
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
	            obj.element("ClientNo", "HOMEY001");
	            obj.element("AppSecret", "123456");
	            obj.element("Timestamp",createTimestamp());
	            out.writeBytes(obj.toString());
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
	            JSONObject jsonObj = JSONObject.fromObject(sb.toString());
	             token = (String) jsonObj.get("ReturnData");
	            reader.close();
	            connection.disconnect();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		  return token;
		}

}
