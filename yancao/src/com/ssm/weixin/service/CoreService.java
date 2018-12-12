package com.ssm.weixin.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ssm.dao.TokenDao;
import com.ssm.dao.WeixinUserInfoDao;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.utils.StringUtil;
import com.ssm.weixin.pojo.message.Image;
import com.ssm.weixin.pojo.message.ImageMessage;
import com.ssm.weixin.pojo.message.TextMessage;
import com.ssm.weixin.utils.MessageUtil;


public class CoreService {
	/* 
	 * 处理微信发送来的请求
	 */

	
	public static String processRequest(HttpServletRequest request){
		String respXml  = null;
		Timestamp date = new Timestamp(new Date().getTime());
        String code = request.getParameter("code"); 
       
        TextMessage textMessage = new TextMessage();
		//回复文本消息
		try{
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
					int qrId =0;
					if(!StringUtil.notNull(eventKey).equals("")){
					 String refereeIdStr = eventKey.substring(8);
					 if(!refereeIdStr.equals("")){
						  refereeId=Integer.valueOf(refereeIdStr);
					 }
					}
					WeixinUserInfoDao weixinDao = new WeixinUserInfoDao();
					TokenDao tokenDao = new TokenDao();
					String token = tokenDao.getToken(date);
					String msg = weixinDao.save(token,refereeId,fromUserName,date);
					textMessage.setContent(msg);
					respXml = MessageUtil.messageToXml(textMessage);
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
					WeixinUserInfoDao weixinDao = new WeixinUserInfoDao();
					TokenDao tokenDao = new TokenDao();
					String token = tokenDao.getToken(date);
					int result = weixinDao.unsubscribe(token,fromUserName,date);
					if(result==0) textMessage.setContent("您好，欢迎您下次关注威腾微信公众号！");
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
					textMessage.setContent("请通过菜单使用网址导航服务.");
					respXml = MessageUtil.messageToXml(textMessage);
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
					//处理菜单点击事件
					String eventKey = requestMap.get("EventKey");
					WeixinUserInfoDao weixinDao = new WeixinUserInfoDao();
					 if (eventKey.equals("11")) {  
							WeixinUserInfo weixin = weixinDao.findByOpenId(fromUserName);
							if(weixin!=null) {
								textMessage.setContent("邀请码为："+weixin.getUserId());
							}else {
								textMessage.setContent("未获得邀请码信息，请查询是否已成为会员。");
							}
							respXml = MessageUtil.messageToXml(textMessage);
	                }
				}else{
					textMessage.setContent("尊敬的婴儿珍家人，欢迎您再次回到婴儿珍社交新零售商城，您已经成为婴儿珍社交新零售商城的会员，建议您把我们的商城在设置里置顶，这样您以后就可以很方便的进入我们的商城了！感谢您的关注！");
					respXml = MessageUtil.messageToXml(textMessage);
				}
			}else{
				textMessage.setContent("请通过菜单使用网址导航服务。");
				respXml = MessageUtil.messageToXml(textMessage);
			}
		}catch(Exception e){
			textMessage.setContent("您好，欢迎您关注微腾公众号，请通过菜单使用网址导航服务！");
			respXml = MessageUtil.messageToXml(textMessage);
			e.printStackTrace();
		}
		return respXml;
	}
}
