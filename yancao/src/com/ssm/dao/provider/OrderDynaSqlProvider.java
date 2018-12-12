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
				if(order.getOrderId()!=null&&!order.getOrderId().equals("")){
					WHERE(" order_id LIKE CONCAT ('%',#{order.orderId},'%')");
				}
				if(order.getUserId()!=null&&!order.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{order.userId},'%')");
				}
				if(order.getRefereeId()!=null){
					WHERE(" referee_id LIKE CONCAT ('%',#{order.refereeId},'%')");
				}
				if(order.getUserName()!=null&&!order.getUserName().equals("")){
					WHERE(" user_name LIKE CONCAT ('%',#{order.userName},'%')");
				}
				if(order.getOrderType()!=null&&order.getOrderType()!=0 ){
					WHERE(" order_type LIKE CONCAT ('%',#{order.orderType},'%')");
				}
				if(order.getPayType()!=null&&order.getPayType()!=0 ){
					WHERE(" pay_type LIKE CONCAT ('%',#{order.payType},'%')");
				}
				if(order.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{order.state},'%')");
				}
				if(order.getStartTime()!=null){
					WHERE("  order_time >= #{order.startTime}");
				}
				if(order.getEndTime()!=null){
					WHERE("  order_time <= #{order.endTime}");
				}
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
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
					if(order.getOrderId()!=null&&!order.getOrderId().equals("")){
						WHERE(" order_id LIKE CONCAT ('%',#{order.orderId},'%')");
					}
					if(order.getUserId()!=null&&!order.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{order.userId},'%')");
					}
					if(order.getRefereeId()!=null){
						WHERE(" referee_id LIKE CONCAT ('%',#{order.refereeId},'%')");
					}
					if(order.getUserName()!=null&&!order.getUserName().equals("")){
						WHERE(" user_name LIKE CONCAT ('%',#{order.userName},'%')");
					}
					if(order.getOrderType()!=null&&order.getOrderType()!=0 ){
						WHERE(" order_type LIKE CONCAT ('%',#{order.orderType},'%')");
					}
					if(order.getPayType()!=null&&order.getPayType()!=0 ){
						WHERE(" pay_type LIKE CONCAT ('%',#{order.payType},'%')");
					}
					if(order.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{order.state},'%')");
					}
					if(order.getStartTime()!=null){
						WHERE("  order_time >= #{order.startTime}");
					}
					if(order.getEndTime()!=null){
						WHERE("  order_time <= #{order.endTime}");
					}
				}
				
			}
		}.toString();
		return sql;
	}
	
	public String selectTagWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.ORDERTABLE);
			if(params.get("order")!=null){
				Order order = (Order) params.get("order");
				if(order.getOrderId()!=null&&!order.getOrderId().equals("")){
					WHERE(" order_id LIKE CONCAT ('%',#{order.orderId},'%')");
				}
				if(order.getUserId()!=null&&!order.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{order.userId},'%')");
				}
				if(order.getRefereeId()!=null){
					WHERE(" referee_id =#{order.refereeId}");
				}
				if(order.getUserName()!=null&&!order.getUserName().equals("")){
					WHERE(" user_name LIKE CONCAT ('%',#{order.userName},'%')");
				}
				if(order.getOrderType()!=null&&order.getOrderType()!=0 ){
					WHERE(" order_type LIKE CONCAT ('%',#{order.orderType},'%')");
				}
				if(order.getPayType()!=null&&order.getPayType()!=0 ){
					WHERE(" pay_type LIKE CONCAT ('%',#{order.payType},'%')");
				}
				if(order.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{order.state},'%')");
				}
				if(order.getStartTime()!=null){
					WHERE("  order_time >= #{order.startTime}");
				}
				if(order.getEndTime()!=null){
					WHERE("  order_time <= #{order.endTime}");
				}
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String countTag( final Map<String,Object> params){
		String sql = new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.ORDERTABLE);
				if(params.get("order")!=null){
					Order order = (Order) params.get("order");
					if(order.getOrderId()!=null&&!order.getOrderId().equals("")){
						WHERE(" order_id LIKE CONCAT ('%',#{order.orderId},'%')");
					}
					if(order.getUserId()!=null&&!order.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{order.userId},'%')");
					}
					if(order.getRefereeId()!=null){
						WHERE(" referee_id =#{order.refereeId}");
					}
					if(order.getUserName()!=null&&!order.getUserName().equals("")){
						WHERE(" user_name LIKE CONCAT ('%',#{order.userName},'%')");
					}
					if(order.getOrderType()!=null&&order.getOrderType()!=0 ){
						WHERE(" order_type LIKE CONCAT ('%',#{order.orderType},'%')");
					}
					if(order.getPayType()!=null&&order.getPayType()!=0 ){
						WHERE(" pay_type LIKE CONCAT ('%',#{order.payType},'%')");
					}
					if(order.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{order.state},'%')");
					}
					if(order.getStartTime()!=null){
						WHERE("  order_time >= #{order.startTime}");
					}
					if(order.getEndTime()!=null){
						WHERE("  order_time <= #{order.endTime}");
					}
				}
				
			}
		}.toString();
		return sql;
	}
	
	public String sumByParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT (" sum(price) price,sum(cash) cash,sum(cash_num) cashNum,sum(integral) integral ");
			FROM(Constants.ORDERTABLE);
			if(params.get("order")!=null){
				Order order = (Order) params.get("order");
				if(order.getUserId()!=null&&!order.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{order.userId},'%')");
				}
				
				if(order.getOrderType()!=null&&order.getOrderType()!=0 ){
					WHERE(" order_type LIKE CONCAT ('%',#{order.orderType},'%')");
				}
				if(order.getRefereeId()!=null){
					WHERE(" referee_id LIKE CONCAT ('%',#{order.refereeId},'%')");
				}
				if(order.getState()!=null){
					
						WHERE(" state LIKE CONCAT ('%',#{order.state},'%')");
				}
				if(order.getStartTime()!=null){
					WHERE("  order_time >= #{order.startTime}");
				}
				if(order.getEndTime()!=null){
					WHERE("  order_time <= #{order.endTime}");
				}
				WHERE(" state > 0 ");
			}
			}
		}.toString();
		return sql;
	}
	
	public String sumTagByParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT (" sum(price) price,sum(cash) cash,sum(cash_num) cashNum,sum(integral) integral ");
			FROM(Constants.ORDERTABLE);
			if(params.get("order")!=null){
				Order order = (Order) params.get("order");
				if(order.getUserId()!=null&&!order.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{order.userId},'%')");
				}
				
				if(order.getOrderType()!=null&&order.getOrderType()!=0 ){
					WHERE(" order_type LIKE CONCAT ('%',#{order.orderType},'%')");
				}
				if(order.getRefereeId()!=null){
					WHERE(" referee_id LIKE CONCAT ('%',#{order.refereeId},'%')");
				}
				if(order.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{order.state},'%')");
				}
				if(order.getStartTime()!=null){
					WHERE("  order_time >= #{order.startTime}");
				}
				if(order.getEndTime()!=null){
					WHERE("  order_time <= #{order.endTime}");
				}
				WHERE(" state > 0 ");
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
				if(order.getOrderId()!=null&&!order.getOrderId().equals("")){
					WHERE(" order_id LIKE CONCAT ('%',#{order.orderId},'%')");
				}
				if(order.getUserId()!=null&&!order.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{order.userId},'%')");
				}
				if(order.getUserId()!=null&&!order.getUserId().equals("")){
					WHERE(" user_name LIKE CONCAT ('%',#{order.userId},'%')");
				}
				if(order.getOrderType()!=null&&order.getOrderType()!=0 ){
					WHERE(" order_type LIKE CONCAT ('%',#{order.orderType},'%')");
				}
				if(order.getPayType()!=null&&order.getPayType()!=0 ){
					WHERE(" pay_type LIKE CONCAT ('%',#{order.payType},'%')");
				}
				if(order.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{order.state},'%')");
				}
				if(order.getStartTime()!=null){
					WHERE("  order_time >= #{order.startTime}");
				}
				if(order.getEndTime()!=null){
					WHERE("  order_time <= #{order.endTime}");
				}
			}
			}
		}.toString();
		return sql;
	}
	
	public String selectDetailListWhitParam(final Map<String,Object> params){
		Order order = (Order) params.get("order");
		String str ="";
		if(order.getStartTime()!=null) str=str+" and order_time>='"+order.getStartTime()+"'"; 
		if(order.getEndTime()!=null) str=str+" and order_time<='"+order.getEndTime()+"'"; 
		if(order.getOrderType()!=null) str=str+" and order_type='"+order.getOrderType()+"'"; 

		String sql = "select od.id id,ord.order_id orderId,ord.userId userId,ord.state state,od.pid pid, od.product_id productId,od.product_name productName,"
				+ "od.specification specification,od.type type,od.productPrice productPrice,od.productPv productPv,od.price price,od.pv pv,od.num num from orders as ord,orderDetail as od where ord.orderId=od.orderId"+str
				+" and ord.state>0 order by ord.orderTime desc";
		return sql;
	}
	
	public String selectDetailProductListWhitParam(final Map<String,Object> params){
		Order order = (Order) params.get("order");
		String str ="";
		if(order.getStartTime()!=null) str=str+" and order_time>='"+order.getStartTime()+"'"; 
		if(order.getEndTime()!=null) str=str+" and order_time<='"+order.getEndTime()+"'"; 
		if(order.getOrderType()!=null) str=str+" and order_type='"+order.getOrderType()+"'"; 

		String sql = "select od.id id,ord.order_id orderId,ord.user_id userId,ord.state state,od.od_id odId,od.pid pid, od.productId productId,od.productName productName,"
				+ "od.specification specification,od.type type,od.productPrice productPrice,od.productPv productPv,od.price price,od.pv pv,od.num num, od.delivery_num deliveryNum from orders as ord,order_detail_product as od where ord.orderId=od.orderId"+str
				+" and ord.state>0 order by ord.order_time desc";
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
						WHERE(" user_id LIKE CONCAT ('%',#{order.userId},'%')");
					}
					
					if(order.getOrderType()!=null&&order.getState()!=0){
						WHERE(" order_type LIKE CONCAT ('%',#{order.orderType},'%')");
					}
					if(order.getPayType()!=null&&order.getPayType()!=0 ){
						WHERE(" pay_type LIKE CONCAT ('%',#{order.payType},'%')");
					}
					if(order.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{order.state},'%')");
					}
					if(order.getStartTime()!=null){
						WHERE("  order_time >= #{order.startTime}");
					}
					if(order.getEndTime()!=null){
						WHERE("  order_time <= #{order.endTime}");
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
					VALUES("order_id","#{orderId}");
				}
				if(order.getUserId()!=null&& !order.getUserId().equals("")){
					VALUES("user_id","#{userId}");
				}
				if(order.getUserName()!=null&& !order.getUserName().equals("")){
					VALUES("user_name","#{userName}");
				}
				if(order.getProvince()!=null&& !order.getProvince().equals("")){
					VALUES("province","#{province}");
				}
				if(order.getCity()!=null&& !order.getCity().equals("")){
					VALUES("city","#{city}");
				}
				if(order.getArea()!=null&& !order.getArea().equals("")){
					VALUES("area","#{area}");
				}
				if(order.getAddress()!=null&& !order.getAddress().equals("")){
					VALUES("address","#{address}");
				}
				if(order.getUid()!=null){
					VALUES("uid","#{uid}");
				}
				if(order.getOrderType()!=null ){
					VALUES("order_type","#{orderType}");
				}
				if(order.getPrice()!=null ){
					VALUES("price","#{price}");
				}
				if(order.getCash()!=null ){
					VALUES("cash","#{cash}");
				}
				if(order.getCashNum()!=null ){
					VALUES("cash_num","#{cashNum}");
				}
				if(order.getIntegral()!=null ){
					VALUES("integral","#{integral}");
				}
				if(order.getGiveCash()!=null ){
					VALUES("give_cash","#{giveCash}");
				}
				if(order.getGiveCashNum()!=null ){
					VALUES("give_cash_num","#{giveCashNum}");
				}
				if(order.getGiveIntegral()!=null ){
					VALUES("give_integral","#{giveIntegral}");
				}
				if(order.getRetailPrice()!=null ){
					VALUES("retail_price","#{retailPrice}");
				}
				if(order.getPayType()!=null ){
					VALUES("pay_type","#{payType}");
				}
				if(order.getState()!=null ){
					VALUES("state","#{state}");
				}
				if(order.getRank()!=null){
					VALUES("rank","#{rank}");
				}
				if(order.getRefereeId()!=null){
					VALUES("referee_id","#{refereeId}");
				}
				if(order.getOrderTime()!=null){
					VALUES("order_time","#{orderTime}");
				}
			}
		}.toString();
	}
	
	public String updateOrder(final Order order){
		return new SQL(){
			{
				UPDATE(Constants.ORDERTABLE);
				if(order.getReviewerId()!=null&&!order.getReviewerId().equals("")){
					SET("reviewer_id=#{reviewerId}");
				}
				if(order.getReviewTime()!=null){
					SET("review_time=#{reviewTime}");
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
