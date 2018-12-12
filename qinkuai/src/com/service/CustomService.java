package com.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.db.DBConn;
import com.pojo.Branch;
import com.pojo.Center;
import com.pojo.InventoryCenter;
import com.pojo.Param;
import com.pojo.Promotion;
import com.pojo.Settle;
import com.pojo.SettleStock;
import com.pojo.ShortMessage;
import com.pojo.TimeParam;
import com.pojo.User;
import com.utils.DateUtil;
import com.utils.StringUtil;

public class CustomService implements ICustomService {
	
	private Statement stmt = null;
	
	private ResultSet rs = null;
	
	  private Connection conn = null;
	
	public Timestamp[] getTime(Connection conn,Timestamp date){
		Timestamp[] time = new Timestamp[2];
		TimeParam tp = getTimeParam(conn);
		time[0]= new  Timestamp(tp.getStartTime().getTime()+ (long)(Math.floor((date.getTime()-tp.getStartTime().getTime())/ (tp.getWeekNum() * 3600000))* tp.getWeekNum() * 3600000));
		time[1]= new Timestamp(time[0].getTime()+(long)(tp.getWeekNum() * 3600000));
		return time;
	}
	public int getWeekTag(Connection conn,Timestamp date){
		int weekTag = 0;
		TimeParam tp = getTimeParam(conn);
		Timestamp[] time = new Timestamp[2];
		time[0]= new  Timestamp(tp.getStartTime().getTime()+ (long)(Math.floor((date.getTime()-tp.getStartTime().getTime())/ (tp.getWeekNum() * 3600000))* tp.getWeekNum() * 3600000));
		time[1]= new Timestamp(time[0].getTime()+(long)(tp.getWeekNum() * 3600000));
		weekTag = (int)Math.floor((time[0].getTime()-tp.getStartTime().getTime())/(tp.getWeekNum() * 3600000)) +1;
		return weekTag;
	}
	

	
	public TimeParam getTimeParam(Connection conn){
		TimeParam tp = new TimeParam();
		try {
		String sql = "select * from timeparam where paramName='时间参数'";
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		
		if(rs.next()){
			tp.setStartTime(rs.getTimestamp("startTime"));
			tp.setWeekNum(rs.getDouble("weekNum"));
			tp.setMonthNum(rs.getInt("monthNum"));
		}else tp=null;
	} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		e.printStackTrace();
	}
		return tp;
	}
	
	
		
	
		
		public String  getCreditDetail(Branch bh,double amount,double balance,int payType,String orderId,Timestamp entryTime) throws SQLException{
			
			String sql = "insert into credit_detail(bid,code,name,amount,balance,pay_type,order_id,entry_time)"
					+ " values('"+bh.getId()+"','"+bh.getCode()+"','"+bh.getName()+"','"+amount+"','"+balance+"','"+payType+"','"+orderId
					+"','"+entryTime+"')";
			return sql;
	}
		
		
		public String createOrderId(Timestamp date){
			String rid = StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(getRandom(10,99));
			    	return rid;
			    
				}
		
		public String createInventoryApplyId(Timestamp date){
			String rid = StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(getRandom(10,99));
			    	return rid;
				}
		
		public String createPurchaseApplyId(Timestamp date){
			String rid = StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(getRandom(10,99));
			    	return rid;
				}
		
		public String createWithDrewId(Timestamp date){
			String rid = "W"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(getRandom(10,99));
			    	return rid;
			    
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
	        
	        
	     
	        
	    	public String createCenterId(Timestamp date){
				String rid = "C"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(getRandom(10,99));
				    	return rid;
				    
					}
	    	
	    	  
	        public Param getParam(Connection conn,String name){
	        Param param = new Param();
	        	try {
	        		String sql = "select * from param  where paramName ='"+name+"'";
	    			stmt = conn.createStatement();
	    			rs = stmt.executeQuery(sql);
	    			if(rs.next()){
	    				param.setId(rs.getInt("id"));
	    				param.setParamName(rs.getString("paramName"));
	    				param.setAmount_1(rs.getDouble("amount_1"));
	    				param.setAmount_2(rs.getDouble("amount_2"));
	    				param.setAmount_3(rs.getDouble("amount_3"));
	    				param.setAmount_4(rs.getDouble("amount_4"));
	    				param.setAmount_5(rs.getDouble("amount_5"));
	    				param.setAmount_6(rs.getDouble("amount_6"));
	    				param.setAmount_7(rs.getDouble("amount_7"));
	    				param.setAmount_8(rs.getDouble("amount_8"));
	    				param.setAmount_9(rs.getDouble("amount_9"));
	    				param.setAmount_10(rs.getDouble("amount_10"));
	    				param.setUnit(rs.getString("unit"));
	    				param.setEntryTime(rs.getTimestamp("entryTime"));
	    			}else param= null;
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				param=null;
	    				e.printStackTrace();
	    			}
	        		return param;
	        }
	        public Timestamp threeMonthDate(Timestamp date) {
	        	
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(date);
	            calendar.set(Calendar.DAY_OF_MONTH, 1);
	            calendar.add(Calendar.MONTH, 4);
	            Timestamp vTime = new Timestamp(calendar.getTime().getTime()-24*60*60*1000);
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
	            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            try {
	            vTime=	new Timestamp(sdf1.parse(sdf.format(vTime)).getTime());
	    		} catch (ParseException e) {
	    			// TODO 自动生成的 catch 块
	    			e.printStackTrace();
	    		}
	            return vTime;
	          
	        }

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
	        
	        public Timestamp threeMonth(Timestamp date) {
	        	 Calendar calendar = Calendar.getInstance();
	             calendar.setTime(date);
	             calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
	             calendar.add(Calendar.MONTH, 3);
	             Timestamp vTime = new Timestamp(calendar.getTime().getTime());
	             return vTime;
	        }
	        
	    	public boolean getMonthEnd(Connection conn,Timestamp date){
	    		boolean b=false;
	    		TimeParam tp = getTimeParam(conn);
	    		int num =(int) tp.getWeekNum();
	    		 Timestamp date1 = new Timestamp(date.getTime()+num*24*60*60*1000);
	             Calendar calendar = Calendar.getInstance();
	             calendar.setTime(date);
	             Calendar calendar1 = Calendar.getInstance();
	             calendar1.setTime(date1);
	             int currentMonth = calendar.get(Calendar.MONTH)+1;
	             int month = calendar1.get(Calendar.MONTH)+1;
	             if(currentMonth<month) b =true;
	    		return b;
	    	}
	        
	    	public boolean getMonthStart(Connection conn,Timestamp date){
	    		boolean b=false;
	    		TimeParam tp = getTimeParam(conn);
	    		int num =(int) tp.getWeekNum();
	    		 Timestamp date1 = new Timestamp(date.getTime()-num*24*60*60*1000);
	             Calendar calendar = Calendar.getInstance();
	             calendar.setTime(date);
	             Calendar calendar1 = Calendar.getInstance();
	             calendar1.setTime(date1);
	             int currentMonth = calendar.get(Calendar.MONTH)+1;
	             int month = calendar1.get(Calendar.MONTH)+1;
	             if(currentMonth>month) b =true;
	    		return b;
	    	}
	    	
	        public InventoryCenter getInventoryCenter(Connection conn,String userId,String productId){
	        	InventoryCenter st = null;
	        	try {
	        	String sql = "select * from inventory_center where userId='"+userId+"' and productId='"+productId+"' for update";
				stmt = conn.createStatement();
				rs =  stmt.executeQuery(sql);
				if(rs.next()){
					 st = new InventoryCenter();
					st.setId(rs.getInt("id"));
					st.setUid(rs.getInt("uId"));
					st.setUserId(rs.getString("userId"));
					st.setProductId(rs.getString("productId"));
					st.setProductName(rs.getString("productName"));
					st.setProductType(rs.getString("productType"));
					st.setTotalNum(rs.getInt("totalNum"));
					st.setNum(rs.getInt("num"));
				}else st =null;
	        	} catch (SQLException e) {
					// TODO Auto-generated catch block
	        		try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
	        	return st;
	        }
	        
	        public SettleStock getSettleForWeekTag(Connection conn,int weekTag,String forupdate){
	        	SettleStock st = null;
	        	try {
	        	String sql = "select * from settle_stock where week_tag='"+weekTag+"' "+forupdate;
				stmt = conn.createStatement();
				rs =  stmt.executeQuery(sql);
				if(rs.next()){
					 st = new SettleStock();
					st.setId(rs.getInt("id"));
					st.setWeekTag(rs.getInt("week_tag"));
					st.setState(rs.getInt("state"));
					st.setStartTime(rs.getTimestamp("start_time"));
					st.setEndTime(rs.getTimestamp("end_time"));
					st.setEntryTime(rs.getTimestamp("entry_time"));
				}else st =null;
	        	} catch (SQLException e) {
					// TODO Auto-generated catch block
	        		try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
	        	return st;
	        }
	        
	        public SettleStock getSettleForTime(Connection conn,Timestamp startTime,String forupdate){
	        	SettleStock st = null;
	        	try {
	        	String sql = "select * from settle_stock where start_time='"+startTime+"' "+forupdate;
				stmt = conn.createStatement();
				rs =  stmt.executeQuery(sql);
				if(rs.next()){
					 st = new SettleStock();
					st.setId(rs.getInt("id"));
					st.setWeekTag(rs.getInt("week_tag"));
					st.setState(rs.getInt("state"));
					st.setStartTime(rs.getTimestamp("start_time"));
					st.setEndTime(rs.getTimestamp("end_time"));
					st.setEntryTime(rs.getTimestamp("entry_time"));
				}else st =null;
	        	} catch (SQLException e) {
					// TODO Auto-generated catch block
	        		try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
	        	return st;
	        }
	        
	        
			public String insetWRewardDetail( User user,
					User user1, int type,double amount, String remark, Settle st,
					Timestamp date) {
				// TODO 自动生成的方法存根
				String sql = "insert into wreward_detail(uId,userId,userName,type,userById,userByUserId,userByUserName,amount,remark,weekTag,startTime,endTime,entryTime) values('"
							+user.getId()+"','"+user.getUserId()+"','"+user.getUserName()+"','"+type+"','"+user1.getId()+"','"+user1.getUserId()+"','"+user1.getUserName()+"','"+amount+"','"+remark
							+"','"+st.getWeekTag()+"','"+st.getStartTime()+"','"+st.getEndTime()+"','"+date+"')";
			
				return sql;
			}
			
			public Center getCenter(Connection conn, String centerId) {
				// TODO 自动生成的方法存根
				Center center = null;
				try{
					String sql = "select * from center where center_id='"+centerId+"' and state='2'";
					stmt = conn.createStatement();
					rs =  stmt.executeQuery(sql);
					if(rs.next()){
						center = new Center();
						center.setId(rs.getInt("id"));
						center.setCenterName(rs.getString("center_name"));
						center.setUserId(rs.getString("user_id"));
						center.setUserName(rs.getString("user_name"));
						center.setType(rs.getInt("type"));
						center.setState(rs.getInt("state"));
						center.setCenterId(rs.getString("center_id"));
					}
				}catch (SQLException e) {
					// TODO Auto-generated catch block
	        		try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
						
				return center;
			}
			
			  public Promotion getPromotion(Connection conn){
				  Promotion param = new Promotion();
			        	try {
			        		String sql = "select * from promotion";
			    			stmt = conn.createStatement();
			    			rs = stmt.executeQuery(sql);
			    			if(rs.next()){
			    				param.setId(rs.getInt("id"));
				    			param.setUid(rs.getInt("uid"));
				    			param.setUserId(rs.getString("userId"));
			    				param.setAmount_1(rs.getInt("amount_1"));
			    				param.setAmount_2(rs.getInt("amount_2"));
			    				param.setAmount_3(rs.getInt("amount_3"));
			    				param.setAmount_4(rs.getInt("amount_4"));
			    				param.setAmount_5(rs.getInt("amount_5"));
				    			param.setStartTime(rs.getTimestamp("startTime"));
				    			param.setEndTime(rs.getTimestamp("endTime"));
			    				param.setEntryTime(rs.getTimestamp("entryTime"));
			    				param.setComments(StringUtil.notNull(rs.getString("comments")));
			    			}else param= null;
			    			} catch (SQLException e) {
			    				// TODO Auto-generated catch block
			    				param=null;
			    				e.printStackTrace();
			    			}
			        		return param;
			        }
			  
			  public Promotion getPromotion(){
				  DBConn db = new DBConn();
				
				  Promotion param = new Promotion();
			        	try {
			        		if(db.createConn()){
			        			  conn = db.getConnection();
				        		String sql = "select * from promotion";
				    			stmt = conn.createStatement();
				    			rs = stmt.executeQuery(sql);
				    			if(rs.next()){
				    				param.setId(rs.getInt("id"));
					    			param.setUid(rs.getInt("uid"));
					    			param.setUserId(rs.getString("userId"));
				    				param.setAmount_1(rs.getInt("amount_1"));
				    				param.setAmount_2(rs.getInt("amount_2"));
				    				param.setAmount_3(rs.getInt("amount_3"));
				    				param.setAmount_4(rs.getInt("amount_4"));
				    				param.setAmount_5(rs.getInt("amount_5"));
					    			param.setStartTime(rs.getTimestamp("startTime"));
					    			param.setEndTime(rs.getTimestamp("endTime"));
				    				param.setEntryTime(rs.getTimestamp("entryTime"));
				    				param.setComments(StringUtil.notNull(rs.getString("comments")));
				    			}else param= null;
			        		}else param = null;
			    			} catch (SQLException e) {
			    				// TODO Auto-generated catch block
			    				param=null;
			    				e.printStackTrace();
			    			}
			        		return param;
			        }
			  
			  public Timestamp getMonthStartTime(Timestamp date){
				  Calendar calendar = Calendar.getInstance();  
			        calendar.setTime(date);
			        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  
			        Date date1 = calendar.getTime(); 
			        Timestamp time = new Timestamp(date1.getTime());  
			        String str = StringUtil.parseToString(time, DateUtil.yyyyMMdd);
			        Timestamp re_time = new Timestamp(StringUtil.parseToDate(str+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
			       return re_time;
			  }
			  
			  public Timestamp getMonthEndTime(Timestamp date){
				  Calendar calendar = Calendar.getInstance();  
			        calendar.setTime(date);
			        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); 	
			        Date date1 = calendar.getTime(); 
			        Timestamp time = new Timestamp(date1.getTime());  
			        String str = StringUtil.parseToString(time, DateUtil.yyyyMMdd);
			        Timestamp re_time = new Timestamp(StringUtil.parseToDate(str+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
			       return re_time;
			  }
			  
			  public Timestamp getPreWeekStartTime(){
				  Calendar calendar = Calendar.getInstance();  
				  //n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
				  int n = -1;
				  calendar.add(Calendar.DATE, n*7);
				  calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
			        Date date1 = calendar.getTime(); 
			        Timestamp time = new Timestamp(date1.getTime());  
			        String str = StringUtil.parseToString(time, DateUtil.yyyyMMdd);
			        Timestamp re_time = new Timestamp(StringUtil.parseToDate(str+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
			       return re_time;
			  }
			  
			  public Timestamp getPreWeekEndTime(){
				  Calendar calendar = Calendar.getInstance();  
				  //n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
				  int n = 0;
				  calendar.add(Calendar.DATE, n*7);
				  calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
			        Date date1 = calendar.getTime(); 
			        Timestamp time = new Timestamp(date1.getTime());  
			        String str = StringUtil.parseToString(time, DateUtil.yyyyMMdd);
			        Timestamp re_time = new Timestamp(StringUtil.parseToDate(str+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
			       return re_time;
			  }
			  
			  public boolean sendSMS(String tel,String content){
					boolean b =false;
					String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
					HttpClient client = new HttpClient(); 
					PostMethod method = new PostMethod(Url);

					client.getParams().setContentCharset("GBK");
					method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");

					NameValuePair[] data = {//提交短信
						    new NameValuePair("account", "C25683089"), //查看用户名请登录用户中心->验证码、通知短信->帐户及签名设置->APIID
						    new NameValuePair("password", "aa0f6db484efc0b7e340902efc4dd8ac"),  //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
						    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
						    new NameValuePair("mobile", tel), 
						    new NameValuePair("content", content),
					};
					method.setRequestBody(data);

					try {
						client.executeMethod(method);
						
						String SubmitResult =method.getResponseBodyAsString();

						//System.out.println(SubmitResult);

						Document doc = DocumentHelper.parseText(SubmitResult);
						Element root = doc.getRootElement();

						String code = root.elementText("code");

						 if("2".equals(code)){
							b=true;
						}

					} catch (HttpException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					 return b;
					
				}
			  
			  public ShortMessage  findByType(Connection conn,String type){
				  ShortMessage sm =new ShortMessage();
				  try {
				  String sql = "select * from short_message where type='"+type+"'";
				  
					stmt = conn.createStatement();
				
				  rs = stmt.executeQuery(sql);
				  if(rs.next()){
					  sm.setId(rs.getInt("id"));
					  sm.setType(rs.getString("type"));
					  sm.setTel(rs.getString("tel"));
					  sm.setShortMsg(rs.getString("short_msg"));
					  sm.setEntryTime(rs.getTimestamp("entry_time"));
				  }else{
					  sm=null;
				  }
				  } catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
						sm =null;
					}
				  return sm;
			  }
			  
			  public void sendSMS(Connection conn,String type){
				  ShortMessage sm = findByType(conn, type);
						if(sm!=null){
						String[] tel = sm.getTel().split(",");
						for(int i=0;i<tel.length;i++){
							if(!tel[i].equals("")){
								sendSMS(tel[i], sm.getShortMsg());
							}
						}
					}
			  }
			  
			  public void sendSMS(Connection conn,List<String> tel,String type){
				  ShortMessage sm = findByType(conn, type);
						if(sm!=null){
						for(int i=0;i<tel.size();i++){
							sendSMS(tel.get(i), sm.getShortMsg());
							
						}
					}
			  }
			  
			  public void sendSMS(Connection conn,String tel,String type){
				  ShortMessage sm = findByType(conn,type);
						if(sm!=null){
							System.out.println(sendSMS(tel, sm.getShortMsg()));
					}
			  }
			  
			  public boolean  isShop(){
				  boolean b = true;
				  String dates = "19:00-24:00";
				  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				  String currentDate = sdf.format(new Date());
				  String[] dateArr = dates.split("-");
				  try {
					     Date currDate = sdf.parse(currentDate);//当前时间
					     Date startDate = sdf.parse(dateArr[0]);//每节开始时间
					     Date endDate = sdf.parse(dateArr[1]);//每节结束时间
					     if(currDate.after(startDate) && currDate.before(endDate)){
					    	 b=false;
					     }
					    } catch (ParseException e) {
					    	b=false;
					     e.printStackTrace();
					     
					   }
				  return b;
				  
			  }
			  
			  public String  saveEmoneyDetail(Connection conn,Branch user,double amount,double balance,int payType,String tradeType,String summary,Timestamp entryTime) throws SQLException{
					
					String sql = "insert into emoneyDetail(userId,userName,amount,balance,payType,tradeType,summary,entryTime)"
							+ " values('"+user.getCode()+"','"+user.getName()+"','"+amount+"','"+balance+"','"+payType+"','"+tradeType
							+"','"+summary+"','"+entryTime+"')";
						
					return sql;
			}
			  
			  public String  saveChargeApply(Connection conn,Branch user,String admin,String accountId,String accountName,String bankName,double emoney,String remark,int type,int state,Timestamp date,Timestamp reviewTime) throws SQLException{
					String cid = "C"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(getRandom(10,99));
					String sql = "insert into chargeApply(applyId,userId,userName,accountId,accountName ,bankName,amount,type,remark,adminByReviewerId,applyTime,reviewTime,state)"
							+ " values('"+cid+"','"+user.getCode()+"','"+user.getName()+"','"+accountId+"','"+accountName+"','"+bankName+"','"+emoney+"','"+type+"','"+remark+"','"+admin
							+"','"+date+"','"+reviewTime+"','"+state+"')";
					return sql;
				}
			  
			  public int getRandom(int min,int max){
			        Random random = new Random();
			        int s = random.nextInt(max)%(max-min+1) + min;
			        return s;
			}
			  
}
