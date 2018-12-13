package com.weixin.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.db.DBConn;
import com.service.IWeixinService;
import com.service.WeixinService;
import com.utils.Constants;
import com.utils.StringUtil;
import com.weixin.pojo.WeixinUserInfo;
import com.weixin.pojo.message.Article;
import com.weixin.pojo.message.NewsMessage;
import com.weixin.pojo.message.TextMessage;
import com.weixin.util.AdvancedUtil;
import com.weixin.util.MessageUtil;

public class CoreService {
	/* 
	 * 处理微信发送来的请求
	 */

	
	public static String processRequest(HttpServletRequest request){
		String respXml  = null;
		DBConn db = new DBConn();
		Timestamp date = new Timestamp(new Date().getTime());
        String code = request.getParameter("code"); 
        Statement stmt = null;
        ResultSet rs = null;
        TextMessage textMessage = new TextMessage();
		//回复文本消息
		
		
		try{
			Connection conn= db.getConnection();
			if(db.createConn()){
			if(conn!=null){
			IWeixinService ws = new WeixinService();
			  Map<String,String> requestMap  = MessageUtil.parseXML(request);
				
				String fromUserName = requestMap.get("FromUserName");
				String toUserName = requestMap.get("ToUserName");
				String msgType = requestMap.get("MsgType");
				textMessage.setToUserName(fromUserName);
				textMessage.setFromUserName(toUserName);
				textMessage.setCreateTime(new Date().getTime());
				textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
			 if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
				String eventType = requestMap.get("Event");
				//关注
				if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
					
					String eventKey = requestMap.get("EventKey");
					
					int refereeId  = 0;
					WeixinUserInfo refereeUser = null;
					String branchId="";
					String branchName="";
					if(!StringUtil.notNull(eventKey).equals("")){
					 String refereeIdStr = eventKey.substring(8);
					 if(refereeIdStr.equals("")){
						 branchId=Constants.TOP_BRANCH_ID;
							branchName=Constants.TOP_BRANCH_NAME;
					 }else{
						   refereeId=Integer.valueOf(refereeIdStr);
						 refereeUser =  ws.findByQRId(conn, refereeId);
						 if(refereeUser!=null){
							 branchId=refereeUser.getBranchId();
						 	branchName = refereeUser.getBranchName();
						 }else{
								branchId=Constants.TOP_BRANCH_ID;
								branchName=Constants.TOP_BRANCH_NAME;
							}
					 }
					}else{
						branchId=Constants.TOP_BRANCH_ID;
						branchName=Constants.TOP_BRANCH_NAME;
					}
					String accessToken =ws.getToken(conn, date);
		          WeixinUserInfo snsUserInfo = AdvancedUtil.getUserInfo(accessToken, fromUserName);
		          WeixinUserInfo weixin = ws.findByOpenId(conn, fromUserName);
		          int qrId=0;
		          String userId="";
		          String qr_img="";
		          String sql = "";
		          if(weixin==null){
		        	 qrId = ws.getQId(conn, date);
		        	 userId = String.valueOf(ws.getUId(conn, date));
		        	 String ticket = AdvancedUtil.createPermanentQRCode(accessToken, qrId);
		      		 String savePath="/usr/local/tomcat/webapps/ht/upload/qr";
		      		AdvancedUtil.getQRCode(ticket, savePath);
		      		qr_img = ticket+".jpg";
		        	  sql = "insert into weixin_userinfo(open_id,subscribe,subscribe_time,user_id,sex,nick_name,qr_id,qr_img,referee_id,branch_id,branch_name,state,end_time)"
		        	  		+ " values('"+fromUserName+"','1','"+snsUserInfo.getSubscribeTime()+"','"+userId+"','"+snsUserInfo.getSex()+"','"+snsUserInfo.getNickName()+"','"+qrId+"','"+qr_img+"','"+refereeId+"','"+branchId+"','"+branchName+"','1','"+date+"');";
		        	 stmt = conn.createStatement();
		        	 stmt.executeUpdate(sql);
		          }else{
		        	  sql = "update weixin_userinfo set subscribe='1',end_time='"+date+"' where open_id='"+fromUserName+"'";
		        	  stmt = conn.createStatement();
		        	 stmt.executeUpdate(sql);
		          }
		         
					textMessage.setContent("您好，欢迎关注华太俱乐部微信公众号！你的会员编号是"+userId);
					respXml = MessageUtil.messageToXml(textMessage);
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
					
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
					//处理菜单点击事件
					String eventKey = requestMap.get("EventKey");
					if(eventKey.equals("aboutus")){
						Article article = new Article();
						article.setTitle("公司简介");
						article.setDescription("世界华埠太太俱乐部总部位于中国香港，"
								+ "中国大陆总部位于广州，其组建的渊源来自于世界华埠太太竞选活动。"
								+ "世界华埠太太俱乐部在加拿大、美国、瑞典、瑞士、澳大利亚、印度尼西亚、新加坡、"
								+ "泰国、缅甸、马来西亚、菲律宾等国家都设有联盟机构，全球有近28万会员。");
						article.setPicUrl("");
						article.setUrl("http://122.112.225.58/ht/AboutServlet.do?method=A_201");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setFromUserName(toUserName);
						newsMessage.setToUserName(fromUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					}else if(eventKey.equals("hexin")){
						textMessage.setContent("世界华埠太太俱乐部总部位于中国香港，"
								+ "中国大陆总部位于广州，其组建的渊源来自于世界华埠太太竞选活动。"
								+ "世界华埠太太俱乐部在加拿大、美国、瑞典、瑞士、澳大利亚、印度尼西亚、新加坡、"
								+ "泰国、缅甸、马来西亚、菲律宾等国家都设有联盟机构，全球有近28万会员。");
						respXml = MessageUtil.messageToXml(textMessage);
					}
				}
			}else{
				textMessage.setContent("请通过菜单使用网址导航服务！");
				respXml = MessageUtil.messageToXml(textMessage);
			}
			}else{
				textMessage.setContent("数据库连接失败！");
				respXml = MessageUtil.messageToXml(textMessage);
			}
			}else{
				textMessage.setContent("数据库连接失败！");
				respXml = MessageUtil.messageToXml(textMessage);
			}
		}catch(Exception e){
			textMessage.setContent(e.toString());
			respXml = MessageUtil.messageToXml(textMessage);
			e.printStackTrace();
		}
		return respXml;
	}
}
