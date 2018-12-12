package com.ssm.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AddressMapper;
import com.ssm.mapper.AdminLogMapper;
import com.ssm.mapper.CenterMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderDeliveryDetailMapper;
import com.ssm.mapper.OrderDeliveryMapper;
import com.ssm.mapper.OrderDetailMapper;
import com.ssm.mapper.OrderDetailProductMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.ProductDetailMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.ProductStockMapper;
import com.ssm.mapper.PromotionMapper;
import com.ssm.mapper.RewardDetailMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.Address;
import com.ssm.pojo.AdminLog;
import com.ssm.pojo.Center;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDelivery;
import com.ssm.pojo.OrderDeliveryDetail;
import com.ssm.pojo.OrderDetail;
import com.ssm.pojo.OrderDetailProduct;
import com.ssm.pojo.Param;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductDetail;
import com.ssm.pojo.ProductStock;
import com.ssm.pojo.Promotion;
import com.ssm.pojo.RewardDetail;
import com.ssm.pojo.User;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.StringUtil;

public class OrderDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
   OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
   UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
   MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);
   RewardDetailMapper rewardMapper = sqlSession.getMapper(RewardDetailMapper.class);
   AddressMapper adrMapper = sqlSession.getMapper(AddressMapper.class);
   PromotionMapper proMapper = sqlSession.getMapper(PromotionMapper.class);
   ProductStockMapper stockMapper = sqlSession.getMapper(ProductStockMapper.class);
   ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);
   CenterMapper centerMapper = sqlSession.getMapper(CenterMapper.class);
   OrderDetailMapper orderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
   OrderDeliveryDetailMapper orderDeliveryDetailMapper = sqlSession.getMapper(OrderDeliveryDetailMapper.class);
   OrderDeliveryMapper orderDeliveryMapper = sqlSession.getMapper(OrderDeliveryMapper.class);
   OrderDetailProductMapper orderDetailProductMapper = sqlSession.getMapper(OrderDetailProductMapper.class);
   AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
   ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
   ProductDetailMapper productDetailMapper = sqlSession.getMapper(ProductDetailMapper.class);

   ICustomService cs = new CustomService();
    
    public Pager findOrderByPage(Order order,Pager pager){
    	try{
    	Map<String,Object> params = new HashMap<>();
		params.put("order",order);
		int recordCount =orderMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<Order> orders =orderMapper.selectByPage(params);
		Order od =orderMapper.sumByParam(params);
		double[] sum ={0,0,0,0};
		if(od!=null){
			sum[0] = od.getPrice();
			sum[1] = od.getCash();
			sum[2] = od.getCashNum();
			sum[3] = od.getIntegral();
		}
		pager.setResultList(orders);
		pager.setSum(sum);
		sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally{
		sqlSession.close();
    	}
		return pager;
	}
    
    public Pager findTagOrderByPage(Order order,Pager pager){
    	try{
    	Map<String,Object> params = new HashMap<>();
		params.put("order",order);
		int recordCount =orderMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<Order> orders =orderMapper.selectTagByPage(params);
		Order od =orderMapper.sumTagByParam(params);
		double[] sum ={0,0,0,0};
		if(od!=null){
			sum[0] = od.getPrice();
			sum[1] = od.getCash();
			sum[2] = od.getCashNum();
			sum[3] = od.getIntegral();
		}
		pager.setResultList(orders);
		pager.setSum(sum);
		sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally{
		sqlSession.close();
    	}
		return pager;
	}
    
    public List<Order> findByList(Order order){
		Map<String,Object> params = new HashMap<>();
		params.put("order",order);
		List<Order> orders =orderMapper.selectByList(params);
		sqlSession.close();
		return orders;
	}
    
    public Order findByOrderId(String orderId){
    	Order orders = orderMapper.selectByOrderId(orderId);
  		sqlSession.close();
  		return orders;
  	}
    
    public Order findById(Integer id){
    	Order orders = null;
    	try{
    	orders = orderMapper.selectById(id);
    	sqlSession.commit();
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		sqlSession.close();
    	}
  		return orders;
  	}
    
    
    public int update(Order order){
    	int num =0;
    	try{
    		Integer result = orderMapper.update(order);
    		if(result!=null) num = result;
    		sqlSession.commit();
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
  		sqlSession.close();
    	}
  		return num;
  	}
    
   
   
    
    public List<OrderDetail> findOrderDetailByList(Order order){
		Map<String,Object> params = new HashMap<>();
		params.put("order",order);
		List<OrderDetail> orders =orderMapper.selectOrderDetailByList(params);
		sqlSession.close();
		return orders;
	}
    
    public List<OrderDetailProduct> findOrderDetailProductByList(Order order){
		Map<String,Object> params = new HashMap<>();
		params.put("order",order);
		List<OrderDetailProduct> orders =orderMapper.selectOrderDetailProductByList(params);
		sqlSession.close();
		return orders;
	}
    
    
    public Order saveRankJoinOrders(String userId,String adrId,String orderId,int payType,List<Product> plist,Timestamp date){
    	Order orders = new Order();
    	String str="";
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
					orders.setReceiver(adr.getReciver());
					orders.setPhone(adr.getPhone());
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
								cash = ArithUtil.add(cash, ArithUtil.mul(pd.getCash(),num));
								integral = ArithUtil.add(integral, ArithUtil.mul(pd.getIntegral(),num));
								cashNum = cashNum+pd.getCashNum()*num;
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
	    				
	    				sqlSession.commit();
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
    		str ="系统繁忙中，请稍后再试。";
    		sqlSession.rollback();
    	}finally {
    		orders.setMsg(str);
    		sqlSession.close();
        }
		return orders;
	}
    
    public Order saveTeOrders(String userId,String adrId,String orderId,int payType,List<Product> plist,Timestamp date){
    	Order orders = new Order();
    	String str ="";
    	String error="";
    	try{
    		User user1 = userMapper.selectByUserIdForUpdate(userId);
    		if(user1!=null){
    			//if(user1.getRefereeId()!=null||user1.getRefereeId()>0) {
    				
    					int rankJoin=user1.getRankJoin();
    					double tprice = 0;
    					double cash =0;
    					int cashNum =0;
    					double integral = 0;
    					Address adr = new Address();
    				if(!adrId.equals("")) {
    					adr = adrMapper.selectById(Integer.valueOf(adrId));
    				}else adr=null;
				orders.setOrderId(orderId);
				orders.setUid(user1.getId());
				orders.setUserId(user1.getUserId());
				orders.setUserName(user1.getUserName());
				orders.setOrderType(2);
				orders.setRank(rankJoin);
				orders.setState(1);
				orders.setPayType(payType);
				orders.setOrderTime(date);
				orders.setRefereeId(user1.getRefereeId());
				if(adr!=null) {
					orders.setProvince(adr.getProvince());
					orders.setCity(adr.getCity());
					orders.setArea(adr.getArea());
					orders.setAddress(adr.getAddress());
					orders.setReceiver(adr.getReciver());
					orders.setPhone(adr.getPhone());
				}else error="收货地址获取失败，请选择。";
    		List<OrderDetail> olist = new ArrayList<OrderDetail>();
			double totalprice = 0;
			
			for (int i = 0; i < plist.size(); i++) {
				if (plist.get(i).getNum()>0) {
					Product pd = productMapper.selectById(plist.get(i).getId());
					int num =plist.get(i).getNum();
					if(pd!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(pd.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(num);
							od.setProductId(pd.getProductId());
							od.setProductName(pd.getProductName());
							od.setProductPrice(plist.get(i).getProductPrice());
							od.setSpecification(pd.getSpecification());
							od.setProductType(pd.getProductType());
							od.setProductSort(pd.getProductSort());
							od.setProductRetailPrice(pd.getPrice());
							od.setRetailPrice(ArithUtil.mul(pd.getPrice()
									, od.getNum()));
							od.setProductCash(plist.get(i).getCash());
							od.setCash(ArithUtil.mul(od.getProductCash()
									, od.getNum()));
							od.setProductCashNum(plist.get(i).getCashNum());
							od.setCashNum(od.getProductCashNum()*od.getNum());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							olist.add(od);
							totalprice = ArithUtil.add(totalprice
									,od.getRetailPrice());
							tprice = ArithUtil.add(tprice
									,od.getPrice());
							cash = ArithUtil.add(cash
									,od.getCash());
							cashNum = cashNum+od.getCashNum();
							integral =ArithUtil.add(integral, plist.get(i).getIntegral());
							if(pd.getNum()-num<0) {
								error+=pd.getProductId()+"库存不足；";
							}
						}
					}
			}
			
			if(error.equals("")) {
				if(ArithUtil.sub(user1.getCash(),cash)>=0&&user1.getCashNum()-cashNum>=0) {
				orders.setPrice(tprice);
				orders.setRetailPrice(totalprice);
				orders.setRank(user1.getRankJoin());
				orders.setCash(cash);
				orders.setCashNum(cashNum);
				orders.setGiveIntegral(integral);
			
	    		Map<String,Object> params = new HashMap<>();
	    		if(orderMapper.save(orders)>0){
	    			params.put("list",olist);
	    			if(orderDetailMapper.insertAll(params)>0){
	    				User updateUser = new User();
	    				updateUser.setId(user1.getId());
	    				updateUser.setCash(ArithUtil.sub(user1.getCash(),cash));
	    				updateUser.setCashNum(user1.getCashNum()-cashNum);
	    				updateUser.setVersion(user1.getVersion());
	    				Integer up = userMapper.updateUser(updateUser);
	    				
	    				if(up!=null&&up>0) {
	    					AccountDetail ad = cs.returnAccountDetail(user1, cash, updateUser.getCash(), 2, Constants.MONEY_DETAIL_YYPE_5, orders.getOrderId(), date);
							 String tableName = Constants.CASHDETAIL_TABLE;
							 Map<String,Object> param1 = new HashMap<>();
							 param1.put("account",ad);
							 param1.put("tableName", tableName);
							 Integer up2 =moneyMapper.save(param1);
							 if(up2!=null&&up2>0) {
								 AccountDetail ad1 = cs.returnAccountDetail(user1, cashNum, updateUser.getCashNum(), 2, Constants.MONEY_DETAIL_YYPE_5, orders.getOrderId(), date);
								 String tableName1 = Constants.CASHNUMDETAIL_TABLE;
								 Map<String,Object> param2 = new HashMap<>();
								 param2.put("account",ad1);
								 param2.put("tableName", tableName1);
								 Integer up3 =moneyMapper.save(param2);
								 if(up3!=null&&up3>0) {
				    				str="yes";
				    				sqlSession.commit();
								 }else {
				    					str="特卖权账户明细更新失败。";
						    			sqlSession.rollback();
				    				}
							 }else {
			    					str="代金券账户明细更新失败。";
					    			sqlSession.rollback();
			    				}
	    				}else {
	    					str="会员信息更新失败。";
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
    		orders.setMsg(str);
    		sqlSession.close();
        }
		return orders;
	}
    
    public String saveIntegralOrders(String userId,String adrId,String orderId,int payType,List<Product> plist,Timestamp date){
    	String str ="";
    	try{
    		User user1 = userMapper.selectByUserIdForUpdate(userId);
    		if(user1!=null){
    			//if(user1.getRefereeId()!=null||user1.getRefereeId()>0) {
    				
    					int rankJoin=user1.getRankJoin();
    					double tprice = 0;
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
				orders.setOrderType(3);
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
					Product pd = productMapper.selectById(plist.get(i).getId());
					int num =plist.get(i).getNum();
					if(pd!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(pd.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(num);
							od.setProductId(pd.getProductId());
							od.setProductName(pd.getProductName());
							od.setProductPrice(plist.get(i).getProductPrice());
							od.setSpecification(pd.getSpecification());
							od.setProductType(pd.getProductType());
							od.setProductSort(pd.getProductSort());
							od.setProductRetailPrice(pd.getPrice());
							od.setRetailPrice(ArithUtil.mul(pd.getPrice()
									, od.getNum()));
							od.setProductIntegral(plist.get(i).getIntegral());
							od.setIntegral(ArithUtil.mul(od.getProductIntegral()
									, od.getNum()));
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							olist.add(od);
							totalprice = ArithUtil.add(totalprice
									,od.getRetailPrice());
							tprice = ArithUtil.add(tprice
									,od.getPrice());
							integral = ArithUtil.add(integral
									,od.getIntegral());
							if(pd.getNum()-num<0) {
								error+=pd.getProductId()+"库存不足；";
							}
						}
					}
			}
			double giveIntegral = 0;
			Param p4=paramMapper.selectByName("赠送积分");
		if(p4!=null) {
			double pro =0;
			if(rankJoin-1==0){
				pro = p4.getAmount_1();
			}else if(rankJoin-2==0){
				pro = p4.getAmount_2();
			}else if(rankJoin-3==0){
				pro = p4.getAmount_3();
			}else if(rankJoin-4==0){
				pro = p4.getAmount_4();
			}else if(rankJoin-5==0){
				pro = p4.getAmount_5();
			}else if(rankJoin-6==0){
				pro = p4.getAmount_6();
			}else if(rankJoin-7==0){
				pro = p4.getAmount_7();
			}
			giveIntegral = ArithUtil.mul(tprice, pro);
		}
			if(error.equals("")) {
				if(ArithUtil.sub(user1.getIntegral(),integral)>=0) {
				orders.setPrice(tprice);
				orders.setRetailPrice(totalprice);
				orders.setRank(user1.getRankJoin());
				orders.setIntegral(integral);
				orders.setGiveIntegral(giveIntegral);
				
				/*
				if(orders.getPayType()-1==0) {
					OrderDelivery orderDelivery = new OrderDelivery();
					orderDelivery.setOrderId(orderId);
					orderDelivery.setUid(user1.getId());
					orderDelivery.setUserId(user1.getUserId());
					orderDelivery.setUserName(user1.getUserName());
					orderDelivery.setOrderType(3);
					orderDelivery.setState(1);
					orderDelivery.setOrderTime(date);
					orders.setPrice(tprice);
					if(adr!=null) {
						orders.setProvince(adr.getProvince());
						orders.setCity(adr.getCity());
						orders.setArea(adr.getArea());
						orders.setAddress(adr.getAddress());
					}
				}
				*/
			
	    		Map<String,Object> params = new HashMap<>();
	    		if(orderMapper.save(orders)>0){
	    			params.put("list",olist);
	    			if(orderDetailMapper.insertAll(params)>0){
	    				User updateUser = new User();
	    				updateUser.setId(user1.getId());
	    				updateUser.setIntegral(ArithUtil.sub(user1.getCash(),integral));
	    				updateUser.setVersion(user1.getVersion());
	    				Integer up = userMapper.updateUser(updateUser);
	    				if(up!=null&&up>0) {
	    					AccountDetail ad = cs.returnAccountDetail(user1, integral, updateUser.getIntegral(), 2, Constants.MONEY_DETAIL_YYPE_6, orders.getOrderId(), date);
							 String tableName = Constants.CASHDETAIL_TABLE;
							 Map<String,Object> param1 = new HashMap<>();
							 param1.put("account",ad);
							 param1.put("tableName", tableName);
							 Integer up2 =moneyMapper.save(param1);
							 if(up2!=null&&up2>0) {
			    				str="yes";
			    				sqlSession.commit();
							 }else {
			    					str="积分账户明细更新失败。";
					    			sqlSession.rollback();
			    				}
	    				}else {
	    					str="会员信息更新失败。";
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
    
  
    public List<Product>  orderDetailSummary(String sql){
    	 List<Product> ptlist = new ArrayList<Product>();
    	 
    	try{
    		List<OrderDetailProduct> olist = orderMapper.selectDetailBySql(sql);
    		Integer recordCount =productMapper.maxId();
    		int num =0;
    		if(recordCount!=null)
    		num = recordCount+1;
    		Product[] prod = new Product[num];
    		List<Product> plist = productMapper.selectAllProduct();
    		Product product = new Product();
    		product.setPrice((double) 0);
    		product.setPv((double) 0);
    		product.setNum(0);
    		for(int i=0;i<plist.size();i++){
    			int id = plist.get(i).getId();
    			if(prod[id]==null) prod[id] = new Product();
    			prod[id].setId(id);
    			prod[id].setProductId(plist.get(i).getProductId());
    			prod[id].setProductName(plist.get(i).getProductName());
    			prod[id].setSpecification(plist.get(i).getSpecification());
    			prod[id].setProductPrice((double) 0);
    			prod[id].setProductPv((double) 0);
    			prod[id].setPrice((double) 0);
    			prod[id].setPv((double) 0);
    			prod[id].setNum(0);
    			prod[id].setType(0);
    		}
    		for(int i=0;i<olist.size();i++){
    			int id = olist.get(i).getPid();
    			if(id<num){
	    			if(prod[id]!=null){
	    				prod[id].setNum(prod[id].getNum()+olist.get(i).getNum());
	    				prod[id].setPrice(ArithUtil.add(prod[id].getPrice(),ArithUtil.mul(olist.get(i).getPrice(), olist.get(i).getDiscount())));
	    				prod[id].setPv(ArithUtil.add(prod[id].getPv(),ArithUtil.mul(olist.get(i).getPv(), olist.get(i).getDiscount())));
	    				prod[id].setType(1);
	    				product.setNum(product.getNum()+olist.get(i).getNum());
	    				product.setPrice(ArithUtil.add(product.getPrice(),ArithUtil.mul(olist.get(i).getPrice(), olist.get(i).getDiscount())));
	    				product.setPv(ArithUtil.add(product.getPv(),ArithUtil.mul(olist.get(i).getPv(), olist.get(i).getDiscount())));
	    			}
    			}
    				
    		}
    		ptlist.add(product);
    		for(int i=1;i<prod.length;i++){
    			if(prod[i]!=null&&prod[i].getType()==1){
    				prod[i].setProductPrice(ArithUtil.div(prod[i].getPrice(), prod[i].getNum()));
    				prod[i].setProductPv(ArithUtil.div(prod[i].getPv(), prod[i].getNum()));
    				ptlist.add(prod[i]);
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		sqlSession.rollback();
    	}finally{
    		sqlSession.close();
    	}
  		return ptlist;
  	}
    
  
    public List<OrderDetailProduct>  orderDetailAll(String sql){
   	 List<OrderDetailProduct> ptlist = new ArrayList<OrderDetailProduct>();
   	 
	   	try{
	   		ptlist = orderMapper.selectDetailAllBySql(sql);
	   	}catch(Exception e){
	   		e.printStackTrace();
	   		sqlSession.rollback();
	   	}finally{
	   		sqlSession.close();
	   	}
 		return ptlist;
 	}
    
    public String  order_back(String id,Timestamp date){
      	 String msg = "";
      	 String error="";
      	String tradeType = "";
      	try{
      		Order order = orderMapper.selectByIdForUpdate(Integer.valueOf(id));
      		if(order!=null) {
      			if(order.getState()==1) {
      				User user= userMapper.selectUsersByIdForUpdate(order.getUid());
	      			if(user!=null) {
	      				if(order.getOrderType()==1) tradeType= Constants.MONEY_DETAIL_YYPE_4;
	      				else if(order.getOrderType()==2) tradeType= Constants.MONEY_DETAIL_YYPE_5;
	      				else if(order.getOrderType()==3) tradeType= Constants.MONEY_DETAIL_YYPE_6;
	      				Integer up = orderMapper.updateForState(0, Integer.valueOf(id));
	      				if(up!=null&&up>0) {
	      					int y=0;
	      					if(order.getOrderType()>1) {
	      						User updateUser = new User();
	      						updateUser.setId(user.getId());
	      						updateUser.setVersion(user.getVersion());
	      						updateUser.setIntegral(user.getIntegral());
	      						updateUser.setCash(user.getCash());
	      						updateUser.setCashNum(user.getCashNum());
	      						if(order.getCash()>0) {
      		      					double cash = ArithUtil.add(user.getCash(),order.getCash());
      		      					if(cash>=0) {
      		      						updateUser.setCash(cash);
      		      						AccountDetail ad = cs.returnAccountDetail(user, order.getCash(), updateUser.getCash(), 1,"订单"+tradeType, order.getOrderId()+"退单", date);
      		      						String tableName = Constants.CASHDETAIL_TABLE;
      		      						Map<String,Object> params = new HashMap<>();
      		      						params.put("account",ad);
      		      						params.put("tableName", tableName);
      		      						Integer up1 =moneyMapper.save(params);
      		      						if(up1!=null&&up1>0) {
      		      							y++;
	      		      						ad=null;
			   							 }else error+="会员代金券账户保存失败。";
      		      					}else error+="会员代金券不足；";	
      		      				}
	      						if(order.getCashNum()>0) {
      		      					int cashNum = user.getCashNum()+order.getCashNum();
      		      					if(cashNum>=0) {
      		      						updateUser.setCashNum(cashNum);
      		      						AccountDetail ad = cs.returnAccountDetail(user, order.getCashNum(), updateUser.getCashNum(), 1,"订单"+tradeType, order.getOrderId()+"退单", date);
      		      						String tableName = Constants.CASHNUMDETAIL_TABLE;
      		      						Map<String,Object> params = new HashMap<>();
      		      						params.put("account",ad);
      		      						params.put("tableName", tableName);
      		      						Integer up1 =moneyMapper.save(params);
      		      						if(up1!=null&&up1>0) {
      		      							y++;
      		      							ad=null;
			   							 }else error+="会员特卖权账户保存失败。";
      		      					}else error+="会员特卖权不足；";	
	      						}
      		      					
      		      				if(order.getIntegral()>0) {
      		      					double integral = ArithUtil.add(user.getIntegral(),order.getIntegral());
      		      					if(integral>=0) {
      		      						updateUser.setIntegral(integral);
      		      						AccountDetail ad = cs.returnAccountDetail(user, order.getIntegral(), updateUser.getIntegral(), 1,"订单"+tradeType, order.getOrderId()+"退单", date);
      		      						String tableName = Constants.INTEGRALDETAIL_TABLE;
      		      						Map<String,Object> params = new HashMap<>();
      		      						params.put("account",ad);
      		      						params.put("tableName", tableName);
      		      						Integer up1 =moneyMapper.save(params);
      		      						if(up1!=null&&up1>0) {
      		      							y++;
      		      							ad=null;
			   							 }else error+="会员积分账户保存失败。";
      		      					}else error+="会员积分不足；";	
      		      				}
      		      				
      		      			
      		      					if(y>0) {
      		      						Integer up1 = userMapper.updateUser(updateUser);
      		      						if(up1!=null&&up1>0) {
      		      							
      		      						}else {
      		      							error="会员账户信息更新失败。";
      		      						}
      		      					}
	      					}
      		      				if(error.equals("")) {
      		      					msg="yes";
      		      					sqlSession.commit();
      		      				}else {
      		      					msg=error;
      		      					sqlSession.rollback();
      		      				}
	      					
	      				}else {
	      					msg="订单状态更新失败，请重试。";
	      					sqlSession.rollback();
	      				}
	      			}else {
	      				msg="订单对应对会员信息获取失败。";
	      			}
      			}else {
      				msg="该订单状态不能进行退单。";
      			}
      		}else {
  				msg="订单信息获取失败。";
  			}		
      	}catch(Exception e){
      		e.printStackTrace();
      		msg="数据保存异常。"+e.getMessage();
      		sqlSession.rollback();
      	}finally{
      		sqlSession.close();
      	}
    		return msg;
    	}
    
    public String  reviewYes(String adminName,String id,Timestamp date){
     	 String msg = "";
     	 String error="";
     	try{
     		Order order = orderMapper.selectByIdForUpdate(Integer.valueOf(id));
     		if(order!=null) {
	      			User user= userMapper.selectUsersByIdForUpdate(order.getUid());
	      			if(user!=null) {
	      				User refereeUser = userMapper.selectUsersByIdForUpdate(user.getRefereeId());
	      				String tradeType = "";
		      				if(order.getOrderType()==1) tradeType= Constants.MONEY_DETAIL_YYPE_4;
		      				else if(order.getOrderType()==2) tradeType= Constants.MONEY_DETAIL_YYPE_5;
		      				else if(order.getOrderType()==3) tradeType= Constants.MONEY_DETAIL_YYPE_6;
	      				if(order.getPayType()-2==0) {
	      					if(order.getOrderType()-1<=0) {
	      					if(refereeUser!=null) {
	      						//-start-库存更新（包括云仓）
		      				List<OrderDetail> olist = orderDetailMapper.selectByOrderId(order.getOrderId());
		      				if(olist!=null&&olist.size()>0) {
		      					for(int i=0;i<olist.size();i++) {
		      					if(olist.get(i).getNum()>0) {
		      						int err = stockMapper.updateSubNum(olist.get(i).getProductId(), refereeUser.getUserId(), olist.get(i).getNum());
		      						if(err>0) {
		      							ProductStock stock = stockMapper.selectByProductId(olist.get(i).getProductId(), user.getUserId());
		      							if(stock!=null) {
				      						 err = stockMapper.updateNum(olist.get(i).getProductId(), user.getUserId(), olist.get(i).getNum());
				      						 if(err==0) error+="会员云仓更新失败;";
		      							}else {
		      								Product pro = productMapper.selectByProductId(olist.get(i).getProductId());
		      								stock = new ProductStock();
		      								stock.setUid(user.getId());
		      								stock.setUserId(user.getUserId());
		      								stock.setUserName(user.getUserName());
		      								stock.setPid(olist.get(i).getPid());
		      								stock.setProductId(olist.get(i).getProductId());
		      								stock.setProductName(olist.get(i).getProductName());
		      								stock.setPrice(olist.get(i).getProductPrice());
		      								stock.setSpecification(olist.get(i).getSpecification());
		      								stock.setNum(olist.get(i).getNum());
		      								stock.setTotalNum(olist.get(i).getNum());
		      								if(pro!=null) stock.setImageUrl(pro.getImageUrl());
		      								stock.setEntryTime(date);
		      								stock.setEndTime(date);
		      								int er = stockMapper.save(stock);
		      								if(er==0) error+=olist.get(i).getProductId()+"会员云仓更新失败；";
		      							}
		      						}else error+=olist.get(i).getProductId()+"云仓库存不足或更新失败；";
		      					}
		      					}
		      					}else error+="未查询到相应的订单详情。";
		      				
		      					//end 更新云仓
		      					//--start--会员等级更新以及特权数等发放
		      					if(error.equals("")) {
		      		      				User updateUser = new User();
		      		      				User updateRefereeUser = new User();
		      		      				updateUser.setId(user.getId());
		      		      				updateUser.setUserId(user.getUserId());
		      		      				updateUser.setUserName(user.getUserName());
		      		      				updateUser.setVersion(user.getVersion());
		      		      				updateRefereeUser.setId(refereeUser.getId());
		      		      				updateRefereeUser.setUserId(refereeUser.getUserId());
		      		      				updateRefereeUser.setUserName(refereeUser.getUserName());
		      		      				updateRefereeUser.setVersion(refereeUser.getVersion());
		      		      				int y= 0;
		      		      				int y1=0;
		      		      				if(order.getOrderType()-1==0) {
		      		    	      			int rankJoin = order.getRank();
		      		    	      			if(user.getRankJoin()-rankJoin<0) {
			      		    	      			Param p1=paramMapper.selectByName("虚拟会员");
			    	    	      				if(p1!=null) {
				    	    	      				rankJoin = checkRankJoin(user,p1,rankJoin);
				    	    	      				updateUser.setRankJoin(rankJoin);
				    	    	      				y++;
				    	    	      				if(refereeUser!=null) {
				    	    	      					int count = rankJoin_up(user,refereeUser,p1,rankJoin);
				    	    	      					if(count==0)
				    	    	      						error+="推荐人推荐信息更新失败-1。";
				    	    	      				}
			    	    	      				}else error+="推荐会员信息指标参数获取失败。";
		      		    	      			}
		      		    	      		}
		      		      				
		      		      				if(order.getGiveCash()>0) {
		      		      					double cash = ArithUtil.sub(refereeUser.getCash(),order.getGiveCash());
		      		      					if(cash>=0) {
			      		      					updateUser.setCash(ArithUtil.add(user.getCash(),order.getGiveCash()));
				      		      				AccountDetail ad = cs.returnAccountDetail(user, order.getGiveCash(), updateUser.getCash(), 1,tradeType, order.getOrderId(), date);
					   							 String tableName = Constants.CASHDETAIL_TABLE;
					   							 Map<String,Object> params = new HashMap<>();
					   							 params.put("account",ad);
					   							 params.put("tableName", tableName);
					   							 Integer up1 =moneyMapper.save(params);
					   							 if(up1!=null&&up1>0) {
					   								y++;
					   								updateRefereeUser.setCash(cash);
					   								AccountDetail ad1 = cs.returnAccountDetail(refereeUser, order.getGiveCash(), updateRefereeUser.getCash(), 2,tradeType, order.getOrderId(), date);
						   							 Map<String,Object> param1 = new HashMap<>();
						   							 param1.put("account",ad1);
						   							 param1.put("tableName", tableName);
						   							 Integer up2 =moneyMapper.save(param1);
						   							 if(up2!=null&&up2>0) {
						   								 y1++;
						   							 }else error+="推荐人代金券账户保存失败。";
					   							 }else error+="会员代金券账户保存失败。";
			      		      					
		      		      					}else error+="推荐人代金券不足；";
		      		      				}else if(order.getCash()>0) {
		      		      					double cash = ArithUtil.sub(user.getCash(),order.getCash());
		      		      					if(cash>=0) {
		      		      						updateUser.setCash(cash);
		      		      						AccountDetail ad = cs.returnAccountDetail(user, order.getCash(), updateUser.getCash(), 2,tradeType, order.getOrderId(), date);
		      		      						String tableName = Constants.CASHDETAIL_TABLE;
		      		      						Map<String,Object> params = new HashMap<>();
		      		      						params.put("account",ad);
		      		      						params.put("tableName", tableName);
		      		      						Integer up1 =moneyMapper.save(params);
		      		      						if(up1!=null&&up1>0) {
			      		      					y++;
			      		      					updateRefereeUser.setCash(ArithUtil.add(refereeUser.getCash(),order.getCash()));
				      		      				AccountDetail ad1 = cs.returnAccountDetail(refereeUser, order.getCash(), updateRefereeUser.getCash(), 1,tradeType, order.getOrderId(), date);
					   							 Map<String,Object> param1 = new HashMap<>();
					   							 param1.put("account",ad1);
					   							 param1.put("tableName", tableName);
					   							 Integer up2 =moneyMapper.save(param1);
						   							 if(up2!=null&&up2>0) {
					      		      					y1++;
						   							 }else error+="推荐人代金券账户保存失败。";
					   							 }else error+="会员代金券账户保存失败。";
			      		      					
		      		      					}else error+="会员代金券不足；";
		      		      							
		      		      				}
		      		      				if(order.getGiveCashNum()>0) {
			      		      				int cashNum = refereeUser.getCashNum()-order.getGiveCashNum();
			      		      				if(cashNum>=0) {
			      		      					updateUser.setCashNum(user.getCashNum()+order.getGiveCashNum());
			      		      				AccountDetail ad = cs.returnAccountDetail(user, order.getGiveCashNum(), updateUser.getCashNum(), 1,tradeType, order.getOrderId(), date);
	      		      						String tableName = Constants.CASHNUMDETAIL_TABLE;
	      		      						Map<String,Object> params = new HashMap<>();
	      		      						params.put("account",ad);
	      		      						params.put("tableName", tableName);
	      		      						Integer up1 =moneyMapper.save(params);
	      		      						if(up1!=null&&up1>0) {
			      		      					y++;
			      		      					updateRefereeUser.setCashNum(cashNum);
			      		      				AccountDetail ad1 = cs.returnAccountDetail(refereeUser, order.getGiveCashNum(), updateRefereeUser.getCashNum(), 2,tradeType, order.getOrderId(), date);
				   							 Map<String,Object> param1 = new HashMap<>();
				   							 param1.put("account",ad1);
				   							 param1.put("tableName", tableName);
				   							 Integer up2 =moneyMapper.save(param1);
					   							 if(up2!=null&&up2>0) {
			      		      					y1++;
					   							 }else error+="推荐人特卖权账户保存失败。";
				   							 }else error+="会员特卖权账户保存失败。";
			      		      				}else error+="推荐人特卖权数不足；";
		      		      				}else if(order.getCashNum()>0) {
		      		      					int cashNum = user.getCashNum()-order.getCashNum();
		      		      					if(cashNum>=0) {
			      		      					updateUser.setCashNum(cashNum);
			      		      				AccountDetail ad = cs.returnAccountDetail(user, order.getCashNum(), updateUser.getCashNum(), 2,tradeType, order.getOrderId(), date);
	      		      						String tableName = Constants.CASHNUMDETAIL_TABLE;
	      		      						Map<String,Object> params = new HashMap<>();
	      		      						params.put("account",ad);
	      		      						params.put("tableName", tableName);
	      		      						Integer up1 =moneyMapper.save(params);
	      		      						if(up1!=null&&up1>0) {
			      		      					y++;
			      		      					updateRefereeUser.setCashNum(refereeUser.getCashNum()+order.getCashNum());
			      		      				 AccountDetail ad1 = cs.returnAccountDetail(refereeUser, order.getCashNum(), updateRefereeUser.getCashNum(), 1,tradeType, order.getOrderId(), date);
				   							 Map<String,Object> param1 = new HashMap<>();
				   							 param1.put("account",ad1);
				   							 param1.put("tableName", tableName);
				   							 Integer up2 =moneyMapper.save(param1);
					   							 if(up2!=null&&up2>0) {
			      		      					y1++;
			      		      				 }else error+="推荐人特卖权账户保存失败。";
				   							 }else error+="会员特卖权账户保存失败。";
		      		      					}else error+="会员特买权数不足；";
		      		      				}
		      		      				if(order.getGiveIntegral()>0) {
		      		      					double integral = ArithUtil.sub(refereeUser.getIntegral(),order.getGiveIntegral());
		      		      					if(integral>=0) {
			      		      					updateUser.setIntegral(ArithUtil.add(user.getIntegral(),order.getGiveIntegral()));
			      		      				AccountDetail ad = cs.returnAccountDetail(user, order.getGiveIntegral(), updateUser.getIntegral(), 1,tradeType, order.getOrderId(), date);
	      		      						String tableName = Constants.INTEGRALDETAIL_TABLE;
	      		      						Map<String,Object> params = new HashMap<>();
	      		      						params.put("account",ad);
	      		      						params.put("tableName", tableName);
	      		      						Integer up1 =moneyMapper.save(params);
	      		      						if(up1!=null&&up1>0) {
			      		      					y++;
			      		      					updateRefereeUser.setIntegral(integral);
			      		      				AccountDetail ad1 = cs.returnAccountDetail(refereeUser, order.getGiveIntegral(), updateRefereeUser.getIntegral(), 2,tradeType, order.getOrderId(), date);
				   							 Map<String,Object> param1 = new HashMap<>();
				   							 param1.put("account",ad1);
				   							 param1.put("tableName", tableName);
				   							 Integer up2 =moneyMapper.save(param1);
					   							 if(up2!=null&&up2>0) {
			      		      					y1++;
					   							 }else error+="推荐人积分账户保存失败。";
				   							 }else error+="会员积分账户保存失败。";
		      		      					}else error+="推荐人积分账户余额不足；";
		      		      				}else if(order.getIntegral()>0) {
		      		      					double integral = ArithUtil.sub(user.getIntegral(),order.getGiveIntegral());
		      		      					if(integral>=0) {
				      		      				updateUser.setIntegral(integral);
				      		      				AccountDetail ad = cs.returnAccountDetail(user, order.getIntegral(), updateUser.getIntegral(), 2,tradeType, order.getOrderId(), date);
		      		      						String tableName = Constants.INTEGRALDETAIL_TABLE;
		      		      						Map<String,Object> params = new HashMap<>();
		      		      						params.put("account",ad);
		      		      						params.put("tableName", tableName);
		      		      						Integer up1 =moneyMapper.save(params);
		      		      						if(up1!=null&&up1>0) {
			      		      					y++;
			      		      					updateRefereeUser.setIntegral(ArithUtil.sub(user.getIntegral(),order.getIntegral()));
			      		      				AccountDetail ad1 = cs.returnAccountDetail(refereeUser, order.getIntegral(), updateRefereeUser.getIntegral(), 1,tradeType, order.getOrderId(), date);
				   							 Map<String,Object> param1 = new HashMap<>();
				   							 param1.put("account",ad1);
				   							 param1.put("tableName", tableName);
				   							 Integer up2 =moneyMapper.save(param1);
					   							 if(up2!=null&&up2>0) {
			      		      					y1++;
			      		      				 }else error+="推荐人积分账户保存失败。";
				   							 }else error+="会员积分账户保存失败。";
		      		      					}else error+="会员积分账户余额不足；";
		      		      				}
		      		      				if(y>0) {
		      		      					int err = userMapper.updateUser(updateUser);
		      		      					if(err>0) {
		      		      						if(y1>0) {
		      		      							err = userMapper.updateUser(updateRefereeUser);
		      		      							if(err==0) error+="推荐人信息更新失败。";
		      		      						}
		      		      					}else error+="会员信息更新失败。";
		      		      				}
		      		      			//--end--会员等级更新以及特权数等发放
		      		      		}//--end error--
		      					
		      				}else error+="未查询到相应邀请人的信息。";
	      					
		      				}else error+="该订单类型不能采用向上级支付。";
	      					
	      				}else {

	      				//-start-库存更新（包括云仓）
	      				List<OrderDetail> olist = orderDetailMapper.selectByOrderId(order.getOrderId());
	      				if(olist!=null&&olist.size()>0) {
	      					for(int i=0;i<olist.size();i++) {
	      					if(olist.get(i).getNum()>0) {
	      						int err = productMapper.updateSubNum(olist.get(i).getProductId(), olist.get(i).getNum());
	      						if(err>0) {
	      							ProductStock stock = stockMapper.selectByProductId(olist.get(i).getProductId(), user.getUserId());
	      							if(stock!=null) {
	      								int er = stockMapper.updateNum(olist.get(i).getProductId(), user.getUserId(), olist.get(i).getNum());
	      								if(er==0) msg+=olist.get(i).getProductId()+"会员云仓更新失败；";
	      							}else {
	      								Product pro = productMapper.selectByProductId(olist.get(i).getProductId());

	      								stock = new ProductStock();
	      								stock.setUid(user.getId());
	      								stock.setUserId(user.getUserId());
	      								stock.setUserName(user.getUserName());
	      								stock.setPid(olist.get(i).getPid());
	      								stock.setProductId(olist.get(i).getProductId());
	      								stock.setProductName(olist.get(i).getProductName());
	      								stock.setSpecification(olist.get(i).getSpecification());
	      								stock.setPrice(olist.get(i).getProductPrice());
	      								stock.setNum(olist.get(i).getNum());
	      								stock.setTotalNum(olist.get(i).getNum());
	      								stock.setEntryTime(date);
	      								stock.setEndTime(date);
	      								if(pro!=null) stock.setImageUrl(pro.getImageUrl());
	      								int er = stockMapper.save(stock);
	      								if(er==0) error+=olist.get(i).getProductId()+"会员云仓更新失败；";
	      							}
	      						}else error+=olist.get(i).getProductId()+"库存不足或更新失败；";
	      					}	//end if(olist.get(i).getNum()>0) 
	      					}
	      					}else error+="未查询到相应的订单详情。";
	      				//-end-库存更新（包括云仓）
	      			//--start--奖金发放	
	      		if(error.equals("")) {
	      			if(order.getPrice()>0&&order.getPayType()-2!=0&&order.getOrderType()<3) {
	      				if(refereeUser!=null) {
	      				Param p1 = paramMapper.selectByName("直推分销奖");
	      				Param p2 = paramMapper.selectByName("间推分销奖");
	      				Param p3 = paramMapper.selectByName("其他分销奖");
	      				if(p1!=null) {
	      					if(p2!=null) {
	      						if(p3!=null) {
	      					double pro1 = 0;
	      					double pro2 = 0;
	      					if(refereeUser.getRankJoin()-7==0) {
	      						pro1 = p1.getAmount_7();
	      					}else if(refereeUser.getRankJoin()-6==0) {
	      						pro1 = p1.getAmount_6();
	      					}else if(refereeUser.getRankJoin()-5==0) {
	      						pro1 = p1.getAmount_5();
	      					}else if(refereeUser.getRankJoin()-4==0) {
	      						pro1 = p1.getAmount_4();
	      					}else if(refereeUser.getRankJoin()-3==0) {
	      						pro1 = p1.getAmount_3();
	      					}else if(refereeUser.getRankJoin()-2==0) {
	      						pro1 = p1.getAmount_2();
	      					}else if(refereeUser.getRankJoin()-1==0) {
	      						pro1 = p1.getAmount_1();
	      					}
	      					if(refereeUser.getRankJoin()-order.getRank()<0) 
	      						pro1 = p3.getAmount_1();
	      					double award1 = ArithUtil.mul(pro1, order.getPrice());
	      					if(award1>0) {
		      					RewardDetail reward = new RewardDetail();
		      					reward.setAward(award1);
		      					reward.setAmount(order.getPrice());
		      					reward.setUid(refereeUser.getId());
		      					reward.setUserId(refereeUser.getUserId());
		      					reward.setUserName(refereeUser.getUserName());
		      					reward.setRefereeId(user.getId());
		      					reward.setRefereeUserId(user.getUserId());
		      					reward.setRefereeUserName(user.getUserName());
		      					reward.setEntryTime(date);
		      					reward.setType(1);
		      					reward.setState(1);
		      					int err = rewardMapper.save(reward);
		      					if(err>0) {
			      					User up1 = new User();
			      					up1.setId(refereeUser.getId());
			      					up1.setVersion(refereeUser.getVersion());
			      					up1.setPreRmoney(ArithUtil.add(refereeUser.getPreRmoney(),award1));
		      						err = userMapper.updateUser(up1);
		      						if(err>0) {
		      							if(refereeUser.getRankJoin()-order.getRank()>=0) {
				      					User refereeUser1 = userMapper.selectUsersByIdForUpdate(refereeUser.getRefereeId());
				      					if(refereeUser1!=null){
				      						if(refereeUser1.getRankJoin()-7==0) {
					      						pro2 = p2.getAmount_7();
					      					}else if(refereeUser1.getRankJoin()-6==0) {
					      						pro2 = p2.getAmount_6();
					      					}else if(refereeUser1.getRankJoin()-5==0) {
					      						pro2 = p2.getAmount_5();
					      					}else if(refereeUser1.getRankJoin()-4==0) {
					      						pro2 = p2.getAmount_4();
					      					}else if(refereeUser1.getRankJoin()-3==0) {
					      						pro2 = p2.getAmount_3();
					      					}else if(refereeUser1.getRankJoin()-2==0) {
					      						pro2 = p2.getAmount_2();
					      					}else if(refereeUser1.getRankJoin()-1==0) {
					      						pro2 = p2.getAmount_1();
					      					}
				      						double award2 = ArithUtil.mul(pro2, order.getPrice());
					      					if(award2>0) {
						      					RewardDetail reward1 = new RewardDetail();
						      					reward1.setAward(award2);
						      					reward1.setAmount(order.getPrice());
						      					reward1.setUid(refereeUser1.getId());
						      					reward1.setUserId(refereeUser1.getUserId());
						      					reward1.setUserName(refereeUser1.getUserName());
						      					reward1.setRefereeId(user.getId());
						      					reward1.setRefereeUserId(user.getUserId());
						      					reward1.setRefereeUserName(user.getUserName());
						      					reward1.setEntryTime(date);
						      					reward1.setType(1);
						      					reward1.setState(1);
						      					 err = rewardMapper.save(reward1);
						      					if(err>0) {
							      					User up2 = new User();
							      					up2.setId(refereeUser1.getId());
							      					up2.setVersion(refereeUser1.getVersion());
							      					up2.setPreRmoney(ArithUtil.add(refereeUser1.getPreRmoney(),award2));
						      						err = userMapper.updateUser(up2);
						      						if(err==0) error+="间推人信息更新失败；";
						      					}else error+="间推人奖金记录保存失败；";
					      					}//end if(award2>0)
				      					}//end if(refereeUser1!=null)
		      							}
		      						}else error+="直推人信息更新失败；";
		      					}else error+="直推人奖金记录保存失败；";
	      					}
	      					}else error+="其他分销奖参数获取失败；";
	      						}else error+="间推分销奖参数获取失败；";
	      					}else error+="直推分销奖参数获取失败";
	      				}
	      				}
	      			}
	      			//团队奖计算
	      		if(error.equals("")) {
	      			Param p4 = paramMapper.selectByName("团队奖");
	      			if(p4!=null) {
	      				Map<String,Object> params = new HashMap<>();
	      				User sel = new User();
	      				sel.setState(1);
	      				params.put("user", sel);
	      				List<User> ulist = userMapper.selectUsersByList(params);
	      				int max = userMapper.maxId(params);
	      				if(max>0) {
	      					User[] users = new User[max+1];
	      					for(int i=0;i<ulist.size();i++) {
	      						int uid= ulist.get(i).getId();
	      						if(users[uid]==null) users[uid] = new User();
	      						users[uid].setId(uid);
	      						users[uid].setUserId(ulist.get(i).getUserId());
	      						users[uid].setUserName(ulist.get(i).getUserName());
	      						users[uid].setRefereeNode(ulist.get(i).getRefereeNode());
	      						users[uid].setPreRmoney(ulist.get(i).getPreRmoney());
	      						users[uid].setRankJoin(ulist.get(i).getRankJoin());
	      					}
	      					
	      					String[] str = StringUtil.notNull(user.getRefereeNode()).split(",");
	      					double s1 = p4.getAmount_4();
	      					double s2 = p4.getAmount_5();
	      					double s3 = p4.getAmount_6();
	      					double s4 = p4.getAmount_7();
	      					double t=0;
	      					for(int i=str.length-1;i>=0 ;i--) {
	      						if(!str[i].equals("")) {
	      							int sid = Integer.valueOf(str[i]);
	      							if(users[sid]!=null) {
	      								double award = 0 ;
	      								if(users[sid].getRankJoin()-4==0) {
	      									if(t<s1) {
	      										 award = ArithUtil.mul(s1, order.getPrice());
	      										 t=ArithUtil.add(t, s1);
	      									}
	      								}else if(users[sid].getRankJoin()-5==0) {
	      									if(t<s1) {
	      										 award = ArithUtil.mul(s2, order.getPrice());
	      										 t=ArithUtil.add(t, s2);
	      									}else if(t<s2) {
	      										double s = ArithUtil.sub(s2, s1);
	      										 award = ArithUtil.mul(s, order.getPrice());
	      										 t=ArithUtil.add(t, s);
	      									}
	      								}else if(users[sid].getRankJoin()-6==0) {
	      									if(t<s1) {
	      										 award = ArithUtil.mul(s3, order.getPrice());
	      										 t=ArithUtil.add(t, s3);
	      									}else if(t<s2) {
	      										double s = ArithUtil.sub(s3, s1);
	      										 award = ArithUtil.mul(s, order.getPrice());
	      										 t=ArithUtil.add(t, s);
	      									}else if(t<s3) {
	      										double s = ArithUtil.sub(s3, s2);
	      										 award = ArithUtil.mul(s, order.getPrice());
	      										 t=ArithUtil.add(t, s);
	      									}
	      								}else if(users[sid].getRankJoin()-7==0) {
	      									if(t<s1) {
	      										 award = ArithUtil.mul(s4, order.getPrice());
	      										 t=ArithUtil.add(t, s4);
	      									}else if(t<s2) {
	      										double s = ArithUtil.sub(s4, s1);
	      										 award = ArithUtil.mul(s, order.getPrice());
	      										 t=ArithUtil.add(t, s);
	      									}else if(t<s3) {
	      										double s = ArithUtil.sub(s4, s2);
	      										 award = ArithUtil.mul(s, order.getPrice());
	      										 t=ArithUtil.add(t, s);
	      									}else if(t<s4) {
	      										double s = ArithUtil.sub(s4, s3);
	      										 award = ArithUtil.mul(s, order.getPrice());
	      										 t=ArithUtil.add(t, s);
	      									}
	      								}
	      									if(award>0) {
		      									RewardDetail reward1 = new RewardDetail();
						      					reward1.setAward(award);
						      					reward1.setAmount(order.getPrice());
						      					reward1.setUid(users[sid].getId());
						      					reward1.setUserId(users[sid].getUserId());
						      					reward1.setUserName(users[sid].getUserName());
						      					reward1.setRefereeId(user.getId());
						      					reward1.setRefereeUserId(user.getUserId());
						      					reward1.setRefereeUserName(user.getUserName());
						      					reward1.setEntryTime(date);
						      					reward1.setType(2);
						      					reward1.setState(1);
						      					int err = rewardMapper.save(reward1);
						      					
						      					if(err==0) error+="团队奖明细保存失败。";
						      					String sqlstr = "update users set pre_rmoney=pre_rmoney+'"+award+"' where id='"+sid+"'";
						      					Integer up = userMapper.updateForSql(sqlstr);
						      					if(up!=null&up>0) {
						      						
						      					}else {
						      						error+="领导人奖金信息更新失败。";
						      					}
	      								}
	      							}
	      						}
	      					}
	      				}
	      				
	      			}else {
	      				error+="团队奖参数获取失败。";
	      			}
	      		}
	      		
	      		//--end--奖金发放
	      		//--start--会员等级更新以及特权数等发放
	      			if(error.equals("")) {
	      				User updateUser = new User();
	      				updateUser.setId(user.getId());
	      				updateUser.setVersion(user.getVersion());
	      				int y= 0;
	      				if(order.getOrderType()==1) {
	    	      			int rankJoin = order.getRank();
	    	      			if(user.getRankJoin()-rankJoin<0) {
	    	      				Param p1=paramMapper.selectByName("虚拟会员");
	    	      				if(p1!=null) {
	    	      				rankJoin = checkRankJoin(user,p1,rankJoin);
	    	      				updateUser.setRankJoin(rankJoin);
	    	      				y++;
	    	      				if(refereeUser!=null) {
	    	      				
	    	      					int count = rankJoin_up(user,refereeUser,p1,rankJoin);
	    	      					if(count==0)
	    	      						error+="推荐人推荐信息更新失败。";
	    	      				}
	    	      				}else error+="推荐会员信息指标参数获取失败。";
	    	      			}
	    	      		}
		      				if(order.getGiveCash()>0) {
		      					updateUser.setCash(ArithUtil.add(user.getCash(),order.getGiveCash()));
		      					AccountDetail ad = cs.returnAccountDetail(user, order.getGiveCash(), updateUser.getCash(), 1,tradeType, order.getOrderId(), date);
		      						String tableName = Constants.CASHDETAIL_TABLE;
		      						Map<String,Object> params = new HashMap<>();
		      						params.put("account",ad);
		      						params.put("tableName", tableName);
		      						Integer up1 =moneyMapper.save(params);
		      						if(up1!=null&&up1>0) {
		      							y++;
		      						}else error+="会员代金券账户明细保存失败。";
		      				}else if(order.getCash()>0) {
		      					updateUser.setCash(ArithUtil.add(user.getCash(),order.getCash()));
		      					AccountDetail ad = cs.returnAccountDetail(user, order.getCash(), updateUser.getCash(), 2,tradeType, order.getOrderId(), date);
	      						String tableName = Constants.CASHDETAIL_TABLE;
	      						Map<String,Object> params = new HashMap<>();
	      						params.put("account",ad);
	      						params.put("tableName", tableName);
	      						Integer up1 =moneyMapper.save(params);
	      						if(up1!=null&&up1>0) {
	      							y++;
	      						}else error+="会员代金券账户明细保存失败。";
		      				}
		      				if(order.getGiveCashNum()>0) {
		      					updateUser.setCashNum(user.getCashNum()+order.getGiveCashNum());
		      					AccountDetail ad = cs.returnAccountDetail(user, order.getGiveCashNum(), updateUser.getCashNum(), 1,tradeType, order.getOrderId(), date);
	      						String tableName = Constants.CASHNUMDETAIL_TABLE;
	      						Map<String,Object> params = new HashMap<>();
	      						params.put("account",ad);
	      						params.put("tableName", tableName);
	      						Integer up1 =moneyMapper.save(params);
	      						if(up1!=null&&up1>0) {
	      							y++;
	      						}else error+="会员特卖权账户明细保存失败。";
		      				}else if(order.getCashNum()>0) {
		      					updateUser.setCashNum(user.getCashNum()-order.getCashNum());
		      					AccountDetail ad = cs.returnAccountDetail(user, order.getCashNum(), updateUser.getCashNum(), 2,tradeType, order.getOrderId(), date);
	      						String tableName = Constants.CASHNUMDETAIL_TABLE;
	      						Map<String,Object> params = new HashMap<>();
	      						params.put("account",ad);
	      						params.put("tableName", tableName);
	      						Integer up1 =moneyMapper.save(params);
	      						if(up1!=null&&up1>0) {
	      							y++;
	      						}else error+="会员特卖权账户明细保存失败。";
		      				}
		      				if(order.getGiveIntegral()>0) {
		      					updateUser.setIntegral(ArithUtil.add(user.getIntegral(),order.getGiveIntegral()));
		      					AccountDetail ad = cs.returnAccountDetail(user, order.getGiveIntegral(), updateUser.getIntegral(), 1,tradeType, order.getOrderId(), date);
	      						String tableName = Constants.INTEGRALDETAIL_TABLE;
	      						Map<String,Object> params = new HashMap<>();
	      						params.put("account",ad);
	      						params.put("tableName", tableName);
	      						Integer up1 =moneyMapper.save(params);
	      						if(up1!=null&&up1>0) {
	      							y++;
	      						}else error+="会员积分账户明细保存失败。";
		      				}else if(order.getIntegral()>0) {
		      					updateUser.setIntegral(ArithUtil.sub(user.getIntegral(),order.getIntegral()));
		      					AccountDetail ad = cs.returnAccountDetail(user, order.getIntegral(), updateUser.getIntegral(), 2,tradeType, order.getOrderId(), date);
	      						String tableName = Constants.INTEGRALDETAIL_TABLE;
	      						Map<String,Object> params = new HashMap<>();
	      						params.put("account",ad);
	      						params.put("tableName", tableName);
	      						Integer up1 =moneyMapper.save(params);
	      						if(up1!=null&&up1>0) {
	      							y++;
	      						}else error+="会员积分账户明细保存失败。";
		      				}
		      				if(y>0) {
		      					int err = userMapper.updateUser(updateUser);
		      					if(err==0) error+="会员信息更新失败。";
		      				}
	      			
	      				}
	      			//--end--会员等级更新以及特权数等发放
	      			}//-end if(order.getPayType()-2==0) 
	      				//--end--更新订单
	    	      		//--start--会员等级更新以及特权数等发放
	    	      			if(error.equals("")) {
	    	      				Order updateOrder = new Order();
	    	      				updateOrder.setId(order.getId());
	    	      				updateOrder.setState(2);;
	    	      				updateOrder.setReviewerId(adminName);
	    	      				updateOrder.setReviewTime(date);
	    	      				Integer err = orderMapper.update(updateOrder);
	    	      				if(err!=null&err>0) {
	    	      					
	    	      				}else {
	    	      					error+="订单更新失败。";
	    	      				}
	    	      			}
	    	      			//end 更新订单
	    	      			if(error.equals("")) {
	    		      			if(order.getOrderType()>1) {
	    		      				OrderDelivery orderDelivery = new OrderDelivery();
	    		      				orderDelivery.setOrderId(order.getOrderId());
	    		      				orderDelivery.setOrderType(order.getOrderType());
	    		      				orderDelivery.setUid(order.getUid());
	    		      				orderDelivery.setUserId(order.getUserId());
	    		      				orderDelivery.setUserName(order.getUserName());
	    		      				orderDelivery.setPrice(order.getPrice());
	    		      				orderDelivery.setFee(order.getFee());
	    		      				orderDelivery.setOrderTime(order.getOrderTime());
	    		      				orderDelivery.setProvince(order.getProvince());
	    		      				orderDelivery.setCity(order.getCity());
	    		      				orderDelivery.setArea(order.getArea());
	    		      				orderDelivery.setAddress(order.getAddress());
	    		      				orderDelivery.setReceiver(order.getReceiver());
	    		      				orderDelivery.setPhone(order.getPhone());
	    		      				orderDelivery.setState(1);
	    		      				Integer up1 = orderDeliveryMapper.save(orderDelivery);
	    		      				if(up1!=null&&up1>0) {
	    		      					List<OrderDetail> olist = orderDetailMapper.selectByOrderId(order.getOrderId());
	    		      					List<OrderDeliveryDetail> odlist = new ArrayList<OrderDeliveryDetail>();
	    		      					for(int i=0;i<olist.size();i++) {
	    		      						OrderDeliveryDetail detail = new OrderDeliveryDetail();
	    		      						detail.setOrderId(orderDelivery.getOrderId());
	    		      						detail.setNum(olist.get(i).getNum());
	    		      						detail.setPid(olist.get(i).getPid());
	    		      						detail.setProductId(olist.get(i).getProductId());
	    		      						detail.setProductName(olist.get(i).getProductName());
	    		      						detail.setSpecification(olist.get(i).getSpecification());
	    		      						detail.setPrice(olist.get(i).getPrice());
	    		      						detail.setProductPrice(olist.get(i).getProductPrice());
	    		      						odlist.add(detail);
	    		      					}
	    		      					if(odlist!=null&&odlist.size()>0) {
	    		      						Map<String,Object> params = new HashMap<String,Object>();
	    		      						params.put("list", odlist);
	    		      						Integer up2 = orderDeliveryDetailMapper.insertAll(params);
	    		      						if(up2!=null&&up2>0) {
	    		      							error="";
	    		      						}else {
	    		      							error="发货单详情信息保存失败。";
	    		      						}
	    		      					}else {
    		      							error="订单详情获取信息失败。";
    		      						}
		    		      			}else {
		      							error="发货单信息保存失败。";
		      							
		      						}
	    		      			}
	    	      			}
	    	      			
	      			}//end if(user)
	      		}//end if(order)	
	      			
	      		if(error.equals("")) {
	      			msg="yes";
	      			sqlSession.commit();
	      		}else {
	      			msg = error;
	      			sqlSession.rollback();
	      		}
     		
     	}catch(Exception e){
     		e.printStackTrace();
     		msg="数据保存异常。"+e.getMessage();
     		sqlSession.rollback();
     	}finally{
     		sqlSession.close();
     	}
   		return msg;
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
				sql=" referee_num_2=referee_num_2-1,";
			}else if(user.getRankJoin()-3==0) {
				virtual = p1.getAmount_3().intValue();
				sql+=" referee_num_3=referee_num_3-1,";
			}else if(user.getRankJoin()-4==0) {
				virtual = p1.getAmount_4().intValue();
				sql+=" referee_num_4=referee_num_4-1,";
			}else if(user.getRankJoin()-5==0) {
				virtual = p1.getAmount_5().intValue();
				sql+=" referee_num_5=referee_num_5-1,";
			}else if(user.getRankJoin()-6==0) {
				virtual = p1.getAmount_6().intValue();
				sql+=" referee_num_6=referee_num_6-1,";
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
				sql+=" referee_num_5=referee_num_5+1,";
			}else if(rankJoin-6==0) {
				virtual1 = p1.getAmount_6().intValue();
				sql+=" referee_num_6=referee_num_6+1,";
			}
			int referee_virtual_num = virtual1-virtual+refereeUser.getRefereeVirtualNum();
			int refere_rankJoin = 0;
			
			if(refereeUser.getRankJoin()>0) {
				if(referee_virtual_num-p1.getAmount_6().intValue()>=0) {
					refere_rankJoin = 6;
				}else if(referee_virtual_num-p1.getAmount_5().intValue()>=0) {
					refere_rankJoin = 5;
				}else if(referee_virtual_num-p1.getAmount_4().intValue()>=0) {
					refere_rankJoin = 4;
				}else if(referee_virtual_num-p1.getAmount_3().intValue()>=0) {
					refere_rankJoin = 3;
				}else if(referee_virtual_num-p1.getAmount_2().intValue()>=0) {
					refere_rankJoin = 2;
				}else if(referee_virtual_num-p1.getAmount_1().intValue()>=0) {
					refere_rankJoin = 1;
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
				count=up;
				if(refereeUser.getRankJoin()-refere_rankJoin<0) {
					User refereeUser2 = userMapper.selectUsersById(refereeUser.getRefereeId());
					if(refereeUser2!=null) {
						count = rankJoin_up(refereeUser,refereeUser2,p1,refere_rankJoin);
					}
				}
			}else count=0;
			System.out.println("count:"+count);

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
