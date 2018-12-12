package com.ssm.test;

import java.util.Date;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Calendar;

import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.DateUtil;
import com.ssm.utils.EmojiFilter;
import com.ssm.utils.StringUtil;
import com.ssm.weixin.utils.SignUtil;

public class StringTest {
	public static boolean hasEmoji(String source){
		 int len = source.length();
	        boolean isEmoji = false;
	        for (int i = 0; i < len; i++) {
	            char hs = source.charAt(i);
	            if (0xd800 <= hs && hs <= 0xdbff) {
	                if (source.length() > 1) {
	                    char ls = source.charAt(i+1);
	                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
	                    if (0x1d000 <= uc && uc <= 0x1f77f) {
	                        return true;
	                    }
	                }
	            } else {
	                // non surrogate
	                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
	                    return true;
	                } else if (0x2B05 <= hs && hs <= 0x2b07) {
	                    return true;
	                } else if (0x2934 <= hs && hs <= 0x2935) {
	                    return true;
	                } else if (0x3297 <= hs && hs <= 0x3299) {
	                    return true;
	                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c || hs == 0x2b1b || hs == 0x2b50|| hs == 0x231a ) {
	                    return true;
	                }
	                if (!isEmoji && source.length() > 1 && i < source.length() -1) {
	                    char ls = source.charAt(i+1);
	                    if (ls == 0x20e3) {
	                        return true;
	                    }
	                }
	            }
	        }
	        return  isEmoji;
		}
	//欧拉函数
	public static int euler(int n){ //返回euler(n)   
	     int res=n,a=n;  
	     for(int i=2;i*i<=a;i++){  
	         if(a%i==0){  
	             res=res/i*(i-1);//先进行除法是为了防止中间数据的溢出   
	             while(a%i==0) a/=i;  
	         }  
	     }  
	     if(a>1) res=res/a*(a-1);  
	     return res;  
	}  
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO 自动生成的方法存根
		String str = "12345678";
		ICustomService cs = new CustomService();

		//System.out.println(MD5.GetMD5Code(str));
		System.out.println(euler(1));
		System.out.println(cs.getWeekTag(new Timestamp(StringUtil.parseToDate("2018-04-09 00:00:00", DateUtil.yyyyMMddHHmmss).getTime())));
		Calendar calendar = Calendar.getInstance(); 
		Date date = new Date();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(StringUtil.parseToString(new Timestamp(calendar.getTime().getTime()), DateUtil.yyyyMMddHHmmss));
		//Date firstDayOfMonth = calendar.getTime();  
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		System.out.println(StringUtil.parseToString(new Timestamp(calendar.getTime().getTime()), DateUtil.yyyyMMddHHmmss));
		
		Double num = 100.00;
		System.out.println("num_int:"+num.intValue());
		
	String str_en = "不一样的自我😶";
	System.out.println(hasEmoji(str_en));
		
		String backUrl4 = "http://www.gzweiteng.com.cn/wap/WeixinUserInfoServlet?method=getWeixin";
		String url14 ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="
		 +SignUtil.APPID + "&redirect_uri="+URLEncoder.encode(backUrl4,"UTF-8")+ "&response_type=code" + "&scope=snsapi_userinfo" + "&state=STATE#wechat_redirect";
		
		//System.out.println(url14);
		
	}

}
