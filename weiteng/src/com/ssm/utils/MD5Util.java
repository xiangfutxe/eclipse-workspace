package com.ssm.utils;

import java.security.MessageDigest;

public class MD5Util {
	 public final static String MD5(String s) {
	        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
	 
	        try {
	            byte[] btInput = s.getBytes();
	            // 鑾峰緱MD5鎽樿绠楁硶鐨�MessageDigest 瀵硅�?
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");
	            // 浣跨敤鎸囧畾鐨勫瓧鑺傛洿鏂版憳瑕�?
	            mdInst.update(btInput);
	            // 鑾峰緱�?�嗘�?
	            byte[] md = mdInst.digest();
	            // 鎶婂瘑鏂囪浆鎹㈡垚鍗佸叚杩涘埗鐨勫瓧绗︿覆褰㈠紡
	            int j = md.length;
	            char str[] = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            String md5Str = new String(str); 
	            return md5Str;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
}
