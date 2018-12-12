package com.ssm.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AgentApplyMapper;
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
import com.ssm.mapper.ProductDetailMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.AccountSupplement;
import com.ssm.pojo.Address;
import com.ssm.pojo.Admin;
import com.ssm.pojo.AgentApply;
import com.ssm.pojo.Center;
import com.ssm.pojo.DSettle;
import com.ssm.pojo.Dept;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDetail;
import com.ssm.pojo.Param;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductDetail;
import com.ssm.pojo.User;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WSettle;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.StringUtil;

public class AgentApplyDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	AgentApplyMapper agentMapper = sqlSession.getMapper(AgentApplyMapper.class);
	UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	   AddressMapper adrMapper = sqlSession.getMapper(AddressMapper.class);
	   ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);
	   AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
	   ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
	   OrderDetailMapper orderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
	   OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
	   ProductDetailMapper productDetailMapper = sqlSession.getMapper(ProductDetailMapper.class);


    ICustomService cs = new CustomService();
    
   
    public Pager findByPage(AgentApply agent,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("agent",agent);
		int recordCount = agentMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<AgentApply> results = agentMapper.selectByPage(params);
		pager.setResultList(results);
		sqlSession.close();
		return pager;
	}
    
    public String save(AgentApply agent){
    	String str = "";
    	try {
	    	if(agentMapper.selectByUserId(agent.getUserId(), agent.getType())==null){
	    		Integer up =agentMapper.save(agent);
	    		if(up!=null&&up>0) {
	    			sqlSession.commit();
			 		str ="区域代理商申请提交成功，请耐心等候审核。";
	    		}
	    	}else{
	    		str ="您已存在相应的申请记录。";
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    		str="数据保存失败，请重试。";
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String reviewYes(AgentApply agent){
    	String str = "";
    	try {
    		AgentApply apply = agentMapper.selectByIdForUpdate(agent.getId());
    		if(apply.getState()-1==0) {
    		Integer up =agentMapper.update(agent);
    		if(up!=null&&up>0) {
    			User user = userMapper.selectByUserIdForUpdate(apply.getUserId());
    			if(user!=null) {
    				User updateUser = new User();
    				updateUser.setId(user.getId());
    				updateUser.setVersion(user.getVersion());
    				updateUser.setAgentTag(apply.getType());
    				updateUser.setProvince(apply.getProvince());
    				updateUser.setCity(apply.getCity());
    				updateUser.setArea(apply.getArea());
    				Integer up1 = userMapper.updateUser(updateUser);
    				if(up1!=null&&up1>0) {
    					sqlSession.commit();
    					str ="yes";
    				}else {
    					str="会员代理标示更新失败。";
    				}
    			}else {
					str="会员信息获取失败。";
				}
    		}else {
    			str = "数据更新失败。";
    		}
    		}else {
    			str="当前状态不能进行审批。";
    			sqlSession.rollback();
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		str="数据保存失败，请重试。";
    	} finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String reviewNo(AgentApply agent){
    	String str = "";
    	try {
    		AgentApply apply = agentMapper.selectByIdForUpdate(agent.getId());
    		if(apply.getState()-1==0) {
    		Integer up =agentMapper.update(agent);
    		if(up!=null&&up>0) {
    					sqlSession.commit();
    					str ="yes";
    		}else {
    			str = "数据更新失败。";
    		}
    		}else {
    			str="当前状态不能进行审批。";
    			sqlSession.rollback();
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		str="数据保存失败，请重试。";
    	} finally {
    		sqlSession.close();
        }
    	return str;
	}
    
 public String delete(Integer id){
    	String  str="";
    	try {
    		Integer up =agentMapper.deleteById(id);
    		if(up!=null&&up>0) {
    			sqlSession.commit();
    			str ="yes";
    		}else {
    			str = "申请记录删除失败。";
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		str="数据操作失败，请重试。";
    	} finally {
    		sqlSession.close();
        }
    	return str;
	}
 
 
 	public AgentApply findById(Integer id){
 		AgentApply agent = new AgentApply();
 		try {
 			agent = agentMapper.selectById(id);
			sqlSession.commit();;
 		}catch(Exception e){
    		e.printStackTrace();
    		agent=null;
    	}finally {
    		sqlSession.close();
        }
		return agent;
	}
 	
 	public List<AgentApply> findListByUserId(String userId,Integer state){
 		List<AgentApply> result =  new ArrayList<AgentApply>();
 		try {
 			result = agentMapper.selectListByUserId(userId, state);
			sqlSession.commit();;
 		}catch(Exception e){
    		e.printStackTrace();
    		result=null;
    	}finally {
    		sqlSession.close();
        }
		return result;
	}
 	
 	public String saveOrders(String userId,String adrId,String orderId,int payType,AgentApply agent,List<Product> plist,Timestamp date){
    	String str ="";
    	try{
    		User user1 = userMapper.selectUsersByUserId(userId);
    		if(user1!=null){
    			//if(user1.getRefereeId()!=null||user1.getRefereeId()>0) {
    				Param p1 = paramMapper.selectByName("会员等级");
    				
    				if(p1!=null){
    					
    					int rankJoin=0;
    					double cash =0;
    					int cashNum =0;
    					double integral = 0;
    					Address adr = new Address();
    				if(!adrId.equals("")) {
    					adr = adrMapper.selectById(Integer.valueOf(adrId));
    				}else adr=null;
				Order orders = new Order();
				orders.setOrderId(orderId);
				orders.setUid(user1.getId());
				orders.setUserId(user1.getUserId());
				orders.setUserName(user1.getUserName());
				orders.setOrderType(1);
				orders.setState(1);
				orders.setPayType(payType);
				orders.setOrderTime(date);
				orders.setRefereeId(user1.getRefereeId());
				if(adr!=null) {
					orders.setProvince(adr.getProvince());
					orders.setCity(adr.getCity());
					orders.setArea(adr.getArea());
					orders.setAddress(adr.getAddress());
				}
    		List<OrderDetail> olist = new ArrayList<OrderDetail>();
			double totalprice = 0;
			String error="";
			for (int i = 0; i < plist.size(); i++) {
				if (plist.get(i).getNum()>0) {
					if(plist.get(i).getType()-1==0) {
						Product pd = productMapper.selectById(plist.get(i).getId());
						int num =plist.get(i).getNum();
						if(pd!=null){
								OrderDetail od = new OrderDetail();
								od.setPid(pd.getId());
								od.setOrderId(orders.getOrderId());
								od.setNum(num);
								od.setProductId(pd.getProductId());
								od.setProductName(pd.getProductName());
								od.setProductPrice(pd.getPrice());
								od.setSpecification(pd.getSpecification());
								od.setProductType(pd.getProductType());
								od.setProductSort(pd.getProductSort());
								od.setPrice(ArithUtil.mul(od.getProductPrice()
										, od.getNum()));
								
								olist.add(od);
								totalprice = ArithUtil.add(totalprice
										,od.getPrice());
								cash = ArithUtil.add(cash, od.getCash());
								integral = ArithUtil.add(integral, od.getIntegral());
								cashNum = cashNum+od.getCashNum();
								if(pd.getNum()-num<0) {
									error+=pd.getProductId()+"库存不足；";
								}
							}
						}else {
							Product pt = productMapper.selectByProductId(plist.get(i).getProductId());
							if(pt!=null) {
								totalprice = ArithUtil.add(totalprice
										,ArithUtil.mul(pt.getPrice(),plist.get(i).getNum()));
								cash = ArithUtil.add(cash, ArithUtil.mul(pt.getCash(),plist.get(i).getNum()));
								integral = ArithUtil.add(integral, ArithUtil.mul(pt.getIntegral(),plist.get(i).getNum()));
								cashNum = cashNum+pt.getCashNum()*plist.get(i).getNum();
							List<ProductDetail> pdlist = productDetailMapper.selectByProductId(plist.get(i).getProductId());
							if(pdlist!=null&&pdlist.size()>0) {
								for(int j=0;j<pdlist.size();j++) {
									Product pd = productMapper.selectByProductId(pdlist.get(j).getProductId());
									int num =plist.get(i).getNum()*pdlist.get(j).getNum();
									if(pd!=null){
											OrderDetail od = new OrderDetail();
											od.setPid(pd.getId());
											od.setOrderId(orders.getOrderId());
											od.setNum(num);
											od.setProductId(pd.getProductId());
											od.setProductName(pd.getProductName());
											od.setProductPrice(pd.getPrice());
											od.setSpecification(pd.getSpecification());
											od.setProductType(pd.getProductType());
											od.setProductSort(pd.getProductSort());
											od.setPrice(ArithUtil.mul(od.getProductPrice()
													, od.getNum()));
											olist.add(od);
											if(pd.getNum()-num<0) {
												error+=pd.getProductId()+"库存不足；";
											}
										}
									}
								}
							}
						}
					}
			}
			if(error.equals("")) {
				if(totalprice>0) {
				if(ArithUtil.sub(totalprice, p1.getAmount_6())>=0) {
				rankJoin = 6;
				
				}else if(ArithUtil.sub(totalprice, p1.getAmount_5())>=0) {
					rankJoin = 5;
					
				}else if(ArithUtil.sub(totalprice, p1.getAmount_4())>=0) {
					rankJoin = 4;
    				
    			}else if(ArithUtil.sub(totalprice, p1.getAmount_3())>=0) {
    				rankJoin = 3;
    					
    			 }else if(ArithUtil.sub(totalprice, p1.getAmount_2())>=0) {
    				 rankJoin = 2;
    					
    			 }else if(ArithUtil.sub(totalprice, p1.getAmount_1())>=0) {
    					rankJoin = 1;
						if(user1.getRankJoin()==0) {
							cash = 0;
							cashNum=0;
						}
    			 	}
				if(user1.getRankJoin()>rankJoin)
				rankJoin = user1.getRankJoin();
				orders.setPrice(totalprice);
				orders.setRank(rankJoin);
				orders.setGiveCash(cash);
				orders.setGiveCashNum(cashNum);
				orders.setGiveIntegral(integral);
			
	    		Map<String,Object> params = new HashMap<>();
	    		if(orderMapper.save(orders)>0){
	    			params.put("list",olist);
	    			if(orderDetailMapper.insertAll(params)>0){
	    				if(agentMapper.save(agent)>0) {
	    				str="yes";
	    				sqlSession.commit();
	    				}else {
	    					str="代理商信息保存失败。";
			    			sqlSession.rollback();
	    				}
	    			}else{
		    			str="订单详情保存失败。";
		    			sqlSession.rollback();
		    		}
	    		}else{
	    			str="订单信息保存失败。";
	    			sqlSession.rollback();
	    		}
				}else{
	    			str="未选择需要购买的产品。";
	    			sqlSession.rollback();
	    		}
			}else{
    			str=error;
    			sqlSession.rollback();
    		}
    					 }else{
    	    			    	str ="会员级别参数获取失败。";
    	    		 }
    				/*
    				}else{
    			    	str ="未查询到有相应的邀请人信息，请绑定邀请人后再重新购买。";
    			    }
    			    */
		    }else{
		    	str ="会员信息获取失败。";
		    }
    	}catch(Exception e){
    		e.printStackTrace();
    		str ="系统繁忙中，请稍后再试。"+e.getMessage();
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
		return str;
	}
 	
}
