package com.sms.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

public class CityUtil {
	
	public static String getMACAddress(InetAddress inetAddress) throws SocketException{  
		 String macAddress = "";  
		
	        byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();  
	        //下面代码是把mac地址拼装成String  
	        StringBuilder sb = new StringBuilder();  
	        for (int i = 0; i < mac.length; i++) {  
	            if (i != 0) {  
	                sb.append("-");  
	            }  
	            //mac[i] & 0xFF 是为了把byte转化为正整数  
	            String s = Integer.toHexString(mac[i] & 0xFF);  
	            sb.append(s.length() == 1 ? 0 + s : s);  
	        }  
	        //把字符串所有小写字母改为大写成为正规的mac地址并返回  
	        macAddress = sb.toString().trim().toUpperCase();  
	        return macAddress;  
	    }  

	public static String getUrlContent(String ip) throws IOException{  
		 URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);//使用的IP库是淘宝IP库
		    HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
		  
		    InputStream res = urlConn.getInputStream();
		    Scanner scanner = new Scanner(res);
		    String urlContent = "";

		    while (scanner.hasNextLine()) {
		        urlContent += (String)scanner.nextLine();
		    }
		    
		   return urlContent;
	   }  

	public static String getAddresses(String content, String encodingString) {
		// 这里调用pconline的接口
		String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
		// 从http://whois.pconline.com.cn取得IP所在的省市区信息
		String returnStr = getResult(urlStr, content, encodingString);
		System.out.println("returnStr:"+returnStr);
		if (returnStr != null) {
			// 处理返回的省市区信息
			int startIndex = returnStr.indexOf("\"pro");
			int endIndex = returnStr.indexOf(",\"addr");
			if (startIndex > 0 && endIndex > 0 && endIndex > startIndex) {
				returnStr = returnStr.substring(startIndex, endIndex);
				returnStr = returnStr.replaceAll("\"", "").replace("pro:", "")
						.replace("city:", "").replace("region:", "");
			}
		}
		return returnStr;
	}

	/**
	 * @param urlStr
	 *            请求的地址
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 */
	private static String getResult(String urlStr, String content, String encoding) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒

			connection.setDoOutput(true);// 是否打开输出流 true|false
			connection.setDoInput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口

			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());// 打开输出流往对端服务器写数据
			out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
			out.flush();// 刷新
			out.close();// 关闭输出流
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
			// ,以BufferedReader流来读取
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();// 关闭连接
			}
		}
		return null;
	}
	
	public String getRemortIP(HttpServletRequest request)
	{
	  if (request.getHeader("x-forwarded-for") == null)
	  {
	    return request.getRemoteAddr();
	  }
	  return request.getHeader("x-forwarded-for");
	}
	  
	public String getIpAddr(HttpServletRequest request)
	{
	  String ip = request.getHeader("x-forwarded-for");
	  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
	  {
	    ip = request.getHeader("Proxy-Client-IP");
	  }
	  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
	  {
	    ip = request.getHeader("WL-Proxy-Client-IP");
	  }
	  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
	  {
	    ip = request.getRemoteAddr();
	  }
	  return ip;
	}

}
