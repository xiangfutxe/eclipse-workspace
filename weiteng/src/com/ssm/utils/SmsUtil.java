package com.ssm.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ssm.sms.util.StringUtil;

public class SmsUtil {
	
	private static  String sms_url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	
	public static String sendSms(String content,String mobile){
		String str="";
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(sms_url);

		client.getParams().setContentCharset("GBK");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");

		//int mobile_code = (int)((Math.random()*9+1)*100000);


	    NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "C13873637"), //查看用户名是登录用户中心->验证码短信->产品总览->APIID
			    // new NameValuePair("password", "wt888888"),  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
			    new NameValuePair("password",StringUtil.MD5Encode("603ee9b05e11d76417820c7be647f048")),
			    new NameValuePair("mobile", mobile), 
			    new NameValuePair("content", content),
		};
		method.setRequestBody(data);

		try {
			client.executeMethod(method);
			
			String SubmitResult =method.getResponseBodyAsString();

			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
			 if("2".equals(code)){
				str = "短信提交成功";
			}
			 System.out.println("短信提交成功");
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			str = e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			str = e.getMessage();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			str = e.getMessage();
		}	
		return str;
	}

}
