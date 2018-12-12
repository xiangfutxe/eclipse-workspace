package com.ssm.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssm.weixin.pojo.message.InputMessage;
import com.ssm.weixin.pojo.MsgType;
import com.ssm.weixin.pojo.message.OutputMessage;
import com.ssm.weixin.pojo.message.TextOutputMessage;
import com.ssm.weixin.service.CoreService;
import com.ssm.weixin.pojo.message.Article;
import com.ssm.weixin.utils.SignUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class WeixinServlet extends HttpServlet {
	 private static final long serialVersionUID = 7534232612712558319L;  
	 
	 public WeixinServlet() {  
	        super();  
	  }  
	 
	 @Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
	 
	 @Override
	  protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
