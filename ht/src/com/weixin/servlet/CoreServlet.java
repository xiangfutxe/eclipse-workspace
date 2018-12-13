package com.weixin.servlet;

import java.io.IOException;  
import java.io.PrintWriter;  

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import com.weixin.service.CoreService;
import com.weixin.util.SignUtil;
  
/** 
 * 微信servlet 
 *  
 * @author Administrator 
 *  
 */  
public class CoreServlet extends HttpServlet {  
  
    private static final long serialVersionUID = 7534232612712558319L;  
  
    public CoreServlet() {  
        super();  
    }  
  
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        try {  
            // 微信加密签名  
            String signature = request.getParameter("signature");  
            // 时间戳  
            String timestamp = request.getParameter("timestamp");  
            // 随机数  
            String nonce = request.getParameter("nonce");  
            // 随机字符串  
            String echostr = request.getParameter("echostr");  
  
            PrintWriter out = response.getWriter();  
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
                out.print(echostr);  
            }  
            out.close();  
            out = null;  
  
        } catch (Exception e) {  
        }  
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	
    	  // 微信加密签名  
        String signature = request.getParameter("signature");  
        // 时间戳  
        String timestamp = request.getParameter("timestamp");  
        // 随机数  
        String nonce = request.getParameter("nonce");  
        
        PrintWriter out = response.getWriter();
        
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
        	String respXML= CoreService.processRequest(request);
            out.print(respXML);  
        }  
        out.close();  
        out = null;  
    }  
}  

