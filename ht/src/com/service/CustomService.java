package com.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

import com.pojo.News;
import com.pojo.User;
import com.weixin.pojo.WeixinUserInfo;

public class CustomService implements ICustomService {
	private Statement stmt = null;
	
	private ResultSet rs = null;
	
	private Connection conn = null;
	  
	 public boolean insetAdminLog(Connection conn,String adminName,String contents,Timestamp date){
	       boolean b =false;
	        	try {
	        		String sql = "insert into admin_log(adminName,contents,entryTime) values('"+adminName+"','"+contents+"','"+date+"')";
	    			stmt = conn.createStatement();
	    			stmt.executeUpdate(sql);
	    			b = true;
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	        		return b;
	        }
	 
	 public  List<News> leftNews(Connection conn){
		 List<News> leftNews = new ArrayList<News>();
		 try {
     		String sql = "select * from  news order by id desc limit 10";
 			stmt = conn.createStatement();
 			rs=stmt.executeQuery(sql);
 			while(rs.next()){
 				News left_news = new News();
 				left_news.setId(rs.getInt("id"));
 				left_news.setBrowseNum(rs.getInt("browse_num"));
 				left_news.setContents(rs.getString("contents"));
 				left_news.setEntryTime(rs.getTimestamp("entryTime"));
 				left_news.setEndTime(rs.getTimestamp("endTime"));
 				left_news.setTitle(rs.getString("title"));
 				left_news.setPublisher(rs.getString("publisher"));
 				left_news.setSort(rs.getInt("sort"));
 				left_news.setState(rs.getInt("state"));
 				left_news.setSource(rs.getString("source"));
 				leftNews.add(left_news);
 			}
 			System.out.println("size:"+leftNews.size());
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 				leftNews = null;
 			}
		 return leftNews;
     		
	 }
	 
	 public  void combination(String[][] chars,String[] chars1,int n,String[] tmp,List<String> slist){
		 for(int i=0;i<chars[n].length;i++){
			 if(chars[n][i]!=null){
				 if(!chars[n][i].equals("")){
					 tmp[n] = chars1[n]+":"+chars[n][i];
					 if(n<chars.length-1){
						 combination(chars,chars1,n+1,tmp,slist);
					 }else{
						 String str="";
						 for(int j=0;j<tmp.length;j++){
							 if(tmp.length-j-1==0)
								 str = str +tmp[j];
							 else str=str+tmp[j]+";";
						 }
						 slist.add(str);
					 }
				 }
			 }
		 }
	 }
	 
	 public String  getEmoneyDetail(Connection conn,WeixinUserInfo user,double amount,double balance,int payType,String tradeType,String summary,Timestamp entryTime) throws SQLException{
			
			String sql = "insert into emoney_detail(user_id,user_name,amount,balance,pay_type,trade_type,summary,entry_time)"
					+ " values('"+user.getUserId()+"','"+user.getNickName()+"','"+amount+"','"+balance+"','"+payType+"','"+tradeType
					+"','"+summary+"','"+entryTime+"')";
			return sql;
	}
	 
	 public String  getIntegralDetail(Connection conn,WeixinUserInfo user,double amount,double balance,int payType,String tradeType,String summary,Timestamp entryTime) throws SQLException{
			
			String sql = "insert into integral_detail(user_id,user_name,amount,balance,pay_type,trade_type,summary,entry_time)"
					+ " values('"+user.getUserId()+"','"+user.getNickName()+"','"+amount+"','"+balance+"','"+payType+"','"+tradeType
					+"','"+summary+"','"+entryTime+"')";
			return sql;
	}
	 
	 public String  getRmoneyDetail(Connection conn,WeixinUserInfo user,double amount,double balance,int payType,String tradeType,String summary,Timestamp entryTime) throws SQLException{
			
			String sql = "insert into rmoney_detail(user_id,user_name,amount,balance,pay_type,trade_type,summary,entry_time)"
					+ " values('"+user.getUserId()+"','"+user.getNickName()+"','"+amount+"','"+balance+"','"+payType+"','"+tradeType
					+"','"+summary+"','"+entryTime+"')";
			return sql;
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
	
}
