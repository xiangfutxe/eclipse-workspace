package com.ssm.sms;

//接口类型：互亿无线触发短信接口，支持发送验证码短信、订单通知短信等。
// 账户注册：请通过该地址开通账户http://sms.ihuyi.com/register.html
// 注意事项：
//（1）调试期间，请用默认的模板进行测试，默认模板详见接口文档；
//（2）请使用APIID（查看APIID请登录用户中心->验证码短信->产品总览->APIID）及 APIkey来调用接口；
//（3）该代码仅供接入互亿无线短信接口参考使用，客户可根据实际需要自行编写；
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
import com.ssm.utils.SmsUtil;   

public class sendsms {
	
	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	
	public static void main(String [] args) {
		System.out.println(SmsUtil.sendSms("尊敬的商户，您好！订单12345已支付，请尽快安排配送。", "18002516301"));
	}
	
}