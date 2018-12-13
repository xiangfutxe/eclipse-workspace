package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Dept;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDelivery;
import com.ssm.utils.Constants;

public class OrderDeliveryDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.ORDERDELIVERYTABLE);
			if(params.get("order_delivery")!=null){
				OrderDelivery order_delivery = (OrderDelivery) params.get("order_delivery");
				if(order_delivery.getUserId()!=null&&!order_delivery.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{order_delivery.userId},'%')");
				}
				if(order_delivery.getUserByCenterId()!=null&&!order_delivery.getUserByCenterId().equals("")){
					WHERE(" userByCenterId LIKE CONCAT ('%',#{order_delivery.userByCenterId},'%')");
				}
				if(order_delivery.getOrderType()!=null&&order_delivery.getOrderType()!=0 ){
					WHERE(" orderType LIKE CONCAT ('%',#{order_delivery.orderType},'%')");
				}
				if(order_delivery.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{order_delivery.state},'%')");
				}
				if(order_delivery.getStartTime()!=null){
					WHERE("  orderTime >= #{order_delivery.startTime}");
				}
				if(order_delivery.getEndTime()!=null){
					WHERE("  orderTime <= #{order_delivery.endTime}");
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
				FROM(Constants.ORDERDELIVERYTABLE);
				if(params.get("order_delivery")!=null){
					OrderDelivery order_delivery = (OrderDelivery) params.get("order_delivery");
					if(order_delivery.getUserId()!=null&&!order_delivery.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{order_delivery.userId},'%')");
					}
					if(order_delivery.getUserByCenterId()!=null&&!order_delivery.getUserByCenterId().equals("")){
						WHERE(" userByCenterId LIKE CONCAT ('%',#{order_delivery.userByCenterId},'%')");
					}
					if(order_delivery.getOrderType()!=null&&order_delivery.getOrderType()!=0 ){
						WHERE(" orderType LIKE CONCAT ('%',#{order_delivery.orderType},'%')");
					}
					if(order_delivery.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{order_delivery.state},'%')");
					}
					if(order_delivery.getStartTime()!=null){
						WHERE("  orderTime >= #{order_delivery.startTime}");
					}
					if(order_delivery.getEndTime()!=null){
						WHERE("  orderTime <= #{order_delivery.endTime}");
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
			FROM(Constants.ORDERDELIVERYTABLE);
			if(params.get("order_delivery")!=null){
				OrderDelivery order_delivery = (OrderDelivery) params.get("order_delivery");
				if(order_delivery.getUserId()!=null&&!order_delivery.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{order_delivery.userId},'%')");
				}
				if(order_delivery.getUserByCenterId()!=null&&!order_delivery.getUserByCenterId().equals("")){
					WHERE(" userByCenterId LIKE CONCAT ('%',#{order_delivery.userByCenterId},'%')");
				}
				if(order_delivery.getOrderType()!=null&&order_delivery.getOrderType()!=0 ){
					WHERE(" orderType LIKE CONCAT ('%',#{order_delivery.orderType},'%')");
				}
				if(order_delivery.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{order_delivery.state},'%')");
				}
				if(order_delivery.getStartTime()!=null){
					WHERE("  orderTime >= #{order_delivery.startTime}");
				}
				if(order_delivery.getEndTime()!=null){
					WHERE("  orderTime <= #{order_delivery.endTime}");
				}
				WHERE(" state > 0");
			}
			}
		}.toString();
		return sql;
	}
	
	public String selectDetailListWhitParam(final Map<String,Object> params){
		OrderDelivery order = (OrderDelivery) params.get("order_delivery");
		String str ="";
		if(order.getStartTime()!=null) str=str+" and orderTime>='"+order.getStartTime()+"'"; 
		if(order.getEndTime()!=null) str=str+" and orderTime<='"+order.getEndTime()+"'"; 
		String sql = "select * from order_delivery as ord,order_delivery_detail as od where ord.orderId=od.orderId"+str
				+" and ord.state>0 order by ord.orderTime desc";
		return sql;
	}
	
	public String sum( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT(" sum(price) as price,sum(pv) as pv,sum(price_cy) as price_cy,sum(pv_cy) as pv_cy ");
				FROM(Constants.ORDERDELIVERYTABLE);
				if(params.get("order_delivery")!=null){
					OrderDelivery order_delivery = (OrderDelivery) params.get("order_delivery");
					if(order_delivery.getUserId()!=null&&!order_delivery.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{order_delivery.userId},'%')");
					}
					if(order_delivery.getUserByCenterId()!=null&&!order_delivery.getUserByCenterId().equals("")){
						WHERE(" userByCenterId LIKE CONCAT ('%',#{order_delivery.userByCenterId},'%')");
					}
					if(order_delivery.getOrderType()!=null&&order_delivery.getOrderType()!=0 ){
						WHERE(" orderType LIKE CONCAT ('%',#{order_delivery.orderType},'%')");
					}
					if(order_delivery.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{order_delivery.state},'%')");
					}
					if(order_delivery.getStartTime()!=null){
						WHERE("  orderTime >= #{order_delivery.startTime}");
					}
					if(order_delivery.getEndTime()!=null){
						WHERE("  orderTime <= #{order_delivery.endTime}");
					}
				}
				
			}
		}.toString();
	}
	
	public String insertOrderDelivery(final OrderDelivery order){
		return new SQL(){
			{
				INSERT_INTO(Constants.ORDERDELIVERYTABLE);
				if(order.getOrderId()!=null&& !order.getOrderId().equals("")){
					VALUES("orderId","#{orderId}");
				}
				if(order.getToOrderId()!=null&& !order.getToOrderId().equals("")){
					VALUES("to_order_id","#{toOrderId}");
				}
				if(order.getToOid()!=null){
					VALUES("to_oid","#{toOid}");
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
				if(order.getState()!=null ){
					VALUES("state","#{state}");
				}
				if(order.getDiscount()!=null ){
					VALUES("discount","#{discount}");
				}
				if(order.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(order.getPv()!=null){
					VALUES("pv","#{pv}");
				}
				if(order.getDeliveryNum()!=null){
					VALUES("delivery_num","#{deliveryNum}");
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
				}if(order.getAdminByConfirmId()!=null&&!order.getAdminByConfirmId().equals("")){
					VALUES("adminByConfirmId","#{adminByConfirmId}");
				}if(order.getConfirmTime()!=null){
					VALUES("confirmTime","#{confirmTime}");
				}
			}
		}.toString();
	}
	
	public String updateOrderDelivery(final OrderDelivery order){
		return new SQL(){
			{
				UPDATE(Constants.ORDERDELIVERYTABLE);
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
				}if(order.getExpress()!=null&&!order.getExpress().equals("")){
					SET("express=#{express}");
				}if(order.getExpressNum()!=null&&!order.getExpressNum().equals("")){
					SET("expressNum=#{expressNum}");
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
				}
				if(order.getState()!=null){
					SET("state=#{state}");
				}
				if(order.getPrice()!=null){
					SET("price=#{price}");
				}
				if(order.getState()!=null){
					SET("pv=#{pv}");
				}
				if(order.getDiscount()!=null ){
					SET("discount=#{discount}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	
	public String  selectListBySql(String sql){
		return sql;
	}
}
