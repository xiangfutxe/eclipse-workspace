package com.ssm.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RSAUtil {
	public static Map<String, Object> initKey() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> map = new HashMap<>();
        map.put("public_key", rsaPublicKey);
        map.put("private_key", rsaPrivateKey);

        return map;

    }
	
	public static RSAPrivateKey getPrivateKey(Map<String, Object> map) throws Exception{
        return (RSAPrivateKey) map.get("private_key");
    }
	
	public static RSAPublicKey getPublicKey(Map<String, Object> map) throws Exception{
        return (RSAPublicKey) map.get("public_key");
    }
	
	public static byte[] encryptRSA(byte[] data, RSAPublicKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] resultBytes = cipher.doFinal(data);
        return resultBytes;
    }
	
	public static byte[] decryptRSA(byte[] src, RSAPrivateKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plainBytes = cipher.doFinal(src);
        return plainBytes;
    }
	
	 public static final String DATA = "helloworld"; 

	    public static void main(String[] args) throws Exception {

	        Map<String, Object> map = RSAUtil.initKey();
	        RSAPrivateKey rsaPrivateKey = RSAUtil.getPrivateKey(map);
	        RSAPublicKey rsaPublicKey = RSAUtil.getPublicKey(map);

	        byte[] resultBytes = RSAUtil.encryptRSA(DATA.getBytes(), rsaPublicKey);

	      System.out.println("rsaPrivateKey : " + rsaPrivateKey);

	        byte[] plainBytes = RSAUtil.decryptRSA(resultBytes, rsaPrivateKey);

	        System.out.println("RES Plain : " + new String(plainBytes));


	    }

}
