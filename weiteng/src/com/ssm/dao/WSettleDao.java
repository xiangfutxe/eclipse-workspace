package com.ssm.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.JoinInfoMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.SettleMapper;
import com.ssm.mapper.UserLogMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.Order;
import com.ssm.pojo.Param;
import com.ssm.pojo.Settle;
import com.ssm.pojo.User;
import com.ssm.pojo.UserLog;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WSettle;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.StringUtil;

public class WSettleDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	WSettleMapper wstMapper = sqlSession.getMapper(WSettleMapper.class);
	WRewardMapper wrdMapper = sqlSession.getMapper(WRewardMapper.class);
	ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);
	UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);
	OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
	  SettleMapper stMapper = sqlSession.getMapper(SettleMapper.class);
	   JoinInfoMapper jfMapper = sqlSession.getMapper(JoinInfoMapper.class);  
		  UserLogMapper userLogMapper = sqlSession.getMapper(UserLogMapper.class);

    public Pager findByPage(WSettle wst,String tableName,Pager pager){
    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("wst",wst);
			params.put("tableName", tableName);
			int recordCount = wstMapper.count(params);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<WSettle> wsts = wstMapper.selectByPage(params);
			sqlSession.commit();
			pager.setResultList(wsts);
    	} finally {
			sqlSession.close();
    	}
    	return pager;
	}
    
    public List<WSettle> findByList(WSettle wst,String tableName){
    	List<WSettle> wsts = null;
    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("wst",wst);
			params.put("tableName", tableName);
			wsts = wstMapper.selectByPage(params);
			sqlSession.commit();
			
    	} finally {
			sqlSession.close();
    	}
    	return wsts;
	}
    
    public List<WSettle> findWstByList(WSettle wst){
    	List<WSettle> wsts = null;
    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("wst",wst);
			wsts = wstMapper.selectListWithParam(params);
			sqlSession.commit();
			
    	} finally {
			sqlSession.close();
    	}
    	return wsts;
	}
    
    public WSettle findByUserId(String userId,String tableName,int weekTag){
    	WSettle wst = new WSettle();
    	try{
    		wst = wstMapper.findByUserId(userId, tableName);
    		User user = userMapper.selectByUserId(userId);
    		if(user!=null){
	    		Timestamp date = new Timestamp(new Date().getTime());
	    		UserLog userlog = new UserLog();
	    		userlog.setUid(user.getId());
    			userlog.setUserId(user.getUserId());
    			userlog.setUserName(user.getUserName());
    			userlog.setType(ConstantsLog.USERLOGTYPE_7);
    			userlog.setContents("第"+weekTag+"期历史业绩查询。");
    			userlog.setEntryTime(date);
    			if(userLogMapper.save(userlog)!=null){
    				sqlSession.commit();
    			}
    		}
    	} finally {
			sqlSession.close();
    	}
    	return wst;
	}
    
    public String UpdateWSettle(){
    	String str ="";
    	int error=0;
    	int er=0;
    	try{
    		User sel_user = new User();
    		Map<String,Object> params = new HashMap<>();
    		params.put("user",sel_user);
    		List<User> ulist = userMapper.selectMoneyByPage(params);
    		Integer maxId= userMapper.maxId(params);
    		int num = 1;
    		if(maxId!=null) num =maxId+1;
    		if(num>1){
    			User[] user = new User[num+1];
    			WSettle[] wst = new WSettle[num+1];
    			WSettle[] wst12 = new WSettle[num+1];
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
    			params.put("wst",wst_sel);
    			List<WSettle> wslist = wstMapper.selectListWithParam(params);
    			for(int i=0;i<wslist.size();i++){
    				int id = wslist.get(i).getuId();
    				if(wst[id]==null) wst[id] = new WSettle();
    				wst[id].setId(wslist.get(i).getId());
    				wst[id].setTotalPerformance((double) 0);
    				wst[id].setTotalPrice((double) 0);
    				wst[id].setNewPerformance((double) 0);
    				wst[id].setNewPrice((double) 0);
    				wst[id].setTotalNum(0);
    				wst[id].setNewNum(0);
					wst[id].setAtprice((double) 0);
					wst[id].setBtprice((double) 0);
					wst[id].setAcpv((double) 0);
					wst[id].setBcpv((double) 0);
					wst[id].setAprice((double) 0);
					wst[id].setBprice((double) 0);
					wst[id].setApv((double) 0);
					wst[id].setBpv((double) 0);
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
					wst[id].setJoinPriceTal((double) 0);
					wst[id].setJoinPvTal((double) 0);
					wst[id].setTotalNumReal(0);
					wst[id].setNewNumReal(0);
					wst[id].setTag(0);
    			}
    			params.put("tableName","wsettle_13");
    			List<WSettle> wlist = wstMapper.selectAll(params);
    			for(int i=0;i<wlist.size();i++){
    				int id = wlist.get(i).getuId();
    				wst[id].setAcpv(wlist.get(i).getAcpv());
    				wst[id].setBcpv(wlist.get(i).getBcpv());
    				wst[id].setTotalPerformance(ArithUtil.add(wlist.get(i).getTotalPerformance(), wlist.get(i).getNewPerformance()));
    				wst[id].setTotalPrice(ArithUtil.add(wlist.get(i).getTotalPrice(),wlist.get(i).getNewPrice()));
    				wst[id].setNewPerformance(wlist.get(i).getNewPerformance());
    				wst[id].setNewPrice(wlist.get(i).getNewPrice());
    				wst[id].setTotalNum(wlist.get(i).getTotalNum()+wlist.get(i).getNewNum());
    				wst[id].setNewNum(wlist.get(i).getNewNum());
    				wst[id].setJoinPriceTal(wlist.get(i).getJoinPriceTal());
					wst[id].setJoinPvTal(wlist.get(i).getJoinPvTal());
					wst[id].setJoinPriceNew(wlist.get(i).getJoinPriceNew());
					wst[id].setJoinPvNew(wlist.get(i).getJoinPvNew());
					wst[id].setTotalNumReal(wlist.get(i).getTotalNumReal()+wlist.get(i).getNewNumReal());
					wst[id].setNewNumReal(wlist.get(i).getNewNumReal());
					wst[id].setAtprice(ArithUtil.add(wlist.get(i).getAtprice(),wlist.get(i).getAprice()));
					wst[id].setBtprice(ArithUtil.add(wlist.get(i).getBtprice(),wlist.get(i).getBprice()));
					wst[id].setAprice(wlist.get(i).getAprice());
					wst[id].setBprice(wlist.get(i).getBprice());
					wst[id].setApv(wlist.get(i).getApv());
					wst[id].setBpv(wlist.get(i).getBpv());
					wst[id].setAtpv(ArithUtil.add(wlist.get(i).getAtpv(),wlist.get(i).getApv()));
					wst[id].setBtpv(ArithUtil.add(wlist.get(i).getBtpv(),wlist.get(i).getBpv()));
					wst[id].setRtpv(ArithUtil.add(wlist.get(i).getRtpv(),wlist.get(i).getRpv()));
					wst[id].setRtprice(ArithUtil.add(wlist.get(i).getRtprice(),wlist.get(i).getRprice()));
					wst[id].setRtpv1(ArithUtil.add(wlist.get(i).getRtpv1(),wlist.get(i).getRpv1()));
					wst[id].setRtprice1(ArithUtil.add(wlist.get(i).getRtprice1(),wlist.get(i).getRprice1()));
					wst[id].setRpv(wlist.get(i).getRpv());
					wst[id].setRprice(wlist.get(i).getRprice());
					wst[id].setRpv1(wlist.get(i).getRpv1());
					wst[id].setRprice1(wlist.get(i).getRprice1());
					
    			}
    			for(int t=14;t<=18;t++){
    			params.put("tableName","wsettle_"+t);
    			List<WSettle> wlist1 = wstMapper.selectAll(params);
    			for(int i=0;i<wlist1.size();i++){
    				int id = wlist1.get(i).getuId();
    				
        				WSettle sel_wst  = new WSettle();
    					sel_wst.setTotalPerformance(wst[id].getTotalPerformance());
    					sel_wst.setTotalPrice(wst[id].getTotalPrice());
    					sel_wst.setJoinPriceTal(wst[id].getJoinPriceTal());
    					sel_wst.setJoinPvTal(wst[id].getJoinPvTal());
    					sel_wst.setTotalNumReal(wst[id].getTotalNumReal());
    					sel_wst.setTotalNum(wst[id].getTotalNum());
    					sel_wst.setAtpv(wst[id].getAtpv());
    					sel_wst.setBtpv(wst[id].getBtpv());
    					sel_wst.setRtpv(wst[id].getRtpv());
    					sel_wst.setRtpv1(wst[id].getRtpv1());
    					sel_wst.setAtprice(wst[id].getAtprice());
    					sel_wst.setBtprice(wst[id].getBtprice());
    					sel_wst.setRtprice(wst[id].getRtprice());
    					sel_wst.setRtprice1(wst[id].getRtprice1());
        				sel_wst.setId(wlist1.get(i).getId());
        				params.put("wst",sel_wst);
        				params.put("tableName","wsettle_"+t);
        				if(wstMapper.updateAll(params)==null)
        					error++;
        				
    				wst[id].setTotalPerformance(ArithUtil.add(wst[id].getTotalPerformance(),wlist1.get(i).getNewPerformance()));
    				wst[id].setTotalPrice(ArithUtil.add(wst[id].getTotalPrice(),wlist1.get(i).getNewPrice()));
    				wst[id].setAtpv(ArithUtil.add(wst[id].getAtpv(),wlist1.get(i).getApv()));
    				wst[id].setBtpv(ArithUtil.add(wst[id].getBtpv(),wlist1.get(i).getBpv()));
    				wst[id].setRtpv(ArithUtil.add(wst[id].getRtpv(),wlist1.get(i).getRpv()));
    				wst[id].setRtpv1(ArithUtil.add(wst[id].getRtpv1(),wlist1.get(i).getRpv1()));
    				wst[id].setAtprice(ArithUtil.add(wst[id].getAtprice(),wlist1.get(i).getAprice()));
    				wst[id].setBtprice(ArithUtil.add(wst[id].getBtprice(),wlist1.get(i).getBprice()));
    				wst[id].setRtprice(ArithUtil.add(wst[id].getRtprice(),wlist1.get(i).getRprice()));
    				wst[id].setRtprice1(ArithUtil.add(wst[id].getRtprice1(),wlist1.get(i).getRprice1()));
    				wst[id].setAcpv(wlist1.get(i).getAcpv());
    				wst[id].setBcpv(wlist1.get(i).getBcpv());
    				wst[id].setJoinPriceTal(ArithUtil.add(wst[id].getJoinPriceTal(),wlist1.get(i).getJoinPriceTal()));
    				wst[id].setJoinPvTal(ArithUtil.add(wst[id].getJoinPvTal(),wlist1.get(i).getJoinPvTal()));
    				wst[id].setTotalNumReal(wst[id].getTotalNumReal()+wlist1.get(i).getNewNumReal());
    				wst[id].setTotalNum(wst[id].getTotalNum()+wlist1.get(i).getNewNum());
    				
    			}
    			}
    			params.put("tableName","wsettle");
    			for(int i=0;i<wst.length;i++){
    				if(wst[i]!=null){
    					WSettle sel_wst  = new WSettle();
    					sel_wst.setTotalPerformance(wst[i].getTotalPerformance());
    					sel_wst.setTotalPrice(wst[i].getTotalPrice());
    					sel_wst.setJoinPriceTal(wst[i].getJoinPriceTal());
    					sel_wst.setJoinPvTal(wst[i].getJoinPvTal());
    					//sel_wst.setTotalNumReal(wst[i].getTotalNumReal());
    					//sel_wst.setTotalNum(wst[i].getTotalNum());
    					sel_wst.setAtpv(wst[i].getAtpv());
    					sel_wst.setBtpv(wst[i].getBtpv());
    					sel_wst.setRtpv(wst[i].getRtpv());
    					sel_wst.setRtpv1(wst[i].getRtpv1());
    					sel_wst.setAtprice(wst[i].getAtprice());
    					sel_wst.setBtprice(wst[i].getBtprice());
    					sel_wst.setRtprice(wst[i].getRtprice());
    					sel_wst.setRtprice1(wst[i].getRtprice1());
    					sel_wst.setAcpv(wst[i].getAcpv());
    					sel_wst.setBcpv(wst[i].getBcpv());
        				sel_wst.setId(wst[i].getId());
        				params.put("wst",sel_wst);
        				if(wstMapper.updateAll(params)==null)
        					error++;
    				}
    			}
    			if(error==0){
        			str="更新成功";
            		sqlSession.commit();
        		}else{
        			str="更新失败有误";
        		}
    		}
    	
    	} finally {
			sqlSession.close();
    	}
    	return str;
	}

    public String checkWSettle(){
    	String str ="";
    	int error=0;
    	int er=0;
    	double duofa=0;
    	double shaofa=0;
    	try{
    		Param p2_1 = paramMapper.selectByName("拓展奖比例");
    		Param p2_2 = paramMapper.selectByName("拓展奖封顶额");
    		User sel_user = new User();
    		Map<String,Object> params = new HashMap<>();
    		params.put("user",sel_user);
    		List<User> ulist = userMapper.selectMoneyByPage(params);
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
    			for(int k=14;k<=17;k++){
    			
    			params.put("tableName", "wsettle_"+k);
    			List<WSettle> wslist = wstMapper.selectAll(params);
    			WSettle[] wst = new WSettle[num+1];
    			WSettle[] wst1 = new WSettle[num+1];
    			WReward[] wrd = new WReward[num+1];
    			
    			for(int i=0;i<wslist.size();i++){
    				int id = wslist.get(i).getuId();
    				if(wst[id]==null) wst[id] = new WSettle();
    				wst[id].setId(wslist.get(i).getId());
    				wst[id].setUserId(wslist.get(i).getUserId());
    				wst[id].setTotalPerformance(wslist.get(i).getTotalPerformance());
    				wst[id].setTotalPrice(wslist.get(i).getTotalPrice());
    				wst[id].setTotalNum(wslist.get(i).getTotalNum());
					wst[id].setAtprice(wslist.get(i).getAtprice());
					wst[id].setBtprice(wslist.get(i).getBtprice());
					wst[id].setApv(wslist.get(i).getApv());
					wst[id].setBpv(wslist.get(i).getBpv());
					wst[id].setAcpv(wslist.get(i).getAcpv());
					wst[id].setBcpv(wslist.get(i).getBcpv());
					wst[id].setAtpv(wslist.get(i).getAtpv());
					wst[id].setBtpv(wslist.get(i).getBtpv());
					wst[id].setRtpv(wslist.get(i).getRtpv());
					wst[id].setRtprice(wslist.get(i).getRtprice());
					wst[id].setRtpv1(wslist.get(i).getRtpv1());
					wst[id].setRankJoin(wslist.get(i).getRankJoin());
					wst[id].setRtprice1(wslist.get(i).getRtprice1());
					wst[id].setJoinPriceTal(wslist.get(i).getJoinPriceTal());
					wst[id].setJoinPvTal(wslist.get(i).getJoinPvTal());
					wst[id].setTotalNumReal(wslist.get(i).getTotalNumReal());
					wst[id].setTag(0);
    			}
    			params.put("tableName", "wsettle_"+(k-1));
    			List<WSettle> wslist1 = wstMapper.selectAll(params);
    			for(int i=0;i<wslist1.size();i++){
    				int id = wslist1.get(i).getuId();
    				if(wst1[id]==null) wst1[id] = new WSettle();
    				wst1[id].setId(wslist1.get(i).getId());
					wst1[id].setAcpv(wslist1.get(i).getAcpv());
					wst1[id].setBcpv(wslist1.get(i).getBcpv());
					wst1[id].setTag(0);
    			}
    			Map<String,Object> param1 = new HashMap<>();
    			param1.put("tableName","wreward_"+k);
    			List<WReward> wrdlist = wrdMapper.selectByList(param1);
    			for(int i=0;i<wrdlist.size();i++){
    				int id = wrdlist.get(i).getuId();
    				if(wrd[id]==null) wrd[id] = new WReward();
    				wrd[id].setId(wrdlist.get(i).getId());
    				wrd[id].setAmount_2(wrdlist.get(i).getAmount_3());
    			}
    			
    			for(int i=0;i<wst.length;i++){
    				double award=0;
    				if(wst[i]!=null){
    					double money = 0;
        				double b =0;
        				double max = 0;
        				double p = 0;
        				if(k==14) p=0.92;
        				else if(k==15) p=0.82;
        				else if(k==16) p=0.51;
        				else if(k==17) p=0.61;
        				if(wst[i].getRankJoin()==1) {
        					b = p2_1.getAmount_1();
        					max = p2_2.getAmount_1();
        				}else if(wst[i].getRankJoin()==2) {
        					b = p2_1.getAmount_2();
        					max = p2_2.getAmount_2();
        				}else if(wst[i].getRankJoin()==3) {
        					b = p2_1.getAmount_3();
        					max = p2_2.getAmount_3();
        				}else if(wst[i].getRankJoin()==4) {
        					b = p2_1.getAmount_4();
        					max = p2_2.getAmount_4();
        				}else if(wst[i].getRankJoin()==5) {
        					b = p2_1.getAmount_5();
        					max = p2_2.getAmount_5();
        				}
    					double apv = wst[i].getApv();
    					double bpv = wst[i].getBpv();
    					if(wst1[i]!=null){
    						if(wst1[i].getAcpv()==null)System.out.println("userId:"+wst[i].getUserId()+";");
    						apv = ArithUtil.add(apv, wst1[i].getAcpv());
    						bpv = ArithUtil.add(bpv, wst1[i].getBcpv());
    					}
    					if(wst[i].getUserId().equals("AA000000")) System.out.println("apv:"+apv+";bpv:"+bpv+";");
        			if(ArithUtil.sub(apv, bpv)>=0){
	        			award = ArithUtil.mul(ArithUtil.mul(bpv, b),p);
    				}else{
    					award = ArithUtil.mul(ArithUtil.mul(apv, b),p);
    					
    				}
        			if(ArithUtil.sub(award, max)>0) award= max;
        			if(ArithUtil.sub(award, wrd[i].getAmount_2())>0||ArithUtil.sub(award, wrd[i].getAmount_2())<0){
        				str+="第"+k+"周"+wst[i].getUserId()+"的拓展奖实际金额为："+award+";系统金额："+wrd[i].getAmount_2()+"；差额："+ArithUtil.sub(award, wrd[i].getAmount_2())+";<br>";
        				error++;
        				if(ArithUtil.sub(award, wrd[i].getAmount_2())>0) shaofa=ArithUtil.add(shaofa,ArithUtil.sub(award, wrd[i].getAmount_2()));
        				if(ArithUtil.sub(award, wrd[i].getAmount_2())<0) duofa=ArithUtil.add(duofa,ArithUtil.sub(wrd[i].getAmount_2(),award));
        			}
    			}
    			}
    			}
    		}
    		str+="<br>合计："+error+"笔；少发："+shaofa+";多发："+duofa+";";
    	} finally {
			sqlSession.close();
    	}
    	return str;
	}
    
    public List<WReward> checkWSettleList(){
    	List<WReward> result = new ArrayList<WReward>();
    	double duofa=0;
    	double shaofa=0;
    	double duofa1=0;
    	double shaofa1=0;
    	try{
    		Param p2_1 = paramMapper.selectByName("拓展奖比例");
    		Param p2_2 = paramMapper.selectByName("拓展奖封顶额");
    		User sel_user = new User();
    		Map<String,Object> params = new HashMap<>();
    		params.put("user",sel_user);
    		List<User> ulist = userMapper.selectMoneyByPage(params);
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
    			for(int k=14;k<=17;k++){
    			
    			params.put("tableName", "wsettle_"+k);
    			List<WSettle> wslist = wstMapper.selectAll(params);
    			WSettle[] wst = new WSettle[num+1];
    			WSettle[] wst1 = new WSettle[num+1];
    			WReward[] wrd = new WReward[num+1];
    			
    			for(int i=0;i<wslist.size();i++){
    				int id = wslist.get(i).getuId();
    				if(wst[id]==null) wst[id] = new WSettle();
    				wst[id].setId(wslist.get(i).getId());
    				wst[id].setUserId(wslist.get(i).getUserId());
    				wst[id].setTotalPerformance(wslist.get(i).getTotalPerformance());
    				wst[id].setTotalPrice(wslist.get(i).getTotalPrice());
    				wst[id].setTotalNum(wslist.get(i).getTotalNum());
					wst[id].setAtprice(wslist.get(i).getAtprice());
					wst[id].setBtprice(wslist.get(i).getBtprice());
					wst[id].setApv(wslist.get(i).getApv());
					wst[id].setBpv(wslist.get(i).getBpv());
					wst[id].setAcpv(wslist.get(i).getAcpv());
					wst[id].setBcpv(wslist.get(i).getBcpv());
					wst[id].setAtpv(wslist.get(i).getAtpv());
					wst[id].setBtpv(wslist.get(i).getBtpv());
					wst[id].setRtpv(wslist.get(i).getRtpv());
					wst[id].setRtprice(wslist.get(i).getRtprice());
					wst[id].setRtpv1(wslist.get(i).getRtpv1());
					wst[id].setRankJoin(wslist.get(i).getRankJoin());
					wst[id].setRtprice1(wslist.get(i).getRtprice1());
					wst[id].setJoinPriceTal(wslist.get(i).getJoinPriceTal());
					wst[id].setJoinPvTal(wslist.get(i).getJoinPvTal());
					wst[id].setTotalNumReal(wslist.get(i).getTotalNumReal());
					wst[id].setTag(0);
    			}
    			params.put("tableName", "wsettle_"+(k-1));
    			List<WSettle> wslist1 = wstMapper.selectAll(params);
    			for(int i=0;i<wslist1.size();i++){
    				int id = wslist1.get(i).getuId();
    				if(wst1[id]==null) wst1[id] = new WSettle();
    				wst1[id].setId(wslist1.get(i).getId());
					wst1[id].setAcpv(wslist1.get(i).getAcpv());
					wst1[id].setBcpv(wslist1.get(i).getBcpv());
					wst1[id].setTag(0);
    			}
    			Map<String,Object> param1 = new HashMap<>();
    			param1.put("tableName","wreward_"+k);
    			List<WReward> wrdlist = wrdMapper.selectByList(param1);
    			for(int i=0;i<wrdlist.size();i++){
    				int id = wrdlist.get(i).getuId();
    				if(wrd[id]==null) wrd[id] = new WReward();
    				wrd[id].setId(wrdlist.get(i).getId());
    				wrd[id].setAmount_2(wrdlist.get(i).getAmount_2());
    			}
    			
    			for(int i=0;i<wst.length;i++){
    				double award=0;
    				if(wst[i]!=null){
    					double money = 0;
        				double b =0;
        				double max = 0;
        				double p = 0;
        				if(k==14) p=0.92;
        				else if(k==15) p=0.82;
        				else if(k==16) p=0.51;
        				else if(k==17) p=0.61;
        				if(wst[i].getRankJoin()==1) {
        					b = p2_1.getAmount_1();
        					max = p2_2.getAmount_1();
        				}else if(wst[i].getRankJoin()==2) {
        					b = p2_1.getAmount_2();
        					max = p2_2.getAmount_2();
        				}else if(wst[i].getRankJoin()==3) {
        					b = p2_1.getAmount_3();
        					max = p2_2.getAmount_3();
        				}else if(wst[i].getRankJoin()==4) {
        					b = p2_1.getAmount_4();
        					max = p2_2.getAmount_4();
        				}else if(wst[i].getRankJoin()==5) {
        					b = p2_1.getAmount_5();
        					max = p2_2.getAmount_5();
        				}
    					double apv = wst[i].getApv();
    					double bpv = wst[i].getBpv();
    					if(wst1[i]!=null){
    						if(wst1[i].getAcpv()==null)System.out.println("userId:"+wst[i].getUserId()+";");
    						apv = ArithUtil.add(apv, wst1[i].getAcpv());
    						bpv = ArithUtil.add(bpv, wst1[i].getBcpv());
    					}
    					if(wst[i].getUserId().equals("AA000000")) System.out.println("apv:"+apv+";bpv:"+bpv+";");
        			double actual_ward = 0;
    				if(ArithUtil.sub(apv, bpv)>=0){
    					actual_ward = ArithUtil.mul(bpv, b);
	        			award = ArithUtil.mul(ArithUtil.mul(bpv, b),p);
    				}else{
    					actual_ward = ArithUtil.mul(apv, b);
    					award = ArithUtil.mul(ArithUtil.mul(apv, b),p);
    					
    				}
        			if(ArithUtil.sub(award, max)>0) award= max;
        			if(ArithUtil.sub(actual_ward, max)>0) actual_ward= max;
        			if(ArithUtil.sub(award, wrd[i].getAmount_2())>0||ArithUtil.sub(award, wrd[i].getAmount_2())<0){
        				if(ArithUtil.sub(award, wrd[i].getAmount_2())>0){
        					shaofa=ArithUtil.add(shaofa,ArithUtil.sub(award, wrd[i].getAmount_2()));
        				}
        				if(ArithUtil.sub(award, wrd[i].getAmount_2())<0){
        					duofa=ArithUtil.add(duofa,ArithUtil.sub(wrd[i].getAmount_2(),award));
        				}
        				
        				if(ArithUtil.sub(actual_ward, wrd[i].getAmount_2())>0){
        					shaofa1=ArithUtil.add(shaofa1,ArithUtil.sub(actual_ward, wrd[i].getAmount_2()));
        				}
        				if(ArithUtil.sub(actual_ward, wrd[i].getAmount_2())<0){
        					duofa1=ArithUtil.add(duofa1,ArithUtil.sub(wrd[i].getAmount_2(),actual_ward));
        				}
        				WReward w = new WReward();
        				w.setWeekTag(k);
        				w.setUserId(wst[i].getUserId());
        				w.setUserName(user[i].getUserName());
        				w.setAmount(award);
        				w.setAmount_1(wrd[i].getAmount_2());
        				w.setAmount_2(ArithUtil.sub(award, wrd[i].getAmount_2()));
        				w.setAmount_3(actual_ward);
        				w.setAmount_4(ArithUtil.sub(actual_ward, wrd[i].getAmount_2()));
        				result.add(w);
        			}
    			}
    			}
    			}
    		}
    		WReward w = new WReward();
			w.setWeekTag(0);
			w.setUserId("合计");
			w.setUserName("");
			w.setAmount((double) 0);
			w.setAmount_1(shaofa);
			w.setAmount_2(duofa);
			w.setAmount_3(shaofa1);
			w.setAmount_4(duofa1);
			result.add(w);
    	} finally {
			sqlSession.close();
    	}
    	return result;
	}
    
    public List<WReward> checkWReward_8(){
    	List<WReward> result = new ArrayList<WReward>();
    	double duofa=0;
    	double shaofa=0;
    	double duofa1=0;
    	double shaofa1=0;
    	try{
    		Param p6_1 = paramMapper.selectByName("见点奖比例");
    		User sel_user = new User();
    		Map<String,Object> params = new HashMap<>();
    		params.put("user",sel_user);
    		List<User> ulist = userMapper.selectMoneyByPage(params);
    		Integer maxId= userMapper.maxId(params)	;
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
    				user[id].setJoinPrice((double) 0);
    				user[id].setJoinPv((double) 0);
    				user[id].setTotalPrice((double) 0);
    				user[id].setTag(0);
    			}
    			for(int k=13;k<=18;k++){
    				double p = 0;
    				if(k==13) p=0.73;
    				else if(k==14)p=0.92;
    				else if(k==15) p =0.82;
    				else if(k==16) p =0.5;
    				else if(k==17) p=0.62;
    				else if(k==18) p=1;
    	    		Settle st = stMapper.selectByWeekTagForUpdate(k);
    	    		params.put("tableName", "wsettle_"+k);
	    			List<WSettle> wslist = wstMapper.selectAll(params);
	    			WSettle[] wst = new WSettle[num+1];
	    			WReward[] wrd = new WReward[num+1];
	    			for(int i=0;i<wslist.size();i++){
	    				int id = wslist.get(i).getuId();
	    				if(wst[id]==null) wst[id] = new WSettle();
	    				wst[id].setId(wslist.get(i).getId());
	    				wst[id].setUserId(wslist.get(i).getUserId());
	    				wst[id].setTotalPerformance(wslist.get(i).getTotalPerformance());
	    				wst[id].setTotalPrice(wslist.get(i).getTotalPrice());
	    				wst[id].setTotalNum(wslist.get(i).getTotalNum());
						wst[id].setAtprice(wslist.get(i).getAtprice());
						wst[id].setBtprice(wslist.get(i).getBtprice());
						wst[id].setApv(wslist.get(i).getApv());
						wst[id].setBpv(wslist.get(i).getBpv());
						wst[id].setAcpv(wslist.get(i).getAcpv());
						wst[id].setBcpv(wslist.get(i).getBcpv());
						wst[id].setAtpv(wslist.get(i).getAtpv());
						wst[id].setBtpv(wslist.get(i).getBtpv());
						wst[id].setRtpv(wslist.get(i).getRtpv());
						wst[id].setRtprice(wslist.get(i).getRtprice());
						wst[id].setRtpv1(wslist.get(i).getRtpv1());
						wst[id].setRankJoin(wslist.get(i).getRankJoin());
						wst[id].setRtprice1(wslist.get(i).getRtprice1());
						wst[id].setJoinPriceTal(wslist.get(i).getJoinPriceTal());
						wst[id].setJoinPvTal(wslist.get(i).getJoinPvTal());
						wst[id].setTotalNumReal(wslist.get(i).getTotalNumReal());
						wst[id].setTag(0);
	    			}
	    			Map<String,Object> param1 = new HashMap<>();
	    			param1.put("tableName","wreward_"+k);
	    			List<WReward> wrdlist = wrdMapper.selectByList(param1);
	    			for(int i=0;i<wrdlist.size();i++){
	    				int id = wrdlist.get(i).getuId();
	    				if(wrd[id]==null) wrd[id] = new WReward();
	    				wrd[id].setId(wrdlist.get(i).getId());
	    				wrd[id].setAmount_8(wrdlist.get(i).getAmount_8());
	    				wrd[id].setAmount((double) 0);
	    			}
	    			JoinInfo sel_jf = new JoinInfo();
            		sel_jf.setStartTime(st.getStartTime());
            		sel_jf.setEndTime(st.getEndTime());
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
    					wst[uid].setJoinPvTal(ArithUtil.add(wst[uid].getJoinPvTal(),pv));
    					wst[uid].setJoinPriceTal(ArithUtil.add(wst[uid].getJoinPvTal(),price));
    					user[uid].setJoinPrice(ArithUtil.add(user[uid].getJoinPrice(),price));
    					user[uid].setJoinPv(ArithUtil.add(user[uid].getJoinPv(),pv));

            		}
            		for(int i=0;i<jlist.size();i++){
            			int id = jlist.get(i).getUid();
            				double pv = jlist.get(i).getPv();
            				if(user[id]!=null){
            					if(!StringUtil.notNull(user[id].getNode()).equals("")){
            						if(user[id].getEntryTime().getTime()-st.getStartTime().getTime()>=0){
            							String[] str = user[id].getNode().split(",");
            							for(int j=0;j<str.length;j++){
            								if(!str[j].equals("")){
            									int sid = Integer.valueOf(str[j]);
            									if(user[sid]!=null){
            										Timestamp val_time = new Timestamp(StringUtil.parseToDate("2018-01-08 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
            										if(user[sid].getEntryTime().getTime()-val_time.getTime()>0){
            										if(ArithUtil.sub(user[sid].getJoinPrice(),user[sid].getTotalPrice())>0){
            											double award = ArithUtil.mul(pv, p6_1.getAmount_1());
            											if(award>0){
            												if(ArithUtil.sub(ArithUtil.sub(user[sid].getJoinPrice(),user[sid].getTotalPrice()),award)<0){
            													award = ArithUtil.sub(user[sid].getJoinPrice(),user[sid].getTotalPrice());
            												}
            												user[sid].setTotalPrice(ArithUtil.add(user[sid].getTotalPrice(),award));
            												if(wrd[sid]!=null){
            													wrd[sid].setAmount(ArithUtil.add(wrd[sid].getAmount(),ArithUtil.mul(award,p)));
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
    			
    			
    			for(int i=0;i<wrd.length;i++){
    				if(wrd[i]!=null){
    					
    					if(ArithUtil.sub(wrd[i].getAmount(), wrd[i].getAmount_8())>0){
        				WReward w = new WReward();
        				w.setWeekTag(k);
        				w.setUserId(wst[i].getUserId());
        				w.setUserName(user[i].getUserName());
        				w.setAmount(wrd[i].getAmount());
        				w.setAmount_1(wrd[i].getAmount_8());
        				w.setAmount_2(ArithUtil.sub(wrd[i].getAmount(), wrd[i].getAmount_8()));
        				result.add(w);
        				shaofa =ArithUtil.add(shaofa,ArithUtil.sub(wrd[i].getAmount(), wrd[i].getAmount_8()));
        			}
    			}
    			}
    			}
    		}
    		WReward w = new WReward();
			w.setWeekTag(0);
			w.setUserId("合计");
			w.setUserName("");
			w.setAmount((double) 0);
			w.setAmount_1((double) 0);
			w.setAmount_2(shaofa);
			result.add(w);
    	} finally {
			sqlSession.close();
    	}
    	return result;
	}
    
    public String updateRankJoin(){
    	String msg ="";
    	int error=0;
    	try{
    		Param p2_1 = paramMapper.selectByName("拓展奖比例");
    		Param p2_2 = paramMapper.selectByName("拓展奖封顶额");
    		User sel_user = new User();
    		Map<String,Object> params = new HashMap<>();
    		params.put("user",sel_user);
    		List<User> ulist = userMapper.selectMoneyByPage(params);
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
    			params.put("tableName", "wsettle");
    			List<WSettle> wslist = wstMapper.selectAll(params);
    			WSettle[] wst = new WSettle[num+1];
    			for(int i=0;i<wslist.size();i++){
    				int id = wslist.get(i).getuId();
    				if(wst[id]==null) wst[id] = new WSettle();
    				wst[id].setId(wslist.get(i).getId());
    				wst[id].setTotalNum(wslist.get(i).getTotalNum());
					wst[id].setTotalNumReal(wslist.get(i).getTotalNumReal());
					wst[id].setTag(0);
    			}
    			Settle st1 = stMapper.selectByWeekTagForUpdate(13);
    			Settle st2 = stMapper.selectByWeekTagForUpdate(18);
    			
    			JoinInfo sel_jf = new JoinInfo();
        		sel_jf.setStartTime(st1.getStartTime());
        		sel_jf.setEndTime(st2.getEndTime());
        		sel_jf.setState(2);
        		params.put("joinInfo", sel_jf);
        		List<JoinInfo> jlist = jfMapper.selectAllByList(params);
        		for(int i=0;i<jlist.size();i++){
					int uid = jlist.get(i).getUid();
					int rid= jlist.get(i).getRid();
					double price = jlist.get(i).getPrice();
					int oldRankJoin = jlist.get(i).getOldRankJoin();
					int newRankJoin = jlist.get(i).getNewRankJoin();
					if(user[uid].getRankJoinOld()<newRankJoin){
						user[uid].setRankJoinOld(newRankJoin);
						user[uid].setTag(1);
					}
					if(oldRankJoin==0){
						wst[rid].setTotalNum(wst[rid].getTotalNum()+1);
						if(price>0)
							wst[rid].setTotalNumReal(wst[rid].getTotalNumReal()+1);
						wst[rid].setTag(1);
					}
				}
				
				for(int i=1;i<user.length;i++){
					if(user[i]!=null){
						if(user[i].getTag()==1){
							User update_user = new User();
							update_user.setRankJoinOld(user[i].getRankJoinOld());
							update_user.setId(user[i].getId());
							if(userMapper.updateUser(update_user)==null)
								error++;
						}
						if(wst[i].getTag()==1){
							WSettle update_wst = new WSettle();
							update_wst.setTotalNum(wst[i].getTotalNum());
							update_wst.setTotalNumReal(wst[i].getTotalNumReal());
							update_wst.setId(wst[i].getId());
							params.put("tableName", "wsettle");
							params.put("wst", update_wst);
							if(wstMapper.updateAll(params)==null)
								error++;
						}
					}
        		}
    		if(error>0){
    			msg = "更新有误!";
    			}else{
    				msg = "更新成功!";
    				sqlSession.commit();
    			}
    		}
    		
    	} finally {
			sqlSession.close();
    	}
    	return msg;
	}
    
    
    public List<WReward> checkWReward_2_18(){
    	List<WReward> result = new ArrayList<WReward>();
    	double duofa=0;
    	double shaofa=0;
    	double duofa1=0;
    	double shaofa1=0;
    	try{
    		Param p6_1 = paramMapper.selectByName("见点奖比例");
    		User sel_user = new User();
    		Map<String,Object> params = new HashMap<>();
    		params.put("user",sel_user);
    		List<User> ulist = userMapper.selectMoneyByPage(params);
    		Integer maxId= userMapper.maxId(params)	;
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
    				user[id].setJoinPrice((double) 0);
    				user[id].setJoinPv((double) 0);
    				user[id].setTotalPrice((double) 0);
    				user[id].setTag(0);
    			}
    			for(int k=13;k<=18;k++){
    				double p = 0;
    				if(k==13) p=0.73;
    				else if(k==14)p=0.92;
    				else if(k==15) p =0.82;
    				else if(k==16) p =0.5;
    				else if(k==17) p=0.62;
    				else if(k==18) p=1;
    	    		Settle st = stMapper.selectByWeekTagForUpdate(k);
    	    		params.put("tableName", "wsettle_"+k);
	    			List<WSettle> wslist = wstMapper.selectAll(params);
	    			WSettle[] wst = new WSettle[num+1];
	    			WReward[] wrd = new WReward[num+1];
	    			for(int i=0;i<wslist.size();i++){
	    				int id = wslist.get(i).getuId();
	    				if(wst[id]==null) wst[id] = new WSettle();
	    				wst[id].setId(wslist.get(i).getId());
	    				wst[id].setUserId(wslist.get(i).getUserId());
	    				wst[id].setTotalPerformance(wslist.get(i).getTotalPerformance());
	    				wst[id].setTotalPrice(wslist.get(i).getTotalPrice());
	    				wst[id].setTotalNum(wslist.get(i).getTotalNum());
						wst[id].setAtprice(wslist.get(i).getAtprice());
						wst[id].setBtprice(wslist.get(i).getBtprice());
						wst[id].setApv(wslist.get(i).getApv());
						wst[id].setBpv(wslist.get(i).getBpv());
						wst[id].setAcpv(wslist.get(i).getAcpv());
						wst[id].setBcpv(wslist.get(i).getBcpv());
						wst[id].setAtpv(wslist.get(i).getAtpv());
						wst[id].setBtpv(wslist.get(i).getBtpv());
						wst[id].setRtpv(wslist.get(i).getRtpv());
						wst[id].setRtprice(wslist.get(i).getRtprice());
						wst[id].setRtpv1(wslist.get(i).getRtpv1());
						wst[id].setRankJoin(wslist.get(i).getRankJoin());
						wst[id].setRtprice1(wslist.get(i).getRtprice1());
						wst[id].setJoinPriceTal(wslist.get(i).getJoinPriceTal());
						wst[id].setJoinPvTal(wslist.get(i).getJoinPvTal());
						wst[id].setTotalNumReal(wslist.get(i).getTotalNumReal());
						wst[id].setTag(0);
	    			}
	    			Map<String,Object> param1 = new HashMap<>();
	    			param1.put("tableName","wreward_"+k);
	    			List<WReward> wrdlist = wrdMapper.selectByList(param1);
	    			for(int i=0;i<wrdlist.size();i++){
	    				int id = wrdlist.get(i).getuId();
	    				if(wrd[id]==null) wrd[id] = new WReward();
	    				wrd[id].setId(wrdlist.get(i).getId());
	    				wrd[id].setAmount_8(wrdlist.get(i).getAmount_8());
	    				wrd[id].setAmount((double) 0);
	    			}
	    			JoinInfo sel_jf = new JoinInfo();
            		sel_jf.setStartTime(st.getStartTime());
            		sel_jf.setEndTime(st.getEndTime());
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
    					wst[uid].setJoinPvTal(ArithUtil.add(wst[uid].getJoinPvTal(),pv));
    					wst[uid].setJoinPriceTal(ArithUtil.add(wst[uid].getJoinPvTal(),price));
    					user[uid].setJoinPrice(ArithUtil.add(user[uid].getJoinPrice(),price));
    					user[uid].setJoinPv(ArithUtil.add(user[uid].getJoinPv(),pv));

            		}
            		for(int i=0;i<jlist.size();i++){
            			int id = jlist.get(i).getUid();
            				double pv = jlist.get(i).getPv();
            				if(user[id]!=null){
            					if(!StringUtil.notNull(user[id].getNode()).equals("")){
            						if(user[id].getEntryTime().getTime()-st.getStartTime().getTime()>=0){
            							String[] str = user[id].getNode().split(",");
            							for(int j=0;j<str.length;j++){
            								if(!str[j].equals("")){
            									int sid = Integer.valueOf(str[j]);
            									if(user[sid]!=null){
            										Timestamp val_time = new Timestamp(StringUtil.parseToDate("2018-01-08 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
            										if(user[sid].getEntryTime().getTime()-val_time.getTime()>0){
            										if(ArithUtil.sub(user[sid].getJoinPrice(),user[sid].getTotalPrice())>0){
            											double award = ArithUtil.mul(pv, p6_1.getAmount_1());
            											if(award>0){
            												if(ArithUtil.sub(ArithUtil.sub(user[sid].getJoinPrice(),user[sid].getTotalPrice()),award)<0){
            													award = ArithUtil.sub(user[sid].getJoinPrice(),user[sid].getTotalPrice());
            												}
            												user[sid].setTotalPrice(ArithUtil.add(user[sid].getTotalPrice(),award));
            												if(wrd[sid]!=null){
            													wrd[sid].setAmount(ArithUtil.add(wrd[sid].getAmount(),ArithUtil.mul(award,p)));
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
    			
    			
    			for(int i=0;i<wrd.length;i++){
    				if(wrd[i]!=null){
    					
    					if(ArithUtil.sub(wrd[i].getAmount(), wrd[i].getAmount_8())>0){
        				WReward w = new WReward();
        				w.setWeekTag(k);
        				w.setUserId(wst[i].getUserId());
        				w.setUserName(user[i].getUserName());
        				w.setAmount(wrd[i].getAmount());
        				w.setAmount_1(wrd[i].getAmount_8());
        				w.setAmount_2(ArithUtil.sub(wrd[i].getAmount(), wrd[i].getAmount_8()));
        				result.add(w);
        				shaofa =ArithUtil.add(shaofa,ArithUtil.sub(wrd[i].getAmount(), wrd[i].getAmount_8()));
        			}
    			}
    			}
    			}
    		}
    		WReward w = new WReward();
			w.setWeekTag(0);
			w.setUserId("合计");
			w.setUserName("");
			w.setAmount((double) 0);
			w.setAmount_1((double) 0);
			w.setAmount_2(shaofa);
			result.add(w);
    	} finally {
			sqlSession.close();
    	}
    	return result;
	}
}
