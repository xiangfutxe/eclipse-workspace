package com.ssm.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.ssm.dao.ProductDao;
import com.ssm.dao.ProductSortDao;
import com.ssm.dao.TokenDao;
import com.ssm.dao.UserDao;
import com.ssm.dao.WeixinUserInfoDao;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductSort;
import com.ssm.pojo.User;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;
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
public class WeixinUserInfoServlet extends HttpServlet {  
  
    private static final long serialVersionUID = 7534232612712558319L;  
	
    public WeixinUserInfoServlet() {  
        super();  
    }  
    public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String method = (String) request.getParameter("method");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if (method == null) {
			PrintWriter out = response.getWriter();
			out.println("invalid request");

		} else if (method.equals("save_weixin")) {
			save_weixin(request, response);
		}  else if (method.equals("getWeixin")) {
			getWeixin(request, response);
		}else if (method.equals("index")) {
			index(request, response);
		}else if (method.equals("user_code")) {
			user_code(request, response);
		}else if (method.equals("user_home")) {
			user_home(request, response);
		}else if (method.equals("user_team")) {
			user_team(request, response);
		}else if (method.equals("shop_list")) {
			shop_list(request, response);
		}else if (method.equals("shop_integral_list")) {
			shop_integral_list(request, response);
		}else if (method.equals("shop_cash_list")) {
			shop_cash_list(request, response);
		}
    }
    public void save_weixin(HttpServletRequest request, HttpServletResponse response)  
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
        //  String accessToken = weixinOauth2Token.getAccessToken();
        	  String accessToken  = tokenDao.getToken(date);
        	  String openId = weixinOauth2Token.getOpenId();
        	  WeixinUserInfoDao weixinDao = new WeixinUserInfoDao();
        	  message+=accessToken+";";
        	 message += weixinDao.save(accessToken, 0, openId, date);
        	  weixinDao = new WeixinUserInfoDao();
        	 WeixinUserInfo weixin= weixinDao.getWeixinUser(openId);
       	  UserDao userDao = new UserDao();
       	  User user1= userDao.findByUserId(weixin.getUserId());
       	  if(weixin!=null) {
       		  request.getSession().setAttribute(Constants.USER_SESSION, weixin);
       		  request.getSession().setAttribute(Constants.USERINFO_SESSION, user1);
       	  }
          /*
          //WeixinUserInfo weixinUser = ws.findByOpenId(conn, openId);
          snsUserInfo.setBranchId(weixinUser.getBranchId());
          snsUserInfo.setBranchName(weixinUser.getBranchName());
          snsUserInfo.setWeixinId(weixinUser.getWeixinId());
          snsUserInfo.setQrId(weixinUser.getQrId());
          snsUserInfo.setQrImg(weixinUser.getQrImg());
          
          if(qrId>0) {
        	  message = "您成为会员成功，会员编号为："+qrId+";";
          }else  message = "您已是会员或者会员信息保存失败。";
          */
          }else  message +="获取CODE信息失败。";
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message= message+ "数据操作异常，请重试！"+e.getMessage();
	}finally{
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
          
    } 
    
    public void getWeixin(HttpServletRequest request, HttpServletResponse response)  
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
        //  String accessToken = weixinOauth2Token.getAccessToken();
        	  String accessToken  = tokenDao.getToken(date);
        	  String openId = weixinOauth2Token.getOpenId();
        	 message="openId："+openId+";";
          /*
          //WeixinUserInfo weixinUser = ws.findByOpenId(conn, openId);
          snsUserInfo.setBranchId(weixinUser.getBranchId());
          snsUserInfo.setBranchName(weixinUser.getBranchName());
          snsUserInfo.setWeixinId(weixinUser.getWeixinId());
          snsUserInfo.setQrId(weixinUser.getQrId());
          snsUserInfo.setQrImg(weixinUser.getQrImg());
          
          if(qrId>0) {
        	  message = "您成为会员成功，会员编号为："+qrId+";";
          }else  message = "您已是会员或者会员信息保存失败。";
          */
          }else  message +="获取CODE信息失败。";
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message= message+ "数据操作异常，请重试！"+e.getMessage();
	}finally{
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("weixin_msg.jsp");
		dispatcher.forward(request, response);
	}
          
    }  
    
    public void index(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	String message ="";
		try{
            String code = request.getParameter("code");  
            if(!"authdeny".equals(code)){
        	  WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(SignUtil.APPID, SignUtil.APPSECRET, code);
        //  String accessToken = weixinOauth2Token.getAccessToken();
        	  String openId = weixinOauth2Token.getOpenId();
        	  WeixinUserInfoDao weixinDao = new WeixinUserInfoDao();
        	  WeixinUserInfo weixin= weixinDao.getWeixinUser(openId);
        	  if(weixin!=null) {
            	  UserDao userDao = new UserDao();
            	  User user1= userDao.findByUserId(weixin.getUserId());
        		  request.getSession().setAttribute(Constants.USER_SESSION, weixin);
        		  request.getSession().setAttribute(Constants.USERINFO_SESSION, user1);
        			ProductDao productDao = new ProductDao();
					Product p = new Product();
					p.setState(1);
					p.setIsHide(0);
					List<Product> coll_pro = productDao.findByProduct(p);
					request.setAttribute("coll_pro", coll_pro);
        	  } else message = "未获取您的会员信息，请在平台商务->成为会员中加入我们。";
          }else  message ="获取CODE信息失败。";
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message= "数据操作异常，请重试！"+e.getMessage();
	}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
	}
          
    }  
    
    public void user_code(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	String message ="";
		try{
            String code = request.getParameter("code");  
            if(!"authdeny".equals(code)){
        	  WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(SignUtil.APPID, SignUtil.APPSECRET, code);
        //  String accessToken = weixinOauth2Token.getAccessToken();
        	  String openId = weixinOauth2Token.getOpenId();
        	  WeixinUserInfoDao weixinDao = new WeixinUserInfoDao();
        	  WeixinUserInfo weixin= weixinDao.getWeixinUser(openId);
        	  if(weixin!=null)
        		  request.getSession().setAttribute(Constants.USER_SESSION, weixin);
        	  else message = "未获取您的会员信息，请在平台商务->成为会员中加入我们。";
          
          
          }else  message ="获取CODE信息失败。";
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message= "数据操作异常，请重试！"+e.getMessage();
	}finally{
		request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user_code.jsp");
			dispatcher.forward(request, response);
	}
          
    }  
    
    public void user_home(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	String message ="";
		try{
            String code = request.getParameter("code");  
            if(!"authdeny".equals(code)){
        	  WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(SignUtil.APPID, SignUtil.APPSECRET, code);
        //  String accessToken = weixinOauth2Token.getAccessToken();
        	  String openId = weixinOauth2Token.getOpenId();
        	  WeixinUserInfoDao weixinDao = new WeixinUserInfoDao();
        	  WeixinUserInfo weixin= weixinDao.getWeixinUser(openId);
        	  UserDao userDao = new UserDao();
        	  User user1= userDao.findByUserId(weixin.getUserId());
        	  if(weixin!=null) {
        		  request.getSession().setAttribute(Constants.USER_SESSION, weixin);
        		  request.getSession().setAttribute(Constants.USERINFO_SESSION, user1);
        	  } else message = "未获取您的会员信息，请在平台商务->成为会员中加入我们。";
          }else  message ="获取CODE信息失败。";
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message= "数据操作异常，请重试！"+e.getMessage();
	}finally{
		request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("userhome.jsp");
			dispatcher.forward(request, response);
	}
          
    }  
    
    public void shop_list(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	String message ="";
		try{
            String code = request.getParameter("code");  
            if(!"authdeny".equals(code)){
        	  WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(SignUtil.APPID, SignUtil.APPSECRET, code);
        //  String accessToken = weixinOauth2Token.getAccessToken();
        	  String openId = weixinOauth2Token.getOpenId();
        	  WeixinUserInfoDao weixinDao = new WeixinUserInfoDao();
        	  WeixinUserInfo weixin= weixinDao.getWeixinUser(openId);
        	  UserDao userDao = new UserDao();
        	  User user1= userDao.findByUserId(weixin.getUserId());
        	  if(weixin!=null) {
        		  request.getSession().setAttribute(Constants.USER_SESSION, weixin);
        		  request.setAttribute("user1", user1);
        		  Collection coll_pt = new ArrayList();
        		  Collection coll = new ArrayList();
        		  String idstr="0";
        			ProductSortDao productSortDao = new ProductSortDao();
        			coll_pt = productSortDao.findAllProductSort();
        			request.setAttribute("idstr", idstr);
        			request.setAttribute("coll_pt", coll_pt);
        			ProductDao productDao = new ProductDao();
        			Product product = new Product();
        			product.setProductType("等级专区");
        			product.setState(1);
        			if(!idstr.equals("")) {
        				 Iterator it = coll_pt.iterator();
        				 while(it.hasNext()){
        					 ProductSort  sort = (ProductSort)it.next();
        					 if(String.valueOf(sort.getId()).equals(idstr)) {
        						 product.setProductSort(sort.getName());
        						 break;
        					 }
        				 }
        			}
        			coll = productDao.findByProduct(product);
        			request.setAttribute("coll", coll);
        	  } else message = "未获取您的会员信息，请在会员商城->成为会员中加入我们。";
          }else  message ="获取CODE信息失败。";
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message= "数据操作异常，请重试！"+e.getMessage();
	}finally{
		request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_list.jsp");
			dispatcher.forward(request, response);
	}
          
    }  
    
    public void shop_cash_list(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	String message ="";
		try{
            String code = request.getParameter("code");  
            if(!"authdeny".equals(code)){
        	  WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(SignUtil.APPID, SignUtil.APPSECRET, code);
        //  String accessToken = weixinOauth2Token.getAccessToken();
        	  String openId = weixinOauth2Token.getOpenId();
        	  WeixinUserInfoDao weixinDao = new WeixinUserInfoDao();
        	  WeixinUserInfo weixin= weixinDao.getWeixinUser(openId);
        	  UserDao userDao = new UserDao();
        	  User user1= userDao.findByUserId(weixin.getUserId());
        	  if(weixin!=null) {
        		  request.getSession().setAttribute(Constants.USER_SESSION, weixin);
        		  request.setAttribute("user1", user1);
        		  Collection coll_pt = new ArrayList();
        		  Collection coll = new ArrayList();
        		  String idstr="0";
        			
        			ProductSortDao productSortDao = new ProductSortDao();
        			coll_pt = productSortDao.findAllProductSort();
        			request.setAttribute("idstr", idstr);
        			request.setAttribute("coll_pt", coll_pt);
        			ProductDao productDao = new ProductDao();
        			Product product = new Product();
        			product.setProductType("特卖专区");
        			product.setState(1);
        			if(!idstr.equals("")) {
        				 Iterator it = coll_pt.iterator();
        				 while(it.hasNext()){
        					 ProductSort  sort = (ProductSort)it.next();
        					 if(String.valueOf(sort.getId()).equals(idstr)) {
        						 product.setProductSort(sort.getName());
        						 break;
        					 }
        				 }
        			}
        			coll = productDao.findByProduct(product);
        			request.setAttribute("coll", coll);
        	  } else message = "未获取您的会员信息，请在会员商城->成为会员中加入我们。";
          }else  message ="获取CODE信息失败。";
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message= "数据操作异常，请重试！"+e.getMessage();
	}finally{
		request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_cash_list.jsp");
			dispatcher.forward(request, response);
	}
          
    }  
    
    public void shop_integral_list(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	String message ="";
		try{
            String code = request.getParameter("code");  
            if(!"authdeny".equals(code)){
        	  WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(SignUtil.APPID, SignUtil.APPSECRET, code);
        //  String accessToken = weixinOauth2Token.getAccessToken();
        	  String openId = weixinOauth2Token.getOpenId();
        	  WeixinUserInfoDao weixinDao = new WeixinUserInfoDao();
        	  WeixinUserInfo weixin= weixinDao.getWeixinUser(openId);
        	  UserDao userDao = new UserDao();
        	  User user1= userDao.findByUserId(weixin.getUserId());
        	  if(weixin!=null) {
        		  request.getSession().setAttribute(Constants.USER_SESSION, weixin);
        		  request.setAttribute("user1", user1);
        		  Collection coll_pt = new ArrayList();
        		  Collection coll = new ArrayList();
        		  String idstr="0";
        			
        			ProductSortDao productSortDao = new ProductSortDao();
        			coll_pt = productSortDao.findAllProductSort();
        			request.setAttribute("idstr", idstr);
        			request.setAttribute("coll_pt", coll_pt);
        			ProductDao productDao = new ProductDao();
        			Product product = new Product();
        			product.setProductType("特卖专区");
        			product.setState(1);
        			if(!idstr.equals("")) {
        				 Iterator it = coll_pt.iterator();
        				 while(it.hasNext()){
        					 ProductSort  sort = (ProductSort)it.next();
        					 if(String.valueOf(sort.getId()).equals(idstr)) {
        						 product.setProductSort(sort.getName());
        						 break;
        					 }
        				 }
        			}
        			coll = productDao.findByProduct(product);
        			request.setAttribute("coll", coll);
        	  } else message = "未获取您的会员信息，请在会员商城->成为会员中加入我们。";
          }else  message ="获取CODE信息失败。";
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message= "数据操作异常，请重试！"+e.getMessage();
	}finally{
		request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("shop_integral_list.jsp");
			dispatcher.forward(request, response);
	}
          
    }  
    

    public void user_team(HttpServletRequest request, HttpServletResponse response)  
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
        //  String accessToken = weixinOauth2Token.getAccessToken();
        	  String accessToken  = tokenDao.getToken(date);
        	  String openId = weixinOauth2Token.getOpenId();
        	  WeixinUserInfoDao weixinDao = new WeixinUserInfoDao();
        	  WeixinUserInfo weixin= weixinDao.getWeixinUser(openId);
        	 int pageNo = 1;
     		int pageSize = 10;
     		User user1= new User();
    		user1.setRefereeUserId(weixin.getUserId());
    				UserDao userDao = new UserDao();
    				Pager pager= new Pager();
    				pager.setPageNo(pageNo);
    				pager.setPageSize(pageSize);
    				pager = userDao.findUserByPage(user1,pager);
    				request.setAttribute("pager", pager);
    				request.getSession().setAttribute(Constants.USER_SESSION, weixin);
          }else  message +="获取CODE信息失败。";
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message= message+ "数据操作异常，请重试！"+e.getMessage();
	}finally{
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user_team.jsp");
		dispatcher.forward(request, response);
	}
          
    }  
  
}  

