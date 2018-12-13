package com.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.utils.Constants;
import com.weixin.pojo.Token;
import com.weixin.pojo.WeixinUserInfo;
import com.weixin.util.CommonUtil;
import com.weixin.util.MessageUtil;
import com.weixin.util.SignUtil;

public class WeixinService implements IWeixinService {
	private Statement stmt = null;
	
	private ResultSet rs = null;
	  
	 public String getToken(Connection conn,Timestamp date){
	       String token ="";
	      boolean b = true;
	        	try {
	        		String sql ="select * from token where state='1'";
	      	       stmt = conn.createStatement();
	      	       rs=stmt.executeQuery(sql);
	      	       if(rs.next()){
	      	    	   int  id =rs.getInt("id");
	      	    	   token = rs.getString("access_token");
	      	    	   int expiresIn = rs.getInt("expires_in");
	      	    	   if(date.getTime()-expiresIn*1000L>0) b=false;
	      	    	   String sql1 = "update token set state='0' where id='"+id+"'";
		    			stmt = conn.createStatement();
		    			stmt.executeUpdate(sql1);
	      	       }else b = false;
		      	       if(!b){
		      	    	 Token AccessToken= CommonUtil.getToken(Constants.WEIXIN_APPID, Constants.WEIXIN_APPSECRET);
		      	    	 token = AccessToken.getAccessToken();
			        		String sql1 = "insert into token(access_token,expires_in,state,entry_time) values('"+AccessToken.getAccessToken()+"','"+AccessToken.getExpiresIn()+"','1','"+date+"')";
			    			stmt = conn.createStatement();
			    			stmt.executeUpdate(sql1);
		      	       }
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    				try {
							conn.rollback();
						} catch (SQLException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
	    			}
	        		return token;
	        }
	 
	 
		
		 public int getQId(Connection conn,Timestamp date) throws SQLException {
				int uid = 0;
				if (check_q_card_pool(conn)) {
					uid = get_q_card_id(conn,date);
				} else {
					add_q_card_pool(conn);
					uid = get_q_card_id(conn,date);
				}
				return uid;
			}
		 
		 public boolean check_q_card_pool(Connection conn) throws SQLException {
				String sql = "select count(*) from qid_pool";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				int num = 0;
				if (rs.next()) {
					num = rs.getInt(1);
				}
				if (num < 1)
					return false;
				else
					return true;
			}
		 
		 public int get_q_card_id(Connection conn,Timestamp date) throws SQLException {
				String sql = "select * from qid_pool where tag='0' order by id asc for update";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				int num = 0;
				int id = 0;
				if (rs.next()) {
					num = rs.getInt("qid");
					id = rs.getInt("id");
					String sqld = "update qid_pool set tag ='1',end_time='"+date+"' where id='" + id + "'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqld);
				} else {
					add_card_pool(conn);
					num = get_card_id(conn,date);
				}
				return num;
			}
		 
		 public synchronized void add_q_card_pool(Connection conn) throws SQLException {
				int maxId = 0;
				String sqld1 = "select max(qid) from qid_pool";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sqld1);
				if (rs.next()) {
					maxId = rs.getInt(1);
				} else {
					maxId = 1;
				}
				if (maxId == 0)
					maxId = 1;
				stmt = conn.createStatement();
				List<Integer> list = new ArrayList<Integer>();
				for (int i = 0; i < 3000; i++) {
					list.add(maxId + i);
				}
				int out = 0;
				int outIndex = 0;
				for (int i = 0; i < 3000; i++) {
					Random ran = new Random();
					outIndex = ran.nextInt(list.size());
					out = list.get(outIndex);
					list.remove(outIndex);
					String sqli = "insert into qid_pool(qid) values('" + (out + 1)
							+ "')";
					stmt.addBatch(sqli);
				}
				String sqld = "delete from qid_pool where tag='1'";
				stmt.addBatch(sqld);
				stmt.executeBatch();
			}
		 
