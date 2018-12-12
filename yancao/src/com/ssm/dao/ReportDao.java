package com.ssm.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AddressMapper;
import com.ssm.mapper.AdminLogMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderDeliveryDetailMapper;
import com.ssm.mapper.OrderDeliveryMapper;
import com.ssm.mapper.OrderDetailMapper;
import com.ssm.mapper.OrderDetailProductMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.PromotionMapper;
import com.ssm.mapper.SettleMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.Address;
import com.ssm.pojo.Order;
import com.ssm.pojo.Settle;
import com.ssm.pojo.User;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WSettle;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.StringUtil;

public class ReportDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    SettleMapper stMapper = sqlSession.getMapper(SettleMapper.class);
    WRewardMapper wrdMapper = sqlSession.getMapper(WRewardMapper.class);
    WSettleMapper wstMapper = sqlSession.getMapper(WSettleMapper.class);

    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);
    AddressMapper adrMapper = sqlSession.getMapper(AddressMapper.class);
    PromotionMapper proMapper = sqlSession.getMapper(PromotionMapper.class);

    OrderDetailMapper orderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
    OrderDeliveryDetailMapper orderDeliveryDetailMapper = sqlSession.getMapper(OrderDeliveryDetailMapper.class);
    OrderDeliveryMapper orderDeliveryMapper = sqlSession.getMapper(OrderDeliveryMapper.class);
    OrderDetailProductMapper orderDetailProductMapper = sqlSession.getMapper(OrderDetailProductMapper.class);
    AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
    ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
    
    
    public List<Order> findOrdersByUserId(String userId){
    	List<Order> wlist = new ArrayList<Order>();
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
    			for(int i=0;i<ulist.size();i++){
    				int id = ulist.get(i).getId();
    				if(user[id]==null) user[id] = new User();
    				user[id].setId(id);
    				user[id].setUserId(ulist.get(i).getUserId()); 
    				user[id].setUserName(ulist.get(i).getUserName());
    				user[id].setRefereeAll(ulist.get(i).getRefereeAll());
    				user[id].setRefereeNode(ulist.get(i).getRefereeNode());
    				user[id].setNode(ulist.get(i).getNode());
    				user[id].setNodeABC(ulist.get(i).getNodeABC());
    				user[id].setEntryTime(ulist.get(i).getEntryTime());
    				user[id].setState(ulist.get(i).getState());
    				user[id].setTag(0);
    			}
    			Order order1 = new Order();
    			params = new HashMap<>();
    			params.put("order",order1);
    			List<Order> olist = orderMapper.selectByList(params);
    			for(int s=0;s<olist.size();s++){
				int orderType = olist.get(s).getOrderType();
				int id = olist.get(s).getuId();
					if(user[id]!=null){
						if(orderType==1||orderType==5){
							String[] str1 = user[id].getNode().split(",");
							for(int i=0;i<str1.length;i++){
								if(!str1[i].equals("")){
									int sid = Integer.valueOf(str1[i]);
									if(userId.equals(user[sid].getUserId())){
										wlist.add(olist.get(s));
										i=str1.length;
										break;
									}
								}
							}
						}
					}
    			}
    		}
    	} finally {
			sqlSession.close();
    	}
    	return wlist;
	}
    
   
    public void findPerformanceByWst(HttpServletRequest request,WSettle wsettle){
    	List<WSettle> wlist = new ArrayList<WSettle>();
    	WSettle re_wst = new WSettle();
    	try{
    		User sel_user = new User();
    		sel_user.setEndTime(wsettle.getEndTime());
    		Map<String,Object> params = new HashMap<>();
    		params.put("user",sel_user);
    		List<User> ulist = userMapper.selectMoneyByPage(params);
    		Integer maxId= userMapper.maxId(params);
    		int num = 1;
    		if(maxId!=null) num =maxId+1;
    		if(num>1){
    			User[] user = new User[num+1];
    			WSettle[] wst = new WSettle[num+1];
    			for(int i=0;i<ulist.size();i++){
    				int id = ulist.get(i).getId();
    				if(user[id]==null) user[id] = new User();
    				user[id].setId(id);
    				user[id].setUserId(ulist.get(i).getUserId()); 
    				user[id].setUserName(ulist.get(i).getUserName());
    				user[id].setRefereeAll(ulist.get(i).getRefereeAll());
    				user[id].setRefereeNode(ulist.get(i).getRefereeNode());
    				user[id].setNode(ulist.get(i).getNode());
    				user[id].setNodeABC(ulist.get(i).getNodeABC());
    				user[id].setEntryTime(ulist.get(i).getEntryTime());
    				user[id].setState(ulist.get(i).getState());
    				user[id].setTag(0);
    				if(wst[id]==null) wst[id] = new WSettle();
    				wst[id].setuId(id);
    				wst[id].setUserId(ulist.get(i).getUserId());
    				wst[id].setUserName(ulist.get(i).getUserName());
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
    				wst[id].setStartTime(wsettle.getStartTime());
    				wst[id].setEndTime(wsettle.getEndTime());
    				
    				if(wsettle.getUserId().equals(user[id].getUserId())){
    					re_wst.setUserId(user[id].getUserId());
    					re_wst.setUserName(user[id].getUserName());
    					re_wst.setNewPerformance((double) 0);
    					re_wst.setNewPrice((double) 0);
    					re_wst.setTotalPerformance((double) 0);
    					re_wst.setTotalPrice((double) 0);
    					re_wst.setApv((double) 0);
    					re_wst.setAprice((double) 0);
    					re_wst.setAtprice((double) 0);
    					re_wst.setBpv((double) 0);
    					re_wst.setBprice((double) 0);
    					re_wst.setBtprice((double) 0);
    					re_wst.setAcpv((double) 0);
    					re_wst.setBcpv((double) 0);
    					re_wst.setRpv((double) 0);
    					re_wst.setRprice((double) 0);
    					re_wst.setRpv1((double) 0);
    					re_wst.setRprice1((double) 0);
    					re_wst.setAtpv((double) 0);
    					re_wst.setBtpv((double) 0);
    					re_wst.setRtpv((double) 0);
    					re_wst.setRtprice((double) 0);
    					re_wst.setRtpv1((double) 0);
    					re_wst.setRtprice1((double) 0);
    				}
    			}
    			Order order1 = new Order();
    			order1.setStartTime(wsettle.getStartTime());
    			order1.setEndTime(wsettle.getEndTime());
    			params = new HashMap<>();
    			params.put("order",order1);
    			List<Order> olist = orderMapper.selectByList(params);
    			double totalPrice=0;
    			for(int s=0;s<olist.size();s++){
    			WSettle wst1 = new WSettle();
				int orderType = olist.get(s).getOrderType();
				double price = olist.get(s).getPrice();
				double pv = olist.get(s).getPv();
				String wst_userId=olist.get(s).getUserId();
				String wst_userName= olist.get(s).getUserName();
				Timestamp orderTime = olist.get(s).getOrderTime();
				int id = olist.get(s).getuId();
				if(user[id]!=null){
					if(orderType==1||orderType==5){
						String[] str1 = user[id].getNode().split(",");
						String[] str2 = user[id].getNodeABC().split(",");
						for(int i=0;i<str1.length;i++){
							if(!str1[i].equals("")){
								int sid = Integer.valueOf(str1[i]);
								if(wsettle.getUserId().equals(user[sid].getUserId())){
									wst1.setTag(1);
									wst1.setStartTime(orderTime);
									wst1.setNewPerformance(pv);
									wst1.setNewPrice(price);
									wst1.setUserId(wst_userId);
									wst1.setUserName(wst_userName);
									re_wst.setNewPerformance(ArithUtil.add(re_wst.getNewPerformance(),pv));
									re_wst.setNewPrice(ArithUtil.add(re_wst.getNewPrice(),price));
									if(str2[i].equals("A")){
										wst1.setApv(pv);
										re_wst.setApv(ArithUtil.add(re_wst.getApv(),pv));
										wst1.setAtpv(price);
										re_wst.setAtpv(ArithUtil.add(re_wst.getAtpv(),price));
									}else if(str2[i].equals("B")){
										wst1.setBpv(pv);
										re_wst.setBpv(ArithUtil.add(re_wst.getBpv(),pv));
										wst1.setBtpv(price);
										re_wst.setBtpv(ArithUtil.add(re_wst.getBtpv(),price));
									}
									break;
								}
							}
						}
						String[] str3 = user[id].getRefereeNode().split(",");
						for(int i=0;i<str3.length;i++){
							if(!str3[i].equals("")){
								int sid = Integer.valueOf(str3[i]);
								if(wsettle.getUserId().equals(user[sid].getUserId())){
									wst1.setTag(1);;
									wst1.setRpv(pv);
									wst1.setRtpv(price);
									wst1.setStartTime(orderTime);
									wst1.setUserId(wst_userId);
									wst1.setUserName(wst_userName);
									re_wst.setRpv(ArithUtil.add(re_wst.getRpv(), pv));
									re_wst.setRtpv(ArithUtil.add(re_wst.getRtpv(), price));
									break;
								}
							}
						}
					}else{
						totalPrice +=price;
						String[] str3 = user[id].getRefereeNode().split(",");
						for(int i=0;i<str3.length;i++){
							if(!str3[i].equals("")){
								int sid = Integer.valueOf(str3[i]);
								if(wsettle.getUserId().equals(user[sid].getUserId())){
									wst1.setTag(1);;
									wst1.setRpv(pv);
									wst1.setRtpv(price);
									wst1.setStartTime(orderTime);
									wst1.setUserId(wst_userId);
									wst1.setUserName(wst_userName);
									re_wst.setRpv1(ArithUtil.add(re_wst.getRpv1(), pv));
									re_wst.setRtpv1(ArithUtil.add(re_wst.getRtpv1(), price));
									break;
								}
							}
						}
					}
				}

			}
    		}
    		request.setAttribute("coll_wst", wlist);
			request.setAttribute("sys_wst", re_wst);
    	} finally {
			sqlSession.close();
    	}
    }
    	
    	public List<WSettle> findRefereePerformance(HttpServletRequest request,WSettle wsettle){
        	List<WSettle> wlist = new ArrayList<WSettle>();
        	try{
        		User sel_user = new User();
        		sel_user.setEndTime(wsettle.getEndTime());
        		Map<String,Object> params = new HashMap<>();
        		params.put("user",sel_user);
        		List<User> ulist = userMapper.selectMoneyByPage(params);
        		Integer maxId= userMapper.maxId(params);
        		int num = 1;
        		if(maxId!=null) num =maxId+1;
        		if(num>1){
        			User[] user = new User[num+1];
        			WSettle[] wst = new WSettle[num+1];
        			for(int i=0;i<ulist.size();i++){
        				int id = ulist.get(i).getId();
        				if(user[id]==null) user[id] = new User();
        				user[id].setId(id);
        				user[id].setUserId(ulist.get(i).getUserId()); 
        				user[id].setUserName(ulist.get(i).getUserName());
        				user[id].setRefereeAll(ulist.get(i).getRefereeAll());
        				user[id].setRefereeNode(ulist.get(i).getRefereeNode());
        				user[id].setRefereeId(ulist.get(i).getRefereeId());
        				user[id].setNode(ulist.get(i).getNode());
        				user[id].setNodeABC(ulist.get(i).getNodeABC());
        				user[id].setEntryTime(ulist.get(i).getEntryTime());
        				user[id].setState(ulist.get(i).getState());
        				user[id].setTag(0);
        				if(wst[id]==null) wst[id] = new WSettle();
        				wst[id].setuId(id);
        				wst[id].setUserId(ulist.get(i).getUserId());
        				wst[id].setUserName(ulist.get(i).getUserName());
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
        				wst[id].setStartTime(wsettle.getStartTime());
        				wst[id].setEndTime(wsettle.getEndTime());
        			}
        			Order order1 = new Order();
        			order1.setStartTime(wsettle.getStartTime());
        			order1.setEndTime(wsettle.getEndTime());
        			params = new HashMap<>();
        			params.put("order",order1);
        			List<Order> olist = orderMapper.selectByList(params);
        			for(int s=0;s<olist.size();s++){
    				int orderType = olist.get(s).getOrderType();
    				double price = olist.get(s).getPrice();
    				double pv = olist.get(s).getPv();
    				int id = olist.get(s).getuId();
    				if(user[id]!=null){
    					if(orderType==1||orderType==5){
    						int sid= user[id].getRefereeId();
    								if(wst[sid]!=null){
    									wst[sid].setTag(1);;
    									wst[sid].setRpv(ArithUtil.add(wst[sid].getRpv(), pv));
    									wst[sid].setRprice(ArithUtil.add(wst[sid].getRprice(), price));
    									wst[sid].setTotalPerformance(ArithUtil.add(wst[sid].getTotalPerformance(), pv));
    									wst[sid].setTotalPrice(ArithUtil.add(wst[sid].getTotalPrice(), price));

    								}
    						
    					}else if(orderType==2){
    						int sid= user[id].getRefereeId();
							if(wst[sid]!=null){
								wst[sid].setTag(1);;
								wst[sid].setApv(ArithUtil.add(wst[sid].getApv(), pv));
								wst[sid].setAprice(ArithUtil.add(wst[sid].getAprice(), price));
								wst[sid].setTotalPerformance(ArithUtil.add(wst[sid].getTotalPerformance(), pv));
								wst[sid].setTotalPrice(ArithUtil.add(wst[sid].getTotalPrice(), price));

							}
					
						}else if(orderType==3){
    						int sid= user[id].getRefereeId();
							if(wst[sid]!=null){
								wst[sid].setTag(1);;
								wst[sid].setBpv(ArithUtil.add(wst[sid].getBpv(), pv));
								wst[sid].setBprice(ArithUtil.add(wst[sid].getBprice(), price));
								wst[sid].setTotalPerformance(ArithUtil.add(wst[sid].getTotalPerformance(), pv));
								wst[sid].setTotalPrice(ArithUtil.add(wst[sid].getTotalPrice(), price));

							}
						}
    				}//end if(user[id]!=null)
    			}
        			for(int i=0;i<wst.length;i++){
        				if(wst[i]!=null){
        					if(wsettle.getUserId().equals("")){
        						wlist.add(wst[i]);
        					}else{
        						if(wsettle.getUserId().equals(wst[i].getUserId())){
        							wlist.add(wst[i]);
        						}
        					}
        				}
        			}
        		}
        	}catch(Exception e){
        		wlist = null;
        		sqlSession.rollback();
        	}finally {
    			sqlSession.close();
        	}
    		return wlist;

	}
    	
    	public List<WSettle> findRefereePerformanceSummary(HttpServletRequest request,WSettle wsettle){
        	List<WSettle> wlist = new ArrayList<WSettle>();
        	try{
        		User sel_user = new User();
        		sel_user.setEndTime(wsettle.getEndTime());
        		Map<String,Object> params = new HashMap<>();
        		params.put("user",sel_user);
        		List<User> ulist = userMapper.selectMoneyByPage(params);
        		Integer maxId= userMapper.maxId(params);
        		int num = 1;
        		if(maxId!=null) num =maxId+1;
        		if(num>1){
        			User[] user = new User[num+1];
        			WSettle[] wst = new WSettle[num+1];
        			for(int i=0;i<ulist.size();i++){
        				int id = ulist.get(i).getId();
        				if(user[id]==null) user[id] = new User();
        				user[id].setId(id);
        				user[id].setUserId(ulist.get(i).getUserId()); 
        				user[id].setUserName(ulist.get(i).getUserName());
        				user[id].setRefereeAll(ulist.get(i).getRefereeAll());
        				user[id].setRefereeNode(ulist.get(i).getRefereeNode());
        				user[id].setRefereeId(ulist.get(i).getRefereeId());
        				user[id].setNode(ulist.get(i).getNode());
        				user[id].setNodeABC(ulist.get(i).getNodeABC());
        				user[id].setEntryTime(ulist.get(i).getEntryTime());
        				user[id].setState(ulist.get(i).getState());
        				user[id].setTag(0);
        				if(wst[id]==null) wst[id] = new WSettle();
        				wst[id].setuId(id);
        				wst[id].setUserId(ulist.get(i).getUserId());
        				wst[id].setUserName(ulist.get(i).getUserName());
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
        				wst[id].setStartTime(wsettle.getStartTime());
        				wst[id].setEndTime(wsettle.getEndTime());
        			}
        			Order order1 = new Order();
        			order1.setStartTime(wsettle.getStartTime());
        			order1.setEndTime(wsettle.getEndTime());
        			params = new HashMap<>();
        			params.put("order",order1);
        			List<Order> olist = orderMapper.selectByList(params);
        			for(int s=0;s<olist.size();s++){
    				int orderType = olist.get(s).getOrderType();
    				double price = olist.get(s).getPrice();
    				double pv = olist.get(s).getPv();
    				int id = olist.get(s).getuId();
    				if(user[id]!=null){
    					if(!StringUtil.notNull(user[id].getRefereeNode()).equals("")){
    						String[] str = user[id].getRefereeNode().split(",");
    					for(int i=0;i<str.length;i++){
    						if(!str[i].equals("")){
    						int sid= Integer.valueOf(str[i]);
    					if(orderType==1||orderType==5){	
    								if(wst[sid]!=null){
    									wst[sid].setTag(1);;
    									wst[sid].setRpv(ArithUtil.add(wst[sid].getRpv(), pv));
    									wst[sid].setRprice(ArithUtil.add(wst[sid].getRprice(), price));
    									wst[sid].setTotalPerformance(ArithUtil.add(wst[sid].getTotalPerformance(), pv));
    									wst[sid].setTotalPrice(ArithUtil.add(wst[sid].getTotalPrice(), price));

    								}
    						
    					}else if(orderType==2){
							if(wst[sid]!=null){
								wst[sid].setTag(1);;
								wst[sid].setApv(ArithUtil.add(wst[sid].getApv(), pv));
								wst[sid].setAprice(ArithUtil.add(wst[sid].getAprice(), price));
								wst[sid].setTotalPerformance(ArithUtil.add(wst[sid].getTotalPerformance(), pv));
								wst[sid].setTotalPrice(ArithUtil.add(wst[sid].getTotalPrice(), price));

							}
					
						}else if(orderType==3){
							if(wst[sid]!=null){
								wst[sid].setTag(1);;
								wst[sid].setBpv(ArithUtil.add(wst[sid].getBpv(), pv));
								wst[sid].setBprice(ArithUtil.add(wst[sid].getBprice(), price));
								wst[sid].setTotalPerformance(ArithUtil.add(wst[sid].getTotalPerformance(), pv));
								wst[sid].setTotalPrice(ArithUtil.add(wst[sid].getTotalPrice(), price));

							}
						}
    						}
						}
    					}
    				}//end if(user[id]!=null)
    			}
        			for(int i=0;i<wst.length;i++){
        				if(wst[i]!=null){
        					if(wsettle.getUserId().equals("")){
        						wlist.add(wst[i]);
        					}else{
        						if(wsettle.getUserId().equals(wst[i].getUserId())){
        							wlist.add(wst[i]);
        						}
        					}
        				}
        			}
        		}
        	}catch(Exception e){
        		wlist = null;
        		sqlSession.rollback();
        	}finally {
    			sqlSession.close();
        	}
    		return wlist;

	}
    
    	
    	  public List<AccountDetail> moneySummary(Timestamp startTime, Timestamp endTime){
    	    	List<AccountDetail> result = new ArrayList<AccountDetail>();
    	    	try{
    	    		
    	    		Map<String,Object> params = new HashMap<>();
    	    		User sel_user = new User();
    	    		params.put("user",sel_user);
    	    		List<User> ulist = userMapper.selectByList(params);
    	    		Integer maxId= userMapper.maxId(params);
    	    		int num = 1;
    	    		if(maxId!=null) num =maxId+1;
    	    		if(num>1){
    	    			User[] user = new User[num+1];
    	    			AccountDetail[] ad = new AccountDetail[num+1];
    	    			for(int i=0;i<ulist.size();i++){
    	    				int id = ulist.get(i).getId();
    	    				if(user[id]==null) user[id] = new User();
    	    				user[id].setId(id);
    	    				user[id].setUserId(ulist.get(i).getUserId()); 
    	    				user[id].setUserName(ulist.get(i).getUserName());
    	    				user[id].setState(ulist.get(i).getState());
    	    				user[id].setTag(0);
    	    				if(ad[id]==null) ad[id] = new AccountDetail();
    	    				ad[id].setUid(user[id].getId());;
    	    				ad[id].setUserId(ulist.get(i).getUserId());
    	    				ad[id].setUserName(ulist.get(i).getUserName());
    	    				ad[id].setStartTime(startTime);
    	    				ad[id].setEndTime(endTime);
    	    				ad[id].setEmoneyBalance((double) 0);
    	    				ad[id].setDmoneyBalance((double) 0);
    	    				ad[id].setRmoneyBalance((double) 0);
    	    				ad[id].setEmoneyAdd((double) 0);
    	    				ad[id].setDmoneyAdd((double) 0);
    	    				ad[id].setRmoneyAdd((double) 0);
    	    				ad[id].setEmoneySub((double) 0);
    	    				ad[id].setDmoneySub((double) 0);
    	    				ad[id].setRmoneySub((double) 0);
    	    			}
	    	    		String sql1 ="select * from emoneyDetail where entryTime>='"+startTime+"' and entryTime<='"+endTime+"' order by entryTime asc";
	    	    		List<AccountDetail> alist1 = moneyMapper.selectByList(sql1);
		    	    	for(int i=0;i<alist1.size();i++){
		    	    		int id = alist1.get(i).getUid();
		    	    		if(ad[id]!=null){
		    	    			if(alist1.get(i).getPayType()==1)
		    	    			ad[id].setEmoneyAdd(ArithUtil.add(ad[id].getEmoneyAdd(), alist1.get(i).getAmount()));
		    	    			else if(alist1.get(i).getPayType()==2)
		    	    				ad[id].setEmoneySub(ArithUtil.add(ad[id].getEmoneySub(), alist1.get(i).getAmount()));
		    	    			ad[id].setEmoneyBalance(alist1.get(i).getBalance());
		    	    		}
		    	    	}
		    	    	String sql2 ="select * from dmoneyDetail where entryTime>='"+startTime+"' and entryTime<='"+endTime+"' order by entryTime asc";
	    	    		List<AccountDetail> alist2 = moneyMapper.selectByList(sql2);
		    	    	for(int i=0;i<alist2.size();i++){
		    	    		int id = alist2.get(i).getUid();
		    	    		if(ad[id]!=null){
		    	    			if(alist2.get(i).getPayType()==1)
		    	    			ad[id].setDmoneyAdd(ArithUtil.add(ad[id].getDmoneyAdd(), alist2.get(i).getAmount()));
		    	    			else if(alist2.get(i).getPayType()==2)
		    	    				ad[id].setDmoneySub(ArithUtil.add(ad[id].getDmoneySub(), alist2.get(i).getAmount()));
		    	    			ad[id].setDmoneyBalance(alist2.get(i).getBalance());
		    	    		}
		    	    	}
		    	    	
		    	    	String sql3 ="select * from rmoneyDetail where entryTime>='"+startTime+"' and entryTime<='"+endTime+"' order by entryTime asc";
	    	    		List<AccountDetail> alist3 = moneyMapper.selectByList(sql3);
		    	    	for(int i=0;i<alist3.size();i++){
		    	    		int id = alist3.get(i).getUid();
		    	    		if(ad[id]!=null){
		    	    			if(alist3.get(i).getPayType()==1)
		    	    			ad[id].setRmoneyAdd(ArithUtil.add(ad[id].getRmoneyAdd(), alist3.get(i).getAmount()));
		    	    			else if(alist3.get(i).getPayType()==2)
		    	    				ad[id].setRmoneySub(ArithUtil.add(ad[id].getRmoneySub(), alist3.get(i).getAmount()));
		    	    			ad[id].setRmoneyBalance(alist3.get(i).getBalance());
		    	    		}
		    	    	}
    	    		for(int i=1;i<ad.length;i++){
    	    			if(ad[i]!=null){
    	    				result.add(ad[i]);
    	    			}
    	    		}
    					
    	    		}
    	    	} finally {
    				sqlSession.close();
    	    	}
    	    	return result;
    		}
    	  
    	  public AccountDetail moneyBalanceSummary(Timestamp startTime, Timestamp endTime){
    			AccountDetail  ad = new AccountDetail();
    			try{
  	    		
  	    				ad.setStartTime(startTime);
  	    				ad.setEndTime(endTime);
  	    				ad.setEmoneyBalance((double) 0);
  	    				ad.setDmoneyBalance((double) 0);
  	    				ad.setRmoneyBalance((double) 0);
  	    				ad.setEmoneyAdd((double) 0);
  	    				ad.setDmoneyAdd((double) 0);
  	    				ad.setRmoneyAdd((double) 0);
  	    				ad.setEmoneySub((double) 0);
  	    				ad.setDmoneySub((double) 0);
  	    				ad.setRmoneySub((double) 0);
  	    				
	    	    		String sql1 ="select * from emoneyDetail where  entryTime<='"+endTime+"' order by entryTime asc";
	    	    		List<AccountDetail> alist1 = moneyMapper.selectByList(sql1);
		    	    	for(int i=0;i<alist1.size();i++){
		    	    			if(alist1.get(i).getPayType()==1){
		    	    				if(alist1.get(i).getEntryTime().getTime()-startTime.getTime()>=0)
		    	    					ad.setEmoneyAdd(ArithUtil.add(ad.getEmoneyAdd(), alist1.get(i).getAmount()));
		    	    				else
		    	    					ad.setEmoneyBalance(ArithUtil.add(ad.getEmoneyBalance(),alist1.get(i).getAmount()));
		    	    			}
		    	    			else if(alist1.get(i).getPayType()==2){
		    	    				if(alist1.get(i).getEntryTime().getTime()-startTime.getTime()>=0)
		    	    					ad.setEmoneySub(ArithUtil.add(ad.getEmoneySub(), alist1.get(i).getAmount()));
		    	    				else
		    	    					ad.setEmoneyBalance(ArithUtil.sub(ad.getEmoneyBalance(),alist1.get(i).getAmount()));
		    	    			}
		    	    	}
		    	    	String sql2 ="select * from dmoneyDetail where entryTime<='"+endTime+"' order by entryTime asc";
	    	    		List<AccountDetail> alist2 = moneyMapper.selectByList(sql2);
		    	    	for(int i=0;i<alist2.size();i++){
		    	    		if(alist2.get(i).getPayType()==1){
		    	    			if(alist2.get(i).getEntryTime().getTime()-startTime.getTime()>=0)
		    	    				ad.setDmoneyAdd(ArithUtil.add(ad.getDmoneyAdd(), alist2.get(i).getAmount()));
		    	    			else
	    	    					ad.setDmoneyBalance(ArithUtil.add(ad.getDmoneyBalance(),alist2.get(i).getAmount()));
	    	    			}
	    	    			else if(alist2.get(i).getPayType()==2){
	    	    				if(alist2.get(i).getEntryTime().getTime()-startTime.getTime()>=0)
	    	    				ad.setDmoneySub(ArithUtil.add(ad.getDmoneySub(), alist2.get(i).getAmount()));
	    	    				else 
	    	    					ad.setDmoneyBalance(ArithUtil.sub(ad.getDmoneyBalance(),alist2.get(i).getAmount()));
	    	    			}
		    	    	}
		    	    	
		    	    	String sql3 ="select * from rmoneyDetail where  entryTime<='"+endTime+"' order by entryTime asc";
	    	    		List<AccountDetail> alist3 = moneyMapper.selectByList(sql3);
		    	    	for(int i=0;i<alist3.size();i++){
		    	    		if(alist3.get(i).getPayType()==1){
		    	    			if(alist3.get(i).getEntryTime().getTime()-startTime.getTime()>=0)
		    	    				ad.setRmoneyAdd(ArithUtil.add(ad.getRmoneyAdd(), alist3.get(i).getAmount()));
		    	    			else
	    	    					ad.setRmoneyBalance(ArithUtil.add(ad.getRmoneyBalance(),alist3.get(i).getAmount()));
	    	    			}
	    	    			else if(alist3.get(i).getPayType()==2){
	    	    				if(alist3.get(i).getEntryTime().getTime()-startTime.getTime()>=0)
	    	    				ad.setRmoneySub(ArithUtil.add(ad.getRmoneySub(), alist3.get(i).getAmount()));
	    	    				else 
	    	    					ad.setRmoneyBalance(ArithUtil.sub(ad.getRmoneyBalance(),alist3.get(i).getAmount()));
	    	    			}
		    	    	}
  	    	} finally {
  				sqlSession.close();
  	    	}
  	    	return ad;
  		}
}
