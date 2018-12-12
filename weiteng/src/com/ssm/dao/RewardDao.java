package com.ssm.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AdminLogMapper;
import com.ssm.mapper.DRewardMapper;
import com.ssm.mapper.JoinInfoMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderDeliveryMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.RewardMapper;
import com.ssm.mapper.SettleMapper;
import com.ssm.mapper.UserLogMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.Address;
import com.ssm.pojo.AdminLog;
import com.ssm.pojo.DReward;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDelivery;
import com.ssm.pojo.Param;
import com.ssm.pojo.Reward;
import com.ssm.pojo.Settle;
import com.ssm.pojo.User;
import com.ssm.pojo.UserLog;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WRewardExtreme;
import com.ssm.pojo.WSettle;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.StringUtil;

public class RewardDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	  SettleMapper stMapper = sqlSession.getMapper(SettleMapper.class);
	    RewardMapper rewardMapper = sqlSession.getMapper(RewardMapper.class);
	    DRewardMapper drdMapper = sqlSession.getMapper(DRewardMapper.class);

	    WSettleMapper wstMapper = sqlSession.getMapper(WSettleMapper.class);   
	    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);  
	    OrderDeliveryMapper orderDeliveryMapper = sqlSession.getMapper(OrderDeliveryMapper.class);  
	   UserMapper userMapper = sqlSession.getMapper(UserMapper.class);  
	   JoinInfoMapper jfMapper = sqlSession.getMapper(JoinInfoMapper.class);  
	   ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);  
	   MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);  
	    AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
		  UserLogMapper userLogMapper = sqlSession.getMapper(UserLogMapper.class);
		  ICustomService cs = new CustomService();

    public Pager findByPage(Reward reward,Pager pager){
    	try{
    		if(reward!=null){
				Map<String,Object> params = new HashMap<>();
				params.put("reward",reward);
				int recordCount = rewardMapper.count(params);
				pager.setRowCount(recordCount);
				if(recordCount>0){
					params.put("pageModel", pager);
				}
				List<Reward> adrs = rewardMapper.selectByPage(params);
				sqlSession.commit();
				pager.setResultList(adrs);
    		}
    	} finally {
			sqlSession.close();
    	}
    	return pager;
	}
    
  
    
  
    
   
  
   
    
    public String countReward(Integer monthTag){
    	String msg="";
    	try{
    		Timestamp date = new Timestamp(new Date().getTime());
    		Settle st = stMapper.selectByMonthTagForUpdate(monthTag);
    		if(st!=null){
    			Param p1 = paramMapper.selectByName("分红奖励");
    			Param p2 = paramMapper.selectByName("区域奖励1");
        		Param p3 = paramMapper.selectByName("区域奖励2");
        		
        		if(p1!=null){
        		if(p2!=null){
        			if(p3!=null){
        				List<User> ulist1 = new ArrayList<User>();
                		List<User> ulist2 = new ArrayList<User>();
                		List<User> ulist3 = new ArrayList<User>();
        		User sel_user = new User();
        		sel_user.setEndTime(st.getEndTime());
        		Map<String,Object> params = new HashMap<>();
        		params.put("user",sel_user);
        		List<User> ulist = userMapper.selectByPage(params);
        		Integer maxId= userMapper.maxId(params);
        		int num = 1;
        		if(maxId!=null) num =maxId+1;
        		if(num>1){
        			User[] user = new User[num+1];
        			Reward[] wrd = new Reward[num+1];
        			for(int i=0;i<ulist.size();i++){
        				int id = ulist.get(i).getId();
        				if(user[id]==null) user[id] = new User();
        				user[id].setId(id);
        				user[id].setUserId(ulist.get(i).getUserId()); 
        				user[id].setUserName(ulist.get(i).getUserName());
        				user[id].setRefereeAll(ulist.get(i).getRefereeAll());
        				user[id].setRefereeNode(ulist.get(i).getRefereeNode());
        				user[id].setRankJoinOld(ulist.get(i).getRankJoinOld());
        				user[id].setRankJoin(ulist.get(i).getRankJoin());
        				user[id].setAgentTag(ulist.get(i).getAgentTag());
        				user[id].setProvince(ulist.get(i).getProvince());
        				user[id].setCity(ulist.get(i).getCity());
        				user[id].setArea(ulist.get(i).getArea());
        				user[id].setEntryTime(ulist.get(i).getEntryTime());
        				user[id].setState(ulist.get(i).getState());
        				user[id].setTag(0);
        				if(wrd[id]==null) wrd[id] = new Reward();
        				wrd[id].setUid(id);
        				wrd[id].setUserId(ulist.get(i).getUserId());
        				wrd[id].setUserName(ulist.get(i).getUserName());
        				wrd[id].setAmount_1((double) 0);
        				wrd[id].setAmount_2((double) 0);
        				wrd[id].setAmount_3((double) 0);
        				wrd[id].setGroupPrice((double) 0);
        				wrd[id].setAreaPrice((double) 0);
        				wrd[id].setRankJoin(ulist.get(i).getRankJoin());
        				wrd[id].setAgentTag(ulist.get(i).getAgentTag());
        				wrd[id].setPayTag(1);
        				wrd[id].setMonthTag(st.getMonthTag());
        				wrd[id].setStartTime(st.getStartTime());
        				wrd[id].setEndTime(st.getEndTime());
        				wrd[id].setPayTag(ulist.get(i).getPayTag());
        				wrd[id].setEntryTime(date);
        				
        				if(user[id].getRankJoin()==5)
        					ulist1.add(user[id]);
        				else if(user[id].getRankJoin()==6)
        					ulist2.add(user[id]);
        				else if(user[id].getRankJoin()==7)
        					ulist2.add(user[id]);
        			}
        			
    				double totalPrice = 0;
    				double totalPrice1 = 0;
    				String sql = "select * from orders where order_time>='"+st.getStartTime()+"' and order_time<='"+st.getEndTime()+"' and pay_type!='2' and state>'1' order by id asc";
            		List<Order> olist = orderMapper.selectBySql(sql);
            		for(int i=0;i<olist.size();i++){
            			int uid = olist.get(i).getUid();
    					String userId = olist.get(i).getUserId();
    					double price =olist.get(i).getPrice();
    					totalPrice = ArithUtil.add(totalPrice,price);
    					String[] str = user[uid].getRefereeNode().split(",");
    					for(int j=0;j<str.length;j++) {
    						if(!str[j].equals("")) {
    							int sid = Integer.valueOf(str[j]);
    							if(wrd[sid]!=null) {
    								wrd[sid].setGroupPrice(ArithUtil.add(wrd[sid].getGroupPrice(), price));
    							}
    						}
    					}
            		}
            		
    				OrderDelivery sel_order = new OrderDelivery();
    				sel_order.setStartTime(st.getStartTime());
    				sel_order.setEndTime(st.getEndTime());
            		params.put("order", sel_order);
            		List<OrderDelivery> olist1 = orderDeliveryMapper.selectByList(params);
    				for(int i=0;i<olist.size();i++){
    					int uid = olist.get(i).getUid();
    					String userId = olist.get(i).getUserId();
    					double price =olist.get(i).getPrice();
    					totalPrice1 = ArithUtil.add(totalPrice1,price);
    					
    				}
    				
            		st.setNewPrice(totalPrice);
            		
            		 // 个人小组业绩计算
            		for(int i=0;i<ulist1.size();i++){
            			int id = ulist1.get(i).getId();
            			if(wrd[id]!=null) {
            				String[] str = StringUtil.notNull(user[id].getRefereeNode()).split(",");
            				for(int j=0;j<str.length;j++) {
            					if(!str[j].equals("")) {
            						int sid = Integer.valueOf(str[j]);
            							if(wrd[sid].getRankJoin()==5) {
            									wrd[sid].setGroupPrice(ArithUtil.sub(wrd[sid].getGroupPrice(), wrd[id].getGroupPrice()));
            							}
            					}
            				}
            			}
            		}
            		for(int i=0;i<ulist2.size();i++){
            			int id = ulist2.get(i).getId();
            			if(wrd[id]!=null) {
            				String[] str = StringUtil.notNull(user[id].getRefereeNode()).split(",");
            				for(int j=0;j<str.length;j++) {
            					if(!str[j].equals("")) {
            						int sid = Integer.valueOf(str[j]);
            							if(wrd[sid].getRankJoin()==6) {
            									wrd[sid].setGroupPrice(ArithUtil.sub(wrd[sid].getGroupPrice(), wrd[id].getGroupPrice()));
            							}
            					}
            				}
            			}
            		}
            		for(int i=0;i<ulist3.size();i++){
            			int id = ulist3.get(i).getId();
            			if(wrd[id]!=null) {
            				String[] str = StringUtil.notNull(user[id].getRefereeNode()).split(",");
            				for(int j=0;j<str.length;j++) {
            					if(!str[j].equals("")) {
            						int sid = Integer.valueOf(str[j]);
            							if(wrd[sid].getRankJoin()==7) {
            									wrd[sid].setGroupPrice(ArithUtil.sub(wrd[sid].getGroupPrice(), wrd[id].getGroupPrice()));
            							}
            					}
            				}
            			}
            		}
            		
            		/*
            		 * 分红奖励计算
            		 */	
            		double amount1= ArithUtil.mul(totalPrice, p1.getAmount_5());
            		if(amount1>0) {
            			for(int i=0;i<ulist1.size();i++) {
            				int id = ulist1.get(i).getId();
            				if(wrd[id].getGroupPrice()>0) {
            					double award = ArithUtil.mul(amount1, ArithUtil.div(wrd[id].getGroupPrice(), totalPrice));
            					wrd[id].setAmount_1(award);
            				}
            			}
            		}
            		double amount2= ArithUtil.mul(totalPrice, p1.getAmount_6());
            		if(amount2>0) {
            			for(int i=0;i<ulist2.size();i++) {
            				int id = ulist2.get(i).getId();
            				if(wrd[id].getGroupPrice()>0) {
            					double award = ArithUtil.mul(amount2, ArithUtil.div(wrd[id].getGroupPrice(), totalPrice));
            					wrd[id].setAmount_1(award);
            				}
            			}
            		}
            		double amount3= ArithUtil.mul(totalPrice, p1.getAmount_7());
            		if(amount3>0) {
            			for(int i=0;i<ulist2.size();i++) {
            				int id = ulist2.get(i).getId();
            				if(wrd[id].getGroupPrice()>0) {
            					double award = ArithUtil.mul(amount3, ArithUtil.div(wrd[id].getGroupPrice(), totalPrice));
            					wrd[id].setAmount_1(award);
            				}
            			}
            		}
            		
            		
            		//计算复消
            		List<Reward> wrdlist = new ArrayList<Reward>();
            	int n1=0;
            	int error1=0;
            		for(int i=0;i<wrd.length;i++){
            			if(wrd[i]!=null){
        					if(n1%800==0){
	        					wrdlist.add(wrd[i]);
	        					
	                    		params.put("list", wrdlist);
	                    		Integer wrd_insert = rewardMapper.insertAll(params);
	                    		if(wrd_insert==null || wrd_insert==0)  error1++;
	                    		wrdlist = new ArrayList<Reward>();
        					}else if(i<wrd.length-1){
        						wrdlist.add(wrd[i]);
        					}else{
        						wrdlist.add(wrd[i]);
	                    		params.put("list", wrdlist);
	                    		Integer wrd_insert = rewardMapper.insertAll(params);
	                    		if(wrd_insert==null || wrd_insert==0)  error1++;
	                    		wrdlist =null;
        					}
        					n1++;
            			}
            			if(i==wrd.length-1&&wrdlist!=null&&wrdlist.size()>0){
                    		params.put("list", wrdlist);
                    		Integer wrd_insert = rewardMapper.insertAll(params);
                    		if(wrd_insert==null || wrd_insert==0)  error1++;
                    		wrdlist =null;
            			}
            		}
            		
            		//保存相关的表
            		//保存结算表
            		
    
            		st.setState(2);
            		if(stMapper.update(st)>0){
            		if(error1==0){
            			
            			msg ="yes";
            			
            		}else{
            			msg = "会员佣金明细保存失败。";
            			sqlSession.rollback();
            		}
            		}else{
            			msg = "结算统计表保存失败。";
            			sqlSession.rollback();
            		}
            		
        		}else{
        			msg ="yes1";
        			sqlSession.rollback();
        		}
        				}else{
                			msg="奖金参数获取失败。";
                			sqlSession.rollback();
                		}
        			}else{
            			msg="培育奖比例参数获取失败。";
            			sqlSession.rollback();
            		}
        			}else{
            			msg="拓展奖封顶额参数获取失败。";
            			sqlSession.rollback();
            		}
        			
        		
    		}else{
    			msg="结算表信息获取失败。";
    			sqlSession.rollback();
    		}
    		sqlSession.commit();
    	}catch(Exception e){ 
    		msg =e.getMessage();
    		e.printStackTrace();
    		sqlSession.rollback();
    	}finally {
			sqlSession.close();
    	}
    	return msg;
	}
    
    public String resetReward(Integer monthTag){
    	String msg="";
    	try{
    		stMapper.deleteByMonthTag(monthTag);
    		rewardMapper.deleteByMonthTag(monthTag);
        	msg = "yes";
    		sqlSession.commit();
    	}catch(Exception e){ 
    		msg =e.getMessage();
    		e.printStackTrace();
    		sqlSession.rollback();
    	}finally {
			sqlSession.close();
    	}
    	return msg;
	}
    
    public String confirmReward(Integer weekTag){
    	String msg="";
    	try{
    		Timestamp date = new Timestamp(new Date().getTime());
    		Settle st = stMapper.selectByMonthTagForUpdate(weekTag);
    		if(st!=null){
    			
    			

    		}else{
    			msg="用户未登录，请重新登陆。";
    			sqlSession.rollback();
			}
    	}catch(Exception e){ 
        		msg =e.getMessage();
        		e.printStackTrace();
        		sqlSession.rollback();
        	}finally {
    			sqlSession.close();
        	}
        	return msg;
    }
    
   
}
