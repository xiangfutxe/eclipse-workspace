package com.weixin.servlet;

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

import com.db.DBConn;
import com.service.CustomService;
import com.service.ICustomService;
import com.service.IWeixinService;
import com.service.WeixinService;
import com.utils.Constants;
import com.weixin.pojo.WeixinOauth2Token;
import com.weixin.pojo.WeixinUserInfo;
import com.weixin.util.AdvancedUtil;
import com.weixin.util.CommonUtil;
import com.weixin.util.SignUtil;
  

/** 
 * 微信servlet 
 *  
 * @author Administrator 
 *  
 */  
public class OAuthServlet extends HttpServlet {  
  
    private static final long serialVersionUID = 7534232612712558319L;  
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	IWeixinService ws = new WeixinService();
    public OAuthServlet() {  
        super();  
    }  
  
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
DBConn db = new DBConn();
String message ="";
		try{
		if(db.createConn()){
            // 用户同意授权后，能获取到code
			conn = db.getConnection();
			Timestamp date = new Timestamp(new Date().getTime());
            String code = request.getParameter("code");  
          if(!"authdeny".equals(code)){
        	  WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(Constants.WEIXIN_APPID, Constants.WEIXIN_APPSECRET, code);
        	
        	  String accessToken =ws.getToken(conn, date);
        	  //String accessToken = weixinOauth2Token.getAccessToken();
          String openId = weixinOauth2Token.getOpenId();
          WeixinUserInfo snsUserInfo = AdvancedUtil.getUserInfo(accessToken, openId);
          WeixinUserInfo weixinUser = ws.findByOpenId(conn, openId);
          snsUserInfo.setBranchId(weixinUser.getBranchId());
          snsUserInfo.setBranchName(weixinUser.getBranchName());
          snsUserInfo.setWeixinId(weixinUser.getWeixinId());
          snsUserInfo.setQrId(weixinUser.getQrId());
          snsUserInfo.setQrImg(weixinUser.getQrImg());
          request.getSession().setAttribute("snsUserInfo",snsUserInfo);
          }
		}else {
			message= "数据库连接已断开！";
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		e.printStackTrace();
		message=  "数据操作异常，请重试！";
	}finally{
		db.close();
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("weixin/user_rect.jsp");
		dispatcher.forward(request, response);
	}
          
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	
    	
    }  
}  

