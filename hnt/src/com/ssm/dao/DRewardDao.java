package com.ssm.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AdminLogMapper;
import com.ssm.mapper.DRewardMapper;
import com.ssm.mapper.DSettleMapper;
import com.ssm.mapper.JoinInfoMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.SettleDayMapper;
import com.ssm.mapper.SettleMapper;
import com.ssm.mapper.UserLogMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.Address;
import com.ssm.pojo.AdminLog;
import com.ssm.pojo.DReward;
import com.ssm.pojo.DSettle;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.Order;
import com.ssm.pojo.Param;
import com.ssm.pojo.Settle;
import com.ssm.pojo.SettleDay;
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

public class DRewardDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	  SettleDayMapper stMapper = sqlSession.getMapper(SettleDayMapper.class);
	    DRewardMapper wrdMapper = sqlSession.getMapper(DRewardMapper.class);
	    DSettleMapper wstMapper = sqlSession.getMapper(DSettleMapper.class);   
	    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);  
	   UserMapper userMapper = sqlSession.getMapper(UserMapper.class);  
	   JoinInfoMapper jfMapper = sqlSession.getMapper(JoinInfoMapper.class);  
	   ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);  
	   MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);  
	    AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
		  UserLogMapper userLogMapper = sqlSession.getMapper(UserLogMapper.class);

			ICustomService cs = new CustomService();

    public Pager findByPage(DReward wrd,String isHide,Pager pager){
    	try{
    		if(wrd!=null){
    			SettleDay  sd = stMapper.selectByDayTime(wrd.getDayTime());
    			if(sd!=null){
    				if(sd.getWeekTag()>0){
					Map<String,Object> params = new HashMap<>();
					params.put("wrd",wrd);
					if(!isHide.equals("")) 
					params.put("hide",isHide);
					params.put("tableName", "reward_day_"+sd.getWeekTag());
					int recordCount = wrdMapper.count(params);
					pager.setRowCount(recordCount);
					if(recordCount>0){
						params.put("pageModel", pager);
					}
					List<DReward> adrs = wrdMapper.selectByPage(params);
					sqlSession.commit();
					pager.setResultList(adrs);
    				}
    			}
    		}
    	} finally {
			sqlSession.close();
    	}
    	return pager;
	}
    
    public List<DReward> findByList(DReward wrd,String hide){
    	List<DReward> wrds =new ArrayList<DReward>();
    	try{
    		if(wrd!=null){
    			SettleDay  sd = stMapper.selectByDayTime(wrd.getDayTime());
    			if(sd!=null){
    				if(sd.getWeekTag()>0){
						Map<String,Object> params = new HashMap<>();
						params.put("wrd",wrd);
						params.put("tableName", "reward_day_"+sd.getWeekTag());
						params.put("hide",hide);
						wrds= wrdMapper.selectByPage(params);
						sqlSession.commit();
    			}
    			}
    		}
    	} finally {
			sqlSession.close();
    	}
    	return wrds;
	}
    
    public List<DReward> findByList(String userId){
    	List<DReward> wrds =new ArrayList<DReward>();
    	try{
    		if(!userId.equals("")){
    			SettleDay  sd = stMapper.selectMaxDayForTag(1);
    			if(sd!=null){
    				if(sd.getWeekTag()>0){
    					DReward wrd = new DReward();
    					wrd.setUserId(userId);
						Map<String,Object> params = new HashMap<>();
						params.put("wrd",wrd);
						params.put("tableName", "reward_day_"+sd.getWeekTag());
						wrds= wrdMapper.selectByList(params);
						sqlSession.commit();
    			}
    		}
    		}
    	} finally {
			sqlSession.close();
    	}
    	return wrds;
	}
    
    public DReward awardSummary(String dayTime,User user_sel){
    	DReward wrd = new DReward();
    	try{
    		SettleDay st = new SettleDay();
    		Timestamp startTime = null;
    		if(dayTime.equals("")){
    			st = stMapper.selectMaxDay();
    			startTime = st.getDayTime();
    		}else{
    	    		startTime = new Timestamp(StringUtil.parseToDate(dayTime+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
    				st = stMapper.selectByDayTime(startTime);
    				
    			}
    		if(st!=null){
    		String str_time = StringUtil.parseToString(startTime, DateUtil.yyyyMMdd);
    		Timestamp endTime = new Timestamp(StringUtil.parseToDate(str_time+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
    		if(st!=null){
    			String tableName = "reward_day_"+st.getWeekTag();
    			user_sel.setEntryTime(startTime);
    			wrd = wrdMapper.sumAward(tableName,user_sel);
    			wrd.setStartTime(startTime);
				wrd.setEndTime(endTime);
				wrd.setAmount(ArithUtil.add(st.getNewPrice(),st.getNewPrice_2()));
				wrd.setNewPerformance(ArithUtil.add(st.getNewPerformance(),st.getNewPerformance_2()));
				sqlSession.commit();
    		}

    		}
    	} finally {
			sqlSession.close();
    	}
    	return wrd;
	}
    
  
    
    public DReward findByUserId(String userId,String tableName,int weekTag){
    	DReward wrd = new DReward();
    	try{
    		wrd = wrdMapper.findByUserId(userId,tableName);
    		User user = userMapper.selectByUserId(userId);
    		if(user!=null){
	    		Timestamp date = new Timestamp(new Date().getTime());
	    		UserLog userlog = new UserLog();
	    		userlog.setUid(user.getId());
    			userlog.setUserId(user.getUserId());
    			userlog.setUserName(user.getUserName());
    			userlog.setType(ConstantsLog.USERLOGTYPE_7);
    			userlog.setContents("第"+weekTag+"期历史奖金查询。");
    			userlog.setEntryTime(date);
    			if(userLogMapper.save(userlog)!=null){
    				sqlSession.commit();
    			}
    		}
				sqlSession.commit();
    	} finally {
			sqlSession.close();
    	}
    	return wrd;
	}
    
    /*
    public List<WReward> awardSummaryAll(int weekTag1,int weekTag2){
    	List<WReward> result = new ArrayList<WReward>();
    	try{
    	
    		Map<String,Object> params = new HashMap<>();
    		User sel_user = new User();
    		params.put("user",sel_user);
    		List<User> ulist = userMapper.selectByList(params);
    		Integer maxId= userMapper.maxId(params);
    		
    		int num = 1;
    		if(maxId!=null) num =maxId+1;
    		if(num>1){
    			Settle st1 = stMapper.selectByWeekTag(weekTag1);
    			Settle st2 = stMapper.selectByWeekTag(weekTag2);
    			User[] user = new User[num+1];
    			WReward[] wrd = new WReward[num+1];
    			for(int i=0;i<ulist.size();i++){
    				int id = ulist.get(i).getId();
    				if(user[id]==null) user[id] = new User();
    				user[id].setId(id);
    				user[id].setUserId(ulist.get(i).getUserId()); 
    				user[id].setUserName(ulist.get(i).getUserName());
    				user[id].setState(ulist.get(i).getState());
    				user[id].setTag(0);
    				if(wrd[id]==null) wrd[id] = new WReward();
    				wrd[id].setuId(id);
    				wrd[id].setUserId(ulist.get(i).getUserId());
    				wrd[id].setUserName(ulist.get(i).getUserName());
    				wrd[id].setStartTime(st1.getStartTime());
    				wrd[id].setEndTime(st2.getEndTime());
    				wrd[id].setAmount_1((double) 0);
    				wrd[id].setAmount_2((double) 0);
    				wrd[id].setAmount_3((double) 0);
    				wrd[id].setAmount_4((double) 0);
    				wrd[id].setAmount_5((double) 0);
    				wrd[id].setAmount_6((double) 0);
    				wrd[id].setAmount_7((double) 0);
    				wrd[id].setAmount_8((double) 0);
    				wrd[id].setAmount_9((double) 0);
    				wrd[id].setAmount_10((double) 0);
    				wrd[id].setAmount_11((double) 0);
    				wrd[id].setAmount_12((double) 0);
    			}
    		for(int i=weekTag1;i<=weekTag2;i++){
	    		String tableName = "wreward_"+i;
	    		List<WReward> wlist=wrdMapper.findAll(tableName, "");
    			for(int j=0;j<wlist.size();j++){
		    		if(wlist.get(j).getAmount_9()>0){
		    				int id = wlist.get(j).getuId();
		    				if(user[id]!=null){
		    					wrd[id].setAmount_1(ArithUtil.add(wrd[id].getAmount_1(), wlist.get(j).getAmount_1()));
		    					wrd[id].setAmount_2(ArithUtil.add(wrd[id].getAmount_2(), wlist.get(j).getAmount_2()));
		    					wrd[id].setAmount_3(ArithUtil.add(wrd[id].getAmount_3(), wlist.get(j).getAmount_3()));
		    					wrd[id].setAmount_4(ArithUtil.add(wrd[id].getAmount_4(), wlist.get(j).getAmount_4()));
		    					wrd[id].setAmount_5(ArithUtil.add(wrd[id].getAmount_5(), wlist.get(j).getAmount_5()));
		    					wrd[id].setAmount_6(ArithUtil.add(wrd[id].getAmount_6(), wlist.get(j).getAmount_6()));
		    					wrd[id].setAmount_7(ArithUtil.add(wrd[id].getAmount_7(), wlist.get(j).getAmount_7()));
		    					wrd[id].setAmount_8(ArithUtil.add(wrd[id].getAmount_8(), wlist.get(j).getAmount_8()));
		    					wrd[id].setAmount_9(ArithUtil.add(wrd[id].getAmount_9(), wlist.get(j).getAmount_9()));
		    					wrd[id].setAmount_10(ArithUtil.add(wrd[id].getAmount_10(), wlist.get(j).getAmount_10()));
		    					wrd[id].setAmount_11(ArithUtil.add(wrd[id].getAmount_11(), wlist.get(j).getAmount_11()));
		    					wrd[id].setAmount_12(ArithUtil.add(wrd[id].getAmount_12(), wlist.get(j).getAmount_12()));
		    				}
		    			}
    			}
    		}
    		for(int i=0;i<wrd.length;i++){
    			if(wrd[i]!=null)
    				result.add(wrd[i]);
    		}
    			sqlSession.commit();
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally {
			sqlSession.close();
    	}
    	return result;
	}
    */
    public String payoff(Timestamp dayTime,String adminName){
    	String msg = "";
    	try{
    		SettleDay st = stMapper.selectByDayTimeForUpdate(dayTime);
    		String str_time = StringUtil.parseToString(dayTime, DateUtil.yyyyMMdd);
    		Timestamp startTime = new Timestamp(StringUtil.parseToDate(str_time+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
    		Timestamp endTime = new Timestamp(StringUtil.parseToDate(str_time+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
    		double award = 0;
    		double doubleAward = 0;
    	if(st!=null&st.getTag()==0){
    		String tableName = "reward_day_"+st.getWeekTag();
    		User sel_user = new User();
    		sel_user.setEndTime(endTime);
    		Map<String,Object> params = new HashMap<>();
    		params.put("user",sel_user);
    		List<User> ulist = userMapper.selectUserByListForUpdate(params);
    		Integer maxId= userMapper.maxId(params);
    		int num = 1;
    		if(maxId!=null) num =maxId+1;
    		if(num>1){
    			User[] user = new User[num+1];
    			for(int i=0;i<ulist.size();i++){
    				int id = ulist.get(i).getId();
    				if(user[id]==null) user[id] = new User();
    				user[id].setId(id);
    				user[id].setUserId(ulist.get(i).getUserId()); 
    				user[id].setUserName(ulist.get(i).getUserName());
    				user[id].setRmoney(ulist.get(i).getRmoney());
    				user[id].setDmoney(ulist.get(i).getDmoney());
    				user[id].setState(ulist.get(i).getState());
    				user[id].setTag(0);
    			}
    		Timestamp date = new Timestamp(new Date().getTime());
    		List<DReward> wlist=wrdMapper.findAll(tableName,dayTime, " for update");
    		int count =0;
    		String error = "";
    		for(int i=0;i<wlist.size();i++){
    			if(wlist.get(i).getState()==1){
    				count++;
    				if(wlist.get(i).getPayTag()==1){
		    			if(wlist.get(i).getAmount_9()>0){
		    				int id = wlist.get(i).getUid();
		    				BigDecimal   b1   =   new   BigDecimal(wlist.get(i).getAmount_12());
		    				BigDecimal   b2   =   new   BigDecimal(wlist.get(i).getAmount_10());  
		    				double amount =b1.setScale(2,   BigDecimal.ROUND_DOWN).doubleValue(); 
		    				double double_amount =b2.setScale(2,   BigDecimal.ROUND_DOWN).doubleValue(); 
		    				award = ArithUtil.add(award, amount);
		    				doubleAward = ArithUtil.add(doubleAward, double_amount);
		    				if(user[id]!=null){
		    					AccountDetail ad = new AccountDetail();
		    					ad.setUid(user[id].getId());
		    					ad.setUserId(user[id].getUserId());
		    					ad.setUserName(user[id].getUserName());
		    					ad.setAmount(amount);
		    					ad.setBalance(ArithUtil.add(user[id].getRmoney(), amount));
		    					ad.setPayType(1);
		    					ad.setTradeType(Constants.MONEY_DETAIL_YYPE_3);
		    					ad.setSummary(str_time+Constants.MONEY_DETAIL_YYPE_3);
		    					ad.setEntryTime(date);
		    					params.put("account",ad);
		    					params.put("tableName", "rmoneyDetail");
		    					Integer up1 = moneyMapper.save(params);
		    					if(up1==null||up1==0)
		    						error +=user[id].getUserId()+"奖金账户明细保存失败<br>";
		    					AccountDetail ad1 = new AccountDetail();
		    					ad1.setUid(user[id].getId());
		    					ad1.setUserId(user[id].getUserId());
		    					ad1.setUserName(user[id].getUserName());
		    					ad1.setAmount(double_amount);
		    					ad1.setBalance(ArithUtil.add(user[id].getDmoney(), double_amount));
		    					ad1.setPayType(1);
		    					ad1.setTradeType(Constants.MONEY_DETAIL_YYPE_3);
		    					ad1.setSummary(str_time+Constants.MONEY_DETAIL_YYPE_3);
		    					ad1.setEntryTime(date);
		    					params.put("account",ad1);
		    					params.put("tableName", "dmoneyDetail");
		    					Integer up2 = moneyMapper.save(params);
		    					if(up2==null||up2==0)
		    						error +=user[id].getUserId()+"复消券账户明细保存失败<br>";
		    					User up_user = new User();
		    					up_user.setId(user[id].getId());
		    					up_user.setRmoney(ad.getBalance());
		    					up_user.setDmoney(ad1.getBalance());
		    					Integer up3 = userMapper.updateUser(up_user);
		    					if(up3==null||up3==0)
		    						error +=user[id].getUserId()+"电子币更新失败。<br>";
		    				}
		    			}
    				}
    			}
    		}
    		st.setTag(1);
    		if(error.equals("")){
    			if(stMapper.update(st)>0){
    		if(wrdMapper.updateForState(tableName,dayTime,2, 1)>=count){
    			msg= "奖金发放成功，合计发放奖金"+StringUtil.decimalFormat(award)+"元，复消券总额"+doubleAward+"元。";
    			AdminLog log = new AdminLog();
				log.setAdminName(adminName);
				log.setContents(msg);
				log.setEntryTime(date);
				log.setType(ConstantsLog.LOGTYPE_14);
				adminLogMapper.save(log);
    			sqlSession.commit();
    		}else{
    			msg="奖金明细更新失败。";
    			sqlSession.rollback();
    		}
    			}else{
        			msg="结算表更新失败。";
        			sqlSession.rollback();
        		}
    		}else{
    			msg = error;
    			sqlSession.rollback();
    		}
    		}else{
    			msg = "没有需要发放奖金的会员";
    			sqlSession.rollback();
    		}
    	}else{
			msg = "未获取需要发放奖金的结算信息或当期奖金已经发放。";
			sqlSession.rollback();
		}
    	}catch(Exception e){
    		
    		msg ="奖金发放失败，请核对。";
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally {
			sqlSession.close();
    	}
    	return msg;
	}
    
	public String countReward(Timestamp dayTime){
    	String msg="";
    	try{
    		Timestamp date = new Timestamp(new Date().getTime());
    		SettleDay st = stMapper.selectByDayTimeForUpdate(dayTime);
    		String str_time = StringUtil.parseToString(dayTime, DateUtil.yyyyMMdd);
    		Timestamp startTime = new Timestamp(StringUtil.parseToDate(str_time+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
    		Timestamp endTime = new Timestamp(StringUtil.parseToDate(str_time+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
    		if(st!=null){
    			Param p1_1 = paramMapper.selectByName("推荐奖比例");
    			Param p2_1 = paramMapper.selectByName("拓展奖比例");
        		Param p2_2 = paramMapper.selectByName("拓展奖封顶额");
        		Param p3_1 = paramMapper.selectByName("培育奖比例");
        		Param p4_1 = paramMapper.selectByName("物流补助比例");
        		Param p5_1 = paramMapper.selectByName("消费奖比例");
        		Param p6_1 = paramMapper.selectByName("见点奖比例");
        		Param p7_1 = paramMapper.selectByName("领导奖标准");
        		Param p7_2 = paramMapper.selectByName("领导奖比例");
        		Param p9 = paramMapper.selectByName("奖金结算参数");
        		Param p10 = paramMapper.selectByName("结算控制参数");
        		Param p11 = paramMapper.selectByName("层碰奖比例");
        		if(p1_1!=null){
        		if(p2_1!=null){
        			if(p2_2!=null){
        				if(p3_1!=null){
        						if(p9!=null){
    			String table_name_r = "reward_day_"+st.getWeekTag();
        		String table_name_s = "settle_day_"+st.getWeekTag();
        		User sel_user = new User();
        		sel_user.setEndTime(endTime);
        		Map<String,Object> params = new HashMap<>();
        		params.put("user",sel_user);
        		List<User> ulist = userMapper.selectMoneyByPage(params);
        		Integer maxId= userMapper.maxId(params);
        		int num = 1;
        		if(maxId!=null) num =maxId+1;
        		if(num>1){
        			User[] user = new User[num+1];
        			DReward[] wrd = new DReward[num+1];
        			DSettle[] wst = new DSettle[num+1];
        			for(int i=0;i<ulist.size();i++){
        				int id = ulist.get(i).getId();
        				if(user[id]==null) user[id] = new User();
        				user[id].setId(id);
        				user[id].setUserId(ulist.get(i).getUserId()); 
        				user[id].setUserName(ulist.get(i).getUserName());
        				user[id].setRefereeAll(ulist.get(i).getRefereeAll());
        				user[id].setRankManage(ulist.get(i).getRankManage());
        				user[id].setRefereeNode(ulist.get(i).getRefereeNode());
        				user[id].setNode(ulist.get(i).getNode());
        				user[id].setRankJoinOld(ulist.get(i).getRankJoinOld());
        				user[id].setRankJoin(ulist.get(i).getRankJoin());
        				user[id].setRankJoinTag(ulist.get(i).getRankJoinTag());
        				user[id].setNodeABC(ulist.get(i).getNodeABC());
        				user[id].setEntryTime(ulist.get(i).getEntryTime());
        				user[id].setState(ulist.get(i).getState());
        				user[id].setRaiseNum(ulist.get(i).getRaiseNum());
        				user[id].setTotalIncome(ulist.get(i).getTotalIncome());
        				user[id].setTotalPrice(ulist.get(i).getTotalIncome());
        				user[id].setCenterType(ulist.get(i).getCenterType());
        				user[id].setJoinPrice(ulist.get(i).getJoinPrice());
        				user[id].setJoinPv(ulist.get(i).getJoinPv());
        				user[id].setIsEmpty(ulist.get(i).getIsEmpty());
        				user[id].setTag(0);
        				if(wrd[id]==null) wrd[id] = new DReward();
        				wrd[id].setUid(id);
        				wrd[id].setUserId(ulist.get(i).getUserId());
        				wrd[id].setUserName(ulist.get(i).getUserName());
        				wrd[id].setAmount_1((double) 0);
        				wrd[id].setAmount_2((double) 0);
        				wrd[id].setAmount_3((double) 0);
        				wrd[id].setAmount_4((double) 0);
        				wrd[id].setAmount_5((double) 0);
        				wrd[id].setAmount_6((double) 0);
        				wrd[id].setAmount_7((double) 0);
        				wrd[id].setAmount_8((double) 0);
        				wrd[id].setAmount_9((double) 0);
        				wrd[id].setAmount_10((double) 0);
        				wrd[id].setAmount_11((double) 0);
        				wrd[id].setAmount_12((double) 0);
        				wrd[id].setWeekTag(st.getWeekTag());
        				wrd[id].setStartTime(startTime);
        				wrd[id].setEndTime(endTime);
        				wrd[id].setPayTag(ulist.get(i).getPayTag());
        				wrd[id].setEntryTime(date);
        				wrd[id].setDayTime(dayTime);
        				if(wst[id]==null) wst[id] = new DSettle();
        				wst[id].setuId(id);
        				wst[id].setUserId(ulist.get(i).getUserId());
        				wst[id].setUserName(ulist.get(i).getUserName());
        				wst[id].setRank(ulist.get(i).getRank());
        				wst[id].setMaxRankNew(ulist.get(i).getRank());
        				wst[id].setRankJoin(ulist.get(i).getRankJoinOld());
        				wst[id].setRankJoinOld(ulist.get(i).getRankJoinOld());
        				wst[id].setNewPerformance((double) 0);
        				wst[id].setNewPrice((double) 0);
        				wst[id].setTotalPerformance((double) 0);
        				wst[id].setTotalPrice((double) 0);
        				wst[id].setTotalNum(0);
    					wst[id].setNewNum(0);
    					wst[id].setApv((double) 0);
    					wst[id].setAprice((double) 0);
    					wst[id].setAtprice((double) 0);
    					wst[id].setBpv((double) 0);
    					wst[id].setBprice((double) 0);
    					wst[id].setBtprice((double) 0);
    					wst[id].setAcpv((double) 0);
    					wst[id].setBcpv((double) 0);
    					wst[id].setRpv((double) 0);
    					wst[id].setRprice((double) 0);
    					wst[id].setRpv1((double) 0);
    					wst[id].setRprice1((double) 0);
    					wst[id].setAtpv((double) 0);
    					wst[id].setBtpv((double) 0);
    					wst[id].setRtpv((double) 0);
    					wst[id].setRtprice((double) 0);
    					wst[id].setRtpv1((double) 0);
    					wst[id].setRtprice1((double) 0);
    					wst[id].setPayTag(ulist.get(i).getPayTag());
    					wst[id].setNewNumReal(0);
    					wst[id].setTotalNumReal(0);
        				wst[id].setWeekTag(st.getWeekTag());
        				wst[id].setStartTime(st.getStartTime());
        				wst[id].setEndTime(st.getEndTime());
        				wst[id].setEntryTime(date);
        				wst[id].setDayTime(dayTime);
        				wst[id].setCtag(0);
        			}
        			DSettle sel_wst = new DSettle();
        			sel_wst.setEndTime(endTime);
        			params.put("wst", sel_wst);
            		List<DSettle> wlist = wstMapper.selectListWithParam(params);
            		for(int i=0;i<wlist.size();i++){
            			int id = wlist.get(i).getuId();
            			if(wst[id]!=null){
            				wst[id].setTotalPerformance(wlist.get(i).getTotalPerformance());
            				wst[id].setTotalPrice(wlist.get(i).getTotalPrice());
            				wst[id].setAtpv(wlist.get(i).getAtpv());
            				wst[id].setAtprice(wlist.get(i).getAtprice());
            				wst[id].setBtprice(wlist.get(i).getBtprice());
        					wst[id].setBtpv(wlist.get(i).getBtpv());
        					wst[id].setRtpv(wlist.get(i).getRtpv());
        					wst[id].setRtprice(wlist.get(i).getRtprice());
        					wst[id].setRtpv1(wlist.get(i).getRtpv1());
        					wst[id].setRtprice1(wlist.get(i).getRtprice1());
        					wst[id].setAcpv(wlist.get(i).getAcpv());
        					wst[id].setBcpv(wlist.get(i).getBcpv());
        					wst[id].setTotalNum(wlist.get(i).getTotalNum());
        					wst[id].setTotalNumReal(wlist.get(i).getTotalNumReal());
        					wst[id].setJoinPriceTal(wlist.get(i).getJoinPriceTal());
            				wst[id].setJoinPvTal(wlist.get(i).getJoinPvTal());
            				wst[id].setJoinPriceNew(wlist.get(i).getJoinPriceNew());
            				wst[id].setJoinPvNew(wlist.get(i).getJoinPvNew());
            				wst[id].setLeftNodeId(StringUtil.notNull(wlist.get(i).getLeftNodeId()));
            				wst[id].setRightNodeId(StringUtil.notNull(wlist.get(i).getRightNodeId()));
            				wst[id].setLeftNodePv(StringUtil.notNull(wlist.get(i).getLeftNodePv()));
            				wst[id].setRightNodePv(StringUtil.notNull(wlist.get(i).getRightNodePv()));
            				wst[id].setLeftNodePrice(StringUtil.notNull(wlist.get(i).getLeftNodePrice()));
            				wst[id].setRightNodePrice(StringUtil.notNull(wlist.get(i).getRightNodePrice()));
            				wst[id].setNodeTag(StringUtil.notNull(wlist.get(i).getNodeTag()));
            			}
            		}
            		
            		int newNum = 0;
    				double totalPv = 0;
    				double totalPrice = 0;
    				double totalPv1 = 0;
    				double totalPrice1 = 0;
    				double totalPv2 = 0;
    				double totalPrice2 = 0;
            		JoinInfo sel_jf = new JoinInfo();
            		sel_jf.setStartTime(startTime);
            		sel_jf.setEndTime(endTime);
            		sel_jf.setState(2);
            		params.put("joinInfo", sel_jf);
            		List<JoinInfo> jlist = jfMapper.selectAllByList(params);
    				for(int i=0;i<jlist.size();i++){
    					int uid = jlist.get(i).getUid();
    					String userId = jlist.get(i).getUserId();
    					double price =jlist.get(i).getPrice();
    					double pv = jlist.get(i).getPv();
    					int newRankJoin = jlist.get(i).getNewRankJoin();
    					int rid =  jlist.get(i).getRid();
    					int oldRankJoin = jlist.get(i).getOldRankJoin();
    					totalPv = ArithUtil.add(totalPv,pv);
    					totalPrice = ArithUtil.add(totalPrice,price);
    					wst[uid].setJoinPvTal(ArithUtil.add(wst[uid].getJoinPvTal(),pv));
    					wst[uid].setJoinPriceTal(ArithUtil.add(wst[uid].getJoinPriceTal(),price));
    					if(oldRankJoin==0) {
    						wst[uid].setJoinPvNew(ArithUtil.add(wst[uid].getJoinPvNew(),pv));
        					wst[uid].setJoinPriceNew(ArithUtil.add(wst[uid].getJoinPriceNew(),price));
    						if(!userId.equals(Constants.TOP_NODE)){
    							newNum++;
    							wst[rid].setNewNum(wst[rid].getNewNum()+1);
    							wst[rid].setTag(1);
	    						if(price>0||pv>0){
	    							wst[rid].setNewNumReal(wst[rid].getNewNumReal()+1);
	    							wst[rid].setTag(1);
	    						}
    						}
    					}else{
    						if(user[uid].getEntryTime().getTime()-endTime.getTime()<=0&&user[uid].getEntryTime().getTime()-startTime.getTime()>=0){
    							wst[uid].setJoinPvNew(ArithUtil.add(wst[uid].getJoinPvNew(),pv));
            					wst[uid].setJoinPriceNew(ArithUtil.add(wst[uid].getJoinPvNew(),price));
    						}
    					}
    					if(wst[uid].getRankJoin()<newRankJoin){
    						wst[uid].setRankJoin(newRankJoin);
    						if(pv==0&&price==0) wst[uid].setRankJoinTag(1);
    						wst[uid].setTag(1);
    					}
    					
    					if(price>0||pv>0){
    					if(oldRankJoin>0){
    						if(!StringUtil.notNull(user[uid].getNode()).equals("")){
    						 String[] str = user[uid].getNode().split(",");
    						 String[] strarray = user[uid].getNodeABC().split(",");
    						 String[] str1 = user[uid].getRefereeNode().split(",");
    						wst[uid].setRpv(ArithUtil.add(wst[uid].getRpv(),pv));
    						wst[uid].setRprice(ArithUtil.add(wst[uid].getRpv(),price));
    						wst[uid].setTag(1);
    						 for(int j=str.length-1;j>=0;j--){
    							if(!str[j].equals("")){
    								int sid = Integer.valueOf(str[j]);
    								 if(strarray[j].equals("A")){
    											wst[sid].setApv(ArithUtil.add(wst[sid].getApv(),pv));
    	    									wst[sid].setAprice(ArithUtil.add(wst[sid].getAprice(),price));
    	    									 wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
    	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
    	        								 wst[sid].setTag(1);
    								 } else if(strarray[j].equals("B")) {
    											wst[sid].setBpv(ArithUtil.add(wst[sid].getBpv(),pv));
    	    									wst[sid].setBprice(ArithUtil.add(wst[sid].getBprice(),price));
    	    									wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
	   	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
	   	        								 wst[sid].setTag(1);
    								 }
    							 }
    						 }
    						 
    						 for(int j=0;j<str1.length;j++){
    								if(!str1[j].equals("")){
    									int sid = Integer.valueOf(str1[j]);
    									 wst[sid].setRpv(ArithUtil.add(wst[sid].getRpv(),pv));
    									 wst[sid].setRprice(ArithUtil.add(wst[sid].getRprice(),price));
    									 wst[sid].setTag(1);
    								 }
    						 }
    						 }
    					}
    					}
    				}
    					
    					for(int i=0;i<jlist.size();i++){
        					int uid = jlist.get(i).getUid();
        					int oldRankJoin = jlist.get(i).getOldRankJoin();
    					if(oldRankJoin==0){
    						double price =wst[uid].getJoinPriceNew();
        					double pv = wst[uid].getJoinPvNew();
    						if(!StringUtil.notNull(user[uid].getNode()).equals("")){
    						 String[] str = user[uid].getNode().split(",");
    						 String[] strarray = user[uid].getNodeABC().split(",");
    						 String[] str1 = user[uid].getRefereeNode().split(",");
    						int t=0;
    						 for(int j=str.length-1;j>=0;j--){
    							if(!str[j].equals("")){
    								t++;
    								int sid = Integer.valueOf(str[j]);
									 String[] node_tag = wst[sid].getNodeTag().split(",");
									 int node_num = 0;
									 if(!wst[sid].getNodeTag().equals("")) node_num = node_tag.length;
    								 if(strarray[j].equals("A")){
    									 String[] node_str_id = wst[sid].getLeftNodeId().split(",");
    									 String[] node_str_pv  =wst[sid].getLeftNodePv().split(",");
    									 String[] node_str_price  =wst[sid].getLeftNodePrice().split(",");
    									 int node_num_l = 0;
    									 if(!wst[sid].getLeftNodeId().equals("")) node_num_l = node_str_id.length;
    									 if(t>node_num_l){
    										 if(t==1){
    											 wst[sid].setLeftNodeId(String.valueOf(uid));
    											 if(t>node_num){ 
    												 wst[sid].setNodeTag("0");
    											 }else if(node_tag[t-1].equals("1")){
    												 	wst[sid].setApv(ArithUtil.add(wst[sid].getApv(),pv));
    	    	    									wst[sid].setAprice(ArithUtil.add(wst[sid].getAprice(),price));
    	    	    									 wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
    	    	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
    	    	        								 wst[sid].setTag(1);
    											 }
	    										 wst[sid].setLeftNodePv(String.valueOf(pv));
		    									wst[sid].setLeftNodePrice(String.valueOf(price));
		    									wst[sid].setCtag(1);
    										 }else{
    											 wst[sid].setLeftNodeId(wst[sid].getLeftNodeId()+","+String.valueOf(uid));
    											 if(t>node_num){
    												 wst[sid].setNodeTag(wst[sid].getNodeTag()+","+"0");
    											 }else if(node_tag[t-1].equals("1")){
 												 	wst[sid].setApv(ArithUtil.add(wst[sid].getApv(),pv));
	    	    									wst[sid].setAprice(ArithUtil.add(wst[sid].getAprice(),price));
	    	    									 wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
	    	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
	    	        								 wst[sid].setTag(1);
    											 }
    											 wst[sid].setLeftNodePv(wst[sid].getLeftNodePv()+","+String.valueOf(pv));
    											 wst[sid].setLeftNodePrice(wst[sid].getLeftNodePrice()+","+String.valueOf(price));
    											 wst[sid].setCtag(1);
    										 }
    									 }else{
    										if(node_tag[t-1].equals("0")){
    											// System.out.println("userId:"+wst[sid].getUserId()+";getNodeTag:"+wst[sid].getNodeTag()+";getLeftNodeId:"+wst[sid].getLeftNodeId()+";getLeftNodePrice:"+wst[sid].getLeftNodePrice());
	    										int node_id = Integer.valueOf(node_str_id[t-1]);
	    										double node_pv = Double.valueOf(node_str_pv[t-1]);
	    										double node_price = Double.valueOf(node_str_price[t-1]);
	    										node_str_pv[t-1] = String.valueOf(ArithUtil.add(node_pv, pv));
	    										node_str_price[t-1] = String.valueOf(ArithUtil.add(node_price, price));
	    										if(ArithUtil.sub(wst[node_id].getJoinPvNew(), wst[uid].getJoinPvNew())>0){
	    											 node_str_id[t-1]=String.valueOf(uid);
	    											 //node_str_pv[t-1]=String.valueOf(wst[uid].getJoinPvNew());
	     											//node_str_price[t-1]=String.valueOf(wst[uid].getJoinPriceNew());
	    										 }
	    										for(int s=0;s<node_str_id.length;s++){
	    											if(s==0){
	    												 wst[sid].setLeftNodeId(node_str_id[s]);
	        											 wst[sid].setLeftNodePv(node_str_pv[s]);
	        											 wst[sid].setLeftNodePrice(node_str_price[s]);
	    											}else{
	    												 wst[sid].setLeftNodeId(wst[sid].getLeftNodeId()+","+node_str_id[s]);
	        											 wst[sid].setLeftNodePv(wst[sid].getLeftNodePv()+","+node_str_pv[s]);
	        											 wst[sid].setLeftNodePrice(wst[sid].getLeftNodePrice()+","+node_str_price[s]);
	    											}
	    										}
    										}else{
    											wst[sid].setApv(ArithUtil.add(wst[sid].getApv(),pv));
    	    									wst[sid].setAprice(ArithUtil.add(wst[sid].getAprice(),price));
    	    									 wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
    	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
    	        								 wst[sid].setTag(1);
    										}
    									 }
    								 } else if(strarray[j].equals("B")) {
    									 String[] node_str_id = wst[sid].getRightNodeId().split(",");
    									 String[] node_str_pv  =wst[sid].getRightNodePv().split(",");
    									 String[] node_str_price  =wst[sid].getRightNodePrice().split(",");
    									 int node_num_l = 0;
    									 if(!wst[sid].getRightNodeId().equals("")) node_num_l = node_str_id.length;
    									 if(t>node_num_l){
    										 if(t==1){
    											 wst[sid].setRightNodeId(String.valueOf(uid));
    											 if(t>node_num){
    												 wst[sid].setNodeTag("0");
    											 }else if(node_tag[t-1].equals("1")){
    												 wst[sid].setBpv(ArithUtil.add(wst[sid].getBpv(),pv));
 	    	    									wst[sid].setBprice(ArithUtil.add(wst[sid].getBprice(),price));
 	    	    									wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
 		   	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
 		   	        								 wst[sid].setTag(1);
    											 }
    											 wst[sid].setRightNodePv(String.valueOf(pv));
    											 wst[sid].setRightNodePrice(String.valueOf(price));
    											 wst[sid].setCtag(1);
    										 }else{
    											 wst[sid].setRightNodeId(wst[sid].getRightNodeId()+","+String.valueOf(uid));
    											 if(t>node_num){
    												 wst[sid].setNodeTag(wst[sid].getNodeTag()+","+"0");
    											 }else if(node_tag[t-1].equals("1")){
    												 wst[sid].setBpv(ArithUtil.add(wst[sid].getBpv(),pv));
 	    	    									wst[sid].setBprice(ArithUtil.add(wst[sid].getBprice(),price));
 	    	    									wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
 		   	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
 		   	        								 wst[sid].setTag(1);
    											 }
    											 wst[sid].setRightNodePv(wst[sid].getRightNodePv()+","+String.valueOf(pv));
    											 wst[sid].setRightNodePrice(wst[sid].getRightNodePrice()+","+String.valueOf(price));
    											 wst[sid].setCtag(1);
    										 }
    									 }else{
    										if(node_tag[t-1].equals("0")){
	    										int node_id = Integer.valueOf(node_str_id[t-1]);
	    										double node_pv = Double.valueOf(node_str_pv[t-1]);
	    										double node_price = Double.valueOf(node_str_price[t-1]);
	    										node_str_pv[t-1] = String.valueOf(ArithUtil.add(node_pv, pv));
	    										node_str_price[t-1] = String.valueOf(ArithUtil.add(node_price, price));
	    										if(ArithUtil.sub(wst[node_id].getJoinPvNew(), wst[uid].getJoinPvNew())>0){
	    											 node_str_id[t-1]=String.valueOf(uid);
	    											// node_str_pv[t-1]=String.valueOf(wst[uid].getJoinPvNew());
	     											//node_str_price[t-1]=String.valueOf(wst[uid].getJoinPriceNew());
	    										 }
	    										for(int s=0;s<node_str_id.length;s++){
	    											if(s==0){
	    												 wst[sid].setRightNodeId(node_str_id[s]);
	        											 wst[sid].setRightNodePv(node_str_pv[s]);
	        											 wst[sid].setRightNodePrice(node_str_price[s]);
	    											}else{
	    												 wst[sid].setRightNodeId(wst[sid].getRightNodeId()+","+node_str_id[s]);
	        											 wst[sid].setRightNodePv(wst[sid].getRightNodePv()+","+node_str_pv[s]);
	        											 wst[sid].setRightNodePrice(wst[sid].getRightNodePrice()+","+node_str_price[s]);
	    											}
	    										}
    										}else{
    											wst[sid].setBpv(ArithUtil.add(wst[sid].getBpv(),pv));
    	    									wst[sid].setBprice(ArithUtil.add(wst[sid].getBprice(),price));
    	    									wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
	   	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
	   	        								 wst[sid].setTag(1);
    										}
    									 }
    								 }
    							
    							 }
    						 }
    						 for(int j=0;j<str1.length;j++){
    								if(!str1[j].equals("")){
    									int sid = Integer.valueOf(str1[j]);
    									 wst[sid].setRpv(ArithUtil.add(wst[sid].getRpv(),pv));
    									 wst[sid].setRprice(ArithUtil.add(wst[sid].getRprice(),price));
    									 wst[sid].setTag(1);
    								 }
    						 }
    						 }
    					}
    				}
    				//统计业绩
    				Order sel_od = new Order();
    				sel_od.setStartTime(startTime);
    				sel_od.setEndTime(endTime);
    				sel_od.setOrderType(2);
            		params.put("order", sel_od);
            		List<Order> olist = orderMapper.selectByList(params);
            		for(int j=0;j<olist.size();j++){
    					double price =  olist.get(j).getPrice();
    					double pv =  olist.get(j).getPv();
    						totalPrice1 = ArithUtil.add(totalPrice1,price);
    						totalPv1 = ArithUtil.add(totalPv1,pv);
            		}
            		sel_od.setOrderType(3);
            		params.put("order", sel_od);
            		List<Order> olist1 = orderMapper.selectByList(params);
            		for(int j=0;j<olist1.size();j++){
            			int uid = olist1.get(j).getuId();
    					double price =  olist1.get(j).getPrice();
    					double pv =  olist1.get(j).getPv();
    					
    						totalPv2 = ArithUtil.add(totalPrice2,pv);
    						totalPrice2 = ArithUtil.add(totalPrice2,price);
    					if(price>0||pv>0){
    						String[] str1 = user[uid].getRefereeNode().split(",");
    						wst[uid].setRpv1(ArithUtil.add(wst[uid].getRpv1(),pv));
    						wst[uid].setRprice1(ArithUtil.add(wst[uid].getRprice1(),price));
    						wst[uid].setTag(1);
    						 for(int i=0;i<str1.length;i++){
    								if(!str1[i].equals("")){
    									int sid = Integer.valueOf(str1[i]);
    									 wst[sid].setRpv1(ArithUtil.add(wst[sid].getRpv1(),pv));
    									 wst[sid].setRprice1(ArithUtil.add(wst[sid].getRprice1(),price));
    									 wst[sid].setTag(1);
    								 }
    						 }
    					}
            		}
            		st.setNewPerformance(totalPv);
            		st.setNewPrice(totalPrice);
            		st.setNewPerformance_1(totalPv1);
            		st.setNewPrice_1(totalPrice1);
            		st.setNewPerformance_2(totalPv2);
            		st.setNewPrice_2(totalPrice2);
            		st.setNewNum(newNum);
            		
            		 // 推荐奖计算
            		for(int i=0;i<jlist.size();i++){
            					double property = 0;
            					int rid = jlist.get(i).getRid();	
            					double pv = jlist.get(i).getPv();
            					if(wst[rid]!=null){
            					if(wst[rid].getRankJoinOld()==1) property = p1_1.getAmount_1();
            					else if(wst[rid].getRankJoinOld()==2) property = p1_1.getAmount_2();
            					else if(wst[rid].getRankJoinOld()==3) property = p1_1.getAmount_3();
            					else if(wst[rid].getRankJoinOld()==4) property = p1_1.getAmount_4();
            					else if(wst[rid].getRankJoinOld()==5) property = p1_1.getAmount_5();
            					double award = ArithUtil.mul(pv, property);
            				if(award>0){
            					if(wrd[rid]!=null){
            						wrd[rid].setAmount_1(ArithUtil.add(wrd[rid].getAmount_1(), ArithUtil.mul(award,p10.getAmount_1())));
            					}
            				}
            			}
            		}
            		
            		//计算层碰奖
            		for(int i=0;i<wst.length;i++){
            			if(wst[i]!=null){
            				if(wst[i].getCtag()==1){
            					String[] left_node_id = wst[i].getLeftNodeId().split(",");
            					String[] right_node_id = wst[i].getRightNodeId().split(",");
            					String[] left_node_pv = wst[i].getLeftNodePv().split(",");
            					String[] right_node_pv = wst[i].getRightNodePv().split(",");
            					String[] left_node_price = wst[i].getLeftNodePrice().split(",");
            					String[] right_node_price = wst[i].getRightNodePrice().split(",");
            					String[] node_tag = wst[i].getNodeTag().split(",");
            					int realNum = wst[i].getTotalNumReal()+wst[i].getNewNumReal();
            					int t_tag=0;
            					int left_num = 0;
    							int right_num=0;
    							if(!wst[i].getLeftNodeId().equals("")) left_num = left_node_id.length;
    							if(!wst[i].getRightNodeId().equals("")) right_num = right_node_id.length;
            					for(int j=0;j<node_tag.length;j++){
            						if(node_tag[j].equals("0")){
            							if(left_num>=(j+1)&&right_num>=(j+1)){
            								double cpv =Math.min(wst[i].getJoinPvTal(), Math.min(Double.valueOf(left_node_pv[j]), Double.valueOf(right_node_pv[j])));
            								double cprice =Math.min(wst[i].getJoinPriceTal(), Math.min(Double.valueOf(left_node_price[j]), Double.valueOf(right_node_price[j])));
            								double pro = 0;
	            							if(j==0) pro=p11.getAmount_1();
	            							else pro = p11.getAmount_2();
	            							double award = ArithUtil.mul(cpv, pro);
	            							if(award>0&&realNum>0)
	            									wrd[i].setAmount_3(ArithUtil.add(wrd[i].getAmount_3(),award));
	            								
	            							node_tag[j]="1";
	            							t_tag=1;
	            							double apv = ArithUtil.sub(Double.valueOf(left_node_pv[j]),cpv);
	            							double bpv = ArithUtil.sub(Double.valueOf(right_node_pv[j]),cpv);
	            							double aprice = ArithUtil.sub(Double.valueOf(left_node_price[j]),cprice);
	            							double bprice= ArithUtil.sub(Double.valueOf(right_node_price[j]),cprice);
	            							wst[i].setApv(ArithUtil.add(wst[i].getApv(), apv));
	            							wst[i].setBpv(ArithUtil.add(wst[i].getBpv(), bpv));
	            							wst[i].setAprice(ArithUtil.add(wst[i].getAprice(), aprice));
	            							wst[i].setBprice(ArithUtil.add(wst[i].getBprice(), bprice));
	            							wst[i].setNewPerformance(ArithUtil.add(wst[i].getNewPerformance(),ArithUtil.add(apv, bpv)));
  	        								wst[i].setNewPrice(ArithUtil.add(wst[i].getNewPrice(),ArithUtil.add(aprice, bprice)));
  	        								wst[i].setTag(1);
            							}
            						}
            					}
            					if(t_tag==1){
            						for(int j=0;j<node_tag.length;j++){
            							if(j==0){
											 wst[i].setNodeTag(node_tag[j]);
										}else{
											 wst[i].setNodeTag(wst[i].getNodeTag()+","+node_tag[j]);
										}
            						}
        						}
        							

            				}
            			}
            		}
            		/*
            		 * 拓展奖
            		 */
            		for(int i=0;i<wst.length;i++){
            			if(wst[i]!=null){
            				int id = i;
            				int realNum = wst[i].getTotalNumReal()+wst[i].getNewNumReal();
            				double apv =0,bpv =0,acpv=0,bcpv=0;
            				apv= ArithUtil.add(wst[id].getApv(),wst[id].getAcpv());
            				bpv = ArithUtil.add(wst[id].getBpv(),wst[id].getBcpv());
            				double money = 0;
            				double b =0;
            				double max = 0;
            				if(wst[id].getRankJoinOld()==1) {
            					b = p2_1.getAmount_1();
            					max = p2_2.getAmount_1();
            				}else if(wst[id].getRankJoinOld()==2) {
            					b = p2_1.getAmount_2();
            					max = p2_2.getAmount_2();
            				}else if(wst[id].getRankJoinOld()==3) {
            					b = p2_1.getAmount_3();
            					max = p2_2.getAmount_3();
            				}else if(wst[id].getRankJoinOld()==4) {
            					b = p2_1.getAmount_4();
            					max = p2_2.getAmount_4();
            				}else if(wst[id].getRankJoinOld()==5) {
            					b = p2_1.getAmount_5();
            					max = p2_2.getAmount_5();
            				}
            			
            				if(apv-bpv>=0){
            					money = ArithUtil.mul(bpv,b);
            					acpv = ArithUtil.sub(apv,bpv);
            					wst[id].setBcpv((double) 0);
            					wst[id].setTag(1);
            					if(acpv>0) {
            						wst[id].setAcpv(acpv);
            					}
            				}else{
            					money = ArithUtil.mul(apv,b);
            					bcpv = ArithUtil.sub(bpv,apv);
            					wst[id].setAcpv((double) 0);
            					wst[id].setTag(1);
            					if(bcpv>0){
            						wst[id].setBcpv(bcpv);
            					}
            				}
            				if(money>0){
            					if(money>max) money = max;
            					if(realNum>0){
	            					wrd[id].setAmount_4(ArithUtil.mul(money, p10.getAmount_3()));
	            					wrd[id].setTag(1);
            					}
            				}
            			}
            		}
            		//培育补贴
            		for(int s=0;s<wrd.length;s++){
            			if(wrd[s]!=null){
            				if(wrd[s].getAmount_4()>0){
            					int id = s;
            					double amount =wrd[id].getAmount_4();
            					String[] str = StringUtil.notNull(user[id].getRefereeNode()).split(",");
            					int t =1;
            					for(int j=str.length;j>0;j--){
            						if(!str[j-1].equals("")){
            							int sid = Integer.valueOf(str[j-1]);
            							double award = 0;
            							if(t<=3){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								if(referee_num>=3&&wst[sid].getRankJoinOld()>=1){
            									award = ArithUtil.mul(amount,p3_1.getAmount_1());
            									wrd[sid].setAmount_5(ArithUtil.add(wrd[sid].getAmount_5(),award));
            									wrd[sid].setTag(1);
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            							}else if(t<=6){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								//System.out.println("userId:"+wst[sid].getUserId()+";t:"+t+";referee_num:"+referee_num);

            								if(referee_num>=6&&wst[sid].getRankJoinOld()>=2){
            									award = ArithUtil.mul(amount,p3_1.getAmount_2());
            									wrd[sid].setAmount_5(ArithUtil.add(wrd[sid].getAmount_5(),award));
            									wrd[sid].setTag(1);
            									t--;
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            							}
            							else if(t<=9){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								//System.out.println("userId:"+wst[sid].getUserId()+";t:"+t+";referee_num:"+referee_num);

            								if(referee_num>=9&&wst[sid].getRankJoinOld()>=3){
            									award = ArithUtil.mul(amount,p3_1.getAmount_3());
            									wrd[sid].setAmount_5(ArithUtil.add(wrd[sid].getAmount_5(),award));
            									wrd[sid].setTag(1);
            									t--;
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            							}
            						}
            						if(t>=9){
	            						j=0;
	            						break;
            						}
            						t++;
            					}
            				}
            			}
            		}
            		
            		//物流补助
            		sel_od.setOrderType(1);
            		params.put("orders", sel_od);
            		List<Order> olist2 = orderMapper.selectByList(params);
            		sel_od.setOrderType(5);
            		params.put("orders", sel_od);
            		List<Order> olist3 = orderMapper.selectByList(params);
            		olist2.addAll(olist3);
            		for(int i=0;i<olist2.size();i++){
            			int id = olist2.get(i).getuId();
            			double pv = olist2.get(i).getPv();
            			int centerId = olist2.get(i).getCenterId();
            			double award=0;
            			if(centerId>0){
            				double perporty = 0;
            				if(user[centerId]!=null){
            					if(pv>0){
            					if(user[centerId].getCenterType()==1) perporty= p4_1.getAmount_1();
            					if(user[centerId].getCenterType()==2) perporty= p4_1.getAmount_2();
            					if(user[centerId].getCenterType()==3) perporty= p4_1.getAmount_3();
            					award = ArithUtil.mul(pv, perporty);
            					wrd[centerId].setAmount_7(ArithUtil.add(wrd[centerId].getAmount_7(),ArithUtil.mul(award,p10.getAmount_6())));
            					wrd[centerId].setTag(1);
            					//String sql2 = cs.insetWRewardDetail(conn, r_table_name_d, user[centerId], user[id], 5, award, "计奖业绩："+pv, st, date);
            					//slist.add(sql2);
            					}
            				}
            			}
            		}
            		
            		//消费奖
            		double totalPerformance =0;
            		for(int i=0;i<olist1.size();i++){
            			int id = olist1.get(i).getuId();
            			double amount = olist1.get(i).getPv();
            			if(amount>0){
            			double p = 0;
            			double	award = ArithUtil.mul(amount, p5_1.getAmount_1());
            			if(user[id]!=null){
            				if(!StringUtil.notNull(user[id].getRefereeNode()).equals("")){
            					String[] str = user[id].getRefereeNode().split(",");
            					double d =p5_1.getAmount_2();
            					int t=(int) d;
            					for(int j=str.length;j>0;j--){
            						if(!str[j-1].equals("")){
            							int sid = Integer.valueOf(str[j-1]);
            							if(wrd[sid]!=null){
            								if(t>0){
            									wrd[sid].setAmount_8(ArithUtil.add(wrd[sid].getAmount_8(), ArithUtil.mul(award,p10.getAmount_7())));
            									wrd[sid].setTag(1);
            									t--;
            								}
            							}
            						}
            					}
            				}
            			}
            			}
            		}
            		
            		 // 见点奖计算
            		for(int i=0;i<user.length;i++){
            			if(user[i]!=null){
            				if(wrd[i]!=null){
            					double total = ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(wrd[i].getAmount_1(), wrd[i].getAmount_2()), wrd[i].getAmount_3()), wrd[i].getAmount_4()), wrd[i].getAmount_5()), wrd[i].getAmount_6()), wrd[i].getAmount_7()), wrd[i].getAmount_8());
            					user[i].setTotalPrice(ArithUtil.add(user[i].getTotalPrice(), total));
            				}
            			}
            		}
            		for(int i=0;i<jlist.size();i++){
            			int id = jlist.get(i).getUid();
            				double pv = jlist.get(i).getPv();
            				if(user[id]!=null){
            					if(!StringUtil.notNull(user[id].getNode()).equals("")){
            						if(user[id].getEntryTime().getTime()-startTime.getTime()>=0){
            							String[] str = user[id].getNode().split(",");
            							double award = ArithUtil.mul(pv, p6_1.getAmount_1());
            							for(int j=0;j<str.length;j++){
            								if(!str[j].equals("")){
            									int sid = Integer.valueOf(str[j]);
            									if(user[sid]!=null){
            										Timestamp val_time = new Timestamp(StringUtil.parseToDate("2018-01-08 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
            										if(user[sid].getEntryTime().getTime()-val_time.getTime()>0){
            										if(user[sid].getIsEmpty()==0){
            											if(award>0){
            												if(wrd[sid]!=null){
            													wrd[sid].setAmount_2(ArithUtil.add(wrd[sid].getAmount_2(),ArithUtil.mul(award,p10.getAmount_2())));
            												}
            											}
            										}
            									}
            									}
            								}
            							}
            						}
            					}
            				}	
            		}
            		
            		
            		//计算等级和领导奖
            		Calendar calendar = Calendar.getInstance();
            		calendar.setTime(dayTime);
            		int month = Calendar.MONTH+1;
            		calendar.add(Calendar.DAY_OF_MONTH, 1);
            		int month1 = Calendar.MONTH+1;
            		
            		if(month1-month>0){
            		calendar.setTime(dayTime);
            		calendar.set(Calendar.DAY_OF_MONTH, 1);
            		Timestamp firstDayOfMonth = new Timestamp(calendar.getTime().getTime());  
            		Timestamp firstTime = new Timestamp(StringUtil.parseToDate(StringUtil.parseToString(firstDayOfMonth, DateUtil.yyyyMMdd)+" 00:00:00",DateUtil.yyyyMMddHHmmss).getTime());
            		calendar.add(Calendar.MONTH, 1);
            		calendar.add(Calendar.DAY_OF_MONTH, -1);
            		Timestamp lastDayOfMonth = new Timestamp(calendar.getTime().getTime());  
            		Timestamp lastTime = new Timestamp(StringUtil.parseToDate(StringUtil.parseToString(lastDayOfMonth, DateUtil.yyyyMMdd)+" 23:59:59",DateUtil.yyyyMMddHHmmss).getTime());
            		
            		List<User> ulist1 =new ArrayList<User>();
            		List<User> ulist2 =new ArrayList<User>();
            		List<User> ulist3 =new ArrayList<User>();
            		List<User> ulist4 =new ArrayList<User>();
            		List<User> ulist5 =new ArrayList<User>();
            		for(int i=user.length-1;i>0;i--){
            			if(user[i]!=null){
            				if(user[i].getState()>0){
            					double total = ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(wrd[i].getAmount_1(), wrd[i].getAmount_2()), wrd[i].getAmount_3()), wrd[i].getAmount_4()), wrd[i].getAmount_5()), wrd[i].getAmount_6()), wrd[i].getAmount_7()), wrd[i].getAmount_8());
            					user[i].setTotalIncome(ArithUtil.add(user[i].getTotalIncome(), total));
            					if(user[i].getEntryTime().getTime()-st.getEndTime().getTime()<=0){
		            				int rank = 0;
		            				if(ArithUtil.sub(user[i].getTotalIncome(),p7_1.getAmount_5())>=0) rank =5;
		            				else if(ArithUtil.sub(user[i].getTotalIncome(),p7_1.getAmount_4())>=0) rank =4;
		            				else if(ArithUtil.sub(user[i].getTotalIncome(),p7_1.getAmount_3())>=0) rank =3;
		            				else if(ArithUtil.sub(user[i].getTotalIncome(),p7_1.getAmount_2())>=0) rank =2;
		            				else if(ArithUtil.sub(user[i].getTotalIncome(),p7_1.getAmount_1())>=0) rank =1;
	            					if(wst[i].getRank()<rank){
	            						wst[i].setRank(rank);
	            					}
	            					if(wst[i].getRank()==1) ulist1.add(user[i]);
	            					else if(wst[i].getRank()==2) ulist2.add(user[i]);
	            					else if(wst[i].getRank()==3) ulist3.add(user[i]);
	            					else if(wst[i].getRank()==4) ulist4.add(user[i]);
	            					else if(wst[i].getRank()==5) ulist5.add(user[i]);
            					}
            				
            				}
            			}
            		}
            		List<SettleDay> sdlist = stMapper.selectBySql("select * from settle_day where dayTime>='"+firstTime+"' and dayTime<='"+lastTime+"'");
            		totalPerformance = st.getNewPerformance();
            		for(int i=0;i<sdlist.size();i++){
            			totalPerformance = ArithUtil.add(totalPerformance, sdlist.get(i).getNewPerformance());	
            		}
            		
            		//计算领导奖
            		int size1 = ulist1.size();
            		if(totalPerformance>0&&size1>0){
            			double award = ArithUtil.div(ArithUtil.mul(totalPerformance, p7_2.getAmount_1()),size1);
	            		if(award>0){
	            			for(int i=0;i<ulist1.size();i++){
	            				int id=ulist1.get(i).getId();
		            			wrd[id].setAmount_6(ArithUtil.add(wrd[id].getAmount_6(),ArithUtil.mul(award,p10.getAmount_1())));
		            			wrd[id].setTag(1);
		            		}
	            		}
            		}
            		
            		int size2 = ulist2.size();
            		if(totalPerformance>0&&size2>0){
            			double award = ArithUtil.div(ArithUtil.mul(totalPerformance, p7_2.getAmount_2()),size2);
	            		if(award>0){
	            			for(int i=0;i<ulist2.size();i++){
	            				int id=ulist2.get(i).getId();
		            			wrd[id].setAmount_6(ArithUtil.add(wrd[id].getAmount_6(),ArithUtil.mul(award,p10.getAmount_2())));
		            			wrd[id].setTag(1);
		            		}
	            		}
            		}
            		
            		int size3 = ulist3.size();
            		if(totalPerformance>0&&size3>0){
            			double award = ArithUtil.div(ArithUtil.mul(totalPerformance, p7_2.getAmount_3()),size3);
	            		if(award>0){
	            			for(int i=0;i<ulist3.size();i++){
	            				int id=ulist3.get(i).getId();
		            			wrd[id].setAmount_6(ArithUtil.add(wrd[id].getAmount_6(),ArithUtil.mul(award,p10.getAmount_3())));
		            			wrd[id].setTag(1);
		            		}
	            		}
            		}
            		
            		int size4 = ulist4.size();
            		if(totalPerformance>0&&size4>0){
            			double award = ArithUtil.div(ArithUtil.mul(totalPerformance, p7_2.getAmount_4()),size4);
	            		if(award>0){
	            			for(int i=0;i<ulist4.size();i++){
	            				int id=ulist4.get(i).getId();
		            			wrd[id].setAmount_6(ArithUtil.add(wrd[id].getAmount_6(),ArithUtil.mul(award,p10.getAmount_4())));
		            			wrd[id].setTag(1);
		            		}
	            		}
            		}
            		
            		int size5 = ulist5.size();
            		if(totalPerformance>0&&size5>0){
            			double award = ArithUtil.div(ArithUtil.mul(totalPerformance, p7_2.getAmount_5()),size5);
	            		if(award>0){
	            			for(int i=0;i<ulist5.size();i++){
	            				int id=ulist5.get(i).getId();
		            			wrd[id].setAmount_6(ArithUtil.add(wrd[id].getAmount_6(),ArithUtil.mul(award,p10.getAmount_5())));
		            			wrd[id].setTag(1);
		            		}
	            		}
            		}
            		}
            		//计算复消
            		List<DReward> wrdlist = new ArrayList<DReward>();
            		int n1 =1;
            		int error1=0;
            		for(int i=0;i<wrd.length;i++){
            			if(wrd[i]!=null){
            				wrd[i].setRankJoin(wst[i].getRankJoin());
            				wrd[i].setRank(wst[i].getRank());
        					wrd[i].setAmount_9(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(wrd[i].getAmount_1(),wrd[i].getAmount_2()),wrd[i].getAmount_3()),wrd[i].getAmount_4()),wrd[i].getAmount_5()),wrd[i].getAmount_6()),wrd[i].getAmount_7()),wrd[i].getAmount_8()));
        					if(wrd[i].getAmount_9()>0){
        						wrd[i].setAmount_10(ArithUtil.mul(wrd[i].getAmount_9(), p9.getAmount_1()));
        						wrd[i].setAmount_11(ArithUtil.mul(wrd[i].getAmount_9(), p9.getAmount_2()));
        						wrd[i].setAmount_12(ArithUtil.sub(ArithUtil.sub(wrd[i].getAmount_9(), wrd[i].getAmount_10()), wrd[i].getAmount_11()));
        					}
        					if(n1%800==0){
	        					wrdlist.add(wrd[i]);
	        					params.put("tableName", table_name_r);
	                    		params.put("list", wrdlist);
	                    		Integer wrd_insert = wrdMapper.insertAll(params);
	                    		if(wrd_insert==null || wrd_insert==0)  error1++;
	                    		wrdlist = new ArrayList<DReward>();
        					}else if(i<wrd.length-1){
        						wrdlist.add(wrd[i]);
        					}else{
        						wrdlist.add(wrd[i]);
        						params.put("tableName", table_name_r);
	                    		params.put("list", wrdlist);
	                    		Integer wrd_insert = wrdMapper.insertAll(params);
	                    		if(wrd_insert==null || wrd_insert==0)  error1++;
	                    		wrdlist =null;
        					}
        					n1++;
            			}
            			if(i==wrd.length-1&&wrdlist!=null&&wrdlist.size()>0){
            				params.put("tableName", table_name_r);
                    		params.put("list", wrdlist);
                    		Integer wrd_insert = wrdMapper.insertAll(params);
                    		if(wrd_insert==null || wrd_insert==0)  error1++;
                    		wrdlist =null;
            			}
            		}
            		int n2 =1;
            		int error2=0;
            		List<DSettle> wstlist = new ArrayList<DSettle>();
            		for(int i=0;i<wst.length;i++){
            			if(wst[i]!=null){
            				if(n2%800==0){
            					wstlist.add(wst[i]);
	        					params.put("tableName", table_name_s);
	                    		params.put("list", wstlist);
	                    		Integer wst_insert = wstMapper.insertAll(params);
	                    		if(wst_insert==null || wst_insert==0)  error2++;
	                    		wstlist = new ArrayList<DSettle>();
        					}else if(i<wst.length-1){
        						wstlist.add(wst[i]);
        					}else{
        						wstlist.add(wst[i]);
        						params.put("tableName", table_name_s);
	                    		params.put("list", wstlist);
	                    		Integer wst_insert = wstMapper.insertAll(params);
	                    		if(wst_insert==null || wst_insert==0)  error2++;
	                    		wstlist =null;
        					}
        					n2++;
            			}
            			if(i==wst.length-1&&wstlist!=null&&wstlist.size()>0){
            				params.put("tableName", table_name_s);
                    		params.put("list", wstlist);
                    		Integer wst_insert = wstMapper.insertAll(params);
                    		if(wst_insert==null || wst_insert==0)  error2++;
                    		wstlist =null;
            			}
            		}
            		//保存相关的表
            		//保存结算表
            		

            		st.setState(2);
            		if(stMapper.update(st)>0){
            		if(error1==0){
            		if(error2==0){
            			
            			msg ="yes";
            			
            		}else{
            			msg = "会员结算表保存失败。";
            			sqlSession.rollback();
            		}
            		}else{
            			msg = "会员奖金表保存失败。";
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
        				msg="拓展奖比例参数获取失败。";
        				sqlSession.rollback();
            		}
        		}else{
        			msg="创业奖参数获取失败。";
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
    
    public String confirmReward(Timestamp dayTime){
    	String msg="";
    	try{
    		SettleDay st = stMapper.selectByDayTime(dayTime);
    		String str_time = StringUtil.parseToString(dayTime, DateUtil.yyyyMMdd);
    		Timestamp startTime = new Timestamp(StringUtil.parseToDate(str_time+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
    		Timestamp endTime = new Timestamp(StringUtil.parseToDate(str_time+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
    		if(st!=null){
    			String table_name_r = "reward_day_"+st.getWeekTag();
        		String table_name_s = "settle_day_"+st.getWeekTag();
        		User sel_user = new User();
        		sel_user.setEndTime(endTime);
        		Map<String,Object> params = new HashMap<>();
        		params.put("user",sel_user);
        		List<User> ulist = userMapper.selectMoneyByPage(params);
        		Integer maxId= userMapper.maxId(params);
        		int num = 1;
        		if(maxId!=null) num =maxId+1;
        		if(num>1){
        			User[] user = new User[num+1];
        			DSettle[] wst = new DSettle[num+1];
        			for(int i=0;i<ulist.size();i++){
        				int id = ulist.get(i).getId();
        				if(user[id]==null) user[id] = new User();
        				user[id].setId(id);
        				user[id].setUserId(ulist.get(i).getUserId()); 
        				user[id].setUserName(ulist.get(i).getUserName());
        				user[id].setRefereeAll(ulist.get(i).getRefereeAll());
        				user[id].setRankManage(ulist.get(i).getRankManage());
        				user[id].setRank(ulist.get(i).getRank());
        				user[id].setRefereeNode(ulist.get(i).getRefereeNode());
        				user[id].setNode(ulist.get(i).getNode());
        				user[id].setRankJoinOld(ulist.get(i).getRankJoinOld());
        				user[id].setRankJoin(ulist.get(i).getRankJoin());
        				user[id].setRankJoinTag(ulist.get(i).getRankJoinTag());
        				user[id].setNodeABC(ulist.get(i).getNodeABC());
        				user[id].setEntryTime(ulist.get(i).getEntryTime());
        				user[id].setState(ulist.get(i).getState());
        				user[id].setRaiseNum(ulist.get(i).getRaiseNum());
        				user[id].setTotalIncome(ulist.get(i).getTotalIncome());
        				user[id].setTag(0);
        			}
        			DSettle wst_sel = new DSettle();
        			wst_sel.setEndTime(endTime);
        			params.put("wst",wst_sel);
        			List<DSettle> wslist = wstMapper.selectListWithParam(params);
        			for(int i=0;i<wslist.size();i++){
        				int id = wslist.get(i).getuId();
        				if(wst[id]==null) wst[id] = new DSettle();
        				wst[id].setId(wslist.get(i).getId());
        				wst[id].setTotalPerformance(wslist.get(i).getTotalPerformance());
        				wst[id].setTotalPrice(wslist.get(i).getTotalPrice());
        				wst[id].setTotalNum(wslist.get(i).getTotalNum());
    					wst[id].setAtprice(wslist.get(i).getAtprice());
    					wst[id].setBtprice(wslist.get(i).getBtprice());
    					wst[id].setAcpv(wslist.get(i).getAcpv());
    					wst[id].setBcpv(wslist.get(i).getBcpv());
    					wst[id].setAtpv(wslist.get(i).getAtpv());
    					wst[id].setBtpv(wslist.get(i).getBtpv());
    					wst[id].setRtpv(wslist.get(i).getRtpv());
    					wst[id].setRtprice(wslist.get(i).getRtprice());
    					wst[id].setRtpv1(wslist.get(i).getRtpv1());
    					wst[id].setRtprice1(wslist.get(i).getRtprice1());
    					wst[id].setJoinPriceTal(wslist.get(i).getJoinPriceTal());
    					wst[id].setJoinPvTal(wslist.get(i).getJoinPvTal());
    					wst[id].setJoinPriceNew(wslist.get(i).getJoinPriceNew());
    					wst[id].setJoinPvNew(wslist.get(i).getJoinPvNew());
    					wst[id].setTotalNumReal(wslist.get(i).getTotalNumReal());
    					wst[id].setLeftNodeId(StringUtil.notNull(wslist.get(i).getLeftNodeId()));
    					wst[id].setRightNodeId(StringUtil.notNull(wslist.get(i).getRightNodeId()));
    					wst[id].setNodeTag(StringUtil.notNull(wslist.get(i).getNodeTag()));
    					wst[id].setLeftNodePv(wslist.get(i).getLeftNodePv());
    					wst[id].setRightNodePv(wslist.get(i).getRightNodePv());
    					wst[id].setLeftNodePrice(wslist.get(i).getLeftNodePrice());
    					wst[id].setRightNodePrice(wslist.get(i).getRightNodePrice());
    					wst[id].setTag(0);
        			}
        			params.put("tableName",table_name_s);
        			wst_sel.setDayTime(st.getDayTime());
        			params.put("wst",wst_sel);
        			List<DSettle> wlist = wstMapper.selectAll(params);
        			for(int i=0;i<wlist.size();i++){
        				int id = wlist.get(i).getuId();
        				if(user[id].getRank()<wlist.get(i).getRank()){
        					user[id].setRank(wlist.get(i).getRank());
        					user[id].setTag(1);
        				}
        				if(user[id].getRankJoinOld()<wlist.get(i).getRankJoin()){
        					user[id].setRankJoinOld(wlist.get(i).getRankJoin());
        					user[id].setTag(1);
        				}
        				if(user[id].getRankJoinTag()!=wlist.get(i).getRankJoinTag()){
        					user[id].setRankJoinTag(wlist.get(i).getRankJoin());
        					user[id].setTag(1);
        				}
        				if(wst[id]!=null){
        					if(wlist.get(i).getNewPerformance()>0){
        						wst[id].setTotalPerformance(ArithUtil.add(wlist.get(i).getTotalPerformance(), wlist.get(i).getNewPerformance()));
        						wst[id].setTag(1);
        					}
        					if(wlist.get(i).getNewPrice()>0){
        						wst[id].setTotalPrice(ArithUtil.add(wlist.get(i).getTotalPrice(), wlist.get(i).getNewPrice()));
        						wst[id].setTag(1);
        					}
        					if(wlist.get(i).getApv()>0){
        						wst[id].setAtpv(ArithUtil.add(wlist.get(i).getAtpv(), wlist.get(i).getApv()));
        						wst[id].setTag(1);
        					}
        					if(wlist.get(i).getAprice()>0){
        						wst[id].setAtprice(ArithUtil.add(wlist.get(i).getAtprice(), wlist.get(i).getAprice()));
        						wst[id].setTag(1);
        					}
        					if(wlist.get(i).getBpv()>0){
        						wst[id].setBtpv(ArithUtil.add(wlist.get(i).getBtpv(), wlist.get(i).getBpv()));
        						wst[id].setTag(1);
        					}
        					if(wlist.get(i).getBprice()>0){
        						wst[id].setBtprice(ArithUtil.add(wlist.get(i).getBtprice(), wlist.get(i).getBprice()));
        						wst[id].setTag(1);
        					}
        					if(wlist.get(i).getRpv()>0){
        						wst[id].setRtpv(ArithUtil.add(wlist.get(i).getRtpv(), wlist.get(i).getRpv()));
        						wst[id].setTag(1);
        					}
        					if(wlist.get(i).getRprice()>0){
        						wst[id].setRtprice(ArithUtil.add(wlist.get(i).getRtprice(), wlist.get(i).getRprice()));
        						wst[id].setTag(1);
        					}
        					if(wlist.get(i).getRpv1()>0){
        						wst[id].setRtpv1(ArithUtil.add(wlist.get(i).getRtpv1(), wlist.get(i).getRpv1()));
        						wst[id].setTag(1);
        					}
        					if(wlist.get(i).getRprice1()>0){
        						wst[id].setRtprice1(ArithUtil.add(wlist.get(i).getRtprice1(), wlist.get(i).getRprice1()));
        						wst[id].setTag(1);
        					}
        					if(wlist.get(i).getNewNum()>0){
        						wst[id].setTotalNum(wlist.get(i).getTotalNum()+ wlist.get(i).getNewNum());
        						wst[id].setTag(1);
        					}
        					if(ArithUtil.add(wlist.get(i).getJoinPriceTal(), wst[id].getJoinPriceTal())>0){
        						wst[id].setJoinPriceTal(wlist.get(i).getJoinPriceTal());
        						wst[id].setTag(1);
        					}
        					if(ArithUtil.add(wlist.get(i).getJoinPvTal(), wst[id].getJoinPvTal())>0){
        						wst[id].setJoinPvTal(wlist.get(i).getJoinPvTal());
        						wst[id].setTag(1);
        					}
        					if(ArithUtil.add(wlist.get(i).getJoinPvNew(), wst[id].getJoinPvNew())>0){
        						wst[id].setJoinPvNew(wlist.get(i).getJoinPvNew());
        						wst[id].setTag(1);
        					}
        					if(ArithUtil.add(wlist.get(i).getJoinPriceNew(), wst[id].getJoinPriceNew())>0){
        						wst[id].setJoinPriceNew(wlist.get(i).getJoinPriceNew());
        						wst[id].setTag(1);
        					}
        					if(wlist.get(i).getNewNumReal()>0){
        						wst[id].setTotalNumReal(wlist.get(i).getTotalNumReal()+ wlist.get(i).getNewNumReal());
        						wst[id].setTag(1);
        					}
        					if(wlist.get(i).getAcpv()-wst[id].getAcpv()>0||wlist.get(i).getAcpv()-wst[id].getAcpv()<0){
        						wst[id].setAcpv(wlist.get(i).getAcpv());
        						wst[id].setTag(1);
        					}
        					if(wlist.get(i).getBcpv()-wst[id].getBcpv()>0||wlist.get(i).getBcpv()-wst[id].getBcpv()<0){
        						wst[id].setBcpv(wlist.get(i).getBcpv());
        						wst[id].setTag(1);
        					}
        					if(!StringUtil.notNull(wlist.get(i).getLeftNodeId()).equals(wst[id].getLeftNodeId())){
        						wst[id].setLeftNodeId(wlist.get(i).getLeftNodeId());
        						wst[id].setTag(1);
        					}
        					if(!StringUtil.notNull(wlist.get(i).getRightNodeId()).equals(wst[id].getRightNodeId())){
        						wst[id].setRightNodeId(wlist.get(i).getRightNodeId());
        						wst[id].setTag(1);
        					}
        					if(!StringUtil.notNull(wlist.get(i).getLeftNodePv()).equals(wst[id].getLeftNodePv())){
        						wst[id].setLeftNodePv(wlist.get(i).getLeftNodePv());
        						wst[id].setTag(1);
        					}
        					if(!StringUtil.notNull(wlist.get(i).getRightNodePv()).equals(wst[id].getRightNodePv())){
        						wst[id].setRightNodePv(wlist.get(i).getRightNodePv());
        						wst[id].setTag(1);
        					}
        					if(!StringUtil.notNull(wlist.get(i).getLeftNodePrice()).equals(wst[id].getLeftNodePrice())){
        						wst[id].setLeftNodePrice(wlist.get(i).getLeftNodePrice());
        						wst[id].setTag(1);
        					}
        					if(!StringUtil.notNull(wlist.get(i).getRightNodePrice()).equals(wst[id].getRightNodePrice())){
        						wst[id].setRightNodePrice(wlist.get(i).getRightNodePrice());
        						wst[id].setTag(1);
        					}
        					if(!StringUtil.notNull(wlist.get(i).getNodeTag()).equals(wst[id].getNodeTag())){
        						wst[id].setNodeTag(wlist.get(i).getNodeTag());
        						wst[id].setTag(1);
        					}
        				}
        			}
        			
        			params.put("tableName",table_name_r);
        			List<DReward> wrdlist = wrdMapper.selectByList(params);
        			for(int i=0;i<wrdlist.size();i++){
        				int id = wrdlist.get(i).getUid();
        				if(wrdlist.get(i).getAmount_9()>0)
        					if(user[id]!=null){
        						user[id].setTotalIncome(ArithUtil.add(user[id].getTotalIncome(), wrdlist.get(i).getAmount_9()));
        						user[id].setTag(1);
        					}
    					
        			}
        			
        				for(int i=0;i<user.length;i++){
        					if(user[i]!=null&&user[i].getTag()==1){
        						User user1 = new User();
        						user1.setId(user[i].getId());
        						user1.setRankJoinOld(user[i].getRankJoinOld());
        						user1.setRankJoinTag(user[i].getRankJoinTag());
        						user1.setRank(user[i].getRank());
        						user1.setTotalIncome(user[i].getTotalIncome());
        						userMapper.updateUser(user1);
        					}
        				}
        				
        				
        				for(int i=0;i<wst.length;i++){
        					if(wst[i]!=null&&wst[i].getTag()==1){
        						wstMapper.update(wst[i]);
        					}
        				}
        			wrdMapper.updateStateOfDReward(table_name_r,dayTime, 1);
        			st.setState(3);
        			stMapper.update(st);
        			sqlSession.commit();
					msg = StringUtil.parseToString(dayTime, DateUtil.yyyyMMdd)+"结算确认成功，请继续点击奖金结算进行操作。";

        		}else{
    				msg="未查询到响应到会员信息。";
    				sqlSession.rollback();
    			}
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
    
    public String importExtraExcel(List<WReward> wlist,String adminName){
    	String msg = "";
    	int error=0;
    	try{
    		Param p9 = paramMapper.selectByName("奖金结算参数");
    		Timestamp date = new Timestamp(new Date().getTime());
    		List<Integer> ilist = new ArrayList<Integer>();
    		String table_name_r = "wreward_19";
    		User sel_user = new User();
    		Map<String,Object> params = new HashMap<>();
    		params.put("user",sel_user);
    		List<User> ulist = userMapper.selectMoneyByPage(params);
    		Integer maxId= userMapper.maxId(params);
    		int num = 1;
    		if(maxId!=null) num =maxId+1;
    		if(num>1){
    			User[] user = new User[num+1];
    			DReward[] wrd = new DReward[num+1];
    			for(int i=0;i<ulist.size();i++){
    				int id = ulist.get(i).getId();
    				if(user[id]==null) user[id] = new User();
    				user[id].setId(id);
    				user[id].setUserId(ulist.get(i).getUserId()); 
    				user[id].setUserName(ulist.get(i).getUserName());
    				user[id].setRefereeAll(ulist.get(i).getRefereeAll());
    				user[id].setRankManage(ulist.get(i).getRankManage());
    				user[id].setRank(ulist.get(i).getRank());
    				user[id].setRefereeNode(ulist.get(i).getRefereeNode());
    				user[id].setNode(ulist.get(i).getNode());
    				user[id].setRankJoinOld(ulist.get(i).getRankJoinOld());
    				user[id].setRankJoin(ulist.get(i).getRankJoin());
    				user[id].setRankJoinTag(ulist.get(i).getRankJoinTag());
    				user[id].setNodeABC(ulist.get(i).getNodeABC());
    				user[id].setEntryTime(ulist.get(i).getEntryTime());
    				user[id].setState(ulist.get(i).getState());
    				user[id].setRaiseNum(ulist.get(i).getRaiseNum());
    				user[id].setTotalIncome(ulist.get(i).getTotalIncome());
    				user[id].setTag(0);
    			}
    			
    			params.put("tableName",table_name_r);
    			List<DReward> wslist = wrdMapper.selectByList(params);
    			for(int i=0;i<wslist.size();i++){
    				int  id = wslist.get(i).getUid();
    				if(wrd[id]==null) wrd[id] = new DReward();
    				wrd[id].setId(wslist.get(i).getId());
    				wrd[id].setUid(id);
    				wrd[id].setUserId(wslist.get(i).getUserId());
    				wrd[id].setUserName(wslist.get(i).getUserName());
    				wrd[id].setAmount_1(wslist.get(i).getAmount_1());
    				wrd[id].setAmount_2(wslist.get(i).getAmount_2());
    				wrd[id].setAmount_3(wslist.get(i).getAmount_3());
    				wrd[id].setAmount_4(wslist.get(i).getAmount_4());
    				wrd[id].setAmount_5(wslist.get(i).getAmount_5());
    				wrd[id].setAmount_6(wslist.get(i).getAmount_6());
    				wrd[id].setAmount_7(wslist.get(i).getAmount_7());
    				wrd[id].setAmount_8(wslist.get(i).getAmount_8());
    				wrd[id].setAmount_9(wslist.get(i).getAmount_9());
    				wrd[id].setAmount_10(wslist.get(i).getAmount_10());
    				wrd[id].setAmount_11(wslist.get(i).getAmount_11());
    				wrd[id].setAmount_12(wslist.get(i).getAmount_12());
    				wrd[id].setPayTag(ulist.get(i).getPayTag());
    				wrd[id].setEntryTime(date);
    				wrd[id].setTag(0);
    			}
    		for(int i=0;i<wlist.size();i++){
    			User user1 = userMapper.selectByUserId(wlist.get(i).getUserId());
    			if(user1!=null){
    					double amount = wlist.get(i).getAmount();
    					int id = user1.getId();
    					if(amount>0){
    						wrd[id].setAmount_8(ArithUtil.add(wrd[id].getAmount_8(), amount));
    						wrd[id].setTag(1);
    					}
    			}
    		}
    		for(int i=0;i<wrd.length;i++){
    			if(wrd[i]!=null){
    				if(wrd[i].getTag()==1){
    					DReward update_wrd = new DReward();
    					update_wrd.setAmount_8(wrd[i].getAmount_8());
    					update_wrd.setAmount_9(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(wrd[i].getAmount_1(),wrd[i].getAmount_2()),wrd[i].getAmount_3()),wrd[i].getAmount_4()),wrd[i].getAmount_5()),wrd[i].getAmount_6()),wrd[i].getAmount_7()),wrd[i].getAmount_8()));
    					update_wrd.setAmount_10(ArithUtil.mul(update_wrd.getAmount_9(), p9.getAmount_1()));
    					update_wrd.setAmount_11(ArithUtil.mul(update_wrd.getAmount_9(), p9.getAmount_2()));
    					update_wrd.setAmount_12(ArithUtil.sub(ArithUtil.sub(update_wrd.getAmount_9(), update_wrd.getAmount_10()), update_wrd.getAmount_11()));
    					update_wrd.setId(wrd[i].getId());	
    					params.put("tableName",table_name_r);
    					params.put("wrd",update_wrd);
    					if(wrdMapper.update(params)==null)
    						error++;
    				}
    				}
    			}
    		}
    		
    	
    			if(error==0){
	    			msg= "奖金补差数据导入成功，请查看。";
	    			sqlSession.commit();
    		
    	
	    	}else{
				msg = "数据导入失败。";
				sqlSession.rollback();
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
   
    public List<DReward> awardSummaryAll_day(Timestamp startTime,Timestamp endTime){
    	List<DReward> result = new ArrayList<DReward>();
    	try{
    	
    		Map<String,Object> params = new HashMap<>();
    		Integer maxId= userMapper.maxId(params);
    		int weekTag1 = cs.getWeekTag(startTime);
    		int weekTag2 = cs.getWeekTag(endTime);
    		User sel_user = new User();
    		params.put("user",sel_user);
    		List<User> ulist = userMapper.selectByList(params);
    		int num = 1;
    		if(maxId!=null) num =maxId+1;
    		if(num>1){
    			
    			User[] user = new User[num+1];
    			DReward[] wrd = new DReward[num+1];
    			for(int i=0;i<ulist.size();i++){
    				int id = ulist.get(i).getId();
    				if(user[id]==null) user[id] = new User();
    				user[id].setId(id);
    				user[id].setUserId(ulist.get(i).getUserId()); 
    				user[id].setUserName(ulist.get(i).getUserName());
    				user[id].setState(ulist.get(i).getState());
    				user[id].setTag(0);
    				if(wrd[id]==null) wrd[id] = new DReward();
    				wrd[id].setUid(id);
    				wrd[id].setUserId(ulist.get(i).getUserId());
    				wrd[id].setUserName(ulist.get(i).getUserName());
    				wrd[id].setStartTime(startTime);
    				wrd[id].setEndTime(endTime);
    				wrd[id].setAmount_1((double) 0);
    				wrd[id].setAmount_2((double) 0);
    				wrd[id].setAmount_3((double) 0);
    				wrd[id].setAmount_4((double) 0);
    				wrd[id].setAmount_5((double) 0);
    				wrd[id].setAmount_6((double) 0);
    				wrd[id].setAmount_7((double) 0);
    				wrd[id].setAmount_8((double) 0);
    				wrd[id].setAmount_9((double) 0);
    				wrd[id].setAmount_10((double) 0);
    				wrd[id].setAmount_11((double) 0);
    				wrd[id].setAmount_12((double) 0);
    			}
    		for(int i=weekTag1;i<=weekTag2;i++){
	    		String tableName = "reward_day_"+i;
	    		params = new HashMap<>();
	    		DReward sel_1 = new DReward();
	    		sel_1.setStartTime(startTime);
	    		sel_1.setEndTime(endTime);
	    		params.put("tableName", tableName);
	    		params.put("wrd", sel_1);
	    		List<DReward> wlist=wrdMapper.selectByList(params);
    			for(int j=0;j<wlist.size();j++){
		    		if(wlist.get(j).getAmount_9()>0){
		    				int id = wlist.get(j).getUid();
		    				if(user[id]!=null){
		    					wrd[id].setAmount_1(ArithUtil.add(wrd[id].getAmount_1(), wlist.get(j).getAmount_1()));
		    					wrd[id].setAmount_2(ArithUtil.add(wrd[id].getAmount_2(), wlist.get(j).getAmount_2()));
		    					wrd[id].setAmount_3(ArithUtil.add(wrd[id].getAmount_3(), wlist.get(j).getAmount_3()));
		    					wrd[id].setAmount_4(ArithUtil.add(wrd[id].getAmount_4(), wlist.get(j).getAmount_4()));
		    					wrd[id].setAmount_5(ArithUtil.add(wrd[id].getAmount_5(), wlist.get(j).getAmount_5()));
		    					wrd[id].setAmount_6(ArithUtil.add(wrd[id].getAmount_6(), wlist.get(j).getAmount_6()));
		    					wrd[id].setAmount_7(ArithUtil.add(wrd[id].getAmount_7(), wlist.get(j).getAmount_7()));
		    					wrd[id].setAmount_8(ArithUtil.add(wrd[id].getAmount_8(), wlist.get(j).getAmount_8()));
		    					wrd[id].setAmount_9(ArithUtil.add(wrd[id].getAmount_9(), wlist.get(j).getAmount_9()));
		    					wrd[id].setAmount_10(ArithUtil.add(wrd[id].getAmount_10(), wlist.get(j).getAmount_10()));
		    					wrd[id].setAmount_11(ArithUtil.add(wrd[id].getAmount_11(), wlist.get(j).getAmount_11()));
		    					wrd[id].setAmount_12(ArithUtil.add(wrd[id].getAmount_12(), wlist.get(j).getAmount_12()));
		    				}
		    			}
    			}
    		}
    		for(int i=0;i<wrd.length;i++){
    			if(wrd[i]!=null)
    				result.add(wrd[i]);
    		}
    			sqlSession.commit();
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally {
			sqlSession.close();
    	}
    	return result;
	}
    
    public int checkReward(){
    	String msg="";
		List<DSettle> result = new ArrayList<DSettle>();
		int change = 0;
    	try{
    		Timestamp date = new Timestamp(new Date().getTime());
    		
    		Timestamp startTime = new Timestamp(StringUtil.parseToDate("2018-03-26 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
    		Timestamp startTime_1 = new Timestamp(StringUtil.parseToDate("2018-04-07 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());

    		Timestamp endTime = new Timestamp(StringUtil.parseToDate("2018-07-01 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
       		
    		Timestamp endTime_start = new Timestamp(StringUtil.parseToDate("2018-07-01 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());

    		SettleDay st = stMapper.selectByDayTimeForUpdate(startTime);
    		if(st!=null){
    			Param p1_1 = paramMapper.selectByName("推荐奖比例");
    			Param p2_1 = paramMapper.selectByName("拓展奖比例");
        		Param p2_2 = paramMapper.selectByName("拓展奖封顶额");
        		Param p3_1 = paramMapper.selectByName("培育奖比例");
        		Param p4_1 = paramMapper.selectByName("物流补助比例");
        		Param p5_1 = paramMapper.selectByName("消费奖比例");
        		Param p6_1 = paramMapper.selectByName("见点奖比例");
        		Param p7_1 = paramMapper.selectByName("领导奖标准");
        		Param p7_2 = paramMapper.selectByName("领导奖比例");
        		Param p9 = paramMapper.selectByName("奖金结算参数");
        		Param p10 = paramMapper.selectByName("结算控制参数");
        		Param p11 = paramMapper.selectByName("层碰奖比例");
        		if(p1_1!=null){
        		if(p2_1!=null){
        			if(p2_2!=null){
        				if(p3_1!=null){
        						if(p9!=null){
    			
        		User sel_user = new User();
        		sel_user.setEndTime(endTime);
        		Map<String,Object> params = new HashMap<>();
        		params.put("user",sel_user);
        		List<User> ulist = userMapper.selectMoneyByPage(params);
        		Integer maxId= userMapper.maxId(params);
        		int num = 1;
        		if(maxId!=null) num =maxId+1;
        		if(num>1){
        			User[] user = new User[num+1];
        			DReward[] wrd = new DReward[num+1];
        			DSettle[] wst = new DSettle[num+1];
        			for(int i=0;i<ulist.size();i++){
        				int id = ulist.get(i).getId();
        				if(user[id]==null) user[id] = new User();
        				user[id].setId(id);
        				user[id].setUserId(ulist.get(i).getUserId()); 
        				user[id].setUserName(ulist.get(i).getUserName());
        				user[id].setRefereeAll(ulist.get(i).getRefereeAll());
        				user[id].setRankManage(ulist.get(i).getRankManage());
        				user[id].setRefereeNode(ulist.get(i).getRefereeNode());
        				user[id].setNode(ulist.get(i).getNode());
        				user[id].setRankJoinOld(ulist.get(i).getRankJoinOld());
        				user[id].setRankJoin(ulist.get(i).getRankJoin());
        				user[id].setRankJoinTag(ulist.get(i).getRankJoinTag());
        				user[id].setNodeABC(ulist.get(i).getNodeABC());
        				user[id].setEntryTime(ulist.get(i).getEntryTime());
        				user[id].setState(ulist.get(i).getState());
        				user[id].setRaiseNum(ulist.get(i).getRaiseNum());
        				user[id].setTotalIncome(ulist.get(i).getTotalIncome());
        				user[id].setTotalPrice(ulist.get(i).getTotalIncome());
        				user[id].setCenterType(ulist.get(i).getCenterType());
        				user[id].setJoinPrice(ulist.get(i).getJoinPrice());
        				user[id].setJoinPv(ulist.get(i).getJoinPv());
        				user[id].setIsEmpty(ulist.get(i).getIsEmpty());
        				user[id].setTag(0);
        				if(wrd[id]==null) wrd[id] = new DReward();
        				wrd[id].setUid(id);
        				wrd[id].setUserId(ulist.get(i).getUserId());
        				wrd[id].setUserName(ulist.get(i).getUserName());
        				wrd[id].setAmount_1((double) 0);
        				wrd[id].setAmount_2((double) 0);
        				wrd[id].setAmount_3((double) 0);
        				wrd[id].setAmount_4((double) 0);
        				wrd[id].setAmount_5((double) 0);
        				wrd[id].setAmount_6((double) 0);
        				wrd[id].setAmount_7((double) 0);
        				wrd[id].setAmount_8((double) 0);
        				wrd[id].setAmount_9((double) 0);
        				wrd[id].setAmount_10((double) 0);
        				wrd[id].setAmount_11((double) 0);
        				wrd[id].setAmount_12((double) 0);
        				wrd[id].setWeekTag(st.getWeekTag());
        				wrd[id].setStartTime(startTime);
        				wrd[id].setEndTime(endTime);
        				wrd[id].setPayTag(ulist.get(i).getPayTag());
        				wrd[id].setEntryTime(date);
        				wrd[id].setDayTime(startTime);
        				if(wst[id]==null) wst[id] = new DSettle();
        				wst[id].setuId(id);
        				wst[id].setUserId(ulist.get(i).getUserId());
        				wst[id].setUserName(ulist.get(i).getUserName());
        				wst[id].setRank(ulist.get(i).getRank());
        				wst[id].setMaxRankNew(ulist.get(i).getRank());
        				wst[id].setRankJoin(ulist.get(i).getRankJoinOld());
        				wst[id].setRankJoinOld(ulist.get(i).getRankJoinOld());
        				wst[id].setNewPerformance((double) 0);
        				wst[id].setNewPrice((double) 0);
        				wst[id].setTotalPerformance((double) 0);
        				wst[id].setTotalPrice((double) 0);
        				wst[id].setTotalNum(0);
    					wst[id].setNewNum(0);
    					wst[id].setApv((double) 0);
    					wst[id].setAprice((double) 0);
    					wst[id].setAtprice((double) 0);
    					wst[id].setBpv((double) 0);
    					wst[id].setBprice((double) 0);
    					wst[id].setBtprice((double) 0);
    					wst[id].setAcpv((double) 0);
    					wst[id].setBcpv((double) 0);
    					wst[id].setRpv((double) 0);
    					wst[id].setRprice((double) 0);
    					wst[id].setRpv1((double) 0);
    					wst[id].setRprice1((double) 0);
    					wst[id].setAtpv((double) 0);
    					wst[id].setBtpv((double) 0);
    					wst[id].setRtpv((double) 0);
    					wst[id].setRtprice((double) 0);
    					wst[id].setRtpv1((double) 0);
    					wst[id].setRtprice1((double) 0);
    					wst[id].setPayTag(ulist.get(i).getPayTag());
    					wst[id].setNewNumReal(0);
    					wst[id].setTotalNumReal(0);
        				wst[id].setWeekTag(st.getWeekTag());
        				wst[id].setJoinPriceTal((double) 0);
        				wst[id].setJoinPvTal((double) 0);
        				wst[id].setJoinPriceNew((double) 0);
        				wst[id].setJoinPvNew((double) 0);
        				wst[id].setLeftNodeId("");
        				wst[id].setRightNodeId("");
        				wst[id].setLeftNodePv("");
        				wst[id].setRightNodePv("");
        				wst[id].setLeftNodePrice("");
        				wst[id].setRightNodePrice("");
        				wst[id].setNodeTag("");
        				wst[id].setStartTime(st.getStartTime());
        				wst[id].setEndTime(st.getEndTime());
        				wst[id].setEntryTime(date);
        				wst[id].setDayTime(startTime);
        				wst[id].setCtag(0);
        			}
        			
        			
        			DSettle sel_wst = new DSettle();
        			sel_wst.setDayTime(startTime);
        			params.put("tableName", "settle_day_1");
        			params.put("wst", sel_wst);
            		List<DSettle> wlist = wstMapper.selectByList(params);
            		for(int i=0;i<wlist.size();i++){
            			int id = wlist.get(i).getuId();
            			if(wst[id]!=null){
            				wst[id].setTotalPerformance(wlist.get(i).getTotalPerformance());
            				wst[id].setTotalPrice(wlist.get(i).getTotalPrice());
            				wst[id].setAtpv(wlist.get(i).getAtpv());
            				wst[id].setAtprice(wlist.get(i).getAtprice());
            				wst[id].setBtprice(wlist.get(i).getBtprice());
        					wst[id].setBtpv(wlist.get(i).getBtpv());
        					wst[id].setRtpv(wlist.get(i).getRtpv());
        					wst[id].setRtprice(wlist.get(i).getRtprice());
        					wst[id].setRtpv1(wlist.get(i).getRtpv1());
        					wst[id].setRtprice1(wlist.get(i).getRtprice1());
        					wst[id].setAcpv(wlist.get(i).getAcpv());
        					wst[id].setBcpv(wlist.get(i).getBcpv());
        					wst[id].setTotalNum(wlist.get(i).getTotalNum());
        					wst[id].setTotalNumReal(wlist.get(i).getTotalNumReal());
        					wst[id].setJoinPriceTal(wlist.get(i).getJoinPriceTal());
            				wst[id].setJoinPvTal(wlist.get(i).getJoinPvTal());
            				wst[id].setJoinPriceNew(wlist.get(i).getJoinPriceNew());
            				wst[id].setJoinPvNew(wlist.get(i).getJoinPvNew());
            				wst[id].setLeftNodeId(StringUtil.notNull(wlist.get(i).getLeftNodeId()));
            				wst[id].setRightNodeId(StringUtil.notNull(wlist.get(i).getRightNodeId()));
            				wst[id].setLeftNodePv(StringUtil.notNull(wlist.get(i).getLeftNodePv()));
            				wst[id].setRightNodePv(StringUtil.notNull(wlist.get(i).getRightNodePv()));
            				wst[id].setLeftNodePrice(StringUtil.notNull(wlist.get(i).getLeftNodePrice()));
            				wst[id].setRightNodePrice(StringUtil.notNull(wlist.get(i).getRightNodePrice()));
            				wst[id].setNodeTag(StringUtil.notNull(wlist.get(i).getNodeTag()));
            			}
            		}
        			DSettle[] wst1 = new DSettle[num+1];
            		sel_wst.setDayTime(endTime_start);
        			params.put("tableName", "settle_day_14");
        			params.put("wst", sel_wst);
            		List<DSettle> wlist1 = wstMapper.selectByList(params);
            		for(int i=0;i<wlist1.size();i++){
            			int id = wlist1.get(i).getuId();
            			if(wst1[id]==null) wst1[id] = new DSettle();
            				wst1[id].setUserId(wlist1.get(i).getUserId());
            				wst1[id].setTotalPerformance(wlist1.get(i).getTotalPerformance());
            				wst1[id].setTotalPrice(wlist1.get(i).getTotalPrice());
            				wst1[id].setAtpv(wlist1.get(i).getAtpv());
            				wst1[id].setAtprice(wlist1.get(i).getAtprice());
            				wst1[id].setBtprice(wlist1.get(i).getBtprice());
        					wst1[id].setBtpv(wlist1.get(i).getBtpv());
        					wst1[id].setRtpv(wlist1.get(i).getRtpv());
        					wst1[id].setRtprice(wlist1.get(i).getRtprice());
        					wst1[id].setRtpv1(wlist1.get(i).getRtpv1());
        					wst1[id].setRtprice1(wlist1.get(i).getRtprice1());
        					wst1[id].setAcpv(wlist1.get(i).getAcpv());
        					wst1[id].setBcpv(wlist1.get(i).getBcpv());
        					wst1[id].setTotalNum(wlist1.get(i).getTotalNum());
        					wst1[id].setTotalNumReal(wlist1.get(i).getTotalNumReal());
        					wst1[id].setJoinPriceTal(wlist1.get(i).getJoinPriceTal());
            				wst1[id].setJoinPvTal(wlist1.get(i).getJoinPvTal());
            				wst1[id].setJoinPriceNew(wlist1.get(i).getJoinPriceNew());
            				wst1[id].setJoinPvNew(wlist1.get(i).getJoinPvNew());
            				wst1[id].setLeftNodeId(StringUtil.notNull(wlist1.get(i).getLeftNodeId()));
            				wst1[id].setRightNodeId(StringUtil.notNull(wlist1.get(i).getRightNodeId()));
            				wst1[id].setLeftNodePv(StringUtil.notNull(wlist1.get(i).getLeftNodePv()));
            				wst1[id].setRightNodePv(StringUtil.notNull(wlist1.get(i).getRightNodePv()));
            				wst1[id].setLeftNodePrice(StringUtil.notNull(wlist1.get(i).getLeftNodePrice()));
            				wst1[id].setRightNodePrice(StringUtil.notNull(wlist1.get(i).getRightNodePrice()));
            				wst1[id].setNodeTag(StringUtil.notNull(wlist1.get(i).getNodeTag()));
            			
            		}
            		
            		int newNum = 0;
    				double totalPv = 0;
    				double totalPrice = 0;
    				double totalPv1 = 0;
    				double totalPrice1 = 0;
    				double totalPv2 = 0;
    				double totalPrice2 = 0;
    				int time_int = (int) ((endTime.getTime()-startTime_1.getTime())/(24*60*60*1000));
    				System.out.println("time_int:"+time_int);
    				for(int tt=0;tt<time_int;tt++){
    				startTime_1 = cs.getNextDay(startTime_1);
    				Timestamp e_time = new Timestamp(startTime_1.getTime()+24*60*60*1000);
    				System.out.println("s_time:"+StringUtil.parseToString(startTime_1, DateUtil.yyyyMMddHHmmss));
            		JoinInfo sel_jf = new JoinInfo();
            		sel_jf.setStartTime(startTime_1);
            		sel_jf.setEndTime(e_time);
            		sel_jf.setState(2);
            		params.put("joinInfo", sel_jf);
            		List<JoinInfo> jlist = jfMapper.selectAllByList(params);
    				for(int i=0;i<jlist.size();i++){
    					int uid = jlist.get(i).getUid();
    					String userId = jlist.get(i).getUserId();
    					double price =jlist.get(i).getPrice();
    					double pv = jlist.get(i).getPv();
    					int newRankJoin = jlist.get(i).getNewRankJoin();
    					int rid =  jlist.get(i).getRid();
    					int oldRankJoin = jlist.get(i).getOldRankJoin();
    					totalPv = ArithUtil.add(totalPv,pv);
    					totalPrice = ArithUtil.add(totalPrice,price);
    					wst[uid].setJoinPvTal(ArithUtil.add(wst[uid].getJoinPvTal(),pv));
    					wst[uid].setJoinPriceTal(ArithUtil.add(wst[uid].getJoinPriceTal(),price));
    					if(oldRankJoin==0) {
    						wst[uid].setJoinPvNew(ArithUtil.add(wst[uid].getJoinPvNew(),pv));
        					wst[uid].setJoinPriceNew(ArithUtil.add(wst[uid].getJoinPriceNew(),price));
    						if(!userId.equals(Constants.TOP_NODE)){
    							newNum++;
    							wst[rid].setNewNum(wst[rid].getNewNum()+1);
    							wst[rid].setTag(1);
	    						if(price>0||pv>0){
	    							wst[rid].setNewNumReal(wst[rid].getNewNumReal()+1);
	    							wst[rid].setTag(1);
	    						}
    						}
    					}else{
    						if(user[uid].getEntryTime().getTime()-endTime.getTime()<=0&&user[uid].getEntryTime().getTime()-startTime.getTime()>=0){
    							wst[uid].setJoinPvNew(ArithUtil.add(wst[uid].getJoinPvNew(),pv));
            					wst[uid].setJoinPriceNew(ArithUtil.add(wst[uid].getJoinPvNew(),price));
    						}
    					}
    					if(wst[uid].getRankJoin()<newRankJoin){
    						wst[uid].setRankJoin(newRankJoin);
    						if(pv==0&&price==0) wst[uid].setRankJoinTag(1);
    						wst[uid].setTag(1);
    					}
    					
    					if(price>0||pv>0){
    					if(oldRankJoin>0){
    						if(!StringUtil.notNull(user[uid].getNode()).equals("")){
    						 String[] str = user[uid].getNode().split(",");
    						 String[] strarray = user[uid].getNodeABC().split(",");
    						 String[] str1 = user[uid].getRefereeNode().split(",");
    						wst[uid].setRpv(ArithUtil.add(wst[uid].getRpv(),pv));
    						wst[uid].setRprice(ArithUtil.add(wst[uid].getRpv(),price));
    						wst[uid].setTag(1);
    						 for(int j=str.length-1;j>=0;j--){
    							if(!str[j].equals("")){
    								int sid = Integer.valueOf(str[j]);
    								 if(strarray[j].equals("A")){
    											wst[sid].setApv(ArithUtil.add(wst[sid].getApv(),pv));
    	    									wst[sid].setAprice(ArithUtil.add(wst[sid].getAprice(),price));
    	    									 wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
    	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
    	        								 wst[sid].setTag(1);
    								 } else if(strarray[j].equals("B")) {
    											wst[sid].setBpv(ArithUtil.add(wst[sid].getBpv(),pv));
    	    									wst[sid].setBprice(ArithUtil.add(wst[sid].getBprice(),price));
    	    									wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
	   	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
	   	        								 wst[sid].setTag(1);
    								 }
    							 }
    						 }
    						 
    						 for(int j=0;j<str1.length;j++){
    								if(!str1[j].equals("")){
    									int sid = Integer.valueOf(str1[j]);
    									 wst[sid].setRpv(ArithUtil.add(wst[sid].getRpv(),pv));
    									 wst[sid].setRprice(ArithUtil.add(wst[sid].getRprice(),price));
    									 wst[sid].setTag(1);
    								 }
    						 }
    						 }
    					}
    					}
    				}
    			
    					for(int i=0;i<jlist.size();i++){
        					int uid = jlist.get(i).getUid();
        					int oldRankJoin = jlist.get(i).getOldRankJoin();
    					if(oldRankJoin==0){
    						double price =wst[uid].getJoinPriceNew();
        					double pv = wst[uid].getJoinPvNew();
    						if(!StringUtil.notNull(user[uid].getNode()).equals("")){
    						 String[] str = user[uid].getNode().split(",");
    						 String[] strarray = user[uid].getNodeABC().split(",");
    						 String[] str1 = user[uid].getRefereeNode().split(",");
    						int t=0;
    						 for(int j=str.length-1;j>=0;j--){
    							if(!str[j].equals("")){
    								t++;
    								int sid = Integer.valueOf(str[j]);
									 String[] node_tag = wst[sid].getNodeTag().split(",");
									 int node_num = 0;
									 if(!wst[sid].getNodeTag().equals("")) node_num = node_tag.length;
    								 if(strarray[j].equals("A")){
    									 String[] node_str_id = wst[sid].getLeftNodeId().split(",");
    									 String[] node_str_pv  =wst[sid].getLeftNodePv().split(",");
    									 String[] node_str_price  =wst[sid].getLeftNodePrice().split(",");
    									 int node_num_l = 0;
    									 if(!wst[sid].getLeftNodeId().equals("")) node_num_l = node_str_id.length;
    									 if(t>node_num_l){
    										 if(t==1){
    											 wst[sid].setLeftNodeId(String.valueOf(uid));
    											 if(t>node_num){ 
    												 wst[sid].setNodeTag("0");
    											 }else if(node_tag[t-1].equals("1")){
    												 	wst[sid].setApv(ArithUtil.add(wst[sid].getApv(),pv));
    	    	    									wst[sid].setAprice(ArithUtil.add(wst[sid].getAprice(),price));
    	    	    									 wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
    	    	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
    	    	        								 wst[sid].setTag(1);
    											 }
	    										 wst[sid].setLeftNodePv(String.valueOf(pv));
		    									wst[sid].setLeftNodePrice(String.valueOf(price));
		    									wst[sid].setCtag(1);
    										 }else{
    											 wst[sid].setLeftNodeId(wst[sid].getLeftNodeId()+","+String.valueOf(uid));
    											 if(t>node_num){
    												 wst[sid].setNodeTag(wst[sid].getNodeTag()+","+"0");
    											 }else if(node_tag[t-1].equals("1")){
 												 	wst[sid].setApv(ArithUtil.add(wst[sid].getApv(),pv));
	    	    									wst[sid].setAprice(ArithUtil.add(wst[sid].getAprice(),price));
	    	    									 wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
	    	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
	    	        								 wst[sid].setTag(1);
    											 }
    											 wst[sid].setLeftNodePv(wst[sid].getLeftNodePv()+","+String.valueOf(pv));
    											 wst[sid].setLeftNodePrice(wst[sid].getLeftNodePrice()+","+String.valueOf(price));
    											 wst[sid].setCtag(1);
    										 }
    									 }else{
    										if(node_tag[t-1].equals("0")){
    											// System.out.println("userId:"+wst[sid].getUserId()+";getNodeTag:"+wst[sid].getNodeTag()+";getLeftNodeId:"+wst[sid].getLeftNodeId()+";getLeftNodePrice:"+wst[sid].getLeftNodePrice());
	    										int node_id = Integer.valueOf(node_str_id[t-1]);
	    										double node_pv = Double.valueOf(node_str_pv[t-1]);
	    										double node_price = Double.valueOf(node_str_price[t-1]);
	    										node_str_pv[t-1] = String.valueOf(ArithUtil.add(node_pv, pv));
	    										node_str_price[t-1] = String.valueOf(ArithUtil.add(node_price, price));
	    										if(ArithUtil.sub(wst[node_id].getJoinPvNew(), wst[uid].getJoinPvNew())>0){
	    											 node_str_id[t-1]=String.valueOf(uid);
	    											 //node_str_pv[t-1]=String.valueOf(wst[uid].getJoinPvNew());
	     											//node_str_price[t-1]=String.valueOf(wst[uid].getJoinPriceNew());
	    										 }
	    										for(int s=0;s<node_str_id.length;s++){
	    											if(s==0){
	    												 wst[sid].setLeftNodeId(node_str_id[s]);
	        											 wst[sid].setLeftNodePv(node_str_pv[s]);
	        											 wst[sid].setLeftNodePrice(node_str_price[s]);
	    											}else{
	    												 wst[sid].setLeftNodeId(wst[sid].getLeftNodeId()+","+node_str_id[s]);
	        											 wst[sid].setLeftNodePv(wst[sid].getLeftNodePv()+","+node_str_pv[s]);
	        											 wst[sid].setLeftNodePrice(wst[sid].getLeftNodePrice()+","+node_str_price[s]);
	    											}
	    										}
    										}else{
    											wst[sid].setApv(ArithUtil.add(wst[sid].getApv(),pv));
    	    									wst[sid].setAprice(ArithUtil.add(wst[sid].getAprice(),price));
    	    									 wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
    	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
    	        								 wst[sid].setTag(1);
    										}
    									 }
    								 } else if(strarray[j].equals("B")) {
    									 String[] node_str_id = wst[sid].getRightNodeId().split(",");
    									 String[] node_str_pv  =wst[sid].getRightNodePv().split(",");
    									 String[] node_str_price  =wst[sid].getRightNodePrice().split(",");
    									 int node_num_l = 0;
    									 if(!wst[sid].getRightNodeId().equals("")) node_num_l = node_str_id.length;
    									 if(t>node_num_l){
    										 if(t==1){
    											 wst[sid].setRightNodeId(String.valueOf(uid));
    											 if(t>node_num){
    												 wst[sid].setNodeTag("0");
    											 }else if(node_tag[t-1].equals("1")){
    												 wst[sid].setBpv(ArithUtil.add(wst[sid].getBpv(),pv));
 	    	    									wst[sid].setBprice(ArithUtil.add(wst[sid].getBprice(),price));
 	    	    									wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
 		   	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
 		   	        								 wst[sid].setTag(1);
    											 }
    											 wst[sid].setRightNodePv(String.valueOf(pv));
    											 wst[sid].setRightNodePrice(String.valueOf(price));
    											 wst[sid].setCtag(1);
    										 }else{
    											 wst[sid].setRightNodeId(wst[sid].getRightNodeId()+","+String.valueOf(uid));
    											 if(t>node_num){
    												 wst[sid].setNodeTag(wst[sid].getNodeTag()+","+"0");
    											 }else if(node_tag[t-1].equals("1")){
    												 wst[sid].setBpv(ArithUtil.add(wst[sid].getBpv(),pv));
 	    	    									wst[sid].setBprice(ArithUtil.add(wst[sid].getBprice(),price));
 	    	    									wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
 		   	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
 		   	        								 wst[sid].setTag(1);
    											 }
    											 wst[sid].setRightNodePv(wst[sid].getRightNodePv()+","+String.valueOf(pv));
    											 wst[sid].setRightNodePrice(wst[sid].getRightNodePrice()+","+String.valueOf(price));
    											 wst[sid].setCtag(1);
    										 }
    									 }else{
    										if(node_tag[t-1].equals("0")){
	    										int node_id = Integer.valueOf(node_str_id[t-1]);
	    										double node_pv = Double.valueOf(node_str_pv[t-1]);
	    										double node_price = Double.valueOf(node_str_price[t-1]);
	    										node_str_pv[t-1] = String.valueOf(ArithUtil.add(node_pv, pv));
	    										node_str_price[t-1] = String.valueOf(ArithUtil.add(node_price, price));
	    										if(ArithUtil.sub(wst[node_id].getJoinPvNew(), wst[uid].getJoinPvNew())>0){
	    											 node_str_id[t-1]=String.valueOf(uid);
	    											// node_str_pv[t-1]=String.valueOf(wst[uid].getJoinPvNew());
	     											//node_str_price[t-1]=String.valueOf(wst[uid].getJoinPriceNew());
	    										 }
	    										for(int s=0;s<node_str_id.length;s++){
	    											if(s==0){
	    												 wst[sid].setRightNodeId(node_str_id[s]);
	        											 wst[sid].setRightNodePv(node_str_pv[s]);
	        											 wst[sid].setRightNodePrice(node_str_price[s]);
	    											}else{
	    												 wst[sid].setRightNodeId(wst[sid].getRightNodeId()+","+node_str_id[s]);
	        											 wst[sid].setRightNodePv(wst[sid].getRightNodePv()+","+node_str_pv[s]);
	        											 wst[sid].setRightNodePrice(wst[sid].getRightNodePrice()+","+node_str_price[s]);
	    											}
	    										}
    										}else{
    											wst[sid].setBpv(ArithUtil.add(wst[sid].getBpv(),pv));
    	    									wst[sid].setBprice(ArithUtil.add(wst[sid].getBprice(),price));
    	    									wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
	   	        								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
	   	        								 wst[sid].setTag(1);
    										}
    									 }
    								 }
    							
    							 }
    						 }
    						 for(int j=0;j<str1.length;j++){
    								if(!str1[j].equals("")){
    									int sid = Integer.valueOf(str1[j]);
    									 wst[sid].setRpv(ArithUtil.add(wst[sid].getRpv(),pv));
    									 wst[sid].setRprice(ArithUtil.add(wst[sid].getRprice(),price));
    									 wst[sid].setTag(1);
    								 }
    						 }
    						 }
    					}
    				}
    					
    					//计算层碰奖
                		for(int i=0;i<wst.length;i++){
                			if(wst[i]!=null){
                				if(wst[i].getCtag()==1){
                					String[] left_node_id = wst[i].getLeftNodeId().split(",");
                					String[] right_node_id = wst[i].getRightNodeId().split(",");
                					String[] left_node_pv = wst[i].getLeftNodePv().split(",");
                					String[] right_node_pv = wst[i].getRightNodePv().split(",");
                					String[] left_node_price = wst[i].getLeftNodePrice().split(",");
                					String[] right_node_price = wst[i].getRightNodePrice().split(",");
                					String[] node_tag = wst[i].getNodeTag().split(",");
                					int realNum = wst[i].getTotalNumReal()+wst[i].getNewNumReal();
                					int t_tag=0;
                					int left_num = 0;
        							int right_num=0;
        							if(!wst[i].getLeftNodeId().equals("")) left_num = left_node_id.length;
        							if(!wst[i].getRightNodeId().equals("")) right_num = right_node_id.length;
                					for(int j=0;j<node_tag.length;j++){
                						if(node_tag[j].equals("0")){
                							if(left_num>=(j+1)&&right_num>=(j+1)){
                								double cpv =Math.min(wst[i].getJoinPvTal(), Math.min(Double.valueOf(left_node_pv[j]), Double.valueOf(right_node_pv[j])));
                								double cprice =Math.min(wst[i].getJoinPriceTal(), Math.min(Double.valueOf(left_node_price[j]), Double.valueOf(right_node_price[j])));
                								double pro = 0;
    	            							if(j==0) pro=p11.getAmount_1();
    	            							else pro = p11.getAmount_2();
    	            							double award = ArithUtil.mul(cpv, pro);
    	            							if(award>0&&realNum>0)
    	            									wrd[i].setAmount_3(ArithUtil.add(wrd[i].getAmount_3(),award));
    	            								
    	            							node_tag[j]="1";
    	            							t_tag=1;
    	            							double apv = ArithUtil.sub(Double.valueOf(left_node_pv[j]),cpv);
    	            							double bpv = ArithUtil.sub(Double.valueOf(right_node_pv[j]),cpv);
    	            							double aprice = ArithUtil.sub(Double.valueOf(left_node_price[j]),cprice);
    	            							double bprice= ArithUtil.sub(Double.valueOf(right_node_price[j]),cprice);
    	            							wst[i].setApv(ArithUtil.add(wst[i].getApv(), apv));
    	            							wst[i].setBpv(ArithUtil.add(wst[i].getBpv(), bpv));
    	            							wst[i].setAprice(ArithUtil.add(wst[i].getAprice(), aprice));
    	            							wst[i].setBprice(ArithUtil.add(wst[i].getBprice(), bprice));
    	            							wst[i].setNewPerformance(ArithUtil.add(wst[i].getNewPerformance(),ArithUtil.add(apv, bpv)));
      	        								wst[i].setNewPrice(ArithUtil.add(wst[i].getNewPrice(),ArithUtil.add(aprice, bprice)));
      	        								wst[i].setTag(1);
                							}
                						}
                					}
                					if(t_tag==1){
                						for(int j=0;j<node_tag.length;j++){
                							if(j==0){
    											 wst[i].setNodeTag(node_tag[j]);
    										}else{
    											 wst[i].setNodeTag(wst[i].getNodeTag()+","+node_tag[j]);
    										}
                						}
            						}
            							

                				}
                			}
                		}
    				}
    				//统计业绩
            		 // 推荐奖计算
    				
    					for(int i=0;i<wst1.length;i++){
    						if(wst1[i]!=null){
    							if(wst[i]!=null){
    								DSettle dst = new DSettle();
    								dst.setUserId(wst1[i].getUserId());
    								dst.setTag(0);
    								if(!wst1[i].getLeftNodePv().equals(wst[i].getLeftNodePv())){
    									dst.setLeftNodePv(wst[i].getLeftNodePv());
    									dst.setLeftNodePrice(wst[i].getLeftNodePrice());
    									dst.setTag(1);
    								}
    								if(!wst1[i].getRightNodePv().equals(wst[i].getRightNodePv())){
    									dst.setRightNodePv(wst[i].getRightNodePv());
    									dst.setRightNodePrice(wst[i].getRightNodePrice());
    									dst.setTag(1);
    								}
    								if(dst.getTag()==1){
    									change = change+wstMapper.updateUserId(dst);
    								}
    							}
    						}
    					}
            			
            		//计算层碰奖
            		
            		//培育补贴
            		
            			
            		//物流补助
            	
            		
            		//消费奖
            		
            		
            		
            		 // 见点奖计算
            		
            		
            		//计算等级和领导奖
            
	           
            		//保存相关的表
            		//保存结算表
            		

            		
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
        				msg="拓展奖比例参数获取失败。";
        				sqlSession.rollback();
            		}
        		}else{
        			msg="创业奖参数获取失败。";
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
    	return change;
	}
    
    public String addReward(){
    	String msg="";
		List<DSettle> result = new ArrayList<DSettle>();
		int change = 0;
    	try{
    		double[][] usr_str = {{1203.2,0},{0,7984},{1280,0},{4800,0},{1400,0},{4000,0},{7984,0}
    		,{0,8011.2},{0,1211.2},{8000,8000},{7946.4,63152},{7946.4,0},{30980,8000},{8016,8011.2}
    		,{0,1200},{0,15100},{15456.8,0},{7756.8,0},{1200,0},{16000,0},{0,8000},{8000,0}};
    		String[] usr_str_1 = {"CG00604375","TT00092788","HD00103390","TT00610110","HD00609065","TT00613302"
    				,"TT00613225","XF00614617","CL00613345","CG00613323","CG00612220","CG00613282","HY00613806"
    				,"HY00612896","TT00612701","HY00614345","HY00612120","HY00612396","HD00613917","HY00614283"
    				,"HY00613201","HY00614316"};
    		
    		Timestamp date = new Timestamp(new Date().getTime());
    		Timestamp startTime = new Timestamp(StringUtil.parseToDate("2018-07-03 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
    		Timestamp endTime = new Timestamp(StringUtil.parseToDate("2018-07-03 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
       	
    		SettleDay st = stMapper.selectByDayTimeForUpdate(startTime);
    		if(st!=null){
    			Param p1_1 = paramMapper.selectByName("推荐奖比例");
    			Param p2_1 = paramMapper.selectByName("拓展奖比例");
        		Param p2_2 = paramMapper.selectByName("拓展奖封顶额");
        		Param p3_1 = paramMapper.selectByName("培育奖比例");
        		Param p4_1 = paramMapper.selectByName("物流补助比例");
        		Param p5_1 = paramMapper.selectByName("消费奖比例");
        		Param p6_1 = paramMapper.selectByName("见点奖比例");
        		Param p7_1 = paramMapper.selectByName("领导奖标准");
        		Param p7_2 = paramMapper.selectByName("领导奖比例");
        		Param p9 = paramMapper.selectByName("奖金结算参数");
        		Param p10 = paramMapper.selectByName("结算控制参数");
        		Param p11 = paramMapper.selectByName("层碰奖比例");
        		if(p1_1!=null){
        		if(p2_1!=null){
        			if(p2_2!=null){
        				if(p3_1!=null){
        						if(p9!=null){
    			
        		User sel_user = new User();
        		sel_user.setEndTime(endTime);
        		Map<String,Object> params = new HashMap<>();
        		params.put("user",sel_user);
        		List<User> ulist = userMapper.selectMoneyByPage(params);
        		Integer maxId= userMapper.maxId(params);
        		int num = 1;
        		if(maxId!=null) num =maxId+1;
        		if(num>1){
        			User[] user = new User[num+1];
        			DReward[] wrd = new DReward[num+1];
        			DSettle[] wst = new DSettle[num+1];
        			for(int i=0;i<ulist.size();i++){
        				int id = ulist.get(i).getId();
        				if(user[id]==null) user[id] = new User();
        				user[id].setId(id);
        				user[id].setUserId(ulist.get(i).getUserId()); 
        				user[id].setUserName(ulist.get(i).getUserName());
        				user[id].setRefereeAll(ulist.get(i).getRefereeAll());
        				user[id].setRankManage(ulist.get(i).getRankManage());
        				user[id].setRefereeNode(ulist.get(i).getRefereeNode());
        				user[id].setNode(ulist.get(i).getNode());
        				user[id].setRankJoinOld(ulist.get(i).getRankJoinOld());
        				user[id].setRankJoin(ulist.get(i).getRankJoin());
        				user[id].setRankJoinTag(ulist.get(i).getRankJoinTag());
        				user[id].setNodeABC(ulist.get(i).getNodeABC());
        				user[id].setEntryTime(ulist.get(i).getEntryTime());
        				user[id].setState(ulist.get(i).getState());
        				user[id].setRaiseNum(ulist.get(i).getRaiseNum());
        				user[id].setTotalIncome(ulist.get(i).getTotalIncome());
        				user[id].setTotalPrice(ulist.get(i).getTotalIncome());
        				user[id].setCenterType(ulist.get(i).getCenterType());
        				user[id].setJoinPrice(ulist.get(i).getJoinPrice());
        				user[id].setJoinPv(ulist.get(i).getJoinPv());
        				user[id].setIsEmpty(ulist.get(i).getIsEmpty());
        				user[id].setTag(0);
        				if(wrd[id]==null) wrd[id] = new DReward();
        				wrd[id].setUid(id);
        				wrd[id].setUserId(ulist.get(i).getUserId());
        				wrd[id].setUserName(ulist.get(i).getUserName());
        				wrd[id].setAmount_1((double) 0);
        				wrd[id].setAmount_2((double) 0);
        				wrd[id].setAmount_3((double) 0);
        				wrd[id].setAmount_4((double) 0);
        				wrd[id].setAmount_5((double) 0);
        				wrd[id].setAmount_6((double) 0);
        				wrd[id].setAmount_7((double) 0);
        				wrd[id].setAmount_8((double) 0);
        				wrd[id].setAmount_9((double) 0);
        				wrd[id].setAmount_10((double) 0);
        				wrd[id].setAmount_11((double) 0);
        				wrd[id].setAmount_12((double) 0);
        				wrd[id].setWeekTag(st.getWeekTag());
        				wrd[id].setStartTime(startTime);
        				wrd[id].setEndTime(endTime);
        				wrd[id].setPayTag(ulist.get(i).getPayTag());
        				wrd[id].setEntryTime(date);
        				wrd[id].setDayTime(startTime);
        				if(wst[id]==null) wst[id] = new DSettle();
        				wst[id].setuId(id);
        				wst[id].setUserId(ulist.get(i).getUserId());
        				wst[id].setUserName(ulist.get(i).getUserName());
        				wst[id].setRank(ulist.get(i).getRank());
        				wst[id].setMaxRankNew(ulist.get(i).getRank());
        				wst[id].setRankJoin(ulist.get(i).getRankJoinOld());
        				wst[id].setRankJoinOld(ulist.get(i).getRankJoinOld());
        				wst[id].setNewPerformance((double) 0);
        				wst[id].setNewPrice((double) 0);
        				wst[id].setTotalPerformance((double) 0);
        				wst[id].setTotalPrice((double) 0);
        				wst[id].setTotalNum(0);
    					wst[id].setNewNum(0);
    					wst[id].setApv((double) 0);
    					wst[id].setAprice((double) 0);
    					wst[id].setAtprice((double) 0);
    					wst[id].setBpv((double) 0);
    					wst[id].setBprice((double) 0);
    					wst[id].setBtprice((double) 0);
    					wst[id].setAcpv((double) 0);
    					wst[id].setBcpv((double) 0);
    					wst[id].setRpv((double) 0);
    					wst[id].setRprice((double) 0);
    					wst[id].setRpv1((double) 0);
    					wst[id].setRprice1((double) 0);
    					wst[id].setAtpv((double) 0);
    					wst[id].setBtpv((double) 0);
    					wst[id].setRtpv((double) 0);
    					wst[id].setRtprice((double) 0);
    					wst[id].setRtpv1((double) 0);
    					wst[id].setRtprice1((double) 0);
    					wst[id].setPayTag(ulist.get(i).getPayTag());
    					wst[id].setNewNumReal(0);
    					wst[id].setTotalNumReal(0);
        				wst[id].setWeekTag(st.getWeekTag());
        				wst[id].setJoinPriceTal((double) 0);
        				wst[id].setJoinPvTal((double) 0);
        				wst[id].setJoinPriceNew((double) 0);
        				wst[id].setJoinPvNew((double) 0);
        				wst[id].setLeftNodeId("");
        				wst[id].setRightNodeId("");
        				wst[id].setLeftNodePv("");
        				wst[id].setRightNodePv("");
        				wst[id].setLeftNodePrice("");
        				wst[id].setRightNodePrice("");
        				wst[id].setNodeTag("");
        				wst[id].setStartTime(st.getStartTime());
        				wst[id].setEndTime(st.getEndTime());
        				wst[id].setEntryTime(date);
        				wst[id].setDayTime(startTime);
        				wst[id].setCtag(0);
        				wst[id].setTag(0);
        			}
        			
        			DSettle[] wst1 = new DSettle[num+1];

        			DSettle sel_wst = new DSettle();
        			sel_wst.setDayTime(startTime);
        			params.put("tableName", "settle_day_15");
        			params.put("wst", sel_wst);
            		List<DSettle> wlist = wstMapper.selectByList(params);
            		for(int i=0;i<wlist.size();i++){
            			int id = wlist.get(i).getuId();
            			if(wst[id]!=null){
            				wst[id].setId(wlist.get(i).getId());
            				wst[id].setTotalPerformance(wlist.get(i).getTotalPerformance());
            				wst[id].setTotalPrice(wlist.get(i).getTotalPrice());
            				wst[id].setAtpv(wlist.get(i).getAtpv());
            				wst[id].setAtprice(wlist.get(i).getAtprice());
            				wst[id].setBtprice(wlist.get(i).getBtprice());
        					wst[id].setBtpv(wlist.get(i).getBtpv());
        					wst[id].setRtpv(wlist.get(i).getRtpv());
        					wst[id].setRtprice(wlist.get(i).getRtprice());
        					wst[id].setRtpv1(wlist.get(i).getRtpv1());
        					wst[id].setRtprice1(wlist.get(i).getRtprice1());
        					wst[id].setAcpv(wlist.get(i).getAcpv());
        					wst[id].setBcpv(wlist.get(i).getBcpv());
        					wst[id].setTotalNum(wlist.get(i).getTotalNum());
        					wst[id].setTotalNumReal(wlist.get(i).getTotalNumReal());
        					wst[id].setJoinPriceTal(wlist.get(i).getJoinPriceTal());
            				wst[id].setJoinPvTal(wlist.get(i).getJoinPvTal());
            				wst[id].setJoinPriceNew(wlist.get(i).getJoinPriceNew());
            				wst[id].setJoinPvNew(wlist.get(i).getJoinPvNew());
            				wst[id].setLeftNodeId(StringUtil.notNull(wlist.get(i).getLeftNodeId()));
            				wst[id].setRightNodeId(StringUtil.notNull(wlist.get(i).getRightNodeId()));
            				wst[id].setLeftNodePv(StringUtil.notNull(wlist.get(i).getLeftNodePv()));
            				wst[id].setRightNodePv(StringUtil.notNull(wlist.get(i).getRightNodePv()));
            				wst[id].setLeftNodePrice(StringUtil.notNull(wlist.get(i).getLeftNodePrice()));
            				wst[id].setRightNodePrice(StringUtil.notNull(wlist.get(i).getRightNodePrice()));
            				wst[id].setNodeTag(StringUtil.notNull(wlist.get(i).getNodeTag()));
            				
            				if(wst1[id]==null) wst1[id] = new DSettle();
            				wst1[id].setUserId(wlist.get(i).getUserId());
            				wst1[id].setTotalPerformance(wlist.get(i).getTotalPerformance());
            				wst1[id].setTotalPrice(wlist.get(i).getTotalPrice());
            				wst1[id].setAtpv(wlist.get(i).getAtpv());
            				wst1[id].setAtprice(wlist.get(i).getAtprice());
            				wst1[id].setBtprice(wlist.get(i).getBtprice());
        					wst1[id].setBtpv(wlist.get(i).getBtpv());
        					wst1[id].setRtpv(wlist.get(i).getRtpv());
        					wst1[id].setRtprice(wlist.get(i).getRtprice());
        					wst1[id].setRtpv1(wlist.get(i).getRtpv1());
        					wst1[id].setRtprice1(wlist.get(i).getRtprice1());
        					wst1[id].setAcpv(wlist.get(i).getAcpv());
        					wst1[id].setBcpv(wlist.get(i).getBcpv());
        					wst1[id].setTotalNum(wlist.get(i).getTotalNum());
        					wst1[id].setTotalNumReal(wlist.get(i).getTotalNumReal());
        					wst1[id].setJoinPriceTal(wlist.get(i).getJoinPriceTal());
            				wst1[id].setJoinPvTal(wlist.get(i).getJoinPvTal());
            				wst1[id].setJoinPriceNew(wlist.get(i).getJoinPriceNew());
            				wst1[id].setJoinPvNew(wlist.get(i).getJoinPvNew());
            				wst1[id].setLeftNodeId(StringUtil.notNull(wlist.get(i).getLeftNodeId()));
            				wst1[id].setRightNodeId(StringUtil.notNull(wlist.get(i).getRightNodeId()));
            				wst1[id].setLeftNodePv(StringUtil.notNull(wlist.get(i).getLeftNodePv()));
            			}
            		}
            		DReward[] wrd1 = new DReward[num+1];
            		DReward sel_wrd = new DReward();
            		sel_wrd.setDayTime(startTime);
        			params.put("tableName", "reward_day_15");
        			params.put("wrd", sel_wrd);
            		List<DReward> wrdlist = wrdMapper.selectByList(params);
            		for(int i=0;i<wrdlist.size();i++){
            			int id = wrdlist.get(i).getUid();
            			if(wrd1[id]==null)  wrd1[id] = new DReward();
            				wrd1[id].setId(wrdlist.get(i).getId());
            				wrd1[id].setUid(id);
            				wrd1[id].setUserId(ulist.get(i).getUserId());
            				wrd1[id].setUserName(ulist.get(i).getUserName());
            				wrd1[id].setAmount_1(wrdlist.get(i).getAmount_1());
            				wrd1[id].setAmount_2(wrdlist.get(i).getAmount_2());
            				wrd1[id].setAmount_3(wrdlist.get(i).getAmount_3());
            				wrd1[id].setAmount_4(wrdlist.get(i).getAmount_4());
            				wrd1[id].setAmount_5(wrdlist.get(i).getAmount_5());
            				wrd1[id].setAmount_6(wrdlist.get(i).getAmount_6());
            				wrd1[id].setAmount_7(wrdlist.get(i).getAmount_7());
            				wrd1[id].setAmount_8(wrdlist.get(i).getAmount_8());
            				wrd1[id].setAmount_9(wrdlist.get(i).getAmount_9());
            				wrd1[id].setAmount_10(wrdlist.get(i).getAmount_10());
            				wrd1[id].setAmount_11(wrdlist.get(i).getAmount_11());
            				wrd1[id].setAmount_12(wrdlist.get(i).getAmount_12());
            				wrd1[id].setWeekTag(st.getWeekTag());
            				wrd1[id].setStartTime(wrdlist.get(i).getStartTime());
            				wrd1[id].setEndTime(wrdlist.get(i).getEndTime());
            				wrd1[id].setPayTag(wrdlist.get(i).getPayTag());
            				wrd1[id].setEntryTime(wrdlist.get(i).getEntryTime());
            				wrd1[id].setDayTime(wrdlist.get(i).getDayTime());
            				wrd1[id].setTag(0);
            		}
            		
        
            		/*
            		 * 拓展奖
            		 */
            		int s1=0;
            		for(int i=0;i<wst.length;i++){
            			if(wst[i]!=null){
            				for(int j=0;j<usr_str_1.length;j++){
            					
            					if(wst[i].getUserId().equals(usr_str_1[j])){
            						s1++;
		            				int id = i;
		            				int realNum = wst[i].getTotalNumReal()+wst[i].getNewNumReal();
		            				double apv =0,bpv =0,acpv=0,bcpv=0;
		            				wst[id].setApv(ArithUtil.add(usr_str[j][0],wst[id].getApv()));
		            				wst[id].setBpv(ArithUtil.add(usr_str[j][1],wst[id].getBpv()));
		            				wst[id].setAtpv(ArithUtil.add(usr_str[j][0],wst[id].getAtpv()));
		            				wst[id].setBtpv(ArithUtil.add(usr_str[j][1],wst[id].getBtpv()));
		            				wst[id].setNewPerformance(ArithUtil.add(ArithUtil.add(usr_str[j][0],usr_str[j][1]),wst[id].getNewPerformance()));
		            				wst[id].setTag(1);
		            				apv= ArithUtil.add(usr_str[j][0],wst[id].getAcpv());
		            				bpv = ArithUtil.add(usr_str[j][1],wst[id].getBcpv());
		            				double money = 0;
		            				double b =0;
		            				double max = 0;
		            				if(wst[id].getRankJoinOld()==1) {
		            					b = p2_1.getAmount_1();
		            					max = p2_2.getAmount_1();
		            				}else if(wst[id].getRankJoinOld()==2) {
		            					b = p2_1.getAmount_2();
		            					max = p2_2.getAmount_2();
		            				}else if(wst[id].getRankJoinOld()==3) {
		            					b = p2_1.getAmount_3();
		            					max = p2_2.getAmount_3();
		            				}else if(wst[id].getRankJoinOld()==4) {
		            					b = p2_1.getAmount_4();
		            					max = p2_2.getAmount_4();
		            				}else if(wst[id].getRankJoinOld()==5) {
		            					b = p2_1.getAmount_5();
		            					max = p2_2.getAmount_5();
		            				}
		            			
		            				if(apv-bpv>=0){
		            					money = ArithUtil.mul(bpv,b);
		            					acpv = ArithUtil.sub(apv,bpv);
		            					wst[id].setBcpv((double) 0);
		            					wst[id].setTag(1);
		            					if(acpv>0) {
		            						wst[id].setAcpv(acpv);
		            					}
		            				}else{
		            					money = ArithUtil.mul(apv,b);
		            					bcpv = ArithUtil.sub(bpv,apv);
		            					wst[id].setAcpv((double) 0);
		            					wst[id].setTag(1);
		            					if(bcpv>0){
		            						wst[id].setBcpv(bcpv);
		            					}
		            				}
		            				if(money>0){
		            					if(money>max) money = max;
		            					if(realNum>0){
			            					wrd[id].setAmount_4(ArithUtil.mul(money, p10.getAmount_3()));
			            					wrd[id].setTag(1);
		            					}
		            				}
            					}
            				}
            			}
            		}
            		//培育补贴
            		for(int s=0;s<wrd.length;s++){
            			if(wrd[s]!=null){
            				if(wrd[s].getAmount_4()>0){
            					int id = s;
            					double amount =wrd[id].getAmount_4();
            					String[] str = StringUtil.notNull(user[id].getRefereeNode()).split(",");
            					int t =1;
            					for(int j=str.length;j>0;j--){
            						if(!str[j-1].equals("")){
            							int sid = Integer.valueOf(str[j-1]);
            							double award = 0;
            							if(t<=3){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								if(referee_num>=3&&wst[sid].getRankJoinOld()>=1){
            									award = ArithUtil.mul(amount,p3_1.getAmount_1());
            									wrd[sid].setAmount_5(ArithUtil.add(wrd[sid].getAmount_5(),award));
            									wrd[sid].setTag(1);
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            							}else if(t<=6){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								//System.out.println("userId:"+wst[sid].getUserId()+";t:"+t+";referee_num:"+referee_num);

            								if(referee_num>=6&&wst[sid].getRankJoinOld()>=2){
            									award = ArithUtil.mul(amount,p3_1.getAmount_2());
            									wrd[sid].setAmount_5(ArithUtil.add(wrd[sid].getAmount_5(),award));
            									wrd[sid].setTag(1);
            									t--;
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            							}
            							else if(t<=9){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								//System.out.println("userId:"+wst[sid].getUserId()+";t:"+t+";referee_num:"+referee_num);

            								if(referee_num>=9&&wst[sid].getRankJoinOld()>=3){
            									award = ArithUtil.mul(amount,p3_1.getAmount_3());
            									wrd[sid].setAmount_5(ArithUtil.add(wrd[sid].getAmount_5(),award));
            									wrd[sid].setTag(1);
            									t--;
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            							}
            						}
            						if(t>=9){
	            						j=0;
	            						break;
            						}
            						t++;
            					}
            				}
            			}
            		}
            		
            		//计算复消
            		int n1 =1;
            		int error1=0;
            		for(int i=0;i<wrd.length;i++){
            			if(wrd[i]!=null){
            				if(wrd1[i]!=null){
            				if(wrd[i].getAmount_4()>0||wrd[i].getAmount_5()>0){
            				DReward drd = new DReward();
            				drd.setId(wrd1[i].getId());
            				drd.setAmount_4(ArithUtil.add(wrd[i].getAmount_4(),wrd1[i].getAmount_4()));
            				drd.setAmount_5(ArithUtil.add(wrd[i].getAmount_5(),wrd1[i].getAmount_5()));
        					drd.setAmount_9(ArithUtil.add(ArithUtil.add(wrd[i].getAmount_4(),wrd[i].getAmount_5()),wrd1[i].getAmount_9()));
        					if(drd.getAmount_9()>0){
        						drd.setAmount_10(ArithUtil.mul(drd.getAmount_9(), p9.getAmount_1()));
        						drd.setAmount_11(ArithUtil.mul(drd.getAmount_9(), p9.getAmount_2()));
        						drd.setAmount_12(ArithUtil.sub(ArithUtil.sub(drd.getAmount_9(), drd.getAmount_10()), drd.getAmount_11()));
        					}
        					params.put("tableName", "reward_day_15");
                			params.put("wrd", drd);
	                    	Integer wrd_insert = wrdMapper.update(params);
	                    	if(wrd_insert==null || wrd_insert==0)  error1++;
	                    	n1++;
	                    	drd =null;
            				}
            				}
            			}
            		}
            		int n2 =1;
            		int error2=0;
            		for(int i=0;i<wst.length;i++){
            			if(wst[i]!=null){
            				if(wst[i].getTag()==1){
            					DSettle update_dst = new DSettle();
            					update_dst.setId(wst[i].getId());
            					update_dst.setAcpv(wst[i].getAcpv());
            					update_dst.setAtpv(wst[i].getAtpv());
            					update_dst.setApv(wst[i].getApv());
            					update_dst.setBcpv(wst[i].getBcpv());
            					update_dst.setBtpv(wst[i].getBtpv());
            					update_dst.setBpv(wst[i].getBpv());
            					update_dst.setNewPerformance(wst[i].getNewPerformance());
	        					params.put("tableName", "settle_day_15");
	                    		params.put("wst", update_dst);
	                    		Integer wst_insert = wstMapper.updateAll(params);
	                    		if(wst_insert==null || wst_insert==0)  error2++;
	                    		DSettle update_dst1 = new DSettle();
	                    		update_dst1.setUserId(wst[i].getUserId());;
            					update_dst1.setAcpv(wst[i].getAcpv());
            					update_dst1.setAtpv(wst[i].getAtpv());
            					update_dst1.setBcpv(wst[i].getBcpv());
            					update_dst1.setBtpv(wst[i].getBtpv());
            					update_dst1.setTotalPerformance(ArithUtil.add(wst[i].getTotalPerformance(), wst[i].getNewPerformance()));
            					Integer wst_insert1 = wstMapper.updateUserId(update_dst1);
            					if(wst_insert1==null || wst_insert1==0)  error2++;
            					n2++;
            					}
            				}
            			}
            	
            		if(error1==0&&error2==0){
            			msg ="数据更新成功！";
            			System.out.println("success2");
            			sqlSession.commit();
            		}else{
            			msg ="数据更新有误";
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
        				msg="拓展奖比例参数获取失败。";
        				sqlSession.rollback();
            		}
        		}else{
        			msg="创业奖参数获取失败。";
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
}
