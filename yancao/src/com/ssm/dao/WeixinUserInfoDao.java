package com.ssm.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AccountSupplementMapper;
import com.ssm.mapper.AddressMapper;
import com.ssm.mapper.AdminLogMapper;
import com.ssm.mapper.AdminMapper;
import com.ssm.mapper.CenterMapper;
import com.ssm.mapper.DSettleMapper;
import com.ssm.mapper.JoinInfoMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderDetailMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.TokenMapper;
import com.ssm.mapper.UidPoolMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.mapper.WeixinUserInfoMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.AccountSupplement;
import com.ssm.pojo.Address;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Center;
import com.ssm.pojo.DSettle;
import com.ssm.pojo.Dept;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.UidPool;
import com.ssm.pojo.User;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WSettle;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.StringUtil;
import com.ssm.weixin.utils.AdvancedUtil;

public class WeixinUserInfoDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    WeixinUserInfoMapper weixinMapper = sqlSession.getMapper(WeixinUserInfoMapper.class);
    AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
    UidPoolMapper poolMapper = sqlSession.getMapper(UidPoolMapper.class);
    TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);

    ICustomService cs = new CustomService();
    Admin admin= new Admin();
    
    
    public Pager findByPage(WeixinUserInfo user,Pager pager){
    	try {
		Map<String,Object> params = new HashMap<>();
		params.put("user",user);
		int recordCount = weixinMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<WeixinUserInfo> result = weixinMapper.selectByPage(params);
		pager.setResultList(result);
    	}catch(Exception e) {
    		pager=null;
    	}finally {
		sqlSession.close();
    	}
		return pager;
	}
    
    public int save(WeixinUserInfo user){
    	int result = 0; 
    	try {
	    	if(weixinMapper.selectByOpenId(user.getOpenId())==null){
	    		if(weixinMapper.save(user)>0) {
	    			sqlSession.commit();
	    		}else {
	    			result =1;
	    		}
			 
	    	}else{
	    		result =2;
	    	}
    	}finally {
    		sqlSession.close();
    		result =3;
        }
    	return result;
	}
    
    public synchronized String save(String accessToken,int refereeId,String fromUserName,Timestamp date){
    	String msg="";
    	int qrId = 0; 
    	  String userId="";
          String qr_img="";
          String nickName="";
          int count =0;
          Random rand = new Random();
      	int rankNum = rand.nextInt(900000)+100000;
      	String password = String.valueOf(rankNum);
      	String psw = MD5.GetMD5Code(password);
    	try {
	          WeixinUserInfo snsUserInfo = AdvancedUtil.getUserInfo(accessToken, fromUserName);
	       if(snsUserInfo!=null) {
	          WeixinUserInfo weixin =weixinMapper.selectByOpenId(snsUserInfo.getOpenId());
	    	if(weixin==null){
	    		qrId = get_card_id(date);
	        	 userId = String.valueOf(600000+qrId);
	        	 double integral =10;
	        	String ticket = AdvancedUtil.createPermanentQRCode(accessToken, qrId);
	      		String savePath="/home/yezuser/tomcat/webapps/ROOT/upload/qr";
	      		AdvancedUtil.getQRCode(ticket, savePath);
	      		qr_img = ticket+".jpg";
	      		snsUserInfo.setSubscribe(1);
	      		snsUserInfo.setUserId(userId);
	      		snsUserInfo.setEntryTime(date);
	      		snsUserInfo.setEndTime(date);
	      		snsUserInfo.setState(1);
	      		snsUserInfo.setQrId(qrId);
	      		snsUserInfo.setQrImg(qr_img);
	        	User user = new User();
	        	user.setUserId(userId);
	        	User updateUser = new User();
	        	if(refereeId>0) {
	        		String refereeUserId = String.valueOf(600000+refereeId);
		        	User refereeUser = userMapper.selectByUserIdForUpdate(refereeUserId);
		        	String refereeNode="";
		        	if(refereeUser!=null) {
			        	user.setRefereeId(refereeUser.getId());
			        	user.setRefereeUserId(refereeUser.getUserId());
			        	if(StringUtil.notNull(refereeUser.getRefereeNode()).equals("")) refereeNode = String.valueOf(refereeUser.getId());
			        	else refereeNode = refereeUser.getRefereeNode()+","+String.valueOf(refereeUser.getId());
			        	user.setRefereeNode(refereeNode);
			      		snsUserInfo.setRefereeId(refereeUser.getId());
			        	//推荐人变更
			        	updateUser.setId(refereeUser.getId());
			        	updateUser.setUserId(refereeUser.getUserId());
			        	updateUser.setUserName(refereeUser.getUserName());
			        	updateUser.setRefereeAll(StringUtil.notNull(refereeUser.getRefereeAll()));
			        	updateUser.setRefereeNum(refereeUser.getRefereeNum());
			        	updateUser.setIntegral(ArithUtil.add(refereeUser.getIntegral(),integral));
			        	updateUser.setVersion(refereeUser.getVersion());
		        	}else {
		        		user.setRefereeId(0);
		        		user.setRefereeUserId("");
		        		user.setRefereeNode("");
		        		updateUser=null;
		        		snsUserInfo.setRefereeId(0);
		        	}
	        	}else {
	        		user.setRefereeId(0);
	        		user.setRefereeUserId("");
	        		user.setRefereeNode("");
	        		snsUserInfo.setRefereeId(0);
	        		updateUser=null;
	        	}
	        	user.setHeadImgUrl(snsUserInfo.getHeadImgUrl());
	        	if(snsUserInfo.getSex()==1) user.setSex("男");
	        	else user.setSex("女");
	        	if(!StringUtil.notNull(snsUserInfo.getNickName()).equals("")) {
	        		nickName=snsUserInfo.getNickName().replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");;
	        	}
	        	if(!hasEmoji(nickName)) {
	        		user.setNickName(nickName);
		        	user.setUserName(nickName);
	        	}
		        	user.setPassword(psw);
		        	user.setPassword2(psw);
		        	user.setOpenId(snsUserInfo.getOpenId());
		        	user.setProvince(snsUserInfo.getProvince());
		        	user.setCity(snsUserInfo.getCity());
		        	user.setState(1);
		        	user.setIntegral(integral);
		        	user.setEntryTime(date);
		        	snsUserInfo.setNickName(nickName);
		        	Integer up = weixinMapper.save(snsUserInfo);
		        	if(up!=null&up>0) {
		        	Integer up1 = userMapper.saveUser(user);
		        	if(up1!=null&up1>0) {
		        		Integer up3 =userMapper.saveUserInfo(user);
		        		if(up3!=null&up3>0) {
		        			AccountDetail ad = cs.returnAccountDetail(user, integral, integral, 1, Constants.MONEY_DETAIL_YYPE_1, "会员首次关注赠送", date);
							 String tableName = Constants.INTEGRALDETAIL_TABLE;
							 Map<String,Object> params = new HashMap<>();
							 params.put("account",ad);
							 params.put("tableName", tableName);
							 Integer up5 =moneyMapper.save(params);
								
					    	if(up5!=null&up5>0) {
					        		if(updateUser!=null) {
					        			String refereeAll="";
					        			if(updateUser.getRefereeAll().equals("")) refereeAll = String.valueOf(user.getId());
							        	else refereeAll = updateUser.getRefereeAll()+","+String.valueOf(user.getId());
					        			updateUser.setRefereeAll(refereeAll);
					        			updateUser.setRefereeNum(updateUser.getRefereeNum()+1);
					        			Integer up2 = userMapper.updateUser(updateUser);
					        			if(up2!=null&up2>0) {
					        				AccountDetail ad1 = cs.returnAccountDetail(updateUser, integral, updateUser.getIntegral(), 1, Constants.MONEY_DETAIL_YYPE_2, "邀请会员赠送", date);
											 Map<String,Object> param1 = new HashMap<>();
											 param1.put("account",ad1);
											 param1.put("tableName", tableName);
											 Integer up4 =moneyMapper.save(param1);
										    	if(up4!=null&up4>0) {
										    		count++;
										    	}else {
							        				msg="邀请人账户明细保存失败。";
							        				count=0;
							        			}
					        			}else {
					        				msg="邀请人信息更新失败，请在会员商城中重新点击成为会员加入我们。";
					        				count=0;
					        			}
					        		}else {
					        			count++;
					        		}
					    	}else {
		        				msg="会员账户明细保存失败。";
		        				count=0;
		        			}
		        		}else {
		        			msg="会员基本信息保存失败，请在会员商城中重新点击成为会员加入我们。";
		        			count=0;
		        		}
		        	}else {
		        		msg="会员信息保存失败，请在会员商城中重新点击成为会员加入我们。";
		        		count=0;
		        	}
		        	}else {
		        		msg="微信信息保存失败，请在会员商城中重新点击成为会员加入我们。";
		        		count=0;
		        	}	
	          }else{
	        	  if(weixin.getSubscribe()-1!=0) {
		        	  WeixinUserInfo weixin_update = new WeixinUserInfo();
		        	  weixin_update.setId(weixin.getId());
		        	  weixin_update.setSubscribe(1);
		        	  weixin_update.setSubscribeTime(snsUserInfo.getSubscribeTime());
		        	  weixin_update.setEndTime(date);
		        	  count = weixinMapper.update(weixin_update);
		        	  userId = weixin_update.getUserId();
	        	  }
	        	  msg="Hi，终于等到您\r\n" + "您的专属会员编号："+weixin.getUserId()+"；\r\n"+
	        			  "婴儿珍致力于成为中国居家焕肤、嫩肤第一品牌\r\n"+
				        	"让每一位爱美人士享受嫩肤的活力！\r\n"+
				        	"想与婴儿珍有一场美丽与财富的“邂逅”？\r\n"+
				        	"点击“会员商城”消费成为我们的会员，我们一起努力、携手并进！\r\n";;
	          }
	          }else msg+="微信会员信息获取失败，请在会员商城中重新点击成为会员加入我们。";
	          if(msg.equals("")) {
	        	  msg="Hi，终于等到您\r\n" + 
	        	  		"您的专属会员编号："+userId+"；\r\n随机登陆密码为："+String.valueOf(rankNum)+"；\r\n"+
			        	"婴儿珍致力于成为中国居家焕肤、嫩肤第一品牌\r\n"+
			        	"让每一位爱美人士享受嫩肤的活力！\r\n"+
			        	"想与婴儿珍有一场美丽与财富的“邂逅”？\r\n"+
			        	"点击“会员商城”消费成为我们的会员，我们一起努力、携手并进！\r\n";
	        	  
	        	 
	        	  
	          }
	          if(count>0) sqlSession.commit();
	          else sqlSession.rollback();
    	}catch(Exception e){
    		msg+="数据连接服务器异常，请在会员商城中重新点击成为会员加入我们。"+nickName;
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
    	return msg;
	}
    
    public synchronized WeixinUserInfo getWeixinUser(String fromUserName){
    	 WeixinUserInfo weixin = null;
    	try {
	         weixin =weixinMapper.selectByOpenId(fromUserName);
    	}catch(Exception e){
    		e.printStackTrace();
		}finally {
	    	sqlSession.close();
	    }
	    	return weixin;
		}
    
    public int unsubscribe(String accessToken,String fromUserName,Timestamp date){
    	int qrId = 0; 
          int count =0;
    	try {
	          WeixinUserInfo weixin =weixinMapper.selectByOpenId(fromUserName);
	        if(weixin!=null) {
	        	  if(weixin.getSubscribe()-1!=0) {
		        	  WeixinUserInfo weixin_update = new WeixinUserInfo();
		        	  weixin_update.setId(weixin.getId());
		        	  weixin_update.setSubscribe(0);
		        	  weixin_update.setEndTime(date);
		        	  count = weixinMapper.update(weixin_update);
	        	  }
	          }
	         
	    	if(count>0) sqlSession.commit();
    	}catch(Exception e){
e.printStackTrace();
}finally {
    		sqlSession.close();
        }
    	return qrId;
	}
    
    public int update(WeixinUserInfo user){
    	int result = 0; 
    	try {
	    	if(weixinMapper.update(user)>0) {
	    		sqlSession.commit();
	    	}else {
	    		result = 1;
	    	}
    	}catch(Exception e) {
    		result = 2;
    	}finally {
    		sqlSession.close();
        }
    	return result;
	}
    

 
 	public WeixinUserInfo findByOpenId(String openId){
 		WeixinUserInfo user = null;
 		try {
 			user = weixinMapper.selectByOpenId(openId);
 		}catch(Exception e) {
 			user =null;
 		}finally {
		sqlSession.close();
 		}
		return user;
	}
 	
 	public WeixinUserInfo findById(Integer id){
 		WeixinUserInfo user = null;
 		try {
 			user = weixinMapper.selectById(id);
 		}catch(Exception e) {
 			user =null;
 		}finally {
		sqlSession.close();
 		}
		return user;
	}
 	
 	public WeixinUserInfo findByUserId(String userId){
 		WeixinUserInfo user = null;
 		try {
 			user = weixinMapper.selectByUserId(userId);
 		}catch(Exception e) {
 			user =null;
 		}finally {
		sqlSession.close();
 		}
		return user;
	}
 	
 	public WeixinUserInfo findByQrId(Integer qrId){
 		WeixinUserInfo user = null;
 		try {
 			user = weixinMapper.selectByQrId(qrId);
 		}catch(Exception e) {
 			user =null;
 		}finally {
		sqlSession.close();
 		}
		return user;
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
	 
	 public boolean hasEmoji(String source){
		 int len = source.length();
	        boolean isEmoji = false;
	        for (int i = 0; i < len; i++) {
	            char hs = source.charAt(i);
	            if (0xd800 <= hs && hs <= 0xdbff) {
	                if (source.length() > 1) {
	                    char ls = source.charAt(i+1);
	                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
	                    if (0x1d000 <= uc && uc <= 0x1f77f) {
	                        return true;
	                    }
	                }
	            } else {
	                // non surrogate
	                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
	                    return true;
	                } else if (0x2B05 <= hs && hs <= 0x2b07) {
	                    return true;
	                } else if (0x2934 <= hs && hs <= 0x2935) {
	                    return true;
	                } else if (0x3297 <= hs && hs <= 0x3299) {
	                    return true;
	                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c || hs == 0x2b1b || hs == 0x2b50|| hs == 0x231a ) {
	                    return true;
	                }
	                if (!isEmoji && source.length() > 1 && i < source.length() -1) {
	                    char ls = source.charAt(i+1);
	                    if (ls == 0x20e3) {
	                        return true;
	                    }
	                }
	            }
	        }
	        return  isEmoji;
		}
 	
}
