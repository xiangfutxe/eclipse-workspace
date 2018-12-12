package com.ssm.weixin.servlet;

import java.io.IOException;   
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.ssm.dao.TokenDao;
import com.ssm.utils.Constants;
import com.ssm.weixin.pojo.SNSUserInfo;
import com.ssm.weixin.pojo.WeixinOauth2Token;
import com.ssm.weixin.utils.AdvancedUtil;
import com.ssm.weixin.utils.SignUtil;  


/** 
 * 微信servlet 
 *  
 * @author Administrator 
 *  
 */  
public class OAuthServlet extends HttpServlet {  
  
    private static final long serialVersionUID = 7534232612712558319L;  
	
    public OAuthServlet() {  
        super();  
    }  
  
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	String message ="";
		try{
			TokenDao tokenDao = new TokenDao();
			Timestamp date = new Timestamp(new Date().getTime());
            String code = request.getParameter("code");  
          if(!"authdeny".equals(code)){
        	  WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(SignUtil.APPID, SignUtil.APPSECRET, code);
        	
        String accessToken = weixinOauth2Token.getAccessToken();
          String openId = weixinOauth2Token.getOpenId();
          SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
          /*
          //WeixinUserInfo weixinUser = ws.findByOpenId(conn, openId);
          snsUserInfo.setBranchId(weixinUser.getBranchId());
          snsUserInfo.setBranchName(weixinUser.getBranchName());
          snsUserInfo.setWeixinId(weixinUser.getWeixinId());
          snsUserInfo.setQrId(weixinUser.getQrId());
          snsUserInfo.setQrImg(weixinUser.getQrImg());
           */
          if(snsUserInfo!=null) {
        	  request.getSession().setAttribute(Constants.USER_SESSION,snsUserInfo);
          }else  message = message+"获取用户信息失败。";
         
          }else  message = message+"获取CODE信息失败。";
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message= message+ "数据操作异常，请重试！";
	}finally{
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
          
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	
    	
    }  
}  

