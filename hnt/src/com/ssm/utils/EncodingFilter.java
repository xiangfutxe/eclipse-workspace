package com.ssm.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 1L;
	
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("UTF-8过滤器初始化成功");  
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		 String method = req.getMethod();  
		  if(method.equals("POST")) {  
		   req.setCharacterEncoding("UTF-8");  
		  } else {  
		   //获取当前提交的参数  
		   String queryString = req.getQueryString();  
		   System.out.println(queryString);  
		   //判断是否有参数  
		   if(queryString != null) {  
		    //判断参数是否只有一个  
		    if(queryString.indexOf("&") == -1) {  
		     String[] strs = queryString.split("=");  
		     //通过参数的key手动编码解码  
		     String param = new String(  
		       req.getParameter(strs[0]).getBytes("ISO-8859-1"),"utf-8");  
		     req.setAttribute(strs[0], param);  
		    } else {  
		     String[] strsquery = queryString.split("&");  
		     for (int i = 0; i < strsquery.length; i++) {  
		      String[] strs = strsquery[i].split("=");  
		      //通过参数的key手动编码解码  
		      String param = new String(  
		        req.getParameter(strs[0]).getBytes("ISO-8859-1"),"utf-8");  
		      req.setAttribute(strs[0], param);  
		     }  
		    }  
		   }  
		  }  
		chain.doFilter(req, res);
	}

	
	
	

}
