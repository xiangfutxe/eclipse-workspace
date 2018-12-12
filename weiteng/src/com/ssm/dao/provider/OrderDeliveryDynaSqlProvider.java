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
					WHERE(" user_id LIKE CONCAT ('%',#{order_delivery.userId},'%')");
				}
				if(order_delivery.getOrderId()!=null&&!order_delivery.getOrderId().equals("")){
					WHERE(" order_id LIKE CONCAT ('%',#{order_delivery.orderId},'%')");
				}
				if(order_delivery.getOrderType()!=null&&order_delivery.getOrderType()!=0 ){
					WHERE(" order_type LIKE CONCAT ('%',#{order_delivery.orderType},'%')");
				}
				if(order_delivery.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{order_delivery.state},'%')");
				}
				if(order_delivery.getStartTime()!=null){
					WHERE("  order_time >= #{order_delivery.startTime}");
				}
				if(order_delivery.getEndTime()!=null){
					WHERE("  order_time <= #{order_delivery.endTime}");
				}
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by order_time desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
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
						WHERE(" user_id LIKE CONCAT ('%',#{order_delivery.userId},'%')");
					}
					if(order_delivery.getOrderId()!=null&&!order_delivery.getOrderId().equals("")){
						WHERE(" order_id LIKE CONCAT ('%',#{order_delivery.orderId},'%')");
					}
					if(order_delivery.getOrderType()!=null&&order_delivery.getOrderType()!=0 ){
						WHERE(" order_type LIKE CONCAT ('%',#{order_delivery.orderType},'%')");
					}
					if(order_delivery.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{order_delivery.state},'%')");
					}
					if(order_delivery.getStartTime()!=null){
						WHERE("  order_time >= #{order_delivery.startTime}");
					}
					if(order_delivery.getEndTime()!=null){
						WHERE("  order_time <= #{order_delivery.endTime}");
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
					WHERE(" user_id LIKE CONCAT ('%',#{order_delivery.userId},'%')");
				}
				
				if(order_delivery.getOrderType()!=null&&order_delivery.getOrderType()!=0 ){
					WHERE(" order_type LIKE CONCAT ('%',#{order_delivery.orderType},'%')");
				}
				if(order_delivery.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{order_delivery.state},'%')");
				}
				if(order_delivery.getStartTime()!=null){
					WHERE("  order_time >= #{order_delivery.startTime}");
				}
				if(order_delivery.getEndTime()!=null){
					WHERE("  order_time <= #{order_delivery.endTime}");
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
						WHERE(" user_id LIKE CONCAT ('%',#{order_delivery.userId},'%')");
					}
					
					if(order_delivery.getOrderType()!=null&&order_delivery.getOrderType()!=0 ){
						WHERE(" order_type LIKE CONCAT ('%',#{order_delivery.orderType},'%')");
					}
					if(order_delivery.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{order_delivery.state},'%')");
					}
					if(order_delivery.getStartTime()!=null){
						WHERE("  order_time >= #{order_delivery.startTime}");
					}
					if(order_delivery.getEndTime()!=null){
						WHERE("  order_time <= #{order_delivery.endTime}");
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
					VALUES("order_id","#{orderId}");
				}
				if(order.getUid()!=null){
					VALUES("uid","#{uid}");
				}
				if(order.getUserName()!=null&& !order.getUserName().equals("")){
					VALUES("user_name","#{userName}");
				}
				if(order.getUserId()!=null&& !order.getUserId().equals("")){
					VALUES("user_id","#{userId}");
				}
				if(order.getOrderType()!=null ){
					VALUES("order_type","#{orderType}");
				}
				if(order.getState()!=null ){
					VALUES("state","#{state}");
				}
				if(order.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(order.getFee()!=null){
					VALUES("fee","#{fee}");
				}
				if(order.getOrderTime()!=null){
					VALUES("order_time","#{orderTime}");
				}
				if(order.getReceiver()!=null&&!order.getReceiver().equals("")){
					VALUES("receiver","#{receiver}");
				}if(order.getProvince()!=null&&!order.getProvince().equals("")){
					VALUES("province","#{province}");
				}if(order.getCity()!=null&&!order.getCity().equals("")){
					VALUES("city","#{city}");
				}if(order.getArea()!=null&&!order.getArea().equals("")){
					VALUES("area","#{area}");
				}if(order.getPhone()!=null&&!order.getPhone().equals("")){
					VALUES("phone","#{phone}");
				}if(order.getAddress()!=null&&!order.getAddress().equals("")){
					VALUES("address","#{address}");
				}if(order.getConfirmId()!=null&&!order.getConfirmId().equals("")){
					VALUES("confirm_id","#{confirmId}");
				}if(order.getConfirmTime()!=null){
					VALUES("confirmTime","#{confirmTime}");
				}if(order.getReviewerId()!=null&&!order.getReviewerId().equals("")){
					VALUES("reviewer_id","#{reviewerId}");
				}if(order.getConfirmTime()!=null){
					VALUES("review_time","#{reviewTime}");
				}
			}
		}.toString();
	}
	
	public String updateOrderDelivery(final OrderDelivery order){
		return new SQL(){
			{
				UPDATE(Constants.ORDERDELIVERYTABLE);
				if(order.getUserId()!=null&& !order.getUserId().equals("")){
					SET("user_id=#{userId}");
				}
				if(order.getUserName()!=null&& !order.getUserName().equals("")){
					SET("user_name=#{userName}");
				}if(order.getReceiver()!=null&&!order.getReceiver().equals("")){
					SET("receiver=#{receiver}");
				}if(order.getPhone()!=null&&!order.getPhone().equals("")){
					SET("phone=#{phone}");
				}if(order.getAddress()!=null&&!order.getAddress().equals("")){
					SET("address=#{address}");
				}if(order.getExpress()!=null&&!order.getExpress().equals("")){
					SET("express=#{express}");
				}if(order.getExpressNum()!=null&&!order.getExpressNum().equals("")){
					SET("express_num=#{expressNum}");
				}if(order.getExpressCode()!=null&&!order.getExpressCode().equals("")){
					SET("express_code=#{expressCode}");
				}if(order.getReviewerId()!=null&&!order.getReviewerId().equals("")){
					SET("reviewer_id=#{reviewerId}");
				}if(order.getConfirmTime()!=null){
					SET("review_time=#{reviewTime}");
				}
				if(order.getFee()!=null){
					SET("fee=#{fee}");
				}
				if(order.getConfirmId()!=null&&!order.getConfirmId().equals("")){
					SET("confirm_id=#{confirmId}");
				}if(order.getConfirmTime()!=null){
					SET("confirm_time=#{confirmTime}");
				}
				if(order.getState()!=null){
					SET("state=#{state}");
				}
				if(order.getPrice()!=null){
					SET("price=#{price}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	
	public String  selectListBySql(String sql){
		return sql;
	}
}
