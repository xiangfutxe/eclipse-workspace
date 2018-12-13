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
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.PromotionMapper;
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
import com.ssm.pojo.Promotion;
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
   AddressMapper adrMapper = sqlSession.getMapper(AddressMapper.class);
   PromotionMapper proMapper = sqlSession.getMapper(PromotionMapper.class);
   ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);
   CenterMapper centerMapper = sqlSession.getMapper(CenterMapper.class);
   OrderDetailMapper orderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
   OrderDeliveryDetailMapper orderDeliveryDetailMapper = sqlSession.getMapper(OrderDeliveryDetailMapper.class);
   OrderDeliveryMapper orderDeliveryMapper = sqlSession.getMapper(OrderDeliveryMapper.class);
   OrderDetailProductMapper orderDetailProductMapper = sqlSession.getMapper(OrderDetailProductMapper.class);
   AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
   ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
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
		double[] sum ={0,0};
		if(od!=null){
			sum[0] = od.getPrice();
			sum[1] = od.getPv();
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
    
    public String deliveryWaitSave(HttpServletRequest request,Integer id,String deliveryId,String adminName,Timestamp date){
    	String msg="";
    	try{
    		Order orders = orderMapper.selectByIdForUpdate(id);
    		if(orders!=null){
    		List<OrderDetailProduct> olist =  orderDetailProductMapper.selectByOrderIdForUpdate(orders.getOrderId());
    		List<OrderDetailProduct> olist1 =  new ArrayList<OrderDetailProduct>();

    		double totalPrice = 0;
			double totalPv = 0;
			int totalNum=0;
			String error="";
			List<OrderDeliveryDetail> odlist = new  ArrayList<OrderDeliveryDetail>();
    		for(int i=0;i<olist.size();i++){
    			int oid =olist.get(i).getId();
    			String numstr = StringUtil.notNull(request.getParameter("num"+oid));
    			if(!numstr.equals("")){
					int num = Integer.valueOf(numstr);
					if(num>0){
					totalNum = totalNum+num;
					orders.setDeliveryNum(orders.getDeliveryNum()+num);
					OrderDeliveryDetail od = new OrderDeliveryDetail();
					od.setPid(olist.get(i).getPid());
					od.setOid(olist.get(i).getId());
					od.setOrderId(deliveryId);
					od.setProductId(olist.get(i).getProductId());
					od.setProductName(olist.get(i).getProductName());
					od.setSpecification(olist.get(i).getSpecification());
					od.setProductPrice(olist.get(i).getProductPrice());
					od.setType(olist.get(i).getType());
					od.setProductType(olist.get(i).getProductType());
					od.setProductPv(olist.get(i).getProductPv());
					od.setPrice(ArithUtil.mul(od.getProductPrice(),num));
					od.setPv(ArithUtil.mul(od.getProductPv(),num));
					od.setNum(num);
					od.setDeliveryNum(od.getDeliveryNum());
					totalPrice = ArithUtil.add(totalPrice, od.getPrice());
					totalPv = ArithUtil.add(totalPv, od.getPv());
					OrderDetailProduct op = new OrderDetailProduct();
					op.setId(olist.get(i).getId());
					op.setDeliveryNum(olist.get(i).getDeliveryNum()+num);
					olist1.add(op);
					if(op.getDeliveryNum()-olist.get(i).getNum()>0){
						error=olist.get(i)+"配货总量超出范围；<br>";
					}else{
						odlist.add(od);
					}
    			}
				
    			}
    		}
    		if(totalNum>0){
    		if(error.equals("")){
    		OrderDelivery orderDelivery = new OrderDelivery();
    		orderDelivery.setToOid(orders.getId());
    		orderDelivery.setToOrderId(orders.getOrderId());
    		orderDelivery.setuId(orders.getuId());
    		orderDelivery.setOrderId(deliveryId);
    		orderDelivery.setUserId(orders.getUserId());
    		orderDelivery.setUserName(orders.getUserName());
    		orderDelivery.setCenterId(orders.getCenterId());
    		orderDelivery.setUserByCenterId(orders.getUserByCenterId());
    		orderDelivery.setPrice(ArithUtil.mul(totalPrice, orders.getDiscount()));
    		orderDelivery.setOrderType(orders.getOrderType());
    		orderDelivery.setState(1);
    		orderDelivery.setDiscount(orders.getDiscount());
    		orderDelivery.setAdminByConfirmId(adminName);
    		orderDelivery.setPv(ArithUtil.mul(totalPv,orders.getDiscount()));
    		orderDelivery.setUserByDeclarationId(orders.getUserByDeclarationId());
    		orderDelivery.setPhone(orders.getPhone());
    		orderDelivery.setReceiver(orders.getReceiver());
    		orderDelivery.setOrderTime(date);
    		orderDelivery.setAddress(orders.getAddress());
    		int tn = orders.getTotalNum()-orders.getDeliveryNum();
    		if(tn>=0){
    			int state = orders.getState();
				if(tn==0) state=3;
    				Order order1 =new Order();
    				order1.setId(orders.getId());
    				order1.setState(state);
    				order1.setDeliveryNum(orders.getDeliveryNum());
    				Integer up1 = orderMapper.update(order1);
    				if(up1!=null&&up1>0){
    					Map<String,Object> params = new HashMap<>();
    	    			params.put("list",odlist);
    					Integer up2 = orderDeliveryDetailMapper.insertAll(params);
    					if(up2!=null&up2>0){
    						Integer up3 = orderDeliveryMapper.save(orderDelivery);
    						if(up3!=null&&up3>0){
    							int t=olist1.size();
    							for(int i=0;i<olist1.size();i++){
    								Integer u =orderDetailProductMapper.update(olist1.get(i));
    								if(u!=null&&u>0) t--; 
    							}
    							if(t==0){
    							msg="配货订单保存成功。";
    				    		sqlSession.commit();
    							}else{
    								msg = "产品详情数量更新失败";
        							sqlSession.rollback();
    							}
    						}else{
    							msg = "配货订单保存失败";
    							sqlSession.rollback();
    						}
    					}else{
							msg = "配货订单详情保存失败";
							sqlSession.rollback();
						}
    				}else{
						msg = "订单配货数量更新失败";
						sqlSession.rollback();
					}
    		}else{
				msg = "订单配货总数超出了订单总数。";
				sqlSession.rollback();
			}
    		}else{
				msg = error;
				sqlSession.rollback();
			}
    		}else{
				msg ="未获取配货的相应信息。";
				sqlSession.rollback();
			}
    		}else{
				msg = "订单信息获取失败。";
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
    
    public List<OrderDetailProduct> findOrderDetailProductByList(Order order){
		Map<String,Object> params = new HashMap<>();
		params.put("order",order);
		List<OrderDetailProduct> orders =orderMapper.selectOrderDetailProductByList(params);
		sqlSession.close();
		return orders;
	}
    
    public String saveEmoneyOrders(User user,String userId,String addressId,String centerId,String[] pid,String[] numstr,String rid,Timestamp date){
    	String str ="";
    	try{
    		User user1 = userMapper.selectByUserIdForUpdate(userId);
    		if(user1!=null){
    			if(user1.getPayTag()==1){
    				Center center = centerMapper.selectByCenterId(centerId);
    				if(center!=null){
    				Param pdis = paramMapper.selectByName("零售购物折扣");
    				if(pdis!=null){
    					
    					double discount = 0;
						if(user1.getRank()>0){
							discount =  pdis.getAmount_5();
						}else if(user1.getRankJoin()==5){
							discount =  pdis.getAmount_5();
						}else if(user1.getRankJoin()==4){
							discount =  pdis.getAmount_4();
						}else if(user1.getRankJoin()==3){
							discount =  pdis.getAmount_3();
						}else if(user1.getRankJoin()==2){
							discount =  pdis.getAmount_2();
						}else if(user1.getRankJoin()==1){
							discount =  pdis.getAmount_1();
						}
    			Address adr = adrMapper.selectById(Integer.valueOf(addressId));
    			if(adr!=null){
				Order orders = new Order();
				orders.setOrderId(rid);
				orders.setuId(user1.getId());
				orders.setUserId(user1.getUserId());
				orders.setUserName(user1.getUserName());
				orders.setOrderType(1);
				orders.setAddress(adr.getProvince()+adr.getCity()+adr.getArea()+adr.getAddress());
				orders.setReceiver(adr.getReceiver());
				orders.setPhone(adr.getPhone());
				orders.setState(1);
				orders.setOrderType(2);
				orders.setOrderTime(date);
				orders.setUserByDeclarationId(user.getUserId());
				orders.setCenterId(center.getuId());
				orders.setUserByCenterId(center.getUserId());
    		List<OrderDetail> olist = new ArrayList<OrderDetail>();
			double totalprice = 0;
			double totalpv = 0;
			int multiple = 1;
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
							if(multiple>1){
								od.setNum(od.getNum()*multiple);
							}
							olist.add(od);
							totalprice = ArithUtil.add(totalprice
									,od.getPrice());
							totalpv = ArithUtil.add(totalpv , od.getPv());
							
						}
					}
				}
			}
			totalprice = ArithUtil.mul(totalprice, discount);
			totalpv = ArithUtil.mul(totalpv, discount);
			orders.setPrice(totalprice);
			orders.setPv(totalpv);
			orders.setDiscount(discount);
			if(ArithUtil.sub(user1.getEmoney(), totalprice)>=0){
				Promotion pro = proMapper.selectByName("母亲节促销");
				if(pro!=null){
					if(orders.getOrderTime().getTime()-pro.getStartTime().getTime()>=0&&orders.getOrderTime().getTime()-pro.getEndTime().getTime()<0){
					if(totalprice>=10000){
						Product p = productMapper.selectByProductId("zp0011");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
					}else if(totalprice>=5000){
						Product p = productMapper.selectByProductId("zp0002");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
					}else if(totalprice>=1500){
						Product p = productMapper.selectByProductId("zp0008");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
					}else if(totalprice>=1000){
						Product p = productMapper.selectByProductId("zp0007");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
						p = productMapper.selectByProductId("zp0006");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
					}else if(totalprice>=500){
						Product p = productMapper.selectByProductId("zp0007");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(2);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
					}
					
					}
				}
				
				pro = proMapper.selectByName("5.20活动促销");
				if(pro!=null){
					if(orders.getOrderTime().getTime()-pro.getStartTime().getTime()>=0&&orders.getOrderTime().getTime()-pro.getEndTime().getTime()<0){
					if(totalprice>=520){
						Product p = productMapper.selectByProductId("zp0012");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
						p = productMapper.selectByProductId("zp0006");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
					}
					}
				}
				orders.setPrice(totalprice);
				orders.setPv(totalpv);
				AccountDetail ad  = new AccountDetail();
				ad.setUid(user1.getId());
	    		ad.setUserId(user1.getUserId());
	    		ad.setUserName(user1.getUserName());
	    		ad.setAmount(totalprice);
	    		ad.setBalance(ArithUtil.sub(user1.getEmoney(), totalprice));
	    		ad.setTradeType("零售购物");
	    		ad.setSummary("零售购物");
	    		ad.setPayType(2);
	    		ad.setEntryTime(orders.getOrderTime());
	    		User user_d = new User();
	    		user_d.setId(user1.getId());
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
    	    		str="收货地址信息获取失败。";
    	    		sqlSession.rollback();
    			}
    				 }else{
     			    	str ="购物折扣获取失败。";
     		 }
    				}else{
     			    	str ="服务中心信息获取失败。";
     		 }
    			 }else{
    			    	str ="会员信息获取失败。";
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
    
    public String saveDmoneyOrders(User user,String userId,String addressId,String centerId,String[] pid,String[] numstr,String rid,Timestamp date){
    	String str ="";
    	try{
    		User user1 = userMapper.selectByUserIdForUpdate(userId);
    		if(user1!=null){
    			if(user1.getPayTag()==1){
    				Center center = centerMapper.selectByCenterId(centerId);
    				if(center!=null){
    			Address adr = adrMapper.selectById(Integer.valueOf(addressId));
    			if(adr!=null){
    			
				Order orders = new Order();
				orders.setOrderId(rid);
				orders.setuId(user1.getId());
				orders.setUserId(user1.getUserId());
				orders.setUserName(user1.getUserName());
				orders.setAddress(adr.getProvince()+adr.getCity()+adr.getArea()+adr.getAddress());
				orders.setReceiver(adr.getReceiver());
				orders.setPhone(adr.getPhone());
				orders.setState(1);
				orders.setOrderType(3);
				orders.setOrderTime(date);
				orders.setUserByDeclarationId(user.getUserId());
				orders.setCenterId(center.getuId());
				orders.setUserByCenterId(center.getUserId());
    		List<OrderDetail> olist = new ArrayList<OrderDetail>();
			double totalprice = 0;
			double totalpv = 0;
			int multiple = 1;
			int z_num = 0;
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
							if(multiple>1){
								od.setNum(od.getNum()*multiple);
							}
							olist.add(od);
							totalprice = ArithUtil.add(totalprice
									,od.getPrice());
							totalpv = ArithUtil.add(totalpv , od.getPv());
							if(pd.getProductId().equals("RYP0010")) z_num=od.getNum();
						}
					}
				}
			}
			orders.setPrice(totalprice);
			orders.setPv(totalpv);
			if(ArithUtil.sub(user1.getDmoney(), totalprice)>=0){
				Promotion pro = proMapper.selectByName("护肤品促销(五月)");
				if(pro!=null){
					if(orders.getOrderTime().getTime()-pro.getStartTime().getTime()>=0&&orders.getOrderTime().getTime()-pro.getEndTime().getTime()<0){
					if(z_num>=2){
						int pnum = (int) Math.floor(ArithUtil.div(z_num, 2));
						Product p = productMapper.selectByProductId("zp0013");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(pnum);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
					}
					}
				}
				
				pro = proMapper.selectByName("母亲节促销");
				if(pro!=null){
					if(orders.getOrderTime().getTime()-pro.getStartTime().getTime()>=0&&orders.getOrderTime().getTime()-pro.getEndTime().getTime()<0){
					if(totalprice>=10000){
						Product p = productMapper.selectByProductId("zp0011");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
					}else if(totalprice>=5000){
						Product p = productMapper.selectByProductId("zp0002");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
					}else if(totalprice>=1500){
						Product p = productMapper.selectByProductId("zp0008");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
					}else if(totalprice>=1000){
						Product p = productMapper.selectByProductId("zp0007");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
						p = productMapper.selectByProductId("zp0006");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
					}else if(totalprice>=500){
						Product p = productMapper.selectByProductId("zp0007");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(2);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
					}
					
					}
				}
				pro = proMapper.selectByName("5.20活动促销");
				if(pro!=null){
					if(orders.getOrderTime().getTime()-pro.getStartTime().getTime()>=0&&orders.getOrderTime().getTime()-pro.getEndTime().getTime()<0){
					if(totalprice>=520){
						Product p = productMapper.selectByProductId("zp0012");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
						p = productMapper.selectByProductId("zp0006");
						if(p!=null){
							OrderDetail od = new OrderDetail();
							od.setPid(p.getId());
							od.setOrderId(orders.getOrderId());
							od.setNum(1);
							od.setProductId(p.getProductId());
							od.setProductName(p.getProductName());
							od.setProductPrice((double) 0);
							od.setProductPv((double) 0);
							od.setProductType(p.getProductType());
							od.setType(p.getType());
							od.setSpecification(p.getSpecification());
							od.setPrice(ArithUtil.mul(od.getProductPrice()
									, od.getNum()));
							od.setPv(ArithUtil.mul(od.getProductPv()
									, od.getNum()));
							olist.add(od);
						}
					}
					}
				}
				orders.setPrice(totalprice);
				orders.setPv(totalpv);
				AccountDetail ad  = new AccountDetail();
    			ad.setUid(user1.getId());
	    		ad.setUserId(user1.getUserId());
	    		ad.setUserName(user1.getUserName());
	    		ad.setAmount(totalprice);
	    		ad.setBalance(ArithUtil.sub(user1.getDmoney(), totalprice));
	    		ad.setTradeType("复消购物");
	    		ad.setSummary("复消购物");
	    		ad.setPayType(2);
	    		ad.setEntryTime(orders.getOrderTime());
	    		User user_d = new User();
	    		user_d.setId(user1.getId());
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
    	    		str="收货地址信息获取失败。";
    	    		sqlSession.rollback();
    			}
    				}else{
        	    		str="服务中心信息获取失败。";
        	    		sqlSession.rollback();
        			}
    			 }else{
    			    	str ="会员信息获取失败。";
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
    
    public String  orderBack(String orderId,String adminName){
    	String msg = "";
    	try{
    		Order order = orderMapper.selectByOrderIdForUpdate(orderId);
    		if(order.getState()==1){
	    		Integer result = orderMapper.updateForState(0, order.getId());
	    		if(result!=null){
	    		User user1 = userMapper.selectByUserIdForUpdate(order.getUserId());
	    		if(user1!=null){
	    			
	    			Timestamp date = new Timestamp(new Date().getTime());
	    			if(order.getOrderType()==2){
	    		
	    			AccountDetail ad  = new AccountDetail();
	    			ad.setUid(user1.getId());
	    			ad.setUserId(user1.getUserId());
		    		ad.setUserName(user1.getUserName());
		    		ad.setAmount(order.getPrice());
		    		ad.setBalance(ArithUtil.add(user1.getEmoney(), order.getPrice()));
		    		ad.setTradeType("购物退回");
		    		ad.setSummary("零售购物退回");
		    		ad.setPayType(1);
		    		ad.setEntryTime(date);
		    		User user_d = new User();
		    		user_d.setId(user1.getId());
		    		user_d.setEmoney(ad.getBalance());
		    		Map<String,Object> params = new HashMap<>();
	    			params.put("account",ad);
	    			params.put("tableName", Constants.EMONEYDETAIL_TABLE);
	    			if(userMapper.updateUser(user_d)>0){
	    	    		if(moneyMapper.save(params)>0){
	    	    		msg="会员零售购物退单成功，订单编号为："+orderId;
	    	    		AdminLog log = new AdminLog();
						log.setAdminName(adminName);
						log.setContents(msg);
						log.setEntryTime(date);
						log.setType(ConstantsLog.LOGTYPE_9);
						adminLogMapper.save(log);
	    	    		sqlSession.commit();
	    			}else{
	    				msg="会员信息更新失败。";
	    				sqlSession.rollback();
	    			}
	    			}else{
	    				msg="会员资金明细更新失败。";
	    				sqlSession.rollback();
	    			}
	    
	    		}else if(order.getOrderType()==3){
	    		
	    			AccountDetail ad  = new AccountDetail();
	    			ad.setUid(user1.getId());
		    		ad.setUserId(user1.getUserId());
		    		ad.setUserName(user1.getUserName());
		    		ad.setAmount(order.getPrice());
		    		ad.setBalance(ArithUtil.add(user1.getDmoney(), order.getPrice()));
		    		ad.setTradeType("购物退回");
		    		ad.setSummary("复消购物退回");
		    		ad.setPayType(1);
		    		ad.setEntryTime(date);
		    		User user_d = new User();
		    		user_d.setId(user1.getId());
		    		user_d.setDmoney(ad.getBalance());
		    		Map<String,Object> params = new HashMap<>();
	    			params.put("account",ad);
	    			params.put("tableName", Constants.DMONEYDETAIL_TABLE);
	    			if(userMapper.updateUser(user_d)>0){
	    	    		if(moneyMapper.save(params)>0){
	    	    			
	    	    		msg="会员复消购物退单成功，订单编号为："+orderId;
	    	    		AdminLog log = new AdminLog();
						log.setAdminName(adminName);
						log.setContents(msg);
						log.setEntryTime(date);
						log.setType(ConstantsLog.LOGTYPE_9);
						adminLogMapper.save(log);
	    	    		sqlSession.commit();
	    			}else{
	    				msg="会员信息更新失败。";
	    				sqlSession.rollback();
	    			}
	    			}else{
	    				msg="会员资金明细更新失败。";
	    				sqlSession.rollback();
	    			}
	    
	    		}else{
	    			msg="该订单不能进行退单.";
	    		}
	    		
    		}else{
    			msg="会员信息获取失败。";
    			
    		}
	    		}else{
	    			msg="当前状态不能进行退单。";
	    			
	    		}
    		}else{
    			msg="订单信息获取失败。";
    			
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		sqlSession.rollback();
    	}finally{
    		sqlSession.close();
    	}
  		return msg;
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
    
    public List<Product>  orderDetailDeliverySummary(String sql){
   	 List<Product> ptlist = new ArrayList<Product>();
   	 
   	try{
   		List<OrderDeliveryDetail> olist = orderMapper.selectDeliveryDetailBySql(sql);
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
}
