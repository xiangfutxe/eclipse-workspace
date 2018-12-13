package com.ssm.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderDeliveryDetailMapper;
import com.ssm.mapper.OrderDeliveryMapper;
import com.ssm.mapper.OrderDetailMapper;
import com.ssm.mapper.OrderDetailProductMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDelivery;
import com.ssm.pojo.OrderDeliveryDetail;
import com.ssm.pojo.OrderDetail;
import com.ssm.pojo.OrderDetailProduct;
import com.ssm.pojo.Product;
import com.ssm.pojo.User;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.StringUtil;

public class OrderDeliveryDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
   OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
   UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
   MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);
   OrderDetailMapper orderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
   OrderDeliveryDetailMapper orderDeliveryDetailMapper = sqlSession.getMapper(OrderDeliveryDetailMapper.class);
   OrderDeliveryMapper orderDeliveryMapper = sqlSession.getMapper(OrderDeliveryMapper.class);
   OrderDetailProductMapper orderDetailProductMapper = sqlSession.getMapper(OrderDetailProductMapper.class);

   ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
    
    public Pager findOrderByPage(OrderDelivery order,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("order_delivery",order);
		int recordCount =orderDeliveryMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<OrderDelivery> orders =orderDeliveryMapper.selectByPage(params);
		pager.setResultList(orders);
		sqlSession.close();
		return pager;
	}
    
    public List<OrderDeliveryDetail> findDetailByList(String orderId){
		List<OrderDeliveryDetail> orders =orderDeliveryDetailMapper.selectByOrderId(orderId);
		sqlSession.close();
		return orders;
	}
    
    public List<OrderDelivery> findByList(String ToOrderId){
		List<OrderDelivery> orders =orderDeliveryMapper.selectListByToOrderId(ToOrderId);
		sqlSession.close();
		return orders;
	}
    
    public List<OrderDelivery> findByListforSql(String sql){
  		List<OrderDelivery> orders =orderDeliveryMapper.selectListBySql(sql);
  		sqlSession.close();
  		return orders;
  	}
    
    public OrderDelivery findByOrderId(String orderId){
    	OrderDelivery orders = orderDeliveryMapper.selectByOrderId(orderId);
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
    
    
    public int update(OrderDelivery order){
    	int num =0;
    	try{
    		Integer result = orderDeliveryMapper.update(order);
    		if(result!=null) num = result;
    		sqlSession.commit();
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
  		sqlSession.close();
    	}
  		return num;
  	}
    
    public int deliveryWaitReBack(Integer id){
    	int num=0;
    	try{
    		Order order = orderMapper.selectByIdForUpdate(id);
    		if(order.getDeliveryNum()==0){
	    		Integer result = orderMapper.updateForState(1, id);
	    		if(result!=null) num = result;
	    		sqlSession.commit();
    		}else{
    			num=2;
    			sqlSession.rollback();
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		sqlSession.rollback();
    	}finally{
    		sqlSession.close();
    	}
  		return num;
  	}
    
   
    
    
    public String deliveryYes(String id,String express,String expressNum,String adminName,Timestamp date){
    	String msg="";
    	try{
    		OrderDelivery orderDelivery = orderDeliveryMapper.selectByOrderIdForUpdate(id);
    		if(orderDelivery!=null){
    			if(orderDelivery.getState()==1){
					List<OrderDelivery> olist = orderDeliveryMapper.selectByToOrderId(orderDelivery.getToOrderId(), 1);
    				OrderDelivery od1 = new OrderDelivery();
    				od1.setId(orderDelivery.getId());
    				od1.setState(2);
    				od1.setExpress(express);
    				od1.setExpressNum(expressNum);
    				od1.setAdminByDeliveryId(adminName);
    				od1.setDeliveryTime(date);
    				Integer up1 = orderDeliveryMapper.update(od1);
    				if(up1!=null&&up1>0){
    					if(olist.size()==1){
    						orderMapper.updateStateForOrderId(4, orderDelivery.getToOrderId(), 3);
    					}
    					msg=id+"配货单配货成功，请通知审核员审核。";
    					sqlSession.commit();
    				}else{
    					msg = "配货单状态更新失败。";
    					sqlSession.rollback();
    				}
    			}else{
					msg = "配货单当前状态不能进行发货。";
					sqlSession.rollback();
				}
    		}else{
				msg = "配货单信息获取失败。";
				sqlSession.rollback();
			}
    		
    	}catch(Exception e){
    		msg="数据操作有误，请重试。";
    		e.printStackTrace();
    		sqlSession.rollback();
    	}finally{
    		sqlSession.close();
    	}
  		return msg;
  	}
    
    public String deliveryBack(String id,String adminName,Timestamp date){
    	String msg="";
    	try{
    		OrderDelivery orderDelivery = orderDeliveryMapper.selectByOrderIdForUpdate(id);
    		String error ="";
    		if(orderDelivery!=null){
    			if(orderDelivery.getState()==1){
    				System.out.println("orderId:"+orderDelivery.getToOrderId());
    	    		Order order = orderMapper.selectByOrderIdForUpdate(orderDelivery.getToOrderId());
    	    		if(order!=null){
    					List<OrderDelivery> olist = orderDeliveryMapper.selectByToOrderId(orderDelivery.getToOrderId(), 1);
    					List<OrderDeliveryDetail> odlist = orderDeliveryDetailMapper.selectByOrderIdForUpdate(orderDelivery.getOrderId());
    					int totalNum =0;
    					for(int i=0;i<odlist.size();i++){
							totalNum =totalNum+odlist.get(i).getNum();
							Integer up1 = orderDetailProductMapper.updateNumByOrderId(odlist.get(i).getNum(), odlist.get(i).getOid());
							if(up1!=null&&up1>0){
								
							}else{
								error +=odlist.get(i).getOrderId()+"配货单数量大于订单数量；<br>";
							}
    					}
    	    	if(error.equals("")){
    				Order od1 = new Order();
    				od1.setId(order.getId());
    				od1.setState(2);
    				od1.setDeliveryNum(order.getDeliveryNum()-totalNum);
    				Integer up1 = orderMapper.update(od1);
    				if(up1!=null&&up1>0){
    					Integer up2 = orderDeliveryMapper.deleteByOrderId(orderDelivery.getOrderId());
    					if(up2!=null&&up2>0){
    						Integer up3 = orderDeliveryDetailMapper.deleteByOrderId(orderDelivery.getOrderId());
    						if(up3!=null&&up3>0){
		    					msg=id+"配货单回退成功。";
		    					sqlSession.commit();
    						}else{
    	    					msg = "配货单详情删除失败。";
    	    					sqlSession.rollback();
    	    				}
    				}else{
    					msg = "配货单信息删除失败。";
    					sqlSession.rollback();
    				}
    			}else{
					msg = "订单信息更新失败。";
					sqlSession.rollback();
				}
    	    	}else{
    				msg = error;
    				sqlSession.rollback();
    			}
    		}else{
				msg = "订单信息获取失败。";
				sqlSession.rollback();
			}
    			}else{
    				msg = "配货单当前状态不能进行该操作。";
    				sqlSession.rollback();
    			}
    		}else{
				msg = "配货单信息获取失败。";
				sqlSession.rollback();
			}
    		
    	}catch(Exception e){
    		msg="数据操作有误，请重试。";
    		e.printStackTrace();
    		sqlSession.rollback();
    	}finally{
    		sqlSession.close();
    	}
  		return msg;
  	}
    
    public List<OrderDetail> findOrderDetailByList(Order order){
		Map<String,Object> params = new HashMap<>();
		params.put("order",order);
		
		List<OrderDetail> orders =orderMapper.selectOrderDetailByList(params);
		sqlSession.close();
		return orders;
	}
    
    public String saveEmoneyOrders(User user,Order orders,String[] pid,String[] numstr){
    	String str ="";
    	try{
    		user = userMapper.selectByUserIdForUpdate(user.getUserId());
    		if(user!=null){
    		List<OrderDetail> olist = new ArrayList<OrderDetail>();
			double totalprice = 0;
			double totalpv = 0;
			for (int i = 0; i < pid.length; i++) {
				if (!(numstr.equals("") || numstr == null)) {
					if (Integer.valueOf(numstr[i]) > 0) {
					Product pd = productMapper.selectById(Integer.valueOf(pid[i]));
					if(pd!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(pd.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(Integer.valueOf(numstr[i]));
							od.setProductId(pd.getProductId());
							od.setProductName(pd.getProductName());
							od.setProductPrice(pd.getPrice());
							od.setProductPv(pd.getPv());
							od.setProductType(pd.getProductType());
							od.setType(pd.getType());
							od.setSpecification(pd.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
							totalprice = ArithUtil.add(totalprice
									,od.getPrice());
							totalpv = ArithUtil.add(totalpv , od.getPv());
							
						}
					}
				}
			}
			orders.setPrice(totalprice);
			orders.setPv(totalpv);
			if(ArithUtil.sub(user.getEmoney(), totalprice)>=0){
				orders.setPrice(totalprice);
				orders.setPv(totalpv);
				AccountDetail ad  = new AccountDetail();
				 ad.setId(user.getId());
	    		ad.setUserId(user.getUserId());
	    		ad.setUserName(user.getUserName());
	    		ad.setAmount(totalprice);
	    		ad.setBalance(ArithUtil.sub(user.getEmoney(), totalprice));
	    		ad.setTradeType("零售购物");
	    		ad.setSummary("零售购物");
	    		ad.setPayType(2);
	    		ad.setEntryTime(orders.getOrderTime());
	    		User user_d = new User();
	    		user_d.setId(user.getId());
	    		user_d.setEmoney(ad.getBalance());
	    		Map<String,Object> params = new HashMap<>();
    			params.put("account",ad);
    			params.put("tableName", Constants.EMONEYDETAIL_TABLE);
    	if(userMapper.updateUser(user_d)>0){
    		if(moneyMapper.save(params)>0){
	    		if(orderMapper.save(orders)>0){
	    			params.put("list",olist);
	    			if(orderDetailMapper.insertAll(params)>0){
	    			str="yes";
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
	    		str="资金明细保存失败。";
	    		sqlSession.rollback();
			}
    	}else{
    		str="会员账户信息更新失败。";
    		sqlSession.rollback();
		}
			}else{
	    		str="会员账户余额不足。";
	    		sqlSession.rollback();
			}
    }else{
    	str ="会员信息获取失败。";
    }
    	}catch(Exception e){
    		e.printStackTrace();
    		str ="系统繁忙中，请稍后再试。";
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
		return str;
	}
    
    public String saveDmoneyOrders(User user,Order orders,String[] pid,String[] numstr){
    	String str ="";
    	try{
    		user = userMapper.selectByUserIdForUpdate(user.getUserId());
    		if(user!=null){
    		List<OrderDetail> olist = new ArrayList<OrderDetail>();
			double totalprice = 0;
			double totalpv = 0;
			for (int i = 0; i < pid.length; i++) {
				if (!(numstr.equals("") || numstr == null)) {
					if (Integer.valueOf(numstr[i]) > 0) {
					Product pd = productMapper.selectById(Integer.valueOf(pid[i]));
					if(pd!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(pd.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(Integer.valueOf(numstr[i]));
							od.setProductId(pd.getProductId());
							od.setProductName(pd.getProductName());
							od.setProductPrice(pd.getPrice());
							od.setProductPv(pd.getPv());
							od.setProductType(pd.getProductType());
							od.setType(pd.getType());
							od.setSpecification(pd.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
							totalprice = ArithUtil.add(totalprice
									,od.getPrice());
							totalpv = ArithUtil.add(totalpv , od.getPv());
							
						}
					}
				}
			}
			orders.setPrice(totalprice);
			orders.setPv(totalpv);
			if(ArithUtil.sub(user.getDmoney(), totalprice)>=0){
				orders.setPrice(totalprice);
				orders.setPv(totalpv);
				AccountDetail ad  = new AccountDetail();
				 ad.setId(user.getId());
	    		ad.setUserId(user.getUserId());
	    		ad.setUserName(user.getUserName());
	    		ad.setAmount(totalprice);
	    		ad.setBalance(ArithUtil.sub(user.getDmoney(), totalprice));
	    		ad.setTradeType("复消购物");
	    		ad.setSummary("复消购物");
	    		ad.setPayType(2);
	    		ad.setEntryTime(orders.getOrderTime());
	    		User user_d = new User();
	    		user_d.setId(user.getId());
	    		user_d.setDmoney(ad.getBalance());
	    		Map<String,Object> params = new HashMap<>();
    			params.put("account",ad);
    			params.put("tableName", Constants.DMONEYDETAIL_TABLE);
    	if(userMapper.updateUser(user_d)>0){
    		if(moneyMapper.save(params)>0){
	    		if(orderMapper.save(orders)>0){
	    			params.put("list",olist);
	    			if(orderDetailMapper.insertAll(params)>0){
	    			str="yes";
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
	    		str="资金明细保存失败。";
	    		sqlSession.rollback();
			}
    	}else{
    		str="会员账户信息更新失败。";
    		sqlSession.rollback();
		}
			}else{
	    		str="会员账户余额不足。";
	    		sqlSession.rollback();
			}
    }else{
    	str ="会员信息获取失败。";
    }
    	}catch(Exception e){
    		e.printStackTrace();
    		str ="系统繁忙中，请稍后再试。";
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
		return str;
	}
    
    public String reviewBack(String id,String adminName,Timestamp date){
    	String msg="";
    	try{
    		OrderDelivery orderDelivery = orderDeliveryMapper.selectByOrderIdForUpdate(id);
    		String error ="";
    		if(orderDelivery!=null){
    			if(orderDelivery.getState()==2){
    	    		Order order = orderMapper.selectByOrderIdForUpdate(orderDelivery.getToOrderId());
    	    		if(order!=null){
    	    			if(order.getState()>3){
    	    				Order order1 = new Order();
    	    				order1.setId(order.getId());
    	    				order1.setState(3);
    	    				order1.setAdminByReviewerId(adminName);
    	    				order1.setReviewerTime(date);
    	    				Integer up1 = orderMapper.update(order1);
    	    				if(up1==null||up1==0){
    	    					error = "订单信息更新失败。";
    	    				}
    	    			}
    	    				
    	    				if(error.equals("")){
    	    					OrderDelivery ody = new OrderDelivery();
    	    					ody.setId(orderDelivery.getId());
        	    				ody.setState(1);
        	    				ody.setAdminByReviewerId(adminName);
        	    				ody.setReviewerTime(date);
        	    				Integer up2 = orderDeliveryMapper.update(ody);
        	    				if(up2!=null&&up2>0){
        	    					msg=id+"配货单审核不退过，已退回。";
    		    					sqlSession.commit();
        	    				}else{
        	    					msg = "配货单信息更新失败。";
        	    					sqlSession.rollback();
        	    				}
    					
		    	    	}else{
		    				msg = error;
		    				sqlSession.rollback();
		    			}
    		}else{
				msg = "订单信息获取失败。";
				sqlSession.rollback();
			}
    			}else{
    				msg = "配货单当前状态不能进行该操作。";
    				sqlSession.rollback();
    			}
    		}else{
				msg = "配货单信息获取失败。";
				sqlSession.rollback();
			}
    		
    	}catch(Exception e){
    		msg="数据操作有误，请重试。";
    		e.printStackTrace();
    		sqlSession.rollback();
    	}finally{
    		sqlSession.close();
    	}
  		return msg;
  	}
    
    public String reviewYes(String id,String adminName,Timestamp date){
    	String msg="";
    	try{
    		OrderDelivery orderDelivery = orderDeliveryMapper.selectByOrderIdForUpdate(id);
    		String error ="";
    		if(orderDelivery!=null){
    			if(orderDelivery.getState()==2){
    	    		Order order = orderMapper.selectByOrderIdForUpdate(orderDelivery.getToOrderId());
    	    		if(order!=null){
    	    			if(order.getState()==4){
    	    				String sql = "select * from order_delivery where state>'0' and state<'3' and to_order_id='"+order.getOrderId()+"' and orderId!='"+orderDelivery.getOrderId()+"'";
    	    				List<OrderDelivery> odylist = orderDeliveryMapper.selectListBySql(sql);
    	    				if(odylist==null||odylist.size()==0){
	    	    				Order order1 = new Order();
	    	    				order1.setId(order.getId());
	    	    				order1.setState(5);
	    	    				order1.setAdminByReviewerId(adminName);
	    	    				order1.setReviewerTime(date);
	    	    				Integer up1 = orderMapper.update(order1);
	    	    				if(up1==null||up1==0){
	    	    					error = "订单信息更新失败。";
	    	    				}
    	    				}
    	    			}
    	    				
    	    				if(error.equals("")){
    	    					OrderDelivery ody = new OrderDelivery(); 
    	    					ody.setId(orderDelivery.getId());
        	    				ody.setState(3);
        	    				ody.setAdminByReviewerId(adminName);
        	    				ody.setReviewerTime(date);
        	    				Integer up2 = orderDeliveryMapper.update(ody);
        	    				if(up2!=null&&up2>0){
        	    					List<OrderDeliveryDetail> odlist = orderDeliveryDetailMapper.selectByOrderId(orderDelivery.getOrderId());
        	    					String error1="";
        	    					if(odlist!=null){
        	    						for(int i=0;i<odlist.size();i++){
        	    							Integer up3 =productMapper.updateSubNum(odlist.get(i).getProductId(), odlist.get(i).getNum());
        	    							if(up3==null||up3==0) error1+=odlist.get(i).getProductId()+"库存不足；<br>";
        	    						}
        	    					}
        	    					if(error1.equals("")){
	        	    					msg=id+"配货单审核通过，相应库存已扣除。";
	    		    					sqlSession.commit();
        	    					}else{
        	    						msg = error1;
        	    						sqlSession.rollback();
        	    					}
        	    				}else{
        	    					msg = "配货单信息更新失败。";
        	    					sqlSession.rollback();
        	    				}
    					
		    	    	}else{
		    				msg = error;
		    				sqlSession.rollback();
		    			}
    		}else{
				msg = "订单信息获取失败。";
				sqlSession.rollback();
			}
    			}else{
    				msg = "配货单当前状态不能进行该操作。";
    				sqlSession.rollback();
    			}
    		}else{
				msg = "配货单信息获取失败。";
				sqlSession.rollback();
			}
    		
    	}catch(Exception e){
    		msg="数据操作有误，请重试。";
    		e.printStackTrace();
    		sqlSession.rollback();
    	}finally{
    		sqlSession.close();
    	}
  		return msg;
  	}
    
    public String reviewReBack(String id,String adminName,Timestamp date){
    	String msg="";
    	try{
    		OrderDelivery orderDelivery = orderDeliveryMapper.selectByOrderIdForUpdate(id);
    		String error ="";
    		if(orderDelivery!=null){
    			if(orderDelivery.getState()==3){
    	    		Order order = orderMapper.selectByOrderIdForUpdate(orderDelivery.getToOrderId());
    	    		if(order!=null){
    	    			if(order.getState()==5){
    	    			
	    	    				Order order1 = new Order();
	    	    				order1.setId(order.getId());
	    	    				order1.setState(4);
	    	    				order1.setAdminByReviewerId(adminName);
	    	    				order1.setReviewerTime(date);
	    	    				Integer up1 = orderMapper.update(order1);
	    	    				if(up1==null||up1==0){
	    	    					error = "订单信息更新失败。";
	    	    				}
    	    			}
    	    				if(error.equals("")){
    	    					OrderDelivery ody = new OrderDelivery(); 
    	    					ody.setId(orderDelivery.getId());
        	    				ody.setState(2);
        	    				ody.setAdminByReviewerId(adminName);
        	    				ody.setReviewerTime(date);
        	    				Integer up2 = orderDeliveryMapper.update(ody);
        	    				if(up2!=null&&up2>0){
        	    					List<OrderDeliveryDetail> odlist = orderDeliveryDetailMapper.selectByOrderId(orderDelivery.getOrderId());
        	    					String error1="";
        	    					if(odlist!=null){
        	    						for(int i=0;i<odlist.size();i++){
        	    							Integer up3 =productMapper.updateNum(odlist.get(i).getProductId(), odlist.get(i).getNum());
        	    							if(up3==null||up3==0) error1+=odlist.get(i).getProductId()+"库存增加失败;<br>";
        	    						}
        	    					}
        	    					if(error1.equals("")){
	        	    					msg=id+"配货单审核通过已撤销，相应库存已增加。";
	    		    					sqlSession.commit();
        	    					}else{
        	    						msg = error1;
        	    						sqlSession.rollback();
        	    					}
        	    				}else{
        	    					msg = "配货单信息更新失败。";
        	    					sqlSession.rollback();
        	    				}
    					
		    	    	}else{
		    				msg = error;
		    				sqlSession.rollback();
		    			}
    		}else{
				msg = "订单信息获取失败。";
				sqlSession.rollback();
			}
    			}else{
    				msg = "配货单当前状态不能进行该操作。";
    				sqlSession.rollback();
    			}
    		}else{
				msg = "配货单信息获取失败。";
				sqlSession.rollback();
			}
    		
    	}catch(Exception e){
    		msg="数据操作有误，请重试。";
    		e.printStackTrace();
    		sqlSession.rollback();
    	}finally{
    		sqlSession.close();
    	}
  		return msg;
  	}
	
}
