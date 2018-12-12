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
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderDeliveryDetailMapper;
import com.ssm.mapper.OrderDeliveryMapper;
import com.ssm.mapper.OrderDetailMapper;
import com.ssm.mapper.OrderDetailProductMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.ProductStockMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.Address;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDelivery;
import com.ssm.pojo.OrderDeliveryDetail;
import com.ssm.pojo.OrderDetailProduct;
import com.ssm.pojo.Param;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductStock;
import com.ssm.pojo.User;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;
import com.ssm.utils.SmsUtil;
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
   AddressMapper adrMapper = sqlSession.getMapper(AddressMapper.class);
   ProductStockMapper stockMapper = sqlSession.getMapper(ProductStockMapper.class);

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
    
    public OrderDelivery findById(Integer id){
    	OrderDelivery orders = null;
    	try{
    	orders = orderDeliveryMapper.selectById(id);
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
    
  
    
    
    public String reviewYes(String id,String adminName,String express,String expressNum,Timestamp date){
    	String msg="";
    	try{
    		OrderDelivery orderDelivery = orderDeliveryMapper.selectByIdForUpdate(Integer.valueOf(id));
    		if(orderDelivery!=null){
    			if(orderDelivery.getState()==2){
    				OrderDelivery od1 = new OrderDelivery();
    				od1.setId(orderDelivery.getId());
    				od1.setState(3);
    				od1.setExpress(express);
    				od1.setExpressNum(expressNum);
    				od1.setReviewerId(adminName);
    				od1.setReviewTime(date);;
    				Integer up1 = orderDeliveryMapper.update(od1);
    				if(up1!=null&&up1>0){
    						SmsUtil.sendSms("尊敬的顾客，您好！您的订单号为"+orderDelivery.getOrderId()+"的商品已经发货，请及时查收。", orderDelivery.getPhone());
    					msg="yes";
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
    
    public String confirmYes(String adminName,String id,Timestamp date){
    	String msg="";
    	try{
    		OrderDelivery orderDelivery = orderDeliveryMapper.selectByIdForUpdate(Integer.valueOf(id));
    		if(orderDelivery!=null){
    			if(orderDelivery.getState()==1){
    				OrderDelivery od1 = new OrderDelivery();
    				od1.setId(orderDelivery.getId());
    				od1.setState(2);
    				od1.setConfirmId(adminName);
    				od1.setConfirmTime(date);;
    				Integer up1 = orderDeliveryMapper.update(od1);
    				if(up1!=null&&up1>0){
    					
    					msg="yes";
    					sqlSession.commit();
    				}else{
    					msg = "配货单状态更新失败。";
    					sqlSession.rollback();
    				}
    			}else{
					msg = "配货单当前状态不能进行确认。";
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
    
    public String confirmYes(String id){
    	String msg="";
    	try{
    		OrderDelivery orderDelivery = orderDeliveryMapper.selectByOrderIdForUpdate(id);
    		if(orderDelivery!=null){
    			if(orderDelivery.getState()==3){
    				Integer up1 = orderDeliveryMapper.updateForState(4,orderDelivery.getId());
    				if(up1!=null&&up1>0){
    					msg="yes";
    					sqlSession.commit();
    				}else{
    					msg = "配货单状态更新失败。";
    					sqlSession.rollback();
    				}
    			}else{
					msg = "配货单当前状态不能进行确认。";
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
    
  
    
    public String reviewBack(String id,String adminName,Timestamp date){
    	String msg="";
    	try{
    		OrderDelivery orderDelivery = orderDeliveryMapper.selectByOrderIdForUpdate(id);
    		String error ="";
    		if(orderDelivery!=null){
    			if(orderDelivery.getState()==2){
    	    		Order order = orderMapper.selectByOrderIdForUpdate(orderDelivery.getOrderId());
    	    		if(order!=null){
    	    			if(order.getState()>3){
    	    				Order order1 = new Order();
    	    				order1.setId(order.getId());
    	    				order1.setState(3);
    	    				order1.setReviewerId(adminName);
    	    				order1.setReviewTime(date);
    	    				Integer up1 = orderMapper.update(order1);
    	    				if(up1==null||up1==0){
    	    					error = "订单信息更新失败。";
    	    				}
    	    			}
    	    				
    	    				if(error.equals("")){
    	    					OrderDelivery ody = new OrderDelivery();
    	    					ody.setId(orderDelivery.getId());
        	    				ody.setState(1);
        	    				ody.setReviewerId(adminName);
        	    				ody.setReviewTime(date);
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
    	    		Order order = orderMapper.selectByOrderIdForUpdate(orderDelivery.getOrderId());
    	    		if(order!=null){
    	    			if(order.getState()==4){
    	    				String sql = "select * from order_delivery where state>'0' and state<'3' and to_order_id='"+order.getOrderId()+"' and orderId!='"+orderDelivery.getOrderId()+"'";
    	    				List<OrderDelivery> odylist = orderDeliveryMapper.selectListBySql(sql);
    	    				if(odylist==null||odylist.size()==0){
	    	    				Order order1 = new Order();
	    	    				order1.setId(order.getId());
	    	    				order1.setState(5);
	    	    				order1.setReviewerId(adminName);
	    	    				order1.setReviewTime(date);
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
        	    				ody.setReviewerId(adminName);
        	    				ody.setReviewTime(date);
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
    	    		Order order = orderMapper.selectByOrderIdForUpdate(orderDelivery.getOrderId());
    	    		if(order!=null){
    	    			if(order.getState()==5){
    	    			
	    	    				Order order1 = new Order();
	    	    				order1.setId(order.getId());
	    	    				order1.setState(4);
	    	    				order1.setReviewerId(adminName);
	    	    				order1.setReviewTime(date);
	    	    				Integer up1 = orderMapper.update(order1);
	    	    				if(up1==null||up1==0){
	    	    					error = "订单信息更新失败。";
	    	    				}
    	    			}
    	    				if(error.equals("")){
    	    					OrderDelivery ody = new OrderDelivery(); 
    	    					ody.setId(orderDelivery.getId());
        	    				ody.setState(2);
        	    				ody.setReviewerId(adminName);
        	    				ody.setReviewTime(date);
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
    
    public String saveOrders(String userId,String adrId,String orderId,double fee,List<ProductStock> plist,Timestamp date){
    	String str ="";
    	try{
    		User user1 = userMapper.selectUsersByUserId(userId);
    		if(user1!=null){
    					
    			if(!adrId.equals("")) {
    				Address adr = new Address();
    				adr = adrMapper.selectById(Integer.valueOf(adrId));
				OrderDelivery orders = new OrderDelivery();
				orders.setOrderId(orderId);
				orders.setUid(user1.getId());
				orders.setUserId(user1.getUserId());
				orders.setUserName(user1.getUserName());
				orders.setOrderType(1);
				orders.setState(1);
				orders.setOrderTime(date);
				orders.setProvince(adr.getProvince());
				orders.setCity(adr.getCity());
				orders.setArea(adr.getArea());
				orders.setAddress(adr.getAddress());
				orders.setReceiver(adr.getReciver());
				orders.setPhone(adr.getPhone());
				orders.setFee(fee);
    		List<OrderDeliveryDetail> olist = new ArrayList<OrderDeliveryDetail>();
			double totalprice = 0;
			String error="";
			for (int i = 0; i < plist.size(); i++) {
				if (plist.get(i).getNum()>0) {
					ProductStock pd = stockMapper.selectById(plist.get(i).getId());
					int num =plist.get(i).getNum();
					if(pd!=null){
							OrderDeliveryDetail od = new OrderDeliveryDetail();
							od.setPid(pd.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(num);
							od.setProductId(pd.getProductId());
							od.setProductName(pd.getProductName());
							od.setProductPrice(pd.getPrice());
							od.setSpecification(pd.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							olist.add(od);
							totalprice = ArithUtil.add(totalprice
									,od.getPrice());
							if(pd.getNum()-num<0) {
								error+=pd.getProductId()+"库存不足；";
								
							}
							Integer up = stockMapper.updateSubNum(od.getProductId(), user1.getUserId(), od.getNum());
							if(up!=null&&up>0) {
								
							}else {
								error+=od.getProductId()+"云仓库存更新失败。";
							}
						}
					}
			}
			orders.setPrice(totalprice);
			if(error.equals("")) {
	    		Map<String,Object> params = new HashMap<>();
	    		if(orderDeliveryMapper.save(orders)>0){
	    			params.put("list",olist);
	    			if(orderDeliveryDetailMapper.insertAll(params)>0){
	    				str="yes";
	    				sqlSession.commit();
	    			}else{
		    			str="配货单详情保存失败。";
		    			sqlSession.rollback();
		    		}
	    		}else{
	    			str="配货信息保存失败。";
	    			sqlSession.rollback();
	    		}
			
			}else{
    			str=error;
    			sqlSession.rollback();
    		}
			
    		    }else{
    		    	str ="收货地址信息获取失败。";
    		    }
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
