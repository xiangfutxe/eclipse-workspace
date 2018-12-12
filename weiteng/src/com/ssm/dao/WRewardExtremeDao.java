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
import com.ssm.mapper.JoinInfoMapper;
import com.ssm.mapper.JoinUpdateDetailMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.SettleMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.WRewardExtremeMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.Address;
import com.ssm.pojo.AdminLog;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.Order;
import com.ssm.pojo.Param;
import com.ssm.pojo.Settle;
import com.ssm.pojo.User;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WRewardExtreme;
import com.ssm.pojo.WSettle;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.StringUtil;

public class WRewardExtremeDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	  SettleMapper stMapper = sqlSession.getMapper(SettleMapper.class);
	    WRewardMapper wrdMapper = sqlSession.getMapper(WRewardMapper.class);
	    WRewardExtremeMapper wrdExtremeMapper = sqlSession.getMapper(WRewardExtremeMapper.class);

	    WSettleMapper wstMapper = sqlSession.getMapper(WSettleMapper.class);   
	    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);  
	   UserMapper userMapper = sqlSession.getMapper(UserMapper.class);  
	   JoinInfoMapper jfMapper = sqlSession.getMapper(JoinInfoMapper.class);  
	   ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);  
	   MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);  
	    AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);


    public Pager findByPage(WRewardExtreme wrd,Pager pager){
    	try{
    		if(wrd!=null){
				Map<String,Object> params = new HashMap<>();
				params.put("wrd",wrd);
				params.put("tableName", "wreward_extreme");
				int recordCount = wrdExtremeMapper.count(params);
				pager.setRowCount(recordCount);
				if(recordCount>0){
					params.put("pageModel", pager);
				}
				List<WRewardExtreme> adrs = wrdExtremeMapper.selectByPage(params);
				sqlSession.commit();
				pager.setResultList(adrs);
    		}
    	} finally {
			sqlSession.close();
    	}
    	return pager;
	}
    
    public List<WRewardExtreme> findByList(WRewardExtreme wrd){
    	List<WRewardExtreme> wrds =null;
    	try{
    		if(wrd!=null){
				Map<String,Object> params = new HashMap<>();
				params.put("wrd",wrd);
				params.put("tableName", "wreward_extreme");
				
				wrds= wrdExtremeMapper.selectByPage(params);
				sqlSession.commit();
    		}
    	} finally {
			sqlSession.close();
    	}
    	return wrds;
	}
    
    public WRewardExtreme awardSummary(String tableName,User user){
    	WRewardExtreme wrd = new WRewardExtreme();
    	try{
    		wrd = wrdExtremeMapper.sumAward(tableName,user);
				sqlSession.commit();
    	} finally {
			sqlSession.close();
    	}
    	return wrd;
	}
    
    public WRewardExtreme findByUserId(String userId,String tableName){
    	WRewardExtreme wrd = new WRewardExtreme();
    	try{
    		wrd = wrdExtremeMapper.findByUserId(userId,tableName);
				sqlSession.commit();
    	} finally {
			sqlSession.close();
    	}
    	return wrd;
	}
    
    public String payoff(Integer weekTag,String adminName){
    	String msg = "";
    	try{
    	
    		double award = 0;
    		double doubleAward = 0;
    		String tableName = "wreward_extreme";
    		User sel_user = new User();
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
    		List<WRewardExtreme> wlist=wrdExtremeMapper.findAll(tableName, " for update");
    		int count =0;
    		String error = "";
    		
    		for(int i=0;i<wlist.size();i++){
    			if(wlist.get(i).getState()==1){
    				count++;
    				if(wlist.get(i).getPayTag()==1){
		    			if(wlist.get(i).getAmount_1()>0){
		    				int id = wlist.get(i).getUid();
		    				BigDecimal   b1   =   new   BigDecimal(wlist.get(i).getAmount_4());
		    				BigDecimal   b2   =   new   BigDecimal(wlist.get(i).getAmount_2());  
		    				double amount =b1.setScale(2,   BigDecimal.ROUND_DOWN).doubleValue(); 
		    				double double_amount =b2.setScale(2,   BigDecimal.ROUND_DOWN).doubleValue(); 
		    				award = ArithUtil.add(award, amount);
		    				doubleAward = ArithUtil.add(doubleAward, double_amount);
		    				if(user[id]!=null){
		    					User up_user = new User();
		    					up_user.setId(user[id].getId());
		    					if(amount>0){
		    					AccountDetail ad = new AccountDetail();
		    					ad.setUid(user[id].getId());
		    					ad.setUserId(user[id].getUserId());
		    					ad.setUserName(user[id].getUserName());
		    					ad.setAmount(amount);
		    					ad.setBalance(ArithUtil.add(user[id].getRmoney(), amount));
		    					ad.setPayType(1);
		    					ad.setTradeType(Constants.MONEY_DETAIL_YYPE_3);
		    					ad.setSummary(Constants.MONEY_DETAIL_YYPE_4);
		    					ad.setEntryTime(date);
		    					params.put("account",ad);
		    					params.put("tableName", "rmoneyDetail");
		    					Integer up1 = moneyMapper.save(params);
		    					if(up1==null||up1==0)
		    						error +=user[id].getUserId()+"奖金账户明细保存失败<br>";
		    					up_user.setRmoney(ad.getBalance());
		    					}
		    					if(double_amount>0){
		    					AccountDetail ad1 = new AccountDetail();
		    					ad1.setUid(user[id].getId());
		    					ad1.setUserId(user[id].getUserId());
		    					ad1.setUserName(user[id].getUserName());
		    					ad1.setAmount(double_amount);
		    					ad1.setBalance(ArithUtil.add(user[id].getDmoney(), double_amount));
		    					ad1.setPayType(1);
		    					ad1.setTradeType(Constants.MONEY_DETAIL_YYPE_3);
		    					ad1.setSummary(Constants.MONEY_DETAIL_YYPE_4);
		    					ad1.setEntryTime(date);
		    					params.put("account",ad1);
		    					params.put("tableName", "dmoneyDetail");
		    					Integer up2 = moneyMapper.save(params);
		    					if(up2==null||up2==0)
		    						error +=user[id].getUserId()+"复消券账户明细保存失败<br>";
		    					up_user.setDmoney(ad1.getBalance());
		    					}
		    					Integer up3 = userMapper.updateUser(up_user);
		    					if(up3==null||up3==0)
		    						error +=user[id].getUserId()+"电子币更新失败。<br>";
		    				}
		    			}
    				}
    			}
    		}
    		if(error.equals("")){
    		if(wrdMapper.updateForState(tableName, 2, 1)>=count){
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
    			msg = error;
    			sqlSession.rollback();
    		}
    		}else{
    			msg = "没有需要发放奖金的会员";
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
    
    public String countReward(Integer weekTag){
    	String msg="";
    	try{
    		Timestamp date = new Timestamp(new Date().getTime());
    		Settle st = stMapper.selectByWeekTagForUpdate(weekTag);
    		if(st!=null){
    			Param p1_1 = paramMapper.selectByName("创业金比例");
    			Param p2_1 = paramMapper.selectByName("拓展奖比例");
        		Param p2_2 = paramMapper.selectByName("拓展奖封顶额");
        		Param p3_1 = paramMapper.selectByName("培育奖比例");
        		
        		Param p9 = paramMapper.selectByName("奖金结算参数");
        		if(p1_1!=null){
        		if(p2_1!=null){
        			if(p2_2!=null){
        				if(p3_1!=null){
        						if(p9!=null){
    			String table_name_r = "wreward_"+st.getWeekTag();
        		String table_name_s = "wsettle_"+st.getWeekTag();
        		User sel_user = new User();
        		sel_user.setEndTime(st.getEndTime());
        		Map<String,Object> params = new HashMap<>();
        		params.put("user",sel_user);
        		List<User> ulist = userMapper.selectMoneyByPage(params);
        		Integer maxId= userMapper.maxId(params);
        		int num = 1;
        		if(maxId!=null) num =maxId+1;
        		if(num>1){
        			User[] user = new User[num+1];
        			WReward[] wrd = new WReward[num+1];
        			WSettle[] wst = new WSettle[num+1];
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
        				user[id].setTag(0);
        				
        				if(wrd[id]==null) wrd[id] = new WReward();
        				wrd[id].setuId(id);
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
        				wrd[id].setStartTime(st.getStartTime());
        				wrd[id].setEndTime(st.getEndTime());
        				wrd[id].setPayTag(ulist.get(i).getPayTag());
        				wrd[id].setEntryTime(date);
        				
        				if(wst[id]==null) wst[id] = new WSettle();
        				wst[id].setuId(id);
        				wst[id].setUserId(ulist.get(i).getUserId());
        				wst[id].setUserName(ulist.get(i).getUserName());
        				
        				wst[id].setRank(ulist.get(i).getRankManage());
        				wst[id].setMaxRankNew(ulist.get(i).getRankManage());
        				wst[id].setRankJoin(ulist.get(i).getRankJoinOld());
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
        			}
        			WSettle sel_wst = new WSettle();
        			sel_wst.setEndTime(st.getEndTime());
        			params.put("wst", sel_wst);
            		List<WSettle> wlist = wstMapper.selectListWithParam(params);
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
            			}
            		}
            		
            		int newNum = 0;
    				double totalPv = 0;
    				double totalPrice = 0;
    				double totalPv1 = 0;
    				double totalPrice1 = 0;
            		JoinInfo sel_jf = new JoinInfo();
            		sel_jf.setStartTime(st.getStartTime());
            		sel_jf.setEndTime(st.getEndTime());
            		sel_jf.setState(2);
            		params.put("joinInfo", sel_jf);
            		List<JoinInfo> jlist = jfMapper.selectAllByList(params);
            		System.out.println("size:"+jlist.size());
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
    					if(oldRankJoin==0) {
    						if(!userId.equals(Constants.TOP_NODE)){
    							newNum++;
    							wst[rid].setNewNum(wst[rid].getNewNum()+1);
    							wst[rid].setTag(1);
	    						if(price>0||pv>0){
	    							wst[rid].setNewNumReal(wst[rid].getNewNumReal()+1);
	    							wst[rid].setTag(1);
	    						}
    						}
    					}
    					
    					if(wst[uid].getRankJoin()<newRankJoin){
    						wst[uid].setRankJoin(newRankJoin);
    						if(pv==0&&price==0) wst[uid].setRankJoinTag(1);
    						wst[uid].setTag(1);
    					}
    				
    					if(price>0||pv>0){
    						 String[] str = user[uid].getNode().split(",");
    						 String[] strarray = user[uid].getNodeABC().split(",");
    						 String[] str1 = user[uid].getRefereeNode().split(",");
    						wst[uid].setRpv(ArithUtil.add(wst[uid].getRpv(),pv));
    						wst[uid].setRprice(ArithUtil.add(wst[uid].getRpv(),price));
    						wst[uid].setTag(1);
    						 for(int j=0;j<str.length;j++){
    							if(!str[j].equals("")){
    								int sid = Integer.valueOf(str[j]);
    								 if(strarray[j].equals("A")){
    									wst[sid].setApv(ArithUtil.add(wst[sid].getApv(),pv));
    									wst[sid].setAprice(ArithUtil.add(wst[sid].getAprice(),price));
    								 } else if(strarray[j].equals("B")) {
    										wst[sid].setBpv(ArithUtil.add(wst[sid].getBpv(),pv));
    										wst[sid].setBprice(ArithUtil.add(wst[sid].getBprice(),price));
    								 }
    								 wst[sid].setNewPerformance(ArithUtil.add(wst[sid].getNewPerformance(),pv));
    								 wst[sid].setNewPrice(ArithUtil.add(wst[sid].getNewPrice(),price));
    								 wst[sid].setTag(1);
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
    				System.out.println("totalPrice:"+totalPrice);
    				//统计业绩
    				Order sel_od = new Order();
    				sel_od.setStartTime(st.getStartTime());
    				sel_od.setEndTime(st.getEndTime());
    				sel_od.setOrderType(2);
            		params.put("order", sel_od);
            		List<Order> olist = orderMapper.selectByList(params);
            		sel_od.setOrderType(3);
            		params.put("order", sel_od);
            		List<Order> olist1 = orderMapper.selectByList(params);
            		olist.addAll(olist1);
            		for(int j=0;j<olist.size();j++){
            			int uid = olist.get(j).getuId();
    					double price =  olist.get(j).getPrice();
    					double pv =  olist.get(j).getPv();
    					totalPv1 = ArithUtil.add(totalPv1,pv);
    					totalPrice1 = ArithUtil.add(totalPrice1,price);
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
            		st.setNewNum(newNum);
            		
            		/*计算等级*/
            		for(int i=user.length-1;i>0;i--){
            			if(user[i]!=null){
            				if(user[i].getState()>0){
            					if(user[i].getUserId().equals("AA000000")){
            						System.out.println("AA000000:"+wst[i].getNewPerformance());
            					}
            				if(user[i].getEntryTime().getTime()-st.getEndTime().getTime()<0){
            				if(!StringUtil.notNull(user[i].getRefereeAll()).equals("")){
            				String[] str = StringUtil.notNull(user[i].getRefereeAll()).split(",");
            				if(str.length>0){
            					List<String> strlist =new ArrayList<String>();
            					for(int j=0;j<str.length;j++){
            						if(!str[j].equals("")){
            							int uid = Integer.valueOf(str[j]);
            							if(uid<=maxId &&user[uid]!=null){
	            							if(user[uid].getEntryTime().getTime()-st.getEndTime().getTime()<0){
	            								strlist.add(str[j]);
	            							}
            							}
            						}
            					}
            					int rank = wst[i].getRank();
            					int num1=0,num2=0,num3=0,num4=0,num5=0,num6=0,num7=0,num8=0,num9=0,num10=0;
            					double total_pv = ArithUtil.add(ArithUtil.add(ArithUtil.add(wst[i].getRtpv(),wst[i].getRpv()),wst[i].getRtpv1()),wst[i].getRpv1());
            					double total_pv_1 = 0;
            					double min_pv = 0;
            					double max_pv = 0;
            					if(strlist.size()>1){
            						for(int j=0;j<strlist.size();j++){
            							if(!strlist.get(j).equals("")){
            							int uid = Integer.valueOf(strlist.get(j));
            							if(uid<=num){
            								if(wst[uid].getMaxRankNew()>0)num1++;
            								if(wst[uid].getMaxRankNew()>1)num2++;
            								if(wst[uid].getMaxRankNew()>2)num3++;
            								if(wst[uid].getMaxRankNew()>3)num4++;
            								if(wst[uid].getMaxRankNew()>4)num5++;
            								if(wst[uid].getMaxRankNew()>5)num6++;
            								if(wst[uid].getMaxRankNew()>6)num7++;
            								if(wst[uid].getMaxRankNew()>7)num8++;
            								if(wst[uid].getMaxRankNew()>8)num9++;
            								if(wst[uid].getMaxRankNew()>9)num10++;
            								double pv = ArithUtil.add(ArithUtil.add(ArithUtil.add(wst[uid].getRpv(),wst[uid].getRtpv()),wst[uid].getRtpv1()),wst[uid].getRpv1());
            								total_pv_1 = ArithUtil.add(total_pv_1,pv);
            								if(j==0) max_pv = pv;
            								else if(max_pv<pv) max_pv = pv;
            							}
            							}
            						}
            						}
            						min_pv = ArithUtil.sub(total_pv_1,max_pv);
            						if(num10>1&num8>2&total_pv>=188800000){
            							rank = 11;
            						}else if(num9>1&num7>2&total_pv>=85600000){
            							rank = 10;
            						}else if(num8>1&num6>2&total_pv>=38800000){
            							rank = 9;
            						}else if(num7>1&num5>2&total_pv>=17600000){
            							rank = 8;
            						}else if(num6>1&num4>2&total_pv>=8000000){
            							rank = 7;
            						}else if(num5>1&&num3>2&total_pv>=3600000){
            							rank = 6;
            						}else if(num4>1&&total_pv>=1600000){
            							rank = 5;
            						}else if(num3>1&&total_pv>=800000){
            							rank = 4;
            						}else if(num2>1&&total_pv>=400000){
            							rank = 3;
            						}else if(num1>1&&total_pv>=200000){
            							rank = 2;
            						}else if(max_pv>=50000&min_pv>=50000&total_pv>=100000){
            							rank = 1;
            						}
            					if(rank>wst[i].getRank()){
            						wst[i].setRank(rank);
            						wst[i].setMaxRankNew(rank);
            						wst[i].setTag(1);
            					}
            					String[] str1 = StringUtil.notNull(user[i].getRefereeNode()).split(",");
            						for(int j=0;j<str1.length;j++){
            							if(!str1[j].equals("")){
            									int sid = Integer.valueOf(str1[j]);
            									if(wst[i].getRank()>wst[sid].getMaxRankNew()){
            										wst[sid].setMaxRankNew(wst[i].getRank());
            										wst[sid].setTag(1);
            									}
            							}
            						}
            					}
            				
            				}
            				}
            				}
            			}
            		}
            		
            		
            		 // 创业金计算
            		for(int i=0;i<jlist.size();i++){
            			int id = jlist.get(i).getUid();
            			double pv = jlist.get(i).getPv();
            			int rid = jlist.get(i).getRid();
            			if(pv>0){
            				if(user[id]!=null){
            					double property = 0;
            					if(wst[id].getRankJoin()==1) property = p1_1.getAmount_1();
            					else if(wst[id].getRankJoin()==2) property = p1_1.getAmount_2();
            					else if(wst[id].getRankJoin()==3) property = p1_1.getAmount_3();
            					else if(wst[id].getRankJoin()==4) property = p1_1.getAmount_4();
            					else if(wst[id].getRankJoin()==5) property = p1_1.getAmount_5();
            					double award = ArithUtil.mul(pv, property);
            					if(wrd[rid]!=null){
            						wrd[rid].setAmount_1(ArithUtil.add(wrd[rid].getAmount_1(), award));
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
            				double apv =0,bpv =0,acpv=0,bcpv=0;
            				apv= ArithUtil.add(wst[id].getApv(),wst[id].getAcpv());
            				bpv = ArithUtil.add(wst[id].getBpv(),wst[id].getBcpv());
            				double money = 0;
            				double b =0;
            				double max = 0;
            				if(wst[id].getRankJoin()==1) {
            					b = p2_1.getAmount_1();
            					max = p2_2.getAmount_1();
            				}else if(wst[id].getRankJoin()==2) {
            					b = p2_1.getAmount_2();
            					max = p2_2.getAmount_2();
            				}else if(wst[id].getRankJoin()==3) {
            					b = p2_1.getAmount_3();
            					max = p2_2.getAmount_3();
            				}else if(wst[id].getRankJoin()==4) {
            					b = p2_1.getAmount_4();
            					max = p2_2.getAmount_4();
            				}else if(wst[id].getRankJoin()==5) {
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
            					wrd[id].setAmount_2(money);
            					wrd[id].setTag(1);
            				}
            			}
            		}
            		//培育补贴
            		for(int s=0;s<wrd.length;s++){
            			if(wrd[s]!=null){
            				if(wrd[s].getAmount_2()>0){
            					int id = s;
            					double amount =wrd[id].getAmount_2();
            					String[] str = StringUtil.notNull(user[id].getRefereeNode()).split(",");
            					int t =8;
            					for(int j=str.length;j>0;j--){
            						if(!str[j-1].equals("")){
            							int sid = Integer.valueOf(str[j-1]);
            							double award = 0;
            							if(t==1){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								if(referee_num<user[sid].getRaiseNum()) referee_num = user[sid].getRaiseNum();
            								if(referee_num>=8&&wst[sid].getRankJoin()>4){
            									award = ArithUtil.mul(amount,p3_1.getAmount_8());
            									wrd[sid].setAmount_3(ArithUtil.add(wrd[sid].getAmount_3(),award));
            									wrd[sid].setTag(1);
            								
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            								t--;
            							}else if(t==2){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								if(referee_num<user[sid].getRaiseNum()) referee_num = user[sid].getRaiseNum();
            								if(referee_num>=7){
            									award = ArithUtil.mul(amount,p3_1.getAmount_7());
            									wrd[sid].setAmount_3(ArithUtil.add(wrd[sid].getAmount_3(),award));
            									wrd[sid].setTag(1);
            									
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            								t--;
            							}
            							else if(t==3){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								if(referee_num<user[sid].getRaiseNum()) referee_num = user[sid].getRaiseNum();
            								if(referee_num>=6){
            									award = ArithUtil.mul(amount,p3_1.getAmount_6());
            									wrd[sid].setAmount_3(ArithUtil.add(wrd[sid].getAmount_3(),award));
            									wrd[sid].setTag(1);
            									
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            								t--;
            							}else if(t==4){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								if(referee_num<user[sid].getRaiseNum()) referee_num = user[sid].getRaiseNum();
            								if(referee_num>=5){
            									award = ArithUtil.mul(amount,p3_1.getAmount_5());
            									wrd[sid].setAmount_3(ArithUtil.add(wrd[sid].getAmount_3(),award));
            									wrd[sid].setTag(1);
            									
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            								t--;
            							}else if(t==5){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								if(referee_num<user[sid].getRaiseNum()) referee_num = user[sid].getRaiseNum();
            								if(referee_num>=4){
            									award = ArithUtil.mul(amount,p3_1.getAmount_4());
            									wrd[sid].setAmount_3(ArithUtil.add(wrd[sid].getAmount_3(),award));
            									wrd[sid].setTag(1);
            									
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            								t--;
            							}else if(t==6){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								if(referee_num<user[sid].getRaiseNum()) referee_num = user[sid].getRaiseNum();
            								if(referee_num>=3){
            									award = ArithUtil.mul(amount,p3_1.getAmount_3());
            									wrd[sid].setAmount_3(ArithUtil.add(wrd[sid].getAmount_3(),award));
            									wrd[sid].setTag(1);
            									
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            								t--;
            							}else if(t==7){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								if(referee_num<user[sid].getRaiseNum()) referee_num = user[sid].getRaiseNum();
            								if(referee_num>=2){
            									award = ArithUtil.mul(amount,p3_1.getAmount_2());
            									wrd[sid].setAmount_3(ArithUtil.add(wrd[sid].getAmount_3(),award));
            									wrd[sid].setTag(1);
            									
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            								t--;
            							}else if(t==8){
            								int referee_num = wst[sid].getNewNumReal()+wst[sid].getTotalNumReal();
            								if(referee_num<user[sid].getRaiseNum()) referee_num = user[sid].getRaiseNum();
            								if(referee_num>=1){
            									award = ArithUtil.mul(amount,p3_1.getAmount_1());
            									wrd[sid].setAmount_3(ArithUtil.add(wrd[sid].getAmount_3(),award));
            									wrd[sid].setTag(1);
            									//String sql1 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 3, award, "第"+(9-t)+"代,计奖业绩为："+amount+"。", st, date);
            									//slist.add(sql1);
            								}
            								t--;
            							}else t--;
            						}
            						if(t<=0){
	            						j=0;
	            						break;
            						}
            					}
            				}
            			}
            		}
            		//领导奖
            		for(int i=0;i<jlist.size();i++){
            			int id = jlist.get(i).getUid();
            			if(wst[id]!=null){
            				double amount = jlist.get(i).getPv();
            				if(amount>0){
            				if(!StringUtil.notNull(user[id].getRefereeNode()).equals("")){
            				String[] str = StringUtil.notNull(user[id].getRefereeNode()).split(",");
            				double p = 0;
            				for(int j=str.length;j>0;j--){
            					if(!str[j-1].equals("")){
            						int sid = Integer.valueOf(str[j-1]);
            						double award = 0;
            						if(p<0.06){
            							if(wst[sid].getRank()==1){
            								if(p<0.01) {
            									award = ArithUtil.mul(amount,ArithUtil.sub(0.01,p));
            									p = (double) 0.01;
            								}
            								 
            							}else if(wst[sid].getRank()==2){
            								if(p<0.02){
            									award = ArithUtil.mul(amount,ArithUtil.sub(0.02,p));
            									p = (double) 0.02;
            								}
            							}else if(wst[sid].getRank()==3){
            								if(p<0.03){
            									award = ArithUtil.mul(amount,ArithUtil.sub(0.03,p));
            									p = (double) 0.03;
            								}
            							}else if(wst[sid].getRank()==4){
            								if(p<0.035){
            									award = ArithUtil.mul(amount,ArithUtil.sub(0.035,p));
            									p = (double) 0.035;
            								}
            							}else if(wst[sid].getRank()==5){
            								if(p<0.04){
            									award = ArithUtil.mul(amount,ArithUtil.sub(0.04,p));
            									p = (double) 0.04;
            								}
            							}else if(wst[sid].getRank()==6){
            								if(p<0.045){
            									award = ArithUtil.mul(amount,ArithUtil.sub(0.045,p));
            									p = (double) 0.045;
            								}
            							}else if(wst[sid].getRank()==7){
            								if(p<0.048){
            									award = ArithUtil.mul(amount,ArithUtil.sub(0.048,p));
            									p = (double) 0.048;
            								}
            							}else if(wst[sid].getRank()==8){
            								if(p<0.051){
            									award = ArithUtil.mul(amount,ArithUtil.sub(0.051,p));
            									p = (double) 0.051;
            								}
            							}else if(wst[sid].getRank()==9){
            								if(p<0.054){
            									award = ArithUtil.mul(amount,ArithUtil.sub(0.054,p));
            									p = (double) 0.054;
            								}
            							}else if(wst[sid].getRank()==10){
            								if(p<0.057){
            									award = ArithUtil.mul(amount,ArithUtil.sub(0.057,p));
            									p = (double) 0.057;
            								}
            							}else if(wst[sid].getRank()==11){
            								if(p<0.06){
            									award = ArithUtil.mul(amount,ArithUtil.sub(0.06,p));
            									p = (double) 0.06;
            								}
            							}
            							}else j=0;
            							if(award>0){
            								wrd[sid].setAmount_4(ArithUtil.add(wrd[sid].getAmount_4(),award));
            								wrd[sid].setTag(1);
            								//String sql2 =cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 4, award, "计奖金额："+amount, st, date);
            								//slist.add(sql2);
            							}
            						}
            						}
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
            				double perporty = 0.02;
            				
            				if(user[centerId]!=null){
            					if(pv>0){
            					award = ArithUtil.mul(pv, perporty);
            					wrd[centerId].setAmount_5(ArithUtil.add(wrd[centerId].getAmount_5(),award));
            					wrd[centerId].setTag(1);
            					//String sql2 = cs.insetWRewardDetail(conn, r_table_name_d, user[centerId], user[id], 5, award, "计奖业绩："+pv, st, date);
            					//slist.add(sql2);
            					}
            				}
            			}
            		}
            		//消费奖
            		for(int i=0;i<olist.size();i++){
            			int id = olist.get(i).getuId();
            			double amount = olist.get(i).getPv();
            			if(amount>0){
            			double p = 0;
            			double	award1 = 0;
            			if(wst[id].getRank()==11){
            				award1=ArithUtil.mul(amount,0.5);
            				p = (double) 0.5;
            			}else if(wst[id].getRank()==10){
            				award1=ArithUtil.mul(amount,0.48);
            				p = (double) 0.48;
            			}else if(wst[id].getRank()==9){
            				award1=ArithUtil.mul(amount,0.46);
            				p = (double) 0.56;
            			}else if(wst[id].getRank()==8){
            				award1=ArithUtil.mul(amount,0.44);
            				p = (double) 0.44;
            			}else if(wst[id].getRank()==7){
            				award1=ArithUtil.mul(amount,0.42);
            				p = (double) 0.42;
            			}else if(wst[id].getRank()==6){
            				award1=ArithUtil.mul(amount,0.4);
            				p = (double) 0.4;
            			}else if(wst[id].getRank()==5){
            				award1=ArithUtil.mul(amount,0.38);
            				p = (double) 0.38;
            			}else if(wst[id].getRank()==4){
            				award1=ArithUtil.mul(amount,0.36);
            				p = (double) 0.36;
            			}else if(wst[id].getRank()==3){
            				award1=ArithUtil.mul(amount,0.34);
            				p = (double) 0.34;
            			}else if(wst[id].getRank()==2){
            				award1=ArithUtil.mul(amount,0.32);
            				p = (double) 0.32;
            			}else if(wst[id].getRank()==1){
            				award1=ArithUtil.mul(amount,0.3);
            				p = (double) 0.3;
            			}else if(wst[id].getRankJoin()==5){
            				award1=ArithUtil.mul(amount,0.25);
            				p = (double) 0.25;
            			}else if(wst[id].getRankJoin()==4){
            				award1=ArithUtil.mul(amount,0.2);
            				p = (double) 0.2;
            			}else if(wst[id].getRankJoin()==3){
            				award1=ArithUtil.mul(amount,0.15);
            				p = (double) 0.15;
            			}else if(wst[id].getRankJoin()==2){
            				award1=ArithUtil.mul(amount,0.1);
            				p = (double) 0.10;
            			}else if(wst[id].getRankJoin()==1){
            				award1=ArithUtil.mul(amount,0.05);
            				p = (double) 0.05;
            			}
            			if(award1>0){
            				wrd[id].setAmount_6(ArithUtil.add(wrd[id].getAmount_6(),award1));
            				wrd[id].setTag(1);
            				//String sql2 = cs.insetWRewardDetail(conn, r_table_name_d, user[id], user[id], 7, award1, "计奖业绩:"+amount+"。", st, date);
            				//slist.add(sql2);
            			}
            			String[] str = StringUtil.notNull(user[id].getRefereeNode()).split(",");
            			for(int j=str.length;j>0;j--){
            				if(!str[j-1].equals("")){
            					int sid = Integer.valueOf(str[j-1]);
            					double award = 0;
            					if(p<0.5){
            						if(wst[sid].getRank()==1){
            							if(p<0.3){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.3,p));
            								p = (double) 0.3;
            							}
            						}else if(wst[sid].getRank()==2){
            							if(p<0.46){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.32,p));
            								p = (double) 0.32;
            							}
            						}else if(wst[sid].getRank()==3){
            							if(p<0.34){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.34,p));
            								p = (double) 0.34;
            							}
            						}else if(wst[sid].getRank()==4){
            							if(p<0.36){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.36,p));
            								p = (double) 0.36;
            							}
            						}else if(wst[sid].getRank()==5){
            							if(p<0.38){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.38,p));
            								p = (double) 0.38;
            							}
            						}else if(wst[sid].getRank()==6){
            							if(p<0.4){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.4,p));
            								p = (double) 0.4;
            							}
            						}else if(wst[sid].getRank()==7){
            							if(p<0.42){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.42,p));
            								p = (double) 0.42;
            							}
            						}else if(wst[sid].getRank()==8){
            							if(p<0.44){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.44,p));
            								p = (double) 0.44;
            							}
            						}else if(wst[sid].getRank()==9){
            							if(p<0.46){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.46,p));
            								p = (double) 0.46;
            							}
            						}else if(wst[sid].getRank()==10){
            							if(p<0.48){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.48,p));
            								p = (double) 0.48;
            							}
            						}else if(wst[sid].getRank()==11){
            							if(p<0.5){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.5,p));
            								p = (double) 0.5;
            							}
            						}else if(wst[sid].getRankJoin()==1){
            							if(p<0.05){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.05,p));
            								p = (double) 0.05;
            							}
            						}else if(wst[sid].getRankJoin()==2){
            							if(p<0.1){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.1,p));
            								p = (double) 0.1;
            							}
            						}else if(wst[sid].getRankJoin()==3){
            							if(p<0.15){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.15,p));
            								p = (double) 0.15;
            							}
            						}else if(wst[sid].getRankJoin()==4){
            							if(p<0.2){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.2,p));
            								p = (double) 0.2;
            							}
            						}else if(wst[sid].getRankJoin()==5){
            							if(p<0.25){
            								award = ArithUtil.mul(amount,ArithUtil.sub(0.25,p));
            								p = (double) 0.25;
            							}
            						}
            					}
            						if(award>0){
            							wrd[sid].setAmount_6(ArithUtil.add(wrd[sid].getAmount_6(),award));
            							wrd[sid].setTag(1);
            							//String sql2 = cs.insetWRewardDetail(conn, r_table_name_d, user[sid], user[id], 7, award, "计奖业绩:"+amount+"。", st, date);
            							//slist.add(sql2);
            						}
            					}
            					if(p>=0.5) j=0;
            					}
            			}
            		}
            		//计算复消
            		List<WReward> wrdlist = new ArrayList<WReward>();
            		int n1 =1;
            		int error1=0;
            		for(int i=0;i<wrd.length;i++){
            			if(wrd[i]!=null){
            				wrd[i].setRankJoin(wst[i].getRankJoin());
            				wrd[i].setRankManage(wst[i].getRank());
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
	                    		wrdlist = new ArrayList<WReward>();
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
            		List<WSettle> wstlist = new ArrayList<WSettle>();
            		for(int i=0;i<wst.length;i++){
            			if(wst[i]!=null){
            				if(n2%800==0){
            					wstlist.add(wst[i]);
	        					params.put("tableName", table_name_s);
	                    		params.put("list", wstlist);
	                    		Integer wst_insert = wstMapper.insertAll(params);
	                    		if(wst_insert==null || wst_insert==0)  error2++;
	                    		wstlist = new ArrayList<WSettle>();
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
    
    public String confirmReward(Integer weekTag){
    	String msg="";
    	try{
    		Timestamp date = new Timestamp(new Date().getTime());
    		Settle st = stMapper.selectByWeekTagForUpdate(weekTag);
    		if(st!=null){
    			
    			String table_name_r = "wreward_"+st.getWeekTag();
        		String table_name_s = "wsettle_"+st.getWeekTag();
        		User sel_user = new User();
        		sel_user.setEndTime(st.getEndTime());
        		Map<String,Object> params = new HashMap<>();
        		params.put("user",sel_user);
        		List<User> ulist = userMapper.selectMoneyByPage(params);
        		Integer maxId= userMapper.maxId(params);
        		int num = 1;
        		if(maxId!=null) num =maxId+1;
        		if(num>1){
        			User[] user = new User[num+1];
        			WReward[] wrd = new WReward[num+1];
        			WSettle[] wst = new WSettle[num+1];
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
        				user[id].setTag(0);
        			}
        			WSettle wst_sel = new WSettle();
        			wst_sel.setEndTime(st.getEndTime());
        			params.put("wst",wst_sel);
        			List<WSettle> wslist = wstMapper.selectListWithParam(params);
        			for(int i=0;i<wslist.size();i++){
        				int id = wslist.get(i).getuId();
        				if(wst[id]==null) wst[id] = new WSettle();
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
    					wst[id].setTotalNumReal(wslist.get(i).getTotalNumReal());
    					wst[id].setTag(0);
        			}
        			params.put("tableName",table_name_s);
        			List<WSettle> wlist = wstMapper.selectAll(params);
        			for(int i=0;i<wlist.size();i++){
        				int id = wlist.get(i).getuId();
        				if(user[id].getRankManage()<wlist.get(i).getRank()){
        					user[id].setRankManage(wlist.get(i).getRank());
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
        					if(wlist.get(i).getNewNumReal()>0){
        						wst[id].setTotalNumReal(wlist.get(i).getTotalNumReal()+ wlist.get(i).getNewNumReal());
        						wst[id].setTag(1);
        					}
        					if(ArithUtil.sub(wlist.get(i).getAcpv(),wst[id].getAcpv())>0||ArithUtil.sub(wlist.get(i).getAcpv(),wst[id].getAcpv())<0){
        						wst[id].setAcpv(wlist.get(i).getAcpv());
        						wst[id].setTag(1);
        					}
        					if(ArithUtil.sub(wlist.get(i).getBcpv(),wst[id].getBcpv())>0||ArithUtil.sub(wlist.get(i).getBcpv(),wst[id].getBcpv())<0){
        						wst[id].setBcpv(wlist.get(i).getBcpv());
        						wst[id].setTag(1);
        					}
        				
        				}
        			}
        				
        				for(int i=0;i<user.length;i++){
        					if(user[i]!=null&&user[i].getTag()==1){
        						User user1 = new User();
        						user1.setId(user[i].getId());
        						user1.setRankJoinOld(user[i].getRankJoinOld());
        						user1.setRankJoinTag(user[i].getRankJoinTag());
        						user1.setRankManage(user[i].getRankManage());
        						userMapper.updateUser(user1);
        					}
        				}
        				
        				for(int i=0;i<wst.length;i++){
        					if(wst[i]!=null&&wst[i].getTag()==1){
        						wstMapper.update(wst[i]);
        					}
        				}
        			wrdMapper.updateStateOfWReward(table_name_r, 1);
        			st.setState(3);
        			stMapper.update(st);
        			Settle st1 = stMapper.selectByWeekTag(0);
        			st1.setEndTime(st.getEndTime());
        			stMapper.update(st1);
        			sqlSession.commit();
					msg = "第"+weekTag+"周结算确认成功，请继续点击奖金结算进行操作。";

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
    
    public String importExcel(List<WRewardExtreme> wlist,String adminName){
    	String msg = "";
    	try{
    		Timestamp date = new Timestamp(new Date().getTime());
    		List<Integer> ilist = new ArrayList<Integer>();
    		for(int i=0;i<wlist.size();i++){
    			wlist.get(i).setState(1);
    			User user1 = userMapper.selectByUserId(wlist.get(i).getUserId());
    			if(user1!=null){
    					wlist.get(i).setUserName(user1.getUserName());
    					wlist.get(i).setUid(user1.getId());
    					wlist.get(i).setEntryTime(date);
    					wlist.get(i).setPayTag(user1.getPayTag());
    			}else{
    				ilist.add(i);
    			}
    		}
    		for(int i=0;i<ilist.size();i++){
    			wlist.remove(ilist.get(i));
    		}
    		int n2 =1;
    		int error2=0;
    		Map<String,Object> params = new HashMap<>();
    		List<WRewardExtreme> wstlist = new ArrayList<WRewardExtreme>();
    		for(int i=0;i<wlist.size();i++){
    				if(n2%800==0){
    					wstlist.add(wlist.get(i));
    					params.put("tableName","wreward_extreme");
    					params.put("list", wstlist);
    					Integer up1 = wrdExtremeMapper.insertAll(params);
    					if(up1==null || up1==0)  error2++;
    					wstlist = new ArrayList<WRewardExtreme>();
    				}else if(i<wlist.size()-1){
    					wstlist.add(wlist.get(i));
					}else{
						wstlist.add(wlist.get(i));
						params.put("tableName","wreward_extreme");
	            		params.put("list", wstlist);
	            		Integer wst_insert = wrdExtremeMapper.insertAll(params);
	            		if(wst_insert==null || wst_insert==0)  error2++;
	            		wstlist =null;
					}
    					n2++;
    		}
    			if(error2==0){
	    			msg= "至尊补贴数据导入成功，请查看。";
	    			
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
    	
	    
   
}
