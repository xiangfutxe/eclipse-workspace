package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Dept;
import com.ssm.pojo.Order;
import com.ssm.utils.Constants;

public class OrderDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.ORDERTABLE);
			if(params.get("order")!=null){
				Order order = (Order) params.get("order");
				if(order.getUserId()!=null&&!order.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{order.userId},'%')");
				}
				if(order.getUserByCenterId()!=null&&!order.getUserByCenterId().equals("")){
					WHERE(" userByCenterId LIKE CONCAT ('%',#{order.userByCenterId},'%')");
				}
				if(order.getUserByDeclarationId()!=null&&!order.getUserByDeclarationId().equals("")){
					WHERE(" userByDeclarationId LIKE CONCAT ('%',#{order.userByDeclarationId},'%')");
				}
				if(order.getOrderType()!=null&&order.getOrderType()!=0 ){
					WHERE(" orderType LIKE CONCAT ('%',#{order.orderType},'%')");
				}
				if(order.getState()!=null){
					if(order.getState()==10){
						WHERE(" state > 0 ");
					}else{
					WHERE(" state LIKE CONCAT ('%',#{order.state},'%')");
					}
				}
				if(order.getStartTime()!=null){
					WHERE("  orderTime >= #{order.startTime}");
				}
				if(order.getEndTime()!=null){
					WHERE("  orderTime <= #{order.endTime}");
				}
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by orderTime desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		String sql = new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.ORDERTABLE);
				if(params.get("order")!=null){
					Order order = (Order) params.get("order");
					if(order.getUserId()!=null&&!order.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{order.userId},'%')");
					}
					if(order.getUserByCenterId()!=null&&!order.getUserByCenterId().equals("")){
						WHERE(" userByCenterId LIKE CONCAT ('%',#{order.userByCenterId},'%')");
					}
					if(order.getUserByDeclarationId()!=null&&!order.getUserByDeclarationId().equals("")){
						WHERE(" userByDeclarationId LIKE CONCAT ('%',#{order.userByDeclarationId},'%')");
					}
					if(order.getOrderType()!=null&&order.getOrderType()!=0 ){
						WHERE(" orderType LIKE CONCAT ('%',#{order.orderType},'%')");
					}
					if(order.getState()!=null ){
						if(order.getState()==10){
							WHERE(" state > 0 ");
						}else{
						WHERE(" state LIKE CONCAT ('%',#{order.state},'%')");
						}
					}
					if(order.getStartTime()!=null){
						WHERE(" orderTime >= #{order.startTime}");
					}
					if(order.getEndTime()!=null){
						WHERE(" orderTime <= #{order.endTime}");
					}
				}
				
			}
		}.toString();
		return sql;
	}
	
	public String sumByParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT (" sum(price) price,sum(pv) pv ");
			FROM(Constants.ORDERTABLE);
			if(params.get("order")!=null){
				Order order = (Order) params.get("order");
				if(order.getUserId()!=null&&!order.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{order.userId},'%')");
				}
				if(order.getUserByCenterId()!=null&&!order.getUserByCenterId().equals("")){
					WHERE(" userByCenterId LIKE CONCAT ('%',#{order.userByCenterId},'%')");
				}
				if(order.getOrderType()!=null&&order.getOrderType()!=0 ){
					WHERE(" orderType LIKE CONCAT ('%',#{order.orderType},'%')");
				}
				if(order.getState()!=null){
					if(order.getState()==10){
						WHERE(" state > 0 ");
					}else{
						WHERE(" state LIKE CONCAT ('%',#{order.state},'%')");
					}
				}
				if(order.getStartTime()!=null){
					WHERE("  orderTime >= #{order.startTime}");
				}
				if(order.getEndTime()!=null){
					WHERE("  orderTime <= #{order.endTime}");
				}
			}
			}
		}.toString();
		return sql;
	}
	
	public String selectListWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.ORDERTABLE);
			if(params.get("order")!=null){
				Order order = (Order) params.get("order");
				if(order.getUserId()!=null&&!order.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{order.userId},'%')");
				}
				if(order.getUserByCenterId()!=null&&!order.getUserByCenterId().equals("")){
					WHERE(" userByCenterId LIKE CONCAT ('%',#{order.userByCenterId},'%')");
				}
				if(order.getOrderType()!=null&&order.getOrderType()!=0 ){
					WHERE(" orderType LIKE CONCAT ('%',#{order.orderType},'%')");
				}
				if(order.getState()!=null){
					if(order.getState()==10){
						WHERE(" state > 0 ");
					}else{
						WHERE(" state LIKE CONCAT ('%',#{order.state},'%')");
					}
				}
				if(order.getStartTime()!=null){
					WHERE("  orderTime >= #{order.startTime}");
				}
				if(order.getEndTime()!=null){
					WHERE("  orderTime <= #{order.endTime}");
				}
				WHERE(" state > 0");
				}
			}
		}.toString();
		System.out.println(sql);
		return sql;
	}
	
	public String selectDetailListWhitParam(final Map<String,Object> params){
		Order order = (Order) params.get("order");
		String str ="";
		if(order.getStartTime()!=null) str=str+" and orderTime>='"+order.getStartTime()+"'"; 
		if(order.getEndTime()!=null) str=str+" and orderTime<='"+order.getEndTime()+"'"; 
		if(order.getOrderType()!=null) str=str+" and orderType='"+order.getOrderType()+"'"; 

		String sql = "select od.id id,ord.orderId orderId,ord.userId userId,ord.state state,od.pid pid, od.productId productId,od.productName productName,"
				+ "od.specification specification,od.type type,od.productPrice productPrice,od.productPv productPv,od.price price,od.pv pv,od.num num from orders as ord,orderDetail as od where ord.orderId=od.orderId"+str
				+" and ord.state>0 order by ord.orderTime desc";
		return sql;
	}
	
	public String selectDetailProductListWhitParam(final Map<String,Object> params){
		Order order = (Order) params.get("order");
		String str ="";
		if(order.getStartTime()!=null) str=str+" and orderTime>='"+order.getStartTime()+"'"; 
		if(order.getEndTime()!=null) str=str+" and orderTime<='"+order.getEndTime()+"'"; 
		if(order.getOrderType()!=null) str=str+" and orderType='"+order.getOrderType()+"'"; 

		String sql = "select od.id id,ord.orderId orderId,ord.userId userId,ord.state state,od.od_id odId,od.pid pid, od.productId productId,od.productName productName,"
				+ "od.specification specification,od.type type,od.productPrice productPrice,od.productPv productPv,od.price price,od.pv pv,od.num num, od.delivery_num deliveryNum from orders as ord,order_detail_product as od where ord.orderId=od.orderId"+str
				+" and ord.state>0 order by ord.orderTime desc";
		return sql;
	}
	
	public String sum( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT(" sum(price) as price,sum(pv) as pv,sum(price_cy) as price_cy,sum(pv_cy) as pv_cy ");
				FROM(Constants.ORDERTABLE);
				if(params.get("order")!=null){
					Order order = (Order) params.get("order");
					if(order.getUserId()!=null&&!order.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{order.userId},'%')");
					}
					if(order.getUserByCenterId()!=null&&!order.getUserByCenterId().equals("")){
						WHERE(" userByCenterId LIKE CONCAT ('%',#{order.userByCenterId},'%')");
					}
					if(order.getOrderType()!=null&&order.getState()!=0){
						WHERE(" orderType LIKE CONCAT ('%',#{order.orderType},'%')");
					}
					if(order.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{order.state},'%')");
					}
					if(order.getStartTime()!=null){
						WHERE("  orderTime >= #{order.startTime}");
					}
					if(order.getEndTime()!=null){
						WHERE("  orderTime <= #{order.endTime}");
					}
				}
				
			}
		}.toString();
	}
	
	public String insertOrder(final Order order){
		return new SQL(){
			{
				INSERT_INTO(Constants.ORDERTABLE);
				if(order.getOrderId()!=null&& !order.getOrderId().equals("")){
					VALUES("orderId","#{orderId}");
				}
				if(order.getUserId()!=null&& !order.getUserId().equals("")){
					VALUES("userId","#{userId}");
				}
				if(order.getUserName()!=null&& !order.getUserName().equals("")){
					VALUES("userName","#{userName}");
				}
				if(order.getuId()!=null){
					VALUES("uId","#{uId}");
				}
				if(order.getUserByCenterId()!=null&&!order.getUserByCenterId().equals("")){
					VALUES("userByCenterId","#{userByCenterId}");
				}
				if(order.getUserByDeclarationId()!=null&&!order.getUserByDeclarationId().equals("")){
					VALUES("userByDeclarationId","#{userByDeclarationId}");
				}
				if(order.getCenterId()!=null ){
					VALUES("centerId","#{centerId}");
				}
				if(order.getOrderType()!=null ){
					VALUES("orderType","#{orderType}");
				}
				if(order.getDiscount()!=null ){
					VALUES("discount","#{discount}");
				}
				if(order.getState()!=null ){
					VALUES("state","#{state}");
				}
				if(order.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(order.getPv()!=null){
					VALUES("pv","#{pv}");
				}
				if(order.getPriceCy()!=null){
					VALUES("price_cy","#{priceCy}");
				}
				if(order.getPvCy()!=null){
					VALUES("pv_cy","#{pvCy}");
				}
				if(order.getOrderTime()!=null){
					VALUES("orderTime","#{orderTime}");
				}
				if(order.getReceiver()!=null&&!order.getReceiver().equals("")){
					VALUES("receiver","#{receiver}");
				}if(order.getPhone()!=null&&!order.getPhone().equals("")){
					VALUES("phone","#{phone}");
				}if(order.getAddress()!=null&&!order.getAddress().equals("")){
					VALUES("address","#{address}");
				}
			}
		}.toString();
	}
	
	public String updateOrder(final Order order){
		return new SQL(){
			{
				UPDATE(Constants.ORDERTABLE);
				if(order.getUserId()!=null&& !order.getUserId().equals("")){
					SET("userId=#{userId}");
				}
				if(order.getUserName()!=null&& !order.getUserName().equals("")){
					SET("userName=#{userName}");
				}if(order.getReceiver()!=null&&!order.getReceiver().equals("")){
					SET("receiver=#{receiver}");
				}if(order.getPhone()!=null&&!order.getPhone().equals("")){
					SET("phone=#{phone}");
				}if(order.getAddress()!=null&&!order.getAddress().equals("")){
					SET("address=#{address}");
				}if(order.getAdminByConfirmId()!=null&&!order.getAdminByConfirmId().equals("")){
					SET("adminByConfirmId=#{adminByConfirmId}");
				}
				if(order.getAdminByReviewerId()!=null&&!order.getAdminByReviewerId().equals("")){
					SET("adminByReviewerId=#{adminByReviewerId}");
				}if(order.getConfirmTime()!=null){
					SET("confirmTime=#{confirmTime}");
				}if(order.getAdminByDeliveryId()!=null&&!order.getAdminByDeliveryId().equals("")){
					SET("adminByDeliveryId=#{adminByDeliveryId}");
				}if(order.getReviewerTime()!=null){
					SET("reviewerTime=#{reviewerTime}");
				}if(order.getDeliveryTime()!=null){
					SET("deliveryTime=#{deliveryTime}");
				}if(order.getDeliveryNum()!=null){
				SET("delivery_num=#{deliveryNum}");
				}
				if(order.getDiscount()!=null ){
					SET("discount=#{discount}");
				}
				if(order.getTotalNum()!=null){
					SET("total_num=#{totalNum}");
					}
				if(order.getState()!=null){
					SET("state=#{state}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	public String selectDetailBySql(String sql){
		return sql;
	}
	

}
