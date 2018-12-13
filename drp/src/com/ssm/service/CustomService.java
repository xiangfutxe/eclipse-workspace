package com.ssm.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

import com.ssm.dao.AdminLogDao;
import com.ssm.dao.UserLogDao;
import com.ssm.pojo.AdminLog;
import com.ssm.pojo.User;
import com.ssm.pojo.UserLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.StringUtil;

public class CustomService implements ICustomService {
	
	 public Timestamp getNextYearTime(Timestamp date){
     	Timestamp time = new Timestamp(date.getTime());
         Calendar curr = Calendar.getInstance();
         curr.setTime(time);
         curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+1);
         Timestamp date1=new Timestamp(curr.getTime().getTime());
         return date1;
     }
	 
	 public int getRandom(int min,int max){
	        Random random = new Random();
	        int s = random.nextInt(max)%(max-min+1) + min;
	        return s;
	}
	 
	 public void insertAdminLog(String adminName,String contents,String type,Timestamp date){
		 AdminLogDao  adminLogDao = new AdminLogDao();
		 AdminLog adminLog = new AdminLog();
		 adminLog.setAdminName(adminName);
		 adminLog.setContents(contents);
		 adminLog.setType(type);
		 adminLog.setEntryTime(date);
		 adminLogDao.saveAdminLog(adminLog);
	 }
	 
	 public void insertUserLog(int uid,String userId,String userName,String contents,String type,Timestamp date){
		 UserLogDao  userLogDao = new UserLogDao();
		 UserLog userlog = new UserLog();
		 userlog.setUid(uid);
		 userlog.setUserId(userId);
		 userlog.setUserName(userName);
		 userlog.setContents(contents);
		 userlog.setType(type);
		 userlog.setEntryTime(date);
		 userLogDao.saveUserLog(userlog);
	 }
	 
	  public  String encodeFileName(HttpServletRequest request,  
              String fileName) throws UnsupportedEncodingException {  
          String agent = request.getHeader("USER-AGENT");  
          if (null != agent && -1 != agent.indexOf("MSIE")) {  
              return URLEncoder.encode(fileName, "UTF-8");  
          } else if (null != agent && -1 != agent.indexOf("Mozilla")) {  
              return "=?UTF-8?B?"  
                      + (new String(Base64.encodeBase64(fileName  
                              .getBytes("UTF-8")))) + "?=";  
          } else {  
              return fileName;  
          }  
      }  
	  
		public  String getRefereeNode(User user) {
			String node = "";
			if (StringUtil.notNull(user.getRefereeNode()).equals(""))
				node = String.valueOf(user.getId());
			else
				node = user.getRefereeNode() + "," + String.valueOf(user.getId());
			return node;
		}

		public  String getDeclarationNode(User user) {
			String node = "";
			if (StringUtil.notNull(user.getDeclarationNode()).equals(""))
				node = String.valueOf(user.getId());
			else
				node = user.getDeclarationNode() + ","
						+ String.valueOf(user.getId());
			return node;
		}

		public  String getNode(User user) {
			String node = "";
			if (StringUtil.notNull(user.getNode()).equals(""))
				node = String.valueOf(user.getId());
			else
				node = user.getNode() + "," + String.valueOf(user.getId());
			return node;
		}

		public String getNodeABC(User user, int nodeTag) {
			String node = "";
			String nodeStr = "";
			if (nodeTag == 1)
				nodeStr = "A";
			else if (nodeTag == 2)
				nodeStr = "B";
			else if (nodeTag == 3)
				nodeStr = "C";
			if (StringUtil.notNull(user.getNodeABC()).equals(""))
				node = nodeStr;
			else
				node = user.getNodeABC() + "," + nodeStr;
			return node;
		}
		
		public int getWeekTag(Timestamp date){
			int weekTag = 0;
			Timestamp startTime=  new Timestamp(StringUtil.parseToDate(DateUtil.STARTTIMESTR,DateUtil.yyyyMMddHHmmss).getTime());
			weekTag = (int)Math.floor((date.getTime()-startTime.getTime())/(7*24*60*60*1000)) +1;
			return weekTag;
		}
		
		 public Timestamp getNextDay(Timestamp date){
			 Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DAY_OF_MONTH, 1); 
				Timestamp startTime =new Timestamp(calendar.getTime().getTime());
		         return startTime;
		     }
		 
		 public Timestamp getPreDay(Timestamp date){
			 Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DAY_OF_MONTH, -1); 
				Timestamp startTime =new Timestamp(calendar.getTime().getTime());
		         return startTime;
		     }


}
