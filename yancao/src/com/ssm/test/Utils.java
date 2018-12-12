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
import java.sql.Timestamp;
import java.util.Date;

import com.ssm.pojo.OrderToken;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.StringUtil;
import com.ssm.utils.order.OrderUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Utils {

	public static final String  url="http://183.6.177.234:9561/api/";
	
	static String act = "ServerIsReady";
	
	static String order_str = "TransferOrder";

       static String urlStr = url + act;    
       
       static String urlStr_order = url + order_str;    

       static String token =OrderUtils.getToken();

       static String timestr = OrderUtils.createTimestamp();

       static String postJson = "{'ClientNo': 'HOMEY001','Token': '" + token+"','Timestamp': '" + timestr + "'}"; 

       // static String result = GetResponseData(urlStr, postJson);

	  public static String GetResponseData(String Url, String JSONData) {
		  String returnData="";
		  try {
			  URL url = new URL(urlStr);
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
          obj.element("Token", token);
          obj.element("Timestamp",timestr);
          System.out.println("timestr:"+timestr);
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
          JSONObject jsonObj = JSONObject.fromObject(sb.toString());
          returnData = (String) jsonObj.get("ReturnData");
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
	  
	  public static String SendOrders(String Url, String JSONData) {
		  String returnData="";
		  try {
			  URL url1 = new URL(urlStr_order);
          HttpURLConnection connection = (HttpURLConnection) url1
                  .openConnection();
          connection.setDoOutput(true);
          connection.setDoInput(true);
          connection.setRequestMethod("POST");
          connection.setUseCaches(false);
          connection.setInstanceFollowRedirects(true);
          //connection.setRequestProperty("connection", "Keep-Alive");
          connection.setRequestProperty("Content-Type", "application/json");
          //connection.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10 "); 

          connection.connect();
        //POST请求
          DataOutputStream out = new DataOutputStream(
                  connection.getOutputStream());
          JSONObject obj = new JSONObject();
          obj.put("ClientNo", "HOMEY001");
          obj.put("Token", token);
          obj.put("Timestamp",timestr);
          obj.put("StorageNoSrc", "HOMEY_RDC_SD");
          obj.put("StorageNoDes", "HOMEY_RDC_SD");
          obj.put("Owner", "HOMEY");
          obj.put("CusTransferNo", "JOCHI00001");
          obj.put("OrderType", "BT");
          obj.put("CusOrderType", "02");
          obj.put("CusTransferTime", "2017-01-22 10:30:55");
          obj.put("CheckTime", "2017-01-22 15:00:00");
          obj.put("CheckPerson", "张三");
          obj.put("CustomizeA", "");
          obj.put("CustomizeB", "");
          obj.put("CustomizeC", "");
          obj.put("CustomizeD", "");
          obj.put("CustomizeE", "");
          obj.put("CustomizeF", "");
          obj.put("Remark", "");
          obj.put("Province", "广东省");
          obj.put("City", "广州市");
          obj.put("County", "白云区");
          obj.put("Town", "");
          obj.put("DetailedAddress", "机场路1962号国际单位F栋309");
          obj.put("ConsigneeName", "张三");
          obj.put("Phone","15989967666");
          obj.put("Mobile","020-110110110");

          JSONArray detailArray = new JSONArray();
          JSONObject objDetail = new JSONObject();
          objDetail.put("LineIndex", 1);//行号（递增，种子为1）
          objDetail.put("LineStatus", 1);//行状态
          objDetail.put("SKU", "202948");//库存量单位，区分单品（编号）
          objDetail.put("SKUAlias","XXX-XXX口服液");//库存量单位名称
          objDetail.put("SPU", "403746");//产品单元，区分品种（编号）（可为空）
          objDetail.put("SPUAlias", "XXX口服液"); //产品单元名称（可为空）
          objDetail.put("UOM",  "EA"); //产品单位
          objDetail.put("QTY", 5);//数量
          objDetail.put("PackNo", "678654");//包装编号
          objDetail.put("PackName", "48/24/1");//包装名称
          objDetail.put("GrossWeight", 1);//毛重
          objDetail.put("GrossWeight", 1);//净重
          objDetail.put("Cubic", 1000);//体积
          objDetail.put("Price", 1089);//价格
          objDetail.put("CustomizeA", "");//预留字段（可为空）  九极：[销售编码]
          objDetail.put("CustomizeB", "");
          objDetail.put("CustomizeC", "");
          objDetail.put("CustomizeD", "");
          objDetail.put("CustomizeE", "");
          objDetail.put("CustomizeF", "");
          objDetail.put("Description", "");//描述（可为空）
          objDetail.put("Remark", "");	//备注（可为空）
          detailArray.add(objDetail);
          obj.put("OrderTransferDetails", detailArray);
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
          JSONObject jsonObj = JSONObject.fromObject(sb.toString());
          returnData = (String) jsonObj.get("ReturnData");
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
	  
	  public static String ImportAsnToWms(String Url, String JSONData) {
		  String returnData="";
		  try {
			  URL url1 = new URL(url+"ImportAsnToWms");
          HttpURLConnection connection = (HttpURLConnection) url1
                  .openConnection();
          connection.setDoOutput(true);
          connection.setDoInput(true);
          connection.setRequestMethod("POST");
          connection.setUseCaches(false);
          connection.setInstanceFollowRedirects(true);
          //connection.setRequestProperty("connection", "Keep-Alive");
          connection.setRequestProperty("Content-Type", "application/json");
          //connection.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10 "); 

          connection.connect();
        //POST请求
          DataOutputStream out = new DataOutputStream(
                  connection.getOutputStream());
          JSONObject obj = new JSONObject();
          obj.put("ClientNo", "HOMEY001");
          obj.put("StorageNo", "HOMEY_RDC_SD");
          obj.put("Owner", "HOMEY");
          obj.put("Token", token);
          obj.put("Timestamp",timestr);
          obj.put("CustomerAsnNo", "asn2017008020001");
          obj.put("Addwho", "TEST");
          obj.put("ExpectedArriveTime", "2017-08-02 18:39:08.4110174+08:00");
          obj.put("CheckTime", "2017-01-22 15:00:00");
          obj.put("CheckPerson", "张三");
          obj.put("CustomizeA", null);
          obj.put("CustomizeB", null);
          obj.put("CustomizeC", null);
          obj.put("CustomizeD", null);
          obj.put("CustomizeE", null);
          obj.put("CustomizeF", "2017-08-02T18:39:08.4110174+08:00");
          obj.put("Remark", "测试");
          JSONArray detailArray = new JSONArray();
          JSONObject objDetail = new JSONObject();
          objDetail.put("LineIndex", 1);//行号（递增，种子为1）
          objDetail.put("LineStatus", 0);//行状态
          objDetail.put("SKU", "202948");//库存量单位，区分单品（编号）
          objDetail.put("SKUAlias","kk001");//库存量单位名称
          objDetail.put("UOM",  "EA"); //产品单位
          objDetail.put("QTY", 5);//数量
          objDetail.put("PackID", "");//包装编号
          objDetail.put("LotAtt03", "");//包装名称
          objDetail.put("ReceivingLocation", "");//毛重
          objDetail.put("LotAtt04", "");//净重
          objDetail.put("LotAtt05", "");//体积
          objDetail.put("CustomizeA", "a");//预留字段（可为空）  九极：[销售编码]
          objDetail.put("CustomizeB", "b");
          objDetail.put("CustomizeC", "c");
          objDetail.put("CustomizeD", "d");
          objDetail.put("CustomizeE", "e");
          objDetail.put("CustomizeF", "2017-08-02T18:39:08.3920163+08:00");
          objDetail.put("Remark", "test");	//备注（可为空）
          detailArray.add(objDetail);
          obj.put("AsnDetails", detailArray);
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
          returnData = (String) jsonObj.get("ReturnData");
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
		 System.out.println(GetResponseData(urlStr, postJson));
		System.out.println(Utils.SendOrders(urlStr, postJson));
		 //System.out.println(Utils.ImportAsnToWms(urlStr, postJson));

	    }
}
