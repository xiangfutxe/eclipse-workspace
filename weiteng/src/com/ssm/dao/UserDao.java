package com.ssm.dao;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;

import com.ssm.pojo.Settle;
import com.ssm.pojo.UidPool;
import com.ssm.pojo.OrderDetail;
import com.ssm.mapper.AddressMapper;
import com.ssm.mapper.AdminLogMapper;
import com.ssm.mapper.CenterMapper;
import com.ssm.mapper.JoinInfoMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderDetailMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.ProductStockMapper;
import com.ssm.mapper.PromotionMapper;
import com.ssm.mapper.SettleMapper;
import com.ssm.mapper.UidPoolMapper;
import com.ssm.mapper.UserLogMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.mapper.WeixinUserInfoMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.Address;
import com.ssm.pojo.AdminLog;
import com.ssm.pojo.Center;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.Order;
import com.ssm.pojo.Param;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductStock;
import com.ssm.pojo.Promotion;
import com.ssm.pojo.User;
import com.ssm.pojo.UserLog;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WSettle;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.Constants;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.StringUtil;
import com.ssm.weixin.pojo.WeixinOauth2Token;
import com.ssm.weixin.utils.AdvancedUtil;
import com.ssm.weixin.utils.SignUtil;
import com.ssm.utils.ArithUtil;
public class UserDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
	    //创建能执行映射文件中sql的sqlSession
	    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	    JoinInfoMapper joinInfoMapper = sqlSession.getMapper(JoinInfoMapper.class);
	    WSettleMapper wsMapper = sqlSession.getMapper(WSettleMapper.class);
	    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
	    OrderDetailMapper orderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
	    ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
	    ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);
	    MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);
	    AddressMapper adrMapper = sqlSession.getMapper(AddressMapper.class);
	    CenterMapper centerMapper = sqlSession.getMapper(CenterMapper.class);
	    AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
	    PromotionMapper proMapper = sqlSession.getMapper(PromotionMapper.class);
	    SettleMapper stMapper = sqlSession.getMapper(SettleMapper.class);
	    WRewardMapper wrdMapper = sqlSession.getMapper(WRewardMapper.class);
	    UserLogMapper userLogMapper = sqlSession.getMapper(UserLogMapper.class);
	    WeixinUserInfoMapper weixinMapper = sqlSession.getMapper(WeixinUserInfoMapper.class);
	    UidPoolMapper poolMapper = sqlSession.getMapper(UidPoolMapper.class);	  
	    ProductStockMapper stockMapper = sqlSession.getMapper(ProductStockMapper.class);


	    ICustomService cs = new CustomService();
	    public User login(String userId,String password,String ip){
		    User user= null;
	    	try{
	    		user = userMapper.login(userId, password);
	    		
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	    	}
	            return user;
	    }
	    
	    public User user_detail(Integer id){
	    	User user= null;
	    	try{
    		user = userMapper.selectById(id);
    		sqlSession.commit();
		    }catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
    		sqlSession.close();
	    	}
            return user;
	    }
	    
	    public int maxId(User user){
	    	int maxId=0;
	    	try{
	    		Map<String,Object> params = new HashMap<>();
	    		params.put("user",user);
    		Integer max =  userMapper.maxId(params);
    		if(max==null) maxId=0;
    		else maxId = max;
    		sqlSession.commit();
		    }catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
    		sqlSession.close();
	    	}
            return maxId;
	    }
	    
	    public User findUserInfoByUseId(String userId){
	    	User user= null;
	    	try{
    		user = userMapper.selectUserInfoByUserId(userId);
    		sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
    		sqlSession.close();
	    	}
            return user;
	    }
	    
	    public User findUserInfoById(Integer id){
	    	User user= null;
	    	try{
    		user = userMapper.selectUserInfoById(id);
    		sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
    		sqlSession.close();
	    	}
            return user;
	    }
	    
	    public Pager findUserByPage(User user,Pager pager){
	    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("user",user);
			int recordCount = userMapper.countAll(params);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<User> users = userMapper.selectByPage(params);
			pager.setResultList(users);
			sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		sqlSession.rollback();
	    	}finally{
	    		sqlSession.close();
	        }
			return pager;
		}
	    
	    public Pager findUsersByPage(User user,Pager pager){
	    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("user",user);
			int recordCount = userMapper.countAll(params);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<User> users = userMapper.selectByPage(params);
			pager.setResultList(users);
			sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		sqlSession.rollback();
	    	}finally{
	    		sqlSession.close();
	        }
			return pager;
		}
	    
	    public List<User> findUserByList(User user){
	    	List<User> users = null;
	    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("user",user);
			 users = userMapper.selectByList(params);
			sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		
	    	}finally{
	    		sqlSession.close();
	        }
			return users;
		}
	    
	    public List<User> findUserBySql(String sql){
	    	List<User> users = null;
	    	try{
			
			 users = userMapper.selectUserBySql(sql);
			sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		
	    	}finally{
	    		sqlSession.close();
	        }
			return users;
		}
	    
	    public User findByUserId(String userId){
	    	User users = null;
	    	try{
			 users = userMapper.selectAllByUserId(userId);
			 sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
			return users;
		}
	    
	    public User findUsersByUserId(String userId){
	    	User users = null;
	    	try{
			 users = userMapper.selectUsersByUserId(userId);
			 sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
			return users;
		}
	    
	    public String user_update_referee(String userId,String userId1){
	    	String str=""; 
	    	try{
	    		Timestamp date=  new Timestamp(new Date().getTime());
			 User user = userMapper.selectByUserIdForUpdate(userId);
			 User user1 = userMapper.selectUsersByUserId(userId1);
			 if(user1.getEntryTime().getTime()-user.getEntryTime().getTime()>0) {
				 if(user1.getRefereeId()==0) {
					 String refereeNode="";
					 String refereeAll="";
					 double integral =10;
					 if(StringUtil.notNull(user.getRefereeNode()).equals(""))
						 refereeNode=String.valueOf(user.getId());
					 else refereeNode=user.getRefereeNode()+","+String.valueOf(user.getId());
					 if(StringUtil.notNull(user.getRefereeAll()).equals(""))
						 refereeAll=String.valueOf(user1.getId());
					 else refereeAll=user.getRefereeAll()+","+String.valueOf(user1.getId());
					 AccountDetail ad = cs.returnAccountDetail(user, integral, ArithUtil.add(user.getIntegral(),integral), 1, Constants.MONEY_DETAIL_YYPE_2, "邀请会员"+user1.getUserId()+"赠送", date);
					 String tableName = Constants.INTEGRALDETAIL_TABLE;
					 Map<String,Object> params = new HashMap<>();
					 params.put("account",ad);
					 params.put("tableName", tableName);
					 Integer up =moneyMapper.save(params);
					 if(up!=null&&up>0)	{
						 String sql = "update users set integral=integral+'"+integral+"',referee_all='"+refereeAll+"',referee_num=referee_num+1,version=version+1 where id='"+user.getId()+"' and version='"+user.getVersion()+"'";
						 Integer up1 = userMapper.updateForSql(sql);
						 if(up1!=null&&up1>0)	{
							 String sql1 = "update users set referee_id='"+user.getId()+"',referee_user_id='"+user.getUserId()+"',referee_node='"+refereeNode+"',version=version+1 where id='"+user1.getId()+"' and version='"+user1.getVersion()+"'";
							 Integer up2 = userMapper.updateForSql(sql1);
							 if(up2!=null&&up2>0)	{
								 String sql2 = "update users set referee_id='"+user.getId()+"'  where user_id='"+user1.getUserId()+"'";
								 Integer up3 = userMapper.updateForSql(sql2);
								 if(up3!=null&&up3>0)	{
								 }else str = "微信邀请人信息更新失败。";
							 }else str = "会员邀请人信息更新失败。";
						 }else str = "会员信息更新失败。";
					 }else str = "积分账户保存失败。";
				 }else str = "该会员会员时间已经存在邀请人。";
			 }else str = "会员时间关注时间不能早已邀请人。";
			 sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		str="数据操作异常。";
	    	}finally{
	    		sqlSession.close();
	        }
			return str;
		}
	    
	    public User user_report(String userId,Timestamp date) {
	    	User user1 = new User();
	    	try {
				user1 = userMapper.selectUsersByUserId(userId);
				if(user1!=null)	{
	    		Timestamp curTime = new Timestamp(StringUtil.parseToDate(StringUtil.parseToString(date, DateUtil.yyyyMMdd)+" 00:00:00",DateUtil.yyyyMMddHHmmss).getTime());
				User updateUser = new User();
				updateUser.setId(user1.getId());
				updateUser.setVersion(user1.getVersion());
				double integral =0;
				int viewNum = 0;
					if(user1.getPreTime()!=null) {
						int time_day = (int) ((curTime.getTime()-user1.getPreTime().getTime())/(24*60*60*1000));
							if(time_day>=1) {
								if(time_day==1) {
									integral = user1.getViewNum()%7;
									if(integral==0&&user1.getViewNum()>1) integral=7;
									viewNum = user1.getViewNum()+1;
								}else {
									integral=1;
									viewNum=1;
								}
							updateUser.setIntegral(ArithUtil.add(integral,user1.getIntegral()));
							updateUser.setPreTime(curTime);
							updateUser.setViewNum(viewNum);
							}
					}else {
						viewNum=1;
						integral=1;
						updateUser.setIntegral(ArithUtil.add(integral,user1.getIntegral()));
						updateUser.setPreTime(curTime);
						updateUser.setViewNum(viewNum);
					}
					
					if(updateUser.getPreTime()!=null) {
						AccountDetail ad = cs.returnAccountDetail(user1, integral, updateUser.getIntegral(), 1, Constants.MONEY_DETAIL_YYPE_3, "连续签到"+viewNum+"天", date);
						 String tableName = Constants.INTEGRALDETAIL_TABLE;
						 Map<String,Object> params = new HashMap<>();
						 params.put("account",ad);
				    	params.put("tableName", tableName);
				    	Integer up1 =moneyMapper.save(params);
				    	if(up1!=null&&up1>0) {
				    		Integer up2 = userMapper.updateUser(updateUser);
				    		if(up2!=null&&up2>0) {
				    			user1.setIntegral(updateUser.getIntegral());
				    			user1.setPreTime(updateUser.getPreTime());
				    			user1.setViewNum(updateUser.getViewNum());
				    			sqlSession.commit();
				    		}else {
				    			user1=null;
				    			sqlSession.rollback();
				    		}
				    	}else {
			    			user1=null;
			    			sqlSession.rollback();
			    		}
					}else {
		    			user1=null;
		    			sqlSession.rollback();
		    		}
				}
	    	}catch(Exception e) {
	    		user1 = null;
	    		e.printStackTrace();
	    	}finally {
	    		sqlSession.close();
	    	}
	    	return user1;
	    }
	
	    
	    public User findUsersById(Integer id){
	    	User users = null;
	    	try{
			 users = userMapper.selectUsersById(id);
			 sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
			return users;
		}
	    
	    public User findById(Integer id){
	    	User users = null;
	    	try{
			 users = userMapper.selectById(id);
			 sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
			return users;
		}
	    
	 
	    public List<User> findUsersByProperty(String property,Integer id){
	    	List<User> users = null;
	    	try{
			 users = userMapper.selectUsersForProperty(property, id);
			 sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
			return users;
		}
	    
	    public void initUser(){
	    	try{
	    	Timestamp date = new Timestamp(new Date().getTime());
	    	User user = new User();
			user.setUserId("600001");
			user.setUserName("user1");
			user.setSex("男");
			user.setNumId("1111111111111111111");
			user.setRankJoin(5);
			user.setOpenId("12312");
			user.setRefereeId(1);
			user.setRefereeUserId("600000");
			user.setPassword(MD5.GetMD5Code("111111"));
			user.setPassword2(MD5.GetMD5Code("111111"));
			user.setState(2);
			user.setEntryTime(date);
			WeixinUserInfo weixin = new WeixinUserInfo();
			weixin.setUserId(user.getUserId());
			weixin.setNickName(user.getUserName());
			weixin.setOpenId(user.getOpenId());
			weixin.setRefereeId(1);
			weixin.setQrId(1000);
			int id = userMapper.saveUser(user);
			if(userMapper.saveUserInfo(user)!=null){
				if(weixinMapper.save(weixin)>0)
					sqlSession.commit();
			}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
	    } 
	    
	    public int saveUser(User user,WeixinUserInfo weixin){
	    	int count= 0;
	    	try{
	    		Param param = paramMapper.selectByName("虚拟会员");
	    		User refereeUser=null;
	    		if(!user.getRefereeUserId().equals("")) {
	    			refereeUser = userMapper.selectUsersByUserId(user.getRefereeUserId());
	    		}
	    		int refereeId = 0;
	    		int refereeVirtualNum=0;
	    		User updateUser = new User();
		    	String refereeNode = "";

	    		if(refereeUser!=null) {
	    			user.setRefereeId(refereeUser.getId());
	    			weixin.setRefereeId(refereeUser.getId());
	    			updateUser.setId(refereeUser.getId());
	    			updateUser.setVersion(refereeUser.getVersion());
	    			updateUser.setRefereeNum(refereeUser.getRefereeNum());
	    			updateUser.setRefereeNum1(refereeUser.getRefereeNum1());
	    			updateUser.setRefereeNum2(refereeUser.getRefereeNum2());
	    			updateUser.setRefereeNum3(refereeUser.getRefereeNum3());
	    			updateUser.setRefereeNum4(refereeUser.getRefereeNum4());
	    			updateUser.setRefereeNum5(refereeUser.getRefereeNum5());
	    			updateUser.setRefereeNum6(refereeUser.getRefereeNum6());
	    			updateUser.setRefereeNum7(refereeUser.getRefereeNum7());
	    			updateUser.setRefereeVirtualNum(refereeUser.getRefereeVirtualNum());
	    			if(StringUtil.notNull(refereeUser.getRefereeNode()).equals("")) refereeNode=String.valueOf(refereeUser.getId());
					else refereeNode=refereeUser.getRefereeNode()+","+String.valueOf(refereeUser.getId());
	    			if(user.getRankJoin()==1) {
	    				updateUser.setRefereeNum1(updateUser.getRefereeNum1()+1);
	    				refereeVirtualNum = param.getAmount_1().intValue();
	    			}else if(user.getRankJoin()==2) {
	    				updateUser.setRefereeNum2(updateUser.getRefereeNum2()+1);
	    				refereeVirtualNum = param.getAmount_2().intValue();
	    			}else if(user.getRankJoin()==3) {
	    				updateUser.setRefereeNum3(updateUser.getRefereeNum3()+1);
	    				refereeVirtualNum = param.getAmount_3().intValue();
	    			}else if(user.getRankJoin()==4) {
	    				updateUser.setRefereeNum4(updateUser.getRefereeNum4()+1);
	    				refereeVirtualNum = param.getAmount_4().intValue();
	    			}else if(user.getRankJoin()==5) {
	    				updateUser.setRefereeNum5(updateUser.getRefereeNum5()+1);
	    				refereeVirtualNum = param.getAmount_5().intValue();
	    			}else if(user.getRankJoin()==6) {
	    				updateUser.setRefereeNum6(updateUser.getRefereeNum6()+1);
	    				refereeVirtualNum = param.getAmount_6().intValue();
	    			}else if(user.getRankJoin()==7) {
	    				updateUser.setRefereeNum7(updateUser.getRefereeNum7()+1);
	    				refereeVirtualNum = param.getAmount_7().intValue();
	    			}
	    			updateUser.setRefereeVirtualNum(refereeUser.getRefereeVirtualNum()+refereeVirtualNum);
	    		}else {
	    			user.setRefereeId(0);
	    			weixin.setRefereeId(0);
	    			updateUser=null;
	    		}
	    	Timestamp date = new Timestamp(new Date().getTime());
	    	int uid = getUId(date);
	    	user.setUserId(String.valueOf(600000+uid));
	    	TokenDao tokenDao = new TokenDao();
	    	  String accessToken  = tokenDao.getToken(date);
	    	String ticket = AdvancedUtil.createPermanentQRCode(accessToken, uid);
      		String savePath="/home/yezuser/tomcat/webapps/ROOT/upload/qr";
      		AdvancedUtil.getQRCode(ticket, savePath);
      		String qr_img = ticket+".jpg";
	    	user.setRefereeNode(refereeNode);
	    	user.setEntryTime(date);
	    	user.setEndTime(date);
			weixin.setQrId(uid);
			weixin.setQrImg(qr_img);
			weixin.setUserId(user.getUserId());
			Integer up = userMapper.saveUser(user);
			int error=1;
			if(up!=null&&up>0) {
				if(updateUser!=null) {
					String refereeAll = "";
					if(StringUtil.notNull(updateUser.getRefereeAll()).equals("")) refereeAll=String.valueOf(user.getId());
					else refereeAll=updateUser.getRefereeAll()+","+String.valueOf(user.getId());
					updateUser.setRefereeAll(refereeAll);
					Integer err = userMapper.updateUser(updateUser);
					if(err!=null&&err>0) {
						error=1;
					}else error=0;
				}
			if(error>0) {
			Integer up1 =userMapper.saveUserInfo(user);
				if(up1!=null&&up1>0) {
				Integer up2 = weixinMapper.save(weixin);
				if(up2!=null&up2>0) {
					count = uid;
					sqlSession.commit();
				}else sqlSession.rollback();
				}else sqlSession.rollback();
			}else sqlSession.rollback();
			}else sqlSession.rollback();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		sqlSession.rollback();
	    	}finally{
	    		sqlSession.close();
	        }
	    	return count;
	    } 
		
		 public int updateUsers(User user){
			 int num =0;
			 try{
				 num = userMapper.updateUser(user);
				 sqlSession.commit();
			 }catch(Exception e){
				 sqlSession.rollback();
			 }finally{
				 sqlSession.close();
			 }
			 return num;
			 
		 }
		 
		 public int updateUserInfo(User user){
			 int num =0;
			 try{
				 User userinfo = userMapper.selectUserInfoByUserId(user.getUserId());
				 if(userinfo!=null) {
					 user.setId(userinfo.getId());
					 num = userMapper.updateUserInfo(user);
					 if(!user.getUserName().equals("")) {
						  userMapper.updateForSql("update users set user_name='"+user.getUserName()+"' where user_id='"+user.getUserId()+"'");
					 }
				 sqlSession.commit();
				 }
			 }catch(Exception e){
				 sqlSession.rollback();
				 e.printStackTrace();
			 }finally{
				 sqlSession.close();
			 }
			 return num;
			 
		 }
		 
		 public int updatePsw(String userId,String password,int type){
			 int num =0;
			 try{
				 String pre = "";
				 if(type==1) pre ="password";
				 else  if(type==2) pre ="password2";
				 String sql="update user_info set "+pre+"='"+password+"' where user_id='"+userId+"'";
				 System.out.println(sql);
				Integer num1 = userMapper.updateForSql(sql);
				 System.out.println("num:"+num);
				 if(num1!=null&num1>0) {
					 sqlSession.commit();
					 num=num1;
				 }
			 }catch(Exception e){
				e.printStackTrace();
				 sqlSession.rollback();
			 }finally{
				 sqlSession.close();
			 }
			 return num;
			 
		 }
		 
		 public String updateUser(User userinfo){
			 String str ="";
			 try{
				if(userMapper.updateUserInfo(userinfo)>0){
					userMapper.updateForSql("update users set user_name='"+userinfo.getUserName()+"' where user_id='"+userinfo.getUserId()+"'");
					userMapper.updateForSql("update weixin_userinfo set nick_name='"+userinfo.getNickName()+"' where user_id='"+userinfo.getUserId()+"'");

						str= "yes";
						 sqlSession.commit();
				}else{
					
					 str="会员基本信息更新失败。";
					 sqlSession.rollback();
				}
				 
			 }catch(Exception e){
				 str="系统繁忙，请稍后再试。";
				 sqlSession.rollback();
				 e.printStackTrace();
			 }finally{
				 sqlSession.close();
			 }
			 return str;
			 
		 }

		 public String user_rankJoin(String userId,Integer rankJoin,Timestamp date){
			 String str ="";
			 try{
		    			User user = userMapper.selectByUserId(userId);
		    			if(user!=null){
		    				User user1= new User();
		    				user1.setId(user.getId());
		    				if(rankJoin>user.getRankJoin()){
		    				user1.setRankJoin(rankJoin);
		    				JoinInfo joinInfo = new JoinInfo();
		    				joinInfo.setUid(user.getId());
		    				joinInfo.setUserId(user.getUserId());
		    				joinInfo.setUserName(user.getUserName());
		    				joinInfo.setState(user.getState());
		    				joinInfo.setOldRankJoin(user.getRankJoin());
		    				joinInfo.setNewRankJoin(rankJoin);
		    				joinInfo.setRid(user.getRefereeId());
		    				joinInfo.setTag(0);
		    				joinInfo.setEntryTime(date);
		    				if(userMapper.updateUser(user1)>0){
		    					if(joinInfoMapper.save(joinInfo)>0){
		    						sqlSession.commit();
		    						str="yes";
		    					}else{
		    						str="会员加盟信息保存失败。";
		    					}
		    				}else{
		    					str="会员等级信息变更失败。";
		    				}
		    				}else{
				    			str="当前会员等级不符合要求变更为新的等级，请核对。";
				    		}
		    			}else{
		    				str ="会员信息获取失败。";
		    			}

			 }catch(Exception e){
		    		str="系统繁忙中，请稍后再试。";
		    		e.printStackTrace();
		    		sqlSession.rollback();
		    	}finally {
		    		sqlSession.close();
		        }
			 return str;
			 }
		 
		
		 
		 public int updatePsw(String[] ids,String password,Integer type){
			 int num=0;
			 try{
				 for(int i=0;i<ids.length;i++){
					 String property="";
					 if(type==1)
						 property="password";
					 else  if(type==2)
						 property="password2";
					 User user1 = userMapper.selectById(Integer.valueOf(ids[i]));
					 if(user1!=null){
						 if(userMapper.updateUserInfoForProperty(property, password, "userId", user1.getUserId())!=null) num++;
					 }
				 }
				 if(num-ids.length==0) {
					 
				 }else{
					 num = 0;
					 sqlSession.rollback();
				 }
				 sqlSession.commit();
			 }catch(Exception e){
					num=0;
		    		e.printStackTrace();
		    		sqlSession.rollback();
			 }finally{
				 
			 }
			 return num;
		 }
		 
		
		 public String updateUsers(String[] ids,Integer isHide){
			 String str="";
			 int num=0;
			 try{
				 for(int i=0;i<ids.length;i++){
					 User user = new User();
					 user.setId(Integer.valueOf(ids[i]));
					num= num+ userMapper.updateUser(user);
				 }
				 if(num-ids.length==0) str = "yes";
				 else{
					 str = "数据更新有误。";
					 sqlSession.rollback();
				 }
				 sqlSession.commit();
			 }catch(Exception e){
					str="系统繁忙中，请稍后再试。";
		    		e.printStackTrace();
		    		sqlSession.rollback();
			 }finally{
				 
			 }
			 return str;
		 }
		 
		 public String updateLock(String[] ids,String payTag){
			 String msg = "";
			 int num=0;
			 try{
				 for(int i=0;i<ids.length;i++){
					 String property="";
					 User user1 = userMapper.selectById(Integer.valueOf(ids[i]));
					 if(user1!=null){
						 if(userMapper.updateOfUsers("payTag", payTag, "userId", user1.getUserId())!=null){
							 num++;
							 if(msg.equals("")) msg = user1.getUserId();
							 else msg = msg +","+user1.getUserId();
						 }
					 }
				 }
				 if(num-ids.length==0) {
					 if(payTag.equals("0"))
						 msg = msg+"等用户冻结成功，请及时通知会员！";
					 	else if(payTag.equals("1"))
						 msg = msg+"等用户解锁成功，请及时通知会员！";
					 	sqlSession.commit();
				 }else{
					 num = 0;
					 msg ="";
					 sqlSession.rollback();
				 }
				
			 }catch(Exception e){
					num=0;
					 msg ="数据操作有误";
		    		e.printStackTrace();
		    		sqlSession.rollback();
			 }finally{
				 sqlSession.close();
			 }
			 return msg;
		 }
		 
		 
		 
		 public String priceSummary(){
			 String msg = "";
		    	try{
		    		String error = "";
		    		double[] sum = {0,0,0};
		    		int max = stMapper.selectMaxWeek();
		    		if(max>0){
		    			for(int i=1;i<=max;i++){
		    				Map<String,Object> params = new HashMap<>();
		    	    		params.put("tableName","wreward_"+i);
		    	    		List<WReward> wlist = 	wrdMapper.selectByList(params);
		    	    		for(int j=0;j<wlist.size();j++){
		    	    			if(wlist.get(i).getAmount_9()>0){
		    	    				String sql1 = "update users set totalIncome = totalIncome+'"+wlist.get(i).getAmount_9()+"' where userId='"+wlist.get(i).getUserId()+"'";
		    	    				Integer up1 = userMapper.updateForSql(sql1);
		    		    			if(up1==null) error=wlist.get(i).getUserId()+"收入更新失败；<br>";
		    	    			}
		    	    		}

		    			}
		    		}
    				Map<String,Object> params1 = new HashMap<>();

    				JoinInfo jf = new JoinInfo();
    				jf.setState(2);
    				Timestamp startTime = new Timestamp(StringUtil.parseToDate("2017-10-01 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
    				Timestamp endTime = new Timestamp(StringUtil.parseToDate("2018-01-08 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());

    				jf.setStartTime(startTime);
    				jf.setEndTime(endTime);
    				params1.put("joinInfo", jf);
		    		List<JoinInfo> ulist = joinInfoMapper.selectAllByList(params1);
		    		for(int i=0;i<ulist.size();i++){
		    			if(ulist.get(i).getPrice()>0){
		    			String sql2 ="update wsettle set joinPriceTal = joinPriceTal +'"+ulist.get(i).getPrice()+"',joinPvTal = joinPvTal +'"+ulist.get(i).getPv()+"' where userId='"+ulist.get(i).getUserId()+"'";
		    			Integer up2=userMapper.updateForSql(sql2);
		    			if(up2==null) error=ulist.get(i).getUserId()+"加盟信息更新失败；<br>";
		    			}
		    		}
		    		if(error.equals("")){
		    			sqlSession.commit();
		    			msg = "数据导入成功，请核对！";
		    		}else{
		    			msg=error;
		    		}
		    	}catch(Exception e){
		    		msg ="数据导入异常，请核对。";
		    		e.printStackTrace();
		    		sqlSession.rollback();
		    	} finally {
					sqlSession.close();
		    	}
		    	return msg;
		 }
		 
		   public List<User> findRefereeListByUserId(String userId){
		    	List<User> users = new ArrayList<User>();
		    	try{
			    		if(!StringUtil.notNull(userId).equals("")){
			    			User user = userMapper.selectAllByUserId(userId);
			    			if(user!=null){
			    				users.add(user);
			    			Map<String,Object> params = new HashMap<>();
			    			List<User> ulist = userMapper.selectByList(params);
			    			for(int i=ulist.size()-1;i>=0;i--){
			    				String refereeNode = ulist.get(i).getRefereeNode();
			    				if(!StringUtil.notNull(refereeNode).equals("")){
			    					String[] str = refereeNode.split(",");
			    					for(int j=0;j<str.length;j++){
			    						if(!str[j].equals("")){
			    							if(str[j].equals(String.valueOf(user.getId()))){
			    								users.add(ulist.get(i));
			    								break;
			    							}
			    						}
			    					}
			    				}
			    			}
					sqlSession.commit();
			    			}else{
				    			users=null;
				    		}
		    		}else{
		    			users=null;
		    		}
		    	}catch(Exception e){
		    		users=null;
		    		e.printStackTrace();
		    		sqlSession.rollback();
		    	}finally{
		    		sqlSession.close();
		        }
				return users;
			}
		   
		   public String priceSummary_13_15(){
				 String msg = "";
			    	try{
			    		String error = "";
			    		double[] sum = {0,0,0};
			    		int max = 15;
			    		if(max>0){
			    			for(int i=13;i<=max;i++){
			    				Map<String,Object> params = new HashMap<>();
			    	    		params.put("tableName","wreward_"+i);
			    	    		List<WReward> wlist = 	wrdMapper.selectByList(params);
			    	    		int t=0;
			    	    		for(int j=0;j<wlist.size();j++){
			    	    			if(wlist.get(j).getAmount_9()>0){
			    	    				t++;
			    	    				String sql1 = "update users set totalIncome = totalIncome+'"+wlist.get(j).getAmount_9()+"' where userId='"+wlist.get(j).getUserId()+"'";
			    	    				Integer up1 = userMapper.updateForSql(sql1);
			    		    			if(up1==null) error=wlist.get(j).getUserId()+"收入更新失败；<br>";
			    	    			}
			    	    		}
			    	    		System.out.println("t:"+t);
			    			}
			    		}else{
			    			error="max获取失败。";
			    		}
	    			
			    		if(error.equals("")){
			    			sqlSession.commit();
			    			msg = "数据更新成功，请核对！";
			    		}else{
			    			msg=error;
			    		}
			    	}catch(Exception e){
			    		msg ="数据导入异常，请核对。";
			    		e.printStackTrace();
			    		sqlSession.rollback();
			    	} finally {
						sqlSession.close();
			    	}
			    	return msg;
			 }
		   
		   public int getUId(Timestamp date){
				int uid = 0;
				if (check_card_pool()) {
					uid = get_card_id(date);
				} else {
					uid = add_card_pool(date);
				}
				return uid;
			}
		 
		 public boolean check_card_pool() {
				Integer num = poolMapper.count();
				if(num!=null&&num > 0)
					return true;
				else
					return false;
			}
		 
		 public synchronized int getUserId(Timestamp date){
				int num = get_card_id(date);
				sqlSession.commit();
				sqlSession.close();
						return num;
			}
		 
		 public synchronized int get_card_id(Timestamp date){
				UidPool uid = poolMapper.getUid();
				int num = 0;
				if(uid!=null) {
					num = uid.getUid();
					uid.setEndTime(date);
					uid.setTag(1);
					poolMapper.update(uid);
				} else {
					add_card_pool(date);
					num = get_card_id(date);
				}
				return num;
			}
		 
		 public synchronized int add_card_pool(Timestamp date){
				Integer maxId = poolMapper.maxId();
				int num = 0;
				if(maxId==null) {
					maxId = 1000;
				}else if (maxId == 0) {
					maxId = 1000;
				}
				List<Integer> list = new ArrayList<Integer>();
				for (int i = 0; i < 3000; i++) {
					list.add(maxId + i);
				}
				int out = 0;
				int outIndex = 0;
				List<UidPool> ulist = new ArrayList<UidPool>();
				poolMapper.deleteByTag(1);
				for (int i = 0; i < 3000; i++) {
					Random ran = new Random();
					outIndex = ran.nextInt(list.size());
					out = list.get(outIndex);
					list.remove(outIndex);
					UidPool up = new UidPool();
					
					up.setUid(out+1);
					if(i==0) {
						num=up.getUid();
						up.setTag(1);
					}else up.setTag(0);
					up.setEntryTime(date);
					up.setEndTime(date);
					ulist.add(up);
					
					if((i+1)%800==0||(i+1)==3000) {
						Map<String,Object> params = new HashMap<>();
						params.put("list", ulist);
						poolMapper.insertAll(params);
						ulist = new ArrayList<UidPool>();
					}
				}
				return num;
			}
		 
		 public String updateRefereeId() {
			 String str="";
			 try {
				 Map<String,Object> params = new HashMap<>();
				 List<User> ulist = userMapper.selectUserByListForUpdate(params);
				 int t=0;
				 for(int i=0;i<ulist.size();i++) {
					 if(!StringUtil.notNull(ulist.get(i).getRefereeUserId()).equals("")) {
						 User user = userMapper.selectUsersByUserId(ulist.get(i).getRefereeUserId());
						 if(user!=null) {
							 String sql1 = "update users set referee_id='"+user.getId()+"' where user_id='"+ulist.get(i).getUserId()+"'";
							 userMapper.updateForSql(sql1);
							 String sql2 = "update weixin_userinfo set referee_id='"+user.getId()+"' where user_id='"+ulist.get(i).getUserId()+"'";
							 userMapper.updateForSql(sql2);
							 t++;
						 }
					 }
				 }
				 sqlSession.commit();
				 str="会员邀请人ID更新成功,合计更新人数为"+t+".";
			 }catch(Exception e) {
				 str="数据操作异常。";
				 sqlSession.rollback();
			 }finally {
				 sqlSession.close();
			 }
			 return str;
		 }
		 
		 public String updateRefereeNode() {
			 String str="";
			 try {
				 Map<String,Object> params = new HashMap<>();
				 List<User> ulist = userMapper.selectUserByListForUpdate(params);
				 int t=0;
				 int maxId = userMapper.maxId(params);
				 User[] user = new User[maxId+1];
				 for(int i=0;i<ulist.size();i++) {
					 int id = ulist.get(i).getId();
					 if(user[id]==null) user[id]=new User();
					 user[id].setId(ulist.get(i).getId());
					 user[id].setUserId(ulist.get(i).getUserId());
					 user[id].setRefereeAll("");
					 user[id].setRefereeNode("");
					 user[id].setRefereeUserId(StringUtil.notNull(ulist.get(i).getRefereeUserId()));
					 user[id].setRefereeId(ulist.get(i).getRefereeId());
				 }
				 for(int i=ulist.size()-1;i>=0;i--) {	
					 int id = ulist.get(i).getId();
					 if(user[id].getRefereeId()>0) {
						 int refereeId = user[id].getRefereeId();
						 if(user[refereeId]!=null) {
							 String refereeNode ="";
							 String refereeAll ="";
							 if(!StringUtil.notNull(user[refereeId].getRefereeNode()).equals("")) {
								 refereeNode = user[refereeId].getRefereeNode()+","+user[refereeId].getId();
							 }else {
								 refereeNode = String.valueOf(user[refereeId].getId());
							 }
							 if(!StringUtil.notNull(user[refereeId].getRefereeAll()).equals("")) {
								 refereeAll = user[refereeId].getRefereeAll()+","+user[id].getId();
							 }else {
								 refereeAll = String.valueOf(user[id].getId());
							 }
							 user[id].setRefereeNode(refereeNode);
							 user[refereeId].setRefereeAll(refereeAll);
						 }
						 }
					 }
				 for(int i=0;i<ulist.size();i++) {
					 int id = ulist.get(i).getId();
					 if(user[id].getRefereeId()>0) {
						 String sql = "update users set referee_node='"+user[id].getRefereeNode()+"',referee_all='"+user[id].getRefereeAll()+"' where id='"+user[id].getId()+"'";
						 userMapper.updateForSql(sql);
						 t++;
					 }
				 }
				 sqlSession.commit();
				 str="会员邀请关系更新成功,合计更新人数为"+t+".";
			 }catch(Exception e) {
				 str="数据操作异常。";
				 sqlSession.rollback();
			 }finally {
				 sqlSession.close();
			 }
			 return str;
		 }
		 
		 public String user_modify(String userId,String rankJoinstr,String cashstr,String cashNumStr,String integralStr,String rmoneyStr,String agentTagStr,String province,String city,String area,String entryTimeStr,String numStr) {
			 String str="";
			 try {
				 Timestamp date = new Timestamp(new Date().getTime());
				 User user = userMapper.selectUsersByUserId(userId);
				 if(user!=null) {
					 User refereeUser = userMapper.selectUsersByUserId(user.getRefereeUserId());
					 if(!rankJoinstr.equals("")) {
						 int rankJoin = Integer.valueOf(rankJoinstr);
						 if(user.getRankJoin()-rankJoin<0) {
	   	      				Param p1=paramMapper.selectByName("虚拟会员");
		      				if(p1!=null) {
	 	      				rankJoin = checkRankJoin(user,p1,rankJoin);
	 	      				String sql1= "update users set rank_join='"+rankJoin+"' where id='"+user.getId()+"'";
	 	      				userMapper.updateForSql(sql1);
		 	      				if(refereeUser!=null) {
		 	      					int count = rankJoin_up(user,refereeUser,p1,rankJoin);
		 	      					if(count==0)
		 	      						str+="推荐人推荐信息更新失败。";
		 	      				}
		      				}else str+="推荐会员信息指标参数获取失败。";
		      			}
					 }
					 if(!cashstr.equals("")) {
						 double cash =Double.valueOf(cashstr);
						 if(cash>0) {
							 String sql1= "update users set cash='"+cash+"' where id='"+user.getId()+"'";
		 	      				userMapper.updateForSql(sql1);
		 	      				AccountDetail ad = cs.returnAccountDetail(user, cash,cash, 1,Constants.MONEY_DETAIL_YYPE_0, "数据初始化导入", date);
		      						String tableName = Constants.CASHDETAIL_TABLE;
		      						Map<String,Object> params = new HashMap<>();
		      						params.put("account",ad);
		      						params.put("tableName", tableName);
		      						Integer up1 =moneyMapper.save(params);
		      						if(up1!=null&&up1>0) {
		      							
		      						}else str+="会员代金券账户保存失败。";
						 }
						 
					 }
					 if(!cashNumStr.equals("")) {
						 int cashNum =Integer.valueOf(cashNumStr);
						 if(cashNum>0) {
							 String sql1= "update users set cash_num='"+cashNum+"' where id='"+user.getId()+"'";
		 	      				userMapper.updateForSql(sql1);
		 	      				AccountDetail ad = cs.returnAccountDetail(user, cashNum,cashNum, 1,Constants.MONEY_DETAIL_YYPE_0, "数据初始化导入", date);
		      						String tableName = Constants.CASHNUMDETAIL_TABLE;
		      						Map<String,Object> params = new HashMap<>();
		      						params.put("account",ad);
		      						params.put("tableName", tableName);
		      						Integer up1 =moneyMapper.save(params);
		      						if(up1!=null&&up1>0) {
		      							
		      						}else str+="会员特卖权账户保存失败。";
						 }
						 
					 }
					 if(!integralStr.equals("")) {
						 double integral =Double.valueOf(integralStr);
						 if(integral>0) {
							 String sql1= "update users set cash='"+integral+"' where id='"+user.getId()+"'";
		 	      				userMapper.updateForSql(sql1);
		 	      				AccountDetail ad = cs.returnAccountDetail(user, integral,integral, 1,Constants.MONEY_DETAIL_YYPE_0, "数据初始化导入", date);
		      						String tableName = Constants.INTEGRALDETAIL_TABLE;
		      						Map<String,Object> params = new HashMap<>();
		      						params.put("account",ad);
		      						params.put("tableName", tableName);
		      						Integer up1 =moneyMapper.save(params);
		      						if(up1!=null&&up1>0) {
		      							
		      						}else str+="会员积分账户保存失败。";
						 }
						 
					 }
					 
					 if(!rmoneyStr.equals("")) {
						 double rmoney =Double.valueOf(rmoneyStr);
						 if(rmoney>0) {
							 String sql1= "update users set rmoney='"+rmoney+"',pre_rmoney='"+rmoney+"' where id='"+user.getId()+"'";
		 	      				userMapper.updateForSql(sql1);
		 	      				AccountDetail ad = cs.returnAccountDetail(user, rmoney,rmoney, 1,Constants.MONEY_DETAIL_YYPE_0, "数据初始化导入", date);
		      						String tableName = Constants.RMONEYDETAIL_TABLE;
		      						Map<String,Object> params = new HashMap<>();
		      						params.put("account",ad);
		      						params.put("tableName", tableName);
		      						Integer up1 =moneyMapper.save(params);
		      						if(up1!=null&&up1>0) {
		      							
		      						}else str+="会员积分账户保存失败。";
						 }
						 
					 }
					 if(!agentTagStr.equals("")) {
						 int agentTag =Integer.valueOf(agentTagStr);
						 if(agentTag>0) {
							 String sql1= "update users set agent_tag='"+agentTag+"',province='"+province+"',city='"+city+"',area='"+area+"' where id='"+user.getId()+"'";
		 	      			userMapper.updateForSql(sql1);
						 }
					 }
					 if(!entryTimeStr.equals("")) {
						 Timestamp entryTime= new Timestamp(StringUtil.parseToDate(entryTimeStr, DateUtil.yyyyMMddHHmmss).getTime());						
							 String sql1= "update users set entry_time='"+entryTime+"' where id='"+user.getId()+"'";
		 	      			userMapper.updateForSql(sql1);
					 }
					 
					 if(!numStr.equals("")) {
						 int num=Integer.valueOf(numStr);
						 Product product= productMapper.selectByProductId("c001");
						 ProductStock stock = new ProductStock();
						 stock.setPid(product.getId());
						 stock.setUid(user.getId());
						 stock.setUserId(user.getUserId());
						 stock.setUserName(user.getUserName());
						 stock.setProductId(product.getProductId());
						 stock.setProductName(product.getProductName());
						 stock.setSpecification(product.getSpecification());
						 stock.setPrice(product.getPrice());
						 stock.setNum(num);
						 stock.setEntryTime(date);
						 stock.setEndTime(date);
						 stock.setImageUrl(product.getImageUrl());
						 stockMapper.save(stock);
						 productMapper.updateSubNum(product.getProductId(), num);
					 }
					 
				 }else{
					 str="会员信息获取失败。";
			 	}//end user
				 sqlSession.commit();
			 }catch(Exception e) {
				 str="数据操作异常。"+e.getMessage();
				 e.printStackTrace();
				 sqlSession.rollback();
			 }finally {
				 sqlSession.close();
			 }
			 return str;
		 }
		 
		 public int rankJoin_up(User user,User refereeUser,Param p1,int rankJoin) {
		    	int count=0;
		    	int virtual = 0;
					int virtual1 = 0;
					String sql="update users set ";
					if(user.getRankJoin()-1==0) {
						virtual = p1.getAmount_1().intValue();
						sql+=" referee_num_1=referee_num_1-1,";
					}else if(user.getRankJoin()-2==0) {
						virtual = p1.getAmount_2().intValue();
						sql=" referee_num_2=referee_num_1-1,";
					}else if(user.getRankJoin()-3==0) {
						virtual = p1.getAmount_3().intValue();
						sql+=" referee_num_3=referee_num_1-1,";
					}else if(user.getRankJoin()-4==0) {
						virtual = p1.getAmount_4().intValue();
						sql+=" referee_num_4=referee_num_1-1,";
					}else if(user.getRankJoin()-5==0) {
						virtual = p1.getAmount_5().intValue();
						sql+=" referee_num_5=referee_num_1-1,";
					}else if(user.getRankJoin()-6==0) {
						virtual = p1.getAmount_6().intValue();
						sql+=" referee_num_6=referee_num_1-1,";
					}
					if(rankJoin-1==0) {
						virtual1 = p1.getAmount_1().intValue();
						sql+=" referee_num_1=referee_num_1+1,";
					}else if(rankJoin-2==0) {
						virtual1 = p1.getAmount_2().intValue();
						sql+=" referee_num_2=referee_num_2+1,";
					}else if(rankJoin-3==0) {
						virtual1 = p1.getAmount_3().intValue();
						sql+=" referee_num_3=referee_num_3+1,";
					}else if(rankJoin-4==0) {
						virtual1 = p1.getAmount_4().intValue();
						sql+=" referee_num_4=referee_num_4+1,";
					}else if(rankJoin-5==0) {
						virtual1 = p1.getAmount_5().intValue();
						sql+=" referee_num_5=referee_num_4+1,";
					}else if(rankJoin-6==0) {
						virtual1 = p1.getAmount_6().intValue();
						sql+=" referee_num_6=referee_num_6+1,";
					}
					int referee_virtual_num = virtual1-virtual+refereeUser.getRefereeVirtualNum();
					int refere_rankJoin = 0;
					
					if(refereeUser.getRankJoin()>0) {
						if(referee_virtual_num-p1.getAmount_1().intValue()>=0) {
							refere_rankJoin = 1;
						}else if(referee_virtual_num-p1.getAmount_2().intValue()>=0) {
							refere_rankJoin = 2;
						}else if(referee_virtual_num-p1.getAmount_3().intValue()>=0) {
							refere_rankJoin = 3;
						}else if(referee_virtual_num-p1.getAmount_4().intValue()>=0) {
							refere_rankJoin = 4;
						}else if(referee_virtual_num-p1.getAmount_5().intValue()>=0) {
							refere_rankJoin = 5;
						}else if(referee_virtual_num-p1.getAmount_6().intValue()>=0) {
							refere_rankJoin = 6;
						}
						if(refereeUser.getRankJoin()-refere_rankJoin<0) {
							sql+="rank_join='"+refere_rankJoin+"',";
							count = refere_rankJoin;
						}
					}
					sql+="referee_virtual_num=referee_virtual_num+'"+referee_virtual_num+"' where id='"+refereeUser.getId()+"'";
					System.out.println(sql);
					Integer up = userMapper.updateForSql(sql);
					if(up!=null&&up>0) {
						if(refereeUser.getRankJoin()-refere_rankJoin<0) {
							User refereeUser2 = userMapper.selectUsersById(refereeUser.getId());
							if(refereeUser2!=null) {
								count = rankJoin_up(refereeUser,refereeUser2,p1,refere_rankJoin);
							}else count=up;
						}else count=up;
					}else count=0;
					return count;
					
		    }
		    
		    public int checkRankJoin(User user,Param p1,int rankJoin) {
		    	int result=rankJoin;
		    	int virtual = user.getRefereeVirtualNum();
					
					
					if(virtual-p1.getAmount_1().intValue()==0) {
						result=1;
					}else if(virtual-p1.getAmount_2().intValue()==0) {
						result=2;
					}else if(virtual-p1.getAmount_3().intValue()==0) {
						result=3;
					}else if(virtual-p1.getAmount_4().intValue()==0) {
						result=4;
					}else if(virtual-p1.getAmount_5().intValue()==0) {
						result=5;
					}else if(virtual-p1.getAmount_6().intValue()==0) {
						result=6;
					}
					if(result<rankJoin) result = rankJoin;
					return result;
					
		    }
		  
}
