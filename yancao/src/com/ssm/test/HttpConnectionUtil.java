package com.ssm.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpConnectionUtil {
		//post请求
	public static final String HTTP_POST ="POST";
	
	public static final String HTTP_GET = "GET";
	
	public static final String CHAESET_UTF_8 = "utf-8";
	
	public static final String CONTENT_TYPE_TEXT_HTML ="text/xml";
	
	public static final String CONTENT_TYPE_FORM_URL="application/x-www-form-urlencoded";
	
	public static final int SEND_REQUEST_TIME_OUT = 50000;
	
	public static final int READ_TIME_OUT = 50000;
	
	public static String requestMethod(String requestType,String urlStr,String body){
		boolean isDoInput = false;
		
		if(body!=null &&body.length()>0){
			isDoInput=true;
		}
		
		OutputStream outputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine= null;
		try{
			URL url = new URL(urlStr);
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
			
			if(isDoInput){
				httpURLConnection.setDoInput(true);
				httpURLConnection.setRequestProperty("Content-Length", String.valueOf(body.length()));
				
			}
			
			httpURLConnection.setDoInput(true);
			
			httpURLConnection.setConnectTimeout(SEND_REQUEST_TIME_OUT);
			httpURLConnection.setReadTimeout(READ_TIME_OUT);
			
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestProperty("Accept-Charset", "CHARSET_UTF_8");
			httpURLConnection.setRequestProperty("Content-Type", CONTENT_TYPE_FORM_URL);
			httpURLConnection.setRequestMethod(requestType);
			
			httpURLConnection.connect();
			
			if(isDoInput){
				outputStream = httpURLConnection.getOutputStream();
				outputStreamWriter = new OutputStreamWriter(outputStream);
				outputStreamWriter.write(body);
				outputStreamWriter.flush();
			}
			
			if(httpURLConnection.getResponseCode()>=300){
				throw new Exception(
						"HTTP Request is not success,Response code is "+httpURLConnection.getResponseCode());
			}
			
			if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
				inputStream = httpURLConnection.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				reader = new BufferedReader(inputStreamReader);
				
				while((tempLine = reader.readLine())!=null){
					resultBuffer.append(tempLine);
					resultBuffer.append("\n");
				}
			}
				
		}catch(MalformedURLException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return resultBuffer.toString();
	}
}