		 public int getUId(Connection conn,Timestamp date) throws SQLException {
				int uid = 0;
				if (check_card_pool(conn)) {
					uid = get_card_id(conn,date);
				} else {
					add_card_pool(conn);
					uid = get_card_id(conn,date);
				}
				return uid;
			}
		 
		 public boolean check_card_pool(Connection conn) throws SQLException {
				String sql = "select count(*) from uid_pool";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				int num = 0;
				if (rs.next()) {
					num = rs.getInt(1);
				}
				if (num < 1)
					return false;
				else
					return true;
			}
		 
		 public int get_card_id(Connection conn,Timestamp date) throws SQLException {
				String sql = "select * from uid_pool where tag='0' order by id asc for update";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				int num = 0;
				int id = 0;
				if (rs.next()) {
					num = rs.getInt("uid");
					id = rs.getInt("id");
					String sqld = "update uid_pool set tag ='1',end_time='"+date+"' where id='" + id + "'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sqld);
				} else {
					add_card_pool(conn);
					num = get_card_id(conn,date);
				}
				return num;
			}
		 
		 public synchronized void add_card_pool(Connection conn) throws SQLException {
				int maxId = 0;
				String sqld1 = "select max(uid) from uid_pool";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sqld1);
				if (rs.next()) {
					maxId = rs.getInt(1);
				} else {
					maxId = 11111111;
				}
				if (maxId == 0)
					maxId = 11111111;
				stmt = conn.createStatement();
				List<Integer> list = new ArrayList<Integer>();
				for (int i = 0; i < 3000; i++) {
					list.add(maxId + i);
				}
				int out = 0;
				int outIndex = 0;
				for (int i = 0; i < 3000; i++) {
					Random ran = new Random();
					outIndex = ran.nextInt(list.size());
					out = list.get(outIndex);
					list.remove(outIndex);
					String sqli = "insert into uid_pool(uid) values('" + (out + 1)
							+ "')";
					stmt.addBatch(sqli);
				}
				String sqld = "delete from uid_pool where tag='1'";
				stmt.addBatch(sqld);
				stmt.executeBatch();
			}
		 
		 public WeixinUserInfo findByOpenId(Connection conn,String openId){
			 WeixinUserInfo weixin = null;
				try {
					String sql = "select * from weixin_userinfo where open_id='"+openId+"'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						weixin = new WeixinUserInfo();
						weixin.setId(rs.getInt("id"));
						weixin.setUserId(rs.getString("user_id"));
						weixin.setEmoney(rs.getDouble("emoney"));
						weixin.setRankManage(rs.getInt("rank_manage"));
						weixin.setQrId(rs.getInt("qr_id"));
						weixin.setWeixinId(MessageUtil.USER_ID_VALUES+weixin.getQrId());
						weixin.setSubscribe(rs.getInt("subscribe"));
						weixin.setSubscribeTime(rs.getString("subscribe_time"));
						weixin.setBranchId(rs.getString("branch_id"));
						weixin.setBranchName(rs.getString("branch_name"));
						weixin.setQrImg(rs.getString("qr_img"));
					}
				}catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
    			}
				return weixin;
		 }
		 
		 public WeixinUserInfo findByQRId(Connection conn,int qr_id){
			 WeixinUserInfo weixin = null;
				try {
					String sql = "select * from weixin_userinfo where qr_id='"+qr_id+"'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						weixin = new WeixinUserInfo();
						weixin.setId(rs.getInt("id"));
						weixin.setUserId(rs.getString("user_id"));
						weixin.setQrId(rs.getInt("qr_id"));
						weixin.setWeixinId(MessageUtil.USER_ID_VALUES+rs.getInt("qr_id"));
						weixin.setSubscribe(rs.getInt("subscribe"));
						weixin.setSubscribeTime(rs.getString("subscribe_time"));
						weixin.setBranchId(rs.getString("branch_id"));
						weixin.setBranchName(rs.getString("branch_name"));
						weixin.setQrImg(rs.getString("qr_img"));
					}else weixin=null;
				}catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
    			}
				return weixin;
		 }
		
	
}
